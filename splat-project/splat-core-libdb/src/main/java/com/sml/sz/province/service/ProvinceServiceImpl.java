/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.province.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.country.entity.Country;
import com.sml.sz.province.entity.Province;
import com.sml.sz.province.dao.ProvinceDao;

/**
 * 省份Service
 * @author 张权
 * @version 2016-03-11
 */
@Service
@Transactional(readOnly = true)
public class ProvinceServiceImpl extends CrudService<ProvinceDao, Province> implements ProvinceService{

	/**
	 * 省份 通过ID 获取
	 * @param String id
	 * @return Province
	 */
	public Province get(String id) {
		return super.get(id);
	}
	
	/**
	 * 省份 查询不分页
	 * @param Province
	 * @return List<Province>
	 */
	public List<Province> findList(Province province) {
		//先获取缓存中有没有数据
		Object provinceInfoList = EhCacheUtils.get("provinceInfoList");
		if(null==provinceInfoList){
			//为空的话是走数据库查询值
			List<Province> tbProvinceInfoList = super.findList(province);
			provinceInfoList=tbProvinceInfoList;
			EhCacheUtils.put("provinceInfoList", tbProvinceInfoList);
		}
		return (List<Province>)provinceInfoList;
	}
	
	/**
	 * 省份 查询分页
	 * @param Page<Province> page,Province
	 * @return Page<Province>
	 */
	public Page<Province> findPage(Page<Province> page, Province province) {
		return super.findPage(page, province);
	}
	
	/**
	 * 省份 保存
	 * @param Province
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Province province) {
		super.save(province);
		if(null != province){
			EhCacheUtils.remove("provinceInfoList");
		}
	}
	
	/**
	 * 省份 删除
	 * @param Province
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Province province) {
		super.delete(province);
		if(null != province){
			EhCacheUtils.remove("provinceInfoList");
		}
	}
	
}