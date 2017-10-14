<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table  bgcolor="#DCDCDC" align="center" width="1200" border="1">
<tr>
<th>序列</th>
<th>总则编码</th>
<th>符合的航空公司</th>
<th>创建日期</th>
<th>状态</th>
<th>销售价计算选择方式</th>
<th>操作</th>
</tr>
<hr/>
<c:forEach items="${generalList}" var="general" varStatus="pList">
<tr>
<td>${pList.index+1}</td>
<td>${general.grId}</td>
<td>${general.supplierId}</td>
<td>${general.strDate}</td>
<td>
<c:if test="${general.generalStatus == 0}">启用</c:if>
<c:if test="${general.generalStatus == 1}">禁用</c:if>
</td>
<td>${general.formulaId}</td>
<td>
<c:if test="${general.generalStatus == 1}"><font color="red">停用</font></c:if>
<c:if test="${general.generalStatus == 0}">
<a href="javascript:void(0);" onclick="deleteGeneralRules('${general.grId}')">停用</a>
</c:if>
&nbsp;详情&nbsp;
<a href = "${ctx}/generalrules/generalRules/toEdit?grId=${general.grId}">修改</a></td>
</tr>
</c:forEach>
</table>
