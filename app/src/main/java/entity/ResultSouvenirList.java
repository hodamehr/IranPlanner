
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultSouvenirList implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_Souvenir")
    @Expose
    private List<ResultSouvenir> resultSouvenir = null;
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

    public List<ResultSouvenir> getResultSouvenir() {
        return resultSouvenir;
    }

    public void setResultSouvenir(List<ResultSouvenir> resultSouvenir) {
        this.resultSouvenir = resultSouvenir;
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
