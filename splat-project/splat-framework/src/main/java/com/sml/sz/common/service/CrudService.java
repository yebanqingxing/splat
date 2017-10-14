/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.sys.dao.UserDao;
import com.sml.sz.sys.entity.User;

/**
 * Service基类
 * @author splat
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	/**
	 * 批量插入
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void saveList(@Param("list")List<T> list){
		dao.saveList(list);
	}
	/**
	 * 根据office_id查询员工
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user){
		return userDao.findUserByOfficeId(user);
	}
	
}
