<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		function stop(id){
			$.ajax({
				 type:"GET",
	             url:"${ctx}/sys/office/stopOrStart",
	             data:{"id":id},
	             success:function(data){
	            	 if(data == '0'){
	            		 $("#span_"+id).text("启用");
	            		 $("#p_"+id).text("停用");
	            	 }else if(data == '1'){
	            		 $("#span_"+id).text("停用");
	            		 $("#p_"+id).text("启用");
	            	 }
	             }	
			});
		}
		$(function(){
		  	var objclear,
		  		objval;
		    $( "#start" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: false,
		      changeYear:false,
		      dateFormat:"yy-mm-dd",
		      numberOfMonths: 2,
		      duration:'normal',
			  minDate: null,//null所有时间都可以选择
			  showButtonPanel:true,//是否显示清空按钮
			  closeText:"",
			  beforeShow : function(input){
			  	objclear = input;
			  },
		      onClose: function(selectedDate) {
		      	objval = selectedDate;
		      	if(selectedDate == ''){
		      		$( "#end" ).datepicker( "option", "minDate", null);
		      	}else{
		      		$( "#end" ).datepicker( "option", "minDate", selectedDate);
		      	}
		      }
		    });
		    $( "#end" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: false,
		      changeYear:false,
		      dateFormat:"yy-mm-dd",
		      numberOfMonths: 2,
		      showButtonPanel:true,//是否显示清空按钮
		      closeText:"",
		      minDate: null,
		      beforeShow : function(input){
			  	objclear = input;
			  },
		      onClose: function(selectedDate){
		      	objval = selectedDate;
		        if(selectedDate == ''){
		      		$( "#start" ).datepicker( "option", "maxDate", null);
		      	}else{
		      		$( "#start" ).datepicker( "option", "maxDate", selectedDate);
		      	}
		      }
		    });
		    $(".ui-datepicker-close").live("click",function(){

		    	objclear.value = "";
				   var dates = $("#start,#end");
				  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
				   dates.datepicker("option", "minDate", null);
				   dates.datepicker("option", "maxDate", null);
		    })
		    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
		    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
		    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
		    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
		  });
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/office/supplierList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">供应商列表</a></li>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="office" action="${ctx}/sys/office/supplierList?type=1" method="post" class="form-horizontal">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="platType" value = "S"/>
		<form:hidden path="type" value = "1"/>
		<div class="query clear">
			<span class="query-span" style="width: 90px; display: inline-block;">采购商名称：</span>
			<form:input path="name" htmlEscape="true" maxlength="50" class="required querycode fl"/>
			<span class="query-span">状态信息：</span>
			<form:select path="useable" class="selectquery fl"  style="margin-top:0;">
				<form:option value = "1" label = "是"/>
				<form:option value = "0" label = "否"/>
			</form:select>
			<span class="query-span">联系人名称：</span>
			<form:input path="master" htmlEscape="true" maxlength="50" class="required querycode fl"/>
		</div>
		<div class="query">
			<span class="query-span" style="width: 90px; display: inline-block;">时间信息：</span>
			<input id = "start" type="text" value="" name = "beginDate" readonly="readonly" class="doubledate ipticon time" style="margin-right: 0;">
			<span>至</span>
			<input id = "end" type="text" value="" name =  "endDate" readonly="readonly" class="doubledate ipticon time">
			<button class="addquery">查询</button>
			<!-- <button class="addquery">添加</button> -->
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed flip-content" style="margin-top:40px;">
		<thead class="flip-content"><tr><th>供应商名称</th><th class="name">联系人</th><th>电话</th><th>座机</th><th>状态</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:if test="${page.list == null||fn:length(page.list) ==0}">
		   <tr><td colspan="7" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
		</c:if>
		<c:forEach items="${page.list}" var="office">
			<tr>
				<td>${office.name}</td>
				<td>${office.master}</td>
				<td>${office.phone}</td>
				<td>${office.platType}</td>
				<c:choose>
					<c:when test="${office.useable == '1' }">
						<td>
							<p id = "p_${office.id }">启用</p>
						</td>
					</c:when>
					<c:otherwise>
						<td id = "td__${office.id }">
							<p id = "p_${office.id }">停用</p>
						</td>
					</c:otherwise>
				</c:choose>				
				<shiro:hasPermission name="sys:user:edit">
					<td>
						<a href="${ctx}/sys/office/officeDetial?id=${office.id}" class="btn btn-xs red">详情 <i class="fa fa-edit"></i></a>
						<a href="${ctx}/sys/office/supplierForm?id=${office.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
						<c:choose>
							<c:when test="${office.useable == '1' }">
								<a class="btn btn-xs red" onclick = stop("${office.id}")>
									<span id = "span_${office.id }">停用</span>
									<i class="fa fa-edit"></i>
								</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-xs red" onclick = stop("${office.id}")>
									<span id = "span_${office.id }">启用</span>
									<i class="fa fa-edit">
									</i>
								</a>
							</c:otherwise>
						</c:choose>		
						<%-- <a href="${ctx}/sys/office/supplierForm?id=${office.id}" class="btn btn-xs red">重置密码 <i class="fa fa-edit"></i></a> --%>
						<a href="javaScript;" class="btn btn-xs red">查看配置 <i class="fa fa-edit"></i></a>
	    				<%-- <a href="${ctx}/sys/office/delete?id=${office.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a> --%>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>