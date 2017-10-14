
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultResetPasswords implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_reset_password")
    @Expose
    private ResultResetPassword resultResetPassword;
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

    public ResultResetPassword getResultResetPassword() {
        return resultResetPassword;
    }

    public void setResultResetPassword(ResultResetPassword resultResetPassword) {
        this.resultResetPassword = resultResetPassword;
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
