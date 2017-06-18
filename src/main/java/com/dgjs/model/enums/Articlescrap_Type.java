package com.dgjs.model.enums;

import com.alibaba.fastjson.JSON;

/**
 * 文章分类
 */
public enum Articlescrap_Type {

	HISTORY(10,"大国简史之中国正史"),
	
	FOREIGN_HISTORY(20,"大国简史之国外历史"),
	
	HUMANITY_HISTORY(30,"大国简史之人文"),
	
	GEOGRAPHY_HISTORY(40,"大国简史之地理"),
	
	UNOFFICIAL_HISTORY(50,"大国简史之野史");
	
	
	private Articlescrap_Type(int key,String value){
		this.key=key;
		this.value=value;
	}
	
    private int key;
	
	private String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Articlescrap_Type valueOf(int key){
		 switch (key) {
        case 10:
            return HISTORY;
        case 20:
            return FOREIGN_HISTORY;
        case 30:
        	return HUMANITY_HISTORY;
        case 40:
        	return GEOGRAPHY_HISTORY;	
        case 50:
        	return UNOFFICIAL_HISTORY;
        default:
            return null;
	  }
    }

}
