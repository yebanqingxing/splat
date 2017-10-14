/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.general.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.ptg.TblPtg;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.general.dao.GeneralRulesDao;
import com.sml.sz.general.entity.GeneralRules;

import com.sml.sz.pubobj.PubObj;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.pnr.Route;
import com.sml.sz.sys.pnr.Rt;
import com.sml.sz.sys.utils.UserUtils;


/**
 * 国际政策总则的增删改查Service
 * @author 张权
 * @version 2016-03-09
 */
@Component("generalRulesFacade")
@Transactional(readOnly = true)
public class GeneralRulesServiceImpl extends CrudService<GeneralRulesDao, GeneralRules> implements GeneralRulesFacade{

	
	@Autowired
	GeneralRulesDao generalRulesDao;
	
	/**
	 * 国际政策总则的增删改查 通过ID 获取
	 * @param String id
	 * @return TbGeneralRules
	 */
	public GeneralRules get(String id) {
		return super.get(id);
	}
	
	/**
	 * 国际政策总则的增删改查 查询不分页
	 * @param TbGeneralRules
	 * @return List<TbGeneralRules>
	 */
	public List<GeneralRules> findList(GeneralRules tbGeneralRules) {
		return super.findList(tbGeneralRules);
	}
	
	/**
	 * 国际政策总则的增删改查 查询分页
	 * @param Page<TbGeneralRules> page,TbGeneralRules
	 * @return Page<TbGeneralRules>
	 */
	public Page<GeneralRules> findPage(Page<GeneralRules> page, GeneralRules tbGeneralRules) {
		page.setOld(false);
		return super.findPage(page, tbGeneralRules);
	}
	
	/**
	 * 国际政策总则的增删改查 保存
	 * @param TbGeneralRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(GeneralRules tbGeneralRules) {
		if("CI".equals(tbGeneralRules.getAirCode().trim().toUpperCase()) || "AE".equals(tbGeneralRules.getAirCode().trim().toUpperCase())){
			tbGeneralRules.setAirCode("CI/AE");
		}else if("AF".equals(tbGeneralRules.getAirCode().trim().toUpperCase()) ||
				"KL".equals(tbGeneralRules.getAirCode().trim().toUpperCase())){
			tbGeneralRules.setAirCode("AF/KL");
		}else if("LH".equals(tbGeneralRules.getAirCode().trim().toUpperCase()) ||
				"OS".equals(tbGeneralRules.getAirCode().trim().toUpperCase()) ||
				"LX".equals(tbGeneralRules.getAirCode().trim().toUpperCase())){
			tbGeneralRules.setAirCode("LH/OS/LX");
		}
		if(null != tbGeneralRules){
			//添加数据
			if("".equals(tbGeneralRules.getId())|| null == tbGeneralRules.getId()){
				Boolean flag = false;
				//判断航司是否为空，为空则不做添加
				if(null != tbGeneralRules.getAirCode()){
					flag = true;
				}
				//判断IT票是否选中，如未选中赋值N
				if("".equals(tbGeneralRules.getNoItReward())|| null == tbGeneralRules.getNoItReward()){
					tbGeneralRules.setNoItReward("N");
				}
				//判断OPEN票是否选中，如未选中赋值N
				if("".equals(tbGeneralRules.getNoOpenReward())|| null == tbGeneralRules.getNoOpenReward()){
					tbGeneralRules.setNoOpenReward("N");
				}
				//判断基本票价票是否选中，如未选中赋值N
				if("".equals(tbGeneralRules.getNoInvolveReward())|| null == tbGeneralRules.getNoInvolveReward()){
					tbGeneralRules.setNoInvolveReward("N");
				}
				//如NoInvolveReward值为N时，赋值为0.00
				if("N".equals(tbGeneralRules.getNoInvolveReward())){
					tbGeneralRules.setNoInvolvePrice("0.00");
					//判断如NoInvolveReward值为Y时，可添加基本票价
				}else if("Y".equals(tbGeneralRules.getNoInvolveReward())){
					if("".equals(tbGeneralRules.getNoInvolvePrice())||null==tbGeneralRules.getNoInvolvePrice()){
						tbGeneralRules.setNoInvolvePrice("0.00");
					}else{
						tbGeneralRules.setNoInvolvePrice(tbGeneralRules.getNoInvolvePrice().toString().trim());
						//判断如果基本票价奖励长度大于4，截取
						if(tbGeneralRules.getNoInvolvePrice().toString().trim().length()>4){
						tbGeneralRules.setNoInvolvePrice(tbGeneralRules.getNoInvolvePrice().toString().trim().substring(0,4));
						}
					}
				}
				//判断低于票价是否选中，如未选中赋值N
				if("".equals(tbGeneralRules.getNoLowReward())|| null == tbGeneralRules.getNoLowReward()){
					tbGeneralRules.setNoLowReward("N");
				}
				//如NoLowReward值为N时，赋值为0.00
				if("N".equals(tbGeneralRules.getNoLowReward())){
					tbGeneralRules.setNoLowPrice("0.00");
					//判断如NoLowReward值为Y时，可添加基本票价
				}else if("Y".equals(tbGeneralRules.getNoLowReward())){
					//判断如果票价低于奖励长度大于4，截取
					if("".equals(tbGeneralRules.getNoLowPrice())||null==tbGeneralRules.getNoLowPrice()){
						tbGeneralRules.setNoLowPrice("0.00");
					}else{
					tbGeneralRules.setNoLowPrice(tbGeneralRules.getNoLowPrice());
					}
				}
				//判断AddOn计奖是否选中，如为空赋值为N
				if("".equals(tbGeneralRules.getAddOnChoice())|| null == tbGeneralRules.getAddOnChoice()){
					tbGeneralRules.setAddOnChoice("N");
				}
				//判断国际计奖是否选中，如未选中赋值为N
				if("".equals(tbGeneralRules.getAddOnInternalChoice())|| null == tbGeneralRules.getAddOnInternalChoice()){
					tbGeneralRules.setAddOnInternalChoice("N");
				}
				//判断国内计奖是否选中，如未选中赋值为N
				if("".equals(tbGeneralRules.getAddOnWorldChoice())|| null == tbGeneralRules.getAddOnWorldChoice()){
					tbGeneralRules.setAddOnInternalChoice("N");
				}
				//判断AddOn是否为N，如为空，国际和国内计奖都赋值为N
				if("N".equals(tbGeneralRules.getAddOnChoice())|| null == tbGeneralRules.getAddOnChoice()){
					tbGeneralRules.setAddOnInternalChoice("N");
					tbGeneralRules.setAddOnWorldChoice("N");
				}
				if("".equals(tbGeneralRules.getSpaChoice())|| null == tbGeneralRules.getSpaChoice()){
					tbGeneralRules.setSpaChoice("N");
				}
				if("".equals(tbGeneralRules.getQChoice())|| null == tbGeneralRules.getQChoice()){
					tbGeneralRules.setQChoice("N");
				}
				tbGeneralRules.setGrId(UUID.randomUUID().toString().substring(0,31).toUpperCase());
				if("0".equals(tbGeneralRules.getGeneralStatus())){
					if(flag == true){
						List<GeneralRules> generalRulesList = super.findList(tbGeneralRules);
						if(generalRulesList.size() != 0){
							for(int i = 0;i < generalRulesList.size(); i++){
								generalRulesList.get(i).setGeneralStatus("1");
							}
						}
						tbGeneralRules.setEffectDate(new Date());
						super.save(tbGeneralRules);
					}
				}
				
				//修改数据
			}else if(null != tbGeneralRules.getId()||!tbGeneralRules.getId().equals("")){
				if(!"".equals(tbGeneralRules.getId())|| null != tbGeneralRules.getId()){
					GeneralRules generalRules = new GeneralRules();
					generalRules = super.get(tbGeneralRules.getGrId());
					generalRules.setId(generalRules.getGrId());
					//如果是修改数据，将要修改的数据禁用（状态改为：1）
					generalRules.setGeneralStatus("1");
					generalRules.setEffectDate(new Date());
					super.save(generalRules);
				}
				//如果是修改数据，将要修改的数据禁用（状态改为：1），并将已修改的数据作为一条新的数据插入
				if(!("").equals(tbGeneralRules.getId())||null != tbGeneralRules.getId()){
					//判断IT票是否选中，如未选中赋值N
					if("".equals(tbGeneralRules.getNoItReward())|| null == tbGeneralRules.getNoItReward()){
						tbGeneralRules.setNoItReward("N");
					}
					//判断OPEN票是否选中，如未选中赋值N
					if("".equals(tbGeneralRules.getNoOpenReward())|| null == tbGeneralRules.getNoOpenReward()){
						tbGeneralRules.setNoOpenReward("N");
					}
					//判断基本票价票是否选中，如未选中赋值N
					if("".equals(tbGeneralRules.getNoInvolveReward())|| null == tbGeneralRules.getNoInvolveReward()){
						tbGeneralRules.setNoInvolveReward("N");
					}
					//如NoInvolveReward值为N时，赋值为0.00
					if("N".equals(tbGeneralRules.getNoInvolveReward())){
						tbGeneralRules.setNoInvolvePrice("0.00");
						//判断如NoInvolveReward值为Y时，可添加基本票价
					}else if("Y".equals(tbGeneralRules.getNoInvolveReward())){
						//判断如果基本票价奖励长度大于4，截取
						if("".equals(tbGeneralRules.getNoInvolvePrice())||null==tbGeneralRules.getNoInvolvePrice()){
							tbGeneralRules.setNoInvolvePrice("0.00");
						}else{
							tbGeneralRules.setNoInvolvePrice(tbGeneralRules.getNoInvolvePrice().toString().trim());
							if(tbGeneralRules.getNoInvolvePrice().toString().trim().length()>4){
							tbGeneralRules.setNoInvolvePrice(tbGeneralRules.getNoInvolvePrice().toString().trim().substring(0,4));
							}
						}
					}
					//判断低于票价是否选中，如未选中赋值N
					if("".equals(tbGeneralRules.getNoLowReward())|| null == tbGeneralRules.getNoLowReward()){
						tbGeneralRules.setNoLowReward("N");
					}
					//如NoLowReward值为N时，赋值为0.00
					if("N".equals(tbGeneralRules.getNoLowReward())){
						tbGeneralRules.setNoLowPrice("0.00");
						//判断如NoLowReward值为Y时，可添加基本票价
					}else if("Y".equals(tbGeneralRules.getNoLowReward())){
						//判断如果票价低于奖励长度大于4，截取
						if("".equals(tbGeneralRules.getNoLowPrice())||null==tbGeneralRules.getNoLowPrice()){
							tbGeneralRules.setNoLowPrice("0.00");
						}else{
						tbGeneralRules.setNoLowPrice(tbGeneralRules.getNoLowPrice());
						}
					}
					//判断AddOn计奖是否选中，如为空赋值为N
					if("".equals(tbGeneralRules.getAddOnChoice())|| null == tbGeneralRules.getAddOnChoice()){
						tbGeneralRules.setAddOnChoice("N");
					}
					//判断国际计奖是否选中，如未选中赋值为N
					if("".equals(tbGeneralRules.getAddOnInternalChoice())|| null == tbGeneralRules.getAddOnInternalChoice()){
						tbGeneralRules.setAddOnInternalChoice("N");
					}
					//判断国内计奖是否选中，如未选中赋值为N
					if("".equals(tbGeneralRules.getAddOnWorldChoice())|| null == tbGeneralRules.getAddOnWorldChoice()){
						tbGeneralRules.setAddOnInternalChoice("N");
					}
					//判断AddOn是否为N，如为空，国际和国内计奖都赋值为N
					if("N".equals(tbGeneralRules.getAddOnChoice())|| null == tbGeneralRules.getAddOnChoice()){
						tbGeneralRules.setAddOnInternalChoice("N");
						tbGeneralRules.setAddOnWorldChoice("N");
					}
					if("".equals(tbGeneralRules.getSpaChoice())|| null == tbGeneralRules.getSpaChoice()){
						tbGeneralRules.setSpaChoice("N");
					}
					if("".equals(tbGeneralRules.getQChoice())|| null == tbGeneralRules.getQChoice()){
						tbGeneralRules.setQChoice("N");
					}
					tbGeneralRules.setId(null);
					tbGeneralRules.setGrId(UUID.randomUUID().toString().substring(0,31).toUpperCase());
					tbGeneralRules.setEffectDate(new Date());
					super.save(tbGeneralRules);
				}
			}
		}
	}
	
	/**
	 * 国际政策总则的增删改查 删除
	 * @param TbGeneralRules
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(GeneralRules tbGeneralRules) {
		super.delete(tbGeneralRules);
	}

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
	public void addGeneralRules(String fileName,String realPath){
		Workbook workbook = getWorkbokk(fileName,realPath);
		
		try {
			Sheet sheetAt = workbook.getSheetAt(0);
			workbook.getActiveSheetIndex();//获得当前活动页的下标
			workbook.getNumberOfSheets();	//获得素有sheet页的总数
			CellStyle createCellStyle = workbook.createCellStyle();
			Font createFont = workbook.createFont();
			createFont.setColor(HSSFColor.GOLD.index);
			createCellStyle.setFont(createFont);
			Row row= null;
			int numLength = sheetAt.getLastRowNum();
			List<GeneralRules> generalRulesList = new ArrayList<GeneralRules>();
			for(int i=0;i<=numLength;i++){
				 row = sheetAt.getRow(i);	
				 GeneralRules generalRules = new GeneralRules();
				 row.getCell(0).setCellStyle(createCellStyle);
				 //获取供应商Id
				 String supplierId = row.getCell(0).getStringCellValue();
				 generalRules.setGrId(UUID.randomUUID().toString().substring(0,31));
				 //判断供应商Id是否为空，不为添加
				 if(!"".equals(supplierId) || null != supplierId ){
					 generalRules.setSupplierId(supplierId);
				 }
				 //获取创建者Id
				 String createUserId = row.getCell(1).getStringCellValue();
				 if(!"".equals(createUserId.trim()) || null != createUserId.trim()){
					 generalRules.setCreateUserId(createUserId.trim());
				 }
				 //获取状态
				 Integer status = (int) row.getCell(2).getNumericCellValue();
				 if(!"".equals(status+"") || null != status+""){
					 if("0".equals(status+"") || "1".equals(status+"")){
						 generalRules.setGeneralStatus((status)+"");
					 }
				 }
				 //获取IT票奖励信息
				 String noItReward = row.getCell(3).getStringCellValue();
				 //判断IT票信息是否为空
				 if(!"".equals(noItReward) || null != noItReward){
					 //信息要符合N/Y的格式
					 if("N".equals(noItReward.trim().toUpperCase()) || "Y".equals(noItReward.trim().toUpperCase())){
						 generalRules.setNoItReward(noItReward.trim().toUpperCase());
					 }
				 }
				 //获取OPEN票奖励信息
				 String noOpenReward = row.getCell(4).getStringCellValue();
				 if(!"".equals(noOpenReward) || null != noOpenReward){
					 if("N".equals(noOpenReward.trim().toUpperCase()) || "Y".equals(noOpenReward.trim().toUpperCase())){
						 generalRules.setNoOpenReward(noOpenReward); 
					 }
				 }
				 //获取低于$$票价无奖励信息
				 String noLowRewaard = row.getCell(5).getStringCellValue().trim().toUpperCase();
				 //低于$$票价无奖励信息是否为空
				 if(!"".equals(noLowRewaard) || null != noLowRewaard){
					 //判断是否为固定格式
					 if("N".equals(noLowRewaard) || "Y".equals(noLowRewaard)){
						 generalRules.setNoLowReward(noLowRewaard);
					 }
				 }
				//获取航司二字码信息
				 String airCode = row.getCell(6).getStringCellValue().trim().toUpperCase();
				 //判断获取的数据是否为空
				 if(!"".equals(airCode) || null != airCode){
					 generalRules.setAirCode(airCode);
				 }
				 //获取含$$票价无奖励
				 String noInvolveReward = row.getCell(7).getStringCellValue().trim().toUpperCase();
				 if(!"".equals(noInvolveReward) || null != noInvolveReward){
					if("N".equals(noInvolveReward) || "Y".equals(noInvolveReward)){
						 generalRules.setNoInvolveReward(noInvolveReward);
						 if("Y".equals(noInvolveReward)){
							//获取含$$票价无奖励信息
							 Integer noInvolvePrice = (int) row.getCell(8).getNumericCellValue();
							 generalRules.setNoInvolvePrice(noInvolvePrice+"");
						 }
					}
				 }
				//获取去程起点信息
				 Integer goOriginChoice = (int) row.getCell(9).getNumericCellValue();
				 //判断获取的去程起点信息是否为空
				 if(null != goOriginChoice){
					 //只有满足固定格式的信息才可添加
					 if(1 == goOriginChoice || 2 == goOriginChoice || 3 == goOriginChoice){
						 generalRules.setGoOriginChoice(goOriginChoice+"");
					 }
				 }
				 //获取去程终点信息
				 Integer goDestinationChoice = (int) row.getCell(10).getNumericCellValue();
				 //判断获取的去程终点信息是否为空
				 if(null != goDestinationChoice){
					 //只有满足固定的格式信息才可添加
					 if(1 == goDestinationChoice || 2 == goDestinationChoice){
						 generalRules.setGoDestinationChoice(goDestinationChoice+"");
					 }
				 }
				//获取回程终点信息
				 Integer backDestinationChoice = (int) row.getCell(11).getNumericCellValue();
				 //判断回程终点信息是否为空
				 if(null != backDestinationChoice){
					 //只有满足固定格式的信息才可添加
					 if(1 == backDestinationChoice || 2 == backDestinationChoice || 3 == backDestinationChoice){
						 generalRules.setBackDestinationChoice(backDestinationChoice+"");
					 }
				 }
				 //获取数据选取信息
				 Integer dataChoice = (int) row.getCell(12).getNumericCellValue();
				 //判断数据选取信息是否为空
				 if(null != dataChoice){
					 //只有满足条件才可以添加
					 if(0 == dataChoice || 1 == dataChoice || 2 == dataChoice){
						 generalRules.setDataChoice(dataChoice+"");
					 }
				 }
				 //获取addOn段信息
				String addOnChoice = row.getCell(13).getStringCellValue().trim().toUpperCase();
				//判断addOn段信息是否为空
				if(!"".equals(addOnChoice) || null != addOnChoice){
					//只有满足固定格式才可以添加
					if("N".equals(addOnChoice) || "Y".equals(addOnChoice)){
						 generalRules.setAddOnChoice(addOnChoice);
						 //只有addOn段信息为Y时
						 if( "Y".equals(addOnChoice)){
							//获取国内addOn计奖信息
								String addOnIneralChoice = row.getCell(14).getStringCellValue();
								//判断获取的国内addOn计奖是否为空
								if(!"".equals(addOnIneralChoice) || null != addOnIneralChoice){
									//只有符合固定格式的信息才可以添加
									if("N".equals(addOnIneralChoice) || "Y".equals(addOnIneralChoice)){
										generalRules.setAddOnInternalChoice(addOnIneralChoice);
									}
								}
								//获取国际addOn计奖
								String addOnWorldChoice = row.getCell(15).getStringCellValue();
								//判断国际addOn计奖是否为空
							if(!"".equals(addOnWorldChoice) || null != addOnWorldChoice){
								//只有符合固定格式的信息才可以添加
								if("N".equals(addOnWorldChoice) || "Y".equals(addOnWorldChoice)){
									 generalRules.setAddOnWorldChoice(addOnWorldChoice);
								}
								
							}
						}
					}
				}
				//获取SPA计奖信息
				String spaChoice = row.getCell(16).getStringCellValue();
				if(!"".equals(spaChoice) || null != spaChoice){
					if("Y".equals(spaChoice) || "N".equals(spaChoice)){
						generalRules.setSpaChoice(spaChoice);
					}
				}
				 //获取Q值计奖信息
				String qChoice = row.getCell(17).getStringCellValue();
				//判断获取的信息是否为空
				if(!"".equals(qChoice)){
					if("Y".equals(qChoice) || "N".equals(qChoice)){
						generalRules.setQChoice(qChoice);
					}
				}
				generalRulesList.add(generalRules);
				if(generalRulesList.size()%500 == 0){
					super.saveList(generalRulesList);
					generalRulesList.clear();
				}
//				super.save(generalRules);
			}
			super.saveList(generalRulesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
///**
// * goOriginChoice;		// od 去程起点:1-出票航第一个航段的起点,
// * 							2-出票航实际承运第一个航段起点,3-第一个国际段的起点
// * goDestinationChoice;	// od 去程终点:1-出票航飞到的最远点（里程）,
// * 							2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
// * 
// * backDestinationChoice;		// od 回程终点:1-出票航最后一个航段的终点,
// * 2-出票航承运的最后一个航段终点,3-最后一个国际段的终点
// * 参数：genLists：总则；rt文本对象； writer:出票航空公司
// */
//	//返回一个对象  总则  起点终点 CompareGen
//	public Rt findListsByGz(List<GeneralRules> genLists,Rt rt,String writer){//出票方
//		for (int i = 0; i < genLists.size(); i++) {
//			List<Route> zzRoutes = rt.getRoute_list();//所有的航段，有中转、停留的不考虑
//			System.out.println(zzRoutes);
//			String goS = genLists.get(i).getGoOriginChoice();//去起点
//			String goX = genLists.get(i).getGoDestinationChoice();//去终点
//			String backZ= genLists.get(i).getBackDestinationChoice();//回终点
//			String[] split = rt.getCarrier().split("\\/");//取出市场方以及编号
//			int n = zzRoutes.size()-1;
//			for(int m = 0;m < n; m++){
//				if(m == n){
//					break;
//				}
//				if(zzRoutes.get(m+1).getTocity().equals(zzRoutes.get(m).getFromCity())){
//					zzRoutes.get(m).setTravelType("1");
//					for (int j = 0; j < zzRoutes.size(); j++) {//遍历所有航段，每一段航段与总则去匹配出一个起点与终点
//						//这里来确定始发站，去程的起点
//						//去程起点:1-出票航第一个航段的起点,2-出票航实际承运第一个航段起点,3-第一个国际段的起点
//						if("1".equals(goS)){
//							//逻辑判断确定
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									//genLists.get(i).set
//									zzRoutes.get(j).setFromCity(writer);
//								}
//							}
//						//2-出票航实际承运第一个航段起点
//						}else if("2".equals(goS)){
//							//用市场方去共享表查询实际承运的
//							
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									zzRoutes.get(j).setFromCity(writer);
//								}
//								
//							}
//							 
//						}else if("3".equals(goS)){
//							
//						}
//						//这里来确定去程终点
//						// 去程终点:1-出票航飞 到的最远点（里程）,2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
//						if("1".equals(goX)){
//							//逻辑判断确定
//						String mileOne = generalRulesDao.findMileage(zzRoutes.get(i).getFromCity()+"-"+zzRoutes.get(i).getTocity());
//						}else if("2".equals(goX)){
//						
//						}
//					}
//				}else{
//					for (int j = 0; j < zzRoutes.size(); j++) {//遍历所有航段，每一段航段与总则去匹配出一个起点与终点
//						//这里来确定始发站，去程的起点
//						//去程起点:1-出票航第一个航段的起点,2-出票航实际承运第一个航段起点,3-第一个国际段的起点
//						if("1".equals(goS)){
//							//逻辑判断确定
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									//genLists.get(i).set
//									zzRoutes.get(j).setFromCity(writer);
//								}
//							}
//						//2-出票航实际承运第一个航段起点
//						}else if("2".equals(goS)){
//							//用市场方去共享表查询实际承运的
//							
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									zzRoutes.get(j).setFromCity(writer);
//								}
//							}
//						}else if("3".equals(goS)){
//							
//						}
//						//这里来确定去程终点
//						// 去程终点:1-出票航飞 到的最远点（里程）,2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
//						if("1".equals(goX)){
//							//逻辑判断确定
//						
//						}else if("2".equals(goX)){
//						
//						}
//						//回程终点
//						//1-出票航最后一个航段的终点,
//						if("1".equals(backZ)){
//							//逻辑判断确定
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									//genLists.get(i).set
//									zzRoutes.get(j).setFromCity(writer);
//								}
//							}
//						// 2-出票航承运的最后一个航段终点
//						}else if("2".equals(backZ)){
//							//用市场方去共享表查询实际承运的
//							for (int k = 0; k < split.length; k++){
//								String marketPlace = split[k].replaceAll("\\d+","");//取出市场方，去跟出票方比较
//								if(writer.equals(marketPlace)){//如果 市场方==出票航空公司，我们把这个值赋值于起点
//									zzRoutes.get(j).setFromCity(writer);
//								}
//							}
//						//3-最后一个国际段的终点
//						}else if("3".equals(backZ)){
//							
//						}
//					}
//				}
//			}
//			
//			
//			
//		}
//		return rt;
//	}

	
	
	
//	/**
//	 * goOriginChoice;		// od 去程起点:1-出票航第一个航段的起点,
//	 * 							2-出票航实际承运第一个航段起点,3-第一个国际段的起点
//	 * goDestinationChoice;	// od 去程终点:1-出票航飞到的最远点（里程）,
//	 * 							2-跨大区(或子区或国际)段的终点（写：我们按照里程最远点当折返点）
//	 * 
//	 * backDestinationChoice;		// od 回程终点:1-出票航最后一个航段的终点,
//	 * 2-出票航承运的最后一个航段终点,3-最后一个国际段的终点
//	 * 参数：genLists：总则；rt文本对象； writer:出票航空公司
//	 */
//		//返回一个对象  总则  起点终点 CompareGen
//		public Rt findListsByGz(List<GeneralRules> genLists,Rt rt,String writer){//出票方
//			for (int i = 0; i < genLists.size(); i++) {
//				List<Route> zzRoutes = rt.getRoute_list();//所有的航段，有中转、停留的不考虑
//				System.out.println(zzRoutes);
//				String[] split = rt.getCarrier().split("\\/");//取出市场方以及编号
//				int n = zzRoutes.size()-1;
//				
//			//航程为偶数的是往返
//			if(){
//				
//			}
//				
//				for(int m = 0;m < n; m++){
//					if(m == n){
//						break;
//					}
//					if(zzRoutes.get(m+1).getTocity().equals(zzRoutes.get(m).getFromCity())){
//						for (int j = 0; j < zzRoutes.size(); j++) {//遍历所有航段，每一段航段与总则去匹配出一个起点与终点
//							
//						}
//					}else{
//						for (int j = 0; j < zzRoutes.size(); j++) {//遍历所有航段，每一段航段与总则去匹配出一个起点与终点
//							
//						}
//					}
//				}
//				
//				
//				
//			}
//			return rt;
//		}

	/**
	 * 解析Excle（支持03、07版本）
	 * @param fileName 文件名
	 * @param realPath 文件路径
	 * @return
	 */
	private Workbook getWorkbokk(String fileName,String realPath){
//		CommonsMultipartFile cf= (CommonsMultipartFile)headPic; //这个myfile是MultipartFile的
//		DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File file = new File(realPath+"/"+fileName);
		Workbook wb =null;
		InputStream inp = null;
		try {
			inp = new FileInputStream(file);
			String names=fileName.split("\\.")[1].toString();
			if(names.equals("xls")){
				wb =new HSSFWorkbook(inp);
			}else{
				wb =new XSSFWorkbook(inp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inp.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wb;
	}

	
}