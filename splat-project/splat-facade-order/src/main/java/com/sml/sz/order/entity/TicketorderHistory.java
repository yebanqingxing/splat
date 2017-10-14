/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机票订单历史Entity
 * @author 李千超
 * @version 2016-03-10
 */
public class TicketorderHistory extends DataEntity<TicketorderHistory> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号
	private Date operationTime;		// 操作时间
	private String operatorName;		// 操作人
	private String previousOperation;		// 历史操作动作
	private String operationDataPre;		// 变化前数据
	private String operationDataAfter;		// 变化后数据
	private String remark;		// 备注
	private String operatorDepartmentId;		// 操作部门id
	private String operatorChannelId;		// 操作渠道id
	private String operatorId;		// 操作人id
	
	public TicketorderHistory() {
		super();
	}

	public TicketorderHistory(String id){
		super(id);
	}

	@Length(min=0, max=36, message="订单号长度必须介于 0 和 36 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	@Length(min=0, max=50, message="操作人长度必须介于 0 和 50 之间")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@Length(min=0, max=50, message="历史操作动作长度必须介于 0 和 50 之间")
	public String getPreviousOperation() {
		return previousOperation;
	}

	public void setPreviousOperation(String previousOperation) {
		this.previousOperation = previousOperation;
	}
	
	@Length(min=0, max=2000, message="变化前数据长度必须介于 0 和 2000 之间")
	public String getOperationDataPre() {
		return operationDataPre;
	}

	public void setOperationDataPre(String operationDataPre) {
		this.operationDataPre = operationDataPre;
	}
	
	@Length(min=0, max=4000, message="变化后数据长度必须介于 0 和 4000 之间")
	public String getOperationDataAfter() {
		return operationDataAfter;
	}

	public void setOperationDataAfter(String operationDataAfter) {
		this.operationDataAfter = operationDataAfter;
	}
	
	@Length(min=0, max=4000, message="备注长度必须介于 0 和 4000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=25, message="操作部门id长度必须介于 0 和 25 之间")
	public String getOperatorDepartmentId() {
		return operatorDepartmentId;
	}

	public void setOperatorDepartmentId(String operatorDepartmentId) {
		this.operatorDepartmentId = operatorDepartmentId;
	}
	
	@Length(min=0, max=25, message="操作渠道id长度必须介于 0 和 25 之间")
	public String getOperatorChannelId() {
		return operatorChannelId;
	}

	public void setOperatorChannelId(String operatorChannelId) {
		this.operatorChannelId = operatorChannelId;
	}
	
	@Length(min=0, max=25, message="操作人id长度必须介于 0 和 25 之间")
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
}