package com.dgjs.model.persistence;

import java.util.Date;

public class Organization {

	private Integer id;
	private String oname;//组织名
    private String summary;//简介
    private Date create_time;//创建时间
    private Date update_time;//修改时间
    
    private String link;//链接
    private Integer proxy;//代理人
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getProxy() {
		return proxy;
	}
	public void setProxy(Integer proxy) {
		this.proxy = proxy;
	}
    
    
}
