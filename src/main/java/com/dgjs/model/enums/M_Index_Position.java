package com.dgjs.model.enums;

public enum M_Index_Position {

	ad(1),//广告位

	first(2),//第一结构
	
	second(3);//第三结构
	
	private M_Index_Position(int key){
		this.key = key;
	}
	
	private int key;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public static M_Index_Position valueOf(int key){
		switch (key) {
		case 1:
			return ad;
		case 2:
			return first;
		case 3:
			return second;
		default:
			break;
		}
		return null;
	}
}
