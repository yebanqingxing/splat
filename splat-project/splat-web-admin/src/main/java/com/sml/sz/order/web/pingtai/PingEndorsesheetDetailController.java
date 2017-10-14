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
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.DetailStatusCount;
import com.sml.sz.order.entity.EndorsesheetDetail;
import com.sml.sz.order.entity.EndorsesheetHistory;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.PayStatusCount;
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
import com.sml.sz.sys.utils.UserUtils;

/**
 * 改签单Controller
 * @author 李千超
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/order/ping/endorsesheetDetail")
public class PingEndorsesheetDetailController extends BaseController {

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
	
	//行程单
	@Autowired
	private InvoiceServiceFacade invoiceServiceFacade;
	
	@Autowired
	private RecipientServiceFacade recipientServiceFacade;
	//机票信息
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	//@RequiresPermissions("order:endorsesheetDetail:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(String flag,EndorsesheetDetail endorsesheetDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EndorsesheetDetail> page = endorsesheetDetailServiceFacade.findPage(new Page<EndorsesheetDetail>(request, response), endorsesheetDetail); 
		page.setOld(false);
		List<EndorsesheetDetail> detail=page.getList();
		for(EndorsesheetDetail detailInfo : detail) {
			
			//订单的旅客 信息
			StringBuffer passengers = new StringBuffer();
			List<EndorsesheetPassenger> passengerList=endorsesheetPassengerServiceFacade.findEndorsePassenger(detailInfo.getEndorsesheetNo());
			//判断集合不是空取值第一个值
			if(null != passengerList && passengerList.size()>0)
				detailInfo.setPassengersTemp(passengerList.get(0).getPassengerName());
			//循环将所有的旅客用逗号累进行隔开
			for (EndorsesheetPassenger passengerListInfo : passengerList) {
				passengers.append("," + passengerListInfo.getPassengerName());
			}
			if(null != passengers && passengers.length()>0)
			detailInfo.setPassengers(passengers.toString().substring(1));
			//取出所有的航段
			List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(detailInfo.getEndorsesheetNo());
			if(null != segmentList && segmentList.size()>0)
				//起飞地三字码 - 目的地三字码
				detailInfo.setSegmentsTemp(segmentList.get(0).getdepartureAddress()+"-"+segmentList.get(0).getArriveAddress());
			StringBuffer sbseg=new StringBuffer();
			//循环取值
			for (TicketorderSegment ticketorderSegment : segmentList) {
				sbseg.append(","+ticketorderSegment.getdepartureAddress()+"-"+ticketorderSegment.getArriveAddress());
			}
			//判断有航段
			if(null != sbseg && sbseg.length() >0)
				detailInfo.setSegments(sbseg.toString().substring(1));
			//取出所有的票号
			StringBuffer sb=new StringBuffer();
			List<TicketorderTicket> ticketList=ticketorderTicketServiceFacade.findTicketList(detailInfo.getOrderNo());
			//取出第一个票号页面展示剩下的是鼠标移上去显示
			if(null != ticketList && ticketList.size() >0)
				detailInfo.setTicketNoTemp(ticketList.get(0).getTicketNo());
			//循环遍历得到所需要的
			for (TicketorderTicket ticketorderTicket : ticketList) {
				sb.append(","+ticketorderTicket.getTicketNo());
			}
			//判断票号是否有没有取值
			if(null != sb  && sb.length() > 0){
				//将票号赋值进去
				detailInfo.setTicketNo(sb.toString().substring(1));
			}
		}
		page.setList(detail);
		//改期的状态以及条数
		Map<String,String> statusCount = endorsesheetDetailServiceFacade.findEndorseDetailStatusCount(endorsesheetDetail);
		
		Map<String,String> payStatusCountList=endorsesheetDetailServiceFacade.findPayStatusCount(endorsesheetDetail);
		Map<String,String> currentStatusList=endorsesheetDetailServiceFacade.findCurrentStatusCount(endorsesheetDetail);
		model.addAttribute("payStatusCountList", payStatusCountList);
		model.addAttribute("currentStatusList",currentStatusList );
		model.addAttribute("statusCount", statusCount);
		model.addAttribute("page", page);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage ="sz/order/pingtai/endorsesheetDetailListPage";
		}else{
			returnPage="sz/order/pingtai/endorsesheetDetailList";
		}
		return returnPage;
	}
	
	
	@RequestMapping(value="findpingEndorsesheetDetail")
	public String findpingEndorsesheetDetail(String endorsesheetNo,Model model ,HttpServletRequest request ,HttpServletResponse response ){
		//获取订单信息
		EndorsesheetDetail detail=endorsesheetDetailServiceFacade.findEndorsesheetDetail(endorsesheetNo);
		
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
		return "sz/order/pingtai/pingEndorsesheetDetail";
	}
	

	
	@RequestMapping(value="editendorsesheetDetailStatus")
	public String editendorsesheetDetailStatus(EndorsesheetPassengerModel endorsesheetPassengers,HttpServletResponse response,HttpServletRequest request){
		//获取操作状态
		String  hid_currentStatus=request.getParameter("hid_currentStatus");
		//获取订单号
		String hid_endorseNo=request.getParameter("hid_endorseNo");
		
		//修改状态改签单状态操作状态（流转状态） 1审核等待 2 审核通过 3 审核拒绝 4 平台审核通过 5 改签完成
		endorsesheetDetailServiceFacade.updateCurrnetStatus(hid_currentStatus, hid_endorseNo);
		//修改旅客的金额
		endorsesheetPassengerServiceFacade.updateEndorsePassengerPrice(endorsesheetPassengers); 
				
		//将记录添加到数据库
		EndorsesheetHistory history=new EndorsesheetHistory();
		history.setOperatorName("平台:"+UserUtils.getUser().getName());
		//操作时间
		history.setOperationTime(new Date());
		if("3".equals(hid_currentStatus))
			history.setPreviousOperation("平台拒绝改签");
		//取消后原单可重新申请
		List<EndorsesheetPassenger> findEndorsePassenger = endorsesheetPassengerServiceFacade.findEndorsePassenger(hid_endorseNo);
		for (EndorsesheetPassenger endorsesheetPassenger : findEndorsePassenger) {
				ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",endorsesheetPassenger.getOrderPassengerId());
		}
		if("2".equals(hid_currentStatus))
			history.setPreviousOperation("平台同意改签");
		history.setEndorsesheetNo(hid_currentStatus);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
		}
		endorsesheetHistoryServiceFacade.save(history);
		return "redirect:"+Global.getAdminPath()+"/order/ping/endorsesheetDetail/list1";
	}
}