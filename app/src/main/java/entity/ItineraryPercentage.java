
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItineraryPercentage  implements Serializable {

    @SerializedName("itinerary_attraction_count")
    @Expose
    private String itineraryAttractionCount;
    @SerializedName("itinerary_attraction_type")
    @Expose
    private String itineraryAttractionType;
    @SerializedName("itinerary_attraction_type_id")
    @Expose
    private String itineraryAttractionTypeId;
    @SerializedName("itinerary_attraction_type_percentage")
    @Expose
    private String itineraryAttractionTypePercentage;
    @SerializedName("itinerary_attraction_Type_total")
    @Expose
    private String itineraryAttractionTypeTotal;

    public String getItineraryAttractionCount() {
        return itineraryAttractionCount;
    }

    public void setItineraryAttractionCount(String itineraryAttractionCount) {
        this.itineraryAttractionCount = itineraryAttractionCount;
    }

    public String getItineraryAttractionType() {
        return itineraryAttractionType;
    }

    public void setItineraryAttractionType(String itineraryAttractionType) {
        this.itineraryAttractionType = itineraryAttractionType;
    }

    public String getItineraryAttractionTypeId() {
        return itineraryAttractionTypeId;
    }

    public void setItineraryAttractionTypeId(String itineraryAttractionTypeId) {
        this.itineraryAttractionTypeId = itineraryAttractionTypeId;
    }

    public String getItineraryAttractionTypePercentage() {
        return itineraryAttractionTypePercentage;
    }

    public void setItineraryAttractionTypePercentage(String itineraryAttractionTypePercentage) {
        this.itineraryAttractionTypePercentage = itineraryAttractionTypePercentage;
    }

    public String getItineraryAttractionTypeTotal() {
        return itineraryAttractionTypeTotal;
    }

    public void setItineraryAttractionTypeTotal(String itineraryAttractionTypeTotal) {
        this.itineraryAttractionTypeTotal = itineraryAttractionTypeTotal;
    }

}
