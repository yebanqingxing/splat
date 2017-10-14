/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.corpinfo.dao;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.corpinfo.entity.TbCorpInfo;

/**
 * 企业客户维护DAO接口
 * @author 黄诗源
 * @version 2016-03-04
 */
@MyBatisDao
public interface TbCorpInfoDao extends CrudDao<TbCorpInfo> {
	
}