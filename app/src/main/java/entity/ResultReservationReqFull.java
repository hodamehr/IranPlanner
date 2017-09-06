
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultReservationReqFull implements Serializable{

    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("request_history")
    @Expose
    private List<RequestHistory> requestHistory = null;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<RequestHistory> getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(List<RequestHistory> requestHistory) {
        this.requestHistory = requestHistory;
    }

}
