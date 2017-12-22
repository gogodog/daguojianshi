package com.dgjs.exceptions;

public class WechatHttpException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8685281927403702758L;

	@Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String code;
    String message;

    public WechatHttpException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}