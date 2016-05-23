package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.amazon.AmazonProduct;
import com.aiss.gamingguru.shared.amazon.AmazonProductImpl;
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

	public AmazonView(ArrayList<Boolean> params) {
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

		searchField.setText("¿Qué quieres comprar?");
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
//
//		searchButton.addClickHandler(new ClickHandler() {
//
//			public void onClick(ClickEvent event) {
//				statusLabel.setText("Searching...");
//				mainPanel.add(statusLabel);
//				final String juego = searchField.getText();
//				RootPanel.get("amazoninfo").clear();
//
//				gService.getAmazon(juego, new AsyncCallback<List<String>>() {
//
//					public void onSuccess(List<String> result) {
//						showElement(result);
//						mainPanel.remove(statusLabel);
//					}
//
//					public void onFailure(Throwable caught) {
//						Window.alert("!Error al realizar la búsqueda de las ofertas!");
//					}
//
//				});
//
//			}
//		});

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("amazoninfo").clear();
				GamingGuru.go("acerca", "", new ArrayList<Boolean>());
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("amazoninfo").clear();
				GamingGuru.go("init", "", new ArrayList<Boolean>());
			}
		});

	}

	private void showElement(List<String> result) {
		String output = "<fieldset style='background-color: #1c3659; overflow: auto; width: 800px; height: 530px;'>";
		output += "<legend style='font-weight: bold'> Tus compras </legend>";
		for (String ama : result) {
			AmazonProduct a = new AmazonProductImpl(ama);
			output += "<fieldset>";

			output += "<br/><span style='align: center; font-weight:bold;'><img src='"
					+ a.getImagen()
					+ "' style= 'width: 20%; height: 20%; float:left'></img> <br/><a href='"
					+ a.getUrl()
					+ "' style='color:white'>"
					+ a.getNombre()
					+ "</a></span><br/>";
			output += "<br/><span style='left:50%'>" + a.getPrecio()
					+ " €</span><br/>";
			output += "</fieldset>";

		}
		output += "</fieldset>";
		HTML games = new HTML(output);
		games.setStyleName("style-steam-info");
		RootPanel.get("amazoninfo").add(games);
	}

}
