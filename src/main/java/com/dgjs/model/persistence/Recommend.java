package com.dgjs.model.persistence;

import com.alibaba.fastjson.JSON;

public class Recommend implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7706136536081867460L;
	private int sort;
	private int status;
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
