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
import com.sml.sz.order.entity.Dispatch;
import com.sml.sz.order.dao.DispatchDao;

/**
 * 行程单派送单Service
 * @author 李千超
 * @version 2016-03-30
 */
@Service(value="dispatchServiceFacade")
@Transactional(readOnly = true)
public class DispatchService extends CrudService<DispatchDao, Dispatch> implements DispatchServiceFacade{

	@Autowired
	DispatchDao dispatchDao;
	/**
	 * 行程单派送单 通过ID 获取
	 * @param String id
	 * @return Dispatch
	 */
	public Dispatch get(String id) {
		return super.get(id);
	}
	
	/**
	 * 行程单派送单 查询不分页
	 * @param Dispatch
	 * @return List<Dispatch>
	 */
	public List<Dispatch> findList(Dispatch dispatch) {
		return super.findList(dispatch);
	}
	
	/**
	 * 行程单派送单 查询分页
	 * @param Page<Dispatch> page,Dispatch
	 * @return Page<Dispatch>
	 */
	public Page<Dispatch> findPage(Page<Dispatch> page, Dispatch dispatch) {
		return super.findPage(page, dispatch);
	}
	
	/**
	 * 行程单派送单 保存
	 * @param Dispatch
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Dispatch dispatch) {
		super.save(dispatch);
	}
	
	/**
	 * 行程单派送单 删除
	 * @param Dispatch
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Dispatch dispatch) {
		super.delete(dispatch);
	}
	
}