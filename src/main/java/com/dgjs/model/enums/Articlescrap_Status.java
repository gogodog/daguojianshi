package com.dgjs.model.enums;

public enum Articlescrap_Status {
	
	INIT(2,"初始化"),
	
    UP(1,"上架"),
	
	DOWN(0,"下架");
	
	 private Articlescrap_Status(int key,String value){
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
	 
	 public static Articlescrap_Status valueOf(int key){
		 switch (key) {
		 case 0:
			return INIT;
		 case 1:
			return UP;
		 case 2:
			return DOWN;
		 default:
			break;
		 }
		 return null;
	 }
}
