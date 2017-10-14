package com.sml.sz.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sml.sz.DateUtils;
import com.sml.sz.MD5EncryptUtil;
import com.sml.sz.StringUtils;
import com.sml.sz.common.beanvalidator.BeanValidators;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.Role;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.SystemService;
import com.sml.sz.sys.utils.UserUtils;
import com.sml.sz.sys.utils.excel.ExportExcel;
import com.sml.sz.sys.utils.excel.ImportExcel;

/**
 * 用户Controller
 * @author splat
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	/*@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String listView(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		User loginUser = UserUtils.getUser();
		if(!loginUser.isAdmin()){
			user.setPlatType(loginUser.getPlatType());
			user.setCompany(loginUser.getCompany());
		}
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/operList";
	}*/
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = {"list", ""})
	public String listView(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		User loginUser = UserUtils.getUser();
		if(!loginUser.isAdmin()){
			user.setPlatType(loginUser.getPlatType());
			user.setCompany(loginUser.getCompany());
		}
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/userList";
	}
	/**
	 * 采购商列表
	 * @param user  查询条件
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = {"buyerList", ""})
	public String buyerList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		User loginUser = UserUtils.getUser();
		if(!loginUser.isAdmin()){//非admin用户设置查询权限为当前采购商能管理的用户（包含本机构和下级机构）
			user.setCompany(loginUser.getCompany());
		}
		user.setPlatType("B");//平台过滤，也可以从页面传值
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/page/buyerUserList";
	}
	
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = {"supplierList", ""})
	public String supplierList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		User loginUser = UserUtils.getUser();
		if(!loginUser.isAdmin()){
			user.setCompany(loginUser.getCompany());
		}
		user.setPlatType("S");
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/page/supplierUserList";
	}
	/**
	 * 操作员增加页面
	 * @param user
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("isAdmin",UserUtils.getUser().isAdmin());
		//model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/operadd";
	}*/
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("isAdmin",UserUtils.getUser().isAdmin());
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}
	/**
	 * 操作员修改页面
	 * @param user
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = "modify")
	public String modify(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("isAdmin",UserUtils.getUser().isAdmin());
		//model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/opermodify";
	}
	
	@RequestMapping(value = "buyerForm")
	public String buyerForm(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		model.addAttribute("isAdmin",UserUtils.getUser().isAdmin());
		return "modules/page/buyerUserForm";
	}
	
	@RequestMapping(value = "supplierForm")
	public String supplierForm(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		model.addAttribute("isAdmin",UserUtils.getUser().isAdmin());
		return "modules/page/supplierUserForm";
	}
	
	/*@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "detail")
	public String detial(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		//model.addAttribute("allRoles", systemService.findAllRole());
		if("B".equals(user.getPlatType())){
			return "modules/page/buyerUserDetial";
		}else if("S".equals(user.getPlatType())){
			return "modules/page/supplierUserDetial";
		}
		return "modules/sys/operdetails";
	}*/
	/**
	 * 用的是现在的页面
	 * @param user
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")*/
	@RequestMapping(value = "detial")//detial?detail??
	public String detial(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		//model.addAttribute("allRoles", systemService.findAllRole());
		if("B".equals(user.getPlatType())){
			return "modules/page/buyerUserDetial";
		}else if("S".equals(user.getPlatType())){
			return "modules/page/supplierUserDetial";
		}
		return "modules/sys/operdetails";
	}
	
	@RequestMapping(value = "passwordReset")
	@ResponseBody
	public String passwordReset(User user){
		user = systemService.getUser(user.getId());
		user.setPassword(SystemService.entryptPassword("888888"));
		systemService.saveUser(user);
		return "ok";
	}
	
	
	/*@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		if(UserUtils.getUser().isAdmin()){
			user.setCompany(new Office(request.getParameter("company.id")));
		}else{
			user.setCompany(UserUtils.getUser().getCompany());
		}
		//user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
			//user.setPassword(MD5EncryptUtil.encryptMD5Code(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		if("B".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/page/buyerUserList?repage";
		}else if("S".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/page/supplierUserList?repage";
		}else{
			return "redirect:" + adminPath + "/sys/sys/operList?repage";
		}
		
	}*/
	
	/*@RequiresPermissions("sys:user:edit")*/
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		/*user.setCompany(new Office(request.getParameter("company.id")));*/
		//user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		user.setCompany(UserUtils.getUser().getCompany());
		user.setOffice(UserUtils.getUser().getCompany());
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
			//user.setPassword(MD5EncryptUtil.encryptMD5Code(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
//		for (Role r : systemService.findAllRole()){
		for (Role r : UserUtils.getRoleList(new Role())){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		if("B".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/user/buyerList";
		}else if("S".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/user/supplierList";
		}else{
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
	}
	
	/**
	 * 用户状态修改
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("sys:user:edit")*/
	@RequestMapping(value = "stop")
	public String stop(String id, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = systemService.getUser(id);
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		if(roleIdList.size() <= 0){
			model.addAttribute("message", "该用户没有可用的角色,请联系管理员");
			return "redirect:" + adminPath + "/sys/user/list";
		}
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		if("1".equals(user.getLoginFlag())){
			user.setLoginFlag("0");
		}else{
			user.setLoginFlag("1");
		}
		systemService.saveUser(user);
		return "redirect:" + adminPath + "/sys/user/list";
	}
	
	/*@RequiresPermissions("sys:user:edit")*/
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}*/
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		if("B".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/user/buyerList";
		}else if("S".equals(user.getPlatType())){
			return "redirect:" + adminPath + "/sys/user/supplierList"; 
		}
		return "redirect:" + adminPath + "/sys/user/list";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")*/
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
    		new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:user:edit")*/
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					if ("true".equals(checkLoginName("", user.getLoginName()))){
						user.setPassword(SystemService.entryptPassword("123456"));
						//user.setPassword(MD5EncryptUtil.encryptMD5Code("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequiresPermissions("sys:user:view")*/
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	/*@RequiresPermissions("sys:user:edit")*/
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 *//*
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}*/

	/**
	 * 返回用户信息
	 * @return
	 */
	/*@RequiresPermissions("user")*/
	/*@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}*/
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("user")*/
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
	
	/*@RequiresPermissions("user")*/
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
    
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
