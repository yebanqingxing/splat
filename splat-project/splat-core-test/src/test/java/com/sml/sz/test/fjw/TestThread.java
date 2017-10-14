package com.sml.sz.test.fjw;

public class TestThread extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("run方法"+Thread.currentThread().getName() + " " + i);
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println("main==="+Thread.currentThread().getName());
			if (i==30) {
				//创建一个新的线程，使线程进入 新建状态
				TestThread testThread1 = new TestThread();
				TestThread testThread2 = new TestThread();
				// 调用start()方法使得线程进入就绪状态
				testThread1.start();
				testThread2.start();
			}
		}

	}
}
