<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<c:forEach items="${page.list }" var="refundsheetDetail">
<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${refundsheetDetail.refundsheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${refundsheetDetail.refundsheetStatus == '1' }">退票订单</c:if>
		    	<c:if test="${refundsheetDetail.refundsheetStatus == '3' }">退单确认</c:if>
		    	<c:if test="${refundsheetDetail.refundsheetStatus == '4' }">退票完成</c:if>
		    	<c:if test="${refundsheetDetail.refundsheetStatus == '2' }">审核拒绝</c:if>
		    	<c:if test="${refundsheetDetail.refundsheetStatus == '5' }">取消退票</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${refundsheetDetail.currentStatus == '1' }">正在审核价格和政策</c:if>
				<c:if test="${refundsheetDetail.currentStatus == '2' }">已经审核价格和政策</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<c:if test="${refundsheetDetail.payStatus == '0' }">已支付</c:if>
				<c:if test="${refundsheetDetail.payStatus == '1' }">未支付</c:if>
				<c:if test="${refundsheetDetail.payStatus == '2' }">支付失败</c:if>
				</span>
				</li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${refundsheetDetail.createTime }"/></li>
					<li>PNR：${refundsheetDetail.crsPnr }</li>
					<li>预订人：${refundsheetDetail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li>出票订单：12345678900</li>
					<li>票号：${refundsheetDetail.ticketNo}</li>
					<li class="orderwid">退票价：5000</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：${refundsheetDetail.passengers}</li>
					<li>税费：1500</li>
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid"><a href="<%=request.getContextPath()%>/a/refundsheetdetail/supplier/refundsheetDetail/findRefundsheetDetail?refundsheetNo=${refundsheetDetail.refundsheetNo }">详情信息</a></button>
			</div>
		</div>
	</div>	
</c:forEach>
<!-- 分页 -->
	<div class="paging">${page }</div>