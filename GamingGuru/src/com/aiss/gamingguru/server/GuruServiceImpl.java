package com.aiss.gamingguru.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.restlet.resource.ClientResource;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.Vg.Videojuego;
import com.aiss.gamingguru.shared.amazon.SignedRequestsHelper;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GuruServiceImpl extends RemoteServiceServlet implements
		GuruService {

	private static final long serialVersionUID = 6282625882306958152L;

	private static final String STEAM_API_KEY = "9A3D6024D560EA19E0346BBAC0F98324";
	private static final String AWS_ACCESS_KEY_ID = "AKIAJGUGYKZ7LKCQMJ5Q";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "webservices.amazon.es";
	private Double formattedPrices = 0.0;
	private String mediumImage = "";
	private Map<Videojuego, String> map = new HashMap<Videojuego, String>();
	private String t;
	private String hardware;
	private String url;
	private int flag = 0;
	private Videojuego vg;

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
			System.out.println(doc.data());
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
		String url = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="
				+ STEAM_API_KEY + "&steamid=" + id + "&format=json";
		System.out.println(url);
		ClientResource cr = new ClientResource(url);
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

	public Set<Videojuego> recommendedGames(Double score, String cat1,
			String cat2, Set<Videojuego> vgs) {
		Double cotaSup = score + 0.5;
		Double cotaInf = score - 0.5;
		Set<Videojuego> res = new HashSet<Videojuego>();
		List<Videojuego> tmp = new ArrayList<Videojuego>();
		for (Videojuego game : vgs) {
			if (game.getNotaMedia() >= cotaInf
					&& game.getNotaMedia() <= cotaSup) {
				tmp.add(game);
			}
		}
		for (int i = 0; i < 5; i++) {
			int random = (int) (Math.random() * tmp.size());
			res.add(tmp.get(random));
			System.out.println(tmp.get(random).getNombre());
		}

		return res;
	}

	public Map<Videojuego, String> getAmazon(Set<Videojuego> juegos) {
		for (Videojuego game : juegos) {
			vg = game;
			flag = 0;

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
			params.put("Keywords", game.getNombre());
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
							a = t + "#" + formattedPrices + "#" + hardware
									+ "#" + mediumImage + "#" + url;
							if (flag == 0
									&& (a.contains(vg.getNombre())
											|| a.toLowerCase().contains(
													vg.getNombre()
															.toLowerCase()) || a
											.toUpperCase().contains(
													vg.getNombre()
															.toUpperCase()))) {
								System.out.println(a);
								map.put(vg, a);
								flag = 1;
							}

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
				saxParser.parse(requestUrl, handler);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/*--------------------------------------------------------------------------------------------------------------*/

	public Map<Integer, Videojuego> fillMap() {
		Map<Integer, Videojuego> map = new HashMap<Integer, Videojuego>();
		try {
			String ruta = "files/data.txt";
			File fichero = new File(ruta);
			if (fichero.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(ruta));
				String line;
				while ((line = br.readLine()) != null) {
					String[] sp = line.split("#");
					Videojuego a = new Videojuego(sp[0] + "#" + sp[1] + "#"
							+ sp[2] + "#" + sp[3]);
					map.put(new Integer(sp[0]), a);
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;
	}

	public Set<Videojuego> getMyGames(Set<Integer> ids,
			Map<Integer, Videojuego> map) {

		Set<Videojuego> games = new HashSet<Videojuego>();

		for (Integer id : ids) {
			games.add(map.get(id));
		}

		games.remove(null);
		return games;
	}
	// private static void openConnection() {
	// try {
	// c = DriverManager.getConnection("jdbc:sqlite:files/test.db");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }

	// private Double calculaMedia(List<Integer> ids) {
	// Statement stmt = null;
	// int cont = 0;
	// double total = 0.0;
	// try {
	// Class.forName("org.sqlite.JDBC");
	// c.setAutoCommit(true);
	// System.out.println("Opened database successfully");
	// stmt = c.createStatement();
	// for (Integer i : ids) {
	// ResultSet rs = stmt
	// .executeQuery("SELECT * FROM GAMES WHERE ID = '" + i
	// + "';");
	// int id = rs.getInt("id");
	// String name = rs.getString("name");
	// Double score = rs.getDouble("score");
	// System.out.println("ID = " + id);
	// System.out.println("NAME = " + name);
	// System.out.println("SCORE = " + score + "\n");
	// cont++;
	// total += score;
	// rs.close();
	// }
	// stmt.close();
	// } catch (Exception e) {
	// System.err.println(e.getClass().getName() + ": " + e.getMessage());
	// System.exit(0);
	// }
	// return total / cont;
	//
	// }

	// private static void closeConecction() {
	// try {
	// c.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

}
