/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sml.sz.common.persistence.DataEntity;

/**
 * 改签单Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class EndorsesheetDetail extends DataEntity<EndorsesheetDetail> {
	
	private static final long serialVersionUID = 1L;
	private String endorsesheetNo;		// 改签单号
	private String orderNo;		// 机票订单号外键
	private String originEndorsesheetNo;		// 源改签单号
	private String oriCrsPnr;		// 原分销pnr
	private String newCrsPnr;		// 新分销pnr
	private String oriArlPnr;		// 原航司pnr
	private String newArlPnr;		// 新航司pnr
	private String oriMidPnr;		// 源中转pnr
	private String newMidPnr;		// 新中转pnr
	private String newOrderNo;		// 新生成的订单表外键
	private String endorseStatus;		// 改签单状态(1,
	private String currentGroup;		// 流转到的组
	private String currentOperator;		// 流转到的人
	private String relevantClient;		// 本单所属客户
	private String createAccount;		// 本单创建者
	private Date createTime;		// 本单创建时间
	private String nextOperation;		// 后续操作
	private String endorseType;		// 改签类型:(自愿/非自愿)
	private String payStatus;		// 支付状态
	private String isOverloanNegoOrder;		// 是否金额特单，1=是，0=否
	private String createType;		// 创建方式
	private String createAccountId;		// 本单创建者id
	private String receptionCustomerId;		// 前台客户id
	private String stationCreaterId;		// 中台创建操作员id
	private String firstSegment;		// 首段改程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15
	private String endorseAirline;		// 改签航司
	private String orderId;		// 订单id
	private String remark;		// 备注
	private String isInternational;		// 国内国际
	private String supplierServiceCharge;		// 上游服务费
	private String distributorServiceCharge;		// 下游服务费
	private String supplierProductNo;		// 上游产品号（销售相关）
	private String saleConfig;		// 销售配置，取值例如bsp等
	private String isFit;		// 散客或团队，使用枚举0-散客，1-团队，2-混合
	private String supplierProductName;		// 上游产品商（销售相关）
	private String manRemark1;		// 手工标记1
	private String manRemark2;		// 手工标记2
	private String lastmodifierOrgid;		// 最后修改机构id
	private String lastmodifierOrgname;		// 最后修改机构名称
	private String lastmodifierId;		// 最后修改人id
	private String lastmodifierName;		// 最后修改人名
	private Date lastmodifierTime;		// 最后修改时间
	private String busiRemark;		// 业务说明
	private String paymentRemark;		// 上游支付方式说明
	private String handleLater;		// handle_later
	private Date lastCirculationTime;		// 最后流转时间
	private String isPriceConfirmed;		// 价格是否确认，0:不确认；1:确认
	private String serialNumber;		// 折扣号
	
	private String endorseDetailType;//改签类型 1 改期单 2 改程单 3 升舱单
	private String currentStatus;//操作状态
	private Date minTime; // 条件查询的最小时间
	private Date maxTime; // 条件查询的最大时间
	
	private String ticketNo;//票号
	private String ticketNoTemp;//页面展示的票号
	
	private String duration;//时长
	
	private String passengers;//该订单下的旅客信息
	private String passengersTemp;//该订单下的旅客的信息页面展示
	
	private String segments;//航段信息
	private String segmentsTemp;//页面展示的航段信息
	
	private double endorseTotalCost;//改签费用总金额
	private double endorseUpgradeCost;//改签费用（改签差价+改签手续费+改签服务费+改签附加费）
	private double endorseReimburseCredentials;//报销凭证费用
	private double insuranceCosts;//保险费用
	private double endorseTaxes;//改签税费
	
	private String invoice;//是否有消费凭证
	
	
private Date goMainTraveldate;//邮寄时间
	
	
	
	
	
	public Date getGoMainTraveldate() {
		return goMainTraveldate;
	}

	public void setGoMainTraveldate(Date goMainTraveldate) {
		this.goMainTraveldate = goMainTraveldate;
	}
	
	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public double getEndorseTaxes() {
		return endorseTaxes;
	}

	public void setEndorseTaxes(double endorseTaxes) {
		this.endorseTaxes = endorseTaxes;
	}

	public double getEndorseTotalCost() {
		return endorseTotalCost;
	}

	public void setEndorseTotalCost(double endorseTotalCost) {
		this.endorseTotalCost = endorseTotalCost;
	}

	public double getEndorseUpgradeCost() {
		return endorseUpgradeCost;
	}

	public void setEndorseUpgradeCost(double endorseUpgradeCost) {
		this.endorseUpgradeCost = endorseUpgradeCost;
	}

	public double getEndorseReimburseCredentials() {
		return endorseReimburseCredentials;
	}

	public void setEndorseReimburseCredentials(double endorseReimburseCredentials) {
		this.endorseReimburseCredentials = endorseReimburseCredentials;
	}

	public double getInsuranceCosts() {
		return insuranceCosts;
	}

	public void setInsuranceCosts(double insuranceCosts) {
		this.insuranceCosts = insuranceCosts;
	}

	public String getTicketNoTemp() {
		return ticketNoTemp;
	}

	public void setTicketNoTemp(String ticketNoTemp) {
		this.ticketNoTemp = ticketNoTemp;
	}

	public String getPassengersTemp() {
		return passengersTemp;
	}

	public void setPassengersTemp(String passengersTemp) {
		this.passengersTemp = passengersTemp;
	}

	public String getSegments() {
		return segments;
	}

	public void setSegments(String segments) {
		this.segments = segments;
	}

	public String getSegmentsTemp() {
		return segmentsTemp;
	}

	public void setSegmentsTemp(String segmentsTemp) {
		this.segmentsTemp = segmentsTemp;
	}

	public String getPassengers() {
		return passengers;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getEndorseDetailType() {
		return endorseDetailType;
	}

	public void setEndorseDetailType(String endorseDetailType) {
		this.endorseDetailType = endorseDetailType;
	}

	public EndorsesheetDetail() {
		super();
	}

	public EndorsesheetDetail(String id){
		super(id);
	}

	@Length(min=0, max=36, message="改签单号长度必须介于 0 和 36 之间")
	public String getEndorsesheetNo() {
		return endorsesheetNo;
	}

	public void setEndorsesheetNo(String endorsesheetNo) {
		this.endorsesheetNo = endorsesheetNo;
	}
	
	@Length(min=0, max=36, message="机票订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=36, message="源改签单号长度必须介于 0 和 36 之间")
	public String getOriginEndorsesheetNo() {
		return originEndorsesheetNo;
	}

	public void setOriginEndorsesheetNo(String originEndorsesheetNo) {
		this.originEndorsesheetNo = originEndorsesheetNo;
	}
	
	@Length(min=0, max=100, message="原分销pnr长度必须介于 0 和 100 之间")
	public String getOriCrsPnr() {
		return oriCrsPnr;
	}

	public void setOriCrsPnr(String oriCrsPnr) {
		this.oriCrsPnr = oriCrsPnr;
	}
	
	@Length(min=0, max=100, message="新分销pnr长度必须介于 0 和 100 之间")
	public String getNewCrsPnr() {
		return newCrsPnr;
	}

	public void setNewCrsPnr(String newCrsPnr) {
		this.newCrsPnr = newCrsPnr;
	}
	
	@Length(min=0, max=100, message="原航司pnr长度必须介于 0 和 100 之间")
	public String getOriArlPnr() {
		return oriArlPnr;
	}

	public void setOriArlPnr(String oriArlPnr) {
		this.oriArlPnr = oriArlPnr;
	}
	
	@Length(min=0, max=100, message="新航司pnr长度必须介于 0 和 100 之间")
	public String getNewArlPnr() {
		return newArlPnr;
	}

	public void setNewArlPnr(String newArlPnr) {
		this.newArlPnr = newArlPnr;
	}
	
	@Length(min=0, max=100, message="源中转pnr长度必须介于 0 和 100 之间")
	public String getOriMidPnr() {
		return oriMidPnr;
	}

	public void setOriMidPnr(String oriMidPnr) {
		this.oriMidPnr = oriMidPnr;
	}
	
	@Length(min=0, max=100, message="新中转pnr长度必须介于 0 和 100 之间")
	public String getNewMidPnr() {
		return newMidPnr;
	}

	public void setNewMidPnr(String newMidPnr) {
		this.newMidPnr = newMidPnr;
	}
	
	@Length(min=0, max=36, message="新生成的订单表外键长度必须介于 0 和 36 之间")
	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}
	
	@Length(min=0, max=3, message="改签单状态(1,长度必须介于 0 和 3 之间")
	public String getEndorseStatus() {
		return endorseStatus;
	}

	public void setEndorseStatus(String endorseStatus) {
		this.endorseStatus = endorseStatus;
	}
	
	@Length(min=0, max=11, message="流转到的组长度必须介于 0 和 11 之间")
	public String getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(String currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	@Length(min=0, max=11, message="流转到的人长度必须介于 0 和 11 之间")
	public String getCurrentOperator() {
		return currentOperator;
	}

	public void setCurrentOperator(String currentOperator) {
		this.currentOperator = currentOperator;
	}
	
	@Length(min=0, max=100, message="本单所属客户长度必须介于 0 和 100 之间")
	public String getRelevantClient() {
		return relevantClient;
	}

	public void setRelevantClient(String relevantClient) {
		this.relevantClient = relevantClient;
	}
	
	@Length(min=0, max=100, message="本单创建者长度必须介于 0 和 100 之间")
	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=11, message="后续操作长度必须介于 0 和 11 之间")
	public String getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
	}
	
	@Length(min=0, max=10, message="改签类型:(自愿/非自愿)长度必须介于 0 和 10 之间")
	public String getEndorseType() {
		return endorseType;
	}

	public void setEndorseType(String endorseType) {
		this.endorseType = endorseType;
	}
	
	@Length(min=0, max=3, message="支付状态长度必须介于 0 和 3 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Length(min=0, max=3, message="是否金额特单，1=是，0=否长度必须介于 0 和 3 之间")
	public String getIsOverloanNegoOrder() {
		return isOverloanNegoOrder;
	}

	public void setIsOverloanNegoOrder(String isOverloanNegoOrder) {
		this.isOverloanNegoOrder = isOverloanNegoOrder;
	}
	
	@Length(min=0, max=3, message="创建方式长度必须介于 0 和 3 之间")
	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}
	
	@Length(min=0, max=11, message="本单创建者id长度必须介于 0 和 11 之间")
	public String getCreateAccountId() {
		return createAccountId;
	}

	public void setCreateAccountId(String createAccountId) {
		this.createAccountId = createAccountId;
	}
	
	@Length(min=0, max=11, message="前台客户id长度必须介于 0 和 11 之间")
	public String getReceptionCustomerId() {
		return receptionCustomerId;
	}

	public void setReceptionCustomerId(String receptionCustomerId) {
		this.receptionCustomerId = receptionCustomerId;
	}
	
	@Length(min=0, max=11, message="中台创建操作员id长度必须介于 0 和 11 之间")
	public String getStationCreaterId() {
		return stationCreaterId;
	}

	public void setStationCreaterId(String stationCreaterId) {
		this.stationCreaterId = stationCreaterId;
	}
	
	@Length(min=0, max=100, message="首段改程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15长度必须介于 0 和 100 之间")
	public String getFirstSegment() {
		return firstSegment;
	}

	public void setFirstSegment(String firstSegment) {
		this.firstSegment = firstSegment;
	}
	
	@Length(min=0, max=5, message="改签航司长度必须介于 0 和 5 之间")
	public String getEndorseAirline() {
		return endorseAirline;
	}

	public void setEndorseAirline(String endorseAirline) {
		this.endorseAirline = endorseAirline;
	}
	
	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=4000, message="备注长度必须介于 0 和 4000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=3, message="国内国际长度必须介于 0 和 3 之间")
	public String getIsInternational() {
		return isInternational;
	}

	public void setIsInternational(String isInternational) {
		this.isInternational = isInternational;
	}
	
	public String getSupplierServiceCharge() {
		return supplierServiceCharge;
	}

	public void setSupplierServiceCharge(String supplierServiceCharge) {
		this.supplierServiceCharge = supplierServiceCharge;
	}
	
	public String getDistributorServiceCharge() {
		return distributorServiceCharge;
	}

	public void setDistributorServiceCharge(String distributorServiceCharge) {
		this.distributorServiceCharge = distributorServiceCharge;
	}
	
	@Length(min=0, max=100, message="上游产品号（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductNo() {
		return supplierProductNo;
	}

	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
	}
	
	@Length(min=0, max=100, message="销售配置，取值例如bsp等长度必须介于 0 和 100 之间")
	public String getSaleConfig() {
		return saleConfig;
	}

	public void setSaleConfig(String saleConfig) {
		this.saleConfig = saleConfig;
	}
	
	@Length(min=0, max=3, message="散客或团队，使用枚举0-散客，1-团队，2-混合长度必须介于 0 和 3 之间")
	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}
	
	@Length(min=0, max=100, message="上游产品商（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductName() {
		return supplierProductName;
	}

	public void setSupplierProductName(String supplierProductName) {
		this.supplierProductName = supplierProductName;
	}
	
	@Length(min=0, max=500, message="手工标记1长度必须介于 0 和 500 之间")
	public String getManRemark1() {
		return manRemark1;
	}

	public void setManRemark1(String manRemark1) {
		this.manRemark1 = manRemark1;
	}
	
	@Length(min=0, max=500, message="手工标记2长度必须介于 0 和 500 之间")
	public String getManRemark2() {
		return manRemark2;
	}

	public void setManRemark2(String manRemark2) {
		this.manRemark2 = manRemark2;
	}
	
	@Length(min=0, max=11, message="最后修改机构id长度必须介于 0 和 11 之间")
	public String getLastmodifierOrgid() {
		return lastmodifierOrgid;
	}

	public void setLastmodifierOrgid(String lastmodifierOrgid) {
		this.lastmodifierOrgid = lastmodifierOrgid;
	}
	
	@Length(min=0, max=200, message="最后修改机构名称长度必须介于 0 和 200 之间")
	public String getLastmodifierOrgname() {
		return lastmodifierOrgname;
	}

	public void setLastmodifierOrgname(String lastmodifierOrgname) {
		this.lastmodifierOrgname = lastmodifierOrgname;
	}
	
	@Length(min=0, max=11, message="最后修改人id长度必须介于 0 和 11 之间")
	public String getLastmodifierId() {
		return lastmodifierId;
	}

	public void setLastmodifierId(String lastmodifierId) {
		this.lastmodifierId = lastmodifierId;
	}
	
	@Length(min=0, max=200, message="最后修改人名长度必须介于 0 和 200 之间")
	public String getLastmodifierName() {
		return lastmodifierName;
	}

	public void setLastmodifierName(String lastmodifierName) {
		this.lastmodifierName = lastmodifierName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastmodifierTime() {
		return lastmodifierTime;
	}

	public void setLastmodifierTime(Date lastmodifierTime) {
		this.lastmodifierTime = lastmodifierTime;
	}
	
	@Length(min=0, max=1000, message="业务说明长度必须介于 0 和 1000 之间")
	public String getBusiRemark() {
		return busiRemark;
	}

	public void setBusiRemark(String busiRemark) {
		this.busiRemark = busiRemark;
	}
	
	@Length(min=0, max=500, message="上游支付方式说明长度必须介于 0 和 500 之间")
	public String getPaymentRemark() {
		return paymentRemark;
	}

	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}
	
	@Length(min=0, max=11, message="handle_later长度必须介于 0 和 11 之间")
	public String getHandleLater() {
		return handleLater;
	}

	public void setHandleLater(String handleLater) {
		this.handleLater = handleLater;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastCirculationTime() {
		return lastCirculationTime;
	}

	public void setLastCirculationTime(Date lastCirculationTime) {
		this.lastCirculationTime = lastCirculationTime;
	}
	
	@Length(min=0, max=11, message="价格是否确认，0:不确认；1:确认长度必须介于 0 和 11 之间")
	public String getIsPriceConfirmed() {
		return isPriceConfirmed;
	}

	public void setIsPriceConfirmed(String isPriceConfirmed) {
		this.isPriceConfirmed = isPriceConfirmed;
	}
	
	@Length(min=0, max=50, message="折扣号长度必须介于 0 和 50 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}