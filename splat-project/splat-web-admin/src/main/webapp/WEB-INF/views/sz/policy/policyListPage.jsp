<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分页政策查询页面</title>
</head>
<body  >
<table bgcolor="#DCDCDC" border="1" width="100%" height="100%" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<th>航班索引</th>
		<th>航班编号</th>
		<th>政策名称</th>
		<th>航司</th>
		<th>创建时间</th>
		<th>行程</th>
		<th>返点</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
<c:forEach items="${policyList}" var="po" varStatus="pList">
	<tr align="center">
		<td>${pList.index+1}</td>
		<td>${po.id}</td>
		<td>${po.policyName}</td>
		<td>${po.tktAirline}</td>
		<td>${po.str}</td>
		<td>
			<c:if test="${po.travelType==0}">单程</c:if>
			<c:if test="${po.travelType==1}"><font color="red" style="background:pink">返程</font></c:if>
			<c:if test="${po.travelType==2}"><font color="red" style="background:green">单程/返程</font></c:if>
		</td>
		<td>${po.rebate}</td>
		<td>
			<c:if test="${po.policyStatus==0}">启用</c:if>
			<c:if test="${po.policyStatus==1}"><font color="red" style="background:pink">禁用</font></c:if>
			<c:if test="${po.policyStatus==2}"><font color="red" style="background:green">挂起</font></c:if>
		</td>
		<td>
			<table>
				<tr>
<%-- 				<shiro:hasPermission name="tbpolicy:tbPolicyRules:edit"> --%>
					<td>
					<a href="${ctx}/policyrules/policyRules/delete?id=${po.id}"/>删除</a>&nbsp;
					停用&nbsp;详情&nbsp;
					<a href="${ctx}/policyrules/policyRules/toUpdate?id=${po.id}&&flag=3"/>修改</a>
					&nbsp;复制</td>
<%-- 				</shiro:hasPermission> --%>
				</tr>
			</table>
		</td>
	</tr>
</c:forEach>
<tr>
<td colspan="9">
<%-- <jsp:include page="/common/ajaxpage.jsp"></jsp:include> --%>
</td>
</tr>
</table>

	

</body>
</html>