package com.dgjs.model.dto;

import com.alibaba.fastjson.JSON;

public class MIndexConfigDto {

	private int ad; //广告位数量
	private int first; //第一种结构显示数量
	private int second; //第二种结构显示数量
	
	public int getAd() {
		return ad;
	}
	public void setAd(int ad) {
		this.ad = ad;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
