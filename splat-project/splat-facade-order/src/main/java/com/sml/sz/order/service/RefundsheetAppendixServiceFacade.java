package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.RefundsheetAppendix;

/**
 * 退票单附件
 * @author lqc
 *
 */
public interface RefundsheetAppendixServiceFacade {
	/**
	 * 退票单附件 通过ID 获取
	 * @param String id
	 * @return RefundsheetAppendix
	 */
	public RefundsheetAppendix get(String id);
	
	/**
	 * 退票单附件 查询不分页
	 * @param RefundsheetAppendix
	 * @return List<RefundsheetAppendix>
	 */
	public List<RefundsheetAppendix> findList(RefundsheetAppendix refundsheetAppendix) ;
	
	/**
	 * 退票单附件 查询分页
	 * @param Page<RefundsheetAppendix> page,RefundsheetAppendix
	 * @return Page<RefundsheetAppendix>
	 */
	public Page<RefundsheetAppendix> findPage(Page<RefundsheetAppendix> page, RefundsheetAppendix refundsheetAppendix);
	
	/**
	 * 退票单附件 保存
	 * @param RefundsheetAppendix
	 * @return void
	 */
	 
	public void save(RefundsheetAppendix refundsheetAppendix);
	
	/**
	 * 退票单附件 删除
	 * @param RefundsheetAppendix
	 * @return void
	 */
	 
	public void delete(RefundsheetAppendix refundsheetAppendix);
	
	
	/**
	 * 查询附件
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetAppendix> findrefundsheetAppendix(String refundsheetNo);
}
