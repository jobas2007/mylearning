package com.creationalpattern;

public class FactoryPatternDemo {
	public static void main(String args[]) {
		PizzaStore store = new PizzaStore(new SimplePizzaFactory());
		store.orderPizza("cheeze");

		// --
		store.orderPizza("greek");
	}
}

class PizzaStore {
	private SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(final String type) {
		Pizza pizza = factory.createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}

class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new CheesePizza();
		} else if (type.equals("greek")) {
			return new GreekPizza();
		} else if (type.equals("pepperoni")) {
			return new PepperoniPizza();
		}
		return new CheesePizza();
	}
}

// ---------------------------------------------------------------------------------------
abstract class Pizza {
	String name;

	abstract void prepare();

	void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	void cut() {
		System.out.println("Cut into 8 pieces");
	}

	void box() {
		System.out.println("Box it ..\n");
	}
}

class CheesePizza extends Pizza {
	void prepare() {
		System.out.println("Preparing Cheeze Pizza.....");
	}
}

class GreekPizza extends Pizza {
	void prepare() {
		System.out.println("Preparing Greek Pizza......");
	}
}

class PepperoniPizza extends Pizza {
	void prepare() {
		System.out.println("Preparing Pepperoni Pizza......");
	}
}
