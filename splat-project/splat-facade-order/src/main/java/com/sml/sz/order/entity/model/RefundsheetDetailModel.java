package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.RefundsheetDetail;

public class RefundsheetDetailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RefundsheetDetail> refundsheetDetails;

	public List<RefundsheetDetail> getRefundsheetDetails() {
		return refundsheetDetails;
	}

	public void setRefundsheetDetails(List<RefundsheetDetail> refundsheetDetails) {
		this.refundsheetDetails = refundsheetDetails;
	}
	
	
}
