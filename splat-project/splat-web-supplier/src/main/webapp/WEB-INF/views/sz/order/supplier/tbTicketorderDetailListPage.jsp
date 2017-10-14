<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/pading.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/doubleDate.css">
	<c:forEach items="${page.list}" var="ticketorderDetail">
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${ticketorderDetail.orderNo}</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${ticketorderDetail.orderStatus  == '1'}">已提交</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '2'}">已申签</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '3'}">已拒单</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '4'}">已签单</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '5'}">已预订</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '6'}">已出票</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '7'}">已删除</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '8'}">已申批</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '9'}">已初核</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '10'}">已复核</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '11'}">已批单</c:if>
				<c:if test="${ticketorderDetail.orderStatus  == '12'}">已核单</c:if>
				</span>
				
				</li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${ticketorderDetail.currentStatus  == '1' }">正在核实价格和政策</c:if>
				<c:if test="${ticketorderDetail.currentStatus  == '2' }">已经核实价格和政策</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<c:if test="${ticketorderDetail.payStatus =='0' }">已支付</c:if>
				<c:if test="${ticketorderDetail.payStatus =='1' }">未支付</c:if>
				<c:if test="${ticketorderDetail.payStatus =='2' }">支付失败</c:if>
				</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${ticketorderDetail.createTime }"/></li>
					<li>PNR：${ticketorderDetail.crsPnr }</li>
					<li>预订人：${ticketorderDetail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li>出票订单：联查出来的暂时不给</li>
					<li>票号：${ticketorderDetail.ticketNo }</li>
					<li class="orderwid">
						<span>结算价：5000</span>
						<span>税收：1200</span>
						<span>总价：6200</span>
					</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：${refundsheetDetail.passengers}</li>
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid"><a href="<%=request.getContextPath()%>/a/order/supplier/tbTicketorderDetail/findticketorderDetail?orderNo=${ticketorderDetail.orderNo}">详情信息</a></button>
			</div>
		</div>
	</div>
	</c:forEach>
	<!-- 分页 -->
	<div class="paging">${page }</div>