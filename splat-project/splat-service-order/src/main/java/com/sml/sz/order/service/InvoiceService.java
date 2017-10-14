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
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.model.InvoiceModel;
import com.sml.sz.order.dao.InvoiceDao;

/**
 * 行程单Service
 * 
 * @author 李千超
 * @version 2016-03-24
 */
@Service(value = "invoiceServiceFacade")
@Transactional(readOnly = true)
public class InvoiceService extends CrudService<InvoiceDao, Invoice> implements InvoiceServiceFacade {

	@Autowired
	InvoiceDao invoiceDao;

	/**
	 * 行程单 通过ID 获取
	 * 
	 * @param String
	 *            id
	 * @return Invoice
	 */
	public Invoice get(String id) {
		return super.get(id);
	}

	/**
	 * 行程单 查询不分页
	 * 
	 * @param Invoice
	 * @return List<Invoice>
	 */
	public List<Invoice> findList(Invoice invoice) {
		return super.findList(invoice);
	}

	/**
	 * 行程单 查询分页
	 * 
	 * @param Page<Invoice>
	 *            page,Invoice
	 * @return Page<Invoice>
	 */
	public Page<Invoice> findPage(Page<Invoice> page, Invoice invoice) {
		return super.findPage(page, invoice);
	}

	/**
	 * 行程单 保存
	 * 
	 * @param Invoice
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Invoice invoice) {
		super.save(invoice);
	}

	/**
	 * 行程单 删除
	 * 
	 * @param Invoice
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Invoice invoice) {
		super.delete(invoice);
	}

	@Transactional(readOnly = false)
	public void saveInvoice(InvoiceModel invoice, String orderNo) {
			List<Invoice> invoiceList=invoice.getInvoices();
			for (Invoice invoice2 : invoiceList) {
				invoice2.setOrderNo(orderNo);
				if(null != invoice2.getInvoiceName() && !"".equals(invoice2.getInvoiceName()))
				{
					super.save(invoice2);
				}
			}
	}

	/**
	 * 通过订单号查询行程单
	 */
	public List<Invoice> findInvoiceByOrderNo(String orderNo) {
		
		return invoiceDao.findInvoiceByOrderNo(orderNo);
	}
	@Transactional(readOnly = false)
	public void update(List<Invoice> invoices) {
		// TODO Auto-generated method stub
		if(invoices != null){
			for (Invoice invoice : invoices) {
				invoiceDao.update(invoice);
			}
		}
		
	}
}