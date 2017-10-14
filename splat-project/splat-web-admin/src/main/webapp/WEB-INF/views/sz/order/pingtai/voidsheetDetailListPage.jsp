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
	<c:forEach items="${page.list}" var="voidsheetDetail">
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">${voidsheetDetail.voidsheetNo }</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">
				<c:if test="${voidsheetDetail.voidsheetStatus == '1' }">下单成功</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '2' }">取消订单</c:if>
		    	<c:if test="${voidsheetDetail.voidsheetStatus == '3' }">异常订单</c:if>
				
				</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">
				<!-- 操作状态（流转状态）1 废票审核中 2 废票操作 3 废票拒绝 4 废票已审核 5 废票完成 6 等待废票 -->
					<c:if test="${voidsheetDetail.currentStatus == '1'  }">审核废票中</c:if>
					<c:if test="${voidsheetDetail.currentStatus == '2'  }">废票操作</c:if>
					<c:if test="${voidsheetDetail.currentStatus == '3'  }">废票拒绝</c:if>
					<c:if test="${voidsheetDetail.currentStatus == '4'  }">废票已审核</c:if>
					<c:if test="${voidsheetDetail.currentStatus == '5'  }">废票完成</c:if>
					<c:if test="${voidsheetDetail.currentStatus == '6'  }">等待废票</c:if>
				</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">
				<c:if test="${voidsheetDetail.payStatus =='0' }">已退款</c:if>
				<c:if test="${voidsheetDetail.payStatus =='1' }">未退款</c:if>
				<c:if test="${voidsheetDetail.payStatus =='2' }">退款失败</c:if>
				</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li class="lidan">订单时间：<fmt:formatDate value="${voidsheetDetail.createTime }"/></li>
                    <li>PNR：${voidsheetDetail.crsPnr }</li>
                    <li>预订人：${voidsheetDetail.createAccount}</li>
                    <li>采购商：${voidsheetDetail.createAccount }</li>
				</ul>
				<ul class="clear">
				   <li class="lidan">出票订单：${voidsheetDetail.orderNo }</li>
                    <li>票号：<a title="${voidsheetDetail.ticketNo}">${voidsheetDetail.ticketNoTemp}</a></li>
                    <li>废票价：${voidsheetDetail.supplierTotSettlementPrice }</li>
<!-- 					<li class="liwid">税费：5000</li> -->
				</ul>
				<ul class="clear">
					<li class="widparent">
                       <span class="widli">航程信息：</span>
                       <span class="widclear"><a title="${voidsheetDetail.segment }">${voidsheetDetail.segmentTemp}</a></span>
                    </li>
                    <li>乘机人：<a titile="${voidsheetDetail.passengers }"> &nbsp;${voidsheetDetail.passengersTemp }</li>
                    <!-- <li class="liwid"><span>出票人：</span>喵星人</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
<!-- 				<button class="addquery butwid">操作历史</button> -->
				<a href="${ctx }/order/ping/voidsheetDetail/findpingVoidsheetDetail?voidsheetNo=${voidsheetDetail.voidsheetNo }"><button type="button" class="addquery butwid">详情信息</button></a>
			</div>
		</div>
	</div>
</c:forEach>
<!-- 分页 -->
	<div class="paging">${page }</div>
	</c:if>
		<c:if test="${page.list.size() <= 0 }">
		<br/><br/><center>暂无订单</center>
		</c:if>