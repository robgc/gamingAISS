package com.aiss.gamingguru.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.restlet.resource.ClientResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.amazon.SignedRequestsHelper;
import com.aiss.gamingguru.shared.steam.App;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GuruServiceImpl extends RemoteServiceServlet implements
		GuruService {

	private static final long serialVersionUID = 6282625882306958152L;
	// private static final String VGINFO_API_KEY =
	// "MAuCGAqmOxmshId9rkkjcjcIGgO5p1AEIrHjsnnYkrHxoJLYHT";
	private static final String STEAM_API_KEY = "9A3D6024D560EA19E0346BBAC0F98324";
	private static final String AWS_ACCESS_KEY_ID = "AKIAJGUGYKZ7LKCQMJ5Q";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "webservices.amazon.es";
	// title, formattedPrice, hardwarePlatform, mediumImage
	private Double formattedPrices = 0.0;
	private String mediumImage = "";
	private List<String> prod;
	private String t;
	private String hardware;
	private String url;

	// public CriticSearch getReviews(String juego) {
	// juego = juego.replace(" ", "+");
	// juego = juego.replace("\u2122", "");
	// juego = juego.replace("\u2018", "'");
	// juego = juego.replace("\u2019", "'");
	// juego = juego.replace("\u00AE", "");
	//
	// // Para evitar errores pasamos todos los carácteres a minúscula
	// juego = juego.toLowerCase();
	// // Los espacios han de ser sustituidos por '+'
	//
	// ClientResource cr = new ClientResource(
	// "https://ahmedakhan-game-review-information-v1.p.mashape.com/api/v1/information?game_name="
	// + juego);
	// // La petición necesita que haya cierta información en la cabecera
	// // (header)
	// Series<Header> headers = cr.getRequest().getHeaders();
	//
	// // Añadimos a la cabecera X-Mashape-Key, necesaria para acceder a la
	// // API
	// // como desarrollador
	// headers.set("X-Mashape-Key", VGINFO_API_KEY);
	//
	// // Especificamos que se devuelva un JSON
	// headers.set("Accept", "application/json");
	//
	// CriticSearch cs = cr.get(CriticSearch.class);
	// return cs;
	// }

	public Map<String, String> getScores(String juego) {
		juego = juego.trim();
		juego = juego.replace(" ", "-");
		juego = juego.toLowerCase();
		Document doc;
		Map<String, String> juegos = new HashMap<String, String>();
		try {
			doc = Jsoup
					.connect("http://www.gamespot.com/" + juego + "/")
					.userAgent(
							"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.6) Gecko/20070802 SeaMonkey/1.1.4")
					.ignoreHttpErrors(true).get();
			Element nota1 = doc.select("[itemprop=ratingValue]").first();
			Element nota2 = doc
					.select("[data-event-tracking=Tracking|games_overview|Kubrick|Metascore]")
					.first();
			Element nota3 = doc
					.select("[data-event-tracking=Tracking|games_overview|Kubrick|UserReviewScore]")
					.first();
			Element imag = doc.select("[itemprop=image]").first();
			String img = imag.absUrl("src");
			if (img != null) {
				juegos.put("IMAGE", img);
			}

			if (nota1 != null) {
				juegos.put("GAMESPOT", nota1.ownText());
			} else {
				juegos.put("GAMESPOT", "No score");
			}

			if (nota2 != null) {
				juegos.put("METACRITIC", nota2.ownText());
			} else {
				juegos.put("METACRITIC", "No score");
			}

			if (nota3 != null) {
				juegos.put("USER", nota3.ownText());
			} else {
				juegos.put("USER", "No score");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return juegos;

	}

	/*--------------------------------------------------------------------------------------------------------------*/

	public GameSearch getGames(String id) {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="
						+ STEAM_API_KEY + "&steamid=" + id + "&format=json");
		GameSearch cs = cr.get(GameSearch.class);
		return cs;
	}

	public GameData getNameId() {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/ISteamApps/GetAppList/v2");
		GameData cs = cr.get(GameData.class);
		return cs;
	}

	/*--------------------------------------------------------------------------------------------------------------*/

	public List<String> getAmazon(String juego) {
		prod = new ArrayList<>();

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
		params.put("Keywords", juego);
		params.put("ResponseGroup", "Images,ItemAttributes,Offers");
		params.put("Sort", "price");
		params.put("Version", "2013-08-01");

		requestUrl = helper.sign(params);

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				int i = 0;
				Double prize = null;
				String a = "";

				boolean bfname = false;
				boolean blname = false;
				boolean bnname = false;
				boolean mimage = false;
				boolean burl = false;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					// System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("Title")) {
						bfname = true;
					}

					if (qName.equalsIgnoreCase("FormattedPrice")) {
						blname = true;
					}

					if (qName.equalsIgnoreCase("HardWarePlatform")) {
						bnname = true;
					}

					if (qName.equalsIgnoreCase("MediumImage") && i == 0) {
						i++;
						mimage = true;
					}

					if (qName.equalsIgnoreCase("DetailPageURL")) {
						burl = true;
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equalsIgnoreCase("Item")) {
						i = 0;
						prize = null;
						a = t + "#" + formattedPrices + "#" + hardware + "#"
								+ mediumImage + "#" + url;
						prod.add(a);
					}

					if (qName.equalsIgnoreCase("ItemSearchResponse")) {
						formattedPrices = 0.0;
						mediumImage = "";
						t = "";
						hardware = "";
						url = "";
					}

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (bfname) {
						String nombre = new String(ch, start, length);
						t = nombre;
						bfname = false;
					}

					if (blname) {
						String var = new String(ch, start, length);
						var = var.replace("EUR", "");
						var = var.replace(",", ".");
						var = var.trim();
						Double precio = new Double(var);
						if (prize == null || precio.compareTo(prize) < 0) {
							prize = precio;
						}
						formattedPrices = prize;
						blname = false;
					}

					if (bnname) {
						String var = new String(ch, start, length);
						hardware = var;
						bnname = false;
					}

					if (mimage) {
						String var = new String(ch, start, length);
						mediumImage = var;
						mimage = false;
					}

					if (burl) {
						String var = new String(ch, start, length);
						url = var;
						burl = false;
					}
				}

			};
			System.out.println(requestUrl);
			saxParser.parse(requestUrl, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

	/*--------------------------------------------------------------------------------------------------------------*/

	public void createSteamFile() {
		// try {
		// File archivoConJuegos = new File(
		// "/war/files/juegos.txt");
		// FileWriter escribir = new FileWriter(archivoConJuegos, true);
		//
		// for(App app : getNameId().getApplist().getApps()) {
		// escribir.write(app.getAppid() + "#" + getNameId() + "#");
		// }
		//
		//
		// escribir.close();
		// } catch (Exception e) {
		// System.out.println(e.toString());
		// }
		try {
			String ruta = "war/files/data.txt";
			File fichero = new File(ruta);
			System.out.println(fichero.getAbsolutePath());

			if (!fichero.exists()) {
				fichero.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

				for (App app : getNameId().getApplist().getApps()) {
					bw.write(app.getAppid() + "#" + app.getName() + "#");
					bw.newLine();
				}

				bw.close();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
