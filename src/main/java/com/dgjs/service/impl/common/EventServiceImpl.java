package com.dgjs.service.impl.common;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.constants.EventCode;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Read_Status;
import com.dgjs.model.param.view.ArticleAudit;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.NoticeMessageService;
import com.dgjs.service.common.EventService;
import com.dgjs.service.content.DraftService;

import freemarker.log.Logger;

@Service
public class EventServiceImpl implements EventService{
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private NoticeMessageService noticeMessageService;
	
	@Autowired
	private DraftService draftService;
	
	@Autowired
	private ExecutorService eventExecutor;
	
	@Autowired
	AdminUserService adminUserService;

	@Override
	public void eventHandler(int event, Object[] args,Integer userId) {
		//不需要处理
		if(event == -1){
			return; 
		}
		try{
			eventExecutor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					processEvent(event,args,userId);
				}
				
			});
		}catch(Exception e){
			log.error("eventHandler exception,with event = "+event, e);
		}
	}
	
	private void processEvent(int event, Object[] args,Integer userId){
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
			Draft draft = draftService.selectById(articleAudit.getAid());
			String title = draft.getTitle();
			String message = null;
			if(articleAudit.getStatus()==Pending_Status.Audit_FAIL){
				 message = MessageFormat.format(EventCode.AUDIT_FAIL_TEMPLATE,title,articleAudit.getAudit_desc());
			}else if(articleAudit.getStatus()==Pending_Status.PUBLISH_PENDING){
				 message = MessageFormat.format(EventCode.AUDIT_SUCCESS_TEMPLATE,title);
			}
			noticeMessage.setMessage(message);
			noticeMessage.setAdmin_id(draft.getUser_id());
			noticeMessage.setStatus(Read_Status.UNREAD);
			noticeMessageService.save(noticeMessage);
		}
	}
	
}
