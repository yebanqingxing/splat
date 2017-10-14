package com.sml.sz.general.entity;
/**
 * 
 * @author plat11
 * 中间对象：与总则对比后返回来的对象
 */
public class CompareGen {
	private String startCity;//起点
	private String arriveCity;//终点
	private CompareGen compareGenObj;
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public CompareGen getCompareGenObj() {
		return compareGenObj;
	}
	public void setCompareGenObj(CompareGen compareGenObj) {
		this.compareGenObj = compareGenObj;
	}
	
}
