package com.dgjs.model.enums;

/**
 * 文章分类
 */
public enum Articlescrap_Type {

	HISTORY(1,"大国正史"),
	
	UNOFFICIAL_HISTORY(2,"大国野史");
	
	private Articlescrap_Type(int key,String value){
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
	
	public static Articlescrap_Type valueOf(int key){
		 switch (key) {
        case 1:
            return HISTORY;
        case 2:
            return UNOFFICIAL_HISTORY;
        default:
            return null;
	  }
    }
}
