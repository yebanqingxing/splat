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
		var orderStatus=$("#orderStatus").val();
		var payStatus=$("#payStatus").val();
		var crsPnr=$("#crsPnr").val();
		var pageNo= n;
		var pageSize=s;
		$.post(
		'<%=request.getContextPath()%>/a/order/supplier/tbTicketorderDetail/list',
		{"orderNo":orderNo,"minTime":minTime,"maxTime":maxTime,
		"ticketNo":ticketNo,"createAccount":createAccount,"orderStatus":orderStatus,
		"payStatus":payStatus,"crsPnr":crsPnr,"pageNo":pageNo,
		"pageSize":pageSize,"flag":1
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
	function findPayStatus(payStatus){
		$("#payStatus").val(payStatus);
		page('1','10');
	}
	
	//查询订单的状态
	function findOrderStatus(orderStatus){
		$("#orderStatus").val(orderStatus)
		page('1','10');
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
		<input type="text" value="" name="aviationcode" class="querydate">
	</div>
	<div class="queryinter">
		<span class="query-span interspan">操作状态：</span>
		<!-- <input type="text" value="" name="querycode" class="querycode"> -->
		<select id="orderStatus" name="orderStatus" class="querycode">
		<option value="">请选择</option>
		<option value="1">已提交</option>
		<option value="2">已审签</option>
		<option value="3">已拒单</option>
		<option value="4">已签单</option>
		<option value="5">已预定</option>
		<option value="6">已出票</option>
		<option value="7">已删除</option>
		<option value="8">已申批</option>
		<option value="9">已初核</option>
		<option value="10">已复核</option>
		<option value="11">已批单</option>
		<option value="12">已核单</option>
		</select>
		<span>支付状态：</span>
		<select class="querycode" name="payStatus" id="payStatus">
		<option value="">请选择</option>
		<option value="0">已支付</option>
		<option value="1">未支付</option>
		<option value="2">支付失败</option>
		</select>
		<button class="addquery" onclick="search()">查询</button>
	</div>
</div>
<div class="colorhei"></div>
<div class="orderkli">
	<div class="orderhead">
		<table border="0">
		  <tr>
		   <c:forEach items="${payStatusCountList  }" var="payStatus">
			<td><c:if test="${payStatus.payStatus == '1' }"><a href="javascript:;" onclick="findPayStatus('1');">等待支付(${payStatus.count })</a></c:if>
			<c:if test="${payStatus.payStatus == '0' }"><a href="javascript:;" onclick="findPayStatus('0');">等待出票(${payStatus.count })</a></c:if>
			 </td>
			</c:forEach>
			<c:forEach items="${orderStatusCountList }" var="orderStatus">
				<td>
				<c:if test="${orderStatus.orderStatus == '1' }"><a href="javascript:;" onclick="findOrderStatus('1')">下单成功(${orderStatus.count })</a></c:if>
				<c:if test="${orderStatus.orderStatus == '2' }"><a href="javascript:;" onclick="findOrderStatus('2')">订单审核中(${orderStatus.count })</a></c:if>
				<c:if test="${orderStatus.orderStatus == '6' }"><a href="javascript:;" onclick="findOrderStatus('6')">出票完成(${orderStatus.count })</a></c:if>
				<c:if test="${orderStatus.orderStatus == '3' }"><a href="javascript:;" onclick="findOrderStatus('3')">拒接审核(${orderStatus.count })</a></c:if>
				</td>
			</c:forEach>
		  </tr>
		</table>
	</div>
	<div id="detailPage">
		<jsp:include page="tbTicketorderDetailListPage.jsp"></jsp:include>
	</div>
	
</div>
</body>
</html>