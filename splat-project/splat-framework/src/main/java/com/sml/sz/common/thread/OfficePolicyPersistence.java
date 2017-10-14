package com.sml.sz.common.thread;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sml.sz.sys.utils.excel.XLSXCovertCSVReader;

/**
 * office政策信息持久化
 * @author yues
 *
 */
public class OfficePolicyPersistence {
	
	//批量插入条数阈值
	public static int threshlod = 50000;

	/**
	 * office数据持久化入口
	 */
	public static void execPersistence(){
		
		if(!XLSXCovertCSVReader.officeData.isEmpty()){
			
			//遍历office信息, 持久化office数据
			for(Entry<String , List<String[]>> entry : XLSXCovertCSVReader.officeData.entrySet()){
				
				execPersistenceData(entry.getKey() , entry.getValue());
			}
		}
	}
	
	/**
	 * 持久化Office数据
	 * @param office 
	 * @param data
	 */
	public static void execPersistenceData(String office , List<String[]> data){
		
		if(null == data || data.size() == 0){
			System.out.println(office + "-可持久化数据列表为空!!");
		}else{
			
			//office数据总和
			//int dataCount = data.size();
			
			//System.out.println(office + "-待持久化数据数量 = " + dataCount);
			
			//初始化线程池对象
			ExecutorService exe = Executors.newCachedThreadPool();
			
			//启动线程池获取线程进行持久化操作
			exe.execute(new OfficePolicyPersistenceThread(office , data , "Th_Full"));
			
			/*//数据总和大于基本数据阈值 , 则分线程进行处理 , 否则直接执行
			if(dataCount > threshlod){
				
				//线程数
				int tempCount = dataCount / threshlod;
				int threadCount = (dataCount % threshlod == 0 ? tempCount : tempCount + 1);
				
				//复制数据信息
				List<String[]> temp = null;
				
				//开始索引
				int startIndex = 0;
				
				//结束索引
				int endIndex = 0;
				
				//线程数-1次循环操作
				for(int i = 0 , end = threadCount-1; i < end; i++){
					
					startIndex = i * threshlod;
					endIndex = (threshlod * (i+1));
					
					temp = data.subList(startIndex , endIndex);
					
					//启动线程池获取线程进行持久化操作
					exe.execute(new OfficePolicyPersistenceThread(office , temp , "Th_" + i));
				}
				
				//最后一次线程截取数据到原始数据末尾
				temp = data.subList(endIndex , dataCount);
				
				//启动线程池获取线程进行持久化操作
				exe.execute(new OfficePolicyPersistenceThread(office , temp , "Th_" + (threadCount-1)));
			}else{
				
				//启动线程池获取线程进行持久化操作
				exe.execute(new OfficePolicyPersistenceThread(office , data , "Th_Full"));
			}*/
		}
	}

}
