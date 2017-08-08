package com.misc;

public class Palindrome {
	public static void main(String args[]) {
		System.out.println(isPalindrome("andna"));
	}
	
	public static boolean isPalindrome(String s) {

	    for (int i=0 , j=s.length()-1 ; i<j ; i++ , j-- ) {
	    		System.out.println(s.charAt(i));
	    		System.out.println(s.charAt(j));
	    		System.out.println("-----------------------------------------------------------");
	        if ( s.charAt(i) != s.charAt(j) ) {
	            return false;
	        }
	    }

	    return true;
	}
}

