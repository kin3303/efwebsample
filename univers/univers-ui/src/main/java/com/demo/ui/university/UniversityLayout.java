package com.demo.ui.university;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=UniversityLayout.NAME)
public class UniversityLayout extends VerticalLayout implements View, UniversitySavedListener {
	public static final String NAME="operations";
	private TabSheet tabSheet;
	
	@Autowired
	private AddUniversityLayoutFactory addUniversityFactory; 

	@Autowired
	private ShowUniversityLayoutFactory showUniversityFactory;
	
	@Autowired
	private StatisticUniversityLayoutFactory statUniversityFactory;
	
	private void addLayout() {

		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addUniversityTab = addUniversityFactory.createComponent(this);
		Component showAllUniversitiesTab = showUniversityFactory.createComponent();
		Component showStaticsTab = statUniversityFactory.createComponent();
		
		tabSheet.addTab(addUniversityTab, StringUtils.MENU_ADD_UNIVERSITY.getString()); 
		tabSheet.addTab(showAllUniversitiesTab, StringUtils.MENU_SHOW_UNIVERSITY.getString()); 
		tabSheet.addTab(showStaticsTab, StringUtils.MENU_STAT_UNIVERSITY.getString()); 
		
		addComponent(tabSheet);
	}
	
	public void universitySaved() { 
		showUniversityFactory.refreshTables();
		statUniversityFactory.refresh();
	}
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
}
