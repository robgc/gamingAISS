package com.aiss.gamingguru.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.views.AcercaView;
import com.aiss.gamingguru.client.views.LoginView;
import com.aiss.gamingguru.client.views.SteamView;
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
		go("init", "", new HashSet<String>(), "", "");
	}

	public static void go(String token, String id, Set<String> params,
			String cat1, String cat2) {
		Panel p = RootPanel.get();
		if (token == "init") {
			p.clear();
			p.add(new LoginView(params));
		} else if (token == "acerca") {
			p.clear();
			p.add(new AcercaView(params));
		} else if (token == "steam") {
			p.clear();
			p.add(new SteamView(id, params, cat1, cat2));
		}
	}
}
