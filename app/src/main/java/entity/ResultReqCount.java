
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultReqCount implements Serializable {

    @SerializedName("reservation_req_status")
    @Expose
    private ReservationReqStatus reservationReqStatus;

    public ReservationReqStatus getReservationReqStatus() {
        return reservationReqStatus;
    }

    public void setReservationReqStatus(ReservationReqStatus reservationReqStatus) {
        this.reservationReqStatus = reservationReqStatus;
    }

}
