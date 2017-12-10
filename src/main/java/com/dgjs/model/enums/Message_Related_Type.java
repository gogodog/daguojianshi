package com.dgjs.model.enums;

public enum Message_Related_Type {

	FeedBack(1,"反馈消息");
	
	private Message_Related_Type(int key,String value){
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
	
	public static Message_Related_Type valueOf(int key){
		switch (key) {
		case 1:
			return FeedBack;
		default:
			break;
		}
		return null;
	}
}
