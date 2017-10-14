/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.VoidsheetHistory;

/**
 * 废票单历史DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface VoidsheetHistoryDao extends CrudDao<VoidsheetHistory> {
	
	/**
	 * 查询历史记录
	 * @param voidsheetNo
	 * @return
	 */
	public List<VoidsheetHistory> findVoidsheetHistory(String voidsheetNo);
	
}