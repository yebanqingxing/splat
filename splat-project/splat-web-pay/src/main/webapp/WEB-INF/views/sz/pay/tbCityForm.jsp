<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试-功能名管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		<li><a href="${ctx}/pay/tbCity/">测试-功能名列表</a></li>
		<li class="active"><a href="${ctx}/pay/tbCity/form?id=${tbCity.id}">测试-功能名<shiro:hasPermission name="pay:tbCity:edit">${not empty tbCity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pay:tbCity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbCity" action="${ctx}/pay/tbCity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">城市名中文：</label>
			<div class="controls">
				<form:input path="cityNameCn" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市名中文简称：</label>
			<div class="controls">
				<form:input path="cityNameCnAbbr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市名英文：</label>
			<div class="controls">
				<form:input path="cityNameEn" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市名中文全拼：</label>
			<div class="controls">
				<form:input path="cityNamePinyin" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市名中文简拼：</label>
			<div class="controls">
				<form:input path="cityNamePinyinName" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">city_code：</label>
			<div class="controls">
				<form:input path="cityCode" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二字码：</label>
			<div class="controls">
				<form:input path="cityCodeAbbr" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属省份名称：</label>
			<div class="controls">
				<form:input path="provinceNameCn" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在省州中文名：</label>
			<div class="controls">
				<form:input path="iataAreaCd" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子区码：</label>
			<div class="controls">
				<form:input path="iataRegionEnAbbr" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子区中文名：</label>
			<div class="controls">
				<form:input path="iataRegionCnName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子区英文名：</label>
			<div class="controls">
				<form:input path="iataRegionEn" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pay:tbCity:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>