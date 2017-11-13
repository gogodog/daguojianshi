package com.dgjs.model.param.view;

public class ArticlePublish {

	private String aid;//文章id
	private Integer visits;//访问量基数
	private String show_time;//展示时间
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
}
