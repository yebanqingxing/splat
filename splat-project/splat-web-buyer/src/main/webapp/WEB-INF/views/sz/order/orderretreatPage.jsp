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
<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<c:if test="${page.list.size() > 0 }">
<c:forEach items="${page.list }" var="refundsheetDetail">
<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${refundsheetDetail.refundsheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<!-- 退票单状态 1 下单成功 2 取消退票 3 退票完成 4 异常订单 5 等待退票 -->
				<c:if test="${refundsheetDetail.refundsheetStatus == '1' }">下单成功</c:if>
				<c:if test="${refundsheetDetail.refundsheetStatus == '2' }">取消退票</c:if>
				<c:if test="${refundsheetDetail.refundsheetStatus == '4' }">异常订单</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：${detail.currentStatus }<span class="redreset">
				<!-- 操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 退票确认(等待确认) 4 审核通过 5 退票操作(采购商同意退票)  -->
				<c:if test="${refundsheetDetail.currentStatus == '1' }">等待审核退票</c:if>
				<c:if test="${refundsheetDetail.currentStatus == '2' }">拒绝审核</c:if>
				<c:if test="${refundsheetDetail.currentStatus == '3' }">等待退票</c:if>
				<c:if test="${refundsheetDetail.currentStatus == '4' }">审核通过</c:if>
				<c:if test="${refundsheetDetail.currentStatus == '5' }">退票完成</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<!-- 支付状态 0 已经退款 1 未退款 2 退款失败 -->
				<c:if test="${refundsheetDetail.payStatus == '0' }">已退款</c:if>
				<c:if test="${refundsheetDetail.payStatus == '1' }">未退款</c:if>
				<c:if test="${refundsheetDetail.payStatus == '2' }">退款失败</c:if>
				</span></li>
				</span>
				</li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li class="lidan">订单时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${refundsheetDetail.createTime }"/></li>
					<li>PNR：${refundsheetDetail.crsPnr }</li>
					<li>预订人：${refundsheetDetail.createAccount }</li>
				</ul>
				<ul class="clear">
					<li class="lidan">出票订单：${refundsheetDetail.orderNo }</li>
					<li>票号：<a title="${refundsheetDetail.ticketNo}">${refundsheetDetail.ticketNoTemp}</a></li>
					<li class="orderwid">退票价：${refundsheetDetail.supplierTotSettlementPrice }</li>
				</ul>
				<ul class="clear">
					<li class="widparent">
					   <span class="widli">航程信息：</span>
					   <span class="widclear"><a title="${refundsheetDetail.segments }">${refundsheetDetail.segmentsTemp }</a></span>
					</li>
					<li>乘机人：<a title="${refundsheetDetail.passengers}">${refundsheetDetail.passengersTemp}</a></li>
<!-- 					<li>税费：</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
<!-- 				<button class="addquery butwid">操作历史</button> -->
				<a href="${ctx }/refundsheetdetail/refundsheetDetail/findRefundsheetDetail?refundsheetNo=${refundsheetDetail.refundsheetNo }"><button class="addquery butwid">详情信息</button></a>
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