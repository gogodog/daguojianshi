package com.dgjs.model.enums;

/**
 * 广告位置
 */
public enum Ad_Position {
	
    INDEX_FIRST(101,"首页第一个广告位"),
    
    INDEX_SECOND(102,"首页第二个广告位");
    
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
		default:
			break;
		}
    	return null;
    }
}
