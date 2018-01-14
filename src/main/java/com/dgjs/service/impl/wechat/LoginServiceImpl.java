package com.dgjs.service.impl.wechat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.constants.WeChatConstans;
import com.dgjs.model.wechat.helper.LoginHelper;
import com.dgjs.model.wechat.req.GetUserAccessToken;
import com.dgjs.model.wechat.req.GetUserInfo;
import com.dgjs.model.wechat.res.UserAccessToken;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.wechat.LoginService;
import com.dgjs.utils.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	AdminUserService adminUserService;

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
			if (userAccessToken == null || userAccessToken.getErrcode() == -1) {
				return false;
			}
			UserInfo userInfo = this.getUserInfo(userAccessToken);
			if (userInfo == null || userInfo.getErrcode() == -1) {
				return false;
			}
			return adminUserService.wxLogin(userInfo,response);
		} catch (Exception e) {
			return true;
		}
	}
}