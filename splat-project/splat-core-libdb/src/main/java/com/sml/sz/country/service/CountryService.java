package com.sml.sz.country.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.country.entity.Country;

public interface CountryService {

	/**
	 * 国家 通过ID 获取
	 * @param String id
	 * @return Country
	 */
	public Country get(String id);
	
	/**
	 * 国家 查询不分页
	 * @param Country
	 * @return List<Country>
	 */
	public List<Country> findList(Country country);
	
	/**
	 * 国家 查询分页
	 * @param Page<Country> page,Country
	 * @return Page<Country>
	 */
	public Page<Country> findPage(Page<Country> page, Country country);
	
	/**
	 * 国家 保存
	 * @param Country
	 * @return void
	 */
	public void save(Country country);
	
	/**
	 * 国家 删除
	 * @param Country
	 * @return void
	 */
	public void delete(Country country);
	
}
