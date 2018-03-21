package com.dgjs.service.admin;

import java.util.List;


import com.dgjs.model.dto.InvitationCodeDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Invitation_Status;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;

public interface InvitationCodeService {

	public InvitationCode produce(Integer fromUserId);
	
	public PageInfoDto<InvitationCodeDto> list(InvitationCondition condition);
	
	public int getValidCount(Integer fromUserId);
	
	 public int updateStatus(List<Long> ids,Invitation_Status status);
	
}
