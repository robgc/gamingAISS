
package com.aiss.gamingguru.shared.vginfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Result implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4110394317130011495L;
	private List<String> websites = new ArrayList<String>();
    private List<String> genre = new ArrayList<String>();
    private List<String> developer = new ArrayList<String>();
    private List<Object> availablePlatform = new ArrayList<Object>();
    private List<String> publisher = new ArrayList<String>();
    private Double averageScore;
    private Gamesradar gamesradar;
    private Ign ign;
    private String name;
    private Metacritic metacritic;
    private String platform;
    private String thumbnail;
    private String rating;
    private String rlsdate;

    /**
     * 
     * @return
     *     The websites
     */
    public List<String> getWebsites() {
        return websites;
    }

    /**
     * 
     * @param websites
     *     The websites
     */
    public void setWebsites(List<String> websites) {
        this.websites = websites;
    }

    /**
     * 
     * @return
     *     The genre
     */
    public List<String> getGenre() {
        return genre;
    }

    /**
     * 
     * @param genre
     *     The genre
     */
    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    /**
     * 
     * @return
     *     The developer
     */
    public List<String> getDeveloper() {
        return developer;
    }

    /**
     * 
     * @param developer
     *     The developer
     */
    public void setDeveloper(List<String> developer) {
        this.developer = developer;
    }

    /**
     * 
     * @return
     *     The availablePlatform
     */
    public List<Object> getAvailablePlatform() {
        return availablePlatform;
    }

    /**
     * 
     * @param availablePlatform
     *     The availablePlatform
     */
    public void setAvailablePlatform(List<Object> availablePlatform) {
        this.availablePlatform = availablePlatform;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    public List<String> getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The averageScore
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * 
     * @param averageScore
     *     The averageScore
     */
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * 
     * @return
     *     The gamesradar
     */
    public Gamesradar getGamesradar() {
        return gamesradar;
    }

    /**
     * 
     * @param gamesradar
     *     The gamesradar
     */
    public void setGamesradar(Gamesradar gamesradar) {
        this.gamesradar = gamesradar;
    }

    /**
     * 
     * @return
     *     The ign
     */
    public Ign getIgn() {
        return ign;
    }

    /**
     * 
     * @param ign
     *     The ign
     */
    public void setIgn(Ign ign) {
        this.ign = ign;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The metacritic
     */
    public Metacritic getMetacritic() {
        return metacritic;
    }

    /**
     * 
     * @param metacritic
     *     The metacritic
     */
    public void setMetacritic(Metacritic metacritic) {
        this.metacritic = metacritic;
    }

    /**
     * 
     * @return
     *     The platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 
     * @param platform
     *     The platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The rlsdate
     */
    public String getRlsdate() {
        return rlsdate;
    }

    /**
     * 
     * @param rlsdate
     *     The rlsdate
     */
    public void setRlsdate(String rlsdate) {
        this.rlsdate = rlsdate;
    }

}
