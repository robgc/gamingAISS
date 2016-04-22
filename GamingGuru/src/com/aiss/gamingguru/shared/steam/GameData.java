package com.aiss.gamingguru.shared.steam;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6241836377888382248L;
	private Applist applist;

	/**
	 * 
	 * @return The applist
	 */
	public Applist getApplist() {
		return applist;
	}

	/**
	 * 
	 * @param applist
	 *            The applist
	 */
	public void setApplist(Applist applist) {
		this.applist = applist;
	}

}
