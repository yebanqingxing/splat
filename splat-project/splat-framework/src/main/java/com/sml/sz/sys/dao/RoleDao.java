/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.TreeDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.sys.entity.Role;

/**
 * 角色DAO接口
 * @author splat
 * @version 2013-12-05
 */
@MyBatisDao
public interface RoleDao extends TreeDao<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

}
