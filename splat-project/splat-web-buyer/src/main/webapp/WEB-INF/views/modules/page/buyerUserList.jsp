<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			}); 
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/buyerList");
			$("#searchForm").submit();
	    	return false;
	    }
		function resetPassword(id){
			$.ajax({
				url:"${ctx}/sys/user/passwordReset?id="+id,
				success:function(date){
					if(date == "ok"){
						alert("重置密码成功");
					}else{
						alert("重置密码失败，请与管理员联系");
					}
				}
			});
		}
	</script>
</head>
<body>
	<style>
		.ul-form li {
			margin-bottom:15px;
		}
		#contentTable tr td,#contentTable tr th{
			text-align:center;
		}
	</style>
	 <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div> 
	<div class="effect-head clear" style="margin-top:20px;">
		<p> 操作员查询：</p>
		<img src="${ctxStatic}/images/right.jpg">
	</div>
	<div class="clear"></div>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search" style="background-color:#FFF;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li>
				<label>姓名：</label> 
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/> 
				
				<label class="control-label">状态:</label>
				<form:select path="loginFlag" >
					<form:option value = "1" label = "正常"/>
					<form:option value = "0" label = "禁用"/>
				</form:select>
			</li>
			<li>
				<label class = "control-label">角色名称</label>
				<form:input path = "role.name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li>
				<label>用户名:</label>
				<form:input path="loginName" htmlEscape = "false" maxlength="50" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${user.office.id}" 
								labelName="office.name" labelValue="${user.office.name}" 
								title="部门" url="/sys/office/treeData?platType=B&isAll=true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li> 
			<li class="btns" style="margin-top:3px;">
				<input id="btnSubmit" class="btn btn-sm green" type="submit" value="查询" onclick="return page();"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed flip-content" style="margin-top:40px;">
		<thead class="flip-content"><tr><th>姓名</th><th class="name">所属部门</th><th>角色</th><th>手机</th><th>状态</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:if test="${page.list == null||fn:length(page.list) ==0}">
		   <tr><td colspan="7" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
		</c:if>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.office.name}</td>
				<td>
					${user.roleNames}
				</td>
				<td>${user.mobile}</td>
				<c:choose>
					<c:when test="${user.loginFlag == '1' }">
						<td>正常</td>
					</c:when>
					<c:otherwise>
						<td>禁用</td>
					</c:otherwise>
				</c:choose>				
				<%-- <shiro:hasPermission name="sys:user:edit"> --%>
					<td>
						<a href="${ctx}/sys/user/detial?platType=B&id=${user.id}" class="btn btn-xs red">详情 <i class="fa fa-edit"></i></a>
						<a href="${ctx}/sys/user/buyerForm?id=${user.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
						<a onclick=resetPassword("${user.id}")  class="btn btn-xs red">
							重置密码 
							<i class="fa fa-edit"></i>
						</a>
	    				<a href="${ctx}/sys/user/delete?platType=B&id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
					</td>
				<%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>