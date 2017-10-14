package com.sml.sz.sys.pnr;

/**
 * ssr 项
 * @author shenxj
 * @date 2015-4-15
 */
public class Ssr {

	private String id;
	private String type;
	private String carrier;
	private String psgID;
	private String actionCode;
	private String seats;
	private String text;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPsgID() {
		return psgID;
	}
	public void setPsgID(String psgID) {
		this.psgID = psgID;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
