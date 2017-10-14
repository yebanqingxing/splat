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
import com.sml.sz.file.download.DownloadFile;
import com.sml.sz.httpclient.SimpleHttpParam;
import com.sml.sz.httpclient.SimpleHttpResult;
import com.sml.sz.httpclient.SimpleHttpUtils;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.RefundsheetAppendix;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetHistory;
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.model.RefundsheetPassengerModel;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.RefundsheetAppendixServiceFacade;
import com.sml.sz.order.service.RefundsheetCirculationServiceFacade;
import com.sml.sz.order.service.RefundsheetDetailServiceFacade;
import com.sml.sz.order.service.RefundsheetHistoryServiceFacade;
import com.sml.sz.order.service.RefundsheetPassengerServiceFacade;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderMessageServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

import freemarker.template.utility.StringUtil;

/**
 * 退票单Controller
 * 
 * @author 李千超
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/refundsheetdetail/supplier/refundsheetDetail")
public class SupplierRefundsheetDetailController extends BaseController {

	// 退票主
	@Autowired
	private RefundsheetDetailServiceFacade refundsheetDetailServiceFacade;

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

	// 原始的航段信息
	@Autowired
	private TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;

	//已经改签过的旅客的信息
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	//每个人一条记录单人价格表
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;

	@Autowired
	TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	//机票的信息
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	//行程单
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	@Autowired
	private TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	//收件人
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	//@RequiresPermissions("refundsheetdetail:refundsheetDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(String flag,RefundsheetDetail refundsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		System.out.println(user.getCompany().getId());
		// 供应商的id
		refundsheetDetail.setSupplierProductNo(user.getCompany().getId());
		//前台客户id
		//refundsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		Page<RefundsheetDetail> page = refundsheetDetailServiceFacade.findPage(new Page<RefundsheetDetail>(request, response), refundsheetDetail);
		page.setOld(false);
		
		//查询状态所对应的条数
		Map<String,String> statusCount=refundsheetDetailServiceFacade.findRefundsheetStatusCount(refundsheetDetail);
		//获取支付条数
		Map<String,String> payStatusCount=refundsheetDetailServiceFacade.findPayStatusCount(refundsheetDetail);
		//获取操作状态条数
		Map<String,String> currentStatus=refundsheetDetailServiceFacade.findCurrentStatus(refundsheetDetail);
		model.addAttribute("page", page);
		model.addAttribute("statusCount", statusCount);
		model.addAttribute("payStatusCount", payStatusCount);
		model.addAttribute("statusCount", statusCount);
		model.addAttribute("currentStatus",currentStatus);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="cgs/order/supplier/orderretreatPage";
		}else{
			returnPage="cgs/order/supplier/orderretreat";
		}
		return returnPage;
	}

	//
	@RequestMapping(value="findRefundsheetDetail")
	public  String findRefundsheetDetail(HttpServletRequest request,HttpServletResponse response,Model model,String refundsheetNo){
		//获取订单的信息
		RefundsheetDetail detail=refundsheetDetailServiceFacade.findRefundsheetDetail(refundsheetNo);
		//获取附件
		List<RefundsheetAppendix> appendixList=refundsheetAppendixServiceFacade.findrefundsheetAppendix(refundsheetNo);
		//获取旅客信息  
		List<RefundsheetPassenger> passengerList=refundsheetPassengerServiceFacade.findRefundsheetPassenger(refundsheetNo);  
		//获取航段  获取已退的航段
		List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(refundsheetNo);
		for (TicketorderSegment segment : segmentList) {
			if(null != segment.getArriveTime() && !"".equals(segment.getArriveTime())){
				String duration=(double)((segment.getArriveTime().getTime()-segment.getDepartureTime().getTime())/(60*60*1000))+"小时";
				//获取时长
				segment.setDuration(duration);
			}
		}
		
		List<RefundsheetHistory> historyList=refundsheetHistoryServiceFacade.findRefundsheetHistory(refundsheetNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(detail.getOrderNo());
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		//查找留言信息
		TicketorderMessage ticketorderMessage=new TicketorderMessage();
		ticketorderMessage.setOrder_no(refundsheetNo);
		List<TicketorderMessage> ticketorderMessageList=ticketorderMessageServiceFacade.findList(ticketorderMessage);
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("recipientList", recipientList);
		
		model.addAttribute("detail",detail );
		model.addAttribute("appendixList",appendixList);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList",segmentList);
		model.addAttribute("historyList",historyList);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		return "cgs/order/supplier/supplierRefundsheetDetail";
	}
	
	@RequestMapping(value="download")
	public void download(String id,HttpServletRequest request ,HttpServletResponse response){
		//这里是用来下载的啊
		RefundsheetAppendix appendix=refundsheetAppendixServiceFacade.get(id);
		DownloadFile.downloadFile(request, response, appendix.getFileAddress(), appendix.getOrgnFileName());
	}
	
	@RequestMapping(value="editRefundsheetStatus")
	public String editRefundsheetStatus(String refundsheetStatus,String refundsheetNo,HttpServletRequest request, HttpServletResponse response,RefundsheetPassengerModel refundsheetPassengerModel){
		//数据库： 退票单状态 1 下单成功（采购商申请退票） 2 取消退票  4 异常订单 
		//操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 等待退票(采购商确认) 4 审核通过 5 退票完成
		//支付状态 0 已经退款 1 未退款 2 退款失败
		//获得退票订单操作状态
		String currentStatus=request.getParameter("hid_currentStatus");
		//System.out.println(request.getParameter("supplierSettlementPrice"));
		
		RefundsheetDetail refundsheetDetail=refundsheetDetailServiceFacade.findRefundsheetDetail(refundsheetNo);
		if(!"2".equals(currentStatus)){
			refundsheetDetail.setSupplierTotSettlementPrice(request.getParameter("supplierSettlementPrice"));
			if(refundsheetPassengerModel != null){
				refundsheetPassengerServiceFacade.update(refundsheetPassengerModel.getRefundsheetPassengers());
			}
			
			if(StringUtils.isNotEmpty(request.getParameter("supplierSettlementPrice"))){
				refundsheetDetail.setSupplierTotSettlementPrice(request.getParameter("supplierSettlementPrice"));
			}
			if(refundsheetPassengerModel != null){
				refundsheetPassengerServiceFacade.update(refundsheetPassengerModel.getRefundsheetPassengers());
			}
		}
		
		
		refundsheetDetail.setRefundsheetNo(refundsheetNo);
		refundsheetDetail.setCurrentStatus(currentStatus);
		
		//System.out.println(refundsheetPassengerModel);
		//添加历史记录
		RefundsheetHistory history=new RefundsheetHistory();
		history.setOperationTime(new Date());
		//状态为2 是拒绝审核
		if("2".equals(currentStatus)){
			history.setPreviousOperation("供应商拒绝退票");
			//供应商拒绝退款申请后，主单的乘客可以重新申请退票
			List<RefundsheetPassenger> findRefundsheetPassenger = refundsheetPassengerServiceFacade.findRefundsheetPassenger(refundsheetDetail.getRefundsheetNo());
				for (RefundsheetPassenger RefundsheetPassenger : findRefundsheetPassenger) {
						ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",RefundsheetPassenger.getOrderPassengerId());
			}
		}
		if("4".equals(currentStatus)){
			history.setPreviousOperation("供应商同意退票");
			//设置支付状态为 1（未退款）
			refundsheetDetail.setPayStatus("1");
		}
		refundsheetDetailServiceFacade.updateRefundsheetStatus(refundsheetDetail);
		history.setRefundsheetNo(refundsheetNo);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		history.setOperatorName("供应商:"+UserUtils.getUser().getName());
		refundsheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/refundsheetdetail/supplier/refundsheetDetail/list";
	}
	
	@RequestMapping(value="toPayForRefundsheetDetail")
	public String toPayForRefundsheetDetail(String refundsheetNo,HttpServletRequest request, HttpServletResponse response){
		//数据库： 退票单状态 1 下单成功（采购商申请退票） 2 取消退票  4 异常订单 
		//操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 等待退票(采购商确认) 4 审核通过 5 退票完成
		//支付状态 0 已经退款 1 未退款 2 退款失败
		//设置操作状态为5:退票完成
		RefundsheetDetail refundsheetDetail = refundsheetDetailServiceFacade.findRefundsheetDetail(refundsheetNo);
		refundsheetDetail.setCurrentStatus("5");
		//去退款
		TicketorderDetail ticketorderDetail2=ticketorderDetailServiceFacade.findorderDetailByOrderNo(refundsheetDetail.getOrderNo());
		if(null != ticketorderDetail2.getRelationOrderNo() && !"".equals(ticketorderDetail2.getRelationOrderNo())){
			SimpleHttpUtils http=new SimpleHttpUtils();
			Map<String,Object> parameters = new LinkedHashMap<String,Object>();
			//baoshengb2b@sina.com  
			parameters.put("accountType", "2");//账户类型 1-对公，2-对私
			parameters.put("userNo", "888000000000000");
			parameters.put("orderNo",ticketorderDetail2.getOrderNo());//订单号
			parameters.put("trxNo", ticketorderDetail2);//流水号
			parameters.put("amount", refundsheetDetail.getTotalPrice());//退款多少
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
				//设置支付状态为0：已退款
				refundsheetDetail.setPayStatus("0");
			}else if(re.getContent().indexOf("error") >1){
				//设置支付状态为2 失败
				refundsheetDetail.setPayStatus("2");
			}
		}
		
		refundsheetDetailServiceFacade.updateRefundsheetStatus(refundsheetDetail);
		//添加历史记录
		RefundsheetHistory history=new RefundsheetHistory();
		history.setOperationTime(new Date());
		history.setPreviousOperation("供应商已退款退票");
		history.setOperatorName("供应商:"+UserUtils.getUser().getName());
		history.setRefundsheetNo(refundsheetNo);
		refundsheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/refundsheetdetail/supplier/refundsheetDetail/findRefundsheetDetail?refundsheetNo="+refundsheetNo;
	}
}