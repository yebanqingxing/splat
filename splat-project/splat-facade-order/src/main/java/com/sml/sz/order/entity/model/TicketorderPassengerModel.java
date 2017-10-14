package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.TicketorderPassenger;

public class TicketorderPassengerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<TicketorderPassenger> ticketorderPassengers;

	public List<TicketorderPassenger> getTicketorderPassengers() {
		return ticketorderPassengers;
	}

	public void setTicketorderPassengers(List<TicketorderPassenger> ticketorderPassengers) {
		this.ticketorderPassengers = ticketorderPassengers;
	}
	
	
}
