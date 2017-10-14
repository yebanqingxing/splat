/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.RefundsheetHistory;

/**
 * 退票单历史表DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface RefundsheetHistoryDao extends CrudDao<RefundsheetHistory> {
	
	/**
	 * 查询历史记录
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetHistory> findRefundsheetHistory(String refundsheetNo);
}