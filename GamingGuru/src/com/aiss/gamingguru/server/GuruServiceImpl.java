package com.aiss.gamingguru.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.shared.amazon.Amazon;
import com.aiss.gamingguru.shared.amazon.SignedRequestsHelper;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GuruServiceImpl extends RemoteServiceServlet implements GuruService {

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
				"https://ahmedakhan-game-review-information-v1.p.mashape.com/api/v1/information?game_name=" + juego);
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
		ClientResource cr = new ClientResource("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="
				+ STEAM_API_KEY + "&steamid=" + id + "&format=json");
		GameSearch cs = cr.get(GameSearch.class);
		return cs;
	}

	@Override
	public GameData getNameId() {
		ClientResource cr = new ClientResource("http://api.steampowered.com/ISteamApps/GetAppList/v2");
		GameData cs = cr.get(GameData.class);
		return cs;
	}

	/*--------------------------------------------------------------------------------------------------------------*/

	
	public Amazon getAmazon() {
		SignedRequestsHelper helper = null;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
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
		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder db = dbf.newDocumentBuilder();
		// Document doc = db.parse(new URL(requestUrl));

//		 XMLReader myReader = XMLReaderFactory.createXMLReader();
//		 myReader.setContentHandler(handler);
//		 myReader.parse(new InputSource(new URL(requestUrl).openStream()));
		

		

		    try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

			boolean bfname = false;
			boolean blname = false;
			boolean bnname = false;
			boolean bsalary = false;

			public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException {

				System.out.println("Start Element :" + qName);

				if (qName.equalsIgnoreCase("FIRSTNAME")) {
					bfname = true;
				}

				if (qName.equalsIgnoreCase("LASTNAME")) {
					blname = true;
				}

				if (qName.equalsIgnoreCase("NICKNAME")) {
					bnname = true;
				}

				if (qName.equalsIgnoreCase("SALARY")) {
					bsalary = true;
				}

			}

			public void endElement(String uri, String localName,
				String qName) throws SAXException {

				System.out.println("End Element :" + qName);

			}

			public void characters(char ch[], int start, int length) throws SAXException {

				if (bfname) {
					System.out.println("First Name : " + new String(ch, start, length));
					bfname = false;
				}

				if (blname) {
					System.out.println("Last Name : " + new String(ch, start, length));
					blname = false;
				}

				if (bnname) {
					System.out.println("Nick Name : " + new String(ch, start, length));
					bnname = false;
				}

				if (bsalary) {
					System.out.println("Salary : " + new String(ch, start, length));
					bsalary = false;
				}

			}

		     };

		       saxParser.parse(requestUrl, handler);
		 
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		  



//		URL u;
//		InputStream is = null;
//		DataInputStream dis;
//		String s;
//		File x = new File("amazonXML.txt");
//		
//		try {
//			FileWriter f = new FileWriter(x);
//			u = new URL(requestUrl);
//			is = u.openStream();
//			dis = new DataInputStream(new BufferedInputStream(is));
//			while ((s = dis.readUTF()) != null) {
//				f.write(s);
//			}
//			f.close();
//			is.close();
//			
//		} catch (MalformedURLException mue) {
//			System.err.println("Ouch - a MalformedURLException happened.");
//			mue.printStackTrace();
//			System.exit(2);
//		} catch (IOException ioe) {
//			System.err.println("Oops- an IOException happened.");
//			ioe.printStackTrace();
//			System.exit(3);
//		}
		Amazon a = new Amazon();
		return a;

	}

}
