/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.airport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sml.sz.EhCacheUtils;
import com.sml.sz.airline.dao.AirlineDao;
import com.sml.sz.airport.dao.AirportDao;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.city.dao.CityDao;
import com.sml.sz.city.entity.City;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.mileage.dao.MileageDao;
import com.sml.sz.mileage.entity.Mileage;
import com.sml.sz.sys.pnr.DateTool;
import com.sml.sz.sys.pnr.Fc;
import com.sml.sz.sys.pnr.PersonTypeInfo;
import com.sml.sz.sys.pnr.Qte;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;

/**
 * 机场Service
 * @author 张权
 * @version 2016-03-11
 */
@Service(value="AirportService")
@Transactional(readOnly = true)
public class AirportServiceImpl extends CrudService<AirportDao, Airport> implements AirportService{

	@Autowired
	private AirportDao airportDao;
	
	@Autowired
	private AirlineDao airlineDao;
	@Autowired
	private MileageDao mileageDao;
	@Autowired
	private CityDao cityDao;
	/**
	 * 机场 通过ID 获取
	 * @param String id
	 * @return Airport
	 */
	public Airport get(String id) {
		return super.get(id);
	}
	
	/**
	 * 机场 查询不分页
	 * @param Airport
	 * @return List<Airport>
	 */
	@SuppressWarnings("unchecked")
	public List<Airport> findList(Airport airport) {
		//先获取缓存中有没有数据
		Object airportInfoList = EhCacheUtils.get("airportInfoList");
		if(null==airportInfoList){
			//为空的话是走数据库查询值
			List<Airport> tbAirportInfoList = super.findList(airport);
			airportInfoList=tbAirportInfoList;
			EhCacheUtils.put("airportInfoList", tbAirportInfoList);
		}
		return (List<Airport>)airportInfoList;
	}
	
	/**
	 * 机场 查询分页
	 * @param Page<Airport> page,Airport
	 * @return Page<Airport>
	 */
	public Page<Airport> findPage(Page<Airport> page, Airport airport) {
		return super.findPage(page, airport);
	}
	
	/**
	 * 机场 保存
	 * @param Airport
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Airport airport) {
		super.save(airport);
		if(null != airport){
			EhCacheUtils.remove("airportInfoList");
		}
	}
	
	/**
	 * 机场 删除
	 * @param Airport
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Airport airport) {
		super.delete(airport);
		if(null != airport){
			EhCacheUtils.remove("airportInfoList");
		}
	}

	public Airport getAirportByAirportCode(String airportCode) {
		return airportDao.getAirportByAirportCode(airportCode);
	}

	/**
	 *根据PNR解析出来的内容判断行程类型以及得出主FC、SPA和AddOn段航程
	 */
//	public Rt updateRt(Rt rt) {
//		List<Route> routeList = rt.getRoute_list();
//		List<Fc> fcList = rt.getFc();
//		int routeListSize = routeList.size();
//		int fcListSize = fcList.size();
//				if(routeListSize == 1){
//					//不做任何判断，国际单程必须计奖
//				}else if(routeListSize == 2){
//					//下面已判断单程还是往返，主要判断几个FC.
//					if(fcListSize == 2){
//						//两段往返的下面已做计算，主要判断单程两段几个的主FC
//						//根据大洲,大区，跨境，进行判断
//						
//					}
//					
//				//三段，单程，含国内一段
//				}else if(routeListSize == 3){
//				//判断有几个FC,如果只有一段，主FC一定含起点航段，判断主FC中有几个OD
//					if(fcListSize == 1){
//						//如果第一段终点与FC的终点相同，那么久只含有一个OD,主FC就是第一段
//						if(routeList.get(0).getTocity().equals(fcList.get(0).getToCity())){
//						//将本段航程运价计算出来
//						Double flyPrice = Double.parseDouble(fcList.get(0).getFcNum())*Double.parseDouble(fcList.get(0).getRate());
//						routeList.get(0).setFlyPrice(flyPrice);
//						routeList.get(0).setIsFc("Y");
//						//计算剩下两段航程的的运价
//						//将两段航程的运价按照公里数比例拆分开分别插入到每段航程中
//						//取出第一段航程的公里数
//						Mileage mileage = mileageDao.findMileage(routeList.get(1).getFromCityCode()+"-"+routeList.get(1).getToCityCode());
//						if(null == mileage){
//							mileage = mileageDao.findMileage(routeList.get(1).getToCityCode()+"-"+routeList.get(1).getFromCityCode());
//						}
//						//取出第二段航程的公里数
//						Mileage mileage1 = mileageDao.findMileage(routeList.get(2).getFromCityCode()+"-"+routeList.get(2).getToCityCode());
//						if(null == mileage1){
//							mileage1 = mileageDao.findMileage(routeList.get(2).getToCityCode()+"-"+routeList.get(2).getFromCityCode());
//						}
//						//求出本段FC的总运价
//						Double fcPrice = Double.parseDouble(fcList.get(0).getFcNum())*Double.parseDouble(fcList.get(0).getRate());
//						//求出本段FC的总公里数
//						int mileSize = Integer.parseInt(mileage.getMileage())+Integer.parseInt(mileage1.getMileage());
//						//求公里数比例
//						Double miles = (double) (mileSize/Integer.parseInt(mileage.getMileage()));
//						//用第一段公里数的比例求出第一段航程的运价
//						Double fcFistPrice = miles*fcPrice;
//						//求出第二段航程的运价
//						Double fcSecondPrice = fcPrice - fcFistPrice;
//						//第三段航程为票面价减去FC航段价格
//						Double fcEndPrice = Double.parseDouble(rt.getQte().getFare()) - fcPrice;
//						//将两段航程的运价分别存入每段航程运价中
//						routeList.get(0).setFlyPrice(fcFistPrice);
//						//并将两段几位主FC
//						routeList.get(1).setIsFc("Y");
//						routeList.get(1).setFlyPrice(fcSecondPrice);
//						routeList.get(2).setIsFc("Y");
//						routeList.get(2).setFlyPrice(fcEndPrice);
//					
//						//如果主FC含两段，那么就是起点与第二段的航程
//						}else if(routeList.get(1).getTocity().equals(fcList.get(0).getToCity())){
//							//将两段航程的运价按照公里数比例拆分开分别插入到每段航程中
//							//取出第一段航程的公里数
//							Mileage mileage = mileageDao.findMileage(routeList.get(0).getFromCityCode()+"-"+routeList.get(0).getToCityCode());
//							if(null == mileage){
//								mileage = mileageDao.findMileage(routeList.get(0).getToCityCode()+"-"+routeList.get(0).getFromCityCode());
//							}
//							//取出第二段航程的公里数
//							Mileage mileage1 = mileageDao.findMileage(routeList.get(2).getFromCityCode()+"-"+routeList.get(2).getToCityCode());
//							if(null == mileage1){
//								mileage1 = mileageDao.findMileage(routeList.get(2).getToCityCode()+"-"+routeList.get(2).getFromCityCode());
//							}
//							//求出本段FC的总运价
//							Double fcPrice = Double.parseDouble(fcList.get(0).getFcNum())*Double.parseDouble(fcList.get(0).getRate());
//							//求出本段FC的总公里数
//							int mileSize = Integer.parseInt(mileage.getMileage())+Integer.parseInt(mileage1.getMileage());
//							//求公里数比例
//							Double miles = (double) (mileSize/Integer.parseInt(mileage.getMileage()));
//							//用第一段公里数的比例求出第一段航程的运价
//							Double fcFistPrice = miles*fcPrice;
//							//求出第二段航程的运价
//							Double fcSecondPrice = fcPrice - fcFistPrice;
//							//第三段航程为票面价减去FC航段价格
//							Double fcEndPrice = Double.parseDouble(rt.getQte().getFare()) - fcPrice;
//							//将两段航程的运价分别存入每段航程运价中
//							routeList.get(0).setFlyPrice(fcFistPrice);
//							//并将两段几位主FC
//							routeList.get(0).setIsFc("Y");
//							routeList.get(0).setFlyPrice(fcSecondPrice);
//							routeList.get(2).setIsFc("Y");
//							routeList.get(2).setFlyPrice(fcEndPrice);
//						//第二段的终点与FC的终点相等，说明整段都为主FC
//						}else if(routeList.get(2).getTocity().equals(fcList.get(0).getToCity())){
//							//如果整段都是一个主FC，不用计算公里比例，可将总价分摊到每个航段中
//							Double price = Double.parseDouble(rt.getQte().getFare());
//							for(int i = 0; i < routeListSize; i++){
//								routeList.get(i).setFlyPrice(price/3);
//							}
//						}
//					}else if(fcListSize == 2){
//						
//						//3航程有两个FC
////						if(){
////							
////						}
//						
//					}
//					//如果航程为4段（往返）
//				}else if(routeList.size() == 4){
//					//首先判断有几个FC,如果只有两个FC，去程一个，回程一个
//					//如果FC只有两个，说明往返各占一个主FC
//					if(fcListSize == 2){
//								//如果fc航段的终点为第一段航程的终点，那么这段就是一个Fc，并且为主FC
////								if(fc.getToCity().equals(route.getTocity())){
////									
////								
////						}
//					//如果有三个fc,麻烦啦。。
//					}else if(fcListSize == 3){
//						
//					//如果每段都是一个FC，比三段好那么一点点，每段都是FC，不用计算每段航程的价格
//					}else if(fcListSize == 4){
//						
//					}
//				}else if(routeListSize == 6){
//					
//				}	
////		int j = 0;
////		List<Route> zzRoutes = rt.getZzRoutes();
//		for (int i = 0; i < routeList.size(); i++) {
//			//判断中转站
////			if(i<routeList.size()-1&&routeList.get(i).getTocity().equals(routeList.get(i+1).getFromCity())){
////				Airport airportByAirportCode = getAirportByAirportCode(routeList.get(i).getTocity());
////				routeList.get(i).setMiddleAirport(airportByAirportCode.getAirportNameCn());
////				routeList.get(i).setTocity(routeList.get(i+1).getTocity());
////				routeList.get(i).setToTime(routeList.get(i+1).getToTime());
////				if(i<routeList.size()-2&&!routeList.get(i+1).getTocity().equals(routeList.get(i).getFromCity()) ){
////					
////					routeList.remove(routeList.get(i+1));
////				}
////				System.out.println(i+"----"+routeList.get(i));
////				zzRoutes.add(i, routeList.get(i));
////				j++;
////			}else {
////				zzRoutes.add(i, routeList.get(i));
////			}
//
//			Airport airportByAirportCode = airportDao.getAirportByAirportCode(routeList.get(i).getFromCity());
//			routeList.get(i).setFromCityCode(airportByAirportCode.getAirportCode());
//			routeList.get(i).setFromCity(airportByAirportCode.getAirportNameCn());
//			routeList.get(i).setFromCountyName(airportByAirportCode.getCountryNameCn());
//			Airport airportByAirportCode2 = airportDao.getAirportByAirportCode(routeList.get(i).getTocity());
//			routeList.get(i).setToCityCode(airportByAirportCode2.getAirportCode());
//			routeList.get(i).setTocity(airportByAirportCode2.getAirportNameCn());
//			routeList.get(i).setToCountryName(airportByAirportCode2.getCountryNameCn());
//			Route route = routeList.get(i);
//			Mileage mileage = mileageDao.findMileage(route.getFromCityCode()+"-"+route.getToCityCode());
//			if(null == mileage){
//				mileage = mileageDao.findMileage(route.getToCityCode()+"-"+route.getFromCityCode());
//			}
//			routeList.get(i).setMileAge(mileage.getMileage());
//			//拼接时间-起飞时间
//			String date = routeList.get(i).getDate();
//			String fromTime = routeList.get(i).getFromTime();
//			StringBuffer fromTimeBuffer = new StringBuffer();
//			String fromHour = fromTime.substring(0,2);
//			String fromMin = fromTime.substring(2,4);
//			fromTimeBuffer.append(date);
//			fromTimeBuffer.append(" ");
//			fromTimeBuffer.append(fromHour);
//			fromTimeBuffer.append(":");
//			fromTimeBuffer.append(fromMin);
//			routeList.get(i).setFromTime(fromTimeBuffer.toString());
//			//拼接时间-降落时间
//			StringBuffer toTimeBuffer = new StringBuffer();
//			String toTime = routeList.get(i).getToTime();
//			String tomHour = toTime.substring(0,2);
//			String toMin = toTime.substring(2,4);
//			int parseFromTime = Integer.parseInt(fromTime);
//			int parseToTime = Integer.parseInt(toTime);
//			String shiJianCha = null;
//			if(parseFromTime>parseToTime){
//				String dataString = DateTool.dataString(date);
//				toTimeBuffer.append(dataString);
//				shiJianCha=parseToTime	+2400-parseFromTime+"";
//			}else{
//				toTimeBuffer.append(date);
//				shiJianCha=parseToTime-parseFromTime+"";
//			}
//			toTimeBuffer.append(" ");
//			toTimeBuffer.append(tomHour);
//			toTimeBuffer.append(":");
//			toTimeBuffer.append(toMin);
//			routeList.get(i).setToTime(toTimeBuffer.toString());
//			String hour = shiJianCha.substring(0, shiJianCha.length()-2);
//			String min = shiJianCha.substring(shiJianCha.length()-2, shiJianCha.length());
//			String flyTimeStr=hour+"小时"+min+"分";
//			routeList.get(i).setFlyTime(flyTimeStr);
//		}
////		for(int i = 0; i < routeList.size(); i++){
////			Route route = routeList.get(i);
////			Mileage mileage = mileageDao.findMileage(route.getFromCityCode()+"-"+route.getToCityCode());
////			routeList.get(i).setMileAge(mileage.getMileage());
////		}
////		//如果为一段航程，为单程
////		//获取去程起点国家
////		Route route = routeList.get(0);
////		Airport airportByAirportCode0 = airportDao.getAirportByAirportCode(route.getFromCityCode());
////		//获取去程国家终点
////		airportByAirportCode0 = airportDao.getAirportByAirportCode(route.getToCityCode());
////		String firstCountryGoOne = airportByAirportCode0.getContinentNameCn();
//		if(routeList.size() == 1){
//			rt.setTravelType("0");
//			//如果为偶数，则判断是2段还是4段或者是6段
//		}else if(routeList.size()%2 == 0){
//			//如果航程为两段，判断起点和终点是否为中国大陆，或香港；是则视为往返，不是则为单程
//			if(routeList.size() == 2){
//				//获取去程起点国家及所属大洲
//				Route routeFirst = routeList.get(0);
//				Airport airportByAirportCode = airportDao.getAirportByAirportCode(routeFirst.getFromCityCode());
//				routeFirst.setFromCountyName(airportByAirportCode.getCountryNameCn());
//				routeFirst.setContentName(airportByAirportCode.getContinentNameCn());
//				//获取去程终点国家及所属大洲
//				Route routeFirstEx = routeList.get(0);
//				airportByAirportCode = airportDao.getAirportByAirportCode(routeFirst.getToCityCode());
//				String firstCountryGo = airportByAirportCode.getCountryNameCn();
//				routeFirstEx.setFromCountyName(firstCountryGo);
//				routeFirstEx.setContentName(airportByAirportCode.getContinentNameCn());
//				//获取回程终点国家及所属大洲
//				Route routeLast = routeList.get(1);
//				Airport airportByAirportCode2 = airportDao.getAirportByAirportCode(routeLast.getToCityCode());
//				routeLast.setFromCountyName(airportByAirportCode2.getCountryNameCn());
//				routeLast.setContentName(airportByAirportCode2.getContinentNameCn());
//				//获取回程国家终点及所属大洲
//				Route routeLastEx = routeList.get(1);
//				airportByAirportCode2 = airportDao.getAirportByAirportCode(routeLast.getToCityCode());
//				String endCountryBack = airportByAirportCode.getCountryNameCn();
//				 routeLastEx.setContentName(endCountryBack);
//				//直接判断起点和终点是否相等，相等则视为往返,并且判断去程终点与回程起点一致(境外点:非中国大陆或中华人民共和国香港特别行政区)
//				if(routeFirst.getFromCityCode().equals(routeLast.getToCityCode()) && firstCountryGo.equals(endCountryBack)&&(!"中国大陆".equals(firstCountryGo) || !"中华人民共和国香港特别行政区".equals(firstCountryGo))){
//					System.out.println("往返1");
//					rt.setTravelType("1");
//					
//					//如果出发点是中国大陆，返回点为中华人民共和国香港特别行政区，同样视为往返；并且判断去程终点与回程起点一致
//				}else if(routeFirst.getFromCountyName().equals("中国大陆") && routeLast.getFromCountyName().equals("中华人民共和国香港特别行政区") && firstCountryGo.equals(endCountryBack)){
//					System.out.println("往返2");
//					rt.setTravelType("1");
//					//判断起点国家和返点国家是否一致，是则为往返；并且判断去程终点与回程起点一致
//				}else if(routeFirst.getFromCountyName().equals(routeLast.getFromCountyName()) && firstCountryGo.equals(endCountryBack)){
//					System.out.println("往返3");
//					rt.setTravelType("1");
//					//判断从中国大陆出发，经过香港回大陆，视为往返
//				}else if(routeFirst.getFromCountyName().equals("中国大陆") && routeLast.getFromCountyName().equals("中国大陆") && firstCountryGo.equals(endCountryBack)&&"中华人民共和国香港特别行政区".equals(firstCountryGo)){
//					System.out.println("往返4");
//					rt.setTravelType("1");
//					//从境外点出发，经中国大陆或中华人民共和国香港特别行政区，最终回到出发点国家，视为往返
//				}else if(("中国大陆".equals(routeLast.getFromCountyName()) || "中华人民共和国香港特别行政区".equals(routeLast.getFromCountyName()))
//						&&("中国大陆".equals(routeFirst.getFromCountyName()) || "中华人民共和国香港特别行政区".equals(routeFirst.getFromCountyName()))
//						&&routeFirst.getFromCountyName().equals(routeLast.getFromCountyName()) && firstCountryGo.equals(endCountryBack)
//						&&("中国大陆".equals(firstCountryGo) || "中华人民共和国香港特别行政区".equals(firstCountryGo))){
//					System.out.println("往返");
//					rt.setTravelType("1");
//					//从中国大陆出发（含国内一段），为单程，第二段为境外航段
//				}else if("中国大陆".equals(routeFirst.getFromCountyName()) && !routeLast.getFromCountyName().equals(routeFirst.getFromCountyName()) 
//						&& routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName()) && !routeFirst.getFromCityCode().equals(routeFirstEx.getToCityCode())){
//					/**判断出主FC,之后判断出航段中的SPA以及AddOn航段*/
//					//判断第一航段的市场方航班公司是否与出票行空公司一致，如一致则为AddOn(国内)
//					if(routeFirst.getFlight().substring(0,2).equals(rt.getComput())){
//						//是AddOn航段
//						routeList.get(0).setIsAddOn("Y");
//						//属于国内AddOn
//						routeList.get(0).setAddOnFly("0");
//					//如果不是，则为SPA航段
//					}else{
//						routeList.get(0).setIsSPA("Y");
//					}
//					rt.setTravelType("0");
//				}
//				//行程4段，匹配类型都为往返类型
//			}else if(routeList.size() == 4){
//				//获取去程起点
//				Route routeFirst = routeList.get(0);
//				Airport airportByAirportCodeFirst = airportDao.getAirportByAirportCode(routeFirst.getFromCityCode());
////				routeFirst.setFromCountyName(airportByAirportCodeFirst.getCountryNameCn());
//				routeFirst.setContentName(airportByAirportCodeFirst.getContinentNameCn());
//				//获取第一段终点
//				Route routeFirstEx = routeList.get(0);
//				Airport airportByAirportCodeFirstEx = airportDao.getAirportByAirportCode(routeFirstEx.getToCityCode());
////				routeFirstEx.setFromCountyName(airportByAirportCodeFirstEx.getCountryNameCn());
//				routeFirstEx.setContentName(airportByAirportCodeFirstEx.getContinentNameCn());
//				//获取航程中间点
//				Route routeMiddle = routeList.get(1);
//				Airport airportByAirportCodeMiddle = airportDao.getAirportByAirportCode(routeMiddle.getToCityCode());
////				routeMiddle.setFromCountyName(airportByAirportCodeMiddle.getCountryNameCn());
//				routeMiddle.setContentName(airportByAirportCodeMiddle.getContinentNameCn());
//				//获取第四段起点
//				Route routeLast = routeList.get(routeList.size()-1);
//				Airport airportByAirportCodeLast = airportDao.getAirportByAirportCode(routeLast.getFromCityCode());
////				routeLast.setFromCountyName(airportByAirportCodeLast.getCountryNameCn());
//				routeLast.setContentName(airportByAirportCodeLast.getContinentNameCn());
//				//获取回程终点
//				Route routeLastEx = routeList.get(routeList.size()-1);
//				Airport airportByAirportCodeLastEx = airportDao.getAirportByAirportCode(routeLastEx.getToCityCode());
////				routeLastEx.setFromCountyName(airportByAirportCodeLastEx.getCountryNameCn());
//				routeLastEx.setContentName(airportByAirportCodeLastEx.getContinentNameCn());
////				for(int i = 0;i < routeList.size();i++){
////					Route route = routeList.get(i);
////					Airport airportByAirportCodeLastEx = airportDao.getAirportByAirportCode(route.getToCityCode());
////					routeList.get(i).setFromCountyName(airportByAirportCodeLastEx.getCountryNameCn());
////					routeList.get(i).setContentName(airportByAirportCodeLastEx.getContinentNameCn());
////				}
//				//国内大陆或香港出发，经境外点，回程终点为中国大陆或香港,为往返
//				if(("中国大陆".equals(routeFirst.getFromCountyName()) || "中华人民共和国香港特别行政区".equals(routeFirst.getFromCountyName())) 
//					&& (!"中国大陆".equals(routeMiddle.getToCountryName()) || !"中华人民共和国香港特别行政区".equals(routeMiddle.getToCountryName()))
//					&& ("中国大陆".equals(routeLast.getToCountryName()) || "中华人民共和国香港特别行政区".equals(routeLast.getToCountryName()))){
//					/**往返要判断出主FC、SPA以及AddOn航段*/
//					if(routeFirst.getContentName().equals(routeFirstEx.getContentName())){
//						//是同一个大洲，判断是不是在同一个国家
//						if(routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName())){
//							//如果是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(0).setIsSPA("Y");
//							}
//						}
//					}else{
//						//不在同一个大洲，首先计算是不是主Fc，根据里程判断
//						if(Integer.parseInt(routeList.get(0).getMileAge()) > Integer.parseInt(routeList.get(1).getMileAge())){
//							routeList.get(0).setIsFc("Y");	
//							//如果不是是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(1).getFlight().substring(0,2))){
//								//如果是同一家，则是国际AddOn
//								routeList.get(1).setIsAddOn("Y");
//								routeList.get(1).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(1).setIsSPA("Y");
//							}
//						}else {
//							routeList.get(1).setIsFc("Y");	
//							//如果不是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(1).setIsSPA("Y");
//							}
//						}
//					}
//					//第三个和第四个航段
//					if(routeLast.getContentName().equals(routeLastEx.getContentName())){
//						//是同一个大洲，判断是不是在同一个国家
//						
//						if(routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName())){
//							//如果是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(0).setIsSPA("Y");
//							}
//						}
//					}else{
//						//不在同一个大洲，首先计算是不是主Fc，根据里程判断
//						if(Integer.parseInt(routeList.get(2).getMileAge()) > Integer.parseInt(routeList.get(3).getMileAge())){
//							routeList.get(2).setIsFc("Y");	
//							//如果不是是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(3).getFlight().substring(0,2))){
//								//如果是同一家，则是国际AddOn
//								routeList.get(3).setIsAddOn("Y");
//								routeList.get(3).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(3).setIsSPA("Y");
//							}
//						}else {
//							routeList.get(1).setIsFc("Y");	
//							//如果不是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(2).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(2).setIsAddOn("Y");
//								routeList.get(2).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(2).setIsSPA("Y");
//							}
//						}
//					}
//					System.out.println("往返");
//					rt.setTravelType("1");
//				//境外点出发，经中国大陆或者中华人民共和国香港特别行政区，回程终点为境外同一国家，为往返
//				}else if((!"中国大陆".equals(routeFirst.getFromCountyName()) && !"中华人民共和国香港特别行政区".equals(routeFirst.getFromCountyName()))
//						 && ("中国大陆".equals(routeFirst.getFromCountyName()) || "中华人民共和国香港特别行政区".equals(routeFirst.getFromCountyName()))
//						 && routeFirst.getFromCountyName().equals(routeLast.getFromCountyName())){
//					/**往返要判断出主FC、SPA以及AddOn航段*/
//					if(routeFirst.getContentName().equals(routeFirstEx.getContentName())){
//						//是同一个大洲，判断是不是在同一个国家
//						if(routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName())){
//							//如果是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(0).setIsSPA("Y");
//							}
//						}
//					}else{
//						//不在同一个大洲，首先计算是不是主Fc，根据里程判断
//						if(Integer.parseInt(routeList.get(0).getMileAge()) > Integer.parseInt(routeList.get(1).getMileAge())){
//							routeList.get(0).setIsFc("Y");	
//							//如果不是是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(1).getFlight().substring(0,2))){
//								//如果是同一家，则是国际AddOn
//								routeList.get(1).setIsAddOn("Y");
//								routeList.get(1).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(1).setIsSPA("Y");
//							}
//						}else {
//							routeList.get(1).setIsFc("Y");	
//							//如果不是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(1).setIsSPA("Y");
//							}
//						}
//					}
//					//第三个和第四个航段
//					if(routeLast.getContentName().equals(routeLastEx.getContentName())){
//						//是同一个大洲，判断是不是在同一个国家
//						
//						if(routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName())){
//							//如果是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(0).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(0).setIsAddOn("Y");
//								routeList.get(0).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(0).setIsSPA("Y");
//							}
//						}
//					}else{
//						//不在同一个大洲，首先计算是不是主Fc，根据里程判断
//						if(Integer.parseInt(routeList.get(2).getMileAge()) > Integer.parseInt(routeList.get(3).getMileAge())){
//							routeList.get(2).setIsFc("Y");	
//							//如果不是是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(3).getFlight().substring(0,2))){
//								//如果是同一家，则是国际AddOn
//								routeList.get(3).setIsAddOn("Y");
//								routeList.get(3).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(3).setIsSPA("Y");
//							}
//						}else {
//							routeList.get(1).setIsFc("Y");	
//							//如果不是同一个国家，判断是否出票行与市场方航空公司一致
//							if(rt.getComput().trim().toUpperCase().equals(routeList.get(2).getFlight().substring(0,2))){
//								//如果是同一家，则是国内AddOn
//								routeList.get(2).setIsAddOn("Y");
//								routeList.get(2).setAddOnFly("0");
//							}else{
//								//如果不是同一家，则是SPA航段
//								routeList.get(2).setIsSPA("Y");
//							}
//						}
//					}
//					System.out.println("往返");
//					rt.setTravelType("1");
//					
//				}else {
//					System.out.println("其他未在标准匹配范围之内");
//				}
//				//行程6段，匹配类型都为往返，如未匹配成功
//			}else if(routeList.size() == 6){
//				//获取去程起点
//				Route routeFirst = routeList.get(0);
//				Airport airportByAirportCodeFirst = airportDao.getAirportByAirportCode(routeFirst.getFromCityCode());
//				routeFirst.setContentName(airportByAirportCodeFirst.getContinentNameCn());
//				routeFirst.setFromCountyName(airportByAirportCodeFirst.getCountryNameCn());
//				//获取航程第一段终点
//				Route routeFirst1 = routeList.get(0);
//				Airport airportByAirportCodeFirst1 = airportDao.getAirportByAirportCode(routeFirst1.getToCityCode());
//				routeFirst1.setContentName(airportByAirportCodeFirst1.getContinentNameCn());
//				routeFirst1.setFromCountyName(airportByAirportCodeFirst1.getCountryNameCn());
//				//获取第二段航程起点
//				Route routeMiddle = routeList.get(1);
//				Airport airportByAirportCodeMiddle = airportDao.getAirportByAirportCode(routeMiddle.getFromCityCode());
//				routeMiddle.setContentName(airportByAirportCodeMiddle.getContinentNameCn());
//				routeMiddle.setFromCountyName(airportByAirportCodeMiddle.getCountryNameCn());
//				//获取航程二段终点
//				Route routeMiddle2 = routeList.get(1);
//				Airport airportByAirportCodeMiddle2 = airportDao.getAirportByAirportCode(routeMiddle2.getToCityCode());
//				routeMiddle2.setContentName(airportByAirportCodeMiddle2.getContinentNameCn());
//				routeMiddle2.setFromCountyName(airportByAirportCodeMiddle2.getCountryNameCn());
//				//获取航程第三段终点
//				Route routeMiddle3 = routeList.get(2);
//				Airport airportByAirportCodeMiddle3 = airportDao.getAirportByAirportCode(routeMiddle3.getToCityCode());
//				routeMiddle3.setContentName(airportByAirportCodeMiddle3.getContinentNameCn());
//				routeMiddle3.setFromCountyName(airportByAirportCodeMiddle3.getCountryNameCn());
//				//获取航程第四段终点
//				Route routeMiddle4 = routeList.get(3);
//				Airport airportByAirportCodeMiddle4 = airportDao.getAirportByAirportCode(routeMiddle4.getToCityCode());
//				routeMiddle4.setContentName(airportByAirportCodeMiddle4.getContinentNameCn());
//				routeMiddle4.setFromCountyName(airportByAirportCodeMiddle4.getCountryNameCn());
//				//获取航程第五段终点
//				Route routeMiddle5 = routeList.get(4);
//				Airport airportByAirportCodeMiddle5 = airportDao.getAirportByAirportCode(routeMiddle5.getToCityCode());
//				routeMiddle5.setContentName(airportByAirportCodeMiddle5.getContinentNameCn());
//				routeMiddle5.setFromCountyName(airportByAirportCodeMiddle5.getCountryNameCn());
//				//获取回程终点
//				Route routeLast = routeList.get(5);
//				Airport airportByAirportCodeLast = airportDao.getAirportByAirportCode(routeLast.getToCityCode());
//				routeLast.setContentName(airportByAirportCodeLast.getContinentNameCn());
//				routeLast.setFromCountyName(airportByAirportCodeLast.getCountryNameCn());
//				//匹配标准：航段为6段，中国大陆出发（含国内一段），回程终点中国大陆
//				//判断思路：去程第一段和最后一段为同一国家（中国大陆）的不同机场，第二段--五段为外境航段，
//				if("中国大陆".equals(routeFirst.getFromCountyName()) 
//					&& "中国大陆".equals(routeFirst.getFromCountyName()) 
//					&& !"中国大陆".equals(routeMiddle.getFromCountyName()) 
//					&& !"中国大陆".equals(routeMiddle2.getFromCountyName()) 
//					&& !"中国大陆".equals(routeMiddle3.getFromCountyName()) 
//					&& !"中国大陆".equals(routeMiddle4.getFromCountyName()) 
//					&& "中国大陆".equals(routeMiddle5.getFromCountyName()) 
//					&& !routeFirst.getFromCityCode().equals(routeFirst.getToCityCode())
//					&& !routeLast.getFromCityCode().equals(routeLast.getToCityCode())
//					&& !routeFirst1.getFromCountyName().equals(routeMiddle2.getFromCountyName())
//					&& !routeMiddle4.getFromCountyName().equals(routeMiddle5.getFromCountyName())
//					&& routeFirst.getFromCountyName().equals(routeLast.getFromCountyName())){
//					//一段和6段位国内航段，只需判断是否为SPA或者是国内AddOn
//					//第一段：如果出票航司和市场方航司一致，则为AddOn航段，否则为SPA
//					if(routeList.get(0).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//						//国内AddOn航段
//						routeList.get(0).setIsAddOn("Y");
//						routeList.get(0).setAddOnFly("0");
//					}else {
//						//SPA航段
//						routeList.get(0).setIsSPA("Y");
//					}
//					//最后一段：如果出票航司和市场方航司一致，则为AddOn航段，否则为SPA
//					if(routeList.get(5).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//						//国内AddOn航段
//						routeList.get(5).setIsAddOn("Y");
//						routeList.get(5).setAddOnFly("0");
//					}else {
//						//SPA航段
//						routeList.get(5).setIsSPA("Y");
//					}
//					/**判断出两个主FC航段，之后判断SPA和AddOn航段*/
//					if(Integer.parseInt(routeList.get(1).getMileAge()) > Integer.parseInt(routeList.get(2).getMileAge()) ){
//						System.out.println("1为主FC");
//						//1为主FC
//						routeList.get(1).setIsFc("Y");
//						//如果1为主FC,判断2为AddOn(国际)或者是SPA
//						if(routeList.get(2).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//							//国际AddOn航段
//							routeList.get(2).setIsAddOn("Y");
//							routeList.get(2).setAddOnFly("1");
//						}else{
//							//SPA航段
//							routeList.get(2).setIsSPA("Y");
//						}
//					}else{
//						//2为主FC
//						System.out.println("2为主FC");
//						routeList.get(2).setIsFc("Y");
//						//如果2为主FC,判断1为AddOn(国际)或者是SPA
//						if(routeList.get(1).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//							//国际AddOn航段
//							routeList.get(1).setIsAddOn("Y");
//							routeList.get(1).setAddOnFly("1");
//						}else{
//							//SPA航段
//							routeList.get(1).setIsSPA("Y");
//						}
//					}
//					if(Integer.parseInt(routeList.get(3).getMileAge()) > Integer.parseInt(routeList.get(4).getMileAge())){
//						System.out.println("3为主FC");
//						//3为主FC
//						routeList.get(3).setIsFc("Y");
//						//如果3为主FC,判断4为AddOn(国际)或者是SPA
//						if(routeList.get(4).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//							//国际AddOn航段
//							routeList.get(4).setIsAddOn("Y");
//							routeList.get(4).setAddOnFly("1");
//						}else{
//							//SPA航段
//							routeList.get(4).setIsSPA("Y");
//						}
//					}else{
//						System.out.println("4为主FC");
//						//4为主FC
//						routeList.get(4).setIsFc("Y");
//						//如果4为主FC,判断3为AddOn(国际)或者是SPA
//						if(routeList.get(3).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//							//国际AddOn航段
//							routeList.get(3).setIsAddOn("Y");
//							routeList.get(3).setAddOnFly("1");
//						}else{
//							//SPA航段
//							routeList.get(3).setIsSPA("Y");
//						}
//					}
//					System.out.println("往返");
//					rt.setTravelType("1");
//				}
//			}	
//		//判断奇数段（除一段外），
//		}else if(routeList.size()%2 == 1 && routeList.size() != 1){
//			//匹配航段为3的单程总则政策，中国大陆（含国内一段），后两段为境外
//			if(routeList.size() == 3){
//				//获取去程起点
//				Route routeFirst = routeList.get(0);
//				Airport airportByAirportCodeFirst = airportDao.getAirportByAirportCode(routeFirst.getFromCityCode());
//				routeFirst.setFromCountyName(airportByAirportCodeFirst.getCountryNameCn());
//				//获取第一段终点
//				Route routeFirstEx = routeList.get(0);
//				Airport airportByAirportCodeFirstEX = airportDao.getAirportByAirportCode(routeFirstEx.getToCityCode());
//				routeFirstEx.setFromCountyName(airportByAirportCodeFirstEX.getCountryNameCn());
//				
//				//获取第二段终点
//				Route routeSecond = routeList.get(1);
//				Airport airportByAirportCodeSecond= airportDao.getAirportByAirportCode(routeSecond.getToCityCode());
//				routeSecond.setFromCountyName(airportByAirportCodeSecond.getCountryNameCn());
//				
//				//获取第三段终点
//				Route routeLast = routeList.get(2);
//				Airport airportByAirportCodeLast= airportDao.getAirportByAirportCode(routeLast.getToCityCode());
//				routeLast.setFromCountyName(airportByAirportCodeLast.getCountryNameCn());
//				
//				//去程起点为中国大陆，第一段终点也为境内且不在同一机场，后两段航程都为境外
//				if("中国大陆".equals(routeFirst.getFromCountyName()) && routeFirst.getFromCountyName().equals(routeFirstEx.getFromCountyName())
//				  && !routeFirst.getToCityCode().equals(routeFirstEx.getFromCityCode()) && !routeFirstEx.getFromCountyName().equals(routeSecond.getFromCountyName())
//				  && !routeFirst.getFromCountyName().equals(routeLast.getFromCountyName())){
//					/**判断出两个主FC航段，之后判断SPA和AddOn航段*/
//					//第一段为国内航段，只需判断是国内AddOn还是SPA
////					if(routeList.get(0).getFlight().equals(rt.getComput())){
////						//国内AddOn航段
////						routeList.get(0).setIsAddOn("Y");
////						routeList.get(0).setAddOnFly("0");
////					}else{
////						//SPA航段
////						routeList.get(0).setIsSPA("Y");
////					}
//					//判断第二段与第三段的主FC
////					if(Integer.parseInt(routeList.get(1).getMileAge()) > Integer.parseInt(routeList.get(2).getMileAge())){
////						//二段为主FC
////						routeList.get(1).setIsFc("Y");
////						//如果二段为主FC，判断三段为国际AddOn还是SPA
////						if(rt.getComput().equals(routeList.get(2).getFlight())){
////							//国际AddOn
////							routeList.get(2).setIsAddOn("Y");
////							routeList.get(2).setAddOnFly("1");
////						}else {
////							routeList.get(2).setIsSPA("Y");
////						}
////					}else {
////						//三段为主FC
////						routeList.get(2).setIsFc("Y");
////						//如果三段为主FC，判断二段为国际AddOn还是SPA
////						if(rt.getComput().equals(routeList.get(1).getFlight())){
////							//国际AddOn
////							routeList.get(1).setIsAddOn("Y");
////							routeList.get(1).setAddOnFly("1");
////						}else {
////							routeList.get(1).setIsSPA("Y");
////						}
////					}
//					
//					for(int i = 0; i < routeListSize; i++){
//						//判断每段航程是否为主FC
//						if("Y".equals(routeList.get(i).getIsFc())){
//							//如果是，不做任何判断
//						}else{
//							//如果不是主Fc,判断是AddOn航段还是SPA，含国内一段的，国内默认为国内AddOn航段
//							if(i == 0){
//								routeList.get(i).setIsAddOn("Y");
//								routeList.get(i).setAddOnFly("0");
//							}else{
//								//判断国际两段是国际AddOn还是SPA
//								if(routeList.get(i).getFlight().substring(0,2).equals(rt.getComput().trim().toUpperCase())){
//									//如果出票行与市场方相等，则为AddOn航段
//									routeList.get(i).setIsAddOn("Y");
//									routeList.get(i).setAddOnFly("1");
//								}else{
//									//否则为SPA航段
//									routeList.get(i).setIsSPA("Y");
//								}
//							}
//						}
//					}
//					System.out.println("单程");
//					rt.setTravelType("0");
//				}
//			}
//		}
//		rt.setRoute_list(routeList);
//		return rt;
//	}
//	
	
	
	/**
	 *根据PNR解析出来的内容判断行程类型（单程/往返）以及得出主FC、SPA和AddOn段航程
	 *以及每段航程的运价
	 */
	@SuppressWarnings("unused")
	public Rt updateRt(Rt rt) {
		//调用此方法获取航段的起飞时间到达时间以及城市、国家、大区、洲三字码和OD里程
		rt = getRoueList(rt);
		List<Route> routeList = rt.getRoute_list();
		Integer routeListSize = routeList.size();
		Integer fcListSize = null;
		List<Qte> qteList = rt.getQte();
		List<Fc> fcList = rt.getFc();
		if(0 == fcList.size()){
			System.out.println("没有FC");
			rt.setTravelType("3");
			return rt;
		}
		//将不同身份的旅客分开放入集合计算
		List<PersonTypeInfo> personInfoList = new ArrayList<PersonTypeInfo>();
		//儿童的FC集合
		List<Fc> childrenFcList = new ArrayList<Fc>();
		//婴儿的FC集合
		List<Fc> babyFcList = new ArrayList<Fc>();
		//成人
		List<Fc> manFcList = new ArrayList<Fc>();
		
		//按照旅客不同类型分开计算运价
//		for(int i = 0 ; i < personInfoList.size(); i++ ){
//			PersonTypeInfo personInfo = personInfoList.get(i);
//			for(int j = 0 ; j < rt.getQte().size(); j++){
//				Qte qte = rt.getQte().get(j);
//				//判断QTE的旅客类型是否与当前旅客类型
//				if(null != personInfo.getPersonType() && null !=qte.getIdentity()){
//					if(personInfo.getPersonType().equals(qte.getIdentity()) || personInfo.getPersonType() == qte.getIdentity()){
//						personInfoList.get(i).setQte(qte);
//					}
//				}else{
//					personInfoList.get(i).setQte(qte);
//				}
//			}
//		}
		
		//int j = 0;
		//List<Route> zzRoutes = rt.getZzRoutes();
		
		
		//判断匹配PNR时，出票航司是否为空。
		if("".equals(rt.getComput()) || null == rt.getComput()){
			//String[] computStr = rt.getCarrier().split("/");
			String comput = getComput(routeList);
			rt.setComput(comput);
		}
		
		try {
			//根据不同的身份，分开计算价格
			for(int i = 0; i < fcList.size(); i++){
				Fc fc = fcList.get(i);
				//成人的Fc
				if(null == fc.getIden()){
					//解出类型为成人，那么类型为成人，身份为普通
					fc.setIden("MAN");
//					fc.setPersonStatus("0");
					manFcList.add(fc);
				//婴儿的FC
				}else if("IN".equals(fc.getIden().trim().toUpperCase())){
					//解出类型为婴儿，那么类型为婴儿，身份为普通
					fc.setIden("IN");
//					fc.setPersonStatus("0");
					babyFcList.add(fc);
				//儿童的FC
				}else if("CH".equals(fc.getIden().toUpperCase().trim())){
					//解出类型为儿童，那么类型为儿童，身份为普通
					fc.setIden("CH");
//					fc.setPersonStatus("0");
					childrenFcList.add(fc);
					 
				}else if("LBR".equals(fc.getIden().trim().toUpperCase())){
					//如果匹配出是劳工，那么旅客类型为成人，身份为劳工
					fc.setIden("MAN");
//					fc.setPersonStatus("3");
					manFcList.add(fc);
				}else if("STU".equals(fc.getIden().trim().toUpperCase())){
					//如果匹配出是学生，那么旅客类型为成人，身份为留学生
					fc.setIden("MAN");
//					fc.setPersonStatus("2");
					manFcList.add(fc);
				}else{
					//其他身份，返给保盛处理
					System.out.println("旅客身份不在标准匹配范围的类型");
//					rt.setTravelType("3");
					return rt;
				}
			}
		} catch (Exception e) {
			logger.error("文本匹配身份失败", e);
		}
		
		
		try {
			//只要集合有值，那么每个FC的集合长度该是一样的，所以取一个集合去做判断就行
			//之后将不同类型的旅客分开放入旅客信息集合中
			if(0 != manFcList.size()){
				fcListSize = manFcList.size();
				PersonTypeInfo personInfo = new PersonTypeInfo();
				personInfo.setFcList(manFcList);
				personInfoList.add(personInfo);
				personInfoList.get(0).setPersonType("MAN");
				personInfoList.get(0).setPersonIden("0");
			}
			if(0 != childrenFcList.size()){
				fcListSize = childrenFcList.size();
				PersonTypeInfo personInfo = new PersonTypeInfo();
				personInfo.setFcList(childrenFcList);
				if(personInfoList.size() == 1){
					personInfoList.add(personInfo);
					personInfoList.get(1).setPersonType("CH");
				}else{
					personInfoList.add(personInfo);
					personInfoList.get(0).setPersonType("CH");
				}
			}
			if(0 != babyFcList.size()){
				fcListSize = babyFcList.size();
				PersonTypeInfo personInfo = new PersonTypeInfo();
				personInfo.setFcList(babyFcList);
				if(personInfoList.size() == 1){
					personInfoList.add(personInfo);
					personInfoList.get(1).setPersonType("IN");
				}else if(personInfoList.size() == 2){
					personInfoList.add(personInfo);
					personInfoList.get(2).setPersonType("IN");
				}else{
					personInfoList.add(personInfo);
					personInfoList.get(0).setPersonType("IN");
				}
			}
		} catch (Exception e) {
			logger.error("将Fc类型分配错误",e);
		}
		
		
		try {
			//将qte的值放入总集合中（按照不同旅客的类型）
			for(int j = 0 ; j < personInfoList.size(); j++){
				PersonTypeInfo personInfo = personInfoList.get(j);
				for(int i = 0; i <  qteList.size(); i++){
					Qte qte = qteList.get(i);
					if(null == qte.getIdentity() || "MAN".equals(qte.getIdentity())){
						qte.setIdentity("MAN");
					}else if("CH".equals(qte.getIdentity()) || "ch".equals(qte.getIdentity())){
						qte.setIdentity("CH");
					}else if("IN".equals(qte.getIdentity()) || "in".equals(qte.getIdentity())){
						qte.setIdentity("IN");
					}
					if(qte.getIdentity().equals(personInfo.getPersonType())){
						personInfoList.get(j).setQte(qte);
					}
				}
			}
		} catch (Exception e) {
			logger.error("获取QTE信息失败",e);
		}
		
		//判断是AddOn还是SPA
		try {
			routeList = getAddOnOrSPA(rt.getComput(),routeList);
		} catch (Exception e) {
			logger.error("AddOnOrSPA判断航程是AddOn还是SPA错误",e);
		}
		
		
		
		//此方法是根据航段的城市信息反向获取FC中的航班信息（承运OD三字码）
		try {
			fcList = getFcODInfo(fcList,routeList);
		} catch (Exception e) {
			logger.error("FC解析有误",e);
		}
		
		//判断是否为IT票(IT票有特殊的就算方式),pnr中IT票没有FcNum,所以为了不打乱下面的方法，先计算出FcNum
				if("IT".equals(rt.getFc().get(0).getFcNum())){
					try {
						rt = getItInfo(rt,personInfoList);
					} catch (Exception e) {
						logger.error("IT票面匹配Fc信息失败",e);
					}
				}

			//匹配标准航段
			if(routeListSize == 1 && fcListSize == 1){
				rt = getOneListSize(rt,personInfoList);
			}else if(routeListSize == 2){
				rt = getTowListSize(rt,personInfoList,fcListSize);
			//三段，单程，含国内一段
			}else if(routeListSize == 3){
				rt = getThreeListSize(rt, personInfoList, fcListSize);
				//如果航程为4段（往返）
			}else if(routeList.size() == 4){
				rt = getFourListSize(rt, personInfoList, fcListSize);
				
				//炸天！！！
			}else if(routeListSize == 6){
				rt = getSixListSize(rt, personInfoList, fcListSize);
			}else{
					System.out.println("6段非标准航段，抛与保盛");
					for(int i = 0 ; i < personInfoList.size(); i++){
						// 获取第一段航程票价
						personInfoList.get(i).setRouteList(routeList);
						rt.setPersonTypeInfoList(personInfoList);
					}
					rt.setTravelType("3");
				}		
		rt.setRoute_list(routeList);
		return rt;
	}
	
	
	/**
	 * 判断每段航程是AddOn航段还是SPA航段
	 * @param comput
	 * @param routeList
	 * @return
	 */
	private List<Route> getAddOnOrSPA(String comput,List<Route> routeList){
		int routListSize = routeList.size();
		for(int i = 1; i < routListSize; i++){
			Route route = routeList.get(i);
			if(comput.toUpperCase().trim().equals(route.getFlight().substring(0,2).toUpperCase())){
				//出票航司与承运公司一直则为AddOn航段
				routeList.get(i).setIsAddOn("Y");
				if("CN".equals(route.getFromCountryCode()) || "HK".equals(route.getFromCountryCode())){
					//如果是国内航段，为国内AddOn
					routeList.get(i).setAddOnFly("0");
				}else{
					//否则为国际AddOn
					routeList.get(i).setAddOnFly("1");
				}
			}else{
				//不是AddOn航段就是国际SPA
				routeList.get(i).setIsSPA("Y");
			}
		}
		return routeList;
	}
	
	
	
	/**
	 * 判断如果是IT票面就走此方法
	 * @param rt
	 * @return
	 */
	private Rt getItInfo(Rt rt,List<PersonTypeInfo> personInfoList){
		List<Fc> fcList = rt.getFc();
		if("IT".equals(fcList.get(0).getFcNum())){
			rt.setPnrType("IT");
			//将不同类型分别存入到FcNum中
			for(int m = 0 ; m < personInfoList.size(); m++){
				PersonTypeInfo personInfo = personInfoList.get(m);
				Qte qte = personInfoList.get(m).getQte();
				Integer mileAges =0;
				for(int i = 0 ; i < personInfo.getFcList().size(); i++){
					Fc fc = personInfo.getFcList().get(i);
					Mileage mileAge = mileageDao.findMileage(fc.getFromCityCode()+"-"+fc.getToCityCode());
					if(null == mileAge){
						mileAge = mileageDao.findMileage(fc.getToCityCode()+"-"+fc.getFromCityCode());
					}
					if(null != mileAge){
						//将每段FC的里程记录留作备用
						personInfoList.get(m).getFcList().get(i).setMileAge(Integer.parseInt(mileAge.getMileage()));
						mileAges += Integer.parseInt(mileAge.getMileage());
					}else{
						System.out.println("表中无本航段数据，AirportServiceImpl:1087行:"+fc.getToCityCode()+"-"+fc.getFromCityCode());
						rt.setTravelType("3");
						return rt;
					}
				}
				personInfoList.get(m).setMileAge(mileAges);
				for(int i = 0 ; i <personInfoList.get(m).getFcList().size(); i++){
					Fc fc = personInfoList.get(m).getFcList().get(i);
					Double mile = (double)((double)fc.getMileAge()/(double)mileAges);
					personInfoList.get(m).getFcList().get(i).setFcNum((mile*(Integer.parseInt(qte.getFare()))/Double.parseDouble(fc.getRate()))+"");
				}
			}
		}
		return rt;
	}
	
	
	
	/**
	 * 反向获取Fc航班三字码
	 * @param fcList
	 * @param routeList
	 * @return
	 */
	private List<Fc> getFcODInfo(List<Fc> fcList, List<Route> routeList){
		int routeListSize = routeList.size();
		try {
			for(int j = 0 ; j < fcList.size(); j++){
				Fc fc = fcList.get(j);
				for(int i = 0 ; i < routeListSize; i++ ){
					Route route = routeList.get(i);
					if(fc.getFromCity().equals(route.getFromAirport())){
						fcList.get(j).setFromCityCode(route.getFromCityCode());
					}
					if(fc.getToCity().equals(route.getToAirport())){
						fcList.get(j).setToCityCode(route.getToCityCode());
					}
				}
			}
		} catch (Exception e) {
			logger.error("文本Fc解析有误或航段解析有误",e);
		}
		return fcList;
	}
	
	
	
	/**
	 * 获取文本的起飞时间到达时间以及城市、国家、大区、洲的三字码和OD里程信息
	 * @param rt
	 * @return
	 */
	private Rt getRoueList(Rt rt){
		List<Route> routeList = rt.getRoute_list();
		List<PersonTypeInfo> personInfoList = new ArrayList<PersonTypeInfo>();
		for (int i = 0; i < routeList.size(); i++) {
			//判断中转站
//			if(i<routeList.size()-1&&routeList.get(i).getTocity().equals(routeList.get(i+1).getFromCity())){
//				Airport airportByAirportCode = getAirportByAirportCode(routeList.get(i).getTocity());
//				routeList.get(i).setMiddleAirport(airportByAirportCode.getAirportNameCn());
//				routeList.get(i).setTocity(routeList.get(i+1).getTocity());
//				routeList.get(i).setToTime(routeList.get(i+1).getToTime());
//				if(i<routeList.size()-2&&!routeList.get(i+1).getTocity().equals(routeList.get(i).getFromCity()) ){
//					
//					routeList.remove(routeList.get(i+1));
//				}
//				System.out.println(i+"----"+routeList.get(i));
//				zzRoutes.add(i, routeList.get(i));
//				j++;
//			}else {
//				zzRoutes.add(i, routeList.get(i));
//			}
			
			Airport airportByAirportCode = airportDao.getAirportByAirportCode(routeList.get(i).getFromCity());
			//机场三字码
			routeList.get(i).setFromCityCode(airportByAirportCode.getAirportCode());
			//机场中文
			routeList.get(i).setFromCity(airportByAirportCode.getAirportNameCn());
			City city = cityDao.findCityIataAreaCd(airportByAirportCode.getCityCode());
			//城市三字码
			routeList.get(i).setFromAirport(city.getCityCode());
			//国家二字码
			routeList.get(i).setFromCountryCode(city.getCityCodeAbbr());
			//大区三字码
			routeList.get(i).setFromAreaCode(city.getIataAreaCd());
			//大洲
			routeList.get(i).setFromRegionCode(city.getIataRegionEnAbbr());
			routeList.get(i).setFromCountyName(airportByAirportCode.getCountryNameCn());
			
			Airport airportByAirportCode2 = airportDao.getAirportByAirportCode(routeList.get(i).getTocity());
			City city1 = cityDao.findCityIataAreaCd(airportByAirportCode2.getCityCode());
			//城市三字码
			routeList.get(i).setToAirport(city1.getCityCode());
			//国家二字码
			routeList.get(i).setToCountryCode(city1.getCityCodeAbbr());
			//大区三字码
			routeList.get(i).setToAreaCode(city1.getIataAreaCd());
			//大洲
			routeList.get(i).setToRegionCode(city1.getIataRegionEnAbbr());
			routeList.get(i).setToCityCode(airportByAirportCode2.getAirportCode());
			routeList.get(i).setTocity(airportByAirportCode2.getAirportNameCn());
			routeList.get(i).setToCountryName(airportByAirportCode2.getCountryNameCn());
			Mileage mileage1 = mileageDao.findMileage(routeList.get(i).getFromCityCode()+"-"+routeList.get(i).getToCityCode());
			if(null == mileage1){
				mileage1 = mileageDao.findMileage(routeList.get(i).getToCityCode()+"-"+routeList.get(i).getFromCityCode());
			}
			if(null == mileage1){
				//公里表中没有这段航程，直接视为非标准航段，留给保盛去核价
				System.out.println("本段航程库中没有公里数:"+routeList.get(i).getToCityCode()+"-"+routeList.get(i).getFromCityCode());
				rt.setTravelType("3");
				for(int l = 0 ; i < personInfoList.size(); l++){
					// 获取第一段航程票价
					personInfoList.get(l).setRouteList(routeList);
					rt.setPersonTypeInfoList(personInfoList);
				}
				return rt;
			}
			routeList.get(i).setMileAge(mileage1.getMileage());
//			Route route = routeList.get(i);
//			Mileage mileage = mileageDao.findMileage(route.getFromCityCode()+"-"+route.getToCityCode());
//			if(null == mileage){
//				mileage = mileageDao.findMileage(route.getToCityCode()+"-"+route.getFromCityCode());
//			}
//			routeList.get(i).setMileAge(mileage.getMileage());
			//拼接时间-起飞时间
			String date = routeList.get(i).getDate();
			String fromTime = routeList.get(i).getFromTime();
			StringBuffer fromTimeBuffer = new StringBuffer();
			String fromHour = fromTime.substring(0,2);
			String fromMin = fromTime.substring(2,4);
			fromTimeBuffer.append(date);
			fromTimeBuffer.append(" ");
			fromTimeBuffer.append(fromHour);
			fromTimeBuffer.append(":");
			fromTimeBuffer.append(fromMin);
			routeList.get(i).setFromTime(fromTimeBuffer.toString());
			//拼接时间-降落时间
			StringBuffer toTimeBuffer = new StringBuffer();
			String toTime = routeList.get(i).getToTime();
			String tomHour = toTime.substring(0,2);
			String toMin = toTime.substring(2,4);
			int parseFromTime = Integer.parseInt(fromTime);
			int parseToTime = Integer.parseInt(toTime);
			String shiJianCha = null;
			if(parseFromTime>parseToTime){
				String dataString = DateTool.dataString(date);
				toTimeBuffer.append(dataString);
				shiJianCha=parseToTime	+2400-parseFromTime+"";
			}else{
				toTimeBuffer.append(date);
				shiJianCha=parseToTime-parseFromTime+"";
			}
			toTimeBuffer.append(" ");
			toTimeBuffer.append(tomHour);
			toTimeBuffer.append(":");
			toTimeBuffer.append(toMin);
			routeList.get(i).setToTime(toTimeBuffer.toString());
			String hour = "";
			try {
				hour = shiJianCha.substring(0, shiJianCha.length()-2);
			} catch (Exception e) {
				logger.error("小时取值错误",e);
			}
			String min ="";
			try {
				min = shiJianCha.substring(shiJianCha.length()-2, shiJianCha.length());
			} catch (Exception e) {
				logger.error("小时取值错误",e);
			}
			String flyTimeStr=hour+"小时"+min+"分";
			routeList.get(i).setFlyTime(flyTimeStr);
		}
		return rt;
	}
	
	
	
	/**
	 * 如果匹配PNR是没有输入出票航司，那么就用承运航司中判断出出票航司
	 * @param routeList
	 * @return
	 */
	public String getComput(List<Route> routeList1){
		String comput = "";
		List<Route> routeList = routeList1;
		if(routeList.size() == 1){
			comput = routeList.get(0).getFlight().substring(0,2);
			return comput;
		}else{
			for(int i = 0 ; i < routeList.size(); i++){
				if(i == (routeList.size()-1)){
					break;
				}
				try {
					if(!routeList.get(i).getFlight().substring(0,2).equals(routeList.get(i+1).getFlight().substring(0,2))){
						Route route = routeList.get(i);
						Route route1 = routeList.get(i+1);
						//City city1 = cityDao.getComput(routeList.get(+1).getFlight().substring(0,2));
						//判断第一段是否跨大区
						if(route.getFromAreaCode().equals(route.getToAreaCode())){
							//第一段没有跨大区，判断第二段是否跨大区
							if(route1.getFromAreaCode().equals(route1.getToAreaCode())){
								//判断大洲
								if(route.getFromRegionCode().equals(route.getToRegionCode())){
									//第一段没有跨洲
									if(route1.getFromRegionCode().equals(route1.getToRegionCode())){
										//第二段没有跨洲
										if(Integer.parseInt(route.getMileAge()) > Integer.parseInt(route1.getMileAge())){
											//第一段为出票航司
											routeList.set(i, route);
										}else{
											//第二段为出票航司
											routeList.set(i+1, route1);
										}
									}else{
										//第二段跨洲
										routeList.set(i+1, route1);
									}
								}else{
									//第一段跨大洲，判断第二段是否跨大洲
									if(route1.getFromRegionCode().equals(route1.getToRegionCode())){
										//第二段没有跨洲，将第一段赋予第二段做下一个循环的比较
										routeList.set(i+1, route);
									}else{
										//第二段也跨洲
										if(Integer.parseInt(route.getMileAge()) > Integer.parseInt(route1.getMileAge())){
											routeList.set(i+1, route);
										}else{
											routeList.set(i+1, route1);
										}
									}
								}
							}else{
								//如果第二段跨大区
								routeList.set(i+1, route1);
							}
						}else{
							//第一段跨大区，判断第二段
							if(route1.getFromAreaCode().equals(route1.getToAreaCode())){
								//第二段没有跨大区。
								routeList.set(i+1, route1);
							}else{
								//第二段跨大区，判断洲
								if(route.getFromRegionCode().equals(route.getToRegionCode())){
									//判断第一段没有跨洲
									if(route1.getFromRegionCode().equals(route1.getToRegionCode())){
										//第二段没有跨洲
										if(Integer.parseInt(route.getMileAge()) > Integer.parseInt(route1.getMileAge())){
											routeList.set(i+1, route);
										}else{
											routeList.set(i+1, route1);
										}
									}else{
										//第二段跨洲
										routeList.set(i+1, route1);
									}
								}else{
									//第一段跨洲，判断第二段
									if(route1.getFromRegionCode().equals(route1.getToRegionCode())){
										//第二段没有跨洲
										routeList.set(i+1, route);
									}else{
										//第二段跨洲，判断距离
										if(Integer.parseInt(route.getMileAge()) > Integer.parseInt(route1.getMileAge())){
											routeList.set(i+1, route);
										}else{
											routeList.set(i+1, route1);
										}
									}
								}
							}
						}
						comput = routeList.get(routeList.size()-1).getFlight().substring(0,2);
					}else{
						comput = routeList.get(i).getFlight().substring(0,2);
					}
				} catch (Exception e) {
					logger.error("判断出票航司时，没有公里数",e);
				}
				
			}
		}
		return comput;
	}
	
	
	
	/**
	 * 一段航程的逻辑判断
	 * @param rt
	 * @param personInfoList
	 * @return
	 */
	private Rt getOneListSize(Rt rt, List<PersonTypeInfo> personInfoList){
		List<Route> routeList = rt.getRoute_list();
		List<Qte> qteList = rt.getQte();
		
		//获取第一段航程信息
		Route route1 = routeList.get(0);
		//获取第一段起点国家
		Airport airportByAirportCode = airportDao.getAirportByAirportCode(route1.getFromCityCode());
		route1.setFromCountyName(airportByAirportCode.getCountryNameCn());
		//获取第一段终点国家
		airportByAirportCode = airportDao.getAirportByAirportCode(route1.getToCityCode());
		route1.setToCountryName(airportByAirportCode.getCountryNameCn());
		//判断是否为国际航段
		if(("中国大陆".equals(route1.getFromCountyName()) || "中华人民共和国香港特别行政区".equals(route1.getFromCountyName())
			&& !route1.getFromCountyName().equals(route1.getToCountryName()))
			|| (!"中国大陆".equals(route1.getFromCountyName()) ||!"中华人民共和国香港特别行政区".equals(route1.getFromCountyName()))
			){
				rt.setTravelType("0");
				rt.setPersonTypeInfoList(personInfoList);
				for(int i = 0 ; i < qteList.size(); i++){
					List<Route> routeLists = new ArrayList<Route>();
					Route route = new Route();
					Qte qte = qteList.get(i);
					routeList.get(0).setIsFc("Y");
					//rt.getPersonTypeInfoList().add(null);
					if(null != qte.getIdentity()){
						//rt.getPersonTypeInfoList().add(null);
						route.setIsAddOn(route1.getIsAddOn());
						route.setIsFc(route1.getIsFc());
						route.setIsSPA(route1.getIsSPA());
						route.setAddOnFly(route1.getAddOnFly());
						routeLists.add(route);
						rt.getPersonTypeInfoList().get(i).setPersonType(qte.getIdentity());
						rt.getPersonTypeInfoList().get(i).setRouteList(routeLists);
						rt.getPersonTypeInfoList().get(i).getRouteList().get(0).setFlyPrice(Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax()));
						rt.getPersonTypeInfoList().get(i).setQte(qte);
					}else{
						//rt.getPersonTypeInfoList().add(null);
						rt.getPersonTypeInfoList().get(i).setPersonType("MAN");
						route.setIsAddOn(route1.getIsAddOn());
						route.setIsFc(route1.getIsFc());
						route.setIsSPA(route1.getIsSPA());
						route.setAddOnFly(route1.getAddOnFly());
						routeLists.add(route);
						rt.getPersonTypeInfoList().get(i).setPersonType(qte.getIdentity());
						rt.getPersonTypeInfoList().get(i).setRouteList(routeLists);
						rt.getPersonTypeInfoList().get(i).getRouteList().get(0).setFlyPrice(Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax()));
						rt.getPersonTypeInfoList().get(i).setQte(qte);
					}
				}
		}else{
			rt.setTravelType("3");
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(routeList);
				rt.setPersonTypeInfoList(personInfoList);
			}
			return rt;
		}
		return rt;
	}
	
	/**
	 * 两段航程的判断解析
	 * @param rt
	 * @param personInfoList
	 * @param fcListSize
	 * @return
	 */
	private Rt getTowListSize(Rt rt, List<PersonTypeInfo> personInfoList,int fcListSize){
		List<Route> routeList = rt.getRoute_list();
		int personListSize = personInfoList.size();
		//获取第一段航程信息
		Route route1 = routeList.get(0);
		//获取第二段航程信息
		Route route2 = routeList.get(1);
//		try {
//			//获取第一段起点国家
//			Airport airportByAirportCode = airportDao.getAirportByAirportCode(route1.getFromCityCode());
//			route1.setFromCountyName(airportByAirportCode.getCountryNameCn());
//			//获取第一段终点国家
//			airportByAirportCode = airportDao.getAirportByAirportCode(route1.getToCityCode());
//			route1.setToCountryName(airportByAirportCode.getCountryNameCn());
//			//获取第二段起点国家
//			airportByAirportCode = airportDao.getAirportByAirportCode(route2.getFromCityCode());
//			route2.setFromCountyName(airportByAirportCode.getCountryNameCn());
//			//获取第二段终点国家
//			airportByAirportCode = airportDao.getAirportByAirportCode(route2.getToCityCode());
//			route2.setToCountryName(airportByAirportCode.getCountryNameCn());
//		} catch (Exception e) {
//			logger.error("获取数据库国家信息失败",e);
//		}
		//首先判断行程类型（单程/往返）
		//国际往返(标准条件：去程起点与回程终点为同一个国家，去程起点与去程终点不是同一个国家，回程起点也是)
		if(route1.getFromCountryCode().equals(route2.getToCountryCode())
			&& !route1.getFromCountryCode().equals(route1.getToCountryCode())
			&& !route2.getFromCountryCode().equals(route2.getToCountryCode())){
			//国际往返航段
			rt.setTravelType("1");
			routeList.get(0).setIsFc("Y");
			routeList.get(1).setIsFc("Y");
			
			//纯国际段（两段）(判断条件：去程起点与终点和回程终点都不为一个国家)(单程)
		}else if(!route1.getFromCountryCode().equals(route2.getToCountryCode())
				&& !route2.getFromCountryCode().equals(route2.getToCountryCode())){
			//国际单程
			rt.setTravelType("0");
			//如果为一个FC，那么整个航程都为主FC
			if(fcListSize == 1){
				routeList.get(0).setIsFc("Y");
				routeList.get(1).setIsFc("Y");
			}else{
				//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
				//首先说两个都为国际航段，所以就直接判断大区
				if(route1.getFromAreaCode().equals(route2.getToAreaCode())){
					//如果第一段没有跨大区，那么需要判断第二段是否跨大区
					if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
						//如果第二段也没有跨大区，判断是否跨大洲
						if(route1.getFromRegionCode().equals(route1.getToRegionCode())){
							//第一段没有跨洲，判断第二段是否跨洲
							if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
								//如果第二段也没有跨洲，就判断公里
								if(Integer.parseInt(route1.getMileAge()) >= Integer.parseInt(route2.getMileAge())){
									routeList.get(0).setIsFc("Y");
								}else{
									routeList.get(1).setIsFc("Y");
								}
							}else{
								//如果第二段跨洲，那么第二段为主FC
								routeList.get(1).setIsFc("Y");
							}
						}else{
							//如果第一段跨洲，判断第二段是否跨洲
							if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
								//如果第二段没有跨洲，那么第一段为主FC
								routeList.get(0).setIsFc("Y");
							}else{
								//如果第二段也跨洲，判断公里
								if(Integer.parseInt(route1.getMileAge()) >= Integer.parseInt(route2.getMileAge())){
									routeList.get(0).setIsFc("Y");
								}else{
									routeList.get(1).setIsFc("Y");
								}
							}
						}
					}else{
						//如果第二段跨大区，那么第二段就为主FC
						routeList.get(1).setIsFc("Y");
					}
				}else{
					//如果第一段跨了大区，判断第二段是否跨大区
					if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
						//如果第二段没有跨大区，那么第一段就为主FC
						routeList.get(0).setIsFc("Y");
					}else{
						//如果第二段也跨大区，直接判断公里
						if(Integer.parseInt(route1.getMileAge()) >= Integer.parseInt(route2.getMileAge())){
							routeList.get(0).setIsFc("Y");
						}else{
							routeList.get(1).setIsFc("Y");
						}
					}
				}
			}
			//一段国内，一段国际
		}else if((("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getFromCountryCode()))
				&&("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getToCountryCode()))
				&& (!"CN".equals(route2.getToCountryCode())||  !"HK".equals(route2.getToCountryCode())))
				||((!"CN".equals(route1.getFromCountryCode()) || !"HK".equals(route1.getFromCountryCode()))
					&&(!"CN".equals(route1.getFromCountryCode()) || !"HK".equals(route1.getToCountryCode()))
					&& ("CN".equals(route2.getToCountryCode())||  "HK".equals(route2.getToCountryCode())))){
			//如果两段为一段国内一段国际，则第一段为国内AddOn,那么第二段为主FC（两个FC）
			rt.setTravelType("0");
			if(fcListSize == 1){
				routeList.get(0).setIsFc("Y");
				routeList.get(1).setIsFc("Y");
			}else if(fcListSize == 2){
				if("CN".equals(route1.getFromCountryCode()) || "HK".equals(route2.getFromCountryCode())){
					routeList.get(1).setIsFc("Y");
				}else{
					routeList.get(0).setIsFc("Y");
				}
			}
		}else{
			System.out.println("非标准航段，抛与保盛");
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(routeList);
				rt.setPersonTypeInfoList(personInfoList);
			}
			rt.setTravelType("3");
		}
		//计算一个FC的两段航程运价
				try {
					if(fcListSize == 1){
						try {
							//求出本段FC的总公里数
							int mileSize = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge());
							//求公里数比例
							Double miles = (double)(Double.parseDouble(route1.getMileAge())*1d/mileSize);
							//按照不同类型的旅客去计算每段航程的运价
							for(int i = 0 ; i < personListSize; i++){
								PersonTypeInfo personTypeInfo = personInfoList.get(i);
								Qte qte = personInfoList.get(i).getQte();
								List<Route> routeList1 = new ArrayList<Route>();
								Route route = new Route();
								Route route3 = new Route();
								routeList1.add(route);
								routeList1.add(route3);
								//List<Fc> fcList1 = personInfoList.get(i).getFcList();
								//for(int j = 0 ; j < fcListSize; j++){
								Double fcPrice = Double.parseDouble(qte.getFare());
								//用第一段公里数的比例求出第一段航程的运价
								Double fcFistPrice = miles*fcPrice;
								//求出第二段航程的运价
								Double fcSecondPrice = fcPrice - fcFistPrice;
								route.setFlyPrice(fcFistPrice);
								route.setIsAddOn(route1.getIsAddOn());
								route.setIsFc(route1.getIsFc());
								route.setIsSPA(route1.getIsSPA());
								route.setAddOnFly(route1.getAddOnFly());
								route3.setFlyPrice(fcSecondPrice);
								route3.setIsAddOn(route2.getIsAddOn());
								route3.setIsFc(route2.getIsFc());
								route3.setIsSPA(route2.getIsSPA());
								route3.setAddOnFly(route2.getAddOnFly());
								personTypeInfo.setRouteList(routeList1);
							}
						} catch (Exception e) {
							logger.error("计算错误",e);
						}
							rt.setPersonTypeInfoList(personInfoList);
						}else if(fcListSize == 2){
							//按照不同类型的旅客去计算每段航程的运价
							for(int i = 0; i < personListSize; i++){
								//获取一个旅客类型总的FC
								PersonTypeInfo personTypeInfo = personInfoList.get(i);
								List<Fc> fcList = personInfoList.get(i).getFcList();
								Fc fc1 = fcList.get(0);
								//获取不同旅客QTE的信息
								Qte qte = personInfoList.get(i).getQte();
								//两段航程每段都有一个FC
								List<Route> routeList1 = new ArrayList<Route>();
								Route route = new Route();
								Route route3 = new Route();
								routeList1.add(route);
								routeList1.add(route3);
								//List<Fc> fcList1 = personInfoList.get(i).getFcList();
								//for(int j = 0 ; j < fcListSize; j++){
								//可以直接用FC数*RUC（别问我什么是NUC，用就对啦。。）如果是外币还要*外币汇率，不是外币就以*1
								Double foreignPrice =1.0;
								if(null != rt.getExchangeRate()){
									foreignPrice = Double.parseDouble(rt.getExchangeRate());
								}
								//获取QTE就是销售价
								Double price = Double.parseDouble(qte.getPrice()) - Double.parseDouble(qte.getTax());
								//第一段航程的运价
								Double fcFistPrice = Double.parseDouble(fc1.getFcNum())*Double.parseDouble(fc1.getRate())*foreignPrice;
								//求出第二段航程的运价（直接用票面价减去第一段运价）
								Double fcSecondPrice = price-fcFistPrice;
								route.setFlyPrice(fcFistPrice);
								route.setIsAddOn(route1.getIsAddOn());
								route.setIsFc(route1.getIsFc());
								route.setIsSPA(route1.getIsSPA());
								route.setAddOnFly(route1.getAddOnFly());
								route3.setFlyPrice(fcSecondPrice);
								route3.setIsAddOn(route2.getIsAddOn());
								route3.setIsFc(route2.getIsFc());
								route3.setIsSPA(route2.getIsSPA());
								route3.setAddOnFly(route2.getAddOnFly());
								personTypeInfo.setRouteList(routeList1);
							}
							rt.setPersonTypeInfoList(personInfoList);
						}else if(fcListSize == 0){
							System.out.println("两段OD文本解析没有FC");
							logger.error("文本解析没有FC");
						}
					} catch (Exception e) {
					logger.error("文本解析有误",e);
				}
				
		return rt;
	}
	
	/**
	 * 三段航程
	 * @param routList
	 * @return
	 */
	private Rt getThreeListSize(Rt rt, List<PersonTypeInfo> personInfoList,int fcListSize ){
		List<Route> routeList = rt.getRoute_list();
		Route route1 = routeList.get(0);
		Route route2 = routeList.get(1);
		Route route3 = routeList.get(2);
		List<Fc> fcList = rt.getFc();
		int routListSize = routeList.size();
		//票面外币汇率（默认为人民币）
		Double exchangeRate = 1.0;
		//如果是外币汇率，则不为空；
		if(null != rt.getExchangeRate()){
			exchangeRate = Double.parseDouble(rt.getExchangeRate());
		}
		//含国内一段（标准条件：国内-国际-国际）
		if(("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getFromCountryCode()))
		&& ("CN".equals(route1.getToCountryCode()) || "HK".equals(route1.getToCountryCode()))
		&& ("CN".equals(route2.getFromCountryCode()) || "HK".equals(route2.getFromCountryCode()))
		&& (!"CN".equals(route2.getToCountryCode()) || !"HK".equals(route2.getToCountryCode()))
		&& (!"CN".equals(route3.getFromCountryCode()) || !"HK".equals(route3.getFromCountryCode()))
		&& (!"CN".equals(route3.getToCountryCode()) || !"HK".equals(route3.getToCountryCode()))){
			//单程
			rt.setTravelType("0");
			//首先判断主FC，再进行计算每段航程的运价
			//如果只有一个FC，那么整段航程都为主FC
			if(fcListSize == 1){
				for(int i = 0 ; i < routListSize; i++){
					routeList.get(i).setIsFc("Y");
				}
				//计算每段航程的运价
				try {
					for(int i = 0 ; i < personInfoList.size(); i++){
						Qte qte = personInfoList.get(i).getQte();
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route = new Route();
						Route route4 = new Route();
						Route route5 = new Route();
						route.setIsFc("Y");
						route.setIsAddOn(route1.getIsAddOn());
						route.setAddOnFly(route1.getAddOnFly());
						route.setIsSPA(route1.getIsSPA());
						route4.setIsFc("Y");
						route4.setIsAddOn(route2.getIsAddOn());
						route4.setAddOnFly(route2.getAddOnFly());
						route4.setIsSPA(route2.getIsSPA());
						route5.setIsFc("Y");
						route5.setIsAddOn(route3.getIsAddOn());
						route5.setAddOnFly(route3.getAddOnFly());
						route5.setIsSPA(route3.getIsSPA());
						routes.add(route);
						routes.add(route4);
						routes.add(route5);
						//求出本段FC的总公里数
						int mileSize = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
						//然后求出公里比例
						//第一段航程的比例
						Double miles = (Double.parseDouble(route1.getMileAge())*1d/mileSize);
						//第二段的航程比例
						Double miles1 = (Double.parseDouble(route2.getMileAge())*1d/mileSize);
						//第三段的航程比例
//							Double miles2 = (double) (mileSize/Integer.parseInt(mileage2.getMileage()));
						//用第一段公里数的比例求出第一段航程的运价
						Double fcFistPrice = miles*allPrice;
						//求出第二段航程的运价
						Double fcFistPrice1 = miles1*allPrice;
						//第三段航程为票面价减去FC航段价格
						Double fcEndPrice = allPrice - fcFistPrice - fcFistPrice1;
						//将三段都记为主FC，并将运价记录
						route.setFlyPrice(fcFistPrice);
						route4.setFlyPrice(fcFistPrice1);
						route5.setFlyPrice(fcEndPrice);
						personInfoList.get(i).setRouteList(routes);
					}
					rt.setPersonTypeInfoList(personInfoList);
				} catch (Exception e) {
					logger.error("标准三段FC判断取值错误（一个FC）",e);
				}
				
			}else if(fcListSize == 2){
				Fc fc = fcList.get(0);
				//如果是两个FC，又是三段航程，需要计算哪个是主FC,
				//（逻辑条件：如果第一个FC的城市为第一段航程的城市，那么这一段就为一个FC）
				if(fc.getToCityCode().equals(route1.getToAirport())){
					//如果第一段航程就是一个Fc,那么后两段就为主FC
					routeList.get(1).setIsFc("Y");
					routeList.get(2).setIsFc("Y");
				}else{
					//如果第一个FC的终点城市为第二段航程的终点城市，那么前两段为一个FC
					//这样就需要判断后两个国际段的FC
					//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
					//首先说两个都为国际航段，所以就直接判断大区
					if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
						//如果第二段没有跨大区，那么需要判断第三段是否跨大区
						if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
							//如果第二段也没有跨大区，判断是否跨大洲
							if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
								//第二段没有跨洲，判断第三段是否跨洲
								if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
									//如果第三段也没有跨洲，就判断公里
									if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
										routeList.get(1).setIsFc("Y");
										routeList.get(0).setIsFc("Y");
									}else{
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第三段跨洲，那么第是三段为主FC
									routeList.get(2).setIsFc("Y");
								}
							}else{
								//如果第二段跨洲，判断第三段是否跨洲
								if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
									//如果第三段没有跨洲，那么第二段为主FC
									routeList.get(0).setIsFc("Y");
									routeList.get(1).setIsFc("Y");
								}else{
									//如果第三段也跨洲，判断公里
									if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
										routeList.get(1).setIsFc("Y");
										routeList.get(0).setIsFc("Y");
									}else{
										routeList.get(2).setIsFc("Y");
									}
								}
							}
						}else{
							//如果第三段跨大区，那么第三段就为主FC
							routeList.get(2).setIsFc("Y");
						}
					}else{
						//如果第二段跨了大区，判断第三段是否跨大区
						if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
							//如果第三段没有跨大区，那么第二段就为主FC
							routeList.get(1).setIsFc("Y");
							routeList.get(0).setIsFc("Y");
						}else{
							//如果第三段也跨大区，直接判断公里
							if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
								routeList.get(1).setIsFc("Y");
								routeList.get(0).setIsFc("Y");
							}else{
								routeList.get(2).setIsFc("Y");
							}
						}
					}
				}
				
				try {
					for(int i = 0 ; i < personInfoList.size(); i++){
						Qte qte = personInfoList.get(i).getQte();
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route = new Route();
						Route route4 = new Route();
						Route route5 = new Route();
						route.setIsFc("Y");
						route.setIsAddOn(route1.getIsAddOn());
						route.setAddOnFly(route1.getAddOnFly());
						route.setIsSPA(route1.getIsSPA());
						route4.setIsFc("Y");
						route4.setIsAddOn(route2.getIsAddOn());
						route4.setAddOnFly(route2.getAddOnFly());
						route4.setIsSPA(route2.getIsSPA());
						route5.setIsFc("Y");
						route5.setIsAddOn(route3.getIsAddOn());
						route5.setAddOnFly(route3.getAddOnFly());
						route5.setIsSPA(route3.getIsSPA());
						routes.add(route);
						routes.add(route4);
						routes.add(route5);
						//先求出第一段FC航程的运价
						Double firstFcPrice = Double.parseDouble(fc.getFcNum())*Double.parseDouble(fc.getRate())*exchangeRate;
						//那么第二段FC的运价就=票面价-第一个FC运价；
						Double secondFCPrice = allPrice - firstFcPrice;
						//既然已经知道第一段就是一个FC（已有运价），那么只需要求后两段运价即可
						int mileSize = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
						//然后求出公里比例
						//第一段航程的比例
						Double miles = (Double.parseDouble(route2.getMileAge())*1d/mileSize);
						//求出第二段航程的运价
						Double fcFistPrice1 = miles*secondFCPrice;
						//第三段航程=第二个FC的运价-第二段航程的运价
						Double fcEndPrice = secondFCPrice - fcFistPrice1;
						//将三段都记为主FC，并将运价记录
						route.setFlyPrice(firstFcPrice);
						route4.setFlyPrice(fcFistPrice1);
						route5.setFlyPrice(fcEndPrice);
						personInfoList.get(i).setRouteList(routes);
					}
				} catch (Exception e) {
					logger.error("三段标准航段运价计算FC取值错误（两个FC）",e);
				}
				rt.setPersonTypeInfoList(personInfoList);
				
			}else{
				//如果为三个FC，那就与上边的逻辑是一样的，只需要判断后面两个航段
				//如果第一个FC的终点城市为第二段航程的终点城市，那么前两段为一个FC
				//这样就需要判断后两个国际段的FC
				//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
				//首先说两个都为国际航段，所以就直接判断大区
				if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
					//如果第二段没有跨大区，那么需要判断第三段是否跨大区
					if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
						//如果第二段也没有跨大区，判断是否跨大洲
						if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
							//第二段没有跨洲，判断第三段是否跨洲
							if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
								//如果第三段也没有跨洲，就判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
									routeList.get(0).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}else{
								//如果第三段跨洲，那么第是三段为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第二段跨洲，判断第三段是否跨洲
							if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
								//如果第三段没有跨洲，那么第二段为主FC
								routeList.get(0).setIsFc("Y");
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第三段也跨洲，判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
									routeList.get(0).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
					}else{
						//如果第三段跨大区，那么第三段就为主FC
						routeList.get(2).setIsFc("Y");
					}
				}else{
					//如果第二段跨了大区，判断第三段是否跨大区
					if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
						//如果第三段没有跨大区，那么第二段就为主FC
						routeList.get(1).setIsFc("Y");
						routeList.get(0).setIsFc("Y");
					}else{
						//如果第三段也跨大区，直接判断公里
						if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
							routeList.get(1).setIsFc("Y");
							routeList.get(0).setIsFc("Y");
						}else{
							routeList.get(2).setIsFc("Y");
						}
					}
				}
			}
			
			try {
				for(int i = 0 ; i < personInfoList.size(); i++){
					Qte qte = personInfoList.get(i).getQte();
					Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
					List<Route> routes = new ArrayList<Route>();
					Fc fc01 = personInfoList.get(i).getFcList().get(0);
					Fc fc02 = personInfoList.get(i).getFcList().get(1);
					Route route = new Route();
					Route route4 = new Route();
					Route route5 = new Route();
					route.setIsFc("Y");
					route.setIsAddOn(route1.getIsAddOn());
					route.setAddOnFly(route1.getAddOnFly());
					route.setIsSPA(route1.getIsSPA());
					route4.setIsFc("Y");
					route4.setIsAddOn(route2.getIsAddOn());
					route4.setAddOnFly(route2.getAddOnFly());
					route4.setIsSPA(route2.getIsSPA());
					route5.setIsFc("Y");
					route5.setIsAddOn(route3.getIsAddOn());
					route5.setAddOnFly(route3.getAddOnFly());
					route5.setIsSPA(route3.getIsSPA());
					routes.add(route);
					routes.add(route4);
					routes.add(route5);
					//先求出第一段FC航程的运价
					Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
					//求出第二段FC运价
					Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
					//那么第二段FC的运价就=票面价-第一个FC运价；
					Double secondFCPrice = allPrice - firstFcPrice - secondFcPrice;
					//将三段都记为主FC，并将运价记录
					route.setFlyPrice(firstFcPrice);
					route4.setFlyPrice(secondFcPrice);
					route5.setFlyPrice(secondFCPrice);
					personInfoList.get(i).setRouteList(routes);
				}
			} catch (Exception e) {
				logger.error("三段标准航段运价计算FC取值错误（两个FC）",e);
			}
			rt.setPersonTypeInfoList(personInfoList);
			
		}else{
			System.out.println("非标准航段，抛与保盛");
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(routeList);
				rt.setPersonTypeInfoList(personInfoList);
			}
			rt.setTravelType("3");
		}
		return rt;
	}
	
	/**
	 * 四段航程
	 * @param routList
	 * @return
	 */
	private Rt getFourListSize(Rt rt,List<PersonTypeInfo> personInfoList,int fcListSize){
		List<Route> routeList = rt.getRoute_list();
		int routeListSize = routeList.size();
		Route route1 = routeList.get(0);
		Route route2 = routeList.get(1);
		Route route3 = routeList.get(2);
		Route route4 = routeList.get(3);
		List<Fc> fcList = rt.getFc();
		Double exchangeRate = 1.0;
		if(null != rt.getExchangeRate()){
			exchangeRate = Double.parseDouble(rt.getExchangeRate());
		}
		if((("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getFromCountryCode()))
			&& ("CN".equals(route4.getToCountryCode()) || "HK".equals(route4.getToCountryCode()))
			&& (!"CN".equals(route2.getToCountryCode()) || !"HK".equals(route3.getFromCountryCode())))
				||((!"CN".equals(route1.getFromCountryCode()) || !"HK".equals(route1.getToCountryCode()))
				&& (!"CN".equals(route4.getFromCountryCode()) || !"HK".equals(route4.getToCountryCode()))
				&& ("CN".equals(route2.getToCountryCode()) || "HK".equals(route3.getFromCountryCode())))){
			rt.setTravelType("1");
			//首先判断有几个FC,如果只有两个FC，去程一个，回程一个
			//如果FC只有两个，说明往返各占一个主FC
			if(fcListSize == 2){
				//如果有两个FC，去程一个含两段，回程一个含两段
				//先把每段航程的运价取到，在去判断FC、SPA、AddOn
				for(int i = 0 ; i < routeListSize; i++){
					routeList.get(i).setIsFc("Y");
				}
				Fc fc = fcList.get(0);
//				Fc fc1 = fcList.get(1);
				try {
					for(int i = 0 ; i < personInfoList.size(); i++){
						Qte qte = personInfoList.get(i).getQte();
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route11 = new Route();
						Route route22 = new Route();
						Route route33 = new Route();
						Route route44 = new Route();
						route11.setIsFc(route1.getIsFc());
						route11.setIsAddOn(route1.getIsAddOn());
						route11.setAddOnFly(route1.getAddOnFly());
						route11.setIsSPA(route1.getIsSPA());
						route22.setIsFc(route2.getIsFc());
						route22.setIsAddOn(route2.getIsAddOn());
						route22.setAddOnFly(route2.getAddOnFly());
						route22.setIsSPA(route2.getIsSPA());
						route33.setIsFc(route2.getIsFc());
						route33.setIsAddOn(route3.getIsAddOn());
						route33.setAddOnFly(route3.getAddOnFly());
						route33.setIsSPA(route3.getIsSPA());
						route44.setIsFc(route2.getIsFc());
						route44.setIsAddOn(route3.getIsAddOn());
						route44.setAddOnFly(route3.getAddOnFly());
						route44.setIsSPA(route3.getIsSPA());
						routes.add(route11);
						routes.add(route22);
						routes.add(route33);
						routes.add(route44);
						//先求出第一段FC航程的运价
						Double firstFcPrice = Double.parseDouble(fc.getFcNum())*Double.parseDouble(fc.getRate())*exchangeRate;
						//那么第二段FC的运价就=票面价-第二个FC运价；
						Double secondFCPrice = allPrice - firstFcPrice;
						//既然已经知道第一段就是一个FC（已有运价），那么只需要求后两段运价即可
						int mileSize1 = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge());
						//求出第一段的公里比例
						Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize1);
						//求出第一段的运价
						Double route1Price = firstFcPrice*route1Miles;
						//求出第二段的运价
						Double route2Price = firstFcPrice -route1Price;
						//算出二个FC的总公里数
						int mileSize2 = Integer.parseInt(route3.getMileAge())+Integer.parseInt(route4.getMileAge());
						//求出第三段的公里比例
						Double route3Miles = (double)(Integer.parseInt(route3.getMileAge())*1d/mileSize2);
						//求出第一段的运价
						Double route3Price = secondFCPrice*route3Miles;
						//求出第二段的运价
						Double route4Price = secondFCPrice -route3Price;
						//将每段航程的运价赋值
						route11.setFlyPrice(route1Price);
						route22.setFlyPrice(route2Price);
						route33.setFlyPrice(route3Price);
						route44.setFlyPrice(route4Price);
						personInfoList.get(i).setRouteList(routes);
					}
				} catch (Exception e) {
					logger.error("四段标准航段，两个Fc段运价计算错误",e);
				}
				rt.setPersonTypeInfoList(personInfoList);
				
				
			//如果有三个fc,麻烦啦。。
			}else if(fcListSize == 3){
				Fc fc1 = personInfoList.get(0).getFcList().get(0);
//				Fc fc2 = personInfoList.get(0).getFcList().get(1);
//				Fc fc3 = personInfoList.get(0).getFcList().get(2);
				//如果第一个FC的终点与第一段航程终点相同，那么去程有两个FC，而回程是一个FC
				if(route1.getToAirport().equals(fc1.getToCityCode())){
					//首先把航程运价取出
					routeList.get(0).setIsFc("N");
					for(int i = 1 ; i < routeListSize; i++){
						routeList.get(i).setIsFc("Y");
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personInfoList.size(); i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
//							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//第三段FC运价
							Double threeFcPrice = allPrice - firstFcPrice - secondFcPrice;
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = secondFcPrice;
							//算出二个FC的总公里数
							int mileSize2 = Integer.parseInt(route3.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第三段的公里比例
							Double route3Miles = (double)(Integer.parseInt(route3.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route3Price = threeFcPrice*route3Miles;
							//求出第二段的运价
							Double route4Price = threeFcPrice -route3Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
				}else{
					//否则就是去程有一个FC，而回程是两个个FC
					//那么，去程只有一个主FC，就要判断回程的主FC
					routeList.get(routeListSize-1).setIsFc("N");
					for(int i = 0 ; i < routeListSize; i++){
						routeList.get(i).setIsFc("Y");
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personInfoList.size(); i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
//							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//第三段FC运价
							Double threeFcPrice = allPrice - firstFcPrice - secondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge());
							//求出第一段的公里比例
							Double route3Miles = (double)(Integer.parseInt(route3.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice*route3Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route1Price;
							//求出第一段的运价
							Double route3Price = secondFcPrice;
							//求出第二段的运价
							Double route4Price = threeFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}
			//如果每段都是一个FC，比三段好那么一点点，每段都是FC，不用计算每段航程的价格
			}else if(fcListSize == 4){
				//每段航程的运价
				int personSize =  personInfoList.size();
				try {
					if("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getFromCountryCode())){
						route2.setIsFc("Y");
						route3.setIsFc("Y");
						route1.setIsFc("N");
						route4.setIsFc("N");
					}else{
						route2.setIsFc("N");
						route3.setIsFc("N");
						route1.setIsFc("Y");
						route4.setIsFc("Y");
					}
					
				} catch (Exception e) {
					logger.error("标准四段，四个FC判断主FC错误");
				}
				try {
					//计算不同旅客类型的每段航程运价
					for(int i = 0 ; i < personSize; i++){
						//去除三段FC
						Fc fc01 = personInfoList.get(i).getFcList().get(0);
						Fc fc02 = personInfoList.get(i).getFcList().get(1);
						Fc fc03 = personInfoList.get(i).getFcList().get(2);
						Qte qte = personInfoList.get(i).getQte();
						//取出票面价
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route11 = new Route();
						Route route22 = new Route();
						Route route33 = new Route();
						Route route44 = new Route();
						//重新录入第一航段的计算相关信息
						route11.setIsFc(route1.getIsFc());
						route11.setIsAddOn(route1.getIsAddOn());
						route11.setAddOnFly(route1.getAddOnFly());
						route11.setIsSPA(route1.getIsSPA());
						//重新录入第二航段的计算相关信息
						route22.setIsFc(route2.getIsFc());
						route22.setIsAddOn(route2.getIsAddOn());
						route22.setAddOnFly(route2.getAddOnFly());
						route22.setIsSPA(route2.getIsSPA());
						//重新录入第二航段的计算相关信息
						route33.setIsFc(route3.getIsFc());
						route33.setIsAddOn(route3.getIsAddOn());
						route33.setAddOnFly(route3.getAddOnFly());
						route33.setIsSPA(route3.getIsSPA());
						//重新录入第四航段的计算相关信息
						route44.setIsFc(route4.getIsFc());
						route44.setIsAddOn(route4.getIsAddOn());
						route44.setAddOnFly(route4.getAddOnFly());
						route44.setIsSPA(route4.getIsSPA());
						//先求出第一段FC航程的运价
						Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
						//那么第二段FC的运价
						Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
						//那么第三段FC的运价
						Double threedFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
						//第三段FC运价
						Double fourFcPrice = allPrice - firstFcPrice - secondFcPrice-threedFcPrice;
//						//算出一个FC的总公里数
//						int mileSize2 = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge());
//						//求出第一段的公里比例
//						Double route3Miles = (double)(Integer.parseInt(route3.getMileAge())*1d/mileSize2);
//						//求出第一段的运价
//						Double route1Price = firstFcPrice*route3Miles;
//						//求出第二段的运价
//						Double route2Price = firstFcPrice - route1Price;
//						//求出第一段的运价
//						Double route3Price = secondFcPrice;
//						//求出第二段的运价
//						Double route4Price = threeFcPrice;
						//将每段航程的运价赋值
						route11.setFlyPrice(firstFcPrice);
						route22.setFlyPrice(secondFcPrice);
						route33.setFlyPrice(threedFcPrice);
						route44.setFlyPrice(fourFcPrice);
						routes.add(route11);
						routes.add(route22);
						routes.add(route33);
						routes.add(route44);
						personInfoList.get(i).setRouteList(routes);
					}
				} catch (Exception e) {
					logger.error("四段标准航段，两个Fc段运价计算错误",e);
				}
				rt.setPersonTypeInfoList(personInfoList);
			}else{
				System.out.println("文本FC取数错误");
				for(int i = 0 ; i < personInfoList.size(); i++){
					// 获取第一段航程票价
					personInfoList.get(i).setRouteList(routeList);
					rt.setPersonTypeInfoList(personInfoList);
				}
				rt.setTravelType("3");
			}
		}else{
			System.out.println("4段航程的非标准航段，抛与保盛");
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(routeList);
				rt.setPersonTypeInfoList(personInfoList);
			}
			rt.setTravelType("3");
		}
		return rt;
	}
	
	
	
	/**
	 * 六段航程
	 * @param routList
	 * @return
	 */
	private Rt getSixListSize(Rt rt, List<PersonTypeInfo> personInfoList,int fcListSize){
		List<Route> routeList = rt.getRoute_list();
		int routeListSize = routeList.size();
		List<Fc> fcList = rt.getFc();
		Double exchangeRate = 1.0;
		if(null != rt.getExchangeRate()){
			exchangeRate = Double.parseDouble(rt.getExchangeRate());
		}
		int personSize = personInfoList.size();
		//标准航段匹配往返含国内一段
		Route route1 = routeList.get(0);
		Route route2 = routeList.get(1);
		Route route3 = routeList.get(2);
		Route route4 = routeList.get(3);
		Route route5 = routeList.get(4);
		Route route6 = routeList.get(5);
		if(("CN".equals(route1.getFromCountryCode()) || "HK".equals(route1.getFromCountryCode()))
			&& ("CN".equals(route1.getToCountryCode()) || "HK".equals(route1.getToCountryCode()))
			&& !"CN".equals(route2.getToCountryCode()) && !"CN".equals(route3.getToCountryCode())
			&& (!"CN".equals(route4.getToCountryCode()) || !"HK".equals(route4.getToCountryCode()))
			&& ("CN".equals(route5.getToCountryCode()) || "HK".equals(route5.getToCountryCode())) 
			&& ("CN".equals(route6.getFromCountryCode()) || "HK".equals(route6.getFromCountryCode()))
			&& ("CN".equals(route6.getToCountryCode()) || "HK".equals(route6.getToCountryCode()))){
			rt.setTravelType("1");
			//先将AddOn和SPA分喽,其中第一段与最后一段都是国内AddOn
			routeList.get(0).setIsAddOn("Y");
			routeList.get(0).setAddOnFly("0");
			routeList.get(5).setIsAddOn("Y");
			routeList.get(5).setAddOnFly("0");
			for(int i = 1; i < 5; i++){
				if(rt.getComput().equals(routeList.get(i).getFlight().substring(0,2))){
					//国际AddOn
					routeList.get(i).setIsAddOn("Y");
					routeList.get(i).setAddOnFly("1");
				}else{
					routeList.get(i).setIsSPA("Y");
				}
			}
			//下面就是判断FC。。。,往返嘛，至少两个FC
			if(fcListSize == 2){
				//fc只有两个，就是说，每段都是主FC
				for(int i = 0; i < routeListSize; i++){
					routeList.get(i).setIsFc("Y");
				}
				try {
					//计算不同旅客类型的每段航程运价
					for(int i = 0 ; i < personSize; i++){
						//去除三段FC
						Fc fc01 = personInfoList.get(i).getFcList().get(0);
//						Fc fc02 = personInfoList.get(i).getFcList().get(1);
						Qte qte = personInfoList.get(i).getQte();
						//取出票面价
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route11 = new Route();
						Route route22 = new Route();
						Route route33 = new Route();
						Route route44 = new Route();
						Route route55 = new Route();
						Route route66 = new Route();
						//重新录入第一航段的计算相关信息
						route11.setIsFc(route1.getIsFc());
						route11.setIsAddOn(route1.getIsAddOn());
						route11.setAddOnFly(route1.getAddOnFly());
						route11.setIsSPA(route1.getIsSPA());
						//重新录入第二航段的计算相关信息
						route22.setIsFc(route2.getIsFc());
						route22.setIsAddOn(route2.getIsAddOn());
						route22.setAddOnFly(route2.getAddOnFly());
						route22.setIsSPA(route2.getIsSPA());
						//重新录入第二航段的计算相关信息
						route33.setIsFc(route3.getIsFc());
						route33.setIsAddOn(route3.getIsAddOn());
						route33.setAddOnFly(route3.getAddOnFly());
						route33.setIsSPA(route3.getIsSPA());
						//重新录入第四航段的计算相关信息
						route44.setIsFc(route4.getIsFc());
						route44.setIsAddOn(route4.getIsAddOn());
						route44.setAddOnFly(route4.getAddOnFly());
						route44.setIsSPA(route4.getIsSPA());
						//重新录入第五航段的计算相关信息
						route55.setIsFc(route5.getIsFc());
						route55.setIsAddOn(route5.getIsAddOn());
						route55.setAddOnFly(route5.getAddOnFly());
						route55.setIsSPA(route5.getIsSPA());
						//先求出第一段FC航程的运价
						Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
						//那么第二段FC的运价
						Double secondFcPrice = allPrice - firstFcPrice;
						//算出一个FC的总公里数
						int mileSize2 = Integer.parseInt(route1.getMileAge())+Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
						//求出第一段的公里比例
						Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
						//求出第二段的公里比例
						Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
						//求出第一段的运价
						Double route1Price = firstFcPrice*route1Miles;
						//求出第二段的运价
						Double route2Price = firstFcPrice*route2Miles;
						//求出第三段的运价
						Double route3Price = firstFcPrice - route1Price - route2Price;
						//第二个FC的总公里数
						int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
						//求出第一段的公里比例
						Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
						//求出第二段的公里比例
						Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
						//求出第四段的运价
						Double route4Price = secondFcPrice * route4Miles;
						//求出第五段的运价
						Double route5Price = secondFcPrice * route5Miles;
						//求出第六段的运价
						Double route6Price = secondFcPrice - route4Price - route5Price;
						//将每段航程的运价赋值
						route11.setFlyPrice(route1Price);
						route22.setFlyPrice(route2Price);
						route33.setFlyPrice(route3Price);
						route44.setFlyPrice(route4Price);
						route55.setFlyPrice(route5Price);
						route66.setFlyPrice(route6Price);
						routes.add(route11);
						routes.add(route22);
						routes.add(route33);
						routes.add(route44);
						routes.add(route55);
						routes.add(route66);
						personInfoList.get(i).setRouteList(routes);
					}
				} catch (Exception e) {
					logger.error("四段标准航段，两个Fc段运价计算错误",e);
				}
				rt.setPersonTypeInfoList(personInfoList);
			}else if(fcListSize == 3){
				Fc fc1 = fcList.get(0);
				Fc fc2 = fcList.get(0);
				Fc fc3 = fcList.get(0);
				//三个FC，要判断去程和返程那个有俩
				if(route1.getToAirport().equals(fc1.getToCityCode())){
					//如果第一段就是去程有两个fC,这样就只需要判断去程那个是主FC
					//很简单，第一段没有跨国。。所以主Fc在去程的第二段和第三段
					
					for(int i = 1 ; i < 6; i++){
						routeList.get(i).setIsFc("Y");
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
							//求出第一段的公里比例
							Double route1Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
//							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = goFirstFcPrice * route1Miles;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice - route2Price;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = secondFcPrice * route4Miles;
							//求出第五段的运价
							Double route5Price = secondFcPrice * route5Miles;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route4Price - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}else if(fc1.getToCityCode().equals(route2.getToAirport())){
					//如果前两段是一个Fc,则需要判断去程的主FC,回程就不需要了
					//依旧把回程都作为主Fc
					//无疑，回程全是主Fc
					for(int i = 3 ; i < 6; i++){
						routeList.get(i).setIsFc("Y");
					}
					
					try {
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(0).setIsFc("Y");
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(0).setIsFc("Y");
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(0).setIsFc("Y");
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(0).setIsFc("Y");
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(0).setIsFc("Y");
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段，三个FC是判断FC取值错误",e);
					}
					
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
							//求出第一段的公里比例
							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
//							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route1Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route1Price;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = secondFcPrice * route4Miles;
							//求出第五段的运价
							Double route5Price = secondFcPrice * route5Miles;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route4Price - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					//如果返程的第一个航程为一个FC，那么去程都为主FC，需要判断返程那个为主FC
				}else if(fc2.getToCityCode().equals(route4.getToAirport())){
					for(int i = 0 ; i < 3; i++){
						routeList.get(i).setIsFc("Y");
					}
					try {
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
											routeList.get(4).setIsFc("Y");
										}else{
											routeList.get(5).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(3).setIsFc("Y");
										routeList.get(4).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
											routeList.get(4).setIsFc("Y");
										}else{
											routeList.get(5).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(5).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(3).setIsFc("Y");
								routeList.get(4).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
									routeList.get(3).setIsFc("Y");
									routeList.get(4).setIsFc("Y");
								}else{
									routeList.get(5).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段，三个FC是判断FC取值错误",e);
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
							//求出第一段的公里比例
							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route1Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice * route2Miles;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice - route1Price - route2Price;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = secondFcPrice * route4Miles;
							//求出第五段的运价
							Double route5Price = secondFcPrice - route4Price;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route4Price - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}else if(fc2.getToCityCode().equals(route5.getToAirport())){
					//如果最后一个航段为一个FC，那么那个是一个国内航段，所以其他都为主FC，不做判断
					for(int i = 0 ; i < 5; i++){
						routeList.get(i).setIsFc("Y");
					}
					
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
							//求出第一段的公里比例
							Double route1Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route1Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice * route2Miles;
							//求出第三段的运价
							Double route3Price = firstFcPrice - route2Price - route2Price;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = goFirstFcPrice * route4Miles;
							//求出第五段的运价
							Double route5Price = goFirstFcPrice - route4Price;
							//求出第六段的运价
							Double route6Price = secondFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}else{
					for(int i = 0 ; i < personInfoList.size(); i++){
						// 获取第一段航程票价
						personInfoList.get(i).setRouteList(routeList);
						rt.setPersonTypeInfoList(personInfoList);
					}
					rt.setTravelType("3");
				}
				
				//如果有四个FC，开挂吧。。。
			}else if(fcListSize == 4){
				//先总结一下：1、去程3个，回程1个(1种情况)；2、去程两个，回程两个（4种情况）；3、去程一个，回程3个(1种情况)
				Fc fc1 = fcList.get(0);
				Fc fc2 = fcList.get(1);
				Fc fc3 = fcList.get(2);
				Fc fc4 = fcList.get(3);
				//先做第一种的第一个情况 （A-B B-C C-D;D-A）
				if(fc3.getToCityCode().equals(route3.getToAirport())
					&& fc1.getToCityCode().equals(route1.getToAirport())
					&& fc2.getToCityCode().equals(route2.getToAirport())){
					//那么回程全是主FC，去程需要判断第二段和第三段哪个是主FC（第一段为国内，不需判断）
					routeList.get(0).setIsFc("Y");
					for(int i = 3 ; i < 6 ; i++){
						routeList.get(i).setIsFc("Y");
					}
					//判断大区吧
					try {
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段，三个FC是判断FC取值错误",e);
					}
					
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第二段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
//							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
							//求出第一段的公里比例
//							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
//							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = goFirstFcPrice;
							//求出第三段的运价
							Double route3Price = gosecondFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route4.getMileAge())+Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = secondFcPrice * route4Miles;
							//求出第五段的运价
							Double route5Price = secondFcPrice * route5Miles;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route4Price - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					
					
					
					
				//第二种的第1个情况（A-B B-C-D;D-C C-B-A）
				//这种情况去程该是第二段和第三段为主FC，而回程就需要判断哪个为主FC
				}else if(fc1.getToCityCode().equals(route1.getToAirport())
						&& fc3.getToCityCode().equals(route4.getToAirport())){
					routeList.get(1).setIsFc("Y");
					routeList.get(2).setIsFc("Y");
					try {
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
										}else{
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(3).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}else{
											routeList.get(3).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(3).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
									routeList.get(3).setIsFc("Y");
								}else{
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段，三个FC是判断FC取值错误",e);
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第四段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
							//求出第一段的公里比例
//							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = goFirstFcPrice * route2Miles;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice - route2Price;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = gosecondFcPrice;
							//求出第五段的运价
							Double route5Price = secondFcPrice * route5Miles;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("四段标准航段，两个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					//这种情况（A-B B-C-D;D-C-B B-A）
				}else if(fc1.getToCityCode().equals(route1.getToAirport()) 
						&& fc3.getToCityCode().equals(route5.getToAirport())){
					//这种情况，不用判断，国际段为主FC
					for(int i = 1 ; i < 5 ; i++){
						routeList.get(i).setIsFc("Y");
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第四段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
							//求出第一段的公里比例
//							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = goFirstFcPrice * route2Miles;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice - route2Price;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第一段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = gosecondFcPrice * route5Miles;
							//求出第五段的运价
							Double route5Price = gosecondFcPrice * route4Price;
							//求出第六段的运价
							Double route6Price = secondFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					
					
					
					//(A-B-C C-D;D-C C-B-A)
				}else if(fc1.getToCityCode().equals(route2.getToAirport())
						&& fc3.getToCityCode().equals(route4.getToAirport())){
					//这个比较麻烦呐，去程回程都要判断
					
					try {
						//判断大区吧、去程的
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
						
						//判断返程的主FC
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
										}else{
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(3).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}else{
											routeList.get(3).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(3).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
									routeList.get(3).setIsFc("Y");
								}else{
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}
						}
						
						
					} catch (Exception e) {
						logger.error("标准6段4个FC取值错误",e);
					}
					
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第四段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
							//求出第一段的公里比例
//							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route2Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route1Price;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第一段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = gosecondFcPrice;
							//求出第五段的运价
							Double route5Price = gosecondFcPrice * route5Miles;
							//求出第六段的运价
							Double route6Price = secondFcPrice - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					//(A-B-C C-D;D-C-B B-A)
				}else if(fc1.getToCityCode().equals(route2.getToAirport()) && fc3.getToCityCode().equals(route5.getToAirport())){
					//回程不需要判断，判读去程后两段的主FC
					routeList.get(3).setIsFc("Y");
					routeList.get(4).setIsFc("Y");
					//判断大区吧、去程的
					try {
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段4个FC取值错误",e);
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第四段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
							//求出第一段的公里比例
//							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route2Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route1Price;
							//求出第三段的运价
							Double route3Price = goFirstFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第一段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = gosecondFcPrice * route5Miles;
							//求出第五段的运价
							Double route5Price = gosecondFcPrice * route4Price;
							//求出第六段的运价
							Double route6Price = secondFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					//(A-B-C-D;D-C C-B B-A)
				}else if(fc1.getToCityCode().equals(route3.getToAirport())){
					//这种情况就只需要判断返程的两段国际FC
					for(int i = 0 ; i <= 2; i++){
						routeList.get(i).setIsFc("Y");
					}
					try{
					//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
					//首先说两个都为国际航段，所以就直接判断大区
					if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
						//如果第一段没有跨大区，那么需要判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段也没有跨大区，判断是否跨大洲
							if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
								//第一段没有跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段也没有跨洲，就判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(3).setIsFc("Y");
									}else{
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第二段跨洲，那么第二段为主FC
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}else{
								//如果第一段跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段没有跨洲，那么第一段为主FC
									routeList.get(3).setIsFc("Y");
								}else{
									//如果第二段也跨洲，判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}else{
										routeList.get(3).setIsFc("Y");
									}
								}
							}
						}else{
							//如果第二段跨大区，那么第二段就为主FC
							routeList.get(4).setIsFc("Y");
							routeList.get(5).setIsFc("Y");
						}
					}else{
						//如果第一段跨了大区，判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段没有跨大区，那么第一段就为主FC
							routeList.get(3).setIsFc("Y");
						}else{
							//如果第二段也跨大区，直接判断公里
							if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
								routeList.get(3).setIsFc("Y");
							}else{
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}
					}
					
					
				} catch (Exception e) {
					logger.error("标准6段4个FC取值错误",e);
				}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double goFirstFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double gosecondFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//那么第四段FC的运价
							Double secondFcPrice = allPrice - firstFcPrice - goFirstFcPrice - gosecondFcPrice;
							//算出一个FC的总公里数
							int mileSize2 = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge())+Integer.parseInt(route3.getMileAge());
							//求出第一段的公里比例
							Double route1Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize2);
//							//求出第二段的公里比例
							Double route2Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize2);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route1Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route2Miles;
							//求出第三段的运价
							Double route3Price = firstFcPrice - route2Price - route1Price;
							//第二个FC的总公里数
//							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第一段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第四段的运价
							Double route4Price = goFirstFcPrice;
							//求出第五段的运价
							Double route5Price = gosecondFcPrice;
							//求出第六段的运价
							Double route6Price = secondFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}else{
					/**怕还有别的情况，返回去吧*/
					System.out.println("六段非标准航段");
					for(int i = 0 ; i < personInfoList.size(); i++){
						// 获取第一段航程票价
						personInfoList.get(i).setRouteList(routeList);
						rt.setPersonTypeInfoList(personInfoList);
					}
					rt.setTravelType("3");
				}
				//如果有5个FC，开挂也不好使。。。。
			}else if(fcListSize == 5){
				//1、去程3个，回程两个；2、去程2个，回程3个
				Fc fc4 = fcList.get(3);
				Fc fc1 = fcList.get(0);
				//(A-B B-C C-D;D-C C-B-A)
				if(fc4.getToCityCode().equals(route5.getToAirport())){
					try {
						//判断大区吧、去程的
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
						
						//判断返程的主FC
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
										}else{
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(3).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}else{
											routeList.get(3).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(3).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
									routeList.get(3).setIsFc("Y");
								}else{
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}
						}
						
						
					} catch (Exception e) {
						logger.error("标准6段5个FC取值错误",e);
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Fc fc04 = personInfoList.get(i).getFcList().get(3);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double threeFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//先求出第四段FC航程的运价
							Double fourFcPrice = Double.parseDouble(fc04.getFcNum())*Double.parseDouble(fc04.getRate())*exchangeRate;
							//那么第五段FC的运价
							Double fiveFcPrice = allPrice - firstFcPrice - secondFcPrice - threeFcPrice - fourFcPrice;
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = secondFcPrice;
							//求出第三段的运价
							Double route3Price = threeFcPrice;
							//第五个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route6.getMileAge());
							//求出第五段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route5.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							
							//求出第四段的运价
							Double route4Price = fourFcPrice;
							
							//求出第五段的运价
							Double route5Price = fiveFcPrice * route4Miles;
							//求出第六段的运价
							Double route6Price = fiveFcPrice - route5Price;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					
					//航段情况(A-B B-C C-D;D-C-B B-A)
				}else if(fc4.getToCityCode().equals(route5.getToAirport())){
					//这种情况，前五段该都是主FC
					routeList.get(3).setIsFc("Y");
					routeList.get(4).setIsFc("Y");
					try {
						//判断大区吧、去程的
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
					} catch (Exception e) {
						logger.error("标准6段4个FC取值错误",e);
					}
					
					
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Fc fc04 = personInfoList.get(i).getFcList().get(3);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double threeFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//先求出第四段FC航程的运价
							Double fourFcPrice = Double.parseDouble(fc04.getFcNum())*Double.parseDouble(fc04.getRate())*exchangeRate;
							//那么第五段FC的运价
							Double fiveFcPrice = allPrice - firstFcPrice - secondFcPrice - threeFcPrice - fourFcPrice;
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//求出第二段的运价
							Double route2Price = secondFcPrice;
							//求出第三段的运价
							Double route3Price = threeFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第四段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							
							//求出第四段的运价
							Double route4Price = fourFcPrice * route4Miles;
							
							//求出第五段的运价
							Double route5Price = fourFcPrice - route4Price;
							//求出第六段的运价
							Double route6Price = fiveFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					//航段情况(A-B B-C-D;D-C C-B B-A)
				}else if(fc1.getToCityCode().equals(route1.getToAirport())){
					routeList.get(1).setIsFc("Y");
					routeList.get(2).setIsFc("Y");
					//判断第四段和第五段的主FC
					//判断回程第一段（四段）与第二段（五段）的主FC,判断大区
					//首先说两个都为国际航段，所以就直接判断大区
					try{
					if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
						//如果第一段没有跨大区，那么需要判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段也没有跨大区，判断是否跨大洲
							if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
								//第一段没有跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段也没有跨洲，就判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(3).setIsFc("Y");
									}else{
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第二段跨洲，那么第二段为主FC
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}else{
								//如果第一段跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段没有跨洲，那么第一段为主FC
									routeList.get(3).setIsFc("Y");
								}else{
									//如果第二段也跨洲，判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}else{
										routeList.get(3).setIsFc("Y");
									}
								}
							}
						}else{
							//如果第二段跨大区，那么第二段就为主FC
							routeList.get(4).setIsFc("Y");
							routeList.get(5).setIsFc("Y");
						}
					}else{
						//如果第一段跨了大区，判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段没有跨大区，那么第一段就为主FC
							routeList.get(3).setIsFc("Y");
						}else{
							//如果第二段也跨大区，直接判断公里
							if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
								routeList.get(3).setIsFc("Y");
							}else{
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}
					}
					
					
				} catch (Exception e) {
					logger.error("标准6段5个FC取值错误",e);
				}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Fc fc04 = personInfoList.get(i).getFcList().get(3);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double threeFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//先求出第四段FC航程的运价
							Double fourFcPrice = Double.parseDouble(fc04.getFcNum())*Double.parseDouble(fc04.getRate())*exchangeRate;
							//那么第五段FC的运价
							Double fiveFcPrice = allPrice - firstFcPrice - secondFcPrice - threeFcPrice - fourFcPrice;
							//求出第一段的运价
							Double route1Price = firstFcPrice;
							//第二个FC的总公里数
							int mileSize = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route3.getMileAge());
//							求出第四段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route2.getMileAge())*1d/mileSize);
							//求出第二段的运价
							Double route2Price = secondFcPrice * route4Miles;
							//求出第三段的运价
							Double route3Price = secondFcPrice - route2Price;
							//第二个FC的总公里数
//							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第四段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							
							//求出第四段的运价
							Double route4Price = threeFcPrice ;
							
							//求出第五段的运价
							Double route5Price = fourFcPrice;
							//求出第六段的运价
							Double route6Price = fiveFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
					
					
					//航段情况(A-B-C C-D;D-C C-B B-A)
				}else if(fc1.getToCityCode().equals(route2.getToAirport())){
					try {
						//判断大区吧、去程的
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(1).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
											routeList.get(1).setIsFc("Y");
										}else{
											routeList.get(2).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(2).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(1).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
									routeList.get(1).setIsFc("Y");
								}else{
									routeList.get(2).setIsFc("Y");
								}
							}
						}
						
						//判断返程的主FC
						//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
						//首先说两个都为国际航段，所以就直接判断大区
						if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
							//如果第一段没有跨大区，那么需要判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段也没有跨大区，判断是否跨大洲
								if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
									//第一段没有跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段也没有跨洲，就判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(3).setIsFc("Y");
										}else{
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}
									}else{
										//如果第二段跨洲，那么第二段为主FC
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第一段跨洲，判断第二段是否跨洲
									if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
										//如果第二段没有跨洲，那么第一段为主FC
										routeList.get(3).setIsFc("Y");
									}else{
										//如果第二段也跨洲，判断公里
										if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
											routeList.get(4).setIsFc("Y");
											routeList.get(5).setIsFc("Y");
										}else{
											routeList.get(3).setIsFc("Y");
										}
									}
								}
							}else{
								//如果第二段跨大区，那么第二段就为主FC
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}else{
							//如果第一段跨了大区，判断第二段是否跨大区
							if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
								//如果第二段没有跨大区，那么第一段就为主FC
								routeList.get(3).setIsFc("Y");
							}else{
								//如果第二段也跨大区，直接判断公里
								if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
									routeList.get(3).setIsFc("Y");
								}else{
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}
						}
						
						
					} catch (Exception e) {
						logger.error("标准6段4个FC取值错误",e);
					}
					try {
						//计算不同旅客类型的每段航程运价
						for(int i = 0 ; i < personSize; i++){
							//去除三段FC
							Fc fc01 = personInfoList.get(i).getFcList().get(0);
							Fc fc02 = personInfoList.get(i).getFcList().get(1);
							Fc fc03 = personInfoList.get(i).getFcList().get(2);
							Fc fc04 = personInfoList.get(i).getFcList().get(3);
							Qte qte = personInfoList.get(i).getQte();
							//取出票面价
							Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
							List<Route> routes = new ArrayList<Route>();
							Route route11 = new Route();
							Route route22 = new Route();
							Route route33 = new Route();
							Route route44 = new Route();
							Route route55 = new Route();
							Route route66 = new Route();
							//重新录入第一航段的计算相关信息
							route11.setIsFc(route1.getIsFc());
							route11.setIsAddOn(route1.getIsAddOn());
							route11.setAddOnFly(route1.getAddOnFly());
							route11.setIsSPA(route1.getIsSPA());
							//重新录入第二航段的计算相关信息
							route22.setIsFc(route2.getIsFc());
							route22.setIsAddOn(route2.getIsAddOn());
							route22.setAddOnFly(route2.getAddOnFly());
							route22.setIsSPA(route2.getIsSPA());
							//重新录入第二航段的计算相关信息
							route33.setIsFc(route3.getIsFc());
							route33.setIsAddOn(route3.getIsAddOn());
							route33.setAddOnFly(route3.getAddOnFly());
							route33.setIsSPA(route3.getIsSPA());
							//重新录入第四航段的计算相关信息
							route44.setIsFc(route4.getIsFc());
							route44.setIsAddOn(route4.getIsAddOn());
							route44.setAddOnFly(route4.getAddOnFly());
							route44.setIsSPA(route4.getIsSPA());
							//重新录入第五航段的计算相关信息
							route55.setIsFc(route5.getIsFc());
							route55.setIsAddOn(route5.getIsAddOn());
							route55.setAddOnFly(route5.getAddOnFly());
							route55.setIsSPA(route5.getIsSPA());
							//先求出第一段FC航程的运价
							Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
							//先求出第二段FC航程的运价
							Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
							//先求出第三段FC航程的运价
							Double threeFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
							//先求出第四段FC航程的运价
							Double fourFcPrice = Double.parseDouble(fc04.getFcNum())*Double.parseDouble(fc04.getRate())*exchangeRate;
							//那么第五段FC的运价
							Double fiveFcPrice = allPrice - firstFcPrice - secondFcPrice - threeFcPrice - fourFcPrice;
							//第一个FC的总公里数
							int mileSize = Integer.parseInt(route2.getMileAge())+Integer.parseInt(route1.getMileAge());
//							求出第四段的公里比例
							Double route4Miles = (double)(Integer.parseInt(route1.getMileAge())*1d/mileSize);
							//求出第一段的运价
							Double route1Price = firstFcPrice * route4Miles;
							//求出第二段的运价
							Double route2Price = firstFcPrice - route1Price;
							//求出第三段的运价
							Double route3Price = secondFcPrice;
							//第二个FC的总公里数
//							int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
							//求出第四段的公里比例
//							Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							//求出第二段的公里比例
//							Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
							
							//求出第四段的运价
							Double route4Price = threeFcPrice ;
							
							//求出第五段的运价
							Double route5Price = fourFcPrice;
							//求出第六段的运价
							Double route6Price = fiveFcPrice;
							//将每段航程的运价赋值
							route11.setFlyPrice(route1Price);
							route22.setFlyPrice(route2Price);
							route33.setFlyPrice(route3Price);
							route44.setFlyPrice(route4Price);
							route55.setFlyPrice(route5Price);
							route66.setFlyPrice(route6Price);
							routes.add(route11);
							routes.add(route22);
							routes.add(route33);
							routes.add(route44);
							routes.add(route55);
							routes.add(route66);
							personInfoList.get(i).setRouteList(routes);
						}
					} catch (Exception e) {
						logger.error("六段标准航段，四个Fc段运价计算错误",e);
					}
					rt.setPersonTypeInfoList(personInfoList);
				}else{
					/**怕还有别的情况，返回去吧*/
					for(int i = 0 ; i < personInfoList.size(); i++){
						// 获取第一段航程票价
						personInfoList.get(i).setRouteList(routeList);
						rt.setPersonTypeInfoList(personInfoList);
					}
				}
			//如果是6个FC，这个。。。。
			}else if(fcListSize == 6){
				//判断去程2 3段和返程4 5 段的主FC
				try {
					//判断大区吧、去程的
					//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
					//首先说两个都为国际航段，所以就直接判断大区
					if(route2.getFromAreaCode().equals(route2.getToAreaCode())){
						//如果第一段没有跨大区，那么需要判断第二段是否跨大区
						if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
							//如果第二段也没有跨大区，判断是否跨大洲
							if(route2.getFromRegionCode().equals(route2.getToRegionCode())){
								//第一段没有跨洲，判断第二段是否跨洲
								if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
									//如果第二段也没有跨洲，就判断公里
									if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
										routeList.get(1).setIsFc("Y");
									}else{
										routeList.get(2).setIsFc("Y");
									}
								}else{
									//如果第二段跨洲，那么第二段为主FC
									routeList.get(2).setIsFc("Y");
								}
							}else{
								//如果第一段跨洲，判断第二段是否跨洲
								if(route3.getFromRegionCode().equals(route3.getToRegionCode())){
									//如果第二段没有跨洲，那么第一段为主FC
									routeList.get(1).setIsFc("Y");
								}else{
									//如果第二段也跨洲，判断公里
									if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
										routeList.get(1).setIsFc("Y");
									}else{
										routeList.get(2).setIsFc("Y");
									}
								}
							}
						}else{
							//如果第二段跨大区，那么第二段就为主FC
							routeList.get(2).setIsFc("Y");
						}
					}else{
						//如果第一段跨了大区，判断第二段是否跨大区
						if(route3.getFromAreaCode().equals(route3.getToAreaCode())){
							//如果第二段没有跨大区，那么第一段就为主FC
							routeList.get(1).setIsFc("Y");
						}else{
							//如果第二段也跨大区，直接判断公里
							if(Integer.parseInt(route2.getMileAge()) >= Integer.parseInt(route3.getMileAge())){
								routeList.get(1).setIsFc("Y");
							}else{
								routeList.get(2).setIsFc("Y");
							}
						}
					}
					
					//判断返程的主FC
					//如果是两个FC，那么需要判断哪个为主FC，大区》大洲》跨国》公里
					//首先说两个都为国际航段，所以就直接判断大区
					if(route4.getFromAreaCode().equals(route4.getToAreaCode())){
						//如果第一段没有跨大区，那么需要判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段也没有跨大区，判断是否跨大洲
							if(route4.getFromRegionCode().equals(route4.getToRegionCode())){
								//第一段没有跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段也没有跨洲，就判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(3).setIsFc("Y");
									}else{
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}
								}else{
									//如果第二段跨洲，那么第二段为主FC
									routeList.get(4).setIsFc("Y");
									routeList.get(5).setIsFc("Y");
								}
							}else{
								//如果第一段跨洲，判断第二段是否跨洲
								if(route5.getFromRegionCode().equals(route5.getToRegionCode())){
									//如果第二段没有跨洲，那么第一段为主FC
									routeList.get(3).setIsFc("Y");
								}else{
									//如果第二段也跨洲，判断公里
									if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
										routeList.get(4).setIsFc("Y");
										routeList.get(5).setIsFc("Y");
									}else{
										routeList.get(3).setIsFc("Y");
									}
								}
							}
						}else{
							//如果第二段跨大区，那么第二段就为主FC
							routeList.get(4).setIsFc("Y");
							routeList.get(5).setIsFc("Y");
						}
					}else{
						//如果第一段跨了大区，判断第二段是否跨大区
						if(route5.getFromAreaCode().equals(route5.getToAreaCode())){
							//如果第二段没有跨大区，那么第一段就为主FC
							routeList.get(3).setIsFc("Y");
						}else{
							//如果第二段也跨大区，直接判断公里
							if(Integer.parseInt(route4.getMileAge()) >= Integer.parseInt(route5.getMileAge())){
								routeList.get(3).setIsFc("Y");
							}else{
								routeList.get(4).setIsFc("Y");
								routeList.get(5).setIsFc("Y");
							}
						}
					}
					
					
				} catch (Exception e) {
					logger.error("标准6段4个FC取值错误",e);
				}
				
				try {
					//计算不同旅客类型的每段航程运价
					for(int i = 0 ; i < personSize; i++){
						//去除三段FC
						Fc fc01 = personInfoList.get(i).getFcList().get(0);
						Fc fc02 = personInfoList.get(i).getFcList().get(1);
						Fc fc03 = personInfoList.get(i).getFcList().get(2);
						Fc fc04 = personInfoList.get(i).getFcList().get(3);
						Fc fc05 = personInfoList.get(i).getFcList().get(4);
						Qte qte = personInfoList.get(i).getQte();
						//取出票面价
						Double allPrice = Double.parseDouble(qte.getPrice())-Double.parseDouble(qte.getTax());
						List<Route> routes = new ArrayList<Route>();
						Route route11 = new Route();
						Route route22 = new Route();
						Route route33 = new Route();
						Route route44 = new Route();
						Route route55 = new Route();
						Route route66 = new Route();
						//重新录入第一航段的计算相关信息
						route11.setIsFc(route1.getIsFc());
						route11.setIsAddOn(route1.getIsAddOn());
						route11.setAddOnFly(route1.getAddOnFly());
						route11.setIsSPA(route1.getIsSPA());
						//重新录入第二航段的计算相关信息
						route22.setIsFc(route2.getIsFc());
						route22.setIsAddOn(route2.getIsAddOn());
						route22.setAddOnFly(route2.getAddOnFly());
						route22.setIsSPA(route2.getIsSPA());
						//重新录入第二航段的计算相关信息
						route33.setIsFc(route3.getIsFc());
						route33.setIsAddOn(route3.getIsAddOn());
						route33.setAddOnFly(route3.getAddOnFly());
						route33.setIsSPA(route3.getIsSPA());
						//重新录入第四航段的计算相关信息
						route44.setIsFc(route4.getIsFc());
						route44.setIsAddOn(route4.getIsAddOn());
						route44.setAddOnFly(route4.getAddOnFly());
						route44.setIsSPA(route4.getIsSPA());
						//重新录入第五航段的计算相关信息
						route55.setIsFc(route5.getIsFc());
						route55.setIsAddOn(route5.getIsAddOn());
						route55.setAddOnFly(route5.getAddOnFly());
						route55.setIsSPA(route5.getIsSPA());
						//先求出第一段FC航程的运价
						Double firstFcPrice = Double.parseDouble(fc01.getFcNum())*Double.parseDouble(fc01.getRate())*exchangeRate;
						//先求出第二段FC航程的运价
						Double secondFcPrice = Double.parseDouble(fc02.getFcNum())*Double.parseDouble(fc02.getRate())*exchangeRate;
						//先求出第三段FC航程的运价
						Double threeFcPrice = Double.parseDouble(fc03.getFcNum())*Double.parseDouble(fc03.getRate())*exchangeRate;
						//先求出第四段FC航程的运价
						Double fourFcPrice = Double.parseDouble(fc04.getFcNum())*Double.parseDouble(fc04.getRate())*exchangeRate;
						//先求出第五段FC航程的运价
						Double fivePrice = Double.parseDouble(fc05.getFcNum())*Double.parseDouble(fc05.getRate())*exchangeRate;
						//那么第六段FC的运价
						Double sixFcPrice = allPrice - firstFcPrice - secondFcPrice - threeFcPrice - fourFcPrice - fivePrice;
						
						//求出第一段的运价
						Double route1Price = firstFcPrice ;
						//求出第二段的运价
						Double route2Price = secondFcPrice ;
						//求出第三段的运价
						Double route3Price = threeFcPrice;
						//第二个FC的总公里数
//						int mileSize = Integer.parseInt(route5.getMileAge())+Integer.parseInt(route4.getMileAge());
						//求出第一段的公里比例
//						Double route4Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
						//求出第二段的公里比例
//						Double route5Miles = (double)(Integer.parseInt(route4.getMileAge())*1d/mileSize);
						//求出第四段的运价
						Double route4Price = fourFcPrice;
						//求出第五段的运价
						Double route5Price = fivePrice;
						//求出第六段的运价
						Double route6Price = sixFcPrice;
						//将每段航程的运价赋值
						route11.setFlyPrice(route1Price);
						route22.setFlyPrice(route2Price);
						route33.setFlyPrice(route3Price);
						route44.setFlyPrice(route4Price);
						route55.setFlyPrice(route5Price);
						route66.setFlyPrice(route6Price);
						routes.add(route11);
						routes.add(route22);
						routes.add(route33);
						routes.add(route44);
						routes.add(route55);
						routes.add(route66);
						personInfoList.get(i).setRouteList(routes);
					}
				} catch (Exception e) {
					logger.error("六段标准航段，四个Fc段运价计算错误",e);
				}
				rt.setPersonTypeInfoList(personInfoList);
				
			}
		}else{
			System.out.println("六段非标准航段，抛与保盛");
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(routeList);
				rt.setPersonTypeInfoList(personInfoList);
			}
			rt.setTravelType("3");
		}
	
		return rt;
	}
}