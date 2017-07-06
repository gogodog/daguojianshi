package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Judge_Level;

public class AJudge {

	private Long id;//id
	private String articlescrap_id;//文章id
	private Judge_Level judge_level;//文章评判级别
	private String ip;//ip地址
	private Date create_time;//创建时间
	private String judge_message;//只有不真实
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArticlescrap_id() {
		return articlescrap_id;
	}
	public void setArticlescrap_id(String articlescrap_id) {
		this.articlescrap_id = articlescrap_id;
	}
	public Judge_Level getJudge_level() {
		return judge_level;
	}
	public void setJudge_level(Judge_Level judge_level) {
		this.judge_level = judge_level;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getJudge_message() {
		return judge_message;
	}
	public void setJudge_message(String judge_message) {
		this.judge_message = judge_message;
	}
	
}
