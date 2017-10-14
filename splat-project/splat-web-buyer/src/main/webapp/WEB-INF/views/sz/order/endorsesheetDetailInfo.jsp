<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改签订单处理</title>
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

	.pnrflight ul li {
		width:33%;
		text-align: center;
	}
</style>
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
								 <%-- <c:if test="${detail.currentStatus == 4 }">等待审核</c:if> --%>
								 <c:if test="${detail.currentStatus == 5 }">改签完成</c:if>
						</span></li>
						<li class="ordermargin">|</li>
						<li>支付状态：<span class="redreset"> <c:if
									test="${detail.payStatus == 0 }">已支付</c:if> <c:if
									test="${detail.payStatus == 1 }">未支付</c:if> <c:if
									test="${detail.payStatus == 2 }">支付失败</c:if>
						</span></li>
					</ul>
				</div>
				<div class="orderconbom-left">
					<ul class="clear">
						<li>原始单号：&nbsp;${detail.orderNo }</li>
						<li>原订单编码PNR：&nbsp;${detail.oriCrsPnr }</li>
						<li>改签申请人：&nbsp;${user.name }</li>
<!-- 						<li class="liwid"><span>操作历史&or;</span></li> -->
					</ul>
					<ul class="clear">
						<li class="colorli">改签费用总金额：${detail.endorseTotalCost }</li>
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
				<img
					src="${ctxStatic}/order/images/clickpnr.png">
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
									name="segId" value="${segment.id }">&nbsp;${segment.operatingFlightNo}<br>&nbsp;${segment.operatingAirline }</li>
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
							<!-- 					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div> -->
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
						<td>&nbsp; ${ticketorderPassenger.passengerName }</td>
						 

						<td>${ticketorderPassenger.endorseType == '1'?'自愿':'非自愿'}</td>
						<td>&nbsp; 
							${ticketorderPassenger.gender == '1'?"男":"女" }
						</td>
						<td>&nbsp; <c:if
								test="${ticketorderPassenger.passengerType == '0' }">成人</c:if> <c:if
								test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if> <c:if
								test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
						</td>
						<td>&nbsp;  <c:if
								test="${ticketorderPassenger.passengerTitle == '0'}">普通</c:if> <c:if
								test="${ticketorderPassenger.passengerTitle == '2' }">学生</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '3' }">劳务</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '4' }">海员</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '5' }">探亲</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '6' }">移民</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '7' }">外交官</c:if>
							<c:if test="${ticketorderPassenger.passengerTitle == '8' }">军人</c:if>
						</td>
						<td>&nbsp; 	<c:if test="${ticketorderPassenger.certType == '1'}">护照</c:if>
							<c:if test="${ticketorderPassenger.certType == '2'}">港澳通行证</c:if>
							<c:if test="${ticketorderPassenger.certType == '3'}">台湾通行证</c:if>
							<c:if test="${ticketorderPassenger.certType == '4'}">外交官</c:if>
						</td>
						<td>
							&nbsp;${ticketorderPassenger.certNo }</td>
						<td>&nbsp;  
							${ticketorderPassenger.phone }
						</td>
						<td>&nbsp; 
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
						<td>&nbsp;
						<c:if test="${singlePrice.passengerType == '0' }">成人</c:if> 
						<c:if test="${singlePrice.passengerType == '1' }">儿童</c:if>
						<c:if test="${singlePrice.passengerType == '4' }">婴儿</c:if>
						</td>
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
	<form id="form01" action="${ctx }/order/endorsesheetDetail/editCurrentStatus"
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
					</tr>
					<c:forEach items="${passengerList }" var="passegner"
						varStatus="passIndex">
						<tr>
							<td>&nbsp;  ${passegner.passengerName }
							</td>
							<td>&nbsp; ${passegner.endorsePriceDifference }
							</td>
							<td>&nbsp; ${passegner.endorsePriceTaxes} 
							</td>
							<td>&nbsp; ${passegner.endorseCommission}
							</td>
							<td>&nbsp; ${passegner.endorseUpgradeFee}
							</td>
							<td>&nbsp; ${passegner.endorseServiceCharge}
							</td>
							<td>&nbsp;${passegner.endorseSurcharge}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="aircraft">
			<div class="single">
				<div class="singlecon">
					<ul class="clear">
						<li style="float: none; margin: 10px 0;" class="meallispan">
							<span class="redreset" style="margin-right: 0 !important;">&lowast;</span>
							<span>改签费用总计：&nbsp;${detail.endorseTotalCost }</span>
							 <span>改签费用：&nbsp;${detail.endorseUpgradeCost }</span> 
							 <span>改签税费：&nbsp;${detail.endorseTaxes }"</span> 
							 <span>报销凭证费用：&nbsp;${detail.endorseReimburseCredentials }</span>
<%-- 							 <span>保险费用：&nbsp;${detail.insuranceCosts }</span> --%>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
	<!-- 消费凭证 -->
<input type="hidden" name="invoice" value="${detail.invoice }"/>
<span style="display: ${detail.invoice == '1' ?'':'none'  }">
		<!-- 行程单费用明细 -->
		<div class="colorhei"></div>
		<!-- 如果选择行程单 -->
		<div class="aircraft airshow-pnr">
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
					<c:forEach items="${invoiceList }" var="invoice" varStatus="idx">
						<tr>
							<td>&nbsp;${invoice.invoiceName }</td>
							<td>&nbsp;${invoice.invoiceType=='1'?'发票':'行程单' }"</td>
							<td>&nbsp;${invoice.openMoney }"</td>
							<td>&nbsp;${invoice.certificateMoney }"</td>
							<td>&nbsp;${invoice.taxpoint }"</td>
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
					<fmt:formatDate pattern="yyyy-MM-dd" value="${detail.goMainTraveldate}"/>
					</div>
					<div class="airconbot clear">
						<span>邮寄说明：</span>
						<textarea class="rosize fl"  disabled="disabled">${detail.busiRemark }</textarea>
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
				<p>操作留言：</p>
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
		<input type="hidden" name="hid_endorse_status" id="hid_endorse_status"/>
		<input type="hidden" name="hid_payStatus" id="hid_payStatus"/>
		<div class="butbotom">
			
			<c:if test="${detail.currentStatus == 2 && detail.payStatus == 1  && detail.endorseStatus != 2}">
<!-- 				<button type="button" onclick="">问题反馈</button> -->
				<button type="button" onclick="submitOrder1();">支付</button>
				<button type="button" onclick="submitOrder2(2);">取消改签</button>
			</c:if>
<%-- 			<c:if test="${detail.currentStatus == 3 }"> --%>
<!-- 				<button type="button" onclick="submitOrder3(1);">重新申请</button> -->
<%-- 			</c:if> --%>
			<c:if test="${detail.currentStatus == 1 && detail.endorseStatus != 2}">
				<button type="button" onclick="submitOrder2(2);">取消改签</button>
			</c:if>
			<!-- 
			以后会有拒绝改签
			 -->
			<button type="button" onclick="window.history.back(-1);">返回</button>
		</div>
		
	</form>
	
	<form id="form2" name="form2" method="post" action="<%=request.getContextPath() %>/a/order/tbTicketorderDetail/toPay" target="_blank" style="display: none;">
		<table>
			<input name="p2_OrderNo" type="text" size="30" value="${detail.endorsesheetNo }" />
			<input name="p3_Amount" type="text" value="0.01" size="30" />
			<input name="p4_Cur" type="text" value="1" size="30" />
			<input name="p5_ProductName" type="text" value="爱游飞分销测试订单" size="30" />
			<input name="p6_Mp" type="text" size="30" value="华北汽贸"/>
			<input name="p7_ReturnUrl" type="hidden" value="http://192.168.1.134:8080/splat-web-buyer/a/order/endorsesheetDetail/pay" size="50" />
			<input name="p8_NotifyUrl" type="hidden" value="http://192.168.1.134:8080/splat-web-buyer/a/order/endorsesheetDetail/pay" size="50" />
			<input name="p9_FrpCode" type="text" size="30" />
			<input name="pa_OrderPeriod" value="30" type="text" size="30" />
			<input name="pb_PayerLoginName" type="text" size="30" />
			<input type="submit" name="Submit" value="提交" />
		</table>
		
	</form>
	<script type="text/javascript">
	//支付
		function submitOrder1(){
			//$("#hid_payStatus").val(type);
			$("#form2").submit();	
		}
	//取消改签
		function submitOrder2(type){
			$("#hid_endorse_status").val(type);
			$("#form01").submit();	
		}
		//重新申请
		function submitOrder3(type){
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