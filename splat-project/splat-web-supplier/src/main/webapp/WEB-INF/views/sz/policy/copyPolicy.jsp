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
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<!-- 时间控件的  -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery/jquery-ui.min.css"><nk>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-ui.min.js"></script>
<style type="text/css">
#box{text-align:left;margin:0 auto; }
.suggest{width:540px; height:300px;overflow-y:scroll;}
.gray{color:gray;}
.ac_results {background:#fff;border:1px solid #7f9db9;position:absolute;z-index:10000;display:none;width:400px; height:300px;overflow-y:scroll;}
.ac_results ul{margin:0;padding:0;list-style:none;}
.ac_results li a{white-space:nowrap;text-decoration:none;display:block;color:#05a;padding:1px 3px;}
.ac_results li{border:1px solid #fff;}
.ac_over,.ac_results li a:hover {background:#c8e3fc;}
.ac_results li a span{float:right;font-size:1px}
.ac_result_tip{border-bottom:1px dashed #666;padding:3px;}
</style>
<script type="text/javascript">
$(function(){
		if($("#ac").val()=="Y"){
			if($("#childrenPoundage").val()==""){
				 document.getElementById("childrenPoundage").value="0.00";
				 }
		 }else{
			 document.getElementById("childrenPoundage").value="";
		 }
		var radio=document.getElementsByName("childrenReward");
		 for(var i=0;i<radio.length;i++){
			if(radio[i].checked==true){
			value=radio[i].value;
			if(value=='04'){
				document.getElementById("childrenRebate").disabled=false;
			}else{
				document.getElementById("childrenRebate").disabled=true;
			}
			break;
			}
		 }
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
function pointreward(){
	 var value="";
	 var radio=document.getElementsByName("childrenReward");
	 for(var i=0;i<radio.length;i++){
		if(radio[i].checked==true){
		value=radio[i].value;
		if(value=='04'){
			document.getElementById("childrenRebate").disabled=false;
		}else{
			document.getElementById("childrenRebate").disabled=true;
		}
		}
	 }
}
function add(){
	/* $.post(
			"${ctx}/policyrules/policyRules/aviation",
			{},
			function(date){
				
			}
		); */
		//alert($("#outOrg").val());
	if($("#policyName").val() == ''){
		alert("政策名称不能为空");
	}else if($("#office").val() == ''){
		alert("开票office不能为空");
	}else if($("#office").val().length>8){
		alert("开票office长度不要超过8位");
	}else if($("#outOrg").val() == ''){
		alert("启程起点不能为空");
	}else if($("#outDes").val() == ''){
		alert("启程终点不能为空");
	}else if($("#cabin").val() == ''){
		alert("舱位信息不能为空");
	}else if($("#tktAirline").val() == ''){
		alert("出票航司不能为空");
	}else if($("#minTime").val() == ''){
		alert("去程旅行开始日期不能为空");
	}else if($("#maxTime").val() == ''){
		alert("去程旅行结束日期不能为空");
	}else if($("#travelType").val() != '0'&&$("#minData").val()==''){
		alert("返程旅行开始日期不能为空");
	}else if($("#travelType").val() != '0'&&$("#maxData").val()==''){
		alert("返程旅行结束日期不能为空");
	}else if($("#rebate").val()==''){
		alert("成人返点不能为空");
	}else{
		$("#addId").submit();
	}
	//
}
$(document).keydown(function(event) {
	   if (event.keyCode == 13){
	        $('form').each(function() {       
	        	event.preventDefault();     
	        });   
	    } 
	  
	});
	
$(function(){
	for(var i = 0; i<$(".arrcity").length; i++){
		$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
	}
	
	$("#airline_arrcity").suggest(airlines,{hot_list:commonAlines,dataContainer:$(".suggest")});
	
	//单程 往返
	
	 $("#travelType").change(function(){
		if($(this).val() == '0'){
			$("#wf_div_desc").hide();
			$("#wf_div_pass").hide();
			$("#wf_div_date").hide();
			$("#wf_div_share").hide();
			$(".ac_results").remove();
			document.getElementById("sharePolicy").checked=false;
			for(var i = 0; i<$(".arrcity").length; i++){
				$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
			}
			
		}else{
			$("#wf_div_desc").show();
			$("#wf_div_pass").show();
			$("#wf_div_date").show();
			$("#wf_div_share").show();
			$(".ac_results").remove();
			document.getElementById("sharePolicy").checked=true;
			for(var i = 0; i<$(".arrcity").length; i++){
				$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
			}
		}
	}); 
	
	if($("#travelType").val() == 0){
		$("#wf_div_desc").hide();
		$("#wf_div_pass").hide();
		$("#wf_div_date").hide();
		$("#wf_div_share").hide();
	}else{
		$("#wf_div_desc").show();
		$("#wf_div_pass").show();
		$("#wf_div_date").show();
		$("#wf_div_share").show();
	}
	//匹配汉字
	 var tktAirline=$("#tktAirline").val();
	   //alert(tktAirline);
	   var arr=tktAirline.split("/");
	   var str="";
	   $.each(arr,function(i,n){
		   if(n != null && n !=''){
			  /*  $.each(commonAlines,function(j,m){
				   if()
			   }) */
			   for(h in airlines){
				   if(n == airlines[h][0]){
					   str += airlines[h][1]+"/"
					   return;
				   }
				}
		   }
	   });
	   
	   $("#tktAirline").siblings(".querycode").val(str);
	   $(".arrcity").each(function(){
		   var hid_code=$(this).siblings(".arrcity_3word").val();
		  
		   var str = "";
		   if(hid_code != null && hid_code != ''){
			   $.each(hid_code.split("/"),function(i,n){
				   if(n != null && n !=''){
					   for(h in citys){
							//alert(j);
						   if(n == citys[h][0]){
							//  alert(h);
							   str += citys[h][1]+"/"
							   return;
						   }
						}
				   }
			   });
			  // alert(hid_code);
			   $(this).val(str);
		   }
		   
	   });
	 
});

function clearNoNum(obj)
{
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	
}
function check1(){
	if(document.getElementById("childrenPoundageChoice").checked){
		document.getElementById("ac").value="Y";
	}else{
		document.getElementById("ac").value="N";
	}
}
</script>
</head>
<body>
<form action="${ctx}/policyrules/policyRules/update" method="post" id="addId">
<input type="hidden" name="token" value="${token}"/>
<div class="addpolicy">
	<img src="${ctxStatic}/images/xiudeil.png">
	<p>政策复制</p>
</div>
<!-- 总则编码 -->
<div class="query clear">
	<div class="queryinter">
		<span class="query-span interspan">政策名称：<span>∗</span></span>
		<input type="text" name="policyName" id="policyName"  value="" class="querycode" onkeyup="this.value=this.value.replace(/[^\u4E00-\u9FA5\d\A-Za-z]/g,'');"><span class="redreset">∗</span>
		<button class="addquery">添加</button>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">开票office号：</span>
		<input type="text" name="office" id="office" class="querycode" value="${tbPolicyRules.office}" onkeyup="this.value=this.value.replace(/[^\d\A-Za-z]/g,'');this.value=this.value.toLocaleUpperCase();"><span class="redreset">∗</span>
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
<%-- 			<img src="${ctxStatic}/images/interno.png" class="addimg"> --%>
			<span class="spanadd">是否换编码开票</span>
		</div>
	</div>
	<div class="block">
		<div class="effect-head clear">
			<p>基本信息：</p>
			<img src="${ctxStatic}/images/right.jpg">
		</div>
	</div>
	<div class="queryinter" style="margin-top: 20px;">
		<span class="query-span interspan">出票航司：</span>
		<div class="box">
			<input type="hidden"  name="tktAirline" id="tktAirline" class="arrcity_3word" value="${tbPolicyRules.tktAirline}"/>
			<input type="text" name="arrcity"  class="querycode " id="airline_arrcity" value="${tbPolicyRules.tktAirline}" style="width: 350px" onkeyup="this.value=this.value.replace(/[^A-Z0-9]/gi,'');this.value=this.value.toLocaleUpperCase();"/><span class="redreset">∗</span>
			<div class="ac_results"></div>
		</div>
		
	</div>
</div>
<div class="colorhei"></div>
<div class="passnger clear">
	<div class="block">
		<div class="effect-head clear">
			<p>旅客信息：</p>
			<img src="${ctxStatic}/images/right.jpg">
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
		<select name="passagerCount">
			<option <c:if test="${tbPolicyRules.passagerCount == 1}"> selected="selected" </c:if>  value="1" >1人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 2}"> selected="selected" </c:if> value="2" >2人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 3}"> selected="selected" </c:if> value="3" >3人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 4}"> selected="selected" </c:if> value="4" >4人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 5}"> selected="selected" </c:if> value="5" >5人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 6}"> selected="selected" </c:if> value="6" >6人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 7}"> selected="selected" </c:if> value="7" >7人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 8}"> selected="selected" </c:if> value="8" >8人</option>
			<option <c:if test="${tbPolicyRules.passagerCount == 9}"> selected="selected" </c:if> value="9" >9人</option>
		</select>
	</div>
</div>
<div class="colorhei"></div>
<div class="intertrip">
	<div class="block">
		<div class="effect-head clear">
			<p>行程信息：</p>
			<img src="${ctxStatic}/images/right.jpg">
		</div>
	</div>
	<div class="queryinter" style="margin-top: 20px;">
		<span class="query-span">行程类型：</span>
		<select class="codeqi" name="travelType" id="travelType">
			<option value="0" selected="selected" <c:if test="${tbPolicyRules.travelType == 0}">selected="selected"</c:if>>单程</option>
			<option value="1" <c:if test="${tbPolicyRules.travelType == 1}">selected="selected"</c:if>>往返</option>
			<option value="2"<c:if test="${tbPolicyRules.travelType == 2}">selected="selected"</c:if>>单程/往返</option>
		</select>
	</div>
	<div class="interborder">
		<p class="interhead">去程信息：</p>
		<div class="queryinter clear">
			<span class="query-span fl" style="line-height: 30px;">去程起点：</span>
			<div class="box">
				<input type="hidden" class="arrcity_3word" name="outOrg" id="outOrg" value="${tbPolicyRules.outOrg}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.outOrg}"/><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
			</div>
			
			<div class="box f1">
				<span class="fl" style="line-height: 30px;">去程起点除外：</span>
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.outOrgEx}"/>
				<div class="ac_results suggest"></div>
				<input type="hidden" name="outOrgEx" class="arrcity_3word" value="${tbPolicyRules.outOrgEx}"/>
		</div>
		</div>
		<div class="queryinter clear">
			<span class="query-span interblock">去程终点：<br />(折返点)</span>
			<div class="box f1">
				<input type="hidden" name="outDes" ID="outDes" class="arrcity_3word" value="${tbPolicyRules.outDes}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.outDes}" /><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
		</div>
			
			<div class="box f1">
			<span class="interblock">去程终点除外：<br />(折返点除外)</span>
				<input type="hidden" name="outDesEx" id="outDesEx"  class="arrcity_3word" value="${tbPolicyRules.outDesEx}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.outDesEx}"/>
				<div class="ac_results suggest"></div>
		</div>
		</div>
	</div>
	<div class="interborder"  style="display: none;" id="wf_div_desc">
		<p class="interhead">回程信息：</p>
		<div class="queryinter clear">
			<span class="query-span">回程终点：</span>
			<div class="box f1">
				<input type="hidden"  name="returnDes" id="returnDes" class="arrcity_3word" value="${tbPolicyRules.returnDes}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.returnDes}"/><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
		</div>
			<span>回程终点除外：</span>
			<div class="box f1">
				<input type="hidden" name="returnDesEx"  class="arrcity_3word" value="${tbPolicyRules.returnDesEx}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.returnDesEx}"/>
				<div class="ac_results suggest"></div>
		</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">中转点信息：</p>
		<div class="queryinter clear">
			<span class="query-span">去程不能经过：</span>
			<div class="box f1">
				<input type="hidden" name="outUnablePass"  class="arrcity_3word" value="${tbPolicyRules.outUnablePass}" />
				<input type="text"  class="querycode arrcity"  value="${tbPolicyRules.outUnablePass}"/>
				<div class="ac_results suggest"></div>
			</div>
			<span>去程必须经过：</span>
			<div class="box f1">
				<input type="hidden" name="outMustPass" class="arrcity_3word" value="${tbPolicyRules.outMustPass}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.outMustPass}"/>
				<div class="ac_results suggest"></div>
		</div>
		</div>
		<div class="queryinter clear" style="display: none;" id="wf_div_pass">
			<span class="query-span">回程不能经过：</span>
			<div class="box f1">
				<input type="hidden" name="returnUnablePass" class="arrcity_3word" value="${tbPolicyRules.returnUnablePass}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.returnUnablePass}"/>
				<div class="ac_results suggest"></div>
		</div>
			<span>回城必须经过：</span>
			<div class="box f1">
				<input type="hidden" name="returnMustPass" class="arrcity_3word" value="${tbPolicyRules.returnMustPass}" />
				<input type="text"  class="querycode arrcity" value="${tbPolicyRules.returnMustPass}"/>
				<div class="ac_results suggest"></div>
		</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">航班限制：</p>
		<div class="queryinter clear">
			<span class="query-span">排除航班号：</span>
			<input type="text" name="excludeFilghtNo" class="querycode" value="${tbPolicyRules.excludeFilghtNo}" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();" /> 
			<span>仅限航班号：</span>
			<input type="text" name="allowFilghtNo" class="querycode" value="${tbPolicyRules.allowFilghtNo}" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();" />
		</div>
	</div>
	<div class="interborder">	
		<div class="marbor">
			<span class="interhead">舱位信息：</span>
			<input type="text" id="cabin" name="cabin" class="querycode" value="${tbPolicyRules.cabin}" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();"  style="width: 350px"/>
		</div>
		<p class="interhead">成人：</p>
		<div class="queryinter clear">
			<span class="query-span">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：</span>
			<input type="text"　id="rebate" name="rebate" class="querycode" value="${tbPolicyRules.rebate}" onkeyup="clearNoNum(this)" /><span class="redreset">∗</span>
			<span>开票费：</span>
			<input type="text" name="billingFee" class="querycode" value="${tbPolicyRules.billingFee}" onkeyup="clearNoNum(this)" />
		</div>
		<div class="queryinter clear" style="display: none;">
			<span class="query-span">特殊代理：</span>
			<input type="text" name="agencyFee" value="${tbPolicyRules.agencyFee}" class="querycode" style="margin-right: 0;" onkeyup="clearNoNum(this)" />
			<span class="redreset">&lowast;如不填写特殊代理费，则由平台的代理费为准</span>
		</div>
		<div class="interno" style="margin-left: 20px;display: none;" id="wf_div_share">
		<input type="checkbox" id="sharePolicy" <c:if test="${tbPolicyRules.sharePolicy == 'Y'}">checked="checked"</c:if> class="checkeffect">
		<input type="hidden" value="${tbPolicyRules.sharePolicy}">
<%-- 			<img src="${ctxStatic}/images/interno.png" class="addimg"> --%>
			<span class="spanadd">是否允许与其他政策组合</span>
		</div>
		<p class="interhead">儿童：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" <c:if test="${tbPolicyRules.childrenReward == 01}">checked="checked"</c:if> name="childrenReward"  value="01" onclick="pointreward();">
<%-- 					<img src="${ctxStatic}/images/radio1.png"> --%>
					<span>与成人一致</span>
				</label>
				<label class="checkradio">
				<input type="radio" <c:if test="${tbPolicyRules.childrenReward == 02}">checked="checked"</c:if> name="childrenReward" value="02" onclick="pointreward();">
<%-- 					<img src="${ctxStatic}/images/radio2.png"> --%>
					<span>可开无后返</span>
				</label>
				<label class="checkradio">
				<input type="radio" <c:if test="${tbPolicyRules.childrenReward == 03}">checked="checked"</c:if> name="childrenReward" value="03" onclick="pointreward();">
<%-- 					<img src="${ctxStatic}/images/radio2.png"> --%>
					<span>不可开</span>
				</label>
				<label class="checkradio">
				<input type="radio" <c:if test="${tbPolicyRules.childrenReward == 04}">checked="checked"</c:if> name="childrenReward" value="04" onclick="pointreward();">
<%-- 					<img src="${ctxStatic}/images/radio2.png"> --%>
					<span>指定奖励</span>
				</label>
				<span>返点：</span>
				<input type="text" name="childrenRebate" id="childrenRebate" class="querycode" value="${tbPolicyRules.childrenRebate}" onkeyup="clearNoNum(this)" disabled="disabled"/>
			</div>
			<div class="interdio">
				<label>
					<input type="checkbox" id="childrenPoundageChoice" name="childrenPoundageChoice2" <c:if test="${tbPolicyRules.childrenPoundageChoice == 'Y'}">checked="checked"</c:if> value="Y" class="checkeffect" onclick="check1();">
					<input type="hidden" id="ac" name="childrenPoundageChoice" value="${tbPolicyRules.childrenPoundageChoice}"/>
					<span class="marginri">加手续费<input type="text"  class="textcheck" id="childrenPoundage" value="${tbPolicyRules.childrenPoundage}" name="childrenPoundage" onkeyup="clearNoNum(this)" />元</span>
				</label>
				<label>
					<input type="checkbox" id="effectil" name="childrenOpenNoCom"  value="Y" <c:if test="${tbPolicyRules.childrenOpenNoCom == 'Y'}">checked="checked"</c:if>  value="0" class="checkeffect">
					<span class="marginri">可开无代理费</span>
				</label>
			</div>
		</div>
		<p class="interhead">婴儿：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" name="badyReward" value="0" <c:if test="${tbPolicyRules.badyReward == 0}">checked="checked"</c:if>/>
<%-- 					<img src="${ctxStatic}/images/radio1.png"> --%>
					<span>可开无奖励</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="badyReward" value="1" <c:if test="${tbPolicyRules.badyReward == 1}">checked="checked"</c:if>/>
<%-- 					<img src="${ctxStatic}/images/radio2.png"> --%>
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
		<span>去程旅行日期：<span class="redreset">∗</span></span>
		<input type="text"   id="minTime" value="${tbPolicyRules.firstDateStartStr}" name="firstDateStart"  class="doubledate ipticon time">
		<span>至</span>
		<input type="text" id="maxTime" value="${tbPolicyRules.firstDateEndStr}"  name="firstDateEnd" class="doubledate ipticon time">
	</div>
	<div class="queryinter interleft" style="display: none;" id="wf_div_date">
		<span>返程旅行日期：<span class="redreset">∗</span></span>
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
			<img src="${ctxStatic}/images/right.jpg">
		</div>
	</div>
	<div class="marbor clear">
		<span class="interhead fl interban">航班限制：</span>
		<textarea class="interarae fl" name="policyRemark">${tbPolicyRules.policyRemark}</textarea>
	</div>
	<div class="interno" style="margin-top: 10px;">
	<input type="checkbox" id="effectil" checked="checked" class="checkeffect" name="isPolicyStatus" value="Y" <c:if test="${tbPolicyRules.policyStatus == 2}">checked="checked"</c:if>>
<%-- 		<img src="${ctxStatic}/images/interno.png" class="addimg"> --%>
		<span class="spanadd">是否换编码开票</span>
	</div>
	</div>
	<div class="clear">
		<div class="button-detail fl" style="margin-left: 148px;" onclick="add()">
			<img src="${ctxStatic}/images/xiudeil.png">
			<p><input type="button" style="background:#c41213;border:0;float:left;margin-top:7px; marfont-size:16px;color:#FFF;" value="添加"/></p>
		</div>
		
		
		<div class="button-detail fl" style="margin-left: 0;" onclick="back()">
			<img src="${ctxStatic}/images/copy.png">
			<p>返回</p>
		</div>
		
	</div>
</div>
</form>
<script type="text/javascript">
function back(){
	
	window.history.back(-1);
}

</script>
</body>
</html>