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

	void getAmazon(String juego, AsyncCallback<List<String>> asyncCallback);

	void getScores(String juego, AsyncCallback<Map<String, String>> callback);

	void fillMap(AsyncCallback<Map<Integer, Videojuego>> callback);

	void getMyGames(Set<Integer> ids, Map<Integer, Videojuego> map,
			AsyncCallback<Set<Videojuego>> callback);

	void recommendedGames(Double score, String cat1, String cat2,
			Set<Integer> vgs, Map<Integer, Videojuego> map,
			AsyncCallback<Set<Integer>> callback);
}
