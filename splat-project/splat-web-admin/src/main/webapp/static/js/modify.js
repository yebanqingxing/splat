$(function(){
	var reg = /^\d{6,16}$/;
	$(".modifybutton").click(function(){
		user($("#modifyused"));
		user($("#modifynew"));
		user($("#modifytwo"));
		if($("#modifynew").val() !== $("#modifytwo").val()){
			 $("#modifytwo").parent().siblings(".redreset").text("两次输入密码请保持一致！");
		}
	})
	function user(obj){
		var user = obj.val();
		if (user == '') {
			obj.parent().siblings(".redreset").text("密码不能为空！");
			return false;
		}else if(user.indexOf(" ") >=0){
			obj.parent().siblings(".redreset").text("您输入的含有空格！");
			return false;
		}else if(!reg.test(user)){
			obj.parent().siblings(".redreset").text("密码为6-16字符组成！");
			return false;
		}else{
			obj.parent().siblings(".redreset").text("");
		}
	}
})