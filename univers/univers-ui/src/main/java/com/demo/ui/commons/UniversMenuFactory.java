package com.demo.ui.commons;

import com.demo.utils.StringUtils;

import com.demo.navigator.UniversNavigator;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversMenuFactory implements UIComponentBuilder {

	private class UniversMenu extends VerticalLayout {
		private Tree mainMenu;
		
		public UniversMenu init() {
			mainMenu = new Tree();
			return this;
		}
		
		public UniversMenu layout() {
			setWidth("100%");
			setHeightUndefined();
			
			//Menu University
			mainMenu.addItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.expandItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.addItem(StringUtils.MENU_OPERATIONS.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_OPERATIONS.getString(), false);
			mainMenu.setParent(StringUtils.MENU_OPERATIONS.getString(), StringUtils.MENU_UNIVERSITY.getString());		
			
			//Menu Student
			mainMenu.addItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.expandItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_ADD_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_REMOVE_STUDENT.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_ADD_STUDENT.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_REMOVE_STUDENT.getString(), false);
			mainMenu.setParent(StringUtils.MENU_ADD_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());
			mainMenu.setParent(StringUtils.MENU_REMOVE_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());
			
			mainMenu.addValueChangeListener(new ValueChangeListener() {
				
				public void valueChange(ValueChangeEvent event) { 
					String selectedItemPath = (String) event.getProperty().getValue();
					
					if( selectedItemPath == null ) return;
					
					System.out.println(selectedItemPath);
					String path = selectedItemPath.toLowerCase().replaceAll("\\s+","");
					UniversNavigator.navigate(path);
					
				}
			});
			
			addComponent(mainMenu);
			
			return this;
		}
	}
	
	public Component createComponent() { 
		return new UniversMenu().init().layout();
	}

}
