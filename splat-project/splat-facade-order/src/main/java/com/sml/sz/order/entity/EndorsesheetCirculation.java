/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 该签单流转Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class EndorsesheetCirculation extends DataEntity<EndorsesheetCirculation> {
	
	private static final long serialVersionUID = 1L;
	private String endorsesheetNo;		// 改签单号
	private String cirRuleId;		// 流转规则id
	private String oriGroup;		// 流转from组
	private String destGroup;		// 流转to组
	private String nextOperation;		// 流转后续操作
	private Date operationTime;		// 流转时间
	private String operatorName;		// 流转前操作人
	private String previousOperation;		// 流转前操作动作
	private String operatorId;		// 流转前操作人id
	
	public EndorsesheetCirculation() {
		super();
	}

	public EndorsesheetCirculation(String id){
		super(id);
	}

	@Length(min=0, max=36, message="改签单号长度必须介于 0 和 36 之间")
	public String getEndorsesheetNo() {
		return endorsesheetNo;
	}

	public void setEndorsesheetNo(String endorsesheetNo) {
		this.endorsesheetNo = endorsesheetNo;
	}
	
	@Length(min=0, max=11, message="流转规则id长度必须介于 0 和 11 之间")
	public String getCirRuleId() {
		return cirRuleId;
	}

	public void setCirRuleId(String cirRuleId) {
		this.cirRuleId = cirRuleId;
	}
	
	@Length(min=0, max=11, message="流转from组长度必须介于 0 和 11 之间")
	public String getOriGroup() {
		return oriGroup;
	}

	public void setOriGroup(String oriGroup) {
		this.oriGroup = oriGroup;
	}
	
	@Length(min=0, max=11, message="流转to组长度必须介于 0 和 11 之间")
	public String getDestGroup() {
		return destGroup;
	}

	public void setDestGroup(String destGroup) {
		this.destGroup = destGroup;
	}
	
	@Length(min=0, max=25, message="流转后续操作长度必须介于 0 和 25 之间")
	public String getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	@Length(min=0, max=50, message="流转前操作人长度必须介于 0 和 50 之间")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@Length(min=0, max=50, message="流转前操作动作长度必须介于 0 和 50 之间")
	public String getPreviousOperation() {
		return previousOperation;
	}

	public void setPreviousOperation(String previousOperation) {
		this.previousOperation = previousOperation;
	}
	
	@Length(min=0, max=25, message="流转前操作人id长度必须介于 0 和 25 之间")
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
}