/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.TicketorderTicketDao;
import com.sml.sz.order.entity.TicketorderTicket;

/**
 * 每个票号一个记录Service
 * @author 李千超
 * @version 2016-03-30
 */
@Service(value="ticketorderTicketServiceFacade")
@Transactional(readOnly = true)
public class TicketorderTicketService extends CrudService<TicketorderTicketDao, TicketorderTicket> implements TicketorderTicketServiceFacade{

	@Autowired
	 TicketorderTicketDao  ticketorderTicketDao;
	/**
	 * 每个票号一个记录 通过ID 获取
	 * @param String id
	 * @return Dispatch
	 */
	public TicketorderTicket get(String id) {
		return super.get(id);
	}
	
	/**
	 * 每个票号一个记录 查询不分页
	 * @param Dispatch
	 * @return List<Dispatch>
	 */
	public List<TicketorderTicket> findList(TicketorderTicket ticketorderTicket) {
		return super.findList(ticketorderTicket);
	}
	
	/**
	 * 每个票号一个记录 查询分页
	 * @param Page<Dispatch> page,Dispatch
	 * @return Page<Dispatch>
	 */
	public Page<TicketorderTicket> findPage(Page<TicketorderTicket> page, TicketorderTicket ticketorderTicket) {
		return super.findPage(page, ticketorderTicket);
	}
	
	/**
	 * 每个票号一个记录 保存
	 * @param Dispatch
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderTicket ticketorderTicket) {
		super.save(ticketorderTicket);
	}
	
	/**
	 * 每个票号一个记录 删除
	 * @param Dispatch
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderTicket ticketorderTicket) {
		super.delete(ticketorderTicket);
	}

	/**
	 * 通过订单号查询所有的票号
	 */
	public List<TicketorderTicket> findTicketList(String orderNo) {
		
		return ticketorderTicketDao.findTicketList(orderNo);
	}
	
}