/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.EndorsesheetHistory;

/**
 * 改签历史DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface EndorsesheetHistoryDao extends CrudDao<EndorsesheetHistory> {
	
	/**
	 * 历史记录
	 * @param endorsesheetNo
	 * @return
	 */
	public List<EndorsesheetHistory> findEndorsesheetHistory(String endorsesheetNo);
}