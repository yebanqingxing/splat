<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- BEGIN HEADER -->
<div class="head-mian">
	<img src="${ctxStatic }/images/head_back.jpg">
	<div class="head-content">
		<div class="logo">
			<img src="${ctxStatic }/images/head_logo.png">
		</div>
	</div>
	<div class="head-connav clear">
		<ul class="signout" style="height:32px;line-height:32px; margin-bottom:0;">
			<li class="signyou">
				<a href="${ctx}/logout">
				<i class="icon-key" style="margin-top:4px;"></i> 安全退出 </a>
			</li>
		</ul>
		<ul id="menu" class="head-nav fl" style="margin-left: 202px;">
			<c:forEach items="${fns:getMenuList()}" var="menu" >
				<c:if test="${menu.parent.id eq '-2'&&menu.isShow eq '1'}">
					<li class="menu">
						<c:if test="${empty menu.href}">
							<a class="menu ${not empty firstMenu && firstMenu ? 'active' : ''}"  href="javascript:void(0);" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
								${menu.name}
							</a>
						</c:if>
						<%-- <c:if test="${not empty menu.href}">
							<a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame">
								${menu.name}
							</a>
						</c:if> --%>
					</li>
					<c:if test="${firstMenu}">
						<c:set var="firstMenuId" value="${menu.id}"/>
					</c:if>
					<c:set var="firstMenu" value="false"/>
				</c:if>
			</c:forEach>
			<%-- <li style="color:#">
				<a href="${ctx}/logout">
				<i class="icon-key"></i> 安全退出 </a>
			</li> --%>
		</ul>
	</div>
</div>
<!-- END HEADER -->