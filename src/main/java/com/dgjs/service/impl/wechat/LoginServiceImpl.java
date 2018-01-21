package com.dgjs.service.impl.wechat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.Session_Keys;
import com.dgjs.constants.WeChatConstans;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.wechat.helper.LoginHelper;
import com.dgjs.model.wechat.req.GetUserAccessToken;
import com.dgjs.model.wechat.req.GetUserInfo;
import com.dgjs.model.wechat.res.UserAccessToken;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.transaction.AdminUserTransactionService;
import com.dgjs.service.wechat.LoginService;
import com.dgjs.utils.StringUtils;
import com.dgjs.utils.WebContextHelper;

@Service
public class LoginServiceImpl implements LoginService {
	
	private Log log = LogFactory.getLog(LoginServiceImpl.class);
	
	@Autowired
	AdminUserService adminUserService;
	
	@Autowired
	AdminUserTransactionService adminUserTransactionService;

	private UserAccessToken getUserAccessToken(String code) throws Exception {
		if (StringUtils.isEmptyOrWhitespaceOnly(code)) {
			return null;
		}
		GetUserAccessToken u = new GetUserAccessToken();
		u.setCode(code);
		u.setGrant_type(WeChatConstans.GRANT_TYPE);
		u.setSecret(WeChatConstans.SECRET);
		u.setAppid(WeChatConstans.APPID);
		return LoginHelper.getUserAccessToken(u);
	}

	private UserInfo getUserInfo(UserAccessToken userAccessToken) throws Exception {
		GetUserInfo ui = new GetUserInfo();
		ui.setAccess_token(userAccessToken.getAccess_token());
		ui.setLang(WeChatConstans.LANG_CN);
		ui.setOpenid(userAccessToken.getOpenid());
		return LoginHelper.getUserInfo(ui);
	}

	@Override
	public boolean login(String code, HttpServletResponse response){
		try {
			UserAccessToken userAccessToken = this.getUserAccessToken(code);
			if (userAccessToken == null || userAccessToken.getErrcode() != -1) {
				return false;
			}
			UserInfo userInfo = this.getUserInfo(userAccessToken);
			log.info("userInfo====="+JSON.toJSONString(userInfo));
			if (userInfo == null || userInfo.getErrcode() != -1) {
				return false;
			}
			AdminUser adminUser = adminUserTransactionService.wxLogin(userInfo,response);
			if(adminUser!=null){
				WebContextHelper.setSessionValue(Session_Keys.USER_INFO, adminUser);
				return true;
			}
		} catch (Exception e) {
			log.error("login exception", e);
		}
		return false;
	}
}
