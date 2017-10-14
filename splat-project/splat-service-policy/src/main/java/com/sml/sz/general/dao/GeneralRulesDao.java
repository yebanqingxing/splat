/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.general.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.general.entity.GeneralRules;

/**
 * 国际政策总则的增删改查DAO接口
 * @author 张权
 * @version 2016-03-09
 */
@MyBatisDao
public interface GeneralRulesDao extends CrudDao<GeneralRules> {
	public String findMileage(String mileAge);
	
}