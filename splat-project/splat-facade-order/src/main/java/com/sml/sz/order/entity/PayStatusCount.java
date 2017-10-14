package com.sml.sz.order.entity;

import com.sml.sz.common.persistence.DataEntity;

public class PayStatusCount extends DataEntity<PayStatusCount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String count;//条数
	
	private String payStatus;//支付的状态

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	
	

}
