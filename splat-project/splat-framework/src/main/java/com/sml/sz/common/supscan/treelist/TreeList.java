/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.common.supscan.treelist;

import java.util.List;

import com.google.common.collect.Lists;
import com.sml.sz.common.supscan.annotation.common.fonts.SupFont;
import com.sml.sz.common.supscan.annotation.treelist.SupTreeList;
import com.sml.sz.common.supscan.common.Common;
import com.sml.sz.common.supscan.common.fonts.Font;
import com.sml.sz.common.supscan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正TreeList
 * @author splat
 * @version 2013-11-04
 */
@XStreamAlias("TreeList")
public class TreeList extends Common {

	/**
	 * 列集合
	 */
	@XStreamAlias("Cols")
	private List<Object> cols;

	public TreeList() {
		super();
	}
	
	public TreeList(Properties properties) {
		this();
		this.properties = properties;
	}
	
	public TreeList(SupTreeList supTreeList) {
		this();
		if (supTreeList != null){
			if (supTreeList.properties() != null){
				this.properties = new Properties(supTreeList.properties());
			}
			if (supTreeList.fonts() != null){
				for (SupFont supFont : supTreeList.fonts()){
					if (this.fonts == null){
						this.fonts = Lists.newArrayList();
					}
					this.fonts.add(new Font(supFont));
				}
			}
		}
	}
	
	public List<Object> getCols() {
		if (cols == null){
			cols = Lists.newArrayList();
		}
		return cols;
	}

	public void setCols(List<Object> cols) {
		this.cols = cols;
	}

}
