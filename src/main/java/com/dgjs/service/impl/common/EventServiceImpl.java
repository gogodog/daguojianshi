package com.dgjs.service.impl.common;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.constants.Constants;
import com.dgjs.constants.EventCode;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Read_Status;
import com.dgjs.model.param.view.ArticleAudit;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.service.admin.NoticeMessageService;
import com.dgjs.service.common.EventService;
import com.dgjs.service.content.PendingService;

import freemarker.log.Logger;

@Service
public class EventServiceImpl implements EventService{
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private NoticeMessageService noticeMessageService;
	
	@Autowired
	private PendingService pendingService;
	
	@Autowired
	private ExecutorService eventExecutor;

	@Override
	public void eventHandler(int event, Object[] args) {
		//不需要处理
		if(event == -1){
			return; 
		}
		try{
			eventExecutor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					processEvent(event,args);
				}
				
			});
		}catch(Exception e){
			log.error("eventHandler exception,with event = "+event, e);
		}
	}
	
	private void processEvent(int event, Object[] args){
		//事件处理
		if(event == EventCode.AUDIT_NOTICE){
			NoticeMessage noticeMessage = new NoticeMessage();
			ArticleAudit articleAudit = null;
			for(Object obj:args){
				if(obj instanceof ArticleAudit){
					 articleAudit = (ArticleAudit) obj;
					 break;
				}
			}
			Pending pending = pendingService.selectById(articleAudit.getAid());
			String title = pending.getTitle();
			String message = null;
			if(articleAudit.getStatus()==Pending_Status.Audit_FAIL){
				 message = MessageFormat.format(EventCode.AUDIT_FAIL_TEMPLATE,title,articleAudit.getAudit_desc());
			}else if(articleAudit.getStatus()==Pending_Status.PUBLISH_PENDING){
				 message = MessageFormat.format(EventCode.AUDIT_SUCCESS_TEMPLATE,title);
			}
			noticeMessage.setMessage(message);
			noticeMessage.setAdmin_id(Constants.USER_ID);
			noticeMessage.setStatus(Read_Status.UNREAD);
			noticeMessageService.save(noticeMessage);
		}
	}
	
}
