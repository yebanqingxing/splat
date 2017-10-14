/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web.pingtai;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.sz.DateUtils;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.file.download.DownloadFile;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.RefundsheetAppendix;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetHistory;
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.RefundsheetAppendixServiceFacade;
import com.sml.sz.order.service.RefundsheetCirculationServiceFacade;
import com.sml.sz.order.service.RefundsheetDetailServiceFacade;
import com.sml.sz.order.service.RefundsheetHistoryServiceFacade;
import com.sml.sz.order.service.RefundsheetPassengerServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 退票单Controller
 * 
 * @author 李千超
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/refundsheetdetail/ping/refundsheetDetail")
public class PingRefundsheetDetailController extends BaseController {

	// 退票主
	@Autowired
	private RefundsheetDetailServiceFacade refundsheetDetailServiceFacade;
	
	//行程单
	@Autowired
	InvoiceServiceFacade invoiceServiceFacade;
	
	// 退票单附件
	@Autowired
	private RefundsheetAppendixServiceFacade refundsheetAppendixServiceFacade;

	// 退票单流转
	@Autowired
	private RefundsheetCirculationServiceFacade refundsheetCirculationServiceFacade;

	// 退票历史
	@Autowired
	private RefundsheetHistoryServiceFacade refundsheetHistoryServiceFacade;

	// 退票单人信息
	@Autowired
	private RefundsheetPassengerServiceFacade refundsheetPassengerServiceFacade;

	// 订单旅客的信息
	@Autowired
	private TicketorderPassengerFacade ticketorderPassengerFacade;

	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	// 原始的航段信息
	@Autowired
	private TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;

	//已经改签过的旅客的信息
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	//每个人一条记录单人价格表
		@Autowired
		private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;

	//机票信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	

	@RequestMapping(value = { "list", "" })
	public String list(String flag,RefundsheetDetail refundsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		Page<RefundsheetDetail> page = refundsheetDetailServiceFacade.findPage(new Page<RefundsheetDetail>(request, response), refundsheetDetail);
		page.setOld(false);
		
		//查询状态所对应的条数
		Map<String,String> statusCountList=refundsheetDetailServiceFacade.findRefundsheetStatusCount(refundsheetDetail);
		//获取支付条数
		Map<String,String> payStatusCountList=refundsheetDetailServiceFacade.findPayStatusCount(refundsheetDetail);
		//获取操作状态条数
		Map<String,String> currentStatusList=refundsheetDetailServiceFacade.findCurrentStatus(refundsheetDetail);
		model.addAttribute("page", page);
		model.addAttribute("statusCountList", statusCountList);
		model.addAttribute("payStatusCountList", payStatusCountList);
		model.addAttribute("currentStatusList",currentStatusList);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="sz/order/pingtai/orderretreatPage";
		}else{
			returnPage="sz/order/pingtai/orderretreat";
		}
		return returnPage;
	}
	
	@RequestMapping(value="pingrefundsheetDetail")
	public String pingrefundsheetDetail(Model model,String refundsheetNo,HttpServletRequest request,HttpServletResponse response){
		//获取订单的信息
		RefundsheetDetail detail=refundsheetDetailServiceFacade.findRefundsheetDetail(refundsheetNo);
		//时长
		long duration=DateUtils.pastMinutes(detail.getCreateTime());
		detail.setDuration(duration+"分钟");
		
		//获取附件
		List<RefundsheetAppendix> appendixList=refundsheetAppendixServiceFacade.findrefundsheetAppendix(refundsheetNo);
		//获取旅客信息  
		List<RefundsheetPassenger> passengerList=refundsheetPassengerServiceFacade.findRefundsheetPassenger(refundsheetNo);  
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(detail.getOrderNo());
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
				
		//获取航段  获取已退的航段
		List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(refundsheetNo);
		List<RefundsheetHistory> historyList=refundsheetHistoryServiceFacade.findRefundsheetHistory(refundsheetNo);
		//行程单
		Invoice invoice=new Invoice();
		invoice.setOrderNo(refundsheetNo);
		List<Invoice> invoiceList=invoiceServiceFacade.findList(invoice);
		//List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("recipientList", recipientList);
		
		model.addAttribute("detail",detail );
		model.addAttribute("appendixList",appendixList);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList",segmentList);
		model.addAttribute("historyList",historyList);
		model.addAttribute("singlePriceList",singlePriceList);
		model.addAttribute("invoiceList",invoiceList);
		return "sz/order/pingtai/pingRefundsheetDetail";
	}
	
	@RequestMapping(value="pingdownload")
	public void download(String id,HttpServletRequest request ,HttpServletResponse response){
		//这里是用来下载的啊
		RefundsheetAppendix appendix=refundsheetAppendixServiceFacade.get(id);
		DownloadFile.downloadFile(request, response, appendix.getFileAddress(), appendix.getOrgnFileName());
	}
	//平台取消退票订单
	@RequestMapping(value="editRefundsheetDetail")
	public String editRefundsheetDetail(String id,HttpServletRequest request ,HttpServletResponse response){
	//获取订单
		RefundsheetDetail refundsheetDetail = refundsheetDetailServiceFacade.get(id);
	//获取订单操作状态
		String currentStatus = request.getParameter("hid_currentStatus");
	//设置订单状态
		refundsheetDetail.setRefundsheetStatus(currentStatus);
	//添加历史纪录
		RefundsheetHistory history = new RefundsheetHistory();
		if("2".equals(currentStatus)){
		//平台取消退款申请后，主单的乘客可以重新申请退票
		List<RefundsheetPassenger> findRefundsheetPassenger = refundsheetPassengerServiceFacade.findRefundsheetPassenger(refundsheetDetail.getRefundsheetNo());
			for (RefundsheetPassenger RefundsheetPassenger : findRefundsheetPassenger) {
					ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",RefundsheetPassenger.getOrderPassengerId());
				}
			//操作记录
			history.setPreviousOperation("平台取消订单");
		}else if ("6".equals(currentStatus)){
			//操作记录
			history.setPreviousOperation("平台取消订单");
		}
	//更新退票订单
		refundsheetDetailServiceFacade.updateRefundsheetStatus(refundsheetDetail);
		history.setOperationTime(new Date());
		history.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		history.setOperatorName("平台:"+UserUtils.getUser().getName());
		refundsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/ping/refundsheetDetail/pingrefundsheetDetail?refundsheetNo="+refundsheetDetail.getRefundsheetNo();
	}

}