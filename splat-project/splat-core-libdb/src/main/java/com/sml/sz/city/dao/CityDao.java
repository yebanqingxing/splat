/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.city.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.city.entity.City;

/**
 * 城市DAO接口
 * @author 张权
 * @version 2016-03-11
 */
@MyBatisDao
public interface CityDao extends CrudDao<City> {
	public City findCityIataAreaCd(String cityCode);
	
	public City getComput(String CountryCode);
}