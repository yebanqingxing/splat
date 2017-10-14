package com.sml.sz.order.service;

import java.util.List;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.VoidsheetAppendix;
/**
 * 废票附件
 * @author lqc
 *
 */
public interface VoidsheetAppendixServiceFacade {
	/**
	 * 废票附件 通过ID 获取
	 * @param String id
	 * @return VoidsheetAppendix
	 */
	public VoidsheetAppendix get(String id);
	
	/**
	 * 废票附件 查询不分页
	 * @param VoidsheetAppendix
	 * @return List<VoidsheetAppendix>
	 */
	public List<VoidsheetAppendix> findList(VoidsheetAppendix voidsheetAppendix);
	
	/**
	 * 废票附件 查询分页
	 * @param Page<VoidsheetAppendix> page,VoidsheetAppendix
	 * @return Page<VoidsheetAppendix>
	 */
	public Page<VoidsheetAppendix> findPage(Page<VoidsheetAppendix> page, VoidsheetAppendix voidsheetAppendix);
	
	/**
	 * 废票附件 保存
	 * @param VoidsheetAppendix
	 * @return void
	 */
	public void save(VoidsheetAppendix voidsheetAppendix);
	
	/**
	 * 废票附件 删除
	 * @param VoidsheetAppendix
	 * @return void
	 */
	public void delete(VoidsheetAppendix voidsheetAppendix);
}
