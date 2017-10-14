<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门详情审批</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/backpur.css">
<style>
	#contentTable tr td{
		text-align:center;
	}
	#contentTable tr th{
		text-align:center;
	}
</style>
<script type="text/javascript">
	$(function(){
		if(${office.useable}){
			$("#useable").find("option:eq(0)").attr("selected",true);
		}else{
			$("#useable").find("option:eq(1)").attr("selected",true);
		}
	})
</script>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/images/backval.png">
	<p>部门详情审批</p>
</div>
<div class="addcontent">
	<div class="addconrow clear" style="margin-top: 30px;">
		<span>部门名称：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.name }">
			<p class="redreset"></p>
		</div>
		<span>联系人姓名：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.master }">
			<p class="redreset"></p>
		</div>
		<span>手机号码：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.phone }">
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>邮箱号码：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.email }">
			<p class="redreset"></p>
		</div>
		<span>电话号码：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.fax }">
			<p class="redreset"></p>
		</div>
		<span>是否启用：</span>
		<div class="addiv fl">
			<select id = "useable" class="querycode widban" style="margin-right: 119px;">
				<option value = '1'>是</option>
				<option value = '0'>否</option>
			</select>
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>公司地址：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.address }">
			<p class="redreset"></p>
		</div>
		<span>公司邮编：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.zipCode }">
			<p class="redreset"></p>
		</div>
		<span>公司电话：</span>
		<div class="addiv fl">
			<input type="text"  class="querycode widadd" value = "${office.phone }">
			<p class="redreset"></p>
		</div>
	</div>
	<!-- <div class="addconrow clear">
		<span>用户名：</span>
		<div class="addiv fl">
			<input type="text" value="" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>初始密码：</span>
		<div class="addiv fl">
			<input type="password" value="" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>类型信息：</span>
		<div class="addiv fl">
			<select class="querycode widban">
				<option>000</option>
				<option>111</option>
			</select>
			<p class="redreset"></p>
		</div>
	</div> -->
	<div class="addconrow clear">
		
		<span>所在城市：</span>
		<div class="addiv fl">
			<input type="text" value="" class="querycode widadd" value = "office.area.name">
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>备注信息：</span>
		<textarea class="addconbei" value = "${office.remarks }"></textarea>
	</div>
	<div class="addconrow clear cenbut">
		<a href = "${ctx}/sys/office/list?platType=A"><button class="addquery">返回</button></a>
	</div>
</div>
</body>
</html>