package com.dgjs.service.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.view.DadianView;

public interface DataService {
	
	public static final String IDSSEPORTE = ";;";
	
	boolean dadian(HttpServletRequest request, DadianView dadianView);

	int getDocShowCount(String docids);
	
	Map<String,Integer> getDocShowCounts(String docids);
	
	int insertDaDian(DadianView view);
	
	int getPageTotalVisits(String pageId);
}
