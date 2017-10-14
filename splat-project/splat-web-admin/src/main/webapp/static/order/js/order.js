 $(function(){
	$(".pnr-content-left ul li").click(function(){
		$(this).find('a').addClass("clickulli").parent().siblings().find("a").removeClass("clickulli");
	})
	$(".orderlist p").click(function(){
		$(this).siblings("ul").slideToggle();
	})
 })