<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际政策添加</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/query.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/pading.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/dateControl.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script> --%>
<%-- <script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script> --%>

<!-- 时间控件的  -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery/jquery-ui.min.css"/>
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
.querycode{
margin-right: 0px
}
.redreset{
margin-right: 30px
}
</style>
<script type="text/javascript">
/* $(function(){
dateControl('#minTime','#maxTime')
dateControl('#minData','#maxData')
}) */
$(function(){
	dateControl('#minTime','#maxTime');
	dateControl('#minData','#maxData');
})

function add(){
	/*  $.post(
			"${ctx}/policyrules/policyRules/aviation",
			{},
			function(date){
				
			}
		);  */
		//alert($("#outOrg").val()); 
	if($("#checkPolicyName").val() == ''){
		alert("政策名称不能为空");
	}else if($("#office").val() == ''){
		alert("开票office不能为空");
	}else if($("#office").val().length>8){
		alert("开票office长度不要超过8");
	}else if($("#outOrg").val() == ''){
		alert("启程起点不能为空");
	}else if($("#outDes").val() == ''){
		alert("启程终点不能为空");
	}else if($("#cabin").val() == ''){
		alert("舱位信息不能为空");
	}else if($("#tktAirline").val() == ''){
		alert("出票航司不能为空");
	}else if($("#travelType").val() == '1' && $("#returnDes").val() == ''){
		alert("回程终点不能为空");
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
}
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
		break;
		}
	 }
	 return value;
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
	
	 $("#travelType").change(function(){
		if($(this).val() == '0'){
			$("#wf_div_desc").hide();
			$("#wf_div_pass").hide();
			$("#wf_div_date").hide();
			$("#wf_div_share").hide();
			$(".ac_results").remove();
			for(var i = 0; i<$(".arrcity").length; i++){
				$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
			}
			
		}else{
			$("#wf_div_desc").show();
			$("#wf_div_pass").show();
			$("#wf_div_date").show();
			$("#wf_div_share").show();
			$(".ac_results").remove();
			for(var i = 0; i<$(".arrcity").length; i++){
				$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
			}
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

</script>
</head>
<body>
<form id="addId" action="${ctx}/policyrules/policyRules/save" method="post">
<input type="hidden" name="token" value="${token}"/>
<div class="addpolicy">
	<img src="${ctxStatic}/general/images/addplo.png">
	<p>政策添加</p>
</div>
<!-- 总则编码 -->
<div class="query clear"> 
	<div class="queryinter">
		<span class="query-span interspan">政策名称：</span>
		<input type="text" name="policyName" id="checkPolicyName" class="querycode" onkeyup="this.value=this.value.replace(/[^\u4E00-\u9FA5\d\A-Za-z]/g,'');"><span class="redreset">∗</span>
		<a href="javascrpt:void(0);" onclick="add()"><button class="addquery" type="button" >添加</button></a>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">开票office号：</span>
		<input type="text" name="office" ID="office" class="querycode" onkeyup="this.value=this.value.replace(/[^\d\A-Za-z]/g,'');this.value=this.value.toLocaleUpperCase();"><span class="redreset">∗</span>
		<span>状态：</span>
		<select class="codeqi" name="policyStatus">
			<option value="0">启用</option>
			<option value="1">禁用</option>
			<option value="2">挂起</option>
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
			<input type="hidden"  name="tktAirline" id="tktAirline" class="arrcity_3word" />
			<input type="text" name="arrcity"  class="querycode " id="airline_arrcity" style="width: 350px" onkeyup="this.value=this.value.replace(/[^A-Z0-9]/gi,'');this.value=this.value.toLocaleUpperCase();"/><span class="redreset">∗</span>
			<div class="ac_results"></div>
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
			<input type="checkbox" id="effectil" checked="checked" class="checkeffect" name="passagerPid" value="0">
			<span class="marginri">普通</span>
		</label>
		<label>
			<input type="checkbox" id="effectil" class="checkeffect" name="passagerPid" value="1">
			<span class="marginri">移民</span>
		</label>
		<label>
			<input type="checkbox" id="effectil" class="checkeffect" name="passagerPid" value="2">
			<span class="marginri">留学生</span>
		</label>
		<label>
			<input type="checkbox" id="effectil" class="checkeffect" name="passagerPid" value="3">
			<span class="marginri">劳工</span>
		</label>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">旅游下线人数：</span>
		<select name="passagerCount">
			<option value="1" >1人</option>
			<option value="2" >2人</option>
			<option value="3" >3人</option>
			<option value="4" >4人</option>
			<option value="5" >5人</option>
			<option value="6" >6人</option>
			<option value="7" >7人</option>
			<option value="8" >8人</option>
			<option value="9" >9人</option>
		</select>
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
		<select class="codeqi" name="travelType" id="travelType">
			<option value="0" selected="selected">单程</option>
			<option value="1">往返</option>
			<option value="2">单程/往返</option>
		</select>
	</div>
	<div class="interborder">
		<p class="interhead">去程信息：</p>
		<div class="queryinter clear">
			<span class="query-span fl" style="line-height: 30px;">去程起点：</span>
			<div class="box">
				<input type="hidden" class="arrcity_3word" name="outOrg" id="outOrg" value="" />
				<input type="text"  class="querycode arrcity" /><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
			</div>
			<div class="box f1">
				<span class="fl" style="line-height: 30px;">去程起点除外：</span>
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				<input type="hidden" name="outOrgEx" class="arrcity_3word" />
			</div>
		</div>
		<div class="queryinter clear">
			<span class="query-span interblock">去程终点：<br />(折返点)</span>
			<div class="box f1">
				<input type="hidden" name="outDes" class="arrcity_3word" id="outDes" value="" />
				<input type="text"  class="querycode arrcity" /><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
				
			</div>
			
			<div class="box f1">
			<span class="interblock">去程终点除外：<br />(折返点除外)</span>
				<input type="hidden" name="outDesEx" id="outDesEx"  class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				
			</div>
		</div>
	</div>
	<div class="interborder" style="display: none;" id="wf_div_desc">
		<p class="interhead">回程信息：</p>
		<div class="queryinter clear">
			<span class="query-span">回程终点：</span>
			<div class="box f1">
				<input type="hidden"  name="returnDes" id="returnDes" class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" /><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
				
			</div>
			<span>回程终点除外：</span>
			<div class="box f1">
				<input type="hidden" name="returnDesEx"  class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				
			</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">中转点信息：</p>
		<div class="queryinter clear">
			<span class="query-span">去程不能经过：</span>
			<div class="box f1">
				<input type="hidden" name="outUnablePass"  class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				
			</div>
			<span>去程必须经过：</span>
			<div class="box f1">
				
				<input type="hidden" name="outMustPass" class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
			</div>
		</div>
		<div class="queryinter clear" style="display: none;" id="wf_div_pass">
			<span class="query-span">回程不能经过：</span>
			<div class="box f1">
				<input type="hidden" name="returnUnablePass" class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				
			</div>
			<span>回城必须经过：</span>
			<div class="box f1">
				<input type="hidden" name="returnMustPass" class="arrcity_3word" value="" />
				<input type="text"  class="querycode arrcity" />
				<div class="ac_results suggest"></div>
				
			</div>
		</div>
	</div>
	<div class="interborder">
		<p class="interhead">航班限制(多个用/分割)：</p>
		<div class="queryinter clear">
			<span class="query-span">排除航班号：</span>
			<input type="text"  name="excludeFilghtNo" class="querycode" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();" />
			<span>仅限航班号：</span>
			<input type="text"  name="allowFilghtNo" class="querycode" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();" />
		</div>
	</div>
	<div class="interborder">	
		<div class="marbor">
			<span class="interhead">舱位信息(多个用/分割)：</span>
			<input type="text" id="cabin" name="cabin" class="querycode" onkeyup="value=value.replace(/[^\w=/]|_/ig,'');this.value=this.value.toLocaleUpperCase();"  style="width: 350px"/>
			<span class="redreset">∗</span>
		</div>
		<p class="interhead">成人：</p>
		<div class="queryinter clear">
			<span class="query-span">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：</span>
			<input type="text" name="rebate" id="rebate" class="querycode" onkeyup="clearNoNum(this)"/><span class="redreset">∗</span>
			<span>开票费：</span>
			<input type="text" name="billingFee" id="billingFee" class="querycode" onkeyup="clearNoNum(this)" value="0.00"/>
		</div>
		<div class="queryinter clear" style="display: none;">
			<span class="query-span">特殊代理：</span>
			<input type="text" name="agencyFee" id="agencyFee" class="querycode" style="margin-right: 0;" onkeyup="clearNoNum(this)" />
			<span class="redreset">&lowast;如不填写特殊代理费，则由平台的代理费为准</span>
		</div>
		<div class="interno" style="margin-left: 20px;display: none;" id="wf_div_share">
		<input type="checkbox" id="sharePolicy" checked="checked" name="sharePolicy" class="checkeffect" value="Y">
<%-- 			<img src="<%=request.getContextPath()%>/images/interno.png" class="addimg"> --%>
			<span class="spanadd">是否允许与其他政策组合</span>
		</div>
		<p class="interhead">儿童：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" checked="checked" name="childrenReward" value="01" onclick="pointreward();">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>与成人一致</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="childrenReward" value="02" onclick="pointreward();">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>可开无后返</span>
				</label>
				<label class="checkradio">
				<input type="radio"  name="childrenReward" value="03" onclick="pointreward();">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>不可开</span>
				</label>
				<label class="checkradio">
				<input type="radio"  name="childrenReward" value="04" onclick="pointreward();">
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>指定奖励</span>
				</label>
				<span>返点：</span>
				<!-- <input type="text" name="childrenRebate" class="querycode" id="fandian" onclick="pointreward();" readonly="readonly"> -->
				<input type="text" name="childrenRebate" id="childrenRebate" class="querycode" disabled="disabled" onkeyup="clearNoNum(this)" >
			<div class="interdio">
				<label>
					<input type="checkbox" id="effectil" name="childrenPoundageChoice" value="Y" class="checkeffect">
					<span class="marginri">加手续费<input type="text" value="" class="textcheck" name="childrenPoundage" onkeyup="clearNoNum(this)" />元</span>
				</label>
				<label>
					<input type="checkbox" id="effectil" name="childrenOpenNoCom"  value="0" class="checkeffect">
					<span class="marginri">可开无代理费</span>
				</label>
			</div>
		</div>
		<p class="interhead">婴儿：</p>
		<div class="queryinter clear">
			<div class="interdio">
				<label class="checkradio">
				<input type="radio" checked="checked" name="badyReward" value="0"/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>可开无奖励</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="badyReward" value="1"/>
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
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="queryinter interleft">
	
	<span>去程旅行日期：<span class="redreset">∗</span></span>
		<input type="text"  id="minTime" name="firstDateStart"  class="doubledate ipticon time" placeholder="2016-04-01"> 
		<span>至</span>
		<input type="text" id="maxTime" name="firstDateEnd" class="doubledate ipticon time" placeholder="2016-05-31">
	</div>
	<div class="queryinter interleft" style="display: none;" id="wf_div_date">
		<span>返程旅行日期：<span class="redreset">∗</span></span>
		<input type="text" id="minData" name="lastDateStart"  class="doubledate ipticon time" placeholder="2016-04-01">
		<span>至</span>
		<input type="text" id="maxData" name="lastDateEnd" class="doubledate ipticon time" placeholder="2016-05-31">
		
		
<!-- 		<input type="text" id="minDate1" name="lastDateStart" class="Wdate querycode" style="margin-right: 0;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate1\')}',dateFmt:'yyyy-MM-dd'})" /> -->
<!-- 		<span style="margin:0 4px;">至</span> -->
<!-- 		<input type="text" id="maxDate1" name="lastDateEnd" class="Wdate querycode" style="margin-right: 0;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate1\')}',dateFmt:'yyyy-MM-dd'})" />   -->
	</div>
</div>
<div class="colorhei"></div>
<div>
	<div class="block">
		<div class="effect-head clear" style="margin-top: 20px;">
			<p>备注信息：</p>
			<img src="${ctxStatic}/general/images/right.jpg">
		</div>
	</div>
	<div class="marbor clear">
		<span class="interhead fl interban">航班限制：</span>
		<textarea class="interarae fl" name="policyRemark"></textarea>
	</div>
	<div class="interno" style="margin-top: 10px;">
	<input type="checkbox" id="effectil" checked="checked" class="checkeffect" name="isPolicyStatus" value="2">
<%-- 		<img src="<%=request.getContextPath()%>/images/interno.png" class="addimg"> --%>
		<span class="spanadd">是否换编码开票</span>
	</div>
	<div class="clear">
		<div class="button-detail fl" onclick="add()">
			<img src="${ctxStatic}/general/images/addplo.png">
			<p><input type="button"  style="background:#c41213;border:0;float:left;margin-top:7px; marfont-size:16px;color:#FFF;" value="添加"/></p>
		</div>
	</div>
	
</div>
</form>

</body>
</html>