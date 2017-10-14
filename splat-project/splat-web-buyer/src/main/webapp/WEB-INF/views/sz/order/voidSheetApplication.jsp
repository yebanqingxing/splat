<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际订单申请废票单</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/payment.css">
<script type="text/javascript" src="${ctxStatic}/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
</head>
<style>
.pnrflight ul li {
		width:33%;
		text-align: center;
	}
</style>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/order/images/titckapply.png">
	<p>国际订单申请废票单</p>
</div>
<form action="<%=request.getContextPath()%>/a/order/voidsheetDetail/saveVoidsheetDetail" method="post" enctype="multipart/form-data">
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
					<li>订单号：<span class="redreset">${orderMap.orderNum }<input type="hidden" name="voidsheetNo" value="${orderMap.orderNum }"></span></li>
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">下单成功</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset"></span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset"></span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>原始单号：${orderMap.orderNo }<input type="hidden" name="orderNo" value="${orderMap.orderNo }"></li>
					<li>原订单编码：${detail.crsPnr }<input type="hidden" name="crsPnr" value="${detail.crsPnr }"></li>
					<li>申请废票人：${user.name }</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 采购商名字 -->
<input type="hidden" name="relevantClient" value="${detail.relevantClient }">
<input type="hidden" name="contactPhone" value="${detail.contactPhone }"/>
<!-- 原始航班信息 -->
<div class="colorhei"></div>
<div class="pnrcontet">
	<div class="clear">
		<div class="effect-head fl">
			<p>原始航班信息：</p>
			<img src="${ctxStatic}/order/images/right.jpg">
		</div>
		<div class="pnrclick fl">
			<img src="${ctxStatic}/order/images/clickpnr.png">
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
						<li class="pnrone licenter">&nbsp;<input type="hidden" name="segId" value="${segment.id }">&nbsp;${segment.operatingFlightNo}&nbsp; ${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter"> -->
<%-- 							<p>&nbsp;${segment.duration}</p> --%>
<%--  							<img src="${ctxStatic}/order/images/pnrhang.png"> --%> 
<!-- 							<p class="transfer">1程中转</p> --> 
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
				<th>性别</th>
				<th>旅客类型</th>
				<th>旅客身份</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>联系电话</th>
				<th>票号</th>
			</tr>
			<c:forEach items="${tPassengerList }" var="ticketorderPassenger">
	<tr>
		<td>&nbsp;<input type="hidden" name="orderPassengerId" value="${ticketorderPassenger.id }">${ticketorderPassenger.passengerName }</td>
		<td>&nbsp;
		${ticketorderPassenger.gender == '1'?"男":"女" }
		</td>
		<td>&nbsp;
		<c:if test="${ticketorderPassenger.passengerType == '0' }">成人</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
		</td>
		<td>&nbsp;
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
		<c:if test="${ticketorderPassenger.certType == '1'}">护照</c:if>
		<c:if test="${ticketorderPassenger.certType == '2'}">港澳通行证</c:if>
		<c:if test="${ticketorderPassenger.certType == '3'}">台湾通行证</c:if>
		<c:if test="${ticketorderPassenger.certType == '4'}">外交官</c:if>
		</td>
		<td>&nbsp;${ticketorderPassenger.certNo }</td>
		<td>&nbsp;${ticketorderPassenger.phone }</td>
		<td>&nbsp;${ticketorderPassenger.ticketNo }</td>
	</tr>
	</c:forEach>
		</table>
	</div>
</div>
<!-- 消费凭证 -->
<input type="hidden" name="invoice" value="${detail.invoice }"/>
<!-- 原始机票金额 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>原始机票金额：</p>
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
				<td>&nbsp;<c:if test="${singlePrice.passengerType == 1 }">儿童</c:if>
					<c:if test="${singlePrice.passengerType == 4 }">婴儿</c:if>
					<c:if test="${singlePrice.passengerType == 0 }">成人</c:if></td>
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
</div>
<!-- 供应商商信息 -->
<!-- 供应商名字 -->
<input type="hidden" name="supplierProductName" value="${detail.supplierName }" />
<!-- 供应商的id -->
<input type="hidden" name="supplierProductNo" value="${detail.supplierProductNo }"/>

<!-- 废费金额 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>废费金额：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>废票手续费</th>
				<!-- <th>服务费</th>
				<th>废票结算价</th> -->
			</tr>
			<c:forEach items="${tPassengerList }" var="passenger">
			<tr>
				<td>&nbsp;${passenger.passengerName }</td>
				<td>&nbsp;待确认</td>
				<!-- <td>&nbsp;待确认</td>
				<td>&nbsp;待确认</td> -->
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="aircraft">
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li class="redreset"><span>&lowast;</span>废票结算总价：待确认</li>
			</ul>
		</div>
	</div>
</div>
<span style="display: ${detail.invoice == '1' ?'':'none'  }" >
<!-- 行程单费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>行程单费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble aircshow">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>发票类型</th>
				<th>填写金额不填税</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<c:forEach items="${invoiceList }" var="invoice">
				<tr>
					<td>${invoice.invoiceName }</td>
					<td>
					<c:if test="${invoice.invoiceType == '1'  }">发票</c:if>
					<c:if test="${invoice.invoiceType == '2'  }">行程单</c:if>
					</td>
					<td>${invoice.openMoney }</td>
					<td>${invoice.certificateMoney }</td>
					<td>${invoice.taxpoint }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
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
					<c:forEach items="${recipientList }" var= "recipient">
					<ul class="clear">
						<li>${recipient.recipientName }</li>
						<li>${recipient.phone }</li>
						<li>${recipient.mailNumber }</li>
						<li>${recipient.recipientMoney }</li>
						<li class="widtable">${recipient.address }</li>
					</ul>
					</c:forEach>
				</div>
			</div>
			<div class="airconbot clear">
			<span>邮寄时间：</span>
			<fmt:formatDate pattern="yyyy-MM-dd" value="${ detail.goMainTraveldate}"/>
			<input type="hidden" name="goMainTraveldate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${ detail.goMainTraveldate}'/>"/>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl" name="busiRemark" disabled="disabled">${detail.busiRemark }</textarea>
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
		<p>操作留言：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="rosizehead">
		<textarea name="message"></textarea>
		<div class="butbotom">
			<button type="submit">申请</button>
		</div>	
	</div>
</div>
</form>
</body>
</html>