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
		<li class="active"><a href="${ctx}/pay/tbCity/">测试-功能名列表</a></li>
		<shiro:hasPermission name="pay:tbCity:edit"><li><a href="${ctx}/pay/tbCity/form">测试-功能名添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbCity" action="${ctx}/pay/tbCity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-sm green" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="pay:tbCity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbCity">
			<tr>
				<shiro:hasPermission name="pay:tbCity:edit"><td>
    				<a href="${ctx}/pay/tbCity/form?id=${tbCity.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
					<a href="${ctx}/pay/tbCity/delete?id=${tbCity.id}" onclick="return confirmx('确认要删除该测试-功能名吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>