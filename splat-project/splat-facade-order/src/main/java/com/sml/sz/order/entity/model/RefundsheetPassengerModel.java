package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetPassenger;

public class RefundsheetPassengerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RefundsheetPassenger> refundsheetPassengers;

	public List<RefundsheetPassenger> getRefundsheetPassengers() {
		return refundsheetPassengers;
	}

	public void setRefundsheetPassengers(List<RefundsheetPassenger> refundsheetPassengers) {
		this.refundsheetPassengers = refundsheetPassengers;
	}

	
	
	
}
