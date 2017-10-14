package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.model.TicketorderPassengerModel;

/**
 * 订单旅客的信息
 * @author lqc
 *
 */
public interface TicketorderPassengerFacade {
	
	/**
	 * 订单旅客的信息 通过ID 获取
	 * @param String id
	 * @return TicketorderPassenger
	 */
	public TicketorderPassenger get(String id);
	
	/**
	 * 订单旅客的信息 查询不分页
	 * @param TicketorderPassenger
	 * @return List<TicketorderPassenger>
	 */
	public List<TicketorderPassenger> findList(TicketorderPassenger ticketorderPassenger) ;
	
	/**
	 * 订单旅客的信息 查询分页
	 * @param Page<TicketorderPassenger> page,TicketorderPassenger
	 * @return Page<TicketorderPassenger>
	 */
	public Page<TicketorderPassenger> findPage(Page<TicketorderPassenger> page, TicketorderPassenger ticketorderPassenger) ;
	
	/**
	 * 订单旅客的信息 保存
	 * @param TicketorderPassenger
	 * @return void
	 */
	
	public void save(TicketorderPassenger ticketorderPassenger);
	
	/**
	 * 订单旅客的信息 删除
	 * @param TicketorderPassenger
	 * @return void
	 */
	 
	public void delete(TicketorderPassenger ticketorderPassenger);
	
	/**
	 * 通过订单号查询相关的旅客信息
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderPassenger> findTicketorderPassenger(String orderNo);
	
	/**
	 * 修改旅客的退票状态
	 * @param refundStatus 退票状态
	 * @param id 旅客id
	 */
	public void updateRefundStatusById(String refundStatus,String id);
	
	/**
	 * 修改旅客的废票状态
	 * @param map
	 */
	public void updateVoidStatusById(String voidStatus, String id);
	
	/**
	 * 修改旅客的改签状态
	 * @param map
	 */
	public void updateEndorseStatusById(String endorseStatus,String id);
	
	/**
	 * 通过选择旅客的id的字符串查询要了退票的旅客的集合
	 * @param passIds
	 * @return
	 */
	public List<TicketorderPassenger> findPassengerList(String passIds,String status);

	/**
	 * 添加旅客信息 
	 * @param ticketorderPassenger
	 * @param orderNum
	 */
	public void savePassenger(TicketorderPassengerModel ticketorderPassengers, String orderNum);

	/**
	 * 出票以后修改票号
	 * @param ticketNo 票号
	 * @param id 旅客id
	 */
	public void updateTicketorderTicket(String ticketNo,String id);
	
	/**
	 * 取消之后修改状态
	 * @param status
	 * @param id
	 */
	public void updateVoidRefundEndorseStatus(String status,String id);

}
