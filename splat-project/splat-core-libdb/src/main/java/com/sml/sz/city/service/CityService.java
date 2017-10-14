package com.sml.sz.city.service;

import java.util.List;


import com.sml.sz.city.entity.City;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.sys.pnr.Rt;

public interface CityService {

	
	/**
	 * 城市 通过ID 获取
	 * @param String id
	 * @return City
	 */
	public City get(String id);
	
	/**
	 * 城市 查询不分页
	 * @param City
	 * @return List<City>
	 */
	public List<City> findList(City city);
	
	/**
	 * 城市 查询分页
	 * @param Page<City> page,City
	 * @return Page<City>
	 */
	public Page<City> findPage(Page<City> page, City city);
	
	/**
	 * 城市 保存
	 * @param City
	 * @return void
	 */
	public void save(City city);
	
	/**
	 * 城市 删除
	 * @param City
	 * @return void
	 */
	public void delete(City city);
	
	public String getComput(Rt rtPnr);
	
}
