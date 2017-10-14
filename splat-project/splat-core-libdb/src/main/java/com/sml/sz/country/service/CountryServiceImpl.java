/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.country.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.country.entity.Country;
import com.sml.sz.country.dao.CountryDao;

/**
 * 国家Service
 * @author 张权
 * @version 2016-03-11
 */
@Service
@Transactional(readOnly = true)
public class CountryServiceImpl extends CrudService<CountryDao, Country> implements CountryService{

	/**
	 * 国家 通过ID 获取
	 * @param String id
	 * @return Country
	 */
	public Country get(String id) {
		return super.get(id);
	}
	
	/**
	 * 国家 查询不分页
	 * @param Country
	 * @return List<Country>
	 */
	public List<Country> findList(Country country) {
		//先获取缓存中有没有数据
		Object countryInfoList = EhCacheUtils.get("countryInfoList");
		if(null==countryInfoList){
			//为空的话是走数据库查询值
			List<Country> tbCountryInfoList = super.findList(country);
			countryInfoList=tbCountryInfoList;
			EhCacheUtils.put("countryInfoList", tbCountryInfoList);
		}
		return (List<Country>)countryInfoList;
	}
	
	/**
	 * 国家 查询分页
	 * @param Page<Country> page,Country
	 * @return Page<Country>
	 */
	public Page<Country> findPage(Page<Country> page, Country country) {
		return super.findPage(page, country);
	}
	
	/**
	 * 国家 保存
	 * @param Country
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Country country) {
		super.save(country);
		if(null != country){
			EhCacheUtils.remove("countryInfoList");
		}
	}
	
	/**
	 * 国家 删除
	 * @param Country
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Country country) {
		super.delete(country);
		if(null != country){
			EhCacheUtils.remove("countryInfoList");
		}
	}
	
}