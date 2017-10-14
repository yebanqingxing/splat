package com.sml.sz.order.entity;

import com.sml.sz.common.persistence.DataEntity;
/**
 * 改签单类型状态的条数
 * @author lqc
 *
 */
public class DetailStatusCount extends DataEntity<DetailStatusCount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String count;//条数
	
	private String endorseStatus;//订单的类型状态

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getEndorseStatus() {
		return endorseStatus;
	}

	public void setEndorseStatus(String endorseStatus) {
		this.endorseStatus = endorseStatus;
	}
}
