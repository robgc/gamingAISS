package com.aiss.gamingguru.client.views;

import com.aiss.gamingguru.client.GreetingService;
import com.aiss.gamingguru.client.GreetingServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;

public class ViewList extends Composite {

	@SuppressWarnings("unused")
	private final GreetingServiceAsync GreetingService = GWT
			.create(GreetingService.class);

	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;

	public ViewList() {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		ScheduledCommand cmd = new ScheduledCommand() {

			@Override
			public void execute() {
				// TODO Auto-generated method stub

			}
		};
		// Create a menu bar
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setHeight("60px");
//		menu.getElement().setAttribute("menu", "menu");
		menu.setAnimationEnabled(true);

		// Make some sub-menus that we will cascade from the top menu.
		menu.addItem("          ", true, cmd);
		menu.addItem("Acerca de", cmd);

		// Make a new menu bar, adding a few cascading menus to it.

		// Add it to the root panel.
		Label manu = new Label(menu.getStyleName());
		Label munu = new Label(String.valueOf(menu.getOffsetHeight()));

		mainPanel.add(menu);
		mainPanel.add(munu);

		mainPanel.add(manu);
		// Create a sub menu of recent documents

	}

}
