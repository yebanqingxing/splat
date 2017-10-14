/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 退票单人信息Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class RefundsheetPassenger extends DataEntity<RefundsheetPassenger> {
	
	private static final long serialVersionUID = 1L;
	private String refundsheetNo;		// 退票单外键
	private String passengerName;		// 姓名
	private String ticketNo;		// 票号/凭证号
	private String fistRefundSegment;		// 首段退程
	private String supplierSettlementPrice;		// 上游机票结算价
	private String supplierUsedSellPrice;		// 上游已用销售价
	private String supplierUsedTax;		// 上游已用税
	private String supplierRefundCharge;		// 上游退票费
	private String supplierOtherCharge;		// 上游附加费
	private String refundsheetId;		// 退票单id
	private String supplierAuditStatus;		// 上游结算状态
	private String distributorAuditStatus;		// 下游结算状态
	private String orderPassengerId;		// 原订单旅客id
	private String supplierRefundSmtPrice;		// 上游退票结算价
	private String supplierInvoiceStatus;		// 上游凭证状态
	private String supplierModifyStatus;		// 上游调单状态
	private String distributorSettlementPrice;		// 下游机票结算价
	private String supplierServiceCharge;		// 上游服务费
	private String distributorUsedSellPrice;		// 下游已用销售价
	private String distributorUsedTax;		// 下游已用税
	private String distributorRefundCharge;		// 下游退票费
	private String distributorOtherCharge;		// 下游附加费
	private String distributorServiceCharge;		// 下游服务费
	private String distributorRefundSmtPrice;		// 下游退票结算价
	private String distributorInvoiceStatus;		// 下游凭证状态
	private String distributorModifyStatus;		// 下游调单状态
	private Date refundTime;		// 退票时间
	private String refundTerminal;		// 退票终端，取值例如6等
	private String refundOffice;		// 退票office
	private String ticketTerminal;		// 出票配置
	private Date beginRefundTime;		// 开始 退票时间
	private Date endRefundTime;		// 结束 退票时间
	private String passengerTitle;		// 旅客称谓(身份)(0,
	private String passengerType;		// 旅客类型（0成人，1儿童，4婴儿）
	private String certType;		// 证件类型(0,
	private String gender;		// 性别
	private String phone;		// 电话
	private String certNo; //证件号
	
	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public RefundsheetPassenger() {
		super();
	}

	public RefundsheetPassenger(String id){
		super(id);
	}

	@Length(min=0, max=36, message="退票单外键长度必须介于 0 和 36 之间")
	public String getRefundsheetNo() {
		return refundsheetNo;
	}

	public void setRefundsheetNo(String refundsheetNo) {
		this.refundsheetNo = refundsheetNo;
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
	
	@Length(min=0, max=100, message="首段退程长度必须介于 0 和 100 之间")
	public String getFistRefundSegment() {
		return fistRefundSegment;
	}

	public void setFistRefundSegment(String fistRefundSegment) {
		this.fistRefundSegment = fistRefundSegment;
	}
	
	public String getSupplierSettlementPrice() {
		return supplierSettlementPrice;
	}

	public void setSupplierSettlementPrice(String supplierSettlementPrice) {
		this.supplierSettlementPrice = supplierSettlementPrice;
	}
	
	public String getSupplierUsedSellPrice() {
		return supplierUsedSellPrice;
	}

	public void setSupplierUsedSellPrice(String supplierUsedSellPrice) {
		this.supplierUsedSellPrice = supplierUsedSellPrice;
	}
	
	public String getSupplierUsedTax() {
		return supplierUsedTax;
	}

	public void setSupplierUsedTax(String supplierUsedTax) {
		this.supplierUsedTax = supplierUsedTax;
	}
	
	public String getSupplierRefundCharge() {
		return supplierRefundCharge;
	}

	public void setSupplierRefundCharge(String supplierRefundCharge) {
		this.supplierRefundCharge = supplierRefundCharge;
	}
	
	public String getSupplierOtherCharge() {
		return supplierOtherCharge;
	}

	public void setSupplierOtherCharge(String supplierOtherCharge) {
		this.supplierOtherCharge = supplierOtherCharge;
	}
	
	@Length(min=0, max=11, message="退票单id长度必须介于 0 和 11 之间")
	public String getRefundsheetId() {
		return refundsheetId;
	}

	public void setRefundsheetId(String refundsheetId) {
		this.refundsheetId = refundsheetId;
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
	
	public String getSupplierRefundSmtPrice() {
		return supplierRefundSmtPrice;
	}

	public void setSupplierRefundSmtPrice(String supplierRefundSmtPrice) {
		this.supplierRefundSmtPrice = supplierRefundSmtPrice;
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
	
	public String getDistributorSettlementPrice() {
		return distributorSettlementPrice;
	}

	public void setDistributorSettlementPrice(String distributorSettlementPrice) {
		this.distributorSettlementPrice = distributorSettlementPrice;
	}
	
	public String getSupplierServiceCharge() {
		return supplierServiceCharge;
	}

	public void setSupplierServiceCharge(String supplierServiceCharge) {
		this.supplierServiceCharge = supplierServiceCharge;
	}
	
	public String getDistributorUsedSellPrice() {
		return distributorUsedSellPrice;
	}

	public void setDistributorUsedSellPrice(String distributorUsedSellPrice) {
		this.distributorUsedSellPrice = distributorUsedSellPrice;
	}
	
	public String getDistributorUsedTax() {
		return distributorUsedTax;
	}

	public void setDistributorUsedTax(String distributorUsedTax) {
		this.distributorUsedTax = distributorUsedTax;
	}
	
	public String getDistributorRefundCharge() {
		return distributorRefundCharge;
	}

	public void setDistributorRefundCharge(String distributorRefundCharge) {
		this.distributorRefundCharge = distributorRefundCharge;
	}
	
	public String getDistributorOtherCharge() {
		return distributorOtherCharge;
	}

	public void setDistributorOtherCharge(String distributorOtherCharge) {
		this.distributorOtherCharge = distributorOtherCharge;
	}
	
	public String getDistributorServiceCharge() {
		return distributorServiceCharge;
	}

	public void setDistributorServiceCharge(String distributorServiceCharge) {
		this.distributorServiceCharge = distributorServiceCharge;
	}
	
	public String getDistributorRefundSmtPrice() {
		return distributorRefundSmtPrice;
	}

	public void setDistributorRefundSmtPrice(String distributorRefundSmtPrice) {
		this.distributorRefundSmtPrice = distributorRefundSmtPrice;
	}
	
	@Length(min=0, max=3, message="下游凭证状态长度必须介于 0 和 3 之间")
	public String getDistributorInvoiceStatus() {
		return distributorInvoiceStatus;
	}

	public void setDistributorInvoiceStatus(String distributorInvoiceStatus) {
		this.distributorInvoiceStatus = distributorInvoiceStatus;
	}
	
	@Length(min=0, max=3, message="下游调单状态长度必须介于 0 和 3 之间")
	public String getDistributorModifyStatus() {
		return distributorModifyStatus;
	}

	public void setDistributorModifyStatus(String distributorModifyStatus) {
		this.distributorModifyStatus = distributorModifyStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	
	@Length(min=0, max=50, message="退票终端，取值例如6等长度必须介于 0 和 50 之间")
	public String getRefundTerminal() {
		return refundTerminal;
	}

	public void setRefundTerminal(String refundTerminal) {
		this.refundTerminal = refundTerminal;
	}
	
	@Length(min=0, max=20, message="退票office长度必须介于 0 和 20 之间")
	public String getRefundOffice() {
		return refundOffice;
	}

	public void setRefundOffice(String refundOffice) {
		this.refundOffice = refundOffice;
	}
	
	@Length(min=0, max=10, message="出票配置长度必须介于 0 和 10 之间")
	public String getTicketTerminal() {
		return ticketTerminal;
	}

	public void setTicketTerminal(String ticketTerminal) {
		this.ticketTerminal = ticketTerminal;
	}
	
	public Date getBeginRefundTime() {
		return beginRefundTime;
	}

	public void setBeginRefundTime(Date beginRefundTime) {
		this.beginRefundTime = beginRefundTime;
	}
	
	public Date getEndRefundTime() {
		return endRefundTime;
	}

	public void setEndRefundTime(Date endRefundTime) {
		this.endRefundTime = endRefundTime;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
		
}