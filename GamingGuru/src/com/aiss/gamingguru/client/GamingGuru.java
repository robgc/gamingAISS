package com.aiss.gamingguru.client;

import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.client.views.ViewList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GamingGuru implements EntryPoint {

	public void onModuleLoad() {
		Panel p = RootPanel.get();
		go("init", new HashMap<String, String>());
	}

	public static void go(String token, Map<String, String> params) {
		Panel p = RootPanel.get();
		if (token == "init") {
			p.clear();
			p.add(new ViewList(params));
		} else if (token == "info") {
//			p.add(new ViewCreate(params));
		}
	}
}
