package com.aiss.gamingguru.client.views;

import java.util.List;

import com.aiss.gamingguru.client.GreetingService;
import com.aiss.gamingguru.client.GreetingServiceAsync;
import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewList extends Composite {

	private final GreetingServiceAsync GreetingService = GWT
			.create(GreetingService.class);

	private final FlexTable alumnosPanel;
	private final FlexTable bannerPanel;
	// private final HorizontalPanel mainPanel;
	private final AbsolutePanel mainPanel;

	public ViewList() {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);
		alumnosPanel = new FlexTable();
		bannerPanel = new FlexTable();

		ScheduledCommand cmd = new ScheduledCommand() {

			@Override
			public void execute() {
				// TODO Auto-generated method stub

			}
		};
		// Create a menu bar
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("1366px");
		menu.setAnimationEnabled(true);
		
		// Make some sub-menus that we will cascade from the top menu.
		menu.addItem("          ",true,cmd);
		menu.addItem("Acerca de", cmd);

		// Make a new menu bar, adding a few cascading menus to it.

		// Add it to the root panel.
		mainPanel.add(menu);
		// Create a sub menu of recent documents

		

	}

	public final void showAlumnos(List<Alumno> AlumnosSimple) {

		int i = 0;
		for (Alumno key : AlumnosSimple) {
			alumnosPanel.setWidget(i + 1, 0, new Label(key.getName()));
			alumnosPanel.setWidget(i + 1, 1, new Label(key.getEmail()));
			i++;
		}

	}
}
