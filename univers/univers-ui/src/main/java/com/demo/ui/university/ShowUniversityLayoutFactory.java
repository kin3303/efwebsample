package com.demo.ui.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.University;
import com.demo.service.university.ShowAllUniversitiesService;
import com.demo.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowUniversityLayoutFactory implements UIComponentBuilder {
	
	private List<University> universities;
	private BeanItemContainer<University> container;
	
	@Autowired
	private ShowAllUniversitiesService showUniversityService;

	private class ShowUniversityLayout extends VerticalLayout {
		private Grid universityTable;

		public ShowUniversityLayout load() { 
			universities = showUniversityService.getAllUniversities();
			return this;
		}

		public ShowUniversityLayout init() { 
			setMargin(true);
			container = new BeanItemContainer<University>(University.class, universities);
			
			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName","universityCountry","universityCity");
			universityTable.removeColumn("id");
			universityTable.setImmediate(true);
			
			return this;
		}

		public ShowUniversityLayout layout() {
			addComponent(universityTable);
			return this;
		}
	}
	
	public void refreshTables() {
		universities = showUniversityService.getAllUniversities();
		container.removeAllItems();
		container.addAll(universities);
	}

	public Component createComponent() {
		// TODO Auto-generated method stub
		return new ShowUniversityLayout().load().init().layout();
	}
}
