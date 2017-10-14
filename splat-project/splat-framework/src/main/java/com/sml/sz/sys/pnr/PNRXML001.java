package com.sml.sz.sys.pnr;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
   
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PNRXML001 {

	private static Logger logger = LoggerFactory.getLogger(PNRXML001.class);

	List listPNR ;
	List listName ;
	List listLine ;
	List listMobile ;
	List listIDCardNo;
	List qtes;
	List fcs;

	public Document createPNRXML(String source) {


		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");

		String pnr = "";//旅客后面的pnr数值,由于有时候pnr文本的第一行的rt后面没有pnr,因此要以旅客pnr的为准.
//
		/*王兰虎添加开始　20120328 17:12 针对 1.前面没有空格的情况，加上空格  航信tmd,有的pnr后面是\r,有的后面是\r\n*/
		//String tmp = source.substring(source.indexOf("\r1."));
		int indexOf = source.indexOf("\n1.");
		if(source.indexOf("\n1.") != -1){
			source = source.replaceFirst("\n1.", "\n 1.");
		}else if(source.indexOf("\r1.") != -1){
			source = source.replaceFirst("\r1.", "\r 1.");
		}

		String pnr1 = "";//这是rt后面的pnr数值

		if(!source.startsWith("1.") && !source.startsWith(" 1.")){//563
			//如果旅客开始行的上一行是rt的话(1.开头表示旅客行,如果不是旅客行的话，那么就认为是rt开头的)
			try {
				pnr1 = source.substring(2,source.indexOf("\n")).replaceAll(" ", "").toUpperCase().substring(0, 6);//把rt XXXXXX的空格去掉
			} catch (Exception e) {
				logger.error("解析pnr出错："+ 
						source.substring(2,source.indexOf("\n")).
						replaceAll(" ", "").toUpperCase() + "不足6位",e);
			}

			//有时侯第一行是rt \n\r
			if (pnr1.length()<=5) pnr1="";
		}else{
			//如果真的不是rt开头的，那么就加上rt
			if(source.startsWith("1.")){
				source = "rt\n " + source;
			}else if(source.startsWith(" 1.")){
				source = "rt\n" + source;
			}

		}
		//上面一句话运行完后,可能出现pnr1为空的情况,因为 可能第一行只有rt,后面没有跟着XXXXXX.

		if((pnr1.length()>0) && source.length()>7 && source.indexOf("\n 1.")>-1 )
		{
			//source = source.replaceAll(pnr, " " + pnr);//PNR前面加一个空格
			//if(source.substring(2, 3).equals(" "))  source = source.replaceFirst(" ", "");//把rt XXXXXX的空格去掉
			source = source.substring(0, source.indexOf("\n 1.")) + source.substring(source.indexOf("\n 1.")).replaceAll(pnr1, " " + pnr1);
		}else if((pnr1.length() == 5 || pnr1.length() == 6) && source.length()>7 && source.indexOf("\r 1.")>-1){
			source = source.substring(0, source.indexOf("\r 1.")) + source.substring(source.indexOf("\r 1.")).replaceAll(pnr1, " " + pnr1);
		}
		/*王兰虎添加结束*/

		boolean groupFlag=false;//团体 true   散客 false
		//if(source.indexOf(" 0.")<source.indexOf(" 1.") && source.indexOf(" 0.")>-1){
		/**
		 * 现在没法确定团体票  影响程序 begin
		 */

		/*	if(source.indexOf(" 0.")>-1){
			System.out.println(source.indexOf(" 0."));

			groupFlag=true;
		}*/

		/**
		 * 现在没法确定团体票  影响程序 end
		 */


		if(groupFlag){
			source = source.substring(source.indexOf(" 0."));

			String currentRow=source.substring(0,source.indexOf("\n"));
			currentRow=currentRow.trim();
			pnr = currentRow.substring(currentRow.lastIndexOf(" "));
			root.addElement("pnrCode").addAttribute("pnr", pnr);
		}
		//有旅客信息
		if (source.indexOf(" 1.") > -1) {
			//从"1."开始一定是旅客行，旅客行　*.后没有空格
			//从旅客行开始取子串
			source = source.substring(source.indexOf(" 1."));
			if (!"".equals(pnr) && source.contains(pnr)) {
				source = source.substring(0, source.indexOf(pnr)).trim();
			}
			//根据换行符拆分成数组
			String[] rows=source.split("\n");
			//把不合适的换行去掉
			//----------------------------
			if(rows!=null){
				rows=cutWrongNewline(rows);
			}
			int currentRow=0;
			for(int i=0;i<rows.length;i++){				
				String rowData=rows[i];//当前解析行

				String nextRowData="";
				if(i<rows.length-1){
					nextRowData=rows[i+1];
					if("".equals(nextRowData.trim()) && (i+2)<rows.length){
						nextRowData=rows[i+2];
					}
				}

				boolean lastPassengerRow=false;//是否是旅客信息的最后一行
				if(nextRowData.indexOf(".")>0 && !"".equals(nextRowData.trim())){
					if(nextRowData.substring(nextRowData.indexOf(".")+1).indexOf(" ")==0){
						//如果下一行是航程、航班信息
						lastPassengerRow=true;
					}
				}
				//旅客信息　最后是否含PNR码 (散客包含　团体旅客信息最后没有PNR码（在0.行中包含）)
				boolean includePNR=false;
				//如果是散客并且是旅客信息的最后一行
				if(groupFlag==false && lastPassengerRow==true){
					includePNR=true;
				}
				//如果第一个"."之后是空格，说明是航程，航班信息
				if(rowData.indexOf(".")>0 && rowData.substring(rowData.indexOf(".")+1).indexOf(" ")==0){
					break;
				}else{			
					if(rowData!=null){
						if(!"".equals(rowData.trim())){
							///*王兰虎添加开始　20120330 17:12 下面这段是为了解析旅客后面的pnr可能折行的情况
							int in = rowData.indexOf("\n");
							rowData = rowData.replaceAll("\n", "").replaceAll("\r", "").replaceAll("  ", "");

							//把当前行的最后一个空格去掉
							if(rowData.substring(rowData.length()-1).equals(" ")) rowData = rowData.substring(0,rowData.length()-1);
							if(rowData.substring(rowData.length()-6).indexOf(" ") != -1){
								//如果旅客后面的pnr被中间还有空格
								if(!(rowData.substring(rowData.length()-3).endsWith(" MR") 
										|| rowData.substring(rowData.length()-3).endsWith(" MS")
										|| rowData.substring(rowData.length()-5).endsWith(" MISS")
										|| rowData.substring(rowData.length()-5).endsWith(" MSTR"))
										){
									//下面的样例不要去掉最后面的空格
									// 1.DIOUF/MATAR MR 2.GAYE/CHEIKH BABOU MR 3.OUALY/BABACAR MR
									rowData = rowData.substring(0,rowData.length()-6) + rowData.substring(rowData.length()-6).replaceAll(" ", "");
								}
							}else{
								if (pnr1.length()== 0 ) 
									pnr1 = rowData.substring(rowData.replaceAll("\r", "").length()-6);
							}

							if(pnr1.length() > 0){
								if(rowData.substring(rowData.replaceAll("\r", "").length()-8).replaceAll(" ", "").equalsIgnoreCase(pnr1))
									rowData = rowData.substring(0, rowData.replaceAll("\r", "").length()-8) + " " + pnr1;

								rowData = rowData.replaceAll(pnr1.replaceAll("\r", ""), " " + pnr1);
							}else if(pnr1.length() == 0){

							}


							parsePassengerRow(root,rowData,includePNR);
						}
					}
					currentRow++;
				}				
			}
			int t=0;
			for(int i=currentRow;i<rows.length;i++){
				
				String rowData=rows[i];
				String substringTotal = selTotalPrice(rows);
				List<String> idenFc = getIdenFc(rows);
				if(substringTotal==null){
					substringTotal="01";
				}
				logger.info("-------------xxx-----------------="+rowData);
				boolean contains = rowData.contains("xsfsq");
				if(contains){

				}
				//如果第一个"."之后是空格，说明是航程，航班信息
				List selectQteRows = selectQteRows(rows);
				selectQteRows.add(rows.length);
				if(rowData!=null){
					/**
					 * version1:if(rowData.indexOf(".")>0 && rowData.length()>2)修改：
					 * 原因：对于这样的情况
					 * 	28MAR16BJS AA X/CHI AA PIT M/IT /-WAS AA X/DFW AA BJS M/IT E                    
						ND ROE6.418450  
					 * 第一行不满足，导致无法解析fc
					 * 
					 * version2：
					 * 冯俊伟
					 * 2016年4月8日17:15:09
					 * 内容：
					 * 
					 */
					if(rowData.indexOf(".")>0 && rowData.length()>2){
						if(rowData.substring(rowData.indexOf(".")+1).indexOf(" ")==0){
							//3.  CA6221 Y   TH30JUN  PEKFRA HK2   1030 1440          E T31
							parseAirRow(root,rowData);					
						}else{					
							//留接口  查询国内机票 身份证时候用
							/*	if(rowData.indexOf(" NI")!=-1&&rowData.indexOf("/P")!=-1&&rowData.split(" NI")[1].split("/P")[0].length()>0){
								String idcarno = rowData.split(" NI")[1].split("/P")[0].trim();
								String idcarindex =  rowData.split(" NI")[1].split("/P")[1].trim();
								if(idcarindex.indexOf(" ")!=-1){
									idcarindex = idcarindex.split(" ")[0].trim();
								}
								root.addElement("IDCardNo").addAttribute("name", idcarno)
								.addAttribute("number", idcarindex);
							}*/
							/**
							 * 解析婴儿的相关东西
							 */
							if(rowData.contains("SSR INFT")){
								String replaceAll = rowData.replaceAll("\\s+", " ");
								String[] split = replaceAll.trim().split(" ");
								root.addElement("passenger")
								.addAttribute("name",split[7])
								.addAttribute("passType","4")
								.addAttribute("passIdentity","9")
								;
							}



							/**
							 * 冯俊伟 添加
							 * 2016年3月23日10:23:48
							 * 作用：ni一般是指国内定的，后面跟身份证。国外的是护照p
							 * 以下代码是提取护照的信息
							 * 
							 */
							if(rowData.indexOf(" P/")!=-1){
								int index = rowData.indexOf(" P/");
								String substring = rowData.substring(index+3, rowData.length()-3);
								String[] split = substring.split("\\/");
								//签发国+证件+     国籍+出生日期+性别+证件有效期  
								///CN/G47740130/CN/27MAY75/F/12JAN21 
								root.addElement("IDCardNo")
								.addAttribute("passportCou",split[0])
								.addAttribute("number",split[1])
								.addAttribute("birCountry", split[2])
								.addAttribute("birth", getBir(split[3]))
								.addAttribute("sex", split[4])
								.addAttribute("validData", getVal(split[5]))
								.addAttribute("pName", getNumberFrSt(split[split.length-1]))
								;
							}

							//解析手机号
							if(rowData.indexOf(" MP ")!=-1&&rowData.split(" MP ")[1].length()>0){
								root.addElement("mobile").addAttribute("name", rowData.split(" MP ")[1].substring(0,11).trim());
							}
							
						}


					}
					/**
					 * 
					 * 冯俊伟
					 * 2016年3月28日18:10:32
					 * 说明：因为格式的不固定，导致用正则一次性去判断很难实现，几乎是不可能完成的。
					 * 所以，只能case-by-case。思路如下：
					 * 					  |----fc的个数为m|
					 * |---x/(include)----|				|----做一次排序
					 * |				  |----OD的个数为n|
					 * |
					 * |
					 * |
					 * |
					 * |
					 * |---x/(not include)idenFc
					 */
					Element rootElement = document.getRootElement();
					Element element2 = rootElement.element("line");
					Element element3 = rootElement.element("qte");
					StringBuffer stringBuffer=new StringBuffer(rowData.replaceAll("-", "").trim());
					String attributeValue = element2.attributeValue("beginTimeMark");
					String identityFc = element2.attributeValue("identity");
					if(rowData.startsWith(attributeValue)||rowData.startsWith(" "+attributeValue)){
						
						String rowNext1=rows[i+1];
						if(rowNext1==""||rowNext1.length()==0){
							rowNext1=rows[i+2];
						}
						if(rowNext1.contains("END")||rowNext1.contains(".")||numbenInString(rowNext1)){
							stringBuffer.append(rowNext1.trim());
						}
						String stringFc1 = stringBuffer.toString();
						String stringFc=stringFc1.replaceAll("\\s+", " ");
						int middleNumbers = stringNumbers(stringFc);
						int indexRoe = stringFc.indexOf("ROE");
						String substring = stringFc.substring(stringFc.length()-15,stringFc.length()-1);//没有下一行，4段
						String fcLast = null;
						if(substring.contains("ROE")){
							fcLast = stringFc.substring(7, stringFc.length()-1);
						}else{
							fcLast = stringFc.substring(7, indexRoe+13);
						}
						//Q值得逻辑判断---begin
						List<String> numberQ = numberQ(fcLast);
						boolean exictsQ = exictsQ(fcLast);
						boolean exictsQSpace = exictsQSpace(fcLast);
						if(exictsQ){

							fcLast= newStringNotQ(fcLast);//得到新的值
						}
						//Q值得逻辑判断---end

						String[] splitSpace = fcLast.split(" ");
						List lists=new ArrayList();//[1090.60, 475.19, 1565.79, 6.418450]
						if(fcLast.contains("/IT")){
							String ss[]=fcLast.split("/IT");
							for (int p = 0; p < ss.length; p++) {
								if(ss[p].matches("\\d+\\.\\d+$|-\\d+\\.\\d+$")) //-?\\d+.?\\d*
									lists.add(ss[p]);
							}
						}else {
							String ss[]=fcLast.split("[^-?\\d+.?\\d*]");
							for (int p = 0; p < ss.length; p++) {
								if(ss[p].matches("\\d+\\.\\d+$|-\\d+\\.\\d+$")) //-?\\d+.?\\d*
									lists.add(ss[p]);
							}
						}
						//[SHA, CA, X/BJS, CA, X/FRA, LH, MUC, M833.53NUC833.53END, 
						// 取出小数 含有nuc  roe  nuc来确定几段fc

						int size = lists.size();
						//当为IT票的时候，通过it的个数去确定有几个fc
						int numberFcForIt = numberFcForIt(fcLast);
						if(numberFcForIt>0){
							size=numberFcForIt+2;
						}
						if(exictsQSpace){
							size=size-numberQ(fcLast).size();
						}
						if(stringFc.contains("X/")){
							//根据还有几个FC去判断 解析
							switch (size) {
							case 3:
								if(exictsQ){// have value of Q
									int sizeQ = numberQ.size();
									if(middleNumbers==2&&sizeQ==1){//2个中转  1个q
										//[HKG, AC, X/YVR, AC, X/YTO, AC, YYT1462.65NUC1468.44ENDROE7.76669]
										createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[6].substring(0, 3),(String) lists.get(0),numberQ.get(0),idenFc.get(t));
									}
								}else if(!exictsQ){//not have value of Q
									//[HAK, MU, X/BJS, AF, X/PAR, AF, X/SAO, AF, VIX, M5982.31NUC5982.31END, ROE6.514200, X]
									if(middleNumbers==3&&lists.get(0).equals(lists.get(1))){
										createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[8],(String) lists.get(0),idenFc.get(t));

										//[BJS, HU, X/BRU, AZ, ROM324.06NUC324.06END, ROE6.418450XT]
									}else if(lists.get(0).equals(lists.get(1))){//说明只含有1个Fc
										if(splitSpace.length==6){
											
											createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[4].substring(0, 3),(String) lists.get(0),idenFc.get(t));
										//[SHA, CA, X/PAR, FI, REK, M1005.49NUC1005.49END, ROE6.514200XT]
										}else if(splitSpace.length==7){
											
											createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[4],(String) lists.get(0),idenFc.get(t));
											//[SHA, HU, X/BJS, HU, X/PRG, OK, BTS, M383.77NUC383.77END, ROE6.514200, X]
										}else if(splitSpace.length==10){
											
											createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[6],(String) lists.get(0),idenFc.get(t));
										}
										else {
											
											createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[6],(String) lists.get(0),idenFc.get(t));
										}	
										//	[ADL, CX, X/HKG, CX, BJS, 423.27NUC429.07END, ROE1.37735]
									}else if(!lists.get(0).equals(lists.get(1))){
										createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[4],(String) lists.get(0),numberQ.get(0),idenFc.get(t));

									}
								}
								break;
							case 4 ://说明只含有2个Fc
								if(exictsQ||exictsQSpace){//This shows that have value of Q.
									if(exictsQSpace){
										List<String> removeQlistFromLists = removeQlistFromLists(lists,numberQ);
										for(int k=0;k<removeQlistFromLists.size()-2;k++){
											if(k==0){
												createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[4],(String) lists.get(0),numberQ.get(k),idenFc.get(t));

											}else if(k==1){
												createFcDocument(root, (String)lists.get(3),splitSpace[4],splitSpace[0],(String) lists.get(1),numberQ.get(k),idenFc.get(t));

											}

										}
									}else if(numberFcForIt(fcLast)>0){//既有q也是it的情况
										for(int k=0;k<size-2;k++){
											if(k==0){//[BJS, KA, X/HKG, CX, YVR, M/IT, CX, X/HKG, KA, BJS, M/IT, END, ROE6.51420]
												createFcDocument(root, getItRoe(fcLast),splitSpace[0],splitSpace[4],"IT",numberQ.get(k),idenFc.get(t));

											}else if(k==1){
												createFcDocument(root, getItRoe(fcLast),splitSpace[4],splitSpace[0],"IT",numberQ.get(k),idenFc.get(t));

											}

										}
									}
									
									
									//this result is further confirmed --->have q
									//[MEL, CX, X/HKG, KA, CSX, 341.95CX, X/HKG, CX, MEL, 303.84NUC657.39END, ROE1.377358, X]
									else if(doublesAdd(lists.get(0),lists.get(1))!=objectToDouble(lists,2)&&middleNumbers==2){
										List<String> numberQ2 = numberQ;
										String lastTotoalQ = doublesAdd(numberQ2);
										for(int k=0;k<lists.size()-2;k++){
											if(k==0){
												createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[4],(String) lists.get(0),lastTotoalQ,idenFc.get(t));

											}else if(k==1){
												createFcDocument(root, (String)lists.get(3),splitSpace[4],splitSpace[0],(String) lists.get(1),idenFc.get(t));

											}

										}

									}

								}else if(numberFcForIt(fcLast)>0){//it
									String iTRate = iTRate(fcLast);
									//[SHA, VS, LON, M/IT, END, ROE6.418450EN]
									for(int p=0;p<numberFcForIt(fcLast);p++){
										if(p==0){
											createFcDocument(root, iTRate,splitSpace[0],splitSpace[2],idenFc.get(t));

										}else if(p==1){
											createFcDocument(root,iTRate,splitSpace[2],splitSpace[0],idenFc.get(t));

										}
									}

								}
								else{//This shows that not have value of Q.
									if(doublesAdd(lists.get(0),lists.get(1))==objectToDouble(lists,2)&&middleNumbers==2){//验证，FC1+Fc2=NUC 中转数有2个
										for(int k=0;k<lists.size()-2;k++){
											//[BJS, DL, X/SEA, DL, NYC, M1090.60DL, X/DTT, DL, BJS, M475.19NU, C1565.79END, ROE6.418450]
											//[SHA, CA, X/BJS579.57UL, X/CMB, UL, DOH1154.48NUC1734.05ENDROE6.41845]

											//[CTU, MU, X/SHA, MU, CHI62.32/NYC, MU, X/SHA, MU, CTU319.39NUC381.71END, ROE6.41845]
											boolean findFirstX = findFirstX(fcLast);
											if(findFirstX){
												
												if(k==0){
													createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(2, 5),(String) lists.get(0),idenFc.get(t));

												}else if(k==1){
													String toCity=splitSpace[0];
													if(toCity!=splitSpace[5]){//[BJS, CA, X/FRA960.97LO, X/WAW, LO, SZZ, M1852.70NUC2813.67END, ROE6.51420]

														toCity=splitSpace[5];
													}
													createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(2, 5),toCity,(String) lists.get(1),idenFc.get(t));

												}
											}else{
												String toCi=splitSpace[4].substring(0, 3);
												if(toCi.contains("/")){
													toCi=splitSpace[4].substring(2, 5);
												}
												
												if(k==0){
													
													createFcDocument(root, (String)lists.get(3),splitSpace[0],toCi,(String) lists.get(0),idenFc.get(t));

												}else{
													String toCityLast=splitSpace[0];
													boolean hasDigit = hasDigit(splitSpace[5].substring(0, 3));
													//[SHA, SK, X/CPH, SK, X/STO1955.72SK, MMA259.46NUC2215.18ENDROE6.51420]
													if(splitSpace[5].substring(0, 3)!=splitSpace[0]&&!splitSpace[5].substring(0, 3).contains("/")&&!hasDigit){
														toCityLast=splitSpace[5].substring(0, 3);
														//[SHA, AY, X/HEL, AY, RIX374.56AY, X/HEL, AY, BJS328.51NUC703.07END, ROE6.51420]
													}else if(splitSpace[7].length()>=3&&splitSpace[7].substring(0, 3)!=toCityLast){
														toCityLast=splitSpace[7].substring(0, 3);
													}
													
													//[BJS, SQ, X/SIN, SQ, SUB, M1571.18SQ, X/SIN, SQ, BJS, M1571.18NUC3142.36END, ROE6.51420]
													createFcDocument(root, (String)lists.get(3),toCi,toCityLast,(String) lists.get(1),idenFc.get(t));

												}
												
												
											}

										}
									}else if(doublesAdd(lists.get(0),lists.get(1))==objectToDouble(lists,2)&&middleNumbers==1){//2个fc 1个中转
										boolean findFirstX = findFirstXFc(fcLast);
										boolean findNexttXFc = findNexttXFc(fcLast);
										//前面不含有
										if(!findFirstX){
											//[BJS, AF, X/PAR, AF, ATL, M4058.61DL, SHA2253.50NUC6312.11END, ROE6.41845]3.30-test001
											if(!findNexttXFc){
												for(int k=0;k<lists.size()-2;k++){
													String firstToCity=splitSpace[4];
													if(firstToCity.length()!=3){//[SHA, BA, X/LON, BA, BER1673.26HU, BJS330.04NUC2003.30END, ROE6.51420]
														firstToCity=splitSpace[4].substring(0, 3);
													}
													if(k==0){
														
														createFcDocument(root, (String)lists.get(3),splitSpace[0],firstToCity,(String) lists.get(k),idenFc.get(t));

													}else{
														String lastToCity=splitSpace[6].substring(0, 3);
														if(splitSpace[6].contains("ROE")){//[SHA, BA, X/LON, BA, BER1673.26HU, BJS330.04NUC2003.30END, ROE6.51420]
															lastToCity=splitSpace[5].substring(0, 3);
														}
														createFcDocument(root, (String)lists.get(3),firstToCity,lastToCity,(String) lists.get(k),idenFc.get(t));

													}

												}//[BJS, KE, X/SEL411.31KE, YVR, AC, YTO, M2222.81NUC2634.12ENDROE6.41845]
												
											}else {
												for(int k=0;k<lists.size()-2;k++){
													if(k==0){
														createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(2, 5),(String) lists.get(k),idenFc.get(t));

													}else{
													//[BJS, TK, X/IST3604.43TK, KHI1162.00NUC4766.43END, ROE6.51420]
														String toCity=splitSpace[0];
														if(toCity.equals(splitSpace[3].substring(0, 3))){
															toCity=splitSpace[3].substring(0, 3);
														}
														createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(2, 5),toCity,(String) lists.get(k),idenFc.get(t));

													}

												}
											}
											//[CTU, CA, KTM303.81CA, X/CTU, CA, BJS334.97NUC638.78END, ROE6.418450, X]
											//[BJS, TG, BKK, TG, HKT323.28TG, X/BKK, TG, BJS368.46NUC691.74END, ROE6.41845]
											//[SHA, SK, CPH364.57/REK, SK, X/CPH, SK, SHA391.83NUC756.40END, ROE6.41845]
											//[SHA, MU, PUS169.62MU, X/SHA, MU, BJS167.32NUC336.94END, ROE6.514200, E]
										}else{
											if(findFirstX){
												for(int k=0;k<lists.size()-2;k++){
													if(splitSpace.length==8){
														if(k==0){
															createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));

														}else{
															if(splitSpace[6].contains("ROE")){
																createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[5].substring(0, 3),(String) lists.get(1),idenFc.get(t));

															}else{
															createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[6].substring(0, 3),(String) lists.get(1),idenFc.get(t));
															}
														}
													}else{

														if(k==0){
															createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));

														}else{
															createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[5].substring(0, 3),(String) lists.get(1),idenFc.get(t));

														}
													}

												}
											}
										}

									}else if(doublesAdd(lists.get(0),lists.get(1))==objectToDouble(lists,2)&&middleNumbers==3){//2个fc 3个中转
										//[CTU, CA, X/BJS, CA, X/FRA, M944.09LH, X/SAO, LH, MVD, M4556.21NUC5500.30END, ROE6.51420] 11 F:\\pnr\\4.6\\2.txt
										for(int k=0;k<lists.size()-2;k++){
											if(splitSpace.length==11){
												if(k==0){
													createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[4].substring(2, 5),(String) lists.get(k),idenFc.get(t));

												}else{
													createFcDocument(root, (String)lists.get(3),splitSpace[4].substring(2, 5),splitSpace[8],(String) lists.get(k),idenFc.get(t));

												}
												//[TNA, CZ, X/CAN, CZ, X/AMS, CZ, LIM4244.57CZ, X/AMS, CZ, BJS4213.87NUC8458.44END, ROE6.514200, X] 12 F:\\pnr\\4.6\\3.txt

											}else if(splitSpace.length==12){
												if(k==0){
													createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[4].substring(2, 5),(String) lists.get(k),idenFc.get(t));

												}else{
													createFcDocument(root, (String)lists.get(3),splitSpace[4].substring(2, 5),splitSpace[9].substring(0, 3),(String) lists.get(k),idenFc.get(t));

												}
											}

										}

									}
								}
								break;
							case 5://说明只含有3个Fc
							//[XMN, CZ, X/CAN483.55CZ, KTM, AI, X/DEL, M1333.24CZ, X/CAN, CZXMN231.80NUC2048.59END, ROE6.514200]
								if(middleNumbers==3){
									if(doublesAdd(lists.get(0),lists.get(1),lists.get(2))==objectToDouble(lists,3)){
										for(int k=0;k<lists.size()-2;k++){
											if(k==0){
												createFcDocument(root, (String)lists.get(4),splitSpace[0],splitSpace[2].substring(2, 5),(String) lists.get(k),idenFc.get(t));
											}else if (k==1){
												createFcDocument(root, (String)lists.get(4),splitSpace[2].substring(2, 5),splitSpace[5].substring(2, 5),(String) lists.get(k),idenFc.get(t));
											}else if (k==2){
												createFcDocument(root, (String)lists.get(4),splitSpace[5].substring(2, 5),splitSpace[0],(String) lists.get(k),idenFc.get(t));
											}

										}
									}
								}else if(middleNumbers==4){
									if(doublesAdd(lists.get(0),lists.get(1),lists.get(2))==objectToDouble(lists,3)){
										for(int k=0;k<lists.size()-2;k++){
											if(k==0){
												createFcDocument(root, (String)lists.get(4),splitSpace[0],splitSpace[6].substring(0, 3),(String) lists.get(k),idenFc.get(t));
											}else if (k==1){
												createFcDocument(root, (String)lists.get(4),splitSpace[6].substring(0, 3),splitSpace[7].substring(2, 5),(String) lists.get(k),idenFc.get(t));
											}else if (k==2){
												createFcDocument(root, (String)lists.get(4),splitSpace[7].substring(2, 5),splitSpace[10].substring(0, 3),(String) lists.get(k),idenFc.get(t));
											}

										}
									}
								}else {//[BJS, HU, TYO321.60KE, X/PUS370.35HU, BJS341.56NUC1033.51END, ROE6.51420]
									for (int j = 0; j < lists.size()-2; j++) {
										
										String toFirstToCity=splitSpace[2].substring(2, 5);
										String toSecondToCity=splitSpace[3].substring(0, 3);
										//String toThirdToCity=splitSpace[2].substring(2, 5);
										boolean hasDigit = hasDigit(toFirstToCity);
										if(hasDigit){
											toFirstToCity=splitSpace[2].substring(0, 3);
										}
										if(toSecondToCity.contains("/")){
											toSecondToCity=splitSpace[3].substring(2, 5);
										}
										
										if(j==0){
										
											createFcDocument(root, (String)lists.get(4),splitSpace[0],toFirstToCity,(String) lists.get(j),idenFc.get(t));

										}else if(j==1){
											createFcDocument(root, (String)lists.get(4),toFirstToCity,toSecondToCity,(String) lists.get(j),idenFc.get(t));

										}else if(j==2){
											createFcDocument(root, (String)lists.get(4),toSecondToCity,splitSpace[4].substring(0, 3),(String) lists.get(j),idenFc.get(t));

										}
									}
								}
								break;
							case 6://说明只含有4个Fc
								if(exictsQ){
									//[BJS, KA, X/HKG502.74KA, OKA, 479.61KA, X/HKG479.61KABJS, 502.74NUC1976.28END, ROE6.51420]
									//list [502.74, 479.61, 479.61, 502.74, 1976.28, 6.51420]
									for(int k=0;k<lists.size()-2;k++){ 
										if(k==0){
											createFcDocument(root, (String)lists.get(lists.size()-1),splitSpace[0],splitSpace[2].substring(2, 5),(String)lists.get(k),idenFc.get(t));

										}else if(k==1){
											createFcDocument(root, (String)lists.get(lists.size()-1),splitSpace[2].substring(2, 5),splitSpace[3],(String)lists.get(k),numberQ.get(0),idenFc.get(t));

										}
										else if(k==2){
											createFcDocument(root, (String)lists.get(lists.size()-1),splitSpace[3],splitSpace[5].substring(2, 5),(String)lists.get(k),idenFc.get(t));

										}
										else if(k==3){
											createFcDocument(root, (String)lists.get(lists.size()-1),splitSpace[5].substring(2, 5),splitSpace[0],(String)lists.get(k),numberQ.get(0),idenFc.get(t));

										}
									}
								
								}
								double doublesAdd4 = doublesAdd(lists.get(0),lists.get(1),lists.get(2),lists.get(3));
								if(doublesAdd4==objectToDouble(lists, 4)){
									for(int k=0;k<lists.size()-2;k++){//-2 是包括了1个总的nuc 跟一个roe
										//[BJS, SQ, X/SIN1015.82SQ, HAN747.49SQ, X/SIN747.49SQ, SHA918.44NUC3429.24END, ROE6.418450, X]												
										if(k==0){
											createFcDocument(root, (String)lists.get(5),splitSpace[0],splitSpace[2].substring(2, 5),(String) lists.get(k),idenFc.get(t));

										}else if(k==1){
											createFcDocument(root, (String)lists.get(5),splitSpace[2].substring(2, 5),splitSpace[3].substring(0, 3),(String) lists.get(k),idenFc.get(t));

										}else if(k==2){
											createFcDocument(root, (String)lists.get(5),splitSpace[3].substring(0, 3),splitSpace[4].substring(2, 5),(String) lists.get(k),idenFc.get(t));

										}else if(k==3){
											createFcDocument(root, (String)lists.get(5),splitSpace[4].substring(2, 5),splitSpace[5].substring(0, 3),(String) lists.get(k),idenFc.get(t));

										}

									}
								}

								break;

							case 7://说明只含有5个Fc
								double doublesAdd = doublesAdd(lists.get(0),lists.get(1),lists.get(2),lists.get(3),lists.get(4));
								if(doublesAdd==objectToDouble(lists, 5)){
									for(int k=0;k<lists.size()-2;k++){//-2 是包括了1个总的nuc 跟一个roe
										//[HA, CA, X/BJS579.57CA, LON1896.87DL, NYC1447.42DL, X/DTTDL, X/BJS, M3046.91CA, SHA579.57NUC7550.34END, ROE6.41845]												
										if(k==0){
											createFcDocument(root, (String)lists.get(6),splitSpace[0],splitSpace[2].substring(2, 5),(String) lists.get(0),idenFc.get(t));

										}else if(k==1){
											createFcDocument(root, (String)lists.get(6),splitSpace[2].substring(2, 5),splitSpace[3].substring(0, 3),(String) lists.get(0),idenFc.get(t));

										}else if(k==2){
											createFcDocument(root, (String)lists.get(6),splitSpace[3].substring(0, 3),splitSpace[4].substring(0, 3),(String) lists.get(2),idenFc.get(t));

										}else if(k==3){
											createFcDocument(root, (String)lists.get(6),splitSpace[4].substring(0, 3),splitSpace[6].substring(2, 5),(String) lists.get(3),idenFc.get(t));

										}else if(k==4){
											createFcDocument(root, (String)lists.get(6),splitSpace[6].substring(2, 5),splitSpace[8].substring(0, 3),(String) lists.get(4),idenFc.get(t));

										}

									}
								}

								break;

							default:
								createFcDocument(root, "Fc格式错误，解析异常");
								break;
							}
							//[510.24, 5.80, 359.12, 875.16, 6.41845]
							//[BJS, CX, HKG510.24KA, SHA, Q5.80, 359.12NUC875.16END, ROE6.41845]											
						}else if(!stringFc.contains("X/")){
							switch (size) {
							case 3:
								if(exictsQ){
									//[HKG, KA, HGH, 290.31NUC296.11END, ROE7.750300EN]
									String string = numberQ.get(0);
									createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[2],(String)lists.get(0),numberQ.get(0),idenFc.get(t));

								}
								//1个fc
								if(numberFcForIt(fcLast)>0){//it
									String iTRate = iTRate(fcLast);
									//[SHA, VS, LON, M/IT, END, ROE6.418450EN]
									for(int p=0;p<numberFcForIt(fcLast);p++){
										if(p==0){
											createFcDocument(root, iTRate,splitSpace[0],splitSpace[2],idenFc.get(t));

										}else if(p==1){
											createFcDocument(root,iTRate,splitSpace[2],splitSpace[0],idenFc.get(t));

										}
									}

								}else{
									//[BJS, CA, BKK359.89NUC359.89END, ROE6.418450XT]
									if(splitSpace.length==4)
										createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));
									//[LCA, EK, DXB, EK, BJS1417.67NUC1417.67END, ROE0.918404XT]

									else if(splitSpace.length==6)
										createFcDocument(root, (String)lists.get(2),splitSpace[0],splitSpace[4].substring(0, 3),(String) lists.get(0),idenFc.get(t));

								}
								break;
							case 4://2个fc
								//这里2把it、 非it区分开
								if(numberFcForIt(fcLast)>0){//it
									String iTRate = iTRate(fcLast);
									//[SHA, UA, LAX, M/IT, UA, SHA, M/IT, END, ROE6.418450XT]
									for(int p=0;p<numberFcForIt(fcLast);p++){
										if(p==0){
											createFcDocument(root, iTRate,splitSpace[0],splitSpace[2],idenFc.get(t));

										}else if(p==1){
											createFcDocument(root,iTRate,splitSpace[2],splitSpace[0],idenFc.get(t));

										}
									}

								}
								if(numberQ.size()!=0){//说明含有q值
									//20AUG16BJS EY AUH Q23.02Q1.50 237.94EY BJS Q23.02Q1.50 237.94NUC524.92END ROE6.514200                   
									
									for (int j = 0; j < lists.size()-2; j++) {
										if(j==0){
											createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),doublesAdd(numberQ.get(0),numberQ.get(1))+"", idenFc.get(t));
										}else if(j==1){
											createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[0],(String) lists.get(1),doublesAdd(numberQ.get(2),numberQ.get(3))+"",idenFc.get(t));
										}
									}
								}
								else{
									//不是it票的判断逻辑
									if(lists.get(0).equals(lists.get(1))){
										for (int j = 0; j < lists.size(); j++) {
											if(j==0){
												createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));

											}else if(j==1){
												createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[0],(String) lists.get(1),idenFc.get(t));

											}
										}
									}else if(!lists.get(0).equals(lists.get(1))){
										//两个fc的情况  没有q
										if(doublesAdd(lists.get(0),lists.get(1))==objectToDouble(lists, 2)&&numberQ.size()==0){
											for (int j = 0; j < lists.size()-2; j++) {
												if(j==0){
													createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));
												}else if(j==1){
													//[SHA, LH, MUC433.66LH, BJS322.37NUC756.03END, ROE6.514200XT]
													String toCity=splitSpace[0];
													if(!toCity.equals(splitSpace[3].substring(0, 3)));{
														toCity=splitSpace[3].substring(0, 3);
													}
													createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),toCity,(String) lists.get(1),idenFc.get(t));
												}
											}//[510.24, 359.12, 875.16, 6.41845][BJS, CX, HKG510.24KA, SHA, 359.12NUC875.16END, ROE6.41845]
										}else if(numberQ.size()!=0){//说明含有q值
											for (int j = 0; j < lists.size()-2; j++) {
												if(j==0){
													createFcDocument(root, (String)lists.get(3),splitSpace[0],splitSpace[2].substring(0, 3),(String) lists.get(0),idenFc.get(t));
												}else if(j==1){
													createFcDocument(root, (String)lists.get(3),splitSpace[2].substring(0, 3),splitSpace[0],(String) lists.get(1),numberQ.get(0),idenFc.get(t));
												}
											}
										}
									}
								}
								break;
							case 5://3个fc
								break;
							case 6://4个fc
								break;
							case 7://5个fc
								break;

							default:
								createFcDocument(root, "Fc格式错误，解析异常");
								break;
							}
						}
						/**
						 * 中转逻辑判断 end
						 * 冯俊伟
						 * 2016年3月28日18:10:32
						 */
						t++;
					}
					if((rows[i].contains("QTE")||rows[i].contains("qte"))&&!rows[i].contains("*")){
						//int k=0;
						//					System.out.println("i位："+i+"    "+selectQteRows.get(0));
						Element element = root.addElement("qte");
						int f = 0;
						for(int h=0;h<selectQteRows.size()-1;h++){
							if((Integer)selectQteRows.get(h)==i){
								f=h;
							}
						}


						for(int g=f;g<f+1;g++){
							String qteIdentity = qteIdentity(rows[(Integer) selectQteRows.get(g)]);

							for (int m = i; m < (Integer) selectQteRows.get(g+1); m++) {
								rowData=rows[m];
								if((rowData.startsWith(" "+substringTotal)&&!rowData.contains("/"))||rowData.startsWith(" FARE")||rowData.startsWith(" TAX")){
									if((rowData.startsWith(" "+substringTotal)&&!rowData.contains("/"))){
										String[] split = rowData.split(" ");
										List<String> strlist=new ArrayList<String>();
										for (int j = 0; j < split.length; j++) {
											if(split[j]!=null&&split[j].length()!=0){
												strlist.add(split[j]);
											}
										}
										if(qteIdentity.length()==0){
											element
											.addAttribute("isContainTax",strlist.get(0))
											.addAttribute("price",strlist.get(2))
											.addAttribute("priceUnit",strlist.get(3))
											.addAttribute("identity",qteIdentity)
											;
										}else{

											//[01, KPOWCN, CH, 11649, CNY, INCL, TAX]
											element
											.addAttribute("isContainTax",strlist.get(0))
											.addAttribute("price",strlist.get(3))
											.addAttribute("priceUnit",strlist.get(4))
											.addAttribute("identity",qteIdentity)
											;
										}
									}
								}
								if(rowData.startsWith(" FARE")){
									//Element rootElement = document.getRootElement();
									//Element element = rootElement.element("qte");
									//替换空格，文本中有多个空格，将多个空格替换成一个空格，方便拆分
									Pattern pattern=Pattern.compile("\\s+");
									Matcher matcher = pattern.matcher(rowData);
									String replaceAll = matcher.replaceAll(" ");
									String[] split1 = replaceAll.split(" ");
									element.addAttribute("fare", split1[3]);
									if(split1.length>4)
										element.addAttribute("type", split1[4]);
								}
								if(rowData.startsWith(" TAX")){
									//Element rootElement = document.getRootElement();
									//Element element = rootElement.element("qte");
									//替换空格，文本中有多个空格，将多个空格替换成一个空格，方便拆分
									Pattern pattern=Pattern.compile("\\s+");
									Matcher matcher = pattern.matcher(rowData);
									String replaceAll = matcher.replaceAll(" ");
									String replaceFirst = replaceAll.replaceAll("[a-zA-Z]", "");
									Matcher matcherSecond = pattern.matcher(replaceFirst);
									String replaceAllSecond = matcherSecond.replaceAll(" ");

									String[] split2 = replaceAllSecond.split(" ");
									int totalMoney = 0;
									for (int j = 0; j < split2.length; j++) {
										String string = split2[j];
										if(string.length()!=0){
											int parseInt = Integer.parseInt(string);
											totalMoney=totalMoney+parseInt;
										}


									}
									element.addAttribute("tax",totalMoney+"");
									//fc 2570.71  482.98  3053.69  6.41845
								}


							}
						}
						
					}
				}else{
					break;
				}

			}
			//logger.info(document.asXML());
			return document;
		} else{//如果没有旅客，并且航程不是从1开始编号，单独做一个（又是一个特殊的）

			boolean returnNull=true;
			String[] rows=source.split("\n");
			if(rows!=null){
				rows=cutWrongNewline(rows);
				//-----------
				for(int i=0;i<rows.length;i++){
					if(rows[i]!=null){               
						if(judgeRowBeginSecond(rows[i])==true){
							parseAirRow(root,rows[i]);
							returnNull=false;
						}

						//解析身份证
						if(rows[i].indexOf(" NI")!=-1&&rows[i].indexOf("/P1")!=-1&&rows[i].split(" NI")[1].split("/P1")[0].length()>0){
							root.addElement("IDCardNo").addAttribute("name", rows[i].split(" NI")[1].split("/P1")[0].trim());
						}

						//解析手机号
						if(rows[i].indexOf(" MP ")!=-1&&rows[i].split(" MP ")[1].length()>0){
							root.addElement("mobile").addAttribute("name", rows[i].split(" MP ")[1].substring(0,11).trim());
						}
					}
				}

			}
			if(returnNull)
				return null;
			else
				return document;
		}

	}



	public  void parsePassengerRow(Element root,String passenger,boolean includePNR){

		if(includePNR){
			passenger=passenger.trim();//去掉头尾的空格
			//添加PNR码
			if(passenger.lastIndexOf(" ") != -1){
				root.addElement("pnrCode").addAttribute("pnr", passenger.substring(passenger.lastIndexOf(" ")));
				passenger=passenger.substring(0,passenger.lastIndexOf(" "));
			}



		}
		String[] passengerInfo=passenger.split("\\.");
		String name="";
		for(int i=1;i<passengerInfo.length;i++){
			if(i==passengerInfo.length-1){
				name=passengerInfo[i];
			}else{
				int median = passengerInfo[i].lastIndexOf(" ")!=-1?passengerInfo[i].lastIndexOf(" "):passengerInfo[i].length();
				name=passengerInfo[i].substring(0,median);
				name = name.replaceAll("\\d", "");//将旅客名字中的数字去掉
			}

			/**
			 * 2016年4月6日18:32:45
			 * 冯俊伟
			 * 取出旅客的身份 与类型
			 * 1.FENG/JUN WEI CHD
			 * 2.XIE/XIN YING
			 * 3.LUO/PING MR
			 * 4.ZHANG/HUILI MS
			 * 
			 */
			String[] split = name.split(" ");//
			String passIdentity=split[split.length-1];//旅客身份
			int pasType = findPasTypeByName(passIdentity);//类型
			int identity = findPasIdentityByName(passIdentity);//身份
			String nameVal=null;
			if(split.length==3){//   FENG/JUN WEI CHD
				String lastName = null;
				for (int j = 0; j < split.length-1; j++) {
					lastName+= split[j];
					lastName+=" ";
				}
				nameVal=lastName.substring(4,lastName.length()).trim();
			}else if(split.length==2){//   XIE/XIN YING || ZHANG/HUILI MS
				boolean res = judgeNameIsIdentity(passIdentity);
				if(!res){
					String lastName = null;
					for (int j = 0; j < split.length; j++) {
						lastName+= split[j];
						lastName+=" ";
					}
					nameVal=lastName.substring(4,lastName.length()).trim();
				}
				pasType=0;
				identity=0;
			}else{
				nameVal=name;
			}


			Element passElm2 = root.addElement("passenger")
					.addAttribute("name", nameVal)
					.addAttribute("passType",pasType+"")
					.addAttribute("passIdentity",identity+"")
					;


		}

	}
	/**
	 * 解析航班、航程信息行
	 * @param root
	 * @param airInfo
	 */
	public void parseAirRow(Element root,String airInfo){
		String[] s = airInfo.trim().split(" ", 30);
		int len = 0;
		//是否跨年
		boolean acrossYear=false;
		String[] airs = new String[9];		
		for (int i = 0; i < s.length; i++) {			
			if(s[i]!=null){
				if (!"".equals(s[i].trim()) ) {
					if (len < 9){
						//如果是跨年的	，把"日期"和"城市"拆分				    
						if(s[i].trim().length()==15 && airs[3] == null){
							acrossYear=true;
							airs[len] = s[i].trim().substring(0,9);
							len++;
							airs[len] = s[i].trim().substring(9);
						}else{
							/*王兰虎添加代码开始　20120328 */
							if(s[i].length()>= 11  && 
									s[i].substring(0,1).compareTo("A")>0 && s[i].substring(0,1).compareTo("Z")<0 &&
									s[i].substring(1,2).compareTo("A")>0 && s[i].substring(1,2).compareTo("Z")<0 &&
									s[i].substring(3,11).replaceAll("\\d", "").length() == 0
									){
								//HK段
								airs[len] = s[i].substring(0,3);
								len++;
								//起飞时间
								airs[len] = s[i].substring(3,7);
								len++;
								//到达时间
								airs[len] = s[i].substring(7,11);


								//但是如果到达时间后面有+1或者-1,则将+1和-1也放到结束时间内显示
								if(s[i].length()>=13 && (s[i].substring(10,11).equals("+1") || s[i].substring(10,11).equals("-1")))
								{
									airs[len] = s[i].substring(8,13);
								}
							}else if(s[i].length()>=8 && s[i].substring(0,8).replaceAll("\\d", "").equals("")){
								//如果出现 3.  MU5558 X   WE04APR  HYNSHA HK2 15201615E--T2  的样例，要针对 15201615E--T2 进行处理，获取2个时间
								//								HU7854 Q SU22APR JZHCTU HK118401930 E
								//								HU7854 Q SU22APR JZHCTU HK118401930E
								//								HU7854 Q SU22APR JZHCTU HK 118401930E
								//								HU7854 Q SU22APR JZHCTU HK 118401930 E
								//								HU7854 Q SU22APR JZHCTUHK118401930E
								//								CA1953 V FR30MAR WNZCKGHK112001420E--T2V1
								String a = "";
								String timeString = s[i];//s[i].replaceAll("E", "").replaceAll("\\+1", "").replaceAll("\\-1", "");

								if(timeString.length() >= 8) {
									if(timeString.substring(0, 9).replaceAll("\\d", "").length() == 0){
										//说明前9位都是数字,满足 118401930E 格式，那么1840开始是有效时间
										timeString = timeString.substring(1);
									}

									s[i] = timeString;

									airs[len] = s[i].substring(0,4);
									len++;
									airs[len] = s[i].substring(4,8);

									//但是如果到达时间后面有+1或者-1,则将+1和-1也放到结束时间内显示
									if(s[i].length()>=10 && (s[i].substring(8,10).equals("+1") || s[i].substring(8,10).equals("-1")))
									{
										airs[len] = s[i].substring(4,10);
									}else if(s[i].length() == 9){
										len++;
										airs[len] = s[i].substring(8,s[i].length());//把E取到
									}
								}

							}else  if(s[i].length()>=16 && (s[i].substring(9, 17).replaceAll("\\d", "").length() == 0)){
								//如果出现 3.  HU7854 Q SU22APR JZHCTUHK118401930E  的样例，要针对 JZHCTUHK118401930E 进行处理，获取2个时间
								//								HU7854 Q SU22APR JZHCTUHK118401930E
								//								CA1953 V FR30MAR WNZCKGHK112001420E--T2V1
								String a = "";
								String timeString = s[i];//.replaceAll("\\+1", "").replaceAll("\\-1", "");

								airs[len] = timeString.substring(0, 6);//起飞机场/到达机场
								len++;

								airs[len] = timeString.substring(6,9);//
								len++;

								timeString = timeString.substring(6);//去掉前面6位机场3字码还有RR1或者HK1字样
								s[i] = timeString;
								if(s[i].length()>= 11  && s[i].substring(3,11).replaceAll("\\d", "").length() == 0){
									//HK段
									//airs[len] = s[i].substring(0,3);
									//len++;
									//起飞时间
									airs[len] = s[i].substring(3,7);
									len++;
									//到达时间
									airs[len] = s[i].substring(7,11);


									//但是如果到达时间后面有+1或者-1,则将+1和-1也放到结束时间内显示
									if(s[i].length()>=13 && (s[i].substring(10,11).equals("+1") || s[i].substring(10,11).equals("-1")))
									{
										airs[len] = s[i].substring(8,13);
									}
								}
							}else{
								airs[len] = s[i].trim();
							}
							/*王兰虎添加代码结束*/
							//airs[len] = s[i].trim();//王兰虎加入上段代码前的一行
						}
						//System.out.println("s["+i+"]="+s[i].trim());
						len++;
					}

				}
			}
		}
		//未确定航班号，时间的航程
		if(airs[1]!=null){
			if(airs[1].endsWith("OPEN")){

				Element line = root.addElement("line");
				if(airs[1]!=null){
					if(airs[1].length()>=2){
						line=line.addAttribute("airCompany",airs[1].substring(0,2));//航空公司
					}else{
						line=line.addAttribute("airCompany","");//航空公司
					}
				}else{
					line=line.addAttribute("airCompany","");//航空公司
				}
				line=line.addAttribute("lineCode", "");//航班号		
				if(airs[2]!=null){
					if(airs[2].length()>=1){
						line=line.addAttribute("berth",airs[2].trim().substring(0, 1));//舱位
					}else{
						line=line.addAttribute("berth","");//舱位
					}
				}else{
					line=line.addAttribute("berth","");//舱位
				}
				if(airs[3]!=null){
					if(airs[3].length()>=3){
						line=line.addAttribute("beginCity", airs[3].substring(0, 3));//起飞城市
					}else{
						line=line.addAttribute("beginCity", "");//起飞城市
					}
					if(airs[3].length()>3){
						line=line.addAttribute("arriveCity",airs[3].substring(3, airs[3].length()));//到达城市
					}else{
						line=line.addAttribute("arriveCity", "");//到达城市
					}
				}else{
					line=line.addAttribute("beginCity", "");//起飞城市
					line=line.addAttribute("arriveCity", "");//到达城市
				}
				line=line.addAttribute("beginTime","")//起飞时间
						.addAttribute("arriveTime","");//到达时间

			}else if("ARNK".equals(airs[1])){
				//中断的航程

				Element line = root.addElement("line")			
						.addAttribute("airCompany","")//航空公司
						.addAttribute("lineCode", "")//航班号					
						.addAttribute("berth","");//舱位
				if(airs[2]!=null){
					if(airs[2].length()>=3){
						line=line.addAttribute("beginCity", airs[2].substring(0, 3));//起飞城市
					}else{
						line=line.addAttribute("beginCity","");//起飞城市
					}
					if(airs[2].length()>3){
						line=line.addAttribute("arriveCity",airs[2].substring(3, airs[2].length()));//到达城市
					}else{
						line=line.addAttribute("arriveCity","");//到达城市
					}
				}else{
					line=line.addAttribute("beginCity", "")//起飞城市
							.addAttribute("arriveCity","");//到达城市
				}
				line=line.addAttribute("beginTime","")//起飞时间
						.addAttribute("arriveTime","");//到达时间
			}
			else{


				Element line = root.addElement("line");
				//------------------------------------
				if(airs[1]!=null){
					if(airs[1].length()>=2){
						line=line.addAttribute("airCompany",airs[1].substring(0,2));//航空公司
					}else{
						line=line.addAttribute("airCompany","");//航空公司
					}				
					line=line.addAttribute("lineCode", airs[1]);//航班号
				}else{
					line=line.addAttribute("airCompany","")//航空公司
							.addAttribute("lineCode", "");//航班号
				}
				//----------------------------------------
				if(airs[2]!=null){				
					if(airs[2].length()>=1){
						line=line.addAttribute("berth",airs[2].trim().substring(0, 1));//舱位
					}else{
						line=line.addAttribute("berth","");//舱位
					}
				}else{
					line=line.addAttribute("berth","");//舱位
				}
				//-----------------------------------------
				if(airs[4]!=null){
					if(airs[4].length()>=3){
						line=line.addAttribute("beginCity", airs[4].substring(0, 3));//起飞城市
					}else{
						line=line.addAttribute("beginCity", "");//起飞城市
					}
					if(airs[4].length()>3){
						line=line.addAttribute("arriveCity",airs[4].substring(3, airs[4].length()));//到达城市
					}else{
						line=line.addAttribute("arriveCity","");//到达城市
					}				

				}else{
					line=line.addAttribute("beginCity", "")//起飞城市				
							.addAttribute("arriveCity","");//到达城市
				}
				//------------------------------------------------					
				if(airs[3]!=null && airs[6]!=null){
					line=line.addAttribute("beginTimeMark",airs[3].substring(2, airs[3].length()));//起飞时间标示，方便解析fc
					line=line.addAttribute("beginTime",getTime(airs[3], airs[6],acrossYear));//起飞时间
				}else{
					line=line.addAttribute("beginTime","");//起飞时间
				}
				if(airs[3]!=null && airs[7]!=null){
					line=line.addAttribute("arriveTime",getArriveTime(airs[3], airs[7],acrossYear));//到达时间
				}else{

					line=line.addAttribute("arriveTime","");//到达时间
				}
			}
		}

	}


	//取得开始时间或到达时间	
	public static String getTime(String PNRDate, String PNRTime,boolean acrossYear) {
		String substring = PNRDate.substring(2, PNRDate.length());
		if (PNRDate == null || PNRDate.trim().length() < 7)
			return "";
		if (PNRTime == null || PNRTime.trim().length() < 4)
			return "";
		PNRDate = PNRDate.trim();
		PNRTime = PNRTime.trim();

		StringBuffer myTime=new StringBuffer();		
		String myYear=getYear();
		if(myYear==null){
			return "";
		}
		if(myYear.length()<4)
			return "";

		/*王兰虎添加开始2008-12-18 17:27:58*/
		//月份日期是当前日期之前的，默认是下一年
		try
		{
			String tmp = CvtMonth(PNRDate.substring(4, 7))+"-" + PNRDate.substring(2, 4);

			if(tmp.compareTo(new DateTool().toDateString().substring(5))<0)
			{
				myYear = String.valueOf(Integer.valueOf(myYear).intValue()+1);
			}
		} catch (NumberFormatException e)
		{
			logger.error("NumberFormatException|",e);
		}		
		/*王兰虎添加结束*/

		//如果是跨年
		if(acrossYear && PNRDate.length()==9){
			myTime.append(myYear.trim().substring(0,2)).append(PNRDate.substring(7,9));
		}else{
			myTime.append(myYear);
		}
		myTime.append("-").append(CvtMonth(PNRDate.substring(4, 7)));
		myTime.append("-").append(PNRDate.substring(2, 4)).append(" ");
		myTime.append(PNRTime.substring(0, 2)).append(":");
		myTime.append(PNRTime.substring(2, 4));



		return myTime.toString();
		/*
		return getYear() + "-"
				+ CvtMonth(PNRDate.substring(4, PNRDate.length())) + "-"
				+ PNRDate.substring(2, 4) + " " + PNRTime.substring(0, 2) + ":"
				+ PNRTime.substring(2, 4);
		 */
	}

	//取得到达时间
	public static String getArriveTime(String PNRDate, String PNRTime,boolean acrossYear) {
		String time = getTime(PNRDate, PNRTime,acrossYear);
		if (PNRTime != null)
			if (PNRTime.length() == 6) {
				return getLaterTime(PNRTime, time);
			} else
				return time;
		else
			return "";
	}

	//日期加天数或减天数
	public static String getLaterTime(String PNRTime, String time) {

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if ("+".equals(PNRTime.substring(4, 5))) {
			gc.setTime(new DateTool(time.substring(0, 10)).toDate());
			gc.add(Calendar.DATE, +Integer.parseInt(PNRTime.substring(5, 6)));
			return sf.format(gc.getTime()) + " " + PNRTime.substring(0, 2)
			+ ":" + PNRTime.substring(2, 4);
		} else if ("-".equals(PNRTime.substring(4, 5))) {
			gc.setTime(new DateTool(time.substring(0, 10)).toDate());
			gc.add(Calendar.DATE, -Integer.parseInt(PNRTime.substring(5, 6)));
			return sf.format(gc.getTime()) + " " + PNRTime.substring(0, 2)
			+ ":" + PNRTime.substring(2, 4);
		}
		return "";
	}

	//取得系统年份
	public static String getYear() {
		DateTool d = new DateTool(System.currentTimeMillis());
		return d.toYearString();
	}

	//月份中英文转换
	public static String CvtMonth(String mon) {
		if ("JAN".equals(mon))
			return "01";
		else if ("FEB".equals(mon))
			return "02";
		else if ("MAR".equals(mon))
			return "03";
		else if ("APR".equals(mon))
			return "04";
		else if ("MAY".equals(mon))
			return "05";
		else if ("JUN".equals(mon))
			return "06";
		else if ("JUL".equals(mon))
			return "07";
		else if ("AUG".equals(mon))
			return "08";
		else if ("SEP".equals(mon))
			return "9";
		else if ("OCT".equals(mon))
			return "10";
		else if ("NOV".equals(mon))
			return "11";
		else if ("DEC".equals(mon))
			return "12";
		return null;

	}
	//判断是否数字加小数点加空格开头
	public boolean judgeRowBeginSecond(String str){
		//System.out.println("="+str+"=");
		Pattern pattern = Pattern.compile("^\\d*\\.\\s.*$");
		Matcher m = pattern.matcher(str.trim());
		return m.matches();		
	}

	//判断字符串是否数字加小数点开头
	public boolean judgeRowBegin(String str){
		//Pattern pattern = Pattern.compile("^\\d*\\.[\\w|\\s]*$");
		Pattern pattern = Pattern.compile("^\\d*\\..*$");
		Matcher m = pattern.matcher(str);
		return m.matches();		
	}
	//去掉不合适的换行(这里就不写成递归了)
	public String[] cutWrongNewline(String rows[]){		
		for(int i=0;i<rows.length-1;i++){
			if(rows[i+1]!=null){
				//System.out.println("下移行="+rows[i+1]);
				if(judgeRowBegin(rows[i+1].trim())==false){
					rows[i]=rows[i]+" "+rows[i+1];	
					rows[i+1]="";

				}
			}
		}		
		return rows;
	}

	//解析document
	public boolean parsePNRXML(Document document) {
		if(document==null){
			return false;
		}
		listPNR = new ArrayList();
		listName = new ArrayList();
		listLine = new ArrayList();
		listMobile = new ArrayList();
		listIDCardNo= new ArrayList();
		qtes=new ArrayList();
		fcs=new  ArrayList();
		PNRContent form = new PNRContent();
		/**
		 * 冯俊伟
		 * 2016年3月23日11:19:40
		 */
		Element outRoot = document.getRootElement();//获取文档的根节点.
		Map IDCardNOmap = new HashMap();
		//获取身份证号码
		//.取得某节点下名为"IDCardNo"的所有字节点并进行遍历.
		for (Iterator i = outRoot.elementIterator("IDCardNo"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();
			if(!trim(foo.attributeValue("passportCou")).equals("")){
				form.setPassportCou(foo.attributeValue("passportCou"));
			}
			if(!trim(foo.attributeValue("number")).equals("")){
				form.setIDCardNo(foo.attributeValue("number"));
			}
			if(!trim(foo.attributeValue("birCountry")).equals("")){
				form.setBirCountry(foo.attributeValue("birCountry"));
			}
			if(!trim(foo.attributeValue("birth")).equals("")){
				form.setBirth(foo.attributeValue("birth"));
			}
			if(!trim(foo.attributeValue("sex")).equals("")){
				form.setSex(foo.attributeValue("sex"));
			}
			if(!trim(foo.attributeValue("validData")).equals("")){
				form.setValidData(foo.attributeValue("validData"));
			}
			if(!trim(foo.attributeValue("pName")).equals("")){
				form.setpName(foo.attributeValue("pName"));
			}
			//if(!trim(foo.attributeValue("code")).equals("")){
			//	form.setCode(foo.attributeValue("code"));
			//}		
			//nj修改 调整身份证号码顺序 2011-11-30
			listIDCardNo.add(form);
			//IDCardNOmap.put(foo.attributeValue("number"),form);
		}

		for (Iterator i = outRoot.elementIterator("Fc"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();
			if(!trim(foo.attributeValue("fcNum")).equals("")){
				form.setFcNum(foo.attributeValue("fcNum"));
			}
			if(!trim(foo.attributeValue("fromCity")).equals("")){
				form.setFromCity(foo.attributeValue("fromCity"));
			}
			if(!trim(foo.attributeValue("toCity")).equals("")){
				form.setToCity(foo.attributeValue("toCity"));
			}
			if(!trim(foo.attributeValue("rate")).equals("")){
				form.setRate(foo.attributeValue("rate"));
			}
			if(!trim(foo.attributeValue("Q")).equals("")){
				form.setqString(foo.attributeValue("Q"));
			}
			if(!trim(foo.attributeValue("iden")).equals("")){
				form.setIden(foo.attributeValue("iden"));
			}
			fcs.add(form);
		}

		//获取手机号
		for (Iterator i = outRoot.elementIterator("mobile"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();

			if(!trim(foo.attributeValue("name")).equals("")){
				form.setMobilePhone(foo.attributeValue("name"));
			}
			//if(!trim(foo.attributeValue("code")).equals("")){
			//	form.setCode(foo.attributeValue("code"));
			//}		
			listMobile.add(form);
		}

		//获取旅客信息
		for (Iterator i = outRoot.elementIterator("passenger"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();

			if(!trim(foo.attributeValue("name")).equals("")){
				form.setName(foo.attributeValue("name"));
			}
			if(!trim(foo.attributeValue("passType")).equals("")){
				form.setPassType(foo.attributeValue("passType"));
			}
			if(!trim(foo.attributeValue("passIdentity")).equals("")){
				form.setPassIdentity(foo.attributeValue("passIdentity"));
			}
			//if(!trim(foo.attributeValue("code")).equals("")){
			//	form.setCode(foo.attributeValue("code"));
			//}		
			listName.add(form);

		}

		//nj 调整身份证号顺序
		if(listName!=null&&listName.size()>0){
			for(int i=1;i<=listName.size();i++){
				//nj修改 调整身份证号码顺序 2011-11-30
				if(IDCardNOmap.get(String.valueOf(i))!=null){
					listIDCardNo.add(IDCardNOmap.get(String.valueOf(i)));
				}else{
					listIDCardNo.add(null);
				}
			}
		}


		//获取PNR
		for (Iterator i = outRoot.elementIterator("pnrCode"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();

			if(!trim(foo.attributeValue("pnr")).equals("")){
				form.setPnrCode(foo.attributeValue("pnr"));
			}
			//if(!trim(foo.attributeValue("code")).equals("")){
			//	form.setCode(foo.attributeValue("code"));
			//}		
			listPNR.add(form);
		}	

		//获取航段信息
		for (Iterator i = outRoot.elementIterator("line"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();

			if(!trim(foo.attributeValue("lineCode")).equals("")){
				form.setLineCode( foo.attributeValue("lineCode"));
			}		
			if(!trim(foo.attributeValue("berth")).equals("")){
				form.setBerth(foo.attributeValue("berth"));
			}		
			if(!trim(foo.attributeValue("beginCity")).equals("")){
				form.setBeginCity(foo.attributeValue("beginCity"));
			}		
			if(!trim(foo.attributeValue("arriveCity")).equals("")){
				form.setArriveCity(foo.attributeValue("arriveCity"));
			}
			if(!trim(foo.attributeValue("beginTimeMark")).equals("")){
				form.setBeginTimeMark(foo.attributeValue("beginTimeMark"));
			}	
			if(!trim(foo.attributeValue("beginTime")).equals("")){
				form.setBeginTime(formatDate(foo.attributeValue("beginTime"),"yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm"));
			}		
			if(!trim(foo.attributeValue("arriveTime")).equals("")){
				form.setArriveTime(formatDate(foo.attributeValue("arriveTime"),"yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm"));
			}	
			if(!trim(foo.attributeValue("airCompany")).equals("")){
				form.setCompany(foo.attributeValue("airCompany"));
			}			
			listLine.add(form);
		}
		/**
		 * 冯俊伟
		 * 2016年3月24日10:21:31
		 * 作用：获取qte内容
		 */
		for (Iterator i = outRoot.elementIterator("qte"); i.hasNext();) {
			Element foo = (Element) i.next();
			form = new PNRContent();

			if(!trim(foo.attributeValue("price")).equals("")){
				form.setPrice(foo.attributeValue("price"));
			}
			if(!trim(foo.attributeValue("priceUnit")).equals("")){
				form.setPriceUnit(foo.attributeValue("priceUnit"));
			}
			if(!trim(foo.attributeValue("state")).equals("")){
				form.setState(foo.attributeValue("state"));
			}
			if(!trim(foo.attributeValue("isContainTax")).equals("")){
				form.setIsContainTax(foo.attributeValue("isContainTax"));
			}
			if(!trim(foo.attributeValue("fare")).equals("")){
				form.setFare(foo.attributeValue("fare"));
			}
			if(!trim(foo.attributeValue("tax")).equals("")){
				form.setTax(foo.attributeValue("tax"));
			}
			if(!trim(foo.attributeValue("type")).equals("")){
				form.setType(foo.attributeValue("type"));
			}
			if(!trim(foo.attributeValue("identity")).equals("")){
				form.setIdentity(foo.attributeValue("identity"));
			}
			/*if(!trim(foo.attributeValue("fcNum")).equals("")){
				Fc fc=new Fc();
				fc.setFcNum(foo.attributeValue("fcNum"));
				fc.setFromCity(foo.attributeValue("fromCity"));
				fc.setToCity(foo.attributeValue("toCity"));
				fc.setRate(foo.attributeValue("rate"));
				form.setFc(fc);
			}
			 */
			qtes.add(form);
		}

		return true;
	}

	public List getListLine() {
		return listLine;
	}

	public List getListName() {
		return listName;
	}
	public List getListPNR() {
		return listPNR;
	}


	public List getListMobile()
	{
		return listMobile;
	}


	public List getListIDCardNo()
	{
		return listIDCardNo;
	}

	public List getQtes() {
		return qtes;
	}

	public void setQtes(List qtes) {
		this.qtes = qtes;
	}


	public List getFcs() {
		return fcs;
	}

	public void setFcs(List fcs) {
		this.fcs = fcs;
	}

	/**
	 * 将给定的字符串做去空处理。
	 * 如果给定字符串为null、"null"、"Null"等，返回空字符串。
	 * 如果给定字符串为" str"、"str "或" str "，则返回"str";
	 * @param str 需要转换的字符串 如null,"null"。
	 * @return 处理后的字符串 如""。
	 */
	public String trim(String str) {

		if (str == null) {
			return "";
		}
		//比较str是否等于"null",忽略大小写。
		if (str.trim().equalsIgnoreCase("null")) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 输入时间字符串，原时间字符串格式，和需要得到的时间字符串格式,得到需要格式的时间字符串。
	 * 注意：y为年，M为月，d为日。H为小时(24小时)，h为小时（12小时）m为分钟，s为秒。注意大小写。
	 * 		 zzzz为时区，EEE为星期，MMMM为月份(大写)，a为上下午。
	 * @param datetime   需要转换格式的时间字符 如2006040123595。
	 * @param oldFormat  原时间字符串格式 如"yyyyMMddHHmmss"。注意，该格式必须与给定时间字符串的格式一致。
	 * @param newFormat  原时间字符串格式 如"yyyy-M-d H:m:s"。
	 * 
	 * @return 需要格式的时间字符串 如：2006-04-01 23:59:59
	 */
	public String formatDate(String datetime, String oldFormat, String newFormat) {
		String dateStr = "";

		try {
			if (datetime == null || oldFormat == null || newFormat == null) {
				//throw new CommonException("Error:DateTools.formatDate:输入参数格式错误!");
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(newFormat);
			Calendar calendar = this.getCalendar(datetime, oldFormat);
			Date newDate = calendar.getTime();
			dateStr = simpleDateFormat.format(newDate);

			return dateStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}


	public String formatDate(String datetime, String oldFormat, String newFormat,Locale loc) {
		String dateStr = "";

		try {
			if (datetime == null || oldFormat == null || newFormat == null) {
				//throw new CommonException("Error:DateTools.formatDate:输入参数格式错误!");
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(newFormat,loc);
			Calendar calendar = this.getCalendar(datetime, oldFormat);
			if (calendar == null) {
				//throw new CommonException("Error:DateTools.formatDate:得到新时间格式Calendar异常!");
			}
			Date newDate = calendar.getTime();
			dateStr = simpleDateFormat.format(newDate);

			return dateStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}


	private Calendar getCalendar(String datetime, String dateFormat) {
		Calendar calendar = null;

		try {
			if (datetime == null || dateFormat == null) {
				return null;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			// 从给定时间字符串的第一位开始分析,返回Date类型
			Date newdate = simpleDateFormat.parse(datetime,
					new ParsePosition(0));
			if (newdate == null) {
				return null;
				//throw new CommonException("输入参数格式错误!");
			}
			calendar = Calendar.getInstance();
			calendar.clear();
			calendar.setTime(newdate);
			return calendar;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	/**
	 * 查找含有中转的个数
	 * 冯俊伟
	 * 2016年3月29日10:40:44X -/
	 */
	private static  int counter = 0; 
	public static int stringNumbers(String str){  
		if (str.indexOf("X/")==-1){  
			return 0;  
		}  
		else if(str.indexOf("X/") != -1){  
			counter++;  
			stringNumbers(str.substring(str.indexOf("X/")+2));  
			return counter;  
		}  
		return 0;  
	}
	/**
	 * 
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月29日
	 * 作用：查找第一个中转后的字符串还有.吗
	 * 	[BJS, DL, X/SEA, DL, NYC, M1090.60DL, X/DTT, DL, BJS, M475.19NU, C1565.79END, ROE6.418450]
		[SHA, CA, X/BJS579.57UL, X/CMB, UL, DOH1154.48NUC1734.05ENDROE6.41845]
	 * 
	 * 
	 */
	public static boolean findFirstX(String str){

		int indexOf = str.indexOf("X/");

		String substring = str.substring(indexOf+2, indexOf+14);
		boolean contains = substring.contains(".");

		return contains;
	}
	/**
	 * 
	 * @param doubles
	 * @auth 冯俊伟
	 * @date 2016年3月29日
	 * 作用：相加
	 */
	public static double doublesAdd(Object ...doubles){
		BigDecimal douBleNull=new BigDecimal(String.valueOf(0.00));
		BigDecimal add = null;
		for (int i = 0; i < doubles.length; i++) {
			BigDecimal bigDecimal = new BigDecimal(String.valueOf(doubles[i]));
			add = douBleNull.add(bigDecimal);
			douBleNull=add;
		}
		return add.doubleValue();
	}

	/**
	 * 
	 * @param lists
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月5日
	 * 作用：计算出最后的q值和
	 */
	public String doublesAdd(List<String> lists){
		BigDecimal douBleNull=new BigDecimal(String.valueOf(0.00));
		BigDecimal add = null;
		for (int i = 0; i < lists.size(); i++) {
			BigDecimal bigDecimal = new BigDecimal(String.valueOf(lists.get(i)));
			add = douBleNull.add(bigDecimal);
			douBleNull=add;
		}
		return add.toString();
		//BigDecimal bigDecimal2 =new BigDecimal(initQ);
		//String lastString = lists.get(lists.size()-1);
		//BigDecimal bigDecimal1 =new BigDecimal(lastString);
		//BigDecimal subtract = bigDecimal1.subtract(bigDecimal2);
		//return initQ.toString();
	}
	/**
	 * 
	 * @param root
	 * @param objects rate fromCity toCity fcNum Q iden
	 * @auth 冯俊伟
	 * @date 2016年3月29日
	 * 作用：fc几点
	 */
	public static void createFcDocument(Element root,String ...objects){
		Element element = root.addElement("Fc");
		
		if(objects.length==4){
			element.addAttribute("rate", objects[0]);
			element.addAttribute("fromCity", objects[1]);
			element.addAttribute("toCity", objects[2]);
			element.addAttribute("iden", objects[3]);
		}else if(objects.length==5){
			element.addAttribute("rate", objects[0]);
			element.addAttribute("fromCity", objects[1]);
			element.addAttribute("toCity", objects[2]);
			element.addAttribute("fcNum",objects[3]);
			element.addAttribute("iden", objects[4]);
		}else if(objects.length==6){
			element.addAttribute("rate", objects[0]);
			element.addAttribute("fromCity", objects[1]);
			element.addAttribute("toCity", objects[2]);
			element.addAttribute("fcNum",objects[3]);
			element.addAttribute("Q",objects[4]);
			element.addAttribute("iden", objects[5]);
		}else if(objects.length==1){//解析异常
			element.addAttribute("rate", objects[0]);
		}
	}
	/**
	 * 
	 * @param lists
	 * @param i
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：object--->double
	 */
	public static Double objectToDouble(List lists,int i){
		return  Double.parseDouble((String) lists.get(i));
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：判断一个出现/x 前面的字符是不是含有.
	 * 主要考虑到以下格式的区分
	 * 
	 * [CTU, CA, KTM303.81CA, X/CTU, CA, BJS334.97NUC638.78END, ROE6.418450, X]
	   [BJS, AF, X/PAR, AF, ATL, M4058.61DL, SHA2253.50NUC6312.11END, ROE6.41845]
	 */
	public static boolean findFirstXFc(String str){
		int indexOf = str.indexOf("X/");

		String substring = str.substring(0, indexOf);
		boolean contains = substring.contains(".");

		return  contains;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月31日
	 * 作用：判断一个出现/x 后面的字符是不是含有.
	 * 
	 * [BJS, KE, X/SEL411.31KE, YVR, AC, YTO, M2222.81NUC2634.12ENDROE6.41845]

	   [BJS, AF, X/PAR, AF, ATL, M4058.61DL, SHA2253.50NUC6312.11END, ROE6.41845]3.30-test001

	  [CTU, CA, KTM303.81CA, X/CTU, CA, BJS334.97NUC638.78END, ROE6.418450, X]
	 */
	public static boolean findNexttXFc(String str){
		int indexOf = str.indexOf("X/");

		String substring = str.substring(indexOf+2, indexOf+10);
		boolean contains = substring.contains(".");

		return  contains;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：字符串是不是含有数字
	 */
	public boolean numbenInString(String str){
		Pattern p = Pattern.compile("[0-9]+?");
		boolean find = p.matcher(str).find();
		return find;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：判断有Q值  eg：Q170.39 
	 */
	public boolean exictsQ(String str){
		Pattern p = Pattern.compile("Q[0-9]{1,3}\\.\\d{1,2}");
		boolean find = p.matcher(str).find();
		return find;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：判断有Q值  
			20AUG16BJS AC X/YVR AC YTO Q BJSYTO11.22 875.01AC X/YVR AC B                    
			JS Q YTOBJS11.22 875.01NUC1772.46END ROE6.514200  
	 */
	public boolean exictsQSpace(String str){
		Pattern p = Pattern.compile(" Q ");
		boolean find = p.matcher(str).find();
		return find;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：驱除Q值得字符串
	 */
	public String newStringNotQ(String str){
		Pattern p = Pattern.compile("Q[0-9]{1,3}\\.\\d{1,2}");
		String[] split = p.split(str);
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			stringBuffer.append(split[i].trim());
			stringBuffer.append(" ");
		}
		return stringBuffer.toString();
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月30日
	 * 作用：将所有的q值取出来，放到集合中
	 */
	public List<String> numberQ(String str){
		if(exictsQSpace(str)){//20AUG16BJS AC X/YVR AC YTO Q BJSYTO11.22 875.01AC X/YVR AC BJS Q YTOBJS11.22 875.01NUC1772.46END ROE6.514200";

			String[] split = str.split(" Q ");
			List<String> lists=new ArrayList<String>();
			for (int i = 1; i < split.length; i++) {
				String string = split[i].substring(6, 12).trim();
				lists.add(string);
				
			}
			return lists;
		}else{
		List<String> list=new ArrayList<String>();
		Pattern p = Pattern.compile("Q[0-9]{1,3}\\.\\d{1,2}");
		Matcher matcher = p.matcher(str);
		while (matcher.find()) {
			matcher.start();
			matcher.end();
			String group = matcher.group();
			String substring = group.substring(1, group.length());
			list.add(substring);
		}
		return list;
		}
	}
	/**
	 * 
	 * @param lists
	 * @param qList
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月12日
	 * 作用：对于q值与Q分离的情况，驱除集合中的q值
	 */
	public List<String> removeQlistFromLists(List <String> lists,List<String> qList){
		List <String> list=new ArrayList<String>();
		
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < qList.size(); j++) {
				if(!qList.get(j).equals(lists.get(i))){
					list.add(lists.get(i));
					break;
				}
			}
		}
		return list;
		
	}
	/**
	 * 
	 * @param fcStr
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月31日
	 * 作用：如果是it票的话，确定有几fc
	 * 
	 */
	public int numberFcForIt(String fcStr){
		Pattern compile = Pattern.compile("/IT");
		Matcher matcher = compile.matcher(fcStr);
		int i=0;
		while(matcher.find()){
			i++;
		}
		return i;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年3月31日
	 * 作用：取出it票的汇率
	 */
	public String iTRate(String str){
		int indexOf = str.indexOf("ROE");
		String substring = str.substring(indexOf+3,indexOf+11);
		return substring;
	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月5日
	 * 作用：取出qte中间的身份   eg：QTE:CH/KA 取出ch来          qte:/sk version1
	 * 
	 * update:version2  2016年4月11日16:32:21 冯俊伟
	 * 对于有多个价格的话：格式为qte:  
	 * 
	 *       
	 */
	public String qteIdentity(String str){
		if(str.indexOf("/")==-1){
			return "";
		}else{
		int indexOf = str.indexOf(":");
		int indexOf2 = str.indexOf("/");
		String identity = str.substring(indexOf+1,indexOf2);
		return identity;
		}
	}
	/**
	 * 
	 * @param rows
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月5日
	 * 作用：查询整个文本qte所在的行
	 */
	public List selectQteRows(String[] rows){
		List qteRows=new ArrayList();
		for (int i = 0; i < rows.length; i++) {
			String string = rows[i];

			if((string.contains("QTE")||string.contains("qte"))&&!string.contains("*")){//*建议输入旅客年龄,具体见HELP:QTE/AGE    
				qteRows.add(i);
			}
		}
		return qteRows;

	}
	/**
	 * 
	 * @param name
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月6日
	 * 作用：查询旅客类型
	 * 	（0成人，1儿童，4婴儿）
	 */
	public int findPasTypeByName(String name){
		if(name.equalsIgnoreCase("CHD")){
			return 1;

		}else{
			return 0;
		}
	} 

	/**
	 * 
	 * @param name
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月6日
	 * 作用：查询旅客身份
	 * 	passenger_title旅客称谓(身份)
	 * (0, "普通", "adt"),stu(2, "学生", "stu"), service(3, "劳务", "lbr"), 
	 * seaman(4, "海员", "sea"), visit(5, "探亲", "vfr"), immigrant(6, "移民", "emi"),
	 *  diplomat(7, "外交官", "adt"), soldier(8, "军人","adt")  9.婴儿    10.儿童
	 */
	public int findPasIdentityByName(String name){
		if("CHD".equalsIgnoreCase(name)//儿童
				||"MISS".equalsIgnoreCase(name)
				||"MSTR".equalsIgnoreCase(name)){
			return 10;
		}else if("MR".equalsIgnoreCase(name)//普通
				||"MRS".equalsIgnoreCase(name)
				||name.length()==0
				){
			return 0;
		}else if("STU".equalsIgnoreCase(name)//学生
				||"SD".equalsIgnoreCase(name)
				){
			return 2;
		}else if("LBR".equalsIgnoreCase(name)//劳务
				||"DL".equalsIgnoreCase(name)
				){
			return 3;
		}else if("SEA".equalsIgnoreCase(name)//海员
				||"SC".equalsIgnoreCase(name)
				){
			return 4;
		}else if("VF".equalsIgnoreCase(name)//探亲
				){
			return 5;
		}else if("EMI".equalsIgnoreCase(name)//移民
				||"EM".equalsIgnoreCase(name)
				){
			return 6;
		}else{
			return 0;
		}
	} 
	/**
	 * 
	 * @param name
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月6日
	 * 作用：this is :名字的第二个字符是身份还是名
	 * 
	 * 主要是针对名字是两个字组成的情况
	 */
	public boolean judgeNameIsIdentity(String name) {
		List<String> nameList =new ArrayList<String>();
		boolean bResult = false;
		nameList.add("MR");
		nameList.add("mr");
		nameList.add("MS");
		nameList.add("ms");
		nameList.add("MRS");
		nameList.add("STU");
		nameList.add("SD");
		nameList.add("CHD");
		nameList.add("MISS");
		nameList.add("MSTR");
		nameList.add("LBR");
		nameList.add("DL");
		nameList.add("SC");
		nameList.add("SEA");
		nameList.add("VF");
		nameList.add("EM");
		nameList.add("EMI");
		for (String string : nameList) {
			if(string.equals(name))
				bResult = true;

		}
		return bResult;

	}
	/**
	 * 
	 * @param strBir
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月6日
	 * 作用：17FEB80  生成生日日期
	 */
	public String getBir(String bir){
		String substring = bir.substring(5, 7);
		String mon = bir.substring(2, 5);
		String day = bir.substring(0, 2);
		int birInt = Integer.parseInt(substring);
		int birFi=birInt+1900;//20DEC22
		int birSec=birInt+2000;
		DateTool d = new DateTool(System.currentTimeMillis());
		String yearString = d.toYearString();
		int nowInt = Integer.parseInt(yearString);

		int yearBir=0;

		if(nowInt-birFi>70){
			yearBir=birSec;
		}else{
			yearBir=birFi;
		}
		String cvtMonth = CvtMonth(mon);
		return yearBir+"-"+cvtMonth+"-"+day;

	}

	/**
	 * 
	 * @param strBir
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月6日
	 * 作用：17FEB80  生成生日日期
	 */
	public String getVal(String bir){
		String substring = bir.substring(5, 7);
		String mon = bir.substring(2, 5);
		String day = bir.substring(0, 2);
		int birInt = Integer.parseInt(substring);
		//int birFi=birInt+1900;//20DEC22
		int birSec=birInt+2000;
		String cvtMonth = CvtMonth(mon);
		return birSec+"-"+cvtMonth+"-"+day;

	}
	/**
	 * 
	 * @param strPrice
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月7日
	 * 作用：查詢出总的价格,如果有多个价格的时候会根据指令xsfsq后面的数字确定实际
	 * 		需要的是哪个价格。所以我们要取出来
	 * 
	 * 01 VHOW6CN+Y*           9152 CNY                    INCL TAX                    
	   02 VH3M6CN+Y*           7502 CNY                    INCL TAX  
	 */
	public String selTotalPrice(String []rows){
		String trim = null;
		for (int i = 0; i < rows.length; i++) {
			if(rows[i].contains("xsfsq")||rows[i].contains("XSFSQ")){
				String regEx="[^0-9]";   
				Pattern   p   =   Pattern.compile(regEx);      
				Matcher   m   =   p.matcher(rows[i]);
				trim = m.replaceAll("").trim();
				return trim;
			}
		
		}
		return trim;
	}
	/**
	 * 
	 * @param content
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月8日
	 * 作用：判断字符串是不是含有数字
	 */
	public boolean hasDigit(String content) {

		boolean flag = false;

		Pattern p = Pattern.compile(".*\\d+.*");

		Matcher m = p.matcher(content);

		if (m.matches())

			flag = true;

		return flag;

	}
	/**
	 * 
	 * @param str
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月11日
	 * 作用：取出字符串中的数字
	 */
	public String getNumberFrSt(String str){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(str);   
		String trim = m.replaceAll("").trim();
		return trim;
		
	}
	/**
	 * 
	 * @param rows
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月12日
	 * 作用：取出fc的身份标识，方便匹配政策求值
	 */
	public List <String> getIdenFc(String[] rows ){
		List <String> lists =new ArrayList<String>();
		for (int i = 0; i < rows.length; i++) {
			String string = rows[i];
			if(string.contains("qte:")||string.contains("QTE:")){
				lists.add(string.substring(string.indexOf(":")+1, string.indexOf("/")));
			}
		}
		return lists;
		
	}
	/**
	 * 
	 * @param itStr
	 * @return
	 * @auth 冯俊伟
	 * @date 2016年4月12日
	 * 作用：取出it票的汇率
	 */
	public String getItRoe(String itStr){
		String[] split = itStr.split("ROE");
		return split[1];
	}
 

}
