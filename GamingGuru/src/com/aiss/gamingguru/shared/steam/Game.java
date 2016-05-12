
package com.aiss.gamingguru.shared.steam;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Game implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2496169736898115712L;
	private Integer appid;
    private Integer playtimeForever;

    /**
     * 
     * @return
     *     The appid
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 
     * @param appid
     *     The appid
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * 
     * @return
     *     The playtimeForever
     */
    public Integer getPlaytimeForever() {
        return playtimeForever;
    }

    /**
     * 
     * @param playtimeForever
     *     The playtime_forever
     */
    public void setPlaytimeForever(Integer playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

}
