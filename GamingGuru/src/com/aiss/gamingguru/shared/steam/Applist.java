
package com.aiss.gamingguru.shared.steam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Applist implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5358588058245624902L;
	private List<App> apps = new ArrayList<App>();

    /**
     * 
     * @return
     *     The apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * 
     * @param apps
     *     The apps
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

}
