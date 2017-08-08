package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	public ImgValidator() {
		pattern = Pattern.compile(IMAGE_PATTERN);
	}

	public boolean validate(final String image) {
		matcher = pattern.matcher(image);
		return matcher.matches();
	}

	public static void main(String args[]) {
		String first = "a.tmp";
		ImgValidator imgValidator = new ImgValidator();
		boolean valid = imgValidator.validate(first);
		System.out.println("Image is valid : " + first + " , " + valid);

		String second = "d.bmp";
		valid = imgValidator.validate(second);
		System.out.println("Image is valid : " + second + " , " + valid);
	}
}
