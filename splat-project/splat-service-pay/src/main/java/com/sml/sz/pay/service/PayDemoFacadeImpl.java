/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.pay.dao.PayDemoDao;
import com.sml.sz.pay.entity.PayDemo;
import com.sml.sz.pay.service.PayDemoFacade;

/**
 * 示例功能Service
 * @author 黄诗源
 * @version 2016-03-06
 */
@Component("payDemoFacade")
@Transactional(readOnly = true)
public class PayDemoFacadeImpl extends CrudService<PayDemoDao, PayDemo> implements PayDemoFacade{
	@Autowired
	PayDemoDao payDemoDao;
	/**
	 * 示例功能 通过ID 获取
	 * @param String id
	 * @return TbDemo
	 */
	public PayDemo get(String id) {
		return payDemoDao.get(id);
	}
	
	/**
	 * 示例功能 查询不分页
	 * @param PayDemo
	 * @return List<TbDemo>
	 */
	public List<PayDemo> findList(PayDemo tbDemo) {
		return payDemoDao.findList(tbDemo);
	}
	
	/**
	 * 示例功能 查询分页
	 * @param Page<TbDemo> page,TbDemo
	 * @return Page<TbDemo>
	 */
	public Page<PayDemo> findPage(Page<PayDemo> page, PayDemo tbDemo) {
		return super.findPage(page, tbDemo);
	}
	
	/**
	 * 示例功能 保存
	 * @param PayDemo
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(PayDemo tbDemo) {
		super.save(tbDemo);
	}
	
	/**
	 * 示例功能 删除
	 * @param PayDemo
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(PayDemo tbDemo) {
		payDemoDao.delete(tbDemo);
	}
	
}