
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestHistory implements Serializable{

    @SerializedName("req_history")
    @Expose
    private ReqHistory reqHistory;

    public ReqHistory getReqHistory() {
        return reqHistory;
    }

    public void setReqHistory(ReqHistory reqHistory) {
        this.reqHistory = reqHistory;
    }

}
