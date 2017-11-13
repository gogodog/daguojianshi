package com.dgjs.system.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.Constants;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.service.admin.OperateLogService;
import com.dgjs.service.common.EventService;
import com.dgjs.utils.IPUtils;

import freemarker.log.Logger;



@Component
@Aspect
public class SystemLogAspect {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	public final static String ADMIN_AOP_URL="execution(public * com.dgjs.controller.admin.*.*.*(..))";
	
	public final static String CPS_AOP_URL="execution(public * com.cps.controller.*.*(..))";
	
	public final static String ANNOTATION="@annotation(com.dgjs.annotation.LogRecord)";

	@Autowired
	private ExecutorService logTaskExecutor;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
    private EventService eventService;
	
	@Around(ANNOTATION+"("+ADMIN_AOP_URL + "||" + CPS_AOP_URL+")" )
	public Object operateLogRecord(ProceedingJoinPoint point) throws Throwable{
		Object obj = null;
		//前置操作
		Signature signature = point.getSignature();
	    MethodSignature ms = (MethodSignature)signature;
		Method method = ms.getMethod();
		LogRecord logRecord=method.getDeclaredAnnotation(LogRecord.class);
		Object[] args = point.getArgs();
		String ip = getIp();
		int event = logRecord.event();
		try{
			//执行controller方法
		    obj = point.proceed(args);
		    //保存操作日志
		    saveOperateLog(args,ip,logRecord);
		    //处理事件
		    eventService.eventHandler(event,args);
		}catch(Exception e){
			saveOperateLog(args,ip,logRecord,0,e.getMessage());
			throw e;
		}
		return obj;
	}
	
	private void saveOperateLog(Object[] args,String ip,LogRecord logRecord){
		saveOperateLog(args,ip,logRecord,1,null);
	}
	
	private void saveOperateLog(Object[] args,String ip,LogRecord logRecord,int isSuccess,String errorMessage){
		try{
             logTaskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					//保存操作日志
					OperateLog operateLog = combineOperateLog(args,ip,logRecord,isSuccess,errorMessage);
					operateLogService.save(operateLog);
				}
			});
		}catch(Exception e){
			log.error("save operateLog exception", e);
		}
	}
	
	private OperateLog combineOperateLog(Object[] args,String ip,LogRecord logRecord,int isSuccess,String errorMessage){
		OperateLog log = new OperateLog();
		log.setAdmin_id(Constants.USER_ID);
		log.setOperate_type(logRecord.operate());
		log.setOperate_desc(logRecord.remark());
		log.setIp(ip);
		List<Object> list = new ArrayList<Object>();
		
		if(args!=null && args.length>0){
			for(Object arg:args){
				if(arg instanceof HttpServletRequest){
					continue;
				}
				if(arg instanceof HttpServletResponse){
					continue;
				}
				list.add(arg);
			}
		}
		log.setParam(JSON.toJSONString(list,false));
		log.setIsSuccess(isSuccess);
		log.setErrorMessage(errorMessage);
		return log;
	}
	
	private String getIp(){
		String ip= null;
		try{
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = servletRequestAttributes.getRequest();
		    ip=IPUtils.getIpAddr3(request);
		}catch(Exception e){
			log.error("get ip excepiton", e);
		}
		return ip;
	}
	
}
