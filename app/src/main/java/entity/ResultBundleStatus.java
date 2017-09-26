
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultBundleStatus implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_reservation_bundle_list")
    @Expose
    private ResultReservationBundleList resultReservationBundleList;
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

    public ResultReservationBundleList getResultReservationBundleList() {
        return resultReservationBundleList;
    }

    public void setResultReservationBundleList(ResultReservationBundleList resultReservationBundleList) {
        this.resultReservationBundleList = resultReservationBundleList;
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
