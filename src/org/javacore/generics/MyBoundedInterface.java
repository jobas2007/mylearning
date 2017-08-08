package org.javacore.generics;

public class MyBoundedInterface {

	public static void main(String a[]) {

		// Creating object of implementation class X called Y and passing as bound parameter
		BoundExmp<Y> bey = new BoundExmp<Y>(new Y());
		bey.doRunTest();

		// Creating object of implementation class X called Z and passing as bound parameter
		BoundExmp<Z> bez = new BoundExmp<Z>(new Z());
		bez.doRunTest();

		// below compilation error as String does not extend X
		// BoundExmp<String> bes = new BoundExmp<String>(new String());
		// bea.doRunTest();
	}
}

class BoundExmp<T extends X> {

	private T objRef;

	public BoundExmp(T obj) {
		this.objRef = obj;
	}

	public void doRunTest() {
		this.objRef.printClass();
	}
}

interface X {
	public void printClass();
}

class Y implements X {
	public void printClass() {
		System.out.println("I am in class Y");
	}
}

class Z implements X {
	public void printClass() {
		System.out.println("I am in class Z");
	}
}
