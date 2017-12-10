package com.dgjs.service.transaction;

import com.dgjs.model.enums.Message_Related_Type;

public interface NtcMsgRltdTransactionService {

	public void saveNoticeMessageRelated(Integer adminId,String message,Long relatedId,Message_Related_Type type);
	
}
