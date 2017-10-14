/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.RefundsheetAppendix;

/**
 * 退票单附件DAO接口
 * @author 李千超
 * @version 2016-03-15
 */
@MyBatisDao
public interface RefundsheetAppendixDao extends CrudDao<RefundsheetAppendix> {
	
	/**
	 * 查询附件
	 * @param refundsheetNo
	 * @return
	 */
	public List<RefundsheetAppendix> findrefundsheetAppendix(String refundsheetNo);
}