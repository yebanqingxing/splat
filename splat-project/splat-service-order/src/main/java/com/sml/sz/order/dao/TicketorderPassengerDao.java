/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;
import java.util.Map;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.TicketorderPassenger;

/**
 * 订单旅客的信息DAO接口
 * @author 李千超
 * @version 2016-03-09
 */
@MyBatisDao
public interface TicketorderPassengerDao extends CrudDao<TicketorderPassenger> {
	
	/**
	 * 通过订单号查询相关的旅客信息
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderPassenger> findTicketorderPassenger(String orderNo);
	
	/**
	 * 修改旅客的退票状态
	 * @param map
	 */
	public void updateRefundStatusById(Map map);
	
	/**
	 * 修改旅客的废票状态
	 * @param map
	 */
	public void updateVoidStatusById(Map map);
	
	/**
	 * 修改旅客的改签状态
	 * @param map
	 */
	public void updateEndorseStatusById(Map map);
	
	/**
	 * 通过选择旅客的id的集合查询要了退票的旅客的集合
	 * @param passIds
	 * @return
	 */
	public List<TicketorderPassenger> findPassengerList(List<Integer> passIds);
	
	/**
	 * 修改是否退票废票以及改签的状态
	 * @param map
	 */
	public void updateVoidRefundEndorseStatus(Map map);
	
	/**
	 * 出票后票号的回填
	 * @param map
	 */
	public void updateTicketorderTicket(Map map);

}