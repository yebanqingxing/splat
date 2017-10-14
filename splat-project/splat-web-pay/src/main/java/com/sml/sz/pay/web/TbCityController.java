/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.pay.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.config.Global;
import com.sml.sz.corpinfo.entity.TbCorpInfo;
import com.sml.sz.corpinfo.service.TbCorpInfoServiceIn;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.StringUtils;
import com.sml.sz.pay.entity.TbCity;
import com.sml.sz.pay.service.TbCityServiceFacade;

/**
 * 测试-功能描述Controller
 * @author 测试-生成功能作者
 * @version 2016-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/tbCity")
public class TbCityController extends BaseController {

	@Autowired
	private TbCityServiceFacade tbCityService;
	
	@Autowired
	private TbCorpInfoServiceIn tb;
	
	@ModelAttribute
	public TbCity get(@RequestParam(required=false) String id) {
		TbCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbCityService.get(id);
		}
		if (entity == null){
			entity = new TbCity();
		}
		return entity;
	}
	
	//@RequiresPermissions("pay:tbCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbCity tbCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbCity> page = tbCityService.findPage(new Page<TbCity>(request, response), tbCity); 
		model.addAttribute("page", page);
		return "sz/pay/tbCityList";
	}

	//@RequiresPermissions("pay:tbCity:view")
	@RequestMapping(value = "form")
	public String form(TbCity tbCity, Model model) {
		List<TbCorpInfo> findList = tb.findList(null);
		model.addAttribute("tbCity", tbCity);
		return "sz/pay/tbCityForm";
	}

	//@RequiresPermissions("pay:tbCity:edit")
	@RequestMapping(value = "save")
	public String save(TbCity tbCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbCity)){
			return form(tbCity, model);
		}
		tbCityService.save(tbCity);
		addMessage(redirectAttributes, "保存测试-功能名成功");
		return "redirect:"+Global.getAdminPath()+"/pay/tbCity/?repage";
	}
	
	@RequiresPermissions("pay:tbCity:edit")
	@RequestMapping(value = "delete")
	public String delete(TbCity tbCity, RedirectAttributes redirectAttributes) {
		tbCityService.delete(tbCity);
		addMessage(redirectAttributes, "删除测试-功能名成功");
		return "redirect:"+Global.getAdminPath()+"/pay/tbCity/?repage";
	}

	@RequestMapping(value = "test")
	public String form() {
		System.err.println("123");
		return "sz/zfb/index";
	}
	@RequestMapping(value = "zf")
	public String zf() {
		
		System.err.println("123");
		return "sz/zfb/alipayapi";
	}
	

	
}