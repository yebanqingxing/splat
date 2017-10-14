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
import com.sml.sz.order.entity.VoidsheetAppendix;
import com.sml.sz.order.dao.VoidsheetAppendixDao;

/**
 * 废票附件Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="voidsheetAppendixServiceFacade")
@Transactional(readOnly = true)
public class VoidsheetAppendixService extends CrudService<VoidsheetAppendixDao, VoidsheetAppendix> implements VoidsheetAppendixServiceFacade{

	@Autowired
	VoidsheetAppendixDao voidsheetAppendixDao;
	/**
	 * 废票附件 通过ID 获取
	 * @param String id
	 * @return VoidsheetAppendix
	 */
	public VoidsheetAppendix get(String id) {
		return super.get(id);
	}
	
	/**
	 * 废票附件 查询不分页
	 * @param VoidsheetAppendix
	 * @return List<VoidsheetAppendix>
	 */
	public List<VoidsheetAppendix> findList(VoidsheetAppendix voidsheetAppendix) {
		return super.findList(voidsheetAppendix);
	}
	
	/**
	 * 废票附件 查询分页
	 * @param Page<VoidsheetAppendix> page,VoidsheetAppendix
	 * @return Page<VoidsheetAppendix>
	 */
	public Page<VoidsheetAppendix> findPage(Page<VoidsheetAppendix> page, VoidsheetAppendix voidsheetAppendix) {
		return super.findPage(page, voidsheetAppendix);
	}
	
	/**
	 * 废票附件 保存
	 * @param VoidsheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(VoidsheetAppendix voidsheetAppendix) {
		super.save(voidsheetAppendix);
	}
	
	/**
	 * 废票附件 删除
	 * @param VoidsheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(VoidsheetAppendix voidsheetAppendix) {
		super.delete(voidsheetAppendix);
	}
	
}