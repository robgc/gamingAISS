/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HiResImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -143950167065093257L;
	/**
	 * 
	 */
	private Width width;

 	public void setWidth(Width width) {
		this.width = width;
	}

	public Width getWidth() {
		return width;
	}

	private Height height;

 	public void setHeight(Height height) {
		this.height = height;
	}

	public Height getHeight() {
		return height;
	}

	private java.lang.String url;

 	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getUrl() {
		return url;
	}

}