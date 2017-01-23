
package entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItineraryList  implements Serializable {
    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_Itinerary")
    @Expose
    private List<ResultItinerary> resultItinerary = null;
    @SerializedName("Statistics")
    @Expose
    private Statistics statistics;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ResultItinerary> getResultItinerary() {
        return resultItinerary;
    }

    public void setResultItinerary(List<ResultItinerary> resultItinerary) {
        this.resultItinerary = resultItinerary;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}


