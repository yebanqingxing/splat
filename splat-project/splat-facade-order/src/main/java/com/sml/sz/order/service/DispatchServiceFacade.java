package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.Dispatch;

/**
 * 行程单派送单Service
 * @author 李千超
 * @version 2016-03-30
 */
public interface DispatchServiceFacade {
	/**
	 * 行程单派送单 通过ID 获取
	 * @param String id
	 * @return Dispatch
	 */
	public Dispatch get(String id);
	
	/**
	 * 行程单派送单 查询不分页
	 * @param Dispatch
	 * @return List<Dispatch>
	 */
	public List<Dispatch> findList(Dispatch dispatch);
	
	/**
	 * 行程单派送单 查询分页
	 * @param Page<Dispatch> page,Dispatch
	 * @return Page<Dispatch>
	 */
	public Page<Dispatch> findPage(Page<Dispatch> page, Dispatch dispatch);
	
	/**
	 * 行程单派送单 保存
	 * @param Dispatch
	 * @return void
	 */
	public void save(Dispatch dispatch);
	
	/**
	 * 行程单派送单 删除
	 * @param Dispatch
	 * @return void
	 */
	public void delete(Dispatch dispatch);
}
