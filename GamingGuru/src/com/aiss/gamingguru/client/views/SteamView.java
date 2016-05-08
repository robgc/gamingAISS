package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.steam.App;
import com.aiss.gamingguru.shared.steam.Game;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
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

public class SteamView extends Composite {
	private Button searchButton = new Button("Search");
	private TextBox searchField = new TextBox();
	private Label statusLabel = new Label();
	private final AbsolutePanel mainPanel;
	private Set<Integer> ids = new HashSet<Integer>();
	private final GuruServiceAsync gService = GWT.create(GuruService.class);

	public SteamView(Map<String, String> params) {

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

		searchField.setText("Your id");

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

			public void onClick(ClickEvent event) {
				statusLabel.setText("Searching...");
				mainPanel.add(statusLabel);
				final String game = searchField.getText();
				RootPanel.get("steaminfo").clear();

				gService.getGames(game, new AsyncCallback<GameSearch>() {

					@Override
					public void onSuccess(GameSearch result) {
						showId(game, result);
						mainPanel.remove(statusLabel);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("!Error al realizar la b�squeda de las cr�ticas!");
					}
				});
				gService.getNameId(new AsyncCallback<GameData>() {

					@Override
					public void onSuccess(GameData result) {
						showName(ids, result);
						ids.clear();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("¡Error al realizar la búsqueda de los juegos!");
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

	private void showId(String game, GameSearch result) {
		int i = 1;
		String output = "<fieldset style='overflow: auto; top:20%; width: 200px; height: 300px;'>";
		output += "<legend style='font-weight: bold'>" + game.toUpperCase()
				+ " ID </legend>";
		if (result != null) {
			for (Game a : result.getResponse().getGames()) {
				ids.add(a.getAppid());

			}
		} else {
			output += "<span> No results </span>";
			output += "</fieldset>";
			HTML games = new HTML(output);
			games.setStyleName("style-VG-info");
			RootPanel.get("steaminfo").add(games);
		}

	}

	private void showName(Set<Integer> ids, GameData result) {
		int i = 1;
		String output = "<fieldset style='overflow: auto; width: 500px; height: 300px;'>";
		output += "<legend style='font-weight: bold'>TUS JUEGOS</legend>";
		if (result != null) {
			for (Integer id : ids) {
				for (App a : result.getApplist().getApps())
					if (a.getAppid().equals(id)) {
						output += "<span style='align:center'> Game " + i++
								+ ": " + a.getName() + " </span><br/>";
					}
			}
		} else {
			output += "<span> No results </span>";
		}
		output += "</fieldset>";
		HTML games = new HTML(output);
		games.setStyleName("style-VG-info");
		RootPanel.get("steaminfo").add(games);
	}

}
