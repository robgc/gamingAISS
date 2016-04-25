package com.aiss.gamingguru.server;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.amazon.Amazon;
import com.aiss.gamingguru.shared.amazon.SignedRequestsHelper;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GuruServiceImpl extends RemoteServiceServlet implements
		GuruService {

	private static final long serialVersionUID = 6282625882306958152L;
	private static final String VGINFO_API_KEY = "MAuCGAqmOxmshId9rkkjcjcIGgO5p1AEIrHjsnnYkrHxoJLYHT";
	private static final String STEAM_API_KEY = "9A3D6024D560EA19E0346BBAC0F98324";
	private static final String AWS_ACCESS_KEY_ID = "AKIAJGUGYKZ7LKCQMJ5Q";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "webservices.amazon.es";

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

		// Añadimos a la cabecera X-Mashape-Key, necesaria para acceder a la
		// API
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

	/*--------------------------------------------------------------------------------------------------------------*/

	public Amazon getAmazon() {
		SignedRequestsHelper helper = null;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String requestUrl = "";

		Map<String, String> params = new HashMap<String, String>();

		params.put("Service", "AWSECommerceService");
		params.put("Operation", "ItemSearch");
		params.put("AWSAccessKeyId", "AKIAJGUGYKZ7LKCQMJ5Q");
		params.put("AssociateTag", "gamingguru06-21");
		params.put("SearchIndex", "VideoGames");
		params.put("Keywords", "Bioshock");
		params.put("ResponseGroup", "Images,ItemAttributes,Offers");
		params.put("Sort", "price");
		params.put("Version", "2013-08-01");

		requestUrl = helper.sign(params);
				
		ClientResource cr = new ClientResource(requestUrl);
		Amazon am = cr.get(Amazon.class);
		return am;
	}

}
