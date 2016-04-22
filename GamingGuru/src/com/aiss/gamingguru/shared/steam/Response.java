
package com.aiss.gamingguru.shared.steam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Response implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8274434253833921925L;
	private Integer gameCount;
    private List<Game> games = new ArrayList<Game>();

    /**
     * 
     * @return
     *     The gameCount
     */
    public Integer getGameCount() {
        return gameCount;
    }

    /**
     * 
     * @param gameCount
     *     The game_count
     */
    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    /**
     * 
     * @return
     *     The games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * 
     * @param games
     *     The games
     */
    public void setGames(List<Game> games) {
        this.games = games;
    }

}
