/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.pay.entity.PayDemo;

/**
 * 示例功能DAO接口
 * @author 黄诗源
 * @version 2016-03-06
 */
@MyBatisDao
public interface PayDemoDao extends CrudDao<PayDemo> {
	
}