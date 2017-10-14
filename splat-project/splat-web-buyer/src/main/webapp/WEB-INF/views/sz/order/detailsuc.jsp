<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生成订单成功—PNR导入</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<!-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/order.css"> -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
<script type="text/javascript" src="${ctxStatic}/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
</head>
<body>
<style>
	.pnrflight ul li {
		width:33%;
	}
</style>
<div class="addpolicy">
	<img src="${ctxStatic}/order/images/pnrdetail.png">
	<p>生成订单成功—PNR导入</p>
</div>
<div class="pnrcontet">
	<div class="effect-head clear">
		<p>订单信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="orderkli">
		<div class="ordercon">
			<div class="orderconhead">
				<ul class="orderconul clear">
					<li>订单号：<span class="redreset">${detail.orderNo }</span></li>
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">
					<c:if test="${detail.orderStatus  == '1'}">下单成功</c:if>
					<c:if test="${detail.orderStatus  == '5'}">异常订单</c:if>
					<c:if test="${detail.orderStatus  == '7'}">取消订单</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset">
					<c:if test="${detail.currentStatus == '1' }">正在审核价格和政策</c:if>
					<c:if test="${detail.currentStatus == '2' }">已经审核价格和政策</c:if>
					<c:if test="${detail.currentStatus == '3' }">请尽快核实价格</c:if>
					<c:if test="${detail.currentStatus == '4' }">已督促采购商支付</c:if>
					<c:if test="${detail.currentStatus == '5' }">审核拒绝</c:if>
					<c:if test="${detail.currentStatus == '6' }">全额退款</c:if>
					<c:if test="${detail.currentStatus == '7' }">等待出票</c:if>
					<c:if test="${detail.currentStatus == '8' }">出票完成</c:if>
				</span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">
				
					</span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>开票编码PNR：</li>
					<li>预定编码PNR：${detail.crsPnr }</li>
					<li>预订人：${detail.createAccount }</li>
<!-- 					<li class="liwid"><span>操作历史&or;</span></li> -->
				</ul>
				<ul class="clear">
					<li>订单总费用：${detail.totalPrice }</li>
					<li>机票采购费用：${detail.settlementPrice }</li>
					<li>机票税费：${detail.dutyPrice }</li>
					<li>报销凭证费：${detail.pzPrice }</li>
<!-- 					<li>保险费：</li> -->
				</ul>
			</div>
		</div>
	</div>
</div>
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
						<li class="pnrone licenter">&nbsp;${segment.operatingFlightNo}&nbsp; ${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter"> -->
<%-- 							<p>&nbsp;${segment.duration}</p> --%>
<%-- 							&nbsp;<img src="${ctxStatic}/order/images/pnrhang.png"> --%>
<!-- <!-- 							<p class="transfer">1程中转</p> --> 
<!-- 						</li> -->
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.arriveTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							<p>&nbsp;${segment.arriveAddress }</p>
						</li>
					</ul>
<!-- 					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div> -->
				</div>
				</c:forEach>
			
			</div>
		</div>
	
</div>
<!-- 乘机人信息 -->
<div class="colorhei" style="margin-top:20px;"></div>
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
			</tr>
			<c:forEach items="${passengerList }" var="ticketPassenger">
			<tr>
				<td>&nbsp;${ticketPassenger.passengerName }</td>
				<td>&nbsp;${ticketPassenger.gender == '1'?"男":"女" }</td>
				<td> &nbsp;
				<c:if test="${ticketPassenger.passengerType == '0' }">成人</c:if>
				<c:if test="${ticketPassenger.passengerType == '1' }">儿童</c:if>
				<c:if test="${ticketPassenger.passengerType == '4' }">婴儿</c:if></td>
				<td>
				<c:if test="${ticketPassenger.passengerTitle == '0'}">普通</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '2' }">学生</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '3' }">劳务</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '4' }">海员</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '5' }">探亲</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '6' }">移民</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '7' }">外交官</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '8' }">军人</c:if>
				<c:if test="${ticketPassenger.passengerTitle == '9' }">婴儿</c:if>
					<c:if test="${ticketPassenger.passengerTitle == '10' }">儿童</c:if>
				</td>
				<td> &nbsp;
					<c:if test="${ticketPassenger.certType == '1'}">护照</c:if>
					<c:if test="${ticketPassenger.certType == '2'}">港澳通行证</c:if>
					<c:if test="${ticketPassenger.certType == '3'}">台湾通行证</c:if>
					<c:if test="${ticketPassenger.certType == '4'}">外交官</c:if>
				</td>
				<td>&nbsp;${ticketPassenger.certNo}</td>
				<td>&nbsp;${ticketPassenger.phone}</td>
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
			<c:forEach items="${singlePriceList }" var="price">
			<tr>
				<td>&nbsp;${price.passengerName }</td>
				<td>&nbsp;
				<c:if test="${price.passengerType == 1 }">儿童</c:if>
				<c:if test="${price.passengerType == 4 }">婴儿</c:if>
				<c:if test="${price.passengerType == 0 }">成人</c:if></td>
				<td>&nbsp;${price.distributorTicketPrice }</td>
				<td>&nbsp;${price.supplierTicketPrice }</td>
				<td>&nbsp;${price.distributorCommission}</td>
				<td>&nbsp;${price.supplierCommission}</td>
				<td>&nbsp;${price.distributorSettlementPrice }</td>
				<td>&nbsp;${price.taxCn}</td>
				<td>&nbsp;${price.supplierSettlementPrice}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单费用总计：${detail.totalPrice }</li>
				<li>机票：${detail.settlementPrice }</li>
				<li>机票税费：${detail.dutyPrice }</li>
				<li>报销凭证费用：${detail.pzPrice }</li>
<!-- 				<li>保险费用：600</li> -->
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
			<fmt:formatDate pattern="yyyy-MM-dd" value="${ ticketorderDetail.goMainTraveldate}"/>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl" disabled="disabled">${ticketorderDetail.busiRemark }</textarea>
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
	<div class="rosizehead">
	<form id="form1" action="${ctx }/order/tbTicketorderDetail/verifyOrder?orderNo=${detail.orderNo }"  method="post">
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
		<div class="butbotom">
			<c:if test="${(detail.currentStatus == null || detail.currentStatus == '') && detail.orderStatus == '1' && detail.currentStatus != '5'}">
			<button onclick="verifyOrder(1)" type=submit>核实价格和政策</button>
			<button onclick="verifyOrder(17)" type="button">取消订单</button>
			</c:if>
			<button class="addquery addche" onclick="window.history.back(-1);">返回</button>
		<input type="hidden" id="hid_currentStatus" name="hid_currentStatus" value="" />
		</div>	
    </form>
	</div>
</div>

<script type="text/javascript">
//核实价格可政策
	function verifyOrder(data){
		 $("#hid_currentStatus").val(data);
		 $("#form1").submit();
		 //alert(1)
	}
		 //window.location.href="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/verifyOrder?orderNo=${detail.orderNo }&currentStatus=1";

	//取消过来修改状态
	function cancel(){
		window.location.href="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/cancel?orderNo=${detail.orderNo }";
	}
	/* $(function(){
		$(".pnrclick ").click(function(){
			$(".showhide").slideToggle();
		})
	}) */
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
    //判断ID为text的文本区域字数是否超过20个 
    if (s.length > 50) {
        document.getElementById('textarea').value = s.substring(0,50);
    }
}
</script>
</body>
</html>