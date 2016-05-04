package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;
import java.util.SortedSet;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)

public class AmazonProductImpl implements AmazonProduct, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String nombre;
	public Double precio;
	public String hardware;
	public SortedSet<String> imagenes;
	public String url;

	public AmazonProductImpl(String nombre, Double precio, String hardware, SortedSet<String> imagenes,
			String url) {
		this.nombre = nombre;
		this.precio = precio;
		this.hardware = hardware;
		this.imagenes = imagenes;
		this.url = url;
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

	public SortedSet<String> getImagenes() {
		return this.imagenes;
	}

	public String getUrl() {
		return this.url;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio (Double precio) {
		this.precio = precio;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public void setImagenes(SortedSet<String> imagenes) {
		this.imagenes = imagenes;
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
				+ ((imagenes == null) ? 0 : imagenes.hashCode());
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
		if (imagenes == null) {
			if (other.imagenes != null)
				return false;
		} else if (!imagenes.equals(other.imagenes))
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
		return getNombre() + ", " + precio;
	}

}
