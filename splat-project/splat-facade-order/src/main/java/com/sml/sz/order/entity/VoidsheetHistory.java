/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 废票单历史Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class VoidsheetHistory extends DataEntity<VoidsheetHistory> {
	
	private static final long serialVersionUID = 1L;
	private String voidsheetNo;		// 废票单号
	private Date operationTime;		// 操作时间
	private String operatorName;		// 操作人
	private String previousOperation;		// 历史操作动作
	private String operationDataPre;		// 变化前数据
	private String operationDataAfter;		// 变化后数据
	private String remark;		// 备注
	private String operatorDepartmentId;		// 操作部门id
	private String operatorChannelId;		// 操作渠道id
	private String operatorId;		// 操作人id
	private Date beginOperationTime;		// 开始 操作时间
	private Date endOperationTime;		// 结束 操作时间
	
	private String ticketNo;//票号
	
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public VoidsheetHistory() {
		super();
	}

	public VoidsheetHistory(String id){
		super(id);
	}

	@Length(min=0, max=36, message="废票单号长度必须介于 0 和 36 之间")
	public String getVoidsheetNo() {
		return voidsheetNo;
	}

	public void setVoidsheetNo(String voidsheetNo) {
		this.voidsheetNo = voidsheetNo;
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
	
	@Length(min=0, max=500, message="变化前数据长度必须介于 0 和 500 之间")
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
	
	public Date getBeginOperationTime() {
		return beginOperationTime;
	}

	public void setBeginOperationTime(Date beginOperationTime) {
		this.beginOperationTime = beginOperationTime;
	}
	
	public Date getEndOperationTime() {
		return endOperationTime;
	}

	public void setEndOperationTime(Date endOperationTime) {
		this.endOperationTime = endOperationTime;
	}
		
}