<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<c:forEach items="${page.list }" var="endorsesheetDetail">
<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${endorsesheetDetail.endorsesheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${endorsesheetDetail.endorseStatus == '1' }">已提交</c:if>
		    	<c:if test="${endorsesheetDetail.endorseStatus == '2' }">已拒单</c:if>
		    	<c:if test="${endorsesheetDetail.endorseStatus == '3' }">已申签</c:if>
		    	<c:if test="${endorsesheetDetail.endorseStatus == '4' }">已核单</c:if>
		    	<c:if test="${endorsesheetDetail.endorseStatus == '5' }">已改签</c:if>
				<c:if test="${endorsesheetDetail.endorseStatus == '6' }">已签单</c:if>
				<c:if test="${endorsesheetDetail.endorseStatus == '7' }">已删除</c:if>
				
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${endorsesheetDetail.currentStatus == '1' }">正在审核价格和政策</c:if>
				<c:if test="${endorsesheetDetail.currentStatus == '2' }">已经审核价格和政策</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<c:if test="${endorsesheetDetail.payStatus == '0' }">已支付</c:if>
				<c:if test="${endorsesheetDetail.payStatus == '1' }">未支付</c:if>
				<c:if test="${endorsesheetDetail.payStatus == '2' }">支付失败</c:if>
				</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：<fmt:formatDate value="${endorsesheetDetail.createTime }" pattern="yyyy-MM-dd"/></li>
					<li>PNR：${endorsesheetDetail.newCrsPnr }</li>
					<li>预订人：${endorsesheetDetail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li>出票订单：12345678900</li>
					<li>票号：2016031411</li>
					<li>总价：5000</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：${endorsesheetDetail.passengers}</li>
					<li>出票人：喵星人</li>
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid"><a href="<%=request.getContextPath()%>/a/order/supplier/endorsesheetDetail/findEndorsesheetDetail?endorsesheetNo=${endorsesheetDetail.endorsesheetNo }">详情信息</a></button>
			</div>
		</div>
	</div>
</c:forEach>
<!-- 分页 -->
<div class="paging">${page }</div>