<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总则管理</title>
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
		<li><a href="${ctx}/generalrules/generalRules/">总则列表</a></li>
		<li class="active"><a href="${ctx}/generalrules/generalRules/form?id=${generalRules.id}">总则<shiro:hasPermission name="generalrules:generalRules:edit">${not empty generalRules.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="generalrules:generalRules:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="generalRules" action="${ctx}/generalrules/generalRules/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">供应商ID：</label>
			<div class="controls">
				<form:input path="supplierId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建用户id：</label>
			<div class="controls">
				<form:input path="createUserId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总则状态，0 启用，1禁用：</label>
			<div class="controls">
				<form:input path="generalStatus" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IT无奖励 Y/N：</label>
			<div class="controls">
				<form:input path="noItReward" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">OPEN无奖励票价,选中Y 不选择N：</label>
			<div class="controls">
				<form:input path="noOpenReward" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低于XX票价无奖励,是否选中，选中了则：</label>
			<div class="controls">
				<form:input path="noLowReward" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低于XX票价无奖励 no_low_reward 为Y 时候才起作用：</label>
			<div class="controls">
				<form:input path="noLowPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">含XX票价无奖励,默认未选中N：</label>
			<div class="controls">
				<form:input path="noInvolveReward" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">含XX票价无奖励 no_involve_reward 为Y 时候才起作用：</label>
			<div class="controls">
				<form:input path="noInvolvePrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">od 去程起点:1-出票航第一个航段的起点,2-出票航实际承运第一个航段起点,3-第一个国际段的起点：</label>
			<div class="controls">
				<form:input path="goOriginChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">od 去程终点:1-出票航飞到的最远点（里程）,2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）：</label>
			<div class="controls">
				<form:input path="goDestinationChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">od 回程终点:1-出票航最后一个航段的终点,2-出票航承运的最后一个航段终点,3-最后一个国际段的终点：</label>
			<div class="controls">
				<form:input path="backDestinationChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据选取 0 最新日期，1销售价最高，2销售价最低：</label>
			<div class="controls">
				<form:input path="dataChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">addon 段 选项 Y/N：</label>
			<div class="controls">
				<form:input path="addOnChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">addon 国内 Y/N,只有add_on_choice 为Y才起作用：</label>
			<div class="controls">
				<form:input path="addOnInternalChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">addon 国际 Y/N,只有add_on_choice 为Y才起作用：</label>
			<div class="controls">
				<form:input path="addOnWorldChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spa 段 Y/N：</label>
			<div class="controls">
				<form:input path="spaChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值计入奖励 Y/N：</label>
			<div class="controls">
				<form:input path="qChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生效日期：</label>
			<div class="controls">
				<input name="effectDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${generalRules.effectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算方式：</label>
			<div class="controls">
				<form:input path="formulaId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">默认手续费：</label>
			<div class="controls">
				<form:input path="poundage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">update_user：</label>
			<div class="controls">
				<form:input path="updateUser" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="generalrules:generalRules:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>