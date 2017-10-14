<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商订单处理</title>
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
	<img src="${ctxStatic}/order/images/orderstage.png">
	<p>供应商订单处理</p>
</div>
<form action="${ctx }/order/ping/tbTicketorderDetail/editTicketorderDetailStatus?orderNo=${detail.orderNo }" method="post">
<input name="ticketorderDetail.orderNo" value="123123" type="hidden" />
<!-- 订单信息 -->
<div class="pnrcontet">
	<div class="effect-head clear">
		<p>订单信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
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
					<c:if test="${detail.orderStatus  == '1'}">下单成功</c:if>
                    <c:if test="${detail.orderStatus  == '5'}">异常订单</c:if>
                    <c:if test="${detail.orderStatus  == '7'}">取消订单</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset">
					<c:if test="${detail.currentStatus  == '1' }">正在核实价格和政策</c:if>
	                <c:if test="${detail.currentStatus  == '2' }">已经核实价格和政策</c:if>
	                <c:if test="${detail.currentStatus == '3' }">已督促供应商核实价格</c:if>
	                <c:if test="${detail.currentStatus == '4' }">已督促采购商支付</c:if>
	                <c:if test="${detail.currentStatus == '5' }">审核拒绝</c:if>
	                <c:if test="${detail.currentStatus == '7' || detail.currentStatus == '6'}">等待出票 </c:if>
	                <c:if test="${detail.currentStatus == '8' }">出票完成 </c:if>
	                <c:if test="${detail.currentStatus == '9' }">申请全额退款 </c:if>
	                <c:if test="${detail.currentStatus == '10' }">申请通过全额退款 </c:if>
	                <c:if test="${detail.currentStatus == '11' }">拒绝全额退款 ，等待出票</c:if>
                </span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">
					<c:if test="${detail.payStatus == '0' }">已支付</c:if>
					<c:if test="${detail.payStatus == '1' }">未支付</c:if>
					<c:if test="${detail.payStatus == '2' }">支付失败</c:if>
					<c:if test="${detail.payStatus == '3' }">未退款</c:if>
                    <c:if test="${detail.payStatus == '4' }">已退款</c:if>
					</span></li>
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
				
					<li>订单总费用：${detail.totalPrice }</li>
					<li>机票采购费用：${detail.settlementPrice }</li>
					<li>机票税费：${detail.dutyPrice }</li>
					<li>报销凭证费：${detail.pzPrice }</li>
<!-- 					<li>保险费：<input value="0" readonly="readonly" style="width: 60px" /></li> -->
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 乘机费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>采购商和供应商信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table>
			<tr>
				<td>&nbsp;供应商&nbsp;&nbsp;</td>
				<td>&nbsp;${detail.relevantClient }&nbsp;&nbsp;</td>
				<td>&nbsp;联系方式&nbsp;&nbsp;</td>
				<td>&nbsp;${office.phone }&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;采购商&nbsp;&nbsp;</td>
				<td>&nbsp;${detail.createAccount }&nbsp;&nbsp;</td>
				<td>&nbsp;联系方式&nbsp;&nbsp;</td>
				<td>&nbsp;${detail.contactPhone }&nbsp;&nbsp;</td>
			</tr>
		</table>
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
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
					<!-- <li class="licenter">飞行时长<br>中转</li> -->
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<c:forEach items="${segmentList }" var="segment">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">&nbsp;${segment.operatingFlightNo }<br>&nbsp;${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
						<%-- <li class="licenter"> 
							<p>&nbsp;${segment.duration}</p> 
							&nbsp;<img src="${ctxStatic }/order/images/pnrhang.png">
<!-- 							<p class="transfer">1程中转</p> -->
						</li> --%>
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
		<p>乘机费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable" id="sing_detail">
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
			<c:forEach items="${singlePriceList }" var="spl" varStatus="idx">
				<tr>
					<td>${spl.passengerName }</td>
					<td>
					<c:if test="${spl.passengerType == '0' }">成人</c:if>
					<c:if test="${spl.passengerType == '1' }">儿童</c:if>
					<c:if test="${spl.passengerType == '4' }">婴儿</c:if></td>
					<td>${spl.distributorTicketPrice }</td>
					<td>${spl.supplierTicketPrice }</td>
					<td>${spl.distributorCommission }</td>
					<td>${spl.supplierCommission }</td>
					<td>${spl.distributorSettlementPrice }</td>
					<td>${spl.taxCn }</td>
					<td>${spl.supplierSettlementPrice }</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	<div class="aircraft" >
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单总费用：${detail.totalPrice }</li>
				<li>机票：${detail.settlementPrice }</li>
				<li>机票税费：${detail.dutyPrice }</li>
				<li>报销凭证费：${detail.pzPrice }</li>
<!-- 				<li>保险费：<input value="0" readonly="readonly" style="width: 60px" /></li> -->
			</ul>
		</div>
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
	<div class="aircrafble aircshow" id="pz_detail">
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
					<td>${invoice.invoiceName }</td>
					<td>${invoice.invoiceType=='1'?'发票':'行程单' }</td>
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
				<fmt:formatDate pattern="yyyy-MM-dd" value="${ detail.goMainTraveldate}"/>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl" disabled="disabled">${detail.busiRemark }</textarea>
			</div>
		</div>
	</div>
</div>
</span>
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
    <div class="aircrafble showhide" style="height:144px; overflow-y:scroll;" >
        <table border="0" class="airctable">
            <tr style="position:absolute; width:960px; background:#FFF; border-radius:7px 7px 0 0;">
                <th style="width:180px;">名称</th>
                <th style="width:300px;">操作记录</th>
                <th style="width:300px;">留言</th>
                <th style="width:180px;">时间日期</th>
            </tr>
            <tr></tr>
             <c:forEach items="${historyList }" var="history">
                        <tr>
                            <td style="width:180px;">${history.operatorName }</td>
                            <td><div style="width:300px;white-space:nowrap;text-overflow:ellipsis;
                            overflow:hidden;" title="${history.previousOperation }">
                             ${history.previousOperation }</div></td>    
                            <td><div style="width:300px;white-space:nowrap;color:blue;text-overflow:ellipsis;
                            overflow:hidden;" title="${history.remark }">
                             ${history.remark }</div></td>
                            <td style="width:180px;"><fmt:formatDate value="${history.operationTime }" pattern="yyyy-MM-dd HH:mm" /> </td>
                        </tr>
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
	
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
		<input type="hidden" name="hid_currentStatus" id="hid_currentStatus" />
			<div class="butbotom">
			<c:if test="${(detail.currentStatus  == 2  && detail.payStatus == '1'  &&  detail.orderStatus != 7)  }">
				<button class="addquery" type="submit" onclick="order_operation('4')">督促采购商支付</button>
			</c:if>
			<c:if test="${((detail.currentStatus  == 7 || detail.currentStatus  == 11)  && detail.payStatus == 0  &&  detail.orderStatus != 7)  }">
				<!--  -->
				<button class="addquery" type="submit" onclick="order_operation('12')">督促供应商出票</button>
			</c:if>
			<c:if test="${(detail.currentStatus  == 1 &&  detail.payStatus == '1' &&  detail.orderStatus != 7)  }">
				<button class="addquery" type="submit" onclick="order_operation('3')">督促供应商核实价格</button>
			</c:if>
			<c:if test="${((detail.currentStatus  == 7 || detail.currentStatus  == 11) && detail.currentStatus != 8 && detail.orderStatus != 7 && detail.orderStatus != 5 &&  detail.payStatus == '0' )  }">
				<button class="addquery" type="submit" onclick="order_operation('6')">确认采购商全额退款</button>
			</c:if>
			<c:if test="${((detail.currentStatus  == 6 || detail.currentStatus  == 9) && detail.currentStatus != 8 && detail.orderStatus != 7 && detail.orderStatus != 5 &&  (detail.payStatus == '0' || detail.payStatus == '3') )  }">
                <button class="addquery" type="submit" onclick="order_operation('11')">拒绝全额退款</button>
            </c:if>
			<button class="addquery addche" onclick="window.history.back(-1);">返回</button>
		</div>	
	</form>
	</div>
</div>
<script type="text/javascript">
function order_operation(type){
	$("#hid_currentStatus").val(type);
}
//计奖的部分*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
$(document).ready(function(){
	
	$("#sing_detail input[type !='hidden']").change(function(){
		//$.each(function(i,n){
			//alert(i);
		//});
	
		//$(this).parent().parent().remove();
		var arr=$(this).parent().parent().find("input");
	//	alert(parseFloat($(arr[4]).val())+parseFloat($(arr[8]).val()));
		var total=parseFloat($(arr[4]).val())+parseFloat($(arr[8]).val())+parseFloat($(arr[9]).val());
		$(arr[10]).val( Number(total).toFixed(2));
		total=0;
		var tx_total=0;
		$("#sing_detail input[tip = '_t']").each(function(){
			total=total+parseFloat($(this).val());
			
		})
		$("#sing_detail input[tip = '_tx']").each(function(){
			tx_total=tx_total+parseFloat($(this).val());
		})
		
		var pm_total=0;
		$("#sing_detail input[tip = '_pm']").each(function(){
			pm_total=pm_total+parseFloat($(this).val());
		})
		$("#ticketorderDetail_totalPrice").val(Number(total).toFixed(2));
		$("#ticketorderDetail_dutyPrice").val(Number(tx_total).toFixed(2));
		
		$("#ticketorderDetail_settlementPrice").val(Number(pm_total).toFixed(2));
		
		//
		$("#ticketorderDetail_totalPrice_1").val(Number(total).toFixed(2));
		$("#ticketorderDetail_dutyPrice_1").val(Number(tx_total).toFixed(2));
		
		$("#ticketorderDetail_settlementPrice_1").val(Number(pm_total).toFixed(2));
		//var total=$(arr[2]).val()*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
			//alert($(arr[2]).val());
			//$(this).parentsUntil("td").find("");
	});	
	
	$("#pz_detail input[type !='hidden']").change(function(){
		var pz_total=0;
		$("#pz_detail input[tip = '_pz']").each(function(){
			pz_total=pz_total+parseFloat($(this).val());
		})
		$("#ticketorderDetail_pzPrice").val(Number(pz_total).toFixed(2));
		$("#ticketorderDetail_pzPrice_1").val(Number(pz_total).toFixed(2));
	});
});
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