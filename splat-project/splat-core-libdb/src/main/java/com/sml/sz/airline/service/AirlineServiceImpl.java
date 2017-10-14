/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.airline.dao.AirlineDao;
import com.sml.sz.airline.entity.Airline;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;


/**
 * 航空公司查询Service
 * @author 冯俊伟
 * @version 2016-03-14
 */
@Service(value="airlineFacade")
@Transactional(readOnly = true)
public class AirlineServiceImpl extends CrudService<AirlineDao, Airline> implements AirlineService{
	@Autowired
	private AirlineDao airlineDao;
	/**
	 * 航空公司查询 通过ID 获取
	 * @param String id
	 * @return Airline
	 */
	public Airline get(String id) {
		return super.get(id);
	}
	
	/**
	 * 航空公司查询 查询不分页
	 * @param Airline
	 * @return List<Airline>
	 */
	public List<Airline> findList(Airline airline) {
		//先获取缓存中有没有数据
		Object airlineInfoList = EhCacheUtils.get("airlineInfoList");
		if(null==airlineInfoList){
			//为空的话是走数据库查询值
			List<Airline> tbAirlineInfoList = super.findList(airline);
			airlineInfoList=tbAirlineInfoList;
			EhCacheUtils.put("airlineInfoList", tbAirlineInfoList);
		}
		return (List<Airline>)airlineInfoList;
	}
	
	/**
	 * 航空公司查询 查询分页
	 * @param Page<Airline> page,Airline
	 * @return Page<Airline>
	 */
	public Page<Airline> findPage(Page<Airline> page, Airline airline) {
		return super.findPage(page, airline);
	}
	
	/**
	 * 航空公司查询 保存
	 * @param Airline
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Airline airline) {
		super.save(airline);
		if(null != airline){
			EhCacheUtils.remove("airlineInfoList");
		}
	}
	
	/**
	 * 航空公司查询 删除
	 * @param Airline
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Airline airline) {
		super.delete(airline);
		if(null != airline){
			EhCacheUtils.remove("airlineInfoList");
		}
	}

	public Airline getAirByAirCode(String airCode) {
		return airlineDao.getAirByAirCode(airCode);
	}

	public Rt updateRt(Rt rt) {
		// TODO Auto-generated method stub
		return null;
	}

	public Rt updateRtByFlyCompanys(Rt rt) {
		ArrayList<String> flyCompanys = new ArrayList<String>();
		ArrayList<String> flyNums = new ArrayList<String>();
		String carrier = rt.getCarrier();
		String[] split = carrier.split("\\/");
		for (int i = 0; i < split.length; i++) {
			Airline airByAirCode = airlineDao.getAirByAirCode(split[i].replaceAll("\\d+",""));
			flyCompanys.add(i, airByAirCode.getAirlineNameCn());
			flyNums.add(i, split[i] );
//			System.out.println(airByAirCode.getAirlineNameCn());
		}
		rt.setFlyCompany(flyCompanys);
		rt.setFlyNum(flyNums);
		return rt;
	}
	
	
	public Rt updateRtByFlyCompanys1(Rt rt) {
		List<Route> routeList = rt.getRoute_list();
		int routeSize = routeList.size();
		//去程起点
		Route route = new Route();
		//中转点
		Route route1 = new Route();
		//去程终点
		Route route2 = new Route();
		//回程终点
		Route route3 = new Route();
//		if(routeSize == 4){
//			List<Route> routeList1 = new ArrayList<Route>();
//			route = routeList.get(0);
//			route.setTocity(routeList.get(1).getTocity());
//			route3 = routeList.get(routeSize-1);
//			route.setFromCity(routeList.get(1).getTocity());
//			routeList1.add(route);
//			routeList1.add(route3);
//			routeList = routeList1;
//		}
//		if(routeSize == 6){
//			List<Route> routeList1 = new ArrayList<Route>();
//			route = routeList.get(0);
//			route3 = routeList.get(routeSize-1);
//			routeList1.add(route);
//			routeList1.add(route3);
//			routeList = routeList1;
//		}
		rt.setRoute_list(routeList);
		return rt;
	}
	
}