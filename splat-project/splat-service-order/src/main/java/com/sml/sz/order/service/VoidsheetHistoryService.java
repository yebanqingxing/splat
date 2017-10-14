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
import com.sml.sz.order.entity.RefundsheetHistory;
import com.sml.sz.order.entity.VoidsheetHistory;
import com.sml.sz.order.dao.VoidsheetHistoryDao;

/**
 * 废票单历史Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="voidsheetHistoryServiceFacade")
@Transactional(readOnly = true)
public class VoidsheetHistoryService extends CrudService<VoidsheetHistoryDao, VoidsheetHistory> implements VoidsheetHistoryServiceFacade{

	@Autowired
	VoidsheetHistoryDao voidsheetHistoryDao;
	/**
	 * 废票单历史 通过ID 获取
	 * @param String id
	 * @return VoidsheetHistory
	 */
	public VoidsheetHistory get(String id) {
		return super.get(id);
	}
	
	/**
	 * 废票单历史 查询不分页
	 * @param VoidsheetHistory
	 * @return List<VoidsheetHistory>
	 */
	public List<VoidsheetHistory> findList(VoidsheetHistory voidsheetHistory) {
		return super.findList(voidsheetHistory);
	}
	
	/**
	 * 废票单历史 查询分页
	 * @param Page<VoidsheetHistory> page,VoidsheetHistory
	 * @return Page<VoidsheetHistory>
	 */
	public Page<VoidsheetHistory> findPage(Page<VoidsheetHistory> page, VoidsheetHistory voidsheetHistory) {
		return super.findPage(page, voidsheetHistory);
	}
	
	/**
	 * 废票单历史 保存
	 * @param VoidsheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(VoidsheetHistory voidsheetHistory) {
		super.save(voidsheetHistory);
	}
	
	/**
	 * 废票单历史 删除
	 * @param VoidsheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(VoidsheetHistory voidsheetHistory) {
		super.delete(voidsheetHistory);
	}

	/**
	 * 查询历史
	 */
	public List<VoidsheetHistory> findVoidsheetHistory(String voidsheetNo) {
		return voidsheetHistoryDao.findVoidsheetHistory(voidsheetNo);
	}

	
	
}