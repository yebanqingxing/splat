<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作员详情</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/backpur.css">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script>
<style type="text/css">
	.addconrow span {
		width: 71px;
	}
	.textright {
		text-align: right;
	}
</style>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/images/operdeta.png">
	<p>操作员信息详情</p>
</div>
<div class="addcontent">
	<div class="addconrow clear" style="margin-top: 30px;">
		<span>姓名：</span>
		<div class="addiv fl">
			<input type="text" value="${user.name }" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>用户名：</span>
		<div class="addiv fl">
			<input type="text" value="${user.loginName }" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span class="textright">邮箱：</span>
		<div class="addiv fl">
			<input type="text" value="${user.email }" class="querycode widadd">
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>身份证：</span>
		<div class="addiv fl">
			<input type="text" value="" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>电话号：</span>
		<div class="addiv fl">
			<input type="text" value="${user.phone }" class="querycode widadd">
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>账户名：</span>
		<div class="addiv fl">
			<input type="text" value="" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>初始密码：</span>
		<div class="addiv fl">
			<input type="password" value="123456" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span class="textright">职级：</span>
		<div class="addiv fl">
			<select class="querycode widadd">
				<option>1</option>
				<option>2</option>
			</select>
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>部门：</span>
		<div class="addiv fl">
			<select class="querycode widadd" style="margin-right:33px;">
				<option>${user.office.name }</option>
			</select>
			<p class="redreset"></p>
		</div>
		<span>角色名：</span>
		<div class="addiv fl">
			<input type="text" value="${user.roleNames }" class="querycode widadd">
			<p class="redreset"></p>
		</div>
		<span>能否登录：</span>
		<div class="addiv fl">
			<select class="querycode widban">
				<option>
					<c:choose>
						<c:when test="${user.loginFlag == 1 }">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
					</c:choose>
				</option>
			</select>
			<p class="redreset"></p>
		</div>
	</div>
	<div class="addconrow clear">
		<span>备注信息：</span>
		<textarea class="addconbei" value = "${user.remarks }"></textarea>
	</div>
	<div class="operbut">
		<a href = "${ctx}/sys/user/supplierList"><button class="addquery">关闭</button></a>
	</div>
</div>
</body>
</html>