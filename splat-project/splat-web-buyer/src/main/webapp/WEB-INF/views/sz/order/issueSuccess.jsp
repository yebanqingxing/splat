<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改签完成后国际订单申请退票单</title>
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
	<img src="${ctxStatic}/order/images/orderwaste.png"  style="margin-top:11px;">
	<p>采购商申请退单订单</p>
</div>
<form action="<%=request.getContextPath()%>/a/refundsheetdetail/refundsheetDetail/saveRefundsheetDetail" method="post" id="form1" enctype="multipart/form-data">
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
					<li>订单号：<span class="redreset">${orderMap.orderNum }<input type="hidden" name="refundsheetNo" value="${orderMap.orderNum }"></span></li>
					<li class="ordermargin">|</li>
					<li>原始订单号：<span class="redreset">${orderMap.orderNo }<input type="hidden" name="orderNo" value="${orderMap.orderNo }"></span></li>
				</ul>
			</div>
			<div class="orderconbom-left">
				<ul class="clear">
					<li>开票编码PNR：${detail.arlPnr }</li>
					<li>预定编码PNR：${detail.crsPnr }</li>
					<li>预订人：${detail.createAccount }</li>
<!-- 					<li class="liwid"><span>操作历史&or;</span></li> -->
				</ul>
				<ul class="clear">
					<li class="colorli">订单总费用：待核实</li>
					<li>机票采购费用：待核实</li>
					<li>税费：待核实</li>
					<li>报销凭证费：待核实</li>
<!-- 					<li>保险费：待核实</li> -->
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
				<c:forEach  items="${tSegmentList }" var="segment">
				<div class="marin" >
					<ul class="clear ultwo" >
						<li class="pnrone licenter" >
							<input type="checkbox" name="segId" value="${segment.id }">&nbsp;${segment.operatingAirline }
						</li>
						<li class="licenter">
							<p>&nbsp;<fmt:formatDate value="${segment.departureTime}" pattern="yyyy-MM-ss HH:mm:ss"/></p>
							<p>&nbsp;${segment.departureAddress }</p>
						</li>
						<%-- <li class="licenter">
							<p>&nbsp;${segment.duration}</p>
							<img src="${ctxStatic}/order/images/pnrhang.png">
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
			<c:forEach items="${tPassengerList }" var="ticketorderPassenger">
	<tr>
		<td><input type="hidden" name="orderPassengerId" value="${ticketorderPassenger.id }">${ticketorderPassenger.passengerName }</td>
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
				<c:forEach items="${tPassengerList }" var="pls">
					<c:if test="${spl.passengerName == pls.passengerName }">
						<tr>
							<td>&nbsp;${spl.passengerName }</td>
							<td>&nbsp;<c:if test="${spl.passengerType == 1 }">儿童</c:if>
					<c:if test="${spl.passengerType == 4 }">婴儿</c:if>
					<c:if test="${spl.passengerType == 0 }">成人</c:if></td>
							<td>&nbsp;${spl.distributorTicketPrice }</td>
							<td>&nbsp;${spl.supplierTicketPrice }</td>
							<td>&nbsp;${spl.distributorCommission }</td>
							<td>&nbsp;${spl.supplierCommission }</td>
							<td>&nbsp;${spl.distributorSettlementPrice }</td>
							<td>&nbsp;${spl.taxCn }</td>
							<td>&nbsp;${spl.supplierSettlementPrice }</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</div>
<!-- 供应商的信息 -->
<input type="hidden" name="supplierProductNo" value="${detail.supplierProductNo }"/>
<input type="hidden" name="supplierProductName" value="${detail.supplierName }"/>
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
				<th>姓名    </th>
				<th>已用销售价</th>
				<th>已用税      </th>
				<th>退票手续费</th>
				<th>服务费</th>
				<th>附加费</th>
				<th>退票结算价</th>
			</tr>
			<c:forEach items="${tPassengerList }" var="pls">
			<tr>
				<td>${pls.passengerName }</td>
				<td>待核实</td>
				<td>待核实</td>
				<td>待核实</td>
				<td>待核实</td>
				<td>待核实</td>
				<td>待核实</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
<!-- 消费凭证 -->
<input type="hidden" name="invoice" value="${detail.invoice }"/>
<div class="aircraft">
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>退票金额总计：待核实</li>
			</ul>
		</div>
	</div>
</div>


<div class="colorhei">上传退票相关证明</div>
<div class="aircraft"><input type="file" name="fileFields" >
<input type="file" name="fileFields" ></div>

<!-- 留言 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>留言：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="rosizehead">
		<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
		<div class="butbotom">
			<button type="button" onclick="refundsheetDetail();" >申请退票</button>
		</div>	
	</div>
</div>
</form>
<script type="text/javascript">
function refundsheetDetail(){
	var chk_value =""; 
	$('input[name="segId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		$("#form1").submit();
	}
	//alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value); 
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