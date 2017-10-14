package com.sml.sz.order.service;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.RefundsheetDetail;

/**
 * 退票主表
 * @author lqc
 *
 */
public interface RefundsheetDetailServiceFacade {
	/**
	 * 退票单 通过ID 获取
	 * @param String id
	 * @return RefundsheetDetail
	 */
	public RefundsheetDetail get(String id);
	
	/**
	 * 退票单 查询不分页
	 * @param RefundsheetDetail
	 * @return List<RefundsheetDetail>
	 */
	public List<RefundsheetDetail> findList(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 退票单 查询分页
	 * @param Page<RefundsheetDetail> page,RefundsheetDetail
	 * @return Page<RefundsheetDetail>
	 */
	public Page<RefundsheetDetail> findPage(Page<RefundsheetDetail> page, RefundsheetDetail refundsheetDetail);
	
	/**
	 * 退票单 保存
	 * @param RefundsheetDetail
	 * @return void
	 */
	public void save(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 退票单 删除
	 * @param RefundsheetDetail
	 * @return void
	 */
	public void delete(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 修改退票单的状态
	 * @param refundsheetStatus  要修改成的状态
	 * @param refundsheetNo 退票单单号
	 */
	public void updateRefundsheetStatus(RefundsheetDetail refundsheetDetail);
	
	
	/**
	 * 查询退票单的状态
	 * @return
	 */
	public Map<String,String> findRefundsheetStatusCount(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 通过订单号查询订单
	 * @param refundsheetNo
	 * @return
	 */
	public RefundsheetDetail findRefundsheetDetail(String refundsheetNo);
	
	/**
	 * 查询支付状态以及条数
	 * @param refundsheetDetail
	 * @return
	 */
	public Map<String,String> findPayStatusCount(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 查看操作状态以及条数
	 * @param refundsheetDetail
	 * @return
	 */
	public Map<String,String> findCurrentStatus(RefundsheetDetail refundsheetDetail);

}
