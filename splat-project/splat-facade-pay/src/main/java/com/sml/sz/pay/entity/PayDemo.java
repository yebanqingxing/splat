/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.entity;

import org.hibernate.validator.constraints.Length;

import com.sml.sz.common.persistence.DataEntity;


/**
 * 示例功能，用来演示项目依赖Entity
 * @author 黄使用
 * @version 2016-03-06
 */
public class PayDemo extends DataEntity<PayDemo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String tel;		// 电话
	
	public PayDemo() {
		super();
	}

	public PayDemo(String id){
		super(id);
	}

	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="电话长度必须介于 0 和 32 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}