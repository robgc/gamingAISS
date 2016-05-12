package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;

public class LoginView extends Composite {
	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;

	public LoginView(Map<String, String> params) {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);
		// Create a menu bar

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");
		Image acercaDe = new Image("files/acerca.png");
		Image mando = new Image("files/mando.png");
		Image steamAPI = new Image("files/STEAM_API.png");
		Image vgAPI = new Image("files/VG_API.png");
		Image amazonAPI = new Image("files/AMAZON_API.png");

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");
		acercaDe.addStyleName("acerca");
		mando.setStyleName("centered");
		vgAPI.setStyleName("API-vg");
		steamAPI.setStyleName("API-steam");
		amazonAPI.setStyleName("API-amazon");

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);
		mainPanel.add(mando);
		mainPanel.add(vgAPI);
		mainPanel.add(steamAPI);
		mainPanel.add(amazonAPI);

		vgAPI.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("info", new HashMap<String, String>());
			}
		});

		steamAPI.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("steam", new HashMap<String, String>());
			}
		});
		
		amazonAPI.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				GamingGuru.go("amazon", new HashMap<String, String>());
			}
		});

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("acerca", new HashMap<String, String>());
			}
		});
		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("init", new HashMap<String, String>());
			}
		});
	}
}
