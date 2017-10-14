<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生成订单前确认</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/order/css/pnrdetail.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/order/js/ordersuc.js"></script>
<style type="text/css">
	.orderkli {
		margin-left: 50px;
	}
</style>
</head>
<body>
<div class="addpolicy clear">
	<img src="<%=request.getContextPath() %>/static/order/images/orderstage.png">
	<p>生成订单前确认</p>
	<img src="<%=request.getContextPath() %>/static/order/images/cclickreme.png" class="fr clickshowpnr">
</div>
<!-- 航班行程信息 -->
<div class="pnrcontet">
	<div class="clear">
		<div class="effect-head fl">
			<p>航班行程信息：</p>
			<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
		</div>
	</div>
	<div class="orderkli showhide">
		<div class="ordercon">
			<div class="pnrcontethead clear">
				<p class="fl pnrmarleft">去程信息</p>
				<p class="fr pnrmarrig">税费：2000</p>
				<p class="fr pnrmarrig">票面价：5000</p>
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
					<li class="licenter">飞行时长<br>中转</li>
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">加拿大航空公司</li>
						<li class="licenter">
							<p>2016年3月17日18:00</p>
							<p>北京-华盛顿</p>
						</li>
						<li class="licenter">
							<p>2小时</p>
							<img src="<%=request.getContextPath() %>/static/order/images/pnrhang.png">
							<p class="transfer">1程中转</p>
						</li>
						<li class="licenter">
							<p>2016年4月10日</p>
							<p>18:00华盛顿</p>
						</li>
					</ul>
					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div>
				</div>
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">加拿大航空公司</li>
						<li class="licenter">
							<p>2016年3月17日18:00</p>
							<p>北京-华盛顿</p>
						</li>
						<li class="licenter">
							<p>2小时</p>
							<img src="<%=request.getContextPath() %>/static/order/images/pnrhang.png">
							<p class="transfer">1程中转</p>
						</li>
						<li class="licenter">
							<p>2016年4月10日</p>
							<p>18:00华盛顿</p>
						</li>
					</ul>
					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div>
				</div>
			</div>
		</div>
		<div class="ordercon">
			<div class="pnrcontethead clear">
				<p class="fl pnrmarleft">返程信息</p>
				<p class="fr pnrmarrig">税费：2000</p>
				<p class="fr pnrmarrig">票面价：5000</p>
			</div>
			<div class="pnrflight">
				<ul class="pnrflightul clear">
					<li class="licenter">航班及航空公司</li>
					<li class="licenter">起飞时间<br>机场</li>
					<li class="licenter">飞行时长<br>中转</li>
					<li class="licenter">到达时间<br>机场</li>
				</ul>
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">加拿大航空公司</li>
						<li class="licenter">
							<p>2016年3月17日18:00</p>
							<p>北京-华盛顿</p>
						</li>
						<li class="licenter">
							<p>2小时</p>
							<img src="<%=request.getContextPath() %>/static/order/images/pnrhang.png">
							<p class="transfer">1程中转</p>
						</li>
						<li class="licenter">
							<p>2016年4月10日</p>
							<p>18:00华盛顿</p>
						</li>
					</ul>
					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div>
				</div>
				<div class="marin">
					<ul class="clear ultwo">
						<li class="pnrone licenter">加拿大航空公司</li>
						<li class="licenter">
							<p>2016年3月17日18:00</p>
							<p>北京-华盛顿</p>
						</li>
						<li class="licenter">
							<p>2小时</p>
							<img src="<%=request.getContextPath() %>/static/order/images/pnrhang.png">
							<p class="transfer">1程中转</p>
						</li>
						<li class="licenter">
							<p>2016年4月10日</p>
							<p>18:00华盛顿</p>
						</li>
					</ul>
					<div class="transfertwo"><span>∗</span>中转信息：北京—华盛顿   1程中转   华盛顿—洛杉矶</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 乘机人信息 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>乘机人信息：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名（英文）</th>
				<th>性别</th>
				<th>旅客类型</th>
				<th>旅客身份</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>联系电话</th>
			</tr>
			<tr>
				<td>喵星人</td>
				<td>男</td>
				<td>成人</td>
				<td>XXX</td>
				<td>身份证</td>
				<td>2100000003365478525</td>
				<td>18310277566</td>
			</tr>
		</table>
	</div>
</div>
<!-- 乘机费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>乘机费用明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>类型</th>
				<th>票面价(销售)</th>
				<th>计奖价</th>
				<th>代理费</th>
				<th>政策返点</th>
				<th>供应商开票费用</th>
				<th>税费</th>
				<th>单张结算价</th>
			</tr>
			<tr>
				<td>喵星人</td>
				<td>男</td>
				<td>成人</td>
				<td>XXX</td>
				<td>身份证</td>
				<td>100%</td>
				<td>2000</td>
				<td>10%</td>
				<td>1000</td>
			</tr>
		</table>
	</div>
</div>
<!-- 行程单费用明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>报销凭证费用明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble airchide">
		<table border="0" class="airctable">
			<tr>
				<th>姓名</th>
				<th>发票类型</th>
				<th>填写金额不填税</th>
				<th>凭证费用</th>
				<th>税点</th>
			</tr>
			<tr>
				<td>喵星人世界</td>
				<td>2016年3月20日</td>
				<td>喵星人世界喵星人世界</td>
				<td>XXXX</td>
				<td>100%</td>
			</tr>
		</table>
	</div>
</div>
<!-- 订单金额明细 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>订单金额明细：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="single">
		<div class="singlecon">
			<ul class="clear">
				<li class="redreset"><span class="redreset">&lowast;</span>订单总金额：60000元</li>
			</ul>
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>报销凭证费用总计：60000</li>
			</ul>
			<ul class="clear">
				<li><span class="redreset">&lowast;</span>订单费用总计：60000</li>
				<li>机票：6000</li>
				<li>机票税费：300</li>
				<li>报销凭证费用：300</li>
				<li>保险费用：600</li>
			</ul>
		</div>
	</div>
</div>
<!-- 邮寄地址 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>邮寄地址：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="aircrafble clear" style="width: 753px;">
		<div class="airchead">
			<ul class="clear">
				<li>收件人</li>
				<li>电话号码</li>
				<li>邮编</li>
				<li>费用</li>
				<li class="widtable">地址信息</li>
			</ul>
		</div>
		<div class="airchead-left fl">
			<div class="aircontop">
				<div class="hidetop">
					<ul class="clear">
						<li>喵星人</li>
						<li>13953668199</li>
						<li>1008611</li>
						<li>2000</li>
						<li class="widtable">北京市昌平区回龙观北店嘉园13号楼三单元602</li>
					</ul>
				</div>
			</div>
			<div class="airconbot clear">
				<span>邮寄说明：</span>
				<textarea class="rosize fl"></textarea>
			</div>
		</div>
	</div>
</div>
<!-- 留言 -->
<div class="colorhei"></div>
<div class="aircraft">
	<div class="effect-head">
		<p>留言：</p>
		<img src="<%=request.getContextPath() %>/static/order/images/right.jpg">
	</div>
	<div class="rosizehead">
		<textarea></textarea>
		<div class="butbotom">
			<button>确认</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(".clickshowpnr").click(function(){
		$('.alertadd',parent.document).hide();
	})
</script>
</body>
</html>