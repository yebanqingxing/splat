<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/pagepc/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/pagepc/css/pnrimport.css">
<script type="text/javascript" src="${ctxStatic}/pagepc/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/pagepc/js/pnradd.js"></script>
<script type="text/javascript" src="${ctxStatic}/pagepc/js/pnr.js"></script>
<body>
<!-- PNR导入信息 -->
<div class="pnrimport">
	<div class="pnrimport-head">
		<img src="${ctxStatic}/pagepc/images/import1.png">
		<span>PNR导入信息</span>
	</div>
	
	<div class="pnrimport-content pnrflight">
		<!-- PNR号（小编） -->
<!-- 		<div class="thatsok clear"> -->
<!-- 			<div class="thatsok-left fl">PNR号（小编）：</div> -->
<!-- 			<div class="thatok-con fl"> -->
<!-- 				<input type="text" value="" id="explain" name="explain" class="thatsok-input"> -->
<!-- 			</div> -->
<!-- 			<div class="thatok-right"> -->
<!-- 				<span>&lowast;</span> -->
<%-- 				<span>说明:xxxxxxxxxx${updateRtByFlyCompanys }</span> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<!-- PNR输入 -->
		<div class="thatsok clear" style="margin:27px 0 0 60px;">
			<div class="thatsok-left fl">PNR输入${updateRtByFlyCompanys }</div>
			<div class="thatok-con fl">
				<textarea id="PNRContent" name="line" class="thatsok-line"></textarea>
			</div> 
		</div>
		<!-- 出票航空公司 -->
		<div class="thatsok clear" style="margin:27px 0 0 60px;">
			<div class="thatsok-left fl">出票航空公司：</div>
			<div class="thatok-con fl">
				<input type="text"  id="comput" name="comput" class="thatsok-input">
			</div>
		</div>
		<div style="text-align:center; margin:20px 0;">
			<button id="selectPRN" class="thatok-button" onclick="selectPNR()" style=" width:80px;">查 询</button>
		</div>
		<!-- 出票信息详情 -->
<!-- 		<div class="clear"></div> -->
<!-- 		<div class="thatok-infor clear">出票信息详情∨</div> -->
<!-- 		<div class="thatsoknone clear"> -->
<!-- 			<div class="thatsok clear"> -->
<!-- 				<div class="thatsok-left fl">旅客类型：</div> -->
<!-- 				<div class="thatok-con fl"> -->
<!-- 					<div> -->
<!-- 						<label> -->
<!-- 							<input name="adult" type="checkbox" value="" class="adult" /> -->
<!-- 							<span>成人</span> -->
<!-- 							<input name="adultinput" type="text" value="" class="adultinput"> -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<label> -->
<!-- 							<input name="children" type="checkbox" value="" class="adult" /> -->
<!-- 							<span>儿童</span> -->
<!-- 							<input name="childreninput" type="text" value="" class="adultinput"> -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<label> -->
<!-- 							<input name="baby" type="checkbox" value="" class="adult" /> -->
<!-- 							<span>婴儿</span> -->
<!-- 							<input name="babyinput" type="text" value="" class="adultinput"> -->
<!-- 						</label> -->
<!-- 						<label> -->
<!-- 							<span class="redlabel">&lowast;</span> -->
<!-- 							<span>婴儿姓名</span> -->
<!-- 							<input name="babytext" type="text" value="" class="adultinput"> -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="thatsok clear"> -->
<!-- 				<div class="thatsok-left fl">旅客身份：</div> -->
<!-- 				<div class="thatok-con fl" id="addhtml"> -->
<!-- 					<div class="select1 clear"> -->
<!-- 						<select class="select-con"> -->
<!-- 							<option value="学生">学生</option> -->
<!-- 							<option value="军人">军人</option> -->
<!-- 						</select> -->
<!-- 						<input name="stedent" type="text" value="" class="adultinput fl"> -->
<!-- 					</div> -->
<!-- 					<div class="select1 clear"> -->
<!-- 						<select class="select-con"> -->
<!-- 							<option value="学生">学生</option> -->
<!-- 							<option value="军人">军人</option> -->
<!-- 						</select> -->
<!-- 						<input name="stedent" type="text" value="" class="adultinput fl"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="thatok-right"> -->
<!-- 					<button id="addbut" class="addremove">添加</button> -->
<!-- 					<button id="removebut" class="addremove">删除</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>
<div class="pnrborder none"></div>
<!-- 航班信息 -->
<div class="pnrimport none">
	<div class="pnrimport-head">
		<img src="${ctxStatic}/pagepc/images/import2.png">
		<span>航班信息</span>
	</div>
	<div class="pnrflight" id="nr">
		<ul class="pnrflightul clear">
			<li class="licenter">航班及航空公司</li>
			<li class="licenter">起飞时间<br />机场</li>
			<li class="licenter">&nbsp;</li>
<!-- 			<li class="licenter">飞行时长<br />中转</li> -->
			<li class="licenter">到达时间<br />机场</li>
		</ul>
	 <!-- <div class="marin">
			<ul class="clear ultwo">
				<li class="pnrone licenter">加拿大航空公司</li>
				<li class="licenter">
					<p>2016年3月17日18:00</p>
					<p>北京-华盛顿</p>
				</li>
				<li class="licenter">
					<p>2小时</p>
					<img src="${ctxStatic}/pagepc/images/pnrhang.png">
					<p class="transfer">1程中转</p>
				</li>
				<li class="licenter">
					<p>2016年4月10日</p>
					<p>18:00华盛顿</p>
				</li>
			</ul>
			<div class="transfertwo"><span>&lowast;</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div>
		</div>  --> 
	</div>
</div>
<div class="pnrborder none"></div>
<div class="pnrimport marbottom none">
	<div class="pnrimport-head">
		<img src="${ctxStatic}/pagepc/images/import3.png">
		<span>政策信息</span>
	</div>
	<div class="policycon" id="ticket">
		
	<!-- 政策内容 -->
<!-- 		<div class="lichcontent"> -->
<!-- 			<ul class="policyhead clear"> -->
<!-- 				<li>供应商</li> -->
<!-- 				<li>乘客类型</li> -->
<!-- 				<li>乘客身份</li> -->
<!-- 				<li>价格</li> -->
<!-- 				<li>税费</li> -->
<!-- 				<li>代理费率</li> -->
<!-- 				<li>返点</li> -->
<!-- 				<li>类型</li> -->
<!-- 				<li>退改规则</li> -->
<!-- 			</ul> -->
<!-- 			<ul class="clear"> -->
<!-- 				<li>保盛</li> -->
<!-- 				<li>成人</li> -->
<!-- 				<li>学生</li> -->
<!-- 				<li class="price">票面价:50000</li> -->
<!-- 				<li>200</li> -->
<!-- 				<li>3%或0%</li> -->
<!-- 				<li>10%</li> -->
<!-- 				<li>AM票</li> -->
<!-- 				<li>退票</li> -->
<!-- 			</ul> -->
<!-- 			<ul class="clear"> -->
<!-- 				<li class="liwid"></li> -->
<!-- 				<li class="price">销售价:50000</li> -->
<!-- 			</ul> -->
<!-- 			<ul class="clear"> -->
<!-- 				<li class="liwid"></li> -->
<!-- 				<li class="price">结算价:50000</li> -->
<!-- 			</ul> -->
<!-- 			<div class="hideaddpnr clear"> -->
<!-- 				<div class="fr borderhide"></div> -->
<!-- 				<ul class="clear"> -->
<!-- 					<li></li> -->
<!-- 					<li>成人</li> -->
<!-- 					<li>学生</li> -->
<!-- 					<li class="price">票面价:50000</li> -->
<!-- 					<li>200</li> -->
<!-- 					<li>3%或0%</li> -->
<!-- 					<li>10%</li> -->
<!-- 					<li>AM票</li> -->
<!-- 					<li>退票</li> -->
<!-- 				</ul> -->
<!-- 				<ul class="clear"> -->
<!-- 					<li class="liwid"></li> -->
<!-- 					<li class="price">销售价:50000</li> -->
<!-- 				</ul> -->
<!-- 				<ul class="clear"> -->
<!-- 					<li class="liwid"></li> -->
<!-- 					<li class="price">结算价:50000</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 			<div class="pnrclickhide">更多乘客类型&or;</div> -->
<!-- 			<div class="lichbottom clear"> -->
<!-- 				<div class="fl redreset liche"> -->
<!-- 					<span>&lowast;</span> -->
<!-- 					<span>出票前请对PNR进行授权，否则后续无法给您出票</span> -->
<!-- 				</div> -->
<!-- 				<button class="thatok-button fr lichbuton">生成订单<tton> -->
<!-- 			</div> -->
<!-- 		</div> -->

	</div>
</div>
<form id= "submitId" action="${ctx}/order/tbTicketorderDetail/toProductAdd" method="post">
<input type="hidden" name="suppliId" value="" id="suppliId">
<div id = "formId"></div>
</form>
</body>
