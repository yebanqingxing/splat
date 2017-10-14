<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/office/list?id=${office.parent.id}&parentIds=${office.parentIds}">机构列表</a></li>
		<li class="active">
			<a href="${ctx}/sys/office/form?id=${office.id}&parent.id=${office.parent.id}">
				<shiro:hasPermission name="sys:office:edit">
					<%-- <c:if test="${platType=='B'}">采购商添加</c:if>
					<c:if test="${platType=='C'}">供应商添加</c:if> --%>
					 ${platType=='B'?'采购商添加':'供应商添加'} 
				</shiro:hasPermission>
				<shiro:lacksPermission name="sys:office:edit">
					查看
				</shiro:lacksPermission>
			</a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级单位:</label>
			<div class="controls">
                <sys:treeselect id="office" name="parent.id" value="${office.id}" labelName="parentName" labelValue="${office.name}"
					title="单位" url="/sys/office/treeData?edit=true" cssClass="required" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购商名称:</label>
			<div class="controls">
              	<form:input path="name"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
				<form:input path="master"/>
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path = "email" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司地址:</label>
			<div class="controls">
				<form:input path = "address" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司邮编:</label>
			<div class="controls">
				<form:input path = "zipCode" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司电话:</label>
			<div class="controls">
				<form:input path = "phone" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属区域:</label>
			<div class="controls">
				<form:input path = "area.id" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">配置:</label>
			<div class="controls">
				<input type = "text" />
				<input type = "button" value = "修改"/>
			</div>
		</div>
		<c:choose>
			<c:when test="${platType=='A'}">
				<div class="control-group">
					<label class="control-label">平台类型:</label>
					<div class="controls">
							<form:select path="platType" class="input-medium">
								<form:option value = "A">平台</form:option>
								<form:option value = "S">供应商</form:option>
								<form:option value = "B">采购商</form:option>
							</form:select>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<form:hidden path="platType" value = "${platType }"/>
			</c:otherwise>
		</c:choose>
		
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表此账号允许登陆，“否”则表示此账号不允许登陆</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">主负责人:</label>
			<div class="controls">
				 <sys:treeselect id="primaryPerson" name="primaryPerson.id" value="${office.primaryPerson.id}" labelName="office.primaryPerson.name" labelValue="${office.primaryPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副负责人:</label>
			<div class="controls">
				 <sys:treeselect id="deputyPerson" name="deputyPerson.id" value="${office.deputyPerson.id}" labelName="office.deputyPerson.name" labelValue="${office.deputyPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<%-- <c:if test="${empty office.id}">
			<div class="control-group">
				<label class="control-label" style="padding-top:0px;">快速添加下级部门:</label>
				<div class="controls">
					<form:checkboxes path="childDeptList" items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</c:if> --%>
		<div class="form-actions">
			<shiro:hasPermission name="sys:office:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>