
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultRegister implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_register")
    @Expose
    private ResultUserRegister resultUserRegister;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserRegister getResultUserRegister() {
        return resultUserRegister;
    }

    public void setResultUserRegister(ResultUserRegister resultUserRegister) {
        this.resultUserRegister = resultUserRegister;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
