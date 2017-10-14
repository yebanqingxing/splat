package com.sml.sz.common.thread;

/**
 * 线程任务对象
 * @author yues
 *
 */
public class PThread extends Thread{

	//线程池
	private ThreadPool pool;
	
	//任务
	protected Runnable target;
	
	//是否销毁
	protected boolean isShutDown = false;
	
	//是否闲置
	protected boolean isIdle = false;
	
	/**
	 * 初始化构造函数
	 * @param target 任务对象
	 * @param name 线程名称
	 * @param pool 连接池对象
	 */
	public PThread(Runnable target , String name , ThreadPool pool){
		super(name);
		this.pool = pool;
		this.target = target;
	}
	
	
	public Runnable getTarget(){
		return this.getTarget();
	}
	
	public boolean isIdle(){
		return this.isIdle();
	}
	
	/**
	 * 线程动作
	 */
	public void run(){
		
		//只要未关闭,则一直不结束该线程
		while(!isShutDown){
			isIdle = false;
			
			//运行任务
			if(null != target){
				target.run();
			}
		}
		
		//任务结束,回归闲置状态
		isIdle = true;
		
		try{
			
			//任务结束不关闭线程,而是放入线程池空闲队列
			pool.repool(this);
			
			//线程空闲 , 等待新任务到来
			synchronized(this){
				wait();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		isIdle = false;
	}
	
	/**
	 * 设定线程任务,唤醒等待线程
	 * @param newTarget
	 */
	public synchronized void setTarget(Runnable newTarget){
		
		this.target = newTarget;
		
		//设置了任务之后,通知run方法开始执行这个任务
		notifyAll();
	}
	
	/**
	 * 关闭线程
	 */
	public synchronized void shutDown(){
		this.isShutDown = true;
		notifyAll();
	}
}
