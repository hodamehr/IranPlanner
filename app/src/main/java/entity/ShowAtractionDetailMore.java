
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShowAtractionDetailMore implements Serializable{

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_Attraction_Full")
    @Expose
    private ResultAttractionFull resultAttractionFull;
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

    public ResultAttractionFull getResultAttractionFull() {
        return resultAttractionFull;
    }

    public void setResultAttractionFull(ResultAttractionFull resultAttractionFull) {
        this.resultAttractionFull = resultAttractionFull;
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
