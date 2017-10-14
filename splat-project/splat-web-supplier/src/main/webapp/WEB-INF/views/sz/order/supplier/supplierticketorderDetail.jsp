<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商订单处理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/pnrdetail.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/ordersuc.js"></script>
</head>
<body>
<div class="addpolicy">
	<img src="<%=request.getContextPath() %>/static/order/images/orderstage.png">
	<p>供应商订单处理</p>
</div>
<!-- 订单信息 -->
<div class="pnrcontet">
	<div class="effect-head clear">
		<p>订单信息：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="orderkli">
		<div class="ordercon">
			<div class="orderconhead">
				<ul class="orderconul clear">
					<li>订单号：<span class="redreset">
					${detail.orderNo }
					</span></li>
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">
					<c:if test="${detail.orderStatus  == '1'}">已提交</c:if>
				<c:if test="${detail.orderStatus  == '2'}">已申签</c:if>
				<c:if test="${detail.orderStatus  == '3'}">已拒单</c:if>
				<c:if test="${detail.orderStatus  == '4'}">已签单</c:if>
				<c:if test="${detail.orderStatus  == '5'}">已预订</c:if>
				<c:if test="${detail.orderStatus  == '6'}">已出票</c:if>
				<c:if test="${detail.orderStatus  == '7'}">已删除</c:if>
				<c:if test="${detail.orderStatus  == '8'}">已申批</c:if>
				<c:if test="${detail.orderStatus  == '9'}">已初核</c:if>
				<c:if test="${detail.orderStatus  == '10'}">已复核</c:if>
				<c:if test="${detail.orderStatus  == '11'}">已批单</c:if>
				<c:if test="${detail.orderStatus  == '12'}">已核单</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset">
					<c:if test="${detail.currentStatus == '1' }">正在审核价格和政策</c:if>
					<c:if test="${detail.currentStatus == '2' }">已经审核价格和政策</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">
					<c:if test="${detail.payStatus == '0' }">已支付</c:if>
					<c:if test="${detail.payStatus == '1' }">未支付</c:if>
					<c:if test="${detail.payStatus == '2' }">支付失败</c:if></span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>开票编码PNR：</li>
					<li>预定编码PNR：${detail.crsPnr }</li>
					<li>预订人：${detail.createAccount }</li>
					<li class="liwid"><span>操作历史&or;</span></li>
				</ul>
				<ul class="clear">
					<li>订单总费用：6500</li>
					<li>机票采购费用：5000</li>
					<li>机票税费：${singlePrice.supplierTax }</li>
					<li>报销凭证费：6000</li>
					<li>保险费：200</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 航班行程信息 -->
<div class="colorhei"></div>
<div class="pnrcontet">
	<div class="clear">
		<div class="effect-head fl">
			<p>航班行程信息：</p>
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
				<p class="fr pnrmarrig">税收：2000</p>
				<p class="fr pnrmarrig">票面价：5000</p>
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
					<li class="licenter">飞行时长<br>中转</li>
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<c:forEach items="${segmentList }" var="segment">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">${segment.operatingAirline }</li>
						<li class="licenter">
							<p><fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>${segment.departureCode }-${segment.arriveCode }</p>
						</li>
<!-- 						<li class="licenter"> -->
<!-- 							<p>2小时</p> -->
<%-- 							<img src="<%=request.getContextPath() %>/status/order/images/pnrhang.png"> --%>
<!-- 							<p class="transfer">1程中转</p> -->
<!-- 						</li> -->
						<li class="licenter">
							<p><fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>${segment.arriveCode }</p>
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
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
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
			</tr>
		<c:forEach items="${passengerList }" var="ticketorderPassenger">
	<tr>
		<td>${ticketorderPassenger.passengerName }</td>
		<td>
		${ticketorderPassenger.gender == '1'?"男":"女" }
		</td>
		<td>
		<c:if test="${ticketorderPassenger.passengerType == '0' }">成人</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
		</td>
		<td>
		<c:if test="${ticketorderPassenger.passengerTitle == '0'}">普通</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '2' }">学生</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '3' }">劳务</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '4' }">海员</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '5' }">探亲</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '6' }">移民</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '7' }">外交官</c:if>
		<c:if test="${ticketorderPassenger.passengerTitle == '8' }">军人</c:if>
		</td>
		<td>
		<c:if test="${ticketorderPassenger.certType == '0' }">身份证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '1' }">护照</c:if> 
		<c:if test="${ticketorderPassenger.certType == '2' }">港澳通行证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '3' }">台湾通行证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '4' }">台胞证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '5' }">回乡证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '8' }">其他</c:if>
		<c:if test="${ticketorderPassenger.certType == '9' }">unknown</c:if> 
		</td>
		<td>${ticketorderPassenger.certNo }</td>
		<td>${ticketorderPassenger.phone }</td>
		<td>${ticketorderPassenger.ticketNo }</td>
	</tr>
	</c:forEach>
</table>
	</div>
</div>
<!-- 乘机费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>乘机费用明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
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
			<tr>
				<td>张三</td>
				<td></td>
				<td><input  name="supplierTicketPrice" value="${singlePrice.supplierTicketPrice }"></td>
				<td><input name="distributorCommission" value="${singlePrice.distributorCommission }"></td>
				<td><input name="supplierTax" value="${singlePrice.supplierTax }"></td>
				<td><input name="supplierCommission" value="${singlePrice.supplierCommission }"></td>
				<td></td>
				<td><input name="supplierTax" value="${singlePrice.supplierTax }"></td>
				<td><input name="supplierSettlementPrice" value="${singlePrice.supplierSettlementPrice }"></td>
			</tr>
		</table>
	</div>
</div>
<!-- 行程单费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>行程单费用明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble aircshow">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>发票类型</th>
				<th>票面价(销售)</th>
				<th>填写金额不填税</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<tr>
				<td>喵星人</td>
				<td>男</td>
				<td>成人</td>
				<td>XXX</td>
				<td>身份证</td>
				<td>100%</td>
			</tr>
		</table>
	</div>
</div>
<div class="aircraft">
	<div class="effect-head">
		<p>行程单费用总价：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单费用总计：60000</li>
				<li>机票：6000</li>
				<li>机票税收：300</li>
				<li>报销凭证费用：300</li>
				<li>保险费用：600</li>
			</ul>
		</div>
	</div>
</div>
<!-- 邮寄地址 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>邮寄地址：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
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
					<ul class="clear">
						<li>喵星人</li>
						<li>13953668199</li>
						<li>1008611</li>
						<li>2000</li>
						<li class="widtable">北京市昌平区回龙观北店嘉园13号楼三单元602</li>
					</ul>
				</div>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl"></textarea>
			</div>
		</div>
		<div class="airchead-right fr">
			<button class="addquery addaircon">添加</button>
			<button class="addquery romaircon">删除</button>
		</div>
	</div>
</div>
<!-- 留言 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>留言：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="rosizehead">
		<textarea></textarea>
		<div class="butbotom">
			<button ><a href="<%=request.getContextPath()%>/a/order/supplier/tbTicketorderDetail/editTicketorderDetailStatus?orderStatus=4&orderNo=${detail.orderNo }">确认订单</a></button>
			<button ><a href="<%=request.getContextPath()%>/a/order/supplier/tbTicketorderDetail/editTicketorderDetailStatus?orderStatus=3&orderNo=${detail.orderNo }">拒绝订单</a></button>
		</div>	
	</div>
</div>
</body>
</html>