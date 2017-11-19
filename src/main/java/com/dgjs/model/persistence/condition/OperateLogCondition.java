package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.utils.DateUtils;

public class OperateLogCondition {

	private int beginNum=0;//从哪条开始查
	private int onePageSize=Constants.DEFAULT_ONEPAGESIZE;
	private int currentPage=1;//当前页
	private boolean needTotalResults=true;//是否需要查询总数
	
	private List<Integer> adminIds;//用户id
	private String username;//用户名
	private String realname;//姓名
	private List<OperateEnum> operateTypes;//操作类型
	private Date createTimeFrom;//创建时间区间
	private Date createTimeTo;//创建时间区间
	private int isSuccess=-1;//操作是否执行成功 1:成功 0:失败
	
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
	public List<Integer> getAdminIds() {
		return adminIds;
	}
	public void setAdminIds(List<Integer> adminIds) {
		this.adminIds = adminIds;
	}
	public Date getCreateTimeFrom() {
		return createTimeFrom;
	}
	public void setCreateTimeFrom(String createTimeFrom) {
		if(!StringUtils.isEmpty(createTimeFrom)){
			this.createTimeFrom = DateUtils.parseDateFromString(createTimeFrom, DateUtils.DAY);
		}
	}
	public Date getCreateTimeTo() {
		return createTimeTo;
	}
	public void setCreateTimeTo(String createTimeTo) {
		if(!StringUtils.isEmpty(createTimeTo)){
			this.createTimeTo = DateUtils.parseDateFromString(createTimeTo, DateUtils.DAY);
		}
	}
	public List<OperateEnum> getOperateTypes() {
		return operateTypes;
	}
	public void setOperateTypes(List<OperateEnum> operateTypes) {
		this.operateTypes = operateTypes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
}
