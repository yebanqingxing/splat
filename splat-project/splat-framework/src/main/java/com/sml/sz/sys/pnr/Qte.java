package com.sml.sz.sys.pnr;





import java.io.Serializable;

public class Qte implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String price;//总的价（**含税的票面价）
	private String priceUnit;//总的票价的单位  属于什么币种
	private String state;//转机 or 停留
	private String isContainTax;//是不是含税  01表示含税
	private String fare;//票面价 不含税
	private String tax;//税价
	//private List<Fc> fc;
	private String type;//票价类型 it 

	private String identity;//旅客身份

	private String taxUnit;//税的单位

	private String fareUnit;//票面价（不含税）的单位

	private String commission;//代理费率









	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getFareUnit() {
		return fareUnit;
	}
	public void setFareUnit(String fareUnit) {
		this.fareUnit = fareUnit;
	}
	public String getTaxUnit() {
		return taxUnit;
	}
	public void setTaxUnit(String taxUnit) {
		this.taxUnit = taxUnit;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsContainTax() {
		return isContainTax;
	}
	public void setIsContainTax(String isContainTax) {
		this.isContainTax = isContainTax;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	@Override
	public String toString() {
		return "Qte [price=" + price + ", priceUnit=" + priceUnit + ", state=" + state + ", isContainTax="
				+ isContainTax + ", fare=" + fare + ", tax=" + tax + ", type=" + type + ", identity=" + identity
				+ ", taxUnit=" + taxUnit + ", fareUnit=" + fareUnit + ", commission=" + commission + "]";
	}

}
