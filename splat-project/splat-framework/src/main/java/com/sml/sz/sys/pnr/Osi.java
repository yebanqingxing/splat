package com.sml.sz.sys.pnr;

/**
 * rt 返回xml，中的 osi项
 * <osis>
 * <osi id="">text</osi>
 * <osi id="">text</osi>
 * </osis>
 * @author shenxj
 *
 */
public class Osi {
	private String id;
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
