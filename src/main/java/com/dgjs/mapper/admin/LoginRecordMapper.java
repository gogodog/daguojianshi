package com.dgjs.mapper.admin;

import com.dgjs.model.persistence.LoginRecord;

public interface LoginRecordMapper {

	public int save(LoginRecord loginRecord);
	
	public LoginRecord getLastLoginRecord(Integer adminId);
}
