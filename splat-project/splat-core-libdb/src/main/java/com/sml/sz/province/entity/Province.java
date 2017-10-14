/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.province.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 省份Entity
 * @author 张权
 * @version 2016-03-11
 */
public class Province extends DataEntity<Province> {
	
	private static final long serialVersionUID = 1L;
	private String provinceNameCn;		// 省州名中文
	private String provinceNameCnAbbr;		// 省州名中文简称
	private String provinceNameEn;		// 省州名英文
	private String provinceNamePinyin;		// 省州名中文全拼
	private String provinceCode;		// 三字码
	private String provinceCodeAbbr;		// 二字码
	private String countryNameCn;		// 所在国家中文名
	
	public Province() {
		super();
	}

	public Province(String id){
		super(id);
	}

	@Length(min=0, max=64, message="省州名中文长度必须介于 0 和 64 之间")
	public String getProvinceNameCn() {
		return provinceNameCn;
	}

	public void setProvinceNameCn(String provinceNameCn) {
		this.provinceNameCn = provinceNameCn;
	}
	
	@Length(min=0, max=32, message="省州名中文简称长度必须介于 0 和 32 之间")
	public String getProvinceNameCnAbbr() {
		return provinceNameCnAbbr;
	}

	public void setProvinceNameCnAbbr(String provinceNameCnAbbr) {
		this.provinceNameCnAbbr = provinceNameCnAbbr;
	}
	
	@Length(min=0, max=64, message="省州名英文长度必须介于 0 和 64 之间")
	public String getProvinceNameEn() {
		return provinceNameEn;
	}

	public void setProvinceNameEn(String provinceNameEn) {
		this.provinceNameEn = provinceNameEn;
	}
	
	@Length(min=0, max=64, message="省州名中文全拼长度必须介于 0 和 64 之间")
	public String getProvinceNamePinyin() {
		return provinceNamePinyin;
	}

	public void setProvinceNamePinyin(String provinceNamePinyin) {
		this.provinceNamePinyin = provinceNamePinyin;
	}
	
	@Length(min=0, max=3, message="三字码长度必须介于 0 和 3 之间")
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Length(min=0, max=2, message="二字码长度必须介于 0 和 2 之间")
	public String getProvinceCodeAbbr() {
		return provinceCodeAbbr;
	}

	public void setProvinceCodeAbbr(String provinceCodeAbbr) {
		this.provinceCodeAbbr = provinceCodeAbbr;
	}
	
	@Length(min=0, max=32, message="所在国家中文名长度必须介于 0 和 32 之间")
	public String getCountryNameCn() {
		return countryNameCn;
	}

	public void setCountryNameCn(String countryNameCn) {
		this.countryNameCn = countryNameCn;
	}
	
}