package com.aiss.gamingguru.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.aiss.gamingguru.api.resources.UserResource;

public class GuruApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public GuruApplication() {
		singletons.add(UserResource.getInstance());
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}
	
}
