/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.aiss.gamingguru.shared.amazon;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ImageSetElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4428708688618701349L;
	/**
	 * 
	 */
	private HiResImage hiresimage;

 	public void setHiresimage(HiResImage hiresimage) {
		this.hiresimage = hiresimage;
	}

	public HiResImage getHiresimage() {
		return hiresimage;
	}

	private ThumbnailImage thumbnailimage;

 	public void setThumbnailimage(ThumbnailImage thumbnailimage) {
		this.thumbnailimage = thumbnailimage;
	}

	public ThumbnailImage getThumbnailimage() {
		return thumbnailimage;
	}

	private LargeImage largeimage;

 	public void setLargeimage(LargeImage largeimage) {
		this.largeimage = largeimage;
	}

	public LargeImage getLargeimage() {
		return largeimage;
	}

	private SwatchImage swatchimage;

 	public void setSwatchimage(SwatchImage swatchimage) {
		this.swatchimage = swatchimage;
	}

	public SwatchImage getSwatchimage() {
		return swatchimage;
	}

	private SmallImage smallimage;

 	public void setSmallimage(SmallImage smallimage) {
		this.smallimage = smallimage;
	}

	public SmallImage getSmallimage() {
		return smallimage;
	}

	private TinyImage tinyimage;

 	public void setTinyimage(TinyImage tinyimage) {
		this.tinyimage = tinyimage;
	}

	public TinyImage getTinyimage() {
		return tinyimage;
	}

	private MediumImage mediumimage;

 	public void setMediumimage(MediumImage mediumimage) {
		this.mediumimage = mediumimage;
	}

	public MediumImage getMediumimage() {
		return mediumimage;
	}

	private java.lang.String _category;

 	public void set_category(java.lang.String _category) {
		this._category = _category;
	}

	public java.lang.String get_category() {
		return _category;
	}

}