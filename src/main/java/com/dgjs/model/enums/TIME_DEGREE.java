package com.dgjs.model.enums;

public enum TIME_DEGREE {

    YEAR(1,"年"),
    
    MONTH(2,"月"),
    
    DAY(3,"日");
    
    private TIME_DEGREE(int key,String value){
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
    
	public static TIME_DEGREE valueOf(int key){
		switch (key) {
		case 1:
			return YEAR;
		case 2:
			return MONTH;
		case 3:
			return DAY;
		default:
			break;
		}
		return null;
	}
}
