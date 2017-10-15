package com.dgjs.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AdminUserInfoMapper;
import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.service.admin.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	AdminUserInfoMapper adminUserInfoMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Override
	public AdminUserInfo getAdminUserInfo(Integer id) {
		return adminUserInfoMapper.selectById(id);
	}
	
	public AdminUser getAdminUser(Integer id){
		return adminUserMapper.selectById(id);
	}

	@Override
	public int saveOrUpdateAdminUserInfo(AdminUserInfo adminUserInfo) {
		AdminUserInfo o=adminUserInfoMapper.selectById(adminUserInfo.getId());
		if(o==null){
			return adminUserInfoMapper.save(adminUserInfo);
		}else{
			return adminUserInfoMapper.update(adminUserInfo);
		}
	}

}
