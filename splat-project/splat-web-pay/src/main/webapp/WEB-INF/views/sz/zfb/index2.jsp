<form id="alipaysubmit" name="alipaysubmit" action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8"
	method="get">
	<input type="hidden" name="sign" value="b591ef6889ad1d28ffb9bafdade08885" />
		<input type="hidden" name="_input_charset" value="utf-8" />
		<input type="hidden"
		name="total_fee" value="0.01" />
		<input type="hidden" name="subject"
		value="123123" />
		<input type="hidden" name="sign_type" value="MD5" />
		<input
		type="hidden" name="service" value="create_direct_pay_by_user" /><input
		type="hidden" name="notify_url"
		value="http://localhost:8070/splat-web-pay/notify_url.jsp" />
		<input
		type="hidden" name="partner" value="2088901668925431" />
		<input
		type="hidden" name="seller_email" value="infosoft@wanfangdata.com.cn" />
		<input
		type="hidden" name="out_trade_no" value="Num0011" />
		<input
		type="hidden" name="payment_type" value="1" />
		<input type="hidden" name="return_url" value="http://localhost:8070/splat-web-pay/return_url.jsp" />
		<input type="submit" value="确认" style="display: none;">
</form>
<script>document.forms['alipaysubmit'].submit();</script>