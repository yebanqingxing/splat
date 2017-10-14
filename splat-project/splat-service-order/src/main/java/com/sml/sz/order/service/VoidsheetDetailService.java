/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.DateUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.entity.VoidsheetStatusCount;
import com.sml.sz.order.dao.VoidsheetDetailDao;

/**
 * 废票单表Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="voidsheetDetailServiceFacade")
@Transactional(readOnly = true)
public class VoidsheetDetailService extends CrudService<VoidsheetDetailDao, VoidsheetDetail> implements VoidsheetDetailServiceFacade{

	@Autowired
	VoidsheetDetailDao voidsheetDetailDao;
	
	@Autowired
	VoidsheetPassengerServiceFacade voidsheetPassengerServiceFacade;
	
	@Autowired
	TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade; 
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade; 
	/**
	 * 废票单表 通过ID 获取
	 * @param String id
	 * @return VoidsheetDetail
	 */
	public VoidsheetDetail get(String id) {
		return super.get(id);
	}
	
	/**
	 * 废票单表 查询不分页
	 * @param VoidsheetDetail
	 * @return List<VoidsheetDetail>
	 */
	public List<VoidsheetDetail> findList(VoidsheetDetail voidsheetDetail) {
		return super.findList(voidsheetDetail);
	}
	
	/**
	 * 废票单表 查询分页
	 * @param Page<VoidsheetDetail> page,VoidsheetDetail
	 * @return Page<VoidsheetDetail>
	 */
	public Page<VoidsheetDetail> findPage(Page<VoidsheetDetail> page, VoidsheetDetail voidsheetDetail) {
		Page<VoidsheetDetail> page2=super.findPage(page, voidsheetDetail);
		List<VoidsheetDetail> detail=page.getList();
		for (VoidsheetDetail detailInfo : detail) {
			
			//获取订单的时长 
			long duration=DateUtils.pastMinutes(detailInfo.getCreateTime());
			detailInfo.setDuration(duration+"");
			StringBuffer sbPassengers=new StringBuffer();
			//取出该订单的旅客
			List<VoidsheetPassenger> pass=voidsheetPassengerServiceFacade.findvoidPassenger(detailInfo.getVoidsheetNo());
			if(null != pass && pass.size()>0)
				//存入第一条  
				detailInfo.setPassengersTemp(pass.get(0).getPassengerName());
			for (VoidsheetPassenger passInfo : pass) {
				sbPassengers.append("," +passInfo.getPassengerName());
			}
			if(null != sbPassengers && sbPassengers.length()>0){
				detailInfo.setPassengers(sbPassengers.toString().substring(1));
			}
			//取出所有的航段
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(detailInfo.getVoidsheetNo());
			if(null != segmentList && segmentList.size()>0)
				//起飞地三字码 - 目的地三字码
				detailInfo.setSegmentTemp(segmentList.get(0).getdepartureAddress()+"-"+segmentList.get(0).getArriveAddress());
			StringBuffer sbseg=new StringBuffer();
			//循环取值
			for (TicketorderSegment ticketorderSegment : segmentList) {
				sbseg.append(","+ticketorderSegment.getdepartureAddress()+"-"+ticketorderSegment.getArriveAddress());
			}
			//判断有航段
			if(null != sbseg && sbseg.length() >0)
				detailInfo.setSegment(sbseg.toString().substring(1));
			//取出所有的票号
			StringBuffer sb=new StringBuffer();
			List<TicketorderTicket> ticketList=ticketorderTicketServiceFacade.findTicketList(detailInfo.getOrderNo());
			//取出第一个票号页面展示剩下的是鼠标移上去显示
			if(null != ticketList && ticketList.size() >0)
				detailInfo.setTicketNoTemp(ticketList.get(0).getTicketNo());
			//循环遍历得到所需要的
			for (TicketorderTicket ticketorderTicket : ticketList) {
				sb.append(","+ticketorderTicket.getTicketNo());
			}
			//判断票号是否有没有取值
			if(null != sb  && sb.length() > 0){
				//将票号赋值进去
				detailInfo.setTicketNo(sb.toString().substring(1));
			}
		}
		page2.setList(detail);
		
		return page2;
	}
	
	/**
	 * 废票单表 保存
	 * @param VoidsheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(VoidsheetDetail voidsheetDetail) {
		super.save(voidsheetDetail);
	}
	
	/**
	 * 废票单表 删除
	 * @param VoidsheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(VoidsheetDetail voidsheetDetail) {
		super.delete(voidsheetDetail);
	}

	/**
	 * 查询订单的信息
	 */
	public VoidsheetDetail findVoidsheetDetail(String voidsheetNo) {
		return voidsheetDetailDao.findVoidsheetDetail(voidsheetNo);
	}

	/**
	 * 修改废票的状态
	 */
	@Transactional(readOnly = false)
	public void updateVoidsheetStatus(String  voidsheetStatus,String voidsheetNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("voidsheetStatus", voidsheetStatus);
		map.put("voidsheetNo",voidsheetNo );
		voidsheetDetailDao.updateVoidsheetStatus(map);
	}
	
	/**
	 * 查询废票状态的条数
	 */
	public Map<String,String> findVoidsheetStatusCount(VoidsheetDetail voidsheetDetail) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//申请废票
		map.put("2","0");//异常订单
		map.put("3", "0");
		//循环判断状态 如果状态一样则将状态相应的条数赋值给相应的条数如果不一样则将条数赋值0
		List<VoidsheetStatusCount> statusCountList=voidsheetDetailDao.findVoidsheetStatusCount( voidsheetDetail);
		for (VoidsheetStatusCount statusCountListInfo : statusCountList) {
			map.put(statusCountListInfo.getVoidsheetStatus(), statusCountListInfo.getCount());
		}
		return map;
	}
	/**
	 * 查询废票支付状态的条数
	 */
	public Map<String,String> findPayStatusCount(VoidsheetDetail voidsheetDetail) {
		//创建一个map集合
		Map<String,String> map=new HashMap<String,String>();
		map.put("0", "0");//支付完成
		map.put("1", "0");//未支状态
		map.put("2", "0");//支付失败
		List<PayStatusCount> payStatusCount=voidsheetDetailDao.findPayStatusCount(voidsheetDetail);
		for (PayStatusCount payStatusCount2 : payStatusCount) {
			//利用map的特性如果键一样就可以替换掉值
			map.put(payStatusCount2.getPayStatus(), payStatusCount2.getCount());
		}
		return map;
	}
	/**
	 * 查询废票操作状态的条数
	 */
	public Map<String,String> findCurrentStatus(VoidsheetDetail voidsheetDetail) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//审核废票
		map.put("2", "0");//废票操作
		map.put("3", "0");//废票拒绝
		map.put("4", "0");//已审核废票
		map.put("5", "0");//废票完成
		map.put("6", "0");//等待确认
		List<CurrentStatus> currentStatus=voidsheetDetailDao.findCurrentStatus(voidsheetDetail);
		//将取出来的集合放到map中
		for (CurrentStatus currentStatus2 : currentStatus) {
			map.put(currentStatus2.getCurrentStatus(), currentStatus2.getCount());
		}
		return map;
	}

	/**
	 * 修改订单的操作状态
	 */
	@Transactional(readOnly = false)
	public void updateCurrentStatus(String currentStatus, String voidsheetNo) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("currentStatus", currentStatus);
		map.put("voidsheetNo", voidsheetNo);
		voidsheetDetailDao.updateCurrentStatus(map);
	}

	/**
	 * 供应商更改价格
	 */
	@Transactional(readOnly = false)
	public void updateSupplierTotSettlementPrice(String SupplierTotSettlementPrice, String orderNo) {
		Map<String ,String > map=new HashMap<String, String>(); 
		map.put("SupplierTotSettlementPrice", SupplierTotSettlementPrice);
		map.put("voidsheetNo",orderNo);
		voidsheetDetailDao.updateSupplierTotSettlementPrice(map);
	}

	/**
	 * 修改支付状态
	 */
	@Transactional(readOnly = false)
	public void updatePayStatus(String payStatus, String voidsheetNo) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("payStatus", payStatus);
		map.put("voidsheetNo",voidsheetNo );
		voidsheetDetailDao.updatePayStatus(map );
		
	}

	
}