package com.aiss.gamingguru.client;

import java.util.List;

import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void getAlumnos(AsyncCallback<List<Alumno>> callback);
	
}
