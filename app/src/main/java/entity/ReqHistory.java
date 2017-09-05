
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReqHistory implements Serializable{

    @SerializedName("req_statud_id")
    @Expose
    private String reqStatudId;
    @SerializedName("req_statud_title")
    @Expose
    private String reqStatudTitle;
    @SerializedName("req_description")
    @Expose
    private String reqDescription;
    @SerializedName("req_statud_expire")
    @Expose
    private String reqStatudExpire;
    @SerializedName("req_timestamp")
    @Expose
    private String reqTimestamp;
    @SerializedName("req_operation_id")
    @Expose
    private String reqOperationId;
    @SerializedName("req_operation_title")
    @Expose
    private String reqOperationTitle;

    public String getReqStatudId() {
        return reqStatudId;
    }

    public void setReqStatudId(String reqStatudId) {
        this.reqStatudId = reqStatudId;
    }

    public String getReqStatudTitle() {
        return reqStatudTitle;
    }

    public void setReqStatudTitle(String reqStatudTitle) {
        this.reqStatudTitle = reqStatudTitle;
    }

    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }

    public String getReqStatudExpire() {
        return reqStatudExpire;
    }

    public void setReqStatudExpire(String reqStatudExpire) {
        this.reqStatudExpire = reqStatudExpire;
    }

    public String getReqTimestamp() {
        return reqTimestamp;
    }

    public void setReqTimestamp(String reqTimestamp) {
        this.reqTimestamp = reqTimestamp;
    }

    public String getReqOperationId() {
        return reqOperationId;
    }

    public void setReqOperationId(String reqOperationId) {
        this.reqOperationId = reqOperationId;
    }

    public String getReqOperationTitle() {
        return reqOperationTitle;
    }

    public void setReqOperationTitle(String reqOperationTitle) {
        this.reqOperationTitle = reqOperationTitle;
    }

}
