package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.model.InvoiceModel;

/**
 * 行程单
 * @author lqc
 *
 */
public interface InvoiceServiceFacade {
	/**
	 * 行程单 通过ID 获取
	 * @param String id
	 * @return Invoice
	 */
	public Invoice get(String id);
	
	/**
	 * 行程单 查询不分页
	 * @param Invoice
	 * @return List<Invoice>
	 */
	public List<Invoice> findList(Invoice invoice);
	
	/**
	 * 行程单 查询分页
	 * @param Page<Invoice> page,Invoice
	 * @return Page<Invoice>
	 */
	public Page<Invoice> findPage(Page<Invoice> page, Invoice invoice);
	
	/**
	 * 行程单 保存
	 * @param Invoice
	 * @return void
	 */
	public void save(Invoice invoice);
	
	/**
	 * 行程单 删除
	 * @param Invoice
	 * @return void
	 */
	public void delete(Invoice invoice);

	/**
	 * 添加行程
	 * @param invoices
	 */
	public void saveInvoice(InvoiceModel invoices,String orderNO);
	
	/**
	 * 通过订单号来查询行程
	 * @param orderNo
	 * @return
	 */
	public List<Invoice> findInvoiceByOrderNo(String orderNo);
	
	/**
	 * 批量修改
	 * @param invoices
	 */
	public void update(List<Invoice> invoices);
	
}
