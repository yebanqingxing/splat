package com.sml.sz.order.service;

import java.util.List;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.EndorsesheetHistory;

/**
 * 改签历史
 * @author lqc
 */
public interface EndorsesheetHistoryServiceFacade {
	/**
	 * 改签历史 通过ID 获取
	 * @param String id
	 * @return EndorsesheetHistory
	 */
	public EndorsesheetHistory get(String id) ;
	
	/**
	 * 改签历史 查询不分页
	 * @param EndorsesheetHistory
	 * @return List<EndorsesheetHistory>
	 */
	public List<EndorsesheetHistory> findList(EndorsesheetHistory endorsesheetHistory);
	
	/**
	 * 改签历史 查询分页
	 * @param Page<EndorsesheetHistory> page,EndorsesheetHistory
	 * @return Page<EndorsesheetHistory>
	 */
	public Page<EndorsesheetHistory> findPage(Page<EndorsesheetHistory> page, EndorsesheetHistory endorsesheetHistory);
	
	/**
	 * 改签历史 保存
	 * @param EndorsesheetHistory
	 * @return void
	 */
	 
	public void save(EndorsesheetHistory endorsesheetHistory);
	
	/**
	 * 改签历史 删除
	 * @param EndorsesheetHistory
	 * @return void
	 */
	 
	public void delete(EndorsesheetHistory endorsesheetHistory) ;
	
	/**
	 * 历史记录
	 * @param endorsesheetNo
	 * @return
	 */
	public List<EndorsesheetHistory> findEndorsesheetHistory(String endorsesheetNo);
}
