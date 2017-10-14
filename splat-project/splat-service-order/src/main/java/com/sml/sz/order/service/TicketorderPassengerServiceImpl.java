/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.TicketorderPassengerDao;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.model.TicketorderPassengerModel;
import com.sml.sz.order.entity.model.TicketorderSinglePriceModel;

/**
 * 订单旅客的信息Service
 * @author 李千超
 * @version 2016-03-09
 */
@Service(value="ticketorderPassengerFacade")
@Transactional(readOnly = true)
public class TicketorderPassengerServiceImpl extends CrudService<TicketorderPassengerDao, TicketorderPassenger> implements TicketorderPassengerFacade{

	@Autowired
	TicketorderPassengerDao ticketorderPassengerDao;
	
	@Autowired
	TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	
	/**
	 * 订单旅客的信息 通过ID 获取
	 * @param String id
	 * @return TicketorderPassenger
	 */
	public TicketorderPassenger get(String id) {
		return super.get(id);
	}
	
	/**
	 * 订单旅客的信息 查询不分页
	 * @param TicketorderPassenger
	 * @return List<TicketorderPassenger>
	 */
	public List<TicketorderPassenger> findList(TicketorderPassenger ticketorderPassenger) {
		return super.findList(ticketorderPassenger);
	}
	
	/**
	 * 订单旅客的信息 查询分页
	 * @param Page<TicketorderPassenger> page,TicketorderPassenger
	 * @return Page<TicketorderPassenger>
	 */
	public Page<TicketorderPassenger> findPage(Page<TicketorderPassenger> page, TicketorderPassenger ticketorderPassenger) {
		return super.findPage(page, ticketorderPassenger);
	}
	
	/**
	 * 订单旅客的信息 保存
	 * @param TicketorderPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderPassenger ticketorderPassenger) {
		super.save(ticketorderPassenger);
	}
	
	/**
	 * 订单旅客的信息 删除
	 * @param TicketorderPassenger
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderPassenger ticketorderPassenger) {
		super.delete(ticketorderPassenger);
	}

	/**
	 * 根据订单号查出旅客的相关信息
	 */
	public List<TicketorderPassenger> findTicketorderPassenger(String orderNo) {
		List<TicketorderPassenger> list=new ArrayList<TicketorderPassenger>();
		list=ticketorderPassengerDao.findTicketorderPassenger(orderNo);
		return list;
	}

	/**
	 * 修改旅客的退票状态
	 */
	@Transactional(readOnly = false)
	public void updateRefundStatusById(String refundStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("refundStatus",refundStatus );
		map.put("id",id);
		ticketorderPassengerDao.updateRefundStatusById(map);
		
	}

	/**
	 * 修改旅客废票状态
	 */
	@Transactional(readOnly = false)
	public void updateVoidStatusById(String voidStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("voidStatus", voidStatus);
		map.put("id", id);
		ticketorderPassengerDao.updateVoidStatusById(map);
	}

	/**
	 * 修改旅客改签状态
	 */
	@Transactional(readOnly = false)
	public void updateEndorseStatusById(String endorseStatus, String id) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("endorseStatus", endorseStatus);
		map.put("id",id);
		ticketorderPassengerDao.updateEndorseStatusById(map);
	}

	/**
	 * 获取要退、改、签票旅客的集合
	 * @param 旅客id的字符串
	 * @param 要修改的状态
	 */
	@Transactional(readOnly=false)
	public List<TicketorderPassenger> findPassengerList(String passIds,String status) {
		List<Integer> ids=new ArrayList<Integer>();
		//传过来的值按照","进行分割
		String [] passIdsArr=passIds.split(",");
		for (String string : passIdsArr) {
			if(null != status && !"".equals(status)){
				Map<String,String> map=new HashMap<String,String>();
				map.put("voidRefundEndorse",status );
				map.put("id",string);
				//修改旅客退废改的状态
				ticketorderPassengerDao.updateVoidRefundEndorseStatus(map);
			}
			ids.add(Integer.parseInt(string.trim()));
		}
		return ticketorderPassengerDao.findPassengerList(ids);
	}

	@Transactional(readOnly=false)
	public void savePassenger(TicketorderPassengerModel ticketorderPassengers,TicketorderSinglePriceModel ticketorderSinglePrices, String orderNum) {
		int index=1;	//旅客的序号 从1开始 不得大于9 pnr解析就不会超过9个
	List<TicketorderPassenger> passenger=ticketorderPassengers.getTicketorderPassengers();
	//取出机票明细
	List<TicketorderSinglePrice> ticketorderSinglePriceList=ticketorderSinglePrices.getTicketorderSinglePrices();
	
	if(null != passenger){
		for (int i = 0; i < passenger.size(); i++) {
			TicketorderPassenger passengerInfo=passenger.get(i);
			TicketorderSinglePrice ticketorderSinglePrice=ticketorderSinglePriceList.get(i);
			//赋值旅客序号 
			passengerInfo.setPassengerIndex(index+i+"");
			//赋值订单号
			passengerInfo.setOrderNo(orderNum);
			super.save(passengerInfo);
			//存入旅客id
			ticketorderSinglePrice.setOrderId(passengerInfo.getId());
			//存入订单号
			ticketorderSinglePrice.setOrderNo(orderNum);
			//机票明细存入数据库
			ticketorderSinglePriceServiceFacade.save(ticketorderSinglePrice);
			
		}
	}
	}
	
	/**
	 * 出票以后修改票号
	 * @param ticketNo 票号
	 * @param id 旅客id
	 */
	@Transactional(readOnly=false)
	public void updateTicketorderTicket(String ticketNo,String id){
		Map<String,String> map=new HashMap<String,String>();
		map.put("ticketNo", ticketNo);
		map.put("id", id);
		ticketorderPassengerDao.updateTicketorderTicket(map);
		
	}

	@Transactional(readOnly=false)
	public void updateVoidRefundEndorseStatus(String status, String id) {
		Map<String,String> map=new HashMap<String ,String>();
		map.put("voidRefundEndorse",status );
		map.put("id",id);
		//修改旅客退废改的状态
		ticketorderPassengerDao.updateVoidRefundEndorseStatus(map);
	}

	public void savePassenger(TicketorderPassengerModel ticketorderPassengers, String orderNum) {
		// TODO Auto-generated method stub
		
	}
	
}