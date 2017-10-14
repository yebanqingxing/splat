package com.sml.sz.order.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.RefundsheetHistory;

/**
 * 退票单历史表
 * @author lqc
 *
 */
public interface RefundsheetHistoryServiceFacade {
	/**
	 * 退票单历史表 通过ID 获取
	 * @param String id
	 * @return RefundsheetHistory
	 */
	public RefundsheetHistory get(String id);
	
	/**
	 * 退票单历史表 查询不分页
	 * @param RefundsheetHistory
	 * @return List<RefundsheetHistory>
	 */
	public List<RefundsheetHistory> findList(RefundsheetHistory refundsheetHistory);
	
	/**
	 * 退票单历史表 查询分页
	 * @param Page<RefundsheetHistory> page,RefundsheetHistory
	 * @return Page<RefundsheetHistory>
	 */
	public Page<RefundsheetHistory> findPage(Page<RefundsheetHistory> page, RefundsheetHistory refundsheetHistory);
	
	/**
	 * 退票单历史表 保存
	 * @param RefundsheetHistory
	 * @return void
	 */
	 
	public void save(RefundsheetHistory refundsheetHistory);
	
	/**
	 * 退票单历史表 删除
	 * @param RefundsheetHistory
	 * @return void
	 */
	 
	public void delete(RefundsheetHistory refundsheetHistory);
	
	/**
	 * 查询历史记录
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetHistory> findRefundsheetHistory(String refundsheetNo);
}
