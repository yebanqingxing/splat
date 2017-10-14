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
import com.sml.sz.sys.dao.RoleDao;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.Role;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 机构Service
 * @author splat
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends TreeService<RoleDao, Role> {

	
	/**
	 * 角色分配页面管理员调用
	 * @param platType
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> findAll(Role role){
		return super.findList(role);
	}
	
	@Transactional(readOnly = true)
	public Page<Role> findList(Page<Role> page,Role role){
		return super.findPage(page, role);
	}
}
