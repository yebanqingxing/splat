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
import com.sml.sz.order.dao.RefundsheetHistoryDao;
import com.sml.sz.order.entity.RefundsheetHistory;

/**
 * 退票单历史表Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="refundsheetHistoryServiceFacade")
@Transactional(readOnly = true)
public class RefundsheetHistoryService extends CrudService<RefundsheetHistoryDao, RefundsheetHistory> implements RefundsheetHistoryServiceFacade {

	@Autowired
	RefundsheetHistoryDao refundsheetHistoryDao;
	/**
	 * 退票单历史表 通过ID 获取
	 * @param String id
	 * @return RefundsheetHistory
	 */
	public RefundsheetHistory get(String id) {
		return super.get(id);
	}
	
	/**
	 * 退票单历史表 查询不分页
	 * @param RefundsheetHistory
	 * @return List<RefundsheetHistory>
	 */
	public List<RefundsheetHistory> findList(RefundsheetHistory refundsheetHistory) {
		return super.findList(refundsheetHistory);
	}
	
	/**
	 * 退票单历史表 查询分页
	 * @param Page<RefundsheetHistory> page,RefundsheetHistory
	 * @return Page<RefundsheetHistory>
	 */
	public Page<RefundsheetHistory> findPage(Page<RefundsheetHistory> page, RefundsheetHistory refundsheetHistory) {
		return super.findPage(page, refundsheetHistory);
	}
	
	/**
	 * 退票单历史表 保存
	 * @param RefundsheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(RefundsheetHistory refundsheetHistory) {
		super.save(refundsheetHistory);
	}
	
	/**
	 * 退票单历史表 删除
	 * @param RefundsheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(RefundsheetHistory refundsheetHistory) {
		super.delete(refundsheetHistory);
	}

	/**
	 * 查询历史记录
	 */
	public List<RefundsheetHistory> findRefundsheetHistory(String refundsheetNo) {
		return refundsheetHistoryDao.findRefundsheetHistory(refundsheetNo);
	}
	
}