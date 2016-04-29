package com.aiss.gamingguru.client;

import com.aiss.gamingguru.shared.amazon.Amazon;
import com.aiss.gamingguru.shared.steam.GameData;
import com.aiss.gamingguru.shared.steam.GameSearch;
import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GuruServiceAsync {

	void getReviews(String juego, AsyncCallback<CriticSearch> callback);

	void getGames(String id, AsyncCallback<GameSearch> callback);

	void getNameId(AsyncCallback<GameData> callback);

	void getAmazon(String juego, AsyncCallback<String[]> asyncCallback);
}
