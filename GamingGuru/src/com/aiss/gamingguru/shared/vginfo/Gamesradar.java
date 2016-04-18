
package com.aiss.gamingguru.shared.vginfo;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gamesradar implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8270424354202453283L;
	private Integer criticScore;
    private String url;

    /**
     * 
     * @return
     *     The criticScore
     */
    public Integer getCriticScore() {
        return criticScore;
    }

    /**
     * 
     * @param criticScore
     *     The criticScore
     */
    public void setCriticScore(Integer criticScore) {
        this.criticScore = criticScore;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
