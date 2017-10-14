<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/general/css/policy.css">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/general/js/addpolicy.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/dateControl.js"></script>

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
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/general/js/j.suggest.js"></script>
<script type="text/javascript" src="${ctxStatic}/general/js/j.dimensions.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			if($("#noInvolvePrice").val()==0){
				 if($("#a3").val()=="Y"){
					 if($("#noInvolvePrice").val()==""){
						 document.getElementById("noInvolvePrice").value="0.00";
						 }
				 }else{
					 document.getElementById("noInvolvePrice").value="";
				 }
			 }
			if($("#noLowPrice").val()==0){
				if($("#a4").val()=="Y"){
					if($("#noLowPrice").val()==""){
						 document.getElementById("noLowPrice").value="0.00";
						 }
				 }else{
					 document.getElementById("noLowPrice").value="";
				 }
			 }
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
<script type="text/javascript">
$(function(){
	$(function(){
		dateControl('#minTime','#maxTime');	
		dateControl('#minData','#maxData');
	})
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

var airCode = "";
function checkAirport(){
	var isTrue = true;
	var codeBrr = $("#arrcity_3word").val();
	var airCodeArr = airCode.split("/");
	for(var i=0;i<airCodeArr.length;i++){
		if(airCodeArr[i] == codeBrr){
			alert("已选过本航司");
			isTrue = false;
		}
	}
	$.post(
			"${ctx}/generalrules/generalRules/checkAirline",
			{
			"codeBrr":codeBrr
			},
			function(date){
				if(date == false){
					alert("没有此航班");
				}else{
					if(isTrue == true){
						airCode += codeBrr+"/";
						if("" != codeBrr){
							$("#arrcity").val(airCode);
						}
					}
				}
			}
		);
}
function check1(){
	if(document.getElementById("effectil1").checked){
		document.getElementById("a1").value="Y";
	}else{
		document.getElementById("a1").value="N";
	}
	if(document.getElementById("effectil2").checked){
		document.getElementById("a2").value="Y";
	}else{
		document.getElementById("a2").value="N";
	}
	if(document.getElementById("effectil3").checked){
		document.getElementById("a3").value="Y";
	}else{
		document.getElementById("a3").value="N";
	}
	if(document.getElementById("effectil4").checked){
		document.getElementById("a4").value="Y";
	}else{
		document.getElementById("a4").value="N";
	}
	if(document.getElementById("effectil5").checked){
		document.getElementById("a5").value="Y";
	}else{
		document.getElementById("a5").value="N";
	}
	if(document.getElementById("effectil6").checked){
		document.getElementById("a6").value="Y";
	}else{
		document.getElementById("a6").value="N";
	}
	if(document.getElementById("effectil7").checked){
		document.getElementById("a7").value="Y";
	}else{
		document.getElementById("a7").value="N";
	}
	if(document.getElementById("effectil8").checked){
		document.getElementById("a8").value="Y";
	}else{
		document.getElementById("a8").value="N";
	}
	if(document.getElementById("effectil9").checked){
		document.getElementById("a9").value="Y";
	}else{
		document.getElementById("a9").value="N";
	}
}
</script>


<title>政策总则详情</title>
</head>
<body bgcolor="#DCDCDC">
<div class="addpolicy">
	<img src="${ctxStatic}/general/images/details.png">
	<p>详细信息</p>
</div>
<!-- 作用域 -->
<div class="policy-effect clear">
	<div class="effect-head clear">
		<p>作用域：</p>
		<img src="<%=request.getContextPath()%>/static/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
	<form action="${ctx}/generalrules/generalRules/edit" method="post" >
		<input type="hidden" name="token" value="${token}"/>
		<div class="effect-content1">
		<input type="hidden" name="arrcity_3word" id="arrcity_3word" value="" />
		<label for="arrcity">航班二字码：</label>
		<input type="text" name="airCode" id="arrcity" value="${generalRules.airCode}" style="width:300px;margin-top: 9px"/>
		<div id='suggest' class="ac_results"></div>
<!-- 			<span>航班二字码：</span> -->
			<input type="hidden" name="grId" value="${generalRules.grId}"/>
			<input type="hidden" name="id" value="${generalRules.grId}"/>
<%-- 			<input type="text" id="codeeffect" value="${generalRules.airCode}"  name="airCode" class="codeeffect"> --%>
			<span>状态：</span>
			<select class="codeqi" name="generalStatus">
				<option value="0" <c:if test="${generalRules.generalStatus == 0}">selected="selected"</c:if> >启用</option>
				<option value="1" <c:if test="${generalRules.generalStatus == 1}">selected="selected"</c:if> >禁用</option>
			</select>
		</div>
		<p class="redreset effectp">&lowast;出票航空公司：CX/KA，MU/FM的SPA行程默认为同航空公司行程，计奖及计算相同</p>
	</div>
</div>
<div class="borderefe"></div>
<!-- 无奖励情况 -->
<div class="policy-effect clear">
	<div class="effect-head clear">
		<p>无奖励情况：</p>
		<img src="${ctxStatic}/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
		<div class="effectli">
			<label>
				<%-- <input type="checkbox" id="effectil" class="checkeffect" name="noItReward" value="Y" <c:if test="${generalRules.noItReward == 'Y'}">checked="checked" </c:if>> --%>
				<input type="checkbox" id="effectil1" class="checkeffect" name="noItReward1" value="Y" <c:if test="${generalRules.noItReward == 'Y'}">checked="checked"</c:if> onclick="check1();">
				<input type="hidden" id="a1" name="noItReward" value="${generalRules.noItReward}"/>
				<span>IT票无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<%-- <input type="checkbox" id="effectil" class="checkeffect" name="noOpenReward" value="Y"  <c:if test="${generalRules.noOpenReward == 'Y'}">checked="checked"</c:if>> --%>
				<input type="checkbox" id="effectil2" class="checkeffect" value="Y" name="noOpenReward2" <c:if test="${generalRules.noOpenReward == 'Y'}">checked="checked"</c:if> onclick="check1();">
				<input type="hidden" id="a2" name="noOpenReward" value="${generalRules.noOpenReward}"/>
				<span>OPEN票无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<%-- <input type="checkbox" id="effectil" class="checkeffect" name="noInvolveReward" value="Y"  <c:if test="${generalRules.noInvolveReward == 'Y'}">checked="checked"</c:if>> --%>
				<input type="checkbox" id="effectil3" class="checkeffect" name="noInvolveReward3" value="Y"  <c:if test="${generalRules.noInvolveReward == 'Y'}">checked="checked"</c:if> onclick="check1();">
				<input type="hidden" id="a3" name="noInvolveReward" value="${generalRules.noInvolveReward}"/>
				<span>含<input type="text" class="textcheck" id="noInvolvePrice" name="noInvolvePrice" value="${generalRules.noInvolvePrice}">票价基本无奖励</span>
			</label>
		</div>
		<div class="effectli">
			<label>
				<input type="checkbox" id="effectil4" class="checkeffect"  name="noLowReward4" value="Y" <c:if test="${generalRules.noLowReward == 'Y'}">checked="checked"</c:if> onclick="check1();">
				<input type="hidden" id="a4" name="noLowReward" value="${generalRules.noLowReward}"/>
				<span>票价低于<input type="text" class="textcheck" id="noLowPrice" name="noLowPrice" value="${generalRules.noLowPrice}">无奖励</span>
			</label>
		</div>
	</div>
</div>
<!-- <div class="borderefe"></div> -->
<!-- <!-- OD设置 --> 
<!-- <div class="policy-effect clear"> -->
<!-- 	<div class="effect-head clear"> -->
<!-- 		<p>OD设置：</p> -->
<%-- 		<img src="${ctxStatic}/general/images/right.jpg"> --%>
<!-- 	</div> -->
<!-- 	<div class="clear"></div> -->
<!-- 	<div class="effect-content"> -->
<!-- 		<div class="effectcon1 fl"> -->
<!-- 			<p>去程起点：</p> -->
<!-- 			<div class="effectli"> -->
<!-- 				<label class="checkradio"> -->
<%-- 					<input type="radio" name="goOriginChoice" value="1" <c:if test="${generalRules.goOriginChoice == 1}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%> 
<!-- 					<span>出票航第一个航段的起点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio"  name="goOriginChoice" value="2" <c:if test="${generalRules.goOriginChoice == 2}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%> 
<!-- 					<span>出票航实际承运第一个航段起点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio" name="goOriginChoice" value="3" <c:if test="${generalRules.goOriginChoice == 3}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%> 
<!-- 					<span>第一个国际段的起点</span> -->
<!-- 				</label> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="effectcon1 fl"> -->
<!-- 			<p>去程终点：</p> -->
<!-- 			<div class="effectli"> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio" name="goDestinationChoice" value="1" <c:if test="${generalRules.goDestinationChoice == 1}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%> 
<!-- 					<span>出票航飞到的最远点（里程）</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio" name="goDestinationChoice" value="1" <c:if test="${generalRules.goDestinationChoice == 1}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%> 
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
<%-- 				<input type="radio" name="backDestinationChoice" value="1" <c:if test="${generalRules.backDestinationChoice == 1}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%> 
<!-- 					<span>出票航最后一个航段的终点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio" name="backDestinationChoice" value="2" <c:if test="${generalRules.backDestinationChoice == 2}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%> 
<!-- 					<span>出票航承运的最后一个航段终点</span> -->
<!-- 				</label> -->
<!-- 				<label class="checkradio"> -->
<%-- 				<input type="radio" name="backDestinationChoice" value="3"  <c:if test="${generalRules.backDestinationChoice == 3}">checked="checked"</c:if>/> --%>
<%-- <%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%> 
<!-- 					<span>最后一个国际段的终点</span> -->
<!-- 				</label> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="borderefe"></div>
<!-- OD设置 -->
<div class="policy-effect clear" style="display: none">
	<div class="effect-head clear">
		<p>OD设置：</p>
		<img src="<%=request.getContextPath()%>/static/general/images/right.jpg">
	</div>
	<div class="clear"></div>
	<div class="effect-content">
		<div class="effectcon1 fl">
			<p>去程起点：</p>
			<div class="effectli">
				<label class="checkradio">
					<input type="radio" name="goOriginChoice" value="1" <c:if test="${generalRules.goOriginChoice == 1}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>出票航第一个航段的起点</span>
				</label>
				<label class="checkradio">
				<input type="radio"  name="goOriginChoice" value="2" <c:if test="${generalRules.goOriginChoice == 2}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>出票航实际承运第一个航段起点</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="goOriginChoice" value="3" <c:if test="${generalRules.goOriginChoice == 3}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>第一个国际段的起点</span>
				</label>
			</div>
		</div>
		<div class="effectcon1 fl">
			<p>去程终点：</p>
			<div class="effectli">
				<label class="checkradio">
				<input type="radio" name="goDestinationChoice" value="1" <c:if test="${generalRules.goDestinationChoice == 1}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>出票航飞到的最远点（里程）</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="goDestinationChoice" value="1" <c:if test="${generalRules.goDestinationChoice == 1}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>跨大区（或子区或国际）段的终点<br />
					<span class="redreset" style="margin-left: 14px;">（我们按照里程最远点为折射点）</span></span>
				</label>
			</div>
		</div>
		<div class="clear"></div>
		<div class="effectcon1">
			<p>回程终点：</p>
			<div class="effectli">
				<label class="checkradio">
				<input type="radio" name="backDestinationChoice" value="1" <c:if test="${generalRules.backDestinationChoice == 1}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio1.png"> --%>
					<span>出票航最后一个航段的终点</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="backDestinationChoice" value="2" <c:if test="${generalRules.backDestinationChoice == 2}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>出票航承运的最后一个航段终点</span>
				</label>
				<label class="checkradio">
				<input type="radio" name="backDestinationChoice" value="3"  <c:if test="${generalRules.backDestinationChoice == 3}">checked="checked"</c:if>/>
<%-- 					<img src="<%=request.getContextPath()%>/images/radio2.png"> --%>
					<span>最后一个国际段的终点</span>
				</label>
			</div>
		</div>
	</div>
</div>
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
					<input type="checkbox" id="effectil5" name="addOnChoice5" value="Y" class="checkeffect"  <c:if test="${generalRules.addOnChoice == 'Y'}">checked="checked"</c:if> onclick="check1();">
					<input type="hidden" id="a5" name="addOnChoice" value="${generalRules.addOnChoice}"/>
					<span>Add On计奖</span>
				</label>
				<label class="checkcon checkleft">
					<input type="checkbox" id="effectil6" name="addOnInternalChoice6" value="Y" class="checkeffect" <c:if test="${generalRules.addOnInternalChoice == 'Y'}">checked="checked"</c:if> onclick="check1();"/>
					<input type="hidden" id="a6" name="addOnInternalChoice" value="${generalRules.addOnInternalChoice}"/>
					<span>国内Add On航段计奖</span>
				</label>
				<label class="checkcon checkleft">
					<input type="checkbox" id="effectil7" name="addOnWorldChoice7" value="Y" class="checkeffect" <c:if test="${generalRules.addOnWorldChoice == 'Y'}">checked="checked"</c:if> onclick="check1();"/>
					<input type="hidden" id="a7" name="addOnWorldChoice" value="${generalRules.addOnWorldChoice}"/>
					<span>国际Add On航段计奖</span>
				</label>
				<label class="checkcon">
					<input type="checkbox" id="effectil8" name="spaChoice8" value="Y" class="checkeffect" <c:if test="${generalRules.spaChoice == 'Y'}">checked="checked"</c:if> onclick="check1();">
					<input type="hidden" id="a8" name="spaChoice" value="${generalRules.spaChoice}"/>
					<span>SPA计奖</span>
				</label>
				<label class="checkcon">
					<input type="checkbox" id="effectil9" class="checkeffect" name="qChoice9" value="Y" <c:if test="${generalRules.qChoice == 'Y'}">checked="checked"</c:if> onclick="check1();">
					<input type="hidden" id="a9" name="qChoice" value="${generalRules.qChoice}"/>
					<span>Q值计奖</span>
				</label>
			</div>
		</div>
		<div style="margin-bottom: 10px;"><span class="redreset">&lowast;</span>采购价计算公式 = 计奖部分&lowast;（1-代理费率）&lowast;（1-返点）+ 无奖励的部分&lowast;（1-代理费率）+ 税款 + 手续费<div>
		<div class="clear">
			<div class="button-detail fl">
				<img src="${ctxStatic}/general/images/removedeil.png">
				<p><a href = "${ctx}/generalrules/generalRules/list" style="background:#c41213;border:0;float:left;marfont-size:16px;color:#FFF;"/>关闭</a></p>
			</div>
			<div class="button-detail fl">
				<img src="${ctxStatic}/general/images/xiudeil.png">
				<p><input type="submit" style="background:#c41213;border:0;float:left;margin-top:7px; marfont-size:16px;color:#FFF;" value="修改"/></p>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>