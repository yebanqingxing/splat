<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  
<table border="1" class="tablehead">
		<thead>
			 <tr>
<!-- 	    <th>总则编码</th> -->
	    <th>符合的航空公司</th>
	    <th>创建日期</th>
	    <th>状态</th>
<!-- 	    <th>销售价计算方式</th> -->
	    <th>操作</th>
	  </tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="generalRules" varStatus="pList">
			<tr>
<!-- 				<td> -->
<%-- 					${generalRules.grId} --%>
<!-- 				</td> -->
				<td>${fn:substring(generalRules.airCode, generalRules.airCode.length()-1,generalRules.airCode.length())=='/'?fn:substring(generalRules.airCode,0, generalRules.airCode.length()-1):generalRules.airCode}</td>
				<td>
					<fmt:formatDate value="${generalRules.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${generalRules.generalStatus == 0}">启用</c:if>
					<c:if test="${generalRules.generalStatus == 1}">禁用</c:if>
				</td>
<%-- 				<td>${generalRules.formulaId}</td> --%>
				<td>
					<button onclick="deleteGeneralRules('${generalRules.grId}')">停用</button>
			    	<button onclick="toDetails('${generalRules.grId}')">详情</button>
			    	<button onclick="toEdit('${generalRules.grId}')">修改</button>
<%-- 				<shiro:hasPermission name="generalrules:generalRules:edit"><td> --%>
<%--     				<a href="${ctx}/generalrules/generalRules/form?grId=${generalRules.grId}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a> --%>
<%-- 					<a href="${ctx}/generalrules/generalRules/delete?id=${generalRules.grId}" onclick="return confirmx('确认要停用该总则吗？', this.href)" class="btn btn-xs purple"> --%>
<!-- 					停用 <i class="fa fa-times"></i> -->
					</a>
				</td>
<%-- 				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<div class="paging">${page}</div>
<%-- 	<div class="pagination">${page}</div> --%>
	