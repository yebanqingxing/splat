<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改签完成页面</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<center>

	<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>&nbsp;</td>
		<td>姓名（英文）</td>
		<td>性别</td>
		<td>旅客类型</td>
		<td>旅客身份</td>
		<td>证件类型</td>
		<td>证件号码</td>
		<td>联系电话</td>
		<td>票号</td>
	</tr>
	<c:forEach items="${endorsesheetPassengerList }" var="endorseorderPassenger">
	<tr>
		<td>
		<c:if test="${endorseorderPassenger.voidRefundEndorse == null }"><input type="checkbox" name="passengerId" value="${endorseorderPassenger.id}"></c:if>
		<c:if test="${endorseorderPassenger.voidRefundEndorse == '1'}"><input type="checkbox" name="passengerId" value="${endorseorderPassenger.id}"></c:if> </td>
		<td>${endorseorderPassenger.passengerName }</td>
		<td>${endorseorderPassenger.gender == '1'?"男":"女" }</td>
		<td>
		<c:if test="${endorseorderPassenger.passengerType == '0' }">成人</c:if>
		<c:if test="${endorseorderPassenger.passengerType == '1' }">儿童</c:if>
		<c:if test="${endorseorderPassenger.passengerType == '4' }">婴儿</c:if>
		</td>
		<td>
		<c:if test="${endorseorderPassenger.passengerTitle == '0'}">普通</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '2' }">学生</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '3' }">劳务</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '4' }">海员</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '5' }">探亲</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '6' }">移民</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '7' }">外交官</c:if>
		<c:if test="${endorseorderPassenger.passengerTitle == '8' }">军人</c:if>
		</td>
		<td>
		<c:if test="${endorseorderPassenger.certType == '0' }">身份证</c:if> 
		<c:if test="${endorseorderPassenger.certType == '1' }">护照</c:if> 
		<c:if test="${endorseorderPassenger.certType == '2' }">港澳通行证</c:if> 
		<c:if test="${endorseorderPassenger.certType == '3' }">台湾通行证</c:if> 
		<c:if test="${endorseorderPassenger.certType == '4' }">台胞证</c:if> 
		<c:if test="${endorseorderPassenger.certType == '5' }">回乡证</c:if> 
		<c:if test="${endorseorderPassenger.certType == '8' }">其他</c:if>
		<c:if test="${endorseorderPassenger.certType == '9' }">unknown</c:if> 
		</td>
		<td>${endorseorderPassenger.certNo }</td>
		<td>${endorseorderPassenger.phone }</td>
		<td>${endorseorderPassenger.ticketNo }</td>
	</tr>
	</c:forEach>
</table>
<input value="申请废票" onclick="voidSheetDetail()"  type="button"><input value="申请退票" onclick="refundsheetDetail()" type="button">
<input value="申请改签" onclick="endordesheet()" type="button">


</center> 
<script type="text/javascript">
//申请退票
function refundsheetDetail(){
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		window.location.href="<%=request.getContextPath()%>/a/refundsheetdetail/refundsheetDetail/endorserRefundsheetDetail?endorsePassIds="+chk_value.substring(1)+"&orderNo=16032408373001000004"
	}
}
//申请废票
function voidSheetDetail(){
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		window.location.href="<%=request.getContextPath()%>/a/order/voidsheetDetail/endorseVoidDetail?passIds="+chk_value.substring(1)+"&orderNo=16032408373001000004"
	}
	
}
//申请改签
function endordesheet(){
	var chk_value =""; 
	$('input[name="passengerId"]:checked').each(function(){ 
	chk_value += ","+$(this).val(); 
	});
	if("" == chk_value){
		alert('你还没有选择任何内容')
	}else{
		window.location.href="<%=request.getContextPath()%>/a/order/endorsesheetDetail/endorseToEndorseDetail?endorseIds="+chk_value.substring(1)+"&orderNo=16032408373001000004"
	}
	
}
</script>
</body>
</html>