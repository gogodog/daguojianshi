package com.dgjs.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.LoginRecordMapper;
import com.dgjs.model.persistence.LoginRecord;
import com.dgjs.service.admin.LoginRecordService;

@Service
public class LoginRecordServiceImpl implements LoginRecordService{
	
	@Autowired
	LoginRecordMapper loginRecordMapper;

	@Override
	public int save(LoginRecord loginRecord) {
		return loginRecordMapper.save(loginRecord);
	}

	@Override
	public LoginRecord getLastLoginRecord(Integer adminId) {
		return loginRecordMapper.getLastLoginRecord(adminId);
	}

}
