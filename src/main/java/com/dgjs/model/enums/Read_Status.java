package com.dgjs.model.enums;

public enum Read_Status {

	UNREAD(0,"未读"),
	
	READE(1,"已读");
	
	private Read_Status(int key,String value){
		this.key = key;
		this.value = value;
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
	
	public static Read_Status valueOf(int key){
		switch (key) {
		case 0:
			return UNREAD;
		case 1:
			return READE;
		default:
			break;
		}
		return null;
	}
}
