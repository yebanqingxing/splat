package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.entity.model.VoidsheetPassengerModel;

/**
 * 废票单人信息
 * @author lqc
 */
public interface VoidsheetPassengerServiceFacade {
	/**
	 * 废票单人信息 通过ID 获取
	 * @param String id
	 * @return VoidsheetPassenger
	 */
	public VoidsheetPassenger get(String id);
	
	/**
	 * 废票单人信息 查询不分页
	 * @param VoidsheetPassenger
	 * @return List<VoidsheetPassenger>
	 */
	public List<VoidsheetPassenger> findList(VoidsheetPassenger voidsheetPassenger);
	
	/**
	 * 废票单人信息 查询分页
	 * @param Page<VoidsheetPassenger> page,VoidsheetPassenger
	 * @return Page<VoidsheetPassenger>
	 */
	public Page<VoidsheetPassenger> findPage(Page<VoidsheetPassenger> page, VoidsheetPassenger voidsheetPassenger) ;
	
	/**
	 * 废票单人信息 保存
	 * @param VoidsheetPassenger
	 * @return void
	 */
	public void save(VoidsheetPassenger voidsheetPassenger);
	
	/**
	 * 废票单人信息 删除
	 * @param VoidsheetPassenger
	 * @return void
	 */
	public void delete(VoidsheetPassenger voidsheetPassenger);
	
	/**
	 * 通过订单号查询旅客信息
	 * @param voidsheetNo
	 * @return
	 */
	public List<VoidsheetPassenger> findvoidPassenger(String voidsheetNo);
	
	/**
	 * 修改价格申请废票
	 * @param passengerModel
	 */
	public void updatevoidPassengerPrice(VoidsheetPassengerModel passengerModel);
}
