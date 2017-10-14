/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.continent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.continent.entity.Continent;
import com.sml.sz.continent.dao.ContinentDao;

/**
 * 大区Service
 * @author 张权
 * @version 2016-03-11
 */
@Service
@Transactional(readOnly = true)
public class ContinentServiceImpl extends CrudService<ContinentDao, Continent> implements ContinentService{

	/**
	 * 大区 通过ID 获取
	 * @param String id
	 * @return Continent
	 */
	public Continent get(String id) {
		return super.get(id);
	}
	
	/**
	 * 大区 查询不分页
	 * @param Continent
	 * @return List<Continent>
	 */
	public List<Continent> findList(Continent continent) {
		//先获取缓存中有没有数据
		Object continentInfoList = EhCacheUtils.get("continentInfoList");
		if(null==continentInfoList){
			//为空的话是走数据库查询值
			List<Continent> tbContinentInfoList = super.findList(continent);
			continentInfoList=tbContinentInfoList;
			EhCacheUtils.put("continentInfoList", tbContinentInfoList);
		}
		return (List<Continent>)continentInfoList;
	}
	
	/**
	 * 大区 查询分页
	 * @param Page<Continent> page,Continent
	 * @return Page<Continent>
	 */
	public Page<Continent> findPage(Page<Continent> page, Continent continent) {
		return super.findPage(page, continent);
	}
	
	/**
	 * 大区 保存
	 * @param Continent
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Continent continent) {
		super.save(continent);
		if(null != continent){
			EhCacheUtils.remove("continentInfoList");
		}
	}
	
	/**
	 * 大区 删除
	 * @param Continent
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Continent continent) {
		super.delete(continent);
		if(null != continent){
			EhCacheUtils.remove("continentInfoList");
		}
	}
	
}