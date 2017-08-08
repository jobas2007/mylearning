package com.thread;

class MyTable {

	void printTable(int n) {
		synchronized (this) {// synchronized block
			for (int i = 1; i <= 5; i++) {
				System.out.println(n * i);
				try {
					Thread.sleep(400);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}// end of the method
}

class MyThreadX extends Thread {
	MyTable t;

	MyThreadX(MyTable t) {
		this.t = t;
	}

	public void run() {
		t.printTable(5);
	}

}

class MyThreadY extends Thread {
	MyTable t;

	MyThreadY(MyTable t) {
		this.t = t;
	}

	public void run() {
		t.printTable(100);
	}
}

public class TestSynchronizedBlock {
	
	public static final String MYNAME="AS";
	
	public static void main(String args[]) {
		MyTable obj = new MyTable();// only one object
		MyThread1 t1 = new MyThread1(obj);
		MyThread2 t2 = new MyThread2(obj);
		t1.start();
		t2.start();

		
		
		
	}
}
