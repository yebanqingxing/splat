<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<ul id="menu-${param.parentId}" class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">			
	<li class="sidebar-toggler-wrapper" style="margin-bottom: 15px;">	
		<div class="sidebar-toggler">
		</div>
	</li>
	<c:set var="menuList" value="${fns:getMenuList()}"/>
	<c:set var="firstMenu" value="true"/>
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
		<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
            <li class="${not empty firstMenu && firstMenu ? 'active open' : ''}">
				<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${param.parentId}" data-href="#collapse-${menu.id}" href="#collapse-${menu.id}" title="${menu.remarks}">
					<i class="${menu.icon }"></i>
					<span class="title">&nbsp;${menu.name}</span>
					<span class="selected"></span>
					<span class="arrow ${not empty firstMenu && firstMenu ? 'open' : ''}"></span>
				</a>
				
				<ul class="sub-menu">
				     <c:forEach items="${menuList}" var="menu2">
				        <c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
				        	<%--用作判断，来确定是否有下级菜单sub-menu,是否有上下箭头arrow --%>
							<c:set var="flag" value="0"></c:set>
							<c:forEach items="${menuList}" var="menu3">
								<c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
									<c:set var="flag" value="1"></c:set>
								</c:if>
							</c:forEach>
				        
							<li>
								<c:if test="${flag == 1}">
									<a data-href=".menu3-${menu2.id}">
								</c:if>
								<c:if test="${flag != 1}">
									<a data-href=".menu3-${menu2.id}" href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}" target="${not empty menu2.target ? menu2.target : 'mainFrame'}" >
								</c:if>
										<i class="${not empty menu2.icon ? menu2.icon : 'circle-arrow-right'}"></i>
										&nbsp;${menu2.name}
										<c:if test="${flag == 1}">
											<span class="arrow"></span>
										</c:if>
									</a>
								
								
								<c:if test="${flag == 1}">
									<ul class="sub-menu">
									     <c:forEach items="${menuList}" var="menu3">
									        <c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
												<li>
													<a data-href=".menu4-${menu3.id}" href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" >
														<i class="${not empty menu3.icon ? menu3.icon : 'circle-arrow-right'}"></i>
														&nbsp;${menu3.name}
													</a>
											  	</li>
										   </c:if>
										</c:forEach>
								    </ul>
								</c:if>
						  	</li>
						  	<c:set var="firstMenu" value="false"/>
					   </c:if>
					</c:forEach>
			    </ul>
			</li>
		</c:if>
	</c:forEach>
</ul>
			
		