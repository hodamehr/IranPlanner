
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowAttractionMoreList implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_attraction_list")
    @Expose
    private List<ResultAttractionList> resultAttractionList = null;
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

    public List<ResultAttractionList> getResultAttractionList() {
        return resultAttractionList;
    }

    public void setResultAttractionList(List<ResultAttractionList> resultAttractionList) {
        this.resultAttractionList = resultAttractionList;
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
