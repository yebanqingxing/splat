/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.web;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jms.MessageNotWriteableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sml.sz.EhCacheUtils;
import com.sml.sz.MakeOrderNum;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.OrderStatusCount;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.SinglePrice;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderHistory;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPassenger;
import com.sml.sz.order.entity.TicketorderPnr;
import com.sml.sz.order.entity.TicketorderSegment;
import com.sml.sz.order.entity.TicketorderSinglePrice;
import com.sml.sz.order.entity.TicketorderTicket;
import com.sml.sz.order.entity.model.InvoiceModel;
import com.sml.sz.order.entity.model.RecipientModel;
import com.sml.sz.order.entity.model.TicketorderPassengerModel;
import com.sml.sz.order.entity.model.TicketorderSegmentModel;
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
import com.sml.sz.pay.util.Pay;
import com.sml.sz.policy.entity.PolicyRules;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.pnr.Passenger;
import com.sml.sz.sys.pnr.PersonTypeInfo;
import com.sml.sz.sys.pnr.Qte;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 订单生成模块Controller
 * @author 李千超
 * @version 2016-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/order/tbTicketorderDetail")
public class TicketorderDetailController extends BaseController {

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
	
	//机票明细
	@Autowired
	TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	@Autowired
	TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	
	//收件人
	@Autowired
	RecipientServiceFacade recipientServiceFacade;
	
	@Autowired
	TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	@ModelAttribute
	public TicketorderDetail get(@RequestParam(required=false) String id) {
		TicketorderDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity =  ticketorderDetailServiceFacade.get(id);
		}
		if (entity == null){
			entity = new TicketorderDetail();
		}
		return entity;
	}
	@RequestMapping(value="affirmOrderDetail")
	public String affirmOrderDetail(){
		
		return "sz/order/affirmOrderDetail";
	}
	
	
	
	//张权传过来的pnr信息接收方法前台展示(之后才是生成订单)
	@RequestMapping(value="toProductAdd")
	public String toProductAdd(HttpServletRequest request,Model model){
		//供应商id
		String suppliId=request.getParameter("suppliId");
		Rt rt=(Rt) EhCacheUtils.get("www");
		//String mapJson=request.getParameter("dataJson");
		//json转换java对象 fastjson
		//JSONObject jsonobject = JSONObject.parseObject(mapJson);
	//	Rt rt=JSONObject.toJavaObject(jsonobject, Rt.class);
		String returnPage = "sz/order/product_add";
		if(null != rt){
		//先非空判断
		if(StringUtils.isNotBlank(rt.getPnr())){
			rt.setPnr(rt.getPnr().trim());
		}
		List<PolicyRules> polices = rt.getPolices();
		PolicyRules policyRoules=new PolicyRules();
		if(null != polices){
			for (int i = 0; i < polices.size(); i++) {
				PolicyRules policyRulesInfo=polices.get(i);
				//?
				if(suppliId.equals(policyRulesInfo.getSupplierId() )){
					policyRoules = policyRulesInfo;
				}
			}
		}
		
		
		//成人的单人价格
		PersonTypeInfo manPersontype=new PersonTypeInfo();
		//儿童的单人价格 
		PersonTypeInfo chPersonTypeInfo=new PersonTypeInfo();
		//婴儿的单人价格
		PersonTypeInfo inPersonTypeInfo=new PersonTypeInfo();
		List<PersonTypeInfo> personTypeInfoList = policyRoules.getPersonList();
		for (PersonTypeInfo personTypeInfo : personTypeInfoList) {
			if(null != personTypeInfo.getPersonType() && "MAN".equals(personTypeInfo.getPersonType()) ){
				//成人的单人价格
				manPersontype = personTypeInfo;
			}else if(null != personTypeInfo.getPersonType() && "CH".equals(personTypeInfo.getPersonType())){
				//儿童的价格
				chPersonTypeInfo = personTypeInfo;
			}else if(null != personTypeInfo.getPersonType() && "IN".equals(personTypeInfo.getPersonType())){
				inPersonTypeInfo =  personTypeInfo;
			}
		}
		
		List<TicketorderSinglePrice> singlePriceList=new ArrayList<TicketorderSinglePrice>();
		
		//旅客信息
		List<TicketorderPassenger> passengerList=new ArrayList<TicketorderPassenger>();
		List<Passenger> passenger_list = rt.getPassenger_list();//旅客
		
		for (Passenger passenger2 : passenger_list) {
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			TicketorderPassenger TicketorderPassenger=new TicketorderPassenger();
			//赋值旅客名
			TicketorderPassenger.setPassengerName(passenger2.getName());
			// f女  m是男
			if(null != passenger2.getSex()){
			if("F".equals(passenger2.getSex().toUpperCase())){
			//标志是女
				TicketorderPassenger.setGender("2");
			}
			//标志是女
			else if("M".equals(passenger2.getSex().toUpperCase())){
				TicketorderPassenger.setGender("1");
			}
			}
			if(null != passenger2.getValidData()){
			//证件有效期expiredtime
		
			try {
				TicketorderPassenger.setExpiredtime(sim.parse(passenger2.getValidData()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			}
			//签发国certificate_country
			TicketorderPassenger.setCertificateCountry(passenger2.getPassportCou());
			//国籍birCountry
			TicketorderPassenger.setNational(passenger2.getBirCountry());
			//旅客类型passType
			TicketorderPassenger.setPassengerType(passenger2.getPassType());
			//成人价格
			if(null != passenger2.getPassType() && "0".equals(passenger2.getPassType())){
				//查找成人旅客价格
				TicketorderSinglePrice singlePrice=new TicketorderSinglePrice(); 
				//旅客名
				singlePrice.setPassengerName(passenger2.getName());
				//旅客类型  旅客类型（0成人，1儿童，4婴儿）
				singlePrice.setPassengerType("0");
				//票面价销售价
				singlePrice.setDistributorTicketPrice(manPersontype.getPrice()+"");
				//记奖价
				if(null != inPersonTypeInfo.getIsPrice() && !"".equals(inPersonTypeInfo.getIsPrice())){
					DecimalFormat format=new DecimalFormat("0");
					String price=format.format(inPersonTypeInfo.getIsPrice());
					singlePrice.setSupplierTicketPrice(Double.valueOf(price));
				}
				
				//代理费
				singlePrice.setDistributorCommission(manPersontype.getAgenCy());
				//政策返点
				singlePrice.setSupplierCommission(manPersontype.getRebate());
				//供应商开票费用
				singlePrice.setDistributorSettlementPrice(manPersontype.getBillingFee());
				//税费
				singlePrice.setTaxCn(manPersontype.getTax());
				//单张结算价
				singlePrice.setSupplierSettlementPrice(manPersontype.getFlyPrice());
				singlePriceList.add(singlePrice);
			}
			//儿童
			else if(null != passenger2.getPassType() && "1".equals(passenger2.getPassType())){
				//查找儿童旅客价格
				TicketorderSinglePrice singlePrice=new TicketorderSinglePrice(); 
				//旅客名
				singlePrice.setPassengerName(passenger2.getName());
				//旅客类型  旅客类型（0成人，1儿童，4婴儿）
				singlePrice.setPassengerType("1");
				//票面价销售价
				singlePrice.setDistributorTicketPrice(chPersonTypeInfo.getPrice()+"");
				//记奖价
				if(null != inPersonTypeInfo.getIsPrice() && !"".equals(inPersonTypeInfo.getIsPrice())){
					DecimalFormat format=new DecimalFormat("0");
					String price=format.format(inPersonTypeInfo.getIsPrice());
					singlePrice.setSupplierTicketPrice(Double.valueOf(price));
				}
				//代理费
				singlePrice.setDistributorCommission(chPersonTypeInfo.getAgenCy());
				//政策返点
				singlePrice.setSupplierCommission(chPersonTypeInfo.getRebate());
				//供应商开票费用
				singlePrice.setDistributorSettlementPrice(chPersonTypeInfo.getBillingFee());
				//税费
				singlePrice.setTaxCn(chPersonTypeInfo.getTax());
				//单张结算价
				singlePrice.setSupplierSettlementPrice(chPersonTypeInfo.getFlyPrice());
				singlePriceList.add(singlePrice);
				
			}//婴儿 
			else if(null != passenger2.getPassType() && "4".equals(passenger2.getPassType())){
				//查找婴儿旅客价格
				TicketorderSinglePrice singlePrice=new TicketorderSinglePrice(); 
				//旅客名
				singlePrice.setPassengerName(passenger2.getName());
				//旅客类型  旅客类型（0成人，1儿童，4婴儿）
				singlePrice.setPassengerType("4");
				//票面价销售价
				singlePrice.setDistributorTicketPrice(inPersonTypeInfo.getPrice()+"");
				//记奖价
				if(null != inPersonTypeInfo.getIsPrice() && !"".equals(inPersonTypeInfo.getIsPrice())){
					DecimalFormat format=new DecimalFormat("0");
					String price=format.format(inPersonTypeInfo.getIsPrice());
					singlePrice.setSupplierTicketPrice(Double.valueOf(price));
				}
				
				//代理费
				singlePrice.setDistributorCommission(inPersonTypeInfo.getAgenCy());
				//政策返点
				singlePrice.setSupplierCommission(inPersonTypeInfo.getRebate());
				//供应商开票费用
				singlePrice.setDistributorSettlementPrice(inPersonTypeInfo.getBillingFee());
				//税费
				singlePrice.setTaxCn(inPersonTypeInfo.getTax());
				//单张结算价
				singlePrice.setSupplierSettlementPrice(inPersonTypeInfo.getFlyPrice());
				singlePriceList.add(singlePrice);
				
			}
			//证件类型
			
			if(null != passenger2.getBirth() && !"".equals(passenger2.getBirth()))
				try {
					TicketorderPassenger.setPassengerBirthday(sim.parse(passenger2.getBirth()));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			//旅客身份passIdentity
			TicketorderPassenger.setPassengerTitle(passenger2.getPassIdentity());
			//证件号identity
			TicketorderPassenger.setCertNo(passenger2.getIdentity());
			passengerList.add(TicketorderPassenger);
		
		}
		
		
		List<TicketorderSegment> ticketorderSegments=new ArrayList<TicketorderSegment>();
		List<Route> route_list = rt.getRoute_list();//航段信息
		List<String> flyCompany = rt.getFlyCompany();//航空公司
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < route_list.size(); i++) {
			Route	route=route_list.get(i);
			//航段信息
			TicketorderSegment segmentInfo=new TicketorderSegment();
			//承运方航司
			segmentInfo.setOperatingAirline(flyCompany.get(i));
			//航班号
			segmentInfo.setOperatingFlightNo(route.getFlight());
			//起飞地机场三字码
			segmentInfo.setDepartureCode(route.getFromAirport());
			//起飞机场
			segmentInfo.setdepartureAddress(route.getFromCity());
			//到达机场三字码
			segmentInfo.setArriveCode(route.getToAirport());
			//到达机场
			segmentInfo.setArriveAddress(route.getTocity());
			//飞行时长
			segmentInfo.setDuration(route.getFlyTime());
			//起飞时间
			if(StringUtils.isNotEmpty(route.getFromTime()) ){
				try {
					segmentInfo.setDepartureTime(sim.parse(route.getFromTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				}
			//到达时间
			if(StringUtils.isNotEmpty(route.getToTime())){
				try {
					segmentInfo.setArriveTime(sim.parse(route.getToTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			//舱位
			segmentInfo.setClassCode(route.getClazz());
			//时长
			segmentInfo.setDuration(route.getFlyTime());
			ticketorderSegments.add(segmentInfo);
		}
		User user = UserUtils.getUser();
		
		
		//状态为三是直接扔给宝盛的在这里直接保存
		if("3".equals(rt.getTravelType()) && "6aa2721eeea54e16a4d5fd572878197d".equals(suppliId)){
			SinglePrice singlePrice1=new SinglePrice();
			SinglePrice singlePrice2=new SinglePrice();
			SinglePrice singlePrice3=new SinglePrice();
			List<Qte> qte=rt.getQte();
			for (Qte qte2 : qte) {
				String identity = qte2.getIdentity();
				//成人
				if(null == identity){
					//price价格 总价 税费tax
					if(null != qte2.getPrice() && !"".equals(qte2.getPrice()) && null != qte2.getTax() && !"".equals(qte2.getTax())){
						singlePrice1.setDistributorTicketPrice((Double.valueOf(qte2.getPrice())- Double.valueOf(qte2.getTax()))+"");
						singlePrice1.setTaxCn(Double.valueOf(qte2.getTax()));
					}
					
					
				}//儿童
				else if(null != identity && !"CH".equals(identity)){
					//price价格 总价 税费tax
					if(null != qte2.getPrice() && !"".equals(qte2.getPrice()) && null != qte2.getTax() && !"".equals(qte2.getTax())){
						singlePrice2.setDistributorTicketPrice((Double.valueOf(qte2.getPrice())- Double.valueOf(qte2.getTax()))+"");
						singlePrice3.setTaxCn(Double.valueOf(qte2.getTax()));
					}
					
				}//婴儿
				else if(null != identity && !"IN".equals(identity)){
					//price价格 总价 税费tax
					if(null != qte2.getPrice() && !"".equals(qte2.getPrice()) && null != qte2.getTax() && !"".equals(qte2.getTax())){
						singlePrice3.setDistributorTicketPrice((Double.valueOf(qte2.getPrice())- Double.valueOf(qte2.getTax()))+"");
						singlePrice3.setTaxCn(Double.valueOf(qte2.getTax()));
					}
				}
			}
			
			//6aa2721eeea54e16a4d5fd572878197d  宝盛的id   宝盛的名字是宝盛航空服务有限公司
			//出现这种情况时直接将信息保存到宝盛里边
			//生成的订单号  cc订单类型  mm订单状态
			TicketorderDetail ticketorderDetail=new TicketorderDetail();
			MakeOrderNum make=new MakeOrderNum();
			String orderNum=make.makeOrderNum("01", "01");
			
			ticketorderDetail.setSupplierProductNo("6aa2721eeea54e16a4d5fd572878197d");
			ticketorderDetail.setSupplierName("宝盛航空服务有限公司");
			//存放采购商id
			ticketorderDetail.setReceptionCustomerId(user.getCompany().getId());
			//采购商的名字
			ticketorderDetail.setRelevantClient(user.getCompany().getName());
			//订单号
			ticketorderDetail.setOrderNo(orderNum);
			//订单的创建时间
			ticketorderDetail.setCreateTime(new Date());
			//订单的操作状态等待审核
			ticketorderDetail.setCurrentStatus("1");
			ticketorderDetail.setPayStatus("1");
			ticketorderDetail.setOrderStatus("1");
			//添加旅客
			StringBuffer sb=new StringBuffer();
			//取出旅客集合
			List<TicketorderPassenger> passenger=passengerList;
			for (TicketorderPassenger ticketorderPassenger : passenger) {
				sb.append(","+ticketorderPassenger.getPassengerName());
			}
			//判断旅客信息有没有
			if(null != sb && sb.length()>0)
				ticketorderDetail.setPassengers(sb.toString().substring(1));
			//将订单添加到数据库
			ticketorderDetailServiceFacade.save(ticketorderDetail);
			TicketorderSinglePriceModel price=new TicketorderSinglePriceModel();
			for (TicketorderSinglePrice singlePrice : singlePriceList) {
				if("0".equals(singlePrice.getPassengerType())){
					singlePrice.setDistributorTicketPrice(singlePrice1.getDistributorTicketPrice());
					singlePrice.setTaxCn(singlePrice1.getTaxCn());
				}else if("1".equals(singlePrice.getPassengerType())){
					singlePrice.setDistributorTicketPrice(singlePrice2.getDistributorTicketPrice());
					singlePrice.setTaxCn(singlePrice2.getTaxCn());
				}else if ("4".equals(singlePrice.getPassengerType())){
					singlePrice.setDistributorTicketPrice(singlePrice3.getDistributorTicketPrice());
					singlePrice.setTaxCn(singlePrice3.getTaxCn());
				}
			}
			price.setTicketorderSinglePrices(singlePriceList);
			//机票明细
			ticketorderSinglePriceServiceFacade.saveSinglePrice(price,orderNum);
			//旅客信息
			TicketorderPassengerModel pssegnerModel=new TicketorderPassengerModel();
			//保存旅客信息
			ticketorderPassengerFacade.savePassenger(pssegnerModel, orderNum);
			//交给宝盛之后就是直接跳到查询页面
			returnPage="redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/list";
		}
		
		//request.setAttribute("mapJson", mapJson);
		model.addAttribute("rt", rt);
		//供应商
		model.addAttribute("policyRoules",policyRoules);
		//旅客信息
		model.addAttribute("passengerList", passengerList);
		//航段
		model.addAttribute("ticketorderSegments", ticketorderSegments);
		//机票明细
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("user", user);
		model.addAttribute("suppliId", suppliId);
		}
		return returnPage;
	
	}
	

	
	@RequestMapping(value="toOrderDetail")
	public String toOrderDetail(){
		
		return "sz/order/orderDetail";
	}
	
	@RequestMapping(value="toProduct_add")
	public String toProduct_add(){
		
		return "sz/order/product_add";
	}
	
	
	
	
	
	@RequestMapping(value="toSaveOrderDetail")
	public String toSaveOrderDetail(Model model,TicketorderPassenger ticketorderPassenger,TicketorderPnr ticketorderPnr,TicketorderSegment ticketorderSegment){
		//pnr
		model.addAttribute("ticketorderPnr", ticketorderPnr);
		//旅客信息
		model.addAttribute("ticketorderPassenger", ticketorderPassenger);
		//机票订单航段
		model.addAttribute("ticketorderSegment", ticketorderSegment);
		
		return "sz/order/createOrderBuffer";
	}
	
	@RequestMapping(value="saveOrderDetail")
	public ModelAndView saveOrderDetail(HttpServletRequest request,RecipientModel recipient,InvoiceModel invoices,TicketorderSinglePriceModel ticketorderSinglePrices,TicketorderDetail ticketorderDetail,TicketorderPassengerModel ticketorderPassengers,TicketorderPnr ticketorderPnr,TicketorderSegmentModel ticketorderSegments){
		//生成的订单号  cc订单类型  mm订单状态
		MakeOrderNum make=new MakeOrderNum();
		String orderNum=make.makeOrderNum("01", "01");
		
		User user = UserUtils.getUser();
		//存放采购商id
		ticketorderDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字
		ticketorderDetail.setRelevantClient(user.getCompany().getName());
		
		
		//供应商的名字SUPPLIER_NAME张权给我
		
		//供应商的idSUPPLIER_PRODUCT_NO 张权给我
		
		
		//订单号
		ticketorderDetail.setOrderNo(orderNum);
		//订单的创建时间
		ticketorderDetail.setCreateTime(new Date());
		
		//航班号多个航好数据库只存一个
		if(null != ticketorderSegments.getTicketorderSegments() && ticketorderSegments.getTicketorderSegments().size() >0)
		ticketorderDetail.setGoMainFlight(ticketorderSegments.getTicketorderSegments().get(0).getOperatingFlightNo());
		//订单创建人   此处的订单创始人
		ticketorderDetail.setCreateAccount(user.getName());
		ticketorderDetail.setCreateAccountId(user.getId());
		//下单成功状态
		ticketorderDetail.setOrderStatus("1");
		//未支付状态
		//ticketorderDetail.setPayStatus("1");
		//操作状态
		//ticketorderDetail.setCurrentStatus("1");
		//添加旅客
		StringBuffer sb=new StringBuffer();
		//取出旅客集合
		List<TicketorderPassenger> passenger=ticketorderPassengers.getTicketorderPassengers();
		for (TicketorderPassenger ticketorderPassenger : passenger) {
			sb.append(","+ticketorderPassenger.getPassengerName());
		}
		//判断旅客信息有没有
		if(null != sb && sb.length()>0)
			ticketorderDetail.setPassengers(sb.toString().substring(1));
		//将订单添加到数据库
		
		//机票明细 每个人一条记录
		//政策返点在页面显示是百分比 
		
		//机票明细
		ticketorderSinglePriceServiceFacade.saveSinglePrice(ticketorderSinglePrices,orderNum);
		
		//invoice.setOrderNo(orderNum);
		//行程单信息
		List<Invoice> invoicesList=new ArrayList<Invoice>();
		//判断是自己还是客人  自己是2  客人是1 
		String youhead=request.getParameter("youhead");
		List<Recipient> RecipientList=new ArrayList<Recipient>();
		String hid_invocie=request.getParameter("hid_invocie");
		ticketorderDetail.setInvoice(hid_invocie);
 		if(null != hid_invocie && !"".equals(hid_invocie)){
			//2是行程单  1 是服务业发票
			String invoice=request.getParameter("invoice1");
			if("2".equals(invoice)){
				List<Invoice> invoices2=invoices.getInvoices();
				for (int i = 0; i < invoices2.size(); i++) {
					if(i != 0 ){
						invoicesList.add(invoices2.get(i));
					}
				}
			}//此时只是选择了发票
			else if("1".equals(invoice)){
				invoicesList.add(invoices.getInvoices().get(0));
			}
			
			//收件人
			if(null != recipient && !"".equals(recipient)){
				//客人
				if("1".equals(youhead)){
					RecipientList=recipient.getRecipient();
					List<Recipient> recipients=new ArrayList<Recipient>();
					for (int i = 0; i < RecipientList.size(); i++) {
						if(i != 0){
							recipients.add(RecipientList.get(i));
						}
					}
					RecipientList=new ArrayList<Recipient>();
					RecipientList=recipients;
					recipient.setRecipient(RecipientList);
					recipientServiceFacade.saveRecipient(recipient,orderNum);
				}
				//代表是自己
				else if("2".equals(youhead)){
					Recipient recipient2=recipient.getRecipient().get(0);
					recipient2.setOrderNo(orderNum);
					RecipientList.add(recipient2);
					recipientServiceFacade.save(recipient2);
				}
				
			}
			InvoiceModel invoiceModel=new InvoiceModel();
			invoiceModel.setInvoices(invoicesList);
			invoiceServiceFacade.saveInvoice(invoiceModel,orderNum);
		}
 		int reSize=RecipientList.size();
 		//计算收件人的价格
 		double price=reSize*20;
 		ticketorderDetail.setTotalPrice(ticketorderDetail.getTotalPrice()+price);
 		ticketorderDetailServiceFacade.save(ticketorderDetail);
		
		//航段信息
		//将航程航班加到数据库
		ticketorderSegmentServiceFacade.saveSegment(ticketorderSegments,orderNum);
		
		//添加旅客
		ticketorderPassengerFacade.savePassenger(ticketorderPassengers,orderNum);
		//获取添加的旅客信息页面展示
		//添加历史
		TicketorderHistory ticketorderHistory=new TicketorderHistory();
		//操作时间
		ticketorderHistory.setOperationTime(new Date());
		//订单号
		ticketorderHistory.setOrderNo(orderNum);
		ticketorderHistory.setOperatorName("采购商:"+user.getName());
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			ticketorderHistory.setRemark(message);
		}
		//操作历史
		ticketorderHistory.setPreviousOperation("生成订单");
		//操作人
		//将操作历史添加数据库
		ticketorderHistoryFacade.save(ticketorderHistory);
		//行程单
		//List<Invoice> invoiceList = invoices.getInvoices();
		
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("detail", ticketorderDetail);
		modelAndView.addObject("singlePriceList", ticketorderSinglePrices.getTicketorderSinglePrices());
		modelAndView.addObject("invoiceList", invoicesList);
		//modelAndView.addObject("priceList",ticketorderSinglePrices.getTicketorderSinglePrices());
		modelAndView.addObject("segmentList", ticketorderSegments.getTicketorderSegments());
		modelAndView.addObject("passengerList", ticketorderPassengers.getTicketorderPassengers());
		//收件人
		modelAndView.addObject("recipientList", RecipientList);
		modelAndView.setViewName("sz/order/detailsuc");
		return modelAndView;
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
		
		//操作人
		
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
		//添加历史
			TicketorderHistory ticketorderHistory=new TicketorderHistory();
			//操作时间
			ticketorderHistory.setOperationTime(new Date());
			//订单号
			ticketorderHistory.setOrderNo(r2_OrderNo);
			ticketorderHistory.setOperatorName("采购商:"+user.getName());
		
		//100状态就是支付成功  
		if("100".equals(r6_Status)){
			//保存历史纪录
			ticketorderHistory.setPreviousOperation("支付成功");
			//修改支付状态
			ticketorderDetailServiceFacade.updatePayStatus("0", r2_OrderNo);
			//记录流水单号
			ticketorderDetailServiceFacade.updateRelevantOrderNo(r7_TrxNo, r2_OrderNo);
			//更改操作状态为等待出票状态
			ticketorderDetailServiceFacade.updateCurrentStatus("7", r2_OrderNo);
			
			String tk_message=request.getParameter("tk_message");
			if(StringUtils.isNotBlank(tk_message)){
				TicketorderMessage ticketorderMessage=new TicketorderMessage();
				ticketorderMessage.setMessage(tk_message);
				ticketorderMessage.setOrder_no(r2_OrderNo);
				ticketorderMessage.setCreateUser(user.getLoginName());
				ticketorderMessageServiceFacade.save(ticketorderMessage);
				//ticketorderMessageServiceFacade.delete(ticketorderMessage);
			}
			
		}
		//支付失败
		else if("101".equals(r6_Status)){
			//修改支付状态 状态是失败状态
			ticketorderDetailServiceFacade.updatePayStatus("2", r2_OrderNo);
			//保存历史纪录
			ticketorderHistory.setPreviousOperation("支付失败");
		}
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			ticketorderHistory.setRemark(message);
		}
		//将操作历史添加数据库
		ticketorderHistoryFacade.save(ticketorderHistory);
		return "redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/findticketorderDetail?orderNo="+r2_OrderNo;
	}
	
	
	@RequestMapping(value="toOrder")
	public String toOrder(){
		return "sz/order/order";
	}
	
	//@RequiresPermissions("order:tbTicketorderDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(String flag,TicketorderDetail tbTicketorderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		System.out.println(user);
		tbTicketorderDetail.setReceptionCustomerId(user.getCompany().getId());
		Page<TicketorderDetail> page = ticketorderDetailServiceFacade.findPage(new Page<TicketorderDetail>(request, response), tbTicketorderDetail); 
		page.setOld(false);
	
		
		//查看支付的状态以及条数
		Map<String,String> payStatusCountList=ticketorderDetailServiceFacade.findPayStatusCount(tbTicketorderDetail);
		//查看订单状以及状态
		Map<String,String> orderStatusCountList=ticketorderDetailServiceFacade.findOrderStatusCount(tbTicketorderDetail);
		//获取操作状态
		Map<String,String> currentStatusList=ticketorderDetailServiceFacade.findCurrentStatus(tbTicketorderDetail);
		model.addAttribute("currentStatusList", currentStatusList);
		model.addAttribute("payStatusCountList", payStatusCountList);
		model.addAttribute("page", page);
		model.addAttribute("orderStatusCountList", orderStatusCountList);
		String returnPage="";
		if(null != flag && "1".equals(flag)){
			returnPage="sz/order/tbTicketorderDetailListPage";
		}else{
			returnPage="sz/order/orderquery";
		}
		return returnPage;
	}
	
	@RequestMapping(value="verifyOrder")
	public String verifyOrder(HttpServletRequest request,HttpServletResponse response,String orderNo){
		//获得当前操作状态
		String Status = request.getParameter("hid_currentStatus");
		//添加历史
		TicketorderHistory ticketorderHistory=new TicketorderHistory();
		if(null != Status && "17".equals(Status)){
			//取消订单  
			ticketorderDetailServiceFacade.updateOrderStatus("7", orderNo);
			ticketorderHistory.setPreviousOperation("采购商取消订单");
		}else if(null != Status && "1".equals(Status)){
			//采购商申请核实价格和政策
			ticketorderDetailServiceFacade.updateCurrentStatus(Status, orderNo);
			ticketorderDetailServiceFacade.updatePayStatus("1", orderNo);
			ticketorderHistory.setPreviousOperation("采购商申请核实价格和政策");
		}else if(null != Status && "3".equals(Status)){
			//采购商督促供应商核实价格和政策
			ticketorderHistory.setPreviousOperation("采购商督促供应商核实价格和政策");	
		}else if(null != Status && "4".equals(Status)){
			ticketorderHistory.setPreviousOperation("采购商督促供应商出票");	
		}else if(null != Status && "18".equals(Status)){
			//采购商重新申请核实价格和政策
			//采购商重新申请操作状态改为未审核状态
			ticketorderDetailServiceFacade.updateCurrentStatus("1", orderNo);
			ticketorderHistory.setPreviousOperation("采购商重新申请核实价格和政策");	
		}else if(null != Status && "9".equals(Status)){
			//采购商申请全额退款
			//设置订单操作状态为9申请全额退款
			ticketorderDetailServiceFacade.updateCurrentStatus("9", orderNo);
			ticketorderHistory.setPreviousOperation("采购商申请全额退款。");
			//设置支付状态为未退款
			ticketorderDetailServiceFacade.updatePayStatus("3", orderNo);
		}else if(null != Status &&"7".equals(Status)){
			//采购商取消全额退款
			//设置订单操作状态为7等待出票
			ticketorderDetailServiceFacade.updateCurrentStatus("7", orderNo);
			ticketorderHistory.setPreviousOperation("采购商取消全额退款，等待出票。");
			//设置支付状态为已支付
			ticketorderDetailServiceFacade.updatePayStatus("0", orderNo);
		}
		//操作时间
		ticketorderHistory.setOperationTime(new Date());
		//订单号
		ticketorderHistory.setOrderNo(orderNo);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			ticketorderHistory.setRemark(message);
		}
		//操作人
		ticketorderHistory.setOperatorName("采购商:"+UserUtils.getUser().getName());
		//将操作历史添加数据库
		ticketorderHistoryFacade.save(ticketorderHistory);
				
		return "redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/list";
	}

	@RequiresPermissions("order:tbTicketorderDetail:view")
	@RequestMapping(value = "form")
	public String form(TicketorderDetail tbTicketorderDetail, Model model) {
		model.addAttribute("tbTicketorderDetail", tbTicketorderDetail);
		return "sz/order/tbTicketorderDetailForm";
	}

	@RequiresPermissions("order:tbTicketorderDetail:edit")
	@RequestMapping(value = "save")
	public String save(TicketorderDetail tbTicketorderDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbTicketorderDetail)){
			return form(tbTicketorderDetail, model);
		}
		ticketorderDetailServiceFacade.save(tbTicketorderDetail);
		addMessage(redirectAttributes, "保存订单生成模块成功");
		return "redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/?repage";
	}
	
	@RequiresPermissions("order:tbTicketorderDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(TicketorderDetail tbTicketorderDetail, RedirectAttributes redirectAttributes) {
		ticketorderDetailServiceFacade.delete(tbTicketorderDetail);
		addMessage(redirectAttributes, "删除订单生成模块成功");
		return "redirect:"+Global.getAdminPath()+"/order/tbTicketorderDetail/?repage";
	}
	
	@RequestMapping(value="findticketorderDetail")
	public String findticketorderDetail(HttpServletResponse response,Model model ,HttpServletRequest request,String orderNo){
		//获取订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		//获取订单的旅客信息
		List<TicketorderPassenger> passengerList=ticketorderPassengerFacade.findTicketorderPassenger(orderNo);
		//获取航段信息
		List<TicketorderSegment> segmentList=ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);
		//废票操作时间段  在每天的六点之前可以申请
		boolean voidsheetTime=false;
		
		//获取系统时间
		Date date=new Date();
		//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sim2=new SimpleDateFormat("yyyy-MM-dd");
		//先比较是不是同一天 
		if(sim2.format(date).equals(sim2.format(detail.getCreateTime()))){
			//看当前时间有没有超过18点
			if( 18 > date.getHours()){
				voidsheetTime = true;
			}
		}
		//获取历史操作
		List<TicketorderHistory> historyList=ticketorderHistoryFacade.findTicketorderHistory(orderNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(orderNo);
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		//收件人信息
		List<Recipient> recipientList = recipientServiceFacade.findRecipientList(orderNo);
		//查找留言信息
		//TicketorderMessage ticketorderMessage=new TicketorderMessage();
		//ticketorderMessage.setOrder_no(orderNo);
		//List<TicketorderMessage> ticketorderMessageList=ticketorderMessageServiceFacade.findList(ticketorderMessage);
		//行程单
		Invoice invoice=new Invoice();
		invoice.setOrderNo(orderNo);
		List<Invoice> invoiceList=invoiceServiceFacade.findList(invoice);
		model.addAttribute("recipientList",recipientList );
		model.addAttribute("detail", detail);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList", segmentList);
		model.addAttribute("historyList", historyList);
		model.addAttribute("singlePriceList", singlePriceList);
		model.addAttribute("invoiceList", invoiceList);
		//model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		model.addAttribute("voidsheetTime", voidsheetTime);
		String returnPage="";
		//判断是否出票完成 假如完成跳到出票成功那个页面
		if("8".equals(detail.getCurrentStatus())){
			//出票完成页面
			returnPage="sz/order/detailticket";
			
		}//采购商在没核实价格和政策时跳到核实价格和政策页面
		else if(null == detail.getCurrentStatus() || "".equals(detail.getCurrentStatus() )){
			returnPage="sz/order/detailsuc";
		}else {
			returnPage="sz/order/ticketorderDetail";
		}
		return returnPage;
	}
	
}