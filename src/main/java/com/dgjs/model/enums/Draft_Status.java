package com.dgjs.model.enums;

public enum Draft_Status {
	
	NORMAL(1,"正常"),
	
	SUBMIT(2,"已提审");

	private int key;
	
	private String value;
	
	private Draft_Status(int key,String value){
		this.key=key;
		this.value=value;
	}

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
	
	public static Draft_Status valueOf(int key){
		switch (key) {
		case 1:
			return NORMAL;
		case 2:
			return SUBMIT;
		default:
			break;
		}
		return null;
	}
	
	public static Integer transTo(Draft_Status status){
		return status==null?null:status.getKey();
	}
	
	public static Draft_Status transFrom(Integer key){
		return key==null?null:Draft_Status.valueOf(key);
	}
}
