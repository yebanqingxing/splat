<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/include.jsp"%> 
<html>
<head>
    <title>我的面板</title>
    
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:800px;}
		p{margin-left:5px; font-size:14px;}
	</style>
</head>
<!DOCTYPE html>

<body>
	 <div id="g1">
	     <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                    	<a >筛选条件</a>
	                        <div id="ck1" name="product1" class="mini-checkbox" checked="true" readOnly="false" text="紧急" onvaluechanged="onValueChanged"></div>
	             			<div id="ck2" name="product2" class="mini-checkbox" checked="true" readOnly="false" text="一般" onvaluechanged="onValueChanged"></div>
	             
	                    </td>
	                    <td style="white-space:nowrap;"> 
	                        <a >部门流程提醒(2条)</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <div id="datagridApproval" class="mini-datagrid" url="${ctx}/gps/gps/getAlarmJsonData" 
		    	style="width:100%;height:100%;" idField="id" pageSize="10" multiSelect="true">
	        	<div property="columns">
		            <div type="indexcolumn"></div>
		            <!-- <div type="checkcolumn"></div> -->
		            <div field="alarm_id" width="120" headerAlign="center" vtype="required;email" autoEscape="true" allowSort="true">序号</div>
		            <div field="device_id" width="100" allowSort="true" >创建日期</div>    
		            <div field="local_time" width="100" headerAlign="center" >创建人</div>
		            <div field="local_time" width="100" headerAlign="center" >工作流</div>
		            <div field="alarm_info" width="100" headerAlign="center" >紧急程度</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >请求标题</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >接收日期</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >状态</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >操作</div>            
	        	</div>
	   	</div>
   	</div>
   	<div id="g2">
   		<div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
		            	<a >筛选条件</a>
		                <div id="ck1" name="product1" class="mini-checkbox" checked="true" readOnly="false" text="紧急" onvaluechanged="onValueChanged"></div>
		             	<div id="ck2" name="product2" class="mini-checkbox" checked="true" readOnly="false" text="一般" onvaluechanged="onValueChanged"></div>
		             	<label >消息类型</label>
		             	<input id="combo1" class="mini-combobox" style="width:150px;" textField="text" valueField="id" emptyText="消息类型选择..."/> 
		             	<label >从</label>
			       		<input id="key_from" class="mini-datepicker" style="width:105px;"/>
			       		<label >到</label>
			       		<input id="key_to" class="mini-datepicker" style="width:105px;"/>
	            </table>           
	        </div>
    	</div>
	   	<div id="datagridNotify" class="mini-datagrid" url="${ctx}/gps/gps/getAlarmJsonData" 
		    	style="width:100%;height:100%;" idField="id" pageSize="10" multiSelect="true">
	        	<div property="columns">
		            <div type="indexcolumn"></div>
		            <!-- <div type="checkcolumn"></div> -->
		            <div field="alarm_id" width="120" headerAlign="center" vtype="required;email" autoEscape="true" allowSort="true">序号</div>
		            <div field="alarm_info" width="100" headerAlign="center" >紧急程度</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >消息类型</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >消息过期时间</div>   
		            <div field="alarm_info" width="100" headerAlign="center" >操作</div>            
	        	</div>
	   	</div>
   	</div>
   	<div id="g3">
	   	<div style="width:100%;">
			<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
		    	<table style="width:100%;">
		        	
		        	<tr>
			            <label >创建人</label>
			            <input id="combo2" class="mini-combobox" style="width:140px;" textField="text" valueField="id" emptyText="创建人选择..."/> 
			            <label >工作流</label>
			            <input id="combo3" class="mini-combobox" style="width:140px;" textField="text" valueField="id" emptyText="工作流选择..."/> 
			            <label >状态</label>
			            <input id="combo4" class="mini-combobox" style="width:140px;" textField="text" valueField="id" emptyText="状态选择..."/> 
			        
			            <label >创建日期</label>
				       	<input id="key_from" class="mini-datepicker" style="width:105px;"/>
				       	<label >接收日期</label>
				       	<input id="key_to" class="mini-datepicker" style="width:105px;"/>
				       	<a >筛选条件</a>
			            <div id="ck1" name="product1" class="mini-checkbox" checked="true" readOnly="false" text="紧急" onvaluechanged="onValueChanged"></div>
			            <div id="ck2" name="product2" class="mini-checkbox" checked="true" readOnly="false" text="一般" onvaluechanged="onValueChanged"></div>
			            <div id="ck3" name="product3" class="mini-checkbox" checked="true" readOnly="false" text="关注" onvaluechanged="onValueChanged"></div>
				     </tr>
		   		</table>           
		  	</div>
	    </div>
	   	<div id="datagridProcessQuery" class="mini-datagrid" url="${ctx}/gps/gps/getAlarmJsonData" 
		    	style="width:100%;height:100%;" idField="id" pageSize="10" multiSelect="true">
	        	<div property="columns">
		            <div type="indexcolumn"></div>
		            <!-- <div type="checkcolumn"></div> -->
		            <div field="alarm_id" width="120" headerAlign="center" vtype="required;email" autoEscape="true" allowSort="true">序号</div>
		              <div field="local_time" width="100" headerAlign="center" >创建人</div>
		            <div field="local_time" width="100" headerAlign="center" >工作流</div>
		            <div field="alarm_info" width="100" headerAlign="center" >紧急程度</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >请求标题</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >接收日期</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >状态</div>  
		            <div field="alarm_info" width="100" headerAlign="center" >操作</div>                  
	        	</div>
	   	</div>
   	</div>
   	<div id="g4">
	   	<table class="form-table" border="0" cellpadding="1" cellspacing="2">
	   		<td>
	   			<div id="p1" class="mini-panel" title="信息部管理" iconCls="icon-add" style="width:200px;height:310px;"    
					  onbuttonclick="onbuttonclick">
					 <p> <a href="" id=links >电脑申请</a > </p>
					 <p> <a href="" id=links >电脑维修</a > </p>
					 <p> <a href="" id=links >打印机申请</a > </p>
					 <p> <a href="" id=links >打印机维修</a > </p>
	   		</td>
	   		<td>
	   			<div id="p2" class="mini-panel" title="办公室管理" iconCls="icon-add" style="width:200px;height:310px;"    
					   onbuttonclick="onbuttonclick">
					 <p> <a href="" id=links >办公用品申请</a > </p>
					 <p> <a href="" id=links >办公用品维修</a > </p>
					 <p> <a href="" id=links >会议室申请</a >  </p>
	   		</td>
	   		<td>
	   			<div id="p3" class="mini-panel" title="计财部管理" iconCls="icon-add" style="width:200px;height:310px;"    
					    onbuttonclick="onbuttonclick">
					 <p> <a href="" id=links >差旅费报销</a >  </p>
					 <p> <a href="" id=links >招待费报销</a >  </p>
					 <p> <a href="" id=links >公务借款</a >  </p>
	   		</td>
	   		<td>
	   			<div id="p4" class="mini-panel" title="其他部门管理" iconCls="icon-add" style="width:200px;height:310px;"
					    onbuttonclick="onbuttonclick">
					  <div  id="aaa">
					  <p>  <a href="" id=links >......</a >  </p>
					  <p>  <a href="" id=links >......</a >  </p>
					  <p>  <a href="" id=links >......</a >  </p>
					  <p>  <a href="" id=links >......</a >  </p>
					  </div>  
					  
	   		</td>
	   	</table>
   	</div>
   	<div id="g5">
   		<div id="tabs1" class="mini-tabs"  tabPosition = "left" activeIndex="0" style="width:100%;height:100%;" plain="false">
		    <div title="收件箱" iconCls="icon-add">
		        
		    </div>
		    <div title="发件箱" iconCls="icon-add" >
		       
		    </div>
		    <div title="草稿箱" iconCls="icon-add">
		       
		    </div>
		    <div title="联系人" iconCls="icon-add">
		       
		    </div>
		</div>
   	</div>
</body>
</html>

<script type="text/javascript">

/* var d = document.getElementById("aaa");

d.style.backgroundColor = "red"; */

    var portal = new mini.ux.Portal();
    portal.set({
        style: "width: 100%;height:100%",
        columns: ["50%", "50%"/* , 250 */]
    });
    portal.render(document.body);

    //panel
    portal.setPanels([
        { column: 0, id: "p1", title: "我的流程审批", showCloseButton: false,showCollapseButton: true, height: "32%" ,body:"#g1"},
        { column: 0, id: "p2", title: "消息提醒", showCloseButton: false,showCollapseButton: true, height: "32%" ,body:"#g2"},
        { column: 0, id: "p3", title: "流程查询", showCloseButton: false,showCollapseButton:true, height: "32%" ,body:"#g3"},
        { column: 1, id: "p4", title: "流程申请", showCloseButton: false,showCollapseButton: true, height: "48%" ,body:"#g4"},
        { column: 1, id: "p5", title: "", showCloseButton: false,showCollapseButton: true, height: "48%" ,body:"#g5"}
    ]);
   
   	/* var bodyEl = portal.getPanelBodyEl("p2");
   
    //bodyEl.appendChild(document.getElementById("Button2"));
	bodyEl.appendChild(document.getElementById("Button2")); */
    //获取配置的panels信息
    //var panels = portal.getPanels();
    //alert(panels.length);

alert("http://mini.com");
	
</script>