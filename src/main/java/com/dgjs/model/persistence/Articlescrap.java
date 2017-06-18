package com.dgjs.model.persistence;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.StringUtils;

public class Articlescrap {

	private String id;//esId
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
	private String start_time_c;//内容的起始时间
	private String start_time_y;//内容的起始时间
	private String start_time_m;//内容的起始时间
	private String start_time_d;//内容的起始时间
	private Recommend recommend;//推荐信息
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		if(!StringUtils.isNullOrEmpty(start_time)){
			if(start_time.contains("公元前")){
				this.setcymd("公元前");
			}else if(start_time.contains("公元")){
				this.setcymd("公元");
			}
		}
	}
	private void setcymd(String key){
		this.start_time_c = key;
		if(!StringUtils.isNullOrEmpty(start_time) && start_time.contains("年")){
			this.start_time_y = start_time.split("年")[0].replace(key, "");
			if(start_time.contains("月")){
				this.start_time_m = start_time.split("年")[1].split("月")[0];
				if(start_time.contains("日")){
					this.start_time_d = start_time.split("月")[1].split("日")[0];
				}
			}
		}
	}
	public String getStart_time_c() {
		return start_time_c;
	}
	public void setStart_time_c(String start_time_c) {
		this.start_time_c = start_time_c;
		this.setStartTime();
	}
	private void setStartTime(){
		String st = "";
		if(!StringUtils.isNullOrEmpty(this.start_time_c)){
			st += this.start_time_c;
			if(!StringUtils.isNullOrEmpty(this.start_time_y)){
				st += (this.start_time_y + "年");
				if(!StringUtils.isNullOrEmpty(this.start_time_m)){
					st += (this.start_time_m + "月");
					if(!StringUtils.isNullOrEmpty(this.start_time_d)){
						st += (this.start_time_d + "日");
					}
				}
			}
		}
		this.start_time = st;
	}
	public String getStart_time_y() {
		return start_time_y;
	}
	public void setStart_time_y(String start_time_y) {
		this.start_time_y = start_time_y;
		this.setStartTime();
	}
	public String getStart_time_m() {
		return start_time_m;
	}
	public void setStart_time_m(String start_time_m) {
		this.start_time_m = start_time_m;
		this.setStartTime();
	}
	public String getStart_time_d() {
		return start_time_d;
	}
	public void setStart_time_d(String start_time_d) {
		this.start_time_d = start_time_d;
		this.setStartTime();
	}
	public Recommend getRecommend() {
		return recommend;
	}
	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public String getTypeValue(){
		return type == null ? null: type.getValue();
	}
}
