<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<style>
    .orderconbom-left ul li {
        width:30%;
    }
    .widparent {
        width:60% !important;
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
	<c:forEach items="${page.list}" var="ticketorderDetail">
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${ticketorderDetail.orderNo}</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${ticketorderDetail.orderStatus  == '1'}">下单成功</c:if>
                <c:if test="${ticketorderDetail.orderStatus  == '5'}">异常订单</c:if>
                <c:if test="${ticketorderDetail.orderStatus  == '7'}">取消订单</c:if>
                </span>
				</li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<c:if test="${ticketorderDetail.currentStatus  == '1' }">正在核实价格和政策</c:if>
                <c:if test="${ticketorderDetail.currentStatus  == '2' }">已经核实价格和政策</c:if>
                <c:if test="${ticketorderDetail.currentStatus == '3' }">已督促供应商核实价格</c:if>
                <c:if test="${ticketorderDetail.currentStatus == '5' }">审核拒绝</c:if>
                <c:if test="${ticketorderDetail.currentStatus == '7' ||ticketorderDetail.currentStatus == '6'}">等待出票 </c:if>
                <c:if test="${ticketorderDetail.currentStatus == '8' }">出票完成 </c:if>
                <c:if test="${ticketorderDetail.currentStatus == '9' }">申请全额退款 </c:if>
                <c:if test="${ticketorderDetail.currentStatus == '10' }">申请通过全额退款 </c:if>
                <c:if test="${ticketorderDetail.currentStatus == '11' }">拒绝全额退款 ,等待出票</c:if>
                </span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">    
				<c:if test="${ticketorderDetail.payStatus =='0' }">已支付</c:if>
				<c:if test="${ticketorderDetail.payStatus =='1' }">未支付</c:if>
				<c:if test="${ticketorderDetail.payStatus =='2' }">支付失败</c:if>
				<c:if test="${ticketorderDetail.payStatus =='3' }">未退款</c:if>
                <c:if test="${ticketorderDetail.payStatus =='4' }">已退款</c:if>
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
					<li>票号：
                    <a title="${ticketorderDetail.ticketNo }">${ticketorderDetail.ticketNoTemp }</a>
                    </li>
                    <li>
                        <span>结算价：${ticketorderDetail.settlementPrice }</span>
                        <span>税费：${ticketorderDetail.dutyPrice}</span>
                    </li>
                    <li>
                        <span>总价：${ticketorderDetail.settlementPrice + ticketorderDetail.dutyPrice }</span>
                    </li>
				</ul>
				<ul class="clear">
					<li class="widparent">
                       <span class="widli">航程信息：</span>
                       <span class="widclear"><a title="${ticketorderDetail.segments}">${ticketorderDetail.segmentsTemp}</a></span>
                    </li>
                    <li>乘机人：<a title="${ticketorderDetail.passengers } ">&nbsp;${ticketorderDetail.passengersTemp }</a>
                    </li>
                </ul>
			</div>
			<div class="orderconbom-right fl">
<!-- 				<button class="addquery butwid">操作历史</button> -->
				<a href="${ctx }/order/ping/tbTicketorderDetail/findpingTicketorderDetail?orderNo=${ticketorderDetail.orderNo}"><button class="addquery butwid" type="button">详情信息</button></a>
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
	