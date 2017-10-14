package com.sml.sz.sys.pnr;

import java.io.Serializable;

/**
 * 
 * @author 冯俊伟
 * fc:将qte文本中的fc的内容封装与fc中
 */
public class Fc  implements Serializable{
	
	public String fcNum;//fc数 
	public String fromCity;//始发城市三字码
	private String fromCityCode;//机场三字码
	public String toCity;//到达城市三字码
	private String toCityCode; //到达机场三字码
	public String rate;//汇率
	public String qString;//Q值
	public String iden;//fc的身份  IN位婴儿， 大人为空
	
	private Integer mileAge;//fc的公里数
	
	
	
	
	
	
	
	
	
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
	public Integer getMileAge() {
		return mileAge;
	}
	public void setMileAge(Integer mileAge) {
		this.mileAge = mileAge;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public String getqString() {
		return qString;
	}
	public void setqString(String qString) {
		this.qString = qString;
	}
	public String getFcNum() {
		return fcNum;
	}
	public void setFcNum(String fcNum) {
		this.fcNum = fcNum;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Fc [fcNum=" + fcNum + ", fromCity=" + fromCity + ", toCity=" + toCity + ", rate=" + rate + ", qString="
				+ qString + ", iden=" + iden + "]";
	}

	
}
