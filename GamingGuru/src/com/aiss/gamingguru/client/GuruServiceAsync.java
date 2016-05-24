package com.aiss.gamingguru.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.shared.Vg.Videojuego;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GuruServiceAsync {

	// void getReviews(String juego, AsyncCallback<CriticSearch> callback);

	void getGames(String id, AsyncCallback<GameSearch> callback);

	void getNameId(AsyncCallback<GameData> callback);

	void getAmazon(Set<String> platforms, Set<Videojuego> juegos,
			AsyncCallback<Map<Videojuego, Set<String>>> asyncCallback);

	void getScores(Set<Videojuego> vgs,
			AsyncCallback<Map<Videojuego, List<String>>> callback);

	void fillMap(AsyncCallback<Map<Integer, Videojuego>> callback);

	void getMyGames(Set<Integer> ids, Map<Integer, Videojuego> map,
			AsyncCallback<Set<Videojuego>> callback);

	void recommendedGames(Double score, String cat1, String cat2,
			Set<Videojuego> vgs, AsyncCallback<Set<Videojuego>> callback);
}
