package com.sml.sz.order.entity;

import com.sml.sz.common.persistence.DataEntity;

public class OrderStatusCount extends DataEntity<OrderStatusCount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502762775181935037L;
	
	private String count;//条数
	
	private String  orderStatus;//订单状态

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	

}
