package com.creationalpattern;

public class SingletonEnum {
	public static void main(String args[]) {
		System.out.println(MySingleton.INSTANCE);
	}

	public static enum MySingleton {
		INSTANCE;
		private MySingleton() {
			System.out.println("Hello - I am alive");
		}
	}
}
