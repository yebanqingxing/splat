/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web.pingtai;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.ext.awt.image.renderable.OffsetRable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sml.sz.DateUtils;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderHistory;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.model.InvoiceModel;
import com.sml.sz.order.entity.model.TicketorderSinglePriceModel;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderHistoryFacade;
import com.sml.sz.order.service.TicketorderMessageServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderPnrServiceFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.sys.entity.Office;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.OfficeService;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 订单生成模块Controller
 * @author 李千超
 * @version 2016-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/order/ping/tbTicketorderDetail")
public class PingTicketorderDetailController extends BaseController {

	//订单主表的service
	@Autowired
	TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	
	//订单旅客的service
	@Autowired
	TicketorderPassengerFacade ticketorderPassengerFacade;
	
	//订单历史的service
	@Autowired
	TicketorderHistoryFacade ticketorderHistoryFacade;
	
	//订单的pnr
	@Autowired
	TicketorderPnrServiceFacade ticketorderPnrServiceFacade;
	
	//航班的航段
	@Autowired
	TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	
	//行程单
	@Autowired
	InvoiceServiceFacade invoiceServiceFacade;
	
	@Autowired
	TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	@Autowired
	TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	
	//收件人
	@Autowired
	RecipientServiceFacade recipientServiceFacade;
	
	
	@RequestMapping(value="toOrder")
	public String toOrder(){
		
		return "sz/order/pingtai/order";
	}
	
	//获取供应商的信息
	@Autowired
	OfficeService officeService;
	//@RequiresPermissions("order:tbTicketorderDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(String flag,TicketorderDetail tbTicketorderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<TicketorderDetail> page = ticketorderDetailServiceFacade.findPage(new Page<TicketorderDetail>(request, response), tbTicketorderDetail); 
		page.setOld(false);
		
		//查操作状态以及条数
		Map<String, String> currentStatus = ticketorderDetailServiceFacade.findCurrentStatus(tbTicketorderDetail);
		//查看支付的状态以及条数
		Map<String, String> findPayStatus=ticketorderDetailServiceFacade.findPayStatusCount(tbTicketorderDetail);
		//查看订单状以及状态
		Map<String, String> findOrderStatus=ticketorderDetailServiceFacade.findOrderStatusCount(tbTicketorderDetail);
		model.addAttribute("page", page);
		model.addAttribute("findPayStatus", findPayStatus);
		model.addAttribute("findOrderStatus", findOrderStatus);
		model.addAttribute("currentStatus", currentStatus);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="sz/order/pingtai/tbTicketorderDetailListPage";
		}else{
			returnPage="sz/order/pingtai/orderquery";
		}
		return returnPage;
	}

	@RequestMapping(value="findpingTicketorderDetail")
	public String findpingTicketorderDetail(String orderNo ,Model model ,HttpServletRequest request, HttpServletResponse response){
		//获取订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		//获取单个供应商的信息  通过供应商的id取值
		Office office = officeService.get(detail.getSupplierProductNo());
		
		//获取订单的旅客信息
		List<TicketorderPassenger> passengerList=ticketorderPassengerFacade.findTicketorderPassenger(orderNo);
		//获取航段信息
		List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);
//		for (TicketorderSegment segment : segmentList) {
//			//TODO
//			if(null != segment.getArriveTime() && null !=segment.getDepartureTime()){
//			String duration=(double)((segment.getArriveTime().getTime()-segment.getDepartureTime().getTime())/(60*60*1000))+"小时";
//			//获取时长
//			segment.setDuration(duration);
//			}
//		}
		
		//获取历史操作
		List<TicketorderHistory> historyList=ticketorderHistoryFacade.findTicketorderHistory(orderNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(orderNo);
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		//收件人信息
		List<Recipient> recipientList = recipientServiceFacade.findRecipientList(orderNo);
		//查找留言信息
		TicketorderMessage ticketorderMessage=new TicketorderMessage();
		ticketorderMessage.setOrder_no(orderNo);
		List<TicketorderMessage> ticketorderMessageList=ticketorderMessageServiceFacade.findList(ticketorderMessage);
		//行程单
		Invoice invoice=new Invoice();
		invoice.setOrderNo(orderNo);
		List<Invoice> invoiceList=invoiceServiceFacade.findList(invoice);
		model.addAttribute("recipientList",recipientList );
		model.addAttribute("detail", detail);
		model.addAttribute("office", office);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList", segmentList);
		model.addAttribute("historyList", historyList);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		
		return "sz/order/pingtai/pingticketorderDetail";
	}
	
	/**
	 * 修改订单的状态
	 * @return
	 */
	@RequestMapping(value="editTicketorderDetailStatus")
	public String editTicketorderDetailStatus(String orderNo,
			TicketorderSinglePriceModel ticketorderSinglePriceModels,
			TicketorderDetail ticketorderDetail,InvoiceModel invoiceModel,
			HttpServletRequest request, HttpServletResponse response){
		String currentStatus = request.getParameter("hid_currentStatus");
		//状态3 的时候是督促供应商核价 4 督促采购商支付 12 督促出票  是不会修改状态的
		if( (ticketorderSinglePriceModels!=null && ticketorderDetail != null) && (!"3".equals(currentStatus) && !"4".equals(currentStatus) && !"12".equals(currentStatus))){
			ticketorderSinglePriceServiceFacade.update(ticketorderSinglePriceModels.getTicketorderSinglePrices());
			ticketorderDetailServiceFacade.updateOrderPrice(ticketorderDetail);
			invoiceServiceFacade.update(invoiceModel.getInvoices());
		}
		TicketorderHistory history=new TicketorderHistory();
		history.setOperationTime(new Date());
		//更新订单状态  "1":已提交-下单成功;"5":异常订单;"7":取消订单(删除);  
		//更新操作状态（流转状态） 1:正在核实价格和政策 2:已经审核价格和政策，3:已督促采购商核实价格,4:请尽快支付,5:审核拒接，6:等待出票（全额退款） 7:等待出票 8:出票完成9：申请全额退款10：申请成功全额退款11：拒绝全额退款，等待出票
		if("4".equals(currentStatus)){
			//TODO
			//督促采购商尽快支付
			history.setPreviousOperation("已督促采购商尽快支付");
			history.setOperatorName(UserUtils.getUser().getName());
		}else if("3".equals(currentStatus)){
			//TODO
			//督促采购商核实价格和政策
			history.setPreviousOperation("已督促供应商核实价格和政策");
			history.setOperatorName(UserUtils.getUser().getName());
		}else if("6".equals(currentStatus)){
			//平台授权采购商全额退款
			history.setPreviousOperation("已授权采购商全额退款");
			history.setOperatorName("平台:"+UserUtils.getUser().getName());
		}else if("11".equals(currentStatus)){
			//平台拒绝采购商申请全额退款
			history.setPreviousOperation("拒绝采购商申请全额退款");
			history.setOperatorName("平台:"+UserUtils.getUser().getName());
			//平台拒绝以后支付状态设置为已支付   这个时候还可以采购商还可以重新申请和平台交流完以后
			ticketorderDetailServiceFacade.updatePayStatus("0", orderNo);
		}else if("2".equals(currentStatus)){
			//2是审核通过
				history.setPreviousOperation("供应商已经审核价格和政策");
				history.setOperatorName("平台:"+UserUtils.getUser().getName());
		}else if("5".equals(currentStatus)){
			//5拒单状态
			history.setPreviousOperation("供应商已拒绝");
			history.setOperatorName("平台:"+UserUtils.getUser().getName());
		}else if("12".equals(currentStatus)){
			history.setPreviousOperation("督促供应商出票");
			history.setOperatorName(UserUtils.getUser().getName());
		}
		//保存订单操作状态
		if(!"3".equals(currentStatus) && !"4".equals(currentStatus) && !"12".equals(currentStatus))
		ticketorderDetailServiceFacade.updateCurrentStatus(currentStatus, orderNo);
		history.setOrderNo(orderNo);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//将历史信息保存数据库
		ticketorderHistoryFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/ping/tbTicketorderDetail/findpingTicketorderDetail?orderNo="+orderNo;
	}
}