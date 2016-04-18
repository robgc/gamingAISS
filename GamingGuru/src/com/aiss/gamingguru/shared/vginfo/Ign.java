
package com.aiss.gamingguru.shared.vginfo;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ign implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -970649864756180540L;
	private String criticScore;
    private String userScore;
    private String url;

    /**
     * 
     * @return
     *     The criticScore
     */
    public String getCriticScore() {
        return criticScore;
    }

    /**
     * 
     * @param criticScore
     *     The criticScore
     */
    public void setCriticScore(String criticScore) {
        this.criticScore = criticScore;
    }

    /**
     * 
     * @return
     *     The userScore
     */
    public String getUserScore() {
        return userScore;
    }

    /**
     * 
     * @param userScore
     *     The userScore
     */
    public void setUserScore(String userScore) {
        this.userScore = userScore;
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
