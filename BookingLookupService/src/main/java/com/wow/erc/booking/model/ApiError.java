package com.wow.erc.booking.model;

public class ApiError {
	 
    private int returnCode;
    private String message;
 
    public ApiError() {
        super();
        this.returnCode = -1;
    }
    
    public ApiError(int returnCode, String message) {
        super();
        this.setReturnCode(returnCode);
        this.setMessage(message);
    }

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
