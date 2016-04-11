package com.aiss.gamingguru.client;

import com.aiss.gamingguru.client.views.ViewList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GamingGuru implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Panel p = RootPanel.get();
		p.clear();
		p.add(new ViewList());
	}
}
