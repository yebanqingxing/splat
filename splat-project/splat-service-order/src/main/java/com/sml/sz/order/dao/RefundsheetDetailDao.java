/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetStatusCount;

/**
 * 退票单DAO接口
 * @author 李千超
 * @version 2016-03-14
 */
@MyBatisDao
public interface RefundsheetDetailDao extends CrudDao<RefundsheetDetail> {
	
	/**
	 * 修改退票单的状态
	 * @param map
	 */
	public void updateRefundsheetStatus(Map map);
	
	/**
	 * 查询退票单的状态条数
	 * @return
	 */
	public List<RefundsheetStatusCount> findRefundsheetStatusCount(RefundsheetDetail refundsheetDetail);
	
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
	public List<PayStatusCount> findPayStatusCount(RefundsheetDetail refundsheetDetail);
	
	/**
	 * 查看操作状态以及条数
	 * @param refundsheetDetail
	 * @return
	 */
	public List<CurrentStatus> findCurrentStatusCount(RefundsheetDetail refundsheetDetail);
}