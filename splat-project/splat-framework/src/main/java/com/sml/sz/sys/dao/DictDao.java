/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author splat
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
