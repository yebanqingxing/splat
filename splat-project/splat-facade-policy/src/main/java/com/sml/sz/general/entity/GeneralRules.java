/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.general.entity;

import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sml.sz.common.persistence.DataEntity;

/**
 * 国际政策总则的增删改查Entity
 * @author 张权
 * @version 2016-03-09
 */
public class GeneralRules extends DataEntity<GeneralRules> {
	
	private static final long serialVersionUID = 1L;
	private String grId;		// 总则ID 主键
	private String supplierId;		// 供应商ID
	private String createUserId;		// 创建用户id
	private String generalStatus;		// 总则状态，0 启用，1禁用
	private String noItReward;		// IT无奖励 Y/N
	private String noOpenReward;		// OPEN无奖励票价,选中Y 不选择N
	private String noLowReward;		// 低于XX票价无奖励,是否选中，选中了则
	private String noLowPrice;		// 低于XX票价无奖励 no_low_reward 为Y 时候才起作用
	private String noInvolveReward;		// 含XX票价无奖励,默认未选中N
	private String noInvolvePrice;		// 含XX票价无奖励 no_involve_reward 为Y 时候才起作用
	private String goOriginChoice;		// od 去程起点:1-出票航第一个航段的起点,2-出票航实际承运第一个航段起点,3-第一个国际段的起点
	private String goDestinationChoice;		// od 去程终点:1-出票航飞到的最远点（里程）,2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
	private String backDestinationChoice;		// od 回程终点:1-出票航最后一个航段的终点,2-出票航承运的最后一个航段终点,3-最后一个国际段的终点
	private String dataChoice;		// 数据选取 0 最新日期，1销售价最高，2销售价最低
	private String addOnChoice;		// addon 段 选项 Y/N
	private String addOnInternalChoice;		// addon 国内 Y/N,只有add_on_choice 为Y才起作用
	private String addOnWorldChoice;		// addon 国际 Y/N,只有add_on_choice 为Y才起作用
	private String spaChoice;		// spa 段 Y/N
	private String qChoice;		// 值计入奖励 Y/N
	private Date effectDate;		// 生效日期
	private String formulaId;		// 计算方式
	private String poundage;		// 默认手续费
	private String updateUser;		// update_user
	private String airCode;			//航司二字码
	
	public String getAirCode() {
		return airCode;
	}

	public String getqChoice() {
		return qChoice;
	}

	public void setqChoice(String qChoice) {
		this.qChoice = qChoice;
	}

	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}

	public GeneralRules() {
		super();
	}

	public GeneralRules(String id){
		super(id);
	}

	public String getStrDate(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String temp ="";
		if(null != this.effectDate){
			temp = sim.format(this.effectDate);
		}
		return temp;
	}
	
	@Length(min=1, max=32, message="总则ID 主键长度必须介于 1 和 32 之间")
	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}
	
	@Length(min=1, max=32, message="供应商ID长度必须介于 1 和 32 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=32, message="创建用户id长度必须介于 0 和 32 之间")
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	@Length(min=1, max=1, message="总则状态，0 启用，1禁用长度必须介于 1 和 1 之间")
	public String getGeneralStatus() {
		return generalStatus;
	}

	public void setGeneralStatus(String generalStatus) {
		this.generalStatus = generalStatus;
	}
	
	@Length(min=0, max=1, message="IT无奖励 Y/N长度必须介于 0 和 1 之间")
	public String getNoItReward() {
		return noItReward;
	}

	public void setNoItReward(String noItReward) {
		this.noItReward = noItReward;
	}
	
	@Length(min=0, max=1, message="OPEN无奖励票价,选中Y 不选择N长度必须介于 0 和 1 之间")
	public String getNoOpenReward() {
		return noOpenReward;
	}

	public void setNoOpenReward(String noOpenReward) {
		this.noOpenReward = noOpenReward;
	}
	
	@Length(min=0, max=1, message="低于XX票价无奖励,是否选中，选中了则长度必须介于 0 和 1 之间")
	public String getNoLowReward() {
		return noLowReward;
	}

	public void setNoLowReward(String noLowReward) {
		this.noLowReward = noLowReward;
	}
	
	public String getNoLowPrice() {
		return noLowPrice;
	}

	public void setNoLowPrice(String noLowPrice) {
		this.noLowPrice = noLowPrice;
	}
	
	@Length(min=0, max=1, message="含XX票价无奖励,默认未选中N长度必须介于 0 和 1 之间")
	public String getNoInvolveReward() {
		return noInvolveReward;
	}

	public void setNoInvolveReward(String noInvolveReward) {
		this.noInvolveReward = noInvolveReward;
	}
	
	public String getNoInvolvePrice() {
		return noInvolvePrice;
	}

	public void setNoInvolvePrice(String noInvolvePrice) {
		this.noInvolvePrice = noInvolvePrice;
	}
	
	@Length(min=0, max=1, message="od 去程起点:1-出票航第一个航段的起点,2-出票航实际承运第一个航段起点,3-第一个国际段的起点长度必须介于 0 和 1 之间")
	public String getGoOriginChoice() {
		return goOriginChoice;
	}

	public void setGoOriginChoice(String goOriginChoice) {
		this.goOriginChoice = goOriginChoice;
	}
	
	@Length(min=0, max=1, message="od 去程终点:1-出票航飞到的最远点（里程）,2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）长度必须介于 0 和 1 之间")
	public String getGoDestinationChoice() {
		return goDestinationChoice;
	}

	public void setGoDestinationChoice(String goDestinationChoice) {
		this.goDestinationChoice = goDestinationChoice;
	}
	
	@Length(min=0, max=1, message="od 回程终点:1-出票航最后一个航段的终点,2-出票航承运的最后一个航段终点,3-最后一个国际段的终点长度必须介于 0 和 1 之间")
	public String getBackDestinationChoice() {
		return backDestinationChoice;
	}

	public void setBackDestinationChoice(String backDestinationChoice) {
		this.backDestinationChoice = backDestinationChoice;
	}
	
	@Length(min=0, max=1, message="数据选取 0 最新日期，1销售价最高，2销售价最低长度必须介于 0 和 1 之间")
	public String getDataChoice() {
		return dataChoice;
	}

	public void setDataChoice(String dataChoice) {
		this.dataChoice = dataChoice;
	}
	
	@Length(min=0, max=1, message="addon 段 选项 Y/N长度必须介于 0 和 1 之间")
	public String getAddOnChoice() {
		return addOnChoice;
	}

	public void setAddOnChoice(String addOnChoice) {
		this.addOnChoice = addOnChoice;
	}
	
	@Length(min=0, max=1, message="addon 国内 Y/N,只有add_on_choice 为Y才起作用长度必须介于 0 和 1 之间")
	public String getAddOnInternalChoice() {
		return addOnInternalChoice;
	}

	public void setAddOnInternalChoice(String addOnInternalChoice) {
		this.addOnInternalChoice = addOnInternalChoice;
	}
	
	@Length(min=0, max=1, message="addon 国际 Y/N,只有add_on_choice 为Y才起作用长度必须介于 0 和 1 之间")
	public String getAddOnWorldChoice() {
		return addOnWorldChoice;
	}

	public void setAddOnWorldChoice(String addOnWorldChoice) {
		this.addOnWorldChoice = addOnWorldChoice;
	}
	
	@Length(min=0, max=1, message="spa 段 Y/N长度必须介于 0 和 1 之间")
	public String getSpaChoice() {
		return spaChoice;
	}

	public void setSpaChoice(String spaChoice) {
		this.spaChoice = spaChoice;
	}
	
	@Length(min=0, max=1, message="值计入奖励 Y/N长度必须介于 0 和 1 之间")
	public String getQChoice() {
		return qChoice;
	}

	public void setQChoice(String qChoice) {
		this.qChoice = qChoice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	
	@Length(min=0, max=11, message="计算方式长度必须介于 0 和 11 之间")
	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	
	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	
	@Length(min=0, max=32, message="update_user长度必须介于 0 和 32 之间")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}