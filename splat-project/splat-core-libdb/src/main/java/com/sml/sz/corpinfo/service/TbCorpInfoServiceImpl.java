/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.corpinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.corpinfo.dao.TbCorpInfoDao;
import com.sml.sz.corpinfo.entity.TbCorpInfo;

/**
 * 企业客户维护Service
 * @author 黄诗源
 * @version 2016-03-04
 */
@Service
@Transactional(readOnly = true)
public class TbCorpInfoServiceImpl extends CrudService<TbCorpInfoDao, TbCorpInfo> implements TbCorpInfoService{

	public TbCorpInfo get(String id) {
		return super.get(id);
	}
	
	public List<TbCorpInfo> findList(TbCorpInfo tbCorpInfo) {
		//先获取缓存中有没有数据
		Object corpInfoList = EhCacheUtils.get("corpInfoList");
		if(null==corpInfoList){
			//为空的话是走数据库查询值
			List<TbCorpInfo> tbCorpInfoList = super.findList(tbCorpInfo);
			corpInfoList=tbCorpInfoList;
			EhCacheUtils.put("corpInfoList", tbCorpInfoList);
		}
		return (List<TbCorpInfo>)corpInfoList;
	}
	
	public Page<TbCorpInfo> findPage(Page<TbCorpInfo> page, TbCorpInfo tbCorpInfo) {
		return super.findPage(page, tbCorpInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TbCorpInfo tbCorpInfo) {
		super.save(tbCorpInfo);
		if(null != tbCorpInfo){
			EhCacheUtils.remove("corpInfoList");
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbCorpInfo tbCorpInfo) {
		super.delete(tbCorpInfo);
		if(null != tbCorpInfo){
			EhCacheUtils.remove("corpInfoList");
		}
	}
	
}