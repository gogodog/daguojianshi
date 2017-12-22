package com.dgjs.model.wechat.helper;

import com.dgjs.model.wechat.req.GetUserAccessToken;
import com.dgjs.model.wechat.req.GetUserInfo;
import com.dgjs.model.wechat.res.UserAccessToken;
import com.dgjs.model.wechat.res.UserInfo;
import com.dgjs.utils.HttpClientUtils;

public class LoginHelper {
	
	public static UserAccessToken getUserAccessToken(GetUserAccessToken userAccessToken) {
		return HttpClientUtils.sendGetWithParams(userAccessToken, "https://api.weixin.qq.com/sns/oauth2/access_token", UserAccessToken.class);
	}

	public static UserInfo getUserInfo(GetUserInfo userInfo) {
		return HttpClientUtils.sendGetWithParams(userInfo, "https://api.weixin.qq.com/sns/userinfo", UserInfo.class);
	}
	
}
