package com.dgjs.service.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.mapper.admin.InvitationCodeMapper;
import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.enums.Invitation_Status;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.Role;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.transaction.InvitationCodeTransactionService;

@Service
public class InvitationCodeTransactionServiceImpl implements InvitationCodeTransactionService{

	@Autowired
	InvitationCodeMapper invitationCodeMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	AdminUserService adminUserService;
	
	@Transactional
	@Override
	public void consume(Integer toUserId, String code) {
		InvitationCode invitationCode = invitationCodeMapper.getValidCode(code);
		if(invitationCode==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR,"无效的邀请码");
		}
		if(invitationCode.getFrom_user_id().intValue()==toUserId.intValue()){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR,"不能邀请自己");
		}
		AdminUser adminUser = adminUserService.getAdminUser(toUserId);
		if(adminUser==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		invitationCode.setTo_user_id(toUserId);
		invitationCode.setStatus(Invitation_Status.USED);
		int flag = invitationCodeMapper.update(invitationCode);
		if(flag < 1){
			throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
		}
		Integer fromUserId = invitationCode.getFrom_user_id();
		AdminUser parentAdminUser = adminUserService.getAdminUser(fromUserId);
		Role parentRole = roleMapper.selectById(parentAdminUser.getRole_id());
		List<Role> roleList = roleMapper.getChildrenRoles(parentRole.getRole_code());
		Integer toUserRoleID = null;
		for(Role role:roleList){
			//父角色的直系子角色
			if(parentRole.getDeep()+1==role.getDeep()){
				toUserRoleID = role.getId();
				break;
			}
		}
	    if(toUserRoleID==null){
	    	throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
	    }
	    adminUser.setRole_id(toUserRoleID);
	    adminUser.setOrganization(parentAdminUser.getOrganization());
	    flag =  adminUserService.updateAdminUser(adminUser);
	    if(flag < 1){
			throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
		}
	}

}
