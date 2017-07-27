package com.dgjs.model.enums;

public enum Index_Type {

    HISTORY(10,"正史"),
	
	PERSON(20,"人物"),
	
	GEOGRAPHY(30,"地理"),
	
    AFFAIRS(50,"时事"),
	
	UNOFFICIAL(60,"野史");
    
    private Index_Type(int key,String value){
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
    
    public static Index_Type valueOf(int key){
    	switch (key) {
		case 10:
			return HISTORY;
		case 20:
			return PERSON;
		case 30:
			return GEOGRAPHY;
		case 50:
			return AFFAIRS;
		case 60:
			return UNOFFICIAL;
		default:
			return null;
		}
    }
}