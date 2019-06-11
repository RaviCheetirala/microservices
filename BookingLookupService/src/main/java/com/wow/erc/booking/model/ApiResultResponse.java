package com.wow.erc.booking.model;

import java.util.List;

public class ApiResultResponse {

	private int returnCode;
	
	private String message;
	 
	private List<Booking> resultList;

	public ApiResultResponse() {
        super();
        this.returnCode = -1;
    }
	
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public List<Booking> getResultList() {
		return resultList;
	}

	public void setResultList(List<Booking> resultList) {
		this.resultList = resultList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
