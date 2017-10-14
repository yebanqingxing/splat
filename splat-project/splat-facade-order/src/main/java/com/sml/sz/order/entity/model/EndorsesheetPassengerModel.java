package com.sml.sz.order.entity.model;

import java.util.List;

import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.order.entity.EndorsesheetPassenger;

public class EndorsesheetPassengerModel extends DataEntity<EndorsesheetPassengerModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<EndorsesheetPassenger> endorsesheetPassengers;

	public List<EndorsesheetPassenger> getEndorsesheetPassengers() {
		return endorsesheetPassengers;
	}

	public void setEndorsesheetPassengers(List<EndorsesheetPassenger> endorsesheetPassengers) {
		this.endorsesheetPassengers = endorsesheetPassengers;
	}
	
	

}
