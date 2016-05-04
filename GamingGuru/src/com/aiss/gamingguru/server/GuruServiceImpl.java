package com.aiss.gamingguru.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.taskdefs.Checksum.FormatElement;
import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.amazon.AmazonProduct;
import com.aiss.gamingguru.shared.amazon.AmazonProductImpl;
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
	// title, formattedPrice, hardwarePlatform, mediumImage
	private Double formattedPrices = 0.0;
	private SortedSet<String> mediumImages = new TreeSet<String>();
	private Set<AmazonProduct> prod = new HashSet<>();
	private String t;
	private String hardware;
	private String url;

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

	public String getAmazon(String juego) {

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
			System.out.println(requestUrl);

			DefaultHandler handler = new DefaultHandler() {

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

					if (qName.equalsIgnoreCase("MediumImage")) {
						mimage = true;
					}

					if (qName.equalsIgnoreCase("DetailPageURL")) {
						burl = true;
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					// System.out.println("End Element :" + qName);

				}
				
				AmazonProduct a;
				
				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (bfname) {
						String nombre = new String(ch, start, length);
						t = nombre;						
						System.out.println("Title : " + t);
						bfname = false;
					}

					if (blname) {
						String var = new String(ch, start, length);
						var = var.replace("EUR", "");
						var = var.replace(",", ".");
						var = var.trim();
						Double precio = new Double(var);
						formattedPrices = precio;
						System.out.println("FormattedPrice : " + formattedPrices);
						blname = false;
					}

					if (bnname) {
						String var = new String(ch, start, length);
						hardware = var;
						System.out.println("HardWarePlatform : " + hardware);
						bnname = false;
					}

					if (mimage) {
						String var = new String(ch, start, length);
						System.out.println("MediumImage : " + var);
						mediumImages.add(var);
						mimage = false;
					}

					if (burl) {
						String var = new String(ch, start, length);
						System.out.println("DetailPageURL : " + var);
						url = var;
						burl = false;
					}
					a = new AmazonProductImpl(t, formattedPrices, hardware, mediumImages, url);
	//				a.setPrecio(formattedPrices);
	//				System.out.println(formattedPrices);
					prod.add(a);
				
					// System.out.println("-------------------------------");
				}
			};

			mediumImages.clear();
			
			saxParser.parse(requestUrl, handler);

		} catch (Exception e) {
//			e.printStackTrace();
		}
		List<AmazonProduct> res = new ArrayList<>(prod);

		System.out.println(res.toString());

		
		return res.get(0).getNombre();
	}

}
