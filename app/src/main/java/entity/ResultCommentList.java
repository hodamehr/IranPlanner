
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultCommentList implements Serializable {
    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_comment")
    @Expose
    private List<ResultComment> resultComment = null;
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

    public List<ResultComment> getResultComment() {
        return resultComment;
    }

    public void setResultComment(List<ResultComment> resultComment) {
        this.resultComment = resultComment;
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

//    @SerializedName("Status")
//    @Expose
//    private Status status;
//    @SerializedName("Result_comment")
//    @Expose
//    private List<ResultComment> resultComment = null;
//    @SerializedName("Statistics")
//    @Expose
//    private Statistics statistics;
//    @SerializedName("ParsDID")
//    @Expose
//    private ParsDID parsDID;
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public List<ResultComment> getResultComment() {
//        return resultComment;
//    }
//
//    public void setResultComment(List<ResultComment> resultComment) {
//        this.resultComment = resultComment;
//    }
//
//    public Statistics getStatistics() {
//        return statistics;
//    }
//
//    public void setStatistics(Statistics statistics) {
//        this.statistics = statistics;
//    }
//
//    public ParsDID getParsDID() {
//        return parsDID;
//    }
//
//    public void setParsDID(ParsDID parsDID) {
//        this.parsDID = parsDID;
//    }

}
