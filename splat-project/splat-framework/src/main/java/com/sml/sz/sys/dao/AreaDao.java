/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.dao;

import com.sml.sz.common.persistence.TreeDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.sys.entity.Area;

/**
 * 区域DAO接口
 * @author splat
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
