package com.dgjs.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告位置
 */
public enum Ad_Position {
	
    INDEX_FIRST(101,"首页第一个广告位"),
    
    INDEX_SECOND(102,"首页第二个广告位"),
    
    INDEX_MIDDLE_FIRST(131,"首页中间第一个广告位"),
    
    INDEX_MIDDLE_SECOND(132,"首页中间第二个广告位"),
    
    INDEX_MIDDLE_THIRD(133,"首页中间第三个广告位"),
    
    INDEX_MIDDLE_FORTH(134,"首页中间第四个广告位"),
    
    INDEX_MIDDLE_FIFTH(135,"首页中间第五个广告位"),
    
    INDEX_BELOW_FIRST(161,"首页底部第一个广告位"),
    
    INDEX_BELOW_SECOND(162,"首页底部第二个广告位"),
    
    INDEX_BELOW_THIRD(163,"首页底部第三个广告位");
    
    private Ad_Position(int key,String value){
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
    
    public static Ad_Position valueOf(int key){
    	switch (key) {
		case 101:
			return INDEX_FIRST;
		case 102:
			return INDEX_SECOND;
		case 131:
			return INDEX_MIDDLE_FIRST;
		case 132:
			return INDEX_MIDDLE_SECOND;
		case 133:
			return INDEX_MIDDLE_THIRD;
		case 134:
			return INDEX_MIDDLE_FORTH;
		case 135:
			return INDEX_MIDDLE_FIFTH;
		case 161:
			return INDEX_BELOW_FIRST;
		case 162:
			return INDEX_BELOW_SECOND;
		case 163:
			return INDEX_BELOW_THIRD;
		default:
			break;
		}
    	return null;
    }
    
    public static List<Ad_Position> getValues(int begin,int end){
    	List<Ad_Position> list = new ArrayList<Ad_Position>();
    	Ad_Position adPosition = null;
    	for(int i=begin;i<end+1;i++){
    		if((adPosition = valueOf(i))!=null){
    			list.add(adPosition);
    		}
    	}
    	return list;
     }
    
}
