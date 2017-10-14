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
import com.sml.sz.order.entity.EndorsesheetCirculation;
import com.sml.sz.order.dao.EndorsesheetCirculationDao;

/**
 * 该签单流转Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="endorsesheetCirculationServiceFacade")
@Transactional(readOnly = true)
public class EndorsesheetCirculationService extends CrudService<EndorsesheetCirculationDao, EndorsesheetCirculation> implements EndorsesheetCirculationServiceFacade{

	@Autowired
	EndorsesheetCirculationDao endorsesheetCirculationDao;
	/**
	 * 该签单流转 通过ID 获取
	 * @param String id
	 * @return EndorsesheetCirculation
	 */
	public EndorsesheetCirculation get(String id) {
		return super.get(id);
	}
	
	/**
	 * 该签单流转 查询不分页
	 * @param EndorsesheetCirculation
	 * @return List<EndorsesheetCirculation>
	 */
	public List<EndorsesheetCirculation> findList(EndorsesheetCirculation endorsesheetCirculation) {
		return super.findList(endorsesheetCirculation);
	}
	
	/**
	 * 该签单流转 查询分页
	 * @param Page<EndorsesheetCirculation> page,EndorsesheetCirculation
	 * @return Page<EndorsesheetCirculation>
	 */
	public Page<EndorsesheetCirculation> findPage(Page<EndorsesheetCirculation> page, EndorsesheetCirculation endorsesheetCirculation) {
		return super.findPage(page, endorsesheetCirculation);
	}
	
	/**
	 * 该签单流转 保存
	 * @param EndorsesheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(EndorsesheetCirculation endorsesheetCirculation) {
		super.save(endorsesheetCirculation);
	}
	
	/**
	 * 该签单流转 删除
	 * @param EndorsesheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(EndorsesheetCirculation endorsesheetCirculation) {
		super.delete(endorsesheetCirculation);
	}
	
}