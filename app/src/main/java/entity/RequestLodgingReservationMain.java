
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestLodgingReservationMain implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_lodging_reservation")
    @Expose
    private ResultLodgingReservation resultLodgingReservation;
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

    public ResultLodgingReservation getResultLodgingReservation() {
        return resultLodgingReservation;
    }

    public void setResultLodgingReservation(ResultLodgingReservation resultLodgingReservation) {
        this.resultLodgingReservation = resultLodgingReservation;
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
