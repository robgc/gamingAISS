package com.aiss.gamingguru.client;

import java.util.List;
import java.util.Set;

import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GuruServiceAsync {

	void getReviews(String juego, AsyncCallback<CriticSearch> callback);

	void getGames(String id, AsyncCallback<GameSearch> callback);

	void getNameId(AsyncCallback<GameData> callback);

}
