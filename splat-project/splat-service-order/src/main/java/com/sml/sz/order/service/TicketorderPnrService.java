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
import com.sml.sz.order.dao.TicketorderPnrDao;
import com.sml.sz.order.entity.TicketorderPnr;

/**
 * 机票订单pnrService
 * @author 李千超
 * @version 2016-03-10
 */
@Service(value="ticketorderPnrServiceFacade")
@Transactional(readOnly = true)
public class TicketorderPnrService extends CrudService<TicketorderPnrDao, TicketorderPnr> implements TicketorderPnrServiceFacade {

	@Autowired
	TicketorderPnrDao ticketorderPnrDao;
	/**
	 * 机票订单pnr 通过ID 获取
	 * @param String id
	 * @return TicketorderPnr
	 */
	public TicketorderPnr get(String id) {
		return super.get(id);
	}
	
	/**
	 * 机票订单pnr 查询不分页
	 * @param TicketorderPnr
	 * @return List<TicketorderPnr>
	 */
	public List<TicketorderPnr> findList(TicketorderPnr ticketorderPnr) {
		return super.findList(ticketorderPnr);
	}
	
	/**
	 * 机票订单pnr 查询分页
	 * @param Page<TicketorderPnr> page,TicketorderPnr
	 * @return Page<TicketorderPnr>
	 */
	public Page<TicketorderPnr> findPage(Page<TicketorderPnr> page, TicketorderPnr ticketorderPnr) {
		return super.findPage(page, ticketorderPnr);
	}
	
	/**
	 * 机票订单pnr 保存
	 * @param TicketorderPnr
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderPnr ticketorderPnr) {
		super.save(ticketorderPnr);
	}
	
	/**
	 * 机票订单pnr 删除
	 * @param TicketorderPnr
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderPnr ticketorderPnr) {
		super.delete(ticketorderPnr);
	}
	
}