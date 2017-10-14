/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 改签单附件Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class EndorsesheetAppendix extends DataEntity<EndorsesheetAppendix> {
	
	private static final long serialVersionUID = 1L;
	private String endorsesheetNo;		// 改签单号外键
	private String operatorName;		// 上传附件者
	private Date uploadTime;		// 上传时间
	private String relevantOperation;		// 附件来源的操作动作
	private String fileType;		// 附件类型
	private String fileAddress;		// 附件地址
	private String fileDescription;		// 附件描述
	private String fileRemark;		// 备注
	private String operatorDepartmentId;		// 操作部门id
	private String operatorChannelId;		// 操作渠道id
	private String orgnFileName;		// 原始文件名
	
	public EndorsesheetAppendix() {
		super();
	}

	public EndorsesheetAppendix(String id){
		super(id);
	}

	@Length(min=0, max=36, message="改签单号外键长度必须介于 0 和 36 之间")
	public String getEndorsesheetNo() {
		return endorsesheetNo;
	}

	public void setEndorsesheetNo(String endorsesheetNo) {
		this.endorsesheetNo = endorsesheetNo;
	}
	
	@Length(min=0, max=50, message="上传附件者长度必须介于 0 和 50 之间")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Length(min=0, max=50, message="附件来源的操作动作长度必须介于 0 和 50 之间")
	public String getRelevantOperation() {
		return relevantOperation;
	}

	public void setRelevantOperation(String relevantOperation) {
		this.relevantOperation = relevantOperation;
	}
	
	@Length(min=0, max=10, message="附件类型长度必须介于 0 和 10 之间")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=1500, message="附件地址长度必须介于 0 和 1500 之间")
	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	
	@Length(min=0, max=200, message="附件描述长度必须介于 0 和 200 之间")
	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getFileRemark() {
		return fileRemark;
	}

	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
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
	
	@Length(min=0, max=200, message="原始文件名长度必须介于 0 和 200 之间")
	public String getOrgnFileName() {
		return orgnFileName;
	}

	public void setOrgnFileName(String orgnFileName) {
		this.orgnFileName = orgnFileName;
	}
	
}