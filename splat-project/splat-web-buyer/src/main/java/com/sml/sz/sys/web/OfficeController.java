/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.OfficeService;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 机构Controller
 * @author splat
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	/*@Autowired
	private SystemService systemService;*/
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
		String platType = UserUtils.getUser().getPlatType();
		model.addAttribute("platType",platType);
//        model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	/*@RequiresPermissions("sys:office:view")*/
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model,HttpServletRequest request, HttpServletResponse response) {
		Page<Office> page = officeService.findList(new Page<Office>(request, response),office);
		model.addAttribute("page", page);
		return "modules/sys/officeList";
	}
	/**
	 * 采购商查询
	 * @param office
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"buyerList"})
	public String buyerList(Office office, Model model,HttpServletRequest request, HttpServletResponse response) {
		if(office.getType() == null || "".equals(office.getType())){
			office.setType("1");
		}//默认查询机构
		Page<Office> page = officeService.findList(new Page<Office>(request, response),office);
		model.addAttribute("page", page);
		return "modules/page/buyerOfficeList";
	}  
	/**
	 * 供应商查询
	 * @param office
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"supplierList"})
	public String supplierList(Office office, Model model,HttpServletRequest request, HttpServletResponse response) {
		//Page<Office> page = officeService.findList(new Page<Office>(request, response),office);
		if(office.getType() == null){
			office.setType("1");
		}//默认查询机构
		Page<Office> page = officeService.findList(new Page<Office>(request, response),office);
		model.addAttribute("page", page);
		return "modules/page/supplierOfficeList";
	}
	/*@RequiresPermissions("sys:office:view")*/
	@RequestMapping(value = "form")
	public String form(Office office,String platType, Model model) {
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	/**
	 * 采购商添加页面
	 * @param office
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "buyerForm")
	public String buyerForm(Office office,Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		if("0".equals(office.getParent().getId())){
			office.getParent().setName("顶级单位");
		}
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		model.addAttribute("office", office);
		return "modules/page/buyerOfficeForm";
	}
	/**
	 * 供应商添加页面
	 * @param office
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "supplierForm")
	public String supplierForm(Office office,Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		if("0".equals(office.getParent().getId())){
			office.getParent().setName("顶级单位");
		}
		/*office.setParent(officeService.get(office.getParent().getId()));*/
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		model.addAttribute("office", office);
		return "modules/page/supplierOfficeForm";
	}
	/*@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		User user = UserUtils.getUser();
		if(user.getPlatType() == "A"){//添加顶级目录
			if(office.getParent() == null || office.getParent().getId() == null){
				office.setParent(officeService.get("1").getParent());
			}
		}
		//数据验证
		if (!beanValidator(model, office)){
			return form(office,null, model);
		}
		officeService.save(office);
		if(office.getChildDeptList()!=null){
			Office childOffice = null;
			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}
		
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id="+id+"&parentIds="+office.getParentIds();
	}*/
	
	/**
	 * 公司详情页
	 * @param office
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "officeDetial")
	public String officeDetial(Office office,Model model){
		office = officeService.get(UserUtils.getUser().getCompany());
		Office depart = new Office();
		depart.setParent(office);
		List<Office> departments = officeService.findAll(depart);
		model.addAttribute("office",office);
		model.addAttribute("departments",departments);
		return "/modules/page/buyerOfficeDetial";
	}
	/**
	 * 部门添加
	 * @param office
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String saveBuyer(Office office, Model model, RedirectAttributes redirectAttributes) {
		//数据验证
		if(office.getParent() == null || office.getParent().getId() == null || "".equals(office.getParent().getId())){
			office.setParent(UserUtils.getUser().getCompany());
		}
		if (!beanValidator(model, office)){
			return buyerDepartmentForm(office,model,redirectAttributes);
		}
		officeService.save(office);
		addMessage(redirectAttributes, "保存部门" + office.getName() + "'成功");
		return officeDetial(office.getParent(),model);
	}
	@RequestMapping(value = "buyerDepartmentForm")
	public String buyerDepartmentForm(Office office, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("office",office);
		return "/modules/page/buyerDepartmentForm";
	}
	
	@RequestMapping(value = "supplierDepartmentForm")
	public String supplierDepartmentForm(Office office, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("office",office);
		return "/modules/page/supplierDepartmentForm";
	}
	
/*	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list";
		}
//		if (Office.isRoot(id)){
//			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
//		}else{
			officeService.delete(office);
			addMessage(redirectAttributes, "删除机构成功");
//		}
		return "redirect:" + adminPath + "/sys/office/list";
	}*/
	/**
	 * 删除部门
	 * @param office
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:office:edit")*/
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		officeService.delete(office);
		addMessage(redirectAttributes, "删除部门成功");
		return "redirect:" + adminPath + "/sys/office/officeDetial";
	}
	
	@ResponseBody
	@RequestMapping(value = "stopOrStart")
	public String stopOrStart(@RequestParam(required=true) String id){
		String message = null;
		Office office = officeService.get(id);
		if(office == null){
			message = "数据错误";
			return message;
		}
		if("1".equals(office.getUseable())){
			office.setUseable("0");
			message = "0";
		}else{
			office.setUseable("1");
			message = "1";
		}
		officeService.save(office);
		return message;
	} 
	
	/**
	 * 
	 * @param extId 排除的ID
	 * @param type  类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade	显示级别
	 * @param isAll 是否是平台超级管理员
	 * @param platType 平台标识
	 * @param edit	获取页面是否是编辑页面
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll,@RequestParam(required=false) String platType,
			@RequestParam(required=false) Boolean edit,HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Map<String,Object> platMap = null;
		List<Office> list = officeService.findList(isAll);
		//User user = UserUtils.getUser();
				//机构添加页面，添加顶级目录
				if(edit != null && edit){
					platMap = Maps.newHashMap();
					platMap.put("id", null);
					platMap.put("pId",0);
					platMap.put("pIds", null);
					platMap.put("name","顶级目录");
				}
				if(platMap != null && platMap.size() > 0){
					mapList.add(platMap);
				}
				for (int i=0; i<list.size(); i++){
					Office e = list.get(i);
					if (
							(StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
							&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
							&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
							&& Global.YES.equals(e.getUseable())
							&& (platType == null || e.getPlatType().equals(platType))//超级管理员机构添加过滤平台
						){
							Map<String, Object> map = Maps.newHashMap();
							map.put("id", e.getId());
							map.put("pId", e.getParentId());
							map.put("pIds", e.getParentIds());
							map.put("name", e.getName());
							if (type != null && "3".equals(type)){
								map.put("isParent", true);
							}
							mapList.add(map);
					}
				}
		return mapList;
	}
}
