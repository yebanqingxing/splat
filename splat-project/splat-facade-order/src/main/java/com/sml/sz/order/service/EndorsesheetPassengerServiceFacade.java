package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.model.EndorsesheetPassengerModel;

/**
 * 改签单旅客Entity
 * @author 李千超
 * @version 2016-03-15
 */
public interface EndorsesheetPassengerServiceFacade {
	/**
	 * 改签单旅客 通过ID 获取
	 * @param String id
	 * @return EndorsesheetPassenger
	 */
	public EndorsesheetPassenger get(String id);
	
	/**
	 * 改签单旅客 查询不分页
	 * @param EndorsesheetPassenger
	 * @return List<EndorsesheetPassenger>
	 */
	public List<EndorsesheetPassenger> findList(EndorsesheetPassenger endorsesheetPassenger);
	
	/**
	 * 改签单旅客 查询分页
	 * @param Page<EndorsesheetPassenger> page,EndorsesheetPassenger
	 * @return Page<EndorsesheetPassenger>
	 */
	public Page<EndorsesheetPassenger> findPage(Page<EndorsesheetPassenger> page, EndorsesheetPassenger endorsesheetPassenger);
	
	/**
	 * 改签单旅客 保存
	 * @param EndorsesheetPassenger
	 * @return void
	 */
	public void save(EndorsesheetPassenger endorsesheetPassenger);
	
	/**
	 * 改签单旅客 删除
	 * @param EndorsesheetPassenger
	 * @return void
	 */
	public void delete(EndorsesheetPassenger endorsesheetPassenger);
	
	
	/**
	 * 通过订单号查询相关的旅客信息
	 * @param orderNo
	 * @return
	 */
	public List<EndorsesheetPassenger> findEndorsePassenger(String orderNo);
	
	/**
	 * 修改旅客的退票状态
	 * @param refundStatus 退票状态
	 * @param id 旅客id
	 */
	public void updateEndorseRefundStatusById(String refundStatus,String id);
	
	/**
	 * 修改旅客的废票状态
	 * @param map
	 */
	public void updateEndorseVoidStatusById(String voidStatus, String id);
	
	/**
	 * 修改旅客的改签状态
	 * @param map
	 */
	public void updateEndorseStatusById(String endorseStatus,String id);
	
	/**
	 * 通过选择旅客的id的字符串查询要了退票的旅客的集合
	 * @param passIds
	 * @param status 
	 * @return
	 */
	public List<EndorsesheetPassenger> findEndorsePassengerList(String passIds,String status);

	/**
	 * 改签保存旅客信息
	 * @param endorsesheetPassengers
	 * @param orderNO
	 */
	public void saveEndorsePassenger(EndorsesheetPassengerModel endorsesheetPassengers, String orderNO);

	/**
	 * 供应商回填价格每个人一条记录
	 * @param endorsesheetPassenger
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void updateEndorsePassengerPrice(EndorsesheetPassengerModel endorsesheetPassengers);

}
