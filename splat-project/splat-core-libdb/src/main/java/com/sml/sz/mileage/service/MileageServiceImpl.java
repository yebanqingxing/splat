/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.mileage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.country.entity.Country;
import com.sml.sz.mileage.entity.Mileage;
import com.sml.sz.mileage.dao.MileageDao;

/**
 * 里程Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service
@Transactional(readOnly = true)
public class MileageServiceImpl extends CrudService<MileageDao, Mileage> {

	/**
	 * 里程 通过ID 获取
	 * @param String id
	 * @return Mileage
	 */
	public Mileage get(String id) {
		return super.get(id);
	}
	
	/**
	 * 里程 查询不分页
	 * @param Mileage
	 * @return List<Mileage>
	 */
	public List<Mileage> findList(Mileage mileage) {
		//先获取缓存中有没有数据
		Object mileageInfoList = EhCacheUtils.get("mileageInfoList");
		if(null==mileageInfoList){
			//为空的话是走数据库查询值
			List<Mileage> tbMileageInfoList = super.findList(mileage);
			mileageInfoList=tbMileageInfoList;
			EhCacheUtils.put("mileageInfoList", tbMileageInfoList);
		}
		return (List<Mileage>)mileageInfoList;
	}
	
	/**
	 * 里程 查询分页
	 * @param Page<Mileage> page,Mileage
	 * @return Page<Mileage>
	 */
	public Page<Mileage> findPage(Page<Mileage> page, Mileage mileage) {
		return super.findPage(page, mileage);
	}
	
	/**
	 * 里程 保存
	 * @param Mileage
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Mileage mileage) {
		super.save(mileage);
		if(null != mileage){
			EhCacheUtils.remove("mileageInfoList");
		}
	}
	
	/**
	 * 里程 删除
	 * @param Mileage
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Mileage mileage) {
		super.delete(mileage);
		if(null != mileage){
			EhCacheUtils.remove("mileageInfoList");
		}
	}
	
}