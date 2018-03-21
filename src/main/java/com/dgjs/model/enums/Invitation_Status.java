package com.dgjs.model.enums;

public enum Invitation_Status {

	VALID(1,"可用"),
	
	USED(2,"已用"),
	
	OUTOFDATE(3,"过期");
	
	private Invitation_Status(int key,String value){
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
	
	public static Invitation_Status valueOf(int key){
		switch (key) {
		case 1:
			return VALID;
		case 2:
			return USED;
		case 3:
			return OUTOFDATE;
		default:
			break;
		}
		return null;
	}
}
