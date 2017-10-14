/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.corpinfo.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.sys.entity.User;

/**
 * 企业客户维护Entity
 * @author 黄诗源
 * @version 2016-03-04
 */
public class TbCorpInfo extends DataEntity<TbCorpInfo> {
	
	private static final long serialVersionUID = 1L;
	private String corpId;		// 公司代码
	private String cityId;		// 城市代码
	private String corpCsname;		// 公司简称
	private String corpCname;		// 中文名称
	private String corpEname;		// 英文名称
	private String contactPerson;		// 联系人
	private String tel;		// 电话
	private String fax;		// 传真
	private String email;		// Email
	private String zipCode;		// 邮编
	private String address;		// 地址
	private String corpType;		// 公司类型A.代理;B.大客户;T.销售中心;S.盟同
	private String userNum;		// 用户数目
	private String corpStatus;		// 公司状态
	private String corpNum;		// 所属中心
	private String mobilPhone;		// 手机号码
	private String endday;		// 编码内CT项 0-输入号码1-公司电话
	private User user;		// 所属业务员
	private String relateId;		// 关联代理代码
	private String logoGif;		// 标识图片
	private String logoBmp;		// Office 号
	private String homepage;		// 网址
	private Date startDate;		// 开始日期
	private Date endDate;		// 终止日期
	private String canPost;		// 是否可提供配置/只能取消新订单
	private String payMethod;		// B.异地供应商;Y异地采购商;N.普通用户
	private String commMethod;		// 返佣方式 C.现返;T.后返;N.不返
	private String commRate;		// 返佣比率
	private String isDelivery;		// 是否配送商
	private String commType;		// 返佣类型 R.同行政策;C.全额返佣;P.返代理费;N
	private String commRate3;		// 3%返佣比率
	private String commType3;		// 3%返佣类型 R/C/P/N
	private String smsFormat;		// 单位营业范围
	private String deliveryMethod;		// 缺省出票方式 S/J/Q
	private String deliveryBy;		// 缺省出票机构
	private String cancelPnr;		// 取消订单时是否默认取消编码
	private String cancelBook;		// 取消编码时是否默认取消订单
	private String creditTotal;		// 信用额度
	private String creditUsed;		// 已使用信用额度
	private String smsUser;		// QQ号码
	private String smsPass;		// 单位注册资金
	private String smsSubid;		// 支付密码
	private String smsRate;		// 短信费率
	private String smsFee;		// 未充值余额
	private String hiddenDis;		// 是否隐藏内部政策 Y-隐藏/N
	private String agentQuery;		// 网站查询开通 Y/N
	private String customLine;		// 客服热线
	private String customId;		// 中心-去哪儿政策/客户内部编号
	private String accountId;		// 单位营业执照号
	private String commTypeo;		// 异地返佣方式  R/C/P/N
	private String commRateo;		// 异地返佣比率
	private String payRate;		// 本地支付费率
	private String payRateo;		// 异地支付费率
	private String autoEtdz;		// 确认出票时自动ETDZ自动弹出订单打印界面
	private String autoPrint;		// 自动弹出订单打印界面
	private String isAgent;		// 是否是同业平台用户 1-是
	private String displayBook;		// 订座后是否显示坐席用语
	private String dutyMob;		// 网上订票值班手机
	private String attInfo;		// 值班短信
	private String smsSales;		// 业务对客户评价
	private String smsTotal;		// sms_total
	private String smsFeenum;		// 已使用短信条数
	private String corpLevel;		// 客户级别:0.无星级;1.一星级&hellip;5.五星级/【中心】是否开通有效票号Y
	private String corpTag;		// 查看&rdquo;数据库修改记录&rdquo;
	private String commFee;		// 加收手续费
	private String commFee3;		// 3%代理费时加收手续费
	private String parentCorp;		// 所属代理
	private String accountDate;		// 结算周期  M1每月1日/W3 每周二
	private String billDate;		// 出单日期  M1每月1日/W1每周日
	private String countEnd;		// 统计终止日
	private String delayDate;		// 顺延日  1星期日/2星期一
	private String isTicket;		// 是否出票机构
	private String agenter;		// 二级代理Y允许下级
	private String insFee;		// 保险同行结算金额
	private String ptRate;		// 平台结算手续费金额
	private String ptFee;		// 平台结算手续费率
	private String payVersion;		// 财务收银版本1.新版本
	private String newpayDate;		// 方式日期yyyymmdd
	private String preBdate;		// 上一次出账单日期
	private String nextBdate;		// 下一次出账单日期
	private String md5;		// 接口密钥
	private String prodId;		// 异地平台，对应Ticket_news中Prod_ID
	private String remark;		// 备注
	private String alertCredit;		// 低信用额报警值
	private String outerNo;		// 外部客户CID
	private String debtTotal;		// 允许欠款的最大金额
	private String debtUsed;		// 已欠款金额
	
	public TbCorpInfo() {
		super();
	}

	public TbCorpInfo(String id){
		super(id);
	}

	@Length(min=0, max=6, message="公司代码长度必须介于 0 和 6 之间")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Length(min=0, max=3, message="城市代码长度必须介于 0 和 3 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=16, message="公司简称长度必须介于 0 和 16 之间")
	public String getCorpCsname() {
		return corpCsname;
	}

	public void setCorpCsname(String corpCsname) {
		this.corpCsname = corpCsname;
	}
	
	@Length(min=0, max=64, message="中文名称长度必须介于 0 和 64 之间")
	public String getCorpCname() {
		return corpCname;
	}

	public void setCorpCname(String corpCname) {
		this.corpCname = corpCname;
	}
	
	@Length(min=0, max=64, message="英文名称长度必须介于 0 和 64 之间")
	public String getCorpEname() {
		return corpEname;
	}

	public void setCorpEname(String corpEname) {
		this.corpEname = corpEname;
	}
	
	@Length(min=0, max=16, message="联系人长度必须介于 0 和 16 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=0, max=16, message="电话长度必须介于 0 和 16 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=16, message="传真长度必须介于 0 和 16 之间")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Length(min=0, max=32, message="Email长度必须介于 0 和 32 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=6, message="邮编长度必须介于 0 和 6 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=128, message="地址长度必须介于 0 和 128 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1, message="盟同长度必须介于 0 和 1 之间")
	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	@Length(min=0, max=1, message="公司状态长度必须介于 0 和 1 之间")
	public String getCorpStatus() {
		return corpStatus;
	}

	public void setCorpStatus(String corpStatus) {
		this.corpStatus	= corpStatus;
	}
	
	@Length(min=0, max=8, message="所属中心长度必须介于 0 和 8 之间")
	public String getCorpNum() {
		return corpNum;
	}

	public void setCorpNum(String corpNum) {
		this.corpNum = corpNum;
	}
	
	@Length(min=0, max=11, message="手机号码长度必须介于 0 和 11 之间")
	public String getMobilPhone() {
		return mobilPhone;
	}

	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}
	
	@Length(min=0, max=11, message="编码内CT项 0-输入号码1-公司电话长度必须介于 0 和 11 之间")
	public String getEndday() {
		return endday;
	}

	public void setEndday(String endday) {
		this.endday = endday;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=6, message="关联代理代码长度必须介于 0 和 6 之间")
	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
	@Length(min=0, max=128, message="标识图片长度必须介于 0 和 128 之间")
	public String getLogoGif() {
		return logoGif;
	}

	public void setLogoGif(String logoGif) {
		this.logoGif = logoGif;
	}
	
	@Length(min=0, max=32, message="Office 号长度必须介于 0 和 32 之间")
	public String getLogoBmp() {
		return logoBmp;
	}

	public void setLogoBmp(String logoBmp) {
		this.logoBmp = logoBmp;
	}
	
	@Length(min=0, max=32, message="网址长度必须介于 0 和 32 之间")
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=1, message="是否可提供配置/只能取消新订单长度必须介于 0 和 1 之间")
	public String getCanPost() {
		return canPost;
	}

	public void setCanPost(String canPost) {
		this.canPost = canPost;
	}
	
	@Length(min=0, max=1, message="普通用户长度必须介于 0 和 1 之间")
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Length(min=0, max=1, message="不返长度必须介于 0 和 1 之间")
	public String getCommMethod() {
		return commMethod;
	}

	public void setCommMethod(String commMethod) {
		this.commMethod = commMethod;
	}
	
	public String getCommRate() {
		return commRate;
	}

	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}
	
	@Length(min=0, max=1, message="是否配送商长度必须介于 0 和 1 之间")
	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}
	
	@Length(min=0, max=1, message="返代理费;N长度必须介于 0 和 1 之间")
	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}
	
	public String getCommRate3() {
		return commRate3;
	}

	public void setCommRate3(String commRate3) {
		this.commRate3 = commRate3;
	}
	
	@Length(min=0, max=1, message="3%返佣类型 R/C/P/N长度必须介于 0 和 1 之间")
	public String getCommType3() {
		return commType3;
	}

	public void setCommType3(String commType3) {
		this.commType3 = commType3;
	}
	
	@Length(min=0, max=32, message="单位营业范围长度必须介于 0 和 32 之间")
	public String getSmsFormat() {
		return smsFormat;
	}

	public void setSmsFormat(String smsFormat) {
		this.smsFormat = smsFormat;
	}
	
	@Length(min=0, max=1, message="缺省出票方式 S/J/Q长度必须介于 0 和 1 之间")
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
	@Length(min=0, max=6, message="缺省出票机构长度必须介于 0 和 6 之间")
	public String getDeliveryBy() {
		return deliveryBy;
	}

	public void setDeliveryBy(String deliveryBy) {
		this.deliveryBy = deliveryBy;
	}
	
	@Length(min=0, max=1, message="取消订单时是否默认取消编码长度必须介于 0 和 1 之间")
	public String getCancelPnr() {
		return cancelPnr;
	}

	public void setCancelPnr(String cancelPnr) {
		this.cancelPnr = cancelPnr;
	}
	
	@Length(min=0, max=1, message="取消编码时是否默认取消订单长度必须介于 0 和 1 之间")
	public String getCancelBook() {
		return cancelBook;
	}

	public void setCancelBook(String cancelBook) {
		this.cancelBook = cancelBook;
	}
	
	public String getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(String creditTotal) {
		this.creditTotal = creditTotal;
	}
	
	@Length(min=0, max=11, message="已使用信用额度长度必须介于 0 和 11 之间")
	public String getCreditUsed() {
		return creditUsed;
	}

	public void setCreditUsed(String creditUsed) {
		this.creditUsed = creditUsed;
	}
	
	@Length(min=0, max=16, message="QQ号码长度必须介于 0 和 16 之间")
	public String getSmsUser() {
		return smsUser;
	}

	public void setSmsUser(String smsUser) {
		this.smsUser = smsUser;
	}
	
	public String getSmsPass() {
		return smsPass;
	}

	public void setSmsPass(String smsPass) {
		this.smsPass = smsPass;
	}
	
	@Length(min=0, max=6, message="支付密码长度必须介于 0 和 6 之间")
	public String getSmsSubid() {
		return smsSubid;
	}

	public void setSmsSubid(String smsSubid) {
		this.smsSubid = smsSubid;
	}
	
	public String getSmsRate() {
		return smsRate;
	}

	public void setSmsRate(String smsRate) {
		this.smsRate = smsRate;
	}
	
	@Length(min=0, max=11, message="未充值余额长度必须介于 0 和 11 之间")
	public String getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(String smsFee) {
		this.smsFee = smsFee;
	}
	
	@Length(min=0, max=1, message="是否隐藏内部政策 Y-隐藏/N长度必须介于 0 和 1 之间")
	public String getHiddenDis() {
		return hiddenDis;
	}

	public void setHiddenDis(String hiddenDis) {
		this.hiddenDis = hiddenDis;
	}
	
	@Length(min=0, max=1, message="网站查询开通 Y/N长度必须介于 0 和 1 之间")
	public String getAgentQuery() {
		return agentQuery;
	}

	public void setAgentQuery(String agentQuery) {
		this.agentQuery = agentQuery;
	}
	
	@Length(min=0, max=16, message="客服热线长度必须介于 0 和 16 之间")
	public String getCustomLine() {
		return customLine;
	}

	public void setCustomLine(String customLine) {
		this.customLine = customLine;
	}
	
	@Length(min=0, max=8, message="中心-去哪儿政策/客户内部编号长度必须介于 0 和 8 之间")
	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	@Length(min=0, max=4, message="单位营业执照号长度必须介于 0 和 4 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=1, message="异地返佣方式  R/C/P/N长度必须介于 0 和 1 之间")
	public String getCommTypeo() {
		return commTypeo;
	}

	public void setCommTypeo(String commTypeo) {
		this.commTypeo = commTypeo;
	}
	
	public String getCommRateo() {
		return commRateo;
	}

	public void setCommRateo(String commRateo) {
		this.commRateo = commRateo;
	}
	
	@Length(min=0, max=11, message="本地支付费率长度必须介于 0 和 11 之间")
	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	
	@Length(min=0, max=11, message="异地支付费率长度必须介于 0 和 11 之间")
	public String getPayRateo() {
		return payRateo;
	}

	public void setPayRateo(String payRateo) {
		this.payRateo = payRateo;
	}
	
	@Length(min=0, max=1, message="确认出票时自动ETDZ自动弹出订单打印界面长度必须介于 0 和 1 之间")
	public String getAutoEtdz() {
		return autoEtdz;
	}

	public void setAutoEtdz(String autoEtdz) {
		this.autoEtdz = autoEtdz;
	}
	
	@Length(min=0, max=1, message="自动弹出订单打印界面长度必须介于 0 和 1 之间")
	public String getAutoPrint() {
		return autoPrint;
	}

	public void setAutoPrint(String autoPrint) {
		this.autoPrint = autoPrint;
	}
	
	@Length(min=0, max=1, message="是否是同业平台用户 1-是长度必须介于 0 和 1 之间")
	public String getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}
	
	@Length(min=0, max=1, message="订座后是否显示坐席用语长度必须介于 0 和 1 之间")
	public String getDisplayBook() {
		return displayBook;
	}

	public void setDisplayBook(String displayBook) {
		this.displayBook = displayBook;
	}
	
	@Length(min=0, max=50, message="网上订票值班手机长度必须介于 0 和 50 之间")
	public String getDutyMob() {
		return dutyMob;
	}

	public void setDutyMob(String dutyMob) {
		this.dutyMob = dutyMob;
	}
	
	@Length(min=0, max=100, message="值班短信长度必须介于 0 和 100 之间")
	public String getAttInfo() {
		return attInfo;
	}

	public void setAttInfo(String attInfo) {
		this.attInfo = attInfo;
	}
	
	@Length(min=0, max=1, message="业务对客户评价长度必须介于 0 和 1 之间")
	public String getSmsSales() {
		return smsSales;
	}

	public void setSmsSales(String smsSales) {
		this.smsSales = smsSales;
	}
	
	@Length(min=0, max=11, message="sms_total长度必须介于 0 和 11 之间")
	public String getSmsTotal() {
		return smsTotal;
	}

	public void setSmsTotal(String smsTotal) {
		this.smsTotal = smsTotal;
	}
	
	@Length(min=0, max=11, message="已使用短信条数长度必须介于 0 和 11 之间")
	public String getSmsFeenum() {
		return smsFeenum;
	}

	public void setSmsFeenum(String smsFeenum) {
		this.smsFeenum = smsFeenum;
	}
	
	@Length(min=0, max=1, message="五星级/【中心】是否开通有效票号Y长度必须介于 0 和 4 之间")
	public String getCorpLevel() {
		return corpLevel;
	}

	public void setCorpLevel(String corpLevel) {
		this.corpLevel = corpLevel;
	}
	
	@Length(min=0, max=16, message="查看&rdquo;数据库修改记录&rdquo;长度必须介于 0 和 16 之间")
	public String getCorpTag() {
		return corpTag;
	}

	public void setCorpTag(String corpTag) {
		this.corpTag = corpTag;
	}
	
	public String getCommFee() {
		return commFee;
	}

	public void setCommFee(String commFee) {
		this.commFee = commFee;
	}
	
	public String getCommFee3() {
		return commFee3;
	}

	public void setCommFee3(String commFee3) {
		this.commFee3 = commFee3;
	}
	
	@Length(min=0, max=6, message="所属代理长度必须介于 0 和 6 之间")
	public String getParentCorp() {
		return parentCorp;
	}

	public void setParentCorp(String parentCorp) {
		this.parentCorp = parentCorp;
	}
	
	@Length(min=0, max=3, message="结算周期  M1每月1日/W3 每周二长度必须介于 0 和 3 之间")
	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	
	@Length(min=0, max=3, message="出单日期  M1每月1日/W1每周日长度必须介于 0 和 3 之间")
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	@Length(min=0, max=3, message="统计终止日长度必须介于 0 和 3 之间")
	public String getCountEnd() {
		return countEnd;
	}

	public void setCountEnd(String countEnd) {
		this.countEnd = countEnd;
	}
	
	@Length(min=0, max=3, message="顺延日  1星期日/2星期一长度必须介于 0 和 3 之间")
	public String getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(String delayDate) {
		this.delayDate = delayDate;
	}
	
	@Length(min=0, max=1, message="是否出票机构长度必须介于 0 和 1 之间")
	public String getIsTicket() {
		return isTicket;
	}

	public void setIsTicket(String isTicket) {
		this.isTicket = isTicket;
	}
	
	@Length(min=0, max=1, message="二级代理Y允许下级长度必须介于 0 和 1 之间")
	public String getAgenter() {
		return agenter;
	}

	public void setAgenter(String agenter) {
		this.agenter = agenter;
	}
	
	public String getInsFee() {
		return insFee;
	}

	public void setInsFee(String insFee) {
		this.insFee = insFee;
	}
	
	public String getPtRate() {
		return ptRate;
	}

	public void setPtRate(String ptRate) {
		this.ptRate = ptRate;
	}
	
	public String getPtFee() {
		return ptFee;
	}

	public void setPtFee(String ptFee) {
		this.ptFee = ptFee;
	}
	
	@Length(min=0, max=1, message="新版本长度必须介于 0 和 1 之间")
	public String getPayVersion() {
		return payVersion;
	}

	public void setPayVersion(String payVersion) {
		this.payVersion = payVersion;
	}
	
	@Length(min=0, max=8, message="方式日期yyyymmdd长度必须介于 0 和 8 之间")
	public String getNewpayDate() {
		return newpayDate;
	}

	public void setNewpayDate(String newpayDate) {
		this.newpayDate = newpayDate;
	}
	
	@Length(min=0, max=10, message="上一次出账单日期长度必须介于 0 和 10 之间")
	public String getPreBdate() {
		return preBdate;
	}

	public void setPreBdate(String preBdate) {
		this.preBdate = preBdate;
	}
	
	@Length(min=0, max=10, message="下一次出账单日期长度必须介于 0 和 10 之间")
	public String getNextBdate() {
		return nextBdate;
	}

	public void setNextBdate(String nextBdate) {
		this.nextBdate = nextBdate;
	}
	
	@Length(min=0, max=32, message="接口密钥长度必须介于 0 和 32 之间")
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	@Length(min=0, max=1, message="异地平台，对应Ticket_news中Prod_ID长度必须介于 0 和 1 之间")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getAlertCredit() {
		return alertCredit;
	}

	public void setAlertCredit(String alertCredit) {
		this.alertCredit = alertCredit;
	}
	
	@Length(min=0, max=20, message="外部客户CID长度必须介于 0 和 20 之间")
	public String getOuterNo() {
		return outerNo;
	}

	public void setOuterNo(String outerNo) {
		this.outerNo = outerNo;
	}
	
	public String getDebtTotal() {
		return debtTotal;
	}

	public void setDebtTotal(String debtTotal) {
		this.debtTotal = debtTotal;
	}
	
	public String getDebtUsed() {
		return debtUsed;
	}

	public void setDebtUsed(String debtUsed) {
		this.debtUsed = debtUsed;
	}
	
}