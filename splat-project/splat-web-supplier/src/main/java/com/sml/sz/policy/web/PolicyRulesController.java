/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.policy.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.DateUtils;
import com.sml.sz.PingYinUtil;
import com.sml.sz.StringUtils;
import com.sml.sz.UploadUtils;
import com.sml.sz.airline.entity.Airline;
import com.sml.sz.airline.service.AirlineService;
import com.sml.sz.airport.entity.Airport;
import com.sml.sz.airport.service.AirportService;
import com.sml.sz.city.entity.City;
import com.sml.sz.city.service.CityService;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.continent.entity.Continent;
import com.sml.sz.continent.service.ContinentService;
import com.sml.sz.country.entity.Country;
import com.sml.sz.country.service.CountryService;
import com.sml.sz.policy.entity.PolicyRules;
import com.sml.sz.policy.service.PolicyRulesFacade;
import com.sml.sz.properties.PropertiesUtil;
import com.sml.sz.pubobj.ErrInfo;
import com.sml.sz.pubobj.PubObj;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.interceptor.Token;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 国际政策的增删改查Controller
 * @author 张权
 * @version 2016-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/policyrules/policyRules")
public class PolicyRulesController extends BaseController {
	@Autowired
	private PolicyRulesFacade policyRulesFacade;
	
	/**航班接口*/
	@Autowired
	private AirportService airportService;
	
	/**城市接口*/
	@Autowired
	private CityService cityService;
	
	/**大区接口*/
	@Autowired
	private ContinentService continentService;
	
	/**国家接口*/
	@Autowired
	private CountryService countryService;
	
	/**航司接口*/
	@Autowired
	private AirlineService airlineService;
	
	
	
//	@ModelAttribute
//	public PolicyRules get(@RequestParam(required=false) String id) {
//		PolicyRules entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = policyRulesFacade.get(id);
//		}
//		if (entity == null){
//			entity = new PolicyRules();
//		}
//		return entity;
//	}
//	
//	@RequiresPermissions("policy:policyRules:view")
	@RequestMapping(value = {"list", ""})
	public String list(PolicyRules tbPolicyRules,Integer flag,HttpServletRequest request, HttpServletResponse response, Model model) {
		//tbPolicyRules.setPolicyStatus("0");
		User user = UserUtils.getUser();
		if(null != user){
			tbPolicyRules.setSupplierId(user.getCompany().getId());
		}
		Page<PolicyRules> page = policyRulesFacade.findPage(new Page<PolicyRules>(request, response), tbPolicyRules); 
		model.addAttribute("page", page);

		//判断页面跳转
		if(null != flag){
			//flag:1,跳转ajax查询页面
			if(flag == 1){
//				List<PolicyRules> policyList = policyRulesFacade.findList(tbPolicyRules);
				model.addAttribute("page", page);
				return "sz/policy/policyListPage";
			}
		}
//		List<PolicyRules> policyList = policyRulesFacade.findList(tbPolicyRules);
		//flag: null,跳转到列表页面
		model.addAttribute("page", page);
		return "sz/policy/policyRulesList";
	}
	
	
	@RequestMapping(value = {"policyRulesListHistory", ""})
	public String policyRulesListHistory(PolicyRules tbPolicyRules,Integer flag,HttpServletRequest request, HttpServletResponse response, Model model) {
		//tbPolicyRules.setPolicyStatus("0");
		User user = UserUtils.getUser();
		if(null != user){
			tbPolicyRules.setSupplierId(user.getCompany().getId());
		}
		Page<PolicyRules> page = policyRulesFacade.findPage(new Page<PolicyRules>(request, response), tbPolicyRules); 
		model.addAttribute("page", page);

		//判断页面跳转
		if(null != flag){
			//flag:1,跳转ajax查询页面
			if(flag == 1){
//				List<PolicyRules> policyList = policyRulesFacade.findList(tbPolicyRules);
				model.addAttribute("page", page);
				return "sz/policy/policyListPage";
			}
		}
//		List<PolicyRules> policyList = policyRulesFacade.findList(tbPolicyRules);
		//flag: null,跳转到列表页面
		model.addAttribute("page", page);
		return "sz/policy/policyRulesListHistory";
	}

//	@RequiresPermissions("policy:policyRules:view")
	@RequestMapping(value = "form")
	public String form(PolicyRules tbPolicyRules, Model model) {
		model.addAttribute("tbPolicyRules", tbPolicyRules);
		return "sz/policy/policyRulesForm";
	}

//	@RequiresPermissions("policy:policyRules:edit")
	@Token(remove=true)
	@RequestMapping(value = "save")
	public String save(PolicyRules tbPolicyRules, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, tbPolicyRules)){
//			return form(tbPolicyRules, model);
//		}
		User user = UserUtils.getUser();
		if(null != user){
			tbPolicyRules.setSupplierId(user.getCompany().getId());
			tbPolicyRules.setCreatUser(user.getId());
		}
		if(null != tbPolicyRules){
			//String jsonString = JSONObject.toJSONString(policyRules);
			//jsonString.
			policyRulesFacade.save(tbPolicyRules);
		}
		addMessage(redirectAttributes, "保存国际政策成功");
		return "redirect:"+Global.getAdminPath()+"/policyrules/policyRules/list";
	}
	
//	@RequiresPermissions("policy:policyRules:edit")
	@RequestMapping(value = "delete")
	public String delete(PolicyRules tbPolicyRules, RedirectAttributes redirectAttributes) {
		policyRulesFacade.delete(tbPolicyRules);
		addMessage(redirectAttributes, "删除国际政策成功");
		return "redirect:"+Global.getAdminPath()+"/policyrules/policyRules/list";
	}
	
	/**
	 * 政策明细修改
	 * @param policyRules
	 * @param redirectAttributes
	 * @return
	 */
	@Token(remove=true)
	@RequestMapping(value = "update")
	public String update(PolicyRules policyRules,String isPolicyStatus, RedirectAttributes redirectAttributes){
		User user = UserUtils.getUser();
		if(null != user){
			policyRules.setSupplierId(user.getCompany().getId());
			policyRules.setUpdateUser(user.getId());
		}
		if(null != policyRules){
			//String jsonString = JSONObject.toJSONString(policyRules);
			//jsonString.
			policyRulesFacade.save(policyRules);
		}
		addMessage(redirectAttributes, "修改国际政策成功");
		return "redirect:"+Global.getAdminPath()+"/policyrules/policyRules/list";
	}
	
	/**
	 *  跳转政策明细修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "toUpdate")
	public String toUpdate(String id,Model model){
		PolicyRules policyRules = policyRulesFacade.get(id);
		model.addAttribute("tbPolicyRules",policyRules);
		return "sz/policy/editPolicyRules";
	}
	
	/**
	 *  跳转政策明细详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "toDetails")
	public String toDetails(String id,Model model){
		PolicyRules policyRules = policyRulesFacade.get(id);
		model.addAttribute("tbPolicyRules",policyRules);
		return "sz/policy/detailsPolicy";
	}
	/**
	 *  跳转政策明细详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toCopy")
	public String toCopy(String id,Model model){
		PolicyRules policyRules = policyRulesFacade.get(id);
		model.addAttribute("tbPolicyRules",policyRules);
		return "sz/policy/copyPolicy";
	}
	/**
	 * ajax加载国家、城市、航班、航线等信息（aviation：航空）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="aviation")
	@ResponseBody
	public ArrayList<PubObj> aviation(Model model){
		/**获取城市集合信息*/
		List<City> cityList = cityService.findList(null);
		/**获取航班集合信息*/
		List<Airport> airPortList = airportService.findList(null);
		/**获取大区集合信息*/
		List<Continent> continentList = continentService.findList(null);
		/**获取国家集合信息*/
		List<Country> countryList = countryService.findList(null);
		/**获取国家集合信息*/
		List<Airline> airlineList = airlineService.findList(null);
		/**讲所有集合信息放入countryArr中*/
		List<PubObj> countryArr= new ArrayList();
		//添加城市集合
		int j = cityList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(cityList.get(i).getCityCode());
			pubObj.setAirName(cityList.get(i).getCityNameEn());
			pubObj.setChinaCode(cityList.get(i).getCityNameCn());
			pubObj.setTwoName(cityList.get(i).getIataAreaCd());
			countryArr.add(pubObj);
		}
		//添加航班集合
		j = airPortList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(airPortList.get(i).getAirportCode());
			pubObj.setAirName(airPortList.get(i).getAirportNameEn());
			pubObj.setChinaCode(airPortList.get(i).getAirportNameCn());
			pubObj.setTwoName(airPortList.get(i).getAirportCode());
			countryArr.add(pubObj);
		}
		//添加大区集合
		j = continentList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(continentList.get(i).getContinentNameCn());
			pubObj.setAirName(continentList.get(i).getContinentNameEn());
			pubObj.setChinaCode(continentList.get(i).getContinentNameCnAbbr());
			pubObj.setTwoName(continentList.get(i).getContinentNameEn());
			countryArr.add(pubObj);
		}
		//添加国家集合
		j = countryList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(countryList.get(i).getCountryNameCn());
			pubObj.setAirName(countryList.get(i).getCountryNamePinyin());
			pubObj.setChinaCode(countryList.get(i).getCountryNameCn());
			pubObj.setTwoName(countryList.get(i).getCountryCodeAbbr());
			countryArr.add(pubObj);
		}
		//添加航线集合
		j = airlineList.size();
		for (int i=0;i<j;i++) {
			PubObj pubObj = new PubObj();
			pubObj.setAirCode(airlineList.get(i).getAirlineNameCn());
			pubObj.setAirName(airlineList.get(i).getAirlineNameEn());
			pubObj.setChinaCode(airlineList.get(i).getAirlineNameCn());
			pubObj.setTwoName(airlineList.get(i).getAirlineCode());
			countryArr.add(pubObj);
		}
		
		StringBuffer bf=new StringBuffer();
		bf.append("var citys=new Array();\n");
		int index=0;
		for (City itme : cityList) {
			bf.append("citys[").append(index).append("]=new Array('")
			.append(itme.getCityCode()).append("','")
			.append(itme.getCityNameCn().replace("'", "")).append("','")
			.append(PingYinUtil.converterToSpell(itme.getCityNameCn().replace("'", "")).toUpperCase()).append("','")
			.append(PingYinUtil.converterToFirstSpell(itme.getCityNameCn().replace("'", "")).toUpperCase()).append("');")
			.append("\n");
			index++;
		}
		for (Airport itme : airPortList) {
			if(StringUtils.isBlank(itme.getAirportNameCn()) || itme.getAirportNameCn().indexOf("火车") > 0){
				continue;
			}
			bf.append("citys[").append(index).append("]=new Array('")
			.append(itme.getAirportCode()).append("','")
			.append(itme.getAirportNameCn().replace("'", "")).append("','")
			.append(PingYinUtil.converterToSpell(itme.getAirportNameCn().replace("'", "")).toUpperCase()).append("','")
			.append(PingYinUtil.converterToFirstSpell(itme.getAirportNameCn().replace("'", "")).toUpperCase()).append("');")
			.append("\n");
			index++;
		}
		//大区
		for (Continent itme : continentList) {
			if(StringUtils.isNotBlank(itme.getContinentCode())){
				bf.append("citys[").append(index).append("]=new Array('")
				.append(itme.getContinentCode()).append("','")
				.append(itme.getContinentNameCn().replace("'", "")).append("','")
				.append(itme.getContinentNamePinyin().replace("'", "")).append("','")
				.append(itme.getContinentCode().replace("'", "")).append("');")
				.append("\n");
				index++;
			}
			
		}
		//国家
		for (Country itme : countryList) {
			bf.append("citys[").append(index).append("]=new Array('")
			.append(itme.getCountryCodeAbbr()).append("','")
			.append(itme.getCountryNameCn().replace("'", "")).append("','")
			.append(PingYinUtil.converterToSpell(itme.getCountryNameCn().replace("'", "")).toUpperCase()).append("','")
			.append(PingYinUtil.converterToFirstSpell(itme.getCountryNameCn().replace("'", "")).toUpperCase()).append("');")
			.append("\n");
			index++;
		}
		index=0;
		bf.append("\n").append("var airlines=new Array();");
		//航线
		for (Airline itme : airlineList) {
			bf.append("airlines[").append(index).append("]=new Array('")
			.append(itme.getAirlineCode()).append("','")
			.append(itme.getAirlineNameCn().replace("'", "")).append("','")
			.append(itme.getAirlineNameEn().replace("'", "").replace(" ", "")
					.replace("Co.", "").replace(",", "").replace("Ltd.", "").toUpperCase()).append("','")
			.append(PingYinUtil.converterToFirstSpell(itme.getAirlineNameCn().replace("'", "")).toUpperCase()).append("');")
			.append("\n");
			index++;
		}
		System.out.println(bf.toString());
		return (ArrayList<PubObj>) countryArr;
	}
	
	/**
	 * ajax加载国家、城市、航班、航线等信息（aviation：航空）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="checkAirline")
	@ResponseBody
	public Boolean checkAirline(String codeBrr,Model model){
		/**获取航班集合信息*/
		List<PubObj> airlineList = aviation(model);
		Boolean isTrue = policyRulesFacade.checkAirline(codeBrr,airlineList);
		
		return isTrue;
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@Token(save=true)
	@RequestMapping(value = "toSave")
	public String toSave(){
		
		return "sz/policy/addPolicy";
	}
	
	/**
	 * 导入政策Excle
	 * @param headPic
	 * @return
	 */
	@RequestMapping("addImportExcle")
	@ResponseBody
	public List<ErrInfo> addImportExcle(@RequestParam(value="excle",required=true) MultipartFile headPic,Model model){
		String realPath = PropertiesUtil.getPropertiesValue("splat.properties").getProperty("userfiles.basedir");
		User user = UserUtils.getUser();
		realPath=realPath+"/"+DateUtils.getDate("yyyyMM")+"/"+user.getLoginName();
		
		System.out.println(realPath);
		
//		List<Airline> airlineList = airlineService.findList(null);
		/*String fileNam = UploadUtils.upfile(headPic, realPath);
		ParseFile parseFile=new ParseFile();
		parseFile.setName("政策上传");
		parseFile.setPath(realPath+"/"+fileNam);*/
		
		//parseFileService.save(parseFile);
		
		//System.out.println(parseFile.getId());
		List<ErrInfo> errInfoList = new ArrayList<ErrInfo>();
		//List<ErrInfo> errInfoList =  policyRulesFacade.addPolicyRules(fileNam,realPath);
//		model.addAttribute("errInfoList",errInfoList);
		return errInfoList;
//		return "sz/policy/importExcle";
	}
	
	/**
	 * 跳转导入Excle
	 * @return
	 */
	@RequestMapping("toImportExcle")
	public String toImportExcle(){
		return "sz/policy/importExcle";
	}
	
	
	public static void main(String[] args) {
//		PolicyRulesFacade policyRulesFacade ;
//		
//		PolicyRules policyRules = new PolicyRules();
//		policyRules = policyRulesFacade.get(policyRules.setId("260786"));
//		System.out.println(policyRules);
	}
}