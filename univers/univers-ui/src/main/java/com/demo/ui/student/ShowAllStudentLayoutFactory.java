package com.demo.ui.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.Student;
import com.demo.service.student.ShowStudentService;
import com.demo.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllStudentLayoutFactory implements UIComponentBuilder {

	private List<Student> students;
	private BeanItemContainer<Student> container;
	
	@Autowired
	private ShowStudentService showStudentService;
	
	public class ShowAllStudentLayout extends VerticalLayout {
		private Grid studentTable;
		
		public ShowAllStudentLayout init() {
			setMargin(true);
			container = new BeanItemContainer<Student>(Student.class, students);
			studentTable = new Grid(container);
			studentTable.setColumnOrder("firstName","lastName","age","gender");
			studentTable.removeColumn("id");
			studentTable.removeColumn("university");
			studentTable.setImmediate(true);
			
			return this;
		}
		
		public ShowAllStudentLayout load() {
			students = showStudentService.getAllStudents();
			return this;
		}
		
		public ShowAllStudentLayout layout() {
			addComponent(studentTable);
			return this;
		}
	}
	
	public void refreshTable() {
		students = showStudentService.getAllStudents();
		container.removeAllItems();
		container.addAll(students);
		
	}	
	
	public Component createComponent() {
		return new ShowAllStudentLayout().load().init().layout();
	}
}
