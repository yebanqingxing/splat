<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际改签订单</title>
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
	<img src="<%=request.getContextPath() %>/static/order/images/ordermeal.png">
	<p>国际改签订单</p>
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
		    <td>审核改签（2）</td>
		    <td>等待支付（2）</td>
		    <td>等待改签（2）</td>
		    <td>改签完成（2）</td>
		    <td>退票订单（2）</td>
		    <td>异常订单（2）</td>
		    <td>审核拒绝（2）</td>
		    <td>改签取消（2）</td>
		  </tr>
		</table>
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
					<li class="liwid">订单时间：2016年3月14日</li>
					<li class="liwid">PNR：123456789000</li>
					<li class="liwid">预订人：张三</li>
					<li class="liwid">采购商：XXXXXXXXXXXX</li>
				</ul>
				<ul class="clear">
					<li class="liwid">出票订单：12345678900</li>
					<li class="liwid">票号：2016031411</li>
					<li class="liwid">成票价：5000</li>
					<li class="liwid">税费：5000</li>
				</ul>
				<ul class="clear">
					<li class="liwid">航程信息：北京—墨西哥</li>
					<li class="liwid">乘机人：李四</li>
					<li class="liwid"><span>出票人：</span>喵星人</li>
				</ul>
			</div>
			<div class="orderconbom-right fl">
				<button class="addquery butwid">操作历史</button>
				<button class="addquery butwid">详情信息</button>
			</div>
		</div>
	</div>
	<!-- 分页 -->
	<div class="paging">
		<ul class="clear centerpad">
			<li>
				<div class="padingback">
					<img src="<%=request.getContextPath() %>/static/order/images/leftclick.png">
					<span>第一页</span>
				</div>
			</li>
			<li>
				<p class="activepading">1</p>
			</li>
			<li>
				<p>2</p>
			</li>
			<li>
				<p>3</p>
			</li>
			<li>
				<p>4</p>
			</li>
			<li>
				<p>5</p>
			</li>
			<li><span class="dian">···</span></li>
			<li>
				<p>6</p>
			</li>
			<li>
				<div class="padingback">
					<span>下一页</span>
					<img src="<%=request.getContextPath() %>/static/order/images/rightclick.png">
				</div>
			</li>
			<li class="redreset lired">共50条</li>
			<li>
				<div class="padingmo">
					<span>末页</span>
				</div>
			</li>
			<li>
				<input type="text" class="search" value="">
			</li>
			<li>
				<div class="padingmo">
					<span>GO</span>
					<img src="<%=request.getContextPath() %>/static/order/images/rightclick.png">
				</div>
			</li>
		</ul>
	</div>
</div>
</body>
</html>