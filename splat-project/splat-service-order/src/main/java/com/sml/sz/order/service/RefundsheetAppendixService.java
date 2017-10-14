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
import com.sml.sz.order.dao.RefundsheetAppendixDao;
import com.sml.sz.order.entity.RefundsheetAppendix;

/**
 * 退票单附件Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="refundsheetAppendixServiceFacade")
@Transactional(readOnly = true)
public class RefundsheetAppendixService  extends CrudService<RefundsheetAppendixDao, RefundsheetAppendix> implements RefundsheetAppendixServiceFacade{

	@Autowired
	RefundsheetAppendixDao refundsheetAppendixDao;
	/**
	 * 退票单附件 通过ID 获取
	 * @param String id
	 * @return RefundsheetAppendix
	 */
	public RefundsheetAppendix get(String id) {
		return super.get(id);
	}
	
	/**
	 * 退票单附件 查询不分页
	 * @param RefundsheetAppendix
	 * @return List<RefundsheetAppendix>
	 */
	public List<RefundsheetAppendix> findList(RefundsheetAppendix refundsheetAppendix) {
		return super.findList(refundsheetAppendix);
	}
	
	/**
	 * 退票单附件 查询分页
	 * @param Page<RefundsheetAppendix> page,RefundsheetAppendix
	 * @return Page<RefundsheetAppendix>
	 */
	public Page<RefundsheetAppendix> findPage(Page<RefundsheetAppendix> page, RefundsheetAppendix refundsheetAppendix) {
		return super.findPage(page, refundsheetAppendix);
	}
	
	/**
	 * 退票单附件 保存
	 * @param RefundsheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(RefundsheetAppendix refundsheetAppendix) {
		super.save(refundsheetAppendix);
	}
	
	/**
	 * 退票单附件 删除
	 * @param RefundsheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(RefundsheetAppendix refundsheetAppendix) {
		super.delete(refundsheetAppendix);
	}

	/**
	 * 获取该订单下的附件
	 */
	public List<RefundsheetAppendix> findrefundsheetAppendix(String refundsheetNo) {
		
		return refundsheetAppendixDao.findrefundsheetAppendix(refundsheetNo);
	}
	
}