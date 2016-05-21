package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.Vg.Videojuego;
import com.aiss.gamingguru.shared.amazon.AmazonProduct;
import com.aiss.gamingguru.shared.amazon.AmazonProductImpl;
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
	private static Set<Integer> ids = new HashSet<Integer>();
	private final GuruServiceAsync gService = GWT.create(GuruService.class);
	private static Map<Integer, Videojuego> map = new HashMap<Integer, Videojuego>();
	private static Set<Videojuego> myGames = new HashSet<Videojuego>();
	private static Set<Videojuego> recommendedGames = new HashSet<Videojuego>();
	private static Double score = 0.0;

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
											score = doAverage(result);
											// showName(result, score);
											myGames.addAll(result);
											Set<Videojuego> mineTmp = new HashSet<Videojuego>(
													myGames);
											Set<Videojuego> allTmp = new HashSet<Videojuego>(
													map.values());

											allTmp.removeAll(mineTmp);

											gService.recommendedGames(
													score,
													"",
													"",
													allTmp,
													new AsyncCallback<Set<Videojuego>>() {

														@Override
														public void onFailure(
																Throwable caught) {
															Window.alert("¡No se encontraron recomendaciones");
														}

														@Override
														public void onSuccess(
																Set<Videojuego> vgs) {
															recommendedGames
																	.addAll(vgs);
															gService.getAmazon(
																	recommendedGames,
																	new AsyncCallback<Map<Videojuego, List<String>>>() {

																		@Override
																		public void onFailure(
																				Throwable caught) {
																			Window.alert("¡No se encontraron enalces de compra");

																		}

																		@Override
																		public void onSuccess(
																				Map<Videojuego, List<String>> map) {
																			showName(
																					recommendedGames,
																					map,
																					score);

																		}

																	});

														}
													});
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

	private static Double doAverage(Set<Videojuego> ids) {
		Double total = 0.0;
		for (Videojuego id : ids) {
			total += id.getNotaMedia();
		}

		return total / ids.size();

	}

	private static void showName(Set<Videojuego> ids,
			Map<Videojuego, List<String>> map, Double average) {
		String output = "<fieldset style='background-color: #1c3659;overflow: auto; width: 800px; height: 530px;'>";
		output += "<legend style='font-weight: bold'>TUS JUEGOS</legend>";
		for (Videojuego vg : ids) {
			output += "<fieldset>";

			output += "<span style='align: center;'>" + vg.getNombre()
					+ " </span><br/>";
			output += "<span style='align: center; font-weight:bold;'><img src='http://cdn.akamai.steamstatic.com/steam/apps/"
					+ vg.getId()
					+ "/header.jpg' style= 'width: 20%; height: 20%; float:left'></img><br/><br/>";

			// if (map.get(vg).get(1) != null) {
			// AmazonProduct a = new AmazonProductImpl(map.get(vg).get(1));
			// output +=
			// "<br/><span style='align: center; font-weight:bold;'><img src='"
			// + a.getImagen()
			// +
			// "' style= 'width: 20%; height: 20%; float:left'></img> <br/><a href='"
			// + a.getUrl()
			// + "' style='color:white'>"
			// + a.getNombre()
			// + "</a></span><br/>";
			// output += "<br/><span style='left:50%'>" + a.getPrecio()
			// + " €</span><br/>";
			// }
			output += "</fieldset>";

			output += "<fieldset>";

			output += "<br/><span style='align: center; font-weight:bold;'><a href='http://store.steampowered.com/app/"
					+ vg.getId()
					+ "/'><img border='3' src='files/steam-compra.jpg' width='20%' height='20%'></a></span><br/>";
			output += "</fieldset>";
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
