package com.dgjs.model.enums;

public enum OperateEnum {

	Add(1, "新增操作"), Delete(2, "删除操作"), Browse(3, "浏览操作"), Update(4, "更新操作");

	private OperateEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;

	private String message;

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
