<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>国际改签订单</title>
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
	
	function page(n,s){
		//$("#pageNo").val(n);
		//$("#pageSize").val(s);
		var orderNo=$("#orderNo").val();
		var minTime=$("#minTime").val();
		var maxTime=$("#maxTime").val();
		var ticketNo=$("#ticketNo").val();
		var createAccount=$("#createAccount").val();
		var endorseStatus=$("#hid_endorseStatus").val();
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
				'${ctx}/order/endorsesheetDetail/list1?number='+Math.random(),
				{"endorsesheetNo":orderNo,"minTime":minTime,"maxTime":maxTime,
				"ticketNo":ticketNo,"createAccount":createAccount,"endorseStatus":endorseStatus,
				"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,"currentStatus":currentStatus,
				"pageSize":pageSize,"flag":1},
				function(data){
					$("#endorseDetailPage").html(data);
				}
		)
		return false;
	}
	
	//模糊查询
	function search(){
		page("1","10");
	}
	
	//查询状态
	function findOrderStatus(obj,status){
		editClass(obj);
		$("#hid_currentStatus").val('');
		//支付状态为空
		$("#hid_payStatus").val('');
		//订单状态赋值为空
		$("#hid_endorseStatus").val(status);
		page("1","10");
	}
	
	//查询操作状态
	function findCurrentStatus(obj,status){
		editClass(obj);
		$("#hid_currentStatus").val(status);
		//支付状态为空
		$("#hid_payStatus").val('');
		//订单状态赋值为空
		$("#hid_endorseStatus").val('');
		page('1','10');
	}
	
	//查找支付状态
	function findPayStatus(obj,status){
		editClass(obj);
		$("#hid_currentStatus").val('');
		//支付状态为空
		$("#hid_payStatus").val(status);
		//订单状态赋值为空
		$("#hid_endorseStatus").val('');
		page('1','10');
	}
	
	//查询所有
	function searchAll(obj,status){
		editClass(obj);
		$("#hid_currentStatus").val('');
		//支付状态为空
		$("#hid_payStatus").val('');
		//订单状态赋值为空
		$("#hid_endorseStatus").val('');
		page('1','10');
	}
	
	function editClass(obj){
		//改变样式选中
		$(obj).parent().addClass("tableclcik").siblings().removeClass("tableclcik")
	editSelect();
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
	<img src="${ctxStatic}/order/images/orderstage.png">
	<p>国际改签订单</p>
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
		<!-- 操作状态（流转状态） 1审核等待 2 审核通过 3 审核拒绝 4 平台审核通过 5 改签完成 -->
			<option value="">请选择</option>
			<option value="1">审核改签</option>
			<option value="2">审核通过</option>
			<option value="3">审核拒绝</option>
			<option value="5">改签完成</option>
		</select>
		<span>支付状态：</span>
		<select name="payStatus" id="payStatus" class="querycode">
		<option value="">请选择</option>
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
<!-- 订单状态的隐藏域 -->
<input type="hidden" name="hid_endorseStatus" id="hid_endorseStatus" value=""/>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderretreat">
		<table border="0">
		  <tr><!-- 操作状态（流转状态） 1审核等待 2 审核通过 3 审核拒绝 4 平台审核通过 5 改签完成 -->
		    <td id="td" class="tableclcik"><a href="javascript:;" onclick="searchAll(this);">全部</a></td>
    		<td><a href="javascript:;" onclick="findCurrentStatus(this,'1')">审核改签(${currentStatus['1']})</a></td>
    		<td><a href="javascript:;" onclick="findPayStatus(this,'1')">等待支付(${payStatusCountList['1']})</a></td>
    		<%-- <td><a href="javascript:;" onclick="findCurrentStatus(this,'2')">等待改签(${currentStatus['2']})</a></td> --%>
    		<td><a href="javascript:;" onclick="findCurrentStatus(this,'5')">改签完成(${currentStatus['5']})</a></td>
    		<td><a href="javascript:;" onclick="findCurrentStatus(this,'3')">审核拒绝(${currentStatus['3']})</a></td>
	    	<td><a href="javascript:;" onclick="findOrderStatus(this,'3')">异常订单(${statusCount['3'] })</a></td>
	    	<td><a href="javascript:;" onclick="findOrderStatus(this,'2')">改签取消(${statusCount['2'] })</a></td>
		    
		  </tr>
		</table>
	</div>
	<!-- 数据展示 -->
	<div id="endorseDetailPage">
	<jsp:include page="endorsesheetDetailListPage.jsp" ></jsp:include>
	</div>
	
</div>
</body>
</html>