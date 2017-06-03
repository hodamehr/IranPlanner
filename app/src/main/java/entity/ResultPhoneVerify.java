
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultPhoneVerify implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_verify")
    @Expose
    private ResultUserVerify resultUserVerify;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserVerify getResultUserVerify() {
        return resultUserVerify;
    }

    public void setResultUserVerify(ResultUserVerify resultUserVerify) {
        this.resultUserVerify = resultUserVerify;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
