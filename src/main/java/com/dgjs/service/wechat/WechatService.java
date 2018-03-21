package com.dgjs.service.wechat;

import java.util.Map;

public interface WechatService {

	public String getAccessToken();
	
	public String getTicket();
	
	public Map<String,Object> getBaseConfig(String url);
}
