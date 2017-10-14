<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
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
		$(document).ready(function() {
			parent.refreshTree();
			var tpl = $("#treeTableTpl").html();
			if(tpl!="" && tpl!=undefined){
				tpl=tpl.replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			}
			var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});
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
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/office/list?platType=A");
			$("#searchForm").submit();
	    	return false;
	    }
		//新增部门
		function goAddDept(){
			$("#searchForm").attr("action","${ctx}/sys/office/departForm");
			$("#searchForm").submit();
		}
		//日期插件
		$(function(){
		  	var objclear,
		  		objval;
		    $( "#start" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: false,
		      changeYear:false,
		      dateFormat:"yy-mm-dd",
		      numberOfMonths: 1,
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
		      numberOfMonths: 1,
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
	</script>
</head>
<body>
	<style>
		.form-horizontal li {
			margin-bottom:15px;
		}
		.control-label {
			margin-right:6px;
		}
	</style>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">机构列表</a></li>
		<shiro:hasPermission name="sys:office:edit">
			<li>
				<a href="${ctx}/sys/office/form?platType=C">
					供应商添加
				</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:office:edit">
			<li>
				<a href="${ctx}/sys/office/form?platType=B">采购商添加</a>
			</li>
		</shiro:hasPermission>
	</ul> --%>
	<br>
	<form:form id="searchForm" modelAttribute="office" action="${ctx}/sys/office/list?platType=A" method="post" class="form-horizontal">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li>
			<label class="control-label">机构名称:</label>
			<form:input path="name" htmlEscape="true" maxlength="50" class="required"/>
		</li>
		<li>
			<label class="control-label">联系人名称:</label>
			<form:input path="master" htmlEscape="true" maxlength="50" class="required"/>
		</li>
		<li>
			<label class="control-label">状态:</label>
			<form:select path="useable" >
				<form:option value = "1" label = "是"/>
				<form:option value = "0" label = "否"/>
			</form:select>
		</li>
		<li style="margin:20px 0px 20px 0px;">
			<label >日&nbsp;&nbsp;&nbsp;期：</label>
			<input type="text" id="start" name = "beginDate" class="time"/> 
			<label >至&nbsp;&nbsp;&nbsp;</label>
			<input type="text" id="end" name = "endDate" class="time"/> 
		</li>
		<li class="btns" style="margin-top:3px;">
			<input id="btnSubmit" class="btn btn-sm green" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-sm green" type="button" value="增加新部门" onclick="goAddDept();"/>
			<!-- <input class="btn btn-sm green" type="button" value="增加新公司" onclick="goAddCompany();"/> -->
		</li>
	</form:form>
	<sys:message content="${message}"/>
	<%-- <table id="treeTable" class="table table-striped table-bordered table-condensed flip-content">
		<thead class="flip-content"><tr><th>采购商名称</th><th>联系人</th><th>电话</th><th>座机</th><th>状态</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/office/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.master}}</td>
			<td>{{row.phone}}</td>
			<td>{{row.phone}}</td>
			<td>{{row.useable}}</td>
			<shiro:hasPermission name="sys:office:edit"><td>
                <a href="${ctx}/sys/office/form?id={{row.id}}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
    			<a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				<a href="${ctx}/sys/office/form?parent.id={{row.id}}" class="btn btn-xs green">添加下级机构 <i class="fa fa-plus"></i></a>
			</td></shiro:hasPermission>
		</tr>
	</script> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed flip-content" style="margin-top:40px;">
		<thead class="flip-content"><tr><th>机构名称</th><th>类型</th><th class="name">联系人</th><th>电话</th><th>座机</th><th>状态</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:if test="${page.list == null||fn:length(page.list) ==0}">
		   <tr><td colspan="7" style="text-align: center;height: 40px;line-height: 40px;">暂无数据</td></tr>
		</c:if>
		<c:forEach items="${page.list}" var="office">
			<tr>
				<td>${office.name}</td>
				<td>
				<c:if test="${office.type==1}">公司</c:if>
				<c:if test="${office.type==2}">部门</c:if>
				</td>
				<td>${office.master}</td>
				<td>${office.phone}</td>
				<td>${office.phone}</td>
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
				<td>
					<a href="${ctx}/sys/office/officeDetial?id=${office.id}" class="btn btn-xs red">详情 <i class="fa fa-edit"></i></a>
					<a href="${ctx}/sys/office/departForm?id=${office.id}" class="btn btn-xs red">修改 <i class="fa fa-edit"></i></a>
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
					<%-- <a href="${ctx}/sys/office/buyerForm?id=${office.id}" class="btn btn-xs red">重置密码 <i class="fa fa-edit"></i></a> --%>
					<a href="#" class="btn btn-xs red">查看配置 <i class="fa fa-edit"></i></a>
    				<a href="${ctx}/sys/office/delete?id=${office.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-xs purple">删除 <i class="fa fa-times"></i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<sys:message content="${message}"/>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		window.onload=function(){
			setTimeout(function(){
				var wid = $('#right', parent.document).width() - 15;
				$('#right', parent.document).css("width",wid);
			},100)
		}
	</script>
</body>
</html>