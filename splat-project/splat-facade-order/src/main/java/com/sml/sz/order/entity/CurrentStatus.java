package com.sml.sz.order.entity;

import java.io.Serializable;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 操作状态条数实体类
 * 
 * @author lqc
 *
 */
public class CurrentStatus extends DataEntity<DetailStatusCount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String count;// 操作状态的条数
	private String currentStatus;// 操作状态

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}
