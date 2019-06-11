package com.wow.erc.booking.model;

public class BookingAudit {

	private String oldDeliveryDock;
	
	private String oldDeliveryDate;
	
	private String oldDeliveryTime;
	
	private String changedBy;
	
	private String changedOn;
	
	private String reasonCode;
	
	public BookingAudit() {
	}

	public String getOldDeliveryDock() {
		return oldDeliveryDock;
	}

	public void setOldDeliveryDock(String oldDeliveryDock) {
		this.oldDeliveryDock = oldDeliveryDock;
	}
	
	public String getOldDeliveryDate() {
		return oldDeliveryDate;
	}

	public void setOldDeliveryDate(String oldDeliveryDate) {
		this.oldDeliveryDate = oldDeliveryDate;
	}

	public String getOldDeliveryTime() {
		return oldDeliveryTime;
	}

	public void setOldDeliveryTime(String oldDeliveryTime) {
		this.oldDeliveryTime = oldDeliveryTime;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getChangedOn() {
		return changedOn;
	}

	public void setChangedOn(String changedOn) {
		this.changedOn = changedOn;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
}
