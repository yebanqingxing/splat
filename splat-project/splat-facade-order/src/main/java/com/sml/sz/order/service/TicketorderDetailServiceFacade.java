package com.sml.sz.order.service;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.OrderStatusCount;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.TicketorderDetail;

public interface TicketorderDetailServiceFacade {
	/**
	 * 订单生成模块 通过ID 获取
	 * @param String id
	 * @return TbTicketorderDetail
	 */
	public TicketorderDetail get(String id);
	
	/**
	 * 订单生成模块 查询不分页
	 * @param TbTicketorderDetailService
	 * @return List<TbTicketorderDetail>
	 */
	public List<TicketorderDetail> findList(TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 订单生成模块 查询分页
	 * @param Page<TbTicketorderDetail> page,TbTicketorderDetail
	 * @return Page<TbTicketorderDetail>
	 */
	public Page<TicketorderDetail> findPage(Page<TicketorderDetail> page, TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 订单生成模块 保存
	 * @param TbTicketorderDetailService
	 * @return void
	 */

	public void save(TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 订单生成模块 删除
	 * @param TbTicketorderDetailService
	 * @return void
	 */
	
	public void delete(TicketorderDetail tbTicketorderDetail);
	
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
	public void updateOrderPrice(TicketorderDetail tbTicketorderDetail);

	/**
	 * 支付成功修改支付状态
	 * @param orderStatus
	 * @param orderNo
	 */
	public void updatePayStatus(String payStatus,String orderNo);

	/**
	 * 查看支付状态以及条数
	 * @return
	 */
	public Map<String, String> findPayStatusCount(TicketorderDetail tbTicketorderDetail);
	
	/**
	 * 查看订单状态以及条数
	 * @return
	 */
	public Map<String, String> findOrderStatusCount(TicketorderDetail tbTicketorderDetail);
	
	
	/**
	 * 修改订单的状态
	 * @param orderStatus 订单状态
	 * @param orderNO 订单号
	 */
	public void updateOrderStatus(String orderStatus,String orderNO);
	
	
	/**
	 *  修改订单的操作状态
	 * @param currentStatus 操作状态
	 * @param orderNo 要修改的订单号
	 */
	public void updateCurrentStatus(String currentStatus,String orderNo);
	
	/**
	 * 通过订单的订单号查询订单的信息
	 * @param orderNo
	 * @return
	 */
	public TicketorderDetail findorderDetailByOrderNo(String orderNo);
	
	/**
	 * 订单成功以后的流水号入库
	 * @param relevantoOrderNo
	 * @param orderNo
	 */
	public void updateRelevantOrderNo(String relevantoOrderNo,String orderNo);

	/**
	 * 更改出票后的状态 
	 */
	public void updateIssueResult(String issueResult,String orderNo);
	
	/**
	 * 查询所有的操作状态
	 * @param ticketorderDetail
	 * @return
	 */
	public Map<String, String> findCurrentStatus(TicketorderDetail ticketorderDetail);

}
