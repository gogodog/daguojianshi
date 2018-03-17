package com.dgjs.model.persistence.condition;

import java.util.List;

import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.UpDown_Status;

public class AdvertisementCondtion extends BasePageCondition{

	private UpDown_Status status;//状态
	private List<Ad_Position> adPositions;//广告位置
	private String adDesc;//广告描述
	private String sort;//排序
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
