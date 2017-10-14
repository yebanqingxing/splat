package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.RefundsheetHistory;
import com.sml.sz.order.entity.VoidsheetHistory;

/**
 * 废票历史
 * @author lqc
 *
 */
public interface VoidsheetHistoryServiceFacade {

	/**
	 * 废票单历史 通过ID 获取
	 * @param String id
	 * @return VoidsheetHistory
	 */
	public VoidsheetHistory get(String id);
	
	/**
	 * 废票单历史 查询不分页
	 * @param VoidsheetHistory
	 * @return List<VoidsheetHistory>
	 */
	public List<VoidsheetHistory> findList(VoidsheetHistory voidsheetHistory);
	
	/**
	 * 废票单历史 查询分页
	 * @param Page<VoidsheetHistory> page,VoidsheetHistory
	 * @return Page<VoidsheetHistory>
	 */
	public Page<VoidsheetHistory> findPage(Page<VoidsheetHistory> page, VoidsheetHistory voidsheetHistory);
	
	/**
	 * 废票单历史 保存
	 * @param VoidsheetHistory
	 * @return void
	 */
	public void save(VoidsheetHistory voidsheetHistory);
	
	/**
	 * 废票单历史 删除
	 * @param VoidsheetHistory
	 * @return void
	 */
	public void delete(VoidsheetHistory voidsheetHistory);

	/**
	 * 查询历史记录
	 * @param voidsheetNo
	 * @return
	 */
	public List<VoidsheetHistory> findVoidsheetHistory(String voidsheetNo);
	
}
