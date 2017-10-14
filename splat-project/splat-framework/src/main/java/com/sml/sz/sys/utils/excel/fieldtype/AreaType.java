/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.sys.utils.excel.fieldtype;

import com.sml.sz.StringUtils;
import com.sml.sz.sys.entity.Area;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author splat
 * @version 2013-03-10
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
