/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 机票订单消息 pnrEntity
 * @author 黄诗源
 * @version 2016-03-10
 */
public class TicketorderMessage extends DataEntity<TicketorderMessage> {
	
	private static final long serialVersionUID = 1L;
	private String order_no;		// crs_pnr
	private String message;		// arl_pnr
	private String createUser;		// mid_pnr
	private Date createTime;		// pnr生成日期（主机）
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}