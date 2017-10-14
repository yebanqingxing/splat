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
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/pading.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<style type="text/css">
#box{text-align:left;margin:0 auto; }
#suggest,#suggest2{width:600px; height:300px; overflow-y:scroll;}
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
			"${ctx}/policyrules/policyRules/aviation",
			{},
			function(date){
				var commoncitys=new Array();
				commoncitys = date;
// 				var commoncitys = eval("("+date+")") ;

	$("#arrcity").suggest(commoncitys,{hot_list:commoncitys,dataContainer:'#arrcity_3word',onSelect:function(){$("#city2").click();},attachObject:'#suggest'});
			}
		);
	// $("#city2").suggest(citys,{hot_list:commoncitys,attachObject:"#suggest2"});
	
});


function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	var v_minDate = $("#minDate").val();
	var v_maxDate = $("#maxDate").val();
	var v_minDate1 = $("#minDate1").val();
	var v_maxDate1= $("#maxDate1").val();
	var v_policy_name = $("#policy_name_id").val();
	var v_supplierId = $("#supplierId").val();
	var v_policyStatusId = $("#policyStatusId").val();
	var pageNo = $("#pageNo").val();
	var pageSize = $("#pageSize").val();
	var v_outOrg=$("#arrcity").val();
	if(v_outOrg == "中文/拼音"){
		v_outOrg = null;
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
		"outOrg":v_outOrg,
			"flag":1
			},
		function(date){
			$("#serchId").html(date);
		}
	);
}

var airCode = "";
function checkAirport(){
// 	var isTrue = true;
	var codeBrr = $("#arrcity_3word").val();
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
		<span>出票日期：</span>
		<input type="text" id="minDate"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		<span>至</span>
		<input type="text" id="maxDate"  class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
	</div>
	<div class="queryinter">
		<span class="query-span interspan">去程起点：</span>
		<div class="box">
			<input type="hidden" name="arrcity_3word" id="arrcity_3word" />
			<input type="text" name="arrcity" id="arrcity" class="querycode" />
			<div id='suggest' class="ac_results"></div>
		</div>
		<span>创建日期：</span>
		<input type="text" id="minDate1"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		<span>至</span>
		<input type="text" id="maxDate1"  class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
	</div>
	<div class="queryinter">
		<span class="query-span interspan">投放分销商：</span>
		<input type="text" id="supplierId" name="querycode" class="querycode">
	</div>
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