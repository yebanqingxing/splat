package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.model.TicketorderSinglePriceModel;

public interface TicketorderSinglePriceServiceFacade {
	/**
	 * 机票订单单人价格（机票明细） 通过ID 获取
	 * @param String id
	 * @return TicketorderSinglePrice
	 */
	public TicketorderSinglePrice get(String id);
	
	/**
	 * 机票订单单人价格（机票明细） 查询不分页
	 * @param TicketorderSinglePrice
	 * @return List<TicketorderSinglePrice>
	 */
	public List<TicketorderSinglePrice> findList(TicketorderSinglePrice ticketorderSinglePrice);
	
	/**
	 * 机票订单单人价格（机票明细） 查询分页
	 * @param Page<TicketorderSinglePrice> page,TicketorderSinglePrice
	 * @return Page<TicketorderSinglePrice>
	 */
	public Page<TicketorderSinglePrice> findPage(Page<TicketorderSinglePrice> page, TicketorderSinglePrice ticketorderSinglePrice);
	
	/**
	 * 机票订单单人价格（机票明细） 保存
	 * @param TicketorderSinglePrice
	 * @return void
	 */
	public void save(TicketorderSinglePrice ticketorderSinglePrice);
	
	/**
	 * 机票订单单人价格（机票明细） 删除
	 * @param TicketorderSinglePrice
	 * @return void
	 */
	public void delete(TicketorderSinglePrice ticketorderSinglePrice);
	
	/**
	 * 机票订单单人价格（机票明细） 修改
	 * @param TicketorderSinglePrice
	 * @return void
	 */
	public void update(List<TicketorderSinglePrice> ticketorderSinglePrices);
	
	
	
	/**
	 * 获取机票明细
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderSinglePrice> findorderSinglePrice(String orderNo);

	
	/**
	 * 保存机票票费用的明细每个人一条记录
	 * @param price
	 * @param orderNum
	 */
	public void saveSinglePrice(TicketorderSinglePriceModel price, String orderNum);
}
