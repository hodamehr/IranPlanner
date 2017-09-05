
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationRequestList implements Serializable {

    public static final String INTENT_KEY_RESULT_RESERVATION = "intent_key_reservation";

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_reservation_req_list")
    @Expose
    private List<ResultReservationReqList> resultReservationReqList = null;
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

    public List<ResultReservationReqList> getResultReservationReqList() {
        return resultReservationReqList;
    }

    public void setResultReservationReqList(List<ResultReservationReqList> resultReservationReqList) {
        this.resultReservationReqList = resultReservationReqList;
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
