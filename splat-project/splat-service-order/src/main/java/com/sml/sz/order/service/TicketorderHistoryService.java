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
import com.sml.sz.order.dao.TicketorderHistoryDao;
import com.sml.sz.order.entity.TicketorderHistory;

/**
 * 机票订单历史Service
 * @author 李千超
 * @version 2016-03-10
 */
@Service(value="ticketorderHistoryFacade")
@Transactional(readOnly = true)
public class TicketorderHistoryService extends CrudService<TicketorderHistoryDao, TicketorderHistory> implements TicketorderHistoryFacade {

	@Autowired
	TicketorderHistoryDao ticketorderHistoryDao;
	
	/**
	 * 机票订单历史 通过ID 获取
	 * @param String id
	 * @return TbTicketorderHistory
	 */
	public TicketorderHistory get(String id) {
		return super.get(id);
	}
	
	/**
	 * 机票订单历史 查询不分页
	 * @param TicketorderHistory
	 * @return List<TbTicketorderHistory>
	 */
	public List<TicketorderHistory> findList(TicketorderHistory tbTicketorderHistory) {
		return super.findList(tbTicketorderHistory);
	}
	
	/**
	 * 机票订单历史 查询分页
	 * @param Page<TbTicketorderHistory> page,TbTicketorderHistory
	 * @return Page<TbTicketorderHistory>
	 */
	public Page<TicketorderHistory> findPage(Page<TicketorderHistory> page, TicketorderHistory tbTicketorderHistory) {
		return super.findPage(page, tbTicketorderHistory);
	}
	
	/**
	 * 机票订单历史 保存
	 * @param TicketorderHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderHistory tbTicketorderHistory) {
		super.save(tbTicketorderHistory);
	}
	
	/**
	 * 机票订单历史 删除
	 * @param TicketorderHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderHistory tbTicketorderHistory) {
		super.delete(tbTicketorderHistory);
	}

	/**
	 * 查询历史记录
	 */
	public List<TicketorderHistory> findTicketorderHistory(String orderNo) {
		return ticketorderHistoryDao.findTicketorderHistory(orderNo);
	}
	
}