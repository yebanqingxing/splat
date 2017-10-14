package com.sml.sz.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sml.sz.StringUtils;


public class ExcelAnalysis {
	/**
	 * 第几行读取
	 */
	public int redRow=1;

	/**
	 * 判断读取条件，以单元格位置起止 -1 代表不判断
	 */
	public int isNull = 0;

	/** 总行数 */
	private int totalRows = 0;

	/** 总列数 */
	private int totalCells = 0;

	/** 错误信息 */
	private static String errorInfo;

	/** 构造方法 */
	public ExcelAnalysis() {
	}

	/**
	 * @描述：得到总行数
	 * @参数：@return
	 * @返回值：int
	 */

	public int getTotalRows() {
		return totalRows;
	}
	/**
	 * @描述：得到总列数
	 * @参数：@return
	 * @返回值：int
	 */

	public int getTotalCells() {
		return totalCells;
	}

	/**
	 * @描述：得到错误信息
	 * @参数：@return
	 * @返回值：String
	 */

	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @描述：验证excel文件
	 * @参数：@param filePath　文件完整路径
	 * @参数：@return
	 * @返回值：boolean
	 */

	public static boolean validateExcel(String filePath) {
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null
				|| !(WDWUtil.isExcel2003(filePath) || WDWUtil
						.isExcel2007(filePath))) {
			errorInfo = "文件名不是excel格式";
			return false;

		}
		System.out.println(filePath);
		/** 检查文件是否存在 */
		/*File file = new File(filePath);

		if (file == null || !file.exists()) {

			errorInfo = "文件不存在";

			return false;

		}*/

		return true;

	}

	/**
	 * @描述：根据文件名读取excel文件
	 * @参数：@param filePath 文件完整路径
	 * @参数：@return
	 * @返回值：List
	 */

	public List<Map<String, String>> read(String filePath) {
		// String filePath =
		// ServletActionContext.getServletContext().getRealPath(path);
		// filePath=path;
		// System.out.println(filePath);
		List<Map<String, String>> dataLst = new ArrayList<Map<String, String>>();
		InputStream is = null;
		try {
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)) {
				System.out.println(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(filePath)) {
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		/** 返回最后读取的结果 */
		return dataLst;
	}

	
	public List<Map<String, String>> read(String uploadFileName,File file) {
		// String filePath =
		// ServletActionContext.getServletContext().getRealPath(path);
		// filePath=path;
		// System.out.println(filePath);
		List<Map<String, String>> dataLst = new ArrayList<Map<String, String>>();
		InputStream is = null;
		try {
			/** 验证文件是否合法 */
			if (!validateExcel(uploadFileName)) {
				System.out.println(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(uploadFileName)) {
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			//File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		/** 返回最后读取的结果 */
		return dataLst;

	}
	/**
	 * 
	 * @描述：根据流读取Excel文件
	 * @参数：@param inputStream
	 * @参数：@param isExcel2003
	 * @参数：@return
	 * @返回值：List
	 */

	public List<Map<String, String>> read(InputStream inputStream,
			boolean isExcel2003) {
		List<Map<String, String>> dataLst = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataLst;
	}

	/**
	 * @描述：读取数据
	 * @参数：@param Workbook
	 * @参数：@return
	 * @返回值：List<List<String>>
	 */
	private List<Map<String, String>> read(Workbook wb) {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> dataLst = new ArrayList<Map<String, String>>();

		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		this.totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
		if (this.totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("总行" + totalRows);
		}

		/** 循环Excel的行 */
		for (int r = redRow; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			map = new HashMap<String, String>();
			/** 循环Excel的列 */
			// row.getCell(isNull).setCellType(Cell.CELL_TYPE_STRING);
			if (isNull >= 0) {
				if (row.getCell(isNull) != null) {
					//row.getCell(isNull).setCellType(Cell.CELL_TYPE_STRING);
					if (!StringUtils.isBlank(stype(row.getCell(isNull)))) {
						for (int c = 0; c < this.getTotalCells(); c++) {
							Cell cell = row.getCell(c);
							if (null != cell) {
								//cell.setCellType(Cell.CELL_TYPE_STRING);
								if (sheet.getRow(0).getCell(c) != null
										&& StringUtils.isNotBlank(sheet
												.getRow(0).getCell(c)
												.getStringCellValue()))
									map.put(sheet.getRow(0).getCell(c)
											.getStringCellValue().trim().toUpperCase(),
											stype(cell));
							}
						}
						dataLst.add(map);
					}
				}
			} else {
				for (int c = 0; c < this.getTotalCells(); c++) {
					Cell cell = row.getCell(c);
					if (null != cell) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if (sheet.getRow(0).getCell(c) != null
								&& StringUtils.isNotBlank(sheet.getRow(0)
										.getCell(c).getStringCellValue()))
							map.put(sheet.getRow(0).getCell(c)
									.getStringCellValue().trim().toUpperCase(),
									stype(cell));
					}
				}

				dataLst.add(map);
			}
		}
		return dataLst;

	}
	/**
	 * 
	 * @描述：根据文件名读取excel文件
	 * @参数：@param filePath 文件完整路径
	 * @参数：@return
	 * @返回值：List
	 */

	public List<List<String>> readList(String filePath)
	{
		List<List<String>> dataLst = new ArrayList<List<String>>();
		if(WDWUtil.isCVS(filePath)){
			dataLst=readCSVFile(filePath);
		}else{
		InputStream is = null;
		try
		{
			/** 验证文件是否合法 */
			if (!validateExcel(filePath))
			{
				System.out.println(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(filePath))
			{
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = readList(is, isExcel2003);
			is.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (is != null){
				try{
					is.close();
				}
				catch (IOException e){
					is = null;
					e.printStackTrace();
				}
			}

		}
		}
		/** 返回最后读取的结果 */
		return dataLst;

	}

	/**
	 * 
	 * @描述：根据流读取Excel文件
	 * @参数：@param inputStream
	 * @参数：@param isExcel2003
	 * @参数：@return
	 * @返回值：List
	 */

	public List<List<String>> readList(InputStream inputStream, boolean isExcel2003)
	{

		List<List<String>> dataLst = null;

		try
		{

			/** 根据版本选择创建Workbook的方式 */

			Workbook wb = null;

			if (isExcel2003)
			{
				wb = new HSSFWorkbook(inputStream);
			}
			else
			{
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = readList(wb);

		}
		catch (IOException e)
		{

			e.printStackTrace();

		}

		return dataLst;

	}

	/**
	 * @描述：读取数据
	 * @参数：@param Workbook
	 * @参数：@return
	 * @返回值：List<List<String>>
	 */
	private List<List<String>> readList(Workbook wb)
	{
		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		this.totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
		if (this.totalRows >= 1 && sheet.getRow(redRow) != null)
		{
			this.totalCells = 44;//sheet.getRow(redRow).getPhysicalNumberOfCells();
		}
		System.out.println("总数"+this.totalCells);
		/** 循环Excel的行 */
		for (int r = redRow; r < this.totalRows; r++)
		{
			Row row = sheet.getRow(r);
			if (row == null)
			{
				continue;
			}
			List<String> rowLst = new ArrayList<String>();
			/** 循环Excel的列 */
			if (isNull >= 0) {
				if (row.getCell(isNull) != null&&!"null".equals(row.getCell(isNull))) {
					
					for (int c = 0; c < this.getTotalCells(); c++)
					{
						Cell cell = row.getCell(c);
						if (null != cell) {
							rowLst.add(stype(cell));
						}else{
							rowLst.add("");
						}
					}
					dataLst.add(rowLst);
				}
			}else{
				for (int c = 0; c < this.getTotalCells(); c++)
				{
					Cell cell = row.getCell(c);
					if (null != cell) {
						
						rowLst.add(stype(cell));
					}
				}
				dataLst.add(rowLst);
			}
			/** 保存第r行的第c列 */
			
		}
		return dataLst;

	}

	
	public List<List<String>> readCSVFile(String path) {  
    	InputStreamReader fr = null;  
        BufferedReader br = null; 
        List<List<String>> listFile = new ArrayList<List<String>>(); 
        try {
    	fr = new InputStreamReader(new FileInputStream(path),"gbk"); 
        br = new BufferedReader(fr);  
        String rec = null;// 一行  
        String str;// 一个单元格  
          
            // 读取一行  
        	boolean start=false;
        	Map<String,String> map=new HashMap<String, String>();
            while ((rec = br.readLine()) != null) {
            	
            	if(rec.indexOf("流水号")>=0){
            		start=true;
            	}
            	if(!start){
            		continue;
            	}
            	if(rec.indexOf("账务明细列表")>0){
            		start=false;
            	}
            	if(!start){
            		continue;
            	}
                Pattern pCells = Pattern  
                        .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");  
                Matcher mCells = pCells.matcher(rec);  
                if(rec.indexOf("采购商收款")>0){
                	//System.out.println(rec+"----"+mCells.find());
                }
                
                List<String> cells = new ArrayList<String>();// 每行记录一个list  
                // 读取每个单元格  
                int index=0;  
                while (mCells.find()) {  
                    str = mCells.group();  
                    str = str.replaceAll(  
                            "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");  
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    cells.add(str);  
                    index=mCells.end(); 
                }
                cells.add(rec.substring(index));
               // System.out.println(cells.size());
               listFile.add(cells); 
               
                 
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
				if (fr != null) {  
				    fr.close();  
				}  
				if (br != null) {  
				    br.close();  
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
        return listFile;  
    }  
	
	private static String stype(Cell cell){
		String rString="";
		if(HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()){
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				SimpleDateFormat formatDate2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
					Date d = cell.getDateCellValue();      
					 rString=formatDate2.format(d);
				// System.out.println(rString);
				 return rString;
			}
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		rString=cell.getStringCellValue();
		return rString;
	}
	/**
	 * @描述：main测试方法
	 * @参数：@param args
	 * @参数：@throws Exception
	 * @返回值：void
	 */
	public static void main(String[] args) throws Exception {
		ExcelAnalysis poi = new ExcelAnalysis();
		// 获取解析后的集合
		poi.redRow=1;
		poi.isNull=3;
		List<List<String>> list=poi.readList("d://HU-MU2.xlsx");
		for (List<String> list2 : list) {
			System.out.println(list2);
		}
		System.out.println(list.size());
		// System.out.println(lists.size());
		
	}

}

/**
 * 
 * @描述：工具类
 */

class WDWUtil {

	/**
	 * @描述：是否是2003的excel，返回true是2003
	 * @参数：@param filePath　文件完整路径
	 * @参数：@return
	 * @返回值：boolean
	 */

	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	/**
	 * @描述：是否是2007的excel，返回true是2007
	 * @参数：@param filePath　文件完整路径
	 * @参数：@return
	 * @返回值：boolean
	 */

	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");

	}
	public static boolean isCVS(String filePath) {
		return filePath.matches("^.+\\.(?i)(csv)$");

	}
}
