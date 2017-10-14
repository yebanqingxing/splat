<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.js"></script>
<title>政策添加页面</title>
</head>
<body>
<form action="${ctx}/policyrules/policyRules/save" method="post">
<table width="1000" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td>政策名称</td>
<td><input type="text" name="policyName"></td>
<td colspan="2"><input type="button" value="增加"/></td>
</tr>
<tr>
<td>状态</td>
<td>
<select name="policyStatus">
	<option value="0" selected="selected">启用</option>
	<option value="1">禁用</option>
	<option value="2">挂起</option>
</select>
</td>
<td>开票Office号</td>
<td><input type="text" name="office"></td>
</tr>
<tr>
<td colspan="2">基础信息</td>
<td colspan="2">
<input type="checkbox">是否换编码开票
</td>
</tr>
<tr>
<td>出票航司</td>
<td colspan="3">
<input type="text" name="tktAirline">
</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="4">
<input type="button" value="旅客信息"/>
</td>
</tr>
<tr>
<td>
旅客身份
</td>
<td>
<div>
<input type="radio" checked="checked" name="passagerPid" value="0"/>普通
<input type="radio" name="passagerPid" value="1"/>留学生<br/>
<input type="radio" name="passagerPid" value="2"/>移民
<input type="radio" name="passagerPid" value="3"/>劳工
</div>
</td>
<td>
旅客下限人数
</td>
<td>
<input type="text" name=passagerCount value="1"/>
</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" height="180" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr height="20">
<td colspan="4">
<input type="button" value="行程信息"/>
</td>
</tr>
<tr>
<td>
行程类型
</td>
<td>
<div>
<select name="travelType">
	<option value="0" selected="selected">单程</option>
	<option value="1">返程</option>
	<option value="2">往返</option>
</select>
</div>
</td>
<tr>
<td colspan="2">
<div style="background:#DCDCDC;width:99%;height:50px;">
<div style="float:left;" >
	去程信息
</div>
<br>
<div>
<table>
<tr>
<td>
去程起点
</td>
<td>
<input type="text" name="outOrg"/>
</td>
<td>
去程起点除外
</td>
<td>
<input type="text" name="outOrgEx"/>
</td>
</tr>
<tr>
<td>
去程终点/折返点
</td>
<td>
<input type="text" name="outDes"/>
</td>
<td>
去程终点/折返点除外
</td>
<td>
<input type="text" name="outDesEx"/>
</td>
</tr>
</table>
</div>
</div>
</td>
</tr>
</table>
<table width="1000" height="80" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="4">回程信息</td>
</tr>
<tr>
<td>
回程终点/折返点
</td>
<td>
<input type="text" name="returnDes"/>
</td>
<td>
回程终点/折返点除外
</td>
<td>
<input type="text" name="returnDesEx"/>
</td>
</tr>
</table>
<table width="1000" height="80" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="4">中转点信息</td>
</tr>
<tr>
<td>
去程不能经过
</td>
<td>
<input type="text" name="outUnablePass"/>
</td>
<td>
去程必须经过
</td>
<td>
<input type="text" name="outMustPass"/>
</td>
</tr>
<tr>
<td>
回程不能经过
</td>
<td>
<input type="text" name="returnUnablePass"/>
</td>
<td>
回程必须经过
</td>
<td>
<input type="text" name="returnMustPass"/>
</td>
</tr>
</table>
<table width="1000" height="80" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="4">航班限制</td>
</tr>
<tr>
<td>
排除航班号
</td>
<td>
<input type="text" name="excludeFilghtNo"/>
</td>
<td>
仅限航班号
</td>
<td>
<input type="text" name="allowFilghtNo"/>
</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" height="40" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="2">舱位</td>
<td colspan="2">
<input type="text" name="cabin">
</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" height="40" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td>成人</td>
<td>返点</td>
<td><input type="text" name="rebate"></td>
<td>开票费</td>
<td><input type="text" name="billingFee"></td>
</tr>
<tr>
<td>&nbsp;</td>
<td>特殊代理费</td>
<td><input type="text" name="agencyFee"></td>
<td colspan="2">
（如不填写特殊代理费，则由平台的代理费为准）
</td>
</tr>
<tr>
<td>儿童</td>
<td>
<label>
<input type="radio" checked="checked" name="childrenReward" value="01">
后返与成人一致
</label>
<label>
<input type="radio" name="childrenReward" value="02">
可开无后返
</label>
<label>
<input type="radio" name="childrenReward" value="03">
不可开
</label>
<label>
<input type="radio" name="childrenReward" value="04">
制定奖励
</label>
</td>
<td>返点</td>
<td colspan="2">
<input name="childrenRebate">
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td colspan="4">
<input type="checkbox" name="childrenPoundageChoice" value="N"/>加手续费
<input name="childrenPoundage">元

<input type="checkbox" name="childrenOpenNoCom" value="0"/>可开无代理费
</td>
<td></td>
</tr>
<tr>
<td>婴儿</td>
<td colspan="4">
<input type="radio" name="badyReward" value="0"/>可开无奖励
<input type="radio" name="badyReward" value="1"/>不可开
</td>
</tr>
<tr>
<td colspan="6">是否允许其它政策组合</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" height="40" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="2">日期要求</td>
</tr>
<tr>
<td>去程旅程日期</td>
<td>
<input type="text" id="minDate" name="firstDateStart" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
===
<input type="text" id="maxDate" name="firstDateEnd" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  
</td>
</tr>
<tr>
<td>返程旅程日期</td>
<td>
<input type="text" id="minDate1" name="lastDateStart" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
===
<input type="text" id="maxDate1" name="lastDateEnd" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate1\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  
</td>
</tr>
<tr>
<td>出票日期</td>
<td>
<input type="text" id="minDate2" name="tktTimeStart"  class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'maxDate2\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
===
<input type="text" id="maxDate2" name="tktTimeEnd" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'minDate2\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  
</td>
</tr>
</table>
<hr width="1000" color="black"/>
<table width="1000" height="40" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCDCDC">
<tr>
<td colspan="2">备注</td>
</tr>
<tr>
<td>销售备注</td>
<td>
<textarea rows="5" cols="50" name="policyRemark"></textarea>
</td>
</tr>
<tr>
<td colspan="2">
<input type="checkbox" name="isPolicyStatus" value="2"/>
发布同时挂起政策</td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="增加"/>
</td>
</tr>
</table>
</form>
</body>
</html>