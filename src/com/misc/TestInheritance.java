package com.misc;

class Parent {
	public int i = 0;
	public void method1() {
		System.out.println("Inside Parent");
	}
}

class Child extends Parent {
	public int i = 10;
	public void method1() {
		System.out.println("Inside Child");
	}
}

public class TestInheritance {
	public static void main(String args[]) {
		Parent p = new Child();
		p.method1();
		System.out.println(p.i);
		try {
			Object o=3/8;
			System.out.println(o);
		}finally {
			
		}
		
		//Child c=(Child)new Parent();
		//c.method1();
	}
}
