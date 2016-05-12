package com.aiss.gamingguru.client;

import java.util.List;

import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("alumno")
public interface GreetingService extends RemoteService {
	List<Alumno> getAlumnos();
}