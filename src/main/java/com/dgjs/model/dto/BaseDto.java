package com.dgjs.model.dto;

public class BaseDto {

	private Boolean isSuccess=true;
	
	private String errorCode;
	
    private String errorMessage;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    public void setErrorInfo(String errorCode,String errorMessage){
    	this.isSuccess=false;
    	this.errorCode=errorCode;
    	this.errorMessage=errorMessage;
    }
}
