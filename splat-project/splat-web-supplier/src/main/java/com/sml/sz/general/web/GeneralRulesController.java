/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.general.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.StringUtils;
import com.sml.sz.airline.entity.Airline;
import com.sml.sz.airline.service.AirlineService;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.airport.service.AirportService;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.file.FileUtil;
import com.sml.sz.general.entity.GeneralRules;
import com.sml.sz.general.service.GeneralRulesFacade;
import com.sml.sz.pubobj.PubObj;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.interceptor.Token;
import com.sml.sz.sys.pnr.Passenger;
import com.sml.sz.sys.pnr.ReturnMessageParse;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 国际政策总则的增删改查Controller
 * @author 张权
 * @version 2016-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/generalrules/generalRules")
public class GeneralRulesController extends BaseController {

	@Autowired
	private GeneralRulesFacade generalRulesFacade;
	
	@Autowired
	private AirportService airportFacade;
	
	/**航司接口*/
	@Autowired
	private AirlineService airlineService;

	
	@ModelAttribute
	public GeneralRules get(@RequestParam(required=false) String id) {
		GeneralRules entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = generalRulesFacade.get(id);
		}
		if (entity == null){
			entity = new GeneralRules();
		}
		return entity;
	}
	
	/**
	 * 查询总则
	 * @param tbGeneralRules
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("generalrules:generalRules:view")
	@RequestMapping(value = {"list", ""})
	public String list(GeneralRules generalRules,Integer flag, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null != user){
			generalRules.setSupplierId(user.getCompany().getId());
		}
		Page<GeneralRules> page = generalRulesFacade.findPage(new Page<GeneralRules>(request, response), generalRules); 
		if(null != flag){
			if(flag == 1){
				model.addAttribute("page", page);
				return "sz/generalrules/generalRulesListPage";
			}
		}
		model.addAttribute("page", page);
		return "sz/generalrules/generalRulesList";
	}
	
	/**
	 * 查询历史总则
	 * @param generalRules
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"historyList", ""})
	public String historyList(GeneralRules generalRules,Integer flag, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null != user){
			generalRules.setSupplierId(user.getCompany().getId());
		}
		Page<GeneralRules> page = generalRulesFacade.findPage(new Page<GeneralRules>(request, response), generalRules); 
		if(null != flag){
			if(flag == 1){
				model.addAttribute("page", page);
				return "sz/generalrules/generalRulesListPage";
			}
		}
		model.addAttribute("page", page);
		return "sz/generalrules/generalRulesHistoryList";
	}

//	@RequiresPermissions("generalrules:generalRules:view")
//	@RequestMapping(value = "form")
//	public String form(GeneralRules tbGeneralRules, Model model) {
//		if(null != tbGeneralRules.getGrId() ||("").equals(tbGeneralRules.getGrId()) ){
//			generalRulesFacade.get(tbGeneralRules.getGrId());
//		}
//		model.addAttribute("generalRules", tbGeneralRules);
//		return "sz/generalrules/generalRulesForm";
//	}

	/**
	 * 添加政策
	 * @param tbGeneralRules
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
//	@RequiresPermissions("generalrules:generalRules:edit")
	@Token(remove=true)
	@RequestMapping(value = "save")
	public String save(GeneralRules tbGeneralRules, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, tbGeneralRules)){
//			return form(tbGeneralRules, model);
//		}
		if(null != tbGeneralRules){
			User user = UserUtils.getUser();
			if(null != user){
				tbGeneralRules.setSupplierId(user.getCompany().getId());
				tbGeneralRules.setCreateUserId(user.getId());
			}
			generalRulesFacade.save(tbGeneralRules);
			addMessage(redirectAttributes, "保存国际政策总则成功");
		}else{
			addMessage(redirectAttributes, "国际政策总则保存失败");
		}
		return "redirect:"+Global.getAdminPath()+"/generalrules/generalRules/list";
	}
	
	@Token(save=true)
	@RequestMapping(value = "toSave")
	public String toSave(){
		
		return "sz/generalrules/addGeneralRules";
	}
	
	/***
	 * 删除政策
	 * @param tbGeneralRules
	 * @param redirectAttributes
	 * @return
	 */
//	@RequiresPermissions("generalrules:generalRules:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneralRules tbGeneralRules, RedirectAttributes redirectAttributes) {
		generalRulesFacade.delete(tbGeneralRules);
		addMessage(redirectAttributes, "删除国际政策总则成功");
		return "redirect:"+Global.getAdminPath()+"/generalrules/generalRules/list";
	}
	
	/**
	 * 跳转国际政策修改页面
	 * @param grId
	 * @param model
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "toEdit")
	public String toEdit(String grId, Model model){
		if(null != grId&&"" != grId){
			GeneralRules generalRules = generalRulesFacade.get(grId);
			if(null != generalRules){
				model.addAttribute("generalRules",generalRules);
			}
		}
		return "sz/generalrules/editGeneralRules";
	}
	
	/**
	 * 跳转详情页面
	 * @param grId
	 * @param model
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "toDetails")
	public String toDetails(String grId, Model model){
		if(null != grId&&"" != grId){
			GeneralRules generalRules = generalRulesFacade.get(grId);
			if(null != generalRules){
				model.addAttribute("generalRules",generalRules);
			}
		}
		return "sz/generalrules/generalDetails";
	}

	
	/**
	 * 修改详情
	 * @param grId
	 * @param model
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "generalDetails")
	public String generalDetails(String grId, Model model){
		if(null != grId&&"" != grId){
			GeneralRules generalRules = generalRulesFacade.get(grId);
			if(null != generalRules){
				model.addAttribute("generalRules",generalRules);
			}
		}
		return "sz/generalrules/editGeneralRules";
	}
	
	
	/**
	 * ajax加载国家、城市、航班、航线等信息（aviation：航空）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="aviation")
	@ResponseBody
	public ArrayList<PubObj> aviation(Model model){
		/**获取国家集合信息*/
		List<Airline> airlineList = airlineService.findList(null);
		/**讲所有集合信息放入countryArr中*/
		List<PubObj> countryArr= new ArrayList<PubObj>();
		
		//添加航司集合
		int j = airlineList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(airlineList.get(i).getAirlineCode());
			pubObj.setAirName(airlineList.get(i).getAirlineNameEn());
			pubObj.setChinaCode(airlineList.get(i).getAirlineNameCn());
			pubObj.setTwoName(airlineList.get(i).getAirlineCode());
			countryArr.add(pubObj);
		}
		return (ArrayList<PubObj>) countryArr;
	}
	
	/**
	 * 修改总则
	 * @param tbGeneralRules
	 * @param redirectAttributes
	 * @return
	 */
	@Token(remove=true)
	@RequestMapping(value = "edit")
	public String edit(GeneralRules tbGeneralRules, RedirectAttributes redirectAttributes){
		if(null != tbGeneralRules){
			generalRulesFacade.save(tbGeneralRules);
			addMessage(redirectAttributes, "修改国际政策总则成功");
		}else{
			addMessage(redirectAttributes, "国际政策总则修改失败");
		}
		return "redirect:"+Global.getAdminPath()+"/generalrules/generalRules/list";
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月14日
	 * 作用：导入prn文本，返回prn对象。
	 */
	@RequestMapping(value="importPNR")
	@ResponseBody
	public Rt importPNR (String fileName,RedirectAttributes  request, Model model){
		String readFile = FileUtil.readFile(fileName);
		Rt rt = ReturnMessageParse.parsePNRXML(readFile);
//		List<Passenger> passengerList = rt.getPassenger_list();
//		List<Route> routeList = rt.getRoute_list();
		String fromCity = rt.getRoute_list().get(0).getFromCity();
		Airport airportByAirportCode = airportFacade.getAirportByAirportCode(fromCity);
		System.out.println(airportByAirportCode.getAirportNameCn());
		//model.addAttribute("passengerList", passengerList);
		//model.addAttribute("routeList", routeList);
		//model.addAttribute("qqqqqqqqqqqq", routeList);
		/*ModelAndView ModelAndView=new ModelAndView();
		ModelAndView.addObject("routeList", routeList);
		ModelAndView.setViewName("redirect:/pagepc/product/product_infor.jsp");*/
		model.addAttribute("rt", rt);
		return rt;
		//return "redirect:/pagepc/product/product_infor.jsp";

	}
}