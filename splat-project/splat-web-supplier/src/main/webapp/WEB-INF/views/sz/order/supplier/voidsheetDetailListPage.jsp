<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

	<c:forEach items="${page.list}" var="voidsheetDetail">
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${voidsheetDetail.voidsheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${voidsheetDetail.voidsheetStatus == '1' }">已提交</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '2' }">已拒单</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '3' }">已核单</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '4' }">已废票</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '5' }">已删除</c:if>
				
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${voidsheetDetail.currentStatus  == '1' }">正在核实价格和政策</c:if>
				<c:if test="${voidsheetDetail.currentStatus  == '2' }">已经核实价格和政策</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<c:if test="${voidsheetDetail.payStatus =='0' }">已支付</c:if>
				<c:if test="${voidsheetDetail.payStatus =='1' }">未支付</c:if>
				<c:if test="${voidsheetDetail.payStatus =='2' }">支付失败</c:if>
				</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li class="liwid">订单时间：<fmt:formatDate value="${voidsheetDetail.createTime }"/></li>
					<li class="liwid">PNR：${voidsheetDetail.crsPnr }</li>
					<li class="liwid">预订人：张三</li>
					<li class="liwid">采购商：XXXXXXXXXXXX</li>
				</ul>
				<ul class="clear">
					<li class="liwid">出票订单：12345678900</li>
					<li class="liwid">票号：${voidsheetDetail.ticketNo}</li>
					<li class="liwid">成票价：5000</li>
					<li class="liwid">税收：5000</li>
				</ul>
				<ul class="clear">
					<li class="liwid">航程信息：北京—墨西哥</li>
					<li class="liwid">乘机人：李四</li>
					<li class="liwid"><span>出票人：</span>喵星人</li>
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid"><a href="<%=request.getContextPath()%>/a/order/supplier/voidsheetDetail/findVoidsheetDetail?voidsheetNo=${voidsheetDetail.voidsheetNo }">详情信息</a></button>
			</div>
		</div>
	</div>
</c:forEach>
<!-- 分页 -->
	<div class="paging">${page }</div>