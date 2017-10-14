package com.sml.sz.sys.pnr;

import java.util.List;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 匹配政策后的数据
 * @author admin
 *
 */
public class PersonTypeInfo extends DataEntity<PersonTypeInfo>{

	/**实体类中主要定义的是根据旅客的不同类型得出的不同信息      张权*/
	private String personType;//旅客的类型(1、成人；2、儿童；3、婴儿)
	private String personIden;//旅客身份
	private Double fare;//根据不同类型定义的不同票价（免税）
	private Double price;//票面价（销售价）
	private Double flyPrice;//每段航程的价格
	private Double isPrice;//计奖部分价格
	private Double rebate;//返点
	private Double agenCy; //代理费
	private Double billingFee;//开票费（手续费）
	private Double tax;//税费
	private String Type;//类型（暂无）
	private Qte qte;//不同类型的QTE
	private List<Fc> fcList;//不同类型fc的集合
	private Integer mileAge;//记录Fc的总公里数
	private List<Route> routeList;//不同类型的航段的价格
	
	
	
	
	
	
	public Integer getMileAge() {
		return mileAge;
	}
	public void setMileAge(Integer mileAge) {
		this.mileAge = mileAge;
	}
	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	public Qte getQte() {
		return qte;
	}
	public void setQte(Qte qte) {
		this.qte = qte;
	}
	public List<Fc> getFcList() {
		return fcList;
	}
	public void setFcList(List<Fc> fcList) {
		this.fcList = fcList;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getPersonIden() {
		return personIden;
	}
	public void setPersonIden(String personIden) {
		this.personIden = personIden;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getFlyPrice() {
		return flyPrice;
	}
	public void setFlyPrice(Double flyPrice) {
		this.flyPrice = flyPrice;
	}
	public Double getIsPrice() {
		return isPrice;
	}
	public void setIsPrice(Double isPrice) {
		this.isPrice = isPrice;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public Double getAgenCy() {
		return agenCy;
	}
	public void setAgenCy(Double agenCy) {
		this.agenCy = agenCy;
	}
	public Double getBillingFee() {
		return billingFee;
	}
	public void setBillingFee(Double billingFee) {
		this.billingFee = billingFee;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	@Override
	public String toString() {
		return "PersonTypeInfo [personType=" + personType + ", personIden=" + personIden + ", fare=" + fare + ", price="
				+ price + ", flyPrice=" + flyPrice + ", isPrice=" + isPrice + ", rebate=" + rebate + ", agenCy="
				+ agenCy + ", billingFee=" + billingFee + ", tax=" + tax + ", Type=" + Type + "]";
	}
	
	
}
