package com.dgjs.service.impl.wechat;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.Session_Keys;
import com.dgjs.constants.WeChatConstans;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.LoginRecord;
import com.dgjs.model.wechat.helper.LoginHelper;
import com.dgjs.model.wechat.req.GetUserAccessToken;
import com.dgjs.model.wechat.req.GetUserInfo;
import com.dgjs.model.wechat.res.UserAccessToken;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.LoginRecordService;
import com.dgjs.service.transaction.AdminUserTransactionService;
import com.dgjs.service.wechat.LoginService;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;
import com.dgjs.utils.WebContextHelper;

@Service
public class LoginServiceImpl implements LoginService {
	
	private Log log = LogFactory.getLog(LoginServiceImpl.class);
	
	@Autowired
	AdminUserService adminUserService;
	
	@Autowired
	AdminUserTransactionService adminUserTransactionService;
	
	@Autowired
	LoginRecordService loginRecordService;
	
	@Autowired
	private ExecutorService loginLogExecutor;

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
	public boolean login(String code,String organization, HttpServletResponse response){
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
			AdminUser adminUser = adminUserTransactionService.wxLogin(userInfo,organization,response);
			if(adminUser!=null){
				setLastLoginTime(adminUser.getId());//设置最后一次登录时间
				WebContextHelper.setSessionValue(Session_Keys.USER_INFO, adminUser);
				return true;
			}
		} catch (Exception e) {
			log.error("login exception", e);
		}
		return false;
	}
	
	public void setLastLoginTime(Integer adminId){
		LoginRecord loginRecord = loginRecordService.getLastLoginRecord(adminId);
		if(loginRecord!=null){
			WebContextHelper.setSessionValue(Session_Keys.LAST_LOGIN_TIME, DateUtils.parseStringFromDate(loginRecord.getLogin_time(), "yyyy-MM-dd HH:mm"));
		}
		loginLogExecutor.execute(new Runnable(){
			@Override
			public void run() {
				LoginRecord lr = new LoginRecord(); 
				lr.setLogin_time(new Date());
				lr.setAdmin_id(adminId);
				loginRecordService.save(lr);
			}
		});
	}
}
