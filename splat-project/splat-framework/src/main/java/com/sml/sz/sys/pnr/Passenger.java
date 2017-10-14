package com.sml.sz.sys.pnr;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 旅客信息
 * @author zty
 * @create time:2010-07-12
 */
public class Passenger extends DataEntity<Passenger>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;//旅客姓名
	private String identity;//证件号
	private String tktno;//票号
	private String identityType;//证件类型
	private String mileageCard;//里程卡
	private String mileageCardNum;//里程卡号
	//CN/G47740130/CN/27MAY75/F  /12JAN21/WANG/JIAN/P2    
   //签发国+证件+     国籍+出生日期+性别+证件有效期  
	
	private String passportCou;//签发国
	private String birCountry;//国籍
	private String birth;//出生日期
	private String sex;//性别
	private String validData;//证件有效期
	
	private String passType;//旅客类型
	
	private String passIdentity;//旅客身份
	
	private String pName;//解析护照中第几个人
	
	
	
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getTktno() {
		return tktno;
	}
	public void setTktno(String tktno) {
		this.tktno = tktno;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getMileageCard() {
		return mileageCard;
	}
	public void setMileageCard(String mileageCard) {
		this.mileageCard = mileageCard;
	}
	public String getMileageCardNum() {
		return mileageCardNum;
	}
	public void setMileageCardNum(String mileageCardNum) {
		this.mileageCardNum = mileageCardNum;
	}
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", identity=" + identity + ", tktno=" + tktno
				+ ", identityType=" + identityType + ", mileageCard=" + mileageCard + ", mileageCardNum="
				+ mileageCardNum + ", passportCou=" + passportCou + ", birCountry=" + birCountry + ", birth=" + birth
				+ ", sex=" + sex + ", validData=" + validData + ", passType=" + passType + ", passIdentity="
				+ passIdentity + ", pName=" + pName + "]";
	}



	
}
