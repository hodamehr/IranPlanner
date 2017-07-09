
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultHome implements Serializable{

    @SerializedName("home_info")
    @Expose
    private HomeInfo homeInfo;
    @SerializedName("home_event")
    @Expose
    private List<Object> homeEvent = null;
    @SerializedName("home_lodging")
    @Expose
    private List<HomeLodging> homeLodging = null;
    @SerializedName("home_lodging_type")
    @Expose
    private List<HomeLodgingType> homeLodgingType = null;
    @SerializedName("home_attraction")
    @Expose
    private List<HomeAttraction> homeAttraction = null;
    @SerializedName("home_attraction_type")
    @Expose
    private List<HomeAttractionType> homeAttractionType = null;
    @SerializedName("home_souvenirs")
    @Expose
    private List<HomeSouvenir> homeSouvenirs = null;
    @SerializedName("home_localfood")
    @Expose
    private List<HomeLocalfood> homeLocalfood = null;
    @SerializedName("home_airport")
    @Expose
    private List<Object> homeAirport = null;

    public HomeInfo getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(HomeInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public List<Object> getHomeEvent() {
        return homeEvent;
    }

    public void setHomeEvent(List<Object> homeEvent) {
        this.homeEvent = homeEvent;
    }

    public List<HomeLodging> getHomeLodging() {
        return homeLodging;
    }

    public void setHomeLodging(List<HomeLodging> homeLodging) {
        this.homeLodging = homeLodging;
    }

    public List<HomeLodgingType> getHomeLodgingType() {
        return homeLodgingType;
    }

    public void setHomeLodgingType(List<HomeLodgingType> homeLodgingType) {
        this.homeLodgingType = homeLodgingType;
    }

    public List<HomeAttraction> getHomeAttraction() {
        return homeAttraction;
    }

    public void setHomeAttraction(List<HomeAttraction> homeAttraction) {
        this.homeAttraction = homeAttraction;
    }

    public List<HomeAttractionType> getHomeAttractionType() {
        return homeAttractionType;
    }

    public void setHomeAttractionType(List<HomeAttractionType> homeAttractionType) {
        this.homeAttractionType = homeAttractionType;
    }

    public List<HomeSouvenir> getHomeSouvenirs() {
        return homeSouvenirs;
    }

    public void setHomeSouvenirs(List<HomeSouvenir> homeSouvenirs) {
        this.homeSouvenirs = homeSouvenirs;
    }

    public List<HomeLocalfood> getHomeLocalfood() {
        return homeLocalfood;
    }

    public void setHomeLocalfood(List<HomeLocalfood> homeLocalfood) {
        this.homeLocalfood = homeLocalfood;
    }

    public List<Object> getHomeAirport() {
        return homeAirport;
    }

    public void setHomeAirport(List<Object> homeAirport) {
        this.homeAirport = homeAirport;
    }

}
