
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItineraryAttractionDay implements Serializable {

    @SerializedName("itinerary_id")
    @Expose
    private String itineraryId;
    @SerializedName("itinerary_dayplan_name")
    @Expose
    private String itineraryDayplanName;
    @SerializedName("itinerary_daylight_name")
    @Expose
    private String itineraryDaylightName;
    @SerializedName("itinerary_daylight_weight")
    @Expose
    private String itineraryDaylightWeight;
    @SerializedName("Resul_Attraction")
    @Expose
    private ResulAttraction resulAttraction;
    @SerializedName("Result_attraction_list")
    @Expose
    private List<ResultAttractionList> resultAttractionList = null;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryDayplanName() {
        return itineraryDayplanName;
    }

    public void setItineraryDayplanName(String itineraryDayplanName) {
        this.itineraryDayplanName = itineraryDayplanName;
    }

    public String getItineraryDaylightName() {
        return itineraryDaylightName;
    }

    public void setItineraryDaylightName(String itineraryDaylightName) {
        this.itineraryDaylightName = itineraryDaylightName;
    }

    public String getItineraryDaylightWeight() {
        return itineraryDaylightWeight;
    }

    public void setItineraryDaylightWeight(String itineraryDaylightWeight) {
        this.itineraryDaylightWeight = itineraryDaylightWeight;
    }

    public ResulAttraction getResulAttraction() {
        return resulAttraction;
    }

    public void setResulAttraction(ResulAttraction resulAttraction) {
        this.resulAttraction = resulAttraction;
    }

    public List<ResultAttractionList> getResultAttractionList() {
        return resultAttractionList;
    }

    public void setResultAttractionList(List<ResultAttractionList> resultAttractionList) {
        this.resultAttractionList = resultAttractionList;
    }

}
