package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.Invoice;

public class InvoiceModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Invoice> invoices;

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
