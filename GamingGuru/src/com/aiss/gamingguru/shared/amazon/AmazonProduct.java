package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;
import java.util.SortedSet;

public interface AmazonProduct extends Serializable {
	
	String getNombre();

	Double getPrecio();

	String getHardware();

	String getImagen ();

	String getUrl();

	void setNombre(String nombre);

	void setPrecio(Double precio);

	void setHardware(String hardware);

	void setImagen (String imagen);
	
	void setUrl(String url);
}
