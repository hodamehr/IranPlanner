
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultVerifyEmail implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_email_verify")
    @Expose
    private ResultUserEmailVerify resultUserEmailVerify;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserEmailVerify getResultUserEmailVerify() {
        return resultUserEmailVerify;
    }

    public void setResultUserEmailVerify(ResultUserEmailVerify resultUserEmailVerify) {
        this.resultUserEmailVerify = resultUserEmailVerify;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
