<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际政策修改</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/query.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/pading.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<!-- 时间控件的  -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery/jquery-ui.min.css"><nk>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-ui.min.js"></script>
<style type="text/css">
#box{text-align:left;margin:0 auto; }
#suggest,#suggest1,#suggest2,#suggest3,#suggest4,#suggest5,#suggest6,#suggest7
#suggest8,#suggest9,#suggest10{width:540px; height:300px;overflow-y:scroll;}
.gray{color:gray;}
.ac_results {background:#fff;border:1px solid #7f9db9;position:absolute;z-index:10000;display:none;}
.ac_results ul{margin:0;padding:0;list-style:none;}
.ac_results li a{white-space:nowrap;text-decoration:none;display:block;color:#05a;padding:1px 3px;}
.ac_results li{border:1px solid #fff;}
.ac_over,.ac_results li a:hover {background:#c8e3fc;}
.ac_results li a span{float:right;font-size:1px}
.ac_result_tip{border-bottom:1px dashed #666;padding:3px;}
</style>
<script type="text/javascript">
$(function(){
	
	$.post(
			"${ctx}/generalrules/generalRules/aviation",
			{},
			function(date){
				
				commoncitys = date;
// 				var commoncitys = eval("("+date+")") ;

	$("#arrcity").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word',onSelect:function(){$("#city2").click();},attachObject:'#suggest'});
			}
		);
	
	
	$.post(
			"${ctx}/policyrules/policyRules/aviation",
			{},
			function(date){
				var commoncitys=new Array();
				commoncitys = date;
// 				var commoncitys = eval("("+date+")") ;
	$("#arrcity1").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word1',onSelect:function(){$("#city2").click();},attachObject:'#suggest1'});
	$("#arrcity2").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word2',onSelect:function(){$("#city2").click();},attachObject:'#suggest2'});
	$("#arrcity3").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word3',onSelect:function(){$("#city2").click();},attachObject:'#suggest3'});
	$("#arrcity4").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word4',onSelect:function(){$("#city2").click();},attachObject:'#suggest4'});
	$("#arrcity5").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word5',onSelect:function(){$("#city2").click();},attachObject:'#suggest5'});
	$("#arrcity6").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word6',onSelect:function(){$("#city2").click();},attachObject:'#suggest6'});
	$("#arrcity7").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word7',onSelect:function(){$("#city2").click();},attachObject:'#suggest7'});
	$("#arrcity8").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word8',onSelect:function(){$("#city2").click();},attachObject:'#suggest8'});
	$("#arrcity9").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word9',onSelect:function(){$("#city2").click();},attachObject:'#suggest9'});
	$("#arrcity10").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word10',onSelect:function(){$("#city2").click();},attachObject:'#suggest10'});
			}
		);
	// $("#city2").suggest(citys,{hot_list:commoncitys,attachObject:"#suggest2"});
	
});



var airCode = "";
function checkAirport(){
// 	var isTrue = true;
	var codeBrr = $("#arrcity_3word").val();
	var codeBrr1 = $("#arrcity_3word1").val();
	var codeBrr2 = $("#arrcity_3word2").val();
	var codeBrr3 = $("#arrcity_3word3").val();
	var codeBrr4 = $("#arrcity_3word4").val();
	var codeBrr5 = $("#arrcity_3word5").val();
	var codeBrr6 = $("#arrcity_3word6").val();
	var codeBrr7 = $("#arrcity_3word7").val();
	var codeBrr8 = $("#arrcity_3word8").val();
	var codeBrr9 = $("#arrcity_3word9").val();
	var codeBrr10 = $("#arrcity_3word10").val();
// 	alert(codeBrr);
// 	var airCodeArr = airCode.split("/");
// 	for(var i=0;i<airCodeArr.length;i++){
// 		if(airCodeArr[i] == codeBrr){
// 			alert("已选过本航司");
// 			isTrue = false;
// 		}
// 	}
// 	$.post(
// 			"${ctx}/policyrules/policyRules/checkAirline",
// 			{
// 			"codeBrr":codeBrr
// 			},
// 			function(date){
// 				if(date == false){
// 					alert("没有此航班");
// 				}else{
// 					if(isTrue == true){
// 						airCode = codeBrr;
// 						if("" != codeBrr){
// 							alert(airCode);
							$("#arrcity").val(codeBrr);
							$("#arrcity1").val(codeBrr1);
							$("#arrcity2").val(codeBrr2);
							$("#arrcity3").val(codeBrr3);
							$("#arrcity4").val(codeBrr4);
							$("#arrcity5").val(codeBrr5);
							$("#arrcity6").val(codeBrr6);
							$("#arrcity7").val(codeBrr7);
							$("#arrcity8").val(codeBrr8);
							$("#arrcity9").val(codeBrr9);
							$("#arrcity10").val(codeBrr10);
// 						}
// 					}
// 				}
// 			}
// 		);
}

$(function(){

	  //status代表的是开始的id
	  	var objclear,
	  		objval,
	  		objqing,
  			objinput;
	    $( "#minTime" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 2,
		  minDate: '',//今天之前可不可以选中 设置值就是今天之前的日期不能用 设置为空呢就是可以选择之前的时
	      duration:'normal',
		  showButtonPanel:true,//是否显示清空按钮
		  closeText:"",
		  beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate) {
	      	objval = selectedDate;
	      	if(selectedDate == ''){
	      		$( "#maxTime" ).datepicker( "option", "minDate", '0');
	      	}else{
	      		$( "#maxTime" ).datepicker( "option", "minDate", selectedDate);
	      	}
	      }
	    });
	    $( "#maxTime" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 2,
	      showButtonPanel:true,//是否显示清空按钮
	      closeText:"",
	      minDate: 0,
	      beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate){
	      	objval = selectedDate;
	        if(selectedDate == ''){
	      		$( "#minTime" ).datepicker( "option", "maxDate", null);
	      	}else{
	      		$( "#minTime" ).datepicker( "option", "maxDate", selectedDate);
	      	}
	      }
	    });
	    $( "#minData" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: false,
		      changeYear:false,
		      dateFormat:"yy-mm-dd",
		      numberOfMonths: 2,
			  minDate: '',//今天之前可不可以选中 设置值就是今天之前的日期不能用 设置为空呢就是可以选择之前的时
		      duration:'normal',
			  showButtonPanel:true,//是否显示清空按钮
			  closeText:"",
			  beforeShow : function(input){
				  objqing = input;
			  },
		      onClose: function(selectedDate) {
		    	  objinput = selectedDate;
		      	if(selectedDate == ''){
		      		$( "#maxData" ).datepicker( "option", "minData", '0');
		      	}else{
		      		$( "#maxData" ).datepicker( "option", "minData", selectedDate);
		      	}
		      }
		    });
		    $( "#maxData" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: false,
		      changeYear:false,
		      dateFormat:"yy-mm-dd",
		      numberOfMonths: 2,
		      showButtonPanel:true,//是否显示清空按钮
		      closeText:"",
		      minDate: 0,
		      beforeShow : function(input){
		    	objqing = input;
			  },
		      onClose: function(selectedDate){
		      	objinput = selectedDate;
		        if(selectedDate == ''){
		      		$( "#minData" ).datepicker( "option", "maxData", null);
		      	}else{
		      		$( "#minData" ).datepicker( "option", "maxData", selectedDate);
		      	}
		      }
		    });
	    $(".ui-datepicker-close").live("click",function(){
	    	objclear.value = "";
			   var dates = $("#minTime,#maxTime");
			   var date = $("#minData,#maxData");
			  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
			   dates.datepicker("option", "minDate", '0');
			   dates.datepicker("option", "maxDate", null);
			   date.datepicker("option", "minDate", '0');
			   date.datepicker("option", "maxDate", null);
	    })
	    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
	    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
	    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
	    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
	  });


</script>
</head>
<body>
<form action="${ctx}/policyrules/policyRules/update" method="post">
<input type="hidden" name="token" value="${token}"/>
<div class="addpolicy">
	<img src="${ctxStatic}/general/images/xiudeil.png">
	<p>政策修改</p>
</div>
<!-- 总则编码 -->
<div class="query clear">
	<div class="queryinter">
		<span class="query-span interspan">政策名称：</span>
		<input type="hidden" name="id" value="${tbPolicyRules.id}">
		<input type="text" name="policyName"  value="${tbPolicyRules.policyName}" class="querycode">
		<button class="addquery">修改</button>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">开票office号：</span>
		<input type="text" name="office" class="querycode" value="${tbPolicyRules.office}">
		<span>状态：</span>
		<select class="codeqi" name="policyStatus">
			<option value="0" <c:if test="${tbPolicyRules.policyStatus == 0}">selected="selected"</c:if>>启用</option>
			<option value="1" <c:if test="${tbPolicyRules.policyStatus == 1}">selected="selected"</c:if>>禁用</option>
			<option value="2" <c:if test="${tbPolicyRules.policyStatus == 2}">selected="selected"</c:if>>挂起</option>
		</select>
	</div>
	<div class="queryinter">
		<div class="interno">
		<input type="checkbox" id="effectil" checked="checked" class="checkeffect">
<%-- 			<img src="<%=request.getContextPath()%>/images/interno.png" class="addimg"> --%>
			<span class="spanadd">是否换编码开票</span>
		</div>
	</div>
	<div class="block">
		<div class="effect-head clear">
			<p>基本信息：</p>
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="queryinter" style="margin-top: 20px;">
		<span class="query-span interspan">出票航司：</span>
		<div class="box">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word" value="" />
			<input type="text" name="tktAirline" id="arrcity" class="querycode" value="${tbPolicyRules.tktAirline}" />
			<div id='suggest' class="ac_results"></div>
		</div>
	</div>
</div>
<div class="colorhei"></div>
<div class="passnger clear">
	<div class="block">
		<div class="effect-head clear">
			<p>旅客信息：</p>
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="queryinter" style="margin-top: 20px;">
		<span class="query-span">旅客身份：</span>
		<label>
			<input type="checkbox" id="effectil" ${fn:indexOf(tbPolicyRules.passagerPid, '0') != -1 ?  'checked' : '' } class="checkeffect" name="passagerPid" value="0">
			<span class="marginri">普通</span>
		</label>
		<label>
			<input type="checkbox" id="effectil1" class="checkeffect" ${fn:indexOf(tbPolicyRules.passagerPid, '1') != -1 ?  'checked' : '' } name="passagerPid" value="1">
			<span class="marginri">移民</span>
		</label>
		<label>
			<input type="checkbox" id="effectil2" class="checkeffect" ${fn:indexOf(tbPolicyRules.passagerPid, '2') != -1 ?  'checked' : '' } name="passagerPid" value="2">
			<span class="marginri">留学生</span>
		</label>
		<label>
			<input type="checkbox" id="effectil3" class="checkeffect" ${fn:indexOf(tbPolicyRules.passagerPid, '3') != -1 ?  'checked' : '' } name="passagerPid" value="3">
			<span class="marginri">劳工</span>
		</label>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">旅游下线人数：</span>
		<input type="text" name=passagerCount value="${tbPolicyRules.passagerCount}" class="querycode">
	</div>
</div>
<div class="colorhei"></div>
<div class="intertrip">
	<div class="block">
		<div class="effect-head clear">
			<p>行程信息：</p>
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="queryinter" style="margin-top: 20px;">
		<span class="query-span">行程类型：</span>
		<select class="codeqi" name="travelType">
			<option value="0" selected="selected" <c:if test="${tbPolicyRules.travelType == 0}">selected="selected"</c:if>>单程</option>
			<option value="1" <c:if test="${tbPolicyRules.travelType == 1}">selected="selected"</c:if>>返程</option>
			<option value="2" <c:if test="${tbPolicyRules.travelType == 2}">selected="selected"</c:if>>往返</option>
		</select>
	</div>
	<div class="interborder">
		<p class="interhead">去程信息：</p>
		<div class="queryinter clear">
			<span class="query-span fl" style="line-height: 30px;">去程起点：</span>
			<div class="box">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word1" value="" />
			<input type="text" name="outOrg" id="arrcity1" class="querycode" value="${tbPolicyRules.outOrg}"/>
			<div id='suggest1' class="ac_results"></div>
			</div>
			<div class="box f1">
			<span class="fl" style="line-height: 30px;">去程起点除外：</span>
			<input type="hidden" name="arrcity_3word" id="arrcity_3word2" value="" />
			<input type="text" name="outOrgEx" id="arrcity2" class="querycode" value="${tbPolicyRules.outOrgEx}" />
			<div id='suggest2' class="ac_results"></div>
		</div>
		</div>
		<div class="queryinter clear">
			<span class="query-span interblock">去程终点：<br />(折返点)</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word3" value="" />
			<input type="text" name="outDes" id="arrcity3" class="querycode" value="${tbPolicyRules.outDes}"/>
			<div id='suggest3' class="ac_results"></div>
		</div>
			<div class="box f1">
			<span class="interblock">去程终点除外：<br />(折返点除外)</span>
			<input type="hidden" name="arrcity_3word" id="arrcity_3word4" value="" />
			<input type="text" name="outDesEx" id="arrcity4" class="querycode" value="${tbPolicyRules.outDesEx}"/>
			<div id='suggest4' class="ac_results"></div>
		</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">回程信息：</p>
		<div class="queryinter clear">
			<span class="query-span">回程终点：</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word5" value="" />
			<input type="text" name="returnDes" id="arrcity5" class="querycode" value="${tbPolicyRules.returnDes}"/>
			<div id='suggest5' class="ac_results"></div>
		</div>
			<span>回程终点除外：</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word6" value="" />
			<input type="text" name="returnDesEx" id="arrcity6" class="querycode" value="${tbPolicyRules.returnDesEx}"/>
			<div id='suggest6' class="ac_results"></div>
		</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">中转点信息：</p>
		<div class="queryinter clear">
			<span class="query-span">去程不能经过：</span>
			<div class="box f1">
				<input type="hidden" name="arrcity_3word" id="arrcity_3word7" value="" />
				<input type="text" name="outUnablePass" id="arrcity7" class="querycode" value="${tbPolicyRules.outUnablePass}"/>
				<div id='suggest7' class="ac_results"></div>
			</div>
			<span>去程必须经过：</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word8" value="" />
			<input type="text" name="outMustPass" id="arrcity8" class="querycode" value="${tbPolicyRules.outMustPass}"/>
			<div id='suggest8' class="ac_results"></div>
		</div>
		</div>
		<div class="queryinter clear">
			<span class="query-span">回程不能经过：</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word9" value="" />
			<input type="text" name="returnUnablePass" id="arrcity9" class="querycode" value="${tbPolicyRules.returnUnablePass}"/>
			<div id='suggest9' class="ac_results"></div>
		</div>
			<span>回程必须经过：</span>
			<div class="box f1">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word10" value="" />
			<input type="text" name="returnMustPass" id="arrcity10" class="querycode" value="${tbPolicyRules.returnMustPass}"/>
			<div id='suggest10' class="ac_results"></div>
		</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">航班限制：</p>
		<div class="queryinter clear">
			<span class="query-span">排除航班号：</span>
			<input type="text" name="excludeFilghtNo" class="querycode" value="${tbPolicyRules.excludeFilghtNo}">
			<span>仅限航班号：</span>
			<input type="text" name="allowFilghtNo" class="querycode" value="${tbPolicyRules.allowFilghtNo}">
		</div>
	</div>
	<div class="interborder">	
		<div class="marbor">
			<span class="interhead">舱位信息：</span>
			<input type="text" name="cabin" class="querycode" value="${tbPolicyRules.cabin}">
		</div>
		<p class="interhead">成人：</p>
		<div class="queryinter clear">
			<span class="query-span">返点：</span>
			<input type="text" name="rebate" class="querycode" value="${tbPolicyRules.rebate}">
			<span>开票费：</span>
			<input type="text" name="billingFee" class="querycode" value="${tbPolicyRules.billingFee}">
		</div>
		<div class="queryinter clear" style="display: none;">
			<span class="query-span">特殊代理：</span>
			<input type="text" name="agencyFee" value="${tbPolicyRules.agencyFee}" class="querycode" style="margin-right: 0;">
			<span class="redreset">&lowast;如不填写特殊代理费，则由平台的代理费为准</span>
		</div>
		<div class="interno" style="margin-left: 20px;">
		<input type="checkbox" id="effectil" checked="checked" class="checkeffect" name="sharePolicy" <c:if test="${tbPolicyRules.childrenPoundageChoice == 'Y'}">checked="checked"</c:if>  value="${tbPolicyRules.sharePolicy}">
<%-- 			<img src="<%=request.getContextPath()%>/images/interno.png" class="addimg"> --%>
			<span class="spanadd">是否允许与其他政策组合</span>
		</div>
		<p class="interhead">儿童：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" checked="checked" name="childrenReward"  value="${tbPolicyRules.childrenReward}">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>与成人一致</span>
				</label>
				<label class="checkradio">
				<input type="radio" checked="checked" name="childrenReward" value="${tbPolicyRules.childrenReward}">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>可开无后返</span>
				</label>
				<label class="checkradio">
				<input type="radio" checked="checked" name="childrenReward" value="${tbPolicyRules.childrenReward}">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>不可开</span>
				</label>
				<label class="checkradio">
				<input type="radio" checked="checked" name="childrenReward" value="${tbPolicyRules.childrenReward}">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>指定奖励</span>
				</label>
				<span>返点：</span>
				<input type="text" name="childrenRebate" class="querycode" value="${tbPolicyRules.childrenRebate}">
			</div>
			<div class="interdio">
				<label>
					<input type="checkbox" id="effectil" name="childrenPoundageChoice" <c:if test="${tbPolicyRules.childrenPoundageChoice == 'Y'}">checked="checked"</c:if> value="Y" class="checkeffect">
					<span class="marginri">加手续费<input type="text"  class="textcheck" value="${tbPolicyRules.childrenPoundage}" name="childrenPoundage">元</span>
				</label>
				<label>
					<input type="checkbox" id="effectil" name="childrenOpenNoCom"  value="Y" <c:if test="${tbPolicyRules.childrenOpenNoCom == 'Y'}">checked="checked"</c:if> class="checkeffect">
					<span class="marginri">可开无代理费</span>
				</label>
			</div>
		</div>
		<p class="interhead">婴儿：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" name="badyReward" value="0" <c:if test="${tbPolicyRules.badyReward == 0}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>可开无奖励</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="badyReward" value="0" <c:if test="${tbPolicyRules.badyReward == 0}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>不可开</span>
				</label>
			</div>
		</div>
	</div>	
</div>
<div class="colorhei"></div>
<div class="interdate">
	<div class="block">
		<div class="effect-head clear" style="margin-top: 20px;">
			<p>日期要求：</p>
			<img src="${ctxStatic}/images/right.jpg">
		</div>
	</div>
	<div class="queryinter interleft">
		<span>去程旅行日期：</span>
		<input type="text"   id="minTime" value="${tbPolicyRules.firstDateStartStr}" name="firstDateStart"  class="doubledate ipticon time">
		<span>至</span>
		<input type="text" id="maxTime" value="${tbPolicyRules.firstDateEndStr}"  name="firstDateEnd" class="doubledate ipticon time">
	</div>
	<div class="queryinter interleft">
		<span>返程旅行日期：</span>
		<input type="text" value="${tbPolicyRules.lastDateStartStr}"  id="minData" name="lastDateStart"  class="doubledate ipticon time">
		<span>至</span>
		<input type="text" value="${tbPolicyRules.lastDateEndStr}" id="maxData" name="lastDateEnd" class="doubledate ipticon time">
</div>
<div class="colorhei">
<input type="hidden" value="${tbPolicyRules.prCode}" name="prCode"/>
<input type="hidden" value="${tbPolicyRules.workTime}" name="workTime"/>
<input type="hidden" value="${tbPolicyRules.endTime}" name="endTime"/>
<input type="hidden" value="${tbPolicyRules.creatUser}" name="creatUser"/>
<input type="hidden" value="${tbPolicyRules.updateUser}" name="updateUser"/>
</div>
<div>
	<div class="block">
		<div class="effect-head clear" style="margin-top: 20px;">
			<p>备注信息：</p>
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="marbor clear">
		<span class="interhead fl interban">航班限制：</span>
		<textarea class="interarae fl" name="policyRemark">${tbPolicyRules.policyRemark}</textarea>
	</div>
	<div class="interno" style="margin-top: 10px;">
	<input type="checkbox" id="effectil" checked="checked" class="checkeffect" name="isPolicyStatus" value="Y" <c:if test="${tbPolicyRules.policyStatus == 2}">checked="checked"</c:if>>
<%-- 		<img src="<%=request.getContextPath()%>/images/interno.png" class="addimg"> --%>
		<span class="spanadd">是否换编码开票</span>
	</div>
	<div class="clear">
		<div class="button-detail fl">
			<img src="${ctxStatic}/general/images/addplo.png">
			<p><input type="submit" style="background:#c41213;border:0;float:left;margin-top:7px; marfont-size:16px;color:#FFF;" value="修改"/></p>
		</div>
	</div>
</div>
</form>
</body>
</html>