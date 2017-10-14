/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airline.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 航空公司查询Entity
 * @author 冯俊伟
 * @version 2016-03-14
 */
public class Airline extends DataEntity<Airline> {
	
	private static final long serialVersionUID = 1L;
	private String airlineNameCn;		// 中文全称
	private String airlineNameCnAbbr;		// 中文简称
	private String airlineCode;		// 二字码
	private String airlineNameTc;		// 航司中文繁体名称
	private String airlineNameEn;		// 航司英文名称
	private String airlineNameEnAbbr;		// 航司英文名称简写
	private String airlineBillingCode;		// 航司结算码
	private String airlineIcs;		// 主机系统
	private String airlineCrs;		// 分销系统
	private String airlineLocationId;		// 航司所属地id
	private String airlineLocation;		// 航司所属地
	private String airlineSite;		// 航司官网
	private String airlineContact;		// 航司全球联系
	private String airlineAlliance;		// 航司联盟
	private String airlineStarAlliance;		// 星空联盟
	private String airlineGroup;		// 航司集团
	private String airlineFrequentCard;		// 常客卡
	
	public Airline() {
		super();
	}

	public Airline(String id){
		super(id);
	}

	@Length(min=0, max=128, message="中文全称长度必须介于 0 和 128 之间")
	public String getAirlineNameCn() {
		return airlineNameCn;
	}

	public void setAirlineNameCn(String airlineNameCn) {
		this.airlineNameCn = airlineNameCn;
	}
	
	@Length(min=0, max=64, message="中文简称长度必须介于 0 和 64 之间")
	public String getAirlineNameCnAbbr() {
		return airlineNameCnAbbr;
	}

	public void setAirlineNameCnAbbr(String airlineNameCnAbbr) {
		this.airlineNameCnAbbr = airlineNameCnAbbr;
	}
	
	@Length(min=0, max=4, message="二字码长度必须介于 0 和 4 之间")
	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	
	@Length(min=0, max=64, message="航司中文繁体名称长度必须介于 0 和 64 之间")
	public String getAirlineNameTc() {
		return airlineNameTc;
	}

	public void setAirlineNameTc(String airlineNameTc) {
		this.airlineNameTc = airlineNameTc;
	}
	
	@Length(min=0, max=128, message="航司英文名称长度必须介于 0 和 128 之间")
	public String getAirlineNameEn() {
		return airlineNameEn;
	}

	public void setAirlineNameEn(String airlineNameEn) {
		this.airlineNameEn = airlineNameEn;
	}
	
	@Length(min=0, max=64, message="航司英文名称简写长度必须介于 0 和 64 之间")
	public String getAirlineNameEnAbbr() {
		return airlineNameEnAbbr;
	}

	public void setAirlineNameEnAbbr(String airlineNameEnAbbr) {
		this.airlineNameEnAbbr = airlineNameEnAbbr;
	}
	
	@Length(min=0, max=32, message="航司结算码长度必须介于 0 和 32 之间")
	public String getAirlineBillingCode() {
		return airlineBillingCode;
	}

	public void setAirlineBillingCode(String airlineBillingCode) {
		this.airlineBillingCode = airlineBillingCode;
	}
	
	@Length(min=0, max=64, message="主机系统长度必须介于 0 和 64 之间")
	public String getAirlineIcs() {
		return airlineIcs;
	}

	public void setAirlineIcs(String airlineIcs) {
		this.airlineIcs = airlineIcs;
	}
	
	@Length(min=0, max=64, message="分销系统长度必须介于 0 和 64 之间")
	public String getAirlineCrs() {
		return airlineCrs;
	}

	public void setAirlineCrs(String airlineCrs) {
		this.airlineCrs = airlineCrs;
	}
	
	@Length(min=0, max=32, message="航司所属地id长度必须介于 0 和 32 之间")
	public String getAirlineLocationId() {
		return airlineLocationId;
	}

	public void setAirlineLocationId(String airlineLocationId) {
		this.airlineLocationId = airlineLocationId;
	}
	
	@Length(min=0, max=32, message="航司所属地长度必须介于 0 和 32 之间")
	public String getAirlineLocation() {
		return airlineLocation;
	}

	public void setAirlineLocation(String airlineLocation) {
		this.airlineLocation = airlineLocation;
	}
	
	@Length(min=0, max=128, message="航司官网长度必须介于 0 和 128 之间")
	public String getAirlineSite() {
		return airlineSite;
	}

	public void setAirlineSite(String airlineSite) {
		this.airlineSite = airlineSite;
	}
	
	@Length(min=0, max=64, message="航司全球联系长度必须介于 0 和 64 之间")
	public String getAirlineContact() {
		return airlineContact;
	}

	public void setAirlineContact(String airlineContact) {
		this.airlineContact = airlineContact;
	}
	
	@Length(min=0, max=64, message="航司联盟长度必须介于 0 和 64 之间")
	public String getAirlineAlliance() {
		return airlineAlliance;
	}

	public void setAirlineAlliance(String airlineAlliance) {
		this.airlineAlliance = airlineAlliance;
	}
	
	@Length(min=0, max=64, message="星空联盟长度必须介于 0 和 64 之间")
	public String getAirlineStarAlliance() {
		return airlineStarAlliance;
	}

	public void setAirlineStarAlliance(String airlineStarAlliance) {
		this.airlineStarAlliance = airlineStarAlliance;
	}
	
	@Length(min=0, max=128, message="航司集团长度必须介于 0 和 128 之间")
	public String getAirlineGroup() {
		return airlineGroup;
	}

	public void setAirlineGroup(String airlineGroup) {
		this.airlineGroup = airlineGroup;
	}
	
	@Length(min=0, max=128, message="常客卡长度必须介于 0 和 128 之间")
	public String getAirlineFrequentCard() {
		return airlineFrequentCard;
	}

	public void setAirlineFrequentCard(String airlineFrequentCard) {
		this.airlineFrequentCard = airlineFrequentCard;
	}
	
}