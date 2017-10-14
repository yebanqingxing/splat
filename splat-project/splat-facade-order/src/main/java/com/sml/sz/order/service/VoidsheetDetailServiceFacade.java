package com.sml.sz.order.service;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetStatusCount;

/**
 * 废票单表
 * @author lqc
 *
 */
public interface VoidsheetDetailServiceFacade {
	/**
	 * 废票单表 通过ID 获取
	 * @param String id
	 * @return VoidsheetDetail
	 */
	public VoidsheetDetail get(String id);
	
	/**
	 * 废票单表 查询不分页
	 * @param VoidsheetDetail
	 * @return List<VoidsheetDetail>
	 */
	public List<VoidsheetDetail> findList(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 废票单表 查询分页
	 * @param Page<VoidsheetDetail> page,VoidsheetDetail
	 * @return Page<VoidsheetDetail>
	 */
	public Page<VoidsheetDetail> findPage(Page<VoidsheetDetail> page, VoidsheetDetail voidsheetDetail);
	
	/**
	 * 废票单表 保存
	 * @param VoidsheetDetail
	 * @return void
	 */
	public void save(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 废票单表 删除
	 * @param VoidsheetDetail
	 * @return void
	 */
	public void delete(VoidsheetDetail voidsheetDetail);
	
	
	
	/**
	 * 查询状态的条数
	 * @return
	 */
	public Map<String,String> findVoidsheetStatusCount(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 查询支付状态条数
	 * @param voidsheetDetail
	 * @return
	 */
	public Map<String,String> findPayStatusCount(VoidsheetDetail voidsheetDetail);
	
	/**
	 * 查询操作状态条数
	 * @param voidsheetDetail
	 * @return
	 */
	public Map<String,String> findCurrentStatus(VoidsheetDetail voidsheetDetail);
	
	
	/**
	 * 查询废票单信息
	 * @param voidsheetNo
	 * @return
	 */
	public VoidsheetDetail findVoidsheetDetail(String voidsheetNo);
	/**
	 * 修改废票单的状态
	 * @param map
	 */
	public void updateVoidsheetStatus(String voidsheetStatus,String voidsheetNo);
	
	/**
	 * 修改操作状态
	 * @param currentStatus
	 * @param voidsheetNo
	 */
	public void updateCurrentStatus(String currentStatus,String voidsheetNo);


	/**
	 * 供应商修改机票的价格
	 * @param SupplierTotSettlementPrice
	 * @param orderNo
	 */
	public void updateSupplierTotSettlementPrice(String SupplierTotSettlementPrice,String orderNo);

	/**
	 * 供应商确认废票以后修改支付状态
	 * @param payStatus
	 * @param voidsheetNo
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void updatePayStatus(String payStatus, String voidsheetNo);


}
