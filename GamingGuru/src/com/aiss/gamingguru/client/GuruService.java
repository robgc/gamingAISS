package com.aiss.gamingguru.client;

import java.util.List;

import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("guru")
public interface GuruService extends RemoteService {
	CriticSearch getReviews(String juego);

	GameSearch getGames(String id);

	GameData getNameId();
	
	List<String> getAmazon(String juego);
}
