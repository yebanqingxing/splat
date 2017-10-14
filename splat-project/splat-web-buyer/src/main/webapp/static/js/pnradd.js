$(function(){
	var addhtml = "<div class='select1 clear'>" + 
						"<select class='select-con'>"+
							"<option value='学生'>学生</option>"+
							"<option value='军人'>军人</option>"+
						"</select>"+
						"<input name='stedent' type='text' value='' class='adultinput fl'>"+
					"</div>"
	$("#addbut").click(function(){
		$("#addhtml").append(addhtml);
	})
	$("#removebut").bind('click',function(){
		$(".select1:last").remove();
	})
	$(".thatok-infor").click(function(){
		$(".thatsoknone").slideToggle();
	})
	$(".pnrclickhide").toggle(function(){
		$(this).siblings(".hideaddpnr").slideDown();
		$(this).html("收起信息&and;");
	},function(){
		$(this).siblings(".hideaddpnr").slideUp();
		$(this).html("更多乘客类型&or;");
	})
})