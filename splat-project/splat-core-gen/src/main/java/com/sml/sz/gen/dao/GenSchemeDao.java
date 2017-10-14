/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.gen.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * @author splat
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {
	
}
