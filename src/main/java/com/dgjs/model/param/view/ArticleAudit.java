package com.dgjs.model.param.view;

import com.dgjs.model.enums.Pending_Status;

public class ArticleAudit {
	
	private String aid;//文章id
	private Pending_Status status;//状态
	private String audit_desc;//审核描述
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public Pending_Status getStatus() {
		return status;
	}
	public void setStatus(Pending_Status status) {
		this.status = status;
	}
	public String getAudit_desc() {
		return audit_desc;
	}
	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}
	
}
