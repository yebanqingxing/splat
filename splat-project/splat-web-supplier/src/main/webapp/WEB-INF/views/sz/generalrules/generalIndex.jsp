<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国际政策管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/general/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/general/css/reset.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/general/js/iframe.js"></script>
</head>
<body>
	<!-- head -->
	<div class="head-mian">
		<img src="<%=request.getContextPath()%>/static/general/images/head_back.jpg">
		<div class="head-content">
			<div class="logo">
				<img src="<%=request.getContextPath()%>/static/general/images/head_logo.png">
			</div>
		</div>
		<div class="head-connav clear">
			<ul class="head-nav fl" style="margin-left: 20%;">
				<li><a href="javascript:void(0);">国际机票产品</a></li>
				<li><a href="javascript:void(0);">国际订单管理</a></li>
				<li><a href="javascript:void(0);" class="active">国际政策管理</a></li>
				<li><a href="javascript:void(0);">下级代理权限</a></li>
				<li><a href="javascript:void(0);">操作员管理</a></li>
				<li><a href="javascript:void(0);">我的管理</a></li>
				<li><a href="javascript:void(0);">报表</a></li>
			</ul>
		</div>
	</div>
	<div class="bottom-back1"></div>
	<div class="bottom-back2"></div>
	<div class="pnr-content">
		<div class="pnr-content-left">
			<ul>
				<li><a href="javascript:void(0);" class="pnr-fast">国际总则维护</a></li>
				<li><a href="${ctx}/generalrules/generalRules/toSave" class="pnr-last clickulli" target="rightFrame">添加总则</a></li>
				<li><a href="${ctx}/generalrules/generalRules/toDetails" class="pnr-last" target="rightFrame">详情信息</a></li>
				<li><a href="${ctx}/generalrules/generalRules/toEdit" class="pnr-last" target="rightFrame">详情修改</a></li>
				<li><a href="${ctx}/generalrules/generalRules/" class="pnr-last" target="rightFrame">总则查询</a></li>
				<li><a href="${ctx}/generalrules/generalRules/historyList" class="pnr-last" target="rightFrame">查询历史总则</a></li>
				<li><a href="${ctx}/generalrules/generalRules/toImportGeneral" class="pnr-last" target="rightFrame">总则导入</a></li>
				<li><a href="javascript:void(0);" class="pnr-last" target="rightFrame">查询导出</a></li>
				<li><a href="javascript:void(0);" class="pnr-fast" target="rightFrame">国际政策维护</a></li>
				<li><a href="${ctx}/policyrules/policyRules" class="pnr-last" target="rightFrame">政策查询</a></li>
				<li><a href="${ctx}/policyrules/policyRules/toSave" class="pnr-last" target="rightFrame">政策添加</a></li>
				<li><a href="${ctx}/policyrules/policyRules/toDetails" class="pnr-last" target="rightFrame">政策详情</a></li>
				<li><a href="${ctx}/policyrules/policyRules/toUpdate" class="pnr-last" target="rightFrame">政策修改</a></li>
				<li><a href="${ctx}/policyrules/policyRules" class="pnr-last" target="rightFrame">政策复制</a></li>
				<li><a href="${ctx}/policyrules/policyRules" class="pnr-last" target="rightFrame">政策历史查询</a></li>
				<li><a href="${ctx}/policyrules/policyRules/toImportExcle" class="pnr-last" target="rightFrame">政策明细导入</a></li>
				<li><a href="${ctx}/policyrules/policyRules" class="pnr-last" target="rightFrame">政策明细导出</a></li>
			</ul>
		</div>
		<div class="pnr-content-right">
			<iframe scrolling="no" frameborder="0" marginheight="0" marginwidth="0" id="iframepage" width="100%" height="100%" name="rightFrame" src="${ctx}/generalrules/generalRules/list" onLoad="iFrameHeight()"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		function iFrameHeight() {   
			var ifm= document.getElementById("iframepage");   
			var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
			   ifm.height = subWeb.body.scrollHeight;
			//    ifm.width = subWeb.body.scrollWidth;
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
		var $wid = $(window).width()*0.8 - 2;
		$(".pnr-content-right").width($wid);
	</script>
</body>
</html>