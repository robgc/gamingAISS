package com.aiss.gamingguru.shared.Vg;

import java.io.Serializable;
import java.sql.SQLException;

import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class Videojuego implements Serializable {
	private static final long serialVersionUID = 949294771241240845L;

	public Integer id;
	public String nombre;
	public Double notaMedia;
	public String[] tags;

	public Videojuego(String videojuego) {
		String[] parts = videojuego.split("#");
		this.id = new Integer(parts[0]);
		this.nombre = parts[1].trim();
		this.notaMedia = new Double(parts[2]);
		String[] tags = parts[3].split("&");
		this.tags = tags;
	}
	
	public Videojuego (Statement s){
		try {
			ResultSet rs = s.executeQuery("SELECT * FROM GAMES;");
			this.id = rs.getInt("id");
			this.nombre = rs.getString("name");
			this.notaMedia = rs.getDouble("score");
			this.tags = rs.getString("tags").split("&");
			
		} catch (SQLException e) {
			System.out.println("no se ha podido acceder al sql");
			e.printStackTrace();
		}
	}

	public Videojuego(Integer id, String nombre, Double notaMedia, String[] tags) {
		this.id = id;
		this.nombre = nombre;
		this.notaMedia = notaMedia;
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		res += this.id.compareTo(v.getId());
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
		res += this.id.hashCode();
		res += this.nombre.hashCode();
		res += this.notaMedia.hashCode();
		res += this.tags.hashCode();
		res = res * 31;
		return res;
	}

	public Boolean equals(Videojuego v) {
		Boolean res = false;
		if (this.id.equals(v.id) && this.nombre.equals(v.nombre) && this.notaMedia.equals(v.notaMedia)
				&& this.tags.equals(v.tags)) {
			res = true;
		}
		return res;
	}
}
