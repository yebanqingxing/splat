<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta>
<title>部门添加</title>
</head>
<body>
	<div class="addpolicy">
		<img src="${ctxStatic }/images/backadd.png">
		<p>部门添加</p>
	</div>
	<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<form:hidden path="platType" value = "S"/>
		<form:hidden path="type" value = "2"/>
		<form:hidden path="useable" value = '1'/>
		<div class="addcontent">
			<div class="addconrow clear" style="margin-top: 30px;">
				<c:if test="${fns:getUser().admin}">
					<span>单位：</span>
					<div class="addiv fl">
						<sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name" labelValue="${office.parent.name}"
							title="单位" url="/sys/office/treeData?platType=S&type=1" cssClass="required" notAllowSelectParent="false"/>
						<p class="redreset"></p>
					</div>
				</c:if>
				<span>部门名称：</span>
				<div class="addiv fl">
					<input name = "name" type="text" value="" class="querycode widadd">
					<p class="redreset"></p>
				</div>
				<!-- <span>联系人姓名：</span>
				<div class="addiv fl">
					<input name = "master" type="text" value="" class="querycode widadd">
					<p class="redreset"></p>
				</div> -->
			</div>
			<div class="addconrow clear">
				<span>邮箱号码：</span>
				<div class="addiv fl">
					<input name = "email" type="text" value="" class="querycode widadd">
					<p class="redreset"></p>
				</div>
				<span>部门电话：</span>
				<div class="addiv fl">
					<input name = "phone" type="text" value="" class="querycode widadd">
					<p class="redreset"></p>
				</div>
			</div>
			<div class="addconrow clear">
				<span>部门地址：</span>
				<div class="addiv fl">
					<input name = "address" type="text"  value="" class="querycode widadd">
					<p class="redreset"></p>
				</div>
				<span>部门邮编：</span>
				<div class="addiv fl">
					<input name = "zipCode" type="text" value="" class="querycode widadd">
					<p class="redreset"></p>
				</div>
			</div>
			<div class="addconrow clear">
				<span>部门级别：</span>
				<div class="addiv fl">
					<form:select path="grade" class="input-medium">
						<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="control-group">
					<label class="control-label">所属区域:</label>
					<div class="controls">
						<sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="areaName" labelValue="${office.area.name}"
							title="单位" url="/sys/area/treeData" cssClass="required" notAllowSelectParent="false"/>
					</div>
				</div>
			</div>
			<button class="addquery" style="display:block;margin:20px auto;">提交</button>
		</div>
	</form:form>
</body>
</html>