<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>总则管理</title>
	<meta name="decorator" content="default"/>
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
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/common/pading.css">
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script>
<script type="text/javascript">


		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			var pageNo = $("#pageNo").val();
			var pageSize = $("#pageSize").val();
			var v_airCode = $("#airCode").val();
			if(v_airCode == "中文/拼音"){
				v_airCode="";
			}
// 			var v_grId = $("#grId").val();
			$.post(
				"${ctx}/generalrules/generalRules/list",
				{"airCode":v_airCode,
				"pageNo":pageNo,
				"pageSize":pageSize,
// 				"grId":v_grId,
				"flag":1},
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
		
		$(function(){
			
			for(var i = 0; i<$(".arrcity").length; i++){
				$(".arrcity").eq(i).suggest(airlines,{hot_list:commonAlines,dataContainer:$(".suggest").eq(i)});
			}
		});
	</script>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/general/images/query.png">
	<p>总则查询</p>
</div>
<%-- <form:form id="searchForm" modelAttribute="generalRules" action="${ctx}/generalrules/generalRules/" method="post" class="breadcrumb form-search"> --%>
<div class="query">
<input id="pageNo" name="pageNo" type="hidden"  value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 	<span class="query-span">总则编码：</span> -->
<!-- 	<input type="text" name="querycode" id="grId" class="querycode"> -->
<label for="arrcity" style="margin-left:50px;">航司二字码：</label>
			
		<div class="box">
			<input type="hidden"  name="airCode" id="airCode" class="arrcity_3word" />
			<input type="text" name="arrcity"  class="querycode arrcity" id="airline_arrcity" style="width: 350px;margin-top: 9px" onkeyup="this.value=this.value.replace(/[^A-Z0-9]/gi,'');this.value=this.value.toLocaleUpperCase();"/><span class="redreset">∗</span>
			<div class="ac_results"></div>
		</div>
			
	<button class="addquery" onclick="page()">查询</button>
	<button class="addquery" onclick="addGeneral()">添加</button>
</div>
<%-- </form:form> --%>
<div class="colorhei"></div>
  <sys:message content="${message}"/>
	<div class="codetable" id="generalId">
		<jsp:include page="/WEB-INF/views/sz/generalrules/generalRulesListPage.jsp"></jsp:include>
	</div>
	
</body>

</html>