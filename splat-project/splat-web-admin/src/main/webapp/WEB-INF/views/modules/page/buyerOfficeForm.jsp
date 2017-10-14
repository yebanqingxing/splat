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
	<div class="effect-head clear" style="margin-top:31px;margin-bottom:4px;">
		<p>采购商<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission>：</p>
		<img src="${ctxStatic}/images/right.jpg">
	</div>
	<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="parent.id" value = "0"/>
		<form:hidden path="platType" value = "B"/>
		<form:hidden path="type" value = "1"/>
		<sys:message content="${message}"/>
		<%-- <div class="control-group">
			<label class="control-label">上级单位:</label>
			<div class="controls">
                <sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parentName" labelValue="${office.parent.name}"
					title="单位" url="/sys/office/treeData?edit=true&platType=B" cssClass="required" notAllowSelectParent="false"/>
			</div>
		</div> --%>
		<br>
		<div class="control-group">
			<label class="control-label">采购商名称:</label>
			<div class="controls">
              	<form:input path="name" class="required"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
				<form:input path="master" class="required"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path = "email" class = "email required"/><span style="color:red">*</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司地址:</label>
			<div class="controls">
				<form:input path = "address" class="required"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司邮编:</label>
			<div class="controls">
				<form:input path = "zipCode" class="required"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司电话:</label>
			<div class="controls">
				<form:input path = "phone" class="required"/><span style="color:red">*</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属区域:</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="areaName" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required" notAllowSelectParent="false"/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">配置:</label>
			<div class="controls">
				<input type = "text" />
				<input type = "button" value = "修改"/>
			</div>
		</div>
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
				<span class="help-inline"><font color="red">*</font>“是”代表此账号允许登陆，“否”则表示此账号不允许登陆</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="sys:office:edit"> --%>
				<input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;
			<%-- </shiro:hasPermission> --%>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>