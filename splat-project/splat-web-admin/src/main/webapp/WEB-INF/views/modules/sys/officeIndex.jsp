<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
		    	<a class="accordion-toggle">组织机构<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
		    </div>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right" style="min-height:942px;">
			<iframe id="officeContent" src="${ctx}/sys/office/list?platType=A" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		var ztreeObject;
		var Zsetting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id;
					$('#officeContent').attr("src","${ctx}/sys/office/list?platType=A&parent.id="+id);
				}
			}
		};
		
		function refreshTree(){
			var isAll = false;
			if(${platType == A}){
				isAll = true;
			}
			$.getJSON("${ctx}/sys/office/treeData?platType=A&isAll="+isAll,function(data){
				var comp = $.fn.zTree.init($("#ztree"), Zsetting, data).expandAll(true);
				if(comp){
					ztreeObject = $.fn.zTree.getZTreeObj("ztree");
				}else{
					console.log("不能赋值");
				}
			});
		}
		refreshTree();
		var leftWidth = 180; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>