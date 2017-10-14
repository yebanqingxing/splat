/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.entity.VoidsheetCirculation;
import com.sml.sz.order.dao.VoidsheetCirculationDao;

/**
 * 废票单流转Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="voidsheetCirculationServiceFacade")
@Transactional(readOnly = true)
public class VoidsheetCirculationService extends CrudService<VoidsheetCirculationDao, VoidsheetCirculation> implements VoidsheetCirculationServiceFacade{

	@Autowired
	VoidsheetCirculationDao voidsheetCirculationDao;
	/**
	 * 废票单流转 通过ID 获取
	 * @param String id
	 * @return VoidsheetCirculation
	 */
	public VoidsheetCirculation get(String id) {
		return super.get(id);
	}
	
	/**
	 * 废票单流转 查询不分页
	 * @param VoidsheetCirculation
	 * @return List<VoidsheetCirculation>
	 */
	public List<VoidsheetCirculation> findList(VoidsheetCirculation voidsheetCirculation) {
		return super.findList(voidsheetCirculation);
	}
	
	/**
	 * 废票单流转 查询分页
	 * @param Page<VoidsheetCirculation> page,VoidsheetCirculation
	 * @return Page<VoidsheetCirculation>
	 */
	public Page<VoidsheetCirculation> findPage(Page<VoidsheetCirculation> page, VoidsheetCirculation voidsheetCirculation) {
		return super.findPage(page, voidsheetCirculation);
	}
	
	/**
	 * 废票单流转 保存
	 * @param VoidsheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(VoidsheetCirculation voidsheetCirculation) {
		super.save(voidsheetCirculation);
	}
	
	/**
	 * 废票单流转 删除
	 * @param VoidsheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(VoidsheetCirculation voidsheetCirculation) {
		super.delete(voidsheetCirculation);
	}
	
}