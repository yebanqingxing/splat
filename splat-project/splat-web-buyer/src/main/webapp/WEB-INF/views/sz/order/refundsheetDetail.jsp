<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退票处理</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
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
	<img src="${ctxStatic}/order/images/orderstage.png"  style="margin-top:11px;">
	<p>退票处理</p>
</div>

<!-- 订单信息 -->
<div class="pnrcontet">
	<div class="effect-head clear">
		<p>退订单信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="orderkli">
		<div class="ordercon">
			<div class="orderconhead">
				<ul class="orderconul clear">
					<li>订单号：<span class="redreset">
					${detail.refundsheetNo }
					</span></li>
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">
					<!-- 退票单状态 1 下单成功 2 取消退票 3 退票完成 4 异常订单 5 等待退票 -->
	                <c:if test="${detail.refundsheetStatus == '1' }">下单成功</c:if>
	                <c:if test="${detail.refundsheetStatus == '2' }">取消退票</c:if>
	                <c:if test="${detail.refundsheetStatus == '4' }">异常订单</c:if>
	                </span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset">
					<!-- 操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 退票确认(等待确认) 4 审核通过 5 退票操作(采购商同意退票)  -->
	                <c:if test="${detail.currentStatus == '1' }">等待审核</c:if>
	                <c:if test="${detail.currentStatus == '2' }">拒绝审核</c:if>
	                <c:if test="${detail.currentStatus == '3' }">等待退票</c:if>
	                <c:if test="${detail.currentStatus == '4' }">审核通过</c:if>
	                <c:if test="${detail.currentStatus == '5' }">退票完成</c:if>
	                </span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">
					<!-- 支付状态 0 已经退款 1 未退款 2 退款失败 -->
	                <c:if test="${detail.payStatus == '0' }">已退款</c:if>
	                <c:if test="${detail.payStatus == '1' }">未退款</c:if>
	                <c:if test="${detail.payStatus == '2' }">退款失败</c:if>
	                </span></li>
					<li class="ordermargin">|</li>
					<li>创建时间：<span class="redreset"><fmt:formatDate value="${detail.createTime }" pattern="yyyy-MM-dd HH:mm" /> 
					</span></li>
				</ul>
			</div>
				<div class="orderconbom-left">
				<ul class="clear">
					<li style="width: 300px">原始订单号：${detail.orderNo }
<%-- 					<a href="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/findticketorderDetail?orderNo=${detail.orderNo }">${detail.orderNo }</a> --%>
					</li>
					<li style="width: 300px">申请退票人：${detail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li style="width: 300px">原始订单PNR：${detail.crsPnr }</li>
				</ul>
				<ul class="clear">
					<li class="colorli">退票结算总价：${detail.supplierTotSettlementPrice }</li>
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
				<!-- <p class="fr pnrmarrig">税费：0</p>
				<p class="fr pnrmarrig">票面价：0</p> -->
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
<!-- 					<li class="licenter">飞行时长<br>中转</li> -->
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<c:forEach items="${segmentList }" var="segment">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">&nbsp;${segment.operatingFlightNo }<br>${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter">  -->
<%-- 							<p>&nbsp;${segment.duration}</p>  --%>
<%-- 							<img src="${ctxStatic }/order/images/pnrhang.png"> --%>
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
		<c:forEach items="${passengerList }" var="ticketorderPassenger">
	<tr>
		<td>
		${ticketorderPassenger.passengerName }
		</td>
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
			<c:if test="${ticketorderPassenger.certType == '1'}">护照</c:if>
			<c:if test="${ticketorderPassenger.certType == '2'}">港澳通行证</c:if>
			<c:if test="${ticketorderPassenger.certType == '3'}">台湾通行证</c:if>
			<c:if test="${ticketorderPassenger.certType == '4'}">外交官</c:if>
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
		<p>原始乘机费用明细：</p>
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
			<c:forEach items="${singlePriceList }" var="spl">
				<c:forEach items="${passengerList }" var="pls">
					<c:if test="${spl.passengerName == pls.passengerName }">
						<tr>
							<td>${spl.passengerName }</td>
							<td><c:if test="${spl.passengerType == 1 }">儿童</c:if>
					<c:if test="${spl.passengerType == 4 }">婴儿</c:if>
					<c:if test="${spl.passengerType == 0 }">成人</c:if></td>
							<td>${spl.distributorTicketPrice }</td>
							<td>${spl.supplierTicketPrice }</td>
							<td>${spl.distributorCommission }</td>
							<td>${spl.supplierCommission }</td>
							<td>${spl.distributorSettlementPrice }</td>
							<td>${spl.taxCn }</td>
							<td>${spl.supplierSettlementPrice }</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</div>
<!-- 报销凭证费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>机票退款金额：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th style="width: 150px">姓名</th>
				<th  style="width: 150px">已用销售价</th>
				<th style="width: 100px">已用税</th>
				<th style="width: 160px">退票手续费</th>
				<th style="width: 100px">服务费</th>
				<th style="width: 100px">附加费</th>
				<th style="width: 150px">退票结算价</th>
			</tr>
			<c:forEach items="${passengerList }" var="pls" varStatus="idx">
				<tr>
			
					<td>${pls.passengerName }</td>
					<td>${pls.supplierUsedSellPrice }</td>
					<td>${pls.supplierUsedTax }</td>
					<td>${pls.supplierRefundCharge }</td>
					<td>${pls.supplierServiceCharge }</td>
					<td>${pls.supplierOtherCharge }</td>
					<td>${pls.supplierSettlementPrice }</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="aircraft">
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>退票总费用：${detail.supplierTotSettlementPrice }</li>
				
			</ul>
		</div>
	</div>
</div>

<!-- 操作说明 -->
<div class="colorhei"></div>
<div class="aircraft">
    <div class="clear">
        <div class="effect-head fl">
            <p>操作说明：</p>
            <img src="${ctxStatic}/order/images/right.jpg">
        </div>
        <div class="pnrclick fl">
                <img src="${ctxStatic}/images/clickpnr.png">
        </div>
    </div>
    <div class="aircrafble showhide" style="height:144px; overflow-y:scroll;">
        <table border="0" class="airctable">
            <tr style="position:absolute; width:960px; background:#FFF; border-radius:7px 7px 0 0;">
                <th style="width:180px;">名称</th>
                <th style="width:300px;">操作记录</th>
                <th style="width:300px;">留言</th>
                <th style="width:180px;">时间日期</th>
            </tr>
            <tr></tr>
             <c:forEach items="${historyList }" var="history">
            <c:if test="${history.operatorName.contains('平台')==false }">
                <tr>
                    <c:choose>
                       <c:when test="${history.operatorName.contains('采购商')==true}">
                          <td style="width:180px;">&nbsp;${history.operatorName }</td>
                       </c:when>
                       <c:when test="${history.operatorName.contains('采购商')==false}">
                          <td style="width:180px;">&nbsp;${history.operatorName.split(":")[0] }</td>
                       </c:when>
                    </c:choose>
                    <td><div style="width:300px;white-space:nowrap;text-overflow:ellipsis;
                        overflow:hidden;" title="${history.previousOperation }">
                         ${history.previousOperation }</div></td>    
                   <td><div style="width:300px;white-space:nowrap;color:blue;text-overflow:ellipsis;
                        overflow:hidden;" title="${history.remark }">
                         ${history.remark }</div></td>
                    <td style="width:180px;">&nbsp;<fmt:formatDate value="${history.operationTime }" pattern="yyyy-MM-dd HH:mm" /> </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
<!-- 留言 -->
<div class="colorhei" style="margin-top: 20px"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>留言：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="rosizehead">
		<table class="airctable">
		<!-- <tr>
			<th>序号</th>
			<th>发起人</th>
			<th>内容</th>
			<th>时间</th>
		</tr> -->
		<%-- <c:forEach items="${ticketorderMessageList }" var="msg" varStatus="index">
				<tr>
					<td>${index.index+1 }</td>
					<td>${msg.createUser }</td>
					<td>${msg.message }</td>
					<td><fmt:formatDate value="${msg.createTime }" pattern="yyyy-MM-ss HH:mm:ss"/></td>
				</tr>
			</c:forEach> --%>
	</table>
	<form id="form1" action="${ctx }/refundsheetdetail/refundsheetDetail/cancle?refundsheetNo=${detail.refundsheetNo }" method="post">
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
			<div class="butbotom">
			<c:if test="${detail.currentStatus  == 4  && detail.refundsheetStatus  == 1 && detail.payStatus  == 1}">
				<button class="addquery addche" onclick="order_operation(3)" type="button">确认退票</button>
			</c:if>
			<c:if test="${(detail.currentStatus  == 1 || detail.currentStatus  == 4 || detail.currentStatus  == 2) && detail.refundsheetStatus  == 1}">
				<button onclick="order_operation(5)" type="button" >取消退票</button>
				<button onclick="order_operation(6)" type="button" >督促供应商审核退票</button>
			</c:if>
			<%-- <c:if test="${detail.currentStatus  == 2 && detail.refundsheetStatus  == 1 }">
                <button onclick="order_operation(1)" type="button" >重新申请退票</button>
            </c:if> --%>
			<input type="hidden" id="hid_currentStatus" value="" name="hid_currentStatus" />
			<button class="addquery addche" type="button" onclick="window.history.back(-1);">返回</button>
		</div>	
	</form>
			
	</div>
</div>
<script type="text/javascript">
function order_operation(type){
	$("#hid_currentStatus").val(type);
	$("#form1").submit();
	
	
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