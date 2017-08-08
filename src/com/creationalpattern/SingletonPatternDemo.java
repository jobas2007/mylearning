package com.creationalpattern;

public class SingletonPatternDemo {
	public static void main(String args[]) {
		System.out.println(Singleton.getInstance());
	}
}

// Lazy instantiation using double locking mechanism.
class Singleton {
	private static Singleton instance;

	private Singleton() {
		System.out.println("Singleton(): Initializing Instance");
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}