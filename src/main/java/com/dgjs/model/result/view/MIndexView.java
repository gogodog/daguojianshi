package com.dgjs.model.result.view;

import com.dgjs.model.enums.Index_Type;

public class MIndexView {

	private Index_Type type; //类型
	private int position; //位置
	private String aid;//文章id
	private String[] pictures;//图片
	private String title;//标题
	private String sub_content;//精简内容
	private String start_time;//开始时间
	private String aType;//文章类型
	
	public Index_Type getType() {
		return type;
	}
	public void setType(Index_Type type) {
		this.type = type;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String[] getPictures() {
		return pictures;
	}
	public void setPictures(String[] pictures) {
		this.pictures = pictures;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_content() {
		return sub_content;
	}
	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getaType() {
		return aType == null?type.getValue():aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	
}
