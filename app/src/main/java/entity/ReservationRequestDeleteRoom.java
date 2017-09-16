
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationRequestDeleteRoom implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_reservation_req_delete")
    @Expose
    private List<Object> resultReservationReqDelete = null;
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

    public List<Object> getResultReservationReqDelete() {
        return resultReservationReqDelete;
    }

    public void setResultReservationReqDelete(List<Object> resultReservationReqDelete) {
        this.resultReservationReqDelete = resultReservationReqDelete;
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
