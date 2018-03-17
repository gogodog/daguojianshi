package com.dgjs.service.admin;

import com.dgjs.model.dto.InvitationCodeDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;

public interface InvitationCodeService {

	public InvitationCode produce(Integer fromUserId);
	
	public PageInfoDto<InvitationCodeDto> list(InvitationCondition condition);
	
	public int getValidCount(Integer fromUserId);
}
