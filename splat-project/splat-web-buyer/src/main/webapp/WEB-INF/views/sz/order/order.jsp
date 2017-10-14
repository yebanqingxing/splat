<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国际机票产品</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/order.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/order.js"></script>
</head>
<style type="text/css">
	.ductleft {
		text-align: left;
		margin-left: 36px;
	}
	.dudtleft {
		margin-left: 44px;
		text-align: left;
	}
	.alertadd {
		width: 100%;
		position: absolute;
		top: 0;
		left: 0;
		background:rgba(0,0,0,0.6);
		display: none;
	}
	.alertshow {
		width: 1082px;
		margin: 0 auto;
		height: 100%;
	}
</style>
<body>
	<!-- head -->
	<div class="head-mian">
		<img src="<%=request.getContextPath() %>/static/order/images/head_back.jpg">
		<div class="head-content">
			<div class="logo">
				<img src="<%=request.getContextPath() %>/static/order/images/head_logo.png">
			</div>
		</div>
		<div class="head-connav clear">
			<ul class="signout">
				<li>
					<a href="javascript:void(0);" class="signyou">邮件</a>
					<span>1</span>
				</li>
				<li>
					<a href="javascript:void(0);" class="signyou">消息</a>
					<span>2</span>
				</li>
				<li class="signyou">
					<a href="javascript:void(0);" class="lasta">欢迎xxx登录</a>
					<a href="javascript:void(0);" class="lasta">退出</a>
				</li>
			</ul>
			<ul class="head-nav">
				<li><a href="javascript:void(0);">基础功能</a></li>
				<li><a href="javascript:void(0);" class="active">国际机票产品</a></li>
				<li><a href="javascript:void(0);">国际订单管理</a></li>
				<li><a href="javascript:void(0);">报表</a></li>
				<li><a href="javascript:void(0);">系统管理</a></li>
				<li><a href="javascript:void(0);">行程单</a></li>
			</ul>
		</div>
	</div>
	<div class="bottom-back1"></div>
	<div class="bottom-back2"></div>
	<div class="pnr-content">
		<div class="pnr-content-left">
			<div class="order">
				<div class="orderlist">
					<p>国际机票订单查询<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul>
						<li><a href="<%=request.getContextPath()%>/a/order/tbTicketorderDetail/list" target="rightFrame" class="clickulli">国际订单查询</a></li>
						<li><a href="<%=request.getContextPath()%>/a/refundsheetdetail/refundsheetDetail" target="rightFrame">国际退票订单</a></li>
						<li><a href="<%=request.getContextPath()%>/a/order/voidsheetDetail" target="rightFrame">国际废票订单</a></li>
						<li><a href="<%=request.getContextPath()%>/a/order/endorsesheetDetail/list1" target="rightFrame">国际改签订单</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际机票订单详情-PNR<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="<%=request.getContextPath() %>/a/order/tbTicketorderDetail/toProduct_add" target="rightFrame">生成订单成功</a></li>	
						<li><a href="javascript:void(0);">供应商价格核实</a></li>
						<li><a href="javascript:void(0);">供应商未核实</a></li>
						<li><a href="javascript:void(0);">供应商确认价格</a></li>
						<li>
							<p>订单成功支付完成<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
							<ul class="ordernone">
								<li><a href="javascript:void(0);">采购商取消订单</a></li>	
								<li><a href="javascript:void(0);">采购商申请全额退款</a></li>
							</ul>
						</li>
						<li><a href="javascript:void(0);">订单成功出票完成</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际订单支付信息<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际订单出票完成<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>订单申请退票确认<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际订单退票信息<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际订单废票信息<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
				<div class="orderlist">
					<p>国际订单改签信息<img src="<%=request.getContextPath() %>/static/order/images/orderdrop.png"></p>
					<ul class="ordernone">
						<li><a href="javascript:void(0);">国际订单查询</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="pnr-content-right">
			<iframe scrolling="no" frameborder="0" marginheight="0" marginwidth="0" id="iframepage" width="100%" height="100%" name="rightFrame" src="orderquery.html" onLoad="iFrameHeight()"></iframe>
		</div>
	</div>
	<!-- 底部 -->
	<div class="fotter clear">
		<span><a href="javascript:void(0);">关于我们</a> | <a href="javascript:void(0);">商务合作</a> | <a href="javascript:void(0);">媒体报道</a> | <a href="javascript:void(0);">友情链接</a> | <a href="javascript:void(0);">联系我们</a> | <a href="javascript:void(0);">责任声明</a><br />XXX公司 ©2016 ****.com All Rights Reserved</span>
	</div>
		<div class="alertadd">
		<div class="alertshow">
			<iframe scrolling="no" frameborder="0" marginheight="0" marginwidth="0" id="alert" width="100%" height="100%" name="alert" src="affirmOrderDetail"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		function iFrameHeight() {   
			var ifm= document.getElementById("iframepage");   
			var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
			   ifm.height = subWeb.body.scrollHeight;
			   ifm.width = subWeb.body.scrollWidth;
			}   
		}    
		var winhei = $(document).height();
		var pnrhei = $(".head-mian").height() + 7;
		var hei = winhei - pnrhei;
		$(".pnr-content-right").css("minHeight",hei);
		
		function reinitIframe(){
			var iframe = document.getElementById("iframepage");
			try{
				iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;

			}catch (ex){}

		}
		window.setInterval("reinitIframe()", 200);
		var $wid = $(window).width() - 223;
		$(".pnr-content-right").width($wid);
		
		function iFrame() {   
			var ifm= document.getElementById("alert");   
			var subWeb = document.frames ? document.frames["alert"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
			   ifm.height = subWeb.body.scrollHeight;
			   ifm.width = subWeb.body.scrollWidth;
			}   
		}
	</script>
</body>
</html>