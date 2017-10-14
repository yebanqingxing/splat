<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改签单页面</title>
</head>
<body>
<center>
<form action="<%=request.getContextPath()%>/a/order/endorsesheetDetail/endorseSaveEndorsesheetDetail" method="post" enctype="multipart/form-data">
<div>订单信息</div>
订单号： 申请改签人：当前登录人<br>
原始单号：${orderMap.orderNo }<input type="text" name="orderNo" value="${orderMap.orderNo }"> 开票编码PNR <br>
订单的状态： 	<c:if test="${detailInfo.orderStatus  == '1'}">已提交</c:if>
			<c:if test="${detailInfo.orderStatus  == '2'}">已申签</c:if>
			<c:if test="${detailInfo.orderStatus  == '3'}">已拒单</c:if>
			<c:if test="${detailInfo.orderStatus  == '4'}">已签单</c:if>
			<c:if test="${detailInfo.orderStatus  == '5'}">已预订</c:if>
			<c:if test="${detailInfo.orderStatus  == '6'}">已出票</c:if>
			<c:if test="${detailInfo.orderStatus  == '7'}">已删除</c:if>
			<c:if test="${detailInfo.orderStatus  == '8'}">已申批</c:if>
			<c:if test="${detailInfo.orderStatus  == '9'}">已初核</c:if>
			<c:if test="${detailInfo.orderStatus  == '10'}">已复核</c:if>
			<c:if test="${detailInfo.orderStatus  == '11'}">已批单</c:if>
			<c:if test="${detailInfo.orderStatus  == '12'}">已核单</c:if>
支付的状态：
<c:if test="${detailInfo.payStatus =='0' }">已支付</c:if>
<c:if test="${detailInfo.payStatus =='1' }">未支付</c:if>
<c:if test="${detailInfo.payStatus =='2' }">支付失败</c:if>
操作状态：
<c:if test="${detailInfo.currentStatus  == '1' }">正在核实价格和政策</c:if>
<c:if test="${detailInfo.currentStatus  == '2' }">已经核实价格和政策</c:if>
<div>原始航班信息</div>
<div>去程</div>
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>航空公司+航班</td>
		<td>时间</td>
		<td>飞行时长</td>
		<td>机场</td>  
		<td>价格和税费</td>
	</tr>
<c:forEach items="${tSegmentList }" var="segment">
	<tr>
		<td><input type="hidden" name="segId" value="${segment.id }"></td>
		<td>&nbsp;&nbsp;${segment.operatingFlightNo}<br>&nbsp;${segment.operatingAirline }</td>
		<td>起飞时间：<fmt:formatDate value="${segment.departureTime }" pattern="yyyy-MM-dd HH:mm"/><br>
		到达时间：<fmt:formatDate value="${segment.arriveTime }" pattern="yyyy-MM-dd HH:mm"/>
		 </td>
		 <td>&nbsp;</td>
		 <td>&nbsp;</td>
	</tr>
</c:forEach>
</table>
<div>乘机人</div>
	<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>姓名（英文）</td>
		<td>退票类型</td>
		<td>性别</td>
		<td>旅客类型</td>
		<td>旅客身份</td>
		<td>证件类型</td>
		<td>证件号码</td>
		<td>联系电话</td>
		<td>票号</td>
	</tr>
	<c:forEach items="${tPassengerList }" var="ticketorderPassenger">
	<tr>
		<td><input type="hidden" name="passids" value="${ticketorderPassenger.id }">${ticketorderPassenger.passengerName }</td>
		<td><select name="endorseType">
			<option value="1">自愿</option>
			<option value="2">非自愿</option>
		</select></td>
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
<div>机票退款金额</div>
<div>上传退票相关证明</div>
<select name="endorseDetailType">
	<option value="">请选择改签类型</option>
	<option value="1">申请改期</option>
	<option value="2">申请改程</option>
	<option value="3">申请升舱</option>
</select>
<input type="submit"  value="申请">
</form>
</center>
</body>
</html>