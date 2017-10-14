<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际退票订单</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/order.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/common/pading.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/order/css/doubleDate.css">
<script type="text/javascript" src="${ctxStatic}/order/js/doubleDate2.0.js"></script>
<!-- 时间控件 -->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery/jquery-ui.min.css"></link>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-ui.min.js"></script>
<script>
$(function(){
    $("#minTime" ).datepicker({
      defaultDate: "+1w",
      changeMonth: false,
      changeYear:false,
      dateFormat:"yy-mm-dd",
      numberOfMonths: 1,
	  minDate: '',//今天之前可不可以选中 设置值就是今天之前的日期不能用 设置为空呢就是可以选择之前的时间
      onClose: function(selectedDate) {
        $( "#maxTime" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $("#maxTime" ).datepicker({
      defaultDate: "+1w",
      changeMonth: false,
      changeYear:false,
      dateFormat:"yy-mm-dd",
      numberOfMonths: 1,
      onClose: function(selectedDate) {
        $( "#minTime" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
  });
	function page(n,s){
		//$("#pageNo").val(n);
		//$("#pageSize").val(s);
		var orderNo=$("#orderNo").val();
		var minTime=$("#minTime").val();
		var maxTime=$("#maxTime").val();
		var ticketNo=$("#ticketNo").val();
		var createAccount=$("#createAccount").val();
		var refundsheetStatus=$("#hid_refundsheetStatus").val();
		var payStatus=$("#payStatus").val();
		var crsPnr=$("#crsPnr").val();
		var currentStatus=$("#currentStatus").val();

		if( '' == currentStatus){
					currentStatus = $("#hid_currentStatus").val();
				}
				if('' == payStatus ){
					payStatus = $("#hid_payStatus").val();
				}
		var pageNo=n;
		var pageSize=s;
		$.post(
				'${ctx}/refundsheetdetail/ping/refundsheetDetail?number='+Math.random(),
				{"refundsheetNo":orderNo,"beginCreateTime":minTime,"endCreateTime":maxTime,
				"ticketNo":ticketNo,"createAccount":createAccount,"refundsheetStatus":refundsheetStatus,
				"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,"currentStatus":currentStatus,
				"pageSize":pageSize,"flag":1},
				function(data){
					$("#refundsheetDetial").html(data);
				}
		)
		return false;
	}
	//模糊查询
	function search(){
		page("1","10");
	}
	//查询订单状态
	function findOrderStatus(obj,status){
		//修改样式
		editClass(obj);
		$("#hid_refundsheetStatus").val(status);
		//将支付值清空
		$("#hid_payStatus").val("")
		//修改订单的操作状态
		$("#hid_currentStatus").val("");
		page("1","10");
	}
	//查询支付状态
	function findPayStatus(obj,status){
		//修改样式
		editClass(obj);
		$("#hid_payStatus").val(status)
		//将操作状态清空
		$("#hid_refundsheetStatus").val("");
		//修改订单的操作状态
		$("#hid_currentStatus").val("");
		page("1","10");
	}
	//查询操作状态
	function findCurrentStatus(obj,status){
		//修改样式
		editClass(obj)
		//修改订单的操作状态
		$("#hid_currentStatus").val(status);
		//将支付值清空
		$("#hid_payStatus").val("")
		//订单状态
		$("#hid_refundsheetStatus").val("");
		page("1","10");
	}
	
	//修改样式
	function editClass(obj){
		//改变样式选中
		$(obj).parent().addClass("tableclcik").siblings().removeClass("tableclcik")
	
	editSelect()
	}
	
	function searchAll(obj){
		//修改样式
		editClass(obj)
		//修改订单的操作状态
		$("#hid_currentStatus").val("");
		//将支付值清空
		$("#hid_payStatus").val("")
		//订单状态
		$("#hid_refundsheetStatus").val("");
		page("1","10");
	}
	
	//修改select不让选择
	function editSelect(){
		if($("#td").attr("class") != 'tableclcik'){
			$("#currentStatus").attr("disabled",true)
			$("#payStatus").attr("disabled",true)
		}else {
			$("#currentStatus").attr("disabled",false)
			$("#payStatus").attr("disabled",false)
		}
	}
</script>
</head>
<body>
<div class="addpolicy">
	<img src="${ctxStatic}/order/images/orderretreat.png">
	<p>国际退票订单</p>
</div>
<!-- 总则编码 -->
<div class="query">
	<div class="queryinter">
		<span class="query-span interspan">订单号：</span>
		<input type="text" value="" id="orderNo" name="orderNo" class="querycode">
		<span>订单时间：</span>
		<input type="text" id="minTime" name="minTime" readonly="readonly" class="doubledate ipticon time">
		<span>至</span>
		<input type="text" id="maxTime" name="maxTime" readonly="readonly" class="doubledate ipticon time">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">票号：</span>
		<input type="text" id="ticketNo" name="ticketNo"  class="querycode">
		<span>旅客姓名：</span>
		<input type="text" id="createAccount" name="createAccount" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">PNR号：</span>
		<input type="text" name="crsPnr" id="crsPnr"  class="querycode">
		<span>订单类型：</span>
		<input type="text" value="" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">操作状态：</span>
		<!-- <input type="text" value="" name="querycode" class="querycode"> -->
		<select id="currentStatus" name="currentStatus"  class="querycode">
			<option value="">请选择</option>
			<!-- 操作状态（流转状态） 1: 等待审核 2 拒绝审核 3 退票确认(采购商确认) 4 审核通过 5 退票操作 -->
			<option value="1">等待审核</option>
			<option value="2">拒绝审核</option>
			<option value="3">等待退票</option>
			<option value="4">审核通过</option>
			<option value="5">退票完成</option>
		</select>
		<span>支付状态：</span>
		<select name="payStatus" id="payStatus" class="querycode">
		<option value="">请选择</option>
		<option value="0">已退款</option>
		<option value="1">未退款</option>
		<option value="2">退款失败</option>
		</select>
		<button class="addquery" onclick="search();">查询</button>
	</div>
</div>
<input type="hidden" name="hid_payStatus" id="hid_payStatus" value=""/>
<!-- 订单操作的隐藏域 --> 
<input type="hidden" name="hid_currentStatus" value="" id="hid_currentStatus"/>
<!-- 订单的状态的隐藏域 -->
<input  type="hidden" name="hid_refundsheetStatus" id="hid_refundsheetStatus" value=""/>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderretreat">
		<table border="0">
		  <tr>
		    <td id="td" class="tableclcik"><a href="javascript:;" onclick="searchAll(this);">全部</a></td>
		    <td><a href="javascript:;" onclick="findCurrentStatus(this,'1')">退单审核(${currentStatusList['1'] })</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'3')">退单确认(${currentStatusList['3'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'3')">等待退票(${currentStatusList['3'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'5')">退票完成(${currentStatusList['5'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'4')">退票异常(${statusCountList['4'] })</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'2')">审核拒绝(${currentStatusList['2'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'2')">取消退票(${statusCountList['2'] })</a></td>
		    <td><a href="javascript:;" onclick="findPayStatus(this,'1')">未退款(${payStatusCountList['1'] })</a></td>
		  </tr>
		</table>
	</div>
	<div id="refundsheetDetial">
		<jsp:include page="orderretreatPage.jsp" ></jsp:include>
	</div>
</div>
</body>
</html>





