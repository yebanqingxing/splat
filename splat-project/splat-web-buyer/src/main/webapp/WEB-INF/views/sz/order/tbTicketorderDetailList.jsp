<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单生成模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			var orderNo=$("#orderNo").val();
			var minTime=$("#minTime").val();
			var maxTime=$("#maxTime").val();
			var ticketNo=$("#ticketNo").val();
			var createAccount=$("#createAccount").val();
			var orderStatus=$("#orderStatus").val();
			var payStatus=$("#payStatus").val();
			var crsPnr=$("#crsPnr").val();
			var pageNo=$("#pageNo").val();
			var pageSize=$("#pageSize").val();
			$.post(
			'${ctx}/order/tbTicketorderDetail/list',
			{"orderNo":orderNo,"minTime":minTime,"maxTime":maxTime,
			"ticketNo":ticketNo,"createAccount":createAccount,"orderStatus":orderStatus,
			"orderStatus":orderStatus,"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,
			"pageSize":pageSize,"flag":1
			},
			function(data){
				$("#detailPage").html(data)
			}
			)
			//$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	
	<form:form id="searchForm" modelAttribute="tbTicketorderDetail" action="${ctx}/order/tbTicketorderDetail/" method="post" class="breadcrumb form-search">
		订单号<input type="text" name="orderNo" id="orderNo">
		订单时间
		<input id="minTime" name="minTime" />至<input id="maxTime" name="maxTime"/>
		<br><br>
		票&nbsp;号<input id="ticketNo" name="ticketNo"> 旅客姓名<input type="text" id="createAccount" name="createAccount">		
		操作状态<select id="orderStatus" name="orderStatus">
		<option value="">请选择</option>
		<option value="1">已提交</option>
		<option value="2">已审签</option>
		<option value="3">已拒单</option>
		<option value="4">已签单</option>
		<option value="5">已预定</option>
		<option value="6">已出票</option>
		<option value="7">已删除</option>
		<option value="8">已申批</option>
		<option value="9">已初核</option>
		<option value="10">已复核</option>
		<option value="11">已批单</option>
		<option value="12">已核单</option>
		</select><br><br>
		PNR号：<input type="text" name="crsPnr"> 
		订单类型<input type="">
		支付状态<select name="payStatus" id="payStatus">
		<option value="">请选择</option>
		<option value="0">已支付</option>
		<option value="1">未支付</option>
		<option value="2">支付失败</option>
		</select>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" onclick="search()" class="btn btn-sm green" type="button" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table>
		<tr>
			<td>全部</td>
			<c:forEach items="${payStatusCountList  }" var="payStatus">
			<td><c:if test="${payStatus.payStatus == '1' }">等待支付(${payStatus.count })</c:if>
			<c:if test="${payStatus.payStatus == '0' }">等待出票(${payStatus.count })</c:if>
			 </td>
			</c:forEach>
			<c:forEach items="${orderStatusCountList }" var="orderStatus">
				<td>
				<c:if test="${orderStatus.orderStatus == '1' }">下单成功(${orderStatus.count })</c:if>
				<c:if test="${orderStatus.orderStatus == '2' }">订单审核中(${orderStatus.count })</c:if>
				<c:if test="${orderStatus.orderStatus == '8' }">出票完成(${orderStatus.count })</c:if>
				<c:if test="${orderStatus.orderStatus == '3' }">拒接审核(${orderStatus.count })</c:if>
				</td>
			</c:forEach>
		</tr>
	</table>
	<div id="detailPage">
		<jsp:include page="tbTicketorderDetailListPage.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		function search(){
			page("1","10");
		}
	
	</script>
</body>
</html>