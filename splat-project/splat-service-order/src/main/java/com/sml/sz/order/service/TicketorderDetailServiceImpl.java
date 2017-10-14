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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.DateUtils;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.TicketorderDetailDao;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.OrderStatusCount;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderTicket;

/**
 * 订单生成模块Service
 * @author 李千超
 * @version 2016-03-08
 */
@Component(value="ticketorderDetailServiceFacade")
public class TicketorderDetailServiceImpl  extends CrudService<TicketorderDetailDao, TicketorderDetail> implements TicketorderDetailServiceFacade {
	
	@Autowired
	TicketorderDetailDao ticketorderDetailDao;
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	@Autowired
	TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	/**
	 * 订单生成模块 通过ID 获取
	 * @param String id
	 * @return TbTicketorderDetail
	 */
	public TicketorderDetail get(String id) {
		return super.get(id);
	}
	
	/**
	 * 订单生成模块 查询不分页
	 * @param TicketorderDetail
	 * @return List<TbTicketorderDetail>
	 */
	public List<TicketorderDetail> findList(TicketorderDetail tbTicketorderDetail) {
		return super.findList(tbTicketorderDetail);
	}
	
	/**
	 * 订单生成模块 查询分页
	 * @param Page<TbTicketorderDetail> page,TbTicketorderDetail
	 * @return Page<TbTicketorderDetail>
	 */
	public Page<TicketorderDetail> findPage(Page<TicketorderDetail> page, TicketorderDetail tbTicketorderDetail) {
		
		Page<TicketorderDetail> page2=super.findPage(page, tbTicketorderDetail);
	List<TicketorderDetail> detail=page2.getList();
		
		for (TicketorderDetail detailInfo : detail) {
			
			//获取时长
			long duration=DateUtils.pastMinutes(detailInfo.getCreateTime());
			detailInfo.setDuration(duration+"");
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
			//旅客信息
			String passengerInfo=detailInfo.getPassengers();
			if(StringUtils.isNotBlank(passengerInfo)){
				String[] passen=passengerInfo.split(",");
				//判断如果长度大于1 的话
				if(passen.length > 1){
					detailInfo.setPassengersTemp(passen[0]+"...");
				}else {
					detailInfo.setPassengersTemp(passen[0]);
				}
			}
			
		
			
			//取出所有的航段
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(detailInfo.getOrderNo());
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
	 * 订单生成模块 保存
	 * @param TicketorderDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderDetail tbTicketorderDetail) {
		super.save(tbTicketorderDetail);
	}
	
	/**
	 * 订单生成模块 删除
	 * @param TicketorderDetail
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderDetail tbTicketorderDetail) {
		super.delete(tbTicketorderDetail);
	}

	/**
	 * 通过订单号查询该订单是否已经支付
	 */
	public String findPayStatusByOrderNo(String orderNo) {
		return ticketorderDetailDao.findPayStatusByOrderNo(orderNo);
	}

	/**
	 * 修改支付状态
	 */
	@Transactional(readOnly = false)
	public void updatePayStatus(String payStatus, String orderNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("payStatus", payStatus);
		map.put("orderNo",orderNo );
		ticketorderDetailDao.updatePayStatus(map);		
	}


	/**
	 * 查看支付状态以及条数
	 * @return
	 */
	public Map<String,String> findPayStatusCount(TicketorderDetail tbTicketorderDetail){
		Map<String,String> map = new HashMap<String,String>();
		map.put("0", "0");//已支付
		map.put("1", "0");//未支付
		map.put("2", "0");//支付失败
		List<PayStatusCount> payStatusCount=ticketorderDetailDao.findPayStatusCount(tbTicketorderDetail);
		for (PayStatusCount payStatusCount2 : payStatusCount) {
			map.put(payStatusCount2.getPayStatus(), payStatusCount2.getCount());
		}
		
		return map;
	}
	
	/**
	 * 查看订单状态以及条数
	 * @return
	 */
	public Map<String, String> findOrderStatusCount(TicketorderDetail tbTicketorderDetail){
		Map<String,String> map = new HashMap<String,String>();
		//订单状态  "1":已提交-下单成功;    "5":异常订单;  "7":取消订单(删除);
		map.put("1", "0");
		map.put("5", "0");
		map.put("7", "0");
		List<OrderStatusCount> list=ticketorderDetailDao.findOrderStatusCount(tbTicketorderDetail);
		for (OrderStatusCount orderStatusCount : list) {
			map.put(orderStatusCount.getOrderStatus(), orderStatusCount.getCount());
		}
		return map;
	}
	
	/**
	 * 修改订单的状态
	 * @param orderStatus 订单状态
	 * @param orderNO 订单号
	 */
	@Transactional(readOnly = false)
	public void updateOrderStatus(String orderStatus,String orderNO){
		Map<String,String> map=new HashMap<String,String>();
		map.put("orderStatus",orderStatus);
		map.put("orderNo", orderNO);
		ticketorderDetailDao.updateOrderStatus(map);
	}
	
	/**
	 *  修改订单的操作状态
	 * @param currentStatus 操作状态
	 * @param orderNo 要修改的订单号
	 */
	@Transactional(readOnly = false)
	public void updateCurrentStatus(String currentStatus,String orderNo){
		Map<String,String> map=new HashMap<String,String>();
		map.put("currentStatus", currentStatus);
		map.put("orderNo", orderNo);
		ticketorderDetailDao.updateCurrentStatus(map);
	}
	
	/**
	 * 通过订单的订单号查询订单的信息
	 * @param orderNo
	 * @return
	 */
	public TicketorderDetail findorderDetailByOrderNo(String orderNo){
		return ticketorderDetailDao.findorderDetailByOrderNo(orderNo);	
	}

	/**
	 * 支付成功成功以后修改流水单号
	 */
	@Transactional(readOnly = false)
	public void updateRelevantOrderNo(String relevantoOrderNo, String orderNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("relevantoOrderNo",relevantoOrderNo );
		map.put("orderNo",orderNo);
		//连接数据库修改
		ticketorderDetailDao.updateRelevantOrderNo(map);
	}

	/**
	 * 出票后修改的状态
	 */
	@Transactional(readOnly = false)
	public void updateIssueResult(String issueResult, String orderNo) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("issueResult", issueResult);
		map.put("orderNo",orderNo);
		ticketorderDetailDao.updateIssueResult(map);
	}
	
	@Transactional(readOnly = false)
	public void updateOrderPrice(TicketorderDetail tbTicketorderDetail) {
		ticketorderDetailDao.updateOrderPrice(tbTicketorderDetail);
	}
	public Map<String, String> findCurrentStatus(TicketorderDetail ticketorderDetail) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "0");//正在核实价策
		map.put("2", "0");//已经审核价格和政策
		map.put("3","0");//已督促采购商核实价格
		map.put("4", "0");//请尽快支付
		map.put("5", "0");//审核拒绝
		map.put("6", "0");//全额退款
		map.put("7", "0");//等待出票
		map.put("8", "0");//出票完成
		map.put("9", "0");//9 申请全额退款(待供应商确认)
		map.put("10", "0");// 10 申请通过全额退款
		//数据库里边取出来所有的状态以及条数
		List<CurrentStatus> currentStatus=ticketorderDetailDao.findCurrentStatus(ticketorderDetail);
		for (CurrentStatus currentStatus2 : currentStatus) {
		map.put(currentStatus2.getCurrentStatus(),currentStatus2.getCount());	
		}
		return map;
	}
}