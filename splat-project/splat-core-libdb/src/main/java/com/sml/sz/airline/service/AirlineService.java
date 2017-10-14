/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airline.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.airline.entity.Airline;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.sys.pnr.Rt;

/**
 * 航空公司查询Service
 * @author 冯俊伟
 * @version 2016-03-14
 */

public interface AirlineService {

	/**
	 * 航空公司查询 通过ID 获取
	 * @param String id
	 * @return Airline
	 */
	public Airline get(String id) ;
	
	/**
	 * 航空公司查询 查询不分页
	 * @param Airline
	 * @return List<Airline>
	 */
	public List<Airline> findList(Airline airline);
	
	/**
	 * 航空公司查询 查询分页
	 * @param Page<Airline> page,Airline
	 * @return Page<Airline>
	 */
	public Page<Airline> findPage(Page<Airline> page, Airline airline);
	/**
	 * 航空公司查询 保存
	 * @param Airline
	 * @return void
	 */
	public void save(Airline airline);
	
	
	/**
	 * 航空公司查询 删除
	 * @param Airline
	 * @return void
	 */
	public void delete(Airline airline) ;
	
	/**
	 * 
	 * @param airCode
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月15日
	 * 作用：通过二字码查询实体
	 */
	public Airline getAirByAirCode(String airCode);
	
	
	/**
	 * 
	 * @param rt
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月16日
	 * 作用：为航班及航空公司的查询以及拼接
	 */
		public Rt updateRtByFlyCompanys(Rt rt);
		
		public Rt updateRtByFlyCompanys1(Rt rt);
	
}