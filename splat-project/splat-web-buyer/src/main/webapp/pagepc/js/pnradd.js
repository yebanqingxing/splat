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
	function selectPNR(){
		$("#selectPRN").attr("disabled", "true");
		var PNRContent = $("#PNRContent").val();
		$.ajax({
			url:contextPath+"/a/generalrules/generalRules/importPNR?fileName="+PNRContent,
			success:function(dataJson){
				//var dataObject= eval("("+dataJson+")");
				var nr=document.getElementById("nr");
				 for( var i=0;i<dataJson.zzRoutes.length;i++){
					 
					 var objdiv = document.createElement("div");
					
					$(objdiv).attr("id","div"+i);
					
					var ulq='<ul class="clear ultwo">';
					
					var liq='<li class="pnrone licenter">';
					
					var liq1='<li class="licenter">';
					var liqp1='<p>';
					var liqh1='</p>';
					var flyCompany=dataJson.flyCompany[i];
					var flyNum=dataJson.flyNum[i];
					var toAirport=dataJson.zzRoutes[i].tocity;
					var fromTime=dataJson.zzRoutes[i].fromTime;
					console.log(fromTime);
					var toTime=dataJson.zzRoutes[i].toTime;
					var time =dataJson.zzRoutes[i].flyTime;
					var middleAirport =dataJson.zzRoutes[i].middleAirport;
					console.log(middleAirport+"middleAirport");
					var fromAirport=dataJson.zzRoutes[i].fromCity;
					var image='<img src="../images/pnrhang.png">';
					var lih='</li>';
					var ulh='</ul>';
					if(typeof(middleAirport)=="undefined"){
						$(objdiv).html(ulq+liq1+liqp1+flyCompany+liqh1+liqp1+flyNum+liqh1+liqh1+lih+liq1+liqp1+fromAirport+liqh1+liqp1+fromTime+liqh1+lih+liq1+liqp1+time+liqh1+image+lih+liq1+liqp1+toAirport+liqh1+liqp1+toTime+liqh1+lih);
					}else{
						$(objdiv).html(ulq+liq1+liqp1+flyCompany+liqh1+liqp1+flyNum+liqh1+liqh1+lih+liq1+liqp1+fromAirport+liqh1+liqp1+fromTime+liqh1+lih+liq1+liqp1+time+liqh1+image+liqp1+middleAirport+liqh1+lih+liq1+liqp1+toAirport+liqh1+liqp1+toTime+liqh1+lih);
					}
					$(objdiv).append(ulh);
					nr.appendChild(objdiv);
				} 
			}
		})
}
})