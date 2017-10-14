package com.sml.sz.policy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.general.entity.GeneralRules;
import com.sml.sz.policy.entity.PolicyRules;
import com.sml.sz.pubobj.ErrInfo;
import com.sml.sz.pubobj.PubObj;
import com.sml.sz.sys.pnr.Rt;

public interface PolicyRulesFacade {

	/**
	 * 国际政策的增删改查 通过ID 获取
	 * @param String id
	 * @return TbPolicyRules
	 */
	public PolicyRules get(String id);
	
	/**
	 * 国际政策的增删改查 查询不分页
	 * @param PolicyRules
	 * @return List<TbPolicyRules>
	 */
	public List<PolicyRules> findList(PolicyRules tbPolicyRules);
	
	/**
	 * 国际政策的增删改查 查询分页
	 * @param Page<TbPolicyRules> page,TbPolicyRules
	 * @return Page<TbPolicyRules>
	 */
	public Page<PolicyRules> findPage(Page<PolicyRules> page, PolicyRules tbPolicyRules);
	
	/**
	 * 国际政策的增删改查 保存
	 * @param PolicyRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(PolicyRules tbPolicyRules);
	
	/**
	 * 判断航司是否存在
	 * @param codeBrr
	 * @param airPortList
	 * @return
	 */
	public Boolean checkAirline(String codeBrr,List<PubObj> airPortList);
	
	/**
	 * 国际政策的增删改查 删除
	 * @param PolicyRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(PolicyRules tbPolicyRules);
	
	
	/**
	 * 
	 * @param beginCity 起点
	 * @param endCity	终点
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月18日
	 * 作用：
	 */
	@Transactional(readOnly = false)
	public Rt getPolicyRulesListByQz(Rt rt,List<GeneralRules> generalRulesList);
	
//	@Transactional(readOnly = false)
//	public List<PolicyRules>  getPolicyRulesListByQz1(String beginCity,String endCity,String comput);
	
	@Transactional(readOnly = false)
	public List<ErrInfo> addPolicyRules(String fileName,String realPath);
}
