/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.continent.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.continent.entity.Continent;

/**
 * 大区DAO接口
 * @author 张权
 * @version 2016-03-11
 */
@MyBatisDao
public interface ContinentDao extends CrudDao<Continent> {
	
}