package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.enums.Feedback_Type;

public class FeedBack {

	private Long id;//id
	private String articlescrap_id;//文章id
	private String ip;//ip地址
	private Date create_time;//创建时间
	private Judge_Level judge_level;//文章评判级别
	private String judge_message;//评论内容
	
	private String uname;//用户姓名
	private String email;//邮箱
	private Integer doubt_level;//疑问程度（当judge_level是DOUBT时有）
	private Feedback_Type feedback_type = Feedback_Type.FRONT;//反馈类型 1：前台反馈 2：后台反馈
	
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDoubt_level() {
		return doubt_level;
	}
	public void setDoubt_level(Integer doubt_level) {
		this.doubt_level = doubt_level;
	}
	public Feedback_Type getFeedback_type() {
		return feedback_type;
	}
	public void setFeedback_type(Feedback_Type feedback_type) {
		this.feedback_type = feedback_type;
	}
    	
}
