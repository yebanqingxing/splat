/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.country.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 国家Entity
 * @author 张权
 * @version 2016-03-11
 */
public class Country extends DataEntity<Country> {
	
	private static final long serialVersionUID = 1L;
	private String countryNameCn;		// 国家名中文
	private String countryNameCnAbbr;		// 国家名中文简称
	private String countryNameEn;		// 国家名英文
	private String countryNameEnAbbr;		// 国家英文名简称
	private String countryNamePinyin;		// country_name_pinyin
	private String countryCode;		// 三字码
	private String countryCodeAbbr;		// 二字码
	private String continentId;		// 大洲id
	private String continentName;		// 大洲名称
	
	public Country() {
		super();
	}

	public Country(String id){
		super(id);
	}

	@Length(min=0, max=64, message="国家名中文长度必须介于 0 和 64 之间")
	public String getCountryNameCn() {
		return countryNameCn;
	}

	public void setCountryNameCn(String countryNameCn) {
		this.countryNameCn = countryNameCn;
	}
	
	@Length(min=0, max=64, message="国家名中文简称长度必须介于 0 和 64 之间")
	public String getCountryNameCnAbbr() {
		return countryNameCnAbbr;
	}

	public void setCountryNameCnAbbr(String countryNameCnAbbr) {
		this.countryNameCnAbbr = countryNameCnAbbr;
	}
	
	@Length(min=0, max=64, message="国家名英文长度必须介于 0 和 64 之间")
	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}
	
	@Length(min=0, max=128, message="国家英文名简称长度必须介于 0 和 128 之间")
	public String getCountryNameEnAbbr() {
		return countryNameEnAbbr;
	}

	public void setCountryNameEnAbbr(String countryNameEnAbbr) {
		this.countryNameEnAbbr = countryNameEnAbbr;
	}
	
	@Length(min=0, max=128, message="country_name_pinyin长度必须介于 0 和 128 之间")
	public String getCountryNamePinyin() {
		return countryNamePinyin;
	}

	public void setCountryNamePinyin(String countryNamePinyin) {
		this.countryNamePinyin = countryNamePinyin;
	}
	
	@Length(min=0, max=3, message="三字码长度必须介于 0 和 3 之间")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Length(min=0, max=2, message="二字码长度必须介于 0 和 2 之间")
	public String getCountryCodeAbbr() {
		return countryCodeAbbr;
	}

	public void setCountryCodeAbbr(String countryCodeAbbr) {
		this.countryCodeAbbr = countryCodeAbbr;
	}
	
	@Length(min=0, max=11, message="大洲id长度必须介于 0 和 11 之间")
	public String getContinentId() {
		return continentId;
	}

	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}
	
	@Length(min=0, max=16, message="大洲名称长度必须介于 0 和 16 之间")
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
}