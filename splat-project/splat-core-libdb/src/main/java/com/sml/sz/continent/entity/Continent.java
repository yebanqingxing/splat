/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.continent.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 大区Entity
 * @author 张权
 * @version 2016-03-11
 */
public class Continent extends DataEntity<Continent> {
	
	private static final long serialVersionUID = 1L;
	private String continentNameCn;		// 大洲名中文
	private String continentNameCnAbbr;		// 大洲名中文简称
	private String continentNameEn;		// 大洲名英文
	private String continentNamePinyin;		// 大洲名中文全拼
	private String continentCode;		// 三字码
	private String continentCodeAbbr;		// 二字码
	
	public Continent() {
		super();
	}

	public Continent(String id){
		super(id);
	}

	@Length(min=0, max=16, message="大洲名中文长度必须介于 0 和 16 之间")
	public String getContinentNameCn() {
		return continentNameCn;
	}

	public void setContinentNameCn(String continentNameCn) {
		this.continentNameCn = continentNameCn;
	}
	
	@Length(min=0, max=16, message="大洲名中文简称长度必须介于 0 和 16 之间")
	public String getContinentNameCnAbbr() {
		return continentNameCnAbbr;
	}

	public void setContinentNameCnAbbr(String continentNameCnAbbr) {
		this.continentNameCnAbbr = continentNameCnAbbr;
	}
	
	@Length(min=0, max=32, message="大洲名英文长度必须介于 0 和 32 之间")
	public String getContinentNameEn() {
		return continentNameEn;
	}

	public void setContinentNameEn(String continentNameEn) {
		this.continentNameEn = continentNameEn;
	}
	
	@Length(min=0, max=32, message="大洲名中文全拼长度必须介于 0 和 32 之间")
	public String getContinentNamePinyin() {
		return continentNamePinyin;
	}

	public void setContinentNamePinyin(String continentNamePinyin) {
		this.continentNamePinyin = continentNamePinyin;
	}
	
	@Length(min=0, max=3, message="三字码长度必须介于 0 和 3 之间")
	public String getContinentCode() {
		return continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}
	
	@Length(min=0, max=2, message="二字码长度必须介于 0 和 2 之间")
	public String getContinentCodeAbbr() {
		return continentCodeAbbr;
	}

	public void setContinentCodeAbbr(String continentCodeAbbr) {
		this.continentCodeAbbr = continentCodeAbbr;
	}
	
}