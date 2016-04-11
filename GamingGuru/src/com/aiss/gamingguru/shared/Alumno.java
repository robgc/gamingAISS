package com.aiss.gamingguru.shared;

import java.io.Serializable;

public class Alumno implements Serializable {
	private static final long serialVersionUID = -4701191347902143352L;
	String name;
	String email;
	String dni;

	public Alumno() {

	}

	public Alumno(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String name) {
		this.email = name;
	}

}
