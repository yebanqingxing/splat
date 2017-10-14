package com.sml.sz.pubobj;

import com.sml.sz.common.persistence.DataEntity;

public class ErrInfo extends DataEntity<ErrInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer rowNum;
	
	private Integer cellNum;
	
	private String sheetName;

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getCellNum() {
		return cellNum;
	}

	public void setCellNum(Integer cellNum) {
		this.cellNum = cellNum;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
}
