package com.dgjs.service.impl.content;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.es.mapper.content.DraftMapper;
import com.dgjs.es.mapper.content.PendingMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Draft_Status;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.PendingService;
import com.dgjs.utils.PictureUtils;
import com.dgjs.utils.StringUtils;

@Service
public class PendingServiceImpl implements PendingService{
	
//	@Autowired
//	PendingMapper pendingMapper;
//	
//	@Autowired
//	ArticlescrapMapper articlescrapMapper;
//	
//	@Autowired
//	DraftMapper draftMapper;
//	
//	@Value("${imageContextPath}${webBasePath}")
//	private String webContextPath;
//	
//	@Value("${fastFDSContextPath}")
//	private String fastFDSContextPath;
//
//	@Override
//	public int savePending(String id)  throws Exception {
//		if(StringUtils.isNullOrEmpty(id)){
//			return 0;
//		}
//		Draft draft = draftMapper.selectById(id);
//		if(draft==null){
//			return 0;
//		}
//		draft.setContent(draftMapper.getContent(id));
//		Pending pending=Pending.transFromDraft(draft);
//		if(pending==null){
//			return 0;
//		}
//		int flag = pendingMapper.savePending(pending);
//		if(flag>0){
//			draft.setDraft_status(Draft_Status.SUBMIT);
//			draftMapper.updateDraft(draft);
//		}
//		return flag;
//	}
//
//	@Override
//	public int audit(String id, Pending_Status status, Integer audit_user_id,
//			String audit_desc) throws Exception {
//		int flag = pendingMapper.audit(id, status, audit_user_id, audit_desc);
//		if(status == Pending_Status.Audit_FAIL){
//			Pending pending=pendingMapper.selectById(id);
//			Draft draft = draftMapper.selectById(pending.getDraft_id());
//			String content = draftMapper.getContent(pending.getDraft_id());
//			draft.setDraft_status(Draft_Status.NORMAL);
//			draft.setContent(content);
//			flag = draftMapper.updateDraft(draft);
//		}
//		return flag;
//	}
//
//	@Override
//	public Pending selectById(String id) {
//		return pendingMapper.selectById(id);
//	}
//
//	@Override
//	public int publish(String id, Integer publish_user_id, 
//			int visits, Date show_time,boolean isShowNow) throws Exception {
//		int flag = 0;
//		Date now = new Date();
//		Pending pending= pendingMapper.publish(id, publish_user_id, now, visits, show_time);
//		if(pending!=null){
//			Articlescrap articlescrap = Pending.transToArticlescrap(pending);
//			//如果需要立刻展示
//			if(isShowNow){
//				articlescrap.setStatus(Articlescrap_Status.UP);
//				articlescrap.setShow_time(new Date());
//			}
//			flag=articlescrapMapper.saveArticlescrap(articlescrap);
//		}
//		return flag;
//	}
//
//	@Override
//	public PageInfoDto<Pending> listPending(PendingCondition condition) {
//		return pendingMapper.listPending(condition);
//	}
//
//	@Override
//	public Pending selectByIdAll(String id) {
//		Pending pending = pendingMapper.selectByIdAll(id);
//		if(pending!=null){
//			if(pending.getStatus()==Pending_Status.PUBLISH_PENDING||pending.getStatus()==Pending_Status.PUBLISHED){
//				pending.setContent(PictureUtils.render(pending.getPictures(), pending.getContent(), fastFDSContextPath));
//			}else{
//				pending.setContent(PictureUtils.render(pending.getPictures(), pending.getContent(), webContextPath));
//			}
//		}
//		return pending;
//	}
//
//	@Override
//	public int movePic(String aid) throws Exception {
//		return pendingMapper.movePic(aid);
//	}

}
