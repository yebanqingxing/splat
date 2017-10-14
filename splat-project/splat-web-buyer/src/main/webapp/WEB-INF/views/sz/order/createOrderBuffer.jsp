<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际机票PNR导入生成订单前—确认页面</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/saveOrderDetail">
<input type="hidden" name="ticketorderPnr" value="${ticketorderPnr }">
<input type="hidden" name="ticketorderPassenger" value="${ticketorderPassenger }">
<input type="hidden" name="ticketorderSegment" value="${ticketorderSegment }">
<div>航班行程信息</div>
<table>
	<tr>
		<td>航空公司+航班号${ticketorderSegment.operatingAirline }
		${ticketorderSegment.operatingFlightNo }</td>
		<td>起飞时间：
		<fmt:formatDate value="${ticketorderSegment.departureTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		到达时间:<fmt:formatDate value="${ticketorderSegment.arriveTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>北京 <u> 7天 </u> 华盛顿</td>
		<td>起飞机场：<input  readonly="readonly" value="北京机场">
		到达机场：<input readonly="readonly" value="华盛顿"></td>
		<td>票面价格：<input readonly="readonly" value="5000">
		税费<input value="2000" readonly="readonly">
		</td> 
	</tr>
</table>
<div>乘机人信息</div>
<table>
	<tr>
		<td>姓名（英文）</td>
		<td>性别</td>
		<td>乘客类型</td>
		<td>证件类型</td>
		<td>证件号码</td>
		<td>联系电话</td>
	</tr>
	<tr>
		<td>${ticketorderPassenger.passengerName }</td>
		<td>${ticketorderPassenger.gender == '1'?"男":"女" }</td>
		<td><c:if test="${ticketorderPassenger.passengerType == '0' }">成人</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
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
		<td>${ticketorderPassenger.phone }</td>
	</tr>
</table>

<div>机票费用明细</div>
<table width="90%">
	<tr>
		<td>姓名</td>
		<td>类型</td>
		<td>票面价（销售）</td>
		<td>记奖价</td>
		<td>代理费</td>
		<td>政策返点</td>
		<td>供应商开票费用</td>
		<td>税费</td>
		<td>单张结算价</td>
	</tr>
	<tr>
		<td>li/qianchao</td>
		<td>chengren</td>
		<td>2000</td>
		<td>2000</td>
		<td>3%</td>
		<td>6%</td>
		<td>供应商开票费用</td>
		<td>税费</td>
		<td>单张结算价</td>
	</tr>
</table>
<div>报销凭证费用明细</div>
<div>邮寄地址</div>
<div>订单总金额</div>
<div>留言说明</div>

<center><input type="submit" value="确认"></center>
</form>
</body>
</html>