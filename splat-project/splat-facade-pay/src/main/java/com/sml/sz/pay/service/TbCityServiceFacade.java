/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.pay.entity.TbCity;


/**
 * 测试-功能描述Service
 * @author 测试-生成功能作者
 * @version 2016-03-09
 */

public interface TbCityServiceFacade {

	/**
	 * 测试-功能描述 通过ID 获取
	 * @param String id
	 * @return TbCity
	 */
	public TbCity get(String id);
	/**
	 * 测试-功能描述 查询不分页
	 * @param TbCity
	 * @return List<TbCity>
	 */
	public List<TbCity> findList(TbCity tbCity);
	
	/**
	 * 测试-功能描述 查询分页
	 * @param Page<TbCity> page,TbCity
	 * @return Page<TbCity>
	 */
	public Page<TbCity> findPage(Page<TbCity> page, TbCity tbCity);
	/**
	 * 测试-功能描述 保存
	 * @param TbCity
	 * @return void
	 */

	public void save(TbCity tbCity);
	
	/**
	 * 测试-功能描述 删除
	 * @param TbCity
	 * @return void
	 */

	public void delete(TbCity tbCity);
	
}