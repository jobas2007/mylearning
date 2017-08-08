package com.misc;
class A {
	public void foo(int z) {
		System.out.println("z value passed before:" + z);
		z = 30;
		System.out.println("z value passed after:" + z);
	}
}

public class TestPassByValuePrimitive {
	public static void main(String args[]) {
		System.out.println("Hello World");
		int x = 10;
		System.out.println("Inside main before x:" + x);
		new A().foo(x);
		System.out.println("Inside main after x:" + x);
	}
}
