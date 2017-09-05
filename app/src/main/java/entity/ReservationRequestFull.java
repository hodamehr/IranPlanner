
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationRequestFull implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_reservation_req_full")
    @Expose
    private List<ResultReservationReqFull> resultReservationReqFull = null;
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

    public List<ResultReservationReqFull> getResultReservationReqFull() {
        return resultReservationReqFull;
    }

    public void setResultReservationReqFull(List<ResultReservationReqFull> resultReservationReqFull) {
        this.resultReservationReqFull = resultReservationReqFull;
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
