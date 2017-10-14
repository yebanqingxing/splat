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
import com.sml.sz.order.entity.RefundsheetCirculation;
import com.sml.sz.order.dao.RefundsheetCirculationDao;

/**
 * 退票单流转Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="refundsheetCirculationServiceFacade")
@Transactional(readOnly = true)
public class RefundsheetCirculationService extends CrudService<RefundsheetCirculationDao, RefundsheetCirculation> implements RefundsheetCirculationServiceFacade{

	@Autowired
	RefundsheetCirculationDao refundsheetCirculationDao;
	/**
	 * 退票单流转 通过ID 获取
	 * @param String id
	 * @return RefundsheetCirculation
	 */
	public RefundsheetCirculation get(String id) {
		return super.get(id);
	}
	
	/**
	 * 退票单流转 查询不分页
	 * @param RefundsheetCirculation
	 * @return List<RefundsheetCirculation>
	 */
	public List<RefundsheetCirculation> findList(RefundsheetCirculation refundsheetCirculation) {
		return super.findList(refundsheetCirculation);
	}
	
	/**
	 * 退票单流转 查询分页
	 * @param Page<RefundsheetCirculation> page,RefundsheetCirculation
	 * @return Page<RefundsheetCirculation>
	 */
	public Page<RefundsheetCirculation> findPage(Page<RefundsheetCirculation> page, RefundsheetCirculation refundsheetCirculation) {
		return super.findPage(page, refundsheetCirculation);
	}
	
	/**
	 * 退票单流转 保存
	 * @param RefundsheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(RefundsheetCirculation refundsheetCirculation) {
		super.save(refundsheetCirculation);
	}
	
	/**
	 * 退票单流转 删除
	 * @param RefundsheetCirculation
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(RefundsheetCirculation refundsheetCirculation) {
		super.delete(refundsheetCirculation);
	}
	
}