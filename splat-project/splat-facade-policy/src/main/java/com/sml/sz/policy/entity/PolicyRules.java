/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.policy.entity;

import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;
import com.sml.sz.sys.pnr.PersonTypeInfo;

/**
 * 国际政策的增删改查Entity
 * @author 张权
 * @version 2016-03-07
 */
public class PolicyRules extends DataEntity<PolicyRules> {
	
	private static final long serialVersionUID = 1L;
	private String prCode;		// pr_code
	private String policyName;		// 政策名称
	private String policyStatus;		// 状态 0 启用，1禁用，2挂起
	private String supplierId;		// 投放分销商
	private String supplierName;   //投放分销商姓名
	private String companyId;//供应商公司ID
	private String office;		// office
	private String passagerPid;		// 留学生 移民 劳工 海员 青年 老年 探亲
	private String tktAirline;		// 出票航司
	private String passagerCount;		// 旅客人数下限
	private String travelType;		// 行程类型,单程、往返、单程/往返
	//private String groupTravelType;//政策组合行程类型
	
	/**去程起点*/
	private String outOrg;		// 去程起点(三字码机场)
	private String outOrgCityCode;//去程起点城市三字码
	private String outOrgCountryCode;//去程起点国家（二字码）
	private String outOrgRegionCode; //去程起点大洲
	private String outOrgAreaCode;//去程起点大区三字码
	
	/**去程起点除外*/
	private String outOrgEx;		// out_org_ex去程起点除外
	private String outOrgExCityCode;	//去程起点除外城市三字码
	private String outOrgExCountryCode;//去程起点除外国家（二字码）
	private String outOrgExRegionCode; //去程起点除外大洲
	private String outOrgExAreaCode;//去程起点除外大区
	
	/**去程终点*/
	private String outDes;		// 去程终点/折返点（机场三字码）
	private String outDesCityCode;//去程终点城市三字码
	private String outDesCountryCode;//去程终点国家二字码
	private String outDesAreaCode;//去程终点大区三字码
	private String outDesRegionCode;//去程终点大洲
	
	/**去程终点除外*/
	private String outDesEx;		// 去程终点（折返点）除外
	private String outDesExCityCode;//去程终点除外城市三字码
	private String outDesExCountryCode;//去程终点除外国家二字码
	private String outDesExAreaCode;//去程终点除外大区三字码
	private String outDesExRegionCode;//去程终点除外大洲
	
	/**回程终点*/
	private String returnDes;		// 回程终点
	private String returnDesCityCode;//回程终点城市三字码
	private String returnDesCountryCode;//回程终点国家二字码
	private String returnDesAreaCode;//回程终点大区三字码
	private String returnDesRegionCode;//回程终点大洲
	
	
	private String returnDesEx;		// 回程终点除外
	private String outUnablePass;		// 去程不允许经过
	private String outMustPass;		// 去程必须经过
	private String returnUnablePass;		// 回程不允许经过
	private String returnMustPass;		// return_must_pass
	private String allowFilghtNo;		// 仅限航班号
	private String excludeFilghtNo;		// 排除航班号
	private String cabin;		// 舱位
	private String rebate;		// 返点
	private String billingFee;		// 开票费
	private String agencyFee;		// 代理费
	private String childrenReward;		// 儿童奖励情况 :01后返与成人一至，02可开无后返，03不可开,04指定奖励,
	private String childrenRebate;		// 儿童返点
	private String childrenPoundage;		// 儿童手续费
	private String childrenPoundageChoice;		// 儿童加手续费 Y/N
	private String childrenOpenNoCom;		// 儿童可开无代理费 Y/N
	private String badyReward;		// 婴儿奖励情况 0 可开无奖励，1 不可开
	private Date firstDateStart;		// 第一国际段旅行日期 开始
	private Date firstDateEnd;		// 第一国际段旅行日期 结束
	private Date lastDateStart;		// 最后国际段旅行日期 开始
	private Date lastDateEnd;		// 最后国际段旅行日期 结束
	private Date tktTimeStart;		// 出票日期 开始
	private Date tktTimeEnd;		// 出票日期  结束
	private String sharePolicy;		// 是否允许与其他政策组合 Y/N
	private String policyRemark;		// 备注
	private Date workTime;		// 生效时间
	private Date endTime;		// 截止时间
	private String creatUser;		// 创建用户ID
	private String updateUser;		// 更新用户
	private String airFlyState;//转机 or 停留
	
	
	private Double callPrice;//匹配成功的政策结算价
	private Double isPrice;//计奖部分金额
	private Double noPrice;//不计奖部分金额
	private Double price;//票面价
	private Double fare;//不含税的价格
	private Double tax;//税价
	private String priceType;//票价类型
	public List<PersonTypeInfo> personList = new ArrayList<PersonTypeInfo>();
	
	
	
	
	

	public String getOutOrgCityCode() {
		return outOrgCityCode;
	}

	public void setOutOrgCityCode(String outOrgCityCode) {
		this.outOrgCityCode = outOrgCityCode;
	}

	public String getOutOrgCountryCode() {
		return outOrgCountryCode;
	}

	public void setOutOrgCountryCode(String outOrgCountryCode) {
		this.outOrgCountryCode = outOrgCountryCode;
	}

	public String getOutOrgRegionCode() {
		return outOrgRegionCode;
	}

	public void setOutOrgRegionCode(String outOrgRegionCode) {
		this.outOrgRegionCode = outOrgRegionCode;
	}

	public String getOutOrgAreaCode() {
		return outOrgAreaCode;
	}

	public void setOutOrgAreaCode(String outOrgAreaCode) {
		this.outOrgAreaCode = outOrgAreaCode;
	}

	public String getOutOrgExCityCode() {
		return outOrgExCityCode;
	}

	public void setOutOrgExCityCode(String outOrgExCityCode) {
		this.outOrgExCityCode = outOrgExCityCode;
	}

	public String getOutOrgExCountryCode() {
		return outOrgExCountryCode;
	}

	public void setOutOrgExCountryCode(String outOrgExCountryCode) {
		this.outOrgExCountryCode = outOrgExCountryCode;
	}

	public String getOutOrgExRegionCode() {
		return outOrgExRegionCode;
	}

	public void setOutOrgExRegionCode(String outOrgExRegionCode) {
		this.outOrgExRegionCode = outOrgExRegionCode;
	}

	public String getOutOrgExAreaCode() {
		return outOrgExAreaCode;
	}

	public void setOutOrgExAreaCode(String outOrgExAreaCode) {
		this.outOrgExAreaCode = outOrgExAreaCode;
	}

	public String getOutDesCityCode() {
		return outDesCityCode;
	}

	public void setOutDesCityCode(String outDesCityCode) {
		this.outDesCityCode = outDesCityCode;
	}

	public String getOutDesCountryCode() {
		return outDesCountryCode;
	}

	public void setOutDesCountryCode(String outDesCountryCode) {
		this.outDesCountryCode = outDesCountryCode;
	}

	public String getOutDesAreaCode() {
		return outDesAreaCode;
	}

	public void setOutDesAreaCode(String outDesAreaCode) {
		this.outDesAreaCode = outDesAreaCode;
	}

	public String getOutDesRegionCode() {
		return outDesRegionCode;
	}

	public void setOutDesRegionCode(String outDesRegionCode) {
		this.outDesRegionCode = outDesRegionCode;
	}

	public String getOutDesExCityCode() {
		return outDesExCityCode;
	}

	public void setOutDesExCityCode(String outDesExCityCode) {
		this.outDesExCityCode = outDesExCityCode;
	}

	public String getOutDesExCountryCode() {
		return outDesExCountryCode;
	}

	public void setOutDesExCountryCode(String outDesExCountryCode) {
		this.outDesExCountryCode = outDesExCountryCode;
	}

	public String getOutDesExAreaCode() {
		return outDesExAreaCode;
	}

	public void setOutDesExAreaCode(String outDesExAreaCode) {
		this.outDesExAreaCode = outDesExAreaCode;
	}

	public String getOutDesExRegionCode() {
		return outDesExRegionCode;
	}

	public void setOutDesExRegionCode(String outDesExRegionCode) {
		this.outDesExRegionCode = outDesExRegionCode;
	}

	public String getReturnDesCityCode() {
		return returnDesCityCode;
	}

	public void setReturnDesCityCode(String returnDesCityCode) {
		this.returnDesCityCode = returnDesCityCode;
	}

	public String getReturnDesCountryCode() {
		return returnDesCountryCode;
	}

	public void setReturnDesCountryCode(String returnDesCountryCode) {
		this.returnDesCountryCode = returnDesCountryCode;
	}

	public String getReturnDesAreaCode() {
		return returnDesAreaCode;
	}

	public void setReturnDesAreaCode(String returnDesAreaCode) {
		this.returnDesAreaCode = returnDesAreaCode;
	}

	public String getReturnDesRegionCode() {
		return returnDesRegionCode;
	}

	public void setReturnDesRegionCode(String returnDesRegionCode) {
		this.returnDesRegionCode = returnDesRegionCode;
	}

	public List<PersonTypeInfo> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonTypeInfo> personList) {
		this.personList = personList;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAirFlyState() {
		return airFlyState;
	}

	public void setAirFlyState(String airFlyState) {
		this.airFlyState = airFlyState;
	}

	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Double getIsPrice() {
		return isPrice;
	}

	public void setIsPrice(Double isPrice) {
		this.isPrice = isPrice;
	}

	public Double getNoPrice() {
		return noPrice;
	}

	public void setNoPrice(Double noPrice) {
		this.noPrice = noPrice;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getCallPrice() {
		return callPrice;
	}

	public void setCallPrice(Double callPrice) {
		this.callPrice = callPrice;
	}

	public PolicyRules() {
		super();
	}

	public PolicyRules(String id){
		super(id);
	}

	//去程结束时间
	public String getFirstDateEndStr(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String temp ="";
		if(null != this.firstDateEnd){
			temp = sim.format(this.firstDateEnd);
		}
		return temp;
	}
	
	
	//去程开始时间
	public String getFirstDateStartStr(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String temp ="";
		if(null != this.firstDateStart){
			temp = sim.format(this.firstDateStart);
		}
		return temp;
	}
	
	//返程结束时间
		public String getLastDateEndStr(){
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String temp ="";
			if(null != this.lastDateEnd){
				temp = sim.format(this.lastDateEnd);
			}
			return temp;
		}
		
		
		//返程开始时间
		public String getLastDateStartStr(){
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String temp ="";
			if(null != this.lastDateStart){
				temp = sim.format(this.lastDateStart);
			}
			return temp;
		}
	
	@Length(min=1, max=32, message="pr_code长度必须介于 1 和 32 之间")
	public String getPrCode() {
		return prCode;
	}

	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	
	@Length(min=0, max=128, message="政策名称长度必须介于 0 和 128 之间")
	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	@Length(min=0, max=1, message="状态 0 启用，1禁用，2挂起长度必须介于 0 和 1 之间")
	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	@Length(min=0, max=32, message="投放分销商长度必须介于 0 和 32 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=8, message="office长度必须介于 0 和 8 之间")
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
	@Length(min=0, max=3, message="留学生 移民 劳工 海员 青年 老年 探亲长度必须介于 0 和 3 之间")
	public String getPassagerPid() {
		return passagerPid;
	}

	public void setPassagerPid(String passagerPid) {
		this.passagerPid = passagerPid;
	}
	
	@Length(min=1, max=64, message="出票航司长度必须介于 1 和 64 之间")
	public String getTktAirline() {
		return tktAirline;
	}

	public void setTktAirline(String tktAirline) {
		this.tktAirline = tktAirline;
	}
	
	@Length(min=0, max=11, message="旅客人数下限长度必须介于 0 和 11 之间")
	public String getPassagerCount() {
		return passagerCount;
	}

	public void setPassagerCount(String passagerCount) {
		this.passagerCount = passagerCount;
	}
	
	@Length(min=0, max=2, message="行程类型,单程、往返、单程/往返长度必须介于 0 和 2 之间")
	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	
	@Length(min=0, max=128, message="去程起点长度必须介于 0 和 128 之间")
	public String getOutOrg() {
		return outOrg;
	}

	public void setOutOrg(String outOrg) {
		this.outOrg = outOrg;
	}
	
	@Length(min=0, max=128, message="out_org_ex长度必须介于 0 和 128 之间")
	public String getOutOrgEx() {
		return outOrgEx;
	}

	public void setOutOrgEx(String outOrgEx) {
		this.outOrgEx = outOrgEx;
	}
	
	@Length(min=0, max=128, message="去程终点/折返点长度必须介于 0 和 128 之间")
	public String getOutDes() {
		return outDes;
	}

	public void setOutDes(String outDes) {
		this.outDes = outDes;
	}
	
	@Length(min=0, max=128, message="去程终点（折返点）除外长度必须介于 0 和 128 之间")
	public String getOutDesEx() {
		return outDesEx;
	}

	public void setOutDesEx(String outDesEx) {
		this.outDesEx = outDesEx;
	}
	
	@Length(min=0, max=128, message="回程终点长度必须介于 0 和 128 之间")
	public String getReturnDes() {
		return returnDes;
	}

	public void setReturnDes(String returnDes) {
		this.returnDes = returnDes;
	}
	
	@Length(min=0, max=128, message="回程终点除外长度必须介于 0 和 128 之间")
	public String getReturnDesEx() {
		return returnDesEx;
	}

	public void setReturnDesEx(String returnDesEx) {
		this.returnDesEx = returnDesEx;
	}
	
	@Length(min=0, max=128, message="去程不允许经过长度必须介于 0 和 128 之间")
	public String getOutUnablePass() {
		return outUnablePass;
	}

	public void setOutUnablePass(String outUnablePass) {
		this.outUnablePass = outUnablePass;
	}
	
	@Length(min=0, max=128, message="去程必须经过长度必须介于 0 和 128 之间")
	public String getOutMustPass() {
		return outMustPass;
	}

	public void setOutMustPass(String outMustPass) {
		this.outMustPass = outMustPass;
	}
	
	@Length(min=0, max=128, message="回程不允许经过长度必须介于 0 和 128 之间")
	public String getReturnUnablePass() {
		return returnUnablePass;
	}

	public void setReturnUnablePass(String returnUnablePass) {
		this.returnUnablePass = returnUnablePass;
	}
	
	@Length(min=0, max=128, message="return_must_pass长度必须介于 0 和 128 之间")
	public String getReturnMustPass() {
		return returnMustPass;
	}

	public void setReturnMustPass(String returnMustPass) {
		this.returnMustPass = returnMustPass;
	}
	
	@Length(min=0, max=128, message="仅限航班号长度必须介于 0 和 128 之间")
	public String getAllowFilghtNo() {
		return allowFilghtNo;
	}

	public void setAllowFilghtNo(String allowFilghtNo) {
		this.allowFilghtNo = allowFilghtNo;
	}
	
	@Length(min=0, max=128, message="排除航班号长度必须介于 0 和 128 之间")
	public String getExcludeFilghtNo() {
		return excludeFilghtNo;
	}

	public void setExcludeFilghtNo(String excludeFilghtNo) {
		this.excludeFilghtNo = excludeFilghtNo;
	}
	
	@Length(min=0, max=128, message="舱位长度必须介于 0 和 128 之间")
	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	
	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
	public String getBillingFee() {
		return billingFee;
	}

	public void setBillingFee(String billingFee) {
		this.billingFee = billingFee;
	}
	
	public String getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(String agencyFee) {
		this.agencyFee = agencyFee;
	}
	
	@Length(min=0, max=2, message="儿童奖励情况 :01后返与成人一至，02可开无后返，03不可开,04指定奖励,长度必须介于 0 和 2 之间")
	public String getChildrenReward() {
		return childrenReward;
	}

	public void setChildrenReward(String childrenReward) {
		this.childrenReward = childrenReward;
	}
	
	public String getChildrenRebate() {
		return childrenRebate;
	}

	public void setChildrenRebate(String childrenRebate) {
		this.childrenRebate = childrenRebate;
	}
	
	public String getChildrenPoundage() {
		return childrenPoundage;
	}

	public void setChildrenPoundage(String childrenPoundage) {
		this.childrenPoundage = childrenPoundage;
	}
	
	@Length(min=0, max=1, message="儿童加手续费 Y/N长度必须介于 0 和 1 之间")
	public String getChildrenPoundageChoice() {
		return childrenPoundageChoice;
	}

	public void setChildrenPoundageChoice(String childrenPoundageChoice) {
		this.childrenPoundageChoice = childrenPoundageChoice;
	}
	
	@Length(min=0, max=1, message="儿童可开无代理费 Y/N长度必须介于 0 和 1 之间")
	public String getChildrenOpenNoCom() {
		return childrenOpenNoCom;
	}

	public void setChildrenOpenNoCom(String childrenOpenNoCom) {
		this.childrenOpenNoCom = childrenOpenNoCom;
	}
	
	@Length(min=0, max=2, message="婴儿奖励情况 0 可开无奖励，1 不可开长度必须介于 0 和 2 之间")
	public String getBadyReward() {
		return badyReward;
	}

	public void setBadyReward(String badyReward) {
		this.badyReward = badyReward;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstDateStart() {
		return firstDateStart;
	}

	public void setFirstDateStart(Date firstDateStart) {
		this.firstDateStart = firstDateStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstDateEnd() {
		return firstDateEnd;
	}

	public void setFirstDateEnd(Date firstDateEnd) {
		this.firstDateEnd = firstDateEnd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastDateStart() {
		return lastDateStart;
	}

	public void setLastDateStart(Date lastDateStart) {
		this.lastDateStart = lastDateStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastDateEnd() {
		return lastDateEnd;
	}

	public void setLastDateEnd(Date lastDateEnd) {
		this.lastDateEnd = lastDateEnd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTktTimeStart() {
		return tktTimeStart;
	}

	public void setTktTimeStart(Date tktTimeStart) {
		this.tktTimeStart = tktTimeStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTktTimeEnd() {
		return tktTimeEnd;
	}

	public void setTktTimeEnd(Date tktTimeEnd) {
		this.tktTimeEnd = tktTimeEnd;
	}
	
	@Length(min=0, max=1, message="是否允许与其他政策组合 Y/N长度必须介于 0 和 1 之间")
	public String getSharePolicy() {
		return sharePolicy;
	}

	public void setSharePolicy(String sharePolicy) {
		this.sharePolicy = sharePolicy;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getPolicyRemark() {
		return policyRemark;
	}

	public void setPolicyRemark(String policyRemark) {
		this.policyRemark = policyRemark;
	}
	
	//创建时间
	public String getWorkTime(){
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String temp ="";
			if(null != this.workTime){
				temp = sim.format(this.workTime);
			}
			return temp;
		}
	

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=32, message="创建用户ID长度必须介于 0 和 32 之间")
	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}
	
	@Length(min=0, max=32, message="更新用户长度必须介于 0 和 32 之间")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}