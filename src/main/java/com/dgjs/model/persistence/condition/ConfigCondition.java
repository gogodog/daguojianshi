package com.dgjs.model.persistence.condition;

import java.util.List;

public class ConfigCondition {

	private String desc;
	private String key;
	private List<String> keys;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	
	
}
