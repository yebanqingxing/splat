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
import com.sml.sz.order.dao.RefundsheetDetailDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.entity.RefundsheetStatusCount;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderTicket;

/**
 * 退票单Service
 * @author 李千超
 * @version 2016-03-14
 */
@Service(value="refundsheetDetailServiceFacade")
@Transactional(readOnly = true)
public class RefundsheetDetailService extends CrudService<RefundsheetDetailDao, RefundsheetDetail> implements RefundsheetDetailServiceFacade{

	@Autowired
	RefundsheetDetailDao refundsheetDetailDao;
	
	@Autowired
	RefundsheetPassengerServiceFacade refundsheetPassengerServiceFacade;
	
	//航段
	@Autowired
	TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	/**
	 * 退票单 通过ID 获取
	 * @param String id
	 * @return RefundsheetDetail
	 */
	public RefundsheetDetail get(String id) {
		return super.get(id);
	}
	
	/**
	 * 退票单 查询不分页
	 * @param RefundsheetDetail
	 * @return List<RefundsheetDetail>
	 */
	public List<RefundsheetDetail> findList(RefundsheetDetail refundsheetDetail) {
		return super.findList(refundsheetDetail);
	}
	
	/**
	 * 退票单 查询分页
	 * @param Page<RefundsheetDetail> page,RefundsheetDetail
	 * @return Page<RefundsheetDetail>
	 */
	public Page<RefundsheetDetail> findPage(Page<RefundsheetDetail> page, RefundsheetDetail refundsheetDetail) {
		
		Page<RefundsheetDetail>  page2=super.findPage(page, refundsheetDetail);
		
		List<RefundsheetDetail> detail=page.getList();
		//循环集合
		 for (RefundsheetDetail detailInfo : detail) {
			 
			//获取时长
			long duration=DateUtils.pastMinutes(detailInfo.getCreateTime());
			detailInfo.setDuration(duration+" ");
			StringBuffer sbPassengers=new StringBuffer();
			//获取该订单的旅客信息
			List<RefundsheetPassenger> pass=refundsheetPassengerServiceFacade.findRefundsheetPassenger(detailInfo.getRefundsheetNo());
			if(null != pass && pass.size()>0){
				detailInfo.setPassengersTemp(pass.get(0).getPassengerName());
				
			//将旅客用逗号进行拼接起来
			for (RefundsheetPassenger passInfo : pass) {
				sbPassengers.append( "," +passInfo.getPassengerName());
			}
			//判断是否有旅客信息
			if(null != sbPassengers && sbPassengers.length()>0){
				detailInfo.setPassengers(sbPassengers.toString().substring(1));
			}
			}
			//取出所有的航段
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(detailInfo.getRefundsheetNo());
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
			//取出票号
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
	 * 退票单 保存
	 * @param RefundsheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(RefundsheetDetail refundsheetDetail) {
		super.save(refundsheetDetail);
	}
	
	/**
	 * 退票单 删除
	 * @param RefundsheetDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(RefundsheetDetail refundsheetDetail) {
		super.delete(refundsheetDetail);
	}



	/**
	 * 查询退票单的状态条数
	 */
	public Map<String,String> findRefundsheetStatusCount(RefundsheetDetail refundsheetDetail) {
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//退票订单
		map.put("2", "0");//取消退票订单
		map.put("4","0");//异常退票
		
		List<RefundsheetStatusCount> statusCount=refundsheetDetailDao.findRefundsheetStatusCount(refundsheetDetail);
		//循环判断状态然后赋值
			for (RefundsheetStatusCount statusCountInfo : statusCount) {
				map.put(statusCountInfo.getRefundsheetStatus(), statusCountInfo.getCount());
			}
			
		return map;
	}

	/**
	 * 通过订单号查询订单
	 */
	public RefundsheetDetail findRefundsheetDetail(String refundsheetNo) {
		return refundsheetDetailDao.findRefundsheetDetail(refundsheetNo);
	}
	@Transactional(readOnly = false)
	public void updateRefundsheetStatus(RefundsheetDetail refundsheetDetail) {
		// TODO Auto-generated method stub
		refundsheetDetailDao.update(refundsheetDetail);
	}

	public Map<String,String> findPayStatusCount(RefundsheetDetail refundsheetDetail) {
		//创建一个map集合
		Map<String,String> map=new HashMap<String,String>();
		map.put("0", "0");//支付完成
		map.put("1", "0");//未支状态
		map.put("2", "0");//支付失败
		List<PayStatusCount> payStatusCount=refundsheetDetailDao.findPayStatusCount(refundsheetDetail);
		for (PayStatusCount payStatusCount2 : payStatusCount) {
			//利用map的特性如果键一样就可以替换掉值
			map.put(payStatusCount2.getPayStatus(), payStatusCount2.getCount());
		}
          return map;
	}

	public Map<String,String> findCurrentStatus(RefundsheetDetail refundsheetDetail) {
		Map<String,String> map=new HashMap<String,String>();
		//操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 退票确认(等待确认) 4 审核通过 5 退票操作(采购商同意退票) 
		map.put("1", "0");//等待审核
		map.put("2", "0");//拒绝审核
		map.put("3", "0");//等待退票
		map.put("4", "0");//审核通过
		map.put("5", "0");//退票完成（采购商确认退票后）
		List<CurrentStatus> currentStatus=refundsheetDetailDao.findCurrentStatusCount(refundsheetDetail);
		for (CurrentStatus currentStatus2 : currentStatus) {
			map.put(currentStatus2.getCurrentStatus(),currentStatus2.getCount());
		}
		return map;
	}
}