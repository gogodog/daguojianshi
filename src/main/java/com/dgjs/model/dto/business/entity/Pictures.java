package com.dgjs.model.dto.business.entity;

import java.io.Serializable;

public class Pictures implements Serializable{
	
	private static final long serialVersionUID = 1729772474000485645L;

	private String[] url;
	
	private int number;


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}
	
	
}
