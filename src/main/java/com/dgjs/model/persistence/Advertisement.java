package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.DateUtils;

public class Advertisement{ 

    private Long id;//id
    private String ad_desc;//广告描述
    private String ad_pic_url;//广告图片地址
    private String ad_link_url;//点击广告链接
    private Ad_Position ad_position;//广告位置
    private Date start_show_time;//开始展示时间
    private Date end_show_time;//结束展示时间
    private UpDown_Status status;//状态
    private Date create_time;//创建时间
    private Date update_time;//修改时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAd_desc() {
		return ad_desc;
	}
	public void setAd_desc(String ad_desc) {
		this.ad_desc = ad_desc;
	}
	public String getAd_pic_url() {
		return ad_pic_url;
	}
	public void setAd_pic_url(String ad_pic_url) {
		this.ad_pic_url = ad_pic_url;
	}
	public String getAd_link_url() {
		return ad_link_url;
	}
	public void setAd_link_url(String ad_link_url) {
		this.ad_link_url = ad_link_url;
	}
	public Ad_Position getAd_position() {
		return ad_position;
	}
	public void setAd_position(Ad_Position ad_position) {
		this.ad_position = ad_position;
	}
	public Date getStart_show_time() {
		return start_show_time;
	}
	public void setStart_show_time(String start_show_time) {
		this.start_show_time = DateUtils.parseDateFromString(start_show_time, DateUtils.DAY);
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getEnd_show_time() {
		return end_show_time;
	}
	public void setEnd_show_time(String end_show_time) {
		this.end_show_time =  DateUtils.parseDateFromString(end_show_time, DateUtils.DAY);;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}

