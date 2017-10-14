/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机票订单航段表Entity
 * @author 李千超
 * @version 2016-03-10
 */
public class TicketorderSegment extends DataEntity<TicketorderSegment> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 所属订单外键
	private String marketingAirline;		// 市场方航司
	private String operatingAirline;		// 承运方航司
	private String marketingFlightNo;		// 市场方航司航班号
	private String operatingFlightNo;		// 承运方航司航班号
	private String isCodeShare;		// 是否代码共享
	private String departureCode;		// 起飞地三字码
	private Date departureTime;		// 起飞时间
	private String departureTimeStr; //起飞时间页面
	private String arriveCode;		// 到达地三字码
	private Date arriveTime;		// 到达时间
	private String arriveTimeStr; //到达时间页面
	private String segmentIndex;		// 在pnr中的航段序号
	private String classCode;		// 舱位代码
	private String subClassCode;		// 子舱位代码（国内有国际没有）
	private String segmentStatus;		// 航段状态rr 再确认hk 确认hl 候补hn 申请no 航空公司不允许销售un 航班取消
	private String classGrade;		// 舱等
	private String planeType;		// 机型
	private String orderId;		// 订单id
	private String odIndex;		// 行程序号，从1开始
	
	private String segId;//判断旅客是否勾选  假如勾选的话就是有退票的航段
	
	private String duration;//时长
	
	private String departureAddress;		// 起飞地名称
	private String arriveAddress;		// 到达地名称
	
	
	public String getdepartureAddress() {
		return departureAddress;
	}

	public void setdepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public String getArriveAddress() {
		return arriveAddress;
	}

	public void setArriveAddress(String arriveAddress) {
		this.arriveAddress = arriveAddress;
	}

	
	public String getDepartureTimeStr() {
		return departureTimeStr;
	}

	public void setDepartureTimeStr(String departureTimeStr) {
		this.departureTimeStr = departureTimeStr;
	}

	public String getArriveTimeStr() {
		return arriveTimeStr;
	}

	public void setArriveTimeStr(String arriveTimeStr) {
		this.arriveTimeStr = arriveTimeStr;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSegId() {
		return segId;
	}

	public void setSegId(String segId) {
		this.segId = segId;
	}

	public TicketorderSegment() {
		super();
	}

	public TicketorderSegment(String id){
		super(id);
	}

	@Length(min=0, max=36, message="所属订单外键长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=5, message="市场方航司长度必须介于 0 和 5 之间")
	public String getMarketingAirline() {
		return marketingAirline;
	}

	public void setMarketingAirline(String marketingAirline) {
		this.marketingAirline = marketingAirline;
	}
	
	@Length(min=0, max=5, message="承运方航司长度必须介于 0 和 5 之间")
	public String getOperatingAirline() {
		return operatingAirline;
	}

	public void setOperatingAirline(String operatingAirline) {
		this.operatingAirline = operatingAirline;
	}
	
	@Length(min=0, max=15, message="市场方航司航班号长度必须介于 0 和 15 之间")
	public String getMarketingFlightNo() {
		return marketingFlightNo;
	}

	public void setMarketingFlightNo(String marketingFlightNo) {
		this.marketingFlightNo = marketingFlightNo;
	}
	
	@Length(min=0, max=15, message="承运方航司航班号长度必须介于 0 和 15 之间")
	public String getOperatingFlightNo() {
		return operatingFlightNo;
	}

	public void setOperatingFlightNo(String operatingFlightNo) {
		this.operatingFlightNo = operatingFlightNo;
	}
	
	@Length(min=0, max=3, message="是否代码共享长度必须介于 0 和 3 之间")
	public String getIsCodeShare() {
		return isCodeShare;
	}

	public void setIsCodeShare(String isCodeShare) {
		this.isCodeShare = isCodeShare;
	}
	
	@Length(min=0, max=5, message="起飞地三字码长度必须介于 0 和 5 之间")
	public String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	@Length(min=0, max=5, message="到达地三字码长度必须介于 0 和 5 之间")
	public String getArriveCode() {
		return arriveCode;
	}

	public void setArriveCode(String arriveCode) {
		this.arriveCode = arriveCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	@Length(min=0, max=11, message="在pnr中的航段序号长度必须介于 0 和 11 之间")
	public String getSegmentIndex() {
		return segmentIndex;
	}

	public void setSegmentIndex(String segmentIndex) {
		this.segmentIndex = segmentIndex;
	}
	
	@Length(min=0, max=5, message="舱位代码长度必须介于 0 和 5 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=5, message="子舱位代码（国内有国际没有）长度必须介于 0 和 5 之间")
	public String getSubClassCode() {
		return subClassCode;
	}

	public void setSubClassCode(String subClassCode) {
		this.subClassCode = subClassCode;
	}
	
	@Length(min=0, max=3, message="航段状态rr 再确认hk 确认hl 候补hn 申请no 航空公司不允许销售un 航班取消长度必须介于 0 和 3 之间")
	public String getSegmentStatus() {
		return segmentStatus;
	}

	public void setSegmentStatus(String segmentStatus) {
		this.segmentStatus = segmentStatus;
	}
	
	@Length(min=0, max=5, message="舱等长度必须介于 0 和 5 之间")
	public String getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(String classGrade) {
		this.classGrade = classGrade;
	}
	
	@Length(min=0, max=10, message="机型长度必须介于 0 和 10 之间")
	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	
	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="行程序号，从1开始长度必须介于 0 和 11 之间")
	public String getOdIndex() {
		return odIndex;
	}

	public void setOdIndex(String odIndex) {
		this.odIndex = odIndex;
	}
	
}