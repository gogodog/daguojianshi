package com.dgjs.model.persistence;

import java.util.Date;

public class Authority {

	private Integer id;//id
	private String authority_name;//权限名称
	private String authority_url;//权限路径
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}
	public String getAuthority_url() {
		return authority_url;
	}
	public void setAuthority_url(String authority_url) {
		this.authority_url = authority_url;
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
	
}
