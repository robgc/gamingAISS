package com.aiss.gamingguru.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GuruServiceImpl extends RemoteServiceServlet implements
		GuruService {

	private static final long serialVersionUID = 6282625882306958152L;
	private static final String VGINFO_API_KEY = "MAuCGAqmOxmshId9rkkjcjcIGgO5p1AEIrHjsnnYkrHxoJLYHT";
	private static final String STEAM_API_KEY = "9A3D6024D560EA19E0346BBAC0F98324";

	@Override
	public CriticSearch getReviews(String juego) {
		juego = juego.replace(" ", "+");
		juego = juego.replace("\u2122", "");
		juego = juego.replace("\u2018", "'");
		juego = juego.replace("\u2019", "'");
		juego = juego.replace("\u00AE", "");

		// Para evitar errores pasamos todos los carácteres a minúscula
		juego = juego.toLowerCase();
		// Los espacios han de ser sustituidos por '+'

		ClientResource cr = new ClientResource(
				"https://ahmedakhan-game-review-information-v1.p.mashape.com/api/v1/information?game_name="
						+ juego);
		// La petición necesita que haya cierta información en la cabecera
		// (header)
		Series<Header> headers = cr.getRequest().getHeaders();

		// Añadimos a la cabecera X-Mashape-Key, necesaria para acceder a la API
		// como desarrollador
		headers.set("X-Mashape-Key", VGINFO_API_KEY);

		// Especificamos que se devuelva un JSON
		headers.set("Accept", "application/json");

		CriticSearch cs = cr.get(CriticSearch.class);
		return cs;
	}

	@Override
	public GameSearch getGames(String id) {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="
						+ STEAM_API_KEY + "&steamid=" + id + "&format=json");
		GameSearch cs = cr.get(GameSearch.class);
		return cs;
	}

	@Override
	public GameData getNameId() {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/ISteamApps/GetAppList/v2");
		GameData cs = cr.get(GameData.class);
		return cs;
	}
}
