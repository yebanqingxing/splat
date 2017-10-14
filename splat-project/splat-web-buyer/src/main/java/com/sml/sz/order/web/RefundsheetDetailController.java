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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.sz.MakeOrderNum;
import com.sml.sz.StringUtils;
import com.sml.sz.UploadUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.web.BaseController;
import com.sml.sz.config.Global;
import com.sml.sz.order.entity.CurrentStatus;
import com.sml.sz.order.entity.EndorsesheetPassenger;
import com.sml.sz.order.entity.Invoice;
import com.sml.sz.order.entity.PayStatusCount;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.RefundsheetAppendix;
import com.sml.sz.order.entity.RefundsheetDetail;
import com.sml.sz.order.entity.RefundsheetHistory;
import com.sml.sz.order.entity.RefundsheetPassenger;
import com.sml.sz.order.entity.RefundsheetStatusCount;
import com.sml.sz.order.entity.TicketorderDetail;
import com.sml.sz.order.entity.TicketorderHistory;
import com.sml.sz.order.entity.TicketorderMessage;
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
import com.sml.sz.order.service.TicketorderDetailServiceFacade;
import com.sml.sz.order.service.TicketorderHistoryFacade;
import com.sml.sz.order.service.TicketorderMessageServiceFacade;
import com.sml.sz.order.service.TicketorderPassengerFacade;
import com.sml.sz.order.service.TicketorderSegmentServiceFacade;
import com.sml.sz.order.service.TicketorderSinglePriceServiceFacade;
import com.sml.sz.order.service.TicketorderTicketServiceFacade;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 退票单Controller
 * 
 * @author 李千超
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/refundsheetdetail/refundsheetDetail")
public class RefundsheetDetailController extends BaseController {

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

	//原始订单的接口
	@Autowired
	private TicketorderDetailServiceFacade ticketorderDetailServiceFacade;
	
	//原始订单的历史信息
	private TicketorderHistoryFacade ticketorderHistoryFacade;
	
	
	//已经改签过的旅客的信息
	@Autowired
	private EndorsesheetPassengerServiceFacade endorsesheetPassengerServiceFacade;
	
	@Autowired
	TicketorderMessageServiceFacade ticketorderMessageServiceFacade;
	
	//收件人
	@Autowired
	RecipientServiceFacade recipientServiceFacade;
	
	//行程单
		@Autowired
		InvoiceServiceFacade invoiceServiceFacade;
	
	@Autowired
	private TicketorderSinglePriceServiceFacade ticketorderSinglePriceServiceFacade;
	
	@Autowired
	private TicketorderTicketServiceFacade ticketorderTicketServiceFacade;
	
	@ModelAttribute
	public RefundsheetDetail get(@RequestParam(required = false) String id) {
		RefundsheetDetail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = refundsheetDetailServiceFacade.get(id);
		}
		if (entity == null) {
			entity = new RefundsheetDetail();
		}
		return entity;
	}
	
	/**
	 * 在改签完成点击进来的申请退票
	 * @return
	 */
	@RequestMapping(value="endorserRefundsheetDetail")
	public String endorserRefundsheetDetail(Model model,String endorsePassIds,String orderNo,HttpServletRequest request,HttpServletResponse response){
		//获取原始订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		
		// 改签完成后要退票的旅客   改签状态是1 退票状态是3 废票状态是2
		List<EndorsesheetPassenger> tPassengerList = endorsesheetPassengerServiceFacade.findEndorsePassengerList(endorsePassIds, "");

		// 原始改签航班信息
		List<TicketorderSegment> tSegmentList = ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);

		// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
		// 改程订单06
		MakeOrderNum make=new MakeOrderNum();
		String orderNum = make.makeOrderNum("01", "02");
		Map map = new HashMap();
		// 退票单的原始订单号
		map.put("orderNo", orderNo);
		// 退票单的订单号
		map.put("orderNum", orderNum);
		model.addAttribute("orderMap", map);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);

		return "sz/order/endorseIssueSuccess";
	}
	
	@RequestMapping(value="saveEndorserRefundsheetDetail")
	public String saveEndorserRefundsheetDetail(@RequestParam MultipartFile[] fileFields, RefundsheetDetail refundsheetDetail,
			RefundsheetPassenger passenger, TicketorderSegment segment, HttpServletRequest request, String orderNo){
		// 改签以后申请退票保存
		// 证明选择了航段要退票的
		if (null != segment.getSegId()) {
			ticketorderSegmentServiceFacade.findRefundSheetSeg(refundsheetDetail.getRefundsheetNo(), segment);
		}
		
		User user = UserUtils.getUser();
		//存放采购商id
		refundsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		refundsheetDetail.setRelevantClient(user.getCompany().getName());
		
		//refundsheetDetail.setCreateAccount(user.getName());
		
		//供应商的名字SUPPLIER_NAME
		
		//供应商的idSUPPLIER_PRODUCT_NO
		// 已提交退票单
		refundsheetDetail.setRefundsheetStatus("1");
		// 支付状态 未退款 1
		refundsheetDetail.setPayStatus("1");
		// 首段航程 航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15?????
		// 创建时间
		refundsheetDetail.setCreateTime(new Date());
		// 将退票单添加到数据库
		refundsheetDetailServiceFacade.save(refundsheetDetail);
		//判断是否有文件上传
		if (null != fileFields) {
			for (MultipartFile multipartFile : fileFields) {
				// 附件的一些操作
				UploadUtils upload = new UploadUtils();
				// 最终的文件名字
				String saveName = upload.upfile(multipartFile,request.getSession().getServletContext().getRealPath("/upload"));
				RefundsheetAppendix appendix = new RefundsheetAppendix();
				// 获取路径添加到数据库
				appendix.setFileAddress(request.getSession().getServletContext().getRealPath("/upload") + saveName);
				appendix.setOrgnFileName(multipartFile.getOriginalFilename());
				appendix.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				// 附件上传时间
				appendix.setUploadTime(new Date());
				// 将附件信息添加到数据库
				refundsheetAppendixServiceFacade.save(appendix);
			}
		}
		// 旅客的信息
		if (null != passenger.getOrderPassengerId()) {
			String[] oldPassId = passenger.getOrderPassengerId().split(",");
			for (String string : oldPassId) {
				EndorsesheetPassenger pass = endorsesheetPassengerServiceFacade.get(string);
				RefundsheetPassenger refundsheetPassenger = new RefundsheetPassenger();
				refundsheetPassenger.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setOrderPassengerId(string.trim());
				refundsheetPassenger.setTicketNo(pass.getTicketNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setPassengerTitle(pass.getPassengerTitle());
				refundsheetPassenger.setPassengerType(pass.getPassengerType());
				refundsheetPassenger.setGender(pass.getGender());
				refundsheetPassenger.setCertType(pass.getCertType());
				refundsheetPassenger.setCertNo(pass.getCertNo());
				refundsheetPassenger.setPhone(pass.getPhone());
				refundsheetPassenger.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setOrderPassengerId(string.trim());
				refundsheetPassenger.setTicketNo(pass.getTicketNo());
				refundsheetPassenger.setSupplierUsedSellPrice("0");
				refundsheetPassenger.setSupplierUsedTax("0");
				refundsheetPassenger.setSupplierServiceCharge("0");
				refundsheetPassenger.setSupplierOtherCharge("0");
				refundsheetPassenger.setSupplierSettlementPrice("0");
				refundsheetPassenger.setSupplierRefundCharge("0");
			
				// 将旅客信息添加数据库
				refundsheetPassengerServiceFacade.save(refundsheetPassenger);
			}
		}
		// 将历史添加数据库
		RefundsheetHistory history = new RefundsheetHistory();
		// 历史操作时间
		history.setOperationTime(new Date());
		history.setOperatorName("采购商:"+user.getName());
		history.setPreviousOperation("采购商已提交退票单。");
		// 订单号
		history.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
		refundsheetHistoryServiceFacade.save(history);
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/refundsheetDetail/list";
	}

	@RequestMapping(value = "refundsheetDetail")
	public String refundsheetDetail(String passIds, String orderNo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 要退票的旅客
		List<TicketorderPassenger> tPassengerList = ticketorderPassengerFacade.findPassengerList(passIds, "");
		//获取订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		// 原始航班信息
		List<TicketorderSegment> tSegmentList = ticketorderSegmentServiceFacade.findSegmentByOrderNo(orderNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(orderNo);
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		/*for (TicketorderSegment segment : tSegmentList) {
			//TODO
			String duration=(double)((segment.getArriveTime().getTime()-segment.getDepartureTime().getTime())/(60*60*1000))+"小时";
			//获取时长
			segment.setDuration(duration);
		}*/
		
		//获取操作历史
		//List<TicketorderHistory> history=ticketorderHistoryFacade.findTicketorderHistory(orderNo);
		// 生成的订单号 cc订单类型 01机票类型 02酒店类型 mm订单状态 出票单01 退票订单02 废票订单03 改期订单04 升舱订单05
		// 改程订单06
		MakeOrderNum make=new MakeOrderNum();
		String orderNum = make.makeOrderNum("01", "02");
		Map map = new HashMap();
		// 退票单的原始订单号
		map.put("orderNo", orderNo);
		// 退票单的订单号
		map.put("orderNum", orderNum);
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(orderNo);
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(orderNo);
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("invoiceList", invoiceList);
		
		model.addAttribute("orderMap", map);
		model.addAttribute("detail", detail);
		model.addAttribute("tPassengerList", tPassengerList);
		model.addAttribute("tSegmentList", tSegmentList);
		model.addAttribute("singlePriceList", singlePriceList);
	//	model.addAttribute("history", history);
		return "sz/order/issueSuccess";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveRefundsheetDetail")
	public String saveRefundsheetDetail(@RequestParam MultipartFile[] fileFields, RefundsheetDetail refundsheetDetail,
			RefundsheetPassenger passenger, TicketorderSegment segment, HttpServletRequest request, String orderNo) {
		// 证明选择了航段要退票的
		if (null != segment.getSegId()) {
			ticketorderSegmentServiceFacade.findRefundSheetSeg(refundsheetDetail.getRefundsheetNo(), segment);
		}
		//获取订单信息
		TicketorderDetail detail=ticketorderDetailServiceFacade.findorderDetailByOrderNo(orderNo);
		User user = UserUtils.getUser();
		//存放采购商id
		refundsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//采购商的名字 
		refundsheetDetail.setRelevantClient(user.getCompany().getName());
		
		refundsheetDetail.setCrsPnr(detail.getCrsPnr());
		
		refundsheetDetail.setArlPnr(detail.getArlPnr());
		
		refundsheetDetail.setMidPnr(detail.getMidPnr());
		
		refundsheetDetail.setCreateAccount(user.getName());
		
		refundsheetDetail.setTicketNoTemp(detail.getTicketNoTemp());
		
		refundsheetDetail.setOrderNo(orderNo);
		//供应商的名字SUPPLIER_NAME
		
		//供应商的idSUPPLIER_PRODUCT_NO
		// 已提交退票单
		refundsheetDetail.setRefundsheetStatus("1");
		//等待审核
		refundsheetDetail.setCurrentStatus("1");
		// 支付状态 3页面不显示支付状态
		refundsheetDetail.setPayStatus("3");
		// 首段航程 航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15?????
		// 创建时间
		refundsheetDetail.setCreateTime(new Date());
		// 将退票单添加到数据库
		refundsheetDetailServiceFacade.save(refundsheetDetail);
		//判断是否有文件上传
		if (null != fileFields) {
			for (MultipartFile multipartFile : fileFields) {
				// 附件的一些操作
				UploadUtils upload = new UploadUtils();
				// 最终的文件名字
				String saveName = upload.upfile(multipartFile,
						request.getSession().getServletContext().getRealPath("/upload"));
				RefundsheetAppendix appendix = new RefundsheetAppendix();
				// 获取路径添加到数据库
				appendix.setFileAddress(request.getSession().getServletContext().getRealPath("/upload") + saveName);
				appendix.setOrgnFileName(multipartFile.getOriginalFilename());
				appendix.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				// 附件上传时间
				appendix.setUploadTime(new Date());
				// 将附件信息添加到数据库
				refundsheetAppendixServiceFacade.save(appendix);
			}
		}
		// List<String> uploadFile = upload.uploadFile(request);

		// 旅客的信息
		if (null != passenger.getOrderPassengerId()) {
			String[] oldPassId = passenger.getOrderPassengerId().split(",");
			for (String string : oldPassId) {
				TicketorderPassenger pass = ticketorderPassengerFacade.get(string);
				RefundsheetPassenger refundsheetPassenger = new RefundsheetPassenger();
				refundsheetPassenger.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setOrderPassengerId(string.trim());
				refundsheetPassenger.setTicketNo(pass.getTicketNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setPassengerTitle(pass.getPassengerTitle());
				refundsheetPassenger.setPassengerType(pass.getPassengerType());
				refundsheetPassenger.setGender(pass.getGender());
				refundsheetPassenger.setCertType(pass.getCertType());
				refundsheetPassenger.setCertNo(pass.getCertNo());
				refundsheetPassenger.setPhone(pass.getPhone());
				refundsheetPassenger.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
				refundsheetPassenger.setPassengerName(pass.getPassengerName());
				refundsheetPassenger.setOrderPassengerId(string.trim());
				refundsheetPassenger.setTicketNo(pass.getTicketNo());
				refundsheetPassenger.setSupplierUsedSellPrice("0");
				refundsheetPassenger.setSupplierUsedTax("0");
				refundsheetPassenger.setSupplierServiceCharge("0");
				refundsheetPassenger.setSupplierOtherCharge("0");
				refundsheetPassenger.setSupplierSettlementPrice("0");
				refundsheetPassenger.setSupplierRefundCharge("0");
				// 将旅客信息添加数据库
				refundsheetPassengerServiceFacade.save(refundsheetPassenger);
				ticketorderPassengerFacade.findPassengerList(pass.getId(), "3");
			}
		}

		// 将历史添加数据库
		RefundsheetHistory history = new RefundsheetHistory();
		// 历史操作时间
		history.setOperationTime(new Date());
		history.setOperatorName("采购商:"+user.getName());
		//操作记录
		history.setPreviousOperation("采购商已提交退票单。");
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
		history.setRemark(message);
		}
		// 订单号
		history.setRefundsheetNo(refundsheetDetail.getRefundsheetNo());
		refundsheetHistoryServiceFacade.save(history);
		
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/refundsheetDetail/list";
	}

	//@RequiresPermissions("refundsheetdetail:refundsheetDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(String flag,RefundsheetDetail refundsheetDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		System.out.println(user);
		//采购商的id
		refundsheetDetail.setReceptionCustomerId(user.getCompany().getId());
		//request("orderBy", "a.id desc");
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
			returnPage="sz/order/orderretreatPage";
		}else{
			returnPage="sz/order/orderretreat";
		}
		return returnPage;
	}

	@RequiresPermissions("refundsheetdetail:refundsheetDetail:view")
	@RequestMapping(value = "form")
	public String form(RefundsheetDetail refundsheetDetail, Model model) {
		model.addAttribute("refundsheetDetail", refundsheetDetail);
		return "sz/refundsheetdetail/refundsheetDetailForm";
	}

	@RequiresPermissions("refundsheetdetail:refundsheetDetail:edit")
	@RequestMapping(value = "save")
	public String save(RefundsheetDetail refundsheetDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, refundsheetDetail)) {
			return form(refundsheetDetail, model);
		}
		refundsheetDetailServiceFacade.save(refundsheetDetail);
		addMessage(redirectAttributes, "保存退票单成功");
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/refundsheetDetail/?repage";
	}

	@RequiresPermissions("refundsheetdetail:refundsheetDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(RefundsheetDetail refundsheetDetail, RedirectAttributes redirectAttributes) {
		refundsheetDetailServiceFacade.delete(refundsheetDetail);
		addMessage(redirectAttributes, "删除退票单成功");
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/refundsheetDetail/?repage";
	}

	@RequestMapping(value="cancle")
	public String cancel(String refundsheetNo,HttpServletRequest request,HttpServletResponse response){
		//取消订单 退票单状态 1 下单成功（采购商申请退票） 2 取消退票 3 退票完成 4 异常订单 5 等待退票
		//操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 退票确认(采购商确认) 4 审核通过 5 退票操作(采购商同意退票)
		User user = UserUtils.getUser();
		RefundsheetDetail refundsheetDetail=refundsheetDetailServiceFacade.findRefundsheetDetail(refundsheetNo);
		//获取订单状态
		String currentStatus = request.getParameter("hid_currentStatus");
		RefundsheetHistory history=new RefundsheetHistory();
		history.setOperationTime(new Date());
		history.setRefundsheetNo(refundsheetNo);
		history.setOperatorName("采购商:"+user.getName());
		if("3".equals(currentStatus)){
			refundsheetDetail.setCurrentStatus("3");
			//refundsheetDetail.setPayStatus("3");
			history.setPreviousOperation("确认退票");
		}else if("1".equals(currentStatus)){
				refundsheetDetail.setCurrentStatus("1");
				history.setPreviousOperation("重新申请退票");
		}else if("3".equals(currentStatus)){
				refundsheetDetail.setRefundsheetStatus("2");
				history.setPreviousOperation("取消订单");
				//采购商取消退款申请后，主单的乘客可以重新申请退票
				List<RefundsheetPassenger> findRefundsheetPassenger = refundsheetPassengerServiceFacade.findRefundsheetPassenger(refundsheetDetail.getRefundsheetNo());
					for (RefundsheetPassenger RefundsheetPassenger : findRefundsheetPassenger) {
							ticketorderPassengerFacade.updateVoidRefundEndorseStatus("",RefundsheetPassenger.getOrderPassengerId());
					}
		}else if("6".equals(currentStatus)){
			history.setPreviousOperation("采购商督促供应商审核退票");
		}
		refundsheetDetailServiceFacade.updateRefundsheetStatus(refundsheetDetail);
		//留言
		String message = request.getParameter("tk_message");
		if(StringUtils.isNotEmpty(message) && !message.equals("限50字")){
			history.setRemark(message);
			}
		//入库
		refundsheetHistoryServiceFacade.save(history); 
		return "redirect:" + Global.getAdminPath() + "/refundsheetdetail/refundsheetDetail/findRefundsheetDetail?refundsheetNo="+refundsheetDetail.getRefundsheetNo();
	}
	
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
		/*for (TicketorderSegment segment : segmentList) {
			String duration=(double)((segment.getArriveTime().getTime()-segment.getDepartureTime().getTime())/(60*60*1000))+"小时";
			//获取时长
			segment.setDuration(duration);
		}*/
		
		List<RefundsheetHistory> historyList=refundsheetHistoryServiceFacade.findRefundsheetHistory(refundsheetNo);
		//机票的明细
		TicketorderSinglePrice ticketorderSinglePrice=new TicketorderSinglePrice();
		ticketorderSinglePrice.setOrderNo(detail.getOrderNo());
		List<TicketorderSinglePrice> singlePriceList =  ticketorderSinglePriceServiceFacade.findList(ticketorderSinglePrice);
		//查找留言信息
		TicketorderMessage ticketorderMessage=new TicketorderMessage();
		ticketorderMessage.setOrder_no(refundsheetNo);
		List<TicketorderMessage> ticketorderMessageList=ticketorderMessageServiceFacade.findList(ticketorderMessage);
		List<Recipient> recipientList=recipientServiceFacade.findRecipientList(detail.getOrderNo());
		List<Invoice> invoiceList=invoiceServiceFacade.findInvoiceByOrderNo(detail.getOrderNo());
		model.addAttribute("recipientList", recipientList);
		model.addAttribute("invoiceList", invoiceList);
		
		model.addAttribute("detail",detail );
		model.addAttribute("appendixList",appendixList);
		model.addAttribute("passengerList", passengerList);
		model.addAttribute("segmentList",segmentList);
		model.addAttribute("historyList",historyList);
		model.addAttribute("singlePriceList", singlePriceList);
		//model.addAttribute("ticketorderMessageList", ticketorderMessageList);
		return "sz/order/refundsheetDetail";
	}
	
}