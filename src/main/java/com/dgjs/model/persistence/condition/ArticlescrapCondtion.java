package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.DateUtils;

public class ArticlescrapCondtion {

	private UpDown_Status status;//状态
	private Articlescrap_Type type;//类型
	private String author;//作者
	private String title;//标题
	private Date createTimeFrom;
	private Date createTimeTo;
	private Date showTimeFrom;
	private Date showTimeTo;
	private Date updateTimeFrom;
	private Date updateTimeTo;
	private int subContentLength;//查询截取的内容长度，默认20
	private Map<String,SortOrder> sort;//排序
	private int beginNum=0;//从哪条开始查
	private int onePageSize=Constants.DEFAULT_ONEPAGESIZE;
	private int currentPage=1;//当前页
	private boolean needTotalResults;//是否需要查询总数
    
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
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
	public int getSubContentLength() {
		return subContentLength;
	}
	public void setSubContentLength(int subContentLength) {
		this.subContentLength = subContentLength;
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
}
