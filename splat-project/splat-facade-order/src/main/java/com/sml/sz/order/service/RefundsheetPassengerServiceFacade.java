package com.sml.sz.order.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.RefundsheetPassenger;

/**
 * 退票单人信息
 * @author lqc
 *
 */
public interface RefundsheetPassengerServiceFacade {
	/**
	 * 退票单人信息 通过ID 获取
	 * @param String id
	 * @return RefundsheetPassenger
	 */
	public RefundsheetPassenger get(String id) ;
	
	/**
	 * 退票单人信息 查询不分页
	 * @param RefundsheetPassenger
	 * @return List<RefundsheetPassenger>
	 */
	public List<RefundsheetPassenger> findList(RefundsheetPassenger refundsheetPassenger);
	
	/**
	 * 退票单人信息 查询分页
	 * @param Page<RefundsheetPassenger> page,RefundsheetPassenger
	 * @return Page<RefundsheetPassenger>
	 */
	public Page<RefundsheetPassenger> findPage(Page<RefundsheetPassenger> page, RefundsheetPassenger refundsheetPassenger);
	
	/**
	 * 退票单人信息 保存
	 * @param RefundsheetPassenger
	 * @return void
	 */
	 
	public void save(RefundsheetPassenger refundsheetPassenger);
	
	/**
	 * 退票单人信息 删除
	 * @param RefundsheetPassenger
	 * @return void
	 */
	public void delete(RefundsheetPassenger refundsheetPassenger);
	
	/**
	 * 退票单人信息 修改
	 * @param RefundsheetPassenger
	 * @return void
	 */
	public void update(List<RefundsheetPassenger> refundsheetPassenger);
	
	/**
	 * 通过订单来查询旅客信息
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetPassenger> findRefundsheetPassenger(String refundsheetNo);
}
