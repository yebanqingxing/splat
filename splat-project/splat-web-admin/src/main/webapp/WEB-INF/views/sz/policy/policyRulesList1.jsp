<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.js"></script>
<title>国际政策查询</title>
</head>
<body>
<br><br>
<table bgcolor="#DCDCDC" align="center" width="1000">
	<tr>
		<td>政策名称</td>
		<td><input type="text" id="policy_name_id"></td>
		<td>出票日期</td>
		<td>
			<input type="text" id="minDate"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			===
			<input type="text" id="maxDate"  class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  
		</td>
	</tr>
	<tr>
		<td>去程起点</td>
		<td><input type="text"></td>
		<td>创建时间</td>
		<td>
			<input type="text" id="minDate1"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			===
			<input type="text" id="maxDate1"  class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  
		</td>
	</tr>
	<tr>
		<td>投放分销商</td>
		<td colspan="3"><input type="text" id="supplierId"></td>
	</tr>
	<tr>
		<td>状态</td>
		<td>
		<select id="policyStatusId">
			<option value="0">启用</option>
			<option value="1">禁用</option>
			<option value="2">挂起</option>
		</select>
		</td>
		<td><input type="button" value="查询" onclick="search()"/></td>
		<td><input type="button" value="添加" onclick="addPolicy()"></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
</table>
<br>
<div id="serchId">
<jsp:include page="/WEB-INF/views/sz/policy/policyListPage.jsp"></jsp:include>
</div>
</body>
<script type="text/javascript">

function addPolicy(){
	window.location.href="${ctx}/policyrules/policyRules/toSave";
}

function search(pageIndex){
	var v_minDate = $("#minDate").val();
	var v_maxDate = $("#maxDate").val();
	var v_minDate1 = $("#minDate1").val();
	var v_maxDate1= $("#maxDate1").val();
	var v_policy_name = $("#policy_name_id").val();
	var v_supplierId = $("#supplierId").val();
	var v_policyStatusId = $("#policyStatusId").val();
	var v_pageSize=$("#pageSize_pageSize").val()
	$.post(
		"${ctx}/policyrules/policyRules/list",
		{"policyName":v_policy_name,
		"tktTimeStart":v_minDate,
		"tktTimeEnd":v_maxDate,
		"workTime":v_minDate1,
		"endTime":v_maxDate1,
		"supplierId":v_supplierId,
		"policyStatus":v_policyStatusId,
			"flag":1
			},
		function(date){
			$("#serchId").html(date);
		}
	);
}

var finallyPageIndex;
function turnPage(pageIndex,pageCount){
	if(pageIndex<1 || pageIndex >pageCount*1){
		alert("输入页数不合法");
		if(finallyPageIndex==null){
			finallyPageIndex=1;
		}
		$("#pageIndex_page").val(finallyPageIndex);
		return;
	}
	finallyPageIndex = pageIndex;
	search(pageIndex);
}


</script>
</html>