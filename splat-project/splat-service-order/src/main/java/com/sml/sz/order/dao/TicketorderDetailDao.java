/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.OrderStatusCount;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.TicketorderDetail;

/**
 * 订单生成模块DAO接口
 * @author 李千超
 * @version 2016-03-08
 */
@MyBatisDao
public interface TicketorderDetailDao extends CrudDao<TicketorderDetail> {
	/**
	 * 通过订单号查询该订单是否已经支付
	 * @param orderNo
	 * @return
	 */
	public String findPayStatusByOrderNo(String orderNo);
	
	/**
	 * 支付成功修改支付状态
	 * @param orderStatus
	 * @param orderNo
	 */
	public void updatePayStatus(Map<String,String> payMap);
	
	/**
	 * 查看支付状态以及条数
	 * @return
	 */
	public List<PayStatusCount> findPayStatusCount(TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 查看订单状态以及条数
	 * @return
	 */
	public List<OrderStatusCount> findOrderStatusCount(TicketorderDetail tbTicketorderDetail);
	
	
	/**
	 * 修改订单状态
	 * @param map
	 */
	public void updateOrderStatus(Map<String,String> map);
	
	/**
	 * 修改订单的操作状态
	 *  @param map
	 */
	public void updateCurrentStatus(Map<String,String> map);
	
	/**
	 * 修改订单的价格
	 *  @param map
	 */
	public void updateOrderPrice(TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 通过订单的订单号查询订单的信息
	 * @param orderNo
	 * @return
	 */
	public TicketorderDetail findorderDetailByOrderNo(String orderNo);
	
	/**
	 * 订单成功以后的流水号入库
	 * @param map
	 */
	public void updateRelevantOrderNo(Map<String,String> map);
	
	
	/**
	 * 
	 * @param map
	 */
	public void updateIssueResult(Map<String,String> map );
	
	/**
	 * 查询所有的操作状态
	 * @param ticketorderDetail
	 * @return
	 */
	public List<CurrentStatus> findCurrentStatus(TicketorderDetail ticketorderDetail);

}