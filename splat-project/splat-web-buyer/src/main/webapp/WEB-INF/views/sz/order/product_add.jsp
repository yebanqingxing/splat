<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PNR增加旅客</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/pnrdetail.css">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
<!-- 时间控件的  -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery/jquery-ui.min.css"></link>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-ui.min.js"></script>
<script type="text/javascript">
var objclear,
	objval;
$(function(){
	  		$(".minTime").datepicker({
	  	      defaultDate: "+1w",
	  	      dateFormat:"yy-mm-dd",
	  	      numberOfMonths: 2,
	  	      changeMonth: true,
	  	      changeYear: true,
	  		  minDate: '',//今天之前可不可以选中 设置值就是今天之前的日期不能用 设置为空呢就是可以选择之前的时
	  	      duration:'normal',
	  		  showButtonPanel:true,//是否显示清空按钮
	  		  closeText:"",
	  		  yearRange:'c-100:c+100',//前100年和后100年
	  		  beforeShow : function(input){
	  		  	objclear = input;
	  		  }
	  	   
	  	    });
	    $(".ui-datepicker-close").live("click",function(){
	    	objclear.value = "";
			   var dates = $("#minTime,#maxTime");
			  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
			   dates.datepicker("option", "minDate", '0');
			   dates.datepicker("option", "maxDate", null);
	    })
	    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
	    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
	    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
	    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
	    $('.time').datepicker('option', 'monthNamesShort', ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月']); 
});
</script>
<style>
	.widtable{
		display: list-item;
	}
	.pnrflight ul li {
		width:33%;
	}
</style>
</head>
<body>

<div class="addpolicy">
	<img src="${ctxStatic}/order/images/roleadd.png"/>
	<p>PNR增加旅客</p>
</div>
<form id="saveOrder" action="${ctx }/order/tbTicketorderDetail/saveOrderDetail" method="post">
<!-- 航班行程信息 -->
<div class="pnrcontet">
	<div class="clear">
		<div class="effect-head fl">
			<p>航班行程信息：</p>
			<img src="${ctxStatic}/order/images/right.jpg">
		</div>
	</div>
	<div  class="orderkli showhide">
		 <div id="ticketDiv" class="ordercon">
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
				<c:forEach items="${ticketorderSegments }" var="segment" varStatus="indexRoute">
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">
						<input type="hidden" name="ticketorderSegments[${indexRoute.index}].classCode" value="${segment.classCode }"></input>
						<input type="hidden" name="ticketorderSegments[${indexRoute.index}].operatingAirline" value="${segment.operatingAirline }"/>
						<input type="hidden" name="ticketorderSegments[${indexRoute.index}].operatingFlightNo" value="${segment.operatingFlightNo }"/>&nbsp;${segment.operatingFlightNo }<br/> &nbsp;${segment.operatingAirline }</li>
						<li class="licenter">
							<p><input type="hidden" name="ticketorderSegments[${indexRoute.index}].departureTime" value="<fmt:formatDate value="${segment.departureTime }" pattern="yyyy-MM-dd HH:mm" />"></input>&nbsp;<fmt:formatDate value="${segment.departureTime }" pattern="yyyy-MM-dd HH:mm" /> </p>
							<p>
							<input type="hidden" name="ticketorderSegments[${indexRoute.index}].departureCode" value="${segment.departureCode }">&nbsp;${segment.departureAddress }
							<input type="hidden" name="ticketorderSegments[${indexRoute.index}].departureAddress" value="${segment.departureAddress }">
							&nbsp;${segment.departureAddress }</p>
						</li>
<!-- 						<li class="licenter"> -->
<%-- 							<p>&nbsp;${segment.duration }</p> --%> 
<%-- 							&nbsp;<img src="${ctxStatic }/order/images/pnrhang.png"> --%>
<!-- 						</li> -->
						<li class="licenter">
							<p><input type="hidden" name="ticketorderSegments[${indexRoute.index}].arriveTime" value="<fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-dd HH:mm"/>"/>&nbsp;<fmt:formatDate value="${segment.arriveTime}" pattern="yyyy-MM-dd HH:mm"/></p>
							<p><input type="hidden" name="ticketorderSegments[${indexRoute.index}].arriveCode" value="${segment.arriveCode }"/>&nbsp;${segment.arriveAddress}
							<input type="hidden" name="ticketorderSegments[${indexRoute.index}].arriveAddress" value="${segment.arriveAddress }"/>
							</p>
						</li>
					</ul>
				</div>
				</c:forEach>
				
			</div>
		</div>
			
		</div>
</div>
<!-- 乘机人信息 -->
<div class="colorhei"></div>
<div class="aircraft" id="ticket">
	<div class="effect-head">
		<p>乘机人信息：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble" >
		<table id="passengerTableId" border="0" class="airctable">
			<tr>
				<th>姓名（英文）</th>
				<th>性别</th>
				<th>旅客类型</th>
				<th>旅客身份</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>证件有效期</th>
				<th>证件签发地</th>
				<th>出生日期</th>
				<th>国籍</th>
			</tr>
			<c:forEach items="${passengerList }" var="passenger" varStatus="statusIndex">
				<tr>
					<td>
					
					
					<c:if test="${passenger.passengerName == null|| passenger.passengerName == '' }">
					<input class="passengerClass" type="text" name="ticketorderPassengers[${statusIndex.index }].passengerName" value="${passenger.passengerName }"/></c:if>
					<c:if test="${passenger.passengerName != null || passenger.passengerName != ''}"><input type="hidden" name="ticketorderPassengers[${statusIndex.index }].passengerName" value="${passenger.passengerName }"/>${passenger.passengerName }</c:if>
					
					</td>
					<td>
					<c:if test="${passenger.gender == '' ||  passenger.gender == null}">
					<select class="passengerClass" name="ticketorderPassengers[${statusIndex.index }].gender">
						<option value="">请选择</option>
						<option value="1" >男</option>
						<option value="2">女</option>
					</select>
					</c:if>
					<c:if test="${passenger.gender != '' &&  passenger.gender != null}">
					<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].gender" value="${passenger.gender }">${passenger.gender == '1'?'男':'女' }
					</c:if>
					</td>
					<td>
					<c:if test="${passenger.passengerType == '' ||  passenger.passengerType == null}">
					<select class="passengerClass" name="ticketorderPassengers[${statusIndex.index }].passengerType">
						<option value="">请选择</option>
						<option value="0" >成人</option>
						<option value="1">儿童</option>
						<option value='4'>婴儿</option>
					</select>
					</c:if>
					<c:if test="${passenger.passengerType != null || passenger.passengerType != '' }">
						<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].passengerType" value="${passenger.passengerType}"/>
					<c:if test="${passenger.passengerType == '0' }">成人</c:if>
					<c:if test="${passenger.passengerType == '1' }">儿童</c:if>
					<c:if test="${passenger.passengerType == '4' }">婴儿</c:if>
					
					</c:if>
					
					</td>
					<td>
					<c:if test="${passenger.passengerTitle == '' ||  passenger.passengerTitle == null}">
						<select class="passengerClass" name="ticketorderPassengers[${statusIndex.index }].passengerTitle">
							<option value=''>请选择</option>
							<option value='0' >普通</option>
							<option value="2">学生</option>
							<option value="3">劳务</option>
							<option value="4">海员</option>
							<option value="5">探亲</option>
							<option value="6">移民</option>
							<option value="7">外交官</option>
							<option value="8">军人</option>
							<option value="9">婴儿</option>
							<option value="10">儿童</option>
						</select>
					</c:if>
					<c:if test="${passenger.passengerTitle != '' && passenger.passengerTitle != null  }">
					<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].passengerTitle" value="${passenger.passengerTitle }"/>
					<c:if test="${passenger.passengerTitle == '0'}">普通</c:if>
					<c:if test="${passenger.passengerTitle == '2' }">学生</c:if>
					<c:if test="${passenger.passengerTitle == '3' }">劳务</c:if>
					<c:if test="${passenger.passengerTitle == '4' }">海员</c:if>
					<c:if test="${passenger.passengerTitle == '5' }">探亲</c:if>
					<c:if test="${passenger.passengerTitle == '6' }">移民</c:if>
					<c:if test="${passenger.passengerTitle == '7' }">外交官</c:if>
					<c:if test="${passenger.passengerTitle == '8' }">军人</c:if>
					<c:if test="${passenger.passengerTitle == '9' }">婴儿</c:if>
					<c:if test="${passenger.passengerTitle == '10' }">儿童</c:if>
					</c:if>
					</td>
					<td>
					<c:if test="${passenger.certType  == '' || assenger.certType == null  }">
					<!-- 证件类型(0, "身份证"), pp(1, "护照"), pass_hkmc(2, "港澳通行证"), pass_tw(3, "台湾通行证"), mtp(4, "台胞证"), homecard(5,
"回乡证"), other(8, "其他"), unknown(9,"unknown") -->
					<select class="passengerClass" name="ticketorderPassengers[${statusIndex.index }].certType">
						<option value="">请选择</option>
						
						<option value="1">护照</option>
						<option value="2">港澳通行证</option>
						<option value="3">台湾通行证</option>
						<option value="4">外交官</option>
					</select>
					</c:if>
					<c:if test="${passenger.certType  != '' && passenger.certType != null}">
					<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].certType" value="${passenger.certType }"/></td>
					<c:if test="${passenger.certType == '1'}">护照</c:if>
					<c:if test="${passenger.certType == '2'}">港澳通行证</c:if>
					<c:if test="${passenger.certType == '3'}">台湾通行证</c:if>
					<c:if test="${passenger.certType == '4'}">外交官</c:if>
					</c:if>
					<td>
					<c:if test="${passenger.certNo == '' || passenger.certNo == null}">
						<input class="passengerClass" style="width: 60px;" type="text" name="ticketorderPassengers[${statusIndex.index }].certNo" value="${passenger.certNo }"/>
					</c:if>
					<c:if test="${passenger.certNo != '' && passenger.certNo != null}">
					<input  type="hidden" name="ticketorderPassengers[${statusIndex.index }].certNo" value="${passenger.certNo }"/>${passenger.certNo }
					</c:if>
					</td>
					<td>
					<c:if test="${passenger.expiredtime == '' ||passenger.expiredtime == null }">
					<input  readonly="readonly" style="width: 80px;" class="passengerClass doubledate ipticon time minTime" type="text"  name="ticketorderPassengers[${statusIndex.index }].expiredtime" value="${passenger.expiredtime }"/>
					</c:if>
					<c:if test="${passenger.expiredtime != '' && passenger.expiredtime != null }">
					<input class="" type="hidden" name="ticketorderPassengers[${statusIndex.index }].expiredtime" value="<fmt:formatDate value='${passenger.expiredtime }' pattern='yyyy-MM-dd'/>"/> <fmt:formatDate value="${passenger.expiredtime }" pattern="yyyy-MM-dd"/></td>
					</c:if>
					<td>
					<c:if test="${passenger.certificateCountry == '' || passenger.certificateCountry == null }">
					<input class="passengerClass" style="width: 60px;" type="text" name="ticketorderPassengers[${statusIndex.index }].certificateCountry" value="${passenger.certificateCountry }"/>
					</c:if>
					<c:if test="${passenger.certificateCountry != '' && passenger.certificateCountry != null }">
					<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].certificateCountry" value="${passenger.certificateCountry }"/>${passenger.certificateCountry }
					</c:if>
					</td>
					<td>
					<c:if test="${passenger.passengerBirthday == '' || passenger.passengerBirthday == null}">
					<input  readonly="readonly"class="passengerClass doubledate ipticon time minTime"  style="width: 80px;" class="doubledate ipticon time" type="text" name="ticketorderPassengers[${statusIndex.index }].passengerBirthday" value=""/>
					</c:if>
					<c:if test="${passenger.passengerBirthday != '' && passenger.passengerBirthday != null}">
						<input  type="hidden" name="ticketorderPassengers[${statusIndex.index }].passengerBirthday" value="<fmt:formatDate value="${passenger.passengerBirthday }" pattern="yyyy-MM-dd"/>"/><fmt:formatDate value="${passenger.passengerBirthday }" pattern="yyyy-MM-dd"/>
					</c:if>
					 </td>
					<td>
					<c:if test="${passenger.national == '' || passenger.national == null}">
					<input class="passengerClass" type="text" style="width: 60px;" name="ticketorderPassengers[${statusIndex.index }].national" value="${passenger.national}"/>
					</c:if>
					<c:if test="${passenger.national != '' && passenger.national  != null}"></c:if>
					<input type="hidden" name="ticketorderPassengers[${statusIndex.index }].national" value="${passenger.national}"/>${passenger.national}
					
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- <div class="pnraddyes clear">
			<label class="cheinput-pnr">
				<input  type="checkbox" onclick="is" checked="checked">
				<span>是否需要保险：</span>
			</label>
			<label class="select-pnr">
				<span>保险类型</span>
				<select>
					<option>11111</option>
					<option>22222</option>!
					<option>33333</option>
				</select>
			</label>
			<label class="mobile-pnr">
				<span>电话号码：</span>
				<input type="text" name='phone'>
			</label>
		</div> -->
<!-- 		<div class="pnraddyes clear"> -->
<!-- 			<label class="cheinput-pnr"> -->
<!-- 				<input name='hid_invocie'  value="1" onclick="isReimbursement(this)"  type="checkbox"> -->
<!-- 				<span>是否需要报销凭证：</span> -->
<!-- 			</label> -->
<!-- 			<span id="spanInvoice"  style="display:none"> -->
<!-- 			<label class="cheinput-pnr" style="margin: 0 10px;"> -->
<!-- 				<input type="radio" id="invoice_1" value="2"  name='invoice1'> -->
<!-- 				<span>行程单</span> -->
<!-- 			</label> -->
<!-- 			<label class="cheinput-pnr" style="margin: 0 10px;"> -->
<!-- 				<input type="radio" id="invoice_2" value="1" name='invoice1'> -->
<!-- 				<span>服务业发票</span> -->
<!-- 			</label> -->
<!-- 			</span> -->
<!-- 		</div> -->
	</div>
</div>
<script type="text/javascript">
//点击保存是否选择行程单
function findInvoice(){
	var invoice=$("input[name='invoice1']:checked").val();
	//如果不是undefined就是选择了行程单或者发票
	if(undefined != invoice){
		$("#xingchengdan").css("display","")
		//document.getElementById("xingchengdan").display="";
		//行程单
		if("2" == invoice){
			$("#invoice").css("display","")
			$("#invoice1").css("display","none")
			//document.getElementById("invoice").display="";
		}
		
		else if('1' == invoice){
			$("#invoice1").css("display","")
			$("#invoice").css("display","none")
			//document.getElementById("invoice1").display="";
		}
	}
}

//是否需要报销凭证
function isReimbursement(obj){
	if(obj.checked){
		$("#spanInvoice").css("display","");
		$("#invoice_1").attr("checked",true);
		$("#sss").css("display","")
		$("#divNone").css("display","");
		//控制邮寄地址显示
		$("#mailAdress").css("display","");
		$("#xingchengdan").css("display","")
		//行程单出来
		$("#invoice").css("display","");
	}else{
		$("#spanInvoice").css("display","none")
		$("#invoice_1").attr("checked",false);
		$("#sss").css("display","none")
		$("#divNone").css("display","none")
		$("#invoice_2").attr("checked",false);
		$("#invoice1").css("display","none");
		//取消的话将原来的价格清空金额在计算一下
		$("#ticketorderDetail_pzPrice_1").val("0.0");
		//机票费用
		var ticketorderDetail_settlementPrice_1=$("#ticketorderDetail_settlementPrice_1").val();
		//所有的税费
		var ticketorderDetail_dutyPrice_1=$("#ticketorderDetail_dutyPrice_1").val();
		$("#ticketorderDetail_totalPrice_1").val(Number(ticketorderDetail_dutyPrice_1*1+ticketorderDetail_settlementPrice_1*1).toFixed(2));
		//控制邮寄地址不显示
		$("#mailAdress").css("display","none");
	}
}
</script>
<!-- pnr号 -->
<input type="hidden" name="crsPnr" value="${rt.pnr }"/>
<!-- 乘机费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>乘机费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table id="singlePriceTable" border="0" class="airctable">
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
			<c:forEach items="${singlePriceList }" var="singlePrice" varStatus="statusSinglePrice">
				<tr>
					<td><input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].passengerName" value="${singlePrice.passengerName }"/>${singlePrice.passengerName }</td>
					<td>
					<input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].passengerType" value="${singlePrice.passengerType }"/>
					<c:if test="${singlePrice.passengerType == 1 }">儿童</c:if>
					<c:if test="${singlePrice.passengerType == 4 }">婴儿</c:if>
					<c:if test="${singlePrice.passengerType == 0 }">成人</c:if>
					</td>
					<td><input tip='_ticketPrice' type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].distributorTicketPrice" value="${singlePrice.distributorTicketPrice=='null'?0:singlePrice.distributorTicketPrice }"/>${singlePrice.distributorTicketPrice=='null'?0:singlePrice.distributorTicketPrice } </td>
					<td><input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].supplierTicketPrice" value="${singlePrice.supplierTicketPrice==null?0:singlePrice.supplierTicketPrice }"/> ${singlePrice.supplierTicketPrice==null?0:singlePrice.supplierTicketPrice  }</td>
					<td><input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].distributorCommission" value="${singlePrice.distributorCommission==null?0:singlePrice.distributorCommission }"/>${singlePrice.distributorCommission==null?0:singlePrice.distributorCommission }</td>
					<td><input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].supplierCommission" value="${singlePrice.supplierCommission == null ?0:singlePrice.supplierCommission}"/>${singlePrice.supplierCommission == null ?0:singlePrice.supplierCommission}</td>
					<td><input type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].distributorSettlementPrice" value="${singlePrice.distributorSettlementPrice == null?0:singlePrice.distributorSettlementPrice}"/>${singlePrice.distributorSettlementPrice  == null?0:singlePrice.distributorSettlementPrice}</td>
					<td><input tip="_taxCn" type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].taxCn" value="${singlePrice.taxCn == null?0:singlePrice.taxCn }"/>${singlePrice.taxCn== null?0:singlePrice.taxCn}</td>
					<td><input tip="_settlementPriceJie" type="hidden" name="ticketorderSinglePrices[${statusSinglePrice.index }].supplierSettlementPrice" value="${singlePrice.supplierSettlementPrice == null?0:singlePrice.supplierSettlementPrice }"/>${singlePrice.supplierSettlementPrice == null?0:singlePrice.supplierSettlementPrice }</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
		<div class="single">
		<div class="singlecon">
<!-- 			<ul class="clear"> -->
<!-- 				<li><span class="redreset">&lowast;</span>报销凭证费用总计：60000</li> -->
<!-- 			</ul> -->
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单总费用：<input value="${detail.totalPrice }" name="totalPrice"  readonly="readonly" id="ticketorderDetail_totalPrice_1"  style="width: 60px" /></li>
				<li>机票：<input value="${detail.settlementPrice }" name="settlementPrice" id="ticketorderDetail_settlementPrice_1" readonly="readonly" style="width: 60px" /></li>
				<li>机票税费：<input value="${detail.dutyPrice }" name="dutyPrice" readonly="readonly"  id="ticketorderDetail_dutyPrice_1"  style="width: 60px" /></li>
				<li>报销凭证费：<input value="${detail.pzPrice == null ?0:detail.pzPrice }" name="pzPrice"  id="ticketorderDetail_pzPrice_1" readonly="readonly" style="width: 60px" /></li>
				<li>
					<div class="pnraddyes clear" style="margin-top:0;">
						<label class="cheinput-pnr" style="margin-left:4px;">
							<input name='hid_invocie'  value="1" onclick="isReimbursement(this)"  type="checkbox">
							<span>是否需要报销凭证：</span>
						</label>
						<span id="spanInvoice"  style="display:none">
						<label class="cheinput-pnr" style="margin: 0 10px;">
							<input type="radio" onclick="findInvoice();" id="invoice_1" value="2"  name='invoice1'>
							<span>行程单</span>
						</label>
						<label class="cheinput-pnr" style="margin: 0 10px;">
							<input type="radio" onclick="findInvoice();" id="invoice_2" value="1" name='invoice1'>
							<span>服务业发票</span>
						</label>
						</span>
					</div>
				</li>
<!-- 			

	<li>保险费：<input value="0" readonly="readonly" style="width: 60px" /></li> -->
			 <!--  隐藏的结算价 -->
			 <input type="hidden" id="settlementPriceJie" value=""/>
			</ul>
		</div>
	</div>
</div>
<!-- 供应商的信息 -->
<!-- 供应商的id -->
<input type="hidden" name="supplierProductNo" value="${suppliId}"/>		
<!-- 供应商的名字 -->		
<%-- <input type="hidden" name="supplierName" value="${policyRoules}"/>				 --%>
<div id="xingchengdan" style="display: none">
<!-- 行程单费用明细 -->
<div id="divNone" class="colorhei" style="display:none"></div>
<!-- 如果选择行程单 -->
<div id="sss" class="aircraft airshow-pnr">
	<div class="effect-head">
		<p>报销凭证费用明细：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div id="invoice" style="display: none" class="aircrafble airchide">
	
		<table id="invoiceTable" border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>发票类型</th> 
				<th>填写金额不填税</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<c:forEach items="${singlePriceList}" var="single" varStatus="singleStatus">
				<tr>
				<td><input type="hidden" name="invoices[${singleStatus.index+1 }].invoiceName" value="${single.passengerName }"/>${single.passengerName }</td>
				<td><input type="hidden" name="invoices[${singleStatus.index+1 }].invoiceType" value="2"/>行程单</td>
				<td>
				<input type="hidden" style="width: 60px;" tip="_totalTax" value="${single.supplierSettlementPrice - single.taxCn}"/>
				<input type="text" style="width: 60px;"  tip="_openMoney" name="invoices[${singleStatus.index+1 }].openMoney" value=""/></td>
				<td><input type="text" style="width: 60px;"  readonly="readonly" name="invoices[${singleStatus.index+1 }].certificateMoney" value="2"/></td>
				<td><input type="text" style="width: 60px;" tip="_tax"  readonly="readonly" name="invoices[${singleStatus.index+1 }].taxpoint" value="0.0" /></td>
			</tr>
			</c:forEach>
		</table>
<!-- 		<div class="vation-pnr fr" style="width: 200px; margin-top: 50px;"> -->
<!-- 		<button type="button" class="addpnrshow" onclick="addInvoice();">增加</button></div> -->
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		var partn=/^[+-]?\d+(\.\d+)?$/;
		$("#invoiceTable input").change(function(){
			var arr = $(this).parent().parent().find("input");
			//alert($(arr[2]).val())
			if(!partn.test($(arr[3]).val())){
				 alert("请正确输入金额");
				 $(this).val("0.0")
				 return false;
			}
			//获取没有税费的价格 手输入的不得超过不含税的60%
			var noTax=$(arr[2]).val();
			//alert( $(arr[3]).val())
			if(noTax * 0.6 < ($(arr[3]).val()*1 - noTax*1)){
				alert("填写的金额不得超过不含税的结算价的60%");
				$(this).val("0.0");
				 $(arr[5]).val("0.0");
				return false;
			}
			//公式 （填入-原来的）*0.06；税点
			var taxInfo=($(arr[3]).val()*1-$(arr[2]).val()*1)*0.06;
			if(taxInfo < 0 ){
				taxInfo=0;
			}
			$(arr[5]).val( Number(taxInfo).toFixed(1))
			//$(arr[9]).val( Number(total).toFixed(2));
			
			var jp=0,tax=0,pz=0,total=0,bx=0;
			$("#invoiceTable input").each(function(){
				if($(this).attr("tip") == '_tax'){
					tax = 2*1 + tax*1 + parseFloat($(this).val());
				}
			});
			//消费凭证用
			$("#ticketorderDetail_pzPrice_1").val(Number(tax).toFixed(1))
			//$("#ticketorderDetail_totalPrice_1").val(Number(total).toFixed(2));
			var ticketorderDetail_settlementPrice_1=$("#ticketorderDetail_settlementPrice_1").val();
			var ticketorderDetail_dutyPrice_1=$("#ticketorderDetail_dutyPrice_1").val();
			$("#ticketorderDetail_totalPrice_1").val(Number(parseFloat(ticketorderDetail_settlementPrice_1) +parseFloat(ticketorderDetail_dutyPrice_1)+parseFloat(tax)).toFixed(1));
		});	
		
		$(".changeinput").change(function(){
			var arr = $(this).parent().parent().find("input");
			//alert($(arr[2]).val())
			if(!partn.test($(arr[3]).val())){
				 alert("请正确输入金额");
				 $(this).val("0.0");
				 $(arr[4]).val('0.0');
				 return false;
			}
			//alert($(arr[2]).val());
			//所有的结算价
			var _settlementPriceJie=$("#settlementPriceJie").val();
			//alert($("#_settlementPriceJie").val())
			//所有的税费
			var ticketorderDetail_dutyPrice_1=$("#ticketorderDetail_dutyPrice_1").val();
			//alert("======"+$(arr[2]).val())
			var endPrice=_settlementPriceJie*1 - ticketorderDetail_dutyPrice_1*1;
			//alert(",,,,,"+endPrice)
			if(endPrice * 0.6 < ($(arr[2]).val() * 1- endPrice*1) ){
				alert("填写的金额不得超过不含税的结算价的60%");
				$(arr[2]).val("0.0");
				$(arr[4]).val('0.0');
				return false;
			}
			var price=($(arr[2]).val()*1 - (_settlementPriceJie*1 - ticketorderDetail_dutyPrice_1*1) )*0.06
			if(price * 1 <0 ){
				price=0;
			}
			$(arr[4]).val(Number(price).toFixed(1));
			$("#ticketorderDetail_pzPrice_1").val(Number(price*1+2*1).toFixed(1));
			var ticketorderDetail_settlementPrice_1=$("#ticketorderDetail_settlementPrice_1").val();
			var ticketorderDetail_dutyPrice_1=$("#ticketorderDetail_dutyPrice_1").val();
			$("#ticketorderDetail_totalPrice_1").val(Number(parseFloat(ticketorderDetail_settlementPrice_1)+parseFloat(ticketorderDetail_dutyPrice_1)+parseFloat(Number(price*1+2*1).toFixed(1))).toFixed(1));
		});
	}); 

	</script>
</div>

<!-- 如果选择服务业发票 -->
<div class="aircraft airshow-pnr" 		>
	<div id="invoice1" class="aircrafble airchide" style="display: none">
		<table id="invoiceTable1" border="0" class="airctable">
			<tr>
				<th>发票抬头</th>
				<th>发票类型</th>
				<th>填写金额</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<tr>
				<td><input style="width: 100px;" type="text" name="invoices[0].invoiceName" /></td>
				<td><input type="hidden" name="invoices[0].invoiceType" value="1"/>发票</td>
				<td><input style="width: 60px;"  class="changeinput" type="text" name="invoices[0].openMoney" /></td>
				<td><input style="width: 60px;"  type="text" name="invoices[0].certificateMoney" readonly="readonly" value="2"/></td>
				<td><input style="width: 60px;"  type="text" name="invoices[0].taxpoint"  readonly="readonly"/></td>
			</tr>
		</table>
<!-- 		<div class="vation-pnr fr" style="width: 200px; margin-top: 50px;"> -->
<!-- 		<button type="button" class="addpnrshow" onclick="addInvoice1();">增加</button></div> -->
	</div>
	<script type="text/javascript">
	</script>
	</div>
</div>

<script type="text/javascript">
function isMy(obj){
	if($(obj).attr("checked") == 'checked'){
		//客人
		if($(obj).val() == 1){
			$("#my").css("display","none");
			$("#noMy").css("display","");	
		}//自己
		else if($(obj).val() == 2){
			$("#my").css("display","");
			$("#noMy").css("display","none");
		}
	}
}
</script>
<span id="mailAdress" style="display: none">
<!-- 邮寄地址 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>邮寄地址：</p>
		<img src="${ctxStatic}/order/images/right.jpg">
	</div>
	<div class="aircrafble clear">
		<div class="youhead-pnr">
			<label class="cheinput-pnr" style="margin-left: 30px;">
				<input type="radio" value="1" onclick="isMy(this)" name="youhead">
				<span>客人</span>
			</label>
			<label class="cheinput-pnr" style="margin-left: 20px;">
				<input type="radio" value="2" onclick="isMy(this)" name="youhead" checked="checked">
				<span>自己</span>
			</label>
		</div>
		<div id="my" class="clear contentshow-pnr">
			<div style="width: 753px;" class="fl">
				<div class="airchead" style="border-bottom: none;">
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
								<li><input  type='text' style="width: 60px;" name='recipient[0].recipientName' value='${user.name }'></li>
								<li><input type='text' style="width: 60px;" name='recipient[0].phone' value="${user.phone }"></li>
								<li><input type='text' style="width: 60px;" name='recipient[0].mailNumber' size="7"></input></li>
								<li><input type='hidden' name='recipient[0].recipientMoney' value='20'/>20</li>
								<li class="widtable"><input type='text' name='recipient[0].address'/></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="vation-pnr fr" style="width: 200px; margin-top: 50px;">
				<button type="button">保存</button>
			</div>
		</div>
		
		<div id="noMy" style="display: none" class="clear contentshow-pnr">
			<div style="width: 753px;" class="fl">
				<div class="airchead" style="border-bottom: none;">
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
						<div class="hidetop showappend-pnr">
							<ul class="clear">
								<li><input class="widaddpnr" type='text' name='recipient[1].recipientName' size='7'></li>
								<li><input class="widaddpnr" type='text' name='recipient[1].phone' size="7"></li>
								<li><input class="widaddpnr" type='text' name='recipient[1].mailNumber' size="7"></li>
								<li><input class="widaddpnr" type='hidden' name='recipient[1].recipientMoney' value='20'/>20</li>
								<li class="widtable"><input class="widaddpnr" type='text' name='recipient[1].address'/></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="vation-pnr fr" style="width: 200px; margin-top: 50px;">
				<button type="button" class="addpnrshow">增加</button>
				<button type="button" class="vispnrshow">保存</button>
				<button type="button" class="show">删除</button>
			</div>
		</div>
		<div class="airconbot clear">
			<span>邮寄时间：</span>
			<input type="text" readonly="readonly" id="minTime" name="goMainTraveldate" class="fl shuruadd-pnr time minTime">
		</div>
		<div class="airconbot clear">
			<span>邮寄说明：</span>
			<textarea class="rosize fl" name="busiRemark"></textarea>
		</div>
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
		<form id="form" action="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/verifyOrder?orderNo=${detail.orderNo }" method="post">
        <textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
		<div class="butbotom">
			<button id="clickaddbut" onclick="submitOrder();" type="button">生成订单</button>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">

$(".show").bind("click",function(){
	var leng = $(".showappend-pnr ul").length;
	if(leng == 1){
		return;
	}else{
		$(".showappend-pnr").find("ul").eq(leng-1).remove();
	};
})
$(function(){
	//票面价格
	var settlementPrice=0;
	$("#singlePriceTable input[type != 'text']").each(function(){
		if($(this).attr("tip") == '_ticketPrice'){
			if($(this).val() != 'IT'){
				settlementPrice = parseFloat(settlementPrice) + parseFloat($(this).val()==null?0:$(this).val())
			}
			
		}
	});
	//赋值
	$("#ticketorderDetail_settlementPrice_1").val(Number(settlementPrice).toFixed(2));
	//税费
	var ticketorderDetail_dutyPrice_1 = 0;
	$("#singlePriceTable input[type != 'text']").each(function(){
		if($(this).attr("tip") == '_taxCn'){
			ticketorderDetail_dutyPrice_1=parseFloat(ticketorderDetail_dutyPrice_1) + parseFloat($(this).val()==null?0:$(this).val());
		}
	});
	$("#ticketorderDetail_dutyPrice_1").val(Number(ticketorderDetail_dutyPrice_1).toFixed(2));
	
	var total=parseFloat(settlementPrice) + parseFloat(ticketorderDetail_dutyPrice_1);
	$("#ticketorderDetail_totalPrice_1").val(Number(total).toFixed(2));
	
	var _settlementPriceJie=0;
	$("#singlePriceTable input[type != 'text']").each(function (){
		if($(this).attr("tip") == '_settlementPriceJie'){
			_settlementPriceJie=parseFloat(_settlementPriceJie) + parseFloat($(this).val()==null?0:$(this).val());
		}
	})
	$("#settlementPriceJie").val(Number(_settlementPriceJie).toFixed(2))
});
 


function submitOrder(){
	var isSubmit=true;
	$(".passengerClass").each(function(){
		if($(this).val() == ''){
			//获取焦点
			$(this).focus();
			isSubmit= false;
			return;
		}
	})
	
	//设置按钮失效
	if(isSubmit){
		$("#clickaddbut").attr('disabled',true)
		$("#saveOrder").submit();
	}else{
		alert("请填写乘机人信息");
		return ;
	}
	
}
function iFrameHeight() {   
	var ifm= document.getElementById("iframepage");   
	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	   ifm.width = subWeb.body.scrollWidth;
	}   
}    
var winhei = $(window).height();
var pnrhei = $(".head-mian").height() + 7;
var hei = winhei - pnrhei;
$(".pnr-content-right").css("minHeight",hei);

function reinitIframe(){
	var iframe = document.getElementById("iframepage");
	try{
		iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;

	}catch (ex){}

}
window.setInterval("reinitIframe()", 200);
var $wid = $(window).width() - 223;
$(".pnr-content-right").width($wid);
$("#clickaddbut").click(function(){
//点击出现页面确认
	var addhei = $('body',parent.document).height();
	$('.alertadd',parent.document).height(addhei);
	$('.alertadd',parent.document).show();
})
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