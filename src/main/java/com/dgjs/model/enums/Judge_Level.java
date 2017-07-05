package com.dgjs.model.enums;

public enum Judge_Level {

	REAL(10,"真实"),
	
	DOUBT(20,"有疑问"),
	
	UNREAL(30,"不真实");
	
	private Judge_Level(int key,String value){
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
    
	public static Judge_Level valueOf(int key){
		switch (key) {
		case 10:
			return REAL;
		case 20:
			return DOUBT;
		case 30:
			return UNREAL;
		default:
			return null;
		}
	}
    
}
