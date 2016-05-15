package com.aiss.gamingguru.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.restlet.resource.ClientResource;

import com.aiss.gamingguru.shared.steam.App;
import com.aiss.gamingguru.shared.steam.GameData;

public class GeneradoraDeArvhivos {

	public static void main(String[] args) {
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
	
	public static GameData getNameId() {
		ClientResource cr = new ClientResource(
				"http://api.steampowered.com/ISteamApps/GetAppList/v2");
		GameData cs = cr.get(GameData.class);
		return cs;
	}

}
