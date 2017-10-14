/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.policy.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.MD5EncryptUtil;
import com.sml.sz.StringUtils;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.general.entity.GeneralRules;
import com.sml.sz.policy.dao.PolicyRulesDao;
import com.sml.sz.policy.entity.PolicyRules;
import com.sml.sz.pubobj.ErrInfo;
import com.sml.sz.pubobj.PubObj;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.pnr.Fc;
import com.sml.sz.sys.pnr.Passenger;
import com.sml.sz.sys.pnr.PersonTypeInfo;
import com.sml.sz.sys.pnr.Qte;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.sys.utils.UserUtils;
import com.sml.sz.sys.utils.excel.ImportExcel;

/**
 * 国际政策的增删改查Service
 * @author 张权
 * @version 2016-03-07
 */
@Component("policyRulesFacade")
@Transactional(readOnly = true)
public class PolicyRulesServiceImpl extends CrudService<PolicyRulesDao, PolicyRules> implements PolicyRulesFacade {

	@Autowired
	private PolicyRulesDao policyRulesDao;
	
	
	/**
	 * 国际政策的增删改查 通过ID 获取
	 * @param String id
	 * @return TbPolicyRules
	 */
	public PolicyRules get(String id) {
		PolicyRules  policyRlues = super.get(id);
		if(!"".equals(policyRlues.getRebate()) || null != policyRlues.getRebate()){
			//Double rebate = Double.parseDouble(policyRlues.getRebate());
//			String rebateStr = rebate.toString();
//			String[] str = rebateStr.split(".");
//			if(str[1].equals("0")){
//				rebateStr = str[0];
//			}
			//float f=(float)(rebate*100);
			policyRlues.setRebate(policyRlues.getRebate());
		}
		if(!"".equals(policyRlues.getAgencyFee()) || null != policyRlues.getAgencyFee()){
			//Double rebate = Double.parseDouble(policyRlues.getAgencyFee());
			//String rebateStr = rebate.toString();
//			String[] str = rebateStr.split(".");
//			if(str[1].equals(0)){
//				rebateStr = str[0];
//			}
			//float f=(float)(rebate*100);
			policyRlues.setAgencyFee(policyRlues.getAgencyFee());
		}
		if(!"".equals(policyRlues.getChildrenRebate()) || null != policyRlues.getChildrenRebate()){
			//Double rebate = Double.parseDouble(policyRlues.getChildrenRebate());
			//String rebateStr = rebate.toString();
//			String[] str = rebateStr.split(".");
//			if(str[1].equals(0)){
//				rebateStr = str[0];
//			}
			//float f=(float)(rebate*100);
			policyRlues.setChildrenRebate(policyRlues.getChildrenRebate());
		}
		return policyRlues;
	}
	
	/**
	 * 国际政策的增删改查 查询不分页
	 * @param PolicyRules
	 * @return List<TbPolicyRules>
	 */
	public List<PolicyRules> findList(PolicyRules tbPolicyRules) {
		//先获取缓存中有没有数据
//		Object corpInfoList = EhCacheUtils.get("a");
//		if(null==corpInfoList){
//			//为空的话是走数据库查询值
//			List<PolicyRules> tbCorpInfoList = super.findList(tbPolicyRules);
//			corpInfoList=tbCorpInfoList;
//			EhCacheUtils.put("a", tbCorpInfoList);
//		}
//		return (List<PolicyRules>)corpInfoList;
		User user = UserUtils.getUser();
		tbPolicyRules.setSupplierId(user.getId());
		return super.findList(tbPolicyRules);
	}
	
	/**
	 * 国际政策的增删改查 查询分页
	 * @param Page<TbPolicyRules> page,TbPolicyRules
	 * @return Page<TbPolicyRules>
	 */
	public Page<PolicyRules> findPage(Page<PolicyRules> page, PolicyRules tbPolicyRules) {
		page.setOld(false);
		page = super.findPage(page, tbPolicyRules);
		int size = page.getList().size();
		for(int i = 0 ; i < size; i++){
			PolicyRules policyRules = page.getList().get(i);
			if(!"".equals(policyRules.getRebate()) || null != policyRules.getRebate()){
				//Double rebate = Double.parseDouble(policyRules.getRebate());
				//float f=(float)(rebate);
				page.getList().get(i).setRebate(policyRules.getRebate());
				
			}
		}
		return page;
	}
	
	/**
	 * 国际政策的增删改查 保存
	 * @param PolicyRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(PolicyRules tbPolicyRules) {
		if("CI".equals(tbPolicyRules.getTktAirline().trim().toUpperCase()) || "AE".equals(tbPolicyRules.getTktAirline().trim().toUpperCase())){
			tbPolicyRules.setTktAirline("CI/AE");
		}else if("AF".equals(tbPolicyRules.getTktAirline().trim().toUpperCase()) ||
				"KL".equals(tbPolicyRules.getTktAirline().trim().toUpperCase())){
			tbPolicyRules.setTktAirline("AF/KL");
		}else if("LH".equals(tbPolicyRules.getTktAirline().trim().toUpperCase()) ||
				"OS".equals(tbPolicyRules.getTktAirline().trim().toUpperCase()) ||
				"LX".equals(tbPolicyRules.getTktAirline().trim().toUpperCase())){
			tbPolicyRules.setTktAirline("LH/OS/LX");
		}
		if(null != tbPolicyRules){
			String[] cabinList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","*"};
			//判断政策名是否为空
			Boolean flag = true;
			//判断office票号是否为空
			Boolean flag1 = true;
			//判断航司是否为空
			Boolean flag2 = true;
			//判断行程类型：单程去程起点是否为空
			Boolean flag3 = true;
			//判断行程类型：单程去程起点除外是否为空
			Boolean flag4 = true;
			//判断行程类型：单程去程终点是否为空
			Boolean flag5 = true;
			//判断行程类型：单程去程终点除外是否为空
			Boolean flag6 = true;
			//判断行程类型：往返回程终点是否为空
			Boolean flag7 = true;
			//判断行程类型：往返回程终点除外是否为空
			Boolean flag8 = true;
			//判断成人返点是否为空
			Boolean flag9 = true;
			//判断成人开票费是否为空
			Boolean flag10 = true;
			//判断儿童返点是否为空
			Boolean flag11 = true;
			//判断去程旅行日期开始是否为空
			Boolean flag12 = true;
			//判断去程旅行日期结束是否为空
			Boolean flag13 = true;
			//添加政策
			if("".equals(tbPolicyRules.getId()) || null == tbPolicyRules.getId()){
				//判断政策名是否为空
				if("".equals(tbPolicyRules.getPolicyName()) || null == tbPolicyRules.getPolicyName()){
					flag = false;
				}
				//判断office票号是否为空
				if("".equals(tbPolicyRules.getOffice()) || null == tbPolicyRules.getOffice()){
					flag1 = false;
				}
				//判断航司是否为空
				if("".equals(tbPolicyRules.getTktAirline()) || null == tbPolicyRules.getTktAirline()){
					flag2 = false;
				}
				
				//判断行程：单程信息为N 往返以及单程/往返为Y
				if("0".equals(tbPolicyRules.getTravelType())){
					//判断行程类型：单程去程起点是否为空
					if(null == tbPolicyRules.getOutOrg()){
						flag3 = false;
					}
					//判断行程类型：单程去程起点除外是否为空
					if(null == tbPolicyRules.getOutOrgEx()){
						flag4 = false;
					}
					//判断行程类型：单程去程终点是否为空
					if(null == tbPolicyRules.getOutOrg()){
						flag5 = false;
					}
					//判断行程类型：单程去程终点除外是否为空
					if(null == tbPolicyRules.getOutOrgEx()){
						flag6 = false;
					}
					//判断行程类型为单程时，回程输入的所有信息无效
					//回程终点无效
					tbPolicyRules.setReturnDes(null);
					//回程终点除外无效
					tbPolicyRules.setReturnDesEx(null);
					//回程终点不能经过无效
					tbPolicyRules.setReturnUnablePass(null);
					//回程终点必须经过无效
					tbPolicyRules.setReturnMustPass(null);
					//返程日期 开始无效
					tbPolicyRules.setLastDateStart(null);
					//返程日期 结束无效
					tbPolicyRules.setLastDateEnd(null);
					//单程下为N
					tbPolicyRules.setSharePolicy("N");
				}else{
					//判断行程类型：单程去程起点是否为空
					if(null == tbPolicyRules.getOutOrg()){
						flag3 = false;
					}
					//判断行程类型：单程去程起点除外是否为空
					if(null == tbPolicyRules.getOutOrgEx()){
						flag4 = false;
					}
					//判断行程类型：往返去程终点是否为空
					if(null == tbPolicyRules.getOutOrg()){
						flag5 = false;
					}
					//判断行程类型：往返去程终点除外是否为空
					if(null == tbPolicyRules.getOutOrgEx()){
						flag6 = false;
					}
					//判断行程类型：往返回程终点是否为空
					if(null == tbPolicyRules.getReturnDes()){
						flag7 = false;
					}
					//判断行程类型：往返回程终点除外是否为空
					if(null == tbPolicyRules.getReturnDes()){
						flag8 = false;
					}
					//往返下为Y
					tbPolicyRules.setSharePolicy("Y");
				}
				//判断成人返点是否为空
				if("".equals(tbPolicyRules.getRebate()) || null == tbPolicyRules.getRebate()){
					flag9 = false;
				}else{
					//Double rebate = Double.parseDouble(tbPolicyRules.getRebate())/100;
					tbPolicyRules.setRebate(tbPolicyRules.getRebate());
				}
				//判断成人开票费是否为空
				if("".equals(tbPolicyRules.getBillingFee()) || null == tbPolicyRules.getBillingFee()){
					flag10 = false;
				}
				//判断儿童返点是否为空
				if("".equals(tbPolicyRules.getChildrenReward()) || null  == tbPolicyRules.getChildrenReward()){
					flag11 = false;
				}
				//判断儿童加手续费是否选中，未选中赋值为N
				if("".equals(tbPolicyRules.getChildrenPoundageChoice()) || null == tbPolicyRules.getChildrenPoundageChoice()){
					tbPolicyRules.setChildrenPoundageChoice("N");
				}
				//判断儿童加手续费是否选中，如未选中输入的手续费无效
				if("N".equals(tbPolicyRules.getChildrenPoundageChoice())){
					tbPolicyRules.setChildrenPoundage("0.00");
				}
				//判断儿童可开无代理费是否选中，未选中赋值为N
				if("".equals(tbPolicyRules.getChildrenOpenNoCom()) || null == tbPolicyRules.getChildrenOpenNoCom()){
					tbPolicyRules.setChildrenOpenNoCom("N");
				}
				//判断去程旅行日期 开始是否为空
				if("".equals(tbPolicyRules.getFirstDateStart()) || null == tbPolicyRules.getFirstDateStart()){
					flag12 = false;
				}
				//判断去程旅行日期 结束是否为空
				if("".equals(tbPolicyRules.getFirstDateEnd()) || null == tbPolicyRules.getFirstDateEnd()){
					flag13 = false;
				}
				
				//判断满足以上所有条件，才可添加信息
				if(flag && flag1 && flag2 && flag3 &&  flag5 &&  flag7  && flag9 && flag11 && flag12 && flag13){
					//判断舱位信息是否合法
					if(null != tbPolicyRules.getCabin()){
						//将合法的舱位信息以：A/B/的格式拼接
						StringBuffer sbfCabin = new StringBuffer();
						String[] cabinArr = tbPolicyRules.getCabin().split("/");
						int m = cabinList.length;
						int n = cabinArr.length;
						for(int i = 0;i<m;i++){
							for(int j=0;j<n;j++){
								if(cabinList[i].trim().toUpperCase().equals(cabinArr[j].trim().toUpperCase())){
									sbfCabin.append(cabinArr[j].trim().toUpperCase());
									sbfCabin.append("/");
								}
							}
						}
						//将拼接的合法舱位放入字段中
						tbPolicyRules.setCabin(sbfCabin.toString().trim());
					}
					if(null != tbPolicyRules.getRebate() || !"".equals(tbPolicyRules.getRebate())){
						//如果返点长度大于4，将其截取
						
						if(tbPolicyRules.getRebate().length()>4){
							tbPolicyRules.setRebate(tbPolicyRules.getRebate().substring(0,5));//0.035
						}
					}
					if(null != tbPolicyRules.getBillingFee()){
						if(!"".equals(tbPolicyRules.getBillingFee())){
							//如果开票费长度大于4，将其截取
							tbPolicyRules.setBillingFee(tbPolicyRules.getBillingFee());
							if(tbPolicyRules.getBillingFee().length()>4){
								tbPolicyRules.setBillingFee(tbPolicyRules.getBillingFee().substring(0,4));
							}
						}else{
							tbPolicyRules.setBillingFee("0.00");
						}
					}else{
							tbPolicyRules.setBillingFee("0.00");
					}
					if(null != tbPolicyRules.getAgencyFee()){
						if(!"".equals(tbPolicyRules.getAgencyFee())){
						//如果代理费长度大于4，将其截取
						Double rebate = Double.parseDouble(tbPolicyRules.getAgencyFee());
						tbPolicyRules.setAgencyFee(rebate.toString());
						if(tbPolicyRules.getAgencyFee().length()>4){
							tbPolicyRules.setAgencyFee(tbPolicyRules.getAgencyFee().substring(0,4));
						}
						}else{
							tbPolicyRules.setAgencyFee("0.00");	
						}
					}else{
						tbPolicyRules.setAgencyFee("0.00");
					}
					//如果儿童返点长度大于4，将其截取
					if(null != tbPolicyRules.getChildrenRebate()){
						if( !"".equals(tbPolicyRules.getChildrenRebate())){
							Double rebate = Double.parseDouble(tbPolicyRules.getChildrenRebate());
							tbPolicyRules.setChildrenRebate(rebate.toString());
							if(tbPolicyRules.getChildrenRebate().length()>4){
								tbPolicyRules.setChildrenRebate(tbPolicyRules.getChildrenRebate().substring(0,4));
							}
						}else{
							tbPolicyRules.setChildrenRebate("0.00");
						}
					
					}else{
						tbPolicyRules.setChildrenRebate("0.00");
					}
					if(null != tbPolicyRules.getChildrenPoundage()){
						if(!"".equals(tbPolicyRules.getChildrenPoundage())){
							tbPolicyRules.setChildrenPoundage(tbPolicyRules.getChildrenPoundage());
							//如果儿童手续费长度大于4，将其截取
							if(tbPolicyRules.getChildrenPoundage().length()>4){
								tbPolicyRules.setChildrenPoundage(tbPolicyRules.getChildrenPoundage().substring(0,4));
							}
						}else{
							tbPolicyRules.setChildrenPoundage("0.00");
						} 
						
					}else{
						tbPolicyRules.setChildrenPoundage("0.00");
					}
					//判断仅限航班号不为空，以/拼接
					if(!"".equals(tbPolicyRules.getAllowFilghtNo().trim()) || null != tbPolicyRules.getAllowFilghtNo().trim()){
						StringBuffer sbfAllwoFilghtNo = new StringBuffer();
						String[] allwoFilgthNo = tbPolicyRules.getAllowFilghtNo().split("/");
						int m = allwoFilgthNo.length;
						for(int i = 0; i < m; i++){
							if(StringUtils.isNotBlank(allwoFilgthNo[i].trim())){
								sbfAllwoFilghtNo.append(allwoFilgthNo[i].trim());
								sbfAllwoFilghtNo.append("/");
							}
						}
						//将拼接的仅限航班号放入字段中
						tbPolicyRules.setAllowFilghtNo(sbfAllwoFilghtNo.toString().trim());
					}
					
//					if("".equals(tbPolicyRules.getSharePolicy()) || null == tbPolicyRules.getSharePolicy()){
//						tbPolicyRules.setSharePolicy("N");
//					}
					//判断排除航班号不为空，以/拼接
					if(null != tbPolicyRules.getExcludeFilghtNo().trim()){
						StringBuffer sbfAllwoFilghtNo = new StringBuffer();
						String[] allwoFilgthNo = tbPolicyRules.getExcludeFilghtNo().split("/");
						int m = allwoFilgthNo.length;
						for(int i = 0; i < m; i++){
							if(StringUtils.isNotBlank(allwoFilgthNo[i].trim())){
								sbfAllwoFilghtNo.append(allwoFilgthNo[i].trim());
								sbfAllwoFilghtNo.append("/");
							}
						}
						//将拼接的排除航班号放入字段中
						tbPolicyRules.setExcludeFilghtNo(sbfAllwoFilghtNo.toString().trim());
					}
					//如果未填写特殊代理费，以平台的默认值为标准。
					if("".equals(tbPolicyRules.getAgencyFee())){
						//代理费率目前为虚假信息,等待确认后再修改
						tbPolicyRules.setAgencyFee("0.08");
					}
					tbPolicyRules.setWorkTime(new Date());
					tbPolicyRules.setPrCode(MD5EncryptUtil.encryptMD5Code(Random.class.toString()));
					super.save(tbPolicyRules);
				}
				//修改政策：修改和修改+状态：将原有数据逻辑删除，只挂起：将原有数据物理删除
			}else if(!"".equals(tbPolicyRules.getId()) || null != tbPolicyRules.getId()){
				Boolean isTrue = true;
				//修改政策：修改和修改+状态：将原有数据逻辑删除，只挂起：将原有数据物理删除
				if(!"".equals(tbPolicyRules.getPolicyStatus())){
					PolicyRules policyRules1 = new PolicyRules();
					policyRules1 = super.get(tbPolicyRules.getId());
					//判断成人返点是否为空
					if("".equals(tbPolicyRules.getRebate()) || null == tbPolicyRules.getRebate()){
						flag9 = false;
					}else{
						Double rebate = Double.parseDouble(tbPolicyRules.getRebate());
						tbPolicyRules.setRebate(rebate.toString());
					}
					//代理费
					Double rebate1 = Double.parseDouble(tbPolicyRules.getAgencyFee());
					tbPolicyRules.setAgencyFee(rebate1.toString());
					if(tbPolicyRules.getChildrenReward().equals("04")){
						//儿童返点
						rebate1 = Double.parseDouble(tbPolicyRules.getChildrenRebate());
						tbPolicyRules.setChildrenRebate(rebate1.toString());
					}else{
						tbPolicyRules.setChildrenRebate("0.00");
					}
					
					//儿童加手续费
					if(!"".equals(tbPolicyRules.getChildrenPoundage())){
						rebate1 = Double.parseDouble(tbPolicyRules.getChildrenPoundage());
						tbPolicyRules.setChildrenPoundage(rebate1.toString());
					}
					else{
						tbPolicyRules.setChildrenPoundage("0.00");
					}
					if(null == tbPolicyRules.getChildrenOpenNoCom()){
						tbPolicyRules.setChildrenOpenNoCom("N");
					}
					if(null == tbPolicyRules.getChildrenPoundageChoice()){
						tbPolicyRules.setChildrenPoundageChoice("N");
					}
					if((tbPolicyRules.getAgencyFee().equals(policyRules1.getAgencyFee())
						&& tbPolicyRules.getAllowFilghtNo().equals(policyRules1.getAllowFilghtNo())
						&& tbPolicyRules.getBadyReward().equals(policyRules1.getBadyReward())
						&& tbPolicyRules.getBillingFee().equals(policyRules1.getBillingFee())
						&& tbPolicyRules.getCabin().equals(policyRules1.getCabin())
						&& tbPolicyRules.getChildrenOpenNoCom().equals(policyRules1.getChildrenOpenNoCom())
						&& tbPolicyRules.getChildrenPoundage().equals(policyRules1.getChildrenPoundage())
						&& tbPolicyRules.getChildrenPoundageChoice().equals(policyRules1.getChildrenPoundageChoice())
						&& tbPolicyRules.getChildrenRebate().equals(policyRules1.getChildrenRebate())
						&& tbPolicyRules.getChildrenReward().equals(policyRules1.getChildrenReward())
						&& tbPolicyRules.getCreateDate() == policyRules1.getCreateDate()
						&& tbPolicyRules.getCreatUser().equals(policyRules1.getCreatUser())
						&& tbPolicyRules.getExcludeFilghtNo().equals(policyRules1.getExcludeFilghtNo())
						&& tbPolicyRules.getFirstDateEnd() == policyRules1.getFirstDateEnd()
						&& tbPolicyRules.getFirstDateStart() == policyRules1.getFirstDateStart()
						&& tbPolicyRules.getPrCode().equals(policyRules1.getPrCode())
						&& tbPolicyRules.getPolicyName().equals(policyRules1.getPolicyName())
						&& tbPolicyRules.getSupplierId().equals(policyRules1.getSupplierId())
						&& tbPolicyRules.getOffice().equals(policyRules1.getOffice())
						&& tbPolicyRules.getPassagerPid().equals(policyRules1.getPassagerPid())
						&& tbPolicyRules.getTktAirline().equals(policyRules1.getTktAirline())
						&& tbPolicyRules.getPassagerCount().equals(policyRules1.getPassagerCount())
						&& tbPolicyRules.getTravelType().equals(policyRules1.getTravelType())
						&& tbPolicyRules.getOutOrg().equals(policyRules1.getOutOrg())
						&& tbPolicyRules.getOutOrgEx().equals(policyRules1.getOutOrgEx())
						&& tbPolicyRules.getOutDes().equals(policyRules1.getOutDes())
						&& tbPolicyRules.getOutDesEx().equals(policyRules1.getOutDesEx())
						&& tbPolicyRules.getReturnDes().equals(policyRules1.getReturnDes())
						&& tbPolicyRules.getReturnDesEx().equals(policyRules1.getReturnDesEx())
						&& tbPolicyRules.getOutDes().equals(policyRules1.getOutDes())
						&& tbPolicyRules.getOutMustPass().equals(policyRules1.getOutMustPass())
						&& tbPolicyRules.getExcludeFilghtNo().equals(policyRules1.getExcludeFilghtNo())
						&& tbPolicyRules.getReturnMustPass().equals(policyRules1.getReturnMustPass())
						&& tbPolicyRules.getReturnUnablePass().equals(policyRules1.getReturnUnablePass())
						&& tbPolicyRules.getPolicyRemark().equals(policyRules1.getPolicyRemark())
						&& tbPolicyRules.getSharePolicy().equals(policyRules1.getSharePolicy())
						&& !tbPolicyRules.getPolicyStatus().equals(policyRules1.getPolicyStatus())
						) || !tbPolicyRules.getPolicyStatus().equals(policyRules1.getPolicyStatus())){
						//待写
						isTrue = false;
						tbPolicyRules.setUpdateUser("李四");
						super.save(tbPolicyRules);
					}
				//修改，成功 之后再做删除的处理(问题)
				if((!"".equals(tbPolicyRules.getId()) || null != tbPolicyRules.getId()) && isTrue){
					policyRules1.setPolicyStatus("3");
					super.save(policyRules1);
					//判断政策名是否为空
					if("".equals(tbPolicyRules.getPolicyName()) || null == tbPolicyRules.getPolicyName()){
						flag = false;
					}
					//判断office票号是否为空
					if("".equals(tbPolicyRules.getOffice()) || null == tbPolicyRules.getOffice()){
						flag1 = false;
					}
					//判断航司是否为空
					if("".equals(tbPolicyRules.getTktAirline()) || null == tbPolicyRules.getTktAirline()){
						flag2 = false;
					}
					
					//判断行程：单程信息为N  往返以及单程/往返为Y
					if("0".equals(tbPolicyRules.getTravelType())){
						//判断行程类型：单程去程起点是否为空
						if(null == tbPolicyRules.getOutOrg()){
							flag3 = false;
						}
						//判断行程类型：单程去程起点除外是否为空
						if(null == tbPolicyRules.getOutOrgEx()){
							flag4 = false;
						}
						//判断行程类型：单程去程终点是否为空
						if(null == tbPolicyRules.getOutOrg()){
							flag5 = false;
						}
						//判断行程类型：单程去程终点除外是否为空
						if(null == tbPolicyRules.getOutOrgEx()){
							flag6 = false;
						}
						//判断行程类型为单程时，回程输入的所有信息无效
						//回程终点无效
						tbPolicyRules.setReturnDes(null);
						//回程终点除外无效
						tbPolicyRules.setReturnDesEx(null);
						//回程终点不能经过无效
						tbPolicyRules.setReturnUnablePass(null);
						//回程终点必须经过无效
						tbPolicyRules.setReturnMustPass(null);
						//返程日期 开始无效
						tbPolicyRules.setLastDateStart(null);
						//返程日期 结束无效
						tbPolicyRules.setLastDateEnd(null);
						//单程为N
						tbPolicyRules.setSharePolicy("N");
					}else{
						//判断行程类型：单程去程起点是否为空
						if(null == tbPolicyRules.getOutOrg()){
							flag3 = false;
						}
						//判断行程类型：单程去程起点除外是否为空
						if(null == tbPolicyRules.getOutOrgEx()){
							flag4 = false;
						}
						//判断行程类型：往返去程终点是否为空
						if(null == tbPolicyRules.getOutOrg()){
							flag5 = false;
						}
						//判断行程类型：往返去程终点除外是否为空
						if(null == tbPolicyRules.getOutOrgEx()){
							flag6 = false;
						}
						//判断行程类型：往返回程终点是否为空
						if(null == tbPolicyRules.getReturnDes()){
							flag7 = false;
						}
						//判断行程类型：往返回程终点除外是否为空
						if(null == tbPolicyRules.getReturnDes()){
							flag8 = false;
						}
						//往返为Y
						tbPolicyRules.setSharePolicy("Y");
					}
					//判断成人返点是否为空
					if("".equals(tbPolicyRules.getRebate()) || null == tbPolicyRules.getRebate()){
						flag9 = false;
					}else{
//						Double rebate = Double.parseDouble(tbPolicyRules.getRebate())/100;
//						tbPolicyRules.setRebate(rebate.toString());
					}
					//判断成人开票费是否为空
					if("".equals(tbPolicyRules.getBillingFee()) || null == tbPolicyRules.getBillingFee()){
						flag10 = false;
					}
					//判断儿童返点是否为空
					if("".equals(tbPolicyRules.getChildrenReward()) || null  == tbPolicyRules.getChildrenReward()){
						flag11 = false;
					}
					//判断儿童加手续费是否选中，未选中赋值为N
					if("".equals(tbPolicyRules.getChildrenPoundageChoice()) || null == tbPolicyRules.getChildrenPoundageChoice()){
						tbPolicyRules.setChildrenPoundageChoice("N");
					}
					//判断儿童加手续费是否选中，如未选中输入的手续费无效
					if("N".equals(tbPolicyRules.getChildrenPoundageChoice())){
						tbPolicyRules.setChildrenPoundage("0.00");
					}
					//判断儿童可开无代理费是否选中，未选中赋值为N
					if("".equals(tbPolicyRules.getChildrenOpenNoCom()) || null == tbPolicyRules.getChildrenOpenNoCom()){
						tbPolicyRules.setChildrenOpenNoCom("N");
					}
					//判断去程旅行日期 开始是否为空
					if("".equals(tbPolicyRules.getFirstDateStart()) || null == tbPolicyRules.getFirstDateStart()){
						flag12 = false;
					}
					//判断去程旅行日期 结束是否为空
					if("".equals(tbPolicyRules.getFirstDateEnd()) || null == tbPolicyRules.getFirstDateEnd()){
						flag13 = false;
					}
					//判断满足以上所有条件，才可添加信息
					if(flag && flag1 && flag2 && flag3 &&  flag5  && flag7  && flag9  && flag11 && flag12 && flag13){
						//判断舱位信息是否合法
						if(null != tbPolicyRules.getCabin()){
							//将合法的舱位信息以：A/B/的格式拼接
							StringBuffer sbfCabin = new StringBuffer();
							String[] cabinArr = tbPolicyRules.getCabin().split("/");
							int m = cabinList.length;
							int n = cabinArr.length;
							for(int i = 0;i<m;i++){
								for(int j=0;j<n;j++){
									if(cabinList[i].trim().toUpperCase().equals(cabinArr[j].trim().toUpperCase())){
										sbfCabin.append(cabinArr[j].trim().toUpperCase());
										sbfCabin.append("/");
									}
								}
							}
							//将拼接的合法舱位放入字段中
							tbPolicyRules.setCabin(sbfCabin.toString().trim());
						}
						if(null != tbPolicyRules.getRebate() || !"".equals(tbPolicyRules.getRebate())){
							//如果返点长度大于4，将其截取
							if(tbPolicyRules.getRebate().length()>4){
								tbPolicyRules.setRebate(tbPolicyRules.getRebate().substring(0,5));
							}
						}
						if(null != tbPolicyRules.getBillingFee() || !"".equals(tbPolicyRules.getBillingFee())){
							//如果开票费长度大于4，将其截取
							if(tbPolicyRules.getBillingFee().length()>4){
								tbPolicyRules.setBillingFee(tbPolicyRules.getBillingFee().substring(0,4));
							}
						}else{
							tbPolicyRules.setBillingFee("0.00");
						}
						if(null != tbPolicyRules.getAgencyFee() || !"".equals(tbPolicyRules.getAgencyFee())){
							//如果代理费长度大于4，将其截取
							Double rebate = Double.parseDouble(tbPolicyRules.getAgencyFee());
							tbPolicyRules.setAgencyFee(rebate.toString());
							if(tbPolicyRules.getAgencyFee().length()>4){
								tbPolicyRules.setAgencyFee(tbPolicyRules.getAgencyFee().substring(0,4));
							}
						}
						//如果儿童返点长度大于4，将其截取
						if(null != tbPolicyRules.getChildrenRebate()){
								Double rebate = Double.parseDouble(tbPolicyRules.getChildrenRebate());
								tbPolicyRules.setChildrenRebate(rebate.toString());
								if(tbPolicyRules.getChildrenRebate().length()>4){
									tbPolicyRules.setChildrenRebate(tbPolicyRules.getChildrenRebate().substring(0,4));
								}
						
						}else{
							tbPolicyRules.setChildrenRebate("0.00");
						}
						
						//如果儿童手续费长度大于4，将其截取
						if(null != tbPolicyRules.getChildrenPoundage()){
							Double rebate = Double.parseDouble(tbPolicyRules.getChildrenPoundage());
							tbPolicyRules.setChildrenPoundage(rebate.toString());
							if(tbPolicyRules.getChildrenPoundage().length()>4){
								tbPolicyRules.setChildrenPoundage(tbPolicyRules.getChildrenPoundage().substring(0,4));
							}
						}
						//判断仅限航班号不为空，以/拼接
						if(!"".equals(tbPolicyRules.getAllowFilghtNo().trim()) || null != tbPolicyRules.getAllowFilghtNo().trim()){
							StringBuffer sbfAllwoFilghtNo = new StringBuffer();
							String[] allwoFilgthNo = tbPolicyRules.getAllowFilghtNo().split("/");
							int m = allwoFilgthNo.length;
							for(int i = 0; i < m; i++){
								if("" != allwoFilgthNo[i].trim()){
									sbfAllwoFilghtNo.append(allwoFilgthNo[i].trim());
									sbfAllwoFilghtNo.append("/");
								}
							}
							//将拼接的仅限航班号放入字段中
							tbPolicyRules.setAllowFilghtNo(sbfAllwoFilghtNo.toString().trim());
						}
						
//						if("".equals(tbPolicyRules.getSharePolicy()) || null == tbPolicyRules.getSharePolicy()){
//							tbPolicyRules.setSharePolicy("N");
//						}
						//判断排除航班号不为空，以/拼接
						if(!"".equals(tbPolicyRules.getExcludeFilghtNo().trim()) ||null != tbPolicyRules.getExcludeFilghtNo().trim()){
							StringBuffer sbfAllwoFilghtNo = new StringBuffer();
							String[] allwoFilgthNo = tbPolicyRules.getExcludeFilghtNo().trim().split("/");
							int m = allwoFilgthNo.length;
							System.out.println(m);
							for(int i = 0; i < m; i++){
								if(""!=allwoFilgthNo[i].trim()){
									sbfAllwoFilghtNo.append(allwoFilgthNo[i].trim());
									sbfAllwoFilghtNo.append("/");
								}
							}
							//将拼接的排除航班号放入字段中
							tbPolicyRules.setExcludeFilghtNo(sbfAllwoFilghtNo.toString().trim());
						}
						//如果未填写特殊代理费，以平台的默认值为标准。
						if("".equals(tbPolicyRules.getAgencyFee())){
							//代理费率目前为虚假信息,等待确认后再修改
							tbPolicyRules.setAgencyFee("0.08");
						}
						tbPolicyRules.setId(null);
						tbPolicyRules.setWorkTime(new Date());
						tbPolicyRules.setPrCode(MD5EncryptUtil.encryptMD5Code(Random.class.toString()));
						super.save(tbPolicyRules);
					}
				}
			}
			}
		}
	}
	
	/**
	 * 国际政策的增删改查 删除
	 * @param PolicyRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(PolicyRules tbPolicyRules){
		if(null != tbPolicyRules){
			tbPolicyRules.setPolicyStatus("2");
			super.delete(tbPolicyRules);
		}
	}

	/**
	 * 单程的计奖部分
	 * @param rt
	 * @param generalRulesList
	 * @return
	 */
	public Rt getOneTravelType(Rt rt,List<GeneralRules> generalRulesList){
		//返回的集合
		List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
		List<Route> routeList = rt.getRoute_list();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		//只有一段航程
		if("0".equals(rt.getTravelType())){
			Route route = routeList.get(0);
			Route route11 = routeList.get(routeList.size()-1);
			//根据航班信息查询出总则对应的政策集合
			PolicyRules policyRules = new PolicyRules();
			//舱位信息
//			String cabin = getCabin(routeList);
//			policyRules.setCabin(cabin);
			//旅客身份
			String passenger = getPersonStatus(rt.getPassenger_list());
			if(null != passenger || !"".equals(passenger)){
				policyRules.setPassagerPid(passenger);
			}
			//去程起点机场三字码
			policyRules.setOutOrg(route.getFromCityCode());
			//去程起点城市三字码
			policyRules.setOutOrgCityCode(route.getFromAirport());
			//去程起点国家三字码
			policyRules.setOutOrgCountryCode(route.getFromCountryCode());
			//去程起点大区三字码
			policyRules.setOutOrgAreaCode(route.getFromAreaCode());
			//去程起点大洲三字码
			policyRules.setOutOrgRegionCode(route.getFromRegionCode());
			//去程终点机场三字码
			policyRules.setOutDes(route11.getToCityCode());
			//去程终点城市三字码
			policyRules.setOutDesCityCode(route11.getToAirport());
			//去程终点国家三字码
			policyRules.setOutDesCountryCode(route11.getToCountryCode());
			//去程终点大区三字码
			policyRules.setOutDesAreaCode(route11.getToAreaCode());
			//去程终点大洲三字码
			policyRules.setOutDesRegionCode(route11.getToRegionCode());
			//启用状态
			policyRules.setPolicyStatus("0");
			//行程类型（单程）
			policyRules.setTravelType("0");
			
			//去程起点时间
			Date startTime;
			//去程终点时间
			Date endTime;
			try {
				if(null != route.getFromTime()){
					startTime = sim.parse(route.getFromTime());
					policyRules.setFirstDateStart(startTime);
				}
				if(null != route.getToTime()){
					endTime = sim.parse(route11.getToTime());
					policyRules.setFirstDateEnd(endTime);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			policyRules.setTktAirline(rt.getComput());
			int p = -1 ;
			//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
				for(int m = 0;m < generalRulesList.size();m++){
					GeneralRules generalRules = generalRulesList.get(m);
					policyRules.setSupplierId(generalRules.getSupplierId());
					//
					List<PolicyRules> policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
					for(int n = 0; n < policyRulesList1.size();n++){
					List<PersonTypeInfo> personList = new ArrayList<PersonTypeInfo>();
						p++;
					//销售价(不含税)
						Double price=0.0;
						Double tax = 0.0;
					try {
						price = Double.parseDouble(rt.getQte().get(0).getFare());
						//税费
						tax = Double.parseDouble(rt.getQte().get(0).getTax());
					} catch (Exception e) {
						logger.error("QTE取免税价和税费错误");
					}
					
					
					//计算后的结算价
					Double callPrice = 0.0;
					//匹配的政策
					PolicyRules policy = policyRulesList1.get(n);
					//匹配的总则
					
					//总则IT票无奖励值为“Y”时，并且为IT票，本张票无奖励,IT票的值从PNR文本取
//					if("Y".equals(generalRules.getNoItReward().trim().toUpperCase()) && "IT".equals("IT")){
						if("Y".equals(generalRules.getNoItReward().trim().toUpperCase())){
//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//						double realPrice = Math.rint(callPrice);
//						policyRulesList1.get(n).setCallPrice(realPrice);
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
						//总则OPEN票无奖励值为Y时，需判断本张票是否为OPEN票，如果是，则本张票无任何奖励
//					}else if("Y".equals(generalRules.getNoOpenReward()) && "OPEN".equals("OPEN")){
					}else if(null == route11.getToTime() && "Y".equals(generalRules.getNoOpenReward())){
//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//						double realPrice = Math.rint(callPrice);
//						policyRulesList1.get(n).setCallPrice(realPrice);
						for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
							Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
							PersonTypeInfo personInfo = new PersonTypeInfo();
							//计奖部分
							Double isPrice = 0.0;
							//不计奖部分
							Double noPrice = 0.0;
							//Double callPrice1 = Double.parseDouble(qte.getFare());
							Double rebate =  Double.parseDouble((policy.getRebate()));
							Double agencyFee = Double.parseDouble(policy.getAgencyFee());
							if(0 == rebate){
								rebate = 1.0*1d/100;
							}else{
								rebate = rebate*1d/100;
							}
							if(0 == agencyFee){
								agencyFee = 1.0*1d/100;
							}else{
								agencyFee = agencyFee*1d/100;
							}
							Double billingFee = Double.parseDouble(policy.getBillingFee());
							callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
							double realPrice1 = Math.rint(callPrice);
							personInfo.setFlyPrice(realPrice1);
							personInfo.setTax(Double.parseDouble(qte.getTax()));
							personInfo.setPersonType(qte.getIdentity());
							personInfo.setIsPrice(Math.rint(isPrice));
							personInfo.setPrice(Double.parseDouble(qte.getFare()));
							personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
							personInfo.setRebate(Double.parseDouble(policy.getRebate()));
							personList.add(personInfo);
						}
						//总则含XXX票价无奖励值为Y时，需判断本张票是否含XXX票价基础，如果含，则本张票无任何奖励
//					}else if("Y".equals(generalRules.getNoInvolveReward()) && "".equals("")){
					}else if("Y".equals(generalRules.getNoInvolveReward())){
//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//						double realPrice = Math.rint(callPrice);
//						policyRulesList1.get(n).setCallPrice(realPrice);
						for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
							Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
							PersonTypeInfo personInfo = new PersonTypeInfo();
							//计奖部分
							Double isPrice = 0.0;
							//不计奖部分
							Double noPrice = 0.0;
							//Double callPrice1 = Double.parseDouble(qte.getFare());
							//如果政策内返点为零，将其付为1；
							Double rebate =  Double.parseDouble((policy.getRebate()));
							Double agencyFee = Double.parseDouble(policy.getAgencyFee());
							if(0 == rebate){
								rebate = 1.0*1d/100;
							}else{
								rebate = rebate*1d/100;
							}
							if(0 == agencyFee){
								agencyFee = 1.0*1d/100;
							}else{
								agencyFee = agencyFee*1d/100;
							}
							Double billingFee = Double.parseDouble(policy.getBillingFee());
							callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
							double realPrice1 = Math.rint(callPrice);
							personInfo.setFlyPrice(realPrice1);
							personInfo.setTax(Double.parseDouble(qte.getTax()));
							personInfo.setPersonType(qte.getIdentity());
							personInfo.setIsPrice(Math.rint(isPrice));
							personInfo.setPrice(Double.parseDouble(qte.getFare()));
							personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
							personInfo.setRebate(Double.parseDouble(policy.getRebate()));
							personList.add(personInfo);
						}
						//总则低于￥￥￥票价无奖励值为Y时，需判断本张票价是否低于规定价格，如果低于，则本张票无任何奖励
//					}else if("Y".equals(generalRules.getNoInvolveReward()) && price < Double.parseDouble(generalRules.getNoInvolvePrice())){
					}else if(price < Double.parseDouble(generalRules.getNoInvolvePrice()) && "Y".equals(generalRules.getNoInvolveReward())){
//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//						double realPrice = Math.rint(callPrice);
//						policyRulesList1.get(n).setCallPrice(realPrice);
						for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
							Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
							PersonTypeInfo personInfo = new PersonTypeInfo();
							//计奖部分
							Double isPrice = 0.0;
							//不计奖部分
							Double noPrice = 0.0;
							//Double callPrice1 = Double.parseDouble(qte.getFare());
							Double rebate =  Double.parseDouble((policy.getRebate()));
							Double agencyFee = Double.parseDouble(policy.getAgencyFee());
							if(0 == rebate){
								rebate = 1.0*1d/100;
							}else{
								rebate = rebate*1d/100;
							}
							if(0 == agencyFee){
								agencyFee = 1.0*1d/100;
							}else{
								agencyFee = agencyFee*1d/100;
							}
							Double billingFee = Double.parseDouble(policy.getBillingFee());
							callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
							double realPrice1 = Math.rint(callPrice);
							personInfo.setFlyPrice(realPrice1);
							personInfo.setTax(Double.parseDouble(qte.getTax()));
							personInfo.setPersonType(qte.getIdentity());
							personInfo.setIsPrice(Math.rint(isPrice));
							personInfo.setPrice(Double.parseDouble(qte.getFare()));
							personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
							personInfo.setRebate(Double.parseDouble(policy.getRebate()));
							personList.add(personInfo);
						}
					}else{
						for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
							Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
							//计奖部分
							Double isPrice = 0.0;
							//不计奖部分
							Double noPrice = 0.0;
							PersonTypeInfo personInfo = new PersonTypeInfo();
							for(int j = 0 ; j < rt.getPersonTypeInfoList().get(i).getRouteList().size(); j++){
								Route route1 = rt.getPersonTypeInfoList().get(i).getRouteList().get(j);
								//判断本航段是否属于AddOn航段
								if("Y".equals(route1.getIsAddOn())){
									//判断本条总则中，AddOn是否计奖
									if("Y".equals(generalRules.getAddOnChoice())){
										//判断本行段是否为国内AddOn
										if("0".equals(route1.getAddOnFly())){
											//判断本条总则中国内AddOn是否计奖
											if("Y".equals(generalRules.getAddOnInternalChoice())){
												isPrice += route1.getFlyPrice();
											}else{
												noPrice += route1.getFlyPrice();
											}
											//判断本航段是否为国际AddOn
										}else if("1".equals(route1.getAddOnFly())){
											//判断本条总则中国际AddOn是否计奖
											if("Y".equals(generalRules.getAddOnWorldChoice())){
												isPrice += route1.getFlyPrice();
											}else{
												noPrice += route1.getFlyPrice();
											}
										}
									}else{
										noPrice += route1.getFlyPrice();
									}
									
									//判断本条航段是否属于SPA航段
								}else if("Y".equals(route1.getIsSPA())){
									//判断本条总则中SPA是否计奖
									if("Y".equals(generalRules.getSpaChoice())){
										isPrice += route1.getFlyPrice();
									}else{
										noPrice += route1.getFlyPrice();
									}
									//判断本条航段是否属于主FC航段
								}else if("Y".equals(route1.getIsFc())){
									isPrice += route1.getFlyPrice();
								}else{
									noPrice += route1.getFlyPrice();
								}
							}
							//Double callPrice1 = Double.parseDouble(qte.getFare());
							Double rebate =  Double.parseDouble((policy.getRebate()));
							Double agencyFee = Double.parseDouble(policy.getAgencyFee());
//							if(0 == rebate){
//								rebate = 1.0*1d/100;
//							}else{
								rebate = rebate*1d/100;
//							}
//							if(0 == agencyFee){
//								agencyFee = 1.0*1d/100;
//							}else{
								agencyFee = agencyFee*1d/100;
//							}
							Double billingFee = Double.parseDouble(policy.getBillingFee());
//							if(0 == agencyFee){
//								agencyFee = 1.0*1d;
//							}
//							if(0 == rebate){
//								rebate = 1.0*1d;
//							}
							callPrice = isPrice*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
							double realPrice = Math.rint(callPrice);
							personInfo.setFlyPrice(realPrice);
							personInfo.setTax(Double.parseDouble(qte.getTax()));
							personInfo.setPersonType(qte.getIdentity());
							personInfo.setIsPrice(Math.rint(isPrice));
							personInfo.setPrice(Double.parseDouble(qte.getFare()));
							personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
							personInfo.setRebate(Double.parseDouble(policy.getRebate()));
							personInfo.setFlyPrice(realPrice);
							personList.add(personInfo);
						}
						//policyRulesList1.get(n).setPersonList(personList);
					}
						policyRulesList.add(policy);
						policyRulesList.get(p).setPersonList(personList);
						
				}
				//policyRulesList = policyRulesList1;
			}
		}
		//去除重复供应商政策并保留成人结算价最低的。
		for(int i=0;i<policyRulesList.size();i++){
            for(int j=policyRulesList.size()-1;j>i;j--){
                if(policyRulesList.get(i).getSupplierId().equals(policyRulesList.get(j).getSupplierId())){
                	if(policyRulesList.get(i).getPersonList().get(0).getFlyPrice() <= policyRulesList.get(j).getPersonList().get(0).getFlyPrice()){
                		policyRulesList.remove(j);
                		continue;
                	}else{
                		policyRulesList.remove(i);
                		continue;
                	}
                }
            }
        }
//		if(0 == policyRulesList.size()){
//			logger.error("没有匹配的政策");
//			rt.setTravelType("3");
//			return rt;
//		}
		rt.setPolices(policyRulesList);
		return rt;
	}
	
	/**
	 * 返回往返的政策结果
	 * @param rt
	 * @param generalRulesList
	 * @return
	 */
	public Rt getGoAndBack(Rt rt,List<GeneralRules> generalRulesList){
		//返回的集合
			List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
			List<Route> routeList = rt.getRoute_list();
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			//只有一段航程
			if("1".equals(rt.getTravelType())){
				Integer c = routeList.size()/2;
				//去程航段信息
				Route route11 = routeList.get(0);
				Route routeMiddle = routeList.get(c-1);
				//回程航段信息
				Route route2 = routeList.get(routeList.size()-1);
				PolicyRules policyRules = new PolicyRules();
				//政策启用状态
				policyRules.setPolicyStatus("0");
				//往返
				policyRules.setTravelType("1");
				//舱位信息
//				String cabin = getCabin(routeList);
//				policyRules.setCabin(cabin);
				//旅客身份
				String passenger = getPersonStatus(rt.getPassenger_list());
				if(null != passenger || !"".equals(passenger)){
					policyRules.setPassagerPid(passenger);
				}
				//去程起点
				policyRules.setOutOrg(route11.getFromCityCode());
				policyRules.setOutOrgCityCode(route11.getFromAirport());
				policyRules.setOutOrgAreaCode(route11.getFromAreaCode());
				policyRules.setOutOrgCountryCode(route11.getFromCountryCode());
				policyRules.setOutOrgRegionCode(route11.getFromRegionCode());
				//去程终点
				policyRules.setOutDes(routeMiddle.getToCityCode());
				policyRules.setOutDesCityCode(routeMiddle.getToAirport());
				policyRules.setOutDesAreaCode(routeMiddle.getToAreaCode());
				policyRules.setOutDesCountryCode(routeMiddle.getToCountryCode());
				policyRules.setOutDesRegionCode(routeMiddle.getToRegionCode());
				//回程终点
				policyRules.setReturnDes(route2.getToCityCode());
				policyRules.setReturnDesCityCode(route2.getToAirport());
				policyRules.setReturnDesAreaCode(route2.getToAreaCode());
				policyRules.setReturnDesCountryCode(route2.getToCountryCode());
				policyRules.setReturnDesRegionCode(route2.getToRegionCode());
				//去程起点时间
				Date startTime;
				//去程终点时间
				Date endTime;
				try {
					if(null != route11.getFromTime()){
						startTime = sim.parse(route11.getFromTime());
						policyRules.setFirstDateStart(startTime);
					}
					if(null != routeMiddle.getToTime()){
						endTime = sim.parse(routeMiddle.getFromTime());
						policyRules.setFirstDateEnd(endTime);
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//出票航司
				policyRules.setTktAirline(rt.getComput());
				int p = -1 ;
				//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
					for(int m = 0;m < generalRulesList.size();m++){
						//匹配的总则
						GeneralRules generalRules = generalRulesList.get(m);
						policyRules.setSupplierId(generalRules.getSupplierId());
						List<PolicyRules> policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
						for(int n = 0; n < policyRulesList1.size();n++){
							List<PersonTypeInfo> personList = new ArrayList<PersonTypeInfo>();
							//匹配的政策
							PolicyRules policy = policyRulesList1.get(n);
							p++;
						//销售价(不含税)
						Double price = Double.parseDouble(rt.getQte().get(0).getFare());
						//税费
						Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
						//计算后的结算价
						Double callPrice = 0.0;
						//总则IT票无奖励值为“Y”时，并且为IT票，本张票无奖励,IT票的值从PNR文本取
//							if("Y".equals(generalRules.getNoItReward().trim().toUpperCase()) && "IT".equals("IT")){
							if("IT".equals(rt.getPnrType()) && "Y".equals(generalRules.getNoItReward().trim().toUpperCase())){
//								callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
//								callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//								double realPrice = Math.rint(callPrice);
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//policyRulesList1.get(n).setCallPrice(realPrice);
							//总则OPEN票无奖励值为Y时，需判断本张票是否为OPEN票，如果是，则本张票无任何奖励
//							}else if("Y".equals(generalRules.getNoOpenReward()) && "OPEN".equals("OPEN")){
						}else if("Y".equals(generalRules.getNoOpenReward())){
//								callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//								double realPrice = Math.rint(callPrice);
//								policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								PersonTypeInfo personInfo = new PersonTypeInfo();
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								//如果政策内返点为零，将其付为1；
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//总则含XXX票价无奖励值为Y时，需判断本张票是否含XXX票价基础，如果含，则本张票无任何奖励
//							}else if("Y".equals(generalRules.getNoInvolveReward()) && "".equals("")){
						}else if("Y".equals(generalRules.getNoInvolveReward())){
//								callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//								double realPrice = Math.rint(callPrice);
//								policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								PersonTypeInfo personInfo = new PersonTypeInfo();
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//总则低于￥￥￥票价无奖励值为Y时，需判断本张票价是否低于规定价格，如果低于，则本张票无任何奖励
//							}else if("Y".equals(generalRules.getNoInvolveReward()) && price < Double.parseDouble(generalRules.getNoInvolvePrice())){
						}else if("Y".equals(generalRules.getNoInvolveReward())){
//								callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//								double realPrice = Math.rint(callPrice);
//								policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*agencyFee+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
						}else{
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								try {
									PersonTypeInfo personTypeInfo = rt.getPersonTypeInfoList().get(i);
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								List<Route> routes = personTypeInfo.getRouteList();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								for(int j = 0 ; j < routes.size(); j++){
									Route route1 = routes.get(j);
									//判断本航段是否属于AddOn航段
									if("Y".equals(route1.getIsFc())){
										isPrice += route1.getFlyPrice();
									}else{
										//判断本航段是否属于AddOn航段
										if("Y".equals(route1.getIsAddOn())){
											//判断本条总则中，AddOn是否计奖
											if("Y".equals(generalRules.getAddOnChoice())){
												//判断本行段是否为国内AddOn
												if("0".equals(route1.getAddOnFly())){
													//判断本条总则中国内AddOn是否计奖
													if("Y".equals(generalRules.getAddOnInternalChoice())){
														isPrice += route1.getFlyPrice();
													}else{
														noPrice += route1.getFlyPrice();
													}
													//判断本航段是否为国际AddOn
												}else if("1".equals(route1.getAddOnFly())){
													//判断本条总则中国际AddOn是否计奖
													if("Y".equals(generalRules.getAddOnWorldChoice())){
														isPrice += route1.getFlyPrice();
													}else{
														noPrice += route1.getFlyPrice();
													}
												}
											}else{
												noPrice += route1.getFlyPrice();
											}
											
											//判断本条航段是否属于SPA航段
										}else if("Y".equals(route1.getIsSPA())){
											//判断本条总则中SPA是否计奖
											if("Y".equals(generalRules.getSpaChoice())){
												isPrice += route1.getFlyPrice();
											}else{
												noPrice += route1.getFlyPrice();
											}
											//判断本条航段是否属于主FC航段
										}else{
											noPrice += route1.getFlyPrice();
										}
									}
								}
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double rebate =  Double.parseDouble((policy.getRebate()));
								Double agencyFee = Double.parseDouble(policy.getAgencyFee());
								if(0 == rebate){
									rebate = 1.0*1d/100;
								}else{
									rebate = rebate*1d/100;
								}
								if(0 == agencyFee){
									agencyFee = 1.0*1d/100;
								}else{
									agencyFee = agencyFee*1d/100;
								}
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = isPrice*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
								} catch (Exception e) {
									logger.error("计奖计算航段取值错误",e);
								}	
							}
						//	policyRulesList1.get(n).setPersonList(personList);
							
						}
							policyRulesList.add(policy);
							policyRulesList.get(p).setPersonList(personList);
					}
				}
			}
			
//			List<PolicyRules> policyLists = new ArrayList<PolicyRules>();
//			for(int i = 0 ; i < policyRulesList.size(); i++){
//				PolicyRules policy = policyRulesList.get(i);
//				for(int j = 0; j < policyRulesList.size(); j++){
//					PolicyRules policyRules = policyRulesList.get(j);
//					Double policyPrice = policy.getPersonList().get(0).getFlyPrice();
//					Double policy1Price = policyRules.getPersonList().get(0).getFlyPrice();
//					if(policy.getSupplierId().equals(policyRules.getSupplierId())){
//						if(policyPrice >= policy1Price){
//							policyLists.add(policy);
//							continue;
//						}else{
//							policyLists.add(policyRules);
//							continue;
//						}
//					}
//				}
//			}
			
			//int pilicySize = policyRulesList.size();
//			for(int i = 0 ; i < policyRulesList.size(); i++){
//				int j = i+1;
//				if(i == (policyRulesList.size()-1)){
//					j = policyRulesList.size()-2;
//				}
//				PolicyRules policy = policyRulesList.get(i);
//				PolicyRules policy1 = policyRulesList.get(j);
//				Double policyPrice = policy.getPersonList().get(0).getFlyPrice();
//				Double policy1Price = policy1.getPersonList().get(0).getFlyPrice();
//				if(policy.getSupplierId().equals(policy1.getSupplierId())){
//					if(policyPrice >= policy1Price){
//						policyRulesList.remove(i+1);
//						i--;
//						continue;
//					}else{
//						policyRulesList.remove(i);
//						i--;
//						continue;
//					}
//				}
//			}
			
			//去除重复供应商政策并保留成人结算价最高的。
			try {
				for(int i=0;i<policyRulesList.size();i++){
		            for(int j=policyRulesList.size()-1;j>i;j--){
		                if(policyRulesList.get(i).getSupplierId().equals(policyRulesList.get(j).getSupplierId())){
		                	if(policyRulesList.get(i).getPersonList().get(0).getFlyPrice() <= policyRulesList.get(j).getPersonList().get(0).getFlyPrice()){
		                		policyRulesList.remove(j);
		                	}else{
		                		policyRulesList.remove(i);
		                	}
		                }
		            }
		        }
			} catch (Exception e) {
				logger.error("政策去重有误");
			}
			
//			if(0 == policyRulesList.size()){
//				System.out.println("无匹配的政策");
//				rt.setTravelType("3");
//				return rt;
//			}
			rt.setPolices(policyRulesList);
			return rt;
	}
	
	/**
	 * 政策组合的匹配
	 * @param rt
	 * @param generalRulesList
	 * @return
	 */
	private Rt GoandBackGroup(Rt rt,List<GeneralRules> generalRulesList){
		//返回的集合
		List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
		List<Route> routeList = rt.getRoute_list();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		//只有一段航程
		if("1".equals(rt.getTravelType())){
			Integer c = routeList.size()/2;
			//去程航段信息
			Route route11 = routeList.get(0);
			Route routeMiddle = routeList.get(c-1);
			Route routeMiddleTo = routeList.get(c);
			//回程航段信息
			Route route2 = routeList.get(routeList.size()-1);
			PolicyRules policyRules = new PolicyRules();
			//政策启用状态
			policyRules.setPolicyStatus("0");
			//往返
			policyRules.setTravelType("1");
			policyRules.setSharePolicy("Y");
			//舱位信息
			//String cabin = getCabin(routeList);
			//policyRules.setCabin(cabin);
			//去程起点
			policyRules.setOutOrg(route11.getFromCityCode());
			policyRules.setOutOrgCityCode(route11.getFromAirport());
			policyRules.setOutOrgAreaCode(route11.getFromAreaCode());
			policyRules.setOutOrgCountryCode(route11.getFromCountryCode());
			policyRules.setOutOrgRegionCode(route11.getFromRegionCode());
			//去查回程终点（先以去程起点为组合政策去匹配）
			policyRules.setReturnDes(route11.getFromCityCode());
			policyRules.setReturnDesCityCode(route11.getFromAirport());
			policyRules.setReturnDesAreaCode(route11.getFromAreaCode());
			policyRules.setReturnDesCountryCode(route11.getFromCountryCode());
			policyRules.setReturnDesRegionCode(route11.getFromRegionCode());
			//去程终点
			policyRules.setOutDes(routeMiddle.getToCityCode());
			policyRules.setOutDesCityCode(routeMiddle.getToAirport());
			policyRules.setOutDesAreaCode(routeMiddle.getToAreaCode());
			policyRules.setOutDesCountryCode(routeMiddle.getToCountryCode());
			policyRules.setOutDesRegionCode(routeMiddle.getToRegionCode());
			//去程起点时间
			Date startTime;
			//去程终点时间
			Date endTime;
			try {
				if(null != route11.getFromTime()){
					startTime = sim.parse(route11.getFromTime());
					policyRules.setFirstDateStart(startTime);
				}
				if(null != routeMiddle.getToTime()){
					endTime = sim.parse(routeMiddle.getFromTime());
					policyRules.setFirstDateEnd(endTime);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//出票航司
			policyRules.setTktAirline(rt.getComput());
			int p = -1 ;
			//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
				for(int m = 0;m < generalRulesList.size();m++){
					//匹配的总则
					GeneralRules generalRules = generalRulesList.get(m);
					policyRules.setSupplierId(generalRules.getSupplierId());
					List<PolicyRules> policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
					if(policyRulesList1.size() > 0){
						//根据原始回程终点作为去程起点
						policyRules.setOutOrg(route2.getToCityCode());
						policyRules.setOutOrgCityCode(route2.getToAirport());
						policyRules.setOutOrgAreaCode(route2.getToAreaCode());
						policyRules.setOutOrgCountryCode(route2.getToCountryCode());
						policyRules.setOutOrgRegionCode(route2.getToRegionCode());
						//如果有这个集合，就根据原始回程终点为终点
						policyRules.setReturnDes(route2.getToCityCode());
						policyRules.setReturnDesCityCode(route2.getToAirport());
						policyRules.setReturnDesAreaCode(route2.getToAreaCode());
						policyRules.setReturnDesCountryCode(route2.getToCountryCode());
						policyRules.setReturnDesRegionCode(route2.getToRegionCode());
						//去程终点
						policyRules.setOutDes(routeMiddleTo.getFromCityCode());
						policyRules.setOutDesCityCode(routeMiddleTo.getFromAirport());
						policyRules.setOutDesAreaCode(routeMiddleTo.getFromAreaCode());
						policyRules.setOutDesCountryCode(routeMiddleTo.getFromCountryCode());
						policyRules.setOutDesRegionCode(routeMiddleTo.getFromRegionCode());
						List<PolicyRules> policyRulesList2 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
						
					for(int n = 0; n < policyRulesList1.size();n++){
						List<PersonTypeInfo> personList = new ArrayList<PersonTypeInfo>();
						//匹配的政策
						PolicyRules policy = policyRulesList1.get(n);
						Double rebate1 =  Double.parseDouble((policy.getRebate()));
						Double agencyFee1 = Double.parseDouble(policy.getAgencyFee());
						for(int b = 0; b < policyRulesList2.size();b++){
							PolicyRules policy1 = policyRulesList2.get(b);
							Double rebate =  Double.parseDouble((policy1.getRebate()));
							Double agencyFee = Double.parseDouble(policy1.getAgencyFee());
							rebate += rebate1;
							agencyFee += agencyFee1;
//							if(0 == rebate){
//								rebate = 1.0*1d/100;
//							}else{
								rebate = rebate*1d/100/2;
//							}
//							if(0 == agencyFee){
//								agencyFee = 1.0*1d/100;
//							}else{
								agencyFee = agencyFee*1d/100/2;
//							}
							p++;
							//销售价(不含税)
							Double price = Double.parseDouble(rt.getQte().get(0).getFare());
							//税费
							Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
							//计算后的结算价
							Double callPrice = 0.0;
						//总则IT票无奖励值为“Y”时，并且为IT票，本张票无奖励,IT票的值从PNR文本取
	//					if("Y".equals(generalRules.getNoItReward().trim().toUpperCase()) && "IT".equals("IT")){
							if("IT".equals(rt.getPnrType()) && "Y".equals(generalRules.getNoItReward().trim().toUpperCase())){
	//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
	//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
	//						double realPrice = Math.rint(callPrice);
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//policyRulesList1.get(n).setCallPrice(realPrice);
							//总则OPEN票无奖励值为Y时，需判断本张票是否为OPEN票，如果是，则本张票无任何奖励
	//					}else if("Y".equals(generalRules.getNoOpenReward()) && "OPEN".equals("OPEN")){
						}else if("Y".equals(generalRules.getNoOpenReward())){
	//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
	//						double realPrice = Math.rint(callPrice);
	//						policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								PersonTypeInfo personInfo = new PersonTypeInfo();
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//总则含XXX票价无奖励值为Y时，需判断本张票是否含XXX票价基础，如果含，则本张票无任何奖励
	//					}else if("Y".equals(generalRules.getNoInvolveReward()) && "".equals("")){
						}else if("Y".equals(generalRules.getNoInvolveReward())){
	//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
	//						double realPrice = Math.rint(callPrice);
	//						policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								PersonTypeInfo personInfo = new PersonTypeInfo();
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
							//总则低于￥￥￥票价无奖励值为Y时，需判断本张票价是否低于规定价格，如果低于，则本张票无任何奖励
	//					}else if("Y".equals(generalRules.getNoInvolveReward()) && price < Double.parseDouble(generalRules.getNoInvolvePrice())){
						}else if("Y".equals(generalRules.getNoInvolveReward())){
	//						callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
	//						double realPrice = Math.rint(callPrice);
	//						policyRulesList1.get(n).setCallPrice(realPrice);
							
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = price*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice1 = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice1);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
								personList.add(personInfo);
							}
						}else{
							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
								PersonTypeInfo personInfo = new PersonTypeInfo();
								//计奖部分
								Double isPrice = 0.0;
								//不计奖部分
								Double noPrice = 0.0;
								for(int j = 0 ; j < rt.getPersonTypeInfoList().get(i).getRouteList().size(); j++){
									Route route1 = rt.getPersonTypeInfoList().get(i).getRouteList().get(j);
									if("Y".equals(route1.getIsFc())){
										isPrice += route1.getFlyPrice();
									}else{
										//判断本航段是否属于AddOn航段
										if("Y".equals(route1.getIsAddOn())){
											//判断本条总则中，AddOn是否计奖
											if("Y".equals(generalRules.getAddOnChoice())){
												//判断本行段是否为国内AddOn
												if("0".equals(route1.getAddOnFly())){
													//判断本条总则中国内AddOn是否计奖
													if("Y".equals(generalRules.getAddOnInternalChoice())){
														isPrice += route1.getFlyPrice();
													}else{
														noPrice += route1.getFlyPrice();
													}
													//判断本航段是否为国际AddOn
												}else if("1".equals(route1.getAddOnFly())){
													//判断本条总则中国际AddOn是否计奖
													if("Y".equals(generalRules.getAddOnWorldChoice())){
														isPrice += route1.getFlyPrice();
													}else{
														noPrice += route1.getFlyPrice();
													}
												}
											}else{
												noPrice += route1.getFlyPrice();
											}
											
											//判断本条航段是否属于SPA航段
										}else if("Y".equals(route1.getIsSPA())){
											//判断本条总则中SPA是否计奖
											if("Y".equals(generalRules.getSpaChoice())){
												isPrice += route1.getFlyPrice();
											}else{
												noPrice += route1.getFlyPrice();
											}
											//判断本条航段是否属于主FC航段
										}
									}
								}
								//Double callPrice1 = Double.parseDouble(qte.getFare());
								Double billingFee = Double.parseDouble(policy.getBillingFee());
								callPrice = isPrice*(1-agencyFee)*(1-rebate)+noPrice*(1-agencyFee)+billingFee+tax;
								double realPrice = Math.rint(callPrice);
								personInfo.setFlyPrice(realPrice);
								personInfo.setTax(Double.parseDouble(qte.getTax()));
								personInfo.setPersonType(qte.getIdentity());
								personInfo.setIsPrice(Math.rint(isPrice));
								personInfo.setPrice(Double.parseDouble(qte.getFare()));
								personInfo.setAgenCy(agencyFee);
								personInfo.setRebate(rebate);
								personList.add(personInfo);
							}
						
						//	policyRulesList1.get(n).setPersonList(personList);
							
							}
							policyRulesList.add(policy);
							policyRulesList.get(p).setPersonList(personList);
						}
					} 
				}
			}
		}
		
//		List<PolicyRules> policyLists = new ArrayList<PolicyRules>();
//		for(int i = 0 ; i < policyRulesList.size(); i++){
//			PolicyRules policy = policyRulesList.get(i);
//			for(int j = 0; j < policyRulesList.size(); j++){
//				PolicyRules policyRules = policyRulesList.get(j);
//				Double policyPrice = policy.getPersonList().get(0).getFlyPrice();
//				Double policy1Price = policyRules.getPersonList().get(0).getFlyPrice();
//				if(policy.getSupplierId().equals(policyRules.getSupplierId())){
//					if(policyPrice >= policy1Price){
//						policyLists.add(policy);
//						break;
//					}else{
//						policyLists.add(policyRules);
//						break;
//					}
//				}
//			}
//		}
//		for(int i = 0 ; i < policyRulesList.size(); i++){
//			int j = i+1;
//			if(i == (policyRulesList.size()-1)){
//				break;
//			}
//			PolicyRules policy = policyRulesList.get(i);
//			PolicyRules policy1 = policyRulesList.get(j);
//			Double policyPrice = policy.getPersonList().get(0).getFlyPrice();
//			Double policy1Price = policy1.getPersonList().get(0).getFlyPrice();
//			if(policy.getSupplierId().equals(policy1.getSupplierId())){
//				if(policyPrice >= policy1Price){
//					policyRulesList.remove(i+1);
//					i--;
//					continue;
//				}else{
//					policyRulesList.remove(i);
//					i--;
//					continue;
//				}
//			}
//		}
		//去除重复供应商政策并保留成人结算价最高的。
				for(int i=0;i<policyRulesList.size();i++){
		            for(int j=policyRulesList.size()-1;j>i;j--){
		                if(policyRulesList.get(i).getSupplierId().equals(policyRulesList.get(j).getSupplierId())){
		                	if(policyRulesList.get(i).getPersonList().get(0).getFlyPrice() <= policyRulesList.get(j).getPersonList().get(0).getFlyPrice()){
		                		policyRulesList.remove(j);
		                	}else{
		                		policyRulesList.remove(i);
		                	}
		                }
		            }
		        }
		rt.setPolices(policyRulesList);
		return rt;
	}
	
	/**
	 * 返给保盛处理的政策
	 * @param rt
	 * @param generalRulesList
	 * @return
	 */
//	public Rt getBsPolicy(Rt rt,List<GeneralRules> generalRulesList){
//		List<PersonTypeInfo> personTypeInfoList = new ArrayList<PersonTypeInfo>();
//	//	List<Route> routeList = rt.getRoute_list();
//		for(int i = 0 ; i < rt.getPersonTypeInfoList().size(); i++){
//			Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
//			PersonTypeInfo personInfo = new PersonTypeInfo();
//			Double callPrice = Double.parseDouble(qte.getFare())*(1-0.05)+Double.parseDouble(qte.getTax())+60.22;
//			double realPrice = Math.rint(callPrice);
//			personInfo.setPersonType(qte.getIdentity());
//			personInfo.setFlyPrice(realPrice);
//			personInfo.setAgenCy(0.05);
//			//personInfo.setRebate(Double.parseDouble(policy.getRebate()));
//			personInfo.setTax(Double.parseDouble(qte.getTax()));
//			personInfo.setPrice(Double.parseDouble(qte.getFare()));
//			personTypeInfoList.add(personInfo);
//		}
//		rt.setPersonTypeInfoList(personTypeInfoList);
//		return rt;
//	}
	
	/**
	 * 匹配政策的总方法
	 * @param rt
	 * @param generalRulesList
	 * @return
	 */
	public Rt getPolicyRulesListByQz(Rt rt,List<GeneralRules> generalRulesList) {
		//返回的集合
		//List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
		//单程的计奖计价
		if("0".equals(rt.getTravelType())){
			rt = getOneTravelType(rt,generalRulesList);
			//往返的计奖计价
		}else if("1".equals(rt.getTravelType())){
			//非政策组合匹配
			rt = getGoAndBack(rt,generalRulesList);
//			//政策组合匹配(组合思路，去程起点与回程终点不一致时)
			List<Route> routeList = rt.getRoute_list();
			int listSize = routeList.size()/2-1;
			List<PolicyRules> policyRules = new ArrayList<PolicyRules>();
			Rt rt1 = new Rt();
			if(!routeList.get(0).getFromAirport().equals(routeList.get(routeList.size()-1).getToAirport())
				&& routeList.get(listSize+1).getFromAirport().equals(routeList.get(routeList.size()-1).getToAirport())){
				rt1 = GoandBackGroup(rt,generalRulesList);
				if(rt1.getPolices().size() > 0){
					rt = getPolicyRluesGroup(rt,rt1);
				}
			}else if(!routeList.get(0).getFromAirport().equals(routeList.get(routeList.size()-1).getToAirport()) 
					&& !routeList.get(listSize).getFromAirport().equals(routeList.get(listSize+1).getFromAirport())){
				rt1 = GoandBackGroup(rt,generalRulesList);
				if(rt1.getPolices().size() > 0){
					rt = getPolicyRluesGroup(rt,rt1);
				}
				
			}else if(routeList.get(0).getFromAirport().equals(routeList.get(routeList.size()-1).getToAirport()) 
					&& !routeList.get(listSize).getFromAirport().equals(routeList.get(listSize+1).getFromAirport())){
				rt1 = GoandBackGroup(rt,generalRulesList);
				if(rt1.getPolices().size() > 0){
					rt = getPolicyRluesGroup(rt,rt1);
				}
			}
			if(rt1.getPolices().size() > 0){
				for(int i = 0 ; i < rt1.getPolices().size(); i++){
					policyRules.add((PolicyRules)rt1.getPolices().get(i));
				}
				//去除重复供应商政策并保留成人结算价最高的。
				
				for(int i=0;i<policyRules.size();i++){
		            for(int j=policyRules.size()-1;j>i;j--){
		                if(policyRules.get(i).getSupplierId().equals(policyRules.get(j).getSupplierId())){
		                	if(policyRules.get(i).getPersonList().get(0).getFlyPrice() <= policyRules.get(j).getPersonList().get(0).getFlyPrice()){
		                		policyRules.remove(j);
		                	}else{
		                		policyRules.remove(i);
		                	}
		                }
		            }
		        }
			}
			if(0 != policyRules.size()){
				rt.setPolices(policyRules);
			}
			//抛给保盛的计价
		}else if("3".equals(rt.getTravelType())){
			List<PersonTypeInfo> personInfoList = rt.getPersonTypeInfoList();
			for(int i = 0 ; i < personInfoList.size(); i++){
				// 获取第一段航程票价
				personInfoList.get(i).setRouteList(rt.getRoute_list());
				rt.setPersonTypeInfoList(personInfoList);
			}
			rt.setTravelType("3");
		}
 		return rt;
	}
	
	
	
	/**
	 * 此方法主要用政策组合匹配之后去重（标准与组合同家供应商去重）
	 * @param rt
	 * @param rt1
	 * @return
	 */
	private Rt getPolicyRluesGroup(Rt rt,Rt rt1){
		//int pSize = rt1.getPolices().size();//rt1.getPolices().size();
		List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
		for(int i = 0 ; i < rt1.getPolices().size(); i++){
			PolicyRules policyRules = (PolicyRules)rt1.getPolices().get(i);
			for(int j = 0 ; j < rt.getPolices().size(); j++){
				PolicyRules policyRules1 = (PolicyRules)rt.getPolices().get(j);
				
				Double policyPrice = policyRules.getPersonList().get(0).getFlyPrice();
				Double policy1Price = policyRules1.getPersonList().get(0).getFlyPrice();
				if(policyRules.getSupplierId().equals(policyRules1.getSupplierId())){
					if(policyPrice >= policy1Price){
						policyRulesList.add(policyRules);
					}else{
						policyRulesList.add(policyRules1);
					}
				}
			}
		//	rt.getPolices().add(policyRules);
		}
		
		
//		for(int i = 0 ; i < policyRulesList.size(); i++){
//			for(int j = 0 ; j < policyRulesList.size(); j++){
////				int s = policyRulesList.size()-1;
////				if(i == (policyRulesList.size()-1)){
////					break;
////				}
//				PolicyRules policy = policyRulesList.get(i);
//				PolicyRules policy1 = policyRulesList.get(j);
//				Double policyPrice = policy.getPersonList().get(0).getFlyPrice();
//				Double policy1Price = policy1.getPersonList().get(0).getFlyPrice();
//				if(policy.getSupplierId().equals(policy1.getSupplierId())){
//					if(policyPrice >= policy1Price){
//						policyRulesList.remove(j);
//						i--;
//						continue;
//					}else{
//						policyRulesList.remove(i);
//						i--;
//						continue;
//					}
//				}
//			}
//			
//		}
		
		
		//去除重复供应商政策并保留成人结算价最高的。
		for(int i=0;i<policyRulesList.size();i++){
            for(int j=policyRulesList.size()-1;j>i;j--){
                if(policyRulesList.get(i).getSupplierId().equals(policyRulesList.get(j).getSupplierId())){
                	if(policyRulesList.get(i).getPersonList().get(0).getFlyPrice() >= policyRulesList.get(j).getPersonList().get(0).getFlyPrice()){
                		policyRulesList.remove(j);
                	}else{
                		policyRulesList.remove(i);
                	}
                }
            }
        }

		
		rt.setPolices(policyRulesList);
		return rt;
	}
	
	/**
	 * 政策匹配时所需要的身份匹配
	 * @param passengerList
	 * @return
	 */
	private String getPersonStatus(List<Passenger> passengerList){
		StringBuffer typeString = new StringBuffer();
		String typeString1 = "";
		//根据FC集合里的旅客类型拆解出旅客的身份
		//解出普通省份
		try {
			for(int i = 0 ; i < passengerList.size(); i++){
				Passenger passenger = passengerList.get(i);
				if("0".equals(passenger.getPassIdentity())){
					typeString.append("0,");
					break;
				}
			}
			//解出移民省份
			for(int i = 0 ; i < passengerList.size(); i++){
				Passenger passenger = passengerList.get(i);
				if("6".equals(passenger.getPassIdentity())){
					typeString.append("1,");
					break;
				}
			}
			//解出留学生身份
			for(int i = 0 ; i < passengerList.size(); i++){
				Passenger passenger = passengerList.get(i);
				if("2".equals(passenger.getPassIdentity())){
					typeString.append("2,");
					break;
				}
			}
			//解出劳工身份
			for(int i = 0 ; i < passengerList.size(); i++){
				Passenger passenger = passengerList.get(i);
				if("3".equals(passenger.getPassIdentity())){
					typeString.append("3,");
					break;
				}
			}
			typeString1 = typeString.substring(0,typeString.length()-1);
		} catch (Exception e) {
			logger.error("旅客身份取值失败",e);
		}
		return typeString1;
	}
	
	
	
	private String getCabin(List<Route> routeList){
		HashSet<String> clazzSet = new HashSet<String>();
		for (Route route : routeList) {
			clazzSet.add(route.getClazz());
		}
		StringBuffer calzzStr = new StringBuffer();
		for (String classZ : clazzSet) {
			calzzStr.append(classZ.toString()+"/");
		}
		return calzzStr.toString();
	}
	
	
	
	
	
	/**
	 * 政策匹配的计算方式，主要方法
	 * 返回政策集合
	 */
//	public List<PolicyRules> getPolicyRulesListByQz1(Rt rt,List<GeneralRules> generalRulesList) {
//		//返回集合
//		List<PolicyRules> policyRulesList = new ArrayList<PolicyRules>();
//		List<Route> routeList = rt.getRoute_list();
//		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
////		int j = routeList.size();
//		//判断 ,0为单程
//		if("0".equals(rt.getTravelType())){
//			//一段航程的计算
//			if(routeList.size() == 1){
//				Route route = routeList.get(0);
//				PolicyRules policyRules = new PolicyRules();
//				policyRules.setOutOrg(route.getFromCityCode());
//				policyRules.setOutDes(route.getToCityCode());
//				policyRules.setPolicyStatus("0");
//				policyRules.setTravelType("0");
//				Date startTime;
//				Date endTime;
//				try {
//					if(null != route.getFromTime()){
//						startTime = sim.parse(route.getFromTime());
//						policyRules.setFirstDateStart(startTime);
//					}
//					if(null != route.getToTime()){
//						endTime = sim.parse(route.getToTime());
//						policyRules.setFirstDateEnd(endTime);
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				policyRules.setTktAirline(rt.getComput());
//				List<PolicyRules> policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//				//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
//				for(int n = 0; n < policyRulesList1.size();n++){
//					for(int m = 0;m < generalRulesList.size();m++){
//						//销售价(不含税)
//						Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//						//税费
//						Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//						Double callPrice = null;
//						PolicyRules policy = policyRulesList1.get(n);
//						GeneralRules generalRules = generalRulesList.get(m);
//						//总则IT票无奖励值为“Y”时，并且为IT票，本张票无奖励,IT票的值从PNR文本取
////						if("Y".equals(generalRules.getNoItReward().trim().toUpperCase()) && "IT".equals("IT")){
//							if("Y".equals(generalRules.getNoItReward().trim().toUpperCase())){
////							callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
//							callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//							double realPrice = Math.rint(callPrice);
//							policyRulesList1.get(n).setCallPrice(realPrice);
//							//总则OPEN票无奖励值为Y时，需判断本张票是否为OPEN票，如果是，则本张票无任何奖励
////						}else if("Y".equals(generalRules.getNoOpenReward()) && "OPEN".equals("OPEN")){
//						}else if("Y".equals(generalRules.getNoOpenReward())){
//							callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//							double realPrice = Math.rint(callPrice);
//							policyRulesList1.get(n).setCallPrice(realPrice);
//							//总则含XXX票价无奖励值为Y时，需判断本张票是否含XXX票价基础，如果含，则本张票无任何奖励
////						}else if("Y".equals(generalRules.getNoInvolveReward()) && "".equals("")){
//						}else if("Y".equals(generalRules.getNoInvolveReward())){
//							callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//							double realPrice = Math.rint(callPrice);
//							policyRulesList1.get(n).setCallPrice(realPrice);
//							//总则低于￥￥￥票价无奖励值为Y时，需判断本张票价是否低于规定价格，如果低于，则本张票无任何奖励
////						}else if("Y".equals(generalRules.getNoInvolveReward()) && price < Double.parseDouble(generalRules.getNoInvolvePrice())){
//						}else if("Y".equals(generalRules.getNoInvolveReward())){
//							callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//							double realPrice = Math.rint(callPrice);
//							policyRulesList1.get(n).setCallPrice(realPrice);
//						}else{
//							for(int i = 0 ; i < rt.getPersonTypeInfoList().size();i++){
//								Qte qte = rt.getPersonTypeInfoList().get(i).getQte();
//								Double callPrice1 = Double.parseDouble(qte.getFare());
//								callPrice = callPrice1*(1-Double.parseDouble((policy.getRebate())))*(1-Double.parseDouble(policy.getAgencyFee()))+Double.parseDouble(policy.getBillingFee())+tax;
//								double realPrice = Math.rint(callPrice);
//								PersonTypeInfo personInfo = new PersonTypeInfo();
//								personInfo.setFlyPrice(realPrice);
//								personInfo.setTax(Double.parseDouble(qte.getTax()));
//								personInfo.setPersonType(qte.getIdentity());
//								personInfo.setPrice(Double.parseDouble(qte.getFare()));
//								personInfo.setAgenCy(Double.parseDouble(policy.getAgencyFee()));
//								personInfo.setRebate(Double.parseDouble(policy.getRebate()));
//								personInfo.setIsPrice(callPrice1);
//								policyRulesList1.get(n).getPersonList().add(personInfo);
//							}
//						}
//					}
//					policyRulesList = policyRulesList1;
//				}
//				//单程两段航程的计算，判断出主Fc,和SPA或者是AddOn的计奖
//			}else if(routeList.size() == 2){
//				List<PolicyRules> policyRulesList1 = new ArrayList<PolicyRules>();
//				Route route1 = routeList.get(0);
//				Route route2 = routeList.get(1);
//				PolicyRules policyRules = new PolicyRules();
//				policyRules.setPolicyStatus("0");
//				//单程
//				policyRules.setTravelType("0");
//				//去程起点
//				policyRules.setOutOrg(route1.getFromCityCode());
//				//去程终点
//				policyRules.setOutDes(route2.getToCityCode());
//				policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//				//计奖部分
//				Double isBonusPrice = 0.00;
//				//不计奖不分
//				Double noBonusPrice = 0.00;
//				//销售价/票面价(不含税)
//				Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//				//结算价
//				Double settlementPrice = 0.00;
//				//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
//				for(int n = 0; n < policyRulesList1.size();n++){
////					User user = userDao.getUser(policyRulesList1.get(n).getSupplierId());
////					if(null != user){
////						policyRulesList1.get(n).setSupplierName(user.getName());
////						policyRulesList1.get(n).setCompanyId(user.getCompanyId());
////					}
//					for(int m = 0;m < generalRulesList.size();m++){
//						GeneralRules generalRules = generalRulesList.get(m);
//						PolicyRules policyRules1 = policyRulesList1.get(n);
//						//IT票无奖励;OPEN票无奖励;含票价基础无奖励;票价低于￥￥无奖励，直接将票面价赋值与不计奖部分
//						if("Y".equals(generalRules.getNoItReward()) || "Y".equals(generalRules.getNoOpenReward()) 
//							|| ("Y".equals(generalRules.getNoInvolveReward()) && "票价基础".equals(generalRules.getNoInvolvePrice()))
//							|| ("Y".equals(generalRules.getNoLowReward()) && price < Double.parseDouble(generalRules.getNoLowPrice()))){
//							noBonusPrice = price;
//							//没有无奖励情况，判断有奖励情况
//						}else{
//							//判断是否为SPA航段
//							if("Y".equals(route1.getIsSPA())){
//								//判断SPA是否计奖
//								if("Y".equals(generalRules.getSpaChoice())){
//									//计奖则把本行段票价赋予计奖部分
//									isBonusPrice = route1.getFlyPrice();
//								}else{
//									//不计奖则把本航段票价赋予不计奖部分
//									noBonusPrice = route1.getFlyPrice();
//								}
//							//判断是否为AddOn航段及国内AddOn航段	
//							}else if("Y".equals(route1.getIsAddOn()) && "0".equals(route1.getAddOnFly())){
//								//计奖则把本行段票价赋予计奖部分
//								if("Y".equals(generalRules.getAddOnInternalChoice())){
//									isBonusPrice = route1.getFlyPrice();
//								}else{
//									//不计奖则把本航段票价赋予不计奖部分
//									noBonusPrice = route1.getFlyPrice();
//								}
//							}
//							//判断第二段航段是否为主FC，为主Fc则直接赋予计奖部分
//							if("Y".equals(route2.getIsFc())){
//								 isBonusPrice += route2.getFlyPrice();//routeList.get(0).getPrice();
//							}else{
//								noBonusPrice += route2.getFlyPrice();
//							}
//						}
//						//根据计算公式计算结算价
//						//代理费
//						Double agencyFee = Double.parseDouble(policyRules1.getAgencyFee());
//						//返点
//						Double rebate = Double.parseDouble(policyRules1.getRebate());
//						//税费
//						Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//						//手续费
//						Double billingFee = Double.parseDouble(policyRules1.getBillingFee());
//						//采购价计算公式=计奖的部分*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
//						settlementPrice = isBonusPrice * (1-agencyFee)*(1-rebate)+noBonusPrice*(1-agencyFee)+tax+billingFee;
//						double realPrice = Math.rint(settlementPrice);
//						policyRulesList1.get(n).setCallPrice(realPrice);
//					}
//				}
//				policyRulesList = policyRulesList1;
//				
//				//单程3段
//			}else if(routeList.size() == 3){
//				/**第一段为国内航段，直接判断是AddOn或者SPA*/
//				List<PolicyRules> policyRulesList1 = new ArrayList<PolicyRules>();
//				List<PolicyRules> policyRulesList2 = new ArrayList<PolicyRules>();
//				Route route1 = routeList.get(0);
//				Route route2 = routeList.get(1);
//				Route route3 = routeList.get(2);
//				PolicyRules policyRules = new PolicyRules();
//				policyRules.setPolicyStatus("0");
//				//单程
//				policyRules.setTravelType("0");
//				//去程起点
//				policyRules.setOutOrg(route1.getFromCityCode());
//				//去程终点
//				policyRules.setOutDes(route3.getToCityCode());
//				policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//				//结算价
//				Double settlementPrice = 0.00;
//				
//				for(int n = 0; n < policyRulesList1.size();n++){
////					User user = userDao.getUser(policyRulesList1.get(n).getSupplierId());
////					if(null != user){
////						policyRulesList1.get(n).setSupplierName(user.getName());
////						policyRulesList1.get(n).setCompanyId(user.getCompanyId());
////					}
//					for(int m = 0;m < generalRulesList.size();m++){
//						GeneralRules generalRules = generalRulesList.get(m);
//						PolicyRules policyRules1 = policyRulesList1.get(n);
//						//计奖部分
//						Double isBonusPrice = 0.00;
//						//不计奖不分
//						Double noBonusPrice = 0.00;
//						//销售价/票面价(不含税)
//						Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//						for(int i = 0; i < routeList.size(); i++){
//							Route route = routeList.get(i);
//							if("Y".equals(route.getIsFc())){
//								isBonusPrice += route.getFlyPrice();
//							}else{
//								if("Y".equals(generalRules.getSpaChoice())){
//									if("Y".equals(route.getIsSPA())){
//										isBonusPrice += route.getFlyPrice();
//									}else{
//										noBonusPrice += route.getFlyPrice();
//									}
//								}else{
//									if("Y".equals(generalRules.getAddOnChoice())){
//										if("Y".equals(route.getIsAddOn())){
//											//0位国内AddOn
//											if("0".equals(route.getAddOnFly())){
//												//国内AddOn是否计奖
//												if("Y".equals(generalRules.getAddOnInternalChoice())){
//													isBonusPrice += route.getFlyPrice();
//												}else{
//													noBonusPrice += route.getFlyPrice();
//												}
//												//为国际AddOn
//											}else{
//												//国际AddOn是否计奖
//												if("Y".equals(generalRules.getAddOnWorldChoice())){
//													isBonusPrice += route.getFlyPrice();
//												}else{
//													noBonusPrice += route.getFlyPrice();
//												}
//											}
//										}else{
//											noBonusPrice += route.getFlyPrice();
//										}
//									}else{
//										noBonusPrice += route.getFlyPrice();
//									}
//								}
//							}
//						}
////						//判断第一段是否为SPA
////						if("Y".equals(route1.getIsSPA())){
////							//判断本条总则SPA航段是否计奖
////							if("Y".equals(generalRules.getSpaChoice())){
////								//将本航段运价赋予计奖部分
////								isBonusPrice = 2500.0;
////							}else {
////								//将本航段运价赋予不计奖部分
////								noBonusPrice = 2500.0;
////							}
////						//判断是否为(国内航段)AddOn航段
////						}else if("Y".equals(route1.getIsAddOn()) && "0".equals(route1.getAddOnFly())){
////							//判断本条总则国内航段是否计奖
////							if("Y".equals(generalRules.getAddOnChoice()) && "Y".equals(generalRules.getAddOnInternalChoice())){
////								//将本航段运价赋予计奖部分
////								isBonusPrice = 2500.0;
////							}else{
////								//将本航段运价赋予不计奖部分
////								noBonusPrice = 2500.0;
////							}
////						}
////						//判断第二段是否为主FC
////						if("Y".equals(route2.getIsFc())){
////							//如果为主FC，直接赋值与计奖部分
////							//将本航段运价赋予计奖部分
////							isBonusPrice += 5500.0;
////						}else{
////							//如果不是主FC，判断是否为国际AddOn或者SPA
////							if("Y".equals(route2.getIsSPA())){
////							 //如果是SPA，判断本条总则SPA是否计奖
////								if("Y".equals(generalRules.getSpaChoice())){
////									//如果计奖，将本行段赋值与计奖部分
////									isBonusPrice += 5500.0;
////								}else{
////									//如果不计奖，将本航段赋值与不计奖部分
////									noBonusPrice += 5500.0;
////								}
////							//判断是否为国际AddOn航段
////							}else if("Y".equals(route2.getIsAddOn()) && "1".equals(route2.getAddOnFly())){
////								//判断本条总则国际AddOn航段是否计奖
////								if("Y".equals(generalRules.getAddOnChoice()) && "Y".equals(generalRules.getAddOnWorldChoice())){
////									//如果计奖，将本航段赋值与计奖部分
////									isBonusPrice += 5500.0;
////								}else{
////									//如果不计奖，将本航段赋值与不计奖部分
////									noBonusPrice += 5500.0;
////								}
////							}
////						}
//						//根据计算公式计算结算价
//						//代理费
//						Double agencyFee = Double.parseDouble(policyRules1.getAgencyFee());
//						//返点
//						Double rebate = Double.parseDouble(policyRules1.getRebate());
//						//税费
//						Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//						//手续费
//						Double billingFee = Double.parseDouble(policyRules1.getBillingFee());
//						//采购价计算公式=计奖的部分*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
//						settlementPrice = isBonusPrice * (1-agencyFee)*(1-rebate)+noBonusPrice*(1-agencyFee)+tax+billingFee;
//						double realPrice = Math.rint(settlementPrice);
////						policyRulesList1.get(n).setCallPrice(realPrice);
//						PolicyRules policyRules2 = policyRulesList1.get(n);
//						policyRules2.setCallPrice(realPrice);
//						policyRulesList2.add(policyRules2);
//					}
//				}
//				policyRulesList = policyRulesList1;
//			}
//			//1为往返航程
//		}else if("1".equals(rt.getTravelType())){
//			//标准往返两段计算，两段都为主FC,满足计奖条件，两段都以主FC的方式计算
//			if(routeList.size() == 2){
//				List<PolicyRules> policyRulesList1 = new ArrayList<PolicyRules>();
//				PolicyRules policyRules = new PolicyRules();
//					Route route = routeList.get(0); 
//					//判断是否为往返类型
//					//if(route.getFromCity().equals(routeList.get(1).getTocity())){
//						//出票航司
//						policyRules.setTktAirline(rt.getComput());
//						//启用状态
//						policyRules.setPolicyStatus("0");
//						//去程起点
//						policyRules.setOutOrg(route.getFromCityCode());
//						//去程终点
//						policyRules.setOutDes(route.getToCityCode());
//						//回程终点
//						policyRules.setReturnDes(routeList.get(1).getToCityCode());
//						policyRules.setTravelType("1");
//						//获取政策集合
//						policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//						//如果是国际一段，就有且只有一个主FC，不做任何判断，直接计算计奖
//						for(int n = 0; n < policyRulesList1.size();n++){
////							User user = userDao.getUser(policyRulesList1.get(n).getSupplierId());
////							if(null != user){
////								policyRulesList1.get(n).setSupplierName(user.getName());
////								policyRulesList1.get(n).setCompanyId(user.getCompanyId());
////							}
//							for(int m = 0;m < generalRulesList.size();m++){
//								//获取PNR解析出的QTE销售价(不含税)
//								Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//								//获取PRN解析出来的QTE税费
//								Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//								Double callPrice = null;
//								PolicyRules policy = policyRulesList1.get(n);
//								GeneralRules generalRules = generalRulesList.get(m);
//								//总则IT票无奖励值为“Y”时，并且为IT票，本张票无奖励,IT票的值从PNR文本取
//								if("Y".equals(generalRules.getNoItReward().trim().toUpperCase()) && "IT".equals("IT")){
////									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
//									//单程一段IT无奖励的计算,无计奖部分*(1-代理税费)+手续费+税款
//									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//									double realPrice = Math.rint(callPrice);
//									policyRulesList1.get(n).setCallPrice(realPrice);
//									//总则OPEN票无奖励值为Y时，需判断本张票是否为OPEN票，如果是，则本张票无任何奖励
//								}else if("Y".equals(generalRules.getNoOpenReward()) && "OPEN".equals("OPEN")){
//									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//									double realPrice = Math.rint(callPrice);
//									policyRulesList1.get(n).setCallPrice(realPrice);
//									//总则含XXX票价无奖励值为Y时，需判断本张票是否含XXX票价基础，如果含，则本张票无任何奖励
//								}else if("Y".equals(generalRules.getNoInvolveReward()) && "".equals("")){
//									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//									double realPrice = Math.rint(callPrice);
//									policyRulesList1.get(n).setCallPrice(realPrice);
//									//总则低于￥￥￥票价无奖励值为Y时，需判断本张票价是否低于规定价格，如果低于，则本张票无任何奖励
//								}else if("Y".equals(generalRules.getNoInvolveReward()) && price < Double.parseDouble(generalRules.getNoInvolvePrice())){
//									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))+Double.parseDouble(policy.getBillingFee())+tax;
//									double realPrice = Math.rint(callPrice);
//									policyRulesList1.get(n).setCallPrice(realPrice);
//									//以上条件都不满足，一段国际航程按照主FC计奖
//								}else{
//									//单程一段主FC计奖的计算,计奖部分*(1-代理税费)*(1-返点)+手续费+税款
//									callPrice = price*(1-Double.parseDouble((policy.getAgencyFee())))*(1-Double.parseDouble(policy.getRebate()))+Double.parseDouble(policy.getBillingFee())+tax;
//									double realPrice = Math.rint(callPrice);
//									policyRulesList1.get(n).setCallPrice(realPrice);
//								}
//							}
//						}
//						policyRulesList = policyRulesList1;
//					//}
//			//往返4段的航程计算，找出两段主FC(去程一个，返程一个),找出SPA和AddOn并根据总则匹配进行计奖计算
//			}else if(routeList.size() == 4){
//				List<PolicyRules> policyRulesList1 = new ArrayList<PolicyRules>();
//				List<PolicyRules> policyRulesList2 = new ArrayList<PolicyRules>();
//				PolicyRules policyRules = new PolicyRules();
//				policyRules.setPolicyStatus("0");
//				//往返类型
//				policyRules.setTravelType("1");
//				//去程起点
//				policyRules.setOutOrg(routeList.get(0).getFromCityCode());
//				//去程终点
//				policyRules.setOutDes(routeList.get(1).getToCityCode());
//				//回程终点
//				policyRules.setReturnDes(routeList.get(3).getToCityCode());
//				policyRules.setTktAirline(rt.getComput());
//				policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//					for(int m = 0; m < policyRulesList1.size(); m++){
//						//计奖部分
//						Double isBonusPrice = 0.00;
//						//不计奖不分
//						Double noBonusPrice = 0.00;
//						//销售价/票面价(不含税)
//						Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//						//结算价
//						Double settlementPrice = 0.00;
//						for(int n = 0; n < generalRulesList.size(); n++){
////						User user = userDao.getUser(policyRulesList1.get(m).getSupplierId());
////						if(null != user){
////							policyRulesList1.get(m).setSupplierName(user.getName());
////							policyRulesList1.get(n).setCompanyId(user.getCompanyId());
////						}
//					GeneralRules generalRules = generalRulesList.get(n);
//					PolicyRules policyRules1 = policyRulesList1.get(m);
//					
//					for(int i = 0; i < routeList.size();i++){
//						Route route = routeList.get(i);
//					//首先判断本航段是否属于主FC
//					if("Y".equals(route.getIsFc())){
//						//如果为主FC，直接将本航段赋值与计奖部分
//						isBonusPrice += route.getFlyPrice();
//					//如果不知主FC，首先判断是SPA还是AddOn航段
//					}else{
//						//判断是否为SPA航段
//						if("Y".equals(route.getIsSPA())){
//							//如果为SPA航段，判断本条总则是否对SPA航段计奖
//							if("Y".equals(generalRules.getSpaChoice())){
//								//如果本航段SPA计奖，将本航段运价赋值与计奖部分
//								isBonusPrice += route.getFlyPrice();
//							}else{
//								//如果本航段SPA不计奖，将本航段运价赋值与不计奖部分
//								noBonusPrice += route.getFlyPrice();
//							}
//						//判断本航段是否属于AddOn航段
//						}else if("Y".equals(route.getIsAddOn())){
//							//判断本条总则是否对AddOn计奖
//							if("Y".equals(generalRules.getAddOnChoice())){
//								//如果对本段航程计奖，判断本段航程是国内AddOn还是国际AddOn
//								if("0".equals(route.getAddOnFly())){
//									//如果为国内AddOn,判断本条总则国内AddOn是否计奖
//									if("Y".equals(generalRules.getAddOnInternalChoice())){
//										//如果国内AddOn计奖则将本段航程赋值与计奖部分
//										isBonusPrice += route.getFlyPrice();
//									}else{
//										//如果国内AddOn不计奖，则将本航段赋值与不计奖部分
//										noBonusPrice += route.getFlyPrice();
//									}
//								//判断本段航程是否为国际AddOn航段
//								}else if("1".equals(route.getAddOnFly())){
//									//如果为国际AddOn，判断本条总则国际AddOn航段是否计奖
//									if("Y".equals(generalRules.getAddOnWorldChoice())){
//										//如果国际AddOn计奖，则将本段航程运价赋值与计奖部分
//										isBonusPrice += route.getFlyPrice();
//									}else{
//										//如果国际AddOn不计奖，则将本段航程运价赋值与不计奖部分
//										noBonusPrice += route.getFlyPrice();
//									}
//								}
//							}
//						}
//					}
//				}
//					//根据计算公式计算结算价
//					//代理费
//					Double agencyFee = Double.parseDouble(policyRules1.getAgencyFee());
//					//返点
//					Double rebate = Double.parseDouble(policyRules1.getRebate());
//					//税费
//					Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//					//手续费
//					Double billingFee = Double.parseDouble(policyRules1.getBillingFee());
//					//采购价计算公式=计奖的部分*（1-代理费率）*（1-返点）+无奖励的部分*（1-代理费率）+税款+手续费
//					settlementPrice = isBonusPrice * (1-agencyFee)*(1-rebate)+noBonusPrice*(1-agencyFee)+tax+billingFee;
//					double realPrice = Math.rint(settlementPrice);
//					PolicyRules policyRules2 = policyRulesList1.get(m);
//					policyRules2.setCallPrice(realPrice);
//					policyRulesList2.add(policyRules2);
//			}
//					
//		}
//				policyRulesList = policyRulesList2;
//			//往返6段航程的计算,找出两段主FC(去程一个,返程一个),找出SPA和AddOn并根据总则匹配进行计奖计算
//			}else if(routeList.size() == 6){
//				List<PolicyRules> policyRulesList1 = new ArrayList<PolicyRules>();
//				List<PolicyRules> policyRulesList2 = new ArrayList<PolicyRules>();
//				PolicyRules policyRules = new PolicyRules();
//				policyRules.setPolicyStatus("0");
//				//往返类型
//				policyRules.setTravelType("1");
//				//去程起点
//				policyRules.setOutOrg(routeList.get(0).getFromCityCode());
//				//去程终点
//				policyRules.setOutDes(routeList.get(2).getToCityCode());
//				//回程终点
//				policyRules.setReturnDes(routeList.get(5).getToCityCode());
//				policyRulesList1 = policyRulesDao.getPolicyRulesListByQz1(policyRules);
//			
//					for(int n = 0; n < generalRulesList.size(); n++){
//						for(int m = 0; m < policyRulesList1.size(); m++){
////							User user = userDao.getUser(policyRulesList1.get(m).getSupplierId());
////							if(null != user){
////								policyRulesList1.get(m).setSupplierName(user.getName());
////								policyRulesList1.get(n).setCompanyId(user.getCompanyId());
////							}
//							GeneralRules generalRules = generalRulesList.get(n);
//							PolicyRules policyRules1 = policyRulesList1.get(m);
//							//结算价
//							Double settlementPrice = 0.00;
//							//计奖部分
//							Double isBonusPrice = 0.00;
//							//不计奖不分
//							Double noBonusPrice = 0.00;
//							//销售价/票面价(不含税)
//							Double price = Double.parseDouble(rt.getQte().get(0).getFare());
//							for(int i = 0; i < routeList.size();i++){
//							Route route = routeList.get(i);
//							//首先判断本航段是否属于主FC
//							if("Y".equals(route.getIsFc())){
//								//如果为主FC，直接将本航段赋值与计奖部分
//								isBonusPrice += 9000.0;
//							//如果不知主FC，首先判断是SPA还是AddOn航段
//							}else{
//								//判断是否为SPA航段
//								if("Y".equals(route.getIsSPA())){
//									//如果为SPA航段，判断本条总则是否对SPA航段计奖
//									if("Y".equals(generalRules.getSpaChoice())){
//										//如果本航段SPA计奖，将本航段运价赋值与计奖部分
//										isBonusPrice += 9000.0;
//									}else{
//										//如果本航段SPA不计奖，将本航段运价赋值与不计奖部分
//										noBonusPrice += 9000.0;
//									}
//								//判断本航段是否属于AddOn航段
//								}else if("Y".equals(route.getIsAddOn())){
//									//判断本条总则是否对AddOn计奖
//									if("Y".equals(generalRules.getAddOnChoice())){
//										//如果对本段航程计奖，判断本段航程是国内AddOn还是国际AddOn
//										if("0".equals(route.getAddOnFly())){
//											//如果为国内AddOn,判断本条总则国内AddOn是否计奖
//											if("Y".equals(generalRules.getAddOnInternalChoice())){
//												//如果国内AddOn计奖则将本段航程赋值与计奖部分
//												isBonusPrice += 9000.0;
//											}else{
//												//如果国内AddOn不计奖，则将本航段赋值与不计奖部分
//												noBonusPrice += 9000.0;
//											}
//										//判断本段航程是否为国际AddOn航段
//										}else if("1".equals(route.getAddOnFly())){
//											//如果为国际AddOn，判断本条总则国际AddOn航段是否计奖
//											if("Y".equals(generalRules.getAddOnWorldChoice())){
//												//如果国际AddOn计奖，则将本段航程运价赋值与计奖部分
//												isBonusPrice += 9000.0;
//											}else{
//												//如果国际AddOn不计奖，则将本段航程运价赋值与不计奖部分
//												noBonusPrice += 9000.0;
//											}
//										}
//									}
//								}
//							}
//							
//						}
//							//根据计算公式计算结算价
//							//代理费
//							Double agencyFee = Double.parseDouble(policyRules1.getAgencyFee());
//							//返点
//							Double rebate = Double.parseDouble(policyRules1.getRebate());
//							//税费
//							Double tax = Double.parseDouble(rt.getQte().get(0).getTax());
//							//手续费
//							Double billingFee = Double.parseDouble(policyRules1.getBillingFee());
//							settlementPrice = isBonusPrice * (1-agencyFee)*(1-rebate)+noBonusPrice*(1-agencyFee)+tax+billingFee;
//							double realPrice = Math.rint(settlementPrice);
//							PolicyRules policyRules2 = policyRulesList1.get(m);
//							policyRules2.setCallPrice(realPrice);
//							policyRulesList2.add(policyRules2);
//					}
//				}
//				policyRulesList = policyRulesList2;
//			}
//		}else if("3".equals(rt.getTravelType())){
//			
//		}
////		for(int i = 0; i < j; i++){
////			
////			Route route = routeList.get(i);
////			PolicyRules policyRulesListByQz = policyRulesDao.getPolicyRulesListByQz(route.getFromCity(),route.getTocity());
////			policyRulesList.add(policyRulesListByQz);
////		}
////		List<PolicyRules> policyRules3 = new ArrayList<PolicyRules>();
////		for(int i = 0; i < generalRulesList.size(); i++ ){
////			PolicyRules policyRules = new PolicyRules();
////			policyRules.setPolicyStatus("0");
////			policyRules.setTktAirline(generalRulesList.get(i).getAirCode());
////			policyRules3 =  policyRulesDao.findList(policyRules);
////		}
////		for(int i = 0; i < policyRules3.size(); i++){
////			User user = new User();
////			user = userDao.getUser(policyRules3.get(i).getSupplierId());
////			if(null != user){
////				policyRulesList.get(i).setSupplierName(user.getName());
////			}
////		}
//		return policyRulesList;
//	}
	
//	public List<PolicyRules> getPolicyRulesListByQz1(String beginCity, String endCity,String comput) {
//		return policyRulesDao.getPolicyRulesListByQz1(beginCity,endCity,comput);
//	}
	
	/**
	 * 判断航司是否存在
	 */
	@Transactional(readOnly = false)
	public Boolean checkAirline(String codeBrr, List<PubObj> airlineList) {
		Boolean isTrue = false;
		if(null != codeBrr || "" != codeBrr){
			if(null != airlineList){
				for (PubObj airline : airlineList) {
					if(airline.getAirCode().equals(codeBrr)){
						isTrue = true;
					}
				}
			}
		}
		return isTrue;
	}
	
	
	@Transactional(readOnly = false)
	public List<ErrInfo> addPolicyRules(String fileName,String realPath){
		
		List<ErrInfo> errInfoList = new ArrayList<ErrInfo>();
		ImportExcel ei;
		try {
			ei = new ImportExcel(realPath+"/"+fileName, 1);
			for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
				Row row = ei.getRow(i);
				for (int j = 0; j < ei.getLastCellNum(); j++) {
					Object val = ei.getCellValue(row, j);
					System.out.print(val+", ");
				}
				System.out.print("\n");
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return errInfoList;
	}


	
	

}