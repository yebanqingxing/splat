<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function addRole(){
			window.location.href="${ctx}/sys/role/buyerForm";
			return false;
		}
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/role/buyerList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<style>
		.addpolicy p {
		    float: left;
		    font-size: 16px;
		    color: #292388;
		}
		#contentTable tr td,#contentTable tr th{
			text-align:center;
		}
	</style>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<shiro:hasPermission name="sys:role:edit"><li><a href="${ctx}/sys/role/form">角色添加</a></li></shiro:hasPermission>
	</ul> --%>
	<sys:message content="${message}"/>
	<div class="addpolicy" style="background-color:#d2f3f6;">
		<img src="${ctxStatic}/images/ordersearch.png">
		<p>操作员角色查询</p>
	</div>
	<div class="query">
		<form:form id="searchForm" modelAttribute="role" action="${ctx}/sys/role/buyerList?type=1" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<span class="query-span fl" style="width: 90px; display: inline-block;">角色名称：</span>
			<form:input path = "name" htmlEscape="false" maxlength="50" class="querycode fl" style="height:30px;"/>
			<span class="query-span fl">状态信息：</span>
			<form:select path="useable"  class="selectquery fl" style="margin-right: 20px; margin-top:0; height:30px;" >
				<form:option value = "1" label = "启用"/>
				<form:option value = "0" label = "禁用"/>
			</form:select>
			<button class="addquery fl" onclick="submit()">查询</button>
			<button  class="addquery" onclick = " return addRole();" >新增</button>
		</form:form>
			
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed flip-content" style="margin-top:40px;">
	    <thead class="flip-content">
	        <tr><th>角色名称</th><th>英文名称</th><th>归属机构</th><th>数据范围</th><shiro:hasPermission name="sys:role:edit"><th>操作</th></shiro:hasPermission></tr>
	    </thead>
		<tbody>
		  <c:if test="${page.list == null||fn:length(page.list) ==0}">
		   <tr><td colspan="5" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
		  </c:if>
			<c:forEach items="${page.list}" var="role">
				<tr>
					<td><a href="${ctx}/sys/role/buyerForm?platType=B&id=${role.id}">${role.name}</a></td>
					<td><a href="${ctx}/sys/role/buyerForm?platType=B&id=${role.id}">${role.enname}</a></td>
					<td>${role.office.name}</td>
					<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>
					<%-- <shiro:hasPermission name="sys:role:edit"> --%>
						<td>
							 <a href="${ctx}/sys/role/assign?id=${role.id}" class="btn btn-xs blue">分配 <i class="icon-crop"></i></a>
							 <%-- <c:if test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
								 <a href="${ctx}/sys/role/form?id=${role.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
							 </c:if> --%>
							  <a href="${ctx}/sys/role/buyerForm?platType=B&id=${role.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
		    				 <a href="${ctx}/sys/role/delete?platType=B&id=${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
						</td>
					<%-- </shiro:hasPermission>	 --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>