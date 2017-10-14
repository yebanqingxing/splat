/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.city.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.city.entity.City;
import com.sml.sz.EhCacheUtils;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.city.dao.CityDao;

/**
 * 城市Service
 * @author 张权
 * @version 2016-03-11
 */
@Service
@Transactional(readOnly = true)
public class CityServiceImpl extends CrudService<CityDao, City> implements CityService{

	/**
	 * 城市 通过ID 获取
	 * @param String id
	 * @return City
	 */
	public City get(String id) {
		return super.get(id);
	}
	
	/**
	 * 城市 查询不分页
	 * @param City
	 * @return List<City>
	 */
	public List<City> findList(City city) {
		//先获取缓存中有没有数据
		Object cityInfoList = EhCacheUtils.get("cityInfoList");
		if(null==cityInfoList){
			//为空的话是走数据库查询值
			List<City> tbCityInfoList = super.findList(city);
			cityInfoList=tbCityInfoList;
			EhCacheUtils.put("cityInfoList", tbCityInfoList);
		}
		return (List<City>)cityInfoList;
	}
	
	/**
	 * 城市 查询分页
	 * @param Page<City> page,City
	 * @return Page<City>
	 */
	public Page<City> findPage(Page<City> page, City city) {
		return super.findPage(page, city);
	}
	
	/**
	 * 城市 保存
	 * @param City
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(City city) {
		super.save(city);
		if(null != city){
			EhCacheUtils.remove("cityInfoList");
		}
	}
	
	/**
	 * 城市 删除
	 * @param City
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(City city) {
		super.delete(city);
		if(null != city){
			EhCacheUtils.remove("cityInfoList");
		}
	}

	public String getComput(Rt rtPnr) {
		rtPnr.getRoute_list().get(0).getFlight();
		return "EK";
	}
	
}