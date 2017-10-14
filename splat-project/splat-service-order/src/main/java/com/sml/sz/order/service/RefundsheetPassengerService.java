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
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.dao.RefundsheetPassengerDao;

/**
 * 退票单人信息Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="refundsheetPassengerServiceFacade")
@Transactional(readOnly = true)
public class RefundsheetPassengerService extends CrudService<RefundsheetPassengerDao, RefundsheetPassenger> implements RefundsheetPassengerServiceFacade {

	@Autowired
	RefundsheetPassengerDao refundsheetPassengerDao;
	/**
	 * 退票单人信息 通过ID 获取
	 * @param String id
	 * @return RefundsheetPassenger
	 */
	public RefundsheetPassenger get(String id) {
		return super.get(id);
	}
	
	/**
	 * 退票单人信息 查询不分页
	 * @param RefundsheetPassenger
	 * @return List<RefundsheetPassenger>
	 */
	public List<RefundsheetPassenger> findList(RefundsheetPassenger refundsheetPassenger) {
		return super.findList(refundsheetPassenger);
	}
	
	/**
	 * 退票单人信息 查询分页
	 * @param Page<RefundsheetPassenger> page,RefundsheetPassenger
	 * @return Page<RefundsheetPassenger>
	 */
	public Page<RefundsheetPassenger> findPage(Page<RefundsheetPassenger> page, RefundsheetPassenger refundsheetPassenger) {
		return super.findPage(page, refundsheetPassenger);
	}
	
	/**
	 * 退票单人信息 保存
	 * @param RefundsheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(RefundsheetPassenger refundsheetPassenger) {
		super.save(refundsheetPassenger);
	}
	
	/**
	 * 退票单人信息 删除
	 * @param RefundsheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(RefundsheetPassenger refundsheetPassenger) {
		super.delete(refundsheetPassenger);
	}

	/**
	 * 通过订单号查询该改定下的旅客
	 */
	public List<RefundsheetPassenger> findRefundsheetPassenger(String refundsheetNo) {
		return refundsheetPassengerDao.findRefundsheetPassenger(refundsheetNo);
	}
	@Transactional(readOnly = false)
	public void update(List<RefundsheetPassenger> refundsheetPassenger) {
		// TODO Auto-generated method stub
		if(refundsheetPassenger != null){
			for (RefundsheetPassenger refundsheetPassenger2 : refundsheetPassenger) {
				refundsheetPassengerDao.update(refundsheetPassenger2);
			}
		}
		
	}
	
}