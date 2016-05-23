package com.aiss.gamingguru.domain;

import javax.persistence.Id;

public class User {
	@Id
	private String id;
	private Double score;
	private String steamid;
	
	public User() {}
	
	public User(String id, Double score, String steamid) {
		super();
		this.id = id;
		this.score = score;
		this.steamid = steamid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getSteamid() {
		return steamid;
	}

	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}
	
}
