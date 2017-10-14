package com.sml.sz.airport.service;

import java.util.List;


import com.sml.sz.airport.entity.Airport;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;

public interface AirportService {

	/**
	 * 机场 通过ID 获取
	 * @param String id
	 * @return Airport
	 */
	public Airport get(String id);
	
	/**
	 * 机场 查询不分页
	 * @param Airport
	 * @return List<Airport>
	 */
	public List<Airport> findList(Airport airport);
	
	/**
	 * 机场 查询分页
	 * @param Page<Airport> page,Airport
	 * @return Page<Airport>
	 */
	public Page<Airport> findPage(Page<Airport> page, Airport airport);
	
	/**
	 * 机场 保存
	 * @param Airport
	 * @return void
	 */
	public void save(Airport airport);
	
	/**
	 * 机场 删除
	 * @param Airport
	 * @return void
	 */
	public void delete(Airport airport);
	
	/**
	 * 
	 * @param airportCode
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月15日
	 * 作用：根据三字节码查询实体
	 */
	public Airport getAirportByAirportCode(String airportCode);
/**
 * 
 * @param rt
 * @return
 * @auth 冯俊伟
 * @date 2016年3月16日
 * 作用：	根据始发城市查找始发机场。为了前台方便取值，把查到的值放到RT对象
 */
	public Rt updateRt(Rt rt);
	
	public String getComput(List<Route> routeList);
}
