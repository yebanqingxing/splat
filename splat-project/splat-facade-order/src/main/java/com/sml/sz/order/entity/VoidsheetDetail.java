/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 废票单表Entity
 * @author 李千超
 * @version 2016-03-18
 */
public class VoidsheetDetail extends DataEntity<VoidsheetDetail> {
	
	private static final long serialVersionUID = 1L;
	private String voidsheetNo;		// 废票单号
	private String orderNo;		// 机票订单号外键
	private String originVoidsheetNo;		// 源废票单号
	private String crsPnr;		// crs_pnr
	private String arlPnr;		// arl_pnr
	private String midPnr;		// mid_pnr
	private String voidsheetStatus;		// 废票单状态(1,
	private String currentGroup;		// 流转到的组
	private String currentOperator;		// 流转到的人
	private String relevantClient;		// 本单所属客户
	private String createAccount;		// 本单创建者
	private Date createTime;		// 本单创建时间
	private String nextOperation;		// 后续操作
	private String payStatus;		// 支付状态
	private String createType;		// 创建方式
	private String receptionCustomerId;		// 前台客户id
	private String createAccountId;		// 本单创建者id
	private String stationCreaterId;		// 中台创建操作员id
	private String firstSegment;		// 首段行程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15
	private String orderId;		// 订单id
	private String remark;		// 备注
	private String supplierTotSettlementPrice;		// 上游废票结算价
	private String distributorTotSmtPrice;		// 下游废票结算价
	private String isFit;		// 团队散客
	private String isInternational;		// 国内国际
	private String supplierProductNo;		// 上游产品号
	private String issueAirline;		// 出票主航司
	private String saleConfig;		// 销售配置，取值例如bsp等
	private String orderCreateType;		// 原单创建方式
	private String supplierProductName;		// 上游产品商（销售相关）
	private String lastModifyDeptId;		// 最后修改人所属机构id
	private String lastModifyDeptName;		// 最后修改人所属机构名称
	private String lastModifyPersonId;		// 最后修改人id
	private String lastModifyPersonName;		// 最后修改人姓名
	private Date lastCirculationTime;		// 最后流转时间
	private String busiRemark;		// 业务说明
	private Date beginCreateTime;		// 开始 本单创建时间
	private Date endCreateTime;		// 结束 本单创建时间
	
	private String currentStatus;//操作状态
	private String ticketNo;//票号
	private String ticketNoTemp; 
	
	private String duration;//时长
	
	private String passengers; // 该订单的所有旅客
	private String passengersTemp;//该订单的旅客页面展示
	private String segmentTemp; // 订单的航段 页面展示 
	private String segment;//订单的航段
	private String contactPhone;//联系方式
	

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
	
	
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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

	public String getSegmentTemp() {
		return segmentTemp;
	}

	public void setSegmentTemp(String segmentTemp) {
		this.segmentTemp = segmentTemp;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
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

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public VoidsheetDetail() {
		super();
	}

	public VoidsheetDetail(String id){
		super(id);
	}

	@Length(min=0, max=36, message="废票单号长度必须介于 0 和 36 之间")
	public String getVoidsheetNo() {
		return voidsheetNo;
	}

	public void setVoidsheetNo(String voidsheetNo) {
		this.voidsheetNo = voidsheetNo;
	}
	
	@Length(min=0, max=36, message="机票订单号外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=36, message="源废票单号长度必须介于 0 和 36 之间")
	public String getOriginVoidsheetNo() {
		return originVoidsheetNo;
	}

	public void setOriginVoidsheetNo(String originVoidsheetNo) {
		this.originVoidsheetNo = originVoidsheetNo;
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
	
	@Length(min=0, max=3, message="废票单状态(1,长度必须介于 0 和 3 之间")
	public String getVoidsheetStatus() {
		return voidsheetStatus;
	}

	public void setVoidsheetStatus(String voidsheetStatus) {
		this.voidsheetStatus = voidsheetStatus;
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
	
	@Length(min=0, max=100, message="首段行程,入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=3, message="团队散客长度必须介于 0 和 3 之间")
	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}
	
	@Length(min=0, max=3, message="国内国际长度必须介于 0 和 3 之间")
	public String getIsInternational() {
		return isInternational;
	}

	public void setIsInternational(String isInternational) {
		this.isInternational = isInternational;
	}
	
	@Length(min=0, max=100, message="上游产品号长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=3, message="原单创建方式长度必须介于 0 和 3 之间")
	public String getOrderCreateType() {
		return orderCreateType;
	}

	public void setOrderCreateType(String orderCreateType) {
		this.orderCreateType = orderCreateType;
	}
	
	@Length(min=0, max=100, message="上游产品商（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductName() {
		return supplierProductName;
	}

	public void setSupplierProductName(String supplierProductName) {
		this.supplierProductName = supplierProductName;
	}
	
	@Length(min=0, max=11, message="最后修改人所属机构id长度必须介于 0 和 11 之间")
	public String getLastModifyDeptId() {
		return lastModifyDeptId;
	}

	public void setLastModifyDeptId(String lastModifyDeptId) {
		this.lastModifyDeptId = lastModifyDeptId;
	}
	
	@Length(min=0, max=100, message="最后修改人所属机构名称长度必须介于 0 和 100 之间")
	public String getLastModifyDeptName() {
		return lastModifyDeptName;
	}

	public void setLastModifyDeptName(String lastModifyDeptName) {
		this.lastModifyDeptName = lastModifyDeptName;
	}
	
	@Length(min=0, max=11, message="最后修改人id长度必须介于 0 和 11 之间")
	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}
	
	@Length(min=0, max=100, message="最后修改人姓名长度必须介于 0 和 100 之间")
	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
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
		
}