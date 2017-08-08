package com.thread;

class Table {

	synchronized void printTable(int n) {// method not synchronized
		for (int i = 1; i <= 5; i++) {
			System.out.println(n * i);
			try {
				Thread.sleep(400);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
}

class MyThread1 extends Thread {
	MyTable t;

	MyThread1(MyTable t) {
		this.t = t;
	}

	public void run() {
		t.printTable(5);
	}

}

class MyThread2 extends Thread {
	MyTable t;

	MyThread2(MyTable t) {
		this.t = t;
	}

	public void run() {
		t.printTable(100);
	}
}

public class TestSynchronization1 {
	public static void main(String args[]) {
		MyTable obj = new MyTable();// only one object
		MyThread1 t1 = new MyThread1(obj);
		MyThread2 t2 = new MyThread2(obj);
		t1.start();
		t2.start();
	}
}
