package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.utils.DateUtils;

public class ArticlescrapCondtion {

	private Articlescrap_Status status;//状态
	private Articlescrap_Type type;//类型
	private String author;//作者
	private String title;//标题
	private Date createTimeFrom;
	private Date createTimeTo;
	private Date showTimeFrom;
	private Date showTimeTo;
	private Date updateTimeFrom;
	private Date updateTimeTo;
	private Map<String,SortOrder> sort;//排序
	private int beginNum=0;//从哪条开始查
	private int onePageSize=Constants.DEFAULT_ONEPAGESIZE;
	private int currentPage=1;//当前页
	private boolean needTotalResults;//是否需要查询总数
	private String keyword;//关键词搜索
	private Integer picNum;//图片数量
	private String[] withoutIds;//不查询的id
	private Integer startTimeFrom;//startTime大于等于
	private Integer startTimeTo;//startTime小于等于
	private Integer greaterStartTime;//startTime要大于的时间
	private Integer lessThanStartTime;//startTime要小于的时间
    private boolean isNeedContent=false;//是否要包含content
	
	public Articlescrap_Status getStatus() {
		return status;
	}
	public void setStatus(Articlescrap_Status status) {
		this.status = status;
	}
	public Articlescrap_Type getType() {
		return type;
	}
	public void setType(Articlescrap_Type type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTimeFrom() {
		return createTimeFrom;
	}
	public void setCreateTimeFrom(Date createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}
	public Date getCreateTimeTo() {
		return createTimeTo;
	}
	public void setCreateTimeTo(Date createTimeTo) {
		this.createTimeTo = createTimeTo;
	}
	public Date getShowTimeFrom() {
		return showTimeFrom;
	}
	public void setShowTimeFrom(String showTimeFrom) {
		if(!StringUtils.isEmpty(showTimeFrom)){
			this.showTimeFrom = DateUtils.parseDateFromString(showTimeFrom, DateUtils.DAY);
		}
	}
	public Date getShowTimeTo() {
		return showTimeTo;
	}
	public void setShowTimeTo(String showTimeTo) {
		if(!StringUtils.isEmpty(showTimeFrom)){
			this.showTimeTo = DateUtils.parseDateFromString(showTimeTo, DateUtils.DAY);
		}
	}
	public Date getUpdateTimeFrom() {
		return updateTimeFrom;
	}
	public void setUpdateTimeFrom(Date updateTimeFrom) {
		this.updateTimeFrom = updateTimeFrom;
	}
	public Date getUpdateTimeTo() {
		return updateTimeTo;
	}
	public void setUpdateTimeTo(Date updateTimeTo) {
		this.updateTimeTo = updateTimeTo;
	}
	
	public Map<String, SortOrder> getSort() {
		return sort;
	}
	public void setSort(Map<String, SortOrder> sort) {
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
	public Integer getStartTimeFrom() {
		return startTimeFrom;
	}
	public void setStartTimeFrom(Integer startTimeFrom) {
		this.startTimeFrom = startTimeFrom;
	}
	public Integer getStartTimeTo() {
		return startTimeTo;
	}
	public void setStartTimeTo(Integer startTimeTo) {
		this.startTimeTo = startTimeTo;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getPicNum() {
		return picNum;
	}
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}
	public String[] getWithoutIds() {
		return withoutIds;
	}
	public void setWithoutIds(String[] withoutIds) {
		this.withoutIds = withoutIds;
	}
	public Integer getGreaterStartTime() {
		return greaterStartTime;
	}
	public void setGreaterStartTime(Integer greaterStartTime) {
		this.greaterStartTime = greaterStartTime;
	}
	public Integer getLessThanStartTime() {
		return lessThanStartTime;
	}
	public void setLessThanStartTime(Integer lessThanStartTime) {
		this.lessThanStartTime = lessThanStartTime;
	}
	public boolean isNeedContent() {
		return isNeedContent;
	}
	public void setNeedContent(boolean isNeedContent) {
		this.isNeedContent = isNeedContent;
	}
	
}
