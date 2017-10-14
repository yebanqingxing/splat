<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctxStatic}/general/js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
function importExcle(){
 var formData = new FormData($("#upId")[0]);  
    $.ajax({  
          url: '${ctx}/policyrules/policyRules/upload',  
	      type: 'POST',  
	      data: formData,  
		  async: false,  
		  cache: false,  
		 contentType: false,  
		 processData: false,  
		 success: function (returndata) {  
			 for(var i = 0; i < returndata.length; i++){
				var obj = $("#backInfo");
				 obj.html(obj.html()+"请查看"+returndata[i].sheetName+"第"+returndata[i].rowNum+"行信息是否正确<br>"); 
			 }
		  },  
		  error: function (returndata) {  
// 		      alert(returndata);  
		      }  
		});  


}
function clicks(){
	if($("#excle").val() == ''){
		alert("请选择上传文件");
	}else{
		$("#msg").text("正在上传……，请勿重复提交！");
		$("#click").attr("disabled","disabled");
		$("#upId").submit();
	}
	
}
</script>
<body>
	<form id = "upId"  method="post" action="${ctx}/policyrules/policyRules/addImportExcle"  enctype="multipart/form-data" style="padding:40px 0 0 30px;">
		<input type="file" id="excle" name = "excle" />
		<input type="button" id="click" onclick="clicks()" value="上传" />
	</form>
	<label id="msg"></label>
	<div id="backInfo">
<%-- 		<c:forEach items="${errInfoList}" var="err"> --%>
<%-- 			请查看${err.sheetName}第${err.rowNum}行信息是否正确<br> --%>
<%-- 		</c:forEach> --%>
	</div>
	<div style="margin-left:30px;">
		<div style="margin:15px 0; display:block;"><a href="${ctx}/policyrules/policyRules/download/import_model.xlsx">导出模板</a></div>
		<span style="font-size:12px; color:#bc0000;">注：请按照模板要求填写，目前校验不完整，不可随意修改模板字段类型;</span>
	</div>
</body>
</html>