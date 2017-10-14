/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 测试-功能描述Entity
 * @author 测试-生成功能作者
 * @version 2016-03-09
 */
public class TbCity extends DataEntity<TbCity> {
	
	private static final long serialVersionUID = 1L;
	private String cityNameCn;		// 城市名中文
	private String cityNameCnAbbr;		// 城市名中文简称
	private String cityNameEn;		// 城市名英文
	private String cityNamePinyin;		// 城市名中文全拼
	private String cityNamePinyinName;		// 城市名中文简拼
	private String cityCode;		// city_code
	private String cityCodeAbbr;		// 二字码
	private String provinceNameCn;		// 所属省份名称
	private String iataAreaCd;		// 所在省州中文名
	private String iataRegionEnAbbr;		// 子区码
	private String iataRegionCnName;		// 子区中文名
	private String iataRegionEn;		// 子区英文名
	
	public TbCity() {
		super();
	}

	public TbCity(String id){
		super(id);
	}

	@Length(min=0, max=64, message="城市名中文长度必须介于 0 和 64 之间")
	public String getCityNameCn() {
		return cityNameCn;
	}

	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}
	
	@Length(min=0, max=64, message="城市名中文简称长度必须介于 0 和 64 之间")
	public String getCityNameCnAbbr() {
		return cityNameCnAbbr;
	}

	public void setCityNameCnAbbr(String cityNameCnAbbr) {
		this.cityNameCnAbbr = cityNameCnAbbr;
	}
	
	@Length(min=0, max=128, message="城市名英文长度必须介于 0 和 128 之间")
	public String getCityNameEn() {
		return cityNameEn;
	}

	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}
	
	@Length(min=0, max=128, message="城市名中文全拼长度必须介于 0 和 128 之间")
	public String getCityNamePinyin() {
		return cityNamePinyin;
	}

	public void setCityNamePinyin(String cityNamePinyin) {
		this.cityNamePinyin = cityNamePinyin;
	}
	
	@Length(min=0, max=16, message="城市名中文简拼长度必须介于 0 和 16 之间")
	public String getCityNamePinyinName() {
		return cityNamePinyinName;
	}

	public void setCityNamePinyinName(String cityNamePinyinName) {
		this.cityNamePinyinName = cityNamePinyinName;
	}
	
	@Length(min=0, max=3, message="city_code长度必须介于 0 和 3 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=2, message="二字码长度必须介于 0 和 2 之间")
	public String getCityCodeAbbr() {
		return cityCodeAbbr;
	}

	public void setCityCodeAbbr(String cityCodeAbbr) {
		this.cityCodeAbbr = cityCodeAbbr;
	}
	
	@Length(min=0, max=32, message="所属省份名称长度必须介于 0 和 32 之间")
	public String getProvinceNameCn() {
		return provinceNameCn;
	}

	public void setProvinceNameCn(String provinceNameCn) {
		this.provinceNameCn = provinceNameCn;
	}
	
	@Length(min=0, max=32, message="所在省州中文名长度必须介于 0 和 32 之间")
	public String getIataAreaCd() {
		return iataAreaCd;
	}

	public void setIataAreaCd(String iataAreaCd) {
		this.iataAreaCd = iataAreaCd;
	}
	
	@Length(min=0, max=6, message="子区码长度必须介于 0 和 6 之间")
	public String getIataRegionEnAbbr() {
		return iataRegionEnAbbr;
	}

	public void setIataRegionEnAbbr(String iataRegionEnAbbr) {
		this.iataRegionEnAbbr = iataRegionEnAbbr;
	}
	
	@Length(min=0, max=32, message="子区中文名长度必须介于 0 和 32 之间")
	public String getIataRegionCnName() {
		return iataRegionCnName;
	}

	public void setIataRegionCnName(String iataRegionCnName) {
		this.iataRegionCnName = iataRegionCnName;
	}
	
	@Length(min=0, max=64, message="子区英文名长度必须介于 0 和 64 之间")
	public String getIataRegionEn() {
		return iataRegionEn;
	}

	public void setIataRegionEn(String iataRegionEn) {
		this.iataRegionEn = iataRegionEn;
	}
	
}