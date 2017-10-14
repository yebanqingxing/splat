/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.test.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.test.entity.Test;

/**
 * 测试DAO接口
 * @author splat
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {
	
}
