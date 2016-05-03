package com.aiss.gamingguru.shared.amazon;

import java.util.SortedSet;

public interface AmazonProduct {
	String getNombre();

	SortedSet<String> getPrecios();

	String getHardware();

	SortedSet<String> getImagenes();

	String getUrl();

	void setNombre(String nombre);

	void setPrecios(SortedSet<String> precios);

	void setHardware(String hardware);

	void setImagenes(SortedSet<String> imagenes);
}
