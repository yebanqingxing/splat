$(function(){
	$("#checkli").click( 
	  function(){ 
	    if(this.checked){ 
	        $("input[name='checkli']").attr('checked', true);
	    }else{ 
	        $("input[name='checkli']").attr('checked', false);
	    } 
	  } 
	)
	$(".checkradio").click(function(){
		var thisurl = $(this).find("img").attr("src");
		if(thisurl == '../images/radio1.png'){
			$(this).find("img").attr("src","${ctxStatic}/images/radio1.png");
		}else{
			$(this).find("img").attr("src","${ctxStatic}/images/radio1.png");
			$(this).siblings().find("img").attr("src","../images/radio2.png");
		}
	})
	$(".interno").click(function(){
		var thisurl = $(this).find("img").attr("src");
		if(thisurl == '../images/interno.png'){
			$(this).find("img").attr("src","${ctxStatic}/images/interyes.png");
		}else{
			$(this).find("img").attr("src","${ctxStatic}/images/interno.png");
		}
	})
})