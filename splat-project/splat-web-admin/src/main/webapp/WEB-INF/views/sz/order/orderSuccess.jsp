<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国际机票PNR导入生成订单页面</title>

</head>
<<<<<<< .mine
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<div id="main">
		<div id="head">
            <dl class="alipay_link">
                <a target="_blank" href="http://www.alipay.com/"><span>支付宝首页</span></a>|
                <a target="_blank" href="https://b.alipay.com/home.htm"><span>商家服务</span></a>|
                <a target="_blank" href="http://help.alipay.com/support/index_sh.htm"><span>帮助中心</span></a>
            </dl>
            <span class="title">支付宝支付</span>
		</div>
        <div class="cashier-nav">
            <ol>
				<li class="current">1、确认信息 →</li>
				<li>2、点击确认 →</li>
				<li class="last">3、确认完成</li>
            </ol>
        </div>
        <form name=alipayment action="http://localhost:8070/splat-web-pay/a/pay/tbCity/zf" method=post target="_blank">
            <div id="body" style="clear:left">
                <dl class="content">
					
					<dd>
						<span class="null-star">*</span>
						<input size="30" name="WIDseller_email" value="infosoft@wanfangdata.com.cn" type="hidden"/>

					</dd>
					<dt>商户订单号：</dt>
					<dd>
						<span class="null-star">*</span>
						<input size="30" value="${ticketorderDetail.orderNo }" name="WIDout_trade_no" />
						<span>商户网站订单系统中唯一订单号，必填
</span>
					</dd>
					<dt>订单名称：</dt>
					<dd>
						<span class="null-star">*</span>
						<input size="30" name="WIDsubject" />
						<span>必填
</span>
					</dd>
					<dt>付款金额：</dt>
					<dd>
						<span class="null-star">*</span>
						<input size="30" value="0.01" name="WIDtotal_fee" />
						<span>必填
</span>
					</dd>
					<dt>订单描述
：</dt>
					<dd>
						<span class="null-star">*</span>
						<input size="30" name="WIDbody" />
						<span></span>
					</dd>
					<dt>商品展示地址：</dt>
					<dd>
						<span class="null-star">*</span>
						<input size="30" name="WIDshow_url" />
						<span>需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
</span>
					</dd>
                    <dt></dt>
                    <dd>
                        <span class="new-btn-login-sp">
                            <button class="" type="submit" style="text-align:center;">确 认</button>
                        </span>
                    </dd>
                </dl>
            </div>
		</form>
        <div id="foot">
			<ul class="foot-ul">
				<li><font class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。 </font></li>
				<li>
					支付宝版权所有 2011-2015 ALIPAY.COM 
				</li>
			</ul>
		</div>
	</div>
=======
<body>
<div>订单信息</div>
订单号：${ticketorderDetail.orderNo } 预订人： 预定编码PNR：${ticketorderDetail.crsPnr } 开票编码PNR
<br>
订单状态：
<c:if test="${ticketorderDetail.orderStatus  == '1'}">已提交</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '2'}">已申签</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '3'}">已拒单</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '4'}">已签单</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '5'}">已预订</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '6'}">已出票</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '7'}">已删除</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '8'}">已申批</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '9'}">已初核</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '10'}">已复核</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '11'}">已批单</c:if>
<c:if test="${ticketorderDetail.orderStatus  == '12'}">已核单</c:if>
操作状态
<c:if test="${ticketorderDetail.currentStatus  == '1' }">正在核实价格和政策</c:if>
<c:if test="${ticketorderDetail.currentStatus  == '2' }">已经核实价格和政策</c:if>
支付状态
<c:if test="${ticketorderDetail.payStatus =='0' }">已支付</c:if>
<c:if test="${ticketorderDetail.payStatus =='1' }">未支付</c:if>
<c:if test="${ticketorderDetail.payStatus =='2' }">支付失败</c:if>
操作历史：XXX在时间下了订单
<br>
订单总费用：  机票采购费：  机票税费： 报销凭证费用：  保险：
<br>
<div>航班行程信息</div>
<div>去程</div>
<div>
航空公司+航班号 ${ticketorderSegment.operatingAirline } ${ticketorderSegment.operatingFlightNo } 起飞时间：<fmt:formatDate value="${ticketorderSegment.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>    <u> </u>  	起飞机场：
                                                          到达时间：<fmt:formatDate value="${ticketorderSegment.arriveTime }" pattern="yyyy-MM-dd HH:mm:ss"/>                到达机场：
<%--中转时间： --%>                                                                                                                                                                                                       票面价格： 税费：
<%-- 航空公司+航班号 ${ } ${ } 起飞时间：<fmt:formatDate value="${ }" pattern="yyyy-MM-dd HH:mm:ss"/>    <u> </u>  	起飞机场： --%>
<%--                                                           到达时间：<fmt:formatDate value="${ }" pattern="yyyy-MM-dd HH:mm:ss"/>                到达机场： --%>
</div>
<div>乘机人信息</div>
<table>
	<tr>
		<td>姓名（英文）</td>
		<td>性别</td>
		<td>乘客类型</td>
		<td>证件类型</td>
		<td>证件号码</td>
		<td>联系电话</td>
	</tr>
	<tr>
		<td>${ticketorderPassenger.passengerName }</td>
		<td>${ticketorderPassenger.gender == '1'?"男":"女" }</td>
		<td><c:if test="${ticketorderPassenger.passengerType == '0' }">成人</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '1' }">儿童</c:if>
		<c:if test="${ticketorderPassenger.passengerType == '4' }">婴儿</c:if>
		</td>
		<td>
		<c:if test="${ticketorderPassenger.certType == '0' }">身份证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '1' }">护照</c:if> 
		<c:if test="${ticketorderPassenger.certType == '2' }">港澳通行证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '3' }">台湾通行证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '4' }">台胞证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '5' }">回乡证</c:if> 
		<c:if test="${ticketorderPassenger.certType == '8' }">其他</c:if>
		<c:if test="${ticketorderPassenger.certType == '9' }">unknown</c:if>  
		</td>
		<td>${ticketorderPassenger.phone }</td>
	</tr>
</table>
<div>机票费用明细</div>
<table width="90%">
	<tr>
		<td>姓名</td>
		<td>类型</td>
		<td>票面价（销售）</td>
		<td>记奖价</td>
		<td>代理费</td>
		<td>政策返点</td>
		<td>供应商开票费用</td>
		<td>税费</td>
		<td>单张结算价</td>
	</tr>
	<tr>
		<td>li/qianchao</td>
		<td>成人</td>
		<td>2000</td>
		<td>2000</td>
		<td>3%</td>
		<td>6%</td>
		<td>供应商开票费用</td>
		<td>税费</td>
		<td>单张结算价</td>
	</tr>
</table>
<table>
	<tr>
		<td>姓名</td>
		<td>发票类型</td>
		<td>填开金额不含税</td>
		<td>凭证费用</td>
		<td>税点</td>
	</tr>
	<tr>
		<td>li/qianchao</td>
		<td>行程单</td>
		<td>500</td>
		<td>5</td>
		<td>&nbsp;</td>
	</tr>
</table>
<div>报销凭证费用总计：5</div>
<div>邮寄地址</div>
<table>
	<tr>
		<td>收件人</td>
		<td>电话</td>
		<td>邮编</td>
		<td>费用</td>
		<td>地址</td>
	</tr>
</table>
邮件说明
<div>留言</div>
<center><input type="button" value="核实价格和政策"><input type="button" value="取消订单"></center>
>>>>>>> .r439
</body>
</html>