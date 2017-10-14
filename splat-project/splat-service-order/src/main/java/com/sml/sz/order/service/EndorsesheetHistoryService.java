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
import com.sml.sz.order.entity.EndorsesheetHistory;
import com.sml.sz.order.dao.EndorsesheetHistoryDao;

/**
 * 改签历史Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="endorsesheetHistoryServiceFacade")
@Transactional(readOnly = true)
public class EndorsesheetHistoryService extends CrudService<EndorsesheetHistoryDao, EndorsesheetHistory> implements EndorsesheetHistoryServiceFacade{

	@Autowired
	EndorsesheetHistoryDao endorsesheetHistoryDao;
	
	/**
	 * 改签历史 通过ID 获取
	 * @param String id
	 * @return EndorsesheetHistory
	 */
	public EndorsesheetHistory get(String id) {
		return super.get(id);
	}
	
	/**
	 * 改签历史 查询不分页
	 * @param EndorsesheetHistory
	 * @return List<EndorsesheetHistory>
	 */
	public List<EndorsesheetHistory> findList(EndorsesheetHistory endorsesheetHistory) {
		return super.findList(endorsesheetHistory);
	}
	
	/**
	 * 改签历史 查询分页
	 * @param Page<EndorsesheetHistory> page,EndorsesheetHistory
	 * @return Page<EndorsesheetHistory>
	 */
	public Page<EndorsesheetHistory> findPage(Page<EndorsesheetHistory> page, EndorsesheetHistory endorsesheetHistory) {
		return super.findPage(page, endorsesheetHistory);
	}
	
	/**
	 * 改签历史 保存
	 * @param EndorsesheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(EndorsesheetHistory endorsesheetHistory) {
		super.save(endorsesheetHistory);
	}
	
	/**
	 * 改签历史 删除
	 * @param EndorsesheetHistory
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(EndorsesheetHistory endorsesheetHistory) {
		super.delete(endorsesheetHistory);
	}

	/**
	 * 查询历史记录
	 */
	public List<EndorsesheetHistory> findEndorsesheetHistory(String endorsesheetNo) {
		return endorsesheetHistoryDao.findEndorsesheetHistory(endorsesheetNo);
	}
	
}