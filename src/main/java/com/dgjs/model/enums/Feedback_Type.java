package com.dgjs.model.enums;

public enum Feedback_Type {

	FRONT(1,"前台"),
	
	ADMIN(2,"后台");
	
	private Feedback_Type(int key,String value){
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
	
	public static Feedback_Type valueOf(int key){
		switch (key) {
		case 1:
			return FRONT;
		case 2:
			return ADMIN;
		default:
			break;
		}
		return null;
	}
}
