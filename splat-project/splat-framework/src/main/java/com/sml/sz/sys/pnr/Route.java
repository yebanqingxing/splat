package com.sml.sz.sys.pnr;

import java.util.List;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 航段信息
 * @author shenxj
 *
 */
public class Route extends DataEntity<Route>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flight;//航班号
	private String clazz;//舱位
	
	private String fromCity;//始发城市,去程起点
	private String tocity;//到达城市，去程终点
	private String fromCityCode;//始发城市，去程起点三字码；
	private String toCityCode;//始发城市，去程起点三字码；
	private String backCity;//回程终点
	
	private String fromAirport;//始发机场
	private String fromAirportCode;//起点机场三字码
	private String toAirport;//到达机场
	private String toAirportCode;//终点机场三字码
	
	private String fromCountyName;//所属国家
	private String fromCountryCode;//去程起点国家二字码
	private String toCountryName;//去程所属国家
	private String toCountryCode;//去程终点国家三字码
	
	private String contentName;//所属大洲
	private String fromContentNameCode;//起点大洲
	private String toContentNameCode;//终点大洲
	private String fromRegionCode;//起点大洲三字码
	private String toRegionCode;//终点大洲三字码
	
	private String fromAreaCode;//起点大区
	private String toAreaCode;//终点大区
	
	private String date;//时间
	private String state;//pnr状态
	private String fromTime;//出港时间
	private String toTime;//到港时间
	private String flyTime; //长飞行时
	private String middleAirport; //中转站
	private String arriveDate;//到达的日期 主要是考虑到时间的加减xxx天数
	private String travelType;//行程类型
	private String isAddOn="N";//本航段是否AddOn航段 ;Y:是AddOn航段,N：不是
	private String addOnFly;//0：国内AddOn;1:国际AddOn
	private String isSPA="N";//本航段是否SPA航段;Y：是,N：否;默认为N
	private String isFc = "N";//本航段是否主FC航段;Y：是,N:否;默认为N;
	private String mileAge;//每个航段的里程;
	private String beginTimeMark;
	private Double flyPrice;//每段航程的运价
	private List<PersonTypeInfo> personTypeInfoList;//旅客类型（标志位，按照不同类型的旅客计算每段航程的运价）

	private Qte qte;

	
	
	
	
	public String getFromRegionCode() {
		return fromRegionCode;
	}
	public void setFromRegionCode(String fromRegionCode) {
		this.fromRegionCode = fromRegionCode;
	}
	public String getToRegionCode() {
		return toRegionCode;
	}
	public void setToRegionCode(String toRegionCode) {
		this.toRegionCode = toRegionCode;
	}
	public String getFromAreaCode() {
		return fromAreaCode;
	}
	public void setFromAreaCode(String fromAreaCode) {
		this.fromAreaCode = fromAreaCode;
	}
	public String getToAreaCode() {
		return toAreaCode;
	}
	public void setToAreaCode(String toAreaCode) {
		this.toAreaCode = toAreaCode;
	}
	public String getFromAirportCode() {
		return fromAirportCode;
	}
	public void setFromAirportCode(String fromAirportCode) {
		this.fromAirportCode = fromAirportCode;
	}
	
	public String getToAirportCode() {
		return toAirportCode;
	}
	public void setToAirportCode(String toAirportCode) {
		this.toAirportCode = toAirportCode;
	}
	public String getFromCountryCode() {
		return fromCountryCode;
	}
	public void setFromCountryCode(String fromCountryCode) {
		this.fromCountryCode = fromCountryCode;
	}
	public String getToCountryCode() {
		return toCountryCode;
	}
	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}
	public String getFromContentNameCode() {
		return fromContentNameCode;
	}
	public void setFromContentNameCode(String fromContentNameCode) {
		this.fromContentNameCode = fromContentNameCode;
	}
	public String getToContentNameCode() {
		return toContentNameCode;
	}
	public void setToContentNameCode(String toContentNameCode) {
		this.toContentNameCode = toContentNameCode;
	}
	public List<PersonTypeInfo> getPersonTypeInfoList() {
		return personTypeInfoList;
	}
	public void setPersonTypeInfoList(List<PersonTypeInfo> personTypeInfoList) {
		this.personTypeInfoList = personTypeInfoList;
	}
	public Double getFlyPrice() {
		return flyPrice;
	}
	public void setFlyPrice(Double flyPrice) {
		this.flyPrice = flyPrice;
	}
	public String getMileAge() {
		return mileAge;
	}
	public void setMileAge(String mileAge) {
		this.mileAge = mileAge;
	}
	public String getAddOnFly() {
		return addOnFly;
	}
	public void setAddOnFly(String addOnFly) {
		this.addOnFly = addOnFly;
	}
	public String getIsAddOn() {
		return isAddOn;
	}
	public void setIsAddOn(String isAddOn) {
		this.isAddOn = isAddOn;
	}
	public String getIsSPA() {
		return isSPA;
	}
	public void setIsSPA(String isSPA) {
		this.isSPA = isSPA;
	}
	public String getIsFc() {
		return isFc;
	}
	public void setIsFc(String isFc) {
		this.isFc = isFc;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	

	public String getFromCountyName() {
		return fromCountyName;
	}
	public void setFromCountyName(String fromCountyName) {
		this.fromCountyName = fromCountyName;
	}
	public String getToCountryName() {
		return toCountryName;
	}
	public void setToCountryName(String toCountryName) {
		this.toCountryName = toCountryName;
	}
	
	public Qte getQte() {
		return qte;
	}
	public void setQte(Qte qte) {
		this.qte = qte;
	}

	public String getBeginTimeMark() {
		return beginTimeMark;
	}
	public void setBeginTimeMark(String beginTimeMark) {
		this.beginTimeMark = beginTimeMark;
	}

	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	
	public String getBackCity() {
		return backCity;
	}
	public void setBackCity(String backCity) {
		this.backCity = backCity;
	}
	

	public String getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getFromCityCode() {
		return fromCityCode;
	}
	public void setFromCityCode(String fromCityCode) {
		this.fromCityCode = fromCityCode;
	}
	public String getToCityCode() {
		return toCityCode;
	}
	public void setToCityCode(String toCityCode) {
		this.toCityCode = toCityCode;
	}
	
	
	public String getMiddleAirport() {
		return middleAirport;
	}
	public void setMiddleAirport(String middleAirport) {
		this.middleAirport = middleAirport;
	}
	public String getFlyTime() {
		return flyTime;
	}
	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}

	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getTocity() {
		return tocity;
	}
	public void setTocity(String tocity) {
		this.tocity = tocity;
	}
	public String getFromAirport() {
		return fromAirport;
	}
	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}
	public String getToAirport() {
		return toAirport;
	}
	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	@Override
	public String toString() {
		return "Route [flight=" + flight + ", clazz=" + clazz + ", fromCity=" + fromCity + ", tocity=" + tocity
				+ ", fromAirport=" + fromAirport + ", toAirport=" + toAirport + ", date=" + date + ", state=" + state
				+ ", fromTime=" + fromTime + ", toTime=" + toTime + ", flyTime=" + flyTime + ", middleAirport="
				+ middleAirport + ", arriveDate=" + arriveDate + "]";
	}


}
