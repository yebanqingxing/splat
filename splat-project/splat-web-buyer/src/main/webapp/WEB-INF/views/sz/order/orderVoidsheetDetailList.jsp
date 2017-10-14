<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际废票订单</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/common/pading.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/doubleDate.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/doubleDate2.0.js"></script>
<!-- 时间控件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery/jquery-ui.min.css"></link>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-ui.min.js"></script>
<script>
$(function(){
    $("#minTime" ).datepicker({
      defaultDate: "+1w",
      changeMonth: false,
      changeYear:false,
      dateFormat:"yy-mm-dd",
      numberOfMonths: 2,
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
      numberOfMonths: 2,
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
		var voidsheetStatus=$("#hid_voidsheetStatus").val();
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
				'${ctx}/order/voidsheetDetail/list?number='+Math.random(),
				{"voidsheetNo":orderNo,"beginCreateTime":minTime,"endCreateTime":maxTime,
				"ticketNo":ticketNo,"createAccount":createAccount,"voidsheetStatus":voidsheetStatus,
				"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,"currentStatus":currentStatus,
				"pageSize":pageSize,"flag":1},
				function(data){
					$("#voidsheetDetail").html(data);
				}
		)
		return false;
	}
	
	function search(){
		page("1","10");
	}
	
	
	//查找操作状态
	function findCurrentStatus(obj,status){
		editClass(obj);
		$("#hid_currentStatus").val(status);
		//订单状态赋值为空
		$("#hid_voidsheetStatus").val("");
		//支付状态赋值为空
		$("#hid_payStatus").val("");
		page("1","10");
	}
	//查找支付状态
    function findPayStatus(obj,status){
        editClass(obj);
        $("#hid_currentStatus").val('');
        //支付状态为空
        $("#hid_payStatus").val(status);
        //订单状态赋值为空
        $("#hid_voidsheetStatus").val('');
        page('1','10');
    }
	//查找订单状态
	function findOrderStatus(obj,status){
		editClass(obj);
		$("#hid_voidsheetStatus").val(status);
		//支付状态赋值为空
		$("#hid_payStatus").val("");
		//订单状态
		$("#hid_currentStatus").val("");
		page("1","10");
	}
	//修改样式
	function editClass(obj){
		//改变样式选中
		$(obj).parent().addClass("tableclcik").siblings().removeClass("tableclcik")
	editSelect();
	
	}
	//查询全部
	function searchAll(obj){
		editClass(obj);
		$("#hid_voidsheetStatus").val('');
		//支付状态赋值为空
		$("#hid_payStatus").val("");
		//订单状态
		$("#hid_currentStatus").val("");
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
	<img src="<%=request.getContextPath() %>/static/order/images/orderwaste.png">
	<p>国际废票订单</p>
</div>
<!-- 总则编码 -->
<div class="query">
	<div class="queryinter">
		<span class="query-span interspan">订单号：</span>
		<input type="text" id="orderNo" name="orderNo" class="querycode">
		<span>订单时间：</span>
		<input type="text" id="minTime" name="minTime" readonly="readonly" class="doubledate ipticon time">
		<span>至</span>
		<input type="text" id="maxTime" name="maxTime" readonly="readonly" class="doubledate ipticon time">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">票号：</span>
		<input type="text" id="ticketNo" name="querycode" class="querycode">
		
		<span>旅客姓名：</span>
		<input type="text" id="createAccount" name="createAccount" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">PNR号：</span>
		<input type="text" id="crsPnr"  name="crsPnr" class="querycode">
		<span>订单类型：</span>
		<input type="text" value="" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">操作状态：</span>
		<!-- <input type="text" value="" name="querycode" class="querycode"> -->
		<select id="currentStatus" name="currentStatus" class="querycode">
			<option value="" selected="selected">请选择</option>
			<option value="1">审核废票</option>
			<option value="6">等待确认</option>
			<option value="2">废票操作</option>
			<option value="5">废票完成</option>
			<option value="3">审核拒绝</option>
		</select>
		<span>支付状态：</span>
		<select id="payStatus" name="payStatus" class="querycode">
			<option value="" selected="selected">请选择</option>
			<option value="0">已支付</option>
			<option value="1">未支付</option>
			<option value="2">支付失败</option>
		</select>
		<button class="addquery" onclick="search()">查询</button>
	</div>
</div>


<input type="hidden" name="hid_payStatus" id="hid_payStatus" value=""/>
<!-- 订单操作的隐藏域 --> 
<input type="hidden" name="hid_currentStatus" value="" id="hid_currentStatus"/>

<!-- 订单的状态 -->
<input type="hidden" name="hid_voidsheetStatus" id="hid_voidsheetStatus" value=""/>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderretreat">
		<table border="0">
		  <tr>
		    <td id="td" class="tableclcik"><a href='javascript:;' onclick='searchAll();'>全部</a></td>
		        <td><a href="javascript:;" onclick="findCurrentStatus(this,'1');">审核废票(${currentStatusList['1'] })</a></td>
		        <td><a href="javascript:;" onclick="findCurrentStatus(this,'6')">等待确认(${currentStatusList['6'] })</a></td>
		        <td><a href="javascript:;" onclick="findCurrentStatus(this,'2')">废票操作(${currentStatusList['2'] })</a></td>
		        <td><a href="javascript:;" onclick="findCurrentStatus(this,'5')">废票完成(${currentStatusList['5'] })</a></td>
		    	<td><a href="javascript:;" onclick="findOrderStatus(this,'3')">异常订单(${statusCountList['3'] })</a></td>
		        <td><a href="javascript:;" onclick="findCurrentStatus(this,'3')">审核拒绝(${currentStatusList['3'] })</a></td>
		      	<td><a href="javascript:;" onclick="findPayStatus(this,'0')">退款订单(${payStatusCountList['0'] })</a></td>
		    	<td><a href="javascript:;" onclick="findOrderStatus(this,'2')">废票取消(${statusCountList['2'] })</a></td>
		  </tr>
		</table>
	</div>
	<div id="voidsheetDetail">
		<jsp:include page="voidsheetDetailListPage.jsp"></jsp:include>
	</div>
</div>
</body>
</html>