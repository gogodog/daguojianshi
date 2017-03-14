package com.dgjs.model.persistence.condition;

import java.util.Date;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;

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
	private Integer subContentLength=Constants.DEFAULT_SUBSTRING_CONTENT_LENGTH;//查询截取的内容长度，默认20
	private String sort;//排序
	private int beginNum;//从哪条开始查
	private int onePageSize;//每页显示
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
	public void setShowTimeFrom(Date showTimeFrom) {
		this.showTimeFrom = showTimeFrom;
	}
	public Date getShowTimeTo() {
		return showTimeTo;
	}
	public void setShowTimeTo(Date showTimeTo) {
		this.showTimeTo = showTimeTo;
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
	public Integer getSubContentLength() {
		return subContentLength;
	}
	public void setSubContentLength(Integer subContentLength) {
		this.subContentLength = subContentLength;
	}
	
}
