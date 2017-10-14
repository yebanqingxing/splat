<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<table bgcolor="#DCDCDC" align="center" width="1000">
<tr>
<td>总则编码</td>
<td><input id="grId" /></td>
<td>航空公司两字码</td>
<td><input id="airCode"/></td>
</tr>
<tr align="center">
<td colspan="2">
<input type = "button" value="查询" onclick="search()"/>
</td>
<td colspan="2">
<input type = "button" onclick="addGeneral()" value="添加"/>
</td>
</tr>
</table>

<div id="serchId">
<jsp:include page="/WEB-INF/views/sz/generalrules/generalRulesListPage.jsp"></jsp:include>
</div>
</body>
<script type="text/javascript">
function addGeneral(){
	window.location.href="${ctx}/generalrules/generalRules/toSave";
}

function search(pageIndex){
	var v_airCode = $("#airCode").val();
	var v_grId = $("#grId").val();
	$.post(
		"${ctx}/generalrules/generalRules/list",
		{"airCode":v_airCode,
		"grId":v_grId,
			"flag":1
			},
		function(date){
			$("#serchId").html(date);
		}
	);
}
	function deleteGeneralRules(id){
		$.post(
				"${ctx}/generalrules/generalRules/delete",
				{"grId":id,
					},
				function(date){
						search();
				}
			);
	}

</script>
</html>