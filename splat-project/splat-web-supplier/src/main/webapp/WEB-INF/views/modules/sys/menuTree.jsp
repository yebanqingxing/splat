<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="order addmargintop" id="menu-${param.parentId}"  data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">			
	<c:set var="menuList" value="${fns:getMenuList()}"/>
	<c:set var="firstMenu" value="true"/>
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
		<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
			<div class="nav_add">
				<c:set var="flag" value="0"></c:set>
				<c:forEach items = "${menuList }" var = "menu2">
					<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
						<c:set var = "flag" value = "1"></c:set>
					</c:if>
				</c:forEach>
					<c:if test="${flag != 1 }">
						 <p class="nav_addclick"><img src="${ctxStatic}/images/nav_add.png">${menu.name }</p>
						<ul class="addnone">
							<li>
								<a  class="${not empty firstMenu && firstMenu ? 'clickulli' : ''}" data-href=".menu-${menu.id}" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${not empty menu.href ? menu.href : '/404'}" target="${not empty menu.target ? menu.target : 'mainFrame'}" >
						 	&bull;&nbsp;${menu.name}
						 </a>
							</li>
						</ul>	
						 <c:set var="firstMenu" value="false"/>
					</c:if>
					<c:if test="${flag == 1 }">
						<p class="nav_addclick"><img src="${ctxStatic}/images/nav_add.png">${menu.name }</p>
					</c:if>
					<ul class="addnone">
				     <c:forEach items="${menuList}" var="menu3">
				        <c:if test="${menu3.parent.id eq menu.id&&menu3.isShow eq '1'}">
			        		<%--用作判断，来确定是否有下级菜单selectMenu --%>
							<c:set var="flag" value="0"></c:set>
							<c:forEach items = "${menuList }" var = "menu4">
								<c:if test="${menu4.parent.id eq menu3.id&&menu4.isShow eq '1'}">
									<c:set var = "flag" value = "1"></c:set>
								</c:if>
							</c:forEach>
				        	<li>
						        <c:if test="${flag != 1 }">
						        	<a  class="${not empty firstMenu && firstMenu ? 'clickulli' : ''}" data-toggle="collapse" data-parent="#menu-${menu3.id}"  href="${not empty menu3.href? ctx : ''}${not empty menu3.href ? menu3.href : 'a/404'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" title="${menu3.remarks}" >
										&bull;&nbsp;${menu3.name}
									</a>
									<c:set var="firstMenu" value="false"/>
						        </c:if>
						        <c:if test="${flag == 1 }">
					        		<p class="nav_addclick">&nbsp;<img src="${ctxStatic}/images/nav_addcli.png">${menu3.name}</p>
						        </c:if>
								<c:forEach items="${menuList}" var="menu5">
									<c:if test="${menu4.parent.id eq menu5.id&&menu5.isShow eq '1'}">
										<a data-toggle="collapse" data-parent="#menu-${menu5.id}" data-href="#collapse-${menu5.id}" href="${fn:indexOf(menu5.href, '://') eq -1 ? ctx : ''}${not empty menu5.href ? menu5.href : ''}" target="${not empty menu5.target ? menu5.target : 'mainFrame'}" title="${menu5.remarks}" >
										&nbsp;&nbsp;${menu5.name}
										</a>
									</c:if>
								</c:forEach>
							  	<c:set var="firstMenu" value="false"/>
					  		</li>
					   	</c:if>
					</c:forEach>
			    </ul>
			    <div class="addnavbor"></div>
			</div>
		</c:if>
	</c:forEach>
</div>
			
		