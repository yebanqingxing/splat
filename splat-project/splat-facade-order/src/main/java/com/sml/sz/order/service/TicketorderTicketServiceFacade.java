package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderTicket;

public interface TicketorderTicketServiceFacade {
	/**
	 * 每个票号一个记录 通过ID 获取
	 * @param String id
	 * @return Dispatch
	 */
	public TicketorderTicket get(String id);
	
	/**
	 * 每个票号一个记录 查询不分页
	 * @param Dispatch
	 * @return List<Dispatch>
	 */
	public List<TicketorderTicket> findList(TicketorderTicket ticketorderTicket);
	
	/**
	 * 每个票号一个记录 查询分页
	 * @param Page<Dispatch> page,Dispatch
	 * @return Page<Dispatch>
	 */
	public Page<TicketorderTicket> findPage(Page<TicketorderTicket> page, TicketorderTicket ticketorderTicket);
	
	/**
	 * 每个票号一个记录 保存
	 * @param Dispatch
	 * @return void
	 */
	public void save(TicketorderTicket ticketorderTicket);
	
	/**
	 * 每个票号一个记录 删除
	 * @param Dispatch
	 * @return void
	 */
	public void delete(TicketorderTicket ticketorderTicket);
	
	/**
	 * 通过订单号查询所有的票号
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderTicket> findTicketList(String orderNo);
}
