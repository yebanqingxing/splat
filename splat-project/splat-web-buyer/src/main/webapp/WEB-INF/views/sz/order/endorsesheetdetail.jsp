<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际订单申请改签单</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/payment.css">
<script type="text/javascript" src="${ctxStatic}/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
<style type="text/css">
	.transfertwo {
		margin-left: 50%;
	}
	.pnrflight ul li {
		width:33%;
		text-align: center;
	}
</style>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/order/images/titckapply.png">
	<p>国际订单申请改签单</p>
</div>
<form id="form01" action="<%=request.getContextPath()%>/a/order/endorsesheetDetail/saveEndorsesheetDetail" method="post" enctype="multipart/form-data">
<!-- 订单信息 -->
<div class="pnrcontet">
	<div class="effect-head clear">
		<p>订单信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="orderkli">
		<div class="ordercon">
			<div class="orderconhead">
				<ul class="orderconul clear leftconul">
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">下单成功</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset"></span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">&nbsp;</span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>原始单号：&nbsp;<input type="hidden" name="orderNo" value="${detail.orderNo }"/>${detail.orderNo }</li>
					<li>原订单编码PNR：&nbsp;${detail.crsPnr }</li>
					<li>改签申请人：&nbsp;${user.name }</li>
<!-- 					<li class="liwid"><span>操作历史&or;</span></li> -->
				</ul>
				<ul class="clear">
					<li class="colorli">改签费用总金额：&nbsp;待确认</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="colorhei"></div>
<div class="pnrcontet">
	<div class="clear">
		<div class="effect-head fl">
			<p>原始航班信息：</p>
			<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
		</div>
		<div class="pnrclick fl">
			<img src="<%=request.getContextPath() %>/static/order/images/clickpnr.png">
		</div>
	</div>
	<div class="orderkli showhide">
		<div class="ordercon">
			<div class="pnrcontethead clear">
				<p class="fl pnrmarleft">去程信息</p>
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
<!-- 					<li class="licenter">飞行时长<br>中转</li> -->
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<c:forEach  items="${tSegmentList }" var="segment">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">&nbsp;<input type="hidden" name="segId" value="${segment.id }">&nbsp;${segment.operatingFlightNo}<br/>${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter"> -->
<%-- 							<p>&nbsp;${segment.duration}</p> --%>
<%-- 							&nbsp;<img src="${ctxStatic}/order/images/pnrhang.png"> --%>
<!-- <!-- 							<p class="transfer">1程中转</p> --> 
<!-- 						</li> -->
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.arriveAddress }</p>
						</li>
					</ul>
<!-- 					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div> -->
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<!-- 供应商信息 -->
<!-- 供应商名字 -->
<input type="hidden" name="supplier_product_name"  value="${detail.supplierName }"/>
<!-- 供应商的id -->
<input type="hidden" name="supplier_product_no" value="${detail.supplierProductNo}"/>
<!-- 改签描述 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>改签描述：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="textapply">
		<textarea name="text_remark" id="text_remark"></textarea>
	</div>
</div>
<!-- 乘机人信息 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>乘机人信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名（英文）</th>
				<th>改签类型</th>
				<th>性别</th>
				<th>旅客类型</th>
				<th>旅客身份</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>联系电话</th>
				<th>票号</th>
			</tr>
			
				<c:forEach items="${tPassengerList }" var="ticketorderPassenger" varStatus="passengStatus">
			<tr>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].passengerName" value="${ticketorderPassenger.passengerName}">${ticketorderPassenger.passengerName }</td>
				<!-- 旅客序号 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].passengerIndex" value="${ticketorderPassenger.passengerIndex }"/>
				<!-- 原旅客信息id -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].orderPassengerId" value="${ticketorderPassenger.id }"/>
				<td>
					<select name="endorsesheetPassengers[${passengStatus.index }].endorseType" class="selectticke">
							<option value="1">自愿</option>
							<option value="2">非自愿</option>
						</select>
				</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].gender" value="${ticketorderPassenger.gender }"/>
				${ticketorderPassenger.gender == '1'?"男":"女" }
				</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].passengerType" value="${ticketorderPassenger.passengerType }"/>
				<c:if test="${ticketorderPassenger.passengerType == '0' }">成人</c:if>
				<c:if test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if>
				<c:if test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
				</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].passengerTitle" value="${ticketorderPassenger.passengerTitle}"/>
				<c:if test="${ticketorderPassenger.passengerTitle == '0'}">普通</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '2' }">学生</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '3' }">劳务</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '4' }">海员</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '5' }">探亲</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '6' }">移民</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '7' }">外交官</c:if>
				<c:if test="${ticketorderPassenger.passengerTitle == '8' }">军人</c:if>
				</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].certType" value="${ticketorderPassenger.certType }"/>
					<c:if test="${ticketorderPassenger.certType == '1'}">护照</c:if>
					<c:if test="${ticketorderPassenger.certType == '2'}">港澳通行证</c:if>
					<c:if test="${ticketorderPassenger.certType == '3'}">台湾通行证</c:if>
					<c:if test="${ticketorderPassenger.certType == '4'}">外交官</c:if> 
				</td>
				<td>
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].certNo" value="${ticketorderPassenger.certNo }"/>
				&nbsp;${ticketorderPassenger.certNo }</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].phone" value="${ticketorderPassenger.phone }"/>
				${ticketorderPassenger.phone }</td>
				<td>&nbsp;
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].ticketNo" value="${ticketorderPassenger.ticketNo }"/>
				${ticketorderPassenger.ticketNo }
				<!-- 证件过期时间 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].expiredtime" value="${ticketorderPassenger.expiredtime }"/>
				<!-- 发证国家 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].certificateCountry " value="${ticketorderPassenger.certificateCountry }"/>
				<!-- 国籍 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].national" value="${ticketorderPassenger.national }"/>
				<!-- 始发国地址 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].docaR" value="${ticketorderPassenger.docaR }" />
				<!-- 目的国地址 -->
				<input type="hidden" name="endorsesheetPassengers[${passengStatus.index }].docaR" value="${ticketorderPassenger.docaD }"/>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
<!-- 原订单金额 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>原订单金额：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>类型</th>
				<th>票面价(销售)</th>
				<th>计奖价</th>
				<th>代理费</th>
				<th>政策返点</th>
				<th>供应商开票费用</th>
				<th>税费</th>
				<th>单张结算价</th>
			</tr>
			<c:forEach items="${singlePriceList }" var="singlePrice">
			<tr>
				<td>&nbsp;${singlePrice.passengerName }</td>
				<td>&nbsp;<c:if test="${singlePrice.passengerType == '0' }">成人</c:if>
				<c:if test="${singlePrice.passengerType == '1' }">儿童</c:if>
				<c:if test="${singlePrice.passengerType == '4' }">婴儿</c:if></td>
				<td>&nbsp;${singlePrice.distributorTicketPrice }</td>
				<td>&nbsp;${singlePrice.supplierTicketPrice }</td>
				<td>&nbsp;${singlePrice.distributorCommission }</td>
				<td>&nbsp;${singlePrice.supplierCommission }</td>
				<td>&nbsp;${singlePrice.distributorSettlementPrice }</td>
				<td>&nbsp;${singlePrice.taxCn }</td>
				<td>&nbsp;${singlePrice.supplierSettlementPrice }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="singlecon">
			<ul class="clear">
<!-- 				<li class="redreset" style="float: none;"><span>&lowast;</span</li> -->
				<li style="float: none; margin:10px 0;" class="meallispan">
					<span class="redreset" style="margin-right: 0 !important;">&lowast;</span>
					<span>改签费用总计：&nbsp;待确认</span>
					<span>改签费用：&nbsp;待确认</span>
					<span>改签税费：&nbsp;待确认</span>
					<span>报销凭证费用：&nbsp;待确认</span>
<!-- 					<span>保险费用：&nbsp;待确认</span> -->
				</li>
			</ul>
		</div>
</div>
<!-- 改签费用金额 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>改签费用金额：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>改签差价</th>
				<th>改签税费</th>
				<th>改签代理费率</th>
				<th>改签手续费</th>
				<th>改钱服务费</th>
				<th>改签附加费</th>
			</tr>
			<c:forEach items="${tPassengerList }" var="passegner" varStatus="passIndex">
				<tr>
					<td>&nbsp;${passegner.passengerName }</td>
					<td>&nbsp;待确认</td>
					<td>&nbsp;待确认</td>
					<td>&nbsp;待确认</td>
					<td>&nbsp;待确认</td>
					<td>&nbsp;待确认</td>
					<td>&nbsp;待确认</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<!-- 消费凭证 -->
<input type="hidden" name="invoice" value="${detail.invoice }"/>
<span style="display: ${detail.invoice == '1' ?'':'none'  }">
<!-- 行程单费用明细 -->
<div class="colorhei"></div>
<!-- 如果选择行程单 -->
<div class="aircraft airshow-pnr">
	<div class="effect-head">
		<p>报销凭证费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble airchide">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>发票类型</th>
				<th>填写金额不填税</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<c:forEach items="${invoiceList }" var="invoice" varStatus="idx" >
				<tr>
					<td>&nbsp;${invoice.invoiceName }</td>
					<td>&nbsp;${invoice.invoiceType=='1'?'发票':'行程单' }</td>
					<td>&nbsp;${invoice.openMoney }</td>
					<td>&nbsp;${invoice.certificateMoney }</td>
					<td>&nbsp;${invoice.taxpoint }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- <div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li class="redreset"><span>&lowast;</span>报销凭证费用总计：待确认</li>
			</ul>
		</div>
	</div> -->
</div>
<%-- <!-- 如果选择服务业发票 -->
<div class="aircraft airshow-pnr" style="display: none;">
	<div class="effect-head">
		<p>报销凭证费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble airchide">
		<table border="0" class="airctable">
			<tr>
				<th>发票抬头</th>
				<th>发票类型</th>
				<th>填写金额</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<c:forEach items="${invoiceList }" var="invoice" varStatus="idx" >
				<tr>
					<td>&nbsp;<input type="text" value="${invoice.invoiceName }" disabled="disabled" /></td>
					<td>&nbsp;<input type="text"  value="${invoice.invoiceType=='1'?'发票':'凭证费' }"  disabled="disabled" style="width: 45px" /></td>
					<td>&nbsp;<input type="text"  value="${invoice.openMoney }"  disabled="disabled" /></td>
					<td>&nbsp;<input type="text"  value="${invoice.certificateMoney }" disabled="disabled"  /></td>
					<td>&nbsp;<input type="text"  value="${invoice.taxpoint }" disabled="disabled"  /></td>
				</tr>
			</c:forEach>
		</table>
	</div> --%>
<!-- 	<div class="single"> -->
<!-- 		<div class="singlecon"> -->
<!-- 			<ul class="clear"> -->
<!-- 				<li><span class="redreset">&lowast;</span>报销凭证费用总计：待确认</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>

<!-- 邮寄地址 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>邮寄地址：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble clear">
		<div class="airchead">
			<ul class="clear">
				<li>收件人</li>
				<li>电话号码</li>
				<li>邮编</li>
				<li>费用</li>
				<li class="widtable">地址信息</li>
			</ul>
		</div>
		<div class="airchead-left fl">
			<div class="aircontop">
				<div class="hidetop">
					<c:forEach items="${recipientList }" var="recipient">
					<ul class="clear">
						<li>&nbsp;${recipient.recipientName }</li>
						<li>&nbsp;${recipient.phone }</li>
						<li>&nbsp;${recipient.mailNumber }</li>
						<li>&nbsp;${recipient.recipientMoney }</li>
						<li class="widtable">&nbsp;${recipient.address }</li>
					</ul>
					</c:forEach>
				</div>
			</div>
			<div class="airconbot clear">
			<span>邮寄时间：</span>
			<fmt:formatDate pattern="yyyy-MM-dd" value="${detail.goMainTraveldate}"/>
			<input type="hidden" name="goMainTraveldate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${detail.goMainTraveldate}"/>"/>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl" name="busiRemark" disabled="disabled">${ticketorderDetail.busiRemark }</textarea>
			</div>
		</div>
<!-- 		<div class="airchead-right fr"> -->
<!-- 			<button class="addquery addaircon">添加</button> -->
<!-- 			<button class="addquery romaircon">删除</button> -->
<!-- 		</div> -->
	</div>
</div>
</span>
<!-- 留言 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>留言：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="rosizehead" style="margin-bottom: 20px;">
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
	</div>
</div>
<!-- 改签PNR -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="mealapply">
		<span>改签PNR：</span>
		<input type="hidden" name="old_crs_pnr" value="${detail.crsPnr }"/>
		<input type="text" name="new_crs_pnr" id="new_crs_pnr"  class="mealinput">
		<span><span class="redreset">&lowast;</span>说明:XXXXXXXXXXXXXXX</span>
	</div>
	<div class="mealapply">
		<span>改签类型：</span>
		<select name="endorseDetailType" id="endorseDetailType">
			<option value="">请选择改签类型</option>
			<option value="1">申请改期</option>
			<option value="2">申请改程</option>
			<option value="3">申请升舱</option>
		</select>
	</div>
	<div class="butbotom">
			<button type="button" onclick="submitOrder();">申请</button>
	</div>	
</div>
<input type="hidden" name="endorseIds" value="${endorseIds }"/>
</form>

<script type="text/javascript">
function submitOrder(){
	if("" == $("#endorseDetailType").val() || "" == $("#new_crs_pnr").val() || "" == $("#text_remark").val()){
		alert("请选择改签类型或输入pnr或输入改签描述");
		return false;
	}
	$("#form01").submit();
}
//留言限制字数
function textdown(e) {
    textevent = e;
    if (textevent.keyCode == 8) {
        return;
    }
    if (document.getElementById('textarea').value.length >= 50) {
        alert("此处限50字")
        if (!document.all) {
            textevent.preventDefault();
        } else {
            textevent.returnValue = false;
        }
    }
}
function textup() {
    var s = document.getElementById('textarea').value;
    //判断ID为text的文本区域字数是否超过50个 
    if (s.length > 50) {
        document.getElementById('textarea').value = s.substring(0,50);
    }
}
</script>
</body>
</html>