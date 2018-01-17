package com.dgjs.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.mapper.admin.AdminUserInfoMapper;
import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.service.admin.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	AdminUserInfoMapper adminUserInfoMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
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

	@Override
	public PageInfoDto<AdminUserResult> list(AdminUserCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = adminUserMapper.count(condition);
		}
		List<AdminUserResult> list= adminUserMapper.list(condition);
		if(list!=null&&!list.isEmpty()){
			List<Integer> roleIds = new ArrayList<Integer>();
			for(AdminUserResult aur:list){
				roleIds.add(aur.getRole_id());
			}
			List<Role> roleList=roleMapper.selectByIds(roleIds);
			Map<Integer,Role> map = null;
			if(roleList!=null && !roleList.isEmpty()){
				map = new HashMap<Integer,Role>();
				for(Role role:roleList){
					map.put(role.getId(), role);
				}
				for(AdminUserResult aur:list){
					aur.setRole_name(map.get(aur.getRole_id()).getRole_name());
				}
			}
			
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

	@Override
	public int updateAdminUser(AdminUser adminUser) {
		return adminUserMapper.update(adminUser);
	}

	@Override
	@Cacheable(value = "cache5m", key = "#root.method.name+'('+#p0+')'")
	public AdminUser getByUserCode(String userCode) {
		return adminUserMapper.getByUserCode(userCode);
	}

	@Override
	public boolean wxLogin(UserInfo userInfo, HttpServletResponse response) {
		//TODO 关悦
		//TODO 微信入口通过unionid判断是否新老用户，若新用户，登记造册
		//TODO 新老用户返回本地code
		//TODO 建议cookie缓存：CookieUtils.addCookie(response, Constants.CK_USERINFO_KEY, JSONObject.toJSONString(code), Constants.CK_USERINFO_MAXAGE);
		System.out.println(JSON.toJSONString(userInfo));
		return true;
	}
}
