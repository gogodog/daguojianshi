package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Invitation_Status;

public class InvitationCode {

	private Long id;//id
	private String invitation_code;//推荐码
	private Date valid_time;//有效时间
	private Invitation_Status status;//状态
	private Integer from_user_id;
	private Integer to_user_id;
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInvitation_code() {
		return invitation_code;
	}
	public void setInvitation_code(String invitation_code) {
		this.invitation_code = invitation_code;
	}
	public Date getValid_time() {
		return valid_time;
	}
	public void setValid_time(Date valid_time) {
		this.valid_time = valid_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getFrom_user_id() {
		return from_user_id;
	}
	public void setFrom_user_id(Integer from_user_id) {
		this.from_user_id = from_user_id;
	}
	public Integer getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(Integer to_user_id) {
		this.to_user_id = to_user_id;
	}
	public Invitation_Status getStatus() {
		return status;
	}
	public void setStatus(Invitation_Status status) {
		this.status = status;
	}
	
}
