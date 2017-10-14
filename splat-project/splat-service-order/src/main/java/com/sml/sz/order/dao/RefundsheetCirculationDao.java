/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.RefundsheetCirculation;

/**
 * 退票单流转DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface RefundsheetCirculationDao extends CrudDao<RefundsheetCirculation> {
	
}