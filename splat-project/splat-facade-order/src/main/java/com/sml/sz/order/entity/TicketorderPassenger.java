/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sml.sz.common.persistence.DataEntity;

/**
 * 订单旅客的信息Entity
 * @author 李千超
 * @version 2016-03-10
 */
public class TicketorderPassenger extends DataEntity<TicketorderPassenger> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 所属订单号外键
	private String passengerName;		// 旅客姓名
	private String passengerIndex;		// 旅客序号
	private String passengerType;		// 旅客类型（0成人，1儿童，4婴儿）
	private String passengerTitle;		// 旅客称谓(身份)(0,
	private String certType;		// 证件类型(0,
	private String certNo;		// 证件号码
	private Date passengerBirthday;		// 出生日期
	
	private String passengerBirthdayStr;//接受页面的时间值
	
	
	private String docaR;		// 始发国地址
	private String docaD;		// 目的国地址
	private String ticketNo;		// 票号冗余
	private String singlePriceId;		// 单人价格id
	private String frequentCardType;		// 常旅客卡类型  类似于vip卡有积分可以换门票啥的 在pnr里边解析出来的 不一定每个人都有
	private String frequentCardNo;		// 常旅客卡号
	private String rvcSheetNo;		// 退废改期升舱关联单据号
	private String orderId;		// 订单id
	private String refundStatus;		// 退票状态
	private String voidStatus;		// 废票状态
	private String endorseStatus;		// 改签状态
	private String supplierRefundStatus;		// 上游退票状态
	private String supplierVoidStatus;		// 上游废票状态
	private String supplierEndorseStatus;		// 上游改签状态
	private String ticketIssueTerminal;		// 出票终端，取值例如6等
	private String ticketIssueOffice;		// 出票office
	private Date issueTime;		// 出票时间
	private String national;		// 国籍
	private String certificateCountry;		// 发证国家
	private Date expiredtime;		// 证件过期时间
	
	private String expiredtimeStr; //接受页面穿过来的证件过期时间
	private String gender;		// 性别 1是男 2 是女
	private String phone;		// 电话
	private String pnrName;		// eterm黑屏中的旅客姓名带称谓
	private String invoice;		// 1：行程单（四个行程是一个行程单超过则在开启一张）；2：代表发票
	
	private String passengerNamecn;//中文名
	
	private String voidRefundEndorse;//退票废票改签票； 1：改签状态 2：废票状态 3：退票 
	
	
	
	public String getPassengerBirthdayStr() {
		return passengerBirthdayStr;
	}

	public void setPassengerBirthdayStr(String passengerBirthdayStr) {
		this.passengerBirthdayStr = passengerBirthdayStr;
	}

	public String getExpiredtimeStr() {
		return expiredtimeStr;
	}

	public void setExpiredtimeStr(String expiredtimeStr) {
		this.expiredtimeStr = expiredtimeStr;
	}

	public String getVoidRefundEndorse() {
		return voidRefundEndorse;
	}

	public void setVoidRefundEndorse(String voidRefundEndorse) {
		this.voidRefundEndorse = voidRefundEndorse;
	}

	public TicketorderPassenger() {
		super();
	}

	public TicketorderPassenger(String id){
		super(id);
	}

	
	public String getPassengerNamecn() {
		return passengerNamecn;
	}

	public void setPassengerNamecn(String passengerNamecn) {
		this.passengerNamecn = passengerNamecn;
	}

	@Length(min=0, max=36, message="所属订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=60, message="旅客姓名长度必须介于 0 和 60 之间")
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
	
	@Length(min=0, max=30, message="旅客类型（0成人，1儿童，4婴儿）长度必须介于 0 和 30 之间")
	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	
	@Length(min=0, max=3, message="旅客称谓(身份)(0,长度必须介于 0 和 3 之间")
	public String getPassengerTitle() {
		return passengerTitle;
	}

	public void setPassengerTitle(String passengerTitle) {
		this.passengerTitle = passengerTitle;
	}
	
	@Length(min=0, max=2, message="证件类型(0,长度必须介于 0 和 2 之间")
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
	
	@Length(min=0, max=1000, message="始发国地址长度必须介于 0 和 1000 之间")
	public String getDocaR() {
		return docaR;
	}

	public void setDocaR(String docaR) {
		this.docaR = docaR;
	}
	
	@Length(min=0, max=1000, message="目的国地址长度必须介于 0 和 1000 之间")
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
	
	@Length(min=0, max=50, message="出票终端，取值例如6等长度必须介于 0 和 50 之间")
	public String getTicketIssueTerminal() {
		return ticketIssueTerminal;
	}

	public void setTicketIssueTerminal(String ticketIssueTerminal) {
		this.ticketIssueTerminal = ticketIssueTerminal;
	}
	
	@Length(min=0, max=30, message="出票office长度必须介于 0 和 30 之间")
	public String getTicketIssueOffice() {
		return ticketIssueOffice;
	}

	public void setTicketIssueOffice(String ticketIssueOffice) {
		this.ticketIssueOffice = ticketIssueOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	
	@Length(min=0, max=20, message="国籍长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=30, message="电话长度必须介于 0 和 30 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="eterm黑屏中的旅客姓名带称谓长度必须介于 0 和 100 之间")
	public String getPnrName() {
		return pnrName;
	}

	public void setPnrName(String pnrName) {
		this.pnrName = pnrName;
	}
	
	@Length(min=0, max=2, message="1：行程单（四个行程是一个行程单超过则在开启一张）；2：代表发票长度必须介于 0 和 2 之间")
	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
}