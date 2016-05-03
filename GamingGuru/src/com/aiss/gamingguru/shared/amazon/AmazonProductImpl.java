package com.aiss.gamingguru.shared.amazon;

import java.util.SortedSet;

public class AmazonProductImpl implements AmazonProduct {
	public String nombre;
	public SortedSet<String> precios;
	public String hardware;
	public SortedSet<String> imagenes;
	public String url;

	public String getNombre() {
		return this.nombre;
	}

	public SortedSet<String> getPrecios() {
		return this.precios;
	}

	public String getHardware() {
		return this.hardware;
	}

	public SortedSet<String> getImagenes() {
		return this.imagenes;
	}

	public String getUrl() {
		return this.url;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecios(SortedSet<String> precios) {
		this.precios = precios;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public void setImagenes(SortedSet<String> imagenes) {
		this.imagenes = imagenes;
	}

}
