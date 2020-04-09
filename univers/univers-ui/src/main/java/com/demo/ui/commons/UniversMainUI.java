package com.demo.ui.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.demo.navigator.UniversNavigator;
import com.demo.ui.student.AddStudentLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path=UniversMainUI.NAME)
@Title("Univers-V-1.1")
@Theme("valo")
public class UniversMainUI extends UI {
	public static final String NAME="/ui";
	
	private Panel changeTab = new Panel();
	
	@Autowired
	private UniversLogoLayoutFactory universLogoLayoutFactory;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private UniversMenuFactory universMenuLayoutFactory;
	
	@Autowired
	private SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest request) {
		
		changeTab.setHeight("100%");
		VerticalLayout rootLayout = new VerticalLayout();
		rootLayout.setSizeFull();
		rootLayout.setMargin(true);
		
		//Content
		Panel contentPanel = new Panel();
		contentPanel.setWidth("75%");
		contentPanel.setHeight("100%");
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		Component menu = universMenuLayoutFactory.createComponent();
		uiLayout.addComponent(menu);
		uiLayout.addComponent(changeTab);
		uiLayout.setComponentAlignment(changeTab,Alignment.TOP_CENTER);
		
		uiLayout.setExpandRatio(menu, 1);
		uiLayout.setExpandRatio(changeTab, 2);
		
		contentPanel.setContent(uiLayout);
		
		//Logo
		Panel logoPanel = new Panel();
		logoPanel.setWidth("75%");
		logoPanel.setHeightUndefined();
		
		Component logo = universLogoLayoutFactory.createComponent();
		logoPanel.setContent(logo);
		
		//Frame
		rootLayout.addComponent(logoPanel);
		rootLayout.addComponent(contentPanel);
		rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
		rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
		rootLayout.setExpandRatio(contentPanel, 1);
		
		initNavigator();
        
        setContent(rootLayout);
	}

	private void initNavigator() {
		UniversNavigator navigator = new UniversNavigator(this, changeTab);
		appContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(AddStudentLayout.NAME);
		
	}
}
