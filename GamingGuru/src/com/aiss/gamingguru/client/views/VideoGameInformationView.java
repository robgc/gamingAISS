package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
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

	public VideoGameInformationView(Map<String, String> params) {

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
				// TODO Auto-generated method stub
				statusLabel.setText("Searching...");
				mainPanel.add(statusLabel);

				final String game = searchField.getText();
				RootPanel.get("gameinfo").clear();

				gService.getReviews(game, new AsyncCallback<CriticSearch>() {

					@Override
					public void onSuccess(CriticSearch result) {
						showReviews(game, result);
						mainPanel.remove(statusLabel);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("¡Error al realizar la búsqueda de las críticas!");
					}
				});
			}
		});

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("gameinfo").clear();
				GamingGuru.go("acerca", new HashMap<String, String>());
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("gameinfo").clear();
				GamingGuru.go("init", new HashMap<String, String>());
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

	private void showReviews(String game, CriticSearch result) {

		if (game == "League of Legends" || game == "LoL"
				|| game == "league of legends") {
			String output = "<fieldset>";
			output += "<legend>" + game + " Critics </legend>";
			if (result != null) {
				output += "<span> EN MI MASHUP NO SE BUSCA PUTA MIERDA</span>";
			}
			output += "</fieldset>";
			output += "<img src = 'http://25.media.tumblr.com/f1c1785b4bb395b4ba1a3afc551cec5d/tumblr_mzv66qO6C81selimao1_500.gif'/>";

			HTML games = new HTML(output);
			games.setStyleName("style-VG-info");

			RootPanel.get("gameinfo").add(games);
		} else {
			String output = "<fieldset>";
			output += "<legend style='font-weight: bold'>" + game.toUpperCase() + " CRITICS </legend>";
			if (result != null) {
				output += "<span style='font-weight: bold'>IGN</span>";
				output += "<br/><span> User score: "
						+ result.getResult().getIgn().getUserScore()
						+ " </span>";
				output += "<br/><span> Critic score: "
						+ result.getResult().getIgn().getCriticScore()
						+ " </span><br/><hr/>";

				output += "<br/><span style='font-weight: bold'>Metacritic</span><br/>";
				output += "<span> User score: "
						+ result.getResult().getMetacritic().getUserScore()
						+ " </span>";
				output += "<br/><span> Critic score: "
						+ result.getResult().getMetacritic().getCriticScore()
						+ " </span>";
			} else {
				output += "<span> No results </span>";
			}
			output += "</fieldset>";

			HTML games = new HTML(output);
			games.setStyleName("style-VG-info");

			RootPanel.get("gameinfo").add(games);
		}

	}
}