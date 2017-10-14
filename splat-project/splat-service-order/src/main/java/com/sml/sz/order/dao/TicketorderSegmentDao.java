/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderSegment;

/**
 * 机票订单航段表DAO接口
 * @author 李千超
 * @version 2016-03-10
 */
@MyBatisDao
public interface TicketorderSegmentDao extends CrudDao<TicketorderSegment> {
	
	/**
	 * 通过订单号查询航段
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderSegment> findSegmentByOrderNo(String orderNo);
	
	/**
	 * 根据id的集合查询航段集合
	 * @param segIds
	 * @return
	 */
	public List<TicketorderSegment> findSegmentByIdList(List<Integer> segIds);
	
	/**
	 * 通过订单的订单号查询订单的信息
	 * @param orderNo
	 * @return
	 */
	public TicketorderDetail findorderDetailByOrderNo(String orderNo);
	
	/**
	 * 
	 * @param ticketorderSegment
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void saveSegment(TicketorderSegment ticketorderSegment);
	
	
 }