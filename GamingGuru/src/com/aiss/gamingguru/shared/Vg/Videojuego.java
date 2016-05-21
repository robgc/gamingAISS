package com.aiss.gamingguru.shared.Vg;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Videojuego implements Serializable {
	private static final long serialVersionUID = 949294771241240845L;

	private Integer id;
	private String nombre;
	private Double notaMedia;
	private String[] tags;

	public Videojuego() {
	}

	public Videojuego(String videojuego) {
		String[] parts = videojuego.split("#");
		this.id = new Integer(parts[0].trim());
		this.nombre = parts[1].trim();
		String[] tags = parts[2].split("&");
		this.tags = tags;
		this.notaMedia = new Double(parts[3]);

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
		if (this.getNombre().equals(v.getNombre())) {
			res = true;
		}
		return res;
	}

	public String toString() {
		return getNombre() + " - " + getNotaMedia();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
