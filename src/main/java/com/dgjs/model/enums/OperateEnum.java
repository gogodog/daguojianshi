package com.dgjs.model.enums;

public enum OperateEnum {

	Add(1, "新增操作"), Delete(2, "删除操作"), Browse(3, "浏览操作"), Update(4, "更新操作");

	private OperateEnum(int key, String value) {
		this.key = key;
		this.value = value;
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

	public static OperateEnum valueOf(int key){
		switch (key) {
		case 1:
           return Add;
		case 2:
	       return Delete;
		case 3:
	       return Browse;
		case 4:
	       return Update;
		default:
			break;
		}
		return null;
	}
}
