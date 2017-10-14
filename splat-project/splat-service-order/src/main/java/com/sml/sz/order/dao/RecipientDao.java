/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.order.entity.Recipient;

/**
 * 收件人DAO接口
 * @author 李千超
 * @version 2016-03-30
 */
@MyBatisDao
public interface RecipientDao extends CrudDao<Recipient> {
	/**
	 * 查询收件人
	 * @param orderNo
	 * @return
	 */
	public List<Recipient> findRecipientList(String orderNo);
}