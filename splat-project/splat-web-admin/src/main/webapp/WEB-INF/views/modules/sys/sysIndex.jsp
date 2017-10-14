<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/>
	<c:set var="tabmode" value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}">
    	<script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script>
    </c:if>
<!-- 	<style type="text/css">
		.head-nav li {margin: 0;}
		.orderlist {text-align:left;}
		.pnr-content-left {margin-top: 36px;}
		.pnr-content-left li {text-align: left;margin-left: 34%;}
		.orderlist p,.orderlist>a { text-align:left; margin-left:30%;}
		.orderlist ul { margin:0;}
	</style> -->
	<script type="text/javascript">
		function iFrameHeight() {   
			var ifm= document.getElementById("mainFrame");   
			var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
			   ifm.height = subWeb.body.scrollHeight;
			//    ifm.width = subWeb.body.scrollWidth;
			}   
		} 
		$(document).ready(function() {
			if (window != top){
				alert(213);	
				top.location.href = location.href;
			}
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': document.body.clientHeight-300},
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: 33
            });
			// 绑定菜单单击事件 头部导航菜单事件
			$("#menu a.menu").click(function(){
				if($(this).hasClass("active")){
					return;
				}
				// 一级菜单焦点
				$("#menu li.menu a").removeClass("active");
				$(this).addClass("active");
				// 左侧区域显示
				$("#left").show();
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				$("#left").empty();
				//登陆后的业务
				// 获取二级菜单数据
				$.get($(this).attr("data-href"), function(data){
					if (data.indexOf("id=\"loginForm\"") != -1){
						alert('未登录或登录超时。请重新登录，谢谢！');
						top.location = "${ctx}";
						return false;
					}
					$("#left ul").hide();
					$("#jerichotab .tab_pages").remove();
					$("#left").append(data);
					$(menuId + " a").bind("focus",function() {
						if(this.blur) {this.blur()};
					});
					$(menuId+" ul").css("display","none");
					// 二级标题
					/* $(".pnr-content-left ul li").click(function(){
						$(this).find('a').addClass("clickulli").parent().siblings().find("a").removeClass("clickulli");
					})   */
					$(".nav_addclick").click(function(){
						var imgsrc = $(this).find("img").attr("src");
						var img = imgsrc.indexOf("nav_add");
						var imgqian = imgsrc.substring(0,img);
						if($(this).hasClass("addnavback")){
							$(this).removeClass("addnavback");
							$(this).siblings("ul").slideUp();
							$(this).find("img").attr("src",imgqian + "nav_add.png");
						}else{
							$(this).addClass("addnavback");
							$(this).siblings("ul").slideDown();
							$(this).find("img").attr("src",imgqian + "nav_addcli.png");
						}
					})
					/* $(".pnr-content-left p").click(function(){
						alert("aaa");
						$(this).siblings("ul").slideToggle();
					}) */
					$(menuId + " a").click(function(event){
						var parentNode = $(this).parent();
						if(parentNode.hasClass("clickulli")){
                                return;
						}
						$(menuId+" a").removeClass("clickulli");
						$(this).addClass("clickulli");
						$(this).next().css("display","block");
						$(this).parent().css("display","block");
						addTab($(this));
					 	return false;
					});
					
					// 展现三级
					var threeLevelFirstMenu="",
					  	threeLevelMenus = $(menuId+" a[target='mainFrame']");
						
						// 默认选中第一个菜单
						if(threeLevelFirstMenu = threeLevelMenus[0]){
							$(threeLevelFirstMenu).parent().parent().siblings("p").click();
							addTab($(threeLevelFirstMenu));
						}
				});
				/* } */
				// 大小宽度调整
				return false;
			});
			$("#jerichotab").hide();
			$("#mainFrame").hide();
			$("#menu a.menu:eq(0)").removeClass("active").click();
		});
		function addTab($this, refresh){
			$(".jericho_tab").show();
			$("#mainFrame").hide();
			if($this.attr("href").indexOf("#collapse")> -1){
				return;
			} 
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: false,
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			
			
			
			/* var d = parent.document.getElementsByClassName("curholder"); */
			var d = getClassNames("curholder","div");
			var dId = $(d).attr("id").split("_")[1];
			var iframId = "jerichotabiframe_"+dId;//获取动态ID
			return false;
		} 
		
		function getClassNames(classStr,tagName){ 
			if (document.getElementsByClassName) { 
				return document.getElementsByClassName(classStr) 
			}else { 
				var nodes = document.getElementsByTagName(tagName),ret = []; 
				for(i = 0; i < nodes.length; i++) { 
					if(hasClass(nodes[i],classStr)){ 
					ret.push(nodes[i]) 
				} 
			} 
			return ret; 
			} 
		} 
		function hasClass(tagStr,classStr){ 
		var arr=tagStr.className.split(/\s+/ ); //这个正则表达式是因为class可以有多个,判断是否包含 
			for (var i=0;i<arr.length;i++){ 
				if (arr[i]==classStr){ 
					return true ; 
					} 
			} 
			return false ; 
		} 
		$(function(){
			setInterval(function(){
              	var d = getClassNames("curholder","div");
      			var dId = $(d).attr("id").split("_")[1];
      			var iframId = "jerichotabiframe_"+dId;//获取动态ID
      			var ifram = document.getElementById(iframId);
      			var form = ifram.contentWindow.document;
      			h = $(form).height();
      			$("#jerichotab_contentholder").css({"height":h,"overflowY":"hidden"});
      			$(ifram).css({"height":h,"overflowY":"hidden"}); 
               },10);
		})
	</script>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
<!-- BEGIN HEADER -->
<%@include file="/WEB-INF/views/include/header.jsp" %>
<!-- END HEADER -->
<div class="bottom-back1"></div>
<div class="bottom-back2"></div>
<div class="pnr-content">
	<div class="pnr-content-left" id = "left">
	</div>
	<div class="pnr-content-right"  id="right" style="width:100%;">
		<!-- <iframe id="mainFrame" name="mainFrame" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"  width="100%" height="100%"  src="" onLoad="iFrameHeight()"></iframe> -->
	</div>
</div>
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
	$(function(){
		var h = $(window).height();
		var w = $(document).width();
		var $wid = $(window).width() - 205;
		var headhei = $(".head-mian").height() + 7 + 60;
		var righthei = h - headhei;
		$(".pnr-content-right").width($wid);
		$('.pnr-content-right').css({ width: w - 260, 'display': 'block', 'marginLeft': 7,'minHeight':righthei });
		
		setInterval(function(){
			var conhei = $(".pnr-content-left").height();
			if(conhei > righthei){
				$('.pnr-content-right').css({'minHeight':conhei });
			}
		},10)
		$(".pnr-content-right").width($wid);
	})
</script>
</body>
<!-- END BODY -->
</html>