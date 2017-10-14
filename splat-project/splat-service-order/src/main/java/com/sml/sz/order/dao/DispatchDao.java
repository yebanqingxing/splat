/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.Dispatch;

/**
 * 行程单派送单DAO接口
 * @author 李千超
 * @version 2016-03-30
 */
@MyBatisDao
public interface DispatchDao extends CrudDao<Dispatch> {
	
}