/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.province.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.province.entity.Province;

/**
 * 省份DAO接口
 * @author 张权
 * @version 2016-03-11
 */
@MyBatisDao
public interface ProvinceDao extends CrudDao<Province> {
	
}