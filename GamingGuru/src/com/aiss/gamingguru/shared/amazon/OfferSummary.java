/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OfferSummary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2246479312332791468L;
	/**
	 * 
	 */
	private LowestNewPrice lowestnewprice;

 	public void setLowestnewprice(LowestNewPrice lowestnewprice) {
		this.lowestnewprice = lowestnewprice;
	}

	public LowestNewPrice getLowestnewprice() {
		return lowestnewprice;
	}

	private java.lang.String totalcollectible;

 	public void setTotalcollectible(java.lang.String totalcollectible) {
		this.totalcollectible = totalcollectible;
	}

	public java.lang.String getTotalcollectible() {
		return totalcollectible;
	}

	private java.lang.String totalrefurbished;

 	public void setTotalrefurbished(java.lang.String totalrefurbished) {
		this.totalrefurbished = totalrefurbished;
	}

	public java.lang.String getTotalrefurbished() {
		return totalrefurbished;
	}

	private java.lang.String totalnew;

 	public void setTotalnew(java.lang.String totalnew) {
		this.totalnew = totalnew;
	}

	public java.lang.String getTotalnew() {
		return totalnew;
	}

	private java.lang.String totalused;

 	public void setTotalused(java.lang.String totalused) {
		this.totalused = totalused;
	}

	public java.lang.String getTotalused() {
		return totalused;
	}

}