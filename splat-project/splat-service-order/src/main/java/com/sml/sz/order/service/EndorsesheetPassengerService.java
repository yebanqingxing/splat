/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.EndorsesheetPassengerDao;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.model.EndorsesheetPassengerModel;

/**
 * 改签单旅客Service
 * 
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value = "endorsesheetPassengerServiceFacade")
@Transactional(readOnly = true)
public class EndorsesheetPassengerService extends CrudService<EndorsesheetPassengerDao, EndorsesheetPassenger>
		implements EndorsesheetPassengerServiceFacade {
	@Autowired
	EndorsesheetPassengerDao endorsesheetPassengerDao;

	/**
	 * 改签单旅客 通过ID 获取
	 * 
	 * @param String
	 *            id
	 * @return EndorsesheetPassenger
	 */
	public EndorsesheetPassenger get(String id) {
		return super.get(id);
	}

	/**
	 * 改签单旅客 查询不分页
	 * 
	 * @param EndorsesheetPassenger
	 * @return List<EndorsesheetPassenger>
	 */
	public List<EndorsesheetPassenger> findList(EndorsesheetPassenger endorsesheetPassenger) {
		return super.findList(endorsesheetPassenger);
	}

	/**
	 * 改签单旅客 查询分页
	 * 
	 * @param Page<EndorsesheetPassenger>
	 *            page,EndorsesheetPassenger
	 * @return Page<EndorsesheetPassenger>
	 */
	public Page<EndorsesheetPassenger> findPage(Page<EndorsesheetPassenger> page,
			EndorsesheetPassenger endorsesheetPassenger) {
		return super.findPage(page, endorsesheetPassenger);
	}

	/**
	 * 改签单旅客 保存
	 * 
	 * @param EndorsesheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(EndorsesheetPassenger endorsesheetPassenger) {
		super.save(endorsesheetPassenger);
	}

	/**
	 * 改签单旅客 删除
	 * 
	 * @param EndorsesheetPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(EndorsesheetPassenger endorsesheetPassenger) {
		super.delete(endorsesheetPassenger);
	}
	
	/**
	 * 根据订单号查出旅客的相关信息
	 */
	public List<EndorsesheetPassenger> findEndorsePassenger(String orderNo) {
		return endorsesheetPassengerDao.findEndorsePassenger(orderNo);
	}

	/**
	 * 修改旅客的退票状态
	 */
	@Transactional(readOnly = false)
	public void updateEndorseRefundStatusById(String refundStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("refundStatus",refundStatus );
		map.put("id",id);
		endorsesheetPassengerDao.updateEndorseRefundStatusById(map);
		
	}

	/**
	 * 修改旅客废票状态
	 */
	@Transactional(readOnly = false)
	public void updateEndorseVoidStatusById(String voidStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("voidStatus", voidStatus);
		map.put("id", id);
		endorsesheetPassengerDao.updateEndorseVoidStatusById(map);
	}

	/**
	 * 修改旅客改签状态
	 */
	@Transactional(readOnly = false)
	public void updateEndorseStatusById(String endorseStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("endorseStatus", endorseStatus);
		map.put("id",id);
		endorsesheetPassengerDao.updateEndorseEndorseStatusById(map);
	}

	/**
	 * 获取要退、改、签票旅客的集合
	 * @param 旅客id的字符串
	 * @param 要修改的状态
	 */
	@Transactional(readOnly=false)
	public List<EndorsesheetPassenger> findEndorsePassengerList(String passIds,String status) {
		List<Integer> ids=new ArrayList<Integer>();
		//传过来的值按照","进行分割
		String [] passIdsArr=passIds.split(",");
		for (String string : passIdsArr) {
			if(StringUtils.isNotEmpty(status)){
			Map<String,String> map=new HashMap<String,String>();
			map.put("voidRefundEndorse",status );
			map.put("id",string);
			endorsesheetPassengerDao.updateVoidRefundEndorseStatus(map);
			}
			ids.add(Integer.parseInt(string.trim()));
		}
		return endorsesheetPassengerDao.findEndorsePassengerList(ids);
	}

	/**
	 * 改签要保存的旅客信息
	 */
	@Transactional(readOnly=false)
	public void saveEndorsePassenger(EndorsesheetPassengerModel endorsesheetPassengers, String orderNO) {
		List<EndorsesheetPassenger> passengers=endorsesheetPassengers.getEndorsesheetPassengers();
		if(null != passengers){
			for (EndorsesheetPassenger endorsesheetPassenger : passengers) {
				//赋值订单号
				endorsesheetPassenger.setOrderNo(orderNO);
				super.save(endorsesheetPassenger);
			}
		}
	}

	
	/**
	 * 供应商修改价格或者是平台填写的价格
	 */
	@Transactional(readOnly=false)
	public void updateEndorsePassengerPrice(EndorsesheetPassengerModel endorsesheetPassengers) {
		List<EndorsesheetPassenger> passengers=endorsesheetPassengers.getEndorsesheetPassengers();
		if(null != passengers ){
			for (EndorsesheetPassenger endorsesheetPassenger : passengers) {
				//修改
				endorsesheetPassengerDao.updateEndorsePassengerPrice(endorsesheetPassenger);
			}
		}
		
	}

}