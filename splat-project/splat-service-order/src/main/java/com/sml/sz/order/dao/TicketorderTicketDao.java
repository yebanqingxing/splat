/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;


import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderTicket;

/**
 * 票号DAO接口
 * @author 李千超
 * @version 2016-03-24
 */
@MyBatisDao
public interface TicketorderTicketDao extends CrudDao<TicketorderTicket> {
	
	/**
	 * 通过订单号查询所有的票号
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderTicket> findTicketList(String orderNo);
}