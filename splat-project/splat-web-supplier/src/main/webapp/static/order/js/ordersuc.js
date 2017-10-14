
var indexOne=2;
$(function(){
	$(".pnrclick ").toggle(function(){
		$(this).parent().siblings(".showhide").slideUp();
		var objsrc = $(this).find("img").attr("src");
		var n = objsrc.indexOf("click");
		var starsrc = objsrc.substring(0,n);
		$(this).find("img").attr("src",starsrc + "clickpnrup.png");
		$(this).parent().siblings(".colorhide").show();
	},function(){
		$(this).parent().siblings(".showhide").slideDown();
		var objsrc = $(this).find("img").attr("src");
		var n = objsrc.indexOf("click");
		var starsrc = objsrc.substring(0,n);
		$(this).find("img").attr("src",starsrc + "clickpnr.png");
		$(this).parent().siblings(".colorhide").hide();
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

	$(".clickshow-pnr").click(function(){
		var thisindex = $(this).index();
		$(".airshow-pnr").eq(thisindex-1).show().siblings(".airshow-pnr").hide();
	})
	indexOne = indexOne+1;
	var appendpnr = "<ul class='clear'>"+
						"<li><input name='recipient["+indexOne+"].recipientName'  type='text' class='widaddpnr'></li>"+
						"<li><input name='recipient["+indexOne+"].phone'  type='text' class='widaddpnr'></li>"+
						"<li><input name='recipient["+indexOne+"].mailNumber'  type='text' class='widaddpnr'></li>"+
						"<li><input name='recipient["+indexOne+"].recipientMoney' value='20' type='hidden' class='widaddpnr'>20</li>"+
						"<li class='widtable'><input name='recipient["+indexOne+"].address' type='text' class='widaddpnr'></li>"+
					"</ul>";
	$(".addpnrshow").bind("click",function(){
		$(".showappend-pnr").append(appendpnr);
	})
	$(".rempnrshow").bind("click",function(){
		var leng = $(".showappend-pnr ul").length;
		$(".showappend-pnr").find("ul").eq(leng-1).remove();
	})
})