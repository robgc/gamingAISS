/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7579053908199013175L;
	/**
	 * 
	 */
	private Offers offers;

 	public void setOffers(Offers offers) {
		this.offers = offers;
	}

	public Offers getOffers() {
		return offers;
	}

	private LargeImage largeimage;

 	public void setLargeimage(LargeImage largeimage) {
		this.largeimage = largeimage;
	}

	public LargeImage getLargeimage() {
		return largeimage;
	}

	private ItemAttributes itemattributes;

 	public void setItemattributes(ItemAttributes itemattributes) {
		this.itemattributes = itemattributes;
	}

	public ItemAttributes getItemattributes() {
		return itemattributes;
	}

	private SmallImage smallimage;

 	public void setSmallimage(SmallImage smallimage) {
		this.smallimage = smallimage;
	}

	public SmallImage getSmallimage() {
		return smallimage;
	}

	private OfferSummary offersummary;

 	public void setOffersummary(OfferSummary offersummary) {
		this.offersummary = offersummary;
	}

	public OfferSummary getOffersummary() {
		return offersummary;
	}

	private MediumImage mediumimage;

 	public void setMediumimage(MediumImage mediumimage) {
		this.mediumimage = mediumimage;
	}

	public MediumImage getMediumimage() {
		return mediumimage;
	}

	private ImageSets imagesets;

 	public void setImagesets(ImageSets imagesets) {
		this.imagesets = imagesets;
	}

	public ImageSets getImagesets() {
		return imagesets;
	}

	private java.lang.String detailpageurl;

 	public void setDetailpageurl(java.lang.String detailpageurl) {
		this.detailpageurl = detailpageurl;
	}

	public java.lang.String getDetailpageurl() {
		return detailpageurl;
	}

	private java.lang.String asin;

 	public void setAsin(java.lang.String asin) {
		this.asin = asin;
	}

	public java.lang.String getAsin() {
		return asin;
	}

	private java.lang.String parentasin;

 	public void setParentasin(java.lang.String parentasin) {
		this.parentasin = parentasin;
	}

	public java.lang.String getParentasin() {
		return parentasin;
	}

}