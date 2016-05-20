package com.aiss.gamingguru.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.client.views.AcercaView;
import com.aiss.gamingguru.client.views.AmazonView;
import com.aiss.gamingguru.client.views.LoginView;
import com.aiss.gamingguru.client.views.SteamView;
import com.aiss.gamingguru.client.views.VideoGameInformationView;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.server.GuruServiceImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GamingGuru extends Composite implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get().clear();
		go("init", "", new ArrayList<Boolean>());
	}

	public static void go(String token, String id, ArrayList<Boolean> params) {
		Panel p = RootPanel.get();
		if (token == "init") {
			p.clear();
			p.add(new LoginView(params));
		} else if (token == "info") {
			p.clear();
			p.add(new VideoGameInformationView(params));
		} else if (token == "acerca") {
			p.clear();
			p.add(new AcercaView(params));
		} else if (token == "steam") {
			p.clear();
			p.add(new SteamView(id, params));
		} else if (token == "amazon") {
			p.clear();
			p.add(new AmazonView(params));
		}
	}

}
