/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 改签单旅客Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class EndorsesheetPassenger extends DataEntity<EndorsesheetPassenger> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 所属订单号外键
	private String passengerName;		// 旅客姓名
	private String passengerIndex;		// 旅客序号
	private String passengerType;		// 旅客类型
	private String passengerTitle;		// 旅客称谓
	private String certType;		// 证件类型
	private String certNo;		// 证件号码
	private Date passengerBirthday;		// 出生日期
	private String docaR;		// 始发国地址
	private String docaD;		// 目的国地址
	private String ticketNo;		// 票号冗余
	private String singlePriceId;		// 单人价格id
	private String frequentCardType;		// 常旅客卡类型
	private String frequentCardNo;		// 常旅客卡号
	private String rvcSheetNo;		// 退废改期升舱关联单据号
	private String orderId;		// 订单id
	private String refundStatus;		// 退票状态
	private String voidStatus;		// 废票状态
	private String endorseStatus;		// 改签状态
	private String endorseOffice;		// 改签office
	private String endorseTerminal;		// 改签终端，取值例如6等
	private Date endorseTime;		// 改签时间
	private String supplierRefundStatus;		// 上游退票状态
	private String supplierVoidStatus;		// 上游废票状态
	private String supplierEndorseStatus;		// 上游改签状态
	private String national;		// 国籍
	private String certificateCountry;		// 发证国家
	private Date expiredtime;		// 证件过期时间
	private String gender;		// 性别
	private String phone;		// 电话
	private String ticketTerminal;		// 出票配置
	
	private String voidRefundEndorse;//退票废票改签票； 1：改签状态 2：废票状态 3：退票 
	
	private String endorseType;//改签类型:(自愿/非自愿) 1 自愿改签 2 非自愿改签
	private double endorsePriceDifference;//改签差价
	private double endorsePriceTaxes;//改期税费
	private double endorseCommission;//改签代理费率
	private double endorseUpgradeFee;//改签手续费
	private double endorseServiceCharge;//改签服务费
	private double endorseSurcharge;//改签附加费
	
	private String orderPassengerId;		// 原订单旅客id
	
	
	
	
	public String getOrderPassengerId() {
		return orderPassengerId;
	}

	public void setOrderPassengerId(String orderPassengerId) {
		this.orderPassengerId = orderPassengerId;
	}

	public double getEndorsePriceDifference() {
		return endorsePriceDifference;
	}

	public void setEndorsePriceDifference(double endorsePriceDifference) {
		this.endorsePriceDifference = endorsePriceDifference;
	}

	public double getEndorsePriceTaxes() {
		return endorsePriceTaxes;
	}

	public void setEndorsePriceTaxes(double endorsePriceTaxes) {
		this.endorsePriceTaxes = endorsePriceTaxes;
	}

	public double getEndorseCommission() {
		return endorseCommission;
	}

	public void setEndorseCommission(double endorseCommission) {
		this.endorseCommission = endorseCommission;
	}

	public double getEndorseUpgradeFee() {
		return endorseUpgradeFee;
	}

	public void setEndorseUpgradeFee(double endorseUpgradeFee) {
		this.endorseUpgradeFee = endorseUpgradeFee;
	}

	public double getEndorseServiceCharge() {
		return endorseServiceCharge;
	}

	public void setEndorseServiceCharge(double endorseServiceCharge) {
		this.endorseServiceCharge = endorseServiceCharge;
	}

	public double getEndorseSurcharge() {
		return endorseSurcharge;
	}

	public void setEndorseSurcharge(double endorseSurcharge) {
		this.endorseSurcharge = endorseSurcharge;
	}

	public String getEndorseType() {
		return endorseType;
	}

	public void setEndorseType(String endorseType) {
		this.endorseType = endorseType;
	}

	public String getVoidRefundEndorse() {
		return voidRefundEndorse;
	}

	public void setVoidRefundEndorse(String voidRefundEndorse) {
		this.voidRefundEndorse = voidRefundEndorse;
	}
	public EndorsesheetPassenger() {
		super();
	}

	public EndorsesheetPassenger(String id){
		super(id);
	}

	@Length(min=0, max=36, message="所属订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=50, message="旅客姓名长度必须介于 0 和 50 之间")
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	@Length(min=0, max=11, message="旅客序号长度必须介于 0 和 11 之间")
	public String getPassengerIndex() {
		return passengerIndex;
	}

	public void setPassengerIndex(String passengerIndex) {
		this.passengerIndex = passengerIndex;
	}
	
	@Length(min=0, max=30, message="旅客类型长度必须介于 0 和 30 之间")
	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	
	@Length(min=0, max=3, message="旅客称谓长度必须介于 0 和 3 之间")
	public String getPassengerTitle() {
		return passengerTitle;
	}

	public void setPassengerTitle(String passengerTitle) {
		this.passengerTitle = passengerTitle;
	}
	
	@Length(min=0, max=2, message="证件类型长度必须介于 0 和 2 之间")
	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}
	
	@Length(min=0, max=40, message="证件号码长度必须介于 0 和 40 之间")
	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPassengerBirthday() {
		return passengerBirthday;
	}

	public void setPassengerBirthday(Date passengerBirthday) {
		this.passengerBirthday = passengerBirthday;
	}
	
	@Length(min=0, max=100, message="始发国地址长度必须介于 0 和 100 之间")
	public String getDocaR() {
		return docaR;
	}

	public void setDocaR(String docaR) {
		this.docaR = docaR;
	}
	
	@Length(min=0, max=100, message="目的国地址长度必须介于 0 和 100 之间")
	public String getDocaD() {
		return docaD;
	}

	public void setDocaD(String docaD) {
		this.docaD = docaD;
	}
	
	@Length(min=0, max=40, message="票号冗余长度必须介于 0 和 40 之间")
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Length(min=0, max=11, message="单人价格id长度必须介于 0 和 11 之间")
	public String getSinglePriceId() {
		return singlePriceId;
	}

	public void setSinglePriceId(String singlePriceId) {
		this.singlePriceId = singlePriceId;
	}
	
	@Length(min=0, max=3, message="常旅客卡类型长度必须介于 0 和 3 之间")
	public String getFrequentCardType() {
		return frequentCardType;
	}

	public void setFrequentCardType(String frequentCardType) {
		this.frequentCardType = frequentCardType;
	}
	
	@Length(min=0, max=20, message="常旅客卡号长度必须介于 0 和 20 之间")
	public String getFrequentCardNo() {
		return frequentCardNo;
	}

	public void setFrequentCardNo(String frequentCardNo) {
		this.frequentCardNo = frequentCardNo;
	}
	
	@Length(min=0, max=36, message="退废改期升舱关联单据号长度必须介于 0 和 36 之间")
	public String getRvcSheetNo() {
		return rvcSheetNo;
	}

	public void setRvcSheetNo(String rvcSheetNo) {
		this.rvcSheetNo = rvcSheetNo;
	}
	
	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=3, message="退票状态长度必须介于 0 和 3 之间")
	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	
	@Length(min=0, max=3, message="废票状态长度必须介于 0 和 3 之间")
	public String getVoidStatus() {
		return voidStatus;
	}

	public void setVoidStatus(String voidStatus) {
		this.voidStatus = voidStatus;
	}
	
	@Length(min=0, max=3, message="改签状态长度必须介于 0 和 3 之间")
	public String getEndorseStatus() {
		return endorseStatus;
	}

	public void setEndorseStatus(String endorseStatus) {
		this.endorseStatus = endorseStatus;
	}
	
	@Length(min=0, max=10, message="改签office长度必须介于 0 和 10 之间")
	public String getEndorseOffice() {
		return endorseOffice;
	}

	public void setEndorseOffice(String endorseOffice) {
		this.endorseOffice = endorseOffice;
	}
	
	@Length(min=0, max=50, message="改签终端，取值例如6等长度必须介于 0 和 50 之间")
	public String getEndorseTerminal() {
		return endorseTerminal;
	}

	public void setEndorseTerminal(String endorseTerminal) {
		this.endorseTerminal = endorseTerminal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndorseTime() {
		return endorseTime;
	}

	public void setEndorseTime(Date endorseTime) {
		this.endorseTime = endorseTime;
	}
	
	@Length(min=0, max=3, message="上游退票状态长度必须介于 0 和 3 之间")
	public String getSupplierRefundStatus() {
		return supplierRefundStatus;
	}

	public void setSupplierRefundStatus(String supplierRefundStatus) {
		this.supplierRefundStatus = supplierRefundStatus;
	}
	
	@Length(min=0, max=3, message="上游废票状态长度必须介于 0 和 3 之间")
	public String getSupplierVoidStatus() {
		return supplierVoidStatus;
	}

	public void setSupplierVoidStatus(String supplierVoidStatus) {
		this.supplierVoidStatus = supplierVoidStatus;
	}
	
	@Length(min=0, max=3, message="上游改签状态长度必须介于 0 和 3 之间")
	public String getSupplierEndorseStatus() {
		return supplierEndorseStatus;
	}

	public void setSupplierEndorseStatus(String supplierEndorseStatus) {
		this.supplierEndorseStatus = supplierEndorseStatus;
	}
	
	@Length(min=0, max=10, message="国籍长度必须介于 0 和 10 之间")
	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}
	
	@Length(min=0, max=10, message="发证国家长度必须介于 0 和 10 之间")
	public String getCertificateCountry() {
		return certificateCountry;
	}

	public void setCertificateCountry(String certificateCountry) {
		this.certificateCountry = certificateCountry;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpiredtime() {
		return expiredtime;
	}

	public void setExpiredtime(Date expiredtime) {
		this.expiredtime = expiredtime;
	}
	
	@Length(min=0, max=5, message="性别长度必须介于 0 和 5 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=15, message="电话长度必须介于 0 和 15 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=10, message="出票配置长度必须介于 0 和 10 之间")
	public String getTicketTerminal() {
		return ticketTerminal;
	}

	public void setTicketTerminal(String ticketTerminal) {
		this.ticketTerminal = ticketTerminal;
	}
	
}