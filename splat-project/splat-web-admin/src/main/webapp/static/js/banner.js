window.onload=function(){
  	var win = $('.home-banner').width();
	var pagenavi = $('#pagenavi').width();
	var winleft = (win-pagenavi)/2;
	$('#pagenavi').css("left",winleft);
	var banhei = $("#banner li img").height();
	$(".home").height(banhei);

	// 登录框垂直居中
	var signhei = $(".sign").height();
	var sign = (banhei-signhei)/2;
	$(".sign").css("top",sign);

	// 首页登录验证
	$(".code-button").click(function(){
		user();
		password();
		if(!code()){
			return false;
		}
	})

	// 用户名验证
	$("#username").blur(function(){
		user();
	})
	$("#password").blur(function(){
		password();
	})
	function user(){
		var userval = $("#username").val();
		var obj = $("#username").parent();
		if (/[\u4E00-\u9FA5]/i.test(userval)) {
		    apendtext(obj,'用户名错误');
		    return;
		} else if(userval == '' || userval == 'undefined'){
			apendtext(obj,'用户名不能为空');
			return;
		}else{
			obj.find("p").remove();
		}
	}
	//密码
	function password(){
		var userval = $("#password").val();
		var obj = $("#password").parent();
		if (userval == '' || userval == 'undefined'){
			apendtext(obj,'密码不能为空');
			return false;
		}else{
			obj.find("p").remove();
		}
	}
	// 验证码
	function code(){
		var userval = $("#code").val();
		var obj = $("#code").parent();
		if (userval == '' || userval == 'undefined'){
			apendtext(obj,'验证码不能为空');
			return false;
		}else if($("#checkCode").val()=="false"){
			apendtext(obj,'验证码请输入正确验证码');
			return false;
		}else{
			obj.find("p").remove();
			return true;
		}
		
	}
	function apendtext(obj,usertext) {
		obj.find("p").remove();
		obj.append("<p style='color:#f32c2c; text-indent:1em; margin-top:2px; float:left;'>"+ usertext +"</p>");
	}
}
