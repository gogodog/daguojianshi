package com.dgjs.exceptions;

import com.dgjs.model.result.view.BaseView;

public class TransactionException extends RuntimeException{

	private static final long serialVersionUID = 8585885506335016286L;
	
	private BaseView returnData;

	public BaseView getReturnData() {
		return returnData;
	}

	public void setReturnData(BaseView returnData) {
		this.returnData = returnData;
	}
	
	public TransactionException(String errorCode, String msg) {
		super(msg);
		this.returnData = new BaseView();
		this.returnData.setErrorCode(errorCode);
		this.returnData.setErrorMessage(msg);
	}

	public TransactionException(String errorCode) {
		super();
		this.returnData = new BaseView();
		this.returnData.setErrorCode(errorCode);
	}
	
	public TransactionException(Throwable arg0,String errorCode,String msg) {
		super(arg0);
		this.returnData = new BaseView();
		this.returnData.setErrorCode(errorCode);
		this.returnData.setErrorMessage(msg);
	}

}