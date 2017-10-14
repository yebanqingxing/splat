/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.sys.entity.Log;

/**
 * 日志DAO接口
 * @author splat
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
