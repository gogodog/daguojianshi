package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;

public class Articlescrap {

	private Long id;//id
	private String title;//标题
	private String content;//文章内容
	private Date show_time;//展示时间
	private Articlescrap_Type type;//文章类型
	private UpDown_Status status;//文章状态
	private String author;//作者
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String sub_content;//精简内容
	private String show_picture;//展示图片
	private Long visits;//访问量
	private String start_time;//内容的起始时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getShow_time() {
		return show_time;
	}
	public void setShow_time(Date show_time) {
		this.show_time = show_time;
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
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public String getSub_content() {
		return sub_content;
	}
	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}
	public String getShow_picture() {
		return show_picture;
	}
	public void setShow_picture(String show_picture) {
		this.show_picture = show_picture;
	}
	public Long getVisits() {
		return visits;
	}
	public void setVisits(Long visits) {
		this.visits = visits;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
}
