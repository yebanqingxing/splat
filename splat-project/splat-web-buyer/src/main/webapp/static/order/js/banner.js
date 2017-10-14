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
		code();
	})

	// 用户名验证
	$("#user").blur(function(){
		user();
	})
	$("#mobile").blur(function(){
		password();
	})
	function user(){
		var userval = $("#user").val();
		var obj = $("#user").parent();
		if (/[\u4E00-\u9FA5]/i.test(userval)) {
		    apendtext(obj,'用户名错误');
		    return;
		} else if(userval == ''){
			apendtext(obj,'用户名不能为空');
			return;
		}else{
			obj.find("p").remove();
		}
	}
	//密码
	function password(){
		var userval = $("#mobile").val();
		var obj = $("#mobile").parent();
		if (userval == ''){
			apendtext(obj,'密码不能为空');
			return;
		}else{
			obj.find("p").remove();
		}
	}
	// 验证码
	function code(){
		var userval = $("#code").val();
		var obj = $("#code").parent();
		if (userval == ''){
			apendtext(obj,'验证码不能为空');
			return;
		}
	}
	function apendtext(obj,usertext) {
		obj.find("p").remove();
		obj.append("<p style='color:#f32c2c; text-indent:1em; margin-top:2px; float:left;'>"+ usertext +"</p>");
	}
}