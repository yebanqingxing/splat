var localObj = window.location;
var contextPathLoc = localObj.pathname.split("/")[0];

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

var server_context=basePath;
	function selectPNR(){
		$("#selectPRN").attr("disabled", "true");
		var PNRContent = $("#PNRContent").val();
		var comput=$("#comput").val();
		var data={
				'fileName':PNRContent,
				'comput':comput
		}
		$.ajax({
			type:'post',
			url:server_context+"/a/generalrules/generalRules/importPNR",
			data: data,
			success:function(dataJson){
				//var dataObject= eval("("+dataJson+")");
				var nr=document.getElementById("nr");
				var ticketPnr=document.getElementById("ticket");
				 for( var i=0;i<dataJson['rt'].zzRoutes.length;i++){
					 
					 var objdiv = document.createElement("div");
					
					$(objdiv).attr("id","div"+i);
					
					var ulq='<ul class="clear ultwo">';
					
					var liq='<li class="pnrone licenter">';
					
					var liq1='<li class="licenter">';
					liq1.id="li"+i;
					var liqp1='<p>';
					liqp1.id="li"+i;
					var liqh1='</p>';
					var flyCompany=dataJson['rt'].flyCompany[i];
					var flyNum=dataJson['rt'].flyNum[i];
					var toAirport=dataJson['rt'].zzRoutes[i].tocity;
					var fromTime=dataJson['rt'].zzRoutes[i].fromTime;
					console.log(fromTime);
					var toTime=dataJson['rt'].zzRoutes[i].toTime;
					var time =dataJson['rt'].zzRoutes[i].flyTime;
					var middleAirport =dataJson['rt'].zzRoutes[i].middleAirport;
					console.log(middleAirport+"middleAirport");
					var fromAirport=dataJson['rt'].zzRoutes[i].fromCity;
					var image='<img src="../images/pnrhang.png">';
					var lih='</li>';
					var ulh='</ul>';
					if(typeof(middleAirport)=="undefined"){
						$(objdiv).html(ulq+liq1+liqp1+flyNum+liqh1+liqp1+flyCompany+liqh1+lih+liq1+liqp1+fromTime+liqh1+liqp1+fromAirport+liqh1+lih+liq1+liqp1+liqh1+image+lih+liq1+liqp1+toTime+liqh1+liqp1+toAirport+liqh1+lih);
					}else{
						$(objdiv).html(ulq+liq1+liqp1+flyNum+liqh1+liqp1+flyCompany+liqh1+lih+liq1+liqp1+fromTime+liqh1+liqp1+fromAirport+liqh1+lih+liq1+liqp1+liqh1+image+liqp1+middleAirport+liqh1+lih+liq1+liqp1+toTime+liqh1+liqp1+toAirport+liqh1+lih);
					}
					$(objdiv).append(ulh);
					nr.appendChild(objdiv);
				} 
				 //返点rebate 代理费agencyFee
				 for( var j=0;j<dataJson['polic'].length;j++){
					 var ticketDiv = document.createElement("div");
					 	ticketDiv.class="lichcontent";
					 	//ticketDiv.id="div_ticket"+j;
						$(ticketDiv).attr("id","div_ticket"+j);
						$(ticketDiv).attr("class","lichcontent");
						var ulq_ticket='<ul class="clear">';
						var liq_ticket='<li>';
						var lih_ticket='</li>';
						var liq_ydyq= '<li class="liwid">';
						var span_ydyq='<span class="redreset">&lowast;</span>';
						var span='<span>预定要求</span>';
						var xiaosPrice='<li class="price">';
						//出票要求
						var outSpan='<span>出票要求</span>'
						//订单
						var ticketOutDiv='<div class="lichbottom clear">';
						var ticketOutDivfl='<div class="fl redreset liche">';
						var spanlow='<span>&lowast;</span>'
						var spanlast='<span>出票前请对PNR进行授权，否则后续无法给您出票</span>'
						var ticButto='<button class="thatok-button fr lichbuton" onclick="ticket()">生成订单</button>';
						var ulh_ticket='</ul>';
						var gys='供应商';
						var passengerType=dataJson['polic'][j].passagerPid;//旅客类型
						var passengerIdentity='旅客身份';var price='票面价:50000';
						var taxPrice='税费'; var agentFee=dataJson['polic'][j].agencyFee;//agencyFee代理费
						var rebate=dataJson['polic'][j].rebate;
						var type=dataJson['polic'][j].travelType;//行程类型
						var dropOrUpdateRule='退改规则';
						var payModel='支付宝';
						var htmlUL1="";
						htmlUL1=ulq_ticket+liq_ticket+gys+lih_ticket+
							     liq_ticket+passengerType+lih_ticket+
								 liq_ticket+passengerIdentity+lih_ticket+
								 xiaosPrice+price+lih_ticket+
								 liq_ticket+taxPrice+lih_ticket+
								 liq_ticket+agentFee+lih_ticket+
								 liq_ticket+rebate+lih_ticket+
								 liq_ticket+type+lih_ticket+
								 liq_ticket+dropOrUpdateRule+lih_ticket+
								 liq_ticket+payModel+lih_ticket+ulh_ticket+
								 ulq_ticket+liq_ydyq+span_ydyq+span+lih_ticket+xiaosPrice+"销售价:50000"+lih_ticket+ulh_ticket+//预定要求的ul
								 ulq_ticket+liq_ydyq+span_ydyq+outSpan+lih_ticket+xiaosPrice+"结算价:50000"+lih_ticket+ulh_ticket+//结算
								 ticketOutDiv+ticketOutDivfl+spanlow+spanlast+"</div>"+ticButto+"</div>"
								 ;
						$(ticketDiv).html(htmlUL1);
						ticketPnr.appendChild(ticketDiv);
						
				 }
			}
		})
	}
	
	function ticket(){
		alert("调用订单接口");
	}