package com.dgjs.model.persistence.condition;

import java.util.List;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.UpDown_Status;

public class AdvertisementCondtion {

	private UpDown_Status status;//状态
	private List<Ad_Position> adPositions;//广告位置
	private String adDesc;//广告描述
	private String sort;//排序
	private int currentPage = 1;//当前页
	private boolean needTotalResults;//是否需要查询总数
	private int beginNum;
	private int onePageSize = Constants.DEFAULT_ONEPAGESIZE;
	
	private Ad_Position adPosition;//广告位置 后台查询用到
	
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public String getAdDesc() {
		return adDesc;
	}
	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public List<Ad_Position> getAdPositions() {
		return adPositions;
	}
	public void setAdPositions(List<Ad_Position> adPositions) {
		this.adPositions = adPositions;
	}
	public Ad_Position getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(Ad_Position adPosition) {
		this.adPosition = adPosition;
	}
	
}
