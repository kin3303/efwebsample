package com.demo.utils;

public enum StudentStringUtils {
	MAIN_MENU("MAIN MENU"),
	SHOW_ALL_STUDENT("SHOW ALL STUDENTS"),
	
	FIRST_NAME("First Name"),
	LAST_NAME("Last name"),
	GENDER("Gender"),
	AGE("Age"),
	SAVE("Save"),
	CLEAR("Clear"),
	UNIVERSITY("University"),
	;
	
	private final String string;
	
	private StudentStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
