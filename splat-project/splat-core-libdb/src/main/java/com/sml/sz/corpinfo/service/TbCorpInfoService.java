/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.corpinfo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.corpinfo.entity.TbCorpInfo;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
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
public interface TbCorpInfoService {

		public TbCorpInfo get(String id);
		
		public List<TbCorpInfo> findList(TbCorpInfo tbCorpInfo);
		
		public Page<TbCorpInfo> findPage(Page<TbCorpInfo> page, TbCorpInfo tbCorpInfo);
		
		@Transactional(readOnly = false)
		public void save(TbCorpInfo tbCorpInfo);
		
		@Transactional(readOnly = false)
		public void delete(TbCorpInfo tbCorpInfo);
	}
	
	