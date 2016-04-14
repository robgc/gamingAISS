package com.aiss.gamingguru.server;

import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;
import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GuruServiceImpl extends RemoteServiceServlet implements GuruService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6282625882306958152L;
	private static final String VGINFO_API_KEY = "MAuCGAqmOxmshId9rkkjcjcIGgO5p1AEIrHjsnnYkrHxoJLYHT";

	@Override
	public CriticSearch getReviews(String juego) {
		juego = juego.replace(" ", "+");
		juego = juego.replace("\u2122", "");
		juego = juego.replace("\u2018", "'");
		juego = juego.replace("\u2019", "'");
		juego = juego.replace("\u00AE","");
		
		//Para evitar errores pasamos todos los carácteres a minúscula
		juego = juego.toLowerCase();
		// Los espacios han de ser sustituidos por '+'
		
		ClientResource cr = new ClientResource("https://ahmedakhan-game-review-information-v1.p.mashape.com/api/v1/information?game_name="+ juego);
		//La petición necesita que haya cierta información en la cabecera (header)
		Series<Header> headers = cr.getRequest().getHeaders();
		
		//Añadimos a la cabecera X-Mashape-Key, necesaria para acceder a la API como desarrollador
		headers.set("X-Mashape-Key", VGINFO_API_KEY);
		
		//Especificamos que se devuelva un JSON
		headers.set("Accept", "application/json");
		
		CriticSearch cs = cr.get(CriticSearch.class);
		return cs;
	}

}
