package com.sml.sz.order.entity;

import java.io.Serializable;

public class SinglePrice implements Serializable{

	/**
	 *  @auth 李千超
	 *  @date 2016年4月22日
	 *  @描述  TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String distributorTicketPrice;
	
	private double taxCn;

	public String getDistributorTicketPrice() {
		return distributorTicketPrice;
	}

	public void setDistributorTicketPrice(String distributorTicketPrice) {
		this.distributorTicketPrice = distributorTicketPrice;
	}

	public double getTaxCn() {
		return taxCn;
	}

	public void setTaxCn(double taxCn) {
		this.taxCn = taxCn;
	}
	
	

}
