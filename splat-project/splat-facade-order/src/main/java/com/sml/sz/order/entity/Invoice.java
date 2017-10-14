/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 行程单Entity
 * @author 李千超
 * @version 2016-03-24
 */
public class Invoice extends DataEntity<Invoice> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号外键
	private String invoiceType;		// 消费凭证类型 1发票 2 行程单
	private String openMoney;		// 填开金额（票面价）
	private String certificateMoney;		// 凭证费用
	private String invoiceName;		// 凭证费用名
	private String taxpoint;		// 税点
	
	public Invoice() {
		super();
	}

	public Invoice(String id){
		super(id);
	}

	@Length(min=0, max=255, message="订单号外键长度必须介于 0 和 255 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=255, message="消费凭证类型 1发票 2 行程单长度必须介于 0 和 255 之间")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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
	
	@Length(min=0, max=255, message="凭证费用名长度必须介于 0 和 255 之间")
	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	
	public String getTaxpoint() {
		return taxpoint;
	}

	public void setTaxpoint(String taxpoint) {
		this.taxpoint = taxpoint;
	}
	
}