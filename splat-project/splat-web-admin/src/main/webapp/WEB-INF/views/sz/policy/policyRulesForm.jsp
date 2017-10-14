<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策明细管理</title>
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
		<li><a href="${ctx}/policyrules/policyRules/">政策明细列表</a></li>
		<li class="active"><a href="${ctx}/policyrules/policyRules/form?id=${policyRules.id}">政策明细<shiro:hasPermission name="policyrules:policyRules:edit">${not empty policyRules.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="policyrules:policyRules:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="policyRules" action="${ctx}/policyrules/policyRules/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">pr_code：</label>
			<div class="controls">
				<form:input path="prCode" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">政策名称：</label>
			<div class="controls">
				<form:input path="policyName" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 0 启用，1禁用，2挂起：</label>
			<div class="controls">
				<form:input path="policyStatus" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投放分销商：</label>
			<div class="controls">
				<form:input path="supplierId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">office：</label>
			<div class="controls">
				<form:input path="office" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">留学生 移民 劳工 海员 青年 老年 探亲：</label>
			<div class="controls">
				<form:input path="passagerPid" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出票航司：</label>
			<div class="controls">
				<form:input path="tktAirline" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">旅客人数下限：</label>
			<div class="controls">
				<form:input path="passagerCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行程类型,单程、往返、单程/往返：</label>
			<div class="controls">
				<form:input path="travelType" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程起点：</label>
			<div class="controls">
				<form:input path="outOrg" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">out_org_ex：</label>
			<div class="controls">
				<form:input path="outOrgEx" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程终点/折返点：</label>
			<div class="controls">
				<form:input path="outDes" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程终点（折返点）除外：</label>
			<div class="controls">
				<form:input path="outDesEx" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程终点：</label>
			<div class="controls">
				<form:input path="returnDes" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程终点除外：</label>
			<div class="controls">
				<form:input path="returnDesEx" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程不允许经过：</label>
			<div class="controls">
				<form:input path="outUnablePass" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程必须经过：</label>
			<div class="controls">
				<form:input path="outMustPass" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程不允许经过：</label>
			<div class="controls">
				<form:input path="returnUnablePass" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">return_must_pass：</label>
			<div class="controls">
				<form:input path="returnMustPass" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仅限航班号：</label>
			<div class="controls">
				<form:input path="allowFilghtNo" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排除航班号：</label>
			<div class="controls">
				<form:input path="excludeFilghtNo" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">舱位：</label>
			<div class="controls">
				<form:input path="cabin" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">返点：</label>
			<div class="controls">
				<form:input path="rebate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开票费：</label>
			<div class="controls">
				<form:input path="billingFee" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理费：</label>
			<div class="controls">
				<form:input path="agencyFee" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">儿童奖励情况 :01后返与成人一至，02可开无后返，03不可开,04指定奖励,：</label>
			<div class="controls">
				<form:input path="childrenReward" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">儿童返点：</label>
			<div class="controls">
				<form:input path="childrenRebate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">儿童手续费：</label>
			<div class="controls">
				<form:input path="childrenPoundage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">儿童加手续费 Y/N：</label>
			<div class="controls">
				<form:input path="childrenPoundageChoice" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">儿童可开无代理费 Y/N：</label>
			<div class="controls">
				<form:input path="childrenOpenNoCom" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">婴儿奖励情况 0 可开无奖励，1 不可开：</label>
			<div class="controls">
				<form:input path="badyReward" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第一国际段旅行日期 开始：</label>
			<div class="controls">
				<input name="firstDateStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.firstDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第一国际段旅行日期 结束：</label>
			<div class="controls">
				<input name="firstDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.firstDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后国际段旅行日期 开始：</label>
			<div class="controls">
				<input name="lastDateStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.lastDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后国际段旅行日期 结束：</label>
			<div class="controls">
				<input name="lastDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.lastDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出票日期 开始：</label>
			<div class="controls">
				<input name="tktTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.tktTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出票日期  结束：</label>
			<div class="controls">
				<input name="tktTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.tktTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许与其他政策组合 Y/N：</label>
			<div class="controls">
				<form:input path="sharePolicy" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="policyRemark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生效时间：</label>
			<div class="controls">
				<input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">截止时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${policyRules.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建用户ID：</label>
			<div class="controls">
				<form:input path="creatUser" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新用户：</label>
			<div class="controls">
				<form:input path="updateUser" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="policyrules:policyRules:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>