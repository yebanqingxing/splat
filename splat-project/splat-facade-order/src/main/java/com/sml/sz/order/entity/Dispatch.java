/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 行程单派送单Entity
 * @author 李千超
 * @version 2016-03-30
 */
public class Dispatch extends DataEntity<Dispatch> {
	
	private static final long serialVersionUID = 1L;
	private String dispatchNo;		// 派送单号
	private Date createTime;		// 创建时间
	private String pnr号;		// pnr号
	private String orderNo;		// 订单号
	private String proposer;		// 申请人
	private String dispatchName;		// 派送人名名字
	private String openMoney;		// 填开金额（不含税）
	private String certificateMoney;		// 凭证费用
	private String taxpoint;		// 税点
	private String ticketNo;		// 票号
	private String dispatchStatus;		// 派送状态 1：没有派送， 2：正在派送， 3：已删除
	private String invoiceStatus;		// 行程单状态 1 已退票 2  已废票 3 已改签
	private String isplatfrom;		// 谁发送行程单 1：平台， 2供应商
	private Date dispatchTime;		// 派送日期
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	private Date beginDispatchTime;		// 开始 派送日期
	private Date endDispatchTime;		// 结束 派送日期
	
	public Dispatch() {
		super();
	}

	public Dispatch(String id){
		super(id);
	}

	@Length(min=0, max=255, message="派送单号长度必须介于 0 和 255 之间")
	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=255, message="pnr号长度必须介于 0 和 255 之间")
	public String getPnr号() {
		return pnr号;
	}

	public void setPnr号(String pnr号) {
		this.pnr号 = pnr号;
	}
	
	@Length(min=0, max=255, message="订单号长度必须介于 0 和 255 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	
	@Length(min=0, max=255, message="派送人名名字长度必须介于 0 和 255 之间")
	public String getDispatchName() {
		return dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}
	
	public String getOpenMoney() {
		return openMoney;
	}

	public void setOpenMoney(String openMoney) {
		this.openMoney = openMoney;
	}
	
	public String getCertificateMoney() {
		return certificateMoney;
	}

	public void setCertificateMoney(String certificateMoney) {
		this.certificateMoney = certificateMoney;
	}
	
	public String getTaxpoint() {
		return taxpoint;
	}

	public void setTaxpoint(String taxpoint) {
		this.taxpoint = taxpoint;
	}
	
	@Length(min=0, max=11, message="票号长度必须介于 0 和 11 之间")
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Length(min=0, max=3, message="派送状态 1：没有派送， 2：正在派送， 3：已删除长度必须介于 0 和 3 之间")
	public String getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	
	@Length(min=0, max=3, message="行程单状态 1 已退票 2  已废票 3 已改签长度必须介于 0 和 3 之间")
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	@Length(min=0, max=3, message="谁发送行程单 1：平台， 2供应商长度必须介于 0 和 3 之间")
	public String getIsplatfrom() {
		return isplatfrom;
	}

	public void setIsplatfrom(String isplatfrom) {
		this.isplatfrom = isplatfrom;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
		
	public Date getBeginDispatchTime() {
		return beginDispatchTime;
	}

	public void setBeginDispatchTime(Date beginDispatchTime) {
		this.beginDispatchTime = beginDispatchTime;
	}
	
	public Date getEndDispatchTime() {
		return endDispatchTime;
	}

	public void setEndDispatchTime(Date endDispatchTime) {
		this.endDispatchTime = endDispatchTime;
	}
		
}