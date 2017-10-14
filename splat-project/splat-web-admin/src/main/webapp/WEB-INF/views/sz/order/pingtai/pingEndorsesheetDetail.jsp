<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台改签订单处理</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/order/css/pnrdetail.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/order/css/payment.css">
<script type="text/javascript"
	src="${ctxStatic}/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/order/js/ordersuc.js"></script>
<style type="text/css">

.transfertwo {
	margin-left: 50%;
}
</style>

<style>
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
						<li>订单状态：<span class="redreset">${detail.endorsesheetNo }</span></li>
						<li class="ordermargin">|</li>
						<li>订单状态：<span class="redreset"> <c:if
									test="${detail.endorseStatus == 1 }">下单成功</c:if> <c:if
									test="${detail.endorseStatus == 2 }">取消改签</c:if> <c:if
									test="${detail.endorseStatus == 3 }">异常订单</c:if>
						</span></li>
						<li class="ordermargin">|</li>
						<li>操作状态：<span class="redreset"> 
						<c:if test="${detail.currentStatus == 1 }">等待审核</c:if> 
						<c:if test="${detail.currentStatus == 2 }">审核通过</c:if> 
						<c:if test="${detail.currentStatus == 3 }">审核拒绝</c:if> 
						<%-- <c:if test="${detail.currentStatus == 4 }">等待审核</c:if>  --%>
						<c:if test="${detail.currentStatus == 5 }">改签完成</c:if>
						</span></li>
						<li class="ordermargin">|</li>
						<li>支付状态：<span class="redreset"> 
							<c:if test="${detail.payStatus == 0 }">已支付</c:if> 
							<c:if test="${detail.payStatus == 1 }">未支付</c:if> 
							<c:if test="${detail.payStatus == 2 }">支付失败</c:if>
						</span></li>
					</ul>
				</div>
				<div class="orderconbom-left">
					<ul class="clear">
						<li>原始单号：&nbsp;${detail.orderNo }</li>
						<li>原订单编码PNR：&nbsp;${detail.oriCrsPnr }</li>
						<li>改签申请人：&nbsp;${user.name }</li>
						<!-- <li class="liwid"><span>操作历史&or;</span></li> -->
					</ul>
					<ul class="clear">
						<li class="colorli"></li>
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
				<img
					src="${ctxStatic}/order/images/right.jpg">
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
						<li class="licenter">起飞时间<br>机场
						</li>
<!-- 						<li class="licenter">飞行时长<br>中转 -->
<!-- 						</li> -->
						<li class="licenter">到达时间<br>机场
						</li>
					</ul>
					<c:forEach items="${tSegmentList }" var="segment">
						<div class="marin">
							<ul class="clear ultwo">
								<li class="pnrone licenter">&nbsp;<input type="hidden"
									name="segId" value="${segment.id }">&nbsp;${segment.operatingFlightNo }<br>&nbsp;${segment.operatingAirline }</li>
								<li class="licenter">
									<p>
										&nbsp;
										<fmt:formatDate value="${segment.departureTime}"
											pattern="yyyy-MM-ss HH:mm:ss" />
									</p>
									<p>&nbsp;${segment.departureAddress }</p>
								</li>
<!-- 								<li class="licenter"> -->
<%-- 									<p>&nbsp;${segment.duration}</p> <img --%>
<%-- 									src="${ctxStatic}/order/images/pnrhang.png"> <!-- 							<p class="transfer">1程中转</p> --> --%>
<!-- 								</li> -->
								<li class="licenter">
									<p>
										&nbsp;
										<fmt:formatDate value="${segment.arriveTime}"
											pattern="yyyy-MM-ss HH:mm:ss" />
									</p>
									<p>&nbsp;${segment.arriveAddress }</p>
								</li>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- 供应商信息 -->
	<!-- 供应商名字 -->
	<!-- 改签描述 -->
	  <div class="colorhei"></div>
        <div class="aircraft">
            <div class="effect-head">
                <p>改签描述：</p>
                <img src="${ctxStatic}/order/images/right.jpg">
            </div>
            <div class="rosizehead" style="margin-bottom: 20px;">
            <textarea readonly="readonly" rows="3" cols="">${detail.remark }</textarea>
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
					<th>退票类型</th>
					<th>性别</th>
					<th>旅客类型</th>
					<th>旅客身份</th>
					<th>证件类型</th>
					<th>证件号码</th>
					<th>联系电话</th>
					<th>票号</th>
				</tr>

				<c:forEach items="${passengerList }" var="ticketorderPassenger"
					varStatus="passengStatus">
					<tr>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].passengerName"
							value="${ticketorderPassenger.passengerName}">${ticketorderPassenger.passengerName }</td>
						<!-- 旅客序号 -->
						<input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].passengerIndex"
							value="${ticketorderPassenger.passengerIndex }" />

						<td><select
							name="endorsesheetPassengers[${passengStatus.index }].endorseType"
							class="selectticke">
								<option value="1"
									<c:if test="${ticketorderPassenger.endorseType == 1 }">selected="selected"</c:if>>自愿</option>
								<option value="2"
									<c:if test="${ticketorderPassenger.endorseType == 2 }">selected="selected"</c:if>>非自愿</option>
						</select></td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].gender"
							value="${ticketorderPassenger.gender }" />
							${ticketorderPassenger.gender == '1'?"男":"女" }
						</td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].passengerType"
							value="${ticketorderPassenger.passengerType }" /> <c:if
								test="${ticketorderPassenger.passengerType == '0' }">成人</c:if> <c:if
								test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if> <c:if
								test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
						</td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].passengerTitle"
							value="${ticketorderPassenger.passengerTitle}" /> <c:if
								test="${ticketorderPassenger.passengerTitle == '0'}">普通</c:if> <c:if
								test="${ticketorderPassenger.passengerTitle == '2' }">学生</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '3' }">劳务</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '4' }">海员</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '5' }">探亲</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '6' }">移民</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '7' }">外交官</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '8' }">军人</c:if>
						</td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].certType"
							value="${ticketorderPassenger.certType }" /> <c:if test="${ticketorderPassenger.certType == '1'}">护照</c:if>
							<c:if test="${ticketorderPassenger.certType == '2'}">港澳通行证</c:if>
							<c:if test="${ticketorderPassenger.certType == '3'}">台湾通行证</c:if>
							<c:if test="${ticketorderPassenger.certType == '4'}">外交官</c:if>
						</td>
						<td><input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].certNo"
							value="${ticketorderPassenger.certNo }" />
							&nbsp;${ticketorderPassenger.certNo }</td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].phone"
							value="${ticketorderPassenger.phone }" />
							${ticketorderPassenger.phone }
						</td>
						<td>&nbsp; <input type="hidden"
							name="endorsesheetPassengers[${passengStatus.index }].ticketNo"
							value="${ticketorderPassenger.ticketNo }" />
							${ticketorderPassenger.ticketNo }
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
						<td>&nbsp;${singlePrice.passengerType }</td>
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
	<form id="form01" action="${ctx }/order/ping/endorsesheetDetail/editendorsesheetDetailStatus"
		method="post">
		<!-- 改签费用金额 -->
		<div class="colorhei"></div>
		<div class="aircraft">
			<div class="effect-head">
				<p>改签费用金额：</p>
				<img src="${ctxStatic}/order/images/right.jpg">
			</div>
			<div class="aircrafble">
				<table border="0" class="airctable" id="table01">
					<tr>
						<th>姓名</th>
						<th>改签差价</th>
						<th>改签税费</th>
						<th>改签代理费率</th>
						<th>改签手续费</th>
						<th>改钱服务费</th>
						<th>改签附加费</th>
						<th>合计</th>
					</tr>
					<c:forEach items="${passengerList }" var="passegner"
						varStatus="passIndex">
						<tr>
							<td>&nbsp; <input type="hidden"
								name="endorsesheetPassengers[${passIndex.index}].id"
								value="${passegner.id }" /> ${passegner.passengerName }
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_difference"
								name="endorsesheetPassengers[${passIndex.index}].endorsePriceDifference"
								value="${passegner.endorsePriceDifference }" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_taxes"
								name="endorsesheetPassengers[${passIndex.index}].endorsePriceTaxes"
								value="${passegner.endorsePriceTaxes}" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_commission"
								name="endorsesheetPassengers[${passIndex.index}].endorseCommission"
								value="${passegner.endorseCommission}" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_upgradefee"
								name="endorsesheetPassengers[${passIndex.index}].endorseUpgradeFee"
								value="${passegner.endorseUpgradeFee}" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_charge"
								name="endorsesheetPassengers[${passIndex.index}].endorseServiceCharge"
								value="${passegner.endorseServiceCharge}" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_surcharge"
								name="endorsesheetPassengers[${passIndex.index}].endorseSurcharge"
								value="${passegner.endorseSurcharge}" />
							</td>
							<td>&nbsp; <input type="text" style="width: 60px"
								tip="_total"
								name="endorsesheetPassengers[${passIndex.index}].endorseSurcharge"
								value="${passegner.endorseSurcharge}" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="singlecon">
			<ul class="clear">
						<li class="colorli">改签费用总金额：<input type="text"
							style="width: 60px;" id="endorseTotalCost" disabled="disabled" value="${detail.endorseTotalCost }"/></li>
					</ul>
			</div>
		</div>
		<!-- 计算价格 -->
		<script type="text/javascript">
        $(document).ready(function() {

            $("#table01 input[type !='hidden']").change(function() {
                var partn=/^[+-]?\d+(\.\d+)?$/;
                var arr = $(this).parent().parent().find("input");
                if(!partn.test($(arr[1]).val()) && !partn.test($(arr[2]).val()) && !partn.test($(arr[3]).val()) && !partn.test($(arr[4]).val()) 
                        && !partn.test($(arr[5]).val()) && !partn.test($(arr[6]).val())         
                ){
                     alert("请正确输入金额");
                     $(this).val("0.0")
                     return false;
                     
                }
                var total = parseFloat($(arr[1]).val()) * (1 - parseFloat($(arr[3]).val())/100)  +parseFloat($(arr[2]).val())+ parseFloat($(arr[4]).val())+ parseFloat($(arr[5]).val())+ parseFloat($(arr[6]).val());
                $(arr[7]).val(Number(total).toFixed(2));
                //获取每一行的值进行加
                var price = parseFloat($("#total_price").val())+ parseFloat(Number(total).toFixed(2));
                $("#total_price").val(price);
                //设置
                var jp = 0, tax = 0, pz = 0, total = 0, bx = 0;
                $("#table01 input[type !='hidden']").each(function() {
                    if ($(this).attr("tip") == '_total') {
                        total += parseFloat($(this).val());
                                    }
                    });
                $("#table01 input[type !='hidden']").each(function() {
                        if ($(this).attr("tip") == '_taxes') {
                            tax += parseFloat($(this).val());
                                    }
                });
                $("#table01 input[type !='hidden']").each(function() {
                    if ($(this).attr("tip") == '_total') {
                        jp += parseFloat($(this).val());
                                }
             });
                //改签税费
                $("#endorseTaxes").val(tax);
                //改签总金额
                $("#endorseTotalCost").val(jp);
               // $("#endorseUpgradeCost").val(total);
               // $("#endorseTotalCost1").val(parseFloat($("#endorseUpgradeCost").val())+ parseFloat($("#endorseReimburseCredentials").val())+ parseFloat($("#insuranceCosts").val()));
               // $("#endorseTotalCost").val(parseFloat($("#endorseUpgradeCost").val())+ parseFloat($("#endorseReimburseCredentials").val())+ parseFloat($("#insuranceCosts").val()));
            });

    });
		</script>
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
			    <div class="effect-head fl" >
			        <p>操作说明：</p>
			        <img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
			    </div>
			    <div class="pnrclick fl">
	                <img src="${ctxStatic}/order/images/clickpnr.png">
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
			<div class="rosizehead" style="margin-bottom: 20px;">
				<textarea name="tk_message" cols="50" rows="5" id="textarea" onKeyDown="textdown(event)"
        onKeyUp="textup()" onfocus="if(value=='限50字'){value=''}" onchange="textup()" 
        onblur="if (value ==''){value='限50字'}">限50字</textarea>
			</div>
		</div>
		<!-- 订单号 -->
		<input type="hidden" name="hid_endorseNo" value="${detail.endorsesheetNo }"/>
		<!-- 操作状态 -->
		<input type="hidden" name="hid_currentStatus" id="hid_currentStatus"/>
		<div class="butbotom">
			<c:if test="${detail.currentStatus == '1' && detail.endorseStatus == '1'}">
				<button type="button" onclick="submitOrder(2);">确认改签</button>
				<button type="button" onclick="submitOrder1(3);">拒绝改签</button>
			</c:if>
			
			<%-- <c:if test="${detail.currentStatus == 2  || detail.currentStatus == 4} ">
				<button type="button" onclick="submitOrder1(3);">拒绝改签</button>
			</c:if> --%>
			<!-- 
			以后会有拒绝改签
			 -->
			<button type="button" onclick="window.history.back(-1);">返回</button>
		</div>
		</div>
	</form>
	<script type="text/javascript">
		function submitOrder(type) {
			$("#hid_currentStatus").val(type);
			if ($("#endorseTotalCost").val() == 0
					|| '' == $("#endorseTotalCost").val()){
				if(confirm("您确定不填写价格吗？")){
					$("#form01").submit();
				}
				
			}
				
				
		}
		
		//拒绝
		function submitOrder1(type){
			$("#hid_currentStatus").val(type);
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