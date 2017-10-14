<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>${fns:getConfig('productName')} 登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${ctxStatic}/bootstrap/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${ctxStatic}/bootstrap/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctxStatic}/bootstrap/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<script>

</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="javascript:void(0);" style="text-decoration:none;color:red;font-size:35px;">
		${fns:getConfig('logoName')}
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form id="loginForm" class="login-form" action="${ctx}/login" method="post">
		<h3 class="form-title">${fns:getConfig('productName')}</h3><br>
		<div class="alert alert-danger display-hide" ${empty message ? 'style="display:none;"' : 'style="display:block;"'}>
			<button class="close" data-close="alert"></button>
			<span>
				${empty message ? '用户名或密码不能为空.' : message}
			</span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" id="username" name="username"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" id="password" name="password"/>
		</div>
		
		<div class="form-actions">
			<button type="submit" class="btn btn-success uppercase">登录</button>
			<label class="rememberme check">
			<input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/>记住密码 </label>
			<a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
		</div>
		<div class="login-options">
			<h4>其他方式登录</h4>
			<ul class="social-icons">
				<li>
					<a class="social-icon-color facebook" data-original-title="facebook" href="javascript:;"></a>
				</li>
				<li>
					<a class="social-icon-color twitter" data-original-title="Twitter" href="javascript:;"></a>
				</li>
				<li>
					<a class="social-icon-color googleplus" data-original-title="Goole Plus" href="javascript:;"></a>
				</li>
				<li>
					<a class="social-icon-color linkedin" data-original-title="Linkedin" href="javascript:;"></a>
				</li>
			</ul>
		</div>
		<div class="create-account">
			<p>
				<a href="javascript:;" id="register-btn" class="uppercase">会员注册</a>
			</p>
		</div>
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form class="forget-form" action="index.html" method="post">
		<h3>忘记密码 ?</h3>
		<p>
			 请输入您在splat注册时候使用的邮箱地址找回密码
		</p>
		<div class="form-group">
			<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email"/>
		</div>
		<div class="form-actions">
			<button type="button" id="back-btn" class="btn btn-default">返回</button>
			<button type="submit" class="btn btn-success uppercase pull-right">提交</button>
		</div>
	</form>
	<!-- END FORGOT PASSWORD FORM -->
</div>
<div class="copyright">
	 2016 &copy; splat by splat <a href="http://www.splat.com" title="http://www.splat.com" target="_blank">splat!</a>
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/respond.min.js"></script>
<script src="${ctxStatic}/bootstrap/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
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
<script>
jQuery(document).ready(function() {     
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
Login.init();
Demo.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>