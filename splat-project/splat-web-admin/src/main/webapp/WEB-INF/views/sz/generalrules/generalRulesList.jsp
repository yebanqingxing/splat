<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总则管理</title>
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
		<li class="active"><a href="${ctx}/generalrules/generalRules">总则列表</a></li>
		<shiro:hasPermission name="generalrules:generalRules:edit"><li><a href="${ctx}/generalrules/generalRules/form">总则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="generalRules" action="${ctx}/generalrules/generalRules/" method="post" class="breadcrumb form-search">
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
				<th>最后更新日期</th>
				<shiro:hasPermission name="generalrules:generalRules:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="generalRules">
			<tr>
				<td><a href="${ctx}/generalrules/generalRules/form?id=${generalRules.id}">
					<fmt:formatDate value="${generalRules.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<shiro:hasPermission name="generalrules:generalRules:edit"><td>
    				<a href="${ctx}/generalrules/generalRules/form?id=${generalRules.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
					<a href="${ctx}/generalrules/generalRules/delete?id=${generalRules.id}" onclick="return confirmx('确认要删除该总则吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>