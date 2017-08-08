package com.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericExamples {

	// @Test
	public void betterForCasting() {
		List<String> list = new ArrayList<>();
		list.add("first");

		String s = list.get(0);
	}

	// @Test
	public void boxesTest() {
		List<Box<String>> boxes = new ArrayList<>();

		Box<String> box1 = new Box<>();
		box1.setObj("a string");

		Box<String> box2 = new Box<>();
		box2.setObj("");

		boxes.add(box1);
		boxes.add(box2);

		box1.inspect(7);

	}

	// @Test
	public void showSumOfNumbers() {
		sumOfNumbers(Arrays.asList(1, 899999, 4));
	}

	public void sumOfNumbers(List<? extends Number> numbers) {
		double d = 0.0;
		for (Number n : numbers) {
			d += n.doubleValue();
		}
		System.out.println(d);
	}

}
