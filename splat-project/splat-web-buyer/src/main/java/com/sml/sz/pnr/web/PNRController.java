/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pnr.web;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sml.sz.EhCacheUtils;
import com.sml.sz.airline.service.AirlineService;
import com.sml.sz.airport.service.AirportService;
import com.sml.sz.city.service.CityService;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.general.entity.GeneralRules;
import com.sml.sz.general.service.GeneralRulesFacade;
import com.sml.sz.order.entity.TicketorderPnr;
import com.sml.sz.order.service.TicketorderPnrServiceFacade;
import com.sml.sz.policy.entity.PolicyRules;
import com.sml.sz.policy.service.PolicyRulesFacade;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.pnr.ReturnMessageParse;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 国际政策匹配Controller
 * @author 张权
 * @version 2016-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/buyer/buyers")
public class PNRController extends BaseController {

	@Autowired
	private AirportService airportService;
	
//	@Autowired
//	private MileageService mileageService;
	
	@Autowired
	private AirlineService airlineFacade;
	@Autowired
	private TicketorderPnrServiceFacade ticketorderPnrServiceFacade;
	@Autowired
	private  GeneralRulesFacade generalRulesFacade;
	
	@Autowired
	private PolicyRulesFacade policyRulesFacade;
	
	private CityService cityService;
	
	/**
	 * 
	 * @param fileName:PNR文本    comput：出票方   
	 * @return
	 * @auth 张权
	 * @date 2016年3月14日
	 * 作用：导入prn文本，返回prn对象。
	 */
	@RequestMapping(value="importPNR",method=RequestMethod.POST)
	@ResponseBody
	public Rt importPNR (String fileName,String comput){
//		TicketorderPnr ticketorderPnr = new TicketorderPnr();
//		if(fileName.length() > 3900){
//			ticketorderPnr.setPnrText(fileName.substring(0,3900));
//			ticketorderPnr.setPnrTextAppend(fileName.substring(3900,fileName.length()-1));
//		}
//		ticketorderPnrServiceFacade.save(ticketorderPnr);
//		String readFile = FileUtil.readFile(fileName);
		Rt rtPrn = null;
		try {
			rtPrn = ReturnMessageParse.parsePNRXML(fileName);
		} catch (Exception e) {
			logger.error("文本解析失败!!", e);
			
		}
		
		try {
		if(!"".equals(comput) || null != comput){
			rtPrn.setComput(comput);
			}
		} catch (Exception e) {
			logger.error("文本解析为空", e);
			
		}
		
		Rt rt = null;
		try {
			rt = airportService.updateRt(rtPrn);
		} catch (Exception e) {
			logger.error("标准航段判断有误", e);
			return rt;
		}
		

		Rt updateRtByFlyCompanys = null;
		try {
			updateRtByFlyCompanys = airlineFacade.updateRtByFlyCompanys(rt);
		} catch (Exception e) {
			return rt;
		}
//		System.out.println(updateRtByFlyCompanys.getZzRoutes());
		//总则查询
		GeneralRules generalRules = new GeneralRules();
		generalRules.setAirCode(rt.getComput());
		generalRules.setGeneralStatus("0");
		List<GeneralRules> findList = generalRulesFacade.findList(generalRules);
		//判断出起点 终点后的
//		Rt rts= generalRulesFacade.findListsByGz(findList, rtPrn, comput);
		List<Route> route_list = updateRtByFlyCompanys.getRoute_list();
		Rt rtTemp = new Rt();
		rtTemp = updateRtByFlyCompanys;
		rtTemp.setRoute_list(route_list);
		rtTemp.setTravelType(rt.getTravelType());
//		Qte qte = new Qte();
//		qte.setTax("2500");
//		qte.setFare("19600");
//		rtTemp.setQte(qte);
//		updateRtByFlyCompanys.setQte(qte);
		//Map<String,Object> hashMap = new HashMap<String, Object>();
//		List <PolicyRules>	policyRuleLists= new ArrayList<PolicyRules>();
//		List<City> cityList = cityService.findList(null);
//		for (int i = 0; i < route_list.size(); i++) {
		try {
			rt = policyRulesFacade.getPolicyRulesListByQz(rtTemp,findList);
		} catch (Exception e) {
			logger.error("政策匹配有误", e);
		}
			
//			policyRuleLists = policyRulesFacade.getPolicyRulesListByQz1(route_list.get(i).getFromCity(), route_list.get(i).getTocity(),comput);
//			policyRuleLists.add(policyRulesListByQz);
//
//		}

			//rt.setPolices(policyRulesListByQz);
		//updateRtByFlyCompanys = airlineFacade.updateRtByFlyCompanys(rt);

		
		//hashMap.put("rt", updateRtByFlyCompanys);
		//hashMap.put("polic", policyRulesListByQz);
		//request.setAttribute("Rt",updateRtByFlyCompanys);
		//request.setAttribute("PolicyRules",policyRulesListByQz );
		//lists.add(0, rts);
		//lists.add(1,policyRuleLists);
		//PnrAndPolices pnrAndPol =new PnrAndPolices();
		//pnrAndPol.setRt(rt);
		//pnrAndPol.setPolices(policyRulesListByQz);
		EhCacheUtils.put("www", rt);
		return rt;

	}
	
	/**
	 * 跳转到PNR解析界面
	 */
	@RequestMapping(value="toPnr")
	public String toPnr(){
		
		return "sz/product/product_infor";
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(1304.9896860000001+1394.9507879999999);
		
	}
}