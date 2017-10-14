package com.sml.sz.sys.pnr;

/**
 * 票号项
 * @author shenxj
 * @date 2015-4-15
 */
public class Ticket {

	private String no;//票号
	private String psrid;//对应用户id
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPsrid() {
		return psrid;
	}
	public void setPsrid(String psrid) {
		this.psrid = psrid;
	}
}
