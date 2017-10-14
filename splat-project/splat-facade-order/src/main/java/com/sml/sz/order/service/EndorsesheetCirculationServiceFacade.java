package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.EndorsesheetCirculation;

/**
 * 该签单流转Service
 * @author 李千超
 * @version 2016-03-15
 */
public interface EndorsesheetCirculationServiceFacade {
	/**
	 * 该签单流转 通过ID 获取
	 * @param String id
	 * @return EndorsesheetCirculation
	 */
	public EndorsesheetCirculation get(String id);
	
	/**
	 * 该签单流转 查询不分页
	 * @param EndorsesheetCirculation
	 * @return List<EndorsesheetCirculation>
	 */
	public List<EndorsesheetCirculation> findList(EndorsesheetCirculation endorsesheetCirculation);
	
	/**
	 * 该签单流转 查询分页
	 * @param Page<EndorsesheetCirculation> page,EndorsesheetCirculation
	 * @return Page<EndorsesheetCirculation>
	 */
	public Page<EndorsesheetCirculation> findPage(Page<EndorsesheetCirculation> page, EndorsesheetCirculation endorsesheetCirculation);
	
	/**
	 * 该签单流转 保存
	 * @param EndorsesheetCirculation
	 * @return void
	 */
	 
	public void save(EndorsesheetCirculation endorsesheetCirculation);
	
	/**
	 * 该签单流转 删除
	 * @param EndorsesheetCirculation
	 * @return void
	 */
	 
	public void delete(EndorsesheetCirculation endorsesheetCirculation);
}
