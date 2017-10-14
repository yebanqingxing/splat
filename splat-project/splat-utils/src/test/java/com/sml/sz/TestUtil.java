package com.sml.sz;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUtil {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testMAC() {
		String windowsMACAddress = MacUtils.getWindowsMACAddress();
		String osName = MacUtils.getOSName();
		String sysPath = SystemPath.getSysPath();
		System.out.println(osName+"-"+windowsMACAddress);
		System.out.println(sysPath);
	}
}
