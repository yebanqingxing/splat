package com.sml.sz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.fop.fo.flow.BidiOverride;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;

import com.sml.sz.file.FileUtil;
import com.sml.sz.properties.PropertiesUtil;
import com.sml.sz.sys.pnr.DateTool;
import com.sml.sz.sys.pnr.PNRXML;

import net.sf.ehcache.store.disk.ods.AATreeSet;

public class TestPNR {

	@Test
	public void test() {
		String name="y   y  y ";
		String replaceAll = name.replaceAll(" ", "1");
		String trim = name.trim();//去掉字符串的空格前缀与后缀

		String aa="aa";
		System.out.println(aa.length());

		System.out.println("7"+trim+"7");
	}

	@Test
	public void test0() {
		String name="yyyqqasda";
		int indexOf = name.indexOf("h");//从0开始
		System.out.println(indexOf);
		String fileName="F:\\pnr.txt";
		String readFile = FileUtil.readFile(fileName);
		String trim = readFile.trim();
		System.out.println(trim);
	}
	@Test
	public void test1(){
		List<Integer> myList = new LinkedList<Integer>();
		for (int i=0; i<30; i++) {
			myList.add(i+1);
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i=0; i<7; i++) { 
			int z=(int)(Math.random()*myList.size());

			result.add(myList.remove(z));
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i)+"--"+i);
		}
	}
	@Test
	public void test001(){
		String aString ="SSR DOCS HU HK1 P/CN/G47740130/CN/27MAY75/F/12JAN21/WANG/JIAN/P2";
		int indexOf = aString.indexOf(" P/");
		String substring = aString.substring(indexOf+3, aString.length()-3);
		System.out.println("护照信息"+substring);
		String[] split = substring.split("\\/");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
	}
	/**
	 * 
	 * 
	 * @auth 冯俊伟
	 * @date 2016年3月23日
	 * 作用：读取文件
	 */
	@Test
	public void test003(){
		String fileName="F:\\pnr.txt";
		File file=new File(fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test11(){
		String aa="FARE  CNY   19600";
		Pattern pattern= Pattern.compile("\\s+");
		Matcher matcher = pattern.matcher(aa);
		String replaceAll = matcher.replaceAll(" ");
		System.out.println(replaceAll);

		String bb=" TAX CNY 90CN CNY 67DE CNY 3435XT";
		String replaceAll2 = bb.replaceAll("[a-zA-Z]", "");
		System.out.println("bb"+replaceAll2);
		boolean matches = aa.startsWith("FARE");
		System.out.println(matches);

		String ccString=" FARE  CNY   19600";
		boolean startsWith = ccString.startsWith(" FARE");
		System.out.println(startsWith);

		String dd="&90&67&3435&";
		String[] split = dd.split("&");
		for (int i = 0; i < split.length; i++) {
			String string = split[i];
			System.out.println("+++"+string);
		}
		System.out.println(split.length);
	}
	@Test
	public void te(){
		String  aa="13JUN16BJS CA X/FRA2570.71LH PAR CA BJS M BJSPAR482.98NUC3053.69END ROE6.418450  ";
		//3016  /2570.71     482.983053.69 6.418450 	
		if(aa.contains("END")&&aa.contains("NUC")){
			String replaceAll = aa.replaceAll("[a-zA-Z]"," ").replaceAll("\\s+", " ");
			replaceAll=replaceAll.substring(6, replaceAll.length());
			if(replaceAll.contains("/")){
				replaceAll=replaceAll.replaceAll("/", "");
			}
			String[] split = replaceAll.split(" ");
			for (int j = 0; j < split.length; j++) {
				String string = split[j];
				if(string.length()!=0){
					double parseInt = Double.parseDouble(string);
					System.out.print("  "+parseInt);
				}


			}
		}

	}
	//29MAY16BJS KL X/AMS KL MUC
	//AF X/PAR AF BJS120NUC
	//END ROE
	/**
	 *  PEKFRA HK2   1030 1440          E T31                  
 4.  LH1046 W   TH30JUN  FRACDG HK2   1720 1830          E                      
 5.  CA876  W   FR08JUL  CDGPEK
	 * 
	 * @auth 冯俊伟
	 * @date 2016年3月28日
	 * 作用：
	 * 
	 */
	@Test
	public void test005(){
		String aa="29MAY16BJS KL X/AMS KL MUC433.90AF X/PAR AF BJS120.74NUC554.64END ROE6.418450";
		String zz="\\d+\\.\\d+";
		String[] split = aa.split(zz);
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			String replaceAll = split[i].replaceAll("\\s+", " ");
			String[] split2 = replaceAll.split(" ");
			for (int j = 0; j < split2.length; j++) {
				System.out.println("split"+j+"的值为："+split2[j]);
				if(i==0&&j==0){
					String substring = split2[0].substring(split2[0].length()-3, split2[0].length());
					stringBuffer.append(substring);
					System.out.println(stringBuffer.toString());
				}

			}
		}

	}
	/**
	 * 
	 * 
	 * @auth 冯俊伟
	 * @date 2016年3月28日
	 * 作用：一段航程
	 */
	@Test
	public void testPnr1(){
		StringBuffer stringBuffer=new StringBuffer();
		String aa="20JUL16BJS CA BKK359.89NUC359.89END ROE6.418450";
		String rll = aa.replaceAll("\\s+", " ");
		String[] split = rll.split(" ");
		for (int i = 0; i < split.length; i++) {
			if(i==0){
				String substring = split[i].substring(split[i].length()-3, split[i].length());
				stringBuffer.append(substring);
				stringBuffer.append(" ");
			}else if(i==2){
				String substring = split[i].substring(0, 3);
				stringBuffer.append(substring);
				stringBuffer.append(" ");
				String replaceAll = split[i].replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
				String[] split2 = replaceAll.split(" ");
				stringBuffer.append(split2[split2.length-1]);
				stringBuffer.append(" ");
			}else if(i==3){
				String replaceAll = split[i].replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
				stringBuffer.append(replaceAll);
			}
		}
		System.out.println(stringBuffer.toString());
	}

	/**
	 * 
	 * 
	 * @auth 冯俊伟
	 * @date 2016年3月28日
	 * 作用：2段航程 往返
	 */
	@Test
	public void testPnr2(){
		StringBuffer stringBuffer=new StringBuffer();
		//String aa="20JUL16BJS CA BKK359.89NUC359.89END ROE6.418450";//1段  2包含nuc

		String aa="20JUL16BJS CA TPE412.87CI BJS545.30NUC958.17END ROE6.418450";//2段  3包含

		String a3="20JUL16SHA CA X/BJS CA X/FRA LH MUC M833.53NUC833.53END ROE6.418450";//3
		String rll = aa.replaceAll("\\s+", " ");
		String[] split = rll.split(" ");
		String firstFromCity = null;
		String firstToCity= null;
		for (int i = 0; i < split.length; i++) {
			if(i==0){
				String substring = split[i].substring(split[i].length()-3, split[i].length());
				firstFromCity=substring;
				stringBuffer.append(substring);
				stringBuffer.append(" ");
			}else if(i==2){
				if(split[i].contains("NUC")){//包含 说明为1段
					String substring = split[i].substring(0, 3);
					stringBuffer.append(substring);
					stringBuffer.append(" ");
					String replaceAll = split[i].replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
					String[] split2 = replaceAll.split(" ");
					stringBuffer.append(split2[split2.length-1]);
					stringBuffer.append(" ");
					//"20JUL16BJS CA TPE412.87CI BJS545.30NUC958.17END ROE6.418450";
					//"20JUL16BJS CA BKK359.89NUC359.89END ROE6.418450"
				}else if(!split[i].contains("NUC")){
					//第一个fc的到达点
					String substring = split[i].substring(0, 3);
					stringBuffer.append(substring);
					stringBuffer.append(" ");
					//第一个fc的长度
					String substring1 = split[i].substring(3,split[i].length()-2);
					stringBuffer.append(substring1);
					stringBuffer.append(" ");

				}
				//"20JUL16BJS CA TPE412.87CI BJS545.30NUC958.17END ROE6.418450";
			}else if(i==3){
				if(split[i].contains("ROE")){
					String replaceAll = split[i].replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
					stringBuffer.append(replaceAll);
				}else if (split[i].contains("NUC")){
					String substring = split[i].substring(0, 3);
					firstToCity=substring;
					stringBuffer.append(substring);
					stringBuffer.append(" ");
					String replaceAll = split[i].replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
					String[] split2 = replaceAll.split(" ");
					stringBuffer.append(split2[1]);
					stringBuffer.append(" ");

				}

			}
		}
		System.out.println(stringBuffer.toString());
	}
	@Test
	public void test33(){
		String aa="20JUL16BJS CA TPE412.87CI BJS545.30NUC958.17END ROE6.418450";//2段  3包含
		String[] split = aa.split("NUC");
		String bb=split[0];
		String[] split2 = bb.split("\\.");
		System.out.println(split2.length);
	}
	@Test
	public void test009(){
		//  									

		String sss="20JUL16SHA CA X/BJS CA X/FRA LH MUC M833.53NUC833.53END ROE6 .418450 XT CNY 54OY CNY 194RD CNY 354YQ CNY 1200YR";

		int indexOf = sss.indexOf("ROE");
		String s = sss.substring(7, indexOf+13);
		System.out.println("s为"+s);


		//String s="hjkfs>1.42<jierjk>0.0<jioiure";
		String ss[]=s.split("[^-?\\d+.?\\d*]");
		for (int i = 0; i < ss.length; i++) {
			if(ss[i].matches("-?\\d+.?\\d*")) 
				System.out.println(ss[i]);
		}


	}
	private static int counter = 0;  
	@Test
	public void test008(){
		String str ="20JULX/16SHA CA X/BJS CA X/FRA LH MUC M833.53NUC833.53END ROE6 .418450 XT CNY 54OY CNY 194RD CNY 354YQ CNY 1200YR";  
		int i = stringNumbers(str);  
		System.out.println(i);

		String s="ab5cd51efg";
		String regex="[0-9]+?";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(s);
		if(m.find()==true){
			System.out.println("字符串包含数字");
		}else{
			System.out.println("字符串不包含数字");
		}
	}

	public static int stringNumbers(String str)  
	{  
		if (str.indexOf("X/")==-1)  
		{  
			return 0;  
		}  
		else if(str.indexOf("X/") != -1)  
		{  
			counter++;  
			stringNumbers(str.substring(str.indexOf("X/")+2));  
			return counter;  
		}  
		return 0;  
	} 
	@Test
	public void test006(){
		BigDecimal bd3=new BigDecimal(String.valueOf(0.00));

		BigDecimal bd4=new BigDecimal(String.valueOf(0.01));
		BigDecimal bd8=new BigDecimal(String.valueOf(0.01));
		BigDecimal add = bd3.add(bd4);
		for(int i=0;i<3;i++){
			if(i==1){
				System.out.println(1);
				continue;
			}
			System.out.println("b");
		}
		System.out.println("外");
	}

	@SuppressWarnings("unused")
	@Test
	public void testSwitch(){
		List <Integer>list=new ArrayList<Integer>();
		list.add(1);
		//list.add(2);
		//list.add(3);
		switch(list.size()){
		case 1:
			if(1!=1){
				System.out.println(123);
			}else {
				System.out.println(123.12);
			}
			break;
		case 2:
			if(true){
				System.out.println(123456);
			}
			break;
		case 3:
			if(true){
				System.out.println(123456789);
			}
			break;

		}

	}

	@Test
	public void testQ(){
		String qString="29JUL16BJS Q5.67 CX HKQ510.24KA SHA Q5.66 359.12NUC875.16END ROE6.418450";
		String zz="Q[1-9]\\.\\d{1,2}";
		Matcher matcher = Pattern.compile(zz).matcher(qString);
		while (matcher.find()) {
			matcher.start();
			matcher.end();
			String group = matcher.group();
			String substring = group.substring(1, group.length());
			System.out.println(substring);
		}

	}

	@Test
	public void testQ1(){//29JUL16BJS CX HKQ510.24KA SHA 359.12NUC875.16END ROE6.418450
		String qString="   29JUL16BJS Q5.67 CX HKQ510.24KA SHA Q5.66 359.12NUC875.16END ROE6.418450";
		String zz="Q[1-9]\\.\\d{1,2}";
		Pattern compile = Pattern.compile(zz);
		Matcher matcher = compile.matcher(qString);
		/*	String[] split = compile.split(qString);
		StringBuilder stringBuilder=new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			stringBuilder.append(split[i].trim());
			stringBuilder.append(" ");
		}
		System.out.println(stringBuilder.toString());*/

		List<String> list=new ArrayList<String>();
		while (matcher.find()) {
			matcher.start();
			matcher.end();
			String group = matcher.group();
			String substring = group.substring(1, group.length());
			list.add(substring);
		}
		System.out.println(list.size());

	}
	/**
	 * 
	 * 
	 * @auth 冯俊伟
	 * @date 2016年3月31日
	 * 作用： 批量启动tomcat
	 */
	@Test
	public void testStart(){
		String tomFather="E:\\tom\\";
		File files=new File(tomFather);
		List <File> listFiles=new ArrayList<File>();
		if(files.exists()){
			File[] listFiles2 = files.listFiles();
			for (int i = 0; i < listFiles2.length; i++) {
				File file = listFiles2[i];
				if(file.isDirectory()){
					listFiles.add(file);
				}
			}
		}
		for (int i = 0; i < listFiles.size(); i++) {
			String name = listFiles.get(i).getName();
			String cmdAddress="cmd /c  E:\\tom\\"+name+"\\bin\\startup.bat";
			try {
				Process process = Runtime.getRuntime().exec(cmdAddress);
				/*	final InputStream in = process.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				StringBuilder buf = new StringBuilder();
				String line = null;
				while((line = br.readLine()) != null)
				buf.append(line);*/
				System.out.println("第"+i+"次启动成功！！！" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("第"+i+"次启动失败！！！");
				e.printStackTrace();
			} // 调用外部程序
		}




	}

	@Test
	public void testShutDown(){
		String tomFather="E:\\tom\\";
		File files=new File(tomFather);
		List <File> listFiles=new ArrayList<File>();
		if(files.exists()){
			File[] listFiles2 = files.listFiles();
			for (int i = 0; i < listFiles2.length; i++) {
				File file = listFiles2[i];
				if(file.isDirectory()){
					listFiles.add(file);
				}
			}
		}
		for (int i = 0; i < listFiles.size(); i++) {
			String name = listFiles.get(i).getName();
			String cmdAddress="cmd /c  E:\\tom\\"+name+"\\bin\\shutdown.bat";
			try {
				Process process = Runtime.getRuntime().exec(cmdAddress);
				/*	final InputStream in = process.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				StringBuilder buf = new StringBuilder();
				String line = null;
				while((line = br.readLine()) != null)
				buf.append(line);*/
				System.out.println("第"+i+"次启动成功！！！" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("第"+i+"次启动失败！！！");
				e.printStackTrace();
			} // 调用外部程序
		}




	}
	@Test
	public void test015(){
		String fcLast="BKK 3U CTU197.58NUC197.58END ROE-35.933000X";

		String[] splitSpace = fcLast.split(" ");
		String ss[]=fcLast.split("[^-?\\d+.?\\d*]");
		List lists=new ArrayList();//[1090.60, 475.19, 1565.79, 6.418450]
		//[SHA, CA, X/BJS, CA, X/FRA, LH, MUC, M833.53NUC833.53END, 
		// 取出小数 含有nuc  roe  nuc来确定几段fc
		for (int p = 0; p < ss.length; p++) {//
			if(ss[p].matches("\\d+\\.\\d+$|-\\d+\\.\\d+$")) 
				lists.add(ss[p]);
		}
		int size = lists.size();
		//System.out.println(size);


		String bb="05APR16BJS UA X/SFO UA LAX M/IT UA X/SFO UA BJS M/IT END ROETU197.58NUC19";
		String[] split = bb.split("/IT[^-?\\d+.?\\d*]");
		System.out.println(split.length);
	}
	@Test
	public void ff(){
		Pattern compile = Pattern.compile("/IT");
		Matcher matcher = compile.matcher("08JUN16SHA UA LAX M/IT UA SHA M/IT END ROE6.418450");
		int i=0;
		List list =new ArrayList();
		while(matcher.find()){
			i++;
		}
		System.out.println(i);
	}

	@Test
	public void doublejian(){
		String a="1.25";
		String b="2.36";
		//double c=b-a;
		BigDecimal bigDecimal1 = new BigDecimal(a);
		BigDecimal bigDecimal2 = new BigDecimal(b);
		BigDecimal c=bigDecimal2.subtract(bigDecimal1);
		System.out.println(c.toString());

		int d=54;
		int f=8;
		if(f<=d){
			System.out.println(1);
		}else if(f<d){
			System.out.println(2);
		}
	}

	@Test
	public void test0023(){//1257
		String aa="QTE:/KA";//QTE:/KA
		int indexOf = aa.indexOf(":");
		int indexOf2 = aa.indexOf("/");
		String substring = aa.substring(indexOf+1,indexOf2);
		System.out.println(substring);

		String [] ar =new String [3] ;
		ar[0]="2";
		ar[1]="21";
		ar[2]="22";
		int j = 0;
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			if(string.contains("21")){
				j=i;
			}
			System.out.println(j);
		}

	}

	@Test
	public void test111(){
		//17FEB80
		/*String bir="17FEB80";
		String substring = bir.substring(5, 7);
		String mon = bir.substring(2, 5);
		String day = bir.substring(0, 2);
		int birInt = Integer.parseInt(substring);
		int birFi=birInt+1900;
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
		if ("JAN".equals(mon))
			mon="01";
		else if ("FEB".equals(mon))
			mon="02";
		else if ("MAR".equals(mon))
			mon= "03";
		else if ("APR".equals(mon))
			mon= "04";
		else if ("MAY".equals(mon))
			mon= "05";
		else if ("JUN".equals(mon))
			mon= "06";
		else if ("JUL".equals(mon))
			mon= "07";
		else if ("AUG".equals(mon))
			mon= "08";
		else if ("SEP".equals(mon))
			mon= "9";
		else if ("OCT".equals(mon))
			mon= "10";
		else if ("NOV".equals(mon))
			mon= "11";
		else if ("DEC".equals(mon))
			mon= "12";
		 */
		String a="p2";
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(a);   
		System.out.println( m.replaceAll("").trim());

		//	System.out.println(yearBir+"-"+mon+"-"+day);

		//20DEC22



	}
	@Test


	public void  exictsQSpace(){
		String str="20AUG16BJS AC X/YVR AC YTO Q BJSYTO11.22 875.01AC X/YVR AC BJS Q YTOBJS11.22 875.01NUC1772.46END ROE6.514200";
		String[] split = str.split(" Q ");
		List<String> lists=new ArrayList<String>();
		for (int i = 1; i < split.length; i++) {
			String string = split[i].substring(6, 12).trim();
			lists.add(string);

		}
		if(true){
			System.out.println(1);
		}else if(true){
			System.out.println(2);
		}
		Pattern p = Pattern.compile(" Q ");
		boolean find = p.matcher(str).find();
		System.out.println(find);
	}

	@Test
	public void test0023s(){//1257
		String str="BJS AA X/CHI AA MSY AA X/E/DFW AA YTO 10M487.15AA X/CHI AA BJS M442.87 1S76.75NUC1029.21END ROE6.51 ";//QTE:/KA
		List<String> list=new ArrayList<String>();
		Pattern p = Pattern.compile("[1-5]S[0-9]{1,3}\\.\\d{1,2}");
		Matcher matcher = p.matcher(str);
		boolean find = matcher.find();
		String cc=" 5AA X/CHI Q11.22AA BJS M442.87 1S76.75NUC1029.21END ROE6.514       ";
		String bb = cc.trim();
		String substring = bb.trim().substring(bb.length()-4, bb.length());
		System.out.println(substring);
		int a=3;
		int b=9;
		double c=a/b;
		System.out.println(c);

		String cd=" 09MAY16URC CZ<FE>LED107.45/-MOW CZ URC107.45NUC214.90END ROE   ";
		if(cd.contains("/-"))
			System.out.println(123);



	}
	@Test
	public void testFolder(){
		String folderName="F:\\pnr\\456";
		boolean hasSonFolder = FileUtil.hasSonFolder(folderName);
		if(hasSonFolder){
			List<File> folders = FileUtil.getFolders(folderName);
			for (int i = 0; i < folders.size(); i++) {
				System.out.println(folders.get(i).getAbsolutePath());

			}
		}

	}
	@Test
	public void testFolderN(){
		String folderName="F:\\pnr\\456";
		
		File file = new File(folderName);
		
		File[] listFiles = file.listFiles();
		
		List <File> fileLists=new ArrayList<File>();
		
		/*
		if(file.exists()){
			File[] listFiles = file.listFiles();
			if(listFiles!=null&&listFiles.length>0){
				for (int i = 0; i < listFiles.length; i++) {
					if(listFiles[i].isDirectory())
						fileLists.add(listFiles[i]);
				}
			}
		}
		System.out.println(fileLists.size());*/
		
		System.out.println("总共的数目为："+listFiles.length);
		
		
		String aaStringa="asd无适用的运价 ";
		boolean contains = aaStringa.contains("无适用的运价 ");
		System.out.println(contains);
		
	}
@Test
public void test999(){
/*	String bb="TAX   CNY      37AY CNY      32US CNY      55XT ";
	String aa = bb.replaceAll("\\s+", " ");
	String[] split = aa.split(" ");
	String taxUn=null;
	for (int i = 0; i < split.length; i++) {
		if(split[i].equalsIgnoreCase("tax"))
			taxUn=split[i+1];
			
	}
	System.out.println(taxUn);
	  String jsonStr = "{\"Name\":\"\u5e0c\u4e9a\",\"Age\":20}"; */ 
    //  Object obj = JSon.parse(jsonStr);  
     // System.out.println(obj);
	/*  Properties propertiesValue = PropertiesUtil.getPropertiesValue("pnr.properties");
	  String object =(String) propertiesValue.get("bjds");
	  System.out.println(object);*/
	  
	/*  String input = "03MAY16SHA CA X/MIL CA LIS M2486.87CA X/FRA851.89CA SHA2709.46NUC6048.22END ROE6.514200XT CNY 66HB CNY 107IT CNY 9MJ CNY 24VT CNY 28PT";
	  String target = "X/";
	    int count =0, start =0;
        while((start=input.indexOf(target,start))>=0){
            start += target.length();
            count ++;
        }*/
	
	String str="2.  KA805  S1  FR01JUL  PVGHKG HK1   1430 1715      SEAME";
	Pattern pattern = Pattern.compile("^\\d*\\..\\s+");
	Matcher m = pattern.matcher(str);
	String substring = str.substring(str.indexOf(".")+1);
	int indexOf = substring.indexOf(" ");
	System.out.println(indexOf);
	//ClassLoader
	  
}

}
