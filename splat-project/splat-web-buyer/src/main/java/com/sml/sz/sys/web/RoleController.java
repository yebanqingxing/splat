/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.web;

import java.util.ArrayList;
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
import com.sml.sz.Collections3;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.Role;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.OfficeService;
import com.sml.sz.sys.service.RoleService;
import com.sml.sz.sys.service.SystemService;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 角色Controller
 * @author splat
 * @version 2013-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private RoleService roleService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getRole(id);
		}else{
			return new Role();
		}
	}
	
	/**
	 * 分页查询角色
	 * @param role
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:role:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(Role role, Model model) {
		Page<Role> page = roleService.findList(new Page<Role>(), role);
		model.addAttribute("page",page);
		return "modules/sys/roleList";
	}
	/**
	 * 超级管理员查找所选平台角色,非超级管理员查寻所在机构的角色
	 * @param role
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:role:view")*/
	@RequestMapping(value = "buyerList")
	public String buyerList(Role role, Model model,HttpServletRequest request, HttpServletResponse response) {
		role.setPlatType("B");//可以从页面传值
		User user = UserUtils.getUser();
		role.setOffice(user.getCompany());
		Page<Role> page = roleService.findList(new Page<Role>(request,response), role);
		model.addAttribute("page",page);
		return "modules/page/buyerRoleList";
	}
	
	@RequestMapping(value = "supplierList")
	public String supplierList(Role role, Model model) {
		//平台过滤可以从页面取值
		/*List<Role> list = systemService.findRoleList(role);
		model.addAttribute("list", list);*/
		role.setPlatType("S");
		Page<Role> page = roleService.findList(new Page<Role>(), role);
		model.addAttribute("page",page);
		return "modules/page/supplierRoleList";
	}
	/**
	 * admin平台页面
	 * @param role
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:role:view")*/
	@RequestMapping(value = "form")
	public String form(Role role, Model model) {
		if (role.getOffice()==null){
			role.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("role", role);
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/roleForm";
	}
	/**
	 * 采购商页面
	 * @param role
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:role:view")*/
	@RequestMapping(value = "buyerForm")
	public String buyerForm(Role role, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("user",user);//页面判断是否是admin
		model.addAttribute("role", role);
		model.addAttribute("menuList", systemService.findAllMenu(role.getPlatType()));
		return "modules/page/buyerRoleForm";
	}
	/**
	 * 供应商页面
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "supplierForm")
	public String supplierForm(Role role, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("user",user);//页面判断是否是admin
		model.addAttribute("role", role);
		model.addAttribute("menuList", systemService.findAllMenu(role.getPlatType()));
		return "modules/page/supplierRoleForm";
	}
	
	/**
	 * 平台角色添加验证不知道怎么回事 不通过 
	 * @param role
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(Role role, Model model, RedirectAttributes redirectAttributes) {
		/*if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		role.setOffice(UserUtils.getUser().getCompany());
		/*if (!beanValidator(model, role)){
			return form(role, model);
		}*/
		if (!"true".equals(checkName(role.getOldName(), role.getName()))){
			addMessage(model, "保存角色'" + role.getName() + "'失败, 角色名已存在");
			return form(role, model);
		}
		/*if (!"true".equals(checkEnname(role.getOldEnname(), role.getEnname()))){
			addMessage(model, "保存角色'" + role.getName() + "'失败, 英文名已存在");
			return form(role, model);
		}*/
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/role/buyerList";
	}
	
	/**
	 * 采购商角色添加
	 * @param role
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "buyerSave")
	public String buyerSave(Role role, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, role)){
			return buyerForm(role, model);
		}*/
		if (!"true".equals(checkName(role.getOldName(), role.getName()))){
			addMessage(model, "保存角色'" + role.getName() + "'失败, 角色名已存在");
			return buyerForm(role, model);
		}
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/role/buyerList";
	}
	
	/**
	 * 供应商角色添加
	 * @param role
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "supplierSave")
	public String supplierSave(Role role, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, role)){
			return supplierForm(role, model);
		}*/
		if (!"true".equals(checkName(role.getOldName(), role.getName()))){
			addMessage(model, "保存角色'" + role.getName() + "'失败, 角色名已存在");
			return supplierForm(role, model);
		}
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/role/supplierList";
	}
	
	@RequestMapping(value = "modify")
	public String modify(Role role, Model model, RedirectAttributes redirectAttributes) {
		/*if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if (!beanValidator(model, role)){
			return form(role, model);
		}
		//role.setPlatType(systemService.getRole(role.getId()).getPlatType());
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		/*if("B".equals(role.getPlatType())){
			return "redirect:" + adminPath + "/sys/role/buyerList";
		}else if("S".equals(role.getPlatType())){
			return "redirect:" + adminPath + "/sys/role/supplierList";
		}*/
		return "redirect:" + adminPath + "/sys/role/list";
	}
	
	/*@RequiresPermissions("sys:role:edit")*/
	@RequestMapping(value = "delete")
	public String delete(Role role, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin() && role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
//		if (Role.isAdmin(id)){
//			addMessage(redirectAttributes, "删除角色失败, 不允许内置角色或编号空");
////		}else if (UserUtils.getUser().getRoleIdList().contains(id)){
////			addMessage(redirectAttributes, "删除角色失败, 不能删除当前用户所在角色");
//		}else{
		systemService.deleteRole(role);
		addMessage(redirectAttributes, "删除角色成功");
//		}
		if("B".equals(role.getPlatType())){
			return "redirect:" + adminPath + "/sys/role/buyerList";
		}else if("S".equals(role.getPlatType())){
			return "redirect:" + adminPath + "/sys/role/supplierList";
		}
		return "redirect:" + adminPath + "/sys/role/list";
	}
	
	/**
	 * 角色分配页面
	 * @param role
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:role:edit")*/
	@RequestMapping(value = "assign")
	public String assign(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("userList", userList);
		return "modules/sys/roleAssign";
	}
	/**
	 * 角色分配 -- 打开角色分配对话框
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "usertorole")
	public String selectUserToRole(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		List<Office> officeList = null;
		Office office = new Office();
		office.setPlatType(role.getPlatType());
		if(UserUtils.getUser().isAdmin()){
			officeList = officeService.findAll(office);
		}else{
			officeList = officeService.findAll();
		}
		model.addAttribute("role", role);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeList);
		return "modules/sys/selectUserToRole";
	}
	
	
	/**
	 * 角色分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	/*@RequiresPermissions("sys:role:view")*/
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);			
		}
		return mapList;
	}
	
	/**
	 * 角色分配 -- 从角色中移除用户
	 * @param userId
	 * @param roleId
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:role:edit")*/
	@RequestMapping(value = "outrole")
	public String outrole(String userId, String roleId, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+roleId;
		}*/
		Role role = systemService.getRole(roleId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从角色【" + role.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			if (user.getRoleList().size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
			}else{
				Boolean flag = systemService.outUserInRole(role, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除成功！");
				}
			}		
		}
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}
	
	/**
	 * 角色分配
	 * @param role
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:role:edit")*/
	@RequestMapping(value = "assignrole")
	public String assignRole(Role role, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToRole(role, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到角色【" + role.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}

	/**
	 * 验证角色名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	/*@RequiresPermissions("user")*/
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证角色英文名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	/*@RequiresPermissions("user")*/
	@ResponseBody
	@RequestMapping(value = "checkEnname")
	public String checkEnname(String oldEnname, String enname) {
		if (enname!=null && enname.equals(oldEnname)) {
			return "true";
		} else if (enname!=null && systemService.getRoleByEnname(enname) == null) {
			return "true";
		}
		return "false";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "treeDate")
	public List<Map<String,Object>> treeDate(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll,@RequestParam(required=false) Boolean edit,HttpServletResponse response){
		List<Map<String,Object>> mapList = Lists.newArrayList();
		List<Role> list = UserUtils.getRoleList(new Role());//获取当前用户的角色列表
		for(Role r : list){
			Map<String,Object> map = Maps.newHashMap();
			map.put("id", r.getId());
			map.put("pId","0");
			map.put("pIds","0");
			map.put("name", r.getName());
			mapList.add(map);
		}
		return mapList;
	}
}
