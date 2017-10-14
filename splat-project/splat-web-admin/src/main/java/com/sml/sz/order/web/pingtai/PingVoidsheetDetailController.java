/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web.pingtai;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.DateUtils;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetHistory;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
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
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.OfficeService;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 废票单表Controller
 * 
 * @author 李千超
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/order/ping/voidsheetDetail")
public class PingVoidsheetDetailController extends BaseController {

	@Autowired
	private VoidsheetDetailServiceFacade voidsheetDetailServiceFacade;

	// 废票附件
	@Autowired
	private VoidsheetAppendixServiceFacade voidsheetAppendixServiceFacade;

	// 废票流转单
	@Autowired
	private VoidsheetCirculationServiceFacade voidsheetCirculationServiceFacade;

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

	//改签旅客信息
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	//单人价格
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	//行程单
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	//收件人
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	//机票信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;

	//供应商信息
	@Autowired
	private OfficeService officeService;
	
	//历史留言
	@Autowired
	private TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	//@RequiresPermissions("order:voidsheetDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(String flag,VoidsheetDetail voidsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
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
			returnPage="sz/order/pingtai/voidsheetDetailListPage";
		}else{
			returnPage="sz/order/pingtai/orderVoidsheetDetailList";
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

	@RequiresPermissions("order:voidsheetDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(VoidsheetDetail voidsheetDetail, RedirectAttributes redirectAttributes) {
		voidsheetDetailServiceFacade.delete(voidsheetDetail);
		addMessage(redirectAttributes, "删除废票单表成功");
		return "redirect:" + Global.getAdminPath() + "/order/voidsheetDetail/?repage";
	}
	
	@RequestMapping(value="findpingVoidsheetDetail")
	public String findpingVoidsheetDetail(Model model,String voidsheetNo,HttpServletRequest request,HttpServletResponse response){
		//获取订单信息
		VoidsheetDetail detail=voidsheetDetailServiceFacade.findVoidsheetDetail(voidsheetNo);
		
		//获取废票的航段信息
		List<TicketorderSegment> segment=ticketorderSegmentServiceFacade.findSegmentByOrderNo(voidsheetNo);
		//获取旅客信息
		List<VoidsheetPassenger> passenger=voidsheetPassengerServiceFacade.findvoidPassenger(voidsheetNo);
		//获取操作历史
		List<VoidsheetHistory> historyList=voidsheetHistoryServiceFacade.findVoidsheetHistory(voidsheetNo);
		//获取原始的记机票明细
		List<TicketorderSinglePrice> singlePrice=ticketorderSinglePriceServiceFacade.findorderSinglePrice(detail.getOrderNo());
		//行程单收件人
				List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
				//收件人
				List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		
		model.addAttribute("detail",detail);
		model.addAttribute("tSegmentList",segment );
		model.addAttribute("tPassengerList",passenger);
		model.addAttribute("historyList", historyList);
		model.addAttribute("singlePriceList", singlePrice);
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("invoiceList", invoiceList);
		return "sz/order/pingtai/pingVoidsheetDetail";
	}
	
	/**
	 * 查看废票的记录
	 * @return
	 */
	public String findVoidsheetDetail(Model model,String voidsheetNo,HttpServletRequest request,HttpServletResponse response){
		//获取订单信息
		VoidsheetDetail detail=voidsheetDetailServiceFacade.findVoidsheetDetail(voidsheetNo);
		//查看订单的时长
		long duration=DateUtils.pastMinutes(detail.getCreateTime());
		detail.setDuration(duration+"分钟");
		//获取单个供应商的信息  通过供应商的id取值
		Office office = officeService.get(detail.getSupplierProductNo());
		//获取废票的航段信息
		List<TicketorderSegment> segment=ticketorderSegmentServiceFacade.findSegmentByOrderNo(voidsheetNo);
		//获取旅客信息
		List<VoidsheetPassenger> passenger=voidsheetPassengerServiceFacade.findvoidPassenger(voidsheetNo);
		//获取操作历史
		List<VoidsheetHistory> history=voidsheetHistoryServiceFacade.findVoidsheetHistory(voidsheetNo);
		//获取原始的记机票明细
		List<TicketorderSinglePrice> singlePrice=ticketorderSinglePriceServiceFacade.findorderSinglePrice(detail.getOrderNo());
		List<TicketorderMessage> ticketorderMessage=ticketorderMessageServiceFacade.findTicketorderMessage(voidsheetNo);
		model.addAttribute("ticketorderMessageList",ticketorderMessage );
		model.addAttribute("office", office);
		model.addAttribute("detail",detail);
		model.addAttribute("tSegmentList",segment );
		model.addAttribute("tPassengerList",passenger);
		model.addAttribute("history", history);
		model.addAttribute("singlePriceList", singlePrice);
		return "sz/order/pingtai/pingVoidsheetDetail";
	}
	
	@RequestMapping(value="voidsheetDetailmessage")
	public String voidsheetDetailmessage(HttpServletRequest request,HttpServletResponse response){
		//历史纪录
		VoidsheetHistory history=new VoidsheetHistory();
		//历史操作时间
		history.setOperationTime(new Date());
		//获取订单号
		String voidsheetNo=request.getParameter("voidsheetNo");
		//订单号
		history.setVoidsheetNo(voidsheetNo);
		User user = UserUtils.getUser();
		history.setOperatorName(user.getName());
		//设置记录
		history.setPreviousOperation("平台督促供应商核实废票");
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//保存历史纪录
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/order/ping/voidsheetDetail/findpingVoidsheetDetail?voidsheetNo="+voidsheetNo;
	}
}