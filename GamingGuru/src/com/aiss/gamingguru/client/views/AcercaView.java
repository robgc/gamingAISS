package com.aiss.gamingguru.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.client.GamingGuru;
import com.aiss.gamingguru.client.GreetingService;
import com.aiss.gamingguru.client.GreetingServiceAsync;
import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class AcercaView extends Composite {
	// private final HorizontalPanel mainPanel;
	private final GreetingServiceAsync GreetingService = GWT
			.create(GreetingService.class);

	private final AbsolutePanel mainPanel;

	public AcercaView( Set<String> params) {
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);

		MenuBar menu = new MenuBar();
		Image icon = new Image("files/mando.png");
		Image fondo = new Image("files/negro.png");

		fondo.setStyleName("background");
		menu.setStyleName("menu");
		icon.setStyleName("menuIcon");

		mainPanel.add(fondo);
		mainPanel.add(icon);
		mainPanel.add(menu);

		GreetingService.getAlumnos(new AsyncCallback<List<Alumno>>() {

			public void onSuccess(List<Alumno> result) {
				showAlumnos(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error loading contacts: " + caught.getMessage());

			}
		});

		String desc = "<fieldset>";
		desc += "<span style='align: center; font-weight:bold;'>\nPresentamos GamingGuru. Una aplicación capaz de analizar tu "
				+ "\nbiblioteca de Steam y ofrecerte recomendaciones personalizadas"
				+ "\nsobre los juegos de tu género favorito en función de los valores "
				+ "\nanalíticos de los juegos de dicha biblioteca. También te ofreceremos "
				+ "\nenlaces directos a la compra de tus recomendaciones en "
				+ "\nplataformas como Amazon o la propia tienda de Steam dejando a "
				+ "\ntu elección para qué plataforma deseas hacer la compra.<br/></span>";
		desc += "</fieldset>";

		HTML description = new HTML(desc);
		description.setStyleName("description");
		RootPanel.get("description").add(description);
		// RootPanel.get("table").add(alumnosPanel);

		icon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get("description").clear();
				RootPanel.get("table").clear();
				GamingGuru.go("init", "", new HashSet<String>());
			}
		});
	}

	public final void showAlumnos(List<Alumno> AlumnosSimple) {

		String output = "<table>" + "<tr>" + "<td>EMAIL</td>"
				+ "<td>NOMBRE</td>" + "</tr>" + "<tr>"
				+ "<td>rodanber@gmail.com</td>"
				+ "<td>Roberto García Calero</td>" + "</tr>" + "<tr>"
				+ "<td>dmunnoz96@gmail.com</td>"
				+ "<td>Domingo Muñoz Daza</td>" + "</tr>" + "<tr>"
				+ "<td>josedaniel.solanopuech@gmail.com</td>"
				+ "<td>José Daniel Solano Puech</td>" + "</tr>" + "<tr>"
				+ "<td>jose-antonio-1110@hotmail.com</td>"
				+ "<td>José Sosa Cifuentes</td>" + "</tr></table> ";

		HTML games = new HTML(output);
		games.setStyleName("alumnoTable");
		RootPanel.get("table").add(games);
	}

}