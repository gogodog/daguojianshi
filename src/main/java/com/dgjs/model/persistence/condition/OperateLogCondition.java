package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.List;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.OperateEnum;

public class OperateLogCondition {

	private int beginNum=0;//从哪条开始查
	private int onePageSize=Constants.DEFAULT_ONEPAGESIZE;
	private int currentPage=1;//当前页
	private boolean needTotalResults;//是否需要查询总数
	
	private Integer adminId;//用户id
	private List<OperateEnum> operateTypes;//操作类型
	private Date createTimeFrom;//创建时间区间
	private Date createTimeTo;//创建时间区间
	
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
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
	public List<OperateEnum> getOperateTypes() {
		return operateTypes;
	}
	public void setOperateTypes(List<OperateEnum> operateTypes) {
		this.operateTypes = operateTypes;
	}
	
}
