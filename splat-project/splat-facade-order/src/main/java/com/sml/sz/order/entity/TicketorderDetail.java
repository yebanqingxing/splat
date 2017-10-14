/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sml.sz.common.persistence.DataEntity;

/**
 * 订单生成模块Entity
 * @author 李千超
 * @version 2016-03-08
 */
public class TicketorderDetail extends DataEntity<TicketorderDetail> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号
	private String originOrderNo;		// 源订单号
	private String crsPnr;		// crs_pnr
	private String arlPnr;		// arl_pnr
	private String midPnr;		// mid_pnr
	private String createAccount;		// 订单创建者
	private String isInternational;		// 国内或国际，使用枚举0-国内，1-国际
	private String isFit;		// 散客或团队，使用枚举0-散客，1-团队，2-混合
	private String orderStatus;		// 订单状态  "1":已提交;  "2":已申签;  "3":已拒单;  "4":已签单;  "5":已预订;  "6":已出票;  "7":已删除;  "8":已申批;  "9":已初核;  "10":已复核;  "11":已批单;"12":已核单
	private String payStatus;		// 支付状态 0已支付,1未支付，2支付失败
	private String operateStatus;		// 支付状态 1 准备核单,2 已核实
	private String issueAirline;		// 出票航空公司
	private Date createTime;		// 创建时间
	private String relevantClient;		// 所属分销商
	private String relevantOrderNo;		// 相关订单号
	private String firstSegment;		// 首段行程（冗余）入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15;
	private Date firstDepartureTime;		// 首段起飞日期冗余
	private String createType;		// 创建方式(0:预订式-定价；1：导入式-定价；3：导入式-核价；4：交互式-定价；5：其他；8：接口式-定价；10:一站式;12:需求单；13：外购式-支票；14：团队单；15：外购式-现金；16：政府采购；17：境外电子)
	private String remark;		// 订单备注
	private String currentGroup;		// 当前操作组（流转）
	private String currentOperator;		// 当前操作人（流转）
	private String nextOperation;		// 后续操作
	private String supplierName;		// 上游产品商（销售相关）
	private String supplierProductNo;		// 上游产品号（销售相关）
	private String saleConfig;		// 销售方式
	private String rvcRules;		// 退改签规则
	private String pnrStatus;		// pnr状态（冗余）1：正常，2：取消，3：异常，4：空
	private String ticketStatus;		// 机票状态（冗余）
	private String originDestinationOptions;		// od信息，带时间，例如pek-hkg;2014-08-04/hkg-pek;2014-09-10
	private String createAccountId;		// 订单创建者id
	private String receptionCustomerId;		// 所属分销商id
	private String stationCreaterId;		// 中台创建操作员id
	private String isOverloanOrder;		// 是否金额特单1=是，0=否
	private String isPolicyNegoOrder;		// 是否政策特单1=是，0=否
	private String goMainFlight;		// 去程主航段航班
	private String backMainFlight;		// 回程主航段航班
	private String goMainSegment;		// 去程主航段行程
	private String backMainSegment;		// 回程主航段行程
	private String goMainCabin;		// 去程主航段舱位
	private String backMainCabin;		// 回程主航段舱位
	private Date goMainTraveldate;		// 去程主航段旅行日期,格式例如:2014-07-15  暂时存入邮寄时间后续在做修改
	private Date backMainTraveldate;		// 回程主航段旅行日期,格式例如:2014-07-15
	private String supplierServiceCharge;		// 上游服务费
	private String distributorServiceCharge;		// 下游服务费
	private String relationOrderNo;		// 关联订单号
	private String plateformOrderNo;		// 第三方平台订单号(商家订单号)
	private String tripType;		// 行程类型,取值采用AirBussinessEnum.TripType。取值范围0\1\3(分表表示OW\RT\中转)
	private String isAutoBook;		// 是否自动预订，0:手工  1：自动
	private String bookResult;		// 预订结果，0：失败  1：成功
	private String bookRemark;		// 自动预订失败描述
	private String isAutoCancel;		// 是否自动取消，0:手工  1：自动
	private String cancelResult;		// 取消结果，0：失败  1：成功
	private String cancelRemark;		// 自动取消失败描述
	private String isAutoIssue;		// 是否自动出票，0:手工  1：自动
	private String issueResult;		// 出票结果，0：失败  1：成功
	private String issueRemark;		// 自动出票失败描述
	private String orderSource;		// 上游订单来源,0：本地订单 1：d平台  2：g系统, 默认值是0
	private String newPnr;		// 新pnr
	private String contactName;		// 联系人姓名
	private String contactPhone;		// 联系人电话
	private String serialNumber;		// 系列号
	private String manRemark1;		// 手工标记1
	private String manRemark2;		// 手工标记2
	private String lastmodifierOrgid;		// 最后修改机构id
	private String lastmodifierOrgname;		// 最后修改机构名称
	private String lastmodifierId;		// 最后修改人id
	private String lastmodifierName;		// 最后修改人名
	private Date lastmodifierTime;		// 最后修改时间
	private String busiRemark;		// 业务说明   暂时存入邮件说明
	private String paymentRemark;		// 上游支付方式说明
	private String handleLater;		// 滞后处理（1滞后，0/null 非滞后）
	private Date lastCirculationTime;		// 最后流转时间
	private String isPriceConfirmed;		// 价格是否确认，0:不确认；1:确认
	private String operationSource;		// 单据按钮操作来源
	private String splitType;		// 合单/拆单类型，合单：0；拆单：1
	private String approveRuleId;		// 申批规则id
	private String approveRuleRemark;		// 申批规则备注
	private String travelType;		// 0：因公，1：因私，默认是1
	
	private Date minTime;//查询时的最小时间
	private Date maxTime;//查询时的最大时间
	
	private String currentStatus;//操作状态（流转状态） 1:正在核实价格和政策 2:已经审核价格和政策
	private String createAccountcn;//旅客中文名 
	
	private String ticketNo;//票号
	
	private String ticketNoTemp;//页面显示  如果票号风特别多截取放入页面展示
	
	private String invoice;//是否有行程单 或者是发票的
	
	
	private String duration;//时长
	
	private String passengers;//乘坐旅客
	private String passengersTemp;//页面展示的旅客
	
	private double taxPrice;
	
	private double dutyPrice;//税费
	
	private double settlementPrice;//订单费用
	
	private double totalPrice;//订单
	
	private Double pzPrice;//凭证费用
	

	private String segments;//航段信息
	private String segmentsTemp;//页面展示的航段信息
	
	
	
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

	public String getPassengersTemp() {
		return passengersTemp;
	}

	public void setPassengersTemp(String passengersTemp) {
		this.passengersTemp = passengersTemp;
	}

	public String getTicketNoTemp() {
		return ticketNoTemp;
	}

	public void setTicketNoTemp(String ticketNoTemp) {
		this.ticketNoTemp = ticketNoTemp;
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
	

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
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

	public String getCreateAccountcn() {
		return createAccountcn;
	}

	public void setCreateAccountcn(String createAccountcn) {
		this.createAccountcn = createAccountcn;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	

	public TicketorderDetail() {
		super();
	}

	public TicketorderDetail(String id){
		super(id);
	}

	@Length(min=1, max=36, message="订单号长度必须介于 1 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=36, message="源订单号长度必须介于 0 和 36 之间")
	public String getOriginOrderNo() {
		return originOrderNo;
	}

	public void setOriginOrderNo(String originOrderNo) {
		this.originOrderNo = originOrderNo;
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
	
	@Length(min=0, max=100, message="订单创建者长度必须介于 0 和 100 之间")
	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}
	
	@Length(min=0, max=3, message="国内或国际，使用枚举0-国内，1-国际长度必须介于 0 和 3 之间")
	public String getIsInternational() {
		return isInternational;
	}

	public void setIsInternational(String isInternational) {
		this.isInternational = isInternational;
	}
	
	@Length(min=0, max=3, message="散客或团队，使用枚举0-散客，1-团队，2-混合长度必须介于 0 和 3 之间")
	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}
	
	@Length(min=0, max=3, message="订单状态长度必须介于 0 和 3 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=0, max=3, message="支付状态 0已支付,1未支付长度必须介于 0 和 3 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Length(min=0, max=5, message="出票航空公司长度必须介于 0 和 5 之间")
	public String getIssueAirline() {
		return issueAirline;
	}

	public void setIssueAirline(String issueAirline) {
		this.issueAirline = issueAirline;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=100, message="所属分销商长度必须介于 0 和 100 之间")
	public String getRelevantClient() {
		return relevantClient;
	}

	public void setRelevantClient(String relevantClient) {
		this.relevantClient = relevantClient;
	}
	
	@Length(min=0, max=36, message="相关订单号长度必须介于 0 和 36 之间")
	public String getRelevantOrderNo() {
		return relevantOrderNo;
	}

	public void setRelevantOrderNo(String relevantOrderNo) {
		this.relevantOrderNo = relevantOrderNo;
	}
	
	@Length(min=0, max=100, message="首段行程（冗余）入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15;长度必须介于 0 和 100 之间")
	public String getFirstSegment() {
		return firstSegment;
	}

	public void setFirstSegment(String firstSegment) {
		this.firstSegment = firstSegment;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstDepartureTime() {
		return firstDepartureTime;
	}

	public void setFirstDepartureTime(Date firstDepartureTime) {
		this.firstDepartureTime = firstDepartureTime;
	}
	
	@Length(min=0, max=3, message="创建方式(0:预订式-定价；1：导入式-定价；3：导入式-核价；4：交互式-定价；5：其他；8：接口式-定价；10:一站式;12:需求单；13：外购式-支票；14：团队单；15：外购式-现金；16：政府采购；17：境外电子)长度必须介于 0 和 3 之间")
	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}
	
	@Length(min=0, max=4000, message="订单备注长度必须介于 0 和 4000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=11, message="当前操作组（流转）长度必须介于 0 和 11 之间")
	public String getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(String currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	@Length(min=0, max=11, message="当前操作人（流转）长度必须介于 0 和 11 之间")
	public String getCurrentOperator() {
		return currentOperator;
	}

	public void setCurrentOperator(String currentOperator) {
		this.currentOperator = currentOperator;
	}
	
	@Length(min=0, max=11, message="后续操作长度必须介于 0 和 11 之间")
	public String getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
	}
	
	@Length(min=0, max=100, message="上游产品商（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=100, message="上游产品号（销售相关）长度必须介于 0 和 100 之间")
	public String getSupplierProductNo() {
		return supplierProductNo;
	}

	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
	}
	
	@Length(min=0, max=50, message="销售方式长度必须介于 0 和 50 之间")
	public String getSaleConfig() {
		return saleConfig;
	}

	public void setSaleConfig(String saleConfig) {
		this.saleConfig = saleConfig;
	}
	
	@Length(min=0, max=4000, message="退改签规则长度必须介于 0 和 4000 之间")
	public String getRvcRules() {
		return rvcRules;
	}

	public void setRvcRules(String rvcRules) {
		this.rvcRules = rvcRules;
	}
	
	@Length(min=0, max=3, message="pnr状态（冗余）1：正常，2：取消，3：异常，4：空长度必须介于 0 和 3 之间")
	public String getPnrStatus() {
		return pnrStatus;
	}

	public void setPnrStatus(String pnrStatus) {
		this.pnrStatus = pnrStatus;
	}
	
	@Length(min=0, max=3, message="机票状态（冗余）长度必须介于 0 和 3 之间")
	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	@Length(min=0, max=500, message="od信息，带时间，例如pek-hkg;2014-08-04/hkg-pek;2014-09-10长度必须介于 0 和 500 之间")
	public String getOriginDestinationOptions() {
		return originDestinationOptions;
	}

	public void setOriginDestinationOptions(String originDestinationOptions) {
		this.originDestinationOptions = originDestinationOptions;
	}
	
	@Length(min=0, max=11, message="订单创建者id长度必须介于 0 和 11 之间")
	public String getCreateAccountId() {
		return createAccountId;
	}

	public void setCreateAccountId(String createAccountId) {
		this.createAccountId = createAccountId;
	}
	
	@Length(min=0, max=11, message="所属分销商id长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=3, message="是否金额特单1=是，0=否长度必须介于 0 和 3 之间")
	public String getIsOverloanOrder() {
		return isOverloanOrder;
	}

	public void setIsOverloanOrder(String isOverloanOrder) {
		this.isOverloanOrder = isOverloanOrder;
	}
	
	@Length(min=0, max=3, message="是否政策特单1=是，0=否长度必须介于 0 和 3 之间")
	public String getIsPolicyNegoOrder() {
		return isPolicyNegoOrder;
	}

	public void setIsPolicyNegoOrder(String isPolicyNegoOrder) {
		this.isPolicyNegoOrder = isPolicyNegoOrder;
	}
	
	@Length(min=0, max=10, message="去程主航段航班长度必须介于 0 和 10 之间")
	public String getGoMainFlight() {
		return goMainFlight;
	}

	public void setGoMainFlight(String goMainFlight) {
		this.goMainFlight = goMainFlight;
	}
	
	@Length(min=0, max=10, message="回程主航段航班长度必须介于 0 和 10 之间")
	public String getBackMainFlight() {
		return backMainFlight;
	}

	public void setBackMainFlight(String backMainFlight) {
		this.backMainFlight = backMainFlight;
	}
	
	@Length(min=0, max=10, message="去程主航段行程长度必须介于 0 和 10 之间")
	public String getGoMainSegment() {
		return goMainSegment;
	}

	public void setGoMainSegment(String goMainSegment) {
		this.goMainSegment = goMainSegment;
	}
	
	@Length(min=0, max=10, message="回程主航段行程长度必须介于 0 和 10 之间")
	public String getBackMainSegment() {
		return backMainSegment;
	}

	public void setBackMainSegment(String backMainSegment) {
		this.backMainSegment = backMainSegment;
	}
	
	@Length(min=0, max=10, message="去程主航段舱位长度必须介于 0 和 10 之间")
	public String getGoMainCabin() {
		return goMainCabin;
	}

	public void setGoMainCabin(String goMainCabin) {
		this.goMainCabin = goMainCabin;
	}
	
	@Length(min=0, max=10, message="回程主航段舱位长度必须介于 0 和 10 之间")
	public String getBackMainCabin() {
		return backMainCabin;
	}

	public void setBackMainCabin(String backMainCabin) {
		this.backMainCabin = backMainCabin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGoMainTraveldate() {
		return goMainTraveldate;
	}

	public void setGoMainTraveldate(Date goMainTraveldate) {
		this.goMainTraveldate = goMainTraveldate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBackMainTraveldate() {
		return backMainTraveldate;
	}

	public void setBackMainTraveldate(Date backMainTraveldate) {
		this.backMainTraveldate = backMainTraveldate;
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
	
	@Length(min=0, max=36, message="关联订单号长度必须介于 0 和 36 之间")
	public String getRelationOrderNo() {
		return relationOrderNo;
	}

	public void setRelationOrderNo(String relationOrderNo) {
		this.relationOrderNo = relationOrderNo;
	}
	
	@Length(min=0, max=50, message="第三方平台订单号(商家订单号)长度必须介于 0 和 50 之间")
	public String getPlateformOrderNo() {
		return plateformOrderNo;
	}

	public void setPlateformOrderNo(String plateformOrderNo) {
		this.plateformOrderNo = plateformOrderNo;
	}
	
	@Length(min=0,max=3,message="行程类型,取值采用AirBussinessEnum.TripType。取值范围0\\1\\3(分表表示OW\\RT\\中转)")
	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	
	@Length(min=0, max=11, message="是否自动预订，0:手工  1：自动长度必须介于 0 和 11 之间")
	public String getIsAutoBook() {
		return isAutoBook;
	}

	public void setIsAutoBook(String isAutoBook) {
		this.isAutoBook = isAutoBook;
	}
	
	@Length(min=0, max=11, message="预订结果，0：失败  1：成功长度必须介于 0 和 11 之间")
	public String getBookResult() {
		return bookResult;
	}

	public void setBookResult(String bookResult) {
		this.bookResult = bookResult;
	}
	
	@Length(min=0, max=1000, message="自动预订失败描述长度必须介于 0 和 1000 之间")
	public String getBookRemark() {
		return bookRemark;
	}

	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}
	
	@Length(min=0, max=11, message="是否自动取消，0:手工  1：自动长度必须介于 0 和 11 之间")
	public String getIsAutoCancel() {
		return isAutoCancel;
	}

	public void setIsAutoCancel(String isAutoCancel) {
		this.isAutoCancel = isAutoCancel;
	}
	
	@Length(min=0, max=11, message="取消结果，0：失败  1：成功长度必须介于 0 和 11 之间")
	public String getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(String cancelResult) {
		this.cancelResult = cancelResult;
	}
	
	@Length(min=0, max=500, message="自动取消失败描述长度必须介于 0 和 500 之间")
	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
	
	@Length(min=0, max=11, message="是否自动出票，0:手工  1：自动长度必须介于 0 和 11 之间")
	public String getIsAutoIssue() {
		return isAutoIssue;
	}

	public void setIsAutoIssue(String isAutoIssue) {
		this.isAutoIssue = isAutoIssue;
	}
	
	@Length(min=0, max=11, message="出票结果，0：失败  1：成功长度必须介于 0 和 11 之间")
	public String getIssueResult() {
		return issueResult;
	}

	public void setIssueResult(String issueResult) {
		this.issueResult = issueResult;
	}
	
	@Length(min=0, max=300, message="自动出票失败描述长度必须介于 0 和 300 之间")
	public String getIssueRemark() {
		return issueRemark;
	}

	public void setIssueRemark(String issueRemark) {
		this.issueRemark = issueRemark;
	}
	
	@Length(min=0, max=11, message="上游订单来源,0：本地订单 1：d平台  2：g系统, 默认值是0长度必须介于 0 和 11 之间")
	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	
	@Length(min=0, max=100, message="新pnr长度必须介于 0 和 100 之间")
	public String getNewPnr() {
		return newPnr;
	}

	public void setNewPnr(String newPnr) {
		this.newPnr = newPnr;
	}
	
	@Length(min=0, max=50, message="联系人姓名长度必须介于 0 和 50 之间")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Length(min=0, max=50, message="联系人电话长度必须介于 0 和 50 之间")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Length(min=0, max=100, message="系列号长度必须介于 0 和 100 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	
	@Length(min=0, max=11, message="价格是否确认，0:不确认；1:确认长度必须介于 0 和 11 之间")
	public String getIsPriceConfirmed() {
		return isPriceConfirmed;
	}

	public void setIsPriceConfirmed(String isPriceConfirmed) {
		this.isPriceConfirmed = isPriceConfirmed;
	}
	
	@Length(min=0, max=11, message="单据按钮操作来源长度必须介于 0 和 11 之间")
	public String getOperationSource() {
		return operationSource;
	}

	public void setOperationSource(String operationSource) {
		this.operationSource = operationSource;
	}
	
	@Length(min=0, max=11, message="合单/拆单类型，合单：0；拆单：1长度必须介于 0 和 11 之间")
	public String getSplitType() {
		return splitType;
	}

	public void setSplitType(String splitType) {
		this.splitType = splitType;
	}
	
	@Length(min=0, max=11, message="申批规则id长度必须介于 0 和 11 之间")
	public String getApproveRuleId() {
		return approveRuleId;
	}

	public void setApproveRuleId(String approveRuleId) {
		this.approveRuleId = approveRuleId;
	}
	
	@Length(min=0, max=500, message="申批规则备注长度必须介于 0 和 500 之间")
	public String getApproveRuleRemark() {
		return approveRuleRemark;
	}

	public void setApproveRuleRemark(String approveRuleRemark) {
		this.approveRuleRemark = approveRuleRemark;
	}
	
	@Length(min=0, max=10, message="0：因公，1：因私，默认是1长度必须介于 0 和 10 之间")
	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getPzPrice() {
		return pzPrice;
	}

	public void setPzPrice(Double pzPrice) {
		this.pzPrice = pzPrice;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	
}