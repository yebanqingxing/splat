/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderPnr;
/**
 * 机票订单pnrService
 * @author 李千超
 * @version 2016-03-10
 */
 
public interface TicketorderPnrServiceFacade  {

	/**
	 * 机票订单pnr 通过ID 获取
	 * @param String id
	 * @return TicketorderPnr
	 */
	public TicketorderPnr get(String id);
	
	/**
	 * 机票订单pnr 查询不分页
	 * @param TicketorderPnr
	 * @return List<TicketorderPnr>
	 */
	public List<TicketorderPnr> findList(TicketorderPnr ticketorderPnr);
	
	/**
	 * 机票订单pnr 查询分页
	 * @param Page<TicketorderPnr> page,TicketorderPnr
	 * @return Page<TicketorderPnr>
	 */
	public Page<TicketorderPnr> findPage(Page<TicketorderPnr> page, TicketorderPnr ticketorderPnr);
	
	/**
	 * 机票订单pnr 保存
	 * @param TicketorderPnr
	 * @return void
	 */
	 
	public void save(TicketorderPnr ticketorderPnr) ;
	
	/**
	 * 机票订单pnr 删除
	 * @param TicketorderPnr
	 * @return void
	 */
	 
	public void delete(TicketorderPnr ticketorderPnr);
	
}