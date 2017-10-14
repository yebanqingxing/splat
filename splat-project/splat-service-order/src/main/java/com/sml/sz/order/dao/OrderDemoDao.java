package com.sml.sz.order.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.OrderDemo;

/**
 * 示例功能DAO接口
 * @author 黄诗源
 * @version 2016-03-06
 */
@MyBatisDao
public interface OrderDemoDao extends CrudDao<OrderDemo> {
	
}
