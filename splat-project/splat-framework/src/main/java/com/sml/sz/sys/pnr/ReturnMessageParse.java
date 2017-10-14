package com.sml.sz.sys.pnr;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MapAccessor;

import com.sml.sz.StringUtils;
import com.sml.sz.file.FileUtil;

/**
 * PNR文本转换工具类
 * @author shenxj
 * @date 2015-4-15
 */
public class ReturnMessageParse {
	
	private static Logger logger = LoggerFactory.getLogger(ReturnMessageParse.class);
	
	/**
	 * pnr文本 解析成 RT 对象
	 * @param xml
	 * @return
	 * @date 2015-4-15 
	 * @author shenxj
	 */
	public static Rt parsePNRXML(String xml){
		Rt rt = new Rt();
		//PNRXML001 pnrXml = new PNRXML001();
		PNRXML pnrXml = new PNRXML();
		List<Passenger> passenger_list = new ArrayList<Passenger>();
		List<Route> routeList = new ArrayList<Route>();
		List<Ticket> ticket_list = new ArrayList<Ticket>();
		List<Rmk> rmk_list = new ArrayList<Rmk>();
		List<Fn> fn_list = new ArrayList<Fn>();
		List<Fc> fc_list = new ArrayList<Fc>();
		List<Fp> fp_list = new ArrayList<Fp>();
		List<Ssr> ssr_list = new ArrayList<Ssr>();
		List<Qte> qte_list=new ArrayList<Qte>();
		String carrier = "";//承运人
		String time = "";//时间
		String date = "";
		String clazz = "";//舱位
		String itinerary = "";//行程
		//String basePrice="";//基础票价
		try{
			boolean flag = pnrXml.parsePNRXML(pnrXml.createPNRXML(xml));
		//System.out.println("pnrxml解析为"+pnrXml.createPNRXML(xml).asXML());
			if(flag){
				List<?> listPNR = pnrXml.getListPNR();
				
				List basePrices = pnrXml.getBaseprice();
				List exchages=pnrXml.getExchages();
				List listName = pnrXml.getListName();
				List listLine = pnrXml.getListLine();
				List listIDCardNo= pnrXml.getListIDCardNo();
				List qtes = pnrXml.getQtes();
				List fcs = pnrXml.getFcs();
				for(int i=0;i<listPNR.size();i++){
					PNRContent p = (PNRContent)listPNR.get(i);
					rt.setPnr(p.getPnrCode());
				}
				for(int i=0;i<basePrices.size();i++){
					PNRContent p = (PNRContent)basePrices.get(0);
					rt.setBasePrice(p.getBasePrice());
				}
				for(int i=0;i<exchages.size();i++){
					PNRContent p = (PNRContent)exchages.get(0);
					rt.setExchangeRate(p.getExchangeRate());
				}
				for(int i=0;i<fcs.size();i++){
					PNRContent p = (PNRContent)fcs.get(i);
					Fc fc=new Fc();
					fc.setFcNum(p.getFcNum());
					fc.setFromCity(p.getFromCity());
					fc.setToCity(p.getToCity());
					fc.setRate(p.getRate());
					fc.setqString(p.getqString());
					fc.setIden(p.getIden());
					fc_list.add(fc);
				}
				/**
				 * Begin qte 
				 * fjw
				 */
				for(int i=0;i<qtes.size();i++){
					PNRContent p = (PNRContent)qtes.get(i);
					Qte qte=new Qte();
					qte.setPrice(p.getPrice());
					qte.setPriceUnit(p.getPriceUnit());
					qte.setState(p.getState());
					qte.setIsContainTax(p.getIsContainTax());
					qte.setFare(p.getFare());
					qte.setTax(p.getTax());
					//qte.setFc(p.getFc());
					qte.setType(p.getType());
					qte.setIdentity(p.getIdentity());
					qte.setFareUnit(p.getFareUnit());
					qte.setTaxUnit(p.getTaxUnit());
					qte.setCommission(p.getCommission());
					qte_list.add(qte);
				}
				/**
				 * end qte 
				 * fjw
				 */
				
				for(int ni=0; ni<listName.size(); ni++){
					PNRContent p = (PNRContent)listName.get(ni);
					Passenger psg = new Passenger();
					String psgname = "";
					if(p.getName().indexOf("\r ")!=-1){//姓名去换换行符
						psgname = p.getName().replaceAll("\r ", "");
					}else if(p.getName().indexOf("\r")!=-1){
						psgname = p.getName().replaceAll("\r", "");
					}else{
						psgname = p.getName();
					}
					psg.setName(psgname);
					if(qte_list.size()==1){
						psg.setPassIdentity(PNRXML.findPasIdentityByName(qte_list.get(0).getIdentity())+"");
						psg.setPassType(PNRXML.findPasTypeByName(qte_list.get(0).getIdentity())+"");
					}else{
						psg.setPassIdentity(p.getPassIdentity());
						psg.setPassType(p.getPassType());
					}
					
					passenger_list.add(psg);
				}
				//身份证号
				if(listIDCardNo!=null&&listIDCardNo.size()>0&&passenger_list!=null){
					for(int i=0;i<passenger_list.size();i++){
						if(i!=listIDCardNo.size()&&listIDCardNo.get(i)!=null){
							PNRContent from = (PNRContent)listIDCardNo.get(i);
							String getpName = from.getpName();
							int pNum = Integer.parseInt(getpName)-1;
							passenger_list.get(pNum).setPassportCou(from.getPassportCou());
							passenger_list.get(pNum).setBirCountry(from.getBirCountry());
							passenger_list.get(pNum).setBirth(from.getBirth());
							passenger_list.get(pNum).setSex(from.getSex());
							passenger_list.get(pNum).setValidData(from.getValidData());
							passenger_list.get(pNum).setpName(from.getpName());
							((Passenger)passenger_list.get(pNum)).setIdentity(from.getIDCardNo());
						}
					}
				}

				for(int li=0; li<listLine.size();li++ ){
					PNRContent p = (PNRContent)listLine.get(li);
					
					//如果行程的信息是中断的航程 循环下一个
					if((p.getLineCode()==null||"".equals(p.getLineCode()))&&
						(p.getBerth()==null||"".equals(p.getBerth()))&&
						(p.getBeginTime()==null||"".equals(p.getBeginTime()))&&
						(p.getArriveTime()==null||"".equals(p.getArriveTime())))
					{
						continue;
					}
						
						Route r = new Route();
						String r_flight = p.getLineCode();
						if(r_flight!=null && r_flight.length()>0){
							r_flight = r_flight.replaceAll("\\*", "");
						}
			 			r.setFlight(r_flight);
						r.setClazz(p.getBerth());
						String bCity = p.getBeginCity();
						String aCity = p.getArriveCity();
						if(StringUtils.isNotBlank(bCity) && bCity.length()>3){
							bCity = bCity.substring(0,3);
						}
						if(StringUtils.isNotBlank(aCity) && aCity.length()>3){
							aCity = aCity.substring(0,3);
						}
						r.setFromCity(bCity);
						r.setTocity(aCity);
						//r.setFromAirport(routes.attributeValue("fromAirport"));
						//r.setToAirport(routes.attributeValue("toAirport"));
						r.setDate(p.getBeginTime()!=null&&!"".equals(p.getBeginTime().trim())?p.getBeginTime().substring(0,10):"");
						//r.setState(routes.attributeValue("state"));
						r.setFromTime(p.getBeginTime()!=null&&!"".equals(p.getBeginTime().trim())?p.getBeginTime().substring(11).replaceAll(":", ""):"");
						r.setBeginTimeMark(p.getBeginTimeMark());
						String arriveTime = p.getArriveTime();
						/**
						 * 冯俊伟添加
						 * 2016年3月22日16:31:39
						 * 主要是方便前台获取到达时间的值 出现+、-的情况
						 */
						if(p.getArriveTime()!=null&&!"".equals(p.getArriveTime().trim()))
							r.setArriveDate(arriveTime);
						/**
						 * 结束
						 * 冯俊伟
						 */
						r.setToTime(p.getArriveTime()!=null&&!"".equals(p.getArriveTime().trim())?p.getArriveTime().substring(11).replaceAll(":", ""):"");
						routeList.add(r);
					
				}
				if(routeList!=null){
					String sysDate[] = getDateTime("yyyy-MM-dd").split("-");
					for(int i=0;i<routeList.size();i++){
						Route r = routeList.get(i);
                        //修改carrier 如果为空替换为 _  开始
                        String flight = r.getFlight() ;
                        if(flight == null || "".equals(flight)) {
                            flight = "_" ;
                        }
                        //修改carrier 如果为空替换为 _  结束
						carrier+=flight;
						clazz+=r.getClazz();
						String ft = "";
						String tt = "";
						if(r.getFromTime()!=null&&!"".equals(r.getFromTime()))
							ft=r.getFromTime().replace(":", "");
						else
							ft="-";
						if(r.getToTime()!=null&&!"".equals(r.getToTime()))
							tt=r.getToTime().replace(":", "");
						else
							tt="-";
						if(ft.equals("-")&&tt.equals("-"))
							//time+=(ft+" "+tt);
                              //modify by sen 修改时间为空时 转换为 0000-0000 开始
                            time+="0000-0000";
                            //modify by sen 修改时间为空时 转换为 0000-0000 结束
						else
							time+=(ft+"-"+tt);
						if(i!=routeList.size()-1){
							carrier+="/";
							clazz+="/";
							time+="/";
						}
						itinerary+=r.getFromCity();
						if(routeList.size()-1!=i){
							Route r_tmp = routeList.get(i+1);
							if(r_tmp.getFromCity().equalsIgnoreCase(r.getTocity())){
								itinerary+="-";
							}else{
								itinerary+="-"+r.getTocity()+"/ /";
							}
						}else{
							itinerary+=("-"+r.getTocity());
						}
						
						if(r.getDate()!=null&&!"".equalsIgnoreCase(r.getDate().trim())){
							String date_tmp[] = r.getDate().split("-");
							int mm = Integer.valueOf(date_tmp[1]);//行程月
							int dd = Integer.valueOf(date_tmp[2]);//行程日
							int sys_mm = Integer.valueOf(sysDate[1]);//系统月
							int sys_dd = Integer.valueOf(sysDate[2]);//系统日
							//2010-08-15    2011-05-09
							if(mm>=sys_mm){
								if(dd>=sys_dd||(dd<sys_dd&&mm>sys_mm)){
									String mm_tmp = "";
									String dd_tmp = "";
									if(mm<10){
										mm_tmp = "0"+mm;
									}else{
										mm_tmp = String.valueOf(mm);
									}
									if(dd<10){
										dd_tmp = "0"+dd;
									}else{
										dd_tmp = String.valueOf(dd);
									}
									date+=(sysDate[0]+"-"+mm_tmp+"-"+dd_tmp);
								}else{
									String mm_tmp = "";
									String dd_tmp = "";
									if(mm<10){
										mm_tmp = "0"+mm;
									}else{
										mm_tmp = String.valueOf(mm);
									}
									if(dd<10){
										dd_tmp = "0"+dd;
									}else{
										dd_tmp = String.valueOf(dd);
									}
									date+=((Integer.valueOf(sysDate[0])+1)+"-"+mm_tmp+"-"+dd_tmp);
									//date+=((Integer.valueOf(sysDate[0])+1)+String.valueOf(mm)+String.valueOf(dd));
								}
							}else{
								String mm_tmp = "";
								String dd_tmp = "";
								if(mm<10){
									mm_tmp = "0"+mm;
								}else{
									mm_tmp = String.valueOf(mm);
								}
								if(dd<10){
									dd_tmp = "0"+dd;
								}else{
									dd_tmp = String.valueOf(dd);
								}
								date+=((Integer.valueOf(sysDate[0])+1)+"-"+mm_tmp+"-"+dd_tmp);
							}
						}else{
							//date+="--";
                            //modify by sen 修改时间为空时 转换为 0000-00-00 开始
                            date+="0000-00-00";
                            //modify by sen 修改时间为空时 转换为 0000-00-00 结束
						}
						if(routeList.size()-1!=i){
							date+="/";
						}
						
					}
				}
				
				rt.setCarrier(carrier);
				rt.setTime(time);
				rt.setClazz(clazz);
				rt.setItinerary(itinerary);
				rt.setDate(date);
				rt.setTicket_list(ticket_list);
				rt.setRmk_list(rmk_list);
				rt.setFn_list(fn_list);
				rt.setFp_list(fp_list);
				rt.setSsr_list(ssr_list);
				rt.setPassenger_list(passenger_list);
				rt.setFc(fc_list);
				rt.setQte(qte_list);
				rt.setRoute_list(routeList);
				return rt;
			}else{
				rt.setThrowBs("YES");
				return rt;
			}
		}catch(Exception e){
			
			logger.error("文本解析失败!!", e);
		}
		return null;
		
	}
	
	private static String getDateTime(String format) {
		String result = null;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat(format);
		result = f.format(c.getTime());
		return result;
	}
	public static void main(String[] args) throws Exception {
		//String filesNameS="F:\\pnr\\宝盛pnr真实环境";
		/*	String filesNameS="F:\\pnr\\谢新颖";
	    List<File> folders = FileUtil.getFolders(filesNameS);
		int k=0;
		int m=0;
		int n=0;
		List<String> liStrings=new ArrayList<String>();
		List<String> listThrows=new ArrayList<String>();
		for (int i = 0; i < folders.size(); i++) {//文件夹
			String folderName = folders.get(i).getAbsolutePath();
			List<File> files = FileUtil.getFiles(folderName);
			for (int j = 0; j < files.size(); j++) {
				m++;
				String name = files.get(j).getAbsolutePath();
				String readFile = FileUtil.readFile(name,"gbk");
				Rt rt = ReturnMessageParse.parsePNRXML(readFile);
				if(rt==null){
					k++;
					liStrings.add(name);	
				}else if("YES".equals(rt.getThrowBs())){
					n++;
					listThrows.add(name);
				}
			}
		}
		int suc=m-k;
		String nu=((double)suc/(double)m)*100+"%";
		System.out.println("测试文本总数为："+m+";解析成功的数为："+suc+";由于*无适用的运价*抛给宝盛的数为"+n+";成功率为：(包含抛给宝盛的)"+nu);
		for (int i = 0; i < liStrings.size(); i++) {
		System.out.println("解析失败的文件名为："+liStrings.get(i));
	}
		for (int i = 0; i < listThrows.size(); i++) {
			System.out.println("由于无适用的运价而抛给宝盛的文件名为："+listThrows.get(i));
		}*/
//end
		
		
		

/*		int k=0;
		int m=0;
		List<String> liStrings=new ArrayList<String>();
		String fileName="F:\\qq文件\\274184916\\FileRecv\\4.16.txt";
		String readFile = FileUtil.readFile(fileName);
		String[] split = readFile.split("^^^");
		for (int i = 0; i < split.length; i++) {
			String string = split[i];
			Rt rt = ReturnMessageParse.parsePNRXML(string);
			m++;
			if(rt==null){
				k++;
				liStrings.add(rt.getPnr());
			}
		}
		int suc=m-k;
		String nu=((double)suc/(double)m)*100+"%";
		System.out.println("测试文本数为："+m+";解析成功的数为："+suc+";成功率为："+nu);
		for (int i = 0; i < liStrings.size(); i++) {
		System.out.println("解析失败的pnr编号为："+liStrings.get(i));
	}
		*/
		
		
		
		
		String fileName="F:\\pnr.txt";
		String readFile = FileUtil.readFile(fileName,"gbk");
		Rt rt = ReturnMessageParse.parsePNRXML(readFile);
		System.out.println("解析后的Rt内容为："+rt);
		System.out.println("OD的个数："+rt.getRoute_list().size()+";旅客的个数："+rt.getPassenger_list().size()+";Fc的个数为："+rt.getFc().size()+";人的个数为："+rt.getPassenger_list().size()+";Qte的个数为："+rt.getQte().size());
		System.out.println("人："+rt.getPassenger_list());
		System.out.println("Fc："+rt.getFc());
		System.out.println("Qte："+rt.getQte());
		System.out.println("od"+rt.getRoute_list());
		System.out.println("basePrice位："+rt.getBasePrice());

	}
}
