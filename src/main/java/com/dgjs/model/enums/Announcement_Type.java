package com.dgjs.model.enums;

public enum Announcement_Type {

	formal(1,"正式的"),
	
	informal(2,"非正式的");
	
	private Announcement_Type(int key,String value){
		this.key = key;
		this.value =value;
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
	
	public static Announcement_Type valueOf(int key){
		switch (key) {
		case 1:
			return formal;
		case 2:
			return informal;
		default:
			break;
		}
		return null;
	}
}
