/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.VoidsheetPassenger;

/**
 * 废票单人信息DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface VoidsheetPassengerDao extends CrudDao<VoidsheetPassenger> {
	/**
	 * 通过订单号查询旅客信息
	 * @param voidsheetNo
	 * @return
	 */
	public List<VoidsheetPassenger> findvoidPassenger(String voidsheetNo);
	/**
	 * 旅客申请废票的时候的价格信息
	 * @param voidsheetPassenger
	 */
	public void updatePassengerPrice(VoidsheetPassenger voidsheetPassenger);
}