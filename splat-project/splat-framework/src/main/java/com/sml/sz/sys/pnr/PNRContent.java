package com.sml.sz.sys.pnr;

public class PNRContent {
	private String pnrCode= "";
	private String company = "";
	private String name = "";
	private String code = "";
	private String lineCode = "";
	private String berth = "";
	private String beginCity = "";
	private String arriveCity = "";
	private String beginTime = "";
	private String arriveTime = "";
	private String beginTimeMark="";
	
	private String mobilePhone = "";
	private String IDCardNo = "";//证件号
	//签发国+证件+国籍+出生日期+性别+证件有效期 
	
	private String passportCou;//签发国
	private String birCountry;//国籍
	private String birth;//出生日期
	private String sex;//性别
	private String validData;//证件有效期
	private String pName;//解析护照中的名字
	
	
	// qte 相关内容
	private String price;//票面价（**含税的票面价）
	private String priceUnit;//票价的单位  属于什么币种
	private String state;//转机 or 停留
	private String isContainTax;//是不是含税  01表示含税
	private String fare;//票面价
	private String fareUnit;//票面价（不含税）的单位
	private String tax;//税价
	private String taxUnit;//税的单位
	private String type;//票价类型 it
	private String identity;
	private String commission;//代理费率
	//fc
	public String fcNum;//fc数 
	public String fromCity;
	public String toCity;
	public String rate;//汇率
	public String qString;//Q值
	public String iden;//fc的身份
	
	
	private String passType;//旅客类型
	
	private String passIdentity;//旅客身份
	
	
	private String basePrice;//基础票价
	private String exchangeRate;//汇率
	
	
	
	
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getTaxUnit() {
		return taxUnit;
	}
	public void setTaxUnit(String taxUnit) {
		this.taxUnit = taxUnit;
	}
	public String getFareUnit() {
		return fareUnit;
	}
	public void setFareUnit(String fareUnit) {
		this.fareUnit = fareUnit;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPassType() {
		return passType;
	}
	public void setPassType(String passType) {
		this.passType = passType;
	}
	public String getPassIdentity() {
		return passIdentity;
	}
	public void setPassIdentity(String passIdentity) {
		this.passIdentity = passIdentity;
	}
	public String getqString() {
		return qString;
	}
	public void setqString(String qString) {
		this.qString = qString;
	}
	
	public String getFcNum() {
		return fcNum;
	}
	public void setFcNum(String fcNum) {
		this.fcNum = fcNum;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getBeginTimeMark() {
		return beginTimeMark;
	}
	public void setBeginTimeMark(String beginTimeMark) {
		this.beginTimeMark = beginTimeMark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsContainTax() {
		return isContainTax;
	}
	public void setIsContainTax(String isContainTax) {
		this.isContainTax = isContainTax;
	}
	public String getPassportCou() {
		return passportCou;
	}
	public void setPassportCou(String passportCou) {
		this.passportCou = passportCou;
	}
	public String getBirCountry() {
		return birCountry;
	}
	public void setBirCountry(String birCountry) {
		this.birCountry = birCountry;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getValidData() {
		return validData;
	}
	public void setValidData(String validData) {
		this.validData = validData;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getBeginCity() {
		return beginCity;
	}
	public void setBeginCity(String beginCity) {
		this.beginCity = beginCity;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getBerth() {
		return berth;
	}
	public void setBerth(String berth) {
		this.berth = berth;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}
	public String getIDCardNo()
	{
		return IDCardNo;
	}
	public void setIDCardNo(String cardNo)
	{
		IDCardNo = cardNo;
	}
	
}