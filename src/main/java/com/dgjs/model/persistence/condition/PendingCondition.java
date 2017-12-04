package com.dgjs.model.persistence.condition;

import java.util.List;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;

public class PendingCondition {

	private Integer userId;
	private String title;
	private Pending_Status status;
	private String keyword;//关键词搜索
	private Articlescrap_Type type;//类型
	private Map<String, SortOrder> sort;//排序
	private int beginNum=0;//从哪条开始查
	private int onePageSize=Constants.DEFAULT_ONEPAGESIZE;
	private int currentPage=1;//当前页
	private boolean needTotalResults;//是否需要查询总数
	private List<Pic_Sync_Status> picSyncStatus;//图片同步状态
	
	private String[] includes;//返回值需要的字段
    private String[] excludes;//返回值不需要的字段
	
	public Pending_Status getStatus() {
		return status;
	}
	public void setStatus(Pending_Status status) {
		this.status = status;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Articlescrap_Type getType() {
		return type;
	}
	public void setType(Articlescrap_Type type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Map<String, SortOrder> getSort() {
		return sort;
	}
	public void setSort(Map<String, SortOrder> sort) {
		this.sort = sort;
	}
	public List<Pic_Sync_Status> getPicSyncStatus() {
		return picSyncStatus;
	}
	public void setPicSyncStatus(List<Pic_Sync_Status> picSyncStatus) {
		this.picSyncStatus = picSyncStatus;
	}
	public String[] getIncludes() {
		return includes;
	}
	public void setIncludes(String[] includes) {
		this.includes = includes;
	}
	public String[] getExcludes() {
		return excludes;
	}
	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}
	
}
