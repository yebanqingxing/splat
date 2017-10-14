/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.Invoice;

/**
 * 行程单DAO接口
 * @author 李千超
 * @version 2016-03-24
 */
@MyBatisDao
public interface InvoiceDao extends CrudDao<Invoice> {
	
	public List<Invoice> findInvoiceByOrderNo(String orderNo);
	
}