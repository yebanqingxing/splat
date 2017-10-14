/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机票订单单人价格（机票明细）Entity
 * @author 李千超
 * @version 2016-03-25
 */
public class TicketorderSinglePrice extends DataEntity<TicketorderSinglePrice> {
	
	private static final long serialVersionUID = 1L;
	private Double supplierSalePrice=(double) 0;		// 上游销售价
	private Double supplierTax=(double) 0;		// 上游税
	private Double supplierAdditionalCharge=(double) 0;		// 上游附加费
	
	private Double supplierTicketPrice=(double) 0;		// 上游票面价(记奖价)
	
	private String supplierZ;		// 上游z值
	private String supplierQ;		// 上游q值
	
	
	private Double supplierCommission=(double) 0;		// 上游代理费率(政策返点)
	
	private Double supplierSettlementPrice=(double) 0;		// 上游单人结算价（单张结算价）
	
	private String supplierReceiptStatus;		// 上游凭证状态
	private String supplierModifyStatus;		// 上游调单状态
	private Double distributorSalePrice=(double) 0;		// 下游销售价
	private Double distributorTax=(double) 0;		// 下游税
	private Double distributorAdditionCharge=(double) 0;		// 下游附加费
	
	
	private String distributorTicketPrice;		// 下游票面价
	
	private String distributorZ;		// 下游z值
	private String distributorQ;		// 下游q值
	
	private Double distributorCommission=(double) 0;		// 下游代理费率（代理费）
	
	private Double distributorSettlementPrice=(double) 0;		// 下游单人结算价（供应商开票费用）
	
	private String distributorReceiptStatus;		// 下游凭证状态
	private String distributorModifyStatus;		// 下游调单状态
	private String pricePtc;		// 运价ptc类型，例如cnn/chd(国内)/adt/stu/inf
	private String orderNo;		// 订单主表所属外键
	private String orderId;		// 订单id
	private String supplierAuditStatus;		// 上游结算状态
	private String distributorAuditStatus;		// 下游结算状态
	private Double taxYq=(double) 0;		// 燃油税
	
	private Double taxCn=(double) 0;		// 机建税(税费)
	
	private Double fareBasisCode=(double) 0;		// 票价基础
	private String baggage;		// 行李信息
	private String passengerName;//旅客姓名
	private String passengerType;//旅客性别
	

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public TicketorderSinglePrice() {
		super();
	}

	public TicketorderSinglePrice(String id){
		super(id);
	}

	public Double getSupplierSalePrice() {
		return supplierSalePrice;
	}

	public void setSupplierSalePrice(Double supplierSalePrice) {
		this.supplierSalePrice = supplierSalePrice;
	}
	
	public Double getSupplierTax() {
		return supplierTax;
	}

	public void setSupplierTax(Double supplierTax) {
		this.supplierTax = supplierTax;
	}
	
	public double getSupplierAdditionalCharge() {
		return supplierAdditionalCharge;
	}

	public void setSupplierAdditionalCharge(Double supplierAdditionalCharge) {
		this.supplierAdditionalCharge = supplierAdditionalCharge;
	}
	
	public Double getSupplierTicketPrice() {
		return supplierTicketPrice;
	}

	public void setSupplierTicketPrice(Double supplierTicketPrice) {
		this.supplierTicketPrice = supplierTicketPrice;
	}
	
	public String getSupplierZ() {
		return supplierZ;
	}

	public void setSupplierZ(String supplierZ) {
		this.supplierZ = supplierZ;
	}
	
	public String getSupplierQ() {
		return supplierQ;
	}

	public void setSupplierQ(String supplierQ) {
		this.supplierQ = supplierQ;
	}
	
	public Double getSupplierCommission() {
		return supplierCommission;
	}

	public void setSupplierCommission(Double supplierCommission) {
		this.supplierCommission = supplierCommission;
	}
	
	public Double getSupplierSettlementPrice() {
		return supplierSettlementPrice;
	}

	public void setSupplierSettlementPrice(Double supplierSettlementPrice) {
		this.supplierSettlementPrice = supplierSettlementPrice;
	}
	
	@Length(min=0, max=3, message="上游凭证状态长度必须介于 0 和 3 之间")
	public String getSupplierReceiptStatus() {
		return supplierReceiptStatus;
	}

	public void setSupplierReceiptStatus(String supplierReceiptStatus) {
		this.supplierReceiptStatus = supplierReceiptStatus;
	}
	
	@Length(min=0, max=3, message="上游调单状态长度必须介于 0 和 3 之间")
	public String getSupplierModifyStatus() {
		return supplierModifyStatus;
	}

	public void setSupplierModifyStatus(String supplierModifyStatus) {
		this.supplierModifyStatus = supplierModifyStatus;
	}
	
	public Double getDistributorSalePrice() {
		return distributorSalePrice;
	}

	public void setDistributorSalePrice(Double distributorSalePrice) {
		this.distributorSalePrice = distributorSalePrice;
	}
	
	public Double getDistributorTax() {
		return distributorTax;
	}

	public void setDistributorTax(Double distributorTax) {
		this.distributorTax = distributorTax;
	}
	
	public Double getDistributorAdditionCharge() {
		return distributorAdditionCharge;
	}

	public void setDistributorAdditionCharge(Double distributorAdditionCharge) {
		this.distributorAdditionCharge = distributorAdditionCharge;
	}
	
	public String getDistributorTicketPrice() {
		return distributorTicketPrice;
	}

	public void setDistributorTicketPrice(String distributorTicketPrice) {
		this.distributorTicketPrice = distributorTicketPrice;
	}
	
	public String getDistributorZ() {
		return distributorZ;
	}

	public void setDistributorZ(String distributorZ) {
		this.distributorZ = distributorZ;
	}
	
	public String getDistributorQ() {
		return distributorQ;
	}

	public void setDistributorQ(String distributorQ) {
		this.distributorQ = distributorQ;
	}
	
	public Double getDistributorCommission() {
		return distributorCommission;
	}

	public void setDistributorCommission(Double distributorCommission) {
		this.distributorCommission = distributorCommission;
	}
	
	public Double getDistributorSettlementPrice() {
		return distributorSettlementPrice;
	}

	public void setDistributorSettlementPrice(Double distributorSettlementPrice) {
		this.distributorSettlementPrice = distributorSettlementPrice;
	}
	
	@Length(min=0, max=3, message="下游凭证状态长度必须介于 0 和 3 之间")
	public String getDistributorReceiptStatus() {
		return distributorReceiptStatus;
	}

	public void setDistributorReceiptStatus(String distributorReceiptStatus) {
		this.distributorReceiptStatus = distributorReceiptStatus;
	}
	
	@Length(min=0, max=3, message="下游调单状态长度必须介于 0 和 3 之间")
	public String getDistributorModifyStatus() {
		return distributorModifyStatus;
	}

	public void setDistributorModifyStatus(String distributorModifyStatus) {
		this.distributorModifyStatus = distributorModifyStatus;
	}
	
	@Length(min=0, max=10, message="运价ptc类型，例如cnn/chd(国内)/adt/stu/inf长度必须介于 0 和 10 之间")
	public String getPricePtc() {
		return pricePtc;
	}

	public void setPricePtc(String pricePtc) {
		this.pricePtc = pricePtc;
	}
	
	@Length(min=0, max=36, message="订单主表所属外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	
	public Double getTaxYq() {
		return taxYq;
	}

	public void setTaxYq(Double taxYq) {
		this.taxYq = taxYq;
	}
	
	public Double getTaxCn() {
		return taxCn;
	}

	public void setTaxCn(Double taxCn) {
		this.taxCn = taxCn;
	}
	
	@Length(min=0, max=50, message="票价基础长度必须介于 0 和 50 之间")
	public Double getFareBasisCode() {
		return fareBasisCode;
	}

	public void setFareBasisCode(Double fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}
	
	@Length(min=0, max=100, message="行李信息长度必须介于 0 和 100 之间")
	public String getBaggage() {
		return baggage;
	}

	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}
	
}