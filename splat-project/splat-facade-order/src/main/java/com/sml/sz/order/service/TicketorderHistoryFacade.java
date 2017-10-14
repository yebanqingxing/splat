package com.sml.sz.order.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderHistory;

public interface TicketorderHistoryFacade {
	/**
	 * 机票订单历史 通过ID 获取
	 * @param String id
	 * @return TbTicketorderHistory
	 */
	public TicketorderHistory get(String id);
	
	/**
	 * 机票订单历史 查询不分页
	 * @param TicketorderHistory
	 * @return List<TbTicketorderHistory>
	 */
	public List<TicketorderHistory> findList(TicketorderHistory tbTicketorderHistory) ;
	
	/**
	 * 机票订单历史 查询分页
	 * @param Page<TbTicketorderHistory> page,TbTicketorderHistory
	 * @return Page<TbTicketorderHistory>
	 */
	public Page<TicketorderHistory> findPage(Page<TicketorderHistory> page, TicketorderHistory tbTicketorderHistory);
	
	/**
	 * 机票订单历史 保存
	 * @param TicketorderHistory
	 * @return void
	 */
	 
	public void save(TicketorderHistory tbTicketorderHistory);
	
	/**
	 * 机票订单历史 删除
	 * @param TicketorderHistory
	 * @return void
	 */
	 
	public void delete(TicketorderHistory tbTicketorderHistory);

	/**
	 * 查询历史记录
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderHistory> findTicketorderHistory(String orderNo);

}
