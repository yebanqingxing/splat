package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.VoidsheetCirculation;

/**
 * 废票单流转 
 * 
 */
public interface VoidsheetCirculationServiceFacade {
	/**
	 * 废票单流转 通过ID 获取
	 * @param String id
	 * @return VoidsheetCirculation
	 */
	public VoidsheetCirculation get(String id) ;
	
	/**
	 * 废票单流转 查询不分页
	 * @param VoidsheetCirculation
	 * @return List<VoidsheetCirculation>
	 */
	public List<VoidsheetCirculation> findList(VoidsheetCirculation voidsheetCirculation);
	
	/**
	 * 废票单流转 查询分页
	 * @param Page<VoidsheetCirculation> page,VoidsheetCirculation
	 * @return Page<VoidsheetCirculation>
	 */
	public Page<VoidsheetCirculation> findPage(Page<VoidsheetCirculation> page, VoidsheetCirculation voidsheetCirculation);
	
	/**
	 * 废票单流转 保存
	 * @param VoidsheetCirculation
	 * @return void
	 */
	public void save(VoidsheetCirculation voidsheetCirculation);
	
	/**
	 * 废票单流转 删除
	 * @param VoidsheetCirculation
	 * @return void
	 */
	public void delete(VoidsheetCirculation voidsheetCirculation);
}
