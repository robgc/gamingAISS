package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class AcercaView extends Composite {
	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;

	public AcercaView(Map<String, String> params) {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");

		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);

		
		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				GamingGuru.go("init", new HashMap<String, String>());
			}
		});
	}
}
