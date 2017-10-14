/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.TicketorderSegmentDao;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.model.TicketorderSegmentModel;

/**
 * 机票订单航段表Service
 * @author 李千超
 * @version 2016-03-10
 */
@Service(value="ticketorderSegmentServiceFacade")
@Transactional(readOnly = true)
public class TicketorderSegmentService extends CrudService<TicketorderSegmentDao, TicketorderSegment> implements TicketorderSegmentServiceFacade{

	@Autowired
	TicketorderSegmentDao ticketorderSegmentDao;
	/**
	 * 机票订单航段表 通过ID 获取
	 * @param String id
	 * @return TicketorderSegment
	 */
	public TicketorderSegment get(String id) {
		return super.get(id);
	}
	
	/**
	 * 机票订单航段表 查询不分页
	 * @param TicketorderSegment
	 * @return List<TicketorderSegment>
	 */
	public List<TicketorderSegment> findList(TicketorderSegment ticketorderSegment) {
		return super.findList(ticketorderSegment);
	}
	
	/**
	 * 机票订单航段表 查询分页
	 * @param Page<TicketorderSegment> page,TicketorderSegment
	 * @return Page<TicketorderSegment>
	 */
	public Page<TicketorderSegment> findPage(Page<TicketorderSegment> page, TicketorderSegment ticketorderSegment) {
		return super.findPage(page, ticketorderSegment);
	}
	
	/**
	 * 机票订单航段表 保存
	 * @param TicketorderSegment
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(TicketorderSegment ticketorderSegment) {
		super.save(ticketorderSegment);
	}
	
	/**
	 * 机票订单航段表 删除
	 * @param TicketorderSegment
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(TicketorderSegment ticketorderSegment) {
		super.delete(ticketorderSegment);
	}

	/**
	 * 通过订单号查询航段
	 */
	public List<TicketorderSegment> findSegmentByOrderNo(String orderNo) {
		
		return ticketorderSegmentDao.findSegmentByOrderNo(orderNo);
	}
	
	@Transactional(readOnly=false)
	public void findRefundSheetSeg(String refundsheetNo, TicketorderSegment segment) {
		
		List<Integer> ids=new ArrayList<Integer>();
		//将获取过来的航段的id进行分割
		String[] segIdArr=segment.getSegId().split(",");
		for (String string : segIdArr) {
			ids.add(Integer.parseInt(string.trim()));
		}
		List<TicketorderSegment> segList = ticketorderSegmentDao.findSegmentByIdList(ids);
		for (TicketorderSegment ticketorderSegment : segList) {
			//将退单编号放进去
			ticketorderSegment.setOrderNo(refundsheetNo);
			//将id赋值为null进行添加
			ticketorderSegment.setId(null);
			//将其退单的航段添加到数据库
			ticketorderSegmentDao.saveSegment(ticketorderSegment);
		}
	}

	@Transactional(readOnly=false)
	public void saveSegment(TicketorderSegmentModel ticketorderSegment, String orderNum) {
		int index=1;
		List<TicketorderSegment> segment=ticketorderSegment.getTicketorderSegments();
		for (int i=0; i<segment.size();i++) {
			TicketorderSegment segmentInfo=segment.get(i);
			//行程信息从1开始
			segmentInfo.setOdIndex(index+i+"");
			//赋值订单号
			segmentInfo.setOrderNo(orderNum);
			
			super.save(segmentInfo);
		}
	}

	public List<TicketorderSegment> findSegmentByIdList(String ids) {
		List<Integer> idList=new ArrayList<Integer>();
		//将获取过来的航段的id进行分割
		String[] segIdArr=ids.split(",");
		for (String string : segIdArr) {
			idList.add(Integer.parseInt(string.trim()));
		}
		return ticketorderSegmentDao.findSegmentByIdList(idList);
	}

	/**
	 * 添加航段
	 */
	@Transactional(readOnly=false)
	public void saveSegmentObj(TicketorderSegment ticketorderSegment) {
		ticketorderSegmentDao.saveSegment(ticketorderSegment);
	}

	
	
}