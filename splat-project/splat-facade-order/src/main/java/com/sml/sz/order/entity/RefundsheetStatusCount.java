package com.sml.sz.order.entity;

import com.sml.sz.common.persistence.DataEntity;

public class RefundsheetStatusCount extends DataEntity<RefundsheetStatusCount>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String refundsheetStatus;
	
	private String count;

	public String getRefundsheetStatus() {
		return refundsheetStatus;
	}

	public void setRefundsheetStatus(String refundsheetStatus) {
		this.refundsheetStatus = refundsheetStatus;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	

}
