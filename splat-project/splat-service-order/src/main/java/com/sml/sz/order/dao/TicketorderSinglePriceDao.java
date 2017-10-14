/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderSinglePrice;

/**
 * 机票订单单人价格（机票明细）DAO接口
 * @author 李千超
 * @version 2016-03-25
 */
@MyBatisDao
public interface TicketorderSinglePriceDao extends CrudDao<TicketorderSinglePrice> {
	
	/**
	 * 获取机票明细
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderSinglePrice> findorderSinglePrice(String orderNo);
	
	
	/**
	 * 通过旅客的id集合将旅客的信息查出来
	 * @param ids
	 * @return
	 * @auth 李千超
	 * @date 2016年4月26日
	 * 作用：
	 */
	public List<TicketorderSinglePrice> findSinglePriceByPssIds(List<Integer> ids);
}