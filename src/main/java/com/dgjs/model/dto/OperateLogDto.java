package com.dgjs.model.dto;

import com.dgjs.model.persistence.OperateLog;

public class OperateLogDto {

	private OperateLog operateLog;
	private String username;
	private String realname;
	public OperateLog getOperateLog() {
		return operateLog;
	}
	public void setOperateLog(OperateLog operateLog) {
		this.operateLog = operateLog;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
    	
}
