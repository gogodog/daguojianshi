package com.dgjs.service.transaction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.mapper.admin.NoticeMessageMapper;
import com.dgjs.mapper.content.AdminFeedBackMapper;
import com.dgjs.mapper.content.NoticeMessageRelatedMapper;
import com.dgjs.model.enums.Message_Related_Type;
import com.dgjs.model.enums.Read_Status;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.NoticeMessageRelated;
import com.dgjs.service.transaction.NtcMsgRltdTransactionService;

@Service
public class NtcMsgRltdTransactionServiceImpl implements NtcMsgRltdTransactionService{

	@Autowired
	NoticeMessageRelatedMapper noticeMessageRelatedMapper;
	
	@Autowired
	NoticeMessageMapper noticeMessageMapper;
	
	@Autowired
	AdminFeedBackMapper adminFeedBackMapper;
	
	@Transactional
	@Override
	public void saveNoticeMessageRelated(Integer adminId, String message, Long relatedId, Message_Related_Type type) {
		if(adminId == null ||StringUtils.isEmpty(message)||relatedId==null||type==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAdmin_id(adminId);
		noticeMessage.setMessage(message);
		noticeMessage.setStatus(Read_Status.UNREAD);
		int flag = noticeMessageMapper.save(noticeMessage);
		if(flag < 1){
			throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
		}
		NoticeMessageRelated noticeMessageRelated = new NoticeMessageRelated();
		noticeMessageRelated.setNotice_message_id(noticeMessage.getId());
		noticeMessageRelated.setRelated_id(relatedId);
		noticeMessageRelated.setType(type);
		flag = noticeMessageRelatedMapper.save(noticeMessageRelated);
		if(flag < 1){
			throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
		}
		if(type == Message_Related_Type.FeedBack){
			flag = adminFeedBackMapper.updateToReply(relatedId);
			if(flag < 1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
			}
		}
	}

}
