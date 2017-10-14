/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.mileage.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.mileage.entity.Mileage;

/**
 * 里程DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface MileageDao extends CrudDao<Mileage> {
	
	public Mileage findMileage(String flyCode);
}