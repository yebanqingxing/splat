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
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetStatusCount;

/**
 * 废票单表DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface VoidsheetDetailDao extends CrudDao<VoidsheetDetail> {
	
	/**
	 * 修改废票单的状态
	 * @param map
	 */
	public void updateVoidsheetStatus(Map<String,String> map);
	
	
	/**
	 * 查询废票的状态的条数
	 * @return
	 */
	public List<VoidsheetStatusCount> findVoidsheetStatusCount(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 查询废票单信息
	 * @param voidsheetNo
	 * @return
	 */
	public VoidsheetDetail findVoidsheetDetail(String voidsheetNo);
	
	/**
	 * 查询支付状态条数
	 * @param voidsheetDetail
	 * @return
	 */
	public List<PayStatusCount> findPayStatusCount(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 查询操作状态条数
	 * @param voidsheetDetail
	 * @return
	 */
	public List<CurrentStatus> findCurrentStatus(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 修改操作状态
	 * @param currentStatus
	 * @param voidsheetNo
	 */
	public void updateCurrentStatus(Map map);
	/**
	 * 供应商修改机票的价格
	 * @param SupplierTotSettlementPrice
	 * @param orderNo
	 */
	public void updateSupplierTotSettlementPrice(Map map);


	/**
	 * 供应商确认废票以后修改支付状态
	 * @param map
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void updatePayStatus(Map<String, String> map);

}