package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.Vg.Videojuego;
import com.aiss.gamingguru.shared.steam.Game;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class SteamView extends Composite {
	private Label statusLabel = new Label();
	private final AbsolutePanel mainPanel;
	private Set<Integer> ids = new HashSet<Integer>();
	private static Double average = 0.0;
	private final GuruServiceAsync gService = GWT.create(GuruService.class);
	private Map<Integer, Videojuego> map = new HashMap<Integer, Videojuego>();
	private Set<Videojuego> myGames = new HashSet<Videojuego>();

	public SteamView(String id, ArrayList<Boolean> params) {

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

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);

		gService.getGames(id, new AsyncCallback<GameSearch>() {

			@Override
			public void onSuccess(GameSearch result) {
				if (result != null) {
					for (Game g : result.getResponse().getGames()) {
						ids.add(g.getAppid());
					}

					mainPanel.remove(statusLabel);

					gService.fillMap(new AsyncCallback<Map<Integer, Videojuego>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("!Error al realizar el rellenado de la tabla!");
						}

						@Override
						public void onSuccess(Map<Integer, Videojuego> result) {

							map.putAll(result);

							gService.getMyGames(ids, map,
									new AsyncCallback<Set<Videojuego>>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("!Error al encontrar tus juegos en el mapa!");

										}

										@Override
										public void onSuccess(
												Set<Videojuego> result) {
											showName(result, doAverage(result));
										}
									});
						}
					});
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("!Error al realizar la búsqueda de los juegos!");
			}
		});
		/*----------------------------------------HACE LA MEDIA-------------------------------*/

		/*------------------------------------------------------------------------------------*/

		acercaDe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("steaminfo").clear();
				RootPanel.get("steamscore").clear();
				GamingGuru.go("acerca", "", new ArrayList<Boolean>());
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("steaminfo").clear();
				RootPanel.get("steamscore").clear();
				GamingGuru.go("init", "", new ArrayList<Boolean>());
			}
		});

		/*---------------------------------------------------------------------*/
	}

	private Double doAverage(Set<Videojuego> ids) {
		Double total = 0.0;
		for (Videojuego id : ids) {
			total += id.getNotaMedia();
		}

		return total / ids.size();

	}

	private void showName(Set<Videojuego> ids, Double average) {
		int i = 1;
		String output = "<fieldset style='background-color: #1c3659;overflow: auto; width: 500px; height: 330px;'>";
		output += "<legend style='font-weight: bold'>TUS JUEGOS</legend>";
		for (Videojuego id : ids) {
			output += "<span style='align:center'> Game " + i++ + ": "
					+ id.getNombre() + " - " + id.getNotaMedia()
					+ " </span><br/>";
		}

		output += "</fieldset>";

		String score = "<fieldset style='background-color: #1c3659;top: 60%;overflow: auto; width: 100px;'>";
		score += "<legend style='font-weight: bold'>TU NOTA</legend>";
		score += "<span style='align:center'> " + average + " </span><br/>";
		score += "</fieldset>";

		HTML totalScore = new HTML(score);
		totalScore.setStyleName("style-steam-score");
		RootPanel.get("steamscore").add(totalScore);

		HTML games = new HTML(output);
		games.setStyleName("style-steam-info");
		RootPanel.get("steaminfo").add(games);
	}
}
