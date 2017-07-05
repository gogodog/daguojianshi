package com.dgjs.service.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.view.DadianView;
import com.dgjs.model.view.IpHttpResponse;

public interface DataService {
	
	String CURRENTIPSRSTYPE = "taobao";
	
	public static final String IDSSEPORTE = ";;";
	
	boolean dadian(HttpServletRequest request, DadianView dadianView);

	int insertDaDian(DadianView view);
	
	int getPageTotalVisits(String pageId);
	
	IpHttpResponse.IpAliData getLocalAliAdressByIp(String ip);
	
	Map<String,Integer> getDocShowCounts(String docids);
	
	Map<String,Integer> getDocShowCounts(List<String> docids);
}
