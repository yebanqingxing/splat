package com.sml.sz.province.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.province.entity.Province;

public interface ProvinceService {

	/**
	 * 省份 通过ID 获取
	 * @param String id
	 * @return Province
	 */
	public Province get(String id);
	
	/**
	 * 省份 查询不分页
	 * @param Province
	 * @return List<Province>
	 */
	public List<Province> findList(Province province);
	
	/**
	 * 省份 查询分页
	 * @param Page<Province> page,Province
	 * @return Page<Province>
	 */
	public Page<Province> findPage(Page<Province> page, Province province);
	
	/**
	 * 省份 保存
	 * @param Province
	 * @return void
	 */
	public void save(Province province);
	
	/**
	 * 省份 删除
	 * @param Province
	 * @return void
	 */
	public void delete(Province province);
}
