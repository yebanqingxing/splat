package com.sml.sz.common.thread;

import java.util.List;
import java.util.Vector;

/**
 * 简易线程池
 * @author yues 2016-04-21
 * <p>简单的线程池配置</p>
 */
public class ThreadPool {	
	
	//空闲的线程队列
	private List<PThread> idleThreads = null;
	
	//已有的线程总数
	private int threadCounter;
	
	//是否关闭线程池
	private boolean isShutDown = false;
	
	/**
	 * 私有构造初始化线程队列
	 */
	private ThreadPool(){		
		this.idleThreads = new Vector<PThread>(10);
		this.threadCounter = 0;
	}
	
	/**
	 * 获取已有的线程总数
	 * @return
	 */
	public int getCreatedThreadCount(){
		return this.threadCounter;
	}
	
	/**
	 * 使用内部类实现延迟加载并解决设置同步降低性能的问题
	 * @author splat
	 *
	 */
	private static class SingletonHolderPool{
		
		/*--线程池实例对象--*/
		private static ThreadPool instance = new ThreadPool();
	}
	
	/**
	 * 获取线程池实例
	 * @return
	 */
	public static ThreadPool getInstance(){
		
		return SingletonHolderPool.instance;
	}
	
	/**
	 * 将线程放入池中
	 * @param repoolingThread 线程对象
	 */
	protected synchronized void repool(PThread repoolingThread){
		
		if(!isShutDown){
			idleThreads.add(repoolingThread);
		}else{
			repoolingThread.shutDown();
		}
	}
	
	public synchronized void start(Runnable target){
		
		PThread thread = null;
		
		//如果有空闲线程则直接使用
		if(null != idleThreads && idleThreads.size() > 0){
			
			//获取队列最后空闲线程的坐标,取得该线程赋予使用
			int lastIndex = idleThreads.size() -1;
			thread = idleThreads.get(lastIndex);
			
			//移除使用线程
			idleThreads.remove(lastIndex);
			
			//立即执行线程任务
			thread.setTarget(target);
		}else{
			
			//没有空闲线程,则创建新线程
			threadCounter++;
			thread = new PThread(target , "PThread#" + threadCounter , this);
			
			//启动该线程
			thread.start();
		}
	}
	
}
