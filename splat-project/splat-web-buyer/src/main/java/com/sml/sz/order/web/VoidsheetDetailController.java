/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.MakeOrderNum;
import com.sml.sz.StringUtils;
import com.sml.sz.UploadUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.VoidsheetAppendix;
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetHistory;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderMessageServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.order.service.VoidsheetAppendixServiceFacade;
import com.sml.sz.order.service.VoidsheetCirculationServiceFacade;
import com.sml.sz.order.service.VoidsheetDetailServiceFacade;
import com.sml.sz.order.service.VoidsheetHistoryServiceFacade;
import com.sml.sz.order.service.VoidsheetPassengerServiceFacade;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 废票单表Controller
 * 
 * @author 李千超
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/order/voidsheetDetail")
public class VoidsheetDetailController extends BaseController {

	@Autowired
	private VoidsheetDetailServiceFacade voidsheetDetailServiceFacade;

	// 废票附件
	@Autowired
	private VoidsheetAppendixServiceFacade voidsheetAppendixServiceFacade;

	// 废票流转单
	@Autowired
	private VoidsheetCirculationServiceFacade voidsheetCirculationServiceFacade;

	//收件人
		@Autowired
		RecipientServiceFacade recipientServiceFacade;
		
	// 废票历史
	@Autowired
	private VoidsheetHistoryServiceFacade voidsheetHistoryServiceFacade;

	// 废票单人信息
	@Autowired
	private VoidsheetPassengerServiceFacade voidsheetPassengerServiceFacade;

	// 原始的旅客信息
	@Autowired
	private TicketorderPassengerFacade ticketorderPassengerFacade;

	// 原始的航班信息
	@Autowired
	private TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	
	//改签单旅客
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	//原始单的service
	@Autowired
	private TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	
	//每个人一条记录单人价格表
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;

	//机票的信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	@Autowired
	private TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	
	//凭证费用
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	@ModelAttribute
	public VoidsheetDetail get(@RequestParam(required = false) String id) {
		VoidsheetDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = voidsheetDetailServiceFacade.get(id);
		}
		if (entity == null) {
			entity = new VoidsheetDetail();
		}
		return entity;
	}

	
	//用户在改签完成页面点击过来的
	@RequestMapping(value="endorseVoidDetail")
	public String endorseVoidDetail(String passIds, String orderNo, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 要废票的旅客 改签状态是1 退票状态是3 废票状态是2
		List<EndorsesheetPassenger> tPassengerList = endorsesheetPassengerServiceFacade.findEndorsePassengerList(passIds, "2");

		// 原始航班信息 
		List<TicketorderSegment> tSegmentList = ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);

		// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
		// 改程订单06
		MakeOrderNum make=new MakeOrderNum();
		String orderNum = make.makeOrderNum("01", "03");
		Map map = new HashMap();
		// 废票单的原始订单号
		map.put("orderNo", orderNo);
		// 废票单的订单号
		map.put("orderNum", orderNum);
		model.addAttribute("orderMap", map);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);
		return "sz/order/endorseVoidSheetApplication";
	}
	
	//采购商进行确认或者重新申请或者取消废票
	@RequestMapping(value="confirm")
	public String confirm(HttpServletRequest request,HttpServletResponse response,String voidsheetNo){
		User user = UserUtils.getUser();
		//历史操作
		VoidsheetHistory history=new VoidsheetHistory();
		history.setOperatorName("采购商:"+user.getName());
		//历史操作时间
		history.setOperationTime(new Date());
		//订单号
		history.setVoidsheetNo(voidsheetNo);
		String hid_voidsheetStatus=request.getParameter("hid_voidsheetStatus");
		//获取操作状态 6 是确认然后修改状态为等待供应商的废票  1 是重新申请
		String currentStatus=request.getParameter("hid_currentStatus");
		if(null != currentStatus && "6".equals(currentStatus)){
			//采购商确认废票 之后的状态是等待供应商废票
			voidsheetDetailServiceFacade.updateCurrentStatus(currentStatus, voidsheetNo);
			history.setPreviousOperation("采购商申确认废票");
		}else if(null != currentStatus && "7".equals(currentStatus)){
			//采购商督促供应商核实废票
			//TODO
			history.setPreviousOperation("采购商督促供应商核实废票");
		}else if(null != hid_voidsheetStatus && "2".equals(hid_voidsheetStatus)){
			//采购商自己取消了订单
			voidsheetDetailServiceFacade.updateVoidsheetStatus(hid_voidsheetStatus, voidsheetNo);
			//取消时原始订单可以重新申请
			List<VoidsheetPassenger> passenger=voidsheetPassengerServiceFacade.findvoidPassenger(voidsheetNo);
			for (VoidsheetPassenger voidsheetPassenger : passenger) {
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("", voidsheetPassenger.getOrderPassengerId());
			}
			history.setPreviousOperation("采购商取消废票");
		}
		
		//此时是取消废票 采购商自己取消的
		String voidsheetStatus=request.getParameter("hid_voidsheetStatus");
		if(null != voidsheetStatus && !"2".equals(voidsheetStatus) && !"".equals(voidsheetStatus)){
			//采购商自己取消将订单的状态改为取消订单
			voidsheetDetailServiceFacade.updateVoidsheetStatus(voidsheetStatus, voidsheetNo);
			VoidsheetDetail voidsheetDetail=voidsheetDetailServiceFacade.findVoidsheetDetail(voidsheetNo);
			//修改之前的状态
			List<TicketorderPassenger> passenger=ticketorderPassengerFacade.findTicketorderPassenger(voidsheetDetail.getOrderNo());
			for (TicketorderPassenger ticketorderPassenger : passenger) {
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("", ticketorderPassenger.getId());
			}
		}
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//将历史添加数据库
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/findVoidsheetDetail?voidsheetNo="+voidsheetNo;
	}
	
	@RequestMapping(value="saveEndorseVoidSheetApplication")
	public String saveEndorseVoidSheetApplication(HttpServletRequest request, HttpServletResponse response, Model model,
			VoidsheetDetail voidsheetDetail, VoidsheetPassenger voidsheetPassenger){
		// FileUtils
		voidsheetDetail.setCreateTime(new Date());
		// 下单成功状态
		voidsheetDetail.setVoidsheetStatus("1");
		// 未退款状态
		voidsheetDetail.setPayStatus("1");
		//操作状态等待审核
		voidsheetDetail.setCurrentStatus("1");
		User user = UserUtils.getUser();
		//存放采购商id
		voidsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		voidsheetDetail.setRelevantClient(user.getCompany().getName());
		voidsheetDetail.setCreateAccount(user.getName());
		//供应商的名字SUPPLIER_NAME
		
		//供应商的idSUPPLIER_PRODUCT_NO
		
		// 将废票添加到数据库
		voidsheetDetailServiceFacade.save(voidsheetDetail);

		// 附件的一些操作
		UploadUtils upload = new UploadUtils();
		// 返回的是路径的集合
		List<String> uploadFile = upload.uploadFile(request);
		for (String string : uploadFile) {
			VoidsheetAppendix appendix = new VoidsheetAppendix();
			// 获取路径添加到数据库
			appendix.setFileAddress(string);
			String saveNameTemp = string.substring(string.lastIndexOf("/") + 1);
			// 最终的文件名字
			String saveName = saveNameTemp.substring(0, saveNameTemp.lastIndexOf("."));
			appendix.setOrgnFileName(saveName);
			appendix.setVoidsheetNo(voidsheetDetail.getOriginVoidsheetNo());
			// 附件上传时间
			appendix.setUploadTime(new Date());
			// 将附件信息添加到数据库
			voidsheetAppendixServiceFacade.save(appendix);
		}
		// 旅客信息的操作
		if (null != voidsheetPassenger.getOrderPassengerId()) {
			String[] oldPassId = voidsheetPassenger.getOrderPassengerId().split(",");
			for (String string : oldPassId) {
				EndorsesheetPassenger pass = endorsesheetPassengerServiceFacade.get(string);
				VoidsheetPassenger passenger = new VoidsheetPassenger();
				passenger.setVoidsheetNo(voidsheetDetail.getVoidsheetNo());
				passenger.setPassengerName(pass.getPassengerName());
				passenger.setOrderPassengerId(string.trim());
				passenger.setTicketNo(pass.getTicketNo());
				passenger.setVoidTime(new Date());
				// 将旅客信息添加数据库
				voidsheetPassengerServiceFacade.save(passenger);
			}
		}
		//航段的操作
		List<TicketorderSegment> segment =ticketorderSegmentServiceFacade.findSegmentByOrderNo(voidsheetDetail.getOrderNo());
		for(TicketorderSegment seg:segment){
			seg.setOrderNo(voidsheetDetail.getVoidsheetNo());
			ticketorderSegmentServiceFacade.saveSegmentObj(seg);
		}
		//历史操作
		VoidsheetHistory history=new VoidsheetHistory();
		history.setOperatorName("采购商:"+user.getName());
		history.setPreviousOperation("采购商申请废票下单成功");
		//历史操作时间
		history.setOperationTime(new Date());
		//订单号
		history.setVoidsheetNo(voidsheetDetail.getVoidsheetNo());
		//将历史添加数据库
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/findVoidsheetDetail?voidsheetNo="+voidsheetDetail.getVoidsheetNo();
	}
	
	
	@RequestMapping(value = "voidsheetDetail")
	public String voidsheetDetail(String passIds, String orderNo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		
		// 要废票的旅客 改签状态是1 退票状态是3 废票状态是2
		List<TicketorderPassenger> tPassengerList = ticketorderPassengerFacade.findPassengerList(passIds, "");
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		// 原始航班信息
		List<TicketorderSegment> tSegmentList = ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);

		List<TicketorderSinglePrice> singlePriceList=ticketorderSinglePriceServiceFacade.findorderSinglePrice(orderNo);
		//收件人信息
		List<Recipient> recipientList = recipientServiceFacade.findRecipientList(orderNo);
		// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
		// 改程订单06
		MakeOrderNum make=new MakeOrderNum();
		String orderNum = make.makeOrderNum("01", "03");
		Map map = new HashMap();
		// 废票单的原始订单号
		map.put("orderNo", orderNo);
		// 废票单的订单号
		map.put("orderNum", orderNum);
		User user = UserUtils.getUser();
		//model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		//行程单信息
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(orderNo);
		
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("user", user);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("orderMap", map);
		model.addAttribute("detail", detail);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);
		model.addAttribute("invoiceList", invoiceList);
		return "sz/order/voidSheetApplication";
	}
//采购商申请废票
	@RequestMapping(value = "saveVoidsheetDetail")
	public String saveVoidsheetDetail(String segId,HttpServletRequest request, HttpServletResponse response, Model model,
			VoidsheetDetail voidsheetDetail, VoidsheetPassenger voidsheetPassenger) {
		
		User user = UserUtils.getUser();
		//存放采购商id
		voidsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		voidsheetDetail.setRelevantClient(user.getCompany().getName());
		
		//供应商的名字SUPPLIER_NAME
		
		//供应商的idSUPPLIER_PRODUCT_NO
		voidsheetDetail.setCreateTime(new Date());
		//本单创建者 当前登录人的名字
		voidsheetDetail.setCreateAccount(user.getName());
		// 下单成功状态
		voidsheetDetail.setVoidsheetStatus("1");
		// 未退款状态
		voidsheetDetail.setPayStatus("1");
		voidsheetDetail.setCurrentStatus("1");
		// 将废票添加到数据库
		voidsheetDetailServiceFacade.save(voidsheetDetail);
		// 附件的一些操作
		UploadUtils upload = new UploadUtils();
		// 返回的是路径的集合
		List<String> uploadFile = upload.uploadFile(request);
		for (String string : uploadFile) {
			VoidsheetAppendix appendix = new VoidsheetAppendix();
			// 获取路径添加到数据库
			appendix.setFileAddress(string);
			String saveNameTemp = string.substring(string.lastIndexOf("/") + 1);
			// 最终的文件名字
			String saveName = saveNameTemp.substring(0, saveNameTemp.lastIndexOf("."));
			appendix.setOrgnFileName(saveName);
			appendix.setVoidsheetNo(voidsheetDetail.getOriginVoidsheetNo());
			// 附件上传时间
			appendix.setUploadTime(new Date());
			// 将附件信息添加到数据库
			voidsheetAppendixServiceFacade.save(appendix);
		}
		// 旅客信息的操作
		if (null != voidsheetPassenger.getOrderPassengerId()) {
			String[] oldPassId = voidsheetPassenger.getOrderPassengerId().split(",");
			for (String string : oldPassId) {
				TicketorderPassenger pass = ticketorderPassengerFacade.get(string);
				VoidsheetPassenger passenger = new VoidsheetPassenger();
				passenger.setOrderPassengerId(pass.getId());
				//修改原始旅客的信息
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("2", pass.getId());
				passenger.setVoidsheetNo(voidsheetDetail.getVoidsheetNo());
				passenger.setPassengerName(pass.getPassengerName());
				passenger.setOrderPassengerId(string.trim());
				passenger.setTicketNo(pass.getTicketNo());
				passenger.setCertNo(pass.getCertNo());
				passenger.setCertType(pass.getCertType());
				passenger.setGender(pass.getGender());
				passenger.setPassengerTitle(pass.getPassengerTitle());
				passenger.setPassengerType(pass.getPassengerType());
				passenger.setVoidTime(new Date());
				// 将旅客信息添加数据库
				voidsheetPassengerServiceFacade.save(passenger);
			}
		}
		//航段的操作
		List<TicketorderSegment> segment=ticketorderSegmentServiceFacade.findSegmentByOrderNo(voidsheetDetail.getOrderNo());
		for(TicketorderSegment seg:segment){
			seg.setOrderNo(voidsheetDetail.getVoidsheetNo());
			ticketorderSegmentServiceFacade.save(seg);
		}
		//历史操作
		VoidsheetHistory history=new VoidsheetHistory();
		//历史操作时间
		history.setOperationTime(new Date());
		history.setOperatorName("采购商:"+user.getName());
		history.setPreviousOperation("采购商申请废票下单成功");
		//订单号
		history.setVoidsheetNo(voidsheetDetail.getVoidsheetNo());
		//将历史添加数据库
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/findVoidsheetDetail?voidsheetNo="+voidsheetDetail.getVoidsheetNo();
	}

	//@RequiresPermissions("order:voidsheetDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(String flag,VoidsheetDetail voidsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		System.out.println(user);
		//采购商的id
		voidsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		Page<VoidsheetDetail> page = voidsheetDetailServiceFacade.findPage(new Page<VoidsheetDetail>(request, response),voidsheetDetail);
		page.setOld(false);
		
		
		Map<String,String> voidsheetstatusCountList=voidsheetDetailServiceFacade.findVoidsheetStatusCount( voidsheetDetail);
		//获取支付状态条数
		Map<String,String> payStatusCountList=voidsheetDetailServiceFacade.findPayStatusCount(voidsheetDetail);
		//获取操作状态
		Map<String,String> currentStatusList=voidsheetDetailServiceFacade.findCurrentStatus(voidsheetDetail);
		model.addAttribute("page", page);
		model.addAttribute("statusCountList", voidsheetstatusCountList);
		model.addAttribute("payStatusCountList",payStatusCountList );
		model.addAttribute("currentStatusList", currentStatusList);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="sz/order/voidsheetDetailListPage";
		}else{
			returnPage="sz/order/orderVoidsheetDetailList";
		}
		return returnPage;
	}

	@RequiresPermissions("order:voidsheetDetail:view")
	@RequestMapping(value = "form")
	public String form(VoidsheetDetail voidsheetDetail, Model model) {
		model.addAttribute("voidsheetDetail", voidsheetDetail);
		return "sz/order/voidsheetDetailForm";
	}

	@RequiresPermissions("order:voidsheetDetail:edit")
	@RequestMapping(value = "save")
	public String save(VoidsheetDetail voidsheetDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, voidsheetDetail)) {
			return form(voidsheetDetail, model);
		}
		voidsheetDetailServiceFacade.save(voidsheetDetail);
		addMessage(redirectAttributes, "保存废票单表成功");
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/?repage";
	}



	@RequestMapping(value="cancel")
	public String cancel(String voidsheetNo,HttpServletRequest request ,HttpServletResponse response){
		//废票单状态(1, "已提交"), refust(2, "已拒单"), audit(3, "已核单"), voidsheet(4, "已废票"), delete(5, "已删除")
		voidsheetDetailServiceFacade.updateVoidsheetStatus("5", voidsheetNo);
		//添加历史记录
		VoidsheetHistory history=new VoidsheetHistory();
		history.setEndOperationTime(new Date());
		history.setPreviousOperation("取消废票");
		history.setOperatorName("采购商:"+UserUtils.getUser().getName());
		history.setVoidsheetNo(voidsheetNo);
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/list";
	}
	
	/**
	 * 查看废票的记录
	 * @return
	 */
	@RequestMapping(value="findVoidsheetDetail")
	public String findVoidsheetDetail(Model model,String voidsheetNo,HttpServletRequest request,HttpServletResponse response){
		//获取订单信息
		VoidsheetDetail detail=voidsheetDetailServiceFacade.findVoidsheetDetail(voidsheetNo);
		//获取废票的航段信息
		List<TicketorderSegment> segment=ticketorderSegmentServiceFacade.findSegmentByOrderNo(voidsheetNo);
		//获取旅客信息
		List<VoidsheetPassenger> passenger=voidsheetPassengerServiceFacade.findvoidPassenger(voidsheetNo);
		//获取操作历史
		List<VoidsheetHistory> history=voidsheetHistoryServiceFacade.findVoidsheetHistory(voidsheetNo);
		//获取原始的记机票明细
		List<TicketorderSinglePrice> singlePrice=ticketorderSinglePriceServiceFacade.findorderSinglePrice(detail.getOrderNo());
		//获取留言
		//List<TicketorderMessage> ticketorderMessage=ticketorderMessageServiceFacade.findTicketorderMessage(voidsheetNo);
		//model.addAttribute("ticketorderMessageList",ticketorderMessage );
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("recipientList", recipientList);
		
		
		model.addAttribute("detail",detail);
		model.addAttribute("tSegmentList",segment );
		model.addAttribute("tPassengerList",passenger);
		model.addAttribute("historyList", history);
		model.addAttribute("singlePriceList", singlePrice);
		return "sz/order/voidsheetDetail";
	}
}