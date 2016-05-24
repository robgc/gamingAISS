package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TextBox;

public class LoginView extends Composite {
	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;
	private TextBox searchField = new TextBox();
	private Label statusLabel = new Label();
	private Set<String> platforms = new HashSet<String>();

	public LoginView(Set<String> params) {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");
		Image acercaDe = new Image("files/acerca.png");
		Image mando = new Image("files/mando.png");

		Image button = new Image("files/steam-button.png");
		Image ps3 = new Image("files/logos/PlayStation_3_logo.png");
		Image ps4 = new Image("files/logos/PlayStation_4_logo.png");
		Image wii = new Image("files/logos/Wii_logo.png");
		Image wiiu = new Image("files/logos/WiiU.png");
		Image x360 = new Image("files/logos/Xbox_360_logo.png");
		Image xOne = new Image("files/logos/Xbox_One_logo.png");

		CheckBox cbps3 = new CheckBox();
		CheckBox cbps4 = new CheckBox();
		CheckBox cb360 = new CheckBox();
		CheckBox cbOne = new CheckBox();
		CheckBox cbwii = new CheckBox();
		CheckBox cbwiiu = new CheckBox();

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");
		acercaDe.addStyleName("acerca");
		mando.setStyleName("centered");
		button.setStyleName("login-button");
		statusLabel.setStyleName("login-status");
		searchField.setStyleName("login-search");

		ps3.setStyleName("ps3_img");
		ps4.setStyleName("ps4_img");
		x360.setStyleName("x360_img");
		xOne.setStyleName("one_img");
		wii.setStyleName("wii_img");
		wiiu.setStyleName("wiiu_img");

		cbps3.setStyleName("cbps3");
		cbps4.setStyleName("cbps4");
		cb360.setStyleName("cb360");
		cbOne.setStyleName("cbOne");
		cbwii.setStyleName("cbwii");
		cbwiiu.setStyleName("cbwiiu");

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);
		mainPanel.add(mando);
		mainPanel.add(button);

		mainPanel.add(ps3);
		mainPanel.add(ps4);
		mainPanel.add(x360);
		mainPanel.add(xOne);
		mainPanel.add(wii);
		mainPanel.add(wiiu);

		mainPanel.add(statusLabel);
		mainPanel.add(searchField);

		/*-----------------------------------------------------------------------------------*/

		cbps3.setValue(false);
		cbps3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();

				if (checked) {
					platforms.add("ps3");
				} else {
					platforms.remove("ps3");
				}
			}
		});

		cbps4.setValue(false);
		cbps4.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					platforms.add("ps4");
				} else {
					platforms.remove("ps4");
				}
			}
		});

		cb360.setValue(false);
		cb360.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					platforms.add("360");
				} else {
					platforms.remove("360");
				}
			}
		});

		cbOne.setValue(false);
		cbOne.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					platforms.add("xbox one");
				} else {
					platforms.remove("xbox one");
				}
			}
		});

		cbwii.setValue(false);
		cbwii.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					platforms.add("wii");
				} else {
					platforms.remove("wii");
				}
			}
		});

		cbwiiu.setValue(false);
		cbwiiu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					platforms.add("wiiu");
				} else {
					platforms.remove("wiiu");
				}
			}
		});

		/*-------------------------------------------------------------------------------------------*/


		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("acerca", "", new HashSet<String>());
			}
		});
		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GamingGuru.go("init", "", new HashSet<String>());
			}
		});

		button.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				statusLabel.setText("Searching...");
				mainPanel.add(statusLabel);
				final String id = searchField.getText();
				GamingGuru.go("steam", id, platforms);
			}
		});

		mainPanel.add(cbps3);
		mainPanel.add(cbps4);
		mainPanel.add(cb360);
		mainPanel.add(cbOne);
		mainPanel.add(cbwii);
		mainPanel.add(cbwiiu);
	}
}
