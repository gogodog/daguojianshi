package com.dgjs.model.persistence;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.M_Index_Position;
import com.dgjs.model.enums.UpDown_Status;

public class MIndexConfig {

	private Long id; //id
	private Index_Type type; //类型
	private M_Index_Position position; //位置
	private int sort;//排序
	
	private UpDown_Status status;//状态
	private String aid;//文章id
	
	private String pictures;//图片
	private String title;//标题
	private String sub_content;//精简内容
	private String link;//跳转链接
	
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	private String start_time;//发生时间
	private String a_type;//原文章类型
	private Long visits;//访问基数
	
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
	public M_Index_Position getPosition() {
		return position;
	}
	public void setPosition(M_Index_Position position) {
		this.position = position;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getStatusValue(){
		return status == null?null:status.getValue();
	}
	public String getTypeValue(){
		return type == null?null:type.getValue();
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public Long getVisits() {
		return visits;
	}
	public void setVisits(Long visits) {
		this.visits = visits;
	}
	public String[] getPics(){
		if(!StringUtils.isEmpty(pictures)){
			return pictures.split(",");
		}
		return null;
	}
}
