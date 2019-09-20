package com.demo.ui.university;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.University;
import com.demo.service.university.AddUniversityService;
import com.demo.utils.NotificationMessages;
import com.demo.utils.UniversityStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {
	
	@Autowired
	private AddUniversityService addUniversityService;

	private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener {

		private TextField universityName;
		private TextField universityCountry;
		private TextField universityCity;
		private Button saveButton;
		private BeanFieldGroup<University> fieldGroup;
		private University university;
		
		private UniversitySavedListener universitySavedListener;
		
		public AddUniversityLayout(UniversitySavedListener universitySavedListener) {
			this.universitySavedListener = universitySavedListener;
			this.university = new University();
		}

		public AddUniversityLayout init() {

			universityName = new TextField(UniversityStringUtils.UNIVERSITY_NAME.getString());
			universityCountry = new TextField(UniversityStringUtils.UNIVERSITY_COUNTRY.getString());
			universityCity = new TextField(UniversityStringUtils.UNIVERSITY_CITY.getString());
			
			saveButton = new Button(UniversityStringUtils.UNIVERSITY_SAVE.getString(), this);
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			universityName.setNullRepresentation("");
			universityCountry.setNullRepresentation("");
			universityCity.setNullRepresentation("");
			
			universityName.addValidator(new StringLengthValidator("Wrong length", 1, 10, false));
			universityCountry.addValidator(new StringLengthValidator("Wrong length", 1, 10, false));
			universityCity.addValidator(new StringLengthValidator("Wrong length", 1, 10, false)); 

			return this;
		}
		
		public AddUniversityLayout bind() {

			fieldGroup = new BeanFieldGroup<University>(University.class);
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(university);

			return this;
		}

		public Component layout() {
			
			setWidth("100%");

			GridLayout grid = new GridLayout(2,4);
			grid.setHeightUndefined();
			grid.setSpacing(true);
			
			grid.addComponent(universityName,0,0,1,0);
			grid.addComponent(universityCountry,0,1,1,1);
			grid.addComponent(universityCity,0,2,1,2);
			grid.addComponent(new HorizontalLayout(saveButton),0,3,0,3);

			return grid;
		}

		public void buttonClick(ClickEvent event) {
			
			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				return;
			}
			
			clearFields();
			addUniversityService.addUniversity(university);
			universitySavedListener.universitySaved();
			Notification.show(NotificationMessages.UNIVERSITY_SAVE_SUCCESS_TITLE.getString(), NotificationMessages.UNIVERSITY_SAVE_VALIDATION_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
		}

		private void clearFields() {
			universityName.setValue(null);
			universityCountry.setValue(null);
			universityCity.setValue(null);
		}
	}
	


	public Component createComponent(UniversitySavedListener universitySavedListener) {
		return new AddUniversityLayout(universitySavedListener).init().bind().layout();
	}
}
