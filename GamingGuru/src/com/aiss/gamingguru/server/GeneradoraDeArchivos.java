package com.aiss.gamingguru.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.restlet.resource.ClientResource;

import com.aiss.gamingguru.shared.steam.App;
import com.aiss.gamingguru.shared.steam.GameData;

public class GeneradoraDeArchivos {
	private static Connection c = null;
	private static int i = 0;

	public static void main(String[] args) {

		// test1();

		// test2();

		// test21();

		// testEtiqueta();

		// test3();

		// test31();

		// test4();

		openConnection();
		createTable();
		test5();
		printTable();
		closeConecction();

	}

	private static void test4() {
		try {
			String ruta = "war/files/data.txt";
			File fichero = new File(ruta);
			System.out.println(fichero.getAbsolutePath());

			if (fichero.exists()) {
				fichero.delete();
			}
			if (!fichero.exists()) {
				fichero.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
				Boolean escribe = true;
				List<App> juegos = new ArrayList<>();
				juegos.addAll(getNameId().getApplist().getApps());
				for (int i = 0; i < juegos.size(); i++) {
					if (!(juegos.get(i).getName().toLowerCase().contains("dlc")
							|| juegos.get(i).getName().toLowerCase()
									.contains("server")
							|| juegos.get(i).getName().toLowerCase()
									.contains("beta")
							|| juegos.get(i).getName().toLowerCase()
									.contains("demo")
							|| juegos.get(i).getName().toLowerCase()
									.contains("sdk")
							|| juegos.get(i).getName().toLowerCase()
									.contains("trailer")
							|| juegos.get(i).getName().toLowerCase()
									.contains("test")
							|| juegos.get(i).getName().toLowerCase()
									.contains("client") || getMediaPuntuacion(juegos
							.get(i).getName()) == 0.0)) {

						System.out.println("Juego " + i + " de "
								+ juegos.size() + " " + juegos.get(i).getName()
								+ ":");
						Double nota = getMediaPuntuacion(juegos.get(i)
								.getName());
						String etiquetas = getEtiquetas(juegos.get(i)
								.getAppid());
						bw.write(juegos.get(i).getAppid() + "#"
								+ juegos.get(i).getName() + "#" + etiquetas
								+ "#" + nota);
						bw.newLine();
						if (i % 10 == 0) {
							bw.flush();
						}
					}
				}

				bw.close();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void test5() {
		try {
			String ruta = "war/files/data.txt";
			File fichero = new File(ruta);
			System.out.println(fichero.getAbsolutePath());

			if (fichero.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(ruta));
				String line;

				while ((line = br.readLine()) != null) {
					addToTable(line);
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void openConnection() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:war/files/test.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeConecction() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable() {
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sql = "DROP TABLE GAMES";
			stmt.executeUpdate(sql);

			String sql1 = "CREATE TABLE GAMES "
					+ "(ID INTEGER PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT     NOT NULL, "
					+ " TAGS           TEXT     NOT NULL, "
					+ " SCORE        NUMERIC(3,2))";

			stmt.executeUpdate(sql1);
			stmt.close();

			System.out.println("Created table successfully");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	private static void addToTable(String s) {
		PreparedStatement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c.setAutoCommit(true);
			String sp[] = s.split("#");

			String sql = "INSERT INTO GAMES (ID,NAME,TAGS,SCORE) VALUES (?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(sp[0]));
			stmt.setString(2, sp[1]);
			stmt.setString(3, sp[2]);
			stmt.setDouble(4, Double.parseDouble(sp[3]));
			stmt.executeUpdate();

			System.out.println(sp[1] + " - " + i++);

			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error introduciendo en tabla");
			System.exit(0);
		}
	}

	private static void printTable() {
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c.setAutoCommit(true);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM GAMES;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tags = rs.getString("tags");
				Double score = rs.getDouble("score");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("TAGS = " + tags);
				System.out.println("SCORE = " + score + "\n");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	private static GameData getNameId() {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/ISteamApps/GetAppList/v2");
		GameData cs = cr.get(GameData.class);
		return cs;
	}

	private static String getEtiquetas(Integer juegoId) {
		Document doc;
		String etiquetas = "";
		try {
			doc = Jsoup
					.connect(
							"http://store.steampowered.com/app/" + juegoId
									+ "/")
					.userAgent(
							"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.6) Gecko/20070802 SeaMonkey/1.1.4")
					.timeout(5000).ignoreHttpErrors(true).get();
			Elements tags = doc.select(".app_tag");

			if (tags != null) {
				System.out.println("tag1:" + tags.get(0).ownText());
				System.out.println("tag2:" + tags.get(1).ownText());
				System.out.println("tag3:" + tags.get(2).ownText());
				System.out.println();
				etiquetas = tags.get(0).ownText() + "&" + tags.get(1).ownText()
						+ "&" + tags.get(2).ownText();
			} else {
				System.out.println("No hay tags");
				etiquetas = "No hay tags";
			}

		} catch (Exception e) {
			System.out.println("Sin etiquetas " + e.toString());
			etiquetas = "Sin etiquetas";
		}

		return etiquetas;
	}

	private static Double getMediaPuntuacion(String juego) {
		String minus = juego.replace(" -", "");
		minus.replace(" ", "-");
		minus.replace("'", "");
		minus = minus.replace(":", "");
		minus = minus.toLowerCase();
		System.out.println("\n---------" + minus + "\n---------");
		Double NotamMedia = getScores(minus);

		System.out.println(NotamMedia);
		return NotamMedia;
	}

	private static Double getScores(String juego) {
		juego = juego.trim();
		juego = juego.replace(" ", "-");
		juego = juego.replace("_", "-");
		juego = juego.toLowerCase();
		System.out.println(juego);
		Document doc;
		Double notaMedia = 0.0;

		try {
			doc = Jsoup
					.connect(
							"https://web.archive.org/web/20160510130703/http://www.gamespot.com/"
									+ juego + "/")
					.userAgent(
							"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.6) Gecko/20070802 SeaMonkey/1.1.4")
					.timeout(50000).ignoreHttpErrors(true).get();
			Element nota1 = doc.select("[itemprop=ratingValue]").first();
			Element nota2 = doc
					.select("[data-event-tracking=Tracking|games_overview|Kubrick|Metascore]")
					.first();
			Element nota3 = doc
					.select("[data-event-tracking=Tracking|games_overview|Kubrick|UserReviewScore]")
					.first();

			if (nota1 != null) {
				if (nota2 != null) {
					notaMedia += (new Double(nota2.ownText()) / 10);
				}

				if (nota3 != null) {
					notaMedia += new Double(nota3.ownText());
				}
				if (nota2 != null && nota3 != null) {
					return notaMedia / 2;
				} else {
					return notaMedia;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notaMedia;

	}

}
