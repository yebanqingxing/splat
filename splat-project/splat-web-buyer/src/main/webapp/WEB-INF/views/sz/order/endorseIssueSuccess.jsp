<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改签完成后国际订单申请退票单</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/pnrdetail.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/ordersuc.js"></script>
</head>
<body>
<div class="addpolicy">
	<img src="<%=request.getContextPath() %>/static/order/images/orderwaste.png">
	<p>采购商取消订单</p>
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
				<ul class="orderconul clear leftconul">
					<li>订单号：<span class="redreset">${orderMap.orderNum }<input type="hidden" name="refundsheetNo" value="${orderMap.orderNum }"></span></li>
					<li class="ordermargin">|</li>
					<li>原始订单号：<span class="redreset">${orderMap.orderNo }<input type="hidden" name="orderNo" value="${orderMap.orderNo }"></span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>开票编码PNR：H78BRF</li>
					<li>预定编码PNR：H78BRF</li>
					<li>预订人：张三</li>
					<li class="liwid"><span>操作历史&or;</span></li>
				</ul>
				<ul class="clear">
					<li class="colorli">订单总费用：6500</li>
					<li>机票采购费用：5000</li>
					<li>税费：1200</li>
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
				<p class="fr pnrmarrig">税费：2000</p>
				<p class="fr pnrmarrig">票面价：5000</p>
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
					<li class="licenter">飞行时长<br>中转</li>
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<c:forEach  items="${tSegmentList }" var="segment">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter"><input type="checkbox" name="segId" value="${segment.id }">&nbsp;${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureCode }-${segment.arriveCode }</p>
						</li>
<!-- 						<li class="licenter"> -->
<!-- 							<p>2小时</p> -->
<%-- 							<img src="<%=request.getContextPath() %>/static/order/images/pnrhang.png"> --%>
<!-- 							<p class="transfer">1程中转</p> -->
<!-- 						</li> -->
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.arriveCode }</p>
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
				<th>票号</th>
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
				<td>${singlePrice.supplierTicketPrice }</td>
				<td>${singlePrice.distributorCommission }</td>
				<td>${singlePrice.supplierTax }</td>
				<td>${singlePrice.supplierCommission }</td>
				<td></td>
				<td>${singlePrice.supplierTax }</td>
				<td>${singlePrice.supplierSettlementPrice }</td>
			</tr>
		</table>
	</div>
</div>
<!-- 报销凭证费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>报销凭证费用明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
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
			<tr>
				<td>喵星人世界</td>
				<td>2016年3月20日</td>
				<td>喵星人世界喵星人世界</td>
				<td>XXXX</td>
				<td>100%</td>
			</tr>
		</table>
	</div>
</div>
<div class="aircraft">
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>报销凭证费用总计：60000</li>
			</ul>
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单费用总计：60000</li>
				<li>机票：6000</li>
				<li>机票税费：300</li>
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
	<div class="aircrafble clear" style="width: 753px;">
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
	</div>
</div>
<!-- 操作说明 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>操作说明：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble ">
		<table border="0" class="airctable">
			<tr>
				<th>名称</th>
				<th>操作记录</th>
				<th>留言</th>
				<th>时间日期</th>
			</tr>
			<tr>
			 <c:forEach items="${historyList }" var="history">
             <c:if test="${history.operatorName.contains('平台')==false }">
                <tr>
                    <c:choose>
                       <c:when test="${history.operatorName.contains('采购商')==true}">
                          <td>${history.operatorName }</td>
                       </c:when>
                       <c:when test="${history.operatorName.contains('采购商')==false}">
                          <td>${history.operatorName.split(":")[0] }</td>
                       </c:when>
                    </c:choose>
                     <td><div style="width:350px;white-space:nowrap;text-overflow:ellipsis;
                        overflow:hidden;" title="${history.previousOperation }">
                         ${history.previousOperation }</div></td>    
                   <td><div style="width:300px;white-space:nowrap;color:blue;text-overflow:ellipsis;
                        overflow:hidden;" title="${history.remark }">
                         ${history.remark }</div></td>
                    <td><fmt:formatDate value="${history.operationTime }" pattern="yyyy-MM-dd HH:mm" /> </td>
                </tr>
                </c:if>
            </c:forEach>
			</tr>
		</table>
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
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
		<div class="butbotom">
			<button>申请退票</button>
		</div>	
	</div>
</div>
</body>
</html>