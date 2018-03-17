package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;

public interface InvitationCodeMapper {

	public int save(InvitationCode invitationCode);
	
	public int update(InvitationCode invitationCode);
	
	public InvitationCode getValidCode(String invitationCode);
	
	public List<InvitationCode> list(InvitationCondition condition);

    public int count(InvitationCondition condition);
    
    public int getValidCount(Integer fromUserId);
}
