]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<shiro:hasPermission name="sys:role:edit"><li><a href="${ctx}/sys/role/form">角色添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed flip-content">
	    <thead class="flip-content">
	        <tr><th>角色名称</th><th>英文名称</th><th>归属机构</th><th>数据范围</th><shiro:hasPermission name="sys:role:edit"><th>操作</th></shiro:hasPermission></tr>
	    </thead>
		<tbody>
		  <c:if test="${list == null||fn:length(list) ==0}">
		   <tr><td colspan="5" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
		  </c:if>
			<c:forEach items="${list}" var="role">
				<tr>
					<td><a href="form?id=${role.id}">${role.name}</a></td>
					<td><a href="form?id=${role.id}">${role.enname}</a></td>
					<td>${role.office.name}</td>
					<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>
					<shiro:hasPermission name="sys:role:edit"><td>
						 <a href="${ctx}/sys/role/assign?id=${role.id}" class="btn btn-xs blue">分配 <i class="icon-crop"></i></a>
						<c:if test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
							 <a href="${ctx}/sys/role/form?id=${role.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
						</c:if>
	    			<a href="${ctx}/sys/role/delete?id=${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
					</td></shiro:hasPermission>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>