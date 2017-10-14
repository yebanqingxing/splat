package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.model.TicketorderSegmentModel;

public interface TicketorderSegmentServiceFacade {
	/**
	 * 机票订单航段表 通过ID 获取
	 * @param String id
	 * @return TicketorderSegment
	 */
	public TicketorderSegment get(String id);
	
	/**
	 * 机票订单航段表 查询不分页
	 * @param TicketorderSegment
	 * @return List<TicketorderSegment>
	 */
	public List<TicketorderSegment> findList(TicketorderSegment ticketorderSegment);
	
	/**
	 * 机票订单航段表 查询分页
	 * @param Page<TicketorderSegment> page,TicketorderSegment
	 * @return Page<TicketorderSegment>
	 */
	public Page<TicketorderSegment> findPage(Page<TicketorderSegment> page, TicketorderSegment ticketorderSegment);
	
	/**
	 * 机票订单航段表 保存
	 * @param TicketorderSegment
	 * @return void
	 */
	 
	public void save(TicketorderSegment ticketorderSegment);
	
	/**
	 * 机票订单航段表 删除
	 * @param TicketorderSegment
	 * @return void
	 */
	 
	public void delete(TicketorderSegment ticketorderSegment);
	
	

	/**
	 * 通过订单号查询航段
	 * @param orderNo
	 * @return
	 */
	public List<TicketorderSegment> findSegmentByOrderNo(String orderNo);

	/**
	 * 退票单时航段的保留 
	 * @param refundsheetNo
	 * @param segment
	 */
	public void findRefundSheetSeg(String refundsheetNo, TicketorderSegment segment);

	/**
	 * 添加航段 航段可能是多个
	 * @param ticketorderSegment
	 * @param orderNum
	 */
	public void saveSegment(TicketorderSegmentModel ticketorderSegments, String orderNum);

	/**
	 * 通过id的字符串查询集合
	 * @param ids
	 * @return
	 */
	public List<TicketorderSegment> findSegmentByIdList(String ids);
	
	/**
	 * 
	 * @param ticketorderSegment
	 * @auth 李千超
	 * @date 2016年4月15日
	 * 作用：
	 */
	public void saveSegmentObj(TicketorderSegment ticketorderSegment);
	

}
