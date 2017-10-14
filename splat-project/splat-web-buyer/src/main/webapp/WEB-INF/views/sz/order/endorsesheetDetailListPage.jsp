<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<style>
    .orderconbom-left ul li {
        width:22%;
    }
    .lidan {
        width:31% !important;
    }
    .widparent {
        width:53% !important;
    }
    .widclear {
        width:68%;
        float:left;
        margin-right:0 !important;
    }
    .widli {
        float:left;
        margin-right:0 !important;
    }
</style>
<c:if test="${page.list.size() > 0 }">
<c:forEach items="${page.list }" var="endorsesheetDetail">
<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${endorsesheetDetail.endorsesheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${endorsesheetDetail.endorseStatus == '1' }">下单成功</c:if>
		    	<c:if test="${endorsesheetDetail.endorseStatus == '2' }">取消订单</c:if>
				<c:if test="${endorsesheetDetail.endorseStatus == '3' }">异常订单</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${endorsesheetDetail.currentStatus == 1 }">等待审核</c:if> 
				<c:if test="${endorsesheetDetail.currentStatus == 2 }">审核通过</c:if> 
				<c:if test="${endorsesheetDetail.currentStatus == 3 }">审核拒绝</c:if>
				<%-- <c:if test="${endorsesheetDetail.currentStatus == 4 }">等待审核</c:if>  --%>
				<c:if test="${endorsesheetDetail.currentStatus == 5 }">改签完成</c:if>
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
					<li class="lidan">订单时间：<fmt:formatDate value="${endorsesheetDetail.createTime }" pattern="yyyy-MM-dd"/></li>
					<li>PNR：${endorsesheetDetail.newCrsPnr }</li>
					<li>预订人：${endorsesheetDetail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li class="lidan">出票订单：${endorsesheetDetail.orderNo }</li>
					<li>票号：<a title="${endorsesheetDetail.ticketNo }">${endorsesheetDetail.ticketNoTemp }</a></li>
					<li>总价：${endorsesheetDetail.endorseTotalCost}</li>
				</ul>
				<ul class="clear">
					<li class="widparent">
					   <span class="widli">航程信息：</span>
					   <span class="widclear"><a title="${endorsesheetDetail.segments }">&nbsp;${endorsesheetDetail.segmentsTemp }</a></span>
					</li>
					<li>乘机人：<a title="${endorsesheetDetail.passengers}">&nbsp;${endorsesheetDetail.passengersTemp}</a></li>
					<!-- <li>出票人：喵星人</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
<!-- 				<button class="addquery butwid">操作历史</button> -->
				<a href="<%=request.getContextPath()%>/a/order/endorsesheetDetail/findEndorsesheetDetail?endorsesheetNo=${endorsesheetDetail.endorsesheetNo }"><button class="addquery butwid">详情信息</button></a>
			</div>
		</div>
	</div>
</c:forEach>
<!-- 分页 -->
<div class="paging">${page }</div>
</c:if>
<c:if test="${page.list.size() <= 0 }">
	<br><br/><center>暂无订单</center>
	</c:if>