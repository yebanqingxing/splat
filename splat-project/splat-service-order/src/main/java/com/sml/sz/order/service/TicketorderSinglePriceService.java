/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.model.TicketorderSinglePriceModel;
import com.sml.sz.order.dao.TicketorderSinglePriceDao;

/**
 * 机票订单单人价格（机票明细）Service
 * @author 李千超
 * @version 2016-03-25
 */
@Service(value="ticketorderSinglePriceServiceFacade")
public class TicketorderSinglePriceService extends CrudService<TicketorderSinglePriceDao, TicketorderSinglePrice> implements TicketorderSinglePriceServiceFacade{

	@Autowired
	TicketorderSinglePriceDao ticketorderSinglePriceDao;
	/**
	 * 机票订单单人价格（机票明细） 通过ID 获取
	 * @param String id
	 * @return TicketorderSinglePrice
	 */
	public TicketorderSinglePrice get(String id) {
		return super.get(id);
	}
	
	/**
	 * 机票订单单人价格（机票明细） 查询不分页
	 * @param TicketorderSinglePrice
	 * @return List<TicketorderSinglePrice>
	 */
	public List<TicketorderSinglePrice> findList(TicketorderSinglePrice ticketorderSinglePrice) {
		return super.findList(ticketorderSinglePrice);
	}
	
	/**
	 * 机票订单单人价格（机票明细） 查询分页
	 * @param Page<TicketorderSinglePrice> page,TicketorderSinglePrice
	 * @return Page<TicketorderSinglePrice>
	 */
	public Page<TicketorderSinglePrice> findPage(Page<TicketorderSinglePrice> page, TicketorderSinglePrice ticketorderSinglePrice) {
		return super.findPage(page, ticketorderSinglePrice);
	}
	
	/**
	 * 机票订单单人价格（机票明细） 保存
	 * @param TicketorderSinglePrice
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderSinglePrice ticketorderSinglePrice) {
		super.save(ticketorderSinglePrice);
	}
	
	/**
	 * 机票订单单人价格（机票明细） 删除
	 * @param TicketorderSinglePrice
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderSinglePrice ticketorderSinglePrice) {
		super.delete(ticketorderSinglePrice);
	}

	/**
	 * 机票明细查询啊
	 */
	public List<TicketorderSinglePrice> findorderSinglePrice(String orderNo) {
		
		return ticketorderSinglePriceDao.findorderSinglePrice(orderNo);
	}

	/**
	 * 保存机票费用明细 每个人一条记录
	 */
	@Transactional(readOnly = false)
	public void saveSinglePrice(TicketorderSinglePriceModel price, String orderNum) {
		List<TicketorderSinglePrice> singlePrice=price.getTicketorderSinglePrices();
		if(null != singlePrice){
		for (int i = 0; i < singlePrice.size(); i++) {
			TicketorderSinglePrice pricreInfo=singlePrice.get(i);
			pricreInfo.setOrderNo(orderNum);
			//订单号
			super.save(pricreInfo);
			
		}
		}
	}
	@Transactional(readOnly = false)
	public void update(List<TicketorderSinglePrice> ticketorderSinglePrices) {
		// TODO Auto-generated method stub
		if(ticketorderSinglePrices!=null){
			for (TicketorderSinglePrice ticketorderSinglePrice : ticketorderSinglePrices) {
				 ticketorderSinglePriceDao.update(ticketorderSinglePrice);
			}
		}
		
	}
	
	/**
	 * 通过旅客的id的字符串将旅客的信息取出来
	 * @param passIds
	 * @return
	 * @auth 李千超
	 * @date 2016年4月26日
	 * 作用：
	 */
	public List<TicketorderSinglePrice> findSinglePriceByPssIds(String passIds){
		String[] idArr=passIds.split(",");
		List<Integer> idIntegers=new ArrayList<Integer>();
		for (int i = 0; i < idArr.length; i++) {
			idIntegers.add(Integer.parseInt(idArr[i].trim()));
		}
		return ticketorderSinglePriceDao.findSinglePriceByPssIds(idIntegers);
	}
	
}