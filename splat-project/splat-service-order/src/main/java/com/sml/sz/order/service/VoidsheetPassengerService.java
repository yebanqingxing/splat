/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.entity.model.VoidsheetPassengerModel;
import com.sml.sz.order.dao.VoidsheetPassengerDao;

/**
 * 废票单人信息Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="voidsheetPassengerServiceFacade")
@Transactional(readOnly = true)
public class VoidsheetPassengerService extends CrudService<VoidsheetPassengerDao, VoidsheetPassenger> implements VoidsheetPassengerServiceFacade{

	@Autowired
	VoidsheetPassengerDao voidsheetPassengerDao;
	/**
	 * 废票单人信息 通过ID 获取
	 * @param String id
	 * @return VoidsheetPassenger
	 */
	public VoidsheetPassenger get(String id) {
		return super.get(id);
	}
	
	/**
	 * 废票单人信息 查询不分页
	 * @param VoidsheetPassenger
	 * @return List<VoidsheetPassenger>
	 */
	public List<VoidsheetPassenger> findList(VoidsheetPassenger voidsheetPassenger) {
		return super.findList(voidsheetPassenger);
	}
	
	/**
	 * 废票单人信息 查询分页
	 * @param Page<VoidsheetPassenger> page,VoidsheetPassenger
	 * @return Page<VoidsheetPassenger>
	 */
	public Page<VoidsheetPassenger> findPage(Page<VoidsheetPassenger> page, VoidsheetPassenger voidsheetPassenger) {
		return super.findPage(page, voidsheetPassenger);
	}
	
	/**
	 * 废票单人信息 保存
	 * @param VoidsheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(VoidsheetPassenger voidsheetPassenger) {
		super.save(voidsheetPassenger);
	}
	
	/**
	 * 废票单人信息 删除
	 * @param VoidsheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(VoidsheetPassenger voidsheetPassenger) {
		super.delete(voidsheetPassenger);
	}

	/**
	 * 查询旅客
	 */
	public List<VoidsheetPassenger> findvoidPassenger(String voidsheetNo) {
		return voidsheetPassengerDao.findvoidPassenger(voidsheetNo);
	}

	/**
	 * 修改价格废票的旅客信息价格
	 */
	@Transactional(readOnly = false)
	public void updatevoidPassengerPrice(VoidsheetPassengerModel passengerModel) {
		List<VoidsheetPassenger> voidsheetPassengers = passengerModel.getVoidsheetPassengers();
		if(null != voidsheetPassengers)
		for (VoidsheetPassenger voidsheetPassenger : voidsheetPassengers) {
			voidsheetPassengerDao.updatePassengerPrice(voidsheetPassenger);
		}
		
		
		
	}
	
}