package com.wow.erc.booking.model;

import java.util.List;

public class Booking {

	private String poNo;

	private String deliveryDock;
	
	private String dcNo;
	
	private String vendorNo;
	
	private String orderDate;
	
	private String deliveryDate;
	
	private String deliveryTime;
	
	private String noOfPallets;
	
	private String source;
	
	private String status;
	
	private List<BookingAudit> auditList;
	
	public Booking() {
	}

	public Booking(String bookingId, String poNo, String deliveryDock, String dcNo, String vendorNo, String orderDate, String deliveryDate, String deliveryTime, String noOfPallets, String source, String status) {
		super();
		this.poNo = poNo;
		this.deliveryDock = deliveryDock;
		this.dcNo = dcNo ;
		this.vendorNo = vendorNo ;
		this.orderDate = orderDate ;
		this.deliveryDate = deliveryDate ;
		this.deliveryTime = deliveryTime ;
		this.noOfPallets = noOfPallets ;
		this.source = source ;
		this.status = status ;
	}
	
	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getDeliveryDock() {
		return deliveryDock;
	}

	public void setDeliveryDock(String deliveryDock) {
		this.deliveryDock = deliveryDock;
	}

	public String getDcNo() {
		return dcNo;
	}

	public void setDcNo(String dcNo) {
		this.dcNo = dcNo;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getNoOfPallets() {
		return noOfPallets;
	}

	public void setNoOfPallets(String noOfPallets) {
		this.noOfPallets = noOfPallets;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BookingAudit> getAuditList() {
		return auditList;
	}

	public void setAuditList(List<BookingAudit> auditList) {
		this.auditList = auditList;
	}
}
