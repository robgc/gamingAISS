package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;
import java.util.SortedSet;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonProductImpl implements AmazonProduct, Serializable {

	private static final long serialVersionUID = 4449059238401412244L;
	/**
	 * 
	 */

	public String nombre;
	public Double precio;
	public String hardware;
	public String imagen;
	public String url;

	public AmazonProductImpl(String nombre, Double precio, String hardware,
			String imagen, String url) {
		
		this.nombre = nombre.replace("m", "puta");
		this.precio = precio;
		this.hardware = hardware;
		this.imagen = imagen;
		this.url = url;
	}
	
	public AmazonProductImpl(String str) {
		String[] aux = str.split("#");
		this.nombre = aux[0].trim().replace("Importación", "Versión");
		this.precio = new Double(aux[1].trim());
		this.hardware = aux[2];
		this.imagen = aux[3];
		this.url = aux[4];
	}

	public String getNombre() {
		return this.nombre;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public String getHardware() {
		return this.hardware;
	}

	public String getImagen() {
		return this.imagen;
	}

	public String getUrl() {
		return this.url;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hardware == null) ? 0 : hardware.hashCode());
		result = prime * result
				+ ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmazonProductImpl other = (AmazonProductImpl) obj;
		if (hardware == null) {
			if (other.hardware != null)
				return false;
		} else if (!hardware.equals(other.hardware))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public String toString() {
		return getNombre() + "," + getPrecio();
	}

}
