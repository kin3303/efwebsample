package com.demo.utils;

public enum NotificationMessages {
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("Error"),
	STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("Field must be filled!!"),
	STUDENT_SAVE_SUCCESS_TITLE("Save Success"),
	STUDENT_SAVE_VALIDATION_SUCCESS_DESCRIPTION("Student has been saved!!"),
	STUDENT_REMOVE_SUCCESS_TITLE("Delete Success"),
	STUDENT_REMOVE_VALIDATION_SUCCESS_DESCRIPTION("Selected student has been deleted!!"),
	UNIVERSITY_SAVE_SUCCESS_TITLE("Save Success"),
	UNIVERSITY_SAVE_VALIDATION_SUCCESS_DESCRIPTION("University successfully saved!"),
	UNIVERSITY_SAVE_FAIL_TITLE("Error"),
	UNIVERSITY_SAVE_FAIL_DESCRIPTION("Must have at least one university!"),
	;
	
	private final String string;
	
	private NotificationMessages(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
