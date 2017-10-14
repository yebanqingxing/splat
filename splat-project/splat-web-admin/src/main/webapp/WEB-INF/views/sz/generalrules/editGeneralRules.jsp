<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加政策总则</title>
</head>
<body bgcolor="#DCDCDC">
<form action="${ctx}/generalrules/generalRules/edit" method="post">
<table bgcolor="#DCDCDC" align="center" width="1000" border="1">
<tr>
<<<<<<< .mine
<td>航司二字码<input type="text" name="grId" value="${generalRules.grId}"/></td>
=======
<td>航司二字码
<input type="hidden" name="grId" value="${generalRules.grId}"/>
<input type="hidden" name="id" value="${generalRules.grId}"/>
<input type="text" name="airCode" value="${generalRules.grId}"/></td>
>>>>>>> .r369
<td>状态
<select name="generalStatus">
<option value="0" <c:if test="${generalRules.generalStatus == 0}">selected="selected"</c:if> >启用</option>
<option value="1"<c:if test="${generalRules.generalStatus == 1}">selected="selected"</c:if> >禁用</option>
</select></td>
</tr>
<tr>
<td><br/><br/><br/></td>
</tr>
<tr>
<td>同盟航空公司：CX/KA，MU/FM，的SPA行程默认为同航空公司行程，计奖及计算相同</td>
</tr>
</table>
<hr/>

<table bgcolor="#DCDCDC" align="center" width="1000">
<tr>
<td>
无奖励情况
</td>
</tr>
<tr>
<td>
<input type="checkbox" name="noItReward" value="Y" <c:if test="${generalRules.noItReward == 'Y'}">checked="checked" </c:if>/>&nbsp;IT票无奖励
</td>
</tr>
<tr>
<td>
<input type="checkbox" name="noOpenReward" value="Y" <c:if test="${generalRules.noOpenReward == 'Y'}">checked="checked"</c:if>/>&nbsp;OPEN票无奖励
</td>
</tr>
<tr>
<td>
<input type="checkbox" name="noInvolveReward" value="Y" <c:if test="${generalRules.noInvolveReward == 'Y'}">checked="checked"</c:if>/>
&nbsp;含<input type="text" name="noInvolvePrice" value="${generalRules.noInvolvePrice}"/>票价基础无奖励
</td>
</tr>
<tr>
<td>
<input type="checkbox" name="noLowReward" value="Y" <c:if test="${generalRules.noLowReward == 'Y'}">checked="checked"</c:if>/>&nbsp;票价低于
<input type="text" name="noLowPrice" value="${generalRules.noLowPrice}"/>无奖励
</td>
</tr>
</table>
<hr/>
<div style="width:1000px;margin-left:180px">
<div style="float:left;">
<table bgcolor="#DCDCDC" align="center" width="400">
<tr>
<td>OD设置</td>
</tr>
<tr>
<td>去程起点</td>
</tr>
<tr>
<td>
<input type="radio" name="goOriginChoice" value="1" <c:if test="${generalRules.goOriginChoice == 1}">checked="checked"</c:if> />
出票航第一个航段的起点
</td>
</tr>
</tr>
<tr>
<td>
<input type="radio" name="goOriginChoice" value="2" <c:if test="${generalRules.goOriginChoice == 2}">checked="checked"</c:if>/>
出票航实际承运第一个航段起点
</td>
</tr>
</tr>
<tr>
<td>
<input type="radio"  name="goOriginChoice" value="3" <c:if test="${generalRules.goOriginChoice == 3}">checked="checked"</c:if>/>
第一个国际段的起点
</td>
</tr>
</table>
</div>

<div style="float:right;width:400px">
<table bgcolor="#DCDCDC" align="center"  width="400">
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>去程终点</td>
</tr>
<tr>
<td>
<input type="radio" name="goDestinationChoice" value="1" <c:if test="${generalRules.goDestinationChoice == 1}">checked="checked"</c:if>/>
出票航飞到的最远点（里程）
</td>
</tr>
</tr>
<tr>
<td>
<input type="radio" name="goDestinationChoice" value="2" <c:if test="${generalRules.goDestinationChoice == 2}">checked="checked"</c:if>/>
跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
</td>
</tr>
</tr>
</table>
</div>
</div>

<br/><br/><br/>
<div style="width:1000px;margin-left:180px">
<div style="float:left;">
<br/>
<table bgcolor="#DCDCDC" align="center"  width="400">
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>回程终点</td>
</tr>
<tr>
<td>
<input type="radio" name="backDestinationChoice" value="1" <c:if test="${generalRules.backDestinationChoice == 1}">checked="checked"</c:if>/>
出票航最后一个航段的终点
</td>
</tr>
</tr>
<tr>
<td>
<input type="radio" name="backDestinationChoice" value="2" <c:if test="${generalRules.backDestinationChoice == 2}">checked="checked"</c:if>/>
出票航承运的最后一个航段终点
</td>
</tr>
</tr>
<tr>
<td>
<input type="radio" name="backDestinationChoice" value="3"  <c:if test="${generalRules.backDestinationChoice == 3}">checked="checked"</c:if>/>
最后一个国际段的终点
</td>
</tr>
</table>
</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
<hr/>
<table bgcolor="#DCDCDC" align="center"  width="1000">
<tr>
<td>采购价计算方式<br><br/></td>
</tr>
<tr>
<td>当未配上有效政策时，将返回默认政策。</td>
</tr>
<tr>
<td>
计算公式：采购价=票面销售价*（1-代理费率）+税费+<input type="text" name="poundage" value="${generalRules.poundage}"/>(手续费)
<br><br>
</tr>
<tr>
<td>当配上有效政策时，销售价计算方式如下。
<br><br>
</td>
</tr>
<tr>
<td><input type="radio" name="formulaId" value="2" <c:if test="${generalRules.formulaId == 2}">checked="checked"</c:if>/>计算方式</td>
</tr>
<tr>
<td>
&nbsp;<input type="checkbox" name="addOnChoice" value="Y" <c:if test="${generalRules.addOnChoice == 'Y'}">checked="checked"</c:if>/>Add On计奖
</td>
</tr>
<tr>
<td>
&nbsp;&nbsp;<input type="checkbox" name="addOnInternalChoice" value="Y" <c:if test="${generalRules.addOnInternalChoice == 'Y'}">checked="checked"</c:if>/>国内Add On计奖
</td>
</tr>
<tr>
<td>
&nbsp;&nbsp;<input type="checkbox" name="addOnWorldChoice" value="Y" <c:if test="${generalRules.addOnWorldChoice == 'Y'}">checked="checked"</c:if>/>国际Add On计奖
</td>
</tr>
<tr>
<td>
&nbsp;<input type="checkbox" name="spaChoice" value="Y" <c:if test="${generalRules.spaChoice == 'Y'}">checked="checked"</c:if>/>SPA计奖
</td>
</tr>
<tr>
<td>
&nbsp;<input type="checkbox" name="qChoice"  value="Y" <c:if test="${generalRules.qChoice == 'Y'}">checked="checked"</c:if>/>Q值计奖
</td>
</tr>
<tr>
<td>
采购价计算公式=计奖的部分*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
<br><br>
</td>
</tr>
<tr>
<td><input type="submit" value="修改总则"></td>
</tr>
</table>
</form>
</body>
</html>