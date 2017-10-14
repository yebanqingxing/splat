/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.EndorsesheetPassenger;

/**
 * 改签单旅客DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface EndorsesheetPassengerDao extends CrudDao<EndorsesheetPassenger> {
	/**
	 * 通过订单号查询相关的旅客信息
	 * @param orderNo
	 * @return
	 */
	public List<EndorsesheetPassenger> findEndorsePassenger(String orderNo);
	
	/**
	 * 修改旅客的退票状态
	 * @param map
	 */
	public void updateEndorseRefundStatusById(Map<String,String> map);
	
	/**
	 * 修改旅客的废票状态
	 * @param map
	 */
	public void updateEndorseVoidStatusById(Map<String,String> map);
	
	/**
	 * 修改旅客的改签状态
	 * @param map
	 */
	public void updateEndorseEndorseStatusById(Map<String,String> map);
	
	/**
	 * 通过选择旅客的id的集合查询要退票的旅客的集合
	 * @param passIds
	 * @return
	 */
	public List<EndorsesheetPassenger> findEndorsePassengerList(List<Integer> passIds);

	/**
	 * 修改的旅客的退废改状态
	 * @param map
	 */
	public void updateVoidRefundEndorseStatus(Map<String,String> map);
	
	
	/**
	 * 供应商回填价格每个人一条记录
	 * @param endorsesheetPassenger
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void updateEndorsePassengerPrice(EndorsesheetPassenger endorsesheetPassenger);


}