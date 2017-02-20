
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWidgetFull {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_widget")
    @Expose
    private ResultWidget resultWidget;
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

    public ResultWidget getResultWidget() {
        return resultWidget;
    }

    public void setResultWidget(ResultWidget resultWidget) {
        this.resultWidget = resultWidget;
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
