package com.demo.ui.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.entity.University;
import com.demo.service.university.ShowAllUniversitiesService;
import com.demo.service.university.StatisticUniversitiesService;
import com.demo.ui.commons.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class StatisticUniversityLayoutFactory implements UIComponentBuilder {

	private List<University> universities;

	private StatisticUniversityLayout layoutRef;

	@Autowired
	private ShowAllUniversitiesService showAllUnivService;

	@Autowired
	private StatisticUniversitiesService statUnivService;

	private class StatisticUniversityLayout extends VerticalLayout {

		public StatisticUniversityLayout load() {
			universities = showAllUnivService.getAllUniversities();
			return this;
		}

		public StatisticUniversityLayout layout() {
			setMargin(true);

			for (University university : universities) {
				int numOfStudents = statUnivService.getNumOfStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>" + university.getUniversityName() + "</b>" + " : " + numOfStudents
						+ "student(s)" + "</p>", ContentMode.HTML);
				addComponent(label);
			}
			return this;
		}
	}

	public void refresh() {
		if (layoutRef == null) {
			return;
		}

		layoutRef.removeAllComponents();
		layoutRef.load();
		layoutRef.layout();

	}

	public Component createComponent() {
		layoutRef = new StatisticUniversityLayout();
		return layoutRef.load().layout();
	}
}
