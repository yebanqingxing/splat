package com.sml.sz.order.entity.model;

import java.util.List;

import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.order.entity.DetailStatusCount;
import com.sml.sz.order.entity.TicketorderSinglePrice;

public class TicketorderSinglePriceModel extends DataEntity<TicketorderSinglePriceModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TicketorderSinglePrice> ticketorderSinglePrices;

	public List<TicketorderSinglePrice> getTicketorderSinglePrices() {
		return ticketorderSinglePrices;
	}

	public void setTicketorderSinglePrices(List<TicketorderSinglePrice> ticketorderSinglePrices) {
		this.ticketorderSinglePrices = ticketorderSinglePrices;
	}
	
	
	
}
