/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 收件人Entity
 * @author 李千超
 * @version 2016-03-30
 */
public class Recipient extends DataEntity<Recipient> {
	
	private static final long serialVersionUID = 1L;
	private String recipientNo;		// 收件人的序列号
	private String recipientName;		// 收件人姓名
	private String card;		// 身份证
	private String mobile;		// 手机号
	private String phone;		// phone
	private Date sendTimer;		// 发送时间
	private String sendTimerStr;  //精确到小时
	
	private String remark;		// 备注
	private String buyerId;		// 采购员的id
	private String operatorId;		// 操作员的id
	private String orderNo; // 订单号
	private String mailNumber;//邮编
	private String recipientMoney;//费用
	private String address;//收件人地址
	
	
	
	public String getSendTimerStr() {
		return sendTimerStr;
	}

	public void setSendTimerStr(String sendTimerStr) {
		this.sendTimerStr = sendTimerStr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailNumber() {
		return mailNumber;
	}

	public void setMailNumber(String mailNumber) {
		this.mailNumber = mailNumber;
	}

	public String getRecipientMoney() {
		return recipientMoney;
	}

	public void setRecipientMoney(String recipientMoney) {
		this.recipientMoney = recipientMoney;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Recipient() {
		super();
	}

	public Recipient(String id){
		super(id);
	}

	@Length(min=0, max=255, message="收件人的序列号长度必须介于 0 和 255 之间")
	public String getRecipientNo() {
		return recipientNo;
	}

	public void setRecipientNo(String recipientNo) {
		this.recipientNo = recipientNo;
	}
	
	@Length(min=0, max=255, message="收件人姓名长度必须介于 0 和 255 之间")
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	
	@Length(min=0, max=255, message="身份证长度必须介于 0 和 255 之间")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=0, max=255, message="手机号长度必须介于 0 和 255 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=255, message="phone长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTimer() {
		return sendTimer;
	}

	public void setSendTimer(Date sendTimer) {
		this.sendTimer = sendTimer;
	}
	
	@Length(min=0, max=2000, message="备注长度必须介于 0 和 2000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=255, message="采购员的id长度必须介于 0 和 255 之间")
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Length(min=0, max=255, message="操作员的id长度必须介于 0 和 255 之间")
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
}