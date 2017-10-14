package com.sml.sz.order.entity.model;

import java.io.Serializable;
import java.util.List;

import com.sml.sz.order.entity.Recipient;

public class RecipientModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Recipient> recipient;

	public List<Recipient> getRecipient() {
		return recipient;
	}

	public void setRecipient(List<Recipient> recipient) {
		this.recipient = recipient;
	}
	
	
	
}
