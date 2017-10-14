package com.sml.sz.test.fjw;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.security.auth.callback.Callback;

import com.sun.mail.handlers.text_html;

/**
 * 
 * @author plat11
 * 线程池
 */
public class TestExecutors {
	public  void te() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Callable callback = new Callable() {
			public String call(){
				TestThread testThread1 = new TestThread();
				testThread1.run();
				TestThread testThread2 = new TestThread();
				testThread2.run();
				System.out.println(123);
				return "123";
			}
		};
		
		executorService.submit( callback);
	}
	public static void main(String[] args) {
		new TestExecutors().te();
	}
}
