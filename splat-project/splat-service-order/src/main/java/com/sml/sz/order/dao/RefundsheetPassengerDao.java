/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.RefundsheetPassenger;

/**
 * 退票担任信息DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface RefundsheetPassengerDao extends CrudDao<RefundsheetPassenger> {
	/**
	 * 通过订单来查询旅客信息
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetPassenger> findRefundsheetPassenger(String refundsheetNo);
}