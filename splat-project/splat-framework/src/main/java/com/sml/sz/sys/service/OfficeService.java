/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.TreeService;
import com.sml.sz.sys.dao.OfficeDao;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 机构Service
 * @author splat
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
	
	@Transactional(readOnly = true)
	public Office get(String id){
		return super.get(id);
	}
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}
	/**
	 * 角色分配页面管理员调用
	 * @param platType
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Office> findAll(Office office){
		return super.findList(office);
	}
	/**
	 * 机构列表以及机构树查询Service
	 * @param isAll 是否查询所有
	 * @return
	 */
	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public Page<Office> findList(Page<Office> page,Office office){
		return super.findPage(page, office);
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		User user = UserUtils.getUser();
		if(!(user.getCompany().getId().equals(office.getParentId())||user.isAdmin())){//排除创建机构时超越用户权限范围
			office.setParent(user.getCompany());
		}
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = true)
	public int count(Office office){
		User user = new User();
		user.setOffice(office);
		List<User> list = super.findUserByOfficeId(user);
		return list.size();
	}
}
