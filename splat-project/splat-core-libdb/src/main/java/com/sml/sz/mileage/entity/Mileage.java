/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.mileage.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 里程Entity
 * @author 李千超
 * @version 2016-03-15
 */
public class Mileage extends DataEntity<Mileage> {
	
	private static final long serialVersionUID = 1L;
	private String od;		// od
	private String mileage;		// 里程
	
	public Mileage() {
		super();
	}

	public Mileage(String id){
		super(id);
	}

	@Length(min=0, max=16, message="od长度必须介于 0 和 16 之间")
	public String getOd() {
		return od;
	}

	public void setOd(String od) {
		this.od = od;
	}
	
	@Length(min=0, max=11, message="里程长度必须介于 0 和 11 之间")
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
}