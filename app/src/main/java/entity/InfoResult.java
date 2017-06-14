
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoResult {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_login")
    @Expose
    private ResultUserInfo resultUserInfo;

    public ResultUserInfo getResultUserInfo() {
        return resultUserInfo;
    }

    public void setResultUserInfo(ResultUserInfo resultUserInfo) {
        this.resultUserInfo = resultUserInfo;
    }

    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
