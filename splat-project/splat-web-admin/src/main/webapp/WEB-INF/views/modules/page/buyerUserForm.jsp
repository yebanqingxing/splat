<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
	<div class="effect-head clear" style="margin-top:20px; margin-bottom:30px;">
		<p><a href="${ctx}/sys/user/form?id=${user.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></p>
		<img src="${ctxStatic}/images/right.jpg">
	</div>
	<div class="clear"></div>
	
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type = "hidden" id = "isAdmin" value = "${isAdmin}"/>
		<sys:message content="${message}"/>
		<input type = "hidden" id = "office_id"  value = ""/>
		<form:hidden path="platType" value = "B"/>
		<div class="control-group">
			<label class="control-label">所属部门:</label>
			<div class="controls">
                <sys:treeselect id="company" name="office.id" value="${not empty user.id?user.office.id:''}"  labelName="officeName" labelValue="${not empty user.id?user.office.name:''}" notAllowSelectRoot="false"
					title="所属部门" url="/sys/office/treeData?platType=B&isAll=true&type=1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色:</label>
			<div class="controls">
                <sys:treeselect id="role" name="roleIdList" value="${not empty user.id?user.roleIds:''}"  labelName="role.name" labelValue="${not empty user.id?user.roleNames:''}"
					isAddUser="true" title="角色" url="/sys/role/treeDate?platType=B" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test = "${empty user.id }">
			<div class="control-group">
				<label class="control-label">初始密码:</label>
				<div class="controls">
					<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">确认密码:</label>
				<div class="controls">
					<input name = "confirmpassword" type="password"  maxlength="50" minlength="3" class="required" equalTo="#newPassword"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">支付编号:</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付密钥:</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="100"/>
			</div>
		</div> -->
		</div> 
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:select path="loginFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">所在城市:</label>
			<div class="controls">
				<form:input path="company.area.name" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司地址:</label>
			<div class="controls">
				<form:input path="company.address" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司邮编:</label>
			<div class="controls">
				<form:input path="company.zipCode" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="userType" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>  --%>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">配置:</label>
			<div class="controls">
				<input type = "text" />
				<input type = "button" value = "修改"/>
			</div>
		</div> -->
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>