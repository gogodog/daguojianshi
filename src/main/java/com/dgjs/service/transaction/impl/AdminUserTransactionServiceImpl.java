package com.dgjs.service.transaction.impl;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.mapper.admin.AdminUserInfoMapper;
import com.dgjs.mapper.admin.OrganizationMapper;
import com.dgjs.model.enums.Admin_Source;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.transaction.AdminUserTransactionService;
import com.dgjs.utils.AESUtils;
import com.dgjs.utils.CookieUtils;

@Service
public class AdminUserTransactionServiceImpl implements AdminUserTransactionService{

	@Autowired
	AdminUserInfoMapper adminUserInfoMapper;
	
	@Autowired
	OrganizationMapper organizationMapper;
	
	@Autowired
	AdminUserService adminUserService;
	
	@Transactional
	@Override
	public AdminUser wxLogin(UserInfo userInfo,String organization,HttpServletResponse response) {
	   if(userInfo==null){
		   throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue(),"userInfo is null");
	   }
	   if(StringUtils.isEmpty(userInfo.getUnionid())){
		   throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue(),"unionid is null");
	   }
	   AdminUser adminUser = adminUserService.getByUnionId(userInfo.getUnionid());
	   //如果是新用户
	   if(adminUser==null){
			adminUser = new AdminUser();
			adminUser.setHeadimgurl(userInfo.getHeadimgurl());
			adminUser.setUsername(userInfo.getNickname());
			adminUser.setOpenid(userInfo.getOpenid());
			adminUser.setRole_id(3);
			adminUser.setSource(Admin_Source.WEIXIN);
			adminUser.setStatus(UpDown_Status.UP);
			adminUser.setUnionid(userInfo.getUnionid());
			adminUser.setUser_code(AESUtils.Encrypt(Constants.WX_CODE_PREFIX+userInfo.getUnionid(), Constants.WX_CODE_ENCRYPT_KEY));
			adminUser.setOrganization(com.dgjs.utils.StringUtils.parseInt(organization));
			int flag = adminUserService.save(adminUser);
			if(flag<1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"save adminUser exception");
			}
			AdminUserInfo adminUserInfo = new AdminUserInfo();
			adminUserInfo.setProvince(userInfo.getProvince());
			adminUserInfo.setCity(userInfo.getCity());
			adminUserInfo.setCountry(userInfo.getCountry());
			if(StringUtils.isNotBlank(userInfo.getSex())){
				adminUserInfo.setSex(Integer.parseInt(userInfo.getSex()));
			}
			adminUserInfo.setHeadimgurl(userInfo.getHeadimgurl());
			adminUserInfo.setReal_name(userInfo.getNickname());
			adminUserInfo.setUser_code(adminUser.getUser_code());
			adminUserInfo.setId(adminUser.getId());
			flag = adminUserInfoMapper.save(adminUserInfo);
			if(flag<1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"save adminUserInfo exception");
			}
		}
	    CookieUtils.addCookie(response, Constants.CK_USERINFO_KEY, adminUser.getUser_code(), Constants.CK_USERINFO_MAXAGE);
		return adminUser;
	}
	
	@Transactional
	@Override
	public void saveOrganization(Organization organization) {
		//新增组织-修改组织链接-修改代理用户组织及角色
		if(organization==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		if(StringUtils.isEmpty(organization.getOname())){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		if(organization.getProxy()==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
        int flag = organizationMapper.save(organization);
        if(flag<1){
        	throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
        }
        Integer id = organization.getId();//组织id
        String link = Constants.CPS_LOGIN_URL + id;
        organization.setLink(link);
        flag = organizationMapper.update(organization);
        if(flag<1){
        	throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
        }
        Integer roleId= Constants.PROXY_ROLE_ID;
        AdminUser adminUser = adminUserService.getAdminUser(organization.getProxy());
        if(adminUser==null){
        	throw new TransactionException(RETURN_STATUS.SERVICE_ERROR.getValue());
        }
        adminUser.setRole_id(roleId);
        adminUser.setOrganization(id);
        flag = adminUserService.updateAdminUser(adminUser);
        if(flag<1){
        	throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
        }
	}

	@Transactional
	@Override
	public void updateOrganization(Organization organization) {
		if(organization==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		if(organization.getId()==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		if(StringUtils.isEmpty(organization.getOname())){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		if(organization.getProxy()==null){
			throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
		}
		Organization origin = organizationMapper.selectById(organization.getId());
		//当修改了代理人，则重新设置原有和新任人的角色
		if(origin.getProxy().intValue()!=organization.getProxy().intValue()){
			AdminUser adminUser = adminUserService.getAdminUser(origin.getProxy());
			adminUser.setRole_id(Constants.SECOND_ROLE_ID);
			int flag = adminUserService.updateAdminUser(adminUser);
			if(flag < 1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
			}
			adminUser = adminUserService.getAdminUser(organization.getProxy());
			adminUser.setRole_id(Constants.PROXY_ROLE_ID);
			adminUser.setOrganization(organization.getId());
			flag = adminUserService.updateAdminUser(adminUser);
			if(flag < 1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
			}
		}
		int flag = organizationMapper.update(organization);
		if(flag < 1){
			throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue());
		}
	}

}
