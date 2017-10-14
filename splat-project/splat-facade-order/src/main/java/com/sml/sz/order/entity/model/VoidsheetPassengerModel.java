package com.sml.sz.order.entity.model;

import java.util.List;

import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.order.entity.VoidsheetPassenger;

public class VoidsheetPassengerModel extends DataEntity<VoidsheetPassengerModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<VoidsheetPassenger> voidsheetPassengers;

	public List<VoidsheetPassenger> getVoidsheetPassengers() {
		return voidsheetPassengers;
	}

	public void setVoidsheetPassengers(List<VoidsheetPassenger> voidsheetPassengers) {
		this.voidsheetPassengers = voidsheetPassengers;
	}
	
	

}
