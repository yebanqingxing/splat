/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderHistory;

/**
 * 机票订单历史DAO接口
 * @author 李千超
 * @version 2016-03-10
 */
@MyBatisDao
public interface TicketorderHistoryDao extends CrudDao<TicketorderHistory> {
	
	/**
	 * 查询历史记录
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderHistory> findTicketorderHistory(String orderNo);
 }