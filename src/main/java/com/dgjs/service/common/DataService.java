package com.dgjs.service.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface DataService {
	
	public static final String IDSSEPORTE = ";;";
	
	boolean dadian(HttpServletRequest request, String dadianView);

	JSONObject getDocShowCounts(String docids);
}
