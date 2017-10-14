/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 废票单人信息Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class VoidsheetPassenger extends DataEntity<VoidsheetPassenger> {
	
	private static final long serialVersionUID = 1L;
	private String voidsheetNo;		// 废票单号外键
	private String passengerName;		// 姓名
	private String ticketNo;		// 票号/凭证号
	private String firstSegment;		// 首段行程
	private String supplierSmtPrice;		// 上游机票结算价
	private String supplierVoidCharge;		// 上游废票费
	private String supplierOtherCharge;		// 上游附加费
	private String supplierVoidSmtFare;		// 上游废票结算价
	private String supplierInvoiceStatus;		// 上游凭证状态
	private String supplierModifyStatus;		// 上游调单状态
	private String distributorSmtPrice;		// 下游机票结算价
	private String voidsheetId;		// 废票单id
	private String supplierAuditStatus;		// 上游结算状态
	private String distributorAuditStatus;		// 下游结算状态
	private String orderPassengerId;		// 原订单旅客id
	private String distributorVoidCharge;		// 下游废票费
	private String distributorOtherCharge;		// 下游附加费
	private String distributorVoidSmtFare;		// 下游废票结算价
	private String distributorInvoiceStatus;		// 下游凭证状态
	private String distributorModifyStatus;		// 上游调单状态
	private String voidTerminal;		// 废票终端，取值例如1等
	private String voidOffice;		// 废票office
	private Date voidTime;		// 废票时间
	private String ticketTerminal;		// 出票终端
	private Date beginVoidTime;		// 开始 废票时间
	private Date endVoidTime;		// 结束 废票时间
	private String gender; //性别 1 是男 2 是女
	private String passengerType;		// 旅客类型（0成人，1儿童，4婴儿）
	private String passengerTitle;		// 旅客称谓(身份)(0,
	private String certType;		// 证件类型(0,
	private String certNo;		// 证件号码
	private String phone;//手机号
	
	
	
	
	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getPassengerTitle() {
		return passengerTitle;
	}

	public void setPassengerTitle(String passengerTitle) {
		this.passengerTitle = passengerTitle;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public VoidsheetPassenger() {
		super();
	}

	public VoidsheetPassenger(String id){
		super(id);
	}

	@Length(min=0, max=36, message="废票单号外键长度必须介于 0 和 36 之间")
	public String getVoidsheetNo() {
		return voidsheetNo;
	}

	public void setVoidsheetNo(String voidsheetNo) {
		this.voidsheetNo = voidsheetNo;
	}
	
	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	@Length(min=0, max=40, message="票号/凭证号长度必须介于 0 和 40 之间")
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Length(min=0, max=100, message="首段行程长度必须介于 0 和 100 之间")
	public String getFirstSegment() {
		return firstSegment;
	}

	public void setFirstSegment(String firstSegment) {
		this.firstSegment = firstSegment;
	}
	
	public String getSupplierSmtPrice() {
		return supplierSmtPrice;
	}

	public void setSupplierSmtPrice(String supplierSmtPrice) {
		this.supplierSmtPrice = supplierSmtPrice;
	}
	
	public String getSupplierVoidCharge() {
		return supplierVoidCharge;
	}

	public void setSupplierVoidCharge(String supplierVoidCharge) {
		this.supplierVoidCharge = supplierVoidCharge;
	}
	
	public String getSupplierOtherCharge() {
		return supplierOtherCharge;
	}

	public void setSupplierOtherCharge(String supplierOtherCharge) {
		this.supplierOtherCharge = supplierOtherCharge;
	}
	
	public String getSupplierVoidSmtFare() {
		return supplierVoidSmtFare;
	}

	public void setSupplierVoidSmtFare(String supplierVoidSmtFare) {
		this.supplierVoidSmtFare = supplierVoidSmtFare;
	}
	
	@Length(min=0, max=3, message="上游凭证状态长度必须介于 0 和 3 之间")
	public String getSupplierInvoiceStatus() {
		return supplierInvoiceStatus;
	}

	public void setSupplierInvoiceStatus(String supplierInvoiceStatus) {
		this.supplierInvoiceStatus = supplierInvoiceStatus;
	}
	
	@Length(min=0, max=3, message="上游调单状态长度必须介于 0 和 3 之间")
	public String getSupplierModifyStatus() {
		return supplierModifyStatus;
	}

	public void setSupplierModifyStatus(String supplierModifyStatus) {
		this.supplierModifyStatus = supplierModifyStatus;
	}
	
	public String getDistributorSmtPrice() {
		return distributorSmtPrice;
	}

	public void setDistributorSmtPrice(String distributorSmtPrice) {
		this.distributorSmtPrice = distributorSmtPrice;
	}
	
	@Length(min=0, max=11, message="废票单id长度必须介于 0 和 11 之间")
	public String getVoidsheetId() {
		return voidsheetId;
	}

	public void setVoidsheetId(String voidsheetId) {
		this.voidsheetId = voidsheetId;
	}
	
	@Length(min=0, max=3, message="上游结算状态长度必须介于 0 和 3 之间")
	public String getSupplierAuditStatus() {
		return supplierAuditStatus;
	}

	public void setSupplierAuditStatus(String supplierAuditStatus) {
		this.supplierAuditStatus = supplierAuditStatus;
	}
	
	@Length(min=0, max=3, message="下游结算状态长度必须介于 0 和 3 之间")
	public String getDistributorAuditStatus() {
		return distributorAuditStatus;
	}

	public void setDistributorAuditStatus(String distributorAuditStatus) {
		this.distributorAuditStatus = distributorAuditStatus;
	}
	
	@Length(min=0, max=11, message="原订单旅客id长度必须介于 0 和 11 之间")
	public String getOrderPassengerId() {
		return orderPassengerId;
	}

	public void setOrderPassengerId(String orderPassengerId) {
		this.orderPassengerId = orderPassengerId;
	}
	
	public String getDistributorVoidCharge() {
		return distributorVoidCharge;
	}

	public void setDistributorVoidCharge(String distributorVoidCharge) {
		this.distributorVoidCharge = distributorVoidCharge;
	}
	
	public String getDistributorOtherCharge() {
		return distributorOtherCharge;
	}

	public void setDistributorOtherCharge(String distributorOtherCharge) {
		this.distributorOtherCharge = distributorOtherCharge;
	}
	
	public String getDistributorVoidSmtFare() {
		return distributorVoidSmtFare;
	}

	public void setDistributorVoidSmtFare(String distributorVoidSmtFare) {
		this.distributorVoidSmtFare = distributorVoidSmtFare;
	}
	
	@Length(min=0, max=3, message="下游凭证状态长度必须介于 0 和 3 之间")
	public String getDistributorInvoiceStatus() {
		return distributorInvoiceStatus;
	}

	public void setDistributorInvoiceStatus(String distributorInvoiceStatus) {
		this.distributorInvoiceStatus = distributorInvoiceStatus;
	}
	
	@Length(min=0, max=3, message="上游调单状态长度必须介于 0 和 3 之间")
	public String getDistributorModifyStatus() {
		return distributorModifyStatus;
	}

	public void setDistributorModifyStatus(String distributorModifyStatus) {
		this.distributorModifyStatus = distributorModifyStatus;
	}
	
	@Length(min=0, max=50, message="废票终端，取值例如1等长度必须介于 0 和 50 之间")
	public String getVoidTerminal() {
		return voidTerminal;
	}

	public void setVoidTerminal(String voidTerminal) {
		this.voidTerminal = voidTerminal;
	}
	
	@Length(min=0, max=10, message="废票office长度必须介于 0 和 10 之间")
	public String getVoidOffice() {
		return voidOffice;
	}

	public void setVoidOffice(String voidOffice) {
		this.voidOffice = voidOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVoidTime() {
		return voidTime;
	}

	public void setVoidTime(Date voidTime) {
		this.voidTime = voidTime;
	}
	
	@Length(min=0, max=10, message="出票终端长度必须介于 0 和 10 之间")
	public String getTicketTerminal() {
		return ticketTerminal;
	}

	public void setTicketTerminal(String ticketTerminal) {
		this.ticketTerminal = ticketTerminal;
	}
	
	public Date getBeginVoidTime() {
		return beginVoidTime;
	}

	public void setBeginVoidTime(Date beginVoidTime) {
		this.beginVoidTime = beginVoidTime;
	}
	
	public Date getEndVoidTime() {
		return endVoidTime;
	}

	public void setEndVoidTime(Date endVoidTime) {
		this.endVoidTime = endVoidTime;
	}
		
}