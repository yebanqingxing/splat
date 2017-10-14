$(function(){
	$(".pnrclick ").click(function(){
		$(".showhide").slideToggle();
	})
	var htmlcon = "<div class='hidetop'>"+
					"<ul class='clear'>"+
						"<li>喵星人</li>"+
						"<li>13953668199</li>"+
						"<li>1008611</li>"+
						"<li>2000</li>"+
						"<li class='widtable'>北京市昌平区回龙观北店嘉园13号楼三单元602</li>"+
					"</ul>"+
				"</div>";
	$(".addaircon").bind("click",function(){
		$(".aircontop").append(htmlcon);
	})
	$(".romaircon").bind("click",function(){
		var leng = $(".hidetop").length;
		$(".aircontop").find(".hidetop").eq(leng-1).remove();
	})
})