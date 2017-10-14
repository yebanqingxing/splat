<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国际机票产品</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/iframe.js"></script>
</head>
<body>
	<!-- head -->
	<div class="head-mian">
		<img src="../images/head_back.jpg">
		<div class="head-content">
			<div class="logo">
				<img src="../images/head_logo.png">
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
			<ul>
				<li><a href="javascript:void(0);" class="pnr-fast" target="rightFrame">PNR导入创建订单</a></li>
				<li><a href="product_infor.jsp" class="pnr-last" target="rightFrame">PNR导入信息</a></li>	
				<li><a href="javascript:void(0);" class="pnr-last">PNR增加旅客</a></li>
				<li><a href="javascript:void(0);" class="pnr-last">PNR订单确认</a></li>
				<li><a href="javascript:void(0);" class="pnr-last">PNR生成订单</a></li>
				<li><a href="javascript:void(0);" class="pnr-fast">航班查询及预定</a></li>
			</ul>
		</div>
		<div class="pnr-content-right">
			<iframe scrolling="no" frameborder="0" marginheight="0" marginwidth="0" id="iframepage" width="100%" height="100%" name="rightFrame" src="product_infor.jsp" onLoad="iFrameHeight()"></iframe>
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
		var winhei = $(window).height();
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
	</script>
</body>
</html>