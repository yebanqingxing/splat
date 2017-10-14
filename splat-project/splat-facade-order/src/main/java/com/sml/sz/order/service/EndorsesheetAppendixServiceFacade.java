package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.EndorsesheetAppendix;

/**
 * 改签单附件Entity
 * @author 李千超
 * @version 2016-03-15
 */
public interface EndorsesheetAppendixServiceFacade {
	/**
	 * 改签单附件 通过ID 获取
	 * @param String id
	 * @return EndorsesheetAppendix
	 */
	public EndorsesheetAppendix get(String id);
	
	/**
	 * 改签单附件 查询不分页
	 * @param EndorsesheetAppendix
	 * @return List<EndorsesheetAppendix>
	 */
	public List<EndorsesheetAppendix> findList(EndorsesheetAppendix endorsesheetAppendix) ;
	
	/**
	 * 改签单附件 查询分页
	 * @param Page<EndorsesheetAppendix> page,EndorsesheetAppendix
	 * @return Page<EndorsesheetAppendix>
	 */
	public Page<EndorsesheetAppendix> findPage(Page<EndorsesheetAppendix> page, EndorsesheetAppendix endorsesheetAppendix) ;
	
	/**
	 * 改签单附件 保存
	 * @param EndorsesheetAppendix
	 * @return void
	 */
	 
	public void save(EndorsesheetAppendix endorsesheetAppendix);
	
	/**
	 * 改签单附件 删除
	 * @param EndorsesheetAppendix
	 * @return void
	 */
	 
	public void delete(EndorsesheetAppendix endorsesheetAppendix);
}
