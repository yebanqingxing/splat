/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机票订单pnrEntity
 * @author 李千超
 * @version 2016-03-10
 */
public class TicketorderPnr extends DataEntity<TicketorderPnr> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 所属订单号外键
	private String crsPnr;		// crs_pnr
	private String arlPnr;		// arl_pnr
	private String midPnr;		// mid_pnr
	private Date bookingTime;		// pnr生成日期（主机）
	private String pnrStatus;		// pnr状态0-正常/1-异常/2-已删除
	private String bookingOffice;		// 预定office（责任组）（授权、清q）
	private Date recordTime;		// 创建时间（数据创建时间）
	private String ticketingOffice;		// 出票office（rt中不能直接取到,detr能）
	private String pnrText;		// pnr原文（存）
	private String salePriceElements;		// 销售价项集合
	private String taxElements;		// 税项集合
	private String commissionElements;		// 代理费率项集合
	private String tcElements;		// tc项集合
	private String eiElements;		// ei项集合
	private String fareBasisElements;		// 票价基础项集合
	private String ticketingConfig;		// 出票配置
	private String fcElements;		// fc项集合
	private String orderId;		// 订单id
	private String pnrTextAppend;		// pnr_text原文追加(主字段存储不够追加字段)
	private String osiCtct;		// osi ctct项
	private String pnrInfo;		// pnrinfo（jinri）
	
	public TicketorderPnr() {
		super();
	}

	public TicketorderPnr(String id){
		super(id);
	}

	@Length(min=0, max=36, message="所属订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=100, message="crs_pnr长度必须介于 0 和 100 之间")
	public String getCrsPnr() {
		return crsPnr;
	}

	public void setCrsPnr(String crsPnr) {
		this.crsPnr = crsPnr;
	}
	
	@Length(min=0, max=100, message="arl_pnr长度必须介于 0 和 100 之间")
	public String getArlPnr() {
		return arlPnr;
	}

	public void setArlPnr(String arlPnr) {
		this.arlPnr = arlPnr;
	}
	
	@Length(min=0, max=100, message="mid_pnr长度必须介于 0 和 100 之间")
	public String getMidPnr() {
		return midPnr;
	}

	public void setMidPnr(String midPnr) {
		this.midPnr = midPnr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}
	
	@Length(min=0, max=3, message="pnr状态0-正常/1-异常/2-已删除长度必须介于 0 和 3 之间")
	public String getPnrStatus() {
		return pnrStatus;
	}

	public void setPnrStatus(String pnrStatus) {
		this.pnrStatus = pnrStatus;
	}
	
	@Length(min=0, max=10, message="预定office（责任组）（授权、清q）长度必须介于 0 和 10 之间")
	public String getBookingOffice() {
		return bookingOffice;
	}

	public void setBookingOffice(String bookingOffice) {
		this.bookingOffice = bookingOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	@Length(min=0, max=10, message="出票office（rt中不能直接取到,detr能）长度必须介于 0 和 10 之间")
	public String getTicketingOffice() {
		return ticketingOffice;
	}

	public void setTicketingOffice(String ticketingOffice) {
		this.ticketingOffice = ticketingOffice;
	}
	
	@Length(min=0, max=4000, message="pnr原文（存）长度必须介于 0 和 4000 之间")
	public String getPnrText() {
		return pnrText;
	}

	public void setPnrText(String pnrText) {
		this.pnrText = pnrText;
	}
	
	@Length(min=0, max=200, message="销售价项集合长度必须介于 0 和 200 之间")
	public String getSalePriceElements() {
		return salePriceElements;
	}

	public void setSalePriceElements(String salePriceElements) {
		this.salePriceElements = salePriceElements;
	}
	
	@Length(min=0, max=100, message="税项集合长度必须介于 0 和 100 之间")
	public String getTaxElements() {
		return taxElements;
	}

	public void setTaxElements(String taxElements) {
		this.taxElements = taxElements;
	}
	
	@Length(min=0, max=100, message="代理费率项集合长度必须介于 0 和 100 之间")
	public String getCommissionElements() {
		return commissionElements;
	}

	public void setCommissionElements(String commissionElements) {
		this.commissionElements = commissionElements;
	}
	
	@Length(min=0, max=200, message="tc项集合长度必须介于 0 和 200 之间")
	public String getTcElements() {
		return tcElements;
	}

	public void setTcElements(String tcElements) {
		this.tcElements = tcElements;
	}
	
	@Length(min=0, max=200, message="ei项集合长度必须介于 0 和 200 之间")
	public String getEiElements() {
		return eiElements;
	}

	public void setEiElements(String eiElements) {
		this.eiElements = eiElements;
	}
	
	@Length(min=0, max=100, message="票价基础项集合长度必须介于 0 和 100 之间")
	public String getFareBasisElements() {
		return fareBasisElements;
	}

	public void setFareBasisElements(String fareBasisElements) {
		this.fareBasisElements = fareBasisElements;
	}
	
	@Length(min=0, max=100, message="出票配置长度必须介于 0 和 100 之间")
	public String getTicketingConfig() {
		return ticketingConfig;
	}

	public void setTicketingConfig(String ticketingConfig) {
		this.ticketingConfig = ticketingConfig;
	}
	
	@Length(min=0, max=1000, message="fc项集合长度必须介于 0 和 1000 之间")
	public String getFcElements() {
		return fcElements;
	}

	public void setFcElements(String fcElements) {
		this.fcElements = fcElements;
	}
	
	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=4000, message="pnr_text原文追加(主字段存储不够追加字段)长度必须介于 0 和 4000 之间")
	public String getPnrTextAppend() {
		return pnrTextAppend;
	}

	public void setPnrTextAppend(String pnrTextAppend) {
		this.pnrTextAppend = pnrTextAppend;
	}
	
	@Length(min=0, max=100, message="osi ctct项长度必须介于 0 和 100 之间")
	public String getOsiCtct() {
		return osiCtct;
	}

	public void setOsiCtct(String osiCtct) {
		this.osiCtct = osiCtct;
	}
	
	@Length(min=0, max=500, message="pnrinfo（jinri）长度必须介于 0 和 500 之间")
	public String getPnrInfo() {
		return pnrInfo;
	}

	public void setPnrInfo(String pnrInfo) {
		this.pnrInfo = pnrInfo;
	}
	
}