package com.demo.ui.student;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.utils.StudentStringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=AddStudentLayout.NAME)
public class AddStudentLayout extends VerticalLayout implements View, StudentSavedListener {

	public static final String NAME="addstudent";
	
	@Autowired
	private AddStudentMainLayoutFactory mainLayoutFactory;
	
	@Autowired
	private ShowAllStudentLayoutFactory showLayoutFactory;

	private TabSheet tabSheet;
	
	public void enter(ViewChangeEvent event) { 
		removeAllComponents();
		addLayout();
	}

	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		mainLayoutFactory.setListener(this);
		Component addStudentMainTab = mainLayoutFactory.createComponent();
		Component showStudentTab = showLayoutFactory.createComponent();
		
		tabSheet.addTab(addStudentMainTab,StudentStringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentTab,StudentStringUtils.SHOW_ALL_STUDENT.getString());
		
		addComponent(tabSheet);
		
	}

	public void studentSaved() {
		showLayoutFactory.refreshTable();
	}
}
