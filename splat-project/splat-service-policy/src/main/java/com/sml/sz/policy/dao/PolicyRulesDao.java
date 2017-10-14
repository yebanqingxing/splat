/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.policy.dao;

import java.util.List;

import com.sml.sz.common.persistence.CrudDao;
import com.sml.sz.common.persistence.annotation.MyBatisDao;
import com.sml.sz.policy.entity.PolicyRules;

/**
 * 国际政策的增删改查DAO接口
 * @author 张权
 * @version 2016-03-07
 */
@MyBatisDao
public interface PolicyRulesDao extends CrudDao<PolicyRules> {
	public PolicyRules getPolicyRulesListByQz(String beginCity,String endCity);
	
	public List<PolicyRules>  getPolicyRulesListByQz1(PolicyRules policyRules);

}