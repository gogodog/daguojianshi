package com.dgjs.model.view;

import com.dgjs.constants.RETURN_STATUS;

public class BaseView {
	
	private boolean isError=false;
	private String errorCode;
	private String errorMessage;
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
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
	
	public void setBaseViewValue(String errorCode,String errorMessage){
		this.isError=true;
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
	
	public void setBaseViewValue(RETURN_STATUS return_status){
		if(return_status==RETURN_STATUS.SUCCESS){
			this.isError=false;
		}else{
			this.isError=true;
			this.errorCode=return_status.toString();
			this.errorMessage=return_status.getValue();
		}
	}
}
