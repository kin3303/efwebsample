package com.demo.utils;

public enum StringUtils {
	MENU_STUDENT("STUDENT"),
	MENU_UNIVERSITY("UNIVERSITY"),
	MENU_ADD_STUDENT("ADD STUDENT"),
	MENU_REMOVE_STUDENT("REMOVE STUDENT"),
	MENU_OPERATIONS("OPERATIONS"),
	REMOVE_STUDENT("REMOVE"),
	MENU_ADD_UNIVERSITY("ADD UNIVERSITY"),
	MENU_SHOW_UNIVERSITY("SHOW UNIVERSITY"),
	MENU_STAT_UNIVERSITY("STAT UNIVERSITY")
	;
	
	private final String string;
	
	private StringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
