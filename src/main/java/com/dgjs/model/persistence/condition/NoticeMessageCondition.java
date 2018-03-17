package com.dgjs.model.persistence.condition;

import com.dgjs.model.enums.Read_Status;

public class NoticeMessageCondition extends BasePageCondition{

	private Integer adminId;
	private Read_Status status;//状态
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Read_Status getStatus() {
		return status;
	}
	public void setStatus(Read_Status status) {
		this.status = status;
	}
	
}
