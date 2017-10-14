<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际政策查询</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/query.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/common/pading.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
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
</style>
<script type="text/javascript">
$(function(){
	$("#minTime").datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 1,
	      duration:'normal',
		  showButtonPanel:true,//是否显示清空按钮
		  closeText:"",
		  beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate) {
	      	objval = selectedDate;
	      	if(selectedDate == ''){
	      		$("#maxTime").datepicker( "option", "minDate", '0');
	      	}else{
	      		$("#maxTime").datepicker( "option", "minDate", selectedDate);
	      	}
	      }
	    });
	    $("#maxTime").datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 1,
	      showButtonPanel:true,//是否显示清空按钮
	      closeText:"",
	      beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate){
	      	objval = selectedDate;
	        if(selectedDate == ''){
	      		$(start).datepicker( "option", "maxDate", null);
	      	}else{
	      		$(start).datepicker( "option", "maxDate", selectedDate);
	      	}
	      }
	    });
	    $(".ui-datepicker-close").live("click",function(){
	    	objclear.value = "";
			   var dates = $(start,end);
			  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
			   dates.datepicker("option", "minDate", '0');
			   dates.datepicker("option", "maxDate", null);
	    })
	    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
	    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
	    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
	    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
})

$(function(){
	for(var i = 0; i<$(".arrcity").length; i++){
		$(".arrcity").eq(i).suggest(citys,{hot_list:commoncitys,dataContainer:$(".suggest").eq(i)});
	}
	
	$("#airline_arrcity").suggest(airlines,{hot_list:commonAlines,dataContainer:$(".suggest")});
	
});
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	var v_minDate = $("#minDate").val();
	var v_maxDate = $("#maxDate").val();
	var v_minDate1 = $("#minTime").val();
	var v_maxDate1= $("#maxTime").val();
	var v_policy_name = $("#policy_name_id").val();
	var v_supplierId = $("#supplierId").val();
	var v_policyStatusId = $("#policyStatusId").val();
	var pageNo = $("#pageNo").val();
	var pageSize = $("#pageSize").val();
	var tktAirline=$("#tktAirline").val();
	if(tktAirline == "中文/拼音"){
		tktAirline = null;
	}
	$.post(
		"${ctx}/policyrules/policyRules/list",
		{"policyName":v_policy_name,
		"tktTimeStart":v_minDate,
		"tktTimeEnd":v_maxDate,
		"workTime":v_minDate1,
		"endTime":v_maxDate1,
		"supplierId":v_supplierId,
		"policyStatus":v_policyStatusId,
		"pageNo":pageNo,
		"pageSize":pageSize,
		"tktAirline":tktAirline,
			"flag":1
			},
		function(date){
			$("#serchId").html(date);
		}
	);
}
$(document).keydown(function(event) {
	   if (event.keyCode == 13){
	        $('form').each(function() {       
	        	event.preventDefault();     
	        });   
	    } 
	});

// var airCode = "";
function checkAirport(){
// 	var isTrue = true;
	var codeBrr = $("#arrcity_3word").val();
// 	alert(codeBrr1);
// 	var airCodeArr = airCode.split("/");
// 	for(var i=0;i<airCodeArr.length;i++){
// 		if(airCodeArr[i] == codeBrr){
// 			alert("已选过本航司");
// 			isTrue = false;
// 		}
// 	}
// 	alert(codeBrr);
		$("#arrcity").val(codeBrr);
// 	$.post(
// 			"${ctx}/generalrules/generalRules/checkAirline",
// 			{
// 			"codeBrr":codeBrr
// 			},
// 			function(date){
// 				alert(date);
// 				if(date == false){
// 					alert("没有此航班");
// 				}else{
// 					if(isTrue == true){
// 						airCode = codeBrr;
// 						if("" != codeBrr){
// 							$("#arrcity").val(codeBrr);
// 						}
// 					}
// 				}
// 			}
// 		);
}


</script>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/general/images/query.png">
	<p>政策查询</p>
</div>

<!-- 总则编码 -->
<div class="query">
	<div class="queryinter">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<span class="query-span interspan">政策名称：</span>
		<input type="text" id="policy_name_id" name="querycode" class="querycode">
		<span style="margin-left: 9px">出票航司：</span>
		<div class="box">
			<input type="hidden"  name="tktAirline" id="tktAirline" class="arrcity_3word" />
			<input type="text" name="arrcity"  class="querycode " id="airline_arrcity" style="width: 350px" onkeyup="this.value=this.value.replace(/[^A-Z0-9]/gi,'');this.value=this.value.toLocaleUpperCase();"/>
			<div class="ac_results"></div>
		</div>
		<!--  
		<span>出票日期：</span>
		<input type="text" id="minDate"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate\')}',dateFmt:'yyyy-MM-dd'})" />
		<span>至</span>
		<input type="text" id="maxDate"  class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate\')}',dateFmt:'yyyy-MM-dd'})" /> -->
	</div>
	<div class="queryinter">
		<span class="query-span interspan">去程起点：</span>
		<div class="box">
				<input type="hidden" class="arrcity_3word" name="tktAirline" id="tktAirline" value="" />
				<input type="text"  class="querycode arrcity" /><span class="redreset">∗</span>
				<div class="ac_results suggest"></div>
		</div>
		<span>创建日期：</span>
		<input type="text"  id="minTime" name="firstDateStart"  class="doubledate ipticon time" placeholder="2016-04-01"> 
		<span>至</span>
		<input type="text" id="maxTime" name="firstDateEnd" class="doubledate ipticon time" placeholder="2016-05-31">
	</div>
<!-- 	<div class="queryinter"> -->
<!-- 		<span class="query-span interspan">投放分销商：</span> -->
<!-- 		<input type="text" id="supplierId" name="querycode" class="querycode"> -->
<!-- 	</div> -->
	<div class="queryinter" >
		<span class="query-span interspan">状态信息：</span>
		<select class="codeqi" id="policyStatusId">
			<option value="">请选择</option>
			<option value="0">启用</option>
			<option value="1">禁用</option>
			<option value="2">挂起</option>
		</select>
		<button class="addquery" onclick="page()">查询</button>
		<button class="addquery" onclick="addPolicy()">添加</button>
	</div>
</div>
<div class="colorhei"></div>
<div class="codetable" id="serchId">
<jsp:include page="/WEB-INF/views/sz/policy/policyListPage.jsp"></jsp:include>
</div>
	





<script type="text/javascript">

function addPolicy(){
	window.location.href="${ctx}/policyrules/policyRules/toSave";
}

function detalisPolicy(id){
	window.location.href="${ctx}/policyrules/policyRules/toDetails?id="+id;
	
}

function toUpdatePolicy(id){
	window.location.href="${ctx}/policyrules/policyRules/toUpdate?id="+id;
	
}

function deletePolicy(id){
	$.post(
			"${ctx}/policyrules/policyRules/delete",
			{"id":id,
				},
			function(date){
					page();
			}
		);
}

</script>
</body>

</html>