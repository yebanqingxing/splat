package com.sml.sz.order.entity;

import com.sml.sz.common.persistence.DataEntity;

public class VoidsheetStatusCount extends DataEntity<VoidsheetStatusCount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String count ;
	
	private String voidsheetStatus;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getVoidsheetStatus() {
		return voidsheetStatus;
	}

	public void setVoidsheetStatus(String voidsheetStatus) {
		this.voidsheetStatus = voidsheetStatus;
	}
	
	

}
