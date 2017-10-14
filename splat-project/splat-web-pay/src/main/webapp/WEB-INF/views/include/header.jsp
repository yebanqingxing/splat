<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="${ctx}" style="text-decoration:none;font-color:red;font-size:35px;">
				${fns:getConfig('logoName')}
			</a>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				
				<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-grid"></i>
					<span class="badge badge-default">
					菜</span>
					</a>
					<ul class="dropdown-menu">
						<li class="external">
							<h3><span class="bold">系统一级菜单</span></h3>
						</li>
						<li>
							<ul id="menu" class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
								<c:set var="firstMenu" value="true"/>
								<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
									<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
										<li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
											<c:if test="${empty menu.href}">
												<a class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
													<span class="details">
														<span class="label label-sm label-icon label-success">
															<i class="fa fa-plus"></i>
														</span>
														${menu.name}
													</span>
												</a>
											</c:if>
											<c:if test="${not empty menu.href}">
												<a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame">
													<span class="details">
														<span class="label label-sm label-icon label-success">
															<i class="fa fa-plus"></i>
														</span>
														${menu.name}
													</span>
												</a>
											</c:if>
										</li>
										<c:if test="${firstMenu}">
											<c:set var="firstMenuId" value="${menu.id}"/>
										</c:if>
										<c:set var="firstMenu" value="false"/>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</li>
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-envelope-open"></i>
					<span class="badge badge-default">
					1 </span>
					</a>
					<ul class="dropdown-menu">
						<li class="external">
							<h3>你有 <span class="bold">1 条</span> 新消息</h3>
							<a href="page_inbox.html">消息列表</a>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
								<li>
									<a href="inbox.html?a=view">
									<span class="subject">
										<span class="from">预留的服务接口 </span>
										<span class="time">预留的服务接口 </span>
									</span>
									<span class="message">预留的服务接口 </span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN TODO DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="icon-calendar"></i>
					<span class="badge badge-default">
					3 </span>
					</a>
					<ul class="dropdown-menu extended tasks">
						<li class="external">
							<h3>你有 <span class="bold">3条</span> 待处理的任务</h3>
							<a href="page_todo.html">任务列表</a>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
								<li>
									<a href="javascript:;">
									<span class="task">
									<span class="desc">预留的服务接口 </span>
									<span class="percent">30%</span>
									</span>
									<span class="progress">
									<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">40% Complete</span></span>
									</span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<!-- END TODO DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-user">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="${fns:getUser().photo}"/>
					<span class="username username-hide-on-mobile">${fns:getUser().name} </span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul id="menu" class="dropdown-menu dropdown-menu-default">
						<li class="menu">
							<a class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=27" data-id="27">
							<i class="icon-user"></i> 个人设置 </a>
						</li>
						<li>
							<a href="${ctx}/logout">
							<i class="icon-key"></i> 安全退出 </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
				<!-- BEGIN QUICK SIDEBAR TOGGLER -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<!-- END QUICK SIDEBAR TOGGLER -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->