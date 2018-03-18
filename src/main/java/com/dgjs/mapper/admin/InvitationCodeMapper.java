package com.dgjs.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Invitation_Status;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;

public interface InvitationCodeMapper {

	public int save(InvitationCode invitationCode);
	
	public int update(InvitationCode invitationCode);
	
	public InvitationCode getValidCode(String invitationCode);
	
	public List<InvitationCode> list(InvitationCondition condition);

    public int count(InvitationCondition condition);
    
    public int getValidCount(Integer fromUserId);
    
    public int updateStatus(@Param("ids")List<Long> ids,@Param("status")Invitation_Status status);
}
