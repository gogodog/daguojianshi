package com.dgjs.model.outside;

public class ReturnData<T> {

	private Boolean isSuccess=true;
	
	private String errorCode;
	
    private String errorMessage;
    
    private T result;

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
    
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setErrorInfo(String errorCode,String errorMessage,T result){
    	this.isSuccess=false;
    	this.errorCode=errorCode;
    	this.errorMessage=errorMessage;
    	this.result=result;
    }
    
}
