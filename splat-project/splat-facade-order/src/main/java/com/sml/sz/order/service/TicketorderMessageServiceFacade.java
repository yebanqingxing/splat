/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPnr;
/**
 * 机票消息pnrService
 * @author 李千超
 * @version 2016-03-10
 */
 
public interface TicketorderMessageServiceFacade  {

	/**
	 * 机票消息 通过ID 获取
	 * @param String id
	 * @return TicketorderPnr
	 */
	public TicketorderMessage get(String id);
	
	/**
	 * 机票消息 查询不分页
	 * @param TicketorderPnr
	 * @return List<TicketorderPnr>
	 */
	public List<TicketorderMessage> findList(TicketorderMessage ticketorderMessage);
	
	/**
	 * 机票消息 查询分页
	 * @param Page<TicketorderPnr> page,TicketorderPnr
	 * @return Page<TicketorderPnr>
	 */
	public Page<TicketorderMessage> findPage(Page<TicketorderMessage> page, TicketorderMessage ticketorderMessage);
	
	/**
	 * 机票消息 保存
	 * @param TicketorderPnr
	 * @return void
	 */
	 
	public void save(TicketorderMessage ticketorderMessage) ;
	
	/**
	 * 机票消息 删除
	 * @param TicketorderPnr
	 * @return void
	 */
	 
	public void delete(TicketorderMessage ticketorderMessage);
	
	/**
	 * 通过订单号查询留言
	 * @param order
	 * @return
	 */
	public List<TicketorderMessage> findTicketorderMessage(String order);
	
}