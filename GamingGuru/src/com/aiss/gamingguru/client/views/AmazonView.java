package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AmazonView extends Composite {
	private Button searchButton = new Button("Search");
	private TextBox searchField = new TextBox();
	private Label statusLabel = new Label();
	private final GuruServiceAsync gService = GWT.create(GuruService.class);
	private final AbsolutePanel mainPanel;

	public AmazonView(Map<String, String> params) {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");
		Image acercaDe = new Image("files/acerca.png");

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");
		acercaDe.addStyleName("acerca");

		searchField.setText("Que quieres comprar?");
		statusLabel.setStyleName("style-VG-status");
		searchField.setStyleName("style-VG-search");
		searchButton.setStyleName("style-VG-button");

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);
		mainPanel.add(searchField);
		mainPanel.add(searchButton);

		searchField.setFocus(true);
		searchField.selectAll();

		searchButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				statusLabel.setText("Searching...");
				mainPanel.add(statusLabel);
				final String juego = searchField.getText();
				RootPanel.get("amazoninfo").clear();

				gService.getAmazon(juego, new AsyncCallback<List<String>>() {
					
					public void onSuccess(List<String> result) {
						showElement(result);
						mainPanel.remove(statusLabel);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("!Error al realizar la b�squeda de las ofertas!");
					}

				});

			}
		});

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("steaminfo").clear();
				GamingGuru.go("acerca", new HashMap<String, String>());
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("steaminfo").clear();
				GamingGuru.go("init", new HashMap<String, String>());
			}
		});

	}

	private void showElement(List<String> result) {
		String output = "<fieldset style='overflow: auto; top:20%; width: 200px; height: 300px;'>";
		output += "<legend style='font-weight: bold'> Tus compras </legend>";
		output += "<span> " + result.get(0)  + " </span>";
		output += "<span> " + result.get(1)  + " </span>";
		output += "<span> " + result.get(2)  + " </span>";
		output += "</fieldset>";
		HTML games = new HTML(output);
		games.setStyleName("style-VG-info");
		RootPanel.get("amazoninfo").add(games);

	}

}
