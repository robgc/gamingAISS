package com.aiss.gamingguru.client.views;

import java.util.Map;

import com.aiss.gamingguru.client.GuruService;
import com.aiss.gamingguru.client.GuruServiceAsync;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VideoGameInformationView extends Composite {
	private Button searchButton = new Button("Search");
	private TextBox searchField = new TextBox();
	private HorizontalPanel searchPanel = new HorizontalPanel();
	private Label statusLabel = new Label();
	private final GuruServiceAsync gService = GWT.create(GuruService.class);
	
	//TODO: Adaptar la vista para ser accesible mediante un enlace
	
	//TODO: Adaptar la vista al diseño general

	public VideoGameInformationView(Map<String, String> params) {
		searchField.setText("Game");

		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.add(statusLabel);

		RootPanel.get("form").add(searchPanel);

		searchField.setFocus(true);
		searchField.selectAll();

		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				statusLabel.setText("Searching...");

				final String game = searchField.getText();
				RootPanel.get("gameinfo").clear();

				gService.getReviews(game, new AsyncCallback<CriticSearch>() {

					@Override
					public void onSuccess(CriticSearch result) {
						showReviews(game, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("¡Error al realizar la búsqueda de las críticas!");
					}
				});
			}
		});
	}
	
	/**
	 * Muestra en pantalla la información obtenida de un videojuego.
	 * @param game Nombre del videojuego
	 * @param result Resultado (Clase JSON)
	 */
	
	private void showReviews(String game, CriticSearch result) {
		String output = "<fieldset>";
		output += "<legend>" + game + " Critics </legend>";
		if (result != null) {
			output += "<span>IGN</span>" ;
			output += "<span> User score: " + result.getResult().getIgn().getUserScore() + "</span>";
			output += "<span> Critic score: " + result.getResult().getIgn().getCriticScore() + "</span>";
			
			output += "<span>Metacritic</span>" ;
			output += "<span> User score: " + result.getResult().getMetacritic().getUserScore() + "</span>";
			output += "<span> Critic score: " + result.getResult().getMetacritic().getCriticScore() + "</span>";
		} else {
			output += "<span> No results </span>";
		}
		output += "</fieldset>";
		
		HTML games = new HTML(output);
		
		RootPanel.get("gameinfo").add(games);
	}
}
