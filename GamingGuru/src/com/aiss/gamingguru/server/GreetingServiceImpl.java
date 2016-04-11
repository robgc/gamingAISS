package com.aiss.gamingguru.server;

import java.util.ArrayList;
import java.util.List;

import com.aiss.gamingguru.client.GreetingService;
import com.aiss.gamingguru.shared.Alumno;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	List<Alumno> lista;

	public void init() {
		lista = new ArrayList<Alumno>();
		lista.add(new Alumno("Roberto García Calero", "rodanber@gmail.com"));
		lista.add(new Alumno("Domingo Muñoz Daza", 
				"dmunnoz96@gmail.com"));
		lista.add(new Alumno("José Daniel Solano Puech",  "josedaniel.solanopuech@gmail.com"));
		lista.add(new Alumno("José Sosa Cifuentes",  "jose-antonio-1110@hotmail.com"));

	}

	@Override
	public List<Alumno> getAlumnos() {
		// TODO Auto-generated method stub
		return lista;
	}

}
