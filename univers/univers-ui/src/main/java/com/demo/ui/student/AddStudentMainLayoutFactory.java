package com.demo.ui.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.Student;
import com.demo.model.entity.University;
import com.demo.service.student.AddStudentService;
import com.demo.service.university.ShowAllUniversitiesService;
import com.demo.ui.commons.UIComponentBuilder;
import com.demo.utils.Gender;
import com.demo.utils.NotificationMessages;
import com.demo.utils.StudentStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory implements UIComponentBuilder {
	
	@Autowired
	private AddStudentService addStudentService;
	
	@Autowired
	private ShowAllUniversitiesService showUniversitiesService;
	
	private StudentSavedListener listener;
	
	public void setListener(StudentSavedListener listener) {
		this.listener = listener;
		
	}
	
	private class AddStudentMainLayout extends VerticalLayout {
		
		@PropertyId("firstName")
		private TextField firstName;
		
		@PropertyId("lastName")
		private TextField lastName;
		
		@PropertyId("age")
		private TextField age;
		
		@PropertyId("gender")
		private ComboBox gender;
		
		@PropertyId("university")
		private ComboBox university;
		
		
		private Button saveButton;
		private Button clearButton;
		
		private BeanFieldGroup<Student> fieldGroup;
		private Student student; 
		
		public AddStudentMainLayout init() {
			firstName = new TextField(StudentStringUtils.FIRST_NAME.getString());
			lastName = new TextField(StudentStringUtils.LAST_NAME.getString());
			age = new TextField(StudentStringUtils.AGE.getString());
			
			gender  = new ComboBox(StudentStringUtils.GENDER.getString());
			gender.addItem(Gender.MALE.getString());
			gender.addItem(Gender.FEMALE.getString());
			
			saveButton = new Button(StudentStringUtils.SAVE.getString());
			clearButton = new Button(StudentStringUtils.CLEAR.getString());
			
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");
			
			
			firstName.addValidator(new StringLengthValidator("Wrong length", 1, 10, false));
			lastName.addValidator(new StringLengthValidator("Wrong length", 1, 10, false));
			age.addValidator(new IntegerRangeValidator("Wrong Range", 0, 100));
			
			university = new ComboBox(StudentStringUtils.UNIVERSITY.getString());
			university.setNullSelectionAllowed(false); 
			
			saveButton.addClickListener(new Button.ClickListener() {
				
				public void buttonClick(ClickEvent event) { 
					save();
					clear();
				}

				private void save() {
					try {
						
						if(!isSaveOperationValid()) {
							Notification.show(NotificationMessages.UNIVERSITY_SAVE_FAIL_TITLE.getString(),
									NotificationMessages.UNIVERSITY_SAVE_FAIL_DESCRIPTION.getString(),Type.ERROR_MESSAGE);
							return;
						}
						
						fieldGroup.commit();
						System.out.println(student.toString());
						addStudentService.saveStudent(student);
						
						if(listener != null) {
							listener.studentSaved();
						}
						
						Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
								NotificationMessages.STUDENT_SAVE_VALIDATION_SUCCESS_DESCRIPTION.getString(),Type.HUMANIZED_MESSAGE);
						
					} catch (CommitException e) { 
						Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),Type.ERROR_MESSAGE);
						return;
					}
					
				}
				
				private void clear() {
					firstName.setValue(null);
					lastName.setValue(null);
					age.setValue(null);
					gender.setValue(null);
					university.setValue(null);
					
				}
			});
			
			clearButton.addClickListener(new Button.ClickListener() {
				
				public void buttonClick(ClickEvent event) { 
					clear();
				}

				private void clear() {
					firstName.setValue(null);
					lastName.setValue(null);
					age.setValue(null);
					gender.setValue(null);
					university.setValue(null);
					
				}
			});
  
			return this;
		}
		
		public AddStudentMainLayout bind() {
			student = new Student();
			fieldGroup = new BeanFieldGroup<Student>(Student.class);
			
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(student);
			
			return this;
		}	
		
		public Component layout() {
			setMargin(true);
			
			GridLayout gridLayout = new GridLayout(2,4);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);
			
			gridLayout.addComponent(firstName, 0, 0);
			gridLayout.addComponent(lastName, 1, 0);
			gridLayout.addComponent(age, 0, 1);
			gridLayout.addComponent(gender, 1, 1);
			
			gridLayout.addComponent(university,0,2,1,2);
			
			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0,3);
			
			return gridLayout;
		}

		public AddStudentMainLayout load() {
			List<University> universityData = showUniversitiesService.getAllUniversities();
			university.addItems(universityData);
			
			return this;
		}
		
		private boolean isSaveOperationValid() { 
			University selectedUniversity = (University)university.getValue();
			if(selectedUniversity != null && selectedUniversity.getUniversityName() != "") {
				return true;
			}
			
			return false;
		}
	}
	
	public Component createComponent() {
		return new AddStudentMainLayout().init().load().bind().layout();
	}
}
