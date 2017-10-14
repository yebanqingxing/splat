/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web.supplier;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.httpclient.SimpleHttpParam;
import com.sml.sz.httpclient.SimpleHttpResult;
import com.sml.sz.httpclient.SimpleHttpUtils;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderHistory;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.model.InvoiceModel;
import com.sml.sz.order.entity.model.RefundsheetDetailModel;
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
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

import groovy.lang.Buildable;

/**
 * 订单生成模块Controller
 * @author 李千超
 * @version 2016-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/order/supplier/tbTicketorderDetail")
public class SupplierTicketorderDetailController extends BaseController {

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
	//每个机票对应一个记录
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	@Autowired
	RecipientServiceFacade recipientServiceFacade;
	/**
	 * 订单留言
	 */
	@Autowired
	TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	@RequestMapping(value="toOrder")
	public String toOrderDetail(){
		return "cgs/order/supplier/order";
	}
	
	
	//@RequiresPermissions("order:tbTicketorderDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(String flag,TicketorderDetail tbTicketorderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		System.out.println(user.getCompany().getId());
		// 供应商的id
		tbTicketorderDetail.setSupplierProductNo(user.getCompany().getId());
		Page<TicketorderDetail> page = ticketorderDetailServiceFacade.findPage(new Page<TicketorderDetail>(request, response), tbTicketorderDetail); 
		//设置分页
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
			returnPage="cgs/order/supplier/tbTicketorderDetailListPage";
		}else{
			returnPage="cgs/order/supplier/orderquery";
		}
		return returnPage;
	}

	//回填票号
	@RequestMapping(value="outTicketNo")
	public String outTicketNo(String id,String orderNo, String ticketNo, HttpServletRequest request,HttpServletResponse response,RefundsheetDetailModel refundsheetDetailModel){
		System.out.println(refundsheetDetailModel);
		System.out.println(request.getParameter("crs_pnr"));
		String tktNo="";
		TicketorderDetail ticketorderDetail=new TicketorderDetail();
		if("6".equals(request.getParameter("hid_orderStatus"))){
			if(refundsheetDetailModel!=null &&  refundsheetDetailModel.getRefundsheetDetails() != null ){
				for (RefundsheetDetail item : refundsheetDetailModel.getRefundsheetDetails()) {
					ticketorderPassengerFacade.updateTicketorderTicket(item.getTicketNo(), item.getId());
					TicketorderTicket ticket=new TicketorderTicket();
					//票号所对应的订单号
					ticket.setOrderNo(orderNo);
					//出票时间
					ticket.setIssuedTime(new Date());
					ticket.setTicketNo(item.getTicketNo());
					ticket.setOrderId((new Date()).getTime()+"");
					tktNo=item.getTicketNo();
					ticketorderTicketServiceFacade.save(ticket);
				}
			}
			
			ticketorderDetail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
			
			ticketorderDetail.setCrsPnr(request.getParameter("crs_pnr"));
			ticketorderDetail.setCurrentStatus("8");
			ticketorderDetail.setIssueResult("1");
			ticketorderDetail.setTicketNoTemp(tktNo);
			ticketorderDetailServiceFacade.updateOrderPrice(ticketorderDetail);
			ticketorderDetailServiceFacade.save(ticketorderDetail);
			//修改订单的状态是出票状态 状态为6是出票状态
		//	ticketorderDetailServiceFacade.updateOrderStatus("6", orderNo);
			//更改出票结果
			//ticketorderDetailServiceFacade.updateIssueResult("1", orderNo);
		}
		TicketorderHistory history=new TicketorderHistory();
		history.setOperationTime(new Date());
		history.setOperatorName("供应商:"+UserUtils.getUser().getName());
		//设置操作记录
		history.setPreviousOperation("供应商出票");
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		history.setOrderNo(orderNo);
		if(null != ticketorderDetail.getRelevantOrderNo() && !"".equals(ticketorderDetail.getRelevantOrderNo())){
		SimpleHttpUtils http=new SimpleHttpUtils();
		Map<String,Object> parameters = new LinkedHashMap<String,Object>();
		//baoshengb2b@sina.com  
		parameters.put("accountType", "2");//账户类型 1-对公，2-对私
		parameters.put("userNo", "888000000000000");
		parameters.put("orderNo", orderNo);//订单号
		parameters.put("trxNo", ticketorderDetail.getRelevantOrderNo());//流水号
		String url="";
		//回填票号
		url="http://210.51.190.223:9083/pay-web-gateway/remit_remitOrderSave.action";
		SimpleHttpParam simple=new SimpleHttpParam(url);
		
		simple.setParameters(parameters);
		simple.setMethod(SimpleHttpUtils.HTTP_METHOD_POST);
		SimpleHttpResult re=http.httpRequest(simple);
		
		System.out.println(re.getStatusCode());
		System.out.println(re.getContent());
		//操作历史记录
			
		}
		//入库
		ticketorderHistoryFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/supplier/tbTicketorderDetail/list";
	}
	
	/**
	 * 修改订单的状态
	 * @return
	 */
	@RequestMapping(value="editTicketorderDetailStatus")
	public String editTicketorderDetailStatus(String currentStatus,String orderNo,HttpServletRequest request,HttpServletResponse response
			,TicketorderSinglePriceModel ticketorderSinglePriceModels,TicketorderDetail ticketorderDetail,InvoiceModel invoiceModel){
		
		System.out.println(ticketorderSinglePriceModels.getTicketorderSinglePrices());
		if(ticketorderSinglePriceModels!=null && ticketorderDetail != null ){
			
			ticketorderSinglePriceServiceFacade.update(ticketorderSinglePriceModels.getTicketorderSinglePrices());
			ticketorderDetailServiceFacade.updateOrderPrice(ticketorderDetail);
			//invoiceServiceFacade.update(invoiceModel.getInvoices());
			//System.out.println(invoiceModel.getInvoices().size());
		}
		
		//1:正在核实价格和政策 2:已经审核价格和政策，3:已督促采购商核实价格,4:请尽快支付,5:审核拒接，6:全额退款 
		//ticketorderDetailServiceFacade.updateOrderStatus(orderStatus, orderNo);
		TicketorderHistory history=new TicketorderHistory();
		history.setOperationTime(new Date());
		if(!"3".equals(currentStatus)){
		ticketorderDetailServiceFacade.updateCurrentStatus(currentStatus, orderNo);
		}
		if("2".equals(currentStatus)){
			//2 是签单
			history.setPreviousOperation("供应商已核单");
		}else if("5".equals(currentStatus)){
			//拒单状态
			history.setPreviousOperation("供应商已拒绝");
		}else if("3".equals(currentStatus)){
			history.setPreviousOperation("督促采购商支付");
		}else if("10".equals(currentStatus)){
			history.setPreviousOperation("供应商同意全额退款。");
			//去退票退款
			//设置支付状态为已退款
			TicketorderDetail ticketorderDetail2=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
			if(null != ticketorderDetail2.getRelationOrderNo() && !"".equals(ticketorderDetail2.getRelationOrderNo())){
				SimpleHttpUtils http=new SimpleHttpUtils();
				Map<String,Object> parameters = new LinkedHashMap<String,Object>();
				//baoshengb2b@sina.com  
				parameters.put("accountType", "2");//账户类型 1-对公，2-对私
				parameters.put("userNo", "888000000000000");
				parameters.put("orderNo",ticketorderDetail2.getOrderNo());//订单号
				parameters.put("trxNo", ticketorderDetail2);//流水号
				parameters.put("amount", ticketorderDetail2.getTotalPrice());//退款多少
				//退款
				SimpleHttpParam simple=new SimpleHttpParam("http://210.51.190.223:9083/pay-web-gateway/remit_returnPay.action");
				simple.setParameters(parameters);
				simple.setMethod(SimpleHttpUtils.HTTP_METHOD_POST);
				SimpleHttpResult re=http.httpRequest(simple);
				
				System.out.println(re.getStatusCode());
				System.out.println(re.getContent());
			
				//re.get
				//标志成功
				if(re.getContent().indexOf("success") >1 ){
					ticketorderDetailServiceFacade.updatePayStatus("4", orderNo);
				}else if(re.getContent().indexOf("error") >1){
					//退款失败
					ticketorderDetailServiceFacade.updatePayStatus("2", orderNo);
				}
			}
			
		}else if("12".equals(currentStatus)){
			//currentStatus=11：拒绝全额退款 
			history.setPreviousOperation("供应商拒绝全额退款。");
		}
		history.setOrderNo(orderNo);
		history.setOperatorName("供应商:"+UserUtils.getUser().getName());
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//将历史信息保存数据库
		ticketorderHistoryFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/supplier/tbTicketorderDetail";
	}
	
	@RequestMapping(value="findticketorderDetail")
	public String findticketorderDetail(HttpServletResponse response,Model model ,HttpServletRequest request,String orderNo){
		//获取订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		String returnPage="";
		
		//获取订单的旅客信息
		List<TicketorderPassenger> passengerList=ticketorderPassengerFacade.findTicketorderPassenger(orderNo);
		//获取航段信息
		List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);
		//获取历史操作
		List<TicketorderHistory> historyList=ticketorderHistoryFacade.findTicketorderHistory(orderNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(orderNo);
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		//查找留言信息
		TicketorderMessage ticketorderMessage=new TicketorderMessage();
		ticketorderMessage.setOrder_no(orderNo);
		List<TicketorderMessage> ticketorderMessageList=ticketorderMessageServiceFacade.findList(ticketorderMessage);
		
		//行程单
		Invoice invoice=new Invoice();
		invoice.setOrderNo(orderNo);
		List<Invoice> invoiceList=invoiceServiceFacade.findList(invoice);
		
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(orderNo);
		model.addAttribute("detail", detail);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList", segmentList);
		model.addAttribute("historyList", historyList);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		model.addAttribute("recipientList", recipientList);
		//已经支付  需要跳到出票页面
		if("0".equals(detail.getPayStatus())){
			returnPage="cgs/order/supplier/outTicketNo";
		}else{
			returnPage="cgs/order/supplier/supplierticketorderDetail";
		}
		return returnPage;
	}
	/*//确认全额退款
		@RequestMapping(value="refundsheetDetailAAAAAA")
		public String refundsheetDetail(HttpServletResponse response,Model model ,HttpServletRequest request,String orderNo,String currentStatus){
			//TODO
			//设置操作状态     10:申请通过全额退款  11:拒绝全额退款  
			ticketorderDetailServiceFacade.updateCurrentStatus(currentStatus, orderNo);
			//添加历史记录
			TicketorderHistory history=new TicketorderHistory();
			history.setOperationTime(new Date());
			if("10".equals(currentStatus)){
				history.setPreviousOperation("供应商同意全额退款。");
				//去退票退款
				//设置支付状态为已退款
				TicketorderDetail ticketorderDetail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
				if(null != ticketorderDetail.getRelationOrderNo() && !"".equals(ticketorderDetail.getRelationOrderNo())){
					SimpleHttpUtils http=new SimpleHttpUtils();
					Map<String,Object> parameters = new LinkedHashMap<String,Object>();
					//baoshengb2b@sina.com  
					parameters.put("accountType", "2");//账户类型 1-对公，2-对私
					parameters.put("userNo", "888000000000000");
					parameters.put("orderNo",ticketorderDetail.getOrderNo());//订单号
					parameters.put("trxNo", ticketorderDetail);//流水号
					//退款
					SimpleHttpParam simple=new SimpleHttpParam("http://210.51.190.223:9083/pay-web-gateway/remit_returnPay.action");
					simple.setParameters(parameters);
					simple.setMethod(SimpleHttpUtils.HTTP_METHOD_POST);
					SimpleHttpResult re=http.httpRequest(simple);
					
					System.out.println(re.getStatusCode());
					System.out.println(re.getContent());
					//标志成功
					if("success".equals(re.getStatusCode())){
						ticketorderDetailServiceFacade.updatePayStatus("4", orderNo);
					}else if("error".equals(re.getStatusCode())){
						//退款失败
						ticketorderDetailServiceFacade.updatePayStatus("2", orderNo);
					}
				}
				
			}else{
				//currentStatus=11：拒绝全额退款 
				history.setPreviousOperation("供应商拒绝全额退款。");
			}
			//保存历史纪录
			history.setOrderNo(orderNo);
			history.setOperatorName("供应商:"+UserUtils.getUser().getName());
			ticketorderHistoryFacade.save(history);
			
			
			
			//回跳到订单列表
			return "redirect:"+Global.getAdminPath()+"/order/supplier/tbTicketorderDetail/list";
		}	*/
}