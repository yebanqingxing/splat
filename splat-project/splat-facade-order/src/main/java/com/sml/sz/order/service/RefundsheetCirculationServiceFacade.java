package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.RefundsheetCirculation;

/**
 * 退票单流转
 * @author lqc
 *
 */
public interface RefundsheetCirculationServiceFacade {
	/**
	 * 退票单流转 通过ID 获取
	 * @param String id
	 * @return RefundsheetCirculation
	 */
	public RefundsheetCirculation get(String id);

	
	/**
	 * 退票单流转 查询不分页
	 * @param RefundsheetCirculation
	 * @return List<RefundsheetCirculation>
	 */
	public List<RefundsheetCirculation> findList(RefundsheetCirculation refundsheetCirculation);
	
	/**
	 * 退票单流转 查询分页
	 * @param Page<RefundsheetCirculation> page,RefundsheetCirculation
	 * @return Page<RefundsheetCirculation>
	 */
	public Page<RefundsheetCirculation> findPage(Page<RefundsheetCirculation> page, RefundsheetCirculation refundsheetCirculation);
	
	/**
	 * 退票单流转 保存
	 * @param RefundsheetCirculation
	 * @return void
	 */
	public void save(RefundsheetCirculation refundsheetCirculation);
	
	/**
	 * 退票单流转 删除
	 * @param RefundsheetCirculation
	 * @return void
	 */
	public void delete(RefundsheetCirculation refundsheetCirculation);
}
