<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/>
	<c:set var="tabmode" value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}">
    	<link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    	<script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script>
    </c:if>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body").css("background-color","#364150");
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': document.body.clientHeight-180},
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: 33
            });
			// 绑定菜单单击事件 头部导航菜单事件
			$("#menu a.menu").click(function(){
				$("#tiles").hide();
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					$(".jericho_tab").hide();
					$("#mainFrame").show();
					return true;
				}
				// 左侧区域显示
				$("#left").show();
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left ul").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if ($(menuId + " .sub-menu:first").css('display') != 'block' ){
						$(menuId + " .sub-menu:first").parent().children('a').click();
					}
					$(menuId + " .sub-menu:first a:first i").click();
				}else{
					//登陆后的业务
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "${ctx}";
							return false;
						}
						$("#left ul").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " a.accordion-toggle").click(function(event){
							var parentNode = $(this).parent();
							if(parentNode.hasClass("active")){
	                                return;
							}
							$(menuId+">li").removeClass("active");
							$(menuId+">li .arrow").removeClass("open");
							$(menuId+">li ul").css("display","none");
							parentNode.addClass("active");
							$(".arrow",this).addClass("open");
							$(this).next().css("display","block");
							addTab($("ul li:first-child a",parentNode));
						 	return false;
						});
						
						// 展现三级
						var threeLevelFirstMenu="",
						  	threeLevelMenus = $(menuId+" a[target='mainFrame']");
						threeLevelMenus.click(function(){
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							return addTab($(this));
						});
						
						// 默认选中第一个菜单
						if(threeLevelFirstMenu = threeLevelMenus[0]){
							debugger
							addTab($(threeLevelFirstMenu));
						}
					});
				}
				// 大小宽度调整
				return false;
			});
			
			$("#jerichotab").hide();
			$("#mainFrame").hide();
		});
		function addTab($this, refresh){
			$(".jericho_tab").show();
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false;
		}
	</script>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body style="background-color: #364150;" class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid">
<!-- BEGIN HEADER -->
<%@include file="/WEB-INF/views/include/header.jsp" %>
<!-- END HEADER -->

<div class="clearfix">
</div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<div id="left" style="width:100%;"></div>
		</div>
	</div>
	<!-- END SIDEBAR -->
	
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content" style="padding:5px;">
		  <div id="right" style="width:100%;">
			<%@include file="/WEB-INF/views/include/home.jsp" %>
		     <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
		  </div>
	    </div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->
<%@include file="/WEB-INF/views/include/footer.jsp" %>
<!-- END FOOTER -->

<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features

	if(!window.isInited){
		window.winClientHeight = document.body.clientHeight;
		window.isInited = true;
	}

});
</script>

<script type="text/javascript"> 
	var leftWidth = 160; // 左侧窗口大小
	var tabTitleHeight = 33; // 页签的高度
	var htmlObj = $("html"), mainObj = $("#main");
	var headerObj = $("#header"), footerObj = $("#footer");
	var frameObj = $("#left, #right, #right iframe");
</script>
</body>
<!-- END BODY -->
</html>