package com.dgjs.model.persistence;

import java.util.Date;

public class Comments{ 

       private String id; //id
       private Long articlescrap_id; //文章id
       private String comment_name; //评论人名称
       private Long comment_id; //评论人id
       private String ip_address; //ip地址
       private String comment; //评论内容
       private Date comment_time; //评论时间
       private String parent_id; //父级评论id
       private Integer is_show; //是否展示
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getArticlescrap_id() {
		return articlescrap_id;
	}
	public void setArticlescrap_id(Long articlescrap_id) {
		this.articlescrap_id = articlescrap_id;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public Long getComment_id() {
		return comment_id;
	}
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
       
}