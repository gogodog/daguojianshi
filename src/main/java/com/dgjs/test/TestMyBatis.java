package com.dgjs.test;

  
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;  
import org.springframework.context.support.GenericXmlApplicationContext;
  
import com.alibaba.fastjson.JSON;  
import com.dgjs.test.dao.SysRightDao;
  
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    
    @SuppressWarnings("resource")
	public static void main(String art[]){
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath:spring-mybatis.xml");
        context.refresh();
        SysRightDao sysRightDao = context.getBean(SysRightDao.class);
        List<Map<String,Object>> sqx = sysRightDao.selectqxByUUID("0f1d24eddadc47c9beb8336048554ee9");
        System.out.println("result:" + JSON.toJSONString(sqx));
        logger.info(JSON.toJSON(sqx));
    }
    
}  