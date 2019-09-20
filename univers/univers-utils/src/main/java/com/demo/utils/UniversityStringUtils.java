package com.demo.utils;

public enum UniversityStringUtils {
	UNIVERSITY_SAVE("Save"),
	UNIVERSITY_NAME("Name"),
	UNIVERSITY_COUNTRY("Country"),
	UNIVERSITY_CITY("City")
	;
	
	private final String string;
	
	private UniversityStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
