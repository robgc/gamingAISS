package com.aiss.gamingguru.client;

import com.aiss.gamingguru.shared.vginfo.CriticSearch;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GuruServiceAsync {

	void getReviews(String juego, AsyncCallback<CriticSearch> callback);

}
