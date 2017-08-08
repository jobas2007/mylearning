package com.misc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Pet implements Comparable{
	int petId;
	String petType;

	public Pet(int argPetId, String argPetType) {
		petId = argPetId;
		this.petType = argPetType;
	}

	public int compareTo(Object o) {
		Pet petAnother = (Pet) o;
		// natural alphabetical ordering by type
		// if equal returns 0, if greater returns +ve int,
		// if less returns -ve int
		return this.petType.compareTo(petAnother.petType);
	}

	public static void main(String[] args) {
		List<Pet> list = new ArrayList<>();
		list.add(new Pet(2, "Dog"));
		list.add(new Pet(1, "Parrot"));
		list.add(new Pet(2, "Cat"));
		Collections.sort(list); // sorts using compareTo method
		for (Iterator<Pet> iter = list.iterator(); iter.hasNext();) {
			Pet element = iter.next();
			System.out.println(element);
		}
	}

	public String toString() {
		return petType;
	}
}

public class ComparableDemo {

}
