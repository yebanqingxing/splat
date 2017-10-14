/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airport.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机场Entity
 * @author 张权
 * @version 2016-03-11
 */
public class Airport extends DataEntity<Airport> {
	
	private static final long serialVersionUID = 1L;
	private String airportNameCn;		// 机场名中文
	private String airportNameCnAbbr;		// 机场名中文简称
	private String airportNameEn;		// 机场名英文
	private String airportNamePinyin;		// 机场名中文全拼
	private String airportCode;		// 三字码
	private String airportCodeAbbr;		// 二字码
	private String cityNameCn;		// 城市中文名冗余
	private String cityCode;		// 城市三字码冗余
	private String provinceNameCn;		// 省州中文名冗余
	private String provinceCode;		// 省州三字码冗余
	private String countryNameCn;		// 国家中文名冗余
	private String countryCode;		// country_code
	private String continentNameCn;		// continent_name_cn
	private String continentCode;		// continent_code
	private String iataRegionNameCn;		// iata_region_name_cn
	private String iataAreaCd;		// iata_area_cd
	private String areaName;		// area_name
	
	public Airport() {
		super();
	}

	public Airport(String id){
		super(id);
	}

	@Length(min=0, max=128, message="机场名中文长度必须介于 0 和 128 之间")
	public String getAirportNameCn() {
		return airportNameCn;
	}

	public void setAirportNameCn(String airportNameCn) {
		this.airportNameCn = airportNameCn;
	}
	
	@Length(min=0, max=128, message="机场名中文简称长度必须介于 0 和 128 之间")
	public String getAirportNameCnAbbr() {
		return airportNameCnAbbr;
	}

	public void setAirportNameCnAbbr(String airportNameCnAbbr) {
		this.airportNameCnAbbr = airportNameCnAbbr;
	}
	
	@Length(min=0, max=128, message="机场名英文长度必须介于 0 和 128 之间")
	public String getAirportNameEn() {
		return airportNameEn;
	}

	public void setAirportNameEn(String airportNameEn) {
		this.airportNameEn = airportNameEn;
	}
	
	@Length(min=0, max=128, message="机场名中文全拼长度必须介于 0 和 128 之间")
	public String getAirportNamePinyin() {
		return airportNamePinyin;
	}

	public void setAirportNamePinyin(String airportNamePinyin) {
		this.airportNamePinyin = airportNamePinyin;
	}
	
	@Length(min=0, max=3, message="三字码长度必须介于 0 和 3 之间")
	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	
	@Length(min=0, max=2, message="二字码长度必须介于 0 和 2 之间")
	public String getAirportCodeAbbr() {
		return airportCodeAbbr;
	}

	public void setAirportCodeAbbr(String airportCodeAbbr) {
		this.airportCodeAbbr = airportCodeAbbr;
	}
	
	@Length(min=0, max=64, message="城市中文名冗余长度必须介于 0 和 64 之间")
	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}
	
	@Length(min=0, max=3, message="城市三字码冗余长度必须介于 0 和 3 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=64, message="省州中文名冗余长度必须介于 0 和 64 之间")
	public String getProvinceNameCn() {
		return provinceNameCn;
	}

	public void setProvinceNameCn(String provinceNameCn) {
		this.provinceNameCn = provinceNameCn;
	}
	
	@Length(min=0, max=3, message="省州三字码冗余长度必须介于 0 和 3 之间")
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Length(min=0, max=32, message="国家中文名冗余长度必须介于 0 和 32 之间")
	public String getCountryNameCn() {
		return countryNameCn;
	}

	public void setCountryNameCn(String countryNameCn) {
		this.countryNameCn = countryNameCn;
	}
	
	@Length(min=0, max=3, message="country_code长度必须介于 0 和 3 之间")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Length(min=0, max=16, message="continent_name_cn长度必须介于 0 和 16 之间")
	public String getContinentNameCn() {
		return continentNameCn;
	}

	public void setContinentNameCn(String continentNameCn) {
		this.continentNameCn = continentNameCn;
	}
	
	@Length(min=0, max=3, message="continent_code长度必须介于 0 和 3 之间")
	public String getContinentCode() {
		return continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}
	
	@Length(min=0, max=32, message="iata_region_name_cn长度必须介于 0 和 32 之间")
	public String getIataRegionNameCn() {
		return iataRegionNameCn;
	}

	public void setIataRegionNameCn(String iataRegionNameCn) {
		this.iataRegionNameCn = iataRegionNameCn;
	}
	
	@Length(min=0, max=64, message="iata_area_cd长度必须介于 0 和 64 之间")
	public String getIataAreaCd() {
		return iataAreaCd;
	}

	public void setIataAreaCd(String iataAreaCd) {
		this.iataAreaCd = iataAreaCd;
	}
	
	@Length(min=0, max=32, message="area_name长度必须介于 0 和 32 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}