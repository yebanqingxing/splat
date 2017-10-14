/**
 * 
 */
package com.sml.sz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* <p>Title: MakeOrderNum.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2016</p>
* <p>Company: 北京XX科技有限公司</p>
* @author huangsy
* @date 2016年3月1日
* @version 1.0 
* @Description: 订单号生成工具，生成非重复订单号，理论上限1毫秒1000个，可扩展
*/

public class MakeOrderNum {
	/**
	 * 锁对象，可以为任意对象
	 */
	private static Object lockObj = "lockerOrder";
	/**
	 * 订单号生成计数器
	 */
	private static long jp_orderNumCount = 0L;
	private static long jd_orderNumCount = 0L;
	/**
	 * 每毫秒生成订单号数量最大值
	 */
	private static int maxPerMSECSize=1000;
	/**
	 * 生成非重复订单号，理论上限1毫秒1000个，可扩展
	 * @param tname 测试用
	 */
	public  String makeOrderNum(String cc,String mm) {
		// 最终生成的订单号
		String finOrderNum = "";
		try {
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				long nowLong = Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				String countStr=cc;
				if("01".equals(cc)){
					if (jp_orderNumCount > maxPerMSECSize) {
						jp_orderNumCount = 0L;
					}
					countStr+=String.format("%0"+4+"d", jp_orderNumCount);
					jp_orderNumCount++;
				}else if("02".equals(cc)){
					if (jd_orderNumCount > maxPerMSECSize) {
						jd_orderNumCount = 0L;
					}
					countStr+=String.format("%0"+4+"d", jd_orderNumCount);
					jd_orderNumCount++;
				}
				
				
				//组装订单号
				//System.out.println(maxPerMSECSize);
				finOrderNum=nowLong+countStr+mm;
				
				//System.out.println(finOrderNum + "--" + Thread.currentThread().getName() + "::" + cc );
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}

	public static void main(String[] args) {
		// 测试多线程调用订单号生成工具
		try {
			for (int i = 0; i < 200; i++) {
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						MakeOrderNum makeOrder = new MakeOrderNum();
						//makeOrder.makeOrderNum("01","01");
						makeOrder.MakeOrderId("01");
					}
				}, "at" + i);
				t1.start();

				Thread t2 = new Thread(new Runnable() {
					public void run() {
						MakeOrderNum makeOrder = new MakeOrderNum();
						//makeOrder.makeOrderNum("02","02");
						makeOrder.MakeOrderId("03");
					}
				}, "bt" + i);
				t2.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*for(int i=0;i<=1000;i++){
			MakeOrderNum makeOrder = new MakeOrderNum();
			makeOrder.makeOrderNum("01","01");
		}*/
	}
	
	/**
	 * 订单号生成器
	 * @param cc 
	 * @return
	 */
	public String MakeOrderId(String cc){
		// 最终生成的订单id
				String finOrderId = "";
				try {
					synchronized (lockObj) {
						// 取系统当前时间作为订单号变量前半部分，精确到毫秒
						long nowLong = Long.parseLong(new SimpleDateFormat("yyMMddHHmm").format(new Date()));
						// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
						String countStr=cc;
						if("01".equals(cc)){
							if (jp_orderNumCount > maxPerMSECSize) {
								jp_orderNumCount = 0L;
							}
							countStr+=String.format("%0"+4+"d", jp_orderNumCount);
							jp_orderNumCount++;
						}else if("02".equals(cc)){
							if (jd_orderNumCount > maxPerMSECSize) {
								jd_orderNumCount = 0L;
							}
							countStr+=String.format("%0"+4+"d", jd_orderNumCount);
							jd_orderNumCount++;
						}
						
						
						//组装订单号
						//System.out.println(maxPerMSECSize);
						finOrderId=nowLong+countStr;
						
						//System.out.println(finOrderId + "--" + Thread.currentThread().getName() + "::" + cc );
						// Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		return finOrderId;
	}

}