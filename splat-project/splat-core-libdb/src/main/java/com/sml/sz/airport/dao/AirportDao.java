/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airport.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.airport.entity.Airport;

/**
 * 机场DAO接口
 * @author 张权
 * @version 2016-03-11
 */
@MyBatisDao
public interface AirportDao extends CrudDao<Airport> {
	/**
	 * 
	 * @param airportCode
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月15日
	 * 作用：根据三字节码查询实体
	 */
	public Airport getAirportByAirportCode(String airportCode);
	
	
}