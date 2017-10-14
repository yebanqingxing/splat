package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.TicketorderSegment;

public class TicketorderSegmentModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TicketorderSegment> ticketorderSegments;

	public List<TicketorderSegment> getTicketorderSegments() {
		return ticketorderSegments;
	}

	public void setTicketorderSegments(List<TicketorderSegment> ticketorderSegments) {
		this.ticketorderSegments = ticketorderSegments;
	}
	
	

}
