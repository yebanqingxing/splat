<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际订单查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/order.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/common/pading.css">
<!-- 时间控件的  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery/jquery-ui.min.css"></link>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-ui.min.js"></script>
<script>
$(function(){

	  //status代表的是开始的id
	  	var objclear,
	  		objval;
	    $( "#minTime" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 2,
		  minDate: '',//今天之前可不可以选中 设置值就是今天之前的日期不能用 设置为空呢就是可以选择之前的时
	      duration:'normal',
		  showButtonPanel:true,//是否显示清空按钮
		  closeText:"",
		  beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate) {
	      	objval = selectedDate;
	      	if(selectedDate == ''){
	      		$( "#maxTime" ).datepicker( "option", "minDate", '0');
	      	}else{
	      		$( "#maxTime" ).datepicker( "option", "minDate", selectedDate);
	      	}
	      }
	    });
	    $( "#maxTime" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      changeYear:false,
	      dateFormat:"yy-mm-dd",
	      numberOfMonths: 2,
	      showButtonPanel:true,//是否显示清空按钮
	      closeText:"",
	      minDate: 0,
	      beforeShow : function(input){
		  	objclear = input;
		  },
	      onClose: function(selectedDate){
	      	objval = selectedDate;
	        if(selectedDate == ''){
	      		$( "#minTime" ).datepicker( "option", "maxDate", null);
	      	}else{
	      		$( "#minTime" ).datepicker( "option", "maxDate", selectedDate);
	      	}
	      }
	    });
	    $(".ui-datepicker-close").live("click",function(){
	    	objclear.value = "";
			   var dates = $("#minTime,#maxTime");
			  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
			   dates.datepicker("option", "minDate", '0');
			   dates.datepicker("option", "maxDate", null);
	    })
	    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
	    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
	    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
	    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
	  });
// 	$(document).ready(function() {
		
// 	});
	function page(n,s){
		//$("#pageNo").val(n);
		//$("#pageSize").val(s);
		var orderNo=$("#orderNo").val();
		var minTime=$("#minTime").val();
		var maxTime=$("#maxTime").val();
		var ticketNo=$("#ticketNo").val();
		var createAccount=$("#createAccount").val();
		var orderStatus=$("#hid_orderStatus").val();
		var payStatus=$("#payStatus").val();
		var crsPnr=$("#crsPnr").val();
		var tripType=$("#tripType").val();
		var currentStatus=$("#currentStatus").val();
		if('' == currentStatus){
			currentStatus= $("#hid_currentStatus").val();
		}
		if('' == payStatus ){
			payStatus = $("#hid_payStatus").val();
		}
		var pageNo= n;
		var pageSize=s;
		$.post(
		'${ctx}/order/tbTicketorderDetail/list?number='+Math.random(),
		{"orderNo":orderNo,"minTime":minTime,"maxTime":maxTime,
		"tripType":tripType,"ticketNo":ticketNo,"createAccount":createAccount,
		"orderStatus":orderStatus,"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,
		"pageSize":pageSize,"flag":1,"currentStatus":currentStatus
		},
		function(data){
			$("#detailPage").html(data)
		}
		)
		//$("#searchForm").submit();
    	return false;
    }
	//模糊查询
	function search(){
		page('1','10');
	}
	//查询支付状态
	function findPayStatus(obj,payStatus){
		//支付状态
		$("#hid_payStatus").val(payStatus);
		//订单状态
		$("#hid_orderStatus").val('');
		//赋值操作状态为空
		$("#hid_currentStatus").val('');
		//修改class样式
		editClass(obj);
		
		page('1','10');
	}
	
	//查询订单的状态
	function findOrderStatus(obj,orderStatus){
		editClass(obj);
		//订单状态
		$("#hid_orderStatus").val(orderStatus);
		$("#hid_currentStatus").val('');
		$("#hid_payStatus").val('');
		editSelect();
		page('1','10');
	}
	
	//查询所有
	function searchAll(obj){
		editClass(obj);
		$("#hid_orderStatus").val('');
		//支付状态赋值为空
		$("#hid_payStatus").val("");
		//订单状态
		$("#hid_currentStatus").val("");
		editSelect()
		page("1","10");
	}
	function editClass(obj){
		//改变样式选中
		$(obj).parent().addClass("tableclcik").siblings().removeClass("tableclcik")
		editSelect();
	}
	
	//查询操作状态
	function findCurrentStatus(obj,status){
		editClass(obj);
		$("#hid_orderStatus").val('');
		//支付状态赋值为空
		$("#hid_payStatus").val("");
		//订单状态
		$("#hid_currentStatus").val(status);
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
</script>
</head>
<body>
<div class="addpolicy">
	<img src="<%=request.getContextPath() %>/static/order/images/ordersearch.png">
	<p>国际订单查询</p>
</div>
<!-- 总则编码 -->
<div class="query">
	<div class="queryinter">
		<span class="query-span interspan">订单号：</span>
		<input type="text" value="" id="orderNo" name="orderNo" class="querycode">
		<span>订单时间：</span>
		<input type="text" readonly="readonly"  id="minTime" name="minTime"  class="doubledate ipticon time">
		<span>至</span>
		<input type="text" readonly="readonly" id="maxTime" name="maxTime" class="doubledate ipticon time">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">票号：</span>
		<input type="text"  id="ticketNo" name="ticketNo" class="querycode">
		<span>旅客姓名：</span>
		<input type="text" id="createAccount" name="createAccount"  class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">PNR号：</span>
		<input type="text" name="crsPnr" id="crsPnr" class="querycode">
		<span>订单类型：</span>
		<select id="tripType" name="tripType" class="querycode">
		<option value="">请选择</option>
		<option value="0">单程</option>
		<option value="1">往返</option>
		<option value="3">中转</option>
		</select>
	</div>
	<div class="queryinter">
		<span class="query-span interspan">操作状态：</span>
		<!-- <input type="text" value="" name="querycode" class="querycode"> -->
		<select id="currentStatus" name="orderStatus" class="querycode">
		<option value="">请选择</option>
		<option value="1">正在核实价格和政策</option>
		<option value="2">已核实价格和政策</option>
		<option value="5">审核拒绝</option>
		<option value="7">等待出票</option>
		<option value="8">出票完成</option>
		<option value="9">申请全额退款</option>
		<option value="10">申请通过全额退款</option>
		</select>
		<span>支付状态：</span>
		<select class="querycode" name="payStatus" id="payStatus">
		<option value="">请选择</option>
		<option value="0">已支付</option>
		<option value="1">未支付</option>
		<option value="2">支付失败</option>
		<option value="3">未退款</option>
		<option value="4">已退款</option>
		</select>
		<button class="addquery" onclick="search();">查询</button>
	</div>
</div>
<input type="hidden" name="hid_currentStatus" value="" id="hid_currentStatus"/>
<!-- 订单的状态 -->
<input type="hidden" name="hid_orderStatus" id="hid_orderStatus" value=""/>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderhead">
		<table border="0">
		  <tr>
		  	<td id="td" class="tableclcik"><a href="javascript:;" onclick="searchAll(this);" >全部</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'1')" >下单成功(${orderStatusCountList['1'] })</a></td>
          	<td><a href="javascript:;" onclick="findCurrentStatus(this,'1')" >订单审核中(${currentStatusList['1'] })</a></td>
			<td><a href="javascript:;" onclick="findPayStatus(this,'1');" >等待支付(${payStatusCountList['1'] })</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'7');" >等待出票(${currentStatusList['7']})</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'8')" >出票完成(${currentStatusList['8'] })</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'5')" >审核拒绝(${currentStatusList['5'] })</a></td>
			<td><a href="javascript:;" onclick="findCurrentStatus(this,'10')" >退款订单(${currentStatusList['10'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'7')" >取消订单(${orderStatusCountList['7'] })</a></td>
			<td><a href="javascript:;" onclick="findOrderStatus(this,'5')" >异常订单(${orderStatusCountList['5'] })</a></td>
		  </tr>
		</table>
	</div>
	<div id="detailPage">
		<jsp:include page="tbTicketorderDetailListPage.jsp"></jsp:include>
	</div>
	
</div>
</body>
</html>