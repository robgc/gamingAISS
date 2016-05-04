package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;
import java.util.SortedSet;

public interface AmazonProduct extends Serializable {
	
	String getNombre();

	Double getPrecio();

	String getHardware();

	SortedSet<String> getImagenes();

	String getUrl();

	void setNombre(String nombre);

	void setPrecio(Double precio);

	void setHardware(String hardware);

	void setImagenes(SortedSet<String> imagenes);
	
	void setUrl(String url);
}
