package com.thread;

public class TestSleepMethod1 extends Thread {
	public void run() {
		for (int i = 1; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println(i+" "+Thread.currentThread().getName());
		}
	}

	public static void main(String args[]) {
		TestSleepMethod1 t1 = new TestSleepMethod1();
		t1.setName("Thread 1");
		TestSleepMethod1 t2 = new TestSleepMethod1();
		t2.setName("Thread 2");
		t1.start();
		t2.start();
	}
}
