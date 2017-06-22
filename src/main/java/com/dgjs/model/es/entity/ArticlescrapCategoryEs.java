package com.dgjs.model.es.entity;

import java.io.Serializable;
import java.util.List;

public class ArticlescrapCategoryEs implements Serializable{

	private static final long serialVersionUID = -3837895326314913286L;

	private List<Integer> group;
	
	private List<String> country;
	
	private List<String> keywords;

	public List<Integer> getGroup() {
		return group;
	}

	public void setGroup(List<Integer> group) {
		this.group = group;
	}

	public List<String> getCountry() {
		return country;
	}

	public void setCountry(List<String> country) {
		this.country = country;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	
}
