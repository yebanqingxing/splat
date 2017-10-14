<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采购商详情审批</title>
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
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/images/backval.png">
	<p>采购商详情审批</p>
</div>
<div class="addcontent">
	<div class="addconrow clear" style="margin-top: 30px;">
		<span>采购商名称：</span>
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
		<span>是否启用：</span>
		<div class="addiv fl">
			<select class="querycode widban" style="margin-right: 119px;">
				<option value = "1" >是</option>
				<option value = "0" >否</option>
			</select>
			<p class="redreset"></p>
		</div>
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
	<%-- <div class="addconrow clear">
		<div class="backbutval">审批</div>
	</div>
	<div class="addconrow clear cenbut">
		<button class="addquery">通过</button>
		<button class="addquery refuse">拒绝</button>
		<div class="reason">
			<div class="imgreson">
				<img src="${ctxStatic}/images/top.png">
			</div>
			<textarea>请填写拒绝理由</textarea>
		</div>
	</div> --%>
	<%-- <table id="contentTable" class="table table-striped table-bordered table-condensed flip-content" style="margin-top:20px;text-align:center;">
			<thead class="flip-content"><tr style = "text-align:center;"><th>部门名称</th><th>部门电话</th><th>部门联系人姓名</th><th>部门地址</th><th>部门邮箱</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody>
			<c:if test="${departments == null||fn:length(departments) ==0}">
			   <tr><td colspan="7" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
			</c:if>
			<c:forEach items="${departments}" var="department">
				<tr style = "text-align:center;">
					<td>${department.name}</td>
					<td>${department.phone}</td>
					<td>${department.master}</td>
					<td>${department.address}</td>
					<td>${department.email}</td>
					<shiro:hasPermission name="sys:role:edit">
					<td>
						<a href="${ctx}/sys/office/delete?platType=B&id=${department.id}" 
							onclick="return confirmx('确认要将部门<b>[${department.name}]</b>从<b>[${office.name}]</b>中移除吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
					</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
	</table> --%>
	<div class="addconrow clear cenbut">
		<a href = "${ctx}/sys/office/buyerList?platType=B&type=1"><button class="addquery">返回</button></a>
	</div>
</div>
<!-- <script type="text/javascript">
	$(function(){
		$(".refuse").click(function(){
			$(".reason").slideToggle();
		})
	})
</script> -->
</body>
</html>