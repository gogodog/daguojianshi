package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Pending_Status;

public class DraftAPRecord {

	private Long id;//id
	private String draft_id;//草稿id
	private Date create_time;//创建时间
	private Integer operator;//操作人
	private Pending_Status action;//操作动作
	private String operate_desc;//描述
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDraft_id() {
		return draft_id;
	}
	public void setDraft_id(String draft_id) {
		this.draft_id = draft_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public Pending_Status getAction() {
		return action;
	}
	public void setAction(Pending_Status action) {
		this.action = action;
	}
	public String getOperate_desc() {
		return operate_desc;
	}
	public void setOperate_desc(String operate_desc) {
		this.operate_desc = operate_desc;
	}
	
}
