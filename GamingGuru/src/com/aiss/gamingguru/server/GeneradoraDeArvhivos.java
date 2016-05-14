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
			String ruta = "C:/Users/Jose Daniel/git/gamingAISS/GamingGuru/war/files";
			File fichero = new File(ruta);

			if (!fichero.exists()) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

				for (App app : getNameId().getApplist().getApps()) {
					bw.write(app.getAppid() + "#" + app.getName() + "#");

				}

				if (fichero.createNewFile()) {
					System.out.println("Se ha creado el archivo");
				} else {
					System.out.println("Inténtalo de nuevo más tarde");
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
