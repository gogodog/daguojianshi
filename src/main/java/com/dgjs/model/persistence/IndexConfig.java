package com.dgjs.model.persistence;

import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.UpDown_Status;

public class IndexConfig {
	private Long id; //id
	private Index_Type type; //类型
	private int position; //位置
	private String aid;//文章id
	private int sort;//排序
	private UpDown_Status status;//状态
	
	private String pictures;//图片
	private String title;//标题
	private String sub_content;//精简内容
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
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
	
}
