/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderPnr;

/**
 * 机票订单pnrDAO接口
 * @author 李千超
 * @version 2016-03-10
 */
@MyBatisDao
public interface TicketorderPnrDao extends CrudDao<TicketorderPnr> {
	
}