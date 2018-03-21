package com.dgjs.service.impl.wechat;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.Constants;
import com.dgjs.model.wechat.res.AccessTokenInfo;
import com.dgjs.model.wechat.res.TicketInfo;
import com.dgjs.service.wechat.WechatService;
import com.dgjs.utils.HttpClientUtils;
import com.dgjs.utils.RandomUtils;
import com.dgjs.utils.SHA1;
import com.dgjs.utils.StringUtils;

import freemarker.log.Logger;

@Service
public class WechatServiceImpl implements WechatService{
	
	private Logger log = Logger.getLogger(this.getClass().getName()); 

	@Value("${weixinappid}")
	private String weixinappiId;
	
	@Value("${weixinappsecret}")
	private String weixinappsecret;
	
	@Override
	@Cacheable(value = "cache2h",key="'getAccessToken'",condition = "#result != null")
	public String getAccessToken() {
		AccessTokenInfo accessTokenInfo = HttpClientUtils.sendGet(MessageFormat.format(Constants.getAccessTokenUrl, weixinappiId,weixinappsecret),AccessTokenInfo.class);
		log.info("wechat get accessTokenInfo="+JSON.toJSONString(accessTokenInfo));
		return accessTokenInfo.getAccess_token();
	}

	@Override
	@Cacheable(value = "cache2h",key="'getTicket'",condition = "#result != null")
	public String getTicket() {
		TicketInfo ticketInfo=HttpClientUtils.sendGet(MessageFormat.format(Constants.getTicketUrl, getAccessToken()),TicketInfo.class);
		log.info("wechat get ticketInfo="+JSON.toJSONString(ticketInfo));
		return ticketInfo.getTicket();
	}

	@Override
	public Map<String, Object> getBaseConfig(String url) {
		String ticket = getTicket();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Map<String,Object> map = new TreeMap<String,Object>();
		String noncestr = RandomUtils.getRandomStr(6);
		map.put("noncestr", noncestr);
		map.put("url", url);
		map.put("jsapi_ticket", ticket);
		map.put("timestamp",  d.getTime()/1000);
		String param = StringUtils.getMapString(map);
		String signature = SHA1.encode(param);
		map.put("appid", weixinappiId);
		map.put("signature", signature);
		return map;
	}

}
