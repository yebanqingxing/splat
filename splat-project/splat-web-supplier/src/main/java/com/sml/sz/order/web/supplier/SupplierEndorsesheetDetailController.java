/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web.supplier;

import java.util.Date;
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
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.EndorsesheetHistory;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.model.EndorsesheetPassengerModel;
import com.sml.sz.order.service.EndorsesheetAppendixServiceFacade;
import com.sml.sz.order.service.EndorsesheetCirculationServiceFacade;
import com.sml.sz.order.service.EndorsesheetDetailServiceFacade;
import com.sml.sz.order.service.EndorsesheetHistoryServiceFacade;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 改签单Controller
 * @author 李千超
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/order/supplier/endorsesheetDetail")
public class SupplierEndorsesheetDetailController extends BaseController {

	//改签
	@Autowired
	private EndorsesheetDetailServiceFacade endorsesheetDetailServiceFacade;
	
	//改签附件
	@Autowired 
	private EndorsesheetAppendixServiceFacade endorsesheetAppendixServiceFacade;
	
	//改签单流转
	@Autowired
	private EndorsesheetCirculationServiceFacade endorsesheetCirculationServiceFacade;
	
	//改签单的历史
	@Autowired
	private EndorsesheetHistoryServiceFacade endorsesheetHistoryServiceFacade;
	
	//改签但旅客
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	//原始旅客信息
	@Autowired
	private TicketorderPassengerFacade ticketorderPassengerFacade;
	
	//原始订单信息
	@Autowired
	private TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	
	//行程单
	@Autowired
	private  InvoiceServiceFacade invoiceServiceFacade;
	
	//收件人
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	//原始航班信息 
	@Autowired
	private TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	
	//单人价格表
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	
	//机票的信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	
	//@RequiresPermissions("order:endorsesheetDetail:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(String flag,EndorsesheetDetail endorsesheetDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		System.out.println(user.getCompany().getId());
		// 供应商的id
		endorsesheetDetail.setSupplierProductNo(user.getCompany().getId());
		
		Page<EndorsesheetDetail> page = endorsesheetDetailServiceFacade.findPage(new Page<EndorsesheetDetail>(request, response), endorsesheetDetail); 
		page.setOld(false);
		//改期的状态以及条数
		Map<String,String> statusCount = endorsesheetDetailServiceFacade.findEndorseDetailStatusCount(endorsesheetDetail);
		//改签的支付状态及条数
		Map<String,String> payStatusCount=endorsesheetDetailServiceFacade.findPayStatusCount(endorsesheetDetail);
		//改签的操作状态及条数
		Map<String,String> currentStatus=endorsesheetDetailServiceFacade.findCurrentStatusCount(endorsesheetDetail);
		model.addAttribute("payStatusCount", payStatusCount);
		model.addAttribute("currentStatus",currentStatus);
		model.addAttribute("statusCount", statusCount);
		model.addAttribute("page", page);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage ="cgs/order/supplier/endorsesheetDetailListPage";
		}else{
			returnPage="cgs/order/supplier/endorsesheetDetailList";
		}
		return returnPage;
	}
	
	/**
	 * 查询单个订单信息
	 * @return
	 */
	@RequestMapping(value="findEndorsesheetDetail")
	public String findEndorsesheetDetail(String endorsesheetNo,HttpServletRequest request,HttpServletResponse response,Model model){
		//获取订单信息
		EndorsesheetDetail detail=endorsesheetDetailServiceFacade.findEndorsesheetDetail(endorsesheetNo);
		//获取原始订单的信息
		TicketorderDetail ticketorderDetail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(detail.getOrderNo());
		
		//获取旅客信息
		List<EndorsesheetPassenger> passenger=endorsesheetPassengerServiceFacade.findEndorsePassenger(endorsesheetNo);
		//获取航段信息
		List<TicketorderSegment> segment=ticketorderSegmentServiceFacade.findSegmentByOrderNo(endorsesheetNo);
		//机票明细
		List<TicketorderSinglePrice> singlePrice=ticketorderSinglePriceServiceFacade.findorderSinglePrice(detail.getOrderNo());
		//历史记录
		List<EndorsesheetHistory> historyList=endorsesheetHistoryServiceFacade.findEndorsesheetHistory(endorsesheetNo);
		//通过原始订单查询旅客的行程单
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		//查看邮寄地址
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("detail",detail );
		model.addAttribute("ticketorderDetail",ticketorderDetail );
		model.addAttribute("passengerList",passenger );
		model.addAttribute("segmentList",segment );
		model.addAttribute("singlePriceList", singlePrice);
		model.addAttribute("historyList", historyList);
		return "cgs/order/supplier/supplierEndorsesheetDetail";
	}
	
	/**
	 * 修改改签的状态  拒接或者同意
	 * @return
	 */
	@RequestMapping(value="editEndorsesheetDetail")
	public String  editEndorsesheetDetail(EndorsesheetPassengerModel endorsesheetPassengers,EndorsesheetDetail endorsesheetDetail,HttpServletRequest request ,HttpServletResponse response ){
		//获取操作状态
		String  hid_currentStatus=request.getParameter("hid_currentStatus");
		//获取订单号
		String hid_endorseNo=request.getParameter("hid_endorseNo");
		
		//修改状态改签单状态操作状态（流转状态） 1审核等待 2 审核通过 3 审核拒绝 4 平台审核通过 5 改签完成
		endorsesheetDetailServiceFacade.updateCurrnetStatus(hid_currentStatus, hid_endorseNo);
		//修改旅客的金额
		endorsesheetPassengerServiceFacade.updateEndorsePassengerPrice(endorsesheetPassengers); 
		endorsesheetDetailServiceFacade.updateEndorsesheetDetail(endorsesheetDetail);
		
		//将记录添加到数据库
		EndorsesheetHistory history=new EndorsesheetHistory();
		//操作时间
		history.setOperationTime(new Date());
		if("3".equals(hid_currentStatus)){
			history.setPreviousOperation("供应商拒绝改签");
		//取消后原单可重新申请
		List<EndorsesheetPassenger> findEndorsePassenger = endorsesheetPassengerServiceFacade.findEndorsePassenger(hid_endorseNo);
		for (EndorsesheetPassenger endorsesheetPassenger : findEndorsePassenger) {
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",endorsesheetPassenger.getOrderPassengerId());
			}
		}
		if("2".equals(hid_currentStatus))
			history.setPreviousOperation("供应商同意改签");
		history.setEndorsesheetNo(hid_endorseNo);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		history.setOperatorName("供应商:"+UserUtils.getUser().getName());
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/supplier/endorsesheetDetail/list1";
	}

}