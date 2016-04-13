package com.aiss.gamingguru.client.views;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Image;

public class ViewList extends Composite {
	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;

	public ViewList() {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);
		// Create a menu bar
		MenuBar menu = new MenuBar();
		Image mando = new Image("files/mando.png");
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");
		Image acercaDe = new Image("files/acerca1.png");

		
		fondo.setStyleName("background");
		menu.setStyleName("menu");
		mando.setStyleName("centered");
		icon.setStyleName("menuIcon");
		acercaDe.addStyleName("acerca");	
		
		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(mando);
		mainPanel.add(menu);
		// Create a sub menu of recent documents

	}

}
