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
import com.sml.sz.order.entity.EndorsesheetAppendix;
import com.sml.sz.order.dao.EndorsesheetAppendixDao;

/**
 * 改签单附件Service
 * @author 李千超
 * @version 2016-03-15
 */
@Service(value="endorsesheetAppendixServiceFacade")
@Transactional(readOnly = true)
public class EndorsesheetAppendixService extends CrudService<EndorsesheetAppendixDao, EndorsesheetAppendix> implements EndorsesheetAppendixServiceFacade{

	@Autowired
	EndorsesheetAppendixDao endorsesheetAppendixDao; 
	/**
	 * 改签单附件 通过ID 获取
	 * @param String id
	 * @return EndorsesheetAppendix
	 */
	public EndorsesheetAppendix get(String id) {
		return super.get(id);
	}
	
	/**
	 * 改签单附件 查询不分页
	 * @param EndorsesheetAppendix
	 * @return List<EndorsesheetAppendix>
	 */
	public List<EndorsesheetAppendix> findList(EndorsesheetAppendix endorsesheetAppendix) {
		return super.findList(endorsesheetAppendix);
	}
	
	/**
	 * 改签单附件 查询分页
	 * @param Page<EndorsesheetAppendix> page,EndorsesheetAppendix
	 * @return Page<EndorsesheetAppendix>
	 */
	public Page<EndorsesheetAppendix> findPage(Page<EndorsesheetAppendix> page, EndorsesheetAppendix endorsesheetAppendix) {
		return super.findPage(page, endorsesheetAppendix);
	}
	
	/**
	 * 改签单附件 保存
	 * @param EndorsesheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(EndorsesheetAppendix endorsesheetAppendix) {
		super.save(endorsesheetAppendix);
	}
	
	/**
	 * 改签单附件 删除
	 * @param EndorsesheetAppendix
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(EndorsesheetAppendix endorsesheetAppendix) {
		super.delete(endorsesheetAppendix);
	}
	
}