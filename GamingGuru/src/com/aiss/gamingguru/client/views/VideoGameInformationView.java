package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.google.gwt.core.shared.GWT;
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

public class VideoGameInformationView extends Composite {
	private Button searchButton = new Button("Search");
	private TextBox searchField = new TextBox();
	private Label statusLabel = new Label();
	private final AbsolutePanel mainPanel;
	private final GuruServiceAsync gService = GWT.create(GuruService.class);

	public VideoGameInformationView(ArrayList<Boolean> params) {

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

		searchField.setText("Game");

		statusLabel.setStyleName("style-VG-status");
		searchField.setStyleName("style-VG-search");
		searchButton.setStyleName("style-VG-button");

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);
		mainPanel.add(searchField);
		mainPanel.add(searchButton);

		// RootPanel.get("form").add(mainPanel);

		searchField.setFocus(true);
		searchField.selectAll();

		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				statusLabel.setText("Searching...");
				mainPanel.add(statusLabel);

				final String game = searchField.getText();
				RootPanel.get("gameinfo").clear();
				RootPanel.get("gameimg").clear();

				// gService.getScores(game,
				// new AsyncCallback<Map<String, String>>() {
				// @Override
				// public void onSuccess(Map<String, String> result) {
				// showReviews(game, result);
				// mainPanel.remove(statusLabel);
				// }
				//
				// @Override
				// public void onFailure(Throwable caught) {
				// Window.alert("¡Error al realizar la búsqueda de las críticas!");
				// }
				// });
			}
		});

		// searchButton.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// statusLabel.setText("Searching...");
		// mainPanel.add(statusLabel);
		//
		// final String game = searchField.getText();
		// RootPanel.get("gameinfo").clear();
		//
		// gService.getReviews(game, new AsyncCallback<CriticSearch>() {
		// @Override
		// public void onSuccess(CriticSearch result) {
		// showReviews(game, result);
		// mainPanel.remove(statusLabel);
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("¡Error al realizar la búsqueda de las críticas!");
		// }
		// });
		// }
		// });

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("gameinfo").clear();
				RootPanel.get("gameimg").clear();
				GamingGuru.go("acerca", "", new ArrayList<Boolean>());
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("gameinfo").clear();
				RootPanel.get("gameimg").clear();
				GamingGuru.go("init", "", new ArrayList<Boolean>());
			}
		});

		/*---------------------------------------------------------------------*/

	}

	/**
	 * Muestra en pantalla la información obtenida de un videojuego.
	 * 
	 * @param game
	 *            Nombre del videojuego
	 * @param result
	 *            Resultado (Clase JSON)
	 */

	private void showReviews(String name, Map<String, String> games) {

		String output = "<fieldset style='background-color:#1c3659'> ";
		output += "<legend style='font-weight: bold'>" + name.toUpperCase()
				+ " <br/>CRITICS </legend>";
		if (games != null) {

			output += "<br/><span> GAMESPOT: " + games.get("GAMESPOT")
					+ "/10 </span><br/><hr/>";
			output += "<br/><span> METACRITIC: " + games.get("METACRITIC")
					+ "/100 </span><br/><hr/>";

			output += "<br/><span> USER SCORE: " + games.get("USER")
					+ "/10</span>";

		} else {
			output += "<span> No results </span>";
		}
		output += "</fieldset>";

		HTML img = new HTML("<img src='" + games.get("IMAGE")
				+ "' style= 'width: 140px; height: 160px'></img>");
		img.setStyleName("cover-img");
		HTML res = new HTML(output);
		res.setStyleName("style-VG-info");
		RootPanel.get("gameimg").add(img);
		RootPanel.get("gameinfo").add(res);
	}
}
