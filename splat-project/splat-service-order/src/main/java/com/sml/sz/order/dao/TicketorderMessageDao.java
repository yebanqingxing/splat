/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPnr;

/**
 * 机票订单 消息 DAO接口
 * @author 李千超
 * @version 2016-03-10
 */
@MyBatisDao
public interface TicketorderMessageDao extends CrudDao<TicketorderMessage> {
	
	/**
	 * 通过订单号查询留言
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderMessage> findTicketorderMessage(String orderNo);
	
}