package com.dgjs.constants;

public enum RETURN_STATUS {

	SUCCESS("成功"),
	
	PARAM_ERROR("参数错误"),
	
	SERVICE_ERROR("业务错误"),
	
	SYSTEM_ERROR("系统错误");
	
	private String value;
	
	private RETURN_STATUS(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
