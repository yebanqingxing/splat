package com.sml.sz.order.service;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.DetailStatusCount;
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.PayStatusCount;

/**
 * 改签单
 * @author lqc
 *
 */
public interface EndorsesheetDetailServiceFacade {
	/**
	 * 改签单 通过ID 获取
	 * @param String id
	 * @return EndorsesheetDetail
	 */
	public EndorsesheetDetail get(String id);
	
	/**
	 * 改签单 查询不分页
	 * @param EndorsesheetDetail
	 * @return List<EndorsesheetDetail>
	 */
	public List<EndorsesheetDetail> findList(EndorsesheetDetail endorsesheetDetail) ;
	
	/**
	 * 改签单 查询分页
	 * @param Page<EndorsesheetDetail> page,EndorsesheetDetail
	 * @return Page<EndorsesheetDetail>
	 */
	public Page<EndorsesheetDetail> findPage(Page<EndorsesheetDetail> page, EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 改签单 保存
	 * @param EndorsesheetDetail
	 * @return void
	 */
	public void save(EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 改签单 删除
	 * @param EndorsesheetDetail
	 * @return void
	 */
	public void delete(EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 修改订单的状态
	 * @param endorseStatus 修改的订单状态
	 * @param endorsesheetNo 修改的订单号
	 */
	public void updateEndorseStatus(String endorseStatus,String endorsesheetNo);
	
	/**
	 * 根据改签单的类型不同查询相应的状态以及条数
	 * @return
	 */
	public Map<String,String> findEndorseDetailStatusCount(EndorsesheetDetail endorsesheetDetail);

	/**
	 * 修改支付的状态
	 * @param payStatus
	 * @param endorsesheetNo
	 */
	public void updatePayStatus(String payStatus,String endorsesheetNo);
	
	/**
	 * 修改废票状态
	 * @param voidStatus
	 * @param endorsesheetNo
	 */
	public void updateVoidStatus(String voidStatus,String  endorsesheetNo);
	
	/**
	 * 修改退票状态
	 * @param refundStatus
	 * @param endorsesheetNo
	 */
	public void updateRefundStatus(String refundStatus,String endorsesheetNo);

	/**
	 * 通过订单的id查询单个订单的状态
	 * @param endorsesheetNO
	 * @return
	 */
	public EndorsesheetDetail findEndorsesheetDetail(String endorsesheetNO);
	
	/**
	 * 查看支付常态
	 * @param endorsesheetDetail
	 * @return
	 */
	public Map<String,String> findPayStatusCount(EndorsesheetDetail endorsesheetDetail);

	/**
	 * 查询操作状态
	 * @param endorsesheetDetail
	 * @return
	 */
	public Map<String,String> findCurrentStatusCount(EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 
	 * @param endorsesheetDetail
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：供应商确认以后修改价格
	 */
	public void updateEndorsesheetDetail(EndorsesheetDetail endorsesheetDetail);

	/**
	 * 修改状态为通过
	 * @param hid_currentStatus
	 * @param hid_endorseNo
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：
	 */
	public void updateCurrnetStatus(String hid_currentStatus, String hid_endorseNo);
	
	/**
	 * 流水单号
	 * @param r7_TrxNo
	 * @param r2_OrderNo
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：
	 */
	public void updateRelevantOrderNo(String r7_TrxNo, String r2_OrderNo);
}
