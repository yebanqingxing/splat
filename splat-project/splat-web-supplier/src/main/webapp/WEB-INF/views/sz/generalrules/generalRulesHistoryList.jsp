<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总则管理</title>
	<meta name="decorator" content="default"/>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/general/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/general/css/query.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/general/css/pading.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/j.suggest.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/j.dimensions.js"></script>
<script type="text/javascript">
$(function(){
	$.post(
			"${ctx}/generalrules/generalRules/aviation",
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
			var pageNo = $("#pageNo").val();
			var pageSize = $("#pageSize").val();
			var v_airCode = $("#arrcity").val();
			if(v_airCode == "中文/拼音"){
				v_airCode="";
			}
			var v_grId = $("#grId").val();
			$.post(
				"${ctx}/generalrules/generalRules/historyList",
				{"airCode":v_airCode,
					"pageNo":pageNo,
					"pageSize":pageSize,
				"grId":v_grId,
				"flag":1
					},
				function(date){
					$("#generalId").html(date);
				}
			);
        }
		
		function addGeneral(){
			window.location.href="${ctx}/generalrules/generalRules/toSave";
		}

		function toEdit(id){
			window.location.href="${ctx}/generalrules/generalRules/toEdit?grId="+id;
		}
		
		function toDetails(id){
			window.location.href="${ctx}/generalrules/generalRules/toDetails?grId="+id;
		}
		
		
		
		function deleteGeneralRules(id){
			$.post(
					"${ctx}/generalrules/generalRules/delete",
					{"grId":id,
						},
					function(date){
							page();
					}
				);
		}
		
		
// 		var airCode = "";
		function checkAirport(){
// 			var isTrue = true;
			var codeBrr = $("#arrcity_3word").val();
// 			var airCodeArr = airCode.split("/");
// 			for(var i=0;i<airCodeArr.length;i++){
// 				if(airCodeArr[i] == codeBrr){
// 					alert("已选过本航司");
// 					isTrue = false;
// 				}
// 			}
// 			$.post(
// 					"${ctx}/generalrules/generalRules/checkAirline",
// 					{
// 					"codeBrr":codeBrr
// 					},
// 					function(date){
// 						if(date == false){
// 							alert("没有此航班");
// 						}else{
// 							if(isTrue == true){
// 								airCode += codeBrr+"/";
// 								if("" != codeBrr){
									$("#arrcity").val(codeBrr);
// 								}
// 							}
// 						}
// 					}
// 				);
		}
	</script>
</head>
<body>
<div class="addpolicy">
	<img src="<%=request.getContextPath()%>/static/general/images/query.png">
	<p>总则历史查询</p>
</div>
<div class="query">
<input id="pageNo" name="pageNo" type="hidden"  value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<span class="query-span">总则编码：</span>
	<input type="text" value="" name="querycode" id="grId" class="querycode">
	<label for="arrcity">航班二字码：</label>
	<input type="hidden" name="arrcity_3word" id="arrcity_3word" value="" />
	<input type="text" name="airCode" id="arrcity" class="querycode"/>
	<div id='suggest' class="ac_results"></div>
	<button class="addquery" onclick="page()">查询</button>
	<button class="addquery" onclick="addGeneral()">添加</button>
</div>
<div class="colorhei"></div>
  <sys:message content="${message}"/>
	<div class="codetable" id="generalId">
		<jsp:include page="/WEB-INF/views/sz/generalrules/generalRulesListPage.jsp"></jsp:include>
	</div>
	
</body>
</html>