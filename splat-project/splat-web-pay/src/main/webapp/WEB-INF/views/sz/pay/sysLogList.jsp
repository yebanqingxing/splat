<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试-功能名管理</title>
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
		<li class="active"><a href="${ctx}/pay/sysLog/">测试-功能名列表</a></li>
		<shiro:hasPermission name="pay:sysLog:edit"><li><a href="${ctx}/pay/sysLog/form">测试-功能名添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysLog" action="${ctx}/pay/sysLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日志标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-sm green" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日志标题</th>
				<shiro:hasPermission name="pay:sysLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysLog">
			<tr>
				<td><a href="${ctx}/pay/sysLog/form?id=${sysLog.id}">
					${sysLog.title}
				</a></td>
				<shiro:hasPermission name="pay:sysLog:edit"><td>
    				<a href="${ctx}/pay/sysLog/form?id=${sysLog.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
					<a href="${ctx}/pay/sysLog/delete?id=${sysLog.id}" onclick="return confirmx('确认要删除该测试-功能名吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>