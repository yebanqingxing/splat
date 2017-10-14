package com.sml.sz.sys.pnr;



import java.util.ArrayList;
import java.util.List;

import com.sml.sz.common.persistence.DataEntity;




/**
 * RT 文本解析对象
 * @author shenxj
 * @date 2015-4-15
 * update1：2016年3月24日14:37:22 冯俊伟
 *  	       修改新增的内容：增加了有关qte的内容
 *  update2:2016年4月5日09:55:24 冯俊伟
 *  		增加fc的相关对象
 *  version3：2016年4月7日00:50:58 冯俊伟
 *  		一个文本有多个qte对象，将qte转化为集合来存放
 */
public class Rt extends DataEntity<Rt> {
	public String beginMark;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private String eterm;
	private String pnr;
	private String b_pnr;
	private String officecode;
	private String ct;
	private Tktl tktl;
	private List<Passenger> passenger_list;  //旅客
	private List<Route> route_list;         //航段信息
	private List<Ticket> ticket_list; //票号
	private List<Rmk> rmk_list;       //备注
	private List<Fn> fn_list;
	private List<Fp> fp_list;
	private List<Osi> osi_list;
	private List<Ssr> ssr_list;
	private String PNRContent;       //pnr信息
	private String carrier;//承运人
	private String time;//时间
	private String clazz;//舱位
	private String itinerary;//行程
	private String date;//日期
	private String errorInfo; //错误信息
	private String travelType;//行程类型
	private String comput;//前台输入的出票航空公司
	private List<String> flyCompany;//航班
	private List<String> flyNum;//航号
	private List<Route> zzRoutes =new ArrayList<Route>(); //中转含在一起  ，航段信息中包含的是中转站分开的

	private String pnrType;//Pnr类型
	private List<Qte> qte;//有关qte的内容
	
	private List<Fc> fc;
	
	private List polices;//匹配的政策信息
	private List<PersonTypeInfo> personTypeInfoList = new ArrayList<PersonTypeInfo>();
	
	private String throwBs;//*无适用的运价     出现这种标志
	
	private String basePrice;//基础票价
	
	
	private String exchangeRate;//汇率
	
	
	
	
	
	
	


	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getPnrType() {
		return pnrType;
	}
	public void setPnrType(String pnrType) {
		this.pnrType = pnrType;
	}

	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getThrowBs() {
		return throwBs;
	}
	public void setThrowBs(String throwBs) {
		this.throwBs = throwBs;
	}
	public List<PersonTypeInfo> getPersonTypeInfoList() {
		return personTypeInfoList;
	}
	public void setPersonTypeInfoList(List<PersonTypeInfo> personTypeInfoList) {
		this.personTypeInfoList = personTypeInfoList;
	}
	public List getPolices() {
		return polices;
	}
	public void setPolices(List polices) {
		this.polices = polices;
	}
	public String getComput() {
		return comput;
	}
	public void setComput(String comput) {
		this.comput = comput;
	}
	public List<Fc> getFc() {
		return fc;
	}
	public void setFc(List<Fc> fc) {
		this.fc = fc;
	}

	
	public List<Qte> getQte() {
		return qte;
	}
	public void setQte(List<Qte> qte) {
		this.qte = qte;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public List<Route> getZzRoutes() {
		return zzRoutes;
	}
	public void setZzRoutes(List<Route> zzRoutes) {
		this.zzRoutes = zzRoutes;
	}
	public List<String> getFlyCompany() {
		return flyCompany;
	}
	public void setFlyCompany(List<String> flyCompany) {
		this.flyCompany = flyCompany;
	}
	public List<String> getFlyNum() {
		return flyNum;
	}
	public void setFlyNum(List<String> flyNum) {
		this.flyNum = flyNum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEterm() {
		return eterm;
	}
	public void setEterm(String eterm) {
		this.eterm = eterm;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getB_pnr() {
		return b_pnr;
	}
	public void setB_pnr(String bPnr) {
		b_pnr = bPnr;
	}
	public String getOfficecode() {
		return officecode;
	}
	public void setOfficecode(String officecode) {
		this.officecode = officecode;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public Tktl getTktl() {
		return tktl;
	}
	public void setTktl(Tktl tktl) {
		this.tktl = tktl;
	}
	public List<Passenger> getPassenger_list() {
		return passenger_list;
	}
	public void setPassenger_list(List<Passenger> passengerList) {
		passenger_list = passengerList;
	}
	public List<Route> getRoute_list() {
		return route_list;
	}
	public void setRoute_list(List<Route> routeList) {
		route_list = routeList;
	}
	public List<Ticket> getTicket_list() {
		return ticket_list;
	}
	public void setTicket_list(List<Ticket> ticketList) {
		ticket_list = ticketList;
	}
	public List<Rmk> getRmk_list() {
		return rmk_list;
	}
	public void setRmk_list(List<Rmk> rmkList) {
		rmk_list = rmkList;
	}
	public List<Fn> getFn_list() {
		return fn_list;
	}
	public void setFn_list(List<Fn> fnList) {
		fn_list = fnList;
	}
	public List<Fp> getFp_list() {
		return fp_list;
	}
	public void setFp_list(List<Fp> fpList) {
		fp_list = fpList;
	}
	public List<Ssr> getSsr_list() {
		return ssr_list;
	}
	public void setSsr_list(List<Ssr> ssrList) {
		ssr_list = ssrList;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getItinerary() {
		return itinerary;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getPNRContent() {
		return PNRContent;
	}
	public void setPNRContent(String pNRContent) {
		PNRContent = pNRContent;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public List<Osi> getOsi_list() {
		return osi_list;
	}
	
	public void setOsi_list(List<Osi> osiList) {
		osi_list = osiList;
	}
	@Override
	public String toString() {
		return "Rt [beginMark=" + beginMark + ", value=" + value + ", eterm=" + eterm + ", pnr=" + pnr + ", b_pnr="
				+ b_pnr + ", officecode=" + officecode + ", ct=" + ct + ", tktl=" + tktl + ", passenger_list="
				+ passenger_list + ", route_list=" + route_list + ", ticket_list=" + ticket_list + ", rmk_list="
				+ rmk_list + ", fn_list=" + fn_list + ", fp_list=" + fp_list + ", osi_list=" + osi_list + ", ssr_list="
				+ ssr_list + ", PNRContent=" + PNRContent + ", carrier=" + carrier + ", time=" + time + ", clazz="
				+ clazz + ", itinerary=" + itinerary + ", date=" + date + ", errorInfo=" + errorInfo + ", travelType="
				+ travelType + ", comput=" + comput + ", flyCompany=" + flyCompany + ", flyNum=" + flyNum
				+ ", zzRoutes=" + zzRoutes + ", qte=" + qte + ", fc=" + fc + ", polices=" + polices
				+ ", personTypeInfoList=" + personTypeInfoList + ", throwBs=" + throwBs + ", basePrice=" + basePrice
				+ ", exchangeRate=" + exchangeRate + "]";
	}
	



	
}
