package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;

import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;

public class DraftCondition extends BasePageCondition{

	//基本信息
	private String author;
	private String title;
	private Articlescrap_Type type;//类型
	private Date createTimeFrom;
	private Date createTimeTo;
	private String keyword;//关键词搜索
	private Map<String, SortOrder> sort;
	private Integer userId;//用户id
	private List<Integer> userIdList;//多用户id
	private int member;//0：自己 1：成员 2：全部
	
	//审核发布参数
	private Pending_Status status;
	private List<Pending_Status> statusList;
	
	//返回字段
	private String[] includes;//返回值需要的字段
    private String[] excludes;//返回值不需要的字段
	
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
	public Map<String, SortOrder> getSort() {
		return sort;
	}
	public void setSort(Map<String, SortOrder> sort) {
		this.sort = sort;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Pending_Status getStatus() {
		return status;
	}
	public void setStatus(Pending_Status status) {
		this.status = status;
	}
	public List<Pending_Status> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Pending_Status> statusList) {
		this.statusList = statusList;
	}
	public List<Integer> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}

}
