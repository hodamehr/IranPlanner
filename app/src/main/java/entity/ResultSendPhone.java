
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultSendPhone  implements Serializable {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_token")
    @Expose
    private ResultUserToken resultUserToken;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserToken getResultUserToken() {
        return resultUserToken;
    }

    public void setResultUserToken(ResultUserToken resultUserToken) {
        this.resultUserToken = resultUserToken;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
