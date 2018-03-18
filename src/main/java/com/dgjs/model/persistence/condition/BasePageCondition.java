package com.dgjs.model.persistence.condition;

import com.dgjs.constants.Constants;

public class BasePageCondition {
	
	private int currentPage = 1;//当前页
	private boolean needTotalResults=true;//是否需要查询总数
	private int beginNum;
	private int onePageSize = Constants.DEFAULT_ONEPAGESIZE;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isNeedTotalResults() {
		return needTotalResults;
	}
	public void setNeedTotalResults(boolean needTotalResults) {
		this.needTotalResults = needTotalResults;
	}
	public int getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getOnePageSize() {
		return onePageSize;
	}
	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
	}
	
	
}