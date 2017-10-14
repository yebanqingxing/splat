<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单成功出票完成</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
<script type="text/javascript" src="${ctxStatic}/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
<style>
	.airchide{ overflow-x:hidden; overflow-y:scroll;}
</style>
<style>
	.pnrflight ul li {
		width:33%;
	}
</style>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/order/images/orderstage.png">
	<p>订单成功出票完成</p>
</div>
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
					<li>订单号：<span class="redreset">${detail.orderNo }</span></li>
					<li class="ordermargin">|</li>
					<li>订单状态：<span class="redreset">
					<span class="redreset">
					<c:if test="${detail.orderStatus  == '1'}">下单成功</c:if>
					<c:if test="${detail.orderStatus  == '5'}">异常订单</c:if>
					<c:if test="${detail.orderStatus  == '7'}">取消订单</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>操作状态：<span class="redreset">
					<c:if test="${detail.currentStatus  == '1' }">正在核实价格和政策</c:if>
	                <c:if test="${detail.currentStatus  == '2' }">已经核实价格和政策</c:if>
	                <c:if test="${detail.currentStatus == '5' }">审核拒绝</c:if>
	                <c:if test="${detail.currentStatus == '7' ||ticketorderDetail.currentStatus == '6'}">等待出票 </c:if>
	                <c:if test="${detail.currentStatus == '8' }">出票完成 </c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>支付状态：<span class="redreset">
					<c:if test="${detail.payStatus == '0' }">已支付</c:if>
					<c:if test="${detail.payStatus == '1' }">未支付</c:if>
					<c:if test="${detail.payStatus == '2' }">支付失败</c:if>
					</span></li>
					<li class="ordermargin">|</li>
					<li>创建时间：<span class="redreset"><fmt:formatDate value="${detail.createTime }" pattern="yyyy-MM-dd HH:mm" /> 
					</span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>开票编码PNR：${detail.crsPnr }</li>
					<li>预定编码PNR：${detail.crsPnr }</li>
					<li>预订人：${detail.createAccount }</li>
<!-- 					<li class="liwid"><span>操作历史&or;</span></li> -->
				</ul>
				<ul class="clear">
					<li>订单总费用：${detail.totalPrice }</li>
					<li>机票采购费用：${detail.settlementPrice }</li>
					<li>机票税费：${detail.dutyPrice } </li>
					<li>报销凭证费：${detail.pzPrice }</li>
<!-- 					<li>保险费：<input value="0" readonly="readonly" style="width: 60px" /></li> -->
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
						<li class="pnrone licenter">&nbsp;${segment.operatingFlightNo}&nbsp;${segment.operatingAirline }</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter"> -->
<%-- 							<p>&nbsp;${segment.duration}</p> --%>
<%-- 							<img src="<%=request.getContextPath() %>/status/order/images/pnrhang.png"> --%>
<!-- 							<p class="transfer">1程中转</p> -->
<!-- 						</li> -->
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-ss HH:mm"/></p>
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
	<tr class="backche">
		
		<td>
		<span style="display:block; float:left; width:30%; text-align:right; margin-top:12px;">
		<c:if test="${ticketorderPassenger.voidRefundEndorse == null || ticketorderPassenger.voidRefundEndorse == ''  }">
		<input class="ticket" style="margin-right:12px;"  type="checkbox" name="passengerId" value="${ticketorderPassenger.id}">
		</c:if>
		</span>
		<span style="float:left; width:70%; text-align:left;">${ticketorderPassenger.passengerName }</span></td>
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
		<c:if test="${detail.payStatus == '0' }">
			<div class="tabletext">
				<button class="addquery addche addmargiright" type="button" onclick="endordesheet(this)">申请改签</button>
				<button class="addquery addche addmargiright" type="button" onclick="refundsheetDetail(this)">申请退票</button>
				<button class="addquery addche addmargiright" type="button" onclick="voidSheetDetail(this)">申请废票</button>
			</div>
		</c:if>
		<%-- <c:if test="${(detail.orderStatus  == 1 && detail.currentStatus  == 6 && detail.payStatus == 0)}">
                <button class="addquery addche" onclick="refundsheetDetail();" type="button" >申请全额退款</button>
	    </c:if> --%>
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
			<c:forEach items="${singlePriceList }" var="spl">
				<tr>
					<td>&nbsp;${spl.passengerName }</td>
					<td>&nbsp;
					<c:if test="${spl.passengerType == 1 }">儿童</c:if>
					<c:if test="${spl.passengerType == 4 }">婴儿</c:if>
					<c:if test="${spl.passengerType == 0 }">成人</c:if>
					</td>
					<td>&nbsp;${spl.distributorTicketPrice }</td>
					<td>&nbsp;${spl.supplierTicketPrice }</td>
					<td>&nbsp;${spl.distributorCommission }</td>
					<td>&nbsp;${spl.supplierCommission }</td>
					<td>&nbsp;${spl.distributorSettlementPrice }</td>
					<td>&nbsp;${spl.taxCn }</td>
					<td>&nbsp;${spl.supplierSettlementPrice }</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	<div class="aircraft">
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
<!-- 				<li><span class="redreset">&lowast;</span>报销凭证费用总计：60000</li> -->
			</ul>
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单总费用：${detail.totalPrice }</li>
				<li>机票：${detail.settlementPrice }</li>
				<li>机票税费：${detail.dutyPrice }</li>
				<li>报销凭证费：${detail.pzPrice }</li>
			</ul>
		</div>
	</div>
</div>
</div>
<span style="display: ${detail.invoice == '1' ?'':'none'  }" >
<!-- 报销凭证费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
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
</div>

<!-- 邮寄地址 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>邮寄地址：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
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
				<c:forEach items="${recipientList }" var= "recipient">
					<ul class="clear">
						<li>${recipient.recipientName }</li>
						<li>${recipient.mobile }</li>
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
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl" disabled="disabled">${ticketorderDetail.busiRemark }</textarea>
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
	<div class="aircrafble showhide"  style="height:144px; overflow-y:scroll;">
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
	<div class="colorhei colorhide" style="display:none;margin-top: 20px;"></div>
	<div class="tabletext">
			<button  class="addquery addche" onclick="window.history.back(-1);">返回</button>
	</div>
</div>

<input type="hidden" id="hid_createTime" name="hid_createTime" value="<fmt:formatDate value='${detail.createTime}' pattern='yyyy/MM/dd HH:mm'/>"/>
<script type="text/javascript">
//申请退票
function refundsheetDetail(obj){
	
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});  
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		$(obj).attr("disabled",true)
		window.location.href="${ctx}/refundsheetdetail/refundsheetDetail/refundsheetDetail?passIds="+chk_value.substring(1)+"&orderNo=${detail.orderNo }"
	}
	//alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value); 
}


//申请废票
function voidSheetDetail(obj){
	
	
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		var voidsheetTime = '${voidsheetTime}';
		if(voidsheetTime != 'false'){
			$(obj).attr("disabled",true);
			window.location.href="${ctx}/order/voidsheetDetail/voidsheetDetail?passIds="+chk_value.substring(1)+"&orderNo=${detail.orderNo }"
		}else{
			alert("废票时间已截止，请联系平台");
		}
		
	} 
	
	}

//申请改签
function endordesheet(obj){
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		$(obj).attr("disabled",true);
		window.location.href="${ctx}/order/endorsesheetDetail/toEndorsesheetDetail?endorseIds="+chk_value.substring(1)+"&orderNo=${detail.orderNo }"
	}
	
}
</script>
</body>
</html>