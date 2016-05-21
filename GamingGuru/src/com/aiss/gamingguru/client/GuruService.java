package com.aiss.gamingguru.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiss.gamingguru.shared.Vg.Videojuego;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("guru")
public interface GuruService extends RemoteService {
	// CriticSearch getReviews(String juego);

	GameSearch getGames(String id);

	GameData getNameId();

	Map<Videojuego, List<String>> getAmazon(Set<Videojuego> juegos);
	
	Map<String, String> getScores(String juego);

	Map<Integer, Videojuego> fillMap();

	Set<Videojuego> getMyGames(Set<Integer> ids, Map<Integer, Videojuego> map);
	
	Set<Videojuego> recommendedGames(Double score, String cat1,
			String cat2, Set<Videojuego> vgs);

}
