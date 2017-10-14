package com.sml.sz.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sml.sz.StringUtils;

public class ReadInternationlAirPort {
	
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

	private String errorInfo;

	/**
	 * 
	 * @描述：得到总行数
	 * @参数：@return
	 * 
	 * @返回值：int
	 */

	public int getTotalRows() {

		return totalRows;

	}

	/**
	 * 
	 * @描述：得到总列数
	 * @参数：@return
	 * 
	 * @返回值：int
	 */

	public int getTotalCells() {

		return totalCells;

	}

	/**
	 * 
	 * @描述：得到错误信息
	 * 
	 * @参数：@return
	 * 
	 * @返回值：String
	 */

	public String getErrorInfo() {

		return errorInfo;

	}

	/**
	 * 
	 * @描述：验证excel文件
	 * 
	 * @参数：@param filePath　文件完整路径
	 * 
	 * @参数：@return
	 * 
	 * @返回值：boolean
	 */

	public boolean validateExcel(String filePath) {

		/** 检查文件名是否为空或者是否是Excel格式的文件 */

		if (filePath == null
				|| !(WDWUtil.isExcel2003(filePath) || WDWUtil
						.isExcel2007(filePath))) {

			errorInfo = "文件名不是excel格式";

			return false;

		}
		/** 检查文件是否存在 */

		/*File file = new File(filePath);

		if (file == null || !file.exists()) {

			errorInfo = "文件不存在";

			return false;

		}*/

		return true;

	}

	/**
	 * 
	 * @描述：根据文件名读取excel文件
	 * 
	 * @参数：@param filePath 文件完整路径
	 * 
	 * @参数：@return
	 * 
	 * @返回值：List
	 */

	public Map<String, String> read(String filePath,List<String> keys,boolean isAll) {
		// String filePath =
		// ServletActionContext.getServletContext().getRealPath(path);
		// filePath=path;
		// System.out.println(filePath);
		Map<String, String> dataLst = new HashMap<String, String>();

		InputStream is = null;

		try {
			if(!isAll && (keys == null || keys.size() <= 0)){
				return dataLst;
			}
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

			dataLst = read(is, isExcel2003,keys,isAll);

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
	 * 
	 * @参数：@param inputStream
	 * 
	 * @参数：@param isExcel2003
	 * 
	 * @参数：@return
	 * 
	 * @返回值：List
	 */

	public Map<String, String> read(InputStream inputStream,
			boolean isExcel2003,List<String> keys,boolean isAll) {

		Map<String, String> dataLst = null;

		try {

			/** 根据版本选择创建Workbook的方式 */

			Workbook wb = null;

			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read0(wb,keys,isAll);

		} catch (IOException e) {

			e.printStackTrace();

		}

		return dataLst;

	}

	private Map<String, String> read0(Workbook wb,List<String> keys,boolean isAll) {
		Map<String, String> map = new HashMap<String, String>();

		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);

		/** 得到Excel的行数 */

		int totalRows = sheet.getPhysicalNumberOfRows();
		/** 循环Excel的行 */
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);

			if (row == null) {

				continue;

			}
			/** 循环Excel的列 */

			// row.getCell(isNull).setCellType(Cell.CELL_TYPE_STRING);
			
			if (row.getCell(0) != null) {
					
				if (!StringUtils.isBlank(stype(row.getCell(0)))) {
					Cell cell = row.getCell(0);
					Cell cell1 = row.getCell(1);
					String tempKey = stype(cell);
					String tempValue = stype(cell1);
					if(isAll){
						map.put(tempKey, tempValue);
					}else{
						if(keys != null && keys.size() > 0){
							for(String key : keys){
								if(tempKey != null && key.equals(tempKey.trim())){
									map.put(tempKey, tempValue);
									break;
								}
							}
							if(map != null && map.size() == keys.size()){
								//当获取到相应key的值时跳出读取excel
								break;
							}
						}else{
							break;
						}
					}
					
				}
			}
			
		}
		return map;

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
	 * 
	 * @描述：main测试方法
	 * 
	 * @参数：@param args
	 * 
	 * @参数：@throws Exception
	 * 
	 * @返回值：void
	 */
	public static void main(String[] args) throws Exception {
		ReadInternationlAirPort poi = new ReadInternationlAirPort();
		// 获取解析后的集合
		poi.redRow=1;
		Map<String, String> lists = poi.read("d:/国际机场名称及代码.xls",null,true);
		Map<String, String> hearders = new HashMap<String, String>();
		if(lists != null && hearders != null){
			
		}
	}

}
