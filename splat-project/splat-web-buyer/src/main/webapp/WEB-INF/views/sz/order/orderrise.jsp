<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际升舱订单</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/pading.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/doubleDate.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/doubleDate2.0.js"></script>
<script>
	$(function(){
		$('.doubledate').kuiDate({
			className:'doubledate',
			isDisabled: "0"  // isDisabled为可选参数，“0”表示今日之前不可选，“1”标志今日之前可选
		});
	});
</script>
</head>
<body>
<div class="addpolicy">
	<img src="<%=request.getContextPath() %>/static/order/images/orderrise.png">
	<p>国际升舱订单</p>
</div>
<!-- 总则编码 -->
<div class="query">
	<div class="queryinter">
		<span class="query-span interspan">订单号：</span>
		<input type="text" value="" name="querycode" class="querycode">
		<span>订单时间：</span>
		<input type="text" readonly="readonly" class="doubledate ipticon">
		<span>至</span>
		<input type="text" readonly="readonly" class="doubledate ipticon">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">票号：</span>
		<input type="text" value="" name="querycode" class="querycode">
		<span>旅客姓名：</span>
		<input type="text" value="" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">PNR号：</span>
		<input type="text" value="" name="querycode" class="querycode">
		<span>订单类型：</span>
		<input type="text" value="" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">操作状态：</span>
		<!-- <input type="text" value="" name="querycode" class="querycode"> -->
		<select class="querycode">
			<option>等待操作</option>
			<option>操作成功</option>
		</select>
		<span>支付状态：</span>
		<select class="querycode">
			<option>等待操作</option>
			<option>操作成功</option>
		</select>
		<button class="addquery">查询</button>
	</div>
</div>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderretreat">
		<table border="0">
		  <tr>
		    <td>全部</td>
		    <td>升舱订单（2）</td>
		    <td>升舱审核（2）</td>
		    <td>等待支付及确认（2）</td>
		    <td>等待升舱（2）</td>
		    <td>升舱完成（2）</td>
		    <td>订单异常（2）</td>
		    <td>审核拒绝（2）</td>
		    <td>取消改期（2）</td>
		  </tr>
		</table>
	</div>
	<div id="endorseDetailPage">
		<jsp:include page="" ></jsp:include>
	</div>
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">20160314100</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">下单成功</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">等待操作</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">等待支付</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：2016年3月14日</li>
					<li>PNR：123456789000</li>
					<li>预订人：张三</li>
				</ul>
				<ul class="clear">
					<li>出票订单：12345678900</li>
					<li>票号：2016031411</li>
					<li>总价：5000</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：李四</li>
					<!-- <li>出票人：喵星人</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid">详情信息</button>
			</div>
		</div>
	</div>
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">20160314100</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">下单成功</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">等待操作</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">等待支付</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：2016年3月14日</li>
					<li>PNR：123456789000</li>
					<li>预订人：张三</li>
				</ul>
				<ul class="clear">
					<li>出票订单：12345678900</li>
					<li>票号：2016031411</li>
					<li>退票价：5000</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：李四</li>
					<!-- <li>出票人：喵星人</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid">详情信息</button>
			</div>
		</div>
	</div>
	<div class="ordercon">
		<div class="orderconhead">
			<ul class="orderconul clear">
				<li>订单号：<span class="redreset">20160314100</span></li>
				<li class="ordermargin">|</li>
				<li>订单状态：<span class="redreset">下单成功</span></li>
				<li class="ordermargin">|</li>
				<li>操作状态：<span class="redreset">等待操作</span></li>
				<li class="ordermargin">|</li>
				<li>支付状态：<span class="redreset">等待支付</span></li>
			</ul>
		</div>
		<div class="orderconbom clear">
			<div class="orderconbom-left fl">
				<ul class="clear">
					<li>订单时间：2016年3月14日</li>
					<li>PNR：123456789000</li>
					<li>预订人：张三</li>
				</ul>
				<ul class="clear">
					<li>出票订单：12345678900</li>
					<li>票号：2016031411</li>
					<li>改签价：5000</li>
				</ul>
				<ul class="clear">
					<li>航程信息：北京—墨西哥</li>
					<li>乘机人：李四</li>
					<!-- <li>出票人：喵星人</li> -->
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid">详情信息</button>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>