<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单生成模块管理</title>
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
		<li><a href="${ctx}/order/tbTicketorderDetail/">订单生成模块列表</a></li>
		<li class="active"><a href="${ctx}/order/tbTicketorderDetail/form?id=${tbTicketorderDetail.id}">订单生成模块<shiro:hasPermission name="order:tbTicketorderDetail:edit">${not empty tbTicketorderDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:tbTicketorderDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbTicketorderDetail" action="${ctx}/order/tbTicketorderDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">源订单号：</label>
			<div class="controls">
				<form:input path="originOrderNo" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">crs_pnr：</label>
			<div class="controls">
				<form:input path="crsPnr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">arl_pnr：</label>
			<div class="controls">
				<form:input path="arlPnr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">mid_pnr：</label>
			<div class="controls">
				<form:input path="midPnr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单创建者：</label>
			<div class="controls">
				<form:input path="createAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国内或国际，使用枚举0-国内，1-国际：</label>
			<div class="controls">
				<form:input path="isInternational" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散客或团队，使用枚举0-散客，1-团队，2-混合：</label>
			<div class="controls">
				<form:input path="isFit" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:input path="orderStatus" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付状态 0已支付,1未支付：</label>
			<div class="controls">
				<form:input path="payStatus" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出票航空公司：</label>
			<div class="controls">
				<form:input path="issueAirline" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分销商：</label>
			<div class="controls">
				<form:input path="relevantClient" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关订单号：</label>
			<div class="controls">
				<form:input path="relevantOrderNo" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首段行程（冗余）入库格式：航班;行程;舱位;旅行日期,例如 ca1831;pek-sha;q;2014-07-15;：</label>
			<div class="controls">
				<form:input path="firstSegment" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首段起飞日期冗余：</label>
			<div class="controls">
				<input name="firstDepartureTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.firstDepartureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建方式(0:预订式-定价；1：导入式-定价；3：导入式-核价；4：交互式-定价；5：其他；8：接口式-定价；10:一站式;12:需求单；13：外购式-支票；14：团队单；15：外购式-现金；16：政府采购；17：境外电子)：</label>
			<div class="controls">
				<form:input path="createType" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前操作组（流转）：</label>
			<div class="controls">
				<form:input path="currentGroup" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前操作人（流转）：</label>
			<div class="controls">
				<form:input path="currentOperator" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">后续操作：</label>
			<div class="controls">
				<form:input path="nextOperation" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上游产品商（销售相关）：</label>
			<div class="controls">
				<form:input path="supplierName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上游产品号（销售相关）：</label>
			<div class="controls">
				<form:input path="supplierProductNo" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售方式：</label>
			<div class="controls">
				<form:input path="saleConfig" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退改签规则：</label>
			<div class="controls">
				<form:input path="rvcRules" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pnr状态（冗余）1：正常，2：取消，3：异常，4：空：</label>
			<div class="controls">
				<form:input path="pnrStatus" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机票状态（冗余）：</label>
			<div class="controls">
				<form:input path="ticketStatus" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">od信息，带时间，例如pek-hkg;2014-08-04/hkg-pek;2014-09-10：</label>
			<div class="controls">
				<form:input path="originDestinationOptions" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单创建者id：</label>
			<div class="controls">
				<form:input path="createAccountId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分销商id：</label>
			<div class="controls">
				<form:input path="receptionCustomerId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中台创建操作员id：</label>
			<div class="controls">
				<form:input path="stationCreaterId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否金额特单1=是，0=否：</label>
			<div class="controls">
				<form:input path="isOverloanOrder" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否政策特单1=是，0=否：</label>
			<div class="controls">
				<form:input path="isPolicyNegoOrder" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程主航段航班：</label>
			<div class="controls">
				<form:input path="goMainFlight" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程主航段航班：</label>
			<div class="controls">
				<form:input path="backMainFlight" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程主航段行程：</label>
			<div class="controls">
				<form:input path="goMainSegment" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程主航段行程：</label>
			<div class="controls">
				<form:input path="backMainSegment" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程主航段舱位：</label>
			<div class="controls">
				<form:input path="goMainCabin" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程主航段舱位：</label>
			<div class="controls">
				<form:input path="backMainCabin" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去程主航段旅行日期,格式例如:2014-07-15：</label>
			<div class="controls">
				<input name="goMainTraveldate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.goMainTraveldate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回程主航段旅行日期,格式例如:2014-07-15：</label>
			<div class="controls">
				<input name="backMainTraveldate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.backMainTraveldate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上游服务费：</label>
			<div class="controls">
				<form:input path="supplierServiceCharge" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下游服务费：</label>
			<div class="controls">
				<form:input path="distributorServiceCharge" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联订单号：</label>
			<div class="controls">
				<form:input path="relationOrderNo" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三方平台订单号(商家订单号)：</label>
			<div class="controls">
				<form:input path="plateformOrderNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行程类型,取值采用airbussinessenum.triptype。取值范围013(分表表示owt中转)：</label>
			<div class="controls">
				<form:input path="tripType" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否自动预订，0:手工  1：自动：</label>
			<div class="controls">
				<form:input path="isAutoBook" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预订结果，0：失败  1：成功：</label>
			<div class="controls">
				<form:input path="bookResult" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自动预订失败描述：</label>
			<div class="controls">
				<form:input path="bookRemark" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否自动取消，0:手工  1：自动：</label>
			<div class="controls">
				<form:input path="isAutoCancel" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取消结果，0：失败  1：成功：</label>
			<div class="controls">
				<form:input path="cancelResult" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自动取消失败描述：</label>
			<div class="controls">
				<form:input path="cancelRemark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否自动出票，0:手工  1：自动：</label>
			<div class="controls">
				<form:input path="isAutoIssue" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出票结果，0：失败  1：成功：</label>
			<div class="controls">
				<form:input path="issueResult" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自动出票失败描述：</label>
			<div class="controls">
				<form:input path="issueRemark" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上游订单来源,0：本地订单 1：d平台  2：g系统, 默认值是0：</label>
			<div class="controls">
				<form:input path="orderSource" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新pnr：</label>
			<div class="controls">
				<form:input path="newPnr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人电话：</label>
			<div class="controls">
				<form:input path="contactPhone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系列号：</label>
			<div class="controls">
				<form:input path="serialNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手工标记1：</label>
			<div class="controls">
				<form:input path="manRemark1" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手工标记2：</label>
			<div class="controls">
				<form:input path="manRemark2" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改机构id：</label>
			<div class="controls">
				<form:input path="lastmodifierOrgid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改机构名称：</label>
			<div class="controls">
				<form:input path="lastmodifierOrgname" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改人id：</label>
			<div class="controls">
				<form:input path="lastmodifierId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改人名：</label>
			<div class="controls">
				<form:input path="lastmodifierName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="lastmodifierTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.lastmodifierTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务说明：</label>
			<div class="controls">
				<form:input path="busiRemark" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上游支付方式说明：</label>
			<div class="controls">
				<form:input path="paymentRemark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">滞后处理（1滞后，0/null 非滞后）：</label>
			<div class="controls">
				<form:input path="handleLater" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后流转时间：</label>
			<div class="controls">
				<input name="lastCirculationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tbTicketorderDetail.lastCirculationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格是否确认，0:不确认；1:确认：</label>
			<div class="controls">
				<form:input path="isPriceConfirmed" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单据按钮操作来源：</label>
			<div class="controls">
				<form:input path="operationSource" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合单/拆单类型，合单：0；拆单：1：</label>
			<div class="controls">
				<form:input path="splitType" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申批规则id：</label>
			<div class="controls">
				<form:input path="approveRuleId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申批规则备注：</label>
			<div class="controls">
				<form:input path="approveRuleRemark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">0：因公，1：因私，默认是1：</label>
			<div class="controls">
				<form:input path="travelType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="order:tbTicketorderDetail:edit"><input id="btnSubmit" class="btn green" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn default" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>