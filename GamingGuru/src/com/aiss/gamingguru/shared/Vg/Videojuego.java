package com.aiss.gamingguru.shared.Vg;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Videojuego implements Serializable {
	private static final long serialVersionUID = 949294771241240845L;

	private String nombre;
	private Double notaMedia;
	private String[] tags;

	public Videojuego() {
	}

	public Videojuego(String videojuego) {
		String[] parts = videojuego.split("#");
		this.nombre = parts[0].trim();
		this.notaMedia = new Double(parts[2]);
		String[] tags = parts[1].split("&");
		this.tags = tags;
	}

	public Videojuego(String nombre, Double notaMedia, String[] tags) {
		this.nombre = nombre;
		this.notaMedia = notaMedia;
		this.tags = tags;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(Double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer compareTo(Videojuego v) {
		Integer res = 0;
		res += this.nombre.compareTo(v.getNombre());
		res += this.notaMedia.compareTo(v.getNotaMedia());
		String[] tags = v.getTags();
		for (int i = 0; i < tags.length; i++) {
			res += this.tags[i].compareTo(tags[i]);
		}
		return res;
	}

	public Integer hashcode() {
		Integer res = 0;
		res += this.nombre.hashCode();
		res += this.notaMedia.hashCode();
		res += this.tags.hashCode();
		res = res * 31;
		return res;
	}

	public Boolean equals(Videojuego v) {
		Boolean res = false;
		if (this.nombre.equals(v.nombre) && this.notaMedia.equals(v.notaMedia)
				&& this.tags.equals(v.tags)) {
			res = true;
		}
		return res;
	}

	public String toString() {
		return getNombre() + " - " + getNotaMedia();
	}
}
