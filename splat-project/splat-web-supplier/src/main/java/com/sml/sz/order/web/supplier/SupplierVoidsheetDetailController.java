
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
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.VoidsheetDetail;
import com.sml.sz.order.entity.VoidsheetHistory;
import com.sml.sz.order.entity.VoidsheetPassenger;
import com.sml.sz.order.entity.model.VoidsheetPassengerModel;
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
@RequestMapping(value = "${adminPath}/order/supplier/voidsheetDetail")
public class SupplierVoidsheetDetailController extends BaseController {

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

	//	改签单的旅客信息
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	//	每个人一条记录单人价格表
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;

	//机票信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;

	//行程单
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	//收件人
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	//留言
	@Autowired
	private TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	
	@Autowired
	private TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	//@RequiresPermissions("order:voidsheetDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(String flag,VoidsheetDetail voidsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		System.out.println(user.getCompany().getId());
		// 供应商的id
		voidsheetDetail.setSupplierProductNo(user.getCompany().getId());
		
		Page<VoidsheetDetail> page = voidsheetDetailServiceFacade.findPage(new Page<VoidsheetDetail>(request, response),voidsheetDetail);
		page.setOld(false);
		
		Map<String,String> voidsheetstatusCount=voidsheetDetailServiceFacade.findVoidsheetStatusCount( voidsheetDetail);
		//获取支付状态条数
		Map<String,String> payStatusCount=voidsheetDetailServiceFacade.findPayStatusCount(voidsheetDetail);
		//获取操作状态
		Map<String,String> currentStatus=voidsheetDetailServiceFacade.findCurrentStatus(voidsheetDetail);
		model.addAttribute("page", page);
		model.addAttribute("statusCount", voidsheetstatusCount);
		model.addAttribute("payStatusCount",payStatusCount);
		model.addAttribute("currentStatus", currentStatus);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="cgs/order/supplier/voidsheetDetailListPage";
		}else{
			returnPage="cgs/order/supplier/orderVoidsheetDetailList";
		}
		return returnPage;
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
		//行程单收件人
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		//收件人
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		model.addAttribute("detail",detail);
		model.addAttribute("tSegmentList",segment );
		model.addAttribute("tPassengerList",passenger);
		model.addAttribute("historyList", history);
		model.addAttribute("singlePriceList", singlePrice);
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("invoiceList", invoiceList);
		return "cgs/order/supplier/supplierVoidsheetDetail";
	}
	
	/**
	 * 修改废票的状态
	 * @return
	 */
	@RequestMapping(value="editsupplierVoidsheetDetail")
	public String editsupplierVoidsheetDetail(VoidsheetPassengerModel voidsheetPassengers,String voidsheetNo,String curretnStatus,HttpServletRequest request,HttpServletResponse response){
		//记录历史
		VoidsheetHistory history=new VoidsheetHistory();
		//获取页面的状态
		String hid_currentStatus=request.getParameter("hid_currentStatus");
		VoidsheetDetail voidsheetDetail=voidsheetDetailServiceFacade.findVoidsheetDetail(voidsheetNo);
		if(null != hid_currentStatus && "5".equals(hid_currentStatus)){
			
			TicketorderDetail ticketorderDetail2=ticketorderDetailServiceFacade.findorderDetailByOrderNo(voidsheetDetail.getOrderNo());
			if(null != ticketorderDetail2.getRelationOrderNo() && !"".equals(ticketorderDetail2.getRelationOrderNo())){
				SimpleHttpUtils http=new SimpleHttpUtils();
				Map<String,Object> parameters = new LinkedHashMap<String,Object>();
				//baoshengb2b@sina.com  
				parameters.put("accountType", "2");//账户类型 1-对公，2-对私
				parameters.put("userNo", "888000000000000");
				parameters.put("orderNo",ticketorderDetail2.getOrderNo());//订单号
				parameters.put("trxNo", ticketorderDetail2);//流水号
				parameters.put("amount", voidsheetDetail.getSupplierTotSettlementPrice());//退款多少
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
					voidsheetDetailServiceFacade.updatePayStatus("0",voidsheetNo);
				}else if(re.getContent().indexOf("error") >1){
					//设置支付状态为2 失败
					voidsheetDetailServiceFacade.updatePayStatus("2",voidsheetNo);
				}
			}
			//TODO
			//供应商支付废票款给采购商
			
			history.setPreviousOperation("供应商确认废票");
		}
		//拒绝还是同意改签废票单状态(1, "已提交"), refust(2, "已拒单"), audit(3, "已核单"), voidsheet(4, "已废票"), delete(5, "已删除")
		voidsheetDetailServiceFacade.updateCurrentStatus(hid_currentStatus, voidsheetNo);
		//修改价格
		if(null != voidsheetPassengers.getVoidsheetPassengers()  && !"5".equalsIgnoreCase(hid_currentStatus) && !"3".equalsIgnoreCase(hid_currentStatus)){
			  voidsheetPassengerServiceFacade.updatevoidPassengerPrice(voidsheetPassengers);
		}
		
		String supplier_tot_settlement_price=request.getParameter("supplier_tot_settlement_price");
		//修改订单的价格
		if(null != supplier_tot_settlement_price && !"3".equalsIgnoreCase(hid_currentStatus) && !"5".equalsIgnoreCase(hid_currentStatus)){
			voidsheetDetailServiceFacade.updateSupplierTotSettlementPrice(supplier_tot_settlement_price, voidsheetNo);
		}
		User user=UserUtils.getUser();
		//记录历史
		history.setOperationTime(new Date());
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		history.setOperatorName("供应商:"+user.getName());
		if("3".equals(hid_currentStatus)){
			//拒绝废票  将原来的状态取消掉 
			List<VoidsheetPassenger> passengers=voidsheetPassengerServiceFacade.findvoidPassenger(voidsheetNo);
			for (VoidsheetPassenger voidsheetPassenger : passengers) {
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("", voidsheetPassenger.getOrderPassengerId());
			}
			history.setPreviousOperation("供应商拒绝废票");
		}
		
		if("4".equals(hid_currentStatus))
			history.setPreviousOperation("供应商同意废票");
		history.setVoidsheetNo(voidsheetNo);
		voidsheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/supplier/voidsheetDetail/findVoidsheetDetail?voidsheetNo="+voidsheetNo;
	}
	
	
	
}