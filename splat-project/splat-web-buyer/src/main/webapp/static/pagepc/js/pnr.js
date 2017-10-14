var localObj = window.location;
var contextPathLoc = localObj.pathname.split("/")[0];

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

var server_context=basePath;
	function selectPNR(){
		//$("#selectPRN").attr("disabled", "true");
		$(".pnrborder").removeClass("none");
		$(".pnrimport").removeClass("none");
		var PNRContent = $("#PNRContent").val();
		var comput=$("#comput").val();
		var data={
				'fileName':PNRContent,
				'comput':comput
		}
		$.ajax({
			type:'post',
			url:server_context+"/a/buyer/buyers/importPNR",
			data: data,
			success:function(dataJson){
				$("#nr").find("div").remove();
				$("#ticket").find("div").remove();
				//alert(dataJson.qte[0].price);
				console.log("rt:"+dataJson);
			//console.log("polices:"+dataJson.polices);
//				var dataObject= eval("("+dataJson+")");
				if("4" == dataJson.platType){
					alert("请输入QTE信息");
					return false;
				}
				var nr=document.getElementById("nr");
				var ticketPnr = document.getElementById("ticket");
				//nr.remove();
				//ticketPnr.remove();
				
				var hiddenInfo = "";

//				var rtQte = dataJson.qte;

				//var rtQte = dataJson.qte[0];

				var startAndEndCity = []; 
				var startAndEndTime = []; 
//				alert(dataJson.qte[0].price);

//				var farePrice1 = "<input type='hidden' name="+"supplierSalePrice"+" value='"+dataJson.qte[0].price+"'/>";
//				var taxPrice1 = "<input type='hidden' name="+"supplierTax"+" value='"+dataJson.qte[0].tax+"'/>";
				//隐藏属性，含：代理费，返点
				var peopleInfo = "";
				//alert(dataJson.passenger_list);
				if( undefined != dataJson.passenger_list){
					for(var i = 0; i <dataJson.passenger_list.length; i++){
						//旅客姓名
						var passengerName = dataJson.passenger_list[i].name;
						//旅客性别
						var passengerSex = dataJson.passenger_list[i].sex;
						//证件类型
						var identityType ="护照";
						//证件号
						var identity = dataJson.passenger_list[i].identity;
						//证件有效期
						var validData = dataJson.passenger_list[i].validData;
						//出生日期
						var birth = dataJson.passenger_list[i].birth;
						//证件签发地
						var passportCou = dataJson.passenger_list[i].passportCou;
						//国籍
						var birCountry = dataJson.passenger_list[i].birCountry;
//						hiddenInfo +="<input type='hidden' name="+"passengerName"+" value='"+passengerName+"'/>"+
//						"<input type='hidden' name="+"certType"+" value='"+identityType+"'/>"+
//						"<input type='hidden' name="+"certNo"+" value='"+identity+"'/>"+
//						//"<input type='hidden' name="+"validData"+" value='"+validData+"'/>"+
//						"<input type='hidden' name="+"passengerBirthdayStr"+" value='"+birth+"'/>"+
//						"<input type='hidden' name="+"certificateCountry"+" value='"+passportCou+"'/>"+
//						"<input type='hidden' name="+"expiredtimeStr"+" value='"+validData+"'/>"+
//						"<input type='hidden' name="+"national"+" value='"+birCountry+"'/>"+
//						"<input type='hidden' name="+"gender"+" value='"+passengerSex+"'/>";
						
					}
				}
				
				 if(undefined != dataJson.route_list){
					 for( var i=0;i<dataJson.route_list.length;i++){
							var objdiv = document.createElement("div");
							$(objdiv).attr("id","div"+i);
							var ulq='<ul class="clear ultwo">';
							var liq='<li class="pnrone licenter">';
							var liq1='<li class="licenter">';
							liq1.id="li"+i;
							var liqp1='<p>';
							liqp1.id="li"+i;
							var liqh1='</p>';
							//航空公司
							var flyCompany=dataJson.flyCompany[i];
							//航班号
							var flight=dataJson.flyNum[i];
							//航号
							var flyNum= dataJson.flyNum[i];
							//到达城市
							var toAirport=dataJson.route_list[i].tocity;
							//始发城市
							var fromAirport=dataJson.route_list[i].fromCity;
							//console.log(fromTime);
							//出港时间
							var fromTime=dataJson.route_list[i].fromTime;
							//到港时间dataJson.route_list[i].toTime+
							var toTime=dataJson.route_list[i].arriveDate;
							//飞行时长
							var time =dataJson.route_list[i].flyTime;
							//中转站

							//var middleAirport =dataJson.route_list[i].middleAirport;
							//var obj=dataJson.qte[0];


//							var middleAirport =dataJson.route_list[i].middleAirport;
//							var obj=dataJson.qte;

							var middleAirport =dataJson.route_list[i].middleAirport;
							//var obj=dataJson.qte[0];


							startAndEndCity[i] = fromAirport+","+toAirport;
							startAndEndTime[i] = fromTime+","+toTime;
//							 taxPrice=obj.tax+".00";
							var hiddenPassengerType="";
							var peopleInfo ;
//							//隐藏属性
//							hiddenInfo += "<input type='hidden' name="+"marketingFlightNo"+" value='"+flyCompany+"'/>"+
//							"<input type='hidden' name="+"fromAndto"+" value='"+startAndEndCity[i]+"'/>"+
//							"<input type='hidden' name="+"fromAndto"+" value='"+startAndEndTime[i]+"'/>"+
//							"<input type='hidden' name="+"flyNum"+" value='"+flight+"'/>"+
//							"<input type='hidden' name="+"flyTime"+" value='"+time+"'/>"; 
							//console.log(middleAirport+"middleAirport");
							var image='<img src="'+server_context+'/static/pagepc/images/pnrhang.png">';
							var lih='</li>';
							var ulh='</ul>';
							var obj1 = $("#formId");
							if(typeof(middleAirport)=="undefined"){
								$(objdiv).html(ulq+liq1+liqp1+flyNum+liqh1+liqp1+flyCompany+liqh1+lih+liq1+liqp1+fromTime+liqh1+liqp1+fromAirport+liqh1+lih+liq1+liqp1+liqh1+image+lih+liq1+liqp1+toTime+liqh1+liqp1+toAirport+liqh1+lih);
							}else{
								$(objdiv).html(ulq+liq1+liqp1+flyNum+liqh1+liqp1+flyCompany+liqh1+lih+liq1+liqp1+fromTime+liqh1+liqp1+fromAirport+liqh1+lih+liq1+liqp1+liqh1+image+liqp1+middleAirport+liqh1+lih+liq1+liqp1+toTime+liqh1+liqp1+toAirport+liqh1+lih);
							}
							$(objdiv).append(ulh);
							nr.appendChild(objdiv);
						} 
				 }
				
				 
				 var uls = "<ul class='policyhead clear'>" +
					"<li>供应商</li><li>旅客类型</li><li>旅客身份</li>" +
					"<li>价格</li><li>税费</li><li>代理费率</li><li>返点</li>" +
					"<li>退改规则</li></ul>";//<li>支付方式</li><li>类型</li>
				 //非标准航段
				 //alert(dataJson.travelType);
				 if("3" == dataJson.travelType){
					 var htmlUL1=uls;
					 if(undefined != dataJson.qte){
						 for(var i = 0 ;i < dataJson.qte.length; i++){
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

								var suppid="6aa2721eeea54e16a4d5fd572878197d"; //dataJson.polices[j].supplierId;
								var ticButto='<button class="thatok-button fr lichbuton" style="margin-right:134px;width:100px" onclick="ticket('+"'"+suppid+"'"+')">请核对政策</button>';

								//var ticButto='<button class="thatok-button fr lichbuton" style="margin-right:134px;" onclick="ticket('+"'"+suppid+"'"+')">生成订单</button>';

								var ulh_ticket='</ul>';
							 
							 var qte = dataJson.qte[i];
							 //票面价
							 //var farePrice = "票面价:"+(qte.price-qte.tax)+".00";
							 //销售价
							 var salesPrice = "销售价:"+(qte.price-qte.tax)+".00";
							 //税费
							 var tax = qte.tax;
							 //非标准航段；
							 gys="保盛";
							 //乘客类型
							 var personType = qte.identity;
							 var passengerIdentity = "普通";
							 //type="暂无";
							 var dropOrUpdateRule='退改规则';
							 if("IN" == personType){
									passengerType="婴儿";
								}else if("MAN" == personType){
									passengerType="成人";
								}else{
//									alert(passengerType);
									passengerType="儿童";
								}
							 
							 if(i == 0){
									//var gys = dataJson['polices'][j].supplierName;//'供应商';
									//var travelType = dataJson.polices[j].travelType;//行程类型
		//									if("0" == travelType){
		//										var type = "单程";
		//									}
		//									if("1" == travelType){
		//										var type = "往返";
		//									}
		//									if("2" == travelType){
		//										var type = "单程/往返";
		//									}
									//callPrice = "结算价:"+callPrice;
		//									peopleInfo ="<input type='hidden' name="+"passengerType"+" value='"+passengerIdentity+"'/>"+ 
		//												"<input type='hidden' name="+"personagenFee"+" value='"+personagenFee+"'/>"+
		//												"<input type='hidden' name="+"rebate"+" value='"+personrebate+"'/>";
									
									//一个订单的信息
								 htmlUL1 +=ulq_ticket+liq_ticket+gys+lih_ticket+
							     liq_ticket+passengerType+lih_ticket+
								 liq_ticket+passengerIdentity+lih_ticket+
								 xiaosPrice+salesPrice+lih_ticket+
								 //xiaosPrice+farePrice+lih_ticket+
								 liq_ticket+tax+lih_ticket+
								 liq_ticket+"&nbsp;"+lih_ticket+
								 liq_ticket+"&nbsp;"+lih_ticket+
								// liq_ticket+"&nbsp;"+lih_ticket+
								 //liq_ticket+type+lih_ticket+
								 liq_ticket+dropOrUpdateRule+lih_ticket//+
								// liq_ticket+payModel+lih_ticket+ulh_ticket+
								 //ulq_ticket+liq_ydyq+span_ydyq+span+
								 //lih_ticket+xiaosPrice+salesPrice+
								 //lih_ticket+ulh_ticket;//预定要求的ul
											 
								}else if(i > 0){
									//一个订单的信息
									htmlUL1 +="<div class='hideaddpnr clear'>"+
											"<div class='fr borderhide'></div>"+
											ulq_ticket+"<li>"+"&nbsp;"+"</li>"+
										     liq_ticket+passengerType+lih_ticket+
											 liq_ticket+passengerIdentity+lih_ticket+
											 xiaosPrice+"&nbsp;"+lih_ticket+
											 //xiaosPrice+farePrice+lih_ticket+
											 liq_ticket+tax+lih_ticket+
											 liq_ticket+"&nbsp"+lih_ticket+
											 liq_ticket+"&nbsp"+lih_ticket+
											// liq_ticket+"&nbsp"+lih_ticket+
											 //liq_ticket+type+lih_ticket+
											 liq_ticket+dropOrUpdateRule+lih_ticket+
											 //liq_ticket+payModel+lih_ticket+ulh_ticket+
											 ulq_ticket+liq_ydyq+"&nbsp"+"&nbsp"+
											 ulq_ticket+liq_ydyq+span_ydyq+span+lih_ticket+xiaosPrice+salesPrice+lih_ticket+ulh_ticket+//预定要求的ul;//+salesPrice+
											 ulq_ticket+liq_ydyq+"&nbsp"+"&nbsp;"+lih_ticket
											; //结算
								}
						 }  
					 }
					 //alert(htmlUL1);
					 htmlUL1+="<div class='pnrclickhide'>更多乘客类型&or;</div>"+ticketOutDiv+ticketOutDivfl+spanlow+spanlast+"</div>"+ticButto+"</div>";
					 $(ticketDiv).html(htmlUL1);
					ticketPnr.appendChild(ticketDiv);
					
				 }else if("0" == dataJson.travelType || "1" == dataJson.travelType){
				if(undefined == dataJson.polices){
					 var ticketDiv = document.createElement("div");
					 $(ticketDiv).html("暂无匹配的政策");
					 ticketPnr.appendChild(ticketDiv);
					return false;
				}else{
					for( var j = 0 ; j < dataJson.polices.length; j++){
						 var htmlUL1 = uls;
						 var polices = dataJson.polices[j]
						 var personListSize = polices.personList.length;
						 for(var k = 0; k < personListSize;k++){
							//taxPrice=obj.tax+".00";
							var gys = dataJson.polices[j].office//supplierName;//'供应商';
							var policy = dataJson.polices[j].personList[k];
							//farePrice = "票面价:"+policy.price+".00";
							salesPrice = "销售价:"+policy.price+".00";
							var tax = policy.tax;
							var personType=policy.personType;
							var agentFee=policy.agenCy+"%";//agencyFee代理费
							//alert(policy.agencyFee);
							//agentFee = agentFee.substring(0,1)+"%";
							var rebate = policy.rebate+"%";//返点
							//rebate = rebate*100+"%";
							//rebate = rebate.substring(0,1)+"%";
							//type="暂无";
							var callPrice = "结算价:"+policy.flyPrice+".00";
							var dropOrUpdateRule='退改规则';
							 var ticketDiv = document.createElement("div");
							 	ticketDiv.class="lichcontent";
							 	//ticketDiv.id="div_ticket"+j;
								$(ticketDiv).attr("id","div_ticket"+j);
								$(ticketDiv).attr("class","lichcontent");
								var ulq_ticket='<ul class="clear">';
								var liq_ticket='<li>';
								var lih_ticket='</li>';
								var liq_ydyq= '<li class="liwid">';
								var span_ydyq='<span class="redreset">&nbsp;</span>'//&lowast;</span>';
								var span='<span>预定要求</span>';
								var xiaosPrice='<li class="price">';
								//出票要求
								var outSpan='<span>出票要求</span>'
								//订单
								var ticketOutDiv='<div class="lichbottom clear">';
								var ticketOutDivfl='<div class="fl redreset liche">';
								var spanlow='<span>&nbsp;</span>'
								var spanlast='<span>出票前请对PNR进行授权，否则后续无法给您出票</span>';
								var suppid=dataJson.polices[j].supplierId;
								var ticButto='<button class="thatok-button fr lichbuton" onclick="ticket('+"'"+suppid+"'"+')">生成订单</button>';
								var ulh_ticket='</ul>';
								var passengerPid = dataJson.polices[j].passagerPid.split(",");
								var passengerType = "";//旅客类型
								var passengerIdentity="";
								for(var i = 0; i<passengerPid.length;i++){
									if("0" == passengerPid[i]){
										//passengerType = "成人";
										passengerIdentity += "普通,"//旅客类型
									}
									if("1" == passengerPid[i]){
										//passengerType = "成人";
										passengerIdentity += "留学生,"//旅客类型
									}
									if("2" == passengerPid[i]){
										passengerType = "成人";
										passengerIdentity += "移民,"//旅客类型
									}
									if("3" == passengerPid[i]){
										//passengerType = "成人";
										passengerIdentity += "劳工,"//旅客类型
									}
								}
								if("IN" == personType){
									passengerType="婴儿";
								}else if("MAN" == personType){
									passengerType="成人";
								}else{
//									alert(passengerType);
									passengerType="儿童";
								}
								//旅客类型
								passengerIdentity = passengerIdentity.substring(0,passengerIdentity.length-1);
								//var personagenFee = dataJson.polices[j].agencyFee;
								//var personrebate = dataJson.polices[j].rebate;
								//var payModel='支付宝';
								if(k == 0){
									//var gys = dataJson['polices'][j].supplierName;//'供应商';
									var travelType = dataJson.polices[j].travelType;//行程类型
		//									if("0" == travelType){
		//										var type = "单程";
		//									}
		//									if("1" == travelType){
		//										var type = "往返";
		//									}
		//									if("2" == travelType){
		//										var type = "单程/往返";
		//									}
									//callPrice = "结算价:"+callPrice;
		//									peopleInfo ="<input type='hidden' name="+"passengerType"+" value='"+passengerIdentity+"'/>"+ 
		//												"<input type='hidden' name="+"personagenFee"+" value='"+personagenFee+"'/>"+
		//												"<input type='hidden' name="+"rebate"+" value='"+personrebate+"'/>";
									
									//一个订单的信息
									htmlUL1 +=ulq_ticket+liq_ticket+gys+lih_ticket+
										     liq_ticket+passengerType+lih_ticket+
											 liq_ticket+passengerIdentity+lih_ticket+
											 xiaosPrice+salesPrice+lih_ticket+
											 //xiaosPrice+farePrice+lih_ticket+
											 liq_ticket+tax+lih_ticket+
											 liq_ticket+agentFee+lih_ticket+
											 liq_ticket+rebate+lih_ticket+
											// liq_ticket+"&nbsp;"+lih_ticket+
											 //liq_ticket+type+lih_ticket+
											 liq_ticket+dropOrUpdateRule+lih_ticket+
											// liq_ticket+payModel+lih_ticket+ulh_ticket+
											 //ulq_ticket+liq_ydyq+span_ydyq+span+lih_ticket+
											 xiaosPrice+"&nbsp;"+lih_ticket+ulh_ticket+//预定要求的ul
											 ulq_ticket+liq_ydyq+span_ydyq+"&nbsp;"+
											 lih_ticket+xiaosPrice+callPrice+lih_ticket+ulh_ticket //结算
								}else if(k > 0){
									//一个订单的信息
									htmlUL1 +="<div class='hideaddpnr clear'>"+
											"<div class='fr borderhide'></div>"+
											ulq_ticket+"<li>"+"&nbsp;"+"</li>"+
										     liq_ticket+passengerType+lih_ticket+
											 liq_ticket+passengerIdentity+lih_ticket+
											 xiaosPrice+salesPrice+lih_ticket+
											 //xiaosPrice+farePrice+lih_ticket+
											 liq_ticket+tax+lih_ticket+
											 liq_ticket+agentFee+lih_ticket+
											 liq_ticket+rebate+lih_ticket+
											// liq_ticket+"&nbsp;"+lih_ticket+
											 //liq_ticket+type+lih_ticket+
											 liq_ticket+dropOrUpdateRule+lih_ticket+
											// liq_ticket+payModel+lih_ticket+ulh_ticket+
											 //ulq_ticket+liq_ydyq+span_ydyq+span+lih_ticket+
											 xiaosPrice+"&nbsp;"+lih_ticket+ulh_ticket+//预定要求的ul
											 ulq_ticket+liq_ydyq+span_ydyq+"&nbsp;"+
											 lih_ticket+xiaosPrice+callPrice+lih_ticket+ulh_ticket+"</div>"; //结算
								}
							
							//obj1.html(obj1.html()+"<div id='formId"+j+"'><input type='hidden' id='dataJson"+j+"' name='dataJson"+j+"' value='"+JSON.stringify(dataJson.polices[j])+"'/></div>");
						 	}
						 htmlUL1+="<div class='pnrclickhide'>更多乘客类型&or;</div>"+ticketOutDiv+ticketOutDivfl+spanlow+spanlast+"</div>"+ticButto+"</div>";
						 $(ticketDiv).html(htmlUL1);
						ticketPnr.appendChild(ticketDiv);
						 }
					}
				}
				 //多个类型的旅客时用到的js
					$(".pnrclickhide").toggle(function(){
						$(this).siblings(".hideaddpnr").slideDown();
						$(this).html("收起信息&and;");
					},function(){
						$(this).siblings(".hideaddpnr").slideUp();
						$(this).html("更多乘客类型&or;");
					})
				 //隐藏的属性,直接传的Map
				 //obj1.html(obj1.html()+hiddenInfo+farePrice1+taxPrice1+hiddenPassengerType+peopleInfo);
				// obj1.html(obj1.html()+"<input type='hidden' id='dataJson' name='dataJson' value='"+JSON.stringify(dataJson)+"'/>" +
				 //		"<input type='hidden' id='dataJson' name='dataJson' value='"+JSON.stringify(dataJson.polices)+"'/>");

			}
		});
		
	}
	
	function ticket(id){
		$("#suppliId").val(id);
		$("#submitId").submit();
	}
	
