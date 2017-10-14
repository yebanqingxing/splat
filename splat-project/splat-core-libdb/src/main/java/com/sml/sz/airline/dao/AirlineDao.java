/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airline.dao;

import com.sml.sz.airline.entity.Airline;
import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;

/**
 * 航空公司查询DAO接口
 * @author 冯俊伟
 * @version 2016-03-14
 */
@MyBatisDao
public interface AirlineDao extends CrudDao<Airline> {
	/**
	 * 
	 * @param airCode
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月15日
	 * 作用：通过二字码查询实体
	 */
	public Airline getAirByAirCode(String airCode);
}