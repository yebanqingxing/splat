<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>总则管理</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/policy.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/addpolicy.js"></script>

<script type="text/javascript" src="${ctxStatic}/js/aircity.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/j.dimensions.js"></script>
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

<script type="text/javascript">
$(function(){
	
	for(var i = 0; i<$(".arrcity").length; i++){
		$(".arrcity").eq(i).suggest(airlines,{hot_list:commonAlines,dataContainer:$(".suggest").eq(i)});
	}
});
$(document).keydown(function(event) {
	   if (event.keyCode == 13){
	        $('form').each(function() {       
	        	event.preventDefault();     
	        });   
	    } 
	});
</script>
	
</head>
<body>
<%-- <input type="hidden" id="countryArrId" value="${countryArr}"/> --%>
	<form:form id="inputForm" modelAttribute="generalRules" action="${ctx}/generalrules/generalRules/save" method="post" class="form-horizontal">
		<input type="hidden" name="token" value="${token}"/>
		<div class="addpolicy">
	<img src="${ctxStatic}/general/images/addplo.png">
	<p>添加总则</p>
</div>
<!-- 作用域 -->
<div class="policy-effect clear">
	<div class="effect-head clear">
		<p>作用域：</p>
		<img src="${ctxStatic}/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
	<form action="${ctx}/generalrules/generalRules/save" method="post">
		<div class="effect-content1" id="box">
<!-- 			<span>航班二字码：</span> -->
		
		<label for="arrcity">航司二字码：</label>
		<div class="box">
			<input type="hidden"  name="airCode" id="arrcity" class="arrcity_3word" />
			<input type="text" name="arrcity"  class="querycode arrcity" id="airline_arrcity" style="width: 350px" onkeyup="this.value=this.value.replace(/[^A-Z0-9]/gi,'');this.value=this.value.toLocaleUpperCase();"/><span class="redreset">∗</span>
			<div class="ac_results"></div>
		</div>
		
<!-- 			<input type="text" id="codeeffect" name="airCode" class="codeeffect"> -->
			<span>状态：</span>
			<select class="codeqi" name="generalStatus">
				<option value="0" >启用</option>
				<option value="1" >禁用</option>
			</select>
		</div>
		<p class="redreset effectp">&lowast;出票航空公司：CX/KA，MU/FM的SPA行程默认为同航空公司行程，计奖及计算相同</p>
	</div>
</div>
<div class="borderefe"></div>
<!-- 无奖励情况 -->
<div class="policy-effect clear">
	<div class="effect-head clear">
		<p>无奖励情况</p>
		<img src="${ctxStatic}/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
		<div class="effectli">
			<label>
				<input type="checkbox" id="effectil" class="checkeffect" name="noItReward" value="Y" >
				<span>IT票无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<input type="checkbox" id="effectil" class="checkeffect" name="noOpenReward" value="Y" >
				<span>OPEN票无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<input type="checkbox" id="effectil" class="checkeffect" name="noInvolveReward" value="Y"  >
				<span>含<input type="text" class="textcheck" name="noInvolvePrice" >票价基本无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<input type="checkbox" id="effectil" class="checkeffect"  name="noLowReward" value="Y" >
				<span>票价低于<input type="text" class="textcheck" name="noLowPrice">无奖励</span>
			</label>
		</div>
	</div>
</div>
<!-- <div class="borderefe"></div> -->
<!-- OD设置 -->
<!-- <div class="policy-effect clear"> -->
<!-- 	<div class="effect-head clear"> -->
<!-- 		<p>OD设置：</p> -->
<%-- 		<img src="<%=request.getContextPath()%>/static/general/images/right.jpg"> --%>
<!-- 	</div> -->
<!-- 	<div class="clear"></div> -->
<!-- 	<div class="effect-content"> -->
<!-- 		<div class="effectcon1 fl"> -->
<!-- 			<p>去程起点：</p> -->
<!-- 			<div class="effectli"> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="goOriginChoice" checked="checked" value="1" /> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png" > --%>
<!-- 					<span>出票航第一个航段的起点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio"  name="goOriginChoice" value="2" /> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"/> --%>
<!-- 					<span>出票航实际承运第一个航段起点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<!-- 					<input type="radio" name="goOriginChoice" value="3" /> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png" > --%>
<!-- 					<span>第一个国际段的起点</span> -->
<!-- 				</label> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="effectcon1 fl"> -->
<!-- 			<p>去程终点：</p> -->
<!-- 			<div class="effectli"> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="goDestinationChoice" checked="checked" value="1"/> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png" > --%>
<!-- 					<span>出票航飞到的最远点（里程）</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="goDestinationChoice" value="1"/> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
<!-- 					<span>跨大区（或子区或国际）段的终点<br /> -->
<!-- 					<span class="redreset" style="margin-left: 14px;">（我们按照里程最远点为折射点）</span></span> -->
<!-- 				</label> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="clear"></div> -->
<!-- 		<div class="effectcon1"> -->
<!-- 			<p>回程终点：</p> -->
<!-- 			<div class="effectli"> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="backDestinationChoice" checked="checked" value="1" /> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png" > --%>
<!-- 					<span>出票航最后一个航段的终点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="backDestinationChoice" value="2"/> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png" > --%>
<!-- 					<span>出票航承运的最后一个航段终点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<!-- 				<input type="radio" name="backDestinationChoice" value="3" /> -->
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png" > --%>
<!-- 					<span>最后一个国际段的终点</span> -->
<!-- 				</label> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="borderefe"></div>
<!-- 采购价计算方式 -->
<div class="policy-effect clear">
	<div class="effect-head clear">
		<p>采购价计算方式：</p>
		<img src="${ctxStatic}/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
		<div class="redreset" style="margin-bottom: 10px;">&lowast;当配上有效政策时，销售价计算方式如下：</div>
		<div class="effectcon1">
			<p style="color: #6a60fb;">计算方式：</p>
			<div class="effectli">
				<label class="checkcon">
					<input type="checkbox" id="checkli" name="addOnChoice" value="Y" class="checkeffect">
					<span>Add On计奖</span>
				</label>
				<label class="checkcon checkleft">
					<input type="checkbox" name="addOnInternalChoice" value="Y" class="checkeffect clickcheck"/>
					<span>国内Add On航段计奖</span>
				</label>
				<label class="checkcon checkleft">
					<input type="checkbox" name="addOnWorldChoice" value="Y" class="checkeffect clickcheck"/>
					<span>国际Add On航段计奖</span>
				</label>
				<label class="checkcon">
					<input type="checkbox" name="spaChoice" value="Y" class="checkeffect">
					<span>SPA计奖</span>
				</label>
				<label class="checkcon">
					<input type="checkbox" class="checkeffect" name="qChoice" value="Y">
					<span>Q值计奖</span>
				</label>
			</div>
		</div>
		<div style="margin-bottom: 10px;"><span class="redreset">&lowast;</span>采购价计算公式 = 计奖部分&lowast;（1-代理费率）&lowast;（1-返点）+ 无奖励的部分&lowast;（1-代理费率）+ 税款 + 手续费<div>
		<div class="button-check">
			<img src="${ctxStatic}/general/images/addplo.png">
			<p><input type="submit" style="background:#c41213;border:0;float:left;margin-top:7px; marfont-size:16px;color:#FFF;"  value="添加总则"/></p>
		</div>
	</div>
</div>
	</form:form>
</body>
</html>