package com.aiss.gamingguru.client.views;

import java.util.List;

import com.aiss.gamingguru.client.GreetingService;
import com.aiss.gamingguru.client.GreetingServiceAsync;
import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewAcerca extends Composite {

	private final GreetingServiceAsync GreetingService = GWT
			.create(GreetingService.class);

	private final VerticalPanel mainPanel;
	private final FlexTable alumnosPanel;
	private final FlexTable descripcionPanel;

	public ViewAcerca() {
		mainPanel = new VerticalPanel();
		initWidget(mainPanel);
		alumnosPanel = new FlexTable();
		descripcionPanel = new FlexTable();
		
		mainPanel.setStyleName("panel");
		
		alumnosPanel.setStylePrimaryName("alumnoTable");
		alumnosPanel.getRowFormatter().setStylePrimaryName(0, "firstRow");
		alumnosPanel.setWidget(0, 0, new Label("Nombre"));
		alumnosPanel.setWidget(0, 1, new Label("Email"));

		GreetingService.getAlumnos(new AsyncCallback<List<Alumno>>() {

			public void onSuccess(List<Alumno> result) {
				showAlumnos(result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error loading contacts: " + caught.getMessage());

			}
		});
		mainPanel.add(alumnosPanel);

		descripcionPanel.setStylePrimaryName("descripcionTable");
		descripcionPanel.setWidget(1, 0, new Label("\n"));
		
		com.google.gwt.user.client.ui.Image mando = new com.google.gwt.user.client.ui.Image(
				"files/mando.png");
		mando.setHeight("248px");
		mando.setWidth("266px");
		
		descripcionPanel.setWidget(2, 0, mando);
		
		Label titulo = new Label("\nGAMINGURU");
		titulo.setStylePrimaryName("titulo");

		descripcionPanel.setWidget(3, 0, titulo);
		descripcionPanel
				.setWidget(
						4,
						0,
						new Label(
								"\nPresentamos GamingGuru. Una aplicación capaz de analizar tu "
										+ "\nbiblioteca de Steam y ofrecerte recomendaciones personalizadas"
										+ "\nsobre los juegos de tu género favorito en función de los valores "
										+ "\nanalíticos de los juegos de dicha biblioteca. También te ofreceremos "
										+ "\nenlaces directos a la compra de tus recomendaciones en "
										+ "\nplataformas como Amazon o la propia tienda de Steam dejando a "
										+ "\ntu elección para qué plataforma deseas hacer la compra."));

		mainPanel.add(descripcionPanel);

	};

	public final void showAlumnos(List<Alumno> AlumnosSimple) {

		int i = 0;
		for (Alumno key : AlumnosSimple) {
			alumnosPanel.setWidget(i + 1, 0, new Label(key.getName()));
			alumnosPanel.setWidget(i + 1, 1, new Label(key.getEmail()));
			i++;
		}

	}
}
