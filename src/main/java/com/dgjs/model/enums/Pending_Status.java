package com.dgjs.model.enums;

public enum Pending_Status {

	AUDIT_PENDING(10,"待审核"),
	
	Audit_FAIL(20,"审核未通过"),
	
	PUBLISH_PENDING(30,"待发布"),
	
	PUBLISHED(40,"已发布");
	
	private Pending_Status(int key,String value){
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
	
	public static Pending_Status valueOf(int key){
		switch (key) {
		case 10:
			return AUDIT_PENDING;
		case 20:
			return Audit_FAIL;
		case 30:
			return PUBLISH_PENDING;
		case 40:
			return PUBLISHED;
		default:
			break;
		}
		return null;
	}
	
	public static Pending_Status transFrom(Integer key){
		if(key == null){
			return null;
		}
		return Pending_Status.valueOf(key);
	}
	
	public static Integer transTo(Pending_Status status){
		if(status==null){
			return null;
		}
		return status.getKey();
	}
}
