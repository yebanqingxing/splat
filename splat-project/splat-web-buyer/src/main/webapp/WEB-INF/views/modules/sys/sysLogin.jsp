<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv= "X-UA-Compatible" content = "IE=edge,chrome=1"/>
<title>${fns:getConfig('productName')} 登录</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/sign.css">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/touchslider.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/banner.js"></script>
<script>
$(document).ready(function() {
	if (window != top){
		top.location.href = location.href;
	}
})
	//验证码换图片
function changeImg() {
    var imgSrc = $("#codeImg");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
} 
//加入时间戳，去缓存机制   
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();if ((url.indexOf("&") >= 0)) {
        url = url + "&timestamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}
function check(){
	var validateCode = $("#code").val();
	$.ajax({
		url:'${ctx}/checkCode?validateCode='+validateCode,
		type:'post',
		success:function(data){
			if (data == "true") {
				$("#checkCode").val("true");
			}else{
				$("#checkCode").val("false");
			}
		}
	})
}
</script>
</head>
<body>
	<!-- head -->
	<div class="head">
		<!-- logo -->
		<div class="head_logo fl">
			<img src="${ctxStatic}/images/logo.png">
		</div>
		<!-- nav -->
		<div class="head_nav fr">
			<ul>
				<li><a href="javascript:void(0);" class="navclick">基础功能</a></li>
				<li><a href="javascript:void(0);">关于我们</a></li>
				<li><a href="javascript:void(0);">战略伙伴</a></li>
				<li><a href="javascript:void(0);">客服热线</a></li>
			</ul>
		</div>
	</div>
	<div class="clear division1"></div>
	<div class="division2"></div>
	<!-- banner和登录 -->
	<div class="home">
		<div class="home-banner clear">
		  <ul id="banner">
		    <li><a href="javascript:void(0);"><img src="${ctxStatic}/images/banner.jpg"></a></li>
		    <li><a href="javascript:void(0);"><img src="${ctxStatic}/images/banner.jpg"></a></li>
		  </ul>
		  <div class="back-bottom">
			  <div id="pagenavi">
			    <a href="#" class="active"></a>
			    <a href="#" class=""></a>
			  </div>
		  </div>
		</div>
		<!-- 登录框 -->
		<div class="alert alert-danger display-hide" ${empty message ? 'style="display:none;"' : 'style="display:block;"'}>
			<button class="close" data-close="alert"></button>
			<span>
				${empty message ? '用户名或密码不能为空.' : message}
			</span>
		</div>
		<form id="loginForm" class="login-form" action="${ctx}/login" method="post">
		<input type="hidden" id = "checkCode"/>
		<div class="sign">
			<div class="sign-main clear">
				<div class="sign-head">
					<h3>用户登录</h3>
					<a href="javascript:void(0);">立即注册》</a>
				</div>
				<div class="form-user">
					<input type="text" value="buyer" name="username" id="username" class="sign-input">
					<img src="${ctxStatic}/images/account.png">
				</div>
				<div class="form-user">
					<input type="password" value="admin"  name = "password" id="password" class="sign-input">
					<img src="${ctxStatic}/images/password.png">
				</div>
				<div class="form-code">
					<input type="text" value="" name="code" id="code" class="sign-code" onblur="check()" maxlength="4">
					<img src="${ctx}/code" alt="验证码" id="codeImg" onclick="changeImg();">
				</div>
				<button class="code-button" type = "submit">登录</button>
				<div class="form-mu">
					<div class="form-pass">
						<input name="rememberme" type="checkbox" value="" /><span>记住账号 </span>
					</div>
					<a href="javascript:void(0);" class="back">找回密码？</a>
				</div>
			</div>
		</div>
		</form>
	</div>
	<!-- 我们的优势 -->
	<div class="advantage clear">
		<h2>我们的优势</h2>
		<div class="advantage-content">
			<div class="advantage1">
				<img src="${ctxStatic}/images/distribution.png">
				<span>强大分销平台</span>
				<p>提供快捷的机票预订</p>
			</div>
			<div class="advantage1">
				<img src="${ctxStatic}/images/excellent.png">
				<span>优质一站式服务</span>
				<p>以客户需求为向导快速解决订票问题</p>
			</div>
			<div class="advantage1">
				<img src="${ctxStatic}/images/mobile.png">
				<span>客户服务热线</span>
				<p>010 - 12345678</p>
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<div class="fotter clear">
		<span><a href="javascript:void(0);">关于我们</a> | <a href="javascript:void(0);">商务合作</a> | <a href="javascript:void(0);">媒体报道</a> | <a href="javascript:void(0);">友情链接</a> | <a href="javascript:void(0);">联系我们</a> | <a href="javascript:void(0);">责任声明</a><br />爱游飞公司 ©2016 ****.com All Rights Reserved</span>
	</div>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctxStatic}/bootstrap/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctxStatic}/bootstrap/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/assets/admin/pages/scripts/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
	console=window.console || {dir:new Function(),log:new Function()};
	var active=0,
    as=document.getElementById('pagenavi').getElementsByTagName('a');
	for(var i=0;i<as.length;i++){
	  (function(){
	    var j=i;
	    as[i].onclick=function(){
	      t4.slide(j);
	      return false;
	    }
	  })();
	}
	var t4=new TouchSlider('banner',{speed:1000, direction:0, interval:6000, fullsize:true});
	t4.on('before',function(m,n){
	    as[m].className='';
	    as[n].className='active';
	})
	jQuery(document).ready(function() {     
		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
		Login.init();
		Demo.init();
	});
</script>
</body>
</html>