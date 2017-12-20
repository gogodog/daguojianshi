package com.dgjs.model.param.view;

public class ArticlePublish {

	private String aid;//文章id
	private Long visits;//访问量基数
	private String show_time;//展示时间
	private int showNow;//是否立刻展示
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public int getShowNow() {
		return showNow;
	}
	public void setShowNow(int showNow) {
		this.showNow = showNow;
	}
	public Long getVisits() {
		return visits;
	}
	public void setVisits(Long visits) {
		this.visits = visits;
	}
}
