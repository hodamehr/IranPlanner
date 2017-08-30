
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReservationReqStatus implements Serializable {

    @SerializedName("status_title")
    @Expose
    private String statusTitle;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("status_count")
    @Expose
    private String statusCount;

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(String statusCount) {
        this.statusCount = statusCount;
    }

}
