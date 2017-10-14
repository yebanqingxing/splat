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
import com.sml.sz.order.dao.EndorsesheetDetailDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.DetailStatusCount;
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderTicket;

/**
 * 改签单Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="endorsesheetDetailServiceFacade")
@Transactional(readOnly = true)
public class EndorsesheetDetailService extends CrudService<EndorsesheetDetailDao, EndorsesheetDetail> implements EndorsesheetDetailServiceFacade{

	@Autowired
	EndorsesheetDetailDao endorsesheetDetailDao;
	
	
	@Autowired
	EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade; 
	
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	
	@Autowired
	TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	/**
	 * 改签单 通过ID 获取
	 * @param String id
	 * @return EndorsesheetDetail
	 */
	public EndorsesheetDetail get(String id) {
		return super.get(id);
	}
	
	/**
	 * 改签单 查询不分页
	 * @param EndorsesheetDetail
	 * @return List<EndorsesheetDetail>
	 */
	public List<EndorsesheetDetail> findList(EndorsesheetDetail endorsesheetDetail) {
		return super.findList(endorsesheetDetail);
	}
	
	/**
	 * 改签单 查询分页
	 * @param Page<EndorsesheetDetail> page,EndorsesheetDetail
	 * @return Page<EndorsesheetDetail>
	 */
	public Page<EndorsesheetDetail> findPage(Page<EndorsesheetDetail> page, EndorsesheetDetail endorsesheetDetail) {
		Page<EndorsesheetDetail> page2=super.findPage(page, endorsesheetDetail);
		List<EndorsesheetDetail> detail=page.getList();
		//循环订单取出里边所对应的旅客信息
		for (EndorsesheetDetail detailInfo : detail) {
			
			//获取时长
			long date=DateUtils.pastMinutes(detailInfo.getCreateTime());
			detailInfo.setDuration(date+"");
			StringBuffer passengers=new  StringBuffer();
			//取出每个订单里边旅客信息
			List<EndorsesheetPassenger> passengerList=endorsesheetPassengerServiceFacade.findEndorsePassenger(detailInfo.getEndorsesheetNo());
			//判断集合是否有值
			if(null != passengerList && passengerList.size()>0)
				detailInfo.setPassengersTemp(passengerList.get(0).getPassengerName());
			//循环用逗号进行拼接起来
			for (EndorsesheetPassenger passengerListInfo : passengerList) {
				passengers.append("," + passengerListInfo.getPassengerName());
			}
			if(null != passengers && passengers.length()>0){
				detailInfo.setPassengers(passengers.toString().substring(1));
			}
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
			//取出所有的航段
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(detailInfo.getEndorsesheetNo());
			if(null != segmentList && segmentList.size()>0)
				//起飞地三字码 - 目的地三字码
				detailInfo.setSegmentsTemp(segmentList.get(0).getdepartureAddress()+"-"+segmentList.get(0).getArriveAddress());
			StringBuffer sbseg=new StringBuffer();
			//循环取值
			for (TicketorderSegment ticketorderSegment : segmentList) {
				sbseg.append(","+ticketorderSegment.getdepartureAddress()+"-"+ticketorderSegment.getArriveAddress());
			}
			//判断有航段
			if(null != sbseg && sbseg.length() >0)
				detailInfo.setSegments(sbseg.toString().substring(1));
		}
		page2.setList(detail);
		
		return page2;
	}
	
	/**
	 * 改签单 保存
	 * @param EndorsesheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(EndorsesheetDetail endorsesheetDetail) {
		super.save(endorsesheetDetail);
	}
	
	/**
	 * 改签单 删除
	 * @param EndorsesheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(EndorsesheetDetail endorsesheetDetail) {
		super.delete(endorsesheetDetail);
	}
	
	/**
	 * 修改订单的状态
	 */
	@Transactional(readOnly = false)
	public void updateEndorseStatus(String endorseStatus, String endorsesheetNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("endorseStatus", endorseStatus);
		map.put("endorsesheetNo",endorsesheetNo );
		endorsesheetDetailDao.updateEndorseStatus(map);
	}

	/**
	 * 根据改签单的类型不同查询相应的状态以及条数
	 * @return
	 */
	public Map<String,String> findEndorseDetailStatusCount(EndorsesheetDetail endorsesheetDetail) {
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//申请改签
		map.put("2", "0");//取消改签
		map.put("3", "0");//异常订单
		//判断状态并且赋值
		List<DetailStatusCount> listStatusCount=endorsesheetDetailDao.findEndorseDetailStatusCount(endorsesheetDetail);
		for (DetailStatusCount statusListInfo : listStatusCount) {
			map.put(statusListInfo.getEndorseStatus(), statusListInfo.getCount());
		}
		return map;
	}

	/**
	 * 修改支付的状态
	 * @param payStatus
	 * @param endorsesheetNo
	 */
	@Transactional(readOnly = false)
	public void updatePayStatus(String payStatus, String endorsesheetNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("payStatus",payStatus );
		map.put("endorsesheetNo", endorsesheetNo);
		endorsesheetDetailDao.updatePayStatus(map);
	}

	/**
	 * 修改废票状态
	 * @param voidStatus
	 * @author lqc
	 * @param endorsesheetNo
	 */
	@Transactional(readOnly = false)
	public void updateVoidStatus(String voidStatus, String endorsesheetNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("voidStatus", voidStatus);
		map.put("endorsesheetNo", endorsesheetNo);
		endorsesheetDetailDao.updateVoidStatus(map);
	}

	/**
	 * 修改订单的退票状态
	 * @param refundStatus
	 * @param endorsesheetNo
	 */
	@Transactional(readOnly = false)
	public void updateRefundStatus(String refundStatus, String endorsesheetNo) {
		Map<String,String> map= new HashMap<String,String>();
		map.put("refundStatus",refundStatus);
		map.put("endorsesheetNo", endorsesheetNo);
		endorsesheetDetailDao.updateRefundStatus(map);
	}
	
	/**
	 * 查询单个记录通过订单号
	 */
	public EndorsesheetDetail findEndorsesheetDetail(String endorsesheetNO) {
		return endorsesheetDetailDao.findEndorsesheetDetail(endorsesheetNO);
	}

	/**
	 * 查询修改订单的支付状态
	 */
	public Map<String,String> findPayStatusCount(EndorsesheetDetail endorsesheetDetail) {
		//创建一个map集合
		Map<String,String> map=new HashMap<String,String>();
		map.put("0", "0");//支付完成
		map.put("1", "0");//未支状态
		map.put("2", "0");//支付失败
		List<PayStatusCount> payStatusCount=endorsesheetDetailDao.findPayStatusCount(endorsesheetDetail);
		for (PayStatusCount payStatusCount2 : payStatusCount) {
			//利用map的特性如果键一样就可以替换掉值
			map.put(payStatusCount2.getPayStatus(), payStatusCount2.getCount());
		}
		return map;
	}

	/**
	 * 查询修改单的操作状态
	 */
	public Map<String,String> findCurrentStatusCount(EndorsesheetDetail endorsesheetDetail) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//审核改签中
		map.put("2", "0");//等待改签
		map.put("3", "0");//审核拒接
		map.put("4", "0");//改签完成
		List<CurrentStatus> currentStatus=endorsesheetDetailDao.findCurrentStatusCount(endorsesheetDetail);
		//将取出来的集合放到map中
		for (CurrentStatus currentStatus2 : currentStatus) {
			map.put(currentStatus2.getCurrentStatus(), currentStatus2.getCount());
		}
		return map;
	}

	/**
	 * 修改价格
	 */
	@Transactional(readOnly = false)
	public void updateEndorsesheetDetail(EndorsesheetDetail endorsesheetDetail) {
		endorsesheetDetailDao.updateEndorsesheetDetail(endorsesheetDetail);
		
	}

	
	@Transactional(readOnly = false)
	public void updateCurrnetStatus(String hid_currentStatus, String hid_endorseNo) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("endorsesheetNo", hid_endorseNo);
		map.put("currentStatus", hid_currentStatus);
		endorsesheetDetailDao.updateCurrnetStatus(map);
	}

	@Transactional(readOnly = false)
	public void updateRelevantOrderNo(String r7_TrxNo, String r2_OrderNo) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("relevant_order_no", r7_TrxNo);
		map.put("endorsesheet_no", r2_OrderNo);
		endorsesheetDetailDao.updateRelevantOrderNo(map);
		
	}
	
}