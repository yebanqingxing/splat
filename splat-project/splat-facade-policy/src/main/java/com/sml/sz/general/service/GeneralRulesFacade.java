package com.sml.sz.general.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.general.entity.GeneralRules;

import com.sml.sz.pubobj.PubObj;

import com.sml.sz.sys.pnr.Rt;


public interface GeneralRulesFacade {

	/**
	 * 国际政策总则的增删改查 通过ID 获取
	 * @param String id
	 * @return TbGeneralRules
	 */
	public GeneralRules get(String id);
	
	/**
	 * 判断航司是否存在
	 * @param codeBrr
	 * @param airPortList
	 * @return
	 */
	public Boolean checkAirline(String codeBrr,List<PubObj> airPortList);
	
	/**
	 * 国际政策总则的增删改查 查询不分页
	 * @param TbGeneralRules
	 * @return List<TbGeneralRules>
	 */
	public List<GeneralRules> findList(GeneralRules tbGeneralRules);
	
	/**
	 * 国际政策总则的增删改查 查询分页
	 * @param Page<TbGeneralRules> page,TbGeneralRules
	 * @return Page<TbGeneralRules>
	 */
	public Page<GeneralRules> findPage(Page<GeneralRules> page, GeneralRules tbGeneralRules);
	
	/**
	 * 国际政策总则的增删改查 保存
	 * @param TbGeneralRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(GeneralRules tbGeneralRules);
	
	/**
	 * 国际政策总则的增删改查 删除
	 * @param TbGeneralRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(GeneralRules tbGeneralRules);
	
	/**
	 * 导入总则
	 * @param headPic
	 */
	@Transactional(readOnly = false)
	public void addGeneralRules(String fileName,String realPath);

	/**
	 * 
	 * @param genLists
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月17日
	 * 作用：在前台输入航司后，查询出来的总则集合中匹配起点与终点
	 * 		主要是通过总则的规则
	 */
//	public Rt findListsByGz(List<GeneralRules> genLists,Rt rt,String writer);


}
