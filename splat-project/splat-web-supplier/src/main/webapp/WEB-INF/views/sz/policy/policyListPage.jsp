<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
</script>
<table border="1" class="tablehead" >
	<tr align="center">
<!-- 		<th>航班索引</th> -->
		<th style="width: 80px">政策编号</th>
		<th style="width: 350px">政策名称</th>
		<th style="width: 80px">航司</th>
		<th style="width: 180px">创建时间</th>
		<th style="width: 80px">行程</th>
		<th style="width: 80px">返点</th>
		<th style="width: 80px">状态</th>
		<th style="width: 300px">操作</th>
	</tr>
<c:forEach items="${page.list}" var="po" varStatus="pList">
	<tr align="center">
<%-- 		<td>${pList.index+1}</td> --%>
		<td>${po.id}</td>
		<td style="text-align: left;">${po.policyName}</td>
		<td><span id="tktAirline">${fn:substring(po.tktAirline, po.tktAirline.length()-1,po.tktAirline.length())=='/'?fn:substring(po.tktAirline,0, po.tktAirline.length()-1):po.tktAirline}</span></td>
		<td>${po.workTime}</td>
		<td>
			<c:if test="${po.travelType==0}">单程</c:if>
			<c:if test="${po.travelType==1}">往返</c:if>
			<c:if test="${po.travelType==2}">单程/返程</c:if>
		</td>
		<td>${po.rebate}</td>
		<td>
			<c:if test="${po.policyStatus==0}">启用</c:if>
			<c:if test="${po.policyStatus==1}"><font color="red" style="background:pink">禁用</font></c:if>
			<c:if test="${po.policyStatus==2}"><font color="red" style="background:green">挂起</font></c:if>
		</td>
		<td>
			<table align="center">
				<tr >
					<td >
				    	<button onclick="deletePolicy('${po.id}')">停用</button>
				    	<button onclick="detalisPolicy('${po.id}')">详情</a></button>
				    	<button onclick="detalisPolicy('${po.id}')">修改</button>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
</c:forEach>
</table>
	<div class="paging">${page}</div>
<%-- <div class="pagination">${page}</div> --%>