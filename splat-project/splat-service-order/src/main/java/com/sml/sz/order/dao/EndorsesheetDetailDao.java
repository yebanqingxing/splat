/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.DetailStatusCount;
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.PayStatusCount;

/**
 * 改签单DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface EndorsesheetDetailDao extends CrudDao<EndorsesheetDetail> {
	/**
	 * 修改订单的状态
	 * @param map
	 */
	public void updateEndorseStatus(Map<String,String> map);
	
	/**
	 * 根据改签单的类型不同查询相应的状态以及条数
	 * @return
	 */
	public List<DetailStatusCount> findEndorseDetailStatusCount(EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 修改改签的支付的状态
	 * @param map
	 */
	public void updatePayStatus(Map<String,String> map);

	
	/**
	 * 修改废票状态
	 * @param map
	 */
	public void updateVoidStatus(Map<String,String> map);
	
	/**
	 * 修改退票状态
	 * @param map
	 */
	public void updateRefundStatus(Map<String,String> map);
	
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
	public List<PayStatusCount> findPayStatusCount(EndorsesheetDetail endorsesheetDetail);

	/**
	 * 查询操作状态
	 * @param endorsesheetDetail
	 * @return
	 */
	public List<CurrentStatus> findCurrentStatusCount(EndorsesheetDetail endorsesheetDetail);

	/**
	 * 
	 * @param endorsesheetDetail
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：供应商确认以后修改价格
	 */
	public void updateEndorsesheetDetail(EndorsesheetDetail endorsesheetDetail);
	
	/**
	 * 修改状态
	 * @param map
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：
	 */
	public void updateCurrnetStatus(Map<String ,String > map);

	/**
	 * 修改流水单号
	 * @param map
	 * @auth 李千超
	 * @date 2016年4月16日
	 * 作用：
	 */
	public void updateRelevantOrderNo(Map<String, String> map);
}