package com.dgjs.model.dto;

import com.dgjs.model.persistence.InvitationCode;

public class InvitationCodeDto {

	private InvitationCode invitationCode;
	
	private String fromUserName;
	
	private String toUserName;

	public InvitationCode getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(InvitationCode invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	
	
}
