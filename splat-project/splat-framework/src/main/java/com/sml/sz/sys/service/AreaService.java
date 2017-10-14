/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.service.TreeService;
import com.sml.sz.sys.dao.AreaDao;
import com.sml.sz.sys.entity.Area;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 区域Service
 * @author splat
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
