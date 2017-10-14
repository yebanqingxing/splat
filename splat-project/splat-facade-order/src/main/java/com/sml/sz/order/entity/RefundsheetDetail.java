/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 退票单Entity
 * @author 李千超
 * @version 2016-03-14
 */
public class RefundsheetDetail extends DataEntity<RefundsheetDetail> {
	
	private static final long serialVersionUID = 1L;
	private String refundsheetNo;		// 退票单号
	private String orderNo;		// 机票订单号外键
	private String bspRefundNo;		// bsp退票单号
	private String crsPnr;		// crs_pnr
	private String arlPnr;		// arl_pnr
	private String midPnr;		// mid_pnr
	private String refundsheetStatus;// 退票单状态(1, "已提交"), refust(2, "已拒单"), audit(3, "已核单"), refund(4, "已退票"), delete(5, "已删除");
	private String refundType;		// 退票单类型
	private String isInternational;		// 国内或国际
	private String isFit;		// 散客或团队
	private String currentGroup;		// 流转到的组
	private String currentOperator;		// 流转到的人
	private String relevantClient;		// 本单所属客户
	private String createAccount;		// 本单创建者
	private Date createTime;		// 本单创建时间
	private String nextOperation;		// 后续操作
	private String originRefundsheetNo;		// 源退票单号
	private String payStatus;		// 支付状态
	private String createType;		// 创建方式
	private String receptionCustomerId;		// 前台客户id
	private String createAccountId;		// 本单创建者id
	private String stationCreaterId;		// 中台创建操作员id
	private String firstSegment;		// 首段退程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15
	private String orderId;		// 订单id
	private String remark;		// 备注
	private String supplierTotSettlementPrice;		// 上游退票结算价
	private String distributorTotSmtPrice;		// 下游退票结算价
	private String supplierProductNo;		// 上游产品号（销售相关）
	private String issueAirline;		// 出票主航司
	private String saleConfig;		// 销售配置，取值例如bsp等
	private String supplierProductName;		// 上游产品商（销售相关）
	private String lastmodifierOrgid;		// 最后修改机构id
	private String lastmodifierOrgname;		// 最后修改机构名称
	private String lastmodifierId;		// 最后修改人id
	private String lastmodifierName;		// 最后修改人名
	private Date lastmodifierTime;		// 最后修改时间
	private Date issuedTime;		// 原机票单的出票时间
	private String handleLater;		// 滞后处理（1滞后，0/null 非滞后）
	private Date lastCirculationTime;		// 最后流转时间
	private String busiRemark;		// 业务说明
	private Date beginCreateTime;		// 开始 本单创建时间
	private Date endCreateTime;		// 结束 本单创建时间
	
	private String ticketNo;//票号
	private String ticketNoTemp;//页面展示票号
	
	private String currentStatus;//操作状态
	
	private String duration;//时长
	
	private String passengers;//该订单下的旅客
	private String passengersTemp;//该订单下的第一个旅客页面展示
	
	private String segments;//该订单下的航段
	private String segmentsTemp;//该订单下的
	
	private double taxPrice;
	
	private double dutyPrice;//税费
	
	private double settlementPrice;//订单费用
	
	private double totalPrice;//订单
	
	private double pzPrice;//凭证费用
	
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
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public RefundsheetDetail() {
		super();
	}

	public RefundsheetDetail(String id){
		super(id);
	}

	@Length(min=0, max=36, message="退票单号长度必须介于 0 和 36 之间")
	public String getRefundsheetNo() {
		return refundsheetNo;
	}

	public void setRefundsheetNo(String refundsheetNo) {
		this.refundsheetNo = refundsheetNo;
	}
	
	@Length(min=0, max=36, message="机票订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=36, message="bsp退票单号长度必须介于 0 和 36 之间")
	public String getBspRefundNo() {
		return bspRefundNo;
	}

	public void setBspRefundNo(String bspRefundNo) {
		this.bspRefundNo = bspRefundNo;
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
	
	@Length(min=0, max=3, message="退票单状态(1,长度必须介于 0 和 3 之间")
	public String getRefundsheetStatus() {
		return refundsheetStatus;
	}

	public void setRefundsheetStatus(String refundsheetStatus) {
		this.refundsheetStatus = refundsheetStatus;
	}
	
	@Length(min=0, max=10, message="退票单类型长度必须介于 0 和 10 之间")
	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	
	@Length(min=0, max=3, message="国内或国际长度必须介于 0 和 3 之间")
	public String getIsInternational() {
		return isInternational;
	}

	public void setIsInternational(String isInternational) {
		this.isInternational = isInternational;
	}
	
	@Length(min=0, max=3, message="散客或团队长度必须介于 0 和 3 之间")
	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
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
	
	@Length(min=0, max=36, message="源退票单号长度必须介于 0 和 36 之间")
	public String getOriginRefundsheetNo() {
		return originRefundsheetNo;
	}

	public void setOriginRefundsheetNo(String originRefundsheetNo) {
		this.originRefundsheetNo = originRefundsheetNo;
	}
	
	@Length(min=0, max=3, message="支付状态长度必须介于 0 和 3 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Length(min=0, max=3, message="创建方式长度必须介于 0 和 3 之间")
	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}
	
	@Length(min=0, max=11, message="前台客户id长度必须介于 0 和 11 之间")
	public String getReceptionCustomerId() {
		return receptionCustomerId;
	}

	public void setReceptionCustomerId(String receptionCustomerId) {
		this.receptionCustomerId = receptionCustomerId;
	}
	
	@Length(min=0, max=11, message="本单创建者id长度必须介于 0 和 11 之间")
	public String getCreateAccountId() {
		return createAccountId;
	}

	public void setCreateAccountId(String createAccountId) {
		this.createAccountId = createAccountId;
	}
	
	@Length(min=0, max=11, message="中台创建操作员id长度必须介于 0 和 11 之间")
	public String getStationCreaterId() {
		return stationCreaterId;
	}

	public void setStationCreaterId(String stationCreaterId) {
		this.stationCreaterId = stationCreaterId;
	}
	
	@Length(min=0, max=100, message="首段退程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15长度必须介于 0 和 100 之间")
	public String getFirstSegment() {
		return firstSegment;
	}

	public void setFirstSegment(String firstSegment) {
		this.firstSegment = firstSegment;
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
	
	public String getSupplierTotSettlementPrice() {
		return supplierTotSettlementPrice;
	}

	public void setSupplierTotSettlementPrice(String supplierTotSettlementPrice) {
		this.supplierTotSettlementPrice = supplierTotSettlementPrice;
	}
	
	public String getDistributorTotSmtPrice() {
		return distributorTotSmtPrice;
	}

	public void setDistributorTotSmtPrice(String distributorTotSmtPrice) {
		this.distributorTotSmtPrice = distributorTotSmtPrice;
	}
	
	@Length(min=0, max=100, message="上游产品号（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductNo() {
		return supplierProductNo;
	}

	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
	}
	
	@Length(min=0, max=5, message="出票主航司长度必须介于 0 和 5 之间")
	public String getIssueAirline() {
		return issueAirline;
	}

	public void setIssueAirline(String issueAirline) {
		this.issueAirline = issueAirline;
	}
	
	@Length(min=0, max=100, message="销售配置，取值例如bsp等长度必须介于 0 和 100 之间")
	public String getSaleConfig() {
		return saleConfig;
	}

	public void setSaleConfig(String saleConfig) {
		this.saleConfig = saleConfig;
	}
	
	@Length(min=0, max=100, message="上游产品商（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductName() {
		return supplierProductName;
	}

	public void setSupplierProductName(String supplierProductName) {
		this.supplierProductName = supplierProductName;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}
	
	@Length(min=0, max=11, message="滞后处理（1滞后，0/null 非滞后）长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=300, message="业务说明长度必须介于 0 和 300 之间")
	public String getBusiRemark() {
		return busiRemark;
	}

	public void setBusiRemark(String busiRemark) {
		this.busiRemark = busiRemark;
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

	public double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public double getDutyPrice() {
		return dutyPrice;
	}

	public void setDutyPrice(double dutyPrice) {
		this.dutyPrice = dutyPrice;
	}

	public double getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getPzPrice() {
		return pzPrice;
	}

	public void setPzPrice(double pzPrice) {
		this.pzPrice = pzPrice;
	}
		
}