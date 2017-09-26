
package entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLodgingRoomList implements Serializable {


    @SerializedName("req_bundle_order")
    @Expose
    private String reqBundleOrdwe;

    @SerializedName("req_id")
    @Expose
    private String reqId;

    public String getReqBundleOrdwe() {
        return reqBundleOrdwe;
    }

    public void setReqBundleOrdwe(String reqBundleOrdwe) {
        this.reqBundleOrdwe = reqBundleOrdwe;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getReqBundleId() {
        return reqBundleId;
    }

    public void setReqBundleId(String reqBundleId) {
        this.reqBundleId = reqBundleId;
    }

    @SerializedName("req_status")

    @Expose
    private String reqStatus;
    @SerializedName("req_bundle_id")
    @Expose
    private String reqBundleId;


    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_room")
    @Expose
    private List<ResultRoom> resultRoom = null;
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

    public List<ResultRoom> getResultRoom() {
        return resultRoom;
    }

    public void setResultRoom(List<ResultRoom> resultRoom) {
        this.resultRoom = resultRoom;
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
