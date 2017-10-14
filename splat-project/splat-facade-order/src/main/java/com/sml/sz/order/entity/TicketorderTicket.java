package com.sml.sz.order.entity;

import java.util.Date;

import com.sml.sz.common.persistence.DataEntity;
/**
 * 每个票号一个记录
 * @author lqc
 *
 */
public class TicketorderTicket extends DataEntity<TicketorderTicket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNo;//订单号
	private String ticketNo;//票号
	private String ticketingOffice;//出票offer
	private String printerNo;//打票机号
	private String detrInfo;//detr原文（存不存数据库）
	private String detrFInfo;//detrf原文
	private String orderId;//订单id
	private Date issuedTime;//退废配置终端
	private String refundVoidConfiguration;//退废配置终端
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getTicketingOffice() {
		return ticketingOffice;
	}
	public void setTicketingOffice(String ticketingOffice) {
		this.ticketingOffice = ticketingOffice;
	}
	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}
	public String getDetrInfo() {
		return detrInfo;
	}
	public void setDetrInfo(String detrInfo) {
		this.detrInfo = detrInfo;
	}
	public String getDetrFInfo() {
		return detrFInfo;
	}
	public void setDetrFInfo(String detrFInfo) {
		this.detrFInfo = detrFInfo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getIssuedTime() {
		return issuedTime;
	}
	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}
	public String getRefundVoidConfiguration() {
		return refundVoidConfiguration;
	}
	public void setRefundVoidConfiguration(String refundVoidConfiguration) {
		this.refundVoidConfiguration = refundVoidConfiguration;
	}
	
    
    
    
    
}