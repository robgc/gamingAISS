package com.aiss.gamingguru.client.views;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.vg.Videojuego;
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
	private static Map<Videojuego, Set<String>> amazon = new HashMap<Videojuego, Set<String>>();
	private static Set<String> platforms = new HashSet<String>();
	private static String categoria1 = "";
	private static String categoria2 = "";

	public SteamView(String id, Set<String> plat, String cat1, String cat2) {
		categoria1 = cat1;
		categoria2 = cat2;
		platforms.addAll(plat);
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");
		Image acercaDe = new Image("files/acerca.png");
		final Image loading = new Image("files/loading-logo.gif");

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");
		acercaDe.addStyleName("acerca");
		loading.setStyleName("centered");

		mainPanel.add(acercaDe);
		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);
		mainPanel.add(loading);

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
							GamingGuru.go("init", "", new HashSet<String>(),
									"", "");
						}

						@Override
						public void onSuccess(Map<Integer, Videojuego> result) {

							map.putAll(result);

							gService.getMyGames(ids, map,
									new AsyncCallback<Set<Videojuego>>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("!Error al encontrar tus juegos en el mapa!");
											GamingGuru.go("init", "",
													new HashSet<String>(), "",
													"");
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
													categoria1,
													categoria2,
													allTmp,
													new AsyncCallback<Set<Videojuego>>() {

														@Override
														public void onFailure(
																Throwable caught) {
															Window.alert("¡No se encontraron recomendaciones");
															GamingGuru
																	.go("init",
																			"",
																			new HashSet<String>(),
																			"",
																			"");
														}

														@Override
														public void onSuccess(
																Set<Videojuego> vgs) {
															recommendedGames
																	.addAll(vgs);
															gService.getAmazon(
																	platforms,
																	recommendedGames,
																	new AsyncCallback<Map<Videojuego, Set<String>>>() {

																		@Override
																		public void onFailure(
																				Throwable caught) {
																			Window.alert("¡No se encontraron enalces de compra");
																			GamingGuru
																					.go("init",
																							"",
																							new HashSet<String>(),
																							"",
																							"");
																		}

																		@Override
																		public void onSuccess(
																				Map<Videojuego, Set<String>> tmp) {
																			amazon.putAll(tmp);

																			mainPanel
																					.remove(loading);
																			showName(
																					recommendedGames,
																					tmp,
																					score);
																			score = 0.0;
																			recommendedGames
																					.clear();
																			amazon.clear();
																			platforms
																					.clear();
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
				GamingGuru.go("acerca", "", new HashSet<String>(), "", "");
			}
		});

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("steaminfo").clear();
				RootPanel.get("steamscore").clear();
				GamingGuru.go("init", "", new HashSet<String>(), "", "");
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

	private static void showName(Set<Videojuego> rec,
			Map<Videojuego, Set<String>> map, Double average) {

		String output = "<fieldset style='background-color: #101e32;overflow: auto; width: 800px; height: 52px;'>";
		output += "<span style='align: center;'><img src='files/steam-icon.png' style= 'padding-left:15px; padding-top:5px; width: 40px; height: 40px; float:left'></img>"
				+ "<div style='padding-left:25px;padding-top:10px; font-size: 2.5em; font-weight:bold;float:left;'>JUEGOS</div>"
				+ "<img src='files/score-icon.png' style= 'padding-left:300px; padding-top:10px; width: 150px; height: 35px;float:left;'></img>";
		output += "</span>";
		output += "</fieldset>";

		output += "<fieldset style='background-color: #1c3659;overflow: auto; width: 800px; height: 450px;'>";
		output += "<legend style='font-weight: bold'>TE RECOMENDAMOS</legend>";
		for (Videojuego vg : rec) {

			output += "<fieldset style='display:inline-block; vertical-align:middle;'>";

			output += "<span style='align: center;'>" + vg.getNombre()
					+ " </span><br/>";
			output += "<span style='align: center; font-weight:bold;'><img src='http://cdn.akamai.steamstatic.com/steam/apps/"
					+ vg.getId()
					+ "/header.jpg' style= 'width: 20%; height: 20%; float:left'></img>";
			output += "<div style='vertical-align:middle; display:inline-block; vertical-align:center; padding-left:380px; padding-top:20px; font-size: 3em; font-weight:bold;'> NOTA: "
					+ vg.getNotaMedia() + " </div></span><br/>";

			output += "<br/><br/><hr/></br><span style='align: center; font-weight:bold;'><a href='http://store.steampowered.com/app/"
					+ vg.getId()
					+ "/'><img border='3' style='display:inline-block;' src='files/steam-compra.jpg' width='20%' height='20%'></a></span><br/>"
					+ "<div style='vertical-align:middle; display:inline-block; vertical-align:center; padding-left:450px; padding-top:0px; font-size: 3em; font-weight:bold;'> PRECIO: "
					+ vg.getPrecio() + " </div></span><br/>";

			for (Set<String> set : map.values()) {
				for (String a : set) {
					if (a.contains(vg.getNombre())
							|| a.toLowerCase().contains(
									vg.getNombre().toLowerCase())) {
						AmazonProduct b = new AmazonProductImpl(a);

						output += "<hr/><span style='align: center; font-weight:bold;'><a href='"
								+ b.getUrl()
								+ "'><img border='3' src='files/amazon-compra.jpg' style='float:left;width:20%;height:20%;'></a>"
								+ "<div style='vertical-align:middle; display:inline-block; vertical-align:center; padding-left:450px; padding-top:0px; font-size: 3em; font-weight:bold;'> PRECIO: "
								+ b.getPrecio().toString()
								+ "€ </div></span><br/>";
					}
				}
			}
			output += "</fieldset>";

		}
		output += "</fieldset>";

		HTML games = new HTML(output);
		games.setStyleName("style-steam-info");
		RootPanel.get("steaminfo").add(games);
	}
}
