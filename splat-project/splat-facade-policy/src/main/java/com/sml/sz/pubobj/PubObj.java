package com.sml.sz.pubobj;

import com.sml.sz.common.persistence.DataEntity;

public class PubObj extends DataEntity<PubObj>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String airCode;
	
	private String chinaCode;
	
	private String airName;
	
	private String twoName;

	public String getAirCode() {
		return airCode;
	}

	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}

	public String getChinaCode() {
		return chinaCode;
	}

	public void setChinaCode(String chinaCode) {
		this.chinaCode = chinaCode;
	}

	public String getAirName() {
		return airName;
	}

	public void setAirName(String airName) {
		this.airName = airName;
	}

	public String getTwoName() {
		return twoName;
	}

	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}
	
	
}
