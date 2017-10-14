package com.sml.sz;

import com.sml.sz.airline.entity.Airline;

public class Test {
	public static void main(String[] args) {
		// [AF111/AF7181/AF116]
		String carrier="AF111/AF7181/AF116";
		String[] split = carrier.split("\\/");
		System.out.println(split);
	/*	for (int i = 0; i < split.length; i++) {
			Airline airByAirCode = airlineFacade.getAirByAirCode(split[i]);
			System.out.println(airByAirCode.getAirlineNameCn());
		}*/
		String str="AF111";
		String replaceAll = str.replaceAll("\\d+","");
		String str1 = str.replaceAll("[a-zA-Z]","" );
		System.out.println(str1);
	}
}
