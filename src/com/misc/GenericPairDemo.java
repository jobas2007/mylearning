package com.misc;
interface Pair<K, V> {
	public K getKey();

	public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {

	private K key;
	private V value;

	public OrderedPair(K key, V value) {

		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

}

public class GenericPairDemo {
	public static void main(String args[]) {
		Pair<String, String> pair=new OrderedPair<>("My Key","My Value");
		System.out.println(pair.getKey());
		System.out.println(pair.getValue());
		
		//
		Pair<Integer, String> pair2=new OrderedPair<>(1,"My Value");
		System.out.println(pair2.getKey());
		System.out.println(pair2.getValue());
		
	}

}
