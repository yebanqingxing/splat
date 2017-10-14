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
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.EndorsesheetHistory;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.VoidsheetHistory;
import com.sml.sz.order.entity.model.EndorsesheetPassengerModel;
import com.sml.sz.order.service.EndorsesheetAppendixServiceFacade;
import com.sml.sz.order.service.EndorsesheetCirculationServiceFacade;
import com.sml.sz.order.service.EndorsesheetDetailServiceFacade;
import com.sml.sz.order.service.EndorsesheetHistoryServiceFacade;
import com.sml.sz.order.service.EndorsesheetPassengerServiceFacade;
import com.sml.sz.order.service.InvoiceServiceFacade;
import com.sml.sz.order.service.RecipientServiceFacade;
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderMessageServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.pay.util.Pay;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 改签单Controller
 * @author 李千超
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/order/endorsesheetDetail")
public class EndorsesheetDetailController extends BaseController {

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
	//原始航班信息 
	@Autowired
	private TicketorderSegmentServiceFacade ticketorderSegmentServiceFacade;
	
	//机票订单单人价格表
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	
	//机票信息色service
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	//行程单信息
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	//收件人信息
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	
	//添加留言
	@Autowired
	private TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	@ModelAttribute
	public EndorsesheetDetail get(@RequestParam(required=false) String id) {
		EndorsesheetDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = endorsesheetDetailServiceFacade.get(id);
		}
		if (entity == null){
			entity = new EndorsesheetDetail();
		}
		return entity;
	}
	
	@RequestMapping(value="toEndorseSuccess")
	public String toEndorseSuccess(Model model,HttpServletRequest request,HttpServletResponse response){
		List<EndorsesheetPassenger>  EndorsesheetPassengerList=endorsesheetPassengerServiceFacade.findEndorsePassenger("16032114114601000006");
		model.addAttribute("endorsesheetPassengerList", EndorsesheetPassengerList);
		return "sz/order/endorseSuccess";
	}
	
	
	@RequestMapping(value="endorseToEndorseDetail")
	public String endorseToEndorseDetail(Model model,String endorseIds,String orderNo,HttpServletRequest request,HttpServletResponse response){
		
		//用户在改签页面完成后点击的再次改签 改签状态是1 退票状态是3 废票状态是2
		List<EndorsesheetPassenger> tPassengerList=endorsesheetPassengerServiceFacade.findEndorsePassengerList(endorseIds, "1");
		
		//获取改签以后的订单的状态
		EndorsesheetDetail endorseDetail=endorsesheetDetailServiceFacade.findEndorsesheetDetail(orderNo);
		List<TicketorderSegment> tSegmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);
		Map map = new HashMap();
		// 改签单的原始订单号
		map.put("orderNo", orderNo);
		// 
		//map.put("orderNum", orderNum);
		model.addAttribute("endorseDetail",endorseDetail);
		model.addAttribute("orderMap", map);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);
		return "sz/order/endorseToEndorseDetail";
	}
	
	@RequestMapping(value="endorseSaveEndorsesheetDetail")
	public String  endorseSaveEndorsesheetDetail(TicketorderSegment segment,String passids,EndorsesheetDetail endorsesheetDetail,HttpServletRequest request,HttpServletResponse response){
		String orderNO="";
		MakeOrderNum make=new MakeOrderNum();
		//证明选择过来的是改期单
		if(null != endorsesheetDetail.getEndorseDetailType() && "1".equals(endorsesheetDetail.getEndorseDetailType())){
			// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
			// 改程订单06
			orderNO = make.makeOrderNum("01", "04");
		}
		//证明选择过来的是改程单
		else if(null != endorsesheetDetail.getEndorseDetailType()  && "2".equals(endorsesheetDetail.getEndorseDetailType())){
			orderNO = make.makeOrderNum("01", "05");
		}
		//选择过来的是升舱单
		else if(null != endorsesheetDetail.getEndorseDetailType()  && "3".equals(endorsesheetDetail.getEndorseDetailType())){
			orderNO = make.makeOrderNum("01", "06");
		}
		// 改签时将原来的航程信息重新录入库内
		if (null != segment.getSegId()) {
			ticketorderSegmentServiceFacade.findRefundSheetSeg(orderNO, segment);
		}
		
		User user = UserUtils.getUser();
		//存放采购商id
		endorsesheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		endorsesheetDetail.setRelevantClient(user.getCompany().getName());
		
		//供应商的名字SUPPLIER_NAME
		
		//供应商的idSUPPLIER_PRODUCT_NO
		
		//改签以后的订单号
		endorsesheetDetail.setEndorsesheetNo(orderNO);
		//下单成功状态
		endorsesheetDetail.setEndorseStatus("1");
		//支付状态 1未支付状态
		endorsesheetDetail.setPayStatus("1");
		endorsesheetDetail.setCreateTime(new Date());
		endorsesheetDetailServiceFacade.save(endorsesheetDetail);
		if(null != passids){
			String[] passIdArr = passids.split(",");
			for (String string : passIdArr) {
				//TicketorderPassenger passenger = ticketorderPassengerFacade.get(string.trim());
				EndorsesheetPassenger epassenger=endorsesheetPassengerServiceFacade.get(string.trim());
				//将id赋值给null使其添加
				epassenger.setId(null);
				//订单号
				epassenger.setOrderNo(orderNO);
				epassenger.setEndorseTime(new Date());
				endorsesheetPassengerServiceFacade.save(epassenger);
			}
		}
		//添加历史记录
		EndorsesheetHistory history=new EndorsesheetHistory();
		history.setOperatorName("采购商:"+user.getName());
		history.setEndorsesheetNo(orderNO);
		history.setPreviousOperation("采购商申请改签下单成功");
		history.setOperationTime(new Date());
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/endorsesheetDetail/list1";
	}
	
	//用户点击修改过来的 
	@RequestMapping("toEndorsesheetDetail")
	public String toEndorsesheetDetail(String endorseIds,String orderNo,HttpServletRequest request,HttpServletResponse response ,Model model){
		//获取原订单信息 取出pnr号
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		
		//原始旅客信息
		List<TicketorderPassenger> tPassengerList=ticketorderPassengerFacade.findPassengerList(endorseIds, "");

		// 原始航班信息
		List<TicketorderSegment> tSegmentList = ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);

		//获取原始订单价格信息
		List<TicketorderSinglePrice> singlePriceList=ticketorderSinglePriceServiceFacade.findorderSinglePrice(orderNo);
		//收件人的信息
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(orderNo);
		//获取行程单信息
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(orderNo);
		//获取原始的订单的状态
		TicketorderDetail detailInfo = ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
		// 改程订单06
		//String orderNum = MakeOrderNum.makeOrderNum("01", "03");
		Map map = new HashMap();
		// 改签单的原始订单号
		map.put("orderNo", orderNo);
		//当前申请人
		User user = UserUtils.getUser();
		//map.put("orderNum", orderNum);
		model.addAttribute("invoiceList",invoiceList);
		model.addAttribute("user", user);
		model.addAttribute("detail",detail);
		model.addAttribute("detailInfo",detailInfo);
		model.addAttribute("orderMap", map);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("recipientList",recipientList);
		model.addAttribute("endorseIds",endorseIds);
		return "sz/order/endorsesheetdetail";
	}
	
	@RequestMapping("saveEndorsesheetDetail")
	public String saveEndorsesheetDetail(String endorseIds,TicketorderSegment segment,EndorsesheetPassengerModel  endorsesheetPassengers,EndorsesheetDetail endorsesheetDetail,HttpServletRequest request,HttpServletResponse response){
		//修改原始旅客信息，主单该旅客将不能重新申请
		List<TicketorderPassenger> tPassengerList=ticketorderPassengerFacade.findPassengerList(endorseIds, "1");

		// 改签保存
		String orderNO="";
		MakeOrderNum make=new MakeOrderNum();
		//证明选择过来的是改期单
		if(null != endorsesheetDetail.getEndorseDetailType() && "1".equals(endorsesheetDetail.getEndorseDetailType())){
			// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
			// 改程订单06
			orderNO = make.makeOrderNum("01", "04");
		}
		//证明选择过来的是改程单
		else if(null != endorsesheetDetail.getEndorseDetailType()  && "2".equals(endorsesheetDetail.getEndorseDetailType())){
			orderNO = make.makeOrderNum("01", "05");
		}
		//选择过来的是升舱单
		else if(null != endorsesheetDetail.getEndorseDetailType()  && "3".equals(endorsesheetDetail.getEndorseDetailType())){
			orderNO = make.makeOrderNum("01", "06");
		}
		// 改签时将原来的航程信息重新录入库内
		if (null != segment.getSegId()) {
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByIdList(segment.getSegId());
			for (TicketorderSegment ticketorderSegment : segmentList) {
				ticketorderSegment.setOrderNo(orderNO);
				ticketorderSegmentServiceFacade.saveSegmentObj(ticketorderSegment);
			}
			
			ticketorderSegmentServiceFacade.findRefundSheetSeg(orderNO, segment);
		}
		User user = UserUtils.getUser();
		//存放采购商id
		endorsesheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		endorsesheetDetail.setRelevantClient(user.getCompany().getName());
		//存放预定人的名字
		endorsesheetDetail.setCreateAccount(user.getName());
		//供应商的名字SUPPLIER_NAME
		//获取供应商的id
		String supplier_product_no=request.getParameter("supplier_product_no");
		endorsesheetDetail.setSupplierProductNo(supplier_product_no);
		String supplier_product_name=request.getParameter("supplier_product_name");
		endorsesheetDetail.setSupplierProductName(supplier_product_name);
		//旧的pnr oldpnr
		String old_crs_pnr=request.getParameter("old_crs_pnr");
		endorsesheetDetail.setOriCrsPnr(old_crs_pnr);
		//新的pnr
		String new_crs_pnr=request.getParameter("new_crs_pnr");
		endorsesheetDetail.setNewCrsPnr(new_crs_pnr);
		
		//供应商的idSUPPLIER_PRODUCT_NO
		//获取改签的描述
		String remark=request.getParameter("text_remark");
		endorsesheetDetail.setRemark(remark);
		//改签以后的订单号
		endorsesheetDetail.setEndorsesheetNo(orderNO);
		//下单成功状态
		endorsesheetDetail.setEndorseStatus("1");
		//支付状态 1未支付状态
		endorsesheetDetail.setPayStatus("1");
		//设置操作状态为
		endorsesheetDetail.setCurrentStatus("1");
		endorsesheetDetail.setCreateTime(new Date());
		//TicketorderDetail ticketorderDetail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(endorsesheetDetail.getOrderNo());
		//是否有消费凭证
		//endorsesheetDetail.setInvoice(ticketorderDetail.getInvoice());
		endorsesheetDetailServiceFacade.save(endorsesheetDetail);
		//航段信息入库
		if(null != segment.getSegId() && !"".equals(segment.getSegId())){
			String[] segIdArr=segment.getSegId().split(",");
			for (String string : segIdArr) {
				TicketorderSegment orderSegment=ticketorderSegmentServiceFacade.get(string.trim());
				orderSegment.setOrderNo(orderNO);
				//保存入库
				ticketorderSegmentServiceFacade.save(orderSegment);
			}
		}
		
		//旅客信息入库
		if(null != endorsesheetPassengers.getEndorsesheetPassengers() ){
			endorsesheetPassengerServiceFacade.saveEndorsePassenger(endorsesheetPassengers,orderNO);
		}
		//TODO
		//将原旅客信息单中
		//添加历史记录
		EndorsesheetHistory history=new EndorsesheetHistory();
		//操作时间
		history.setOperationTime(new Date());
		//操作人当前登录人
		history.setOperatorName("采购商:"+user.getName());
		history.setEndorsesheetNo(endorsesheetDetail.getEndorsesheetNo());
		//操作记录
		history.setPreviousOperation("申请改签");
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
		history.setRemark(message);
		}
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/endorsesheetDetail/list1";
	}
	
	
	
	
	@RequestMapping(value="toPay")
	public String toPay( Model model, HttpServletRequest request) throws Exception{
		User user = UserUtils.getUser();
		System.out.println(user.getCompany().getId());
		String p1_MerchantNo = "888000000000000";
		String p2_OrderNo = new String(request.getParameter("p2_OrderNo").getBytes("ISO-8859-1"), "UTF-8");
		String p3_Amount = new String(request.getParameter("p3_Amount").getBytes("ISO-8859-1"), "UTF-8");
		String p4_Cur = new String(request.getParameter("p4_Cur").getBytes("ISO-8859-1"), "UTF-8");
		String p5_ProductName = request.getParameter("p5_ProductName");
		String p6_Mp = request.getParameter("p6_Mp");
		String p7_ReturnUrl = new String(request.getParameter("p7_ReturnUrl").getBytes("ISO-8859-1"), "UTF-8");
		String p8_NotifyUrl = new String(request.getParameter("p8_NotifyUrl").getBytes("ISO-8859-1"), "UTF-8");
		String p9_FrpCode = new String(request.getParameter("p9_FrpCode").getBytes("ISO-8859-1"), "UTF-8");
		String pa_OrderPeriod ="0";
		String pb_PayerLoginName ="";
		String pc_ProductDesc="订单备注";//订单备注
		String key = "1234qwer";

		//把请求参数打包成数组
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1_MerchantNo", p1_MerchantNo);
		map.put("p2_OrderNo", p2_OrderNo);
		map.put("p3_Amount", p3_Amount);
		map.put("p4_Cur", p4_Cur);
		map.put("p5_ProductName", p5_ProductName);
		map.put("p6_Mp", p6_Mp);
		map.put("p7_ReturnUrl", p7_ReturnUrl);
		map.put("p8_NotifyUrl", p8_NotifyUrl);
		map.put("p9_FrpCode", p9_FrpCode);
		map.put("pa_OrderPeriod", pa_OrderPeriod);
		map.put("pb_PayerLoginName", pb_PayerLoginName);
		map.put("pc_ProductDesc", pc_ProductDesc);
		
		String pay_url = "http://210.51.190.223:9083/pay-web-gateway/gateway_init.action";
		map.put("pay_url", pay_url);

		// 创建支付请求html  
		String html = new Pay(p1_MerchantNo, key).buildPayUrlPost(map);
		model.addAttribute("html",html);
		return "sz/order/html";
	}
	
	
	@RequestMapping(value="pay")
	public String pay(HttpServletRequest request){
		//添加历史记录
		EndorsesheetHistory history=new EndorsesheetHistory();
		//操作时间
		history.setOperationTime(new Date());
		
		
		String r1_MerchantNo=request.getParameter("r1_MerchantNo");
		String r2_OrderNo=request.getParameter("r2_OrderNo");//订单id
		String r3_Amount="3";//request.getParameter("r3_Amount");
		String r4_Cur=request.getParameter("r4_Cur");
		String r5_Mp=request.getParameter("r5_Mp");
		String r6_Status=request.getParameter("r6_Status");//状态 100成功 101失败
		String r7_TrxNo=request.getParameter("r7_TrxNo");//流水号
		String r8_BankOrderNo=request.getParameter("r8_BankOrderNo");
		String r9_BankTrxNo=request.getParameter("r9_BankTrxNo");
		String ra_PayTime=request.getParameter("ra_PayTime");
		String rb_DealTime=request.getParameter("rb_DealTime");
		String rc_BankCode=request.getParameter("rc_BankCode");
		String hmac=request.getParameter("hmac");
		
		User user = UserUtils.getUser();
		//100状态就是支付成功  
		if("100".equals(r6_Status)){
			//修改支付状态
			endorsesheetDetailServiceFacade.updatePayStatus("0", r2_OrderNo);
			//记录流水单号
			endorsesheetDetailServiceFacade.updateRelevantOrderNo(r7_TrxNo, r2_OrderNo);
			
			//更改操作状态为等待出票状态
			endorsesheetDetailServiceFacade.updateCurrnetStatus("5", r2_OrderNo);
			String tk_message=request.getParameter("tk_message");
			if(StringUtils.isNotBlank(tk_message)){
				TicketorderMessage ticketorderMessage=new TicketorderMessage();
				ticketorderMessage.setMessage(tk_message);
				ticketorderMessage.setOrder_no(r2_OrderNo);
				ticketorderMessage.setCreateUser(user.getLoginName());
				ticketorderMessageServiceFacade.save(ticketorderMessage);
				
				history.setPreviousOperation("采购商支付成功");
			}
		}
		//支付失败
		else if("101".equals(r6_Status)){
			//修改支付状态 状态是失败状态
			ticketorderDetailServiceFacade.updatePayStatus("2", r2_OrderNo);
			history.setPreviousOperation("采购商支付失败");
		}
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//操作人当前登录人
		history.setOperatorName("采购商:"+user.getName());
		history.setEndorsesheetNo(r2_OrderNo);
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/list";
	}
	
	
	//@RequiresPermissions("order:endorsesheetDetail:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(String flag,EndorsesheetDetail endorsesheetDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		System.out.println(user);
		//采购商的id
		endorsesheetDetail.setReceptionCustomerId(user.getCompany().getId());
		Page<EndorsesheetDetail> page = endorsesheetDetailServiceFacade.findPage(new Page<EndorsesheetDetail>(request, response), endorsesheetDetail); 
		page.setOld(false);
		
		//改期的状态以及条数
		Map<String,String> statusCount = endorsesheetDetailServiceFacade.findEndorseDetailStatusCount(endorsesheetDetail);
		//获取支付状态条数
		Map<String,String> payStatusCountList=endorsesheetDetailServiceFacade.findPayStatusCount(endorsesheetDetail);
		
		Map<String,String> currentStatusList=endorsesheetDetailServiceFacade.findCurrentStatusCount(endorsesheetDetail);
		model.addAttribute("payStatusCountList", payStatusCountList);
		model.addAttribute("currentStatus",currentStatusList);
		model.addAttribute("statusCount", statusCount);
		model.addAttribute("page", page);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage ="sz/order/endorsesheetDetailListPage";
		}else{
			returnPage="sz/order/endorsesheetDetailList";
		}
		return returnPage;
	}
	
	@RequestMapping("editCurrentStatus")
	public String editCurrentStatus(HttpServletRequest request,HttpServletResponse response){
		//支付
		String  hid_payStatus=request.getParameter("hid_payStatus");
		//取消
		String hid_endorse_status=request.getParameter("hid_endorse_status");
		//重新申请
		String hid_currentStatus=request.getParameter("hid_currentStatus");
		String hid_endorseNo=request.getParameter("hid_endorseNo");
		//添加历史记录
		EndorsesheetHistory history=new EndorsesheetHistory();
		//操作时间
		history.setOperationTime(new Date());
		//点击支付过来的
		if(null != hid_payStatus && "0".equals(hid_payStatus)){
			//修改支付状态
			endorsesheetDetailServiceFacade.updatePayStatus(hid_payStatus, hid_endorseNo);
			//修改订单完成的状态
			endorsesheetDetailServiceFacade.updateCurrnetStatus("5", hid_endorseNo);
			history.setPreviousOperation("采购商支付改签订单");
		}
		
		//点击取消过来的
		if(null != hid_endorse_status && "2".equals(hid_endorse_status)){
			endorsesheetDetailServiceFacade.updateEndorseStatus(hid_endorse_status, hid_endorseNo);
			history.setPreviousOperation("采购商取消改签订单");
			//取消后原单可重新申请
			List<EndorsesheetPassenger> findEndorsePassenger = endorsesheetPassengerServiceFacade.findEndorsePassenger(hid_endorseNo);
			for (EndorsesheetPassenger endorsesheetPassenger : findEndorsePassenger) {
					ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",endorsesheetPassenger.getOrderPassengerId());
			}
		}
		//点击重新申请
		if(null != hid_currentStatus && "1".equals(hid_currentStatus)){
			endorsesheetDetailServiceFacade.updateCurrnetStatus(hid_currentStatus, hid_endorseNo);
			history.setPreviousOperation("采购商重新申请改签订单");
		}
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		//操作人当前登录人
		history.setOperatorName("采购商:"+UserUtils.getUser().getName());
		history.setEndorsesheetNo(hid_endorseNo);
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/endorsesheetDetail/list1";
	}


	@RequestMapping(value="cancel")
	public String cancel(String endorsesheetNo,HttpServletRequest request,HttpServletResponse response){
		//改签单状态(1, "已提交"), refust(2, "已拒单"), apply(3, "已申签"), audit(4, "已核单"), endorse(5, "已改签"), sign(6, "已签单"), delete(7, "已删除")
		endorsesheetDetailServiceFacade.updateEndorseStatus("7", endorsesheetNo);
		//历史纪录
		EndorsesheetHistory history=new EndorsesheetHistory();
		history.setOperationTime(new Date());
		//添加历史纪录
		history.setPreviousOperation("取消改签");
		//操作人当前登录人
		history.setOperatorName("采购商:"+UserUtils.getUser().getName());
		history.setEndorsesheetNo(endorsesheetNo);
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/endorsesheetDetail/list";
	}
	
	/**
	 * 查询单个订单信息
	 * @return
	 */
	@RequestMapping(value="findEndorsesheetDetail")
	public String findEndorsesheetDetail(String endorsesheetNo,HttpServletRequest request,HttpServletResponse response,Model model){
		//获取订单信息
		EndorsesheetDetail detail=endorsesheetDetailServiceFacade.findEndorsesheetDetail(endorsesheetNo);
		//获取原始订单的价格
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
		String returnPage="sz/order/endorsesheetDetailInfo";
		if("4".equals(detail.getCurrentStatus())){
			model.addAttribute("detail", ticketorderDetailServiceFacade.findorderDetailByOrderNo(detail.getOrderNo()));
			returnPage="sz/order/endorsesheetdetail";
		}
		return returnPage;
	}
}