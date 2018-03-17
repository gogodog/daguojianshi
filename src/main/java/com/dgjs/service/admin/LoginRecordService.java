package com.dgjs.service.admin;

import com.dgjs.model.persistence.LoginRecord;

public interface LoginRecordService {

    public int save(LoginRecord loginRecord);
	
	public LoginRecord getLastLoginRecord(Integer adminId);
	
}
