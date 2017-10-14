package com.sml.sz.pay.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.pay.entity.PayDemo;

public interface PayDemoFacade {

	/**
	 * 示例功能 通过ID 获取
	 * @param String id
	 * @return TbDemo
	 */
	public PayDemo get(String id);
	
	/**
	 * 示例功能 查询不分页
	 * @param PayDemo
	 * @return List<TbDemo>
	 */
	public List<PayDemo> findList(PayDemo tbDemo) ;
	
	/**
	 * 示例功能 查询分页
	 * @param Page<TbDemo> page,TbDemo
	 * @return Page<TbDemo>
	 */
	public Page<PayDemo> findPage(Page<PayDemo> page, PayDemo tbDemo) ;
	
	/**
	 * 示例功能 保存
	 * @param PayDemo
	 * @return void
	 */
	public void save(PayDemo tbDemo);
	
	/**
	 * 示例功能 删除
	 * @param PayDemo
	 * @return void
	 */
	public void delete(PayDemo tbDemo);
}
