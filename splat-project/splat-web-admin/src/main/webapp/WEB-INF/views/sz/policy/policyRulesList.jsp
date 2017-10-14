<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/policyrules/policyRules/">政策明细列表</a></li>
		<shiro:hasPermission name="policyrules:policyRules:edit"><li><a href="${ctx}/policyrules/policyRules/form">政策明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="policyRules" action="${ctx}/policyrules/policyRules/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>政策名称：</label>
				<form:input path="policyName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>状态 0 启用，1禁用，2挂起：</label>
				<form:input path="policyStatus" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>投放分销商：</label>
				<form:input path="supplierId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>去程起点：</label>
				<form:input path="outOrg" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>出票日期 开始：</label>
				<input name="tktTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${policyRules.tktTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>出票日期  结束：</label>
				<input name="tktTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${policyRules.tktTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>生效时间：</label>
				<input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${policyRules.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>截止时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${policyRules.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-sm green" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<shiro:hasPermission name="policyrules:policyRules:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="policyRules">
			<tr>
				<td><a href="${ctx}/policyrules/policyRules/form?id=${policyRules.id}">
					<fmt:formatDate value="${policyRules.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<shiro:hasPermission name="policyrules:policyRules:edit"><td>
    				<a href="${ctx}/policyrules/policyRules/form?id=${policyRules.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
					<a href="${ctx}/policyrules/policyRules/delete?id=${policyRules.id}" onclick="return confirmx('确认要删除该政策明细吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>