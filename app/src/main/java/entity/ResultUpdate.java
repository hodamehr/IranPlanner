
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUpdate {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_update")
    @Expose
    private ResultUserUpdate resultUserUpdate;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserUpdate getResultUserUpdate() {
        return resultUserUpdate;
    }

    public void setResultUserUpdate(ResultUserUpdate resultUserUpdate) {
        this.resultUserUpdate = resultUserUpdate;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
