package com.demo.ui.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.Student;
import com.demo.service.student.RemoveStudentService;
import com.demo.service.student.ShowStudentService;
import com.demo.ui.commons.UniversMainUI;
import com.demo.utils.NotificationMessages;
import com.demo.utils.StringUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = RemoveStudentLayout.NAME, ui = UniversMainUI.class)
public class RemoveStudentLayout extends VerticalLayout implements View, Button.ClickListener {

	@Autowired
	private ShowStudentService studentShowService;

	@Autowired
	private RemoveStudentService removeStudentService;

	public static final String NAME = "removestudent";
	private Grid removeStudentTable;
	private Button removeStudentsButton;
	private List<Student> students;

	private void addLayout() {

		removeStudentsButton = new Button(StringUtils.REMOVE_STUDENT.getString());

		setMargin(true);
		BeanItemContainer<Student> container = new BeanItemContainer<Student>(Student.class, students);

		removeStudentTable = new Grid(container);
		removeStudentTable.setColumnOrder("firstName", "lastName", "age", "gender");
		removeStudentTable.removeColumn("id");
		removeStudentTable.setImmediate(true);
		removeStudentTable.setSelectionMode(SelectionMode.MULTI);

		removeStudentsButton.addClickListener(this);
		removeStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		addComponent(removeStudentTable);
		addComponent(removeStudentsButton);
	}

	public void buttonClick(ClickEvent event) {

		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();

		for (Object selectedItem : selectionModel.getSelectedRows()) {
			Student student = (Student) selectedItem;
			removeStudentTable.getContainerDataSource().removeItem(student);
			removeStudentService.removeStudent(student);
		}

		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
				NotificationMessages.STUDENT_REMOVE_VALIDATION_SUCCESS_DESCRIPTION.getString(), Type.HUMANIZED_MESSAGE);

		removeStudentTable.getSelectionModel().reset();
	}

	private void loadStudents() {
		students = studentShowService.getAllStudents();
	}

	public void enter(ViewChangeEvent event) {

		if (removeStudentTable != null)
			return;

		loadStudents();
		addLayout();
	}
}
